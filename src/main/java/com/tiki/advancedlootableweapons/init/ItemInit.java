package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.items.SharpeningStoneItem;
import com.tiki.advancedlootableweapons.items.TanningKnifeItem;
import com.tiki.advancedlootableweapons.items.armor.ArmorPlateItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AdvancedLootableWeapons.MODID);
    public static final List<Item> hotToolHeads = new ArrayList<>();


    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_INGOT = ITEMS.register("kobold_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_INGOT = ITEMS.register("shadow_platinum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_INGOT = ITEMS.register("frost_steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_INGOT = ITEMS.register("refined_obsidian_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_INGOT = ITEMS.register("crystallite_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_INGOT = ITEMS.register("dusksteel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_NUGGET = ITEMS.register("kobold_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_NUGGET = ITEMS.register("shadow_platinum_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_NUGGET = ITEMS.register("frost_steel_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_NUGGET = ITEMS.register("refined_obsidian_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_NUGGET = ITEMS.register("crystallite_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_NUGGET = ITEMS.register("dusksteel_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> FELDSPAR_POWDER = ITEMS.register("feldspar_powder", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_POWDER = ITEMS.register("granite_powder", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_POWDER = ITEMS.register("diorite_powder", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_CLAY_BALL = ITEMS.register("granite_clay_ball", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_CLAY_BALL = ITEMS.register("diorite_clay_ball", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_BRICK = ITEMS.register("granite_brick", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_BRICK = ITEMS.register("diorite_brick", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW = ITEMS.register("shadow", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CONGEALED_SHADOW = ITEMS.register("congealed_shadow", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    //tanning
    public static final RegistryObject<Item> TANNING_KNIFE = ITEMS.register("tanning_knife",() -> new TanningKnifeItem(Tiers.IRON,new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> UNTRIMMED_HIDE = ITEMS.register("untrimmed_hide",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> TRIMMED_HIDE = ITEMS.register("trimmed_hide",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CURED_HIDE = ITEMS.register("cured_hide",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> LIMED_HIDE = ITEMS.register("limed_hide",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DELIMED_HIDE = ITEMS.register("delimed_hide",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    //forge hammers
    public static final RegistryObject<Item> STONE_FORGE_HAMMER = ITEMS.register("stone_forge_hammer", () -> new ForgeHammerItem(Tiers.STONE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> IRON_FORGE_HAMMER = ITEMS.register("iron_forge_hammer", () -> new ForgeHammerItem(Tiers.IRON, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_STEEL_FORGE_HAMMER = ITEMS.register("kobold_steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_KOBOLD, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> COPPER_FORGE_HAMMER = ITEMS.register("copper_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_COPPER, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_FORGE_HAMMER = ITEMS.register("silver_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_SILVER, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_FORGE_HAMMER = ITEMS.register("bronze_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_BRONZE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_FORGE_HAMMER = ITEMS.register("platinum_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_PLATINUM, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_FORGE_HAMMER = ITEMS.register("steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_STEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_FORGE_HAMMER = ITEMS.register("shadow_platinum_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_SHADOW_PLATINUM, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_FORGE_HAMMER = ITEMS.register("frost_steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_FROST_STEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_FORGE_HAMMER = ITEMS.register("refined_obsidian_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_REFINED_OBSIDIAN, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_FORGE_HAMMER = ITEMS.register("crystallite_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_CRYSTALLITE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_FORGE_HAMMER = ITEMS.register("dusksteel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.MAT_DUSKSTEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> DAGGER_HEAD = ITEMS.register("dagger_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KABUTOWARI_HEAD = ITEMS.register("kabutowari_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> RAPIER_HEAD = ITEMS.register("rapier_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> TALWAR_HEAD = ITEMS.register("talwar_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> MACE_HEAD = ITEMS.register("mace_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CLEAVER_HEAD = ITEMS.register("cleaver_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STAFF_HEAD = ITEMS.register("staff_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> LONGSWORD_HEAD = ITEMS.register("longsword_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BATTLEAXE_HEAD = ITEMS.register("battleaxe_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> ZWEIHANDER_HEAD = ITEMS.register("zweihander_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KODACHI_HEAD = ITEMS.register("kodachi_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> NODACHI_HEAD = ITEMS.register("nodachi_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SABRE_HEAD = ITEMS.register("sabre_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> MAKHAIRA_HEAD = ITEMS.register("makhaira_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SPEAR_HEAD = ITEMS.register("spear_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> LONG_WEAPON_HANDLE = ITEMS.register("long_weapon_handle", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));


    //armors

    public static final RegistryObject<Item> IRON_CHAIN_LINK = ITEMS.register("iron_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLD_CHAIN_LINK = ITEMS.register("gold_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> COPPER_CHAIN_LINK = ITEMS.register("copper_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SILVER_CHAIN_LINK = ITEMS.register("silver_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BRONZE_CHAIN_LINK = ITEMS.register("bronze_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> PLATINUM_CHAIN_LINK = ITEMS.register("platinum_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STEEL_CHAIN_LINK = ITEMS.register("steel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_CHAIN_LINK = ITEMS.register("obsidian_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KOBOLD_CHAIN_LINK = ITEMS.register("kobold_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_CHAIN_LINK = ITEMS.register("shadow_platinum_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_CHAIN_LINK = ITEMS.register("frost_steel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_CHAIN_LINK = ITEMS.register("crystallite_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_CHAIN_LINK = ITEMS.register("dusksteel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    public static final RegistryObject<Item> IRON_ARMOR_PLATE = ITEMS.register("iron_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLD_ARMOR_PLATE = ITEMS.register("gold_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_STUDDED_STEEL_ARMOR_PLATE = ITEMS.register("diamond_studded_steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> COPPER_ARMOR_PLATE = ITEMS.register("copper_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SILVER_ARMOR_PLATE = ITEMS.register("silver_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BRONZE_ARMOR_PLATE = ITEMS.register("bronze_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> PLATINUM_ARMOR_PLATE = ITEMS.register("platinum_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STEEL_ARMOR_PLATE = ITEMS.register("steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_ARMOR_PLATE = ITEMS.register("obsidian_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    public static final RegistryObject<Item> KOBOLD_ARMOR_PLATE = ITEMS.register("kobold_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_ARMOR_PLATE= ITEMS.register("shadow_platinum_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_ARMOR_PLATE = ITEMS.register("frost_steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_ARMOR_PLATE = ITEMS.register("crystallite_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_ARMOR_PLATE = ITEMS.register("dusksteel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    /*public static final RegistryObject<Item> CHAIN_BINDING_IRON = new ItemArmorBinding("binding_chain_iron", ArmorMaterial.IRON);
    public static final RegistryObject<Item> CHAIN_BINDING_GOLD = new ItemArmorBinding("binding_chain_gold", ArmorMaterial.GOLD);
    public static final RegistryObject<Item> CHAIN_BINDING_COPPER = new ItemArmorBinding("binding_chain_copper", AMAT_COPPER);
    public static final RegistryObject<Item> CHAIN_BINDING_SILVER = new ItemArmorBinding("binding_chain_silver", AMAT_SILVER);
    public static final RegistryObject<Item> CHAIN_BINDING_BRONZE = new ItemArmorBinding("binding_chain_bronze", AMAT_BRONZE);
    public static final RegistryObject<Item> CHAIN_BINDING_PLATINUM = new ItemArmorBinding("binding_chain_platinum", AMAT_PLATINUM);
    public static final RegistryObject<Item> CHAIN_BINDING_STEEL = new ItemArmorBinding("binding_chain_steel", AMAT_STEEL);
    public static final RegistryObject<Item> CHAIN_BINDING_OBSIDIAN = new ItemArmorBinding("binding_chain_obsidian", AMAT_OBSIDIAN);

    public static final RegistryObject<Item> CHAIN_BINDING_KOBOLD = new ItemArmorBinding("binding_chain_kobold", AMAT_KOBOLD);
    public static final RegistryObject<Item> CHAIN_BINDING_SHADOW_PLATINUM = new ItemArmorBinding("binding_chain_shadow_platinum", AMAT_SHADOW_PLATINUM);
    public static final RegistryObject<Item> CHAIN_BINDING_FROST_STEEL = new ItemArmorBinding("binding_chain_frost_steel", AMAT_FROST_STEEL);
    public static final RegistryObject<Item> CHAIN_BINDING_CRYSTALLITE = new ItemArmorBinding("binding_chain_crystallite", AMAT_CRYSTALLITE);
    public static final RegistryObject<Item> CHAIN_BINDING_DUSKSTEEL = new ItemArmorBinding("binding_chain_dusksteel", AMAT_DUSKSTEEL);

    public static final RegistryObject<Item> LEATHER_BINDING = new ItemArmorBinding("binding_leather", ArmorMaterial.LEATHER);*/

    //////////


    public static WeaponAttributes[] attributes;

    public static final List<RegistryObject<AlwWeaponItem>> WEAPONS = new ArrayList<>();

    static {


        attributes = Arrays.copyOf(WeaponAttributes.values(), WeaponAttributes.values().length - 2);

        for (WeaponAttributes attribute : attributes) {
                RegistryObject<AlwWeaponItem> weapon =
                        ITEMS.register( attribute.getType().toLowerCase(Locale.ROOT),
                                () -> new AlwWeaponItem(attribute, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
                WEAPONS.add(weapon);
            }
    }

    public static final RegistryObject<HotToolHeadItem> HOT_TOOL_HEAD = ITEMS.register("hot_tool_head", () -> new HotToolHeadItem(null, 0, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> HOT_TOOL_ROD_2 = ITEMS.register("hot_tool_rod_2", () -> new HotToolHeadItem(null, 2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> HOT_TOOL_ROD = ITEMS.register("hot_tool_rod", () -> new HotToolHeadItem(HOT_TOOL_ROD_2.get(), 1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> LONG_TOOL_ROD = ITEMS.register("long_tool_rod", () -> new HotToolHeadItem(null, 3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> DAGGER_HOT_TOOL_HEAD_2 = ITEMS.register("dagger_hot_tool_head_2", () -> new HotToolHeadItem(null, 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> DAGGER_HOT_TOOL_HEAD = ITEMS.register("dagger_hot_tool_head", () -> new HotToolHeadItem(DAGGER_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> KABUTOWARI_HOT_TOOL_HEAD_5 = ITEMS.register("kabutowari_hot_tool_head_5", () -> new HotToolHeadItem(null, 5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> KABUTOWARI_HOT_TOOL_HEAD_4 = ITEMS.register("kabutowari_hot_tool_head_4", () -> new HotToolHeadItem(KABUTOWARI_HOT_TOOL_HEAD_5.get(), 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> KABUTOWARI_HOT_TOOL_HEAD_3 = ITEMS.register("kabutowari_hot_tool_head_3", () -> new HotToolHeadItem(KABUTOWARI_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> KABUTOWARI_HOT_TOOL_HEAD_2 = ITEMS.register("kabutowari_hot_tool_head_2", () -> new HotToolHeadItem(KABUTOWARI_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> KABUTOWARI_HOT_TOOL_HEAD = ITEMS.register("kabutowari_hot_tool_head", () -> new HotToolHeadItem(KABUTOWARI_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> TALWAR_HOT_TOOL_HEAD_3 = ITEMS.register("talwar_hot_tool_head_3", () -> new HotToolHeadItem(null, 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> TALWAR_HOT_TOOL_HEAD_2 = ITEMS.register("talwar_hot_tool_head_2", () -> new HotToolHeadItem(TALWAR_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> TALWAR_HOT_TOOL_HEAD = ITEMS.register("talwar_hot_tool_head", () -> new HotToolHeadItem(TALWAR_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> RAPIER_HOT_TOOL_HEAD_4 = ITEMS.register("rapier_hot_tool_head_4", () -> new HotToolHeadItem(null, 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> RAPIER_HOT_TOOL_HEAD_3 = ITEMS.register("rapier_hot_tool_head_3", () -> new HotToolHeadItem(RAPIER_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> RAPIER_HOT_TOOL_HEAD_2 = ITEMS.register("rapier_hot_tool_head_2", () -> new HotToolHeadItem(RAPIER_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> RAPIER_HOT_TOOL_HEAD = ITEMS.register("rapier_hot_tool_head", () -> new HotToolHeadItem(RAPIER_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> CLEAVER_HOT_TOOL_HEAD = ITEMS.register("cleaver_hot_tool_head", () -> new HotToolHeadItem(null, 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> MACE_HOT_TOOL_HEAD_3 = ITEMS.register("mace_hot_tool_head_3", () -> new HotToolHeadItem(null, 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> MACE_HOT_TOOL_HEAD_2 = ITEMS.register("mace_hot_tool_head_2", () -> new HotToolHeadItem(MACE_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> MACE_HOT_TOOL_HEAD = ITEMS.register("mace_hot_tool_head", () -> new HotToolHeadItem(MACE_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> STAFF_HOT_TOOL_HEAD_5 = ITEMS.register("staff_hot_tool_head_5", () -> new HotToolHeadItem(null, 5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> STAFF_HOT_TOOL_HEAD_4 = ITEMS.register("staff_hot_tool_head_4", () -> new HotToolHeadItem(STAFF_HOT_TOOL_HEAD_5.get(), 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> STAFF_HOT_TOOL_HEAD_3 = ITEMS.register("staff_hot_tool_head_3", () -> new HotToolHeadItem(STAFF_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> STAFF_HOT_TOOL_HEAD_2 = ITEMS.register("staff_hot_tool_head_2", () -> new HotToolHeadItem(STAFF_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> STAFF_HOT_TOOL_HEAD = ITEMS.register("staff_hot_tool_head", () -> new HotToolHeadItem(STAFF_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> LONGSWORD_HOT_TOOL_HEAD_4 = ITEMS.register("longsword_hot_tool_head_4", () -> new HotToolHeadItem(null, 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> LONGSWORD_HOT_TOOL_HEAD_3 = ITEMS.register("longsword_hot_tool_head_3", () -> new HotToolHeadItem(LONGSWORD_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> LONGSWORD_HOT_TOOL_HEAD_2 = ITEMS.register("longsword_hot_tool_head_2", () -> new HotToolHeadItem(LONGSWORD_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> LONGSWORD_HOT_TOOL_HEAD = ITEMS.register("longsword_hot_tool_head", () -> new HotToolHeadItem(LONGSWORD_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> BATTLEAXE_HOT_TOOL_HEAD_5 = ITEMS.register("battleaxe_hot_tool_head_5", () -> new HotToolHeadItem(null, 5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> BATTLEAXE_HOT_TOOL_HEAD_4 = ITEMS.register("battleaxe_hot_tool_head_4", () -> new HotToolHeadItem(BATTLEAXE_HOT_TOOL_HEAD_5.get(), 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> BATTLEAXE_HOT_TOOL_HEAD_3 = ITEMS.register("battleaxe_hot_tool_head_3", () -> new HotToolHeadItem(BATTLEAXE_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> BATTLEAXE_HOT_TOOL_HEAD_2 = ITEMS.register("battleaxe_hot_tool_head_2", () -> new HotToolHeadItem(BATTLEAXE_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> BATTLEAXE_HOT_TOOL_HEAD = ITEMS.register("battleaxe_hot_tool_head", () -> new HotToolHeadItem(BATTLEAXE_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> ZWEIHANDER_HOT_TOOL_HEAD_5 = ITEMS.register("zweihander_hot_tool_head_5", () -> new HotToolHeadItem(null, 5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> ZWEIHANDER_HOT_TOOL_HEAD_4 = ITEMS.register("zweihander_hot_tool_head_4", () -> new HotToolHeadItem(ZWEIHANDER_HOT_TOOL_HEAD_5.get(), 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> ZWEIHANDER_HOT_TOOL_HEAD_3 = ITEMS.register("zweihander_hot_tool_head_3", () -> new HotToolHeadItem(ZWEIHANDER_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> ZWEIHANDER_HOT_TOOL_HEAD_2 = ITEMS.register("zweihander_hot_tool_head_2", () -> new HotToolHeadItem(ZWEIHANDER_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> ZWEIHANDER_HOT_TOOL_HEAD = ITEMS.register("zweihander_hot_tool_head", () -> new HotToolHeadItem(ZWEIHANDER_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> KODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("kodachi_hot_tool_head_2", () -> new HotToolHeadItem(null, 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> KODACHI_HOT_TOOL_HEAD = ITEMS.register("kodachi_hot_tool_head", () -> new HotToolHeadItem(KODACHI_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> NODACHI_HOT_TOOL_HEAD_4 = ITEMS.register("nodachi_hot_tool_head_4", () -> new HotToolHeadItem(null, 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> NODACHI_HOT_TOOL_HEAD_3 = ITEMS.register("nodachi_hot_tool_head_3", () -> new HotToolHeadItem(NODACHI_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> NODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("nodachi_hot_tool_head_2", () -> new HotToolHeadItem(NODACHI_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> NODACHI_HOT_TOOL_HEAD = ITEMS.register("nodachi_hot_tool_head", () -> new HotToolHeadItem(NODACHI_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> SABRE_HOT_TOOL_HEAD_4 = ITEMS.register("sabre_hot_tool_head_4", () -> new HotToolHeadItem(null, 4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> SABRE_HOT_TOOL_HEAD_3 = ITEMS.register("sabre_hot_tool_head_3", () -> new HotToolHeadItem(SABRE_HOT_TOOL_HEAD_4.get(), 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> SABRE_HOT_TOOL_HEAD_2 = ITEMS.register("sabre_hot_tool_head_2", () -> new HotToolHeadItem(SABRE_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> SABRE_HOT_TOOL_HEAD = ITEMS.register("sabre_hot_tool_head", () -> new HotToolHeadItem(SABRE_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> MAKHAIRA_HOT_TOOL_HEAD_3 = ITEMS.register("makhaira_hot_tool_head_3", () -> new HotToolHeadItem(null, 3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> MAKHAIRA_HOT_TOOL_HEAD_2 = ITEMS.register("makhaira_hot_tool_head_2", () -> new HotToolHeadItem(MAKHAIRA_HOT_TOOL_HEAD_3.get(), 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> MAKHAIRA_HOT_TOOL_HEAD = ITEMS.register("makhaira_hot_tool_head", () -> new HotToolHeadItem(MAKHAIRA_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<HotToolHeadItem> SPEAR_HOT_TOOL_HEAD_2 = ITEMS.register("spear_hot_tool_head_2", () -> new HotToolHeadItem(null, 2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<HotToolHeadItem> SPEAR_HOT_TOOL_HEAD = ITEMS.register("spear_hot_tool_head", () -> new HotToolHeadItem(SPEAR_HOT_TOOL_HEAD_2.get(), 1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    //whetstones
    public static final RegistryObject<Item> STONE_WHETSTONE = ITEMS.register("stone_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), Tiers.WOOD));
    public static final RegistryObject<Item> IRON_WHETSTONE = ITEMS.register("iron_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), Tiers.IRON));
    public static final RegistryObject<Item> KOBOLD_WHETSTONE = ITEMS.register("kobold_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_KOBOLD));
    public static final RegistryObject<Item> COPPER_WHETSTONE = ITEMS.register("copper_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_COPPER));
    public static final RegistryObject<Item> SILVER_WHETSTONE = ITEMS.register("silver_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_SILVER));
    public static final RegistryObject<Item> BRONZE_WHETSTONE = ITEMS.register("bronze_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_BRONZE));
    public static final RegistryObject<Item> PLATINUM_WHETSTONE = ITEMS.register("platinum_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_PLATINUM));
    public static final RegistryObject<Item> STEEL_WHETSTONE = ITEMS.register("steel_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_STEEL));
    public static final RegistryObject<Item> SHADOW_PLATINUM_WHETSTONE = ITEMS.register("shadow_platinum_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_SHADOW_PLATINUM));
    public static final RegistryObject<Item> FROST_STEEL_WHETSTONE = ITEMS.register("frost_steel_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_FROST_STEEL));
    public static final RegistryObject<Item> OBSIDIAN_WHETSTONE = ITEMS.register("obsidian_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_REFINED_OBSIDIAN));
    public static final RegistryObject<Item> CRYSTALLITE_WHETSTONE = ITEMS.register("crystallite_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_CRYSTALLITE));
    public static final RegistryObject<Item> DUSKSTEEL_WHETSTONE = ITEMS.register("dusksteel_whetstone", () -> new SharpeningStoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.MAT_DUSKSTEEL));

    public static final RegistryObject<BucketItem> MILK_OF_LIME_BUCKET = ITEMS.register("milk_of_lime_bucket", () -> new BucketItem(FluidInit.MILK_OF_LIME,new Item.Properties().tab(ModCreativeTabs.BLOCK_TAB)));
    public static final RegistryObject<BucketItem> MAGNESIUM_LACTATE_BUCKET = ITEMS.register("magnesium_lactate_bucket", () -> new BucketItem(FluidInit.MAGNESIUM_LACTATE,new Item.Properties().tab(ModCreativeTabs.BLOCK_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
