package io.github.thecodecrafters.craftingcode.core.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemAcessor {
	@Accessor
	void setGroup( ItemGroup group );
}
