package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.categories.DrumQuenchingCategory;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.*;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.ArmorBindingItem;
import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;

import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen) {
        super(gen, AdvancedLootableWeapons.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        blockTranslations();
        itemTranslations();

        addDefaultFluid(FluidInit.MILK_OF_LIME);
        addDefaultFluid(FluidInit.MAGNESIUM_LACTATE);

        add(ForgeHammerItem.INFO,"Hit an anvil with me to start forging weapons!");
        add(ArmorBindingItem.INFO,"Bonus Durability");

        add(HeatableToolPartItem.QUENCH_KEY,"Quenched");
        add(HeatableToolPartItem.UNQUENCH_KEY,"Unquenched");

        add(DrumQuenchingCategory.REQUIRES_CLAY,"Requires Clay");

        addDefaultMenu(MenuInit.ANVIL_FORGING);
        addDefaultMenu(MenuInit.JAW_CRUSHER);
        add("container.advancedlootableweapons.sharpeningStone", "Sharpen Weapon");
        add("container.advancedlootableweapons.alloy_furnace", "Alloy Furnace");
        add("container.advancedlootableweapons.forge", "Forge");
        add("container.advancedlootableweapons.forge2", "Advanced Forge");
        add("container.advancedlootableweapons.forgeWeapon", "Anvil Forging");

        add(REICompat.QUENCHING,"Drum Quenching");
        add(REICompat.DRUM_CAT,"Drum");
        add(REICompat.JAW_CRUSHER_CAT,"Jaw Crusher");
        add(REICompat.ALLOY_FURNACE_CAT,"Alloy Furnace");


        add("enchantment.advancedlootableweapons.refined", "Refined");


        add("attribute.name.generic.bonusAttackDamage", "Bonus Attack Damage");


        addTab(ModCreativeTabs.MISC_TAB, "Advanced Lootable Weapons");
        addTab(ModCreativeTabs.BLOCK_TAB, "ALW Blocks");
        addTab(ModCreativeTabs.WEAPON_TAB, "ALW Weapons & Hot Tool Heads");

        add(HeatableToolPartItem.Temp.cool.translation.getKey(),"Cool");
        add(HeatableToolPartItem.Temp.warm.translation.getKey(),"Warm");
        add(HeatableToolPartItem.Temp.hot.translation.getKey(),"Hot");
    }

    protected void blockTranslations() {
        addDefaultBlock(BlockInit.BRONZE_BLOCK);
        addDefaultBlock(BlockInit.CRYSTALLITE_BLOCK);
        addDefaultBlock(BlockInit.DUSKSTEEL_BLOCK);
        addDefaultBlock(BlockInit.FROST_STEEL_BLOCK);
        addDefaultBlock(BlockInit.KOBOLD_STEEL_BLOCK);
        addDefaultBlock(BlockInit.PLATINUM_BLOCK);
        addDefaultBlock(BlockInit.REFINED_OBSIDIAN_BLOCK);
        addDefaultBlock(BlockInit.SHADOW_PLATINUM_BLOCK);
        addDefaultBlock(BlockInit.SILVER_BLOCK);
        addDefaultBlock(BlockInit.STEEL_BLOCK);
        addDefaultBlock(BlockInit.TIN_BLOCK);

        addDefaultBlock(BlockInit.MILK_OF_LIME);
        addDefaultBlock(BlockInit.MAGNESIUM_LACTATE);

        addDefaultBlock(BlockInit.CRYSTALLITE_ORE);
        addDefaultBlock(BlockInit.PLATINUM_ORE);
        addDefaultBlock(BlockInit.SILVER_ORE);
        addDefaultBlock(BlockInit.TIN_ORE);

        addDefaultBlock(BlockInit.COBBLED_FELDSPAR);
        addDefaultBlock(BlockInit.FELDSPAR);
        addDefaultBlock(BlockInit.DIORITE_BRICKS);
        addDefaultBlock(BlockInit.GRANITE_BRICKS);

        addDefaultBlock(BlockInit.DIORITE_CLAY_POWDER);
        addDefaultBlock(BlockInit.GRANITE_CLAY_POWDER);
        addDefaultBlock(BlockInit.DIORITE_CLAY);
        addDefaultBlock(BlockInit.GRANITE_CLAY);
        addDefaultBlock(BlockInit.ALLOY_FURNACE);
        addDefaultBlock(BlockInit.FORGE);
        addDefaultBlock(BlockInit.ADVANCED_FORGE);
        addDefaultBlock(BlockInit.CLAY_DRUM);
        add("block.advancedlootableweapons.advanced_forge_1", "Advanced Forge");
        addDefaultBlock(BlockInit.JAW_CRUSHER);

        addDefaultBlock(BlockInit.OAK_BELLOWS);
        addDefaultBlock(BlockInit.BIRCH_BELLOWS);
        addDefaultBlock(BlockInit.JUNGLE_BELLOWS);
        addDefaultBlock(BlockInit.SPRUCE_BELLOWS);
        addDefaultBlock(BlockInit.ACACIA_BELLOWS);
        addDefaultBlock(BlockInit.DARK_OAK_BELLOWS);
    }

    protected void itemTranslations() {
        addDefaultItem(ItemInit.BRONZE_INGOT);
        addDefaultItem(ItemInit.CRYSTALLITE_INGOT);
        addDefaultItem(ItemInit.DUSKSTEEL_INGOT);
        addDefaultItem(ItemInit.FROST_STEEL_INGOT);
        addDefaultItem(ItemInit.KOBOLD_STEEL_INGOT);
        addDefaultItem(ItemInit.PLATINUM_INGOT);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_INGOT);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_INGOT);
        addDefaultItem(ItemInit.SILVER_INGOT);
        addDefaultItem(ItemInit.STEEL_INGOT);
        addDefaultItem(ItemInit.TIN_INGOT);

        addDefaultItem(ItemInit.MILK_OF_LIME_BUCKET);
        addDefaultItem(ItemInit.MAGNESIUM_LACTATE_BUCKET);

        addDefaultItem(ItemInit.BRONZE_NUGGET);
        addDefaultItem(ItemInit.CRYSTALLITE_NUGGET);
        addDefaultItem(ItemInit.DUSKSTEEL_NUGGET);
        addDefaultItem(ItemInit.FROST_STEEL_NUGGET);
        addDefaultItem(ItemInit.KOBOLD_STEEL_NUGGET);
        addDefaultItem(ItemInit.PLATINUM_NUGGET);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_NUGGET);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_NUGGET);
        addDefaultItem(ItemInit.SILVER_NUGGET);
        addDefaultItem(ItemInit.STEEL_NUGGET);
        addDefaultItem(ItemInit.TIN_NUGGET);

        addDefaultItem(ItemInit.IRON_CHAIN_LINK);
        addDefaultItem(ItemInit.GOLD_CHAIN_LINK);
        addDefaultItem(ItemInit.COPPER_CHAIN_LINK);
        addDefaultItem(ItemInit.SILVER_CHAIN_LINK);
        addDefaultItem(ItemInit.BRONZE_CHAIN_LINK);
        addDefaultItem(ItemInit.PLATINUM_CHAIN_LINK);
        addDefaultItem(ItemInit.STEEL_CHAIN_LINK);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_CHAIN_LINK);
        addDefaultItem(ItemInit.KOBOLD_STEEL_CHAIN_LINK);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_CHAIN_LINK);
        addDefaultItem(ItemInit.FROST_STEEL_CHAIN_LINK);
        addDefaultItem(ItemInit.CRYSTALLITE_CHAIN_LINK);
        addDefaultItem(ItemInit.DUSKSTEEL_CHAIN_LINK);

        addDefaultItem(ItemInit.IRON_ARMOR_PLATE);
        addDefaultItem(ItemInit.GOLD_ARMOR_PLATE);
        addDefaultItem(ItemInit.DIAMOND_STUDDED_STEEL_ARMOR_PLATE);
        addDefaultItem(ItemInit.COPPER_ARMOR_PLATE);
        addDefaultItem(ItemInit.SILVER_ARMOR_PLATE);
        addDefaultItem(ItemInit.BRONZE_ARMOR_PLATE);
        addDefaultItem(ItemInit.PLATINUM_ARMOR_PLATE);
        addDefaultItem(ItemInit.STEEL_ARMOR_PLATE);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_ARMOR_PLATE);
        addDefaultItem(ItemInit.KOBOLD_STEEL_ARMOR_PLATE);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_ARMOR_PLATE);
        addDefaultItem(ItemInit.FROST_STEEL_ARMOR_PLATE);
        addDefaultItem(ItemInit.CRYSTALLITE_ARMOR_PLATE);
        addDefaultItem(ItemInit.DUSKSTEEL_ARMOR_PLATE);

        addDefaultItem(ItemInit.DAGGER);
        addDefaultItem(ItemInit.KABUTOWARI);
        addDefaultItem(ItemInit.RAPIER);
        addDefaultItem(ItemInit.TALWAR);
        addDefaultItem(ItemInit.CLEAVER);
        addDefaultItem(ItemInit.MACE);
        addDefaultItem(ItemInit.STAFF);
        addDefaultItem(ItemInit.LONGSWORD);
        addDefaultItem(ItemInit.KODACHI);
        addDefaultItem(ItemInit.NODACHI);
        addDefaultItem(ItemInit.BATTLEAXE);
        addDefaultItem(ItemInit.ZWEIHANDER);
        addDefaultItem(ItemInit.SABRE);
        addDefaultItem(ItemInit.MAKHAIRA);
        addDefaultItem(ItemInit.SPEAR);

        for (String s : WeaponMaterial.LOOKUP.keySet()) {
            add(WeaponMaterial.getTranslationKey(s).getKey(),getBasicName(s));
        }

        addDefaultItem(ItemInit.TANNING_KNIFE);
        addDefaultItem(ItemInit.UNTRIMMED_HIDE);
        addDefaultItem(ItemInit.TRIMMED_HIDE);
        addDefaultItem(ItemInit.CURED_HIDE);
        addDefaultItem(ItemInit.LIMED_HIDE);
        addDefaultItem(ItemInit.DELIMED_HIDE);
        addDefaultItem(ItemInit.SALT);

        addDefaultItem(ItemInit.SHADOW);
        addDefaultItem(ItemInit.CONGEALED_SHADOW);
        addDefaultItem(ItemInit.CRYSTAL);
        addDefaultItem(ItemInit.OBSIDIAN_SHARD);

        addDefaultItem(ItemInit.FELDSPAR_POWDER);
        addDefaultItem(ItemInit.DIORITE_BRICK);
        addDefaultItem(ItemInit.DIORITE_CLAY_BALL);
        addDefaultItem(ItemInit.DIORITE_POWDER);
        addDefaultItem(ItemInit.GRANITE_BRICK);
        addDefaultItem(ItemInit.GRANITE_CLAY_BALL);
        addDefaultItem(ItemInit.GRANITE_POWDER);

        addDefaultItem(ItemInit.STONE_FORGE_HAMMER);
        addDefaultItem(ItemInit.IRON_FORGE_HAMMER);
        addDefaultItem(ItemInit.COPPER_FORGE_HAMMER);
        addDefaultItem(ItemInit.SILVER_FORGE_HAMMER);
        addDefaultItem(ItemInit.BRONZE_FORGE_HAMMER);
        addDefaultItem(ItemInit.PLATINUM_FORGE_HAMMER);
        addDefaultItem(ItemInit.STEEL_FORGE_HAMMER);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_FORGE_HAMMER);
        addDefaultItem(ItemInit.KOBOLD_STEEL_FORGE_HAMMER);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_FORGE_HAMMER);
        addDefaultItem(ItemInit.FROST_STEEL_FORGE_HAMMER);
        addDefaultItem(ItemInit.CRYSTALLITE_FORGE_HAMMER);
        addDefaultItem(ItemInit.DUSKSTEEL_FORGE_HAMMER);


        addDefaultItem(ItemInit.BRONZE_WHETSTONE);
        addDefaultItem(ItemInit.COPPER_WHETSTONE);
        addDefaultItem(ItemInit.CRYSTALLITE_WHETSTONE);
        addDefaultItem(ItemInit.DUSKSTEEL_WHETSTONE);
        addDefaultItem(ItemInit.FROST_STEEL_WHETSTONE);
        addDefaultItem(ItemInit.KOBOLD_STEEL_WHETSTONE);
        addDefaultItem(ItemInit.PLATINUM_WHETSTONE);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_WHETSTONE);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_WHETSTONE);
        addDefaultItem(ItemInit.SILVER_WHETSTONE);
        addDefaultItem(ItemInit.STEEL_WHETSTONE);

        add(ItemInit.TOOL_HEAD.get(), "Tool Head");

        add(ItemInit.TOOL_ROD.get(), "Tool Rod(Stage 1)");
        add(ItemInit.TOOL_ROD_2.get(), "Tool Rod(Finished)");
        
        add(ItemInit.LONG_TOOL_ROD.get(), "Long Tool Rod");
        add(ItemInit.WOODEN_LONG_TOOL_ROD.get(), "Wooden Long Tool Rod");

        add(ItemInit.WOODEN_DAGGER_HEAD.get(), "Wooden Dagger Head");
        add(ItemInit.DAGGER_HEAD.get(), "Dagger Head(Stage 1)");
        add(ItemInit.DAGGER_HEAD_2.get(), "Dagger Head(Finished)");

        add(ItemInit.WOODEN_KABUTOWARI_HEAD.get(), "Wooden Kabutowari Head");
        add(ItemInit.KABUTOWARI_HEAD.get(), "Kabutowari Head(Stage 1)");
        add(ItemInit.KABUTOWARI_HEAD_2.get(), "Kabutowari Head(Stage 2)");
        add(ItemInit.KABUTOWARI_HEAD_3.get(), "Kabutowari Head(Stage 3)");
        add(ItemInit.KABUTOWARI_HEAD_4.get(), "Kabutowari Head(Stage 4)");
        add(ItemInit.KABUTOWARI_HEAD_5.get(), "Kabutowari Head(Finished)");

        add(ItemInit.WOODEN_TALWAR_HEAD.get(), "Wooden Talwar Head");
        add(ItemInit.TALWAR_HEAD.get(), "Talwar Head(Stage 1)");
        add(ItemInit.TALWAR_HEAD_2.get(), "Talwar Head(Stage 2)");
        add(ItemInit.TALWAR_HEAD_3.get(), "Talwar Head(Finished)");

        add(ItemInit.WOODEN_RAPIER_HEAD.get(), "Wooden Rapier Head");
        add(ItemInit.RAPIER_HEAD.get(), "Rapier Head(Stage 1)");
        add(ItemInit.RAPIER_HEAD_2.get(), "Rapier Head(Stage 2)");
        add(ItemInit.RAPIER_HEAD_3.get(), "Rapier Head(Stage 3)");
        add(ItemInit.RAPIER_HEAD_4.get(), "Rapier Head(Finished)");

        add(ItemInit.WOODEN_CLEAVER_HEAD.get(), "Wooden Cleaver Head");
        add(ItemInit.CLEAVER_HEAD.get(), "Cleaver Head(Finished)");

        add(ItemInit.WOODEN_MACE_HEAD.get(), "Wooden Mace Head");
        add(ItemInit.MACE_HEAD.get(), "Mace Head(Stage 1)");
        add(ItemInit.MACE_HEAD_2.get(), "Mace Head(Stage 2)");
        add(ItemInit.MACE_HEAD_3.get(), "Mace Head(Finished)");

        add(ItemInit.WOODEN_STAFF_HEAD.get(), "Wooden Staff Head");
        add(ItemInit.STAFF_HEAD.get(), "Staff Head(Stage 1)");
        add(ItemInit.STAFF_HEAD_2.get(), "Staff Head(Stage 2)");
        add(ItemInit.STAFF_HEAD_3.get(), "Staff Head(Stage 3)");
        add(ItemInit.STAFF_HEAD_4.get(), "Staff Head(Stage 4)");
        add(ItemInit.STAFF_HEAD_5.get(), "Staff Head(Finished)");

        add(ItemInit.WOODEN_LONGSWORD_HEAD.get(), "Wooden Longsword Head");
        add(ItemInit.LONGSWORD_HEAD.get(), "Longsword Head(Stage 1)");
        add(ItemInit.LONGSWORD_HEAD_2.get(), "Longsword Head(Stage 2)");
        add(ItemInit.LONGSWORD_HEAD_3.get(), "Longsword Head(Stage 3)");
        add(ItemInit.LONGSWORD_HEAD_4.get(), "Longsword Head(Finished)");

        add(ItemInit.WOODEN_KODACHI_HEAD.get(), "Wooden Kodachi Head");
        add(ItemInit.KODACHI_HEAD.get(), "Kodachi Head(Stage 1)");
        add(ItemInit.KODACHI_HEAD_2.get(), "Kodachi Head(Finished)");

        add(ItemInit.WOODEN_BATTLEAXE_HEAD.get(), "Wooden Battleaxe Head");
        add(ItemInit.BATTLEAXE_HEAD.get(), "Battleaxe Head(Stage 1)");
        add(ItemInit.BATTLEAXE_HEAD_2.get(), "Battleaxe Head(Stage 2)");
        add(ItemInit.BATTLEAXE_HEAD_3.get(), "Battleaxe Head(Stage 3)");
        add(ItemInit.BATTLEAXE_HEAD_4.get(), "Battleaxe Head(Stage 4)");
        add(ItemInit.BATTLEAXE_HEAD_5.get(), "Battleaxe Head(Finished)");

        add(ItemInit.WOODEN_ZWEIHANDER_HEAD.get(), "Wooden Zweihander head");
        add(ItemInit.ZWEIHANDER_HEAD.get(), "Zweihander Head(Stage 1)");
        add(ItemInit.ZWEIHANDER_HEAD_2.get(), "Zweihander Head(Stage 2)");
        add(ItemInit.ZWEIHANDER_HEAD_3.get(), "Zweihander Head(Stage 3)");
        add(ItemInit.ZWEIHANDER_HEAD_4.get(), "Zweihander Head(Stage 4)");
        add(ItemInit.ZWEIHANDER_HEAD_5.get(), "Zweihander Head(Finished)");

        add(ItemInit.WOODEN_NODACHI_HEAD.get(), "Wooden Nodachi Head");
        add(ItemInit.NODACHI_HEAD.get(), "Nodachi Head(Stage 1)");
        add(ItemInit.NODACHI_HEAD_2.get(), "Nodachi Head(Stage 2)");
        add(ItemInit.NODACHI_HEAD_3.get(), "Nodachi Head(Stage 3)");
        add(ItemInit.NODACHI_HEAD_4.get(), "Nodachi Head(Finished)");

        add(ItemInit.WOODEN_SABRE_HEAD.get(), "Wooden Sabre Head");
        add(ItemInit.SABRE_HEAD.get(), "Sabre Head(Stage 1)");
        add(ItemInit.SABRE_HEAD_2.get(), "Sabre Head(Stage 2)");
        add(ItemInit.SABRE_HEAD_3.get(), "Sabre Head(Stage 3)");
        add(ItemInit.SABRE_HEAD_4.get(), "Sabre Head(Finished)");

        add(ItemInit.WOODEN_MAKHAIRA_HEAD.get(), "Wooden Makhaira Head");
        add(ItemInit.MAKHAIRA_HEAD.get(), "Makhaira Head(Stage 1)");
        add(ItemInit.MAKHAIRA_HEAD_2.get(), "Makhaira Head(Stage 2)");
        add(ItemInit.MAKHAIRA_HEAD_3.get(), "Makhaira Head(Finished)");

        add(ItemInit.WOODEN_SPEAR_HEAD.get(), "Wooden Spear Head");
        add(ItemInit.SPEAR_HEAD.get(), "Spear Head(Stage 1)");
        add(ItemInit.SPEAR_HEAD_2.get(), "Spear Head(Finished)");


        add("item.advancedlootableweapons.helmet_kobold", "Kobold Steel Helmet");
        add("item.advancedlootableweapons.helmet_copper", "Copper Helmet");
        add("item.advancedlootableweapons.helmet_silver", "Silver Helmet");
        add("item.advancedlootableweapons.helmet_bronze", "Bronze Helmet");
        add("item.advancedlootableweapons.helmet_platinum", "Platinum Helmet");
        add("item.advancedlootableweapons.helmet_steel", "Steel Helmet");
        add("item.advancedlootableweapons.helmet_shadow_platinum", "Shadow Platinum Helmet");
        add("item.advancedlootableweapons.helmet_frost_steel", "Frost Steel Helmet");
        add("item.advancedlootableweapons.helmet_obsidian", "Refined Obsidian Helmet");
        add("item.advancedlootableweapons.helmet_crystallite", "Crystallite Helmet");
        add("item.advancedlootableweapons.helmet_dusksteel", "Dusksteel Helmet");


        add("item.advancedlootableweapons.chestplate_kobold", "Kobold Steel Chestplate");
        add("item.advancedlootableweapons.chestplate_copper", "Copper Chestplate");
        add("item.advancedlootableweapons.chestplate_silver", "Silver Chestplate");
        add("item.advancedlootableweapons.chestplate_bronze", "Bronze Chestplate");
        add("item.advancedlootableweapons.chestplate_platinum", "Platinum Chestplate");
        add("item.advancedlootableweapons.chestplate_steel", "Steel Chestplate");
        add("item.advancedlootableweapons.chestplate_shadow_platinum", "Shadow Platinum Chestplate");
        add("item.advancedlootableweapons.chestplate_frost_steel", "Frost Steel Chestplate");
        add("item.advancedlootableweapons.chestplate_obsidian", "Refined Obsidian Chestplate");
        add("item.advancedlootableweapons.chestplate_crystallite", "Crystallite Chestplate");
        add("item.advancedlootableweapons.chestplate_dusksteel", "Dusksteel Chestplate");


        add("item.advancedlootableweapons.leggings_kobold", "Kobold Steel Leggings");
        add("item.advancedlootableweapons.leggings_copper", "Copper Leggings");
        add("item.advancedlootableweapons.leggings_silver", "Silver Leggings");
        add("item.advancedlootableweapons.leggings_bronze", "Bronze Leggings");
        add("item.advancedlootableweapons.leggings_platinum", "Platinum Leggings");
        add("item.advancedlootableweapons.leggings_steel", "Steel Leggings");
        add("item.advancedlootableweapons.leggings_shadow_platinum", "Shadow Platinum Leggings");
        add("item.advancedlootableweapons.leggings_frost_steel", "Frost Steel Leggings");
        add("item.advancedlootableweapons.leggings_obsidian", "Refined Obsidian Leggings");
        add("item.advancedlootableweapons.leggings_crystallite", "Crystallite Leggings");
        add("item.advancedlootableweapons.leggings_dusksteel", "Dusksteel Leggings");


        add("item.advancedlootableweapons.boots_kobold", "Kobold Steel Boots");
        add("item.advancedlootableweapons.boots_copper", "Copper Boots");
        add("item.advancedlootableweapons.boots_silver", "Silver Boots");
        add("item.advancedlootableweapons.boots_bronze", "Bronze Boots");
        add("item.advancedlootableweapons.boots_platinum", "Platinum Boots");
        add("item.advancedlootableweapons.boots_steel", "Steel Boots");
        add("item.advancedlootableweapons.boots_shadow_platinum", "Shadow Platinum Boots");
        add("item.advancedlootableweapons.boots_frost_steel", "Frost Steel Boots");
        add("item.advancedlootableweapons.boots_obsidian", "Refined Obsidian Boots");
        add("item.advancedlootableweapons.boots_crystallite", "Crystallite Boots");
        add("item.advancedlootableweapons.boots_dusksteel", "Dusksteel Boots");
    }

    protected void addTab(CreativeModeTab tab,String translation) {
        add(((TranslatableComponent)tab.getDisplayName()).getKey(),translation);
    }

    protected void addMenu(Supplier<? extends MenuType<?>> supplier,String key) {
        add(Utils.getMenuDescId(supplier.get()),key);
    }

    protected void addFluid(Supplier<? extends Fluid> supplier,String key) {
        add(Utils.getFluidDescId(supplier.get()),key);
    }

    protected void addDefaultMenu(Supplier<? extends MenuType<?>> supplier) {
        addMenu(supplier,getNameFromMenu(supplier.get()));
    }

    protected void addDefaultFluid(Supplier<? extends Fluid> supplier) {
        addFluid(supplier,getNameFromFluid(supplier.get()));
    }

    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier,getNameFromBlock(supplier.get()));
    }

    public static String getNameFromMenu(MenuType<?> menuType) {
        return StringUtils.capitaliseAllWords(Utils.getMenuDescId(menuType).split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromFluid(Fluid fluid) {
        return StringUtils.capitaliseAllWords(Utils.getFluidDescId(fluid).split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getBasicName(String key) {
        return StringUtils.capitaliseAllWords(key.replace("_", " "));
    }


    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

}
