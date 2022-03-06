package io.github.Bubblie.craftingcode.langapi;

public interface LanguageProvider {
	String getLanguageName();
	VirtualMachine createMachine();
}
