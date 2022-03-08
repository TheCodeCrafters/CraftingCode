package io.github.thecodecrafters.craftingcode.core;

import io.github.thecodecrafters.craftingcode.util.PanamaEnabler;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class CraftingCodePreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        // This needs to be done *before* any classes that use Panama are loaded
        PanamaEnabler.enablePanama();
    }
}
