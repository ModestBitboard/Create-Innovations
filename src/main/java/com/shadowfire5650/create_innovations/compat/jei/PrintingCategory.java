package com.shadowfire5650.create_innovations.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.shadowfire5650.create_innovations.compat.jei.animations.AnimatedItemPrinter;
import com.shadowfire5650.create_innovations.content.recipes.ItemPrinterRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PrintingCategory extends InnovationsRecipeCategory<ItemPrinterRecipe>{
	
	private AnimatedItemPrinter item_printer = new AnimatedItemPrinter();
	
	public PrintingCategory(Info<ItemPrinterRecipe> info) {
		super(info);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ItemPrinterRecipe recipe, IFocusGroup focuses) {
		builder
				.addSlot(RecipeIngredientRole.INPUT, 15, 9)
				.setBackground(getRenderedSlot(), -1, -1)
				.addIngredients(recipe.getIngredients().get(0));

		builder
				.addSlot(RecipeIngredientRole.OUTPUT, 140, 28)
				.setBackground(getRenderedSlot(), -1, -1)
				.addItemStack(recipe.getResultItem());
	}

	@Override
	public void draw(ItemPrinterRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {
		AllGuiTextures.JEI_ARROW.render(matrixStack, 85, 32);
		AllGuiTextures.JEI_DOWN_ARROW.render(matrixStack, 43, 4);
		item_printer.draw(matrixStack, 48, 27);
	}
}
