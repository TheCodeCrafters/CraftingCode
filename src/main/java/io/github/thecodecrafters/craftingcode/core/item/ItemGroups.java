package io.github.thecodecrafters.craftingcode.core.item;

import io.github.thecodecrafters.craftingcode.core.Const;
import io.github.thecodecrafters.craftingcode.core.registry.ItemRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroups {
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create( Const.getId("main") )
			.icon( () -> new ItemStack( ItemRegistry.get("computer_block") ) )
			.build();
}
