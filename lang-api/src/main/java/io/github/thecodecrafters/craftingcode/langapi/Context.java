package io.github.thecodecrafters.craftingcode.langapi;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * An try-with-resources-enabled object that is responsible for:
 *  - Save/loading the language state for a computer
 *  - Getting a value in the state
 *  - Setting a value in the state
 *  - Call a global function defined in the state
 */
public interface Context extends AutoCloseable {
	@NotNull Value getValue( @NotNull String name );
	void setValue( @NotNull String name, @NotNull Value value );
	@NotNull Value call( @NotNull String name, Object... args ) throws VmException;

	void saveState( @NotNull OutputStream stream );
	void loadState( @NotNull InputStream stream );
}
