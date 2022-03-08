package io.github.thecodecrafters.craftingcode.core.registry;

import io.github.thecodecrafters.craftingcode.core.Const;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {
	private static final Map<String, Block> BLOCKS = new HashMap<>() {{
//		put( "block_name", new DeriviedBlock() );
	}};

	public static void register() {
		for ( var block : BLOCKS.entrySet() ) {
			Registry.register( Registry.BLOCK, Const.getId( block.getKey() ), block.getValue() );
		}
	}

	public static Block get( String blockId ) {
		return BLOCKS.getOrDefault( blockId, Blocks.AIR );
	}
}
