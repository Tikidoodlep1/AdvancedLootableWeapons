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

public record WeaponMaterial(Tier tier, MetalStats metalStats,boolean canMakeWeapon) {

	public static final Map<String, WeaponMaterial> LOOKUP = new HashMap<>();
	public static final WeaponMaterial NULL = new WeaponMaterial(null, null,false);
	public static final WeaponMaterial STEEL = new WeaponMaterial(ModMaterials.MAT_STEEL,MetalStats.STEEL,true);

	static {
		LOOKUP.put("wood",new WeaponMaterial(Tiers.WOOD, null,true));
		LOOKUP.put("stone",new WeaponMaterial(Tiers.STONE, null,false));
		LOOKUP.put("copper",new WeaponMaterial(ModMaterials.MAT_COPPER,MetalStats.COPPER,true));
		LOOKUP.put("iron",new WeaponMaterial(Tiers.IRON,MetalStats.IRON,true));
		LOOKUP.put("gold",new WeaponMaterial(Tiers.GOLD,MetalStats.GOLD,false));
		LOOKUP.put("diamond",new WeaponMaterial(Tiers.DIAMOND,null,false));
		LOOKUP.put("netherite",new WeaponMaterial(Tiers.NETHERITE,null,true));
		LOOKUP.put("bronze",new WeaponMaterial(ModMaterials.MAT_BRONZE,MetalStats.BRONZE,true));
		LOOKUP.put("kobold_steel",new WeaponMaterial(ModMaterials.MAT_KOBOLD,MetalStats.KOBOLD,true));
		LOOKUP.put("silver",new WeaponMaterial(ModMaterials.MAT_SILVER,MetalStats.SILVER,true));
		LOOKUP.put("platinum",new WeaponMaterial(ModMaterials.MAT_PLATINUM,MetalStats.PLATINUM,true));
		LOOKUP.put("refined_obsidian",new WeaponMaterial(ModMaterials.MAT_REFINED_OBSIDIAN,MetalStats.REFINED_OBSIDIAN,true));
		LOOKUP.put("shadow_platinum",new WeaponMaterial(ModMaterials.MAT_SHADOW_PLATINUM,MetalStats.SHADOW_PLATINUM,true));
		LOOKUP.put("frost_steel",new WeaponMaterial(ModMaterials.MAT_FROST_STEEL,MetalStats.FROST_STEEL,true));
		LOOKUP.put("dusk_steel",new WeaponMaterial(ModMaterials.MAT_DUSKSTEEL,MetalStats.DUSKSTEEL,true));
		LOOKUP.put("crystallite",new WeaponMaterial(ModMaterials.MAT_CRYSTALLITE,MetalStats.CRYSTALLITE,true));
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
