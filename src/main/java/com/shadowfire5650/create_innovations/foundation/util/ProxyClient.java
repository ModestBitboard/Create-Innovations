package com.shadowfire5650.create_innovations.foundation.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

@SuppressWarnings("deprecation")
public class ProxyClient extends Proxy {
	protected static final Set<ModelPropertyWrapper> ITEM_PROPERTY_GETTERS = new HashSet<>();
	
	@Override
	public void registerItemModelProperty(Item item, ResourceLocation resourceLoc, IProxyItemPropertyGetter propertyGetter) {

        ITEM_PROPERTY_GETTERS.add(new ModelPropertyWrapper(item, resourceLoc, propertyGetter));
    }
	
	public static void registerItemModelProperties() {

        for (ModelPropertyWrapper wrapper : ITEM_PROPERTY_GETTERS) {
            ItemProperties.register(wrapper.item, wrapper.resourceLoc, wrapper.propertyGetter);
        }
        ITEM_PROPERTY_GETTERS.clear();
    }

    protected static class ModelPropertyWrapper {

        Item item;
        ResourceLocation resourceLoc;
        ItemPropertyFunction propertyGetter;

        ModelPropertyWrapper(Item item, ResourceLocation resourceLoc, IProxyItemPropertyGetter propertyGetter) {

            this.item = item;
            this.resourceLoc = resourceLoc;
            this.propertyGetter = propertyGetter::call;
        }

    }
	
	
}
