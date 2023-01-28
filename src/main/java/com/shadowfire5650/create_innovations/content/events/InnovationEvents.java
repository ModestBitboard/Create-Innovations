package com.shadowfire5650.create_innovations.content.events;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.shadowfire5650.create_innovations.content.item.InnovationItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CreateInnovations.MOD_ID)
public class InnovationEvents {

	@SubscribeEvent
	public static void addCustomTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.LIBRARIAN) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			ItemStack stack = new ItemStack(InnovationItems.ITEM_SCHEMATIC.get(), 1);
			CompoundTag schematicNbtData = new CompoundTag();
			schematicNbtData.putString("recipeId", "create_innovations:item_printing/voltaic_mechanism");
			schematicNbtData.putString("NamedAfter", "item.create_innovations.voltaic_mechanism");
			stack.setTag(schematicNbtData);
			int villagerLevel = 1;
				
			trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 12), stack, 4, 12, 0.02F)
			);
		}
	}

}
