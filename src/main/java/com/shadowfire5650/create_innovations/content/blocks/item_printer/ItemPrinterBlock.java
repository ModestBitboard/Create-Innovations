package com.shadowfire5650.create_innovations.content.blocks.item_printer;

import com.shadowfire5650.create_innovations.content.blocks.InnovationTileEntities;
import com.shadowfire5650.create_innovations.content.item.InnovationItems;
import com.simibubi.create.content.contraptions.base.KineticBlock;
import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ItemPrinterBlock extends KineticBlock implements ITE<ItemPrinterTileEntity> {
	
	public static final BooleanProperty LOADED = BooleanProperty.create("loaded");
	public static final BooleanProperty CREATIVE = BooleanProperty.create("creative");

	public ItemPrinterBlock(Properties pProperties) {
		super(pProperties);
		registerDefaultState(defaultBlockState().setValue(LOADED, false));
		registerDefaultState(defaultBlockState().setValue(CREATIVE, false));
	}

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face == Direction.DOWN || face == Direction.UP;
	}

	@Override
	public Axis getRotationAxis(BlockState state) {
		return Axis.Y;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
	                                 BlockHitResult hit) {
	    ItemPrinterTileEntity fabricatorTileEntity = (ItemPrinterTileEntity) worldIn.getBlockEntity(pos);
	    ItemStack handInStack = player.getItemInHand(handIn);

	    if (worldIn.isClientSide) {
	        return InteractionResult.SUCCESS;
	    }
	    
	    if(handInStack.getItem() == InnovationItems.ITEM_SCHEMATIC.get()) {
	        fabricatorTileEntity.insertSchematic(handInStack, player);
	        worldIn.setBlock(pos, state.setValue(LOADED, true).setValue(CREATIVE, false), 3);
	    }
	    
	    if(handInStack.getItem() == InnovationItems.CREATIVE_ITEM_SCHEMATIC.get()) {
	        fabricatorTileEntity.insertSchematic(handInStack, player);
	        worldIn.setBlock(pos, state.setValue(LOADED, false).setValue(CREATIVE, true), 3);
	    }
	    
	    if(handInStack.isEmpty() && fabricatorTileEntity.hasSchematic() && player.isShiftKeyDown()) {
	        fabricatorTileEntity.removeSchematic(player);
	        worldIn.setBlock(pos, state.setValue(LOADED, false).setValue(CREATIVE, false), 3);
	    }

	    if (!handInStack.isEmpty()) {
	        return InteractionResult.PASS;
	    }

	    withTileEntityDo(worldIn, pos, fabricator -> {
	            boolean emptyOutput = true;
	            IItemHandlerModifiable inv = fabricator.outputInv;
	            for (int slot = 0; slot < inv.getSlots(); slot++) {
	                ItemStack stackInSlot = inv.getStackInSlot(slot);
	                if (!stackInSlot.isEmpty())
	                    emptyOutput = false;
	                player.getInventory()
	                        .placeItemBackInInventory(stackInSlot);
	                inv.setStackInSlot(slot, ItemStack.EMPTY);
	            }

	            if (emptyOutput) {
	                inv = fabricator.inputInv;
	                for (int slot = 0; slot < inv.getSlots(); slot++) {
	                    player.getInventory()
	                            .placeItemBackInInventory(inv.getStackInSlot(slot));
	                    inv.setStackInSlot(slot, ItemStack.EMPTY);
	                }
	            }

	            fabricator.setChanged();
	            fabricator.sendData();
	        });

	        return InteractionResult.SUCCESS;
	    }
	
	@Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasBlockEntity() && state.getBlock() != newState.getBlock()) {
            withTileEntityDo(worldIn, pos, te -> {
                ItemHelper.dropContents(worldIn, pos, te.inputInv);
                ItemHelper.dropContents(worldIn, pos, te.schematicInv);
                ItemHelper.dropContents(worldIn, pos, te.outputInv);
            });

            worldIn.removeBlockEntity(pos);
        }
    }
	
	@Override
	public Class<ItemPrinterTileEntity> getTileEntityClass() {
		return ItemPrinterTileEntity.class;
	}
	
	@Override
	public BlockEntityType<? extends ItemPrinterTileEntity> getTileEntityType() {
		return InnovationTileEntities.ITEM_PRINTER.get();
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(LOADED));
		super.createBlockStateDefinition(pBuilder.add(CREATIVE));
	}
}
