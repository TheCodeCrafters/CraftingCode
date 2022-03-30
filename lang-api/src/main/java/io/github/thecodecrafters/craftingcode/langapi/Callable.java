package io.github.thecodecrafters.craftingcode.langapi;

import org.jetbrains.annotations.NotNull;

/**
 * An object that may be invoked by java.
 * Often used for callbacks and event handlers
 */
public interface Callable {
	@NotNull Value invoke(Object... args ) throws WrappedException;
}
