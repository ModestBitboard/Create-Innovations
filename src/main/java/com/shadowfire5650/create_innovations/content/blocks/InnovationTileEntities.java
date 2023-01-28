package com.shadowfire5650.create_innovations.content.blocks;

import com.shadowfire5650.create_innovations.CreateInnovations;
import com.shadowfire5650.create_innovations.content.blocks.item_printer.ItemPrinterTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance;
import com.simibubi.create.content.contraptions.relays.encased.ShaftRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class InnovationTileEntities {

	public static final BlockEntityEntry<ItemPrinterTileEntity> ITEM_PRINTER = CreateInnovations.registrate()
			.tileEntity("mechanical_item_printer", ItemPrinterTileEntity::new)
			.instance(() -> ShaftInstance::new, false)
			.validBlocks(InnovationBlocks.ITEM_PRINTER)
			.renderer(() -> ShaftRenderer::new)
			.register();
	
	public static void register() {
		
	}
}
