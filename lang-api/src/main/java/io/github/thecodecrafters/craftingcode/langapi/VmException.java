package io.github.thecodecrafters.craftingcode.langapi;

/**
 * Exception that happened in the VM
 */
public class VmException extends RuntimeException {
	public VmException(Exception e) {
		super(e);
	}
}
