package io.github.thecodecrafters.craftingcode.lua;

import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;
import org.jetbrains.annotations.NotNull;

public class LuaProvider implements LanguageProvider {
	@NotNull
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

	@NotNull
	@Override
	public VirtualMachine createMachine() {
		return new LuaVirtualMachine();
	}
}
