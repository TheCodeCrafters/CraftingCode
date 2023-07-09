package io.github.thecodecrafters.craftingcode.langapi;

/**
 * Exception that happened in the VM
 */
public class VmException extends RuntimeException {
	public VmException(Throwable cause) {
		super(cause);
	}

	public VmException(String message) {
		super(message);
	}

	public VmException(String message, Throwable cause) {
		super(message, cause);
	}
}
