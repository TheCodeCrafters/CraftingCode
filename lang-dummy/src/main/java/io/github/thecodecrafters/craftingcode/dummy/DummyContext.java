package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DummyContext implements Context {
	private final Map<String, Value> values = new HashMap<>();

	DummyContext( Map<String, Value> baseGlobals ) {
		this.values.putAll( baseGlobals );
	}

	@Override
	public @NotNull Value getValue(@NotNull String name) {
		return values.getOrDefault( name, DummyValue.ofNull() );
	}

	@Override
	public void setValue(@NotNull String name, @NotNull Value value) {
		values.put( name, value );
	}

	@Override
	public @NotNull Value call(@NotNull String name, Object... args ) throws VmException {
		if ( values.containsKey( name ) )
			try {
				Value func;
				if ( ( func = values.get( name ) ).isCallable() )
					return func.asCallable().invoke(args);
				else
					throw new NotACallableException( name + " is not a callable!" );
			} catch ( Exception e ) {
				throw new WrappedException(e);
			}
		throw new ValueNotFoundError("Tried to get inexistant value `" + name + "`");
	}

	@Override
	public void saveState(@NotNull OutputStream stream) {
		// no op
	}

	@Override
	public void loadState(@NotNull InputStream stream) {
		// no op
	}

	@Override
	public void close() {
		// no op
	}
}
