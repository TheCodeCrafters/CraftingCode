package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.Callable;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.WrappedException;

public record DummyCallable( Value result ) implements Callable {

	@Override
	public Value invoke(Object... args) throws WrappedException {
		return result();
	}
}
