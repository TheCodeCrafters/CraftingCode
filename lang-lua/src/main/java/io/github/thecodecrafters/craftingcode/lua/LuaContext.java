package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.Context;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VmException;
import io.github.thecodecrafters.craftingcode.lua.convert.LuaConverter;
import io.github.thecodecrafters.craftingcode.lua.foreign.lua_Alloc;
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.ResourceScope;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.UUID;

import static io.github.thecodecrafters.craftingcode.lua.foreign.Lua.*;

public class LuaContext implements Context {
    ResourceScope contextScope = ResourceScope.newConfinedScope();
    long maxMemory = 1 << 22; // 4MiB
    long usedMemory = 0;
    private final @NotNull MemoryAddress state;

    public LuaContext(UUID uuid, HashMap<String, MemoryAddress> luaFunctions) {
        state = lua_newstate(lua_Alloc.allocate((ud, ptr, osize, nsize) -> {
            if (nsize == 0) {
                if (!MemoryAddress.NULL.equals(ptr)) {
                    usedMemory -= osize;
                    CLinker.freeMemory(ptr);
                }
                return MemoryAddress.NULL;
            } else if (MemoryAddress.NULL.equals(ptr)) {
                if (usedMemory + nsize > maxMemory)
                    return MemoryAddress.NULL;
                usedMemory += nsize;

                return CLinker.allocateMemory(nsize);
            } else {
                if (usedMemory - osize + nsize > maxMemory)
                    return MemoryAddress.NULL;
                usedMemory -= osize;
                usedMemory += nsize;

                MemoryAddress newPtr = CLinker.allocateMemory(nsize);
                if (!MemoryAddress.NULL.equals(newPtr)) {
                    if (nsize > osize) {
                        newPtr.asSegment(nsize, contextScope).copyFrom(ptr.asSegment(osize, contextScope));
                    } else {
                        newPtr.asSegment(nsize, contextScope).copyFrom(ptr.asSegment(nsize, contextScope));
                    }
                }

                CLinker.freeMemory(ptr);
                return newPtr;
            }
        }, contextScope), MemoryAddress.NULL);
        if (MemoryAddress.NULL.equals(state))
            throw new OutOfMemoryError("could not create Lua state");
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            luaopen_os(state);
            lua_getfield(state, -1, CLinker.toCString("setlocale", scope));
            lua_pushstring(state, CLinker.toCString("C", scope));
            lua_call(state, 1, 0);
            lua_pop(state, 1);
            for (var entry : luaFunctions.entrySet()) {
                lua_pushcfunction(state, entry.getValue());
                lua_setglobal(state, CLinker.toCString(entry.getKey(), scope));
            }
        }
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
            lua_call(state, args.length, 1);
        }
        return LuaConverter.valueFromLua(state, -1);
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
        if (!MemoryAddress.NULL.equals(state))
            lua_close(state);
        contextScope.close();
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        LuaVirtualMachine virtualMachine = new LuaProvider().createMachine();
        virtualMachine.registerFunction("test", MethodHandles.lookup().findStatic(LuaContext.class, "test", MethodType.methodType(Object.class, String.class, String.class)));
        var ctx = virtualMachine.getContext(new UUID(0, 0));
        for (int i = 0; i < 100; i++)
            System.out.println(ctx.call("test", "Hello", "Lua"));
        ctx.getValue("os");
        ctx.close();
        if (ctx.usedMemory != 0)
            throw new IllegalStateException("Used memory not 0 after close: " + ctx.usedMemory);
    }

    static int i = 0;
    private static Object test(String s, String s2) {
        System.out.println(s + ", " + s2 + '!');
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
