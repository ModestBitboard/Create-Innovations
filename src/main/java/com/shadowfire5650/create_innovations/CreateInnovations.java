package com.shadowfire5650.create_innovations;

import com.shadowfire5650.create_innovations.content.blocks.InnovationBlocks;
import com.shadowfire5650.create_innovations.content.blocks.InnovationTileEntities;
import com.shadowfire5650.create_innovations.content.item.InnovationItems;
import com.shadowfire5650.create_innovations.content.fluid.InnovationFluids;
import com.shadowfire5650.create_innovations.content.recipes.InnovationRecipes;
import com.shadowfire5650.create_innovations.content.tags.InnovationTags;
import com.shadowfire5650.create_innovations.foundation.util.Proxy;
import com.shadowfire5650.create_innovations.foundation.util.ProxyClient;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("create_innovations")
public class CreateInnovations {
	
	public static final Proxy PROXY = DistExecutor.unsafeRunForDist(() -> ProxyClient::new, () -> Proxy::new);
	public static final String MOD_ID = "create_innovations";
	public static final String NAME = "Create Innovations";
	public static final String VERSION = "1.0";
	
	private static final NonNullSupplier<CreateRegistrate> registrate = CreateRegistrate.lazy(CreateInnovations.MOD_ID);
	
	public static final CreativeModeTab MAIN_TAB = new CreativeModeTab(MOD_ID + "_main") {
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(InnovationItems.INTEGRATED_CIRCUIT.get());
		}
	};

	public CreateInnovations() {
		onCtor();

	}
	
	public static void onCtor() {
		ModLoadingContext modLoadingContext = ModLoadingContext.get();

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

		InnovationBlocks.register();
		InnovationTileEntities.register();
		InnovationItems.register();
		InnovationFluids.register();
		InnovationTags.register();
		InnovationRecipes.register(modEventBus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> onCtorClient(modEventBus, forgeEventBus));

		modEventBus.addListener(CreateInnovations::clientSetup);
	}

	private static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
		InnovationBlockPartials.init();
	}

	private static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ProxyClient::registerItemModelProperties);

    }

	public static CreateRegistrate registrate() {
		return registrate.get();
	}
	
	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
