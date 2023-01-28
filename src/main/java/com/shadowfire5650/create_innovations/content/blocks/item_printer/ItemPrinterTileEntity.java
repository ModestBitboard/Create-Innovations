package com.shadowfire5650.create_innovations.content.blocks.item_printer;

import com.shadowfire5650.create_innovations.content.item.InnovationItems;
import com.shadowfire5650.create_innovations.content.recipes.InnovationRecipes;
import com.shadowfire5650.create_innovations.content.recipes.ItemPrinterRecipe;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.foundation.tileEntity.TileEntityBehaviour;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ItemPrinterTileEntity extends KineticTileEntity {
	public ItemStackHandler inputInv;
    public ItemStackHandler outputInv;
    public LazyOptional<IItemHandler> capability;
    public int timer;
    private static ItemPrinterRecipe lastRecipe;
    public ItemStackHandler schematicInv;
    protected CombinedInvWrapper inAndOutInv;
  
	public ItemPrinterTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		
		inputInv = new ItemStackHandler(1);
        outputInv = new ItemStackHandler(1);
        capability = LazyOptional.of(ItemPrinterInventoryHandler::new);
        schematicInv = new ItemStackHandler(1){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                if(stack == InnovationItems.ITEM_SCHEMATIC.asStack()) {
                    return true;
                }
                
                if(stack == InnovationItems.CREATIVE_ITEM_SCHEMATIC.asStack()) {
                	return true;
                }
                return false;
            }
        };
    }
	
	@Override
    public void tick() {
        super.tick();

		if (getSpeed() == 0)
			return;

        for (int i = 0; i < outputInv.getSlots(); i++)
			if (outputInv.getStackInSlot(i)
				.getCount() == outputInv.getSlotLimit(i))
				return;
        
        if (timer > 0) {
			timer -= getProcessingSpeed();
			
			if (hasSchematic()) {
				if (level.isClientSide) {
					spawnParticles();
					return;
				}
			}
			
            if (timer <= 0)
                process();
            return;
        }

        if (inputInv.getStackInSlot(0)
                .isEmpty())
            return;

        RecipeWrapper inventoryIn = new RecipeWrapper(inputInv);
		if (lastRecipe == null || !lastRecipe.matches(inventoryIn, level)) {
            Optional<ItemPrinterRecipe> recipe = InnovationRecipes.ITEM_PRINTING.find(inventoryIn, level);
            if (!recipe.isPresent()) {
                timer = 100;
                sendData();
            } else {
                lastRecipe = recipe.get();
                timer = 100;
                sendData();
            }
            return;
        }

        timer = 100;
        sendData();
	}

	public int getProcessingSpeed() {
		return Mth.clamp((int) Math.abs(getSpeed() / 16f), 1, 512);
	}

	private boolean hasSchematicNbt() {
		
		if (!hasSchematic()) {
			return false;
		}
		if (schematicInv.getStackInSlot(0).getItem() == InnovationItems.CREATIVE_ITEM_SCHEMATIC.get()) {
			return true;
		}
		if (!schematicInv.getStackInSlot(0).hasTag()) {
			return false;
		}
		if (!schematicInv.getStackInSlot(0).getTag().contains("recipeId")) {
			return false;
		}
		
		if (!schematicInv.getStackInSlot(0).getTag().getString("recipeId").contains(lastRecipe.getId().toString())) {
			return false;
		}
		
		return true;
    }

	@Override
    public void setRemoved() {
        super.setRemoved();
        capability.invalidate();
    }
	
	private void process() {
		RecipeWrapper inventoryIn = new RecipeWrapper(inputInv);
		
		if (lastRecipe == null || !lastRecipe.matches(inventoryIn, level)) {
			Optional<ItemPrinterRecipe> recipe = InnovationRecipes.ITEM_PRINTING.find(inventoryIn, level);
			if (!recipe.isPresent())
				return;
			
			lastRecipe = recipe.get();
		}
		
		if (!hasSchematicNbt())
			return;
		
		ItemStack stackInSlot = inputInv.getStackInSlot(0);
		stackInSlot.shrink(1);
		inputInv.setStackInSlot(0, stackInSlot);
		lastRecipe.rollResults()
			.forEach(stack -> ItemHandlerHelper.insertItemStacked(outputInv, stack, false));
		
		sendData();
		setChanged();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
	    if (isItemHandlerCap(cap)) {
	    	return capability.cast();
	    }
	    return super.getCapability(cap, side);
	}
	
	private boolean canProcess(ItemStack stack) {
		ItemStackHandler tester = new ItemStackHandler(1);
		tester.setStackInSlot(0, stack);
		RecipeWrapper inventoryIn = new RecipeWrapper(tester);

		if (lastRecipe != null && lastRecipe.matches(inventoryIn, level))
			return true;
		return InnovationRecipes.ITEM_PRINTING.find(inventoryIn, level)
			.isPresent();
	}
	    
	public void insertSchematic(ItemStack schematicStack, Player player) {
	    if(schematicInv.getStackInSlot(0).isEmpty()){
	        ItemStack schematicToInsert = schematicStack.copy();
	        schematicToInsert.setCount(1);
	        schematicStack.shrink(1);
	        schematicInv.setStackInSlot(0, schematicToInsert);
	        setChanged();
	    }
	}
	
	public boolean hasSchematic() {
	    return !schematicInv.getStackInSlot(0).isEmpty();
	}

	public void removeSchematic(Player player) {
	    player.getInventory().placeItemBackInInventory(schematicInv.getStackInSlot(0));
	    schematicInv.setStackInSlot(0, ItemStack.EMPTY);
	}
	
	public void spawnParticles() {
		ItemStack stackInSlot = inputInv.getStackInSlot(0);
		if (stackInSlot.isEmpty())
			return;

		ItemParticleOption data = new ItemParticleOption(ParticleTypes.ITEM, stackInSlot);
		float angle = level.random.nextFloat() * 360;
		Vec3 offset = new Vec3(0, 0, 0.5f);
		offset = VecHelper.rotate(offset, angle, Axis.Y);
		Vec3 target = VecHelper.rotate(offset, 25, Axis.Y);

		Vec3 center = offset.add(VecHelper.getCenterOf(worldPosition));
		target = VecHelper.offsetRandomly(target.subtract(offset), level.random, 1 / 128f);
		level.addParticle(data, center.x, center.y, center.z, target.x, target.y, target.z);
	}
	
	@Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putInt("Timer", timer);
        compound.put("InputInventory", inputInv.serializeNBT());
        compound.put("OutputInventory", outputInv.serializeNBT());
        compound.put("SchematicInventory", schematicInv.serializeNBT());

        super.write(compound, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        timer = compound.getInt("Timer");
        inputInv.deserializeNBT(compound.getCompound("InputInventory"));
        outputInv.deserializeNBT(compound.getCompound("OutputInventory"));
        schematicInv.deserializeNBT(compound.getCompound("SchematicInventory"));
        super.read(compound, clientPacket);
    }
	
    private class ItemPrinterInventoryHandler extends CombinedInvWrapper {

    	public ItemPrinterInventoryHandler() {
            super(inputInv, outputInv);
        }
    	
    	@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			if (outputInv == getHandlerFromIndex(getIndexForSlot(slot)))
				return false;
			return canProcess(stack) && super.isItemValid(slot, stack);
		}

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (outputInv == getHandlerFromIndex(getIndexForSlot(slot)))
                return stack;
            if (!isItemValid(slot, stack))
                return stack;
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (inputInv == getHandlerFromIndex(getIndexForSlot(slot)))
                return ItemStack.EMPTY;
            return super.extractItem(slot, amount, simulate);
        }
    }

	@Override
	public void addBehaviours(List<TileEntityBehaviour> behaviours) {
	}
	
	public void playSound(SoundEvent sound, float volume, float pitch) {
		Vec3 vec = VecHelper.getCenterOf(worldPosition);
		level.playLocalSound(vec.x, vec.y, vec.z, sound, SoundSource.BLOCKS, volume, pitch, false);
	}
}
