package com.tiki.advancedlootableweapons.handlers;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public enum EnumMaterialType {
	
	WOOD(Tiers.WOOD, "Wood"),
	STONE(Tiers.STONE, "Stone"),
	KOBOLD_STEEL(null, "Kobold Steel"),
	GOLD(Tiers.GOLD, "Gold"),
	COPPER(null, "Copper"),
	SILVER(null, "Silver"),
	IRON(Tiers.IRON, "Iron"),
	BRONZE(null, "Bronze"),
	PLATINUM(null, "Platinum"),
	STEEL(null, "Steel"),
	DIAMOND(Tiers.DIAMOND, "Diamond"),
	SHADOW_PLATINUM(null, "Shadow Platinum"),
	FROST_STEEL(null, "Frost Steel"),
	OBSIDIAN(null, "Obsidian"),
	NETHERITE(Tiers.NETHERITE, "Netherite"),
	CRYSTALLITE(null, "Crystallite"),
	DUSKSTEEL(null, "Dusksteel"),
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
