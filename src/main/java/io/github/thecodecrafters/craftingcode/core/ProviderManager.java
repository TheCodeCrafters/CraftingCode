package io.github.thecodecrafters.craftingcode.core;

import io.github.thecodecrafters.craftingcode.dummy.DummyProvider;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static io.github.thecodecrafters.craftingcode.core.Const.MOD_LOGGER;

public class ProviderManager {
	private final Map<String, LanguageProvider> providers = new HashMap<>();


	public void loadProviders() {
		MOD_LOGGER.info("Loading language providers...");
		for ( var provider : FabricLoader.getInstance().getEntrypoints(  "craftingcode:lang", LanguageProvider.class ) ) {
			providers.put(
					provider.getClass().getSimpleName().toLowerCase().replace("provider", ""),
					provider
			);
		}
		// add builtin providers
		// TODO: Make this optional and make a config option to disable it
		providers.put( "dummy", new DummyProvider() );
		MOD_LOGGER.info( "Found {} provider{}!", providers.size(), providers.size() > 1 ? "s" : "" );
	}

	public @Nullable LanguageProvider getDummyProvider() {
		return providers.get("dummy");
	}
}
