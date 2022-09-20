package io.github.thecodecrafters.craftingcode.core.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ComputerBlockEntity extends BlockEntity {
	public ComputerBlockEntity( BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState ) {
		super( blockEntityType, blockPos, blockState );
	}
}
