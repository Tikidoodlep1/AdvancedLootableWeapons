package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.init.ModMaterials;
import com.tiki.advancedlootableweapons.util.MetalStats;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.HashMap;
import java.util.Map;

public record WeaponMaterial(Tier tier, MetalStats metalStats) {

	public static final Map<String, WeaponMaterial> LOOKUP = new HashMap<>();
	public static final WeaponMaterial NULL = new WeaponMaterial(null, null);
	public static final WeaponMaterial STEEL = new WeaponMaterial(ModMaterials.MAT_STEEL,MetalStats.STEEL);

	static {
		LOOKUP.put("Wood",new WeaponMaterial(Tiers.WOOD, null));
		LOOKUP.put("Stone",new WeaponMaterial(Tiers.STONE, null));
		LOOKUP.put("Copper",new WeaponMaterial(ModMaterials.MAT_COPPER,MetalStats.COPPER));
		LOOKUP.put("Iron",new WeaponMaterial(Tiers.IRON,MetalStats.IRON));
		LOOKUP.put("Gold",new WeaponMaterial(Tiers.GOLD,MetalStats.GOLD));
		LOOKUP.put("Diamond",new WeaponMaterial(Tiers.DIAMOND,null));
		LOOKUP.put("Netherite",new WeaponMaterial(Tiers.NETHERITE,null));
		LOOKUP.put("Bronze",new WeaponMaterial(ModMaterials.MAT_BRONZE,MetalStats.BRONZE));
		LOOKUP.put("Kobold_Steel",new WeaponMaterial(ModMaterials.MAT_KOBOLD,MetalStats.KOBOLD));
		LOOKUP.put("Silver",new WeaponMaterial(ModMaterials.MAT_SILVER,MetalStats.SILVER));
		LOOKUP.put("Platinum",new WeaponMaterial(ModMaterials.MAT_PLATINUM,MetalStats.PLATINUM));
		LOOKUP.put("Refined_Obsidian",new WeaponMaterial(ModMaterials.MAT_REFINED_OBSIDIAN,MetalStats.REFINED_OBSIDIAN));
		LOOKUP.put("Shadow_Platinum",new WeaponMaterial(ModMaterials.MAT_SHADOW_PLATINUM,MetalStats.SHADOW_PLATINUM));
		LOOKUP.put("Frost_Steel",new WeaponMaterial(ModMaterials.MAT_FROST_STEEL,MetalStats.FROST_STEEL));
		LOOKUP.put("Dusk_Steel",new WeaponMaterial(ModMaterials.MAT_DUSKSTEEL,MetalStats.DUSKSTEEL));
		LOOKUP.put("Crystallite",new WeaponMaterial(ModMaterials.MAT_CRYSTALLITE,MetalStats.CRYSTALLITE));

		LOOKUP.put("Steel",STEEL);

		LOOKUP.put("null",NULL);
	}

	public static String getMaterialNameF(WeaponMaterial mat) {
		for(Map.Entry<String,WeaponMaterial> e : LOOKUP.entrySet()) {
			if(e.getValue() == mat) {
				return e.getKey();
			}
		}
		return "null";
	}
}
