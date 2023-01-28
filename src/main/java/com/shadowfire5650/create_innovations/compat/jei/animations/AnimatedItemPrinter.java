package com.shadowfire5650.create_innovations.compat.jei.animations;

import com.mojang.blaze3d.vertex.PoseStack;
import com.shadowfire5650.create_innovations.content.blocks.InnovationBlocks;
import com.shadowfire5650.create_innovations.content.blocks.item_printer.ItemPrinterBlock;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.element.GuiGameElement;

public class AnimatedItemPrinter extends AnimatedKinetics {
	private boolean shadow = true;

	public AnimatedItemPrinter(boolean shadow) {
		this.shadow = shadow;
	}

	public AnimatedItemPrinter() {
		shadow = true;
	}

	@Override
	public void draw(PoseStack poseStack, int xOffset, int yOffset) {
		poseStack.pushPose();
		poseStack.translate(xOffset, yOffset, 0);
		if(shadow)
			AllGuiTextures.JEI_SHADOW.render(poseStack, -16, 13);
		poseStack.translate(-2, 18, 0);
		int scale = 22;

		GuiGameElement.of(InnovationBlocks.ITEM_PRINTER.getDefaultState()
			.setValue(ItemPrinterBlock.LOADED, true))
			.rotateBlock(22.5, 22.5, 0)
			.scale(scale)
			.render(poseStack);

		poseStack.popPose();
	}
}
