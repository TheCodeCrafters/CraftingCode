package io.github.thecodecrafters.craftingcode.core.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TechniciansGuideItem extends Item {
	public TechniciansGuideItem() {
		super( new Settings().maxCount(1).group( ItemGroups.ITEM_GROUP ) );
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		return super.use(world, user, hand);
	}
}
