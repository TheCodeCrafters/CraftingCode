package io.github.thecodecrafters.craftingcode.langapi;

import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.UUID;

/**
 * A VirtualMachine is where the language interpreter is managed and used
 * can be also used to register stuff the language may interact with
 */
public interface VirtualMachine {
	/**
	 * Register a global class that may be instantiated in the language without importing anything.
	 * Some language may not have the concept of classes, in those cases, its up to to implementation to choose what to do if:
	 *  - Implement classes in a compatible manner ( ex. lua's metatables )
	 *  - Just ignore classes and do nothing
	 * @param clazz class to register
	 */
	void registerClass( Class<?> clazz );

	/**
	 * Register a global function that may be called in the language without importing anything.
	 * @param handle handle of the function
	 */
	void registerFunction( MethodHandle handle );

	/**
	 * Register a module that can be imported by the language.
	 * A module for java is just a map of classes, functions or values that is indexed by Strings
	 * @param module map of values to be registered as module
	 */
	void registerModule( Map<String, Value> module );

	/**
	 * Get or create a context for a specific UUID
	 * @param uuid of the context
	 * @return a new Context implementation or an existing one
	 */
	Context getContext(UUID uuid);

	/**
	 * Delete the context specified by this uuid
	 * @param uuid of the context to delete
	 */
	void deleteContext(UUID uuid);

	/**
	 * Getter for the provider that created this VirtualMachine implementation.
	 * @return the owning language provider
	 */
	LanguageProvider getProvider();
}
