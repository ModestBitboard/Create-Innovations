package com.shadowfire5650.create_innovations.content.fluid;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.shadowfire5650.create_innovations.content.tags.InnovationTags;
import com.simibubi.create.content.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InnovationFluids {
	
	private static final CreateRegistrate REGISTRATE = CreateInnovations.registrate();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> RAW_LOGIC =
			REGISTRATE.fluid("raw_logic",
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/raw_logic_still"),
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/raw_logic_flow"), NoColorFluidAttributes::new)
					.lang("Liquified Logic (Unprocessed)")
					.tag(InnovationTags.forgeFluidTag("raw_logic"))
					.attributes(b -> b.viscosity(1500)
							.density(1400))
					.properties(p -> p.levelDecreasePerBlock(2)
							.tickRate(25)
							.slopeFindDistance(3)
							.explosionResistance(100f))
					.register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing>
			NUMBER_0 = number("0"),
			NUMBER_1 = number("1"),
			NUMBER_2 = number("2"),
			NUMBER_3 = number("3"),
			NUMBER_4 = number("4"),
			NUMBER_5 = number("5"),
			NUMBER_6 = number("6"),
			NUMBER_7 = number("7"),
			NUMBER_8 = number("8"),
			NUMBER_9 = number("9");

	public static final FluidEntry<ForgeFlowingFluid.Flowing> CHROMATIC_FLUID =
			REGISTRATE.fluid("chromatic_fluid",
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/chromatic_fluid_still"),
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/chromatic_fluid_flow"), NoColorFluidAttributes::new)
					.lang("Chromatic Fluid")
					.tag(InnovationTags.forgeFluidTag("chromatic_fluid"))
					.attributes(b -> b.viscosity(1500)
							.density(1400))
					.properties(p -> p.levelDecreasePerBlock(2)
							.tickRate(25)
							.slopeFindDistance(3)
							.explosionResistance(100f))
					.register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_SAND =
			REGISTRATE.fluid("fine_sand",
					new ResourceLocation(CreateInnovations.MOD_ID,"fluid/fine_sand_still"),
					new ResourceLocation(CreateInnovations.MOD_ID,"fluid/fine_sand_flow"), NoColorFluidAttributes::new)
					.lang("Fine Sand")
					.tag(InnovationTags.forgeFluidTag("fine_sand"))
					.attributes(b -> b.viscosity(1500)
							.density(1400))
					.properties(p -> p.levelDecreasePerBlock(2)
							.tickRate(25)
							.slopeFindDistance(3)
							.explosionResistance(100f))
					.register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> RESIN =
			REGISTRATE.fluid("resin",
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/resin_still"),
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/resin_flow"), NoColorFluidAttributes::new)
					.lang("Resin")
					.tag(InnovationTags.forgeFluidTag("resin"))
					.attributes(b -> b.viscosity(1500)
							.density(1400))
					.properties(p -> p.levelDecreasePerBlock(2)
							.tickRate(25)
							.slopeFindDistance(3)
							.explosionResistance(100f))
					.register();

	public static final FluidEntry<ForgeFlowingFluid.Flowing> MATRIX =
			REGISTRATE.fluid("matrix",
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/matrix_still"),
							new ResourceLocation(CreateInnovations.MOD_ID,"fluid/matrix_flow"), NoColorFluidAttributes::new)
					.lang("matrix")
					.tag(InnovationTags.forgeFluidTag("matrix"))
					.attributes(b -> b.viscosity(1500)
							.density(1400))
					.properties(p -> p.levelDecreasePerBlock(2)
							.tickRate(25)
							.slopeFindDistance(3)
							.explosionResistance(100f))
					.register();

	private static FluidEntry<ForgeFlowingFluid.Flowing> number(String num) {
		return REGISTRATE.fluid("number_"+num,
						new ResourceLocation(CreateInnovations.MOD_ID,"fluid/number/number_"+num+"_still"),
						new ResourceLocation(CreateInnovations.MOD_ID,"fluid/number/number_"+num+"_flow"), NoColorFluidAttributes::new)
				.lang("number_"+num)
				.tag(InnovationTags.forgeFluidTag("number_"+num))
				.attributes(b -> b.viscosity(1500)
						.density(1400))
				.properties(p -> p.levelDecreasePerBlock(2)
						.tickRate(25)
						.slopeFindDistance(3)
						.explosionResistance(100f))
				.register();
	}

	private static BlockState matrixOre() {
		List<BlockState> ores = Arrays.asList(
				AllPaletteStoneTypes.ASURINE.getBaseBlock().get().defaultBlockState(),
				AllPaletteStoneTypes.CRIMSITE.getBaseBlock().get().defaultBlockState(),
				AllPaletteStoneTypes.OCHRUM.getBaseBlock().get().defaultBlockState(),
				AllPaletteStoneTypes.VERIDIUM.getBaseBlock().get().defaultBlockState()
		);
		Random rand = new Random();
		return ores.get(rand.nextInt(ores.size()));
	}
	
	public static void register() {

	}

	@Nullable
	public static BlockState getLavaInteraction(FluidState fluidState) {
		Fluid fluid = fluidState.getType();
		if (fluid.isSame(LIQUID_SAND.get()))
			return Blocks.SANDSTONE.defaultBlockState();

		if (fluid.isSame(RESIN.get()))
			return Blocks.DRIPSTONE_BLOCK.defaultBlockState();

		if (fluid.isSame(RAW_LOGIC.get()))
			return Blocks.DIORITE.defaultBlockState();

		if (fluid.isSame(CHROMATIC_FLUID.get()))
			return Blocks.COBBLED_DEEPSLATE.defaultBlockState();

		if (fluid.isSame(MATRIX.get()))
			return matrixOre();
		
		return null;
	}
	
	private static class NoColorFluidAttributes extends FluidAttributes {

		protected NoColorFluidAttributes(Builder builder, Fluid fluid) {
			super(builder, fluid);
		}

		@Override
		public int getColor(BlockAndTintGetter world, BlockPos pos) {
			return 0x00ffffff;
		}
	}
}
