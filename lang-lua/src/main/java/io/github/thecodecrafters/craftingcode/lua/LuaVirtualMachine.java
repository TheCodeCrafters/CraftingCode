package io.github.thecodecrafters.craftingcode.lua;


import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;
import io.github.thecodecrafters.craftingcode.lua.convert.FunctionAdapter;
import io.github.thecodecrafters.craftingcode.lua.convert.LuaConverter;
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.MemoryAddress;

import static io.github.thecodecrafters.craftingcode.lua.foreign.Lua.*;
import static jdk.incubator.foreign.MemoryAddress.NULL;

import jdk.incubator.foreign.ResourceScope;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LuaVirtualMachine implements VirtualMachine {
    private final ResourceScope luaFunctionScope = ResourceScope.newImplicitScope();
    private final HashMap<String, MemoryAddress> luaFunctions = new HashMap<>();
    final MemoryAddress errorHandler;

    {
        try {
            errorHandler = FunctionAdapter.adaptToLua(MethodHandles.lookup().findStatic(LuaVirtualMachine.class, "errorHandler", MethodType.methodType(Throwable.class, MemoryAddress.class, String.class)), luaFunctionScope);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: handle non-string error objects
    private static Throwable errorHandler(MemoryAddress L, String msg) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            luaL_traceback(L, L, NULL, 0);
        }
        var traceback = LuaConverter.stringFromLua(L, -1);
        var index = traceback.indexOf('\n', traceback.indexOf(':')+2);
        lua_pop(L, 1);
        return new LuaException(msg, traceback.substring(index+1));
    }

    private final LuaProvider languageProvider;
    private final HashMap<UUID, LuaContext> contexts = new HashMap<>();

    public LuaVirtualMachine(LuaProvider languageProvider) {
        this.languageProvider = languageProvider;
    }

    @Override
    public void registerClass(@NotNull Class<?> clazz) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public void registerFunction(@NotNull String name, @NotNull MethodHandle handle) {
        luaFunctions.put(name, FunctionAdapter.adaptToLua(handle, luaFunctionScope));
    }

    @Override
    public void registerModule(@NotNull Map<String, Value> module) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public @NotNull LuaContext getContext(UUID uuid) {
        return contexts.computeIfAbsent(uuid, uuid1 -> new LuaContext(this, uuid1, luaFunctions));
    }

    @Override
    public void deleteContext(@NotNull UUID uuid) {
        contexts.remove(uuid).close();
    }

    @Override
    public @NotNull LanguageProvider getProvider() {
        return languageProvider;
    }
}
