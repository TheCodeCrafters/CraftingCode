package io.github.bubblie.craftingcode.langapi;

public interface LanguageProvider {
	String getLanguageName();
	VirtualMachine createMachine();
}
