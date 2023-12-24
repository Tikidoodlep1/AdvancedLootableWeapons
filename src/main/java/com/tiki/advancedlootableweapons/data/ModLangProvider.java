package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.ModCreativeTabs;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen) {
        super(gen, AdvancedLootableWeapons.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        addDefaultItem(ItemInit.BRONZE_INGOT);
        addDefaultItem(ItemInit.CRYSTALLITE_INGOT);
        addDefaultItem(ItemInit.DUSKSTEEL_INGOT);
        addDefaultItem(ItemInit.FROST_STEEL_INGOT);
        addDefaultItem(ItemInit.KOBOLD_INGOT);
        addDefaultItem(ItemInit.PLATINUM_INGOT);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_INGOT);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_INGOT);
        addDefaultItem(ItemInit.SILVER_INGOT);
        addDefaultItem(ItemInit.STEEL_INGOT);
        addDefaultItem(ItemInit.TIN_INGOT);


        addDefaultItem(ItemInit.BRONZE_NUGGET);
        addDefaultItem(ItemInit.CRYSTALLITE_NUGGET);
        addDefaultItem(ItemInit.DUSKSTEEL_NUGGET);
        addDefaultItem(ItemInit.FROST_STEEL_NUGGET);
        addDefaultItem(ItemInit.KOBOLD_NUGGET);
        addDefaultItem(ItemInit.PLATINUM_NUGGET);
        addDefaultItem(ItemInit.REFINED_OBSIDIAN_NUGGET);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_NUGGET);
        addDefaultItem(ItemInit.SILVER_NUGGET);
        addDefaultItem(ItemInit.STEEL_NUGGET);
        addDefaultItem(ItemInit.TIN_NUGGET);

        addDefaultItem(ItemInit.CHAIN_LINK_IRON);
        addDefaultItem(ItemInit.CHAIN_LINK_GOLD);
        addDefaultItem(ItemInit.CHAIN_LINK_COPPER);
        addDefaultItem(ItemInit.CHAIN_LINK_SILVER);
        addDefaultItem(ItemInit.CHAIN_LINK_BRONZE);
        addDefaultItem(ItemInit.CHAIN_LINK_PLATINUM);
        addDefaultItem(ItemInit.CHAIN_LINK_STEEL);
        addDefaultItem(ItemInit.CHAIN_LINK_OBSIDIAN);
        addDefaultItem(ItemInit.CHAIN_LINK_KOBOLD);
        addDefaultItem(ItemInit.CHAIN_LINK_SHADOW_PLATINUM);
        addDefaultItem(ItemInit.CHAIN_LINK_FROST_STEEL);
        addDefaultItem(ItemInit.CHAIN_LINK_CRYSTALLITE);
        addDefaultItem(ItemInit.CHAIN_LINK_DUSKSTEEL);
        

        for (RegistryObject<AlwWeapon> weapon : ItemInit.WEAPONS) {
            addItem(weapon,getNameFromItem(weapon.get()));
        }

        add("item.advancedlootableweapons.stone_forge_hammer", "Stone Forge Hammer");
        add("item.advancedlootableweapons.iron_forge_hammer", "Iron Forge Hammer");
        add("item.advancedlootableweapons.kobold_forge_hammer", "Kobold Steel Forge Hammer");
        add("item.advancedlootableweapons.copper_forge_hammer", "Copper Forge Hammer");
        add("item.advancedlootableweapons.silver_forge_hammer", "Silver Forge Hammer");
        add("item.advancedlootableweapons.bronze_forge_hammer", "Bronze Forge Hammer");
        add("item.advancedlootableweapons.platinum_forge_hammer", "Platinum Forge Hammer");
        add("item.advancedlootableweapons.steel_forge_hammer", "Steel Forge Hammer");
        add("item.advancedlootableweapons.shadow_platinum_forge_hammer", "Shadow Platinum Forge Hammer");
        add("item.advancedlootableweapons.frost_steel_forge_hammer", "Frost Steel Forge Hammer");
        add("item.advancedlootableweapons.obsidian_forge_hammer", "Refined Obsidian Forge Hammer");
        add("item.advancedlootableweapons.crystallite_forge_hammer", "Crystallite Forge Hammer");
        add("item.advancedlootableweapons.dusksteel_forge_hammer", "Dusksteel Forge Hammer");


        add("item.advancedlootableweapons.stone_sharpening_stone", "Stone Whetstone");
        add("item.advancedlootableweapons.iron_sharpening_stone", "Iron Whetstone");
        add("item.advancedlootableweapons.kobold_sharpening_stone", "Kobold Steel Whetstone");
        add("item.advancedlootableweapons.copper_sharpening_stone", "Copper Whetstone");
        add("item.advancedlootableweapons.silver_sharpening_stone", "Silver Whetstone");
        add("item.advancedlootableweapons.bronze_sharpening_stone", "Bronze Whetstone");
        add("item.advancedlootableweapons.platinum_sharpening_stone", "Platinum Whetstone");
        add("item.advancedlootableweapons.steel_sharpening_stone", "Steel Whetstone");
        add("item.advancedlootableweapons.shadow_platinum_sharpening_stone", "Shadow Platinum Whetstone");
        add("item.advancedlootableweapons.frost_steel_sharpening_stone", "Frost Steel Whetstone");
        add("item.advancedlootableweapons.obsidian_sharpening_stone", "Refined Obsidian Whetstone");
        add("item.advancedlootableweapons.crystallite_sharpening_stone", "Crystallite Whetstone");
        add("item.advancedlootableweapons.dusksteel_sharpening_stone", "Dusksteel Whetstone");


        add("item.advancedlootableweapons.hot_tool_head", "Tool Head");

        add("item.advancedlootableweapons.hot_tool_rod", "Weapon Handle(Stage 1)");
        add("item.advancedlootableweapons.hot_tool_rod_2", "Weapon Handle(Finished)");
        add("item.advancedlootableweapons.long_tool_rod", "Long Weapon Handle");
        add("item.advancedlootableweapons.long_weapon_handle", "Wooden Long Weapon Handle");

        add("item.advancedlootableweapons.dagger_head", "Wooden Dagger Head");
        add("item.advancedlootableweapons.dagger_hot_tool_head", "Dagger Head(Stage 1)");
        add("item.advancedlootableweapons.dagger_hot_tool_head_2", "Dagger Head(Finished)");

        add("item.advancedlootableweapons.kabutowari_head", "Wooden Kabutowari Head");
        add("item.advancedlootableweapons.kabutowari_hot_tool_head", "Kabutowari Head(Stage 1)");
        add("item.advancedlootableweapons.kabutowari_hot_tool_head_2", "Kabutowari Head(Stage 2)");
        add("item.advancedlootableweapons.kabutowari_hot_tool_head_3", "Kabutowari Head(Stage 3)");
        add("item.advancedlootableweapons.kabutowari_hot_tool_head_4", "Kabutowari Head(Stage 4)");
        add("item.advancedlootableweapons.kabutowari_hot_tool_head_5", "Hot Kabutowari Head(Finished)");

        add("item.advancedlootableweapons.talwar_head", "Wooden Talwar Head");
        add("item.advancedlootableweapons.talwar_hot_tool_head", "Talwar Head(Stage 1)");
        add("item.advancedlootableweapons.talwar_hot_tool_head_2", "Talwar Head(Stage 2)");
        add("item.advancedlootableweapons.talwar_hot_tool_head_3", "Talwar Head(Finished)");

        add("item.advancedlootableweapons.rapier_head", "Wooden Rapier Head");
        add("item.advancedlootableweapons.rapier_hot_tool_head", "Rapier Head(Stage 1)");
        add("item.advancedlootableweapons.rapier_hot_tool_head_2", "Rapier Head(Stage 2)");
        add("item.advancedlootableweapons.rapier_hot_tool_head_3", "Rapier Head(Stage 3)");
        add("item.advancedlootableweapons.rapier_hot_tool_head_4", "Rapier Head(Finished)");

        add("item.advancedlootableweapons.cleaver_head", "Wooden Cleaver Head");
        add("item.advancedlootableweapons.cleaver_hot_tool_head", "Cleaver Head(Finished)");

        add("item.advancedlootableweapons.mace_head", "Wooden Mace Head");
        add("item.advancedlootableweapons.mace_hot_tool_head", "Mace Head(Stage 1)");
        add("item.advancedlootableweapons.mace_hot_tool_head_2", "Mace Head(Stage 2)");
        add("item.advancedlootableweapons.mace_hot_tool_head_3", "Mace Head(Finished)");

        add("item.advancedlootableweapons.staff_head", "Wooden Staff Head");
        add("item.advancedlootableweapons.staff_hot_tool_head", "Staff Head(Stage 1)");
        add("item.advancedlootableweapons.staff_hot_tool_head_2", "Staff Head(Stage 2)");
        add("item.advancedlootableweapons.staff_hot_tool_head_3", "Staff Head(Stage 3)");
        add("item.advancedlootableweapons.staff_hot_tool_head_4", "Staff Head(Stage 4)");
        add("item.advancedlootableweapons.staff_hot_tool_head_5", "Staff Head(Finished)");

        add("item.advancedlootableweapons.longsword_head", "Wooden Longsword Head");
        add("item.advancedlootableweapons.longsword_hot_tool_head", "Longsword Head(Stage 1)");
        add("item.advancedlootableweapons.longsword_hot_tool_head_2", "Longsword Head(Stage 2)");
        add("item.advancedlootableweapons.longsword_hot_tool_head_3", "Longsword Head(Stage 3)");
        add("item.advancedlootableweapons.longsword_hot_tool_head_4", "Longsword Head(Finished)");

        add("item.advancedlootableweapons.kodachi_head", "Wooden Kodachi Head");
        add("item.advancedlootableweapons.kodachi_hot_tool_head", "Kodachi Head(Stage 1)");
        add("item.advancedlootableweapons.kodachi_hot_tool_head_2", "Kodachi Head(Finished)");

        add("item.advancedlootableweapons.battleaxe_head", "Wooden Battleaxe Head");
        add("item.advancedlootableweapons.battleaxe_hot_tool_head", "Battleaxe Head(Stage 1)");
        add("item.advancedlootableweapons.battleaxe_hot_tool_head_2", "Battleaxe Head(Stage 2)");
        add("item.advancedlootableweapons.battleaxe_hot_tool_head_3", "Battleaxe Head(Stage 3)");
        add("item.advancedlootableweapons.battleaxe_hot_tool_head_4", "Battleaxe Head(Stage 4)");
        add("item.advancedlootableweapons.battleaxe_hot_tool_head_5", "Battleaxe Head(Finished)");

        add("item.advancedlootableweapons.zweihander_head", "Wooden Zweihander head");
        add("item.advancedlootableweapons.zweihander_hot_tool_head", "Zweihander Head(Stage 1)");
        add("item.advancedlootableweapons.zweihander_hot_tool_head_2", "Zweihander Head(Stage 2)");
        add("item.advancedlootableweapons.zweihander_hot_tool_head_3", "Zweihander Head(Stage 3)");
        add("item.advancedlootableweapons.zweihander_hot_tool_head_4", "Zweihander Head(Stage 4)");
        add("item.advancedlootableweapons.zweihander_hot_tool_head_5", "Zweihander Head(Finished)");

        add("item.advancedlootableweapons.nodachi_head", "Wooden Nodachi Head");
        add("item.advancedlootableweapons.nodachi_hot_tool_head", "Nodachi Head(Stage 1)");
        add("item.advancedlootableweapons.nodachi_hot_tool_head_2", "Nodachi Head(Stage 2)");
        add("item.advancedlootableweapons.nodachi_hot_tool_head_3", "Nodachi Head(Stage 3)");
        add("item.advancedlootableweapons.nodachi_hot_tool_head_4", "Nodachi Head(Finished)");

        add("item.advancedlootableweapons.sabre_head", "Wooden Sabre Head");
        add("item.advancedlootableweapons.sabre_hot_tool_head", "Sabre Head(Stage 1)");
        add("item.advancedlootableweapons.sabre_hot_tool_head_2", "Sabre Head(Stage 2)");
        add("item.advancedlootableweapons.sabre_hot_tool_head_3", "Sabre Head(Stage 3)");
        add("item.advancedlootableweapons.sabre_hot_tool_head_4", "Sabre Head(Finished)");

        add("item.advancedlootableweapons.makhaira_head", "Wooden Makhaira Head");
        add("item.advancedlootableweapons.makhaira_hot_tool_head", "Makhaira Head(Stage 1)");
        add("item.advancedlootableweapons.makhaira_hot_tool_head_2", "Makhaira Head(Stage 2)");
        add("item.advancedlootableweapons.makhaira_hot_tool_head_3", "Makhaira Head(Finished)");

        add("item.advancedlootableweapons.spear_head", "Wooden Spear Head");
        add("item.advancedlootableweapons.spear_hot_tool_head", "Spear Head(Stage 1)");
        add("item.advancedlootableweapons.spear_hot_tool_head_2", "Spear Head(Finished)");


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


        add("block.advancedlootableweapons.block_tin", "Tin Block");
        add("block.advancedlootableweapons.block_kobold", "Kobold Steel Block");
        add("block.advancedlootableweapons.block_silver", "Silver Block");
        add("block.advancedlootableweapons.block_bronze", "Bronze Block");
        add("block.advancedlootableweapons.block_platinum", "Platinum Block");
        add("block.advancedlootableweapons.block_steel", "Steel Block");
        add("block.advancedlootableweapons.block_shadow_platinum", "Shadow Platinum Block");
        add("block.advancedlootableweapons.block_frost_steel", "Frost Steel Block");
        add("block.advancedlootableweapons.block_obsidian_steel", "Refined Obsidian Block");
        add("block.advancedlootableweapons.block_crystallite", "Crystallite Block");
        add("block.advancedlootableweapons.block_dusksteel", "Dusksteel Block");
        add("block.advancedlootableweapons.ore_silver", "Silver Ore");
        add("block.advancedlootableweapons.ore_tin", "Tin Ore");
        add("block.advancedlootableweapons.ore_platinum", "Platinum Ore");
        add("block.advancedlootableweapons.ore_crystallite", "Crystallite Ore");
        add("block.advancedlootableweapons.cobble_feldspar", "Cobbled Feldspar");
        add("block.advancedlootableweapons.rock_feldspar", "Feldspar");
        add("block.advancedlootableweapons.block_brick_diorite", "Diorite Bricks");
        add("block.advancedlootableweapons.block_brick_granite", "Granite Bricks");
        add("block.advancedlootableweapons.block_powder_granite", "Granite Clay Powder");
        add("block.advancedlootableweapons.block_powder_diorite", "Diorite Clay Powder");
        add("block.advancedlootableweapons.block_clay_diorite", "Diorite Clay");
        add("block.advancedlootableweapons.block_clay_granite", "Granite Clay");
        addBlock(BlockInit.ALLOY_FURNACE, "Alloy Furnace");
        addBlock(BlockInit.FORGE, "Basic Forge");
        add("block.advancedlootableweapons.advanced_forge", "Advanced Forge");
        add("block.advancedlootableweapons.advanced_forge_1", "Advanced Forge");
        add("block.advancedlootableweapons.block_jaw_crusher", "Jaw Crusher");


        add("item.advancedlootableweapons.shadow", "Shadow");
        add("item.advancedlootableweapons.shadow_blob", "Congealed Shadow");
        add("item.advancedlootableweapons.crystal", "Crystal");
        add("item.advancedlootableweapons.shard_obsidian", "Obsidian Shard");

        addDefaultItem(ItemInit.FELDSPAR_POWDER);
        addDefaultItem(ItemInit.DIORITE_BRICK);
        addDefaultItem(ItemInit.DIORITE_CLAY_BALL);
        addDefaultItem(ItemInit.DIORITE_POWDER);
        addDefaultItem(ItemInit.GRANITE_BRICK);
        addDefaultItem(ItemInit.GRANITE_CLAY_BALL);
        addDefaultItem(ItemInit.GRANITE_POWDER);


        add("container.advancedlootableweapons.sharpeningStone", "Sharpen Weapon");
        add("container.advancedlootableweapons.alloy_furnace", "Alloy Furnace");
        add("container.advancedlootableweapons.forge", "Forge");
        add("container.advancedlootableweapons.forge2", "Advanced Forge");
        add("container.advancedlootableweapons.forgeWeapon", "Anvil Forging");
        add("container.advancedlootableweapons.jaw_crusher", "Jaw Crusher");


        add("enchantment.advancedlootableweapons.refined", "Refined");


        add("attribute.name.generic.bonusAttackDamage", "Bonus Attack Damage");


        addTab(ModCreativeTabs.MISC_TAB, "Advanced Lootable Weapons");
        addTab(ModCreativeTabs.BLOCK_TAB, "ALW Blocks");
        addTab(ModCreativeTabs.WEAPON_TAB, "ALW Weapons & Hot Tool Heads");

        add(ItemHotToolHead.Temp.cool.translation.getKey(),"Cool");
        add(ItemHotToolHead.Temp.warm.translation.getKey(),"Warm");
        add(ItemHotToolHead.Temp.hot.translation.getKey(),"Hot");
    }

    protected void addTab(CreativeModeTab tab,String translation) {
        add(((TranslatableComponent)tab.getDisplayName()).getKey(),translation);
    }

    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

}
