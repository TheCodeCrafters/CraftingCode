package io.github.thecodecrafters.craftingcode.compat.modmenu;

import com.terraformersmc.modmenu.ModMenu;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.thecodecrafters.craftingcode.core.CraftingCode;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;

public class CraftingCodeModMenuEntrypoint implements ModMenuApi {
	static {
		LanguageProvider provider = CraftingCode.getInstance().getProviderManager().getDummyProvider();
		if ( provider != null ) {
			ModMenu.PARENT_MAP.put(
				ModMenu.MODS.get( "craftingcode" ),
				ModMenu.MODS.put(
					"craftingcode-dummy",
					new CraftingCodeModImpl( provider )
				)
			);
		}
	}
}
