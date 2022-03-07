package io.github.bubblie.craftingcode.dummy;

import io.github.bubblie.craftingcode.langapi.LanguageProvider;
import io.github.bubblie.craftingcode.langapi.VirtualMachine;

public class DummyProvider implements LanguageProvider {
	@Override
	public String getLanguageName() {
		return "DummyLang";
	}

	@Override
	public String getLanguageVersion() {
		return "1.0";
	}

	@Override
	public String getLanguageDescription() {
		return "Ur a dum dummy";
	}

	@Override
	public VirtualMachine createMachine() {
		return null;
	}
}
