package io.github.thecodecrafters.craftingcode.lua;

import org.jetbrains.annotations.Contract;

@SuppressWarnings("unchecked")
public class ThrowableUtil {
    @Contract("_ -> fail")
    public static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
