package com.tiki.advancedlootableweapons.client;

import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.tiki.advancedlootableweapons.AdvancedLootableWeapons.MODID;

public class ALWClient {

    static final ItemPropertyFunction HEAT_FUNCTION = (stack, world, player, id) -> {
        if (stack.getDamageValue() <= 3000) {
            return 0;
        } else if (stack.getDamageValue() > 3000 && stack.getDamageValue() < 5000) {
            return 1;
        } else {
            return 2;
        }
    };

    public static void registerItemModelPredicates() {
        for(Item i : ItemInit.hotToolHeads) {
            ItemProperties.register(i, new ResourceLocation(MODID, "heat"), HEAT_FUNCTION);
        }
    }
}
