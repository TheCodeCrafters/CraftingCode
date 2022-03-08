package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;

public class LuaProvider implements LanguageProvider {
	@Override
	public String getLanguageName() {
		return "LUA";
	}

	@Override
	public String getLanguageVersion() {
		return "5.3";
	}

	@Override
	public String getLanguageDescription() {
		return  """
				A powerful, efficient, lightweight, embeddable scripting language.
				It supports procedural programming, object-oriented programming, functional programming, data-driven programming, and data description.
				""";
	}

	@Override
	public VirtualMachine createMachine() {
		return null;
	}
}
