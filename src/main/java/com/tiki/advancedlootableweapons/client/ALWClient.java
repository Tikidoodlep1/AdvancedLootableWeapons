package com.tiki.advancedlootableweapons.client;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.tiki.advancedlootableweapons.AdvancedLootableWeapons.MODID;

public class ALWClient {

    static final ItemPropertyFunction HEAT_FUNCTION_WRAPPER = (pP1, pP2, pP3, pP4) -> ItemHotToolHead.HEAT_FUNCTION.apply(pP1, pP2, pP3, pP4).ordinal();

    public static void registerItemModelPredicates() {
        for(Item i : ItemInit.hotToolHeads) {
            ItemProperties.register(i, new ResourceLocation(MODID, "heat"), HEAT_FUNCTION_WRAPPER);
        }
    }
}
