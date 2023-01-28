package com.shadowfire5650.create_innovations.content.blocks;

import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ThermalGlassCasingBlock extends GlassBlock implements IWrenchable {

    public ThermalGlassCasingBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.getBlock() instanceof ThermalGlassCasingBlock ? true
                : super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.FAIL;
    }

}