package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.Context;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;

import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.UUID;

public class LuaVirtualMachine implements VirtualMachine {
    @Override
    public void registerClass(Class<?> clazz) {
        throw new UnsupportedOperationException("not implemented"); // TODO
    }

    @Override
    public void registerFunction(String name, MethodHandle handle) {

    }

    @Override
    public void registerModule(Map<String, Value> module) {

    }

    @Override
    public Context getContext(UUID uuid) {
        return null;
    }

    @Override
    public void deleteContext(UUID uuid) {

    }

    @Override
    public LanguageProvider getProvider() {
        return null;
    }
}
