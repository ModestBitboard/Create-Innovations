package com.shadowfire5650.create_innovations.foundation.util;

import com.shadowfire5650.create_innovations.CreateInnovations;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ProxyUtils {
	
	private ProxyUtils() {

    }
	public static void registerItemModelProperty(Item item, ResourceLocation resourceLoc, IProxyItemPropertyGetter propertyGetter) {

        CreateInnovations.PROXY.registerItemModelProperty(item, resourceLoc, propertyGetter);
    }

}
