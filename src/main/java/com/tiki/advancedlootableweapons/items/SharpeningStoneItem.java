package com.tiki.advancedlootableweapons.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class SharpeningStoneItem extends Item {
    private final Tier tier;

    public SharpeningStoneItem(Properties pProperties, Tier tier) {
        super(pProperties);
        this.tier = tier;
    }
}
