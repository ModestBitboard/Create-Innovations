package com.shadowfire5650.create_innovations.content.item.custom;

import com.shadowfire5650.create_innovations.foundation.util.ProxyUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class StackingItem extends Item {
	
	public StackingItem(Properties properties) {

        super(properties);

        ProxyUtils.registerItemModelProperty(this, new ResourceLocation("count"), (stack, world, living, seed) -> ((float) stack.getCount()) / stack.getMaxStackSize());
    }
}
