package com.shadowfire5650.create_innovations.content.blocks;

import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ThermalCasingBlock extends Block implements IWrenchable {

    public ThermalCasingBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.FAIL;
    }

}