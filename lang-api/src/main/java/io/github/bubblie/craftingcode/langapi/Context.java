package io.github.bubblie.craftingcode.langapi;

import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * An try-with-resources-enabled object that is responsible for:
 *  - Save/loading the language state for a computer
 *  - Getting a value in the state
 *  - Setting a value in the state
 *  - Call a global function defined in the state
 *  - Get the last throwed exception/error
 */
public interface Context extends AutoCloseable {
	Value getValue( String name );
	void setValue( String name, Value value );
	Value call( String name ) throws WrappedException;
	@Nullable WrappedException getLastException();

	void saveState( OutputStream stream );
	void loadState( InputStream stream );
}
