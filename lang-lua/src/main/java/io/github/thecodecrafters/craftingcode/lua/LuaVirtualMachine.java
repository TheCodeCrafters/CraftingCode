package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.Context;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;
import io.github.thecodecrafters.craftingcode.lua.convert.FunctionAdapter;
import jdk.incubator.foreign.MemoryAddress;
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
    private final MemoryAddress errorHandler;

    {
        try {
            errorHandler = FunctionAdapter.adaptToLua(MethodHandles.lookup().findStatic(LuaVirtualMachine.class, "errorHandler", MethodType.methodType(void.class, String.class)), luaFunctionScope);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: handle non-string error objects
    private static void errorHandler(String msg) {

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
        return contexts.computeIfAbsent(uuid, uuid1 -> new LuaContext(uuid1, luaFunctions));
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
