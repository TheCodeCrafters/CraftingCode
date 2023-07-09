package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.Context;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VmException;
import io.github.thecodecrafters.craftingcode.lua.convert.LuaConverter;
import io.github.thecodecrafters.craftingcode.lua.foreign.lua_Alloc;
import jdk.incubator.foreign.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.thecodecrafters.craftingcode.lua.ThrowableUtil.sneakyThrow;
import static io.github.thecodecrafters.craftingcode.lua.foreign.Lua.*;

public class LuaContext implements Context {
    static {
        System.loadLibrary("cc_lua_alloc");
    }
    private final @NotNull LuaVirtualMachine vm;
    @NotNull ResourceScope contextScope = ResourceScope.newConfinedScope();
    private static final MemoryAddress cc_lua_alloc = SymbolLookup.loaderLookup().lookup("cc_lua_alloc").orElseThrow();
    private final @NotNull MemorySegment allocatorSizes = cc_allocator_sizes.allocate(contextScope);
    private final @NotNull MemoryAddress state;
    private static final @NotNull AtomicInteger ID_GEN = new AtomicInteger();
    private final int id = ID_GEN.getAndIncrement();
    private static final ArrayList<WeakReference<LuaContext>> CONTEXTS = new ArrayList<>();
    private final @NotNull HashMap<MemoryAddress, Object> wrappedObjects = new HashMap<>();


