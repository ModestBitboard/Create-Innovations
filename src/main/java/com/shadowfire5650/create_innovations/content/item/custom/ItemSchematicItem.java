package com.shadowfire5650.create_innovations.content.item.custom;

import org.jetbrains.annotations.Nullable;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.simibubi.create.foundation.utility.Components;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;


public class ItemSchematicItem extends Item {
	public ItemSchematicItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

		if(pStack.hasTag()) {
			if(pStack.getTag().contains("NamedAfter")) {
				String itemName = pStack.getTag().getString("NamedAfter");
				pTooltipComponents.add(Components.translatable(itemName).withStyle(ChatFormatting.GOLD));
			}
		}
		
		else {
			pTooltipComponents.add(Components.translatable(CreateInnovations.MOD_ID + ".item_schematic.invalid").withStyle(ChatFormatting.RED));
		}
		
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {
		
	}
	
}
