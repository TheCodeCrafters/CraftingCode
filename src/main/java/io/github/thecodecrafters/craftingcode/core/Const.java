package io.github.thecodecrafters.craftingcode.core;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModOrigin;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Const {
	public static final String 	  MOD_ID = "craftingcode";
	public static final String    MOD_NAME;
	public static final ModOrigin MOD_ORIGIN;
	public static final String    MOD_VERSION;
	public static final String    API_VERSION = "0.1.0";
	public static final Logger    MOD_LOGGER = LoggerFactory.getLogger("CraftingCode-Core");

	public static Identifier getId(String path ) {
		return new Identifier( MOD_ID, path );
	}

	static {
		var container = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();
		var meta = container.getMetadata();
		MOD_NAME = meta.getName();
		MOD_ORIGIN = container.getOrigin();
		MOD_VERSION = meta.getVersion().getFriendlyString();
	}
}
