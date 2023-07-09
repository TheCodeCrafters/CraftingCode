package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.WrappedException;

public class LuaException extends WrappedException {
    public LuaException(String message, String traceback) {
        super(message, traceback);
    }
}
