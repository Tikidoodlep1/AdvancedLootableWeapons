package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModMaterials {
    //Mats
    public static final ConfigurableForgeTier KOBOLD_STEEL = new ConfigurableForgeTier(1, CommonConfigHandler.KOBOLD_STEEL_DURABILITY, 6.0F, CommonConfigHandler.KOBOLD_STEEL_DAMAGE, 22,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.KOBOLD_STEEL_INGOT.get()));
    public static final ConfigurableForgeTier COPPER = new ConfigurableForgeTier(1, CommonConfigHandler.COPPER_DURABILITY, 6.0F, CommonConfigHandler.COPPER_DAMAGE, 22,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final ConfigurableForgeTier SILVER = new ConfigurableForgeTier(1, CommonConfigHandler.SILVER_DURABILITY, 7.0F, CommonConfigHandler.SILVER_DAMAGE, 24,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.SILVER_INGOT.get()));
    public static final ConfigurableForgeTier BRONZE = new ConfigurableForgeTier(2, CommonConfigHandler.BRONZE_DURABILITY, 6.5F, CommonConfigHandler.BRONZE_DAMAGE, 12,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.BRONZE_INGOT.get()));
    public static final ConfigurableForgeTier PLATINUM = new ConfigurableForgeTier(2, CommonConfigHandler.PLATINUM_DURABILITY, 10.0F, CommonConfigHandler.PLATINUM_DAMAGE, 26,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.PLATINUM_INGOT.get()));
    public static final ConfigurableForgeTier STEEL = new ConfigurableForgeTier(2, CommonConfigHandler.STEEL_DURABILITY, 7.0F, CommonConfigHandler.STEEL_DAMAGE, 18,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.STEEL_INGOT.get()));
    public static final ConfigurableForgeTier SHADOW_PLATINUM = new ConfigurableForgeTier(3, CommonConfigHandler.SHADOW_PLATINUM_DURABILITY, 8.25F, CommonConfigHandler.SHADOW_PLATINUM_DAMAGE, 21,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.SHADOW_PLATINUM_INGOT.get()));
    public static final ConfigurableForgeTier FROST_STEEL = new ConfigurableForgeTier(3, CommonConfigHandler.FROST_STEEL_DURABILITY, 7.0F, CommonConfigHandler.FROST_STEEL_DAMAGE, 30,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.FROST_STEEL_INGOT.get()));
    public static final ConfigurableForgeTier REFINED_OBSIDIAN = new ConfigurableForgeTier(3, CommonConfigHandler.REFINED_OBSIDIAN_DURABILITY, 7.5F, CommonConfigHandler.REFINED_OBSIDIAN_DAMAGE, 18,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.REFINED_OBSIDIAN_INGOT.get()));
    public static final ConfigurableForgeTier CRYSTALLITE = new ConfigurableForgeTier(4, CommonConfigHandler.CRYSTALLITE_DURABILITY, 7.5F, CommonConfigHandler.CRYSTALLITE_DAMAGE, 20,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.CRYSTALLITE_INGOT.get()));
    public static final ConfigurableForgeTier DUSKSTEEL = new ConfigurableForgeTier(4, CommonConfigHandler.DUSKSTEEL_DURABILITY, 8.5F, CommonConfigHandler.DUSKSTEEL_DAMAGE, 14,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.DUSKSTEEL_INGOT.get()));
}
