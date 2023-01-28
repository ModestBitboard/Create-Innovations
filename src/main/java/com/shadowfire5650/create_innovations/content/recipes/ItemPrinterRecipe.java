package com.shadowfire5650.create_innovations.content.recipes;

import com.simibubi.create.content.contraptions.processing.ProcessingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder.ProcessingRecipeParams;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
public class ItemPrinterRecipe extends ProcessingRecipe<RecipeWrapper> {

	public ItemPrinterRecipe(ProcessingRecipeParams params) {
		super(InnovationRecipes.ITEM_PRINTING, params);
	}

	@Override
	public boolean matches(RecipeWrapper inv, Level worldIn) {
		if (inv.isEmpty())
			return false;
		return ingredients.get(0)
			.test(inv.getItem(0));
	}
	
	@Override
	protected int getMaxOutputCount() {
		return 1;
	}

	@Override
	protected int getMaxInputCount() {
		return 1;
	}
}
