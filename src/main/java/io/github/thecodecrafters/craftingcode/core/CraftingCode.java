package io.github.thecodecrafters.craftingcode.core;

import io.github.thecodecrafters.craftingcode.core.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

import static io.github.thecodecrafters.craftingcode.core.Const.*;

public class CraftingCode implements ModInitializer {
	private static CraftingCode INSTANCE;

	private final ProviderManager providerManager = new ProviderManager();

	public CraftingCode() { INSTANCE = this; }

	@Override
	public void onInitialize() {
		MOD_LOGGER.info(
				"Initializing CraftingCode-Core version {} with api version {}",
				MOD_VERSION,
				API_VERSION
		);
		providerManager.loadProviders();
		ItemRegistry.register();
	}

	public ProviderManager getProviderManager() {
		return providerManager;
	}

	public static CraftingCode getInstance() {
		return INSTANCE;
	}
}