    public LuaContext(@NotNull LuaVirtualMachine vm, @NotNull UUID uuid, @NotNull HashMap<@NotNull String, @NotNull MemoryAddress> luaFunctions) {
        this.vm = vm;
        cc_allocator_sizes.maxMemory$set(allocatorSizes, 1L << 22); // 4MiB
        state = lua_newstate(cc_lua_alloc, allocatorSizes);
        var L = state;
        lua_gc(L, LUA_GCSETPAUSE(), 120);
        lua_gc(L, LUA_GCSETSTEPMUL(), 300);
        if (MemoryAddress.NULL.equals(L))
            throw new OutOfMemoryError("could not create Lua state");
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            luaopen_os(L);
            lua_getfield(L, -1, CLinker.toCString("setlocale", scope));
            lua_pushstring(L, CLinker.toCString("C", scope));
            lua_call(L, 1, 0);
            lua_pop(L, 1);
            for (var entry : luaFunctions.entrySet()) {
                lua_pushcfunction(L, entry.getValue());
                lua_setglobal(L, CLinker.toCString(entry.getKey(), scope));
            }
            while (CONTEXTS.size() <= id)
                CONTEXTS.add(null);
            CONTEXTS.set(id, new WeakReference<>(this));
            lua_pushinteger(L, id);
            lua_rawsetp(L, LUA_REGISTRYINDEX(), RegistryIds.ID);
            luaL_newmetatable(L, MetatableNames.WRAPPED_JAVA_OBJECT);
            setupJavaWrapperTable(scope);
            lua_pop(L, 1);
            luaL_newmetatable(L, MetatableNames.WRAPPED_JAVA_THROWABLE);
            setupJavaWrapperTable(scope);
            lua_pop(L, 1);
        }
    }

    private void setupJavaWrapperTable(ResourceScope scope) {
        var L = state;
        lua_pushliteral(L, "__metatable");
        lua_pushliteral(L, "Permission denied");
        lua_rawset(L, -3);
        lua_pushliteral(L, "__gc");
        lua_pushcfunction(L, objectWrapperGcAddress);
        lua_rawset(L, -3);
    }

    private MemoryAddress objectWrapperGcAddress;

    {
        try {
            objectWrapperGcAddress = CLinker.getInstance().upcallStub(MethodHandles.lookup().bind(this, "objectWrapperGc", MethodType.methodType(int.class, MemoryAddress.class)), lua_CFunction, contextScope);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getWrappedObject(MemoryAddress address) {
        return wrappedObjects.get(address);
    }

    public static Object getWrappedObject(Addressable L, int idx) {
        if (lua_isnoneornil(L, idx))
            return null;
        return getContextByState(L).getWrappedObject(lua_touserdata(L, idx));
    }

    public void wrapObject(Addressable L, Object object) {
        if (object == null) {
            lua_pushnil(L);
            return;
        }
        wrappedObjects.put(lua_newuserdata(state, 1), object);
        luaL_getmetatable(state, MetatableNames.WRAPPED_JAVA_OBJECT);
        lua_setmetatable(state, -2);
    }

    public void wrapThrowable(Addressable L, Throwable t) {
        if (t == null) {
            lua_pushnil(L);
            return;
        }
        wrappedObjects.put(lua_newuserdata(state, 1), t);
        luaL_getmetatable(state, MetatableNames.WRAPPED_JAVA_THROWABLE);
        lua_setmetatable(state, -2);
    }

    public static LuaContext getContextByState(Addressable L) {
        lua_rawgetp(L, LUA_REGISTRYINDEX(), RegistryIds.ID);
        var contextRef = CONTEXTS.get((int) lua_tointeger(L, -1));
        lua_pop(L, 1);
        final LuaContext context;
        if (contextRef != null)
            context = contextRef.get();
        else
            context = null;
        return Objects.requireNonNull(context);
    }

    @SuppressWarnings("unused")
    private int objectWrapperGc(MemoryAddress L) {
        if (lua_gettop(L) != 1 || !lua_isuserdata(L, 1)) {
            lua_pushliteral(L, "Error: invalid arguments in Java object wrapper __gc");
            lua_error(L);
        }
        MemoryAddress address = lua_touserdata(L, 1);

        wrappedObjects.remove(address);
        return 0;
    }

    @Override
    public @NotNull Value getValue(@NotNull String name) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            lua_getglobal(state, CLinker.toCString(name, scope));
        }
        return LuaConverter.valueFromLua(state, -1);
    }

    @Override
    public void setValue(@NotNull String name, @NotNull Value value) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public @NotNull Value call(@NotNull String name, Object... args) throws VmException {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            lua_getglobal(state, CLinker.toCString(name, scope));
            for (var arg : args)
                LuaConverter.objectToLua(state, arg);
            protectedCall(state, args.length, 1);
        }
        Value value = LuaConverter.valueFromLua(state, -1);
        lua_pop(state, 1);
        return value;
    }

    public void protectedCall(@NotNull Addressable L, int n, int r) {
        // TODO: don't use lua_gettop
        int top = lua_gettop(L);
        int handlerIdx = top - n;
        lua_pushcfunction(L, vm.errorHandler);
        lua_insert(L, handlerIdx);
        int result = lua_pcall(L, n, r, handlerIdx);
        lua_remove(L, handlerIdx);
        if (result == LUA_OK()) {
        } else if (result == LUA_ERRRUN()) {
            Value error = LuaConverter.valueFromLua(state, -1);
            if (error.isJavaObject()) {
                lua_pop(L, 1);
                Object o = error.asJavaObject();
                if (o instanceof Throwable t)
                    sneakyThrow(t);
                else
                    throw new LuaException("(error handler failed to convert) " + o, null);
            }
            lua_pop(L, 1);
        } else {
            throw new IllegalStateException("Unhandled Lua result: " + result);
        }
        lua_remove(L, handlerIdx);
    }

    @Override
    public void saveState(@NotNull OutputStream stream) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public void loadState(@NotNull InputStream stream) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public void close() {
        long contextUsedMemory = cc_allocator_sizes.usedMemory$get(allocatorSizes);
        System.out.println("Used memory: " + contextUsedMemory);
        if (!MemoryAddress.NULL.equals(state))
            lua_close(state);
        CONTEXTS.set(id, null);
        wrappedObjects.clear();
        contextUsedMemory = cc_allocator_sizes.usedMemory$get(allocatorSizes);
        if (contextUsedMemory != 0)
            throw new IllegalStateException("Used memory not 0 after close: " + contextUsedMemory);
        contextScope.close();
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        LuaVirtualMachine virtualMachine = new LuaProvider().createMachine();
        virtualMachine.registerFunction("test", MethodHandles.lookup().findStatic(LuaContext.class, "test", MethodType.methodType(Object.class, MemoryAddress.class, String.class, String.class)));
        LuaContext context = virtualMachine.getContext(new UUID(0, 0));
        try (var ctx = context) {
            for (int i = 0; i < 1000000; i++)
                try {
                    System.out.println(ctx.call("test", "Hello", "Lua"));
                } catch (VmException e) {
                    e.printStackTrace();
                }
            ctx.getValue("os");
        }
    }

    static int i = 0;

    private static Object test(MemoryAddress L, String s, String s2) {
        System.out.println(s + ", " + s2 + '!');
        lua_pushliteral(L, "Artificial error");
        lua_error(L);
        //Thread.dumpStack();
        switch (i++) {
            default:
                i = 1;
            case 0:
                return "str";
            case 1:
                return null;
            case 2:
                return 123;
            case 3:
                return 12.3d;
        }
    }
}
