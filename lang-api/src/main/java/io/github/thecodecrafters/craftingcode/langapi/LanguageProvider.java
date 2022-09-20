package io.github.thecodecrafters.craftingcode.langapi;

import org.jetbrains.annotations.NotNull;

/**
 * The provider is the thing the mod interfaces with when loading languages.
 * It provides:
 *  - a language's metadata ( name, version, description )
 *  - a way to create an environment where the language can execute code ( Virtual Machine )
 */
public interface LanguageProvider {
	@NotNull
	String getLanguageName();
	String getLanguageVersion();
	String getLanguageDescription();
	@NotNull
	VirtualMachine createMachine();
}
