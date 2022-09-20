package io.github.thecodecrafters.craftingcode.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ComputerBlock extends BlockWithEntity {
	protected ComputerBlock( Settings settings ) {
		super( settings );
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity( BlockPos pos, BlockState state ) {
		return null;
	}
}
