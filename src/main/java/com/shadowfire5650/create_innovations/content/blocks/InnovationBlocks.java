package com.shadowfire5650.create_innovations.content.blocks;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.shadowfire5650.create_innovations.InnovationSpriteShifts;
import com.shadowfire5650.create_innovations.content.blocks.item_printer.ItemPrinterBlock;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.AllTags.pickaxeOnly;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;

public class InnovationBlocks {
	
	private static final CreateRegistrate REGISTRATE = CreateInnovations.registrate().creativeModeTab(() -> CreateInnovations.MAIN_TAB);

	private static Properties glassProperties(Properties p) {
		return p.isValidSpawn(InnovationBlocks::never)
				.isRedstoneConductor(InnovationBlocks::never)
				.isSuffocating(InnovationBlocks::never)
				.isViewBlocking(InnovationBlocks::never);
	}

	private static boolean never(BlockState p_235436_0_, BlockGetter p_235436_1_, BlockPos p_235436_2_) {
		return false;
	}

	private static Boolean never(BlockState p_235427_0_, BlockGetter p_235427_1_, BlockPos p_235427_2_,
								 EntityType<?> p_235427_3_) {
		return false;
	}

	// Materials
	
	static {
		REGISTRATE.startSection(AllSections.MATERIALS);
	}
	
	public static final BlockEntry<Block> SILICON_BLOCK = REGISTRATE.block("silicon_block", Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
			.properties(p -> p.sound(SoundType.AMETHYST))
			.transform(pickaxeOnly())
			.simpleItem()
			.register();
	
	public static final BlockEntry<Block> ANDESITE_ALLOY_BLOCK = REGISTRATE.block("andesite_alloy_block", Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.STONE))
			.properties(p -> p.sound(SoundType.METAL))
			.transform(pickaxeOnly())
			.simpleItem()
			.register();

	public static final BlockEntry<Block> SHADOW_BLOCK = REGISTRATE.block("shadow_steel_block", Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.COLOR_BLACK))
			.properties(p -> p.sound(SoundType.AMETHYST))
			.transform(pickaxeOnly())
			.simpleItem()
			.register();

	public static final BlockEntry<Block> RADIANT_BLOCK = REGISTRATE.block("refined_radiance_block", Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.SNOW))
			.properties(p -> p.sound(SoundType.AMETHYST))
			.transform(pickaxeOnly())
			.properties(p -> p.lightLevel($ -> 15))
			.simpleItem()
			.register();

	public static final BlockEntry<Block> OVERCHARGED_BLOCK = REGISTRATE.block("overcharged_block", Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
			.properties(p -> p.sound(SoundType.AMETHYST))
			.transform(pickaxeOnly())
			.properties(p -> p.lightLevel($ -> 15))
			.simpleItem()
			.register();

	public static final BlockEntry<Block> RUBBER_BLOCK = REGISTRATE.block("rubber_block", Block::new)
			.initialProperties(() -> Blocks.WHITE_WOOL)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_WHITE))
			.properties(p -> p.sound(SoundType.SLIME_BLOCK))
			.transform(pickaxeOnly())
			.simpleItem()
			.register();

	public static final BlockEntry<Block> CURED_RUBBER_BLOCK = REGISTRATE.block("cured_rubber_block", Block::new)
			.initialProperties(() -> Blocks.WHITE_WOOL)
			.properties(p -> p.color(MaterialColor.COLOR_BLACK))
			.properties(p -> p.sound(SoundType.SLIME_BLOCK))
			.transform(pickaxeOnly())
			.simpleItem()
			.register();
	
	// Palettes
	
	static {
		REGISTRATE.startSection(AllSections.PALETTES);
	}
	
	public static final BlockEntry<CasingBlock> OVERCHARGED_CASING = REGISTRATE.block("overcharged_casing", CasingBlock::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
			.transform(BuilderTransformers.casing(() -> InnovationSpriteShifts.OVERCHARGED_CASING))
			.properties(p -> p.lightLevel($ -> 12))
			.register();
	
	public static final BlockEntry<CasingBlock> CAUTION_CASING = REGISTRATE.block("caution_casing", CasingBlock::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_YELLOW))
			.transform(BuilderTransformers.casing(() -> InnovationSpriteShifts.CAUTION_CASING))
			.register();

	public static final BlockEntry<ThermalCasingBlock> THERMAL_CASING = REGISTRATE.block("thermal_casing", ThermalCasingBlock::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
			.properties(p -> p.sound(SoundType.COPPER))
			.onRegister(connectedTextures(() -> new SimpleCTBehaviour(InnovationSpriteShifts.THERMAL_CASING)))
			.item()
			.build()
			.register();

	public static final BlockEntry<ThermalGlassCasingBlock> THERMAL_GLASS_CASING = REGISTRATE.block("thermal_glass_casing", ThermalGlassCasingBlock::new)
			.initialProperties(() -> Blocks.GLASS)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
			.properties(p -> p.sound(SoundType.COPPER))
			.properties(InnovationBlocks::glassProperties)
			.onRegister(connectedTextures(() -> new SimpleCTBehaviour(InnovationSpriteShifts.THERMAL_GLASS_CASING)))
			.addLayer(() -> RenderType::cutout)
			.item()
			.build()
			.register();

	// Kinetics
	
	static {
		REGISTRATE.startSection(AllSections.KINETICS);
	}

	public static final BlockEntry<ItemPrinterBlock> ITEM_PRINTER = REGISTRATE.block("mechanical_item_printer", ItemPrinterBlock::new)
			.initialProperties(SharedProperties::softMetal)	
			.properties(p -> p.color(MaterialColor.TERRACOTTA_YELLOW))
			.properties(BlockBehaviour.Properties::noOcclusion)
			.transform(pickaxeOnly())
			.transform(BlockStressDefaults.setImpact(8.0))
			.simpleItem()
			.register();

	public static void register() {
		
	}
}
