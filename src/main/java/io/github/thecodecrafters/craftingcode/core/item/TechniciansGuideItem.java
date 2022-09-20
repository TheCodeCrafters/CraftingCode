package io.github.thecodecrafters.craftingcode.core.item;

import io.github.thecodecrafters.craftingcode.core.Const;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

public class TechniciansGuideItem extends Item {
	public TechniciansGuideItem() {
		super( new Item.Settings().group( ItemGroups.ITEM_GROUP ) );
	}

	@Override
	public TypedActionResult<ItemStack> use( World world, PlayerEntity user, Hand hand ) {
		PatchouliAPI.get().openBookGUI( Const.getId( "technicians_guide" ) );
		return TypedActionResult.success( user.getStackInHand( hand ) );
	}
}
