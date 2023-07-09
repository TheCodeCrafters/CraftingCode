package io.github.thecodecrafters.craftingcode.langapi;

public class ValueNotFoundError extends VmException {
	public ValueNotFoundError(Throwable cause) {
		super(cause);
	}

	public ValueNotFoundError(String message) {
		super(message);
	}
}
