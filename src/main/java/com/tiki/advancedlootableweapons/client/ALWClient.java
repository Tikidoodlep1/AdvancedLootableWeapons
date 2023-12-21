package com.tiki.advancedlootableweapons.client;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.tiki.advancedlootableweapons.AdvancedLootableWeapons.MODID;

public class ALWClient {

    static final ItemPropertyFunction HEAT_FUNCTION_WRAPPER = ItemHotToolHead.HEAT_FUNCTION::apply;

    public static void registerItemModelPredicates() {
        for(Item i : ItemInit.hotToolHeads) {
            ItemProperties.register(i, new ResourceLocation(MODID, "heat"), HEAT_FUNCTION_WRAPPER);
        }
    }
}
