package com.tiki.advancedlootableweapons.util;

public class HotMetalHelper {
	
	public static final int IRON_TC = 94;
	public static final int GOLD_TC = 327;
	public static final int COPPER_TC = 413;
	public static final int SILVER_TC = 403;
	public static final int BRONZE_TC = 26;
	public static final int PLATINUM_TC = 72;
	public static final int STEEL_TC = 59;
	public static final int OBSIDIAN_STEEL_TC = 52;
	public static final int KOBOLD_TC = 183;
	public static final int SHADOW_PLATINUM_TC = 124;
	public static final int FROST_STEEL_TC = 158;
	public static final int CRYSTALLITE_TC = 284;
	public static final int DUSKSTEEL_TC = 27;

	public static final int IRON_MP = 1811;
	public static final int GOLD_MP = 1337;
	public static final int COPPER_MP = 1357;
	public static final int SILVER_MP = 1234;
	public static final int BRONZE_MP = 1127;
	public static final int PLATINUM_MP = 2041;
	public static final int STEEL_MP = 1753;
	public static final int OBSIDIAN_STEEL_MP = 1578;
	public static final int KOBOLD_MP = 487;
	public static final int SHADOW_PLATINUM_MP = 1463;
	public static final int FROST_STEEL_MP = 1442;
	public static final int CRYSTALLITE_MP = 2864;
	public static final int DUSKSTEEL_MP = 1582;
	
	public static final int IRON_SH = 460;
	public static final int GOLD_SH = 129;
	public static final int COPPER_SH = 376;
	public static final int SILVER_SH = 238;
	public static final int BRONZE_SH = 376;
	public static final int PLATINUM_SH = 125;
	public static final int STEEL_SH = 502;
	public static final int OBSIDIAN_STEEL_SH = 837;
	public static final int KOBOLD_SH = 284;
	public static final int SHADOW_PLATINUM_SH = 627;
	public static final int FROST_STEEL_SH = 318;
	public static final int CRYSTALLITE_SH = 882;
	public static final int DUSKSTEEL_SH = 402;
	
	public static final double IRON_IW = 1.13;
	public static final double GOLD_IW = 2.78;
	public static final double COPPER_IW = 1.29;
	public static final double SILVER_IW = 1.51;
	public static final double BRONZE_IW = 2.82;
	public static final double PLATINUM_IW = 3.09;
	public static final double STEEL_IW = 1.16;
	public static final double OBSIDIAN_STEEL_IW = 0.35;
	public static final double KOBOLD_IW = 1.22;
	public static final double SHADOW_PLATINUM_IW = 2.52;
	public static final double FROST_STEEL_IW = 1.40;
	public static final double CRYSTALLITE_IW = 2.28;
	public static final double DUSKSTEEL_IW = 1.26;
	
	public static final double AREA_CONST = 2.4D;
	public static final double DIST_CONST = 0.5D;
	
	public static final int BASIC_FORGE_TEMP = 1755;
	public static final int ADVANCED_FORGE_TEMP = 2250;
	public static final int ROOM_TEMP = 293;
	
	public static final int HOT_TOOL_HEAD_MAX_DUR = 6000;
	
	public static int getHeatGainLoss(String material, int temp, int currentDamage) {
		int MP = SHADOW_PLATINUM_MP;
		int TC = FROST_STEEL_TC;
		int SH = SILVER_SH;
		double IW = COPPER_IW;
		switch(material) {
		case "Iron":
			MP = IRON_MP;
			TC = IRON_TC;
			SH = IRON_SH;
			IW = IRON_IW;
			break;
		case "Gold":
			MP = GOLD_MP;
			TC = GOLD_TC;
			SH = GOLD_SH;
			IW = GOLD_IW;
			break;
		case "Copper":
			MP = COPPER_MP;
			TC = COPPER_TC;
			SH = COPPER_SH;
			IW = COPPER_IW;
			break;
		case "Silver":
			MP = SILVER_MP;
			TC = SILVER_TC;
			SH = SILVER_SH;
			IW = SILVER_IW;
			break;
		case "Bronze":
			MP = BRONZE_MP;
			TC = BRONZE_TC;
			SH = BRONZE_SH;
			IW = BRONZE_IW;
			break;
		case "Platinum":
			MP = PLATINUM_MP;
			TC = PLATINUM_TC;
			SH = PLATINUM_SH;
			IW = PLATINUM_IW;
			break;
		case "Steel":
			MP = STEEL_MP;
			TC = STEEL_TC;
			SH = STEEL_SH;
			IW = STEEL_IW;
			break;
		case "Obsidian":
			MP = OBSIDIAN_STEEL_MP;
			TC = OBSIDIAN_STEEL_TC;
			SH = OBSIDIAN_STEEL_SH;
			IW = OBSIDIAN_STEEL_IW;
			break;
		case "Kobold":
			MP = KOBOLD_MP;
			TC = KOBOLD_TC;
			SH = KOBOLD_SH;
			IW = KOBOLD_IW;
			break;
		case "Shadow Platinum":
			MP = SHADOW_PLATINUM_MP;
			TC = SHADOW_PLATINUM_TC;
			SH = SHADOW_PLATINUM_SH;
			IW = SHADOW_PLATINUM_IW;
			break;
		case "Frost Steel":
			MP = FROST_STEEL_MP;
			TC = FROST_STEEL_TC;
			SH = FROST_STEEL_SH;
			IW = FROST_STEEL_IW;
			break;
		case "Crystallite":
			MP = CRYSTALLITE_MP;
			TC = CRYSTALLITE_TC;
			SH = CRYSTALLITE_SH;
			IW = CRYSTALLITE_IW;
			break;
		case "Dusksteel":
			MP = DUSKSTEEL_MP;
			TC = DUSKSTEEL_TC;
			SH = DUSKSTEEL_SH;
			IW = DUSKSTEEL_IW;
			break;
		}
		int damage = currentDamage == 0 ? 1 : currentDamage;
		int tempDiff = temp > (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) ? temp - (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) : (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) - temp;
		
		return (int) Math.max(Math.ceil(Math.sqrt( ((TC * AREA_CONST) * (tempDiff/ DIST_CONST) / (IW * SH * damage)) )), 1);
	}
}
