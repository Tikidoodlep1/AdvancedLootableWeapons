package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.ModMaterials;
import com.tiki.advancedlootableweapons.util.MetalStats;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.HashMap;
import java.util.Map;

public record WeaponMaterial(Tier tier, MetalStats metalStats, boolean canMakeWeapon) {

    public static final Map<String, WeaponMaterial> LOOKUP = new HashMap<>();
    public static final WeaponMaterial NULL = new WeaponMaterial(null, null, false);

    public static final WeaponMaterial STEEL = register("steel", new WeaponMaterial(ModMaterials.STEEL, MetalStats.STEEL, true));
    public static final WeaponMaterial FROST_STEEL = register("frost_steel", new WeaponMaterial(ModMaterials.FROST_STEEL, MetalStats.FROST_STEEL, true));
    public static final WeaponMaterial WOOD = register("wood", new WeaponMaterial(Tiers.WOOD, null, true));
    public static final WeaponMaterial STONE = register("stone", new WeaponMaterial(Tiers.STONE, null, false));
    public static final WeaponMaterial COPPER = register("copper", new WeaponMaterial(ModMaterials.COPPER, MetalStats.COPPER, true));
    public static final WeaponMaterial IRON = register("iron", new WeaponMaterial(Tiers.IRON, MetalStats.IRON, true));
    public static final WeaponMaterial GOLD = register("gold", new WeaponMaterial(Tiers.GOLD, MetalStats.GOLD, false));
    public static final WeaponMaterial DIAMOND = register("diamond", new WeaponMaterial(Tiers.DIAMOND, null, false));
    public static final WeaponMaterial NETHERITE = register("netherite", new WeaponMaterial(Tiers.NETHERITE, null, false));
    public static final WeaponMaterial BRONZE = register("bronze", new WeaponMaterial(ModMaterials.BRONZE, MetalStats.BRONZE, true));
    public static final WeaponMaterial KOBOLD_STEEL = register("kobold_steel", new WeaponMaterial(ModMaterials.KOBOLD_STEEL, MetalStats.KOBOLD_STEEL, true));
    public static final WeaponMaterial SILVER = register("silver", new WeaponMaterial(ModMaterials.SILVER, MetalStats.SILVER, true));
    public static final WeaponMaterial PLATINUM = register("platinum", new WeaponMaterial(ModMaterials.PLATINUM, MetalStats.PLATINUM, true));
    public static final WeaponMaterial REFINED_OBSIDIAN = register("refined_obsidian", new WeaponMaterial(ModMaterials.REFINED_OBSIDIAN, MetalStats.REFINED_OBSIDIAN, true));
    public static final WeaponMaterial SHADOW_PLATINUM = register("shadow_platinum", new WeaponMaterial(ModMaterials.SHADOW_PLATINUM, MetalStats.SHADOW_PLATINUM, true));
    public static final WeaponMaterial DUSKSTEEL = register("dusksteel", new WeaponMaterial(ModMaterials.DUSKSTEEL, MetalStats.DUSKSTEEL, true));
    public static final WeaponMaterial CRYSTALLITE = register("crystallite", new WeaponMaterial(ModMaterials.CRYSTALLITE, MetalStats.CRYSTALLITE, true));


    public static WeaponMaterial register(String name, WeaponMaterial material) {
        LOOKUP.put(name, material);
        return material;
    }


    public static TranslatableComponent getTranslationKey(String material) {
        return new TranslatableComponent(AdvancedLootableWeapons.MODID + ".material." + material);
    }

    public static WeaponMaterial getByName(String name) {
        return LOOKUP.getOrDefault(name,NULL);
    }

    public static String getMaterialNameF(WeaponMaterial mat) {
        for (Map.Entry<String, WeaponMaterial> e : LOOKUP.entrySet()) {
            if (e.getValue() == mat) {
                return e.getKey();
            }
        }
        return "";
    }
}
