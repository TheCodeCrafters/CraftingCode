package io.github.thecodecrafters.craftingcode.langapi;

/**
 * An exception/error that happened in the language
 */
public class WrappedException extends VmException {
	public WrappedException(Exception e) {
		super(e);
	}
}
