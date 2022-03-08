package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.Callable;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.WrappedException;

import java.lang.invoke.MethodHandle;
import java.util.Objects;

public class DummyCallable implements Callable {
	private final Value result;
	private final MethodHandle impl;

	public DummyCallable( Value result ) {
		this.result = result;
		this.impl = null;
	}

	public DummyCallable( MethodHandle impl ) {
		this.impl = impl;
		this.result = null;
	}

	@Override
	public Value invoke(Object... args) throws WrappedException {
		try {
			var res = result;
			if ( res == null )
				res = (Value) Objects.requireNonNull(impl).invoke( args );
			return res;
		} catch (Throwable e) {
			throw new WrappedException(e);
		}
	}
}
