package io.github.thecodecrafters.craftingcode.core.registry;

import io.github.thecodecrafters.craftingcode.core.Const;
import io.github.thecodecrafters.craftingcode.core.item.ItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
	private static final Map<String, Item> ITEMS = new HashMap<>() {{
		put( "technicians_guide", new Item( new Item.Settings().group(ItemGroups.ITEM_GROUP) ) );
	}};

	public static void register() {
		for ( var item : ITEMS.entrySet() ) {
			Registry.register( Registry.ITEM, Const.getId( item.getKey() ), item.getValue() );
		}
	}

	public static Item get(String itemId) {
		return ITEMS.getOrDefault(itemId, Items.AIR);
	}

}
