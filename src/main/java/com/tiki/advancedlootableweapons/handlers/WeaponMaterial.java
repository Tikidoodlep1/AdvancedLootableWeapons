package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.ModMaterials;
import com.tiki.advancedlootableweapons.util.MetalStats;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.HashMap;
import java.util.Map;

public record WeaponMaterial(Tier tier, MetalStats metalStats) {

	public static final Map<String, WeaponMaterial> LOOKUP = new HashMap<>();
	public static final WeaponMaterial NULL = new WeaponMaterial(null, null);
	public static final WeaponMaterial STEEL = new WeaponMaterial(ModMaterials.MAT_STEEL,MetalStats.STEEL);

	static {
		LOOKUP.put("wood",new WeaponMaterial(Tiers.WOOD, null));
		LOOKUP.put("stone",new WeaponMaterial(Tiers.STONE, null));
		LOOKUP.put("copper",new WeaponMaterial(ModMaterials.MAT_COPPER,MetalStats.COPPER));
		LOOKUP.put("iron",new WeaponMaterial(Tiers.IRON,MetalStats.IRON));
		LOOKUP.put("gold",new WeaponMaterial(Tiers.GOLD,MetalStats.GOLD));
		LOOKUP.put("diamond",new WeaponMaterial(Tiers.DIAMOND,null));
		LOOKUP.put("netherite",new WeaponMaterial(Tiers.NETHERITE,null));
		LOOKUP.put("bronze",new WeaponMaterial(ModMaterials.MAT_BRONZE,MetalStats.BRONZE));
		LOOKUP.put("kobold_steel",new WeaponMaterial(ModMaterials.MAT_KOBOLD,MetalStats.KOBOLD));
		LOOKUP.put("silver",new WeaponMaterial(ModMaterials.MAT_SILVER,MetalStats.SILVER));
		LOOKUP.put("platinum",new WeaponMaterial(ModMaterials.MAT_PLATINUM,MetalStats.PLATINUM));
		LOOKUP.put("refined_obsidian",new WeaponMaterial(ModMaterials.MAT_REFINED_OBSIDIAN,MetalStats.REFINED_OBSIDIAN));
		LOOKUP.put("shadow_platinum",new WeaponMaterial(ModMaterials.MAT_SHADOW_PLATINUM,MetalStats.SHADOW_PLATINUM));
		LOOKUP.put("frost_steel",new WeaponMaterial(ModMaterials.MAT_FROST_STEEL,MetalStats.FROST_STEEL));
		LOOKUP.put("dusk_steel",new WeaponMaterial(ModMaterials.MAT_DUSKSTEEL,MetalStats.DUSKSTEEL));
		LOOKUP.put("crystallite",new WeaponMaterial(ModMaterials.MAT_CRYSTALLITE,MetalStats.CRYSTALLITE));
		LOOKUP.put("steel",STEEL);
	}

	public static TranslatableComponent getTranslationKey(String material) {
		return new TranslatableComponent(AdvancedLootableWeapons.MODID+".material."+material);
	}

	public static String getMaterialNameF(WeaponMaterial mat) {
		for(Map.Entry<String,WeaponMaterial> e : LOOKUP.entrySet()) {
			if(e.getValue() == mat) {
				return e.getKey();
			}
		}
		return "";
	}
}
