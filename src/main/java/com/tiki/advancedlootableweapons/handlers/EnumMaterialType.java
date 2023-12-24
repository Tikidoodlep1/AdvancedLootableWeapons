package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.init.ModMaterials;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public enum EnumMaterialType {
	
	WOOD(Tiers.WOOD, "Wood"),
	STONE(Tiers.STONE, "Stone"),
	KOBOLD_STEEL(ModMaterials.MAT_KOBOLD, "Kobold Steel"),
	GOLD(Tiers.GOLD, "Gold"),
	COPPER(ModMaterials.MAT_COPPER, "Copper"),
	SILVER(ModMaterials.MAT_SILVER, "Silver"),
	IRON(Tiers.IRON, "Iron"),
	BRONZE(ModMaterials.MAT_BRONZE, "Bronze"),
	PLATINUM(ModMaterials.MAT_PLATINUM, "Platinum"),
	STEEL(ModMaterials.MAT_STEEL, "Steel"),
	DIAMOND(Tiers.DIAMOND, "Diamond"),
	SHADOW_PLATINUM(ModMaterials.MAT_SHADOW_PLATINUM, "Shadow Platinum"),
	FROST_STEEL(ModMaterials.MAT_FROST_STEEL, "Frost Steel"),
	OBSIDIAN(ModMaterials.MAT_OBSIDIAN, "Obsidian"),
	NETHERITE(Tiers.NETHERITE, "Netherite"),
	CRYSTALLITE(ModMaterials.MAT_CRYSTALLITE, "Crystallite"),
	DUSKSTEEL(ModMaterials.MAT_DUSKSTEEL, "Dusksteel"),
	NULL_MAT(null, "null");

	private final Tier mat;
	private final String matName;
	private final int matOrdinal;
	
	EnumMaterialType(Tier material, String materialName) {
		this.mat = material;
		this.matName = materialName;
		this.matOrdinal = this.ordinal();
	}
	
	public static String getMaterialNameF(Tier mat) {
		for(EnumMaterialType e : EnumMaterialType.values()) {
			if(e.mat == mat) {
				return e.matName;
			}
		}
		return NULL_MAT.matName;
	}
	
	public Tier getMaterial() {
		return this.mat;
	}
	
	public String getMaterialNameF() {
		return this.matName;
	}
	
	public String getMaterialNameUnformatted() {
		return this.mat.toString();
	}
	
	public int getMaterialOrdinal() {
		return this.matOrdinal;
	}
}
