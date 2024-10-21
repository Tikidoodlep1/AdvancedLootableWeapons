package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.WhetstoneItem;
import com.tiki.advancedlootableweapons.items.TanningKnifeItem;
import com.tiki.advancedlootableweapons.items.armor.*;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.tiki.advancedlootableweapons.init.ModMaterials.BRONZE;
import static com.tiki.advancedlootableweapons.init.ModMaterials.SILVER;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AdvancedLootableWeapons.MODID);
    public static final List<Item> hotToolHeads = new ArrayList<>();


    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_STEEL_INGOT = ITEMS.register("kobold_steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
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
    public static final RegistryObject<Item> KOBOLD_STEEL_NUGGET = ITEMS.register("kobold_steel_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
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
    public static final RegistryObject<Item> CHARCOAL_POWDER = ITEMS.register("charcoal_powder",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CLAY_QUENCHING_MIX = ITEMS.register("clay_quenching_mix",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CLAY_QUENCHING_SLIP = ITEMS.register("clay_quenching_slip",() -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    //tanning
    public static final RegistryObject<Item> TANNING_KNIFE = ITEMS.register("tanning_knife", () -> new TanningKnifeItem(Tiers.IRON, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> UNTRIMMED_HIDE = ITEMS.register("untrimmed_hide", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> TRIMMED_HIDE = ITEMS.register("trimmed_hide", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CURED_HIDE = ITEMS.register("cured_hide", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> LIMED_HIDE = ITEMS.register("limed_hide", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DELIMED_HIDE = ITEMS.register("delimed_hide", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    //forge hammers
    public static final RegistryObject<Item> STONE_FORGE_HAMMER = ITEMS.register("stone_forge_hammer", () -> new ForgeHammerItem(Tiers.STONE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> IRON_FORGE_HAMMER = ITEMS.register("iron_forge_hammer", () -> new ForgeHammerItem(Tiers.IRON, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_STEEL_FORGE_HAMMER = ITEMS.register("kobold_steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.KOBOLD_STEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> COPPER_FORGE_HAMMER = ITEMS.register("copper_forge_hammer", () -> new ForgeHammerItem(ModMaterials.COPPER, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_FORGE_HAMMER = ITEMS.register("silver_forge_hammer", () -> new ForgeHammerItem(SILVER, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_FORGE_HAMMER = ITEMS.register("bronze_forge_hammer", () -> new ForgeHammerItem(BRONZE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_FORGE_HAMMER = ITEMS.register("platinum_forge_hammer", () -> new ForgeHammerItem(ModMaterials.PLATINUM, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_FORGE_HAMMER = ITEMS.register("steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.STEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_FORGE_HAMMER = ITEMS.register("shadow_platinum_forge_hammer", () -> new ForgeHammerItem(ModMaterials.SHADOW_PLATINUM, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_FORGE_HAMMER = ITEMS.register("frost_steel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.FROST_STEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_FORGE_HAMMER = ITEMS.register("refined_obsidian_forge_hammer", () -> new ForgeHammerItem(ModMaterials.REFINED_OBSIDIAN, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_FORGE_HAMMER = ITEMS.register("crystallite_forge_hammer", () -> new ForgeHammerItem(ModMaterials.CRYSTALLITE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_FORGE_HAMMER = ITEMS.register("dusksteel_forge_hammer", () -> new ForgeHammerItem(ModMaterials.DUSKSTEEL, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> WOODEN_DAGGER_HEAD = ITEMS.register("wooden_dagger_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_KABUTOWARI_HEAD = ITEMS.register("wooden_kabutowari_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_RAPIER_HEAD = ITEMS.register("wooden_rapier_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_TALWAR_HEAD = ITEMS.register("wooden_talwar_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_MACE_HEAD = ITEMS.register("wooden_mace_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_CLEAVER_HEAD = ITEMS.register("wooden_cleaver_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_STAFF_HEAD = ITEMS.register("wooden_staff_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_LONGSWORD_HEAD = ITEMS.register("wooden_longsword_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_BATTLEAXE_HEAD = ITEMS.register("wooden_battleaxe_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_ZWEIHANDER_HEAD = ITEMS.register("wooden_zweihander_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_KODACHI_HEAD = ITEMS.register("wooden_kodachi_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_NODACHI_HEAD = ITEMS.register("wooden_nodachi_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_SABRE_HEAD = ITEMS.register("wooden_sabre_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_MAKHAIRA_HEAD = ITEMS.register("wooden_makhaira_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_SPEAR_HEAD = ITEMS.register("wooden_spear_head", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> WOODEN_LONG_TOOL_ROD = ITEMS.register("wooden_long_tool_rod", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));


    //armors

    public static final RegistryObject<Item> IRON_CHAIN_LINK = ITEMS.register("iron_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLD_CHAIN_LINK = ITEMS.register("gold_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> COPPER_CHAIN_LINK = ITEMS.register("copper_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SILVER_CHAIN_LINK = ITEMS.register("silver_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BRONZE_CHAIN_LINK = ITEMS.register("bronze_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> PLATINUM_CHAIN_LINK = ITEMS.register("platinum_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STEEL_CHAIN_LINK = ITEMS.register("steel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_CHAIN_LINK = ITEMS.register("refined_obsidian_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KOBOLD_STEEL_CHAIN_LINK = ITEMS.register("kobold_steel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_CHAIN_LINK = ITEMS.register("shadow_platinum_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_CHAIN_LINK = ITEMS.register("frost_steel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_CHAIN_LINK = ITEMS.register("crystallite_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_CHAIN_LINK = ITEMS.register("dusksteel_chain_link", () -> new Item(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    public static final RegistryObject<Item> IRON_ARMOR_PLATE = ITEMS.register("iron_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLD_ARMOR_PLATE = ITEMS.register("gold_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_STUDDED_LEATHER = ITEMS.register("diamond_studded_leather", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_STUDDED_STEEL_ARMOR_PLATE = ITEMS.register("diamond_studded_steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> COPPER_ARMOR_PLATE = ITEMS.register("copper_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SILVER_ARMOR_PLATE = ITEMS.register("silver_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BRONZE_ARMOR_PLATE = ITEMS.register("bronze_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> PLATINUM_ARMOR_PLATE = ITEMS.register("platinum_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STEEL_ARMOR_PLATE = ITEMS.register("steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_ARMOR_PLATE = ITEMS.register("refined_obsidian_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    public static final RegistryObject<Item> KOBOLD_STEEL_ARMOR_PLATE = ITEMS.register("kobold_steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_ARMOR_PLATE = ITEMS.register("shadow_platinum_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_ARMOR_PLATE = ITEMS.register("frost_steel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_ARMOR_PLATE = ITEMS.register("crystallite_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_ARMOR_PLATE = ITEMS.register("dusksteel_armor_plate", () -> new ArmorPlateItem(new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)));

    public static final RegistryObject<Item> IRON_CHAIN_BINDING = ITEMS.register("iron_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ArmorMaterials.IRON));
    public static final RegistryObject<Item> GOLD_CHAIN_BINDING = ITEMS.register("gold_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ArmorMaterials.GOLD));
    public static final RegistryObject<Item> COPPER_CHAIN_BINDING = ITEMS.register("copper_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.COPPER));
    public static final RegistryObject<Item> SILVER_CHAIN_BINDING = ITEMS.register("silver_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.SILVER));
    public static final RegistryObject<Item> BRONZE_CHAIN_BINDING = ITEMS.register("bronze_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.BRONZE));
    public static final RegistryObject<Item> PLATINUM_CHAIN_BINDING = ITEMS.register("platinum_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.PLATINUM));
    public static final RegistryObject<Item> STEEL_CHAIN_BINDING = ITEMS.register("steel_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.STEEL));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_CHAIN_BINDING = ITEMS.register("refined_obsidian_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.REFINED_OBSIDIAN));

    public static final RegistryObject<Item> KOBOLD_STEEL_CHAIN_BINDING = ITEMS.register("kobold_steel_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.KOBOLD_STEEL));
    public static final RegistryObject<Item> SHADOW_PLATINUM_CHAIN_BINDING = ITEMS.register("shadow_platinum_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.SHADOW_PLATINUM));
    public static final RegistryObject<Item> FROST_STEEL_CHAIN_BINDING = ITEMS.register("frost_steel_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.FROST_STEEL));
    public static final RegistryObject<Item> CRYSTALLITE_CHAIN_BINDING = ITEMS.register("crystallite_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.CRYSTALLITE));
    public static final RegistryObject<Item> DUSKSTEEL_CHAIN_BINDING = ITEMS.register("dusksteel_chain_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ModArmorMaterials.DUSKSTEEL));

    public static final RegistryObject<Item> LEATHER_STRIP = ITEMS.register("leather_strip",() -> new Item(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB)));
    public static final RegistryObject<Item> LEATHER_BINDING = ITEMS.register("leather_binding",() -> new ArmorBindingItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB), ArmorMaterials.LEATHER));

    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_LEATHER_SET = createUnboundSet(ArmorMaterials.LEATHER);

    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_IRON_CHAIN_SET = createUnboundChainSet(ArmorMaterials.IRON);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_GOLD_CHAIN_SET = createUnboundChainSet(ArmorMaterials.GOLD);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_KOBOLD_STEEL_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.KOBOLD_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_COPPER_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.COPPER);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_SILVER_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.SILVER);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_BRONZE_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.BRONZE);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_STEEL_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_PLATINUM_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_SHADOW_PLATINUM_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.SHADOW_PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_FROST_STEEL_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.FROST_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_REFINED_OBSIDIAN_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.REFINED_OBSIDIAN);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_CRYSTALLITE_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.CRYSTALLITE);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_DUSKSTEEL_CHAIN_SET = createUnboundChainSet(ModArmorMaterials.DUSKSTEEL);

    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_IRON_SET = createUnboundSet(ArmorMaterials.IRON);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_GOLD_SET = createUnboundSet(ArmorMaterials.GOLD);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_KOBOLD_STEEL_SET = createUnboundSet(ModArmorMaterials.KOBOLD_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_COPPER_SET = createUnboundSet(ModArmorMaterials.COPPER);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_SILVER_SET = createUnboundSet(ModArmorMaterials.SILVER);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_BRONZE_SET = createUnboundSet(ModArmorMaterials.BRONZE);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_STEEL_SET = createUnboundSet(ModArmorMaterials.STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_PLATINUM_SET = createUnboundSet(ModArmorMaterials.PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_SHADOW_PLATINUM_SET = createUnboundSet(ModArmorMaterials.SHADOW_PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_FROST_STEEL_SET = createUnboundSet(ModArmorMaterials.FROST_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_REFINED_OBSIDIAN_SET = createUnboundSet(ModArmorMaterials.REFINED_OBSIDIAN);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_CRYSTALLITE_SET = createUnboundSet(ModArmorMaterials.CRYSTALLITE);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_DUSKSTEEL_SET = createUnboundSet(ModArmorMaterials.DUSKSTEEL);

    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_DIAMOND_STUDDED_LEATHER_SET = createUnboundSet(ModArmorMaterials.DIAMOND_STUDDED_LEATHER);
    public static final Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> UNBOUND_DIAMOND_STUDDED_STEEL_SET = createUnboundSet(ModArmorMaterials.DIAMOND_STUDDED_STEEL);


    //public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> LEATHER_SET = createBoundSet(ArmorMaterials.LEATHER);

    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> IRON_CHAIN_SET = createBoundSet(ModArmorMaterials.IRON_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> GOLD_CHAIN_SET = createBoundSet(ModArmorMaterials.GOLD_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> KOBOLD_STEEL_CHAIN_SET = createBoundSet(ModArmorMaterials.KOBOLD_STEEL_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> COPPER_CHAIN_SET = createBoundSet(ModArmorMaterials.COPPER_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> SILVER_CHAIN_SET = createBoundSet(ModArmorMaterials.SILVER_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> BRONZE_CHAIN_SET = createBoundSet(ModArmorMaterials.BRONZE_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> STEEL_CHAIN_SET = createBoundSet(ModArmorMaterials.STEEL_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> PLATINUM_CHAIN_SET = createBoundSet(ModArmorMaterials.PLATINUM_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> SHADOW_PLATINUM_CHAIN_SET = createBoundSet(ModArmorMaterials.SHADOW_PLATINUM_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> FROST_STEEL_CHAIN_SET = createBoundSet(ModArmorMaterials.FROST_STEEL_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> REFINED_OBSIDIAN_CHAIN_SET = createBoundSet(ModArmorMaterials.REFINED_OBSIDIAN_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> CRYSTALLITE_CHAIN_SET = createBoundSet(ModArmorMaterials.CRYSTALLITE_CHAIN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> DUSKSTEEL_CHAIN_SET = createBoundSet(ModArmorMaterials.DUSKSTEEL_CHAIN);

    //public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> IRON_SET = createBoundSet(ArmorMaterials.IRON);
    //public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> GOLD_SET = createBoundSet(ArmorMaterials.GOLD);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> KOBOLD_STEEL_SET = createBoundSet(ModArmorMaterials.KOBOLD_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> COPPER_SET = createBoundSet(ModArmorMaterials.COPPER);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> SILVER_SET = createBoundSet(ModArmorMaterials.SILVER);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> BRONZE_SET = createBoundSet(ModArmorMaterials.BRONZE);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> STEEL_SET = createBoundSet(ModArmorMaterials.STEEL);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> PLATINUM_SET = createBoundSet(ModArmorMaterials.PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> SHADOW_PLATINUM_SET = createBoundSet(ModArmorMaterials.SHADOW_PLATINUM);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> FROST_STEEL_SET = createBoundSet(ModArmorMaterials.FROST_STEEL);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> REFINED_OBSIDIAN_SET = createBoundSet(ModArmorMaterials.REFINED_OBSIDIAN);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> CRYSTALLITE_SET = createBoundSet(ModArmorMaterials.CRYSTALLITE);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> DUSKSTEEL_SET = createBoundSet(ModArmorMaterials.DUSKSTEEL);

    public static final Map<EquipmentSlot,RegistryObject<DyeableBoundArmorItem>> DIAMOND_STUDDED_LEATHER_SET = createDyeableBoundSet(ModArmorMaterials.DIAMOND_STUDDED_LEATHER);
    public static final Map<EquipmentSlot,RegistryObject<BoundArmorItem>> DIAMOND_STUDDED_STEEL_SET = createBoundSet(ModArmorMaterials.DIAMOND_STUDDED_STEEL);

    public static Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> createUnboundSet(ArmorMaterial armorMaterial) {
        String name = armorMaterial.getName();
        if (name.contains(":")) {
            name = name.split(":")[1];
        }

        Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> map = new EnumMap<>(EquipmentSlot.class);
        for (Map.Entry<EquipmentSlot, String> entry : Utils.ARMOR_SLOTS.entrySet()) {
            var item = ITEMS.register("unbound_"+name+"_"+entry.getValue(),() -> new UnboundArmorItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB)));
            map.put(entry.getKey(),item);
        }
        return map;
    }

    public static Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> createUnboundChainSet(ArmorMaterial armorMaterial) {
        String name = armorMaterial.getName();
        if (name.contains(":")) {
            name = name.split(":")[1];
        }
        Map<EquipmentSlot,RegistryObject<UnboundArmorItem>> map = new EnumMap<>(EquipmentSlot.class);
        for (Map.Entry<EquipmentSlot, String> entry : Utils.ARMOR_SLOTS.entrySet()) {
            var item = ITEMS.register("unbound_"+name+"_chain_"+entry.getValue(),() -> new UnboundArmorItem(new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB)));
            map.put(entry.getKey(),item);
        }
        return map;
    }

    public static Map<EquipmentSlot,RegistryObject<BoundArmorItem>> createBoundSet(ArmorMaterial armorMaterial) {
        String name = armorMaterial.getName();
        if (name.contains(":")) {
            name = name.split(":")[1];
        }
        Map<EquipmentSlot,RegistryObject<BoundArmorItem>> map = new EnumMap<>(EquipmentSlot.class);
        for (Map.Entry<EquipmentSlot, String> entry : Utils.ARMOR_SLOTS.entrySet()) {
            var item = ITEMS.register(name+"_"+entry.getValue(),() -> new BoundArmorItem(armorMaterial,entry.getKey(),
                    new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB)));
            map.put(entry.getKey(),item);
        }
        return map;
    }

    public static Map<EquipmentSlot,RegistryObject<DyeableBoundArmorItem>> createDyeableBoundSet(ArmorMaterial armorMaterial) {
        String name = armorMaterial.getName();
        if (name.contains(":")) {
            name = name.split(":")[1];
        }
        Map<EquipmentSlot,RegistryObject<DyeableBoundArmorItem>> map = new EnumMap<>(EquipmentSlot.class);
        for (Map.Entry<EquipmentSlot, String> entry : Utils.ARMOR_SLOTS.entrySet()) {
            var item = ITEMS.register(name+"_"+entry.getValue(),() -> new DyeableBoundArmorItem(armorMaterial,entry.getKey(),
                    new Item.Properties().tab(ModCreativeTabs.ARMOR_TAB)));
            map.put(entry.getKey(),item);
        }
        return map;
    }

    ////////// weapons

    public static final RegistryObject<AlwWeaponItem> DAGGER = registerWeaponType(WeaponAttributes.DAGGER);
    public static final RegistryObject<AlwWeaponItem> KABUTOWARI = registerWeaponType(WeaponAttributes.KABUTOWARI);
    public static final RegistryObject<AlwWeaponItem> RAPIER = registerWeaponType(WeaponAttributes.RAPIER);
    public static final RegistryObject<AlwWeaponItem> TALWAR = registerWeaponType(WeaponAttributes.TALWAR);
    public static final RegistryObject<AlwWeaponItem> CLEAVER = registerWeaponType(WeaponAttributes.CLEAVER);
    public static final RegistryObject<AlwWeaponItem> MACE = registerWeaponType(WeaponAttributes.MACE);
    public static final RegistryObject<AlwWeaponItem> STAFF = registerWeaponType(WeaponAttributes.STAFF);
    public static final RegistryObject<AlwWeaponItem> LONGSWORD = registerWeaponType(WeaponAttributes.LONGSWORD);
    public static final RegistryObject<AlwWeaponItem> KODACHI = registerWeaponType(WeaponAttributes.KODACHI);
    public static final RegistryObject<AlwWeaponItem> NODACHI = registerWeaponType(WeaponAttributes.NODACHI);
    public static final RegistryObject<AlwWeaponItem> BATTLEAXE = registerWeaponType(WeaponAttributes.BATTLEAXE);
    public static final RegistryObject<AlwWeaponItem> ZWEIHANDER = registerWeaponType(WeaponAttributes.ZWEIHANDER);
    public static final RegistryObject<AlwWeaponItem> SABRE = registerWeaponType(WeaponAttributes.SABRE);
    public static final RegistryObject<AlwWeaponItem> MAKHAIRA = registerWeaponType(WeaponAttributes.MAKHAIRA);
    public static final RegistryObject<AlwWeaponItem> SPEAR = registerWeaponType(WeaponAttributes.SPEAR);


    private static RegistryObject<AlwWeaponItem> registerWeaponType(WeaponAttributes weaponAttributes) {
        return ITEMS.register(weaponAttributes.getType(),
                () -> new AlwWeaponItem(weaponAttributes, new Item.Properties().tab(ModCreativeTabs.MISC_TAB).durability(2)));
    }

    public static final RegistryObject<HeatableToolPartItem> TOOL_HEAD = ITEMS.register("tool_head", () -> new HeatableToolPartItem(0, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> TOOL_ROD_2 = ITEMS.register("tool_rod_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> TOOL_ROD = ITEMS.register("tool_rod", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> LONG_TOOL_ROD = ITEMS.register("long_tool_rod", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> DAGGER_HEAD_2 = ITEMS.register("dagger_head_2", () -> new HeatableToolPartItem(2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> DAGGER_HEAD = ITEMS.register("dagger_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> KABUTOWARI_HEAD_5 = ITEMS.register("kabutowari_head_5", () -> new HeatableToolPartItem(5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> KABUTOWARI_HEAD_4 = ITEMS.register("kabutowari_head_4", () -> new HeatableToolPartItem(4, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> KABUTOWARI_HEAD_3 = ITEMS.register("kabutowari_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> KABUTOWARI_HEAD_2 = ITEMS.register("kabutowari_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> KABUTOWARI_HEAD = ITEMS.register("kabutowari_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> TALWAR_HEAD_3 = ITEMS.register("talwar_head_3", () -> new HeatableToolPartItem(3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> TALWAR_HEAD_2 = ITEMS.register("talwar_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> TALWAR_HEAD = ITEMS.register("talwar_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> RAPIER_HEAD_4 = ITEMS.register("rapier_head_4", () -> new HeatableToolPartItem(4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> RAPIER_HEAD_3 = ITEMS.register("rapier_head_3", () -> new HeatableToolPartItem(3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> RAPIER_HEAD_2 = ITEMS.register("rapier_head_2", () -> new HeatableToolPartItem(2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> RAPIER_HEAD = ITEMS.register("rapier_head", () -> new HeatableToolPartItem(1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> CLEAVER_HEAD = ITEMS.register("cleaver_head", () -> new HeatableToolPartItem(1, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> MACE_HEAD_3 = ITEMS.register("mace_head_3", () -> new HeatableToolPartItem(3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> MACE_HEAD_2 = ITEMS.register("mace_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> MACE_HEAD = ITEMS.register("mace_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> STAFF_HEAD_5 = ITEMS.register("staff_head_5", () -> new HeatableToolPartItem(5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> STAFF_HEAD_4 = ITEMS.register("staff_head_4", () -> new HeatableToolPartItem(4, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> STAFF_HEAD_3 = ITEMS.register("staff_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> STAFF_HEAD_2 = ITEMS.register("staff_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> STAFF_HEAD = ITEMS.register("staff_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> LONGSWORD_HEAD_4 = ITEMS.register("longsword_head_4", () -> new HeatableToolPartItem(4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> LONGSWORD_HEAD_3 = ITEMS.register("longsword_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> LONGSWORD_HEAD_2 = ITEMS.register("longsword_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> LONGSWORD_HEAD = ITEMS.register("longsword_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> BATTLEAXE_HEAD_5 = ITEMS.register("battleaxe_head_5", () -> new HeatableToolPartItem(5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> BATTLEAXE_HEAD_4 = ITEMS.register("battleaxe_head_4", () -> new HeatableToolPartItem(4, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> BATTLEAXE_HEAD_3 = ITEMS.register("battleaxe_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> BATTLEAXE_HEAD_2 = ITEMS.register("battleaxe_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> BATTLEAXE_HEAD = ITEMS.register("battleaxe_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> ZWEIHANDER_HEAD_5 = ITEMS.register("zweihander_head_5", () -> new HeatableToolPartItem(5, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> ZWEIHANDER_HEAD_4 = ITEMS.register("zweihander_head_4", () -> new HeatableToolPartItem(4, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> ZWEIHANDER_HEAD_3 = ITEMS.register("zweihander_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> ZWEIHANDER_HEAD_2 = ITEMS.register("zweihander_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> ZWEIHANDER_HEAD = ITEMS.register("zweihander_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> KODACHI_HEAD_2 = ITEMS.register("kodachi_head_2", () -> new HeatableToolPartItem(2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> KODACHI_HEAD = ITEMS.register("kodachi_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> NODACHI_HEAD_4 = ITEMS.register("nodachi_head_4", () -> new HeatableToolPartItem(4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> NODACHI_HEAD_3 = ITEMS.register("nodachi_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> NODACHI_HEAD_2 = ITEMS.register("nodachi_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> NODACHI_HEAD = ITEMS.register("nodachi_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> SABRE_HEAD_4 = ITEMS.register("sabre_head_4", () -> new HeatableToolPartItem(4, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> SABRE_HEAD_3 = ITEMS.register("sabre_head_3", () -> new HeatableToolPartItem(3, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> SABRE_HEAD_2 = ITEMS.register("sabre_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> SABRE_HEAD = ITEMS.register("sabre_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> MAKHAIRA_HEAD_3 = ITEMS.register("makhaira_head_3", () -> new HeatableToolPartItem(3, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> MAKHAIRA_HEAD_2 = ITEMS.register("makhaira_head_2", () -> new HeatableToolPartItem(2, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> MAKHAIRA_HEAD = ITEMS.register("makhaira_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> SPEAR_HEAD_2 = ITEMS.register("spear_head_2", () -> new HeatableToolPartItem(2, true, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());
    public static final RegistryObject<HeatableToolPartItem> SPEAR_HEAD = ITEMS.register("spear_head", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    public static final RegistryObject<HeatableToolPartItem> CHAIN_RING = ITEMS.register("chain_ring", () -> new HeatableToolPartItem(1, false, new Item.Properties().tab(ModCreativeTabs.WEAPON_TAB)).addToRegistryMap());

    //whetstones
    public static final RegistryObject<Item> STONE_WHETSTONE = ITEMS.register("stone_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), Tiers.WOOD));
    public static final RegistryObject<Item> IRON_WHETSTONE = ITEMS.register("iron_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), Tiers.IRON));
    public static final RegistryObject<Item> KOBOLD_STEEL_WHETSTONE = ITEMS.register("kobold_steel_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.KOBOLD_STEEL));
    public static final RegistryObject<Item> COPPER_WHETSTONE = ITEMS.register("copper_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.COPPER));
    public static final RegistryObject<Item> SILVER_WHETSTONE = ITEMS.register("silver_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), SILVER));
    public static final RegistryObject<Item> BRONZE_WHETSTONE = ITEMS.register("bronze_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), BRONZE));
    public static final RegistryObject<Item> PLATINUM_WHETSTONE = ITEMS.register("platinum_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.PLATINUM));
    public static final RegistryObject<Item> STEEL_WHETSTONE = ITEMS.register("steel_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.STEEL));
    public static final RegistryObject<Item> SHADOW_PLATINUM_WHETSTONE = ITEMS.register("shadow_platinum_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.SHADOW_PLATINUM));
    public static final RegistryObject<Item> FROST_STEEL_WHETSTONE = ITEMS.register("frost_steel_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.FROST_STEEL));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_WHETSTONE = ITEMS.register("refined_obsidian_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.REFINED_OBSIDIAN));
    public static final RegistryObject<Item> CRYSTALLITE_WHETSTONE = ITEMS.register("crystallite_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.CRYSTALLITE));
    public static final RegistryObject<Item> DUSKSTEEL_WHETSTONE = ITEMS.register("dusksteel_whetstone", () -> new WhetstoneItem(new Item.Properties().stacksTo(16).tab(ModCreativeTabs.WEAPON_TAB), ModMaterials.DUSKSTEEL));

    public static final RegistryObject<BucketItem> MILK_OF_LIME_BUCKET = ITEMS.register("milk_of_lime_bucket", () -> new BucketItem(FluidInit.MILK_OF_LIME, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));
    public static final RegistryObject<BucketItem> MAGNESIUM_LACTATE_BUCKET = ITEMS.register("magnesium_lactate_bucket", () -> new BucketItem(FluidInit.MAGNESIUM_LACTATE, new Item.Properties().tab(ModCreativeTabs.MISC_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
