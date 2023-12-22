package com.tiki.advancedlootableweapons.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> INGOTS_SILVER = forge("ingots/silver");
    public static final TagKey<Item> INGOTS_PLATINUM = forge("ingots/platinum");


    private static TagKey<Item> forge(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
