package com.tiki.advancedlootableweapons.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> INGOTS_BRONZE = forge("ingots/bronze");
    public static final TagKey<Item> INGOTS_CRYSTALLITE = forge("ingots/crystallite");
    public static final TagKey<Item> INGOTS_DUSKSTEEL = forge("ingots/dusksteel");
    public static final TagKey<Item> INGOTS_FROST_STEEL = forge("ingots/frost_steel");
    public static final TagKey<Item> INGOTS_KOBOLD = forge("ingots/kobold");
    public static final TagKey<Item> INGOTS_PLATINUM = forge("ingots/platinum");
    public static final TagKey<Item> INGOTS_REFINED_OBSIDIAN = forge("ingots/refined_obsidian");
    public static final TagKey<Item> INGOTS_SILVER = forge("ingots/silver");
    public static final TagKey<Item> INGOTS_STEEL = forge("ingots/steel");
    public static final TagKey<Item> INGOTS_SHADOW_PLATINUM = forge("ingots/shadow_platinum");


    private static TagKey<Item> forge(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
