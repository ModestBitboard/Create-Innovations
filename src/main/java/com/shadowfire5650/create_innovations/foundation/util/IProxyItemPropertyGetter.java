package com.shadowfire5650.create_innovations.foundation.util;

import javax.annotation.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IProxyItemPropertyGetter {

    float call(ItemStack stack, @Nullable Level world, @Nullable LivingEntity entity, int seed);

}