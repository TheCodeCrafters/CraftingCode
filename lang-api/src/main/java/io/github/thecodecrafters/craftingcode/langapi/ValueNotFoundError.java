package io.github.thecodecrafters.craftingcode.langapi;

public class ValueNotFoundError extends VmException {
	public ValueNotFoundError(Exception e) {
		super(e);
	}

	public ValueNotFoundError(String s) {
		super(s);
	}
}
