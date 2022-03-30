package io.github.thecodecrafters.craftingcode.compat.modmenu;

import com.terraformersmc.modmenu.ModMenu;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModIconHandler;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class CraftingCodeModImpl implements Mod {
	private final LanguageProvider provider;

	public CraftingCodeModImpl( LanguageProvider provider ) {
		this.provider = provider;
	}

	@Override
	public @NotNull String getId() {
		return "craftingcode-" + provider.getLanguageName().toLowerCase(Locale.ROOT);
	}

	@Override
	public @NotNull String getName() {
		return "CraftingCode " + provider.getLanguageName();
	}

	@Override
	public /*@NotNull*/ NativeImageBackedTexture getIcon(ModIconHandler iconHandler, int i) {
		return null;
	}

	@Override
	public @NotNull String getSummary() {
		return "";
	}

	@Override
	public @NotNull String getDescription() {
		return provider.getLanguageDescription();
	}

	@Override
	public @NotNull String getVersion() {
		return provider.getLanguageVersion();
	}

	@Override
	public @NotNull String getPrefixedVersion() {
		return provider.getLanguageVersion();
	}

	@Override
	public @NotNull List<String> getAuthors() {
		return List.of("The CodeCrafters team");
	}

	@Override
	public @NotNull List<String> getContributors() {
		return List.of();
	}

	@Override
	public @NotNull Set<Badge> getBadges() {
		return Set.of(Badge.LIBRARY);
	}

	@Override
	public @Nullable String getWebsite() {
		return null;
	}

	@Override
	public @Nullable String getIssueTracker() {
		return null;
	}

	@Override
	public @Nullable String getSource() {
		return ModMenu.MODS.get("craftingcode").getSource();
	}

	@Override
	public @Nullable String getParent() {
		return "craftingcode";
	}

	@Override
	public @NotNull Set<String> getLicense() {
		return ModMenu.MODS.get("craftingcode").getLicense();
	}

	@Override
	public @NotNull Map<String, String> getLinks() {
		return Map.of();
	}

	@Override
	public boolean isReal() {
		return false;
	}
}
