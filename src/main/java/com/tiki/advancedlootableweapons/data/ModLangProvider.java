package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.ModCreativeTabs;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.items.armor.ArmorBindingItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

        addDefaultItem(ItemInit.IRON_CHAIN_LINK);
        addDefaultItem(ItemInit.GOLD_CHAIN_LINK);
        addDefaultItem(ItemInit.COPPER_CHAIN_LINK);
        addDefaultItem(ItemInit.SILVER_CHAIN_LINK);
        addDefaultItem(ItemInit.BRONZE_CHAIN_LINK);
        addDefaultItem(ItemInit.PLATINUM_CHAIN_LINK);
        addDefaultItem(ItemInit.STEEL_CHAIN_LINK);
        addDefaultItem(ItemInit.OBSIDIAN_CHAIN_LINK);
        addDefaultItem(ItemInit.KOBOLD_CHAIN_LINK);
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
        addDefaultItem(ItemInit.OBSIDIAN_ARMOR_PLATE);
        addDefaultItem(ItemInit.KOBOLD_ARMOR_PLATE);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_ARMOR_PLATE);
        addDefaultItem(ItemInit.FROST_STEEL_ARMOR_PLATE);
        addDefaultItem(ItemInit.CRYSTALLITE_ARMOR_PLATE);
        addDefaultItem(ItemInit.DUSKSTEEL_ARMOR_PLATE);
        

        for (RegistryObject<AlwWeapon> weapon : ItemInit.WEAPONS) {
            addItem(weapon,getNameFromItem(weapon.get()));
        }

        add(ForgeHammerItem.INFO,"Hit an anvil with me to start forging weapons!");
        add(ArmorBindingItem.INFO,"Bonus Durability");
        add("container.advancedlootableweapons.anvil_forging","Anvil Forging");

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


        addDefaultItem(ItemInit.BRONZE_WHETSTONE);
        addDefaultItem(ItemInit.COPPER_WHETSTONE);
        addDefaultItem(ItemInit.CRYSTALLITE_WHETSTONE);
        addDefaultItem(ItemInit.DUSKSTEEL_WHETSTONE);
        addDefaultItem(ItemInit.FROST_STEEL_WHETSTONE);
        addDefaultItem(ItemInit.KOBOLD_WHETSTONE);
        addDefaultItem(ItemInit.PLATINUM_WHETSTONE);
        addDefaultItem(ItemInit.OBSIDIAN_WHETSTONE);
        addDefaultItem(ItemInit.SHADOW_PLATINUM_WHETSTONE);
        addDefaultItem(ItemInit.SILVER_WHETSTONE);
        addDefaultItem(ItemInit.STEEL_WHETSTONE);


        add(HotToolHeadItem.QUENCH_KEY,"Quenched");
        add(HotToolHeadItem.UNQUENCH_KEY,"Unquenched");

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
        add("item.advancedlootableweapons.kabutowari_hot_tool_head_5", "Kabutowari Head(Finished)");

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

        add("category.advancedlootableweapons.drum_quenching.requires_clay","Requires Clay");

        addDefaultBlock(BlockInit.BRONZE_BLOCK);
        addDefaultBlock(BlockInit.CRYSTALLITE_BLOCK);
        addDefaultBlock(BlockInit.DUSKSTEEL_BLOCK);
        addDefaultBlock(BlockInit.FROST_STEEL_BLOCK);
        addDefaultBlock(BlockInit.KOBOLD_BLOCK);
        addDefaultBlock(BlockInit.PLATINUM_BLOCK);
        addDefaultBlock(BlockInit.REFINED_OBSIDIAN_BLOCK);
        addDefaultBlock(BlockInit.SHADOW_PLATINUM_BLOCK);
        addDefaultBlock(BlockInit.SILVER_BLOCK);
        addDefaultBlock(BlockInit.STEEL_BLOCK);
        addDefaultBlock(BlockInit.TIN_BLOCK);

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

        add(HotToolHeadItem.Temp.cool.translation.getKey(),"Cool");
        add(HotToolHeadItem.Temp.warm.translation.getKey(),"Warm");
        add(HotToolHeadItem.Temp.hot.translation.getKey(),"Hot");
    }

    protected void addTab(CreativeModeTab tab,String translation) {
        add(((TranslatableComponent)tab.getDisplayName()).getKey(),translation);
    }

    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier,getNameFromBlock(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

}
