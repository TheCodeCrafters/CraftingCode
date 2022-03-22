package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.*;

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
	public Value getValue(String name) {
		return values.getOrDefault( name, DummyValue.ofNull() );
	}

	@Override
	public void setValue(String name, Value value) {
		values.put( name, value );
	}

	@Override
	public Value call( String name, Object... args ) throws VmException {
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
	public void saveState(OutputStream stream) {
		// no op
	}

	@Override
	public void loadState(InputStream stream) {
		// no op
	}

	@Override
	public void close() {
		// no op
	}
}
