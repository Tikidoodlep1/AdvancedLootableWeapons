package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.items.ItemForgeHammer;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

@SuppressWarnings("deprecation")
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AdvancedLootableWeapons.MODID);
    public static final List<Item> hotToolHeads = new ArrayList<>();

    //Mats
    public static final ForgeTier MAT_KOBOLD = new ForgeTier(1, CommonConfigHandler.KOBOLD_DURABILITY.get(), 6.0F, CommonConfigHandler.KOBOLD_DAMAGE.get(), 22,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.KOBOLD_INGOT.get()));
    public static final ForgeTier MAT_COPPER = new ForgeTier(1, CommonConfigHandler.COPPER_DURABILITY.get(), 6.0F, CommonConfigHandler.COPPER_DAMAGE.get(), 22,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier MAT_SILVER = new ForgeTier(1, CommonConfigHandler.SILVER_DURABILITY.get(), 7.0F, CommonConfigHandler.SILVER_DAMAGE.get(), 24,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.SILVER_INGOT.get()));
    public static final ForgeTier MAT_BRONZE = new ForgeTier(2, CommonConfigHandler.BRONZE_DURABILITY.get(), 6.5F, CommonConfigHandler.BRONZE_DAMAGE.get(), 12,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.BRONZE_INGOT.get()));
    public static final ForgeTier MAT_PLATINUM = new ForgeTier(2, CommonConfigHandler.PLATINUM_DURABILITY.get(), 10.0F, CommonConfigHandler.PLATINUM_DAMAGE.get(), 26,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.PLATINUM_INGOT.get()));
    public static final ForgeTier MAT_STEEL = new ForgeTier(2, CommonConfigHandler.STEEL_DURABILITY.get(), 7.0F, CommonConfigHandler.STEEL_DAMAGE.get(), 18,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemInit.STEEL_INGOT.get()));
    public static final ForgeTier MAT_SHADOW_PLATINUM = new ForgeTier(3, CommonConfigHandler.SHADOW_PLATINUM_DURABILITY.get(), 8.25F, CommonConfigHandler.SHADOW_PLATINUM_DAMAGE.get(), 21,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.SHADOW_PLATINUM_INGOT.get()));
    public static final ForgeTier MAT_FROST_STEEL = new ForgeTier(3, CommonConfigHandler.FROST_STEEL_DURABILITY.get(), 7.0F, CommonConfigHandler.FROST_STEEL_DAMAGE.get(), 30,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.FROST_STEEL_INGOT.get()));
    public static final ForgeTier MAT_OBSIDIAN = new ForgeTier(3, CommonConfigHandler.OBSIDIAN_DURABILITY.get(), 7.5F, CommonConfigHandler.OBSIDIAN_DAMAGE.get(), 18,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.REFINED_OBSIDIAN_INGOT.get()));
    public static final ForgeTier MAT_CRYSTALLITE = new ForgeTier(4, CommonConfigHandler.CRYSTALLITE_DURABILITY.get(), 7.5F, CommonConfigHandler.CRYSTALLITE_DAMAGE.get(), 20,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.CRYSTALLITE_INGOT.get()));
    public static final ForgeTier MAT_DUSKSTEEL = new ForgeTier(4, CommonConfigHandler.DUSKSTEEL_DURABILITY.get(), 8.5F, CommonConfigHandler.DUSKSTEEL_DAMAGE.get(), 14,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.DUSKSTEEL_INGOT.get()));


    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_INGOT = ITEMS.register("kobold_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_INGOT = ITEMS.register("shadow_platinum_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_INGOT = ITEMS.register("frost_steel_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_INGOT = ITEMS.register("refined_obsidian_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_INGOT = ITEMS.register("crystallite_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_INGOT = ITEMS.register("dusksteel_ingot", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_NUGGET = ITEMS.register("kobold_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_NUGGET = ITEMS.register("shadow_platinum_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_NUGGET = ITEMS.register("frost_steel_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> REFINED_OBSIDIAN_NUGGET = ITEMS.register("refined_obsidian_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_NUGGET = ITEMS.register("crystallite_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_NUGGET = ITEMS.register("dusksteel_nugget", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> FELDSPAR_POWDER = ITEMS.register("feldspar_powder", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_POWDER = ITEMS.register("granite_powder", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_POWDER = ITEMS.register("diorite_powder", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_CLAY = ITEMS.register("granite_clay", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_CLAY = ITEMS.register("diorite_clay", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> GRANITE_BRICK = ITEMS.register("granite_brick", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DIORITE_BRICK = ITEMS.register("diorite_brick", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW = ITEMS.register("shadow", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CONGEALED_SHADOW = ITEMS.register("congealed_shadow", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> STONE_FORGE_HAMMER = ITEMS.register("stone_forge_hammer", () -> new ItemForgeHammer(Tiers.STONE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> IRON_FORGE_HAMMER = ITEMS.register("iron_forge_hammer", () -> new ItemForgeHammer(Tiers.IRON, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> KOBOLD_FORGE_HAMMER = ITEMS.register("kobold_forge_hammer", () -> new ItemForgeHammer(MAT_KOBOLD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> COPPER_FORGE_HAMMER = ITEMS.register("copper_forge_hammer", () -> new ItemForgeHammer(MAT_COPPER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SILVER_FORGE_HAMMER = ITEMS.register("silver_forge_hammer", () -> new ItemForgeHammer(MAT_SILVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> BRONZE_FORGE_HAMMER = ITEMS.register("bronze_forge_hammer", () -> new ItemForgeHammer(MAT_BRONZE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> PLATINUM_FORGE_HAMMER = ITEMS.register("platinum_forge_hammer", () -> new ItemForgeHammer(MAT_PLATINUM, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> STEEL_FORGE_HAMMER = ITEMS.register("steel_forge_hammer", () -> new ItemForgeHammer(MAT_STEEL, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> SHADOW_PLATINUM_FORGE_HAMMER = ITEMS.register("shadow_platinum_forge_hammer", () -> new ItemForgeHammer(MAT_SHADOW_PLATINUM, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> FROST_STEEL_FORGE_HAMMER = ITEMS.register("frost_steel_forge_hammer", () -> new ItemForgeHammer(MAT_FROST_STEEL, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_FORGE_HAMMER = ITEMS.register("obsidian_forge_hammer", () -> new ItemForgeHammer(MAT_OBSIDIAN, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> CRYSTALLITE_FORGE_HAMMER = ITEMS.register("crystallite_forge_hammer", () -> new ItemForgeHammer(MAT_CRYSTALLITE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
    public static final RegistryObject<Item> DUSKSTEEL_FORGE_HAMMER = ITEMS.register("dusksteel_forge_hammer", () -> new ItemForgeHammer(MAT_DUSKSTEEL, new Item.Properties().tab(CreativeTabs.MISC_TAB)));

    public static final RegistryObject<Item> DAGGER_HEAD = ITEMS.register("dagger_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KABUTOWARI_HEAD = ITEMS.register("kabutowari_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> RAPIER_HEAD = ITEMS.register("rapier_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> TALWAR_HEAD = ITEMS.register("talwar_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> MACE_HEAD = ITEMS.register("mace_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> CLEAVER_HEAD = ITEMS.register("cleaver_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> STAFF_HEAD = ITEMS.register("staff_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> LONGSWORD_HEAD = ITEMS.register("longsword_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> BATTLEAXE_HEAD = ITEMS.register("battleaxe_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> ZWEIHANDER_HEAD = ITEMS.register("zweihander_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> KODACHI_HEAD = ITEMS.register("kodachi_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> NODACHI_HEAD = ITEMS.register("nodachi_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SABRE_HEAD = ITEMS.register("sabre_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> MAKHAIRA_HEAD = ITEMS.register("makhaira_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> SPEAR_HEAD = ITEMS.register("spear_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
    public static final RegistryObject<Item> LONG_WEAPON_HANDLE = ITEMS.register("long_weapon_handle", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));


    public static final List<Pair<String, Tier>> tiers;

    public static WeaponAttributes[] attributes;

    public static final List<RegistryObject<AlwWeapon>> WEAPONS = new ArrayList<>();

    static {

        tiers = new ArrayList<>();
        tiers.add(Pair.of("wood", Tiers.WOOD));
        tiers.add(Pair.of("iron", Tiers.IRON));
        tiers.add(Pair.of("kobold", MAT_KOBOLD));
        tiers.add(Pair.of("copper", MAT_COPPER));
        tiers.add(Pair.of("silver", MAT_SILVER));
        tiers.add(Pair.of("bronze", MAT_BRONZE));
        tiers.add(Pair.of("platinum", MAT_PLATINUM));
        tiers.add(Pair.of("steel", MAT_STEEL));
        tiers.add(Pair.of("shadow_platinum", MAT_SHADOW_PLATINUM));
        tiers.add(Pair.of("frost_steel", MAT_FROST_STEEL));
        tiers.add(Pair.of("obsidian", MAT_OBSIDIAN));
        tiers.add(Pair.of("crystallite", MAT_CRYSTALLITE));
        tiers.add(Pair.of("dusksteel", MAT_DUSKSTEEL));

        attributes = Arrays.copyOf(WeaponAttributes.values(),WeaponAttributes.values().length - 2);

        for (WeaponAttributes attribute : attributes) {
            for (Pair<String, Tier> tier : tiers) {
                RegistryObject<AlwWeapon> weapon =
                ITEMS.register(tier.getLeft()+"_"+attribute.getType().toLowerCase(Locale.ROOT),
                        ()-> new AlwWeapon(tier.getRight(),attribute,new Item.Properties().tab(CreativeTabs.MISC_TAB)));
                WEAPONS.add(weapon);
            }
        }
    }

    public static final RegistryObject<ItemHotToolHead> HOT_TOOL_HEAD = ITEMS.register("hot_tool_head", () -> new ItemHotToolHead(null, 0, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> HOT_TOOL_ROD_2 = ITEMS.register("hot_tool_rod_2", () -> new ItemHotToolHead(null, 2, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> HOT_TOOL_ROD = ITEMS.register("hot_tool_rod", () -> new ItemHotToolHead(HOT_TOOL_ROD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> LONG_TOOL_ROD = ITEMS.register("long_tool_rod", () -> new ItemHotToolHead(null, 3, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> DAGGER_HOT_TOOL_HEAD_2 = ITEMS.register("dagger_hot_tool_head_2", () -> new ItemHotToolHead(null, 2, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> DAGGER_HOT_TOOL_HEAD = ITEMS.register("dagger_hot_tool_head", () -> new ItemHotToolHead(DAGGER_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> KABUTOWARI_HOT_TOOL_HEAD_5 = ITEMS.register("kabutowari_hot_tool_head_5", () -> new ItemHotToolHead(null, 5, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> KABUTOWARI_HOT_TOOL_HEAD_4 = ITEMS.register("kabutowari_hot_tool_head_4", () -> new ItemHotToolHead(KABUTOWARI_HOT_TOOL_HEAD_5.get(), 4, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> KABUTOWARI_HOT_TOOL_HEAD_3 = ITEMS.register("kabutowari_hot_tool_head_3", () -> new ItemHotToolHead(KABUTOWARI_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> KABUTOWARI_HOT_TOOL_HEAD_2 = ITEMS.register("kabutowari_hot_tool_head_2", () -> new ItemHotToolHead(KABUTOWARI_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> KABUTOWARI_HOT_TOOL_HEAD = ITEMS.register("kabutowari_hot_tool_head", () -> new ItemHotToolHead(KABUTOWARI_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> TALWAR_HOT_TOOL_HEAD_3 = ITEMS.register("talwar_hot_tool_head_3", () -> new ItemHotToolHead(null, 3, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> TALWAR_HOT_TOOL_HEAD_2 = ITEMS.register("talwar_hot_tool_head_2", () -> new ItemHotToolHead(TALWAR_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> TALWAR_HOT_TOOL_HEAD = ITEMS.register("talwar_hot_tool_head", () -> new ItemHotToolHead(TALWAR_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> RAPIER_HOT_TOOL_HEAD_4 = ITEMS.register("rapier_hot_tool_head_4", () -> new ItemHotToolHead(null, 4, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> RAPIER_HOT_TOOL_HEAD_3 = ITEMS.register("rapier_hot_tool_head_3", () -> new ItemHotToolHead(RAPIER_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> RAPIER_HOT_TOOL_HEAD_2 = ITEMS.register("rapier_hot_tool_head_2", () -> new ItemHotToolHead(RAPIER_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> RAPIER_HOT_TOOL_HEAD = ITEMS.register("rapier_hot_tool_head", () -> new ItemHotToolHead(RAPIER_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> CLEAVER_HOT_TOOL_HEAD = ITEMS.register("cleaver_hot_tool_head", () -> new ItemHotToolHead(null, 1, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> MACE_HOT_TOOL_HEAD_3 = ITEMS.register("mace_hot_tool_head_3", () -> new ItemHotToolHead(null, 3, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> MACE_HOT_TOOL_HEAD_2 = ITEMS.register("mace_hot_tool_head_2", () -> new ItemHotToolHead(MACE_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> MACE_HOT_TOOL_HEAD = ITEMS.register("mace_hot_tool_head", () -> new ItemHotToolHead(MACE_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> STAFF_HOT_TOOL_HEAD_5 = ITEMS.register("staff_hot_tool_head_5", () -> new ItemHotToolHead(null, 5, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> STAFF_HOT_TOOL_HEAD_4 = ITEMS.register("staff_hot_tool_head_4", () -> new ItemHotToolHead(STAFF_HOT_TOOL_HEAD_5.get(), 4, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> STAFF_HOT_TOOL_HEAD_3 = ITEMS.register("staff_hot_tool_head_3", () -> new ItemHotToolHead(STAFF_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> STAFF_HOT_TOOL_HEAD_2 = ITEMS.register("staff_hot_tool_head_2", () -> new ItemHotToolHead(STAFF_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> STAFF_HOT_TOOL_HEAD = ITEMS.register("staff_hot_tool_head", () -> new ItemHotToolHead(STAFF_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> LONGSWORD_HOT_TOOL_HEAD_4 = ITEMS.register("longsword_hot_tool_head_4", () -> new ItemHotToolHead(null, 4, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> LONGSWORD_HOT_TOOL_HEAD_3 = ITEMS.register("longsword_hot_tool_head_3", () -> new ItemHotToolHead(LONGSWORD_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> LONGSWORD_HOT_TOOL_HEAD_2 = ITEMS.register("longsword_hot_tool_head_2", () -> new ItemHotToolHead(LONGSWORD_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> LONGSWORD_HOT_TOOL_HEAD = ITEMS.register("longsword_hot_tool_head", () -> new ItemHotToolHead(LONGSWORD_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> BATTLEAXE_HOT_TOOL_HEAD_5 = ITEMS.register("battleaxe_hot_tool_head_5", () -> new ItemHotToolHead(null, 5, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> BATTLEAXE_HOT_TOOL_HEAD_4 = ITEMS.register("battleaxe_hot_tool_head_4", () -> new ItemHotToolHead(BATTLEAXE_HOT_TOOL_HEAD_5.get(), 4, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> BATTLEAXE_HOT_TOOL_HEAD_3 = ITEMS.register("battleaxe_hot_tool_head_3", () -> new ItemHotToolHead(BATTLEAXE_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> BATTLEAXE_HOT_TOOL_HEAD_2 = ITEMS.register("battleaxe_hot_tool_head_2", () -> new ItemHotToolHead(BATTLEAXE_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> BATTLEAXE_HOT_TOOL_HEAD = ITEMS.register("battleaxe_hot_tool_head", () -> new ItemHotToolHead(BATTLEAXE_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> ZWEIHANDER_HOT_TOOL_HEAD_5 = ITEMS.register("zweihander_hot_tool_head_5", () -> new ItemHotToolHead(null, 5, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> ZWEIHANDER_HOT_TOOL_HEAD_4 = ITEMS.register("zweihander_hot_tool_head_4", () -> new ItemHotToolHead(ZWEIHANDER_HOT_TOOL_HEAD_5.get(), 4, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> ZWEIHANDER_HOT_TOOL_HEAD_3 = ITEMS.register("zweihander_hot_tool_head_3", () -> new ItemHotToolHead(ZWEIHANDER_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> ZWEIHANDER_HOT_TOOL_HEAD_2 = ITEMS.register("zweihander_hot_tool_head_2", () -> new ItemHotToolHead(ZWEIHANDER_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> ZWEIHANDER_HOT_TOOL_HEAD = ITEMS.register("zweihander_hot_tool_head", () -> new ItemHotToolHead(ZWEIHANDER_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> KODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("kodachi_hot_tool_head_2", () -> new ItemHotToolHead(null, 2, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> KODACHI_HOT_TOOL_HEAD = ITEMS.register("kodachi_hot_tool_head", () -> new ItemHotToolHead(KODACHI_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> NODACHI_HOT_TOOL_HEAD_4 = ITEMS.register("nodachi_hot_tool_head_4", () -> new ItemHotToolHead(null, 4, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> NODACHI_HOT_TOOL_HEAD_3 = ITEMS.register("nodachi_hot_tool_head_3", () -> new ItemHotToolHead(NODACHI_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> NODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("nodachi_hot_tool_head_2", () -> new ItemHotToolHead(NODACHI_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> NODACHI_HOT_TOOL_HEAD = ITEMS.register("nodachi_hot_tool_head", () -> new ItemHotToolHead(NODACHI_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> SABRE_HOT_TOOL_HEAD_4 = ITEMS.register("sabre_hot_tool_head_4", () -> new ItemHotToolHead(null, 4, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> SABRE_HOT_TOOL_HEAD_3 = ITEMS.register("sabre_hot_tool_head_3", () -> new ItemHotToolHead(SABRE_HOT_TOOL_HEAD_4.get(), 3, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> SABRE_HOT_TOOL_HEAD_2 = ITEMS.register("sabre_hot_tool_head_2", () -> new ItemHotToolHead(SABRE_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> SABRE_HOT_TOOL_HEAD = ITEMS.register("sabre_hot_tool_head", () -> new ItemHotToolHead(SABRE_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> MAKHAIRA_HOT_TOOL_HEAD_3 = ITEMS.register("makhaira_hot_tool_head_3", () -> new ItemHotToolHead(null, 3, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> MAKHAIRA_HOT_TOOL_HEAD_2 = ITEMS.register("makhaira_hot_tool_head_2", () -> new ItemHotToolHead(MAKHAIRA_HOT_TOOL_HEAD_3.get(), 2, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> MAKHAIRA_HOT_TOOL_HEAD = ITEMS.register("makhaira_hot_tool_head", () -> new ItemHotToolHead(MAKHAIRA_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static final RegistryObject<ItemHotToolHead> SPEAR_HOT_TOOL_HEAD_2 = ITEMS.register("spear_hot_tool_head_2", () -> new ItemHotToolHead(null, 2, true, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
    public static final RegistryObject<ItemHotToolHead> SPEAR_HOT_TOOL_HEAD = ITEMS.register("spear_hot_tool_head", () -> new ItemHotToolHead(SPEAR_HOT_TOOL_HEAD_2.get(), 1, false, new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
