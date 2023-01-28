package com.shadowfire5650.create_innovations.content.item;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.shadowfire5650.create_innovations.content.item.custom.*;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.content.contraptions.itemAssembly.SequencedAssemblyItem;
import com.simibubi.create.content.curiosities.RefinedRadianceItem;
import com.simibubi.create.content.curiosities.ShadowSteelItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
 
public class InnovationItems {
	
	private static final CreateRegistrate REGISTRATE = CreateInnovations.registrate().creativeModeTab(() -> CreateInnovations.MAIN_TAB);
	
	// Schematics
	
	static {
		REGISTRATE.startSection(AllSections.SCHEMATICS);
	}
	
	public static final ItemEntry<Item> CREATIVE_ITEM_SCHEMATIC = REGISTRATE.item("creative_item_schematic", Item::new)
			.properties(p -> p.stacksTo(1))
			.properties(p -> p.rarity(Rarity.EPIC))
			.register();

	public static final ItemEntry<ItemSchematicItem> ITEM_SCHEMATIC = REGISTRATE.item("item_schematic", ItemSchematicItem::new)
			.properties(p -> p.stacksTo(1))
			.register();
	
	// Materials
	
	static {
		REGISTRATE.startSection(AllSections.MATERIALS);
	}
	
	public static final ItemEntry<Item> 
		SILICON = ingredient("silicon"),
		SILICON_COMPOUND = ingredient("silicon_compound"),
		MISSINGNO = ingredient("missingno"),
		RUBBER = ingredient("rubber"),
		CURED_RUBBER = ingredient("cured_rubber"),
		SAND_CHUNKS = ingredient("sand_chunks"),
		LAPIS_SHEET = ingredient("lapis_sheet"),
		INTEGRATED_CIRCUIT = ingredient("integrated_circuit"),
		VOLTAIC_MECHANISM = ingredient("voltaic_mechanism"),
		VILLAGER_MECHANISM = ingredient("villager_mechanism");

	public static final ItemEntry<Item> OVERCLOCKED_TUBE = REGISTRATE.item("overclocked_tube", Item::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON)).register();

	public static final ItemEntry<MatrixItem> QUANTUM_CIRCUIT = REGISTRATE.item("quantum_circuit", MatrixItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON)).register();

	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_VOLTAIC_MECHANISM =
			REGISTRATE.item("incomplete_voltaic_mechanism", SequencedAssemblyItem::new)
			.register();

	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_VILLAGER_MECHANISM =
			REGISTRATE.item("incomplete_villager_mechanism", SequencedAssemblyItem::new)
					.register();

	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_OVERCLOCKED_TUBE =
			REGISTRATE.item("incomplete_overclocked_tube", SequencedAssemblyItem::new)
					.register();
	
	public static final ItemEntry<OverchargedAlloyItem> OVERCHARGED_ALLOY = REGISTRATE.item("overcharged_alloy", OverchargedAlloyItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.register();
	
	public static final ItemEntry<RefinedRadianceItem> RADIANT_SHEET = REGISTRATE.item("radiant_sheet", RefinedRadianceItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.register();
	
	public static final ItemEntry<ShadowSteelItem> SHADOW_SHEET = REGISTRATE.item("shadow_sheet", ShadowSteelItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.register();
	
	public static final ItemEntry<OverchargedAlloyItem> OVERCHARGED_SHEET = REGISTRATE.item("overcharged_sheet", OverchargedAlloyItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.register();
	
	public static final ItemEntry<StackingItem>
		SILVER_COIN = coin("silver_coin"),
		GOLD_COIN = coin("gold_coin"),
		BRONZE_COIN = coin("bronze_coin"),
		INVAR_COIN = coin("invar_coin");

	public static final ItemEntry<NumberItem>
		NUMBER_ZERO = number("zero"),
		NUMBER_ONE = number("one"),
		NUMBER_TWO = number("two"),
		NUMBER_THREE = number("three"),
		NUMBER_FOUR = number("four"),
		NUMBER_FIVE = number("five"),
		NUMBER_SIX = number("six"),
		NUMBER_SEVEN = number("seven"),
		NUMBER_EIGHT = number("eight"),
		NUMBER_NINE = number("nine"),
		OPERATOR_PLUS = number("plus"),
		OPERATOR_MINUS = number("minus"),
		OPERATOR_MULTIPLY = number("multiply"),
		OPERATOR_DIVIDE = number("divide");

	public static final ItemEntry<MatrixItem> COMPUTATION_MATRIX = REGISTRATE.item("computation_matrix", MatrixItem::new)
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.properties(p -> p.stacksTo(1))
			.register();

	// Logistics

	static {
		REGISTRATE.startSection(AllSections.LOGISTICS);
	}

	public static final ItemEntry<UnusedItem>
		BLANK_CAST = unusedIngredient("cast");

	public static final ItemEntry<Item>
		NUGGET_CAST = cast("nugget"),
		INGOT_CAST = cast("ingot"),
		ROD_CAST = cast("rod"),
		THREE_CAST = cast("three"),
		EIGHT_CAST = cast("eight"),
		PLUS_CAST = cast("plus"),
		MINUS_CAST = cast("minus"),
		MULTIPLY_CAST = cast("multiply"),
		DIVIDE_CAST = cast("divide");

	// Shortcuts
	
	private static ItemEntry<Item> ingredient(String name) {
		return REGISTRATE.item(name, Item::new)
				.register();
	}
	
	private static ItemEntry<UnusedItem> unusedIngredient(String name) {
		return REGISTRATE.item(name, UnusedItem::new)
				.register();
	}
	
	private static ItemEntry<StackingItem> coin(String name) {
		return REGISTRATE.item(name, StackingItem::new)
				.register();
	}

	private static ItemEntry<NumberItem> number(String name) {
		return REGISTRATE.item(name, NumberItem::new)
				.register();
	}

	private static ItemEntry<Item> cast(String name) {
		return REGISTRATE.item(name+"_cast", Item::new)
				.properties(p -> p.stacksTo(1))
				.onRegister(s -> TooltipHelper.referTo(s, BLANK_CAST))
				.register();
	}
	
	// Load this class
	
	public static void register() {

	}
}
