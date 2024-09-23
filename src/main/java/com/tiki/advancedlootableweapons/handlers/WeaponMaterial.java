package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.ModMaterials;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import com.tiki.advancedlootableweapons.util.MetalStats;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public record WeaponMaterial(String name, TagKey<Item> valid, Supplier<Item> defaultItem, Tier tier,
                             MetalStats metalStats, boolean canBeForged, boolean canMakeWeapon) {

    public static final List<WeaponMaterial> LOOKUP = new ArrayList<>();
    public static final WeaponMaterial NULL = new WeaponMaterial("null", null, () -> Items.AIR, null, null, false, false);

    public static final WeaponMaterial STEEL = register(new WeaponMaterial("steel", ModItemTags.INGOTS_STEEL, ItemInit.STEEL_INGOT, ModMaterials.STEEL, MetalStats.STEEL, true, true));
    public static final WeaponMaterial FROST_STEEL = register(new WeaponMaterial("frost_steel", ModItemTags.INGOTS_FROST_STEEL, ItemInit.FROST_STEEL_INGOT, ModMaterials.FROST_STEEL, MetalStats.FROST_STEEL, true, true));
    public static final WeaponMaterial WOOD = register(new WeaponMaterial("wood", ItemTags.PLANKS, () -> Items.OAK_PLANKS, Tiers.WOOD, null, false, true));
    public static final WeaponMaterial STONE = register(new WeaponMaterial("stoone", ItemTags.STONE_TOOL_MATERIALS, () -> Items.COBBLESTONE, Tiers.STONE, null, false, false));
    public static final WeaponMaterial COPPER = register(new WeaponMaterial("copper", Tags.Items.INGOTS_COPPER, () -> Items.COPPER_INGOT, ModMaterials.COPPER, MetalStats.COPPER, true, true));
    public static final WeaponMaterial IRON = register(new WeaponMaterial("iron", Tags.Items.INGOTS_IRON, () -> Items.IRON_INGOT, Tiers.IRON, MetalStats.IRON, true, true));
    public static final WeaponMaterial GOLD = register(new WeaponMaterial("gold", Tags.Items.INGOTS_GOLD, () -> Items.GOLD_INGOT, Tiers.GOLD, MetalStats.GOLD, true, false));
    public static final WeaponMaterial DIAMOND = register(new WeaponMaterial("diamond", Tags.Items.GEMS_DIAMOND, () -> Items.DIAMOND, Tiers.DIAMOND, null, false, false));
    public static final WeaponMaterial NETHERITE = register(new WeaponMaterial("netherite", Tags.Items.INGOTS_NETHERITE, () -> Items.NETHERITE_INGOT, Tiers.NETHERITE, null, false, false));
    public static final WeaponMaterial BRONZE = register(new WeaponMaterial("bronze", ModItemTags.INGOTS_BRONZE, ItemInit.BRONZE_INGOT, ModMaterials.BRONZE, MetalStats.BRONZE, true, true));
    public static final WeaponMaterial KOBOLD_STEEL = register(new WeaponMaterial("kobold_steel", ModItemTags.INGOTS_KOBOLD_STEEL, ItemInit.KOBOLD_STEEL_INGOT, ModMaterials.KOBOLD_STEEL, MetalStats.KOBOLD_STEEL, true, true));
    public static final WeaponMaterial SILVER = register(new WeaponMaterial("silver", ModItemTags.INGOTS_SILVER, ItemInit.SILVER_INGOT, ModMaterials.SILVER, MetalStats.SILVER, true, true));
    public static final WeaponMaterial PLATINUM = register(new WeaponMaterial("platinum", ModItemTags.INGOTS_PLATINUM, ItemInit.PLATINUM_INGOT, ModMaterials.PLATINUM, MetalStats.PLATINUM, true, true));
    public static final WeaponMaterial REFINED_OBSIDIAN = register(new WeaponMaterial("refined_obsidian", ModItemTags.INGOTS_REFINED_OBSIDIAN, ItemInit.REFINED_OBSIDIAN_INGOT, ModMaterials.REFINED_OBSIDIAN, MetalStats.REFINED_OBSIDIAN, true, true));
    public static final WeaponMaterial SHADOW_PLATINUM = register(new WeaponMaterial("shadow_platinum", ModItemTags.INGOTS_SHADOW_PLATINUM, ItemInit.SHADOW_PLATINUM_INGOT, ModMaterials.SHADOW_PLATINUM, MetalStats.SHADOW_PLATINUM, true, true));
    public static final WeaponMaterial DUSKSTEEL = register(new WeaponMaterial("dusksteel", ModItemTags.INGOTS_DUSKSTEEL, ItemInit.DUSKSTEEL_INGOT, ModMaterials.DUSKSTEEL, MetalStats.DUSKSTEEL, true, true));
    public static final WeaponMaterial CRYSTALLITE = register(new WeaponMaterial("crystallite", ModItemTags.INGOTS_CRYSTALLITE, ItemInit.CRYSTALLITE_INGOT, ModMaterials.CRYSTALLITE, MetalStats.CRYSTALLITE, true, true));


    public static WeaponMaterial register(WeaponMaterial material) {
        LOOKUP.add(material);
        return material;
    }


    public TranslatableComponent getTranslationKey() {
        return new TranslatableComponent(AdvancedLootableWeapons.MODID + ".material." + name);
    }

    public static WeaponMaterial findMaterial(Item item) {
        for (WeaponMaterial weaponMaterial : LOOKUP) {
            if (item.builtInRegistryHolder().is(weaponMaterial.valid)) {
                return weaponMaterial;
            }
        }
        return NULL;
        //   return CACHE.computeIfAbsent(item,item1 -> {
        //        return LOOKUP.stream().filter(weaponMaterial -> item.builtInRegistryHolder().is(weaponMaterial.valid)).findFirst().orElse(NULL);
        //    });
    }
}
