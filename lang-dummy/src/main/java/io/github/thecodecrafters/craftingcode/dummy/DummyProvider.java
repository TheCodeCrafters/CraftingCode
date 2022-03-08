package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;
import org.jetbrains.annotations.NotNull;

public class DummyProvider implements LanguageProvider {
	@Override
	public @NotNull String getLanguageName() {
		return "DummyLang";
	}

	@Override
	public String getLanguageVersion() {
		return "1.0";
	}

	@Override
	public String getLanguageDescription() {
		return "Provides a language api implementation for testing purposes";
	}

	@Override
	public @NotNull VirtualMachine createMachine() {
		return new DummyVirtualMachine( this );
	}
}
