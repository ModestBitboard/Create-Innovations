package com.shadowfire5650.create_innovations.compat.jei;

import net.minecraft.world.item.crafting.Recipe;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;

public abstract class InnovationsRecipeCategory<T extends Recipe<?>> extends CreateRecipeCategory<T> {

	public InnovationsRecipeCategory(Info<T> info) {
		super(info);
	}
	
	/*
	@Override
	public Component getTitle() {
		return new TranslatableComponent(CreateInnovations.MOD_ID + ".recipe." + name);
	}
	*/
	
}
