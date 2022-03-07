package io.github.bubblie.craftingcode.langapi;

/**
 * An object that may be invoked by java.
 * Often used for callbacks and event handlers
 */
public interface Callable {
	Value invoke( Object... args ) throws WrappedException;
}
