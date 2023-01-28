package com.shadowfire5650.create_innovations.content.item.custom;

import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NumberItem extends Item {

	public NumberItem(Properties pProperties) {
		super(pProperties);
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		Level world = entity.level;
		Vec3 pos = entity.position();
		CompoundTag persistentData = entity.getPersistentData();

		if (world.isClientSide) {
			if (world.random.nextFloat() < getIdleParticleChance(entity)) {
				Vec3 ppos = VecHelper.offsetRandomly(pos, world.random, .5f);
				world.addParticle(ParticleTypes.ELECTRIC_SPARK, ppos.x, pos.y, ppos.z, 0, 0, 0);
			}

			return false;
		}

		if (!persistentData.contains("JustCreated"))
			return false;
		onCreated(entity, persistentData);
		return false;
	}

	protected float getIdleParticleChance(ItemEntity entity) {
		return (float) (Mth.clamp(entity.getItem()
				.getCount() - 10, Mth.clamp(entity.getDeltaMovement().y * 20, 5, 20), 100) / 64f);
	}

	protected void onCreated(ItemEntity entity, CompoundTag persistentData) {
		persistentData.remove("JustCreated");
		entity.setSilent(true);
	}
}
