package com.shadowfire5650.create_innovations.content.events;

import com.shadowfire5650.create_innovations.content.fluid.InnovationFluids;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class InnovationClientEvents {
	
	@SubscribeEvent
	public static void getFogDensity(EntityViewRenderEvent.RenderFogEvent event) {
		Camera info = event.getCamera();
		Level level = Minecraft.getInstance().level;
		BlockPos blockPos = info.getBlockPosition();
		FluidState fluidState = level.getFluidState(blockPos);
		if (info.getPosition().y > blockPos.getY() + fluidState.getHeight(level, blockPos))
			return;

		Fluid fluid = fluidState.getType();

		if (InnovationFluids.LIQUID_SAND.get()
			.isSame(fluid)) {
			event.scaleFarPlaneDistance(1f/32f);
			event.setCanceled(true);
			return;
		}

		if (InnovationFluids.RESIN.get()
				.isSame(fluid)) {
			event.scaleFarPlaneDistance(1f/32f);
			event.setCanceled(true);
			return;
		}

	}
	
	@SubscribeEvent
	public static void getFogColor(EntityViewRenderEvent.FogColors event) {
		Camera info = event.getCamera();
		Level level = Minecraft.getInstance().level;
		BlockPos blockPos = info.getBlockPosition();
		FluidState fluidState = level.getFluidState(blockPos);
		if (info.getPosition().y > blockPos.getY() + fluidState.getHeight(level, blockPos))
			return;

		Fluid fluid = fluidState.getType();

		if (InnovationFluids.LIQUID_SAND.get()
			.isSame(fluid)) {
			event.setRed(230 / 255f);
			event.setGreen(220 / 255f);
			event.setBlue(197 / 255f);
			return;
		}

		if (InnovationFluids.RESIN.get()
				.isSame(fluid)) {
			event.setRed(125 / 255f);
			event.setGreen(85 / 255f);
			event.setBlue(16 / 255f);
			return;
		}

	}
	
}
