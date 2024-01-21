package tiki.rotn.advancedlootableweapons.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

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
													//8 = length, 12 = base, 3 = height, then convert to m^2 instead of cm^2
	public static final double SURFACE_AREA_CONST = (2*( (8*12) + (12*3) + (8*3) ))/100;
	public static final double HEAT_TRANSFER_CONST = 10.0D;
	
	public static final int BASIC_FORGE_TEMP = 1755;
	public static final int ADVANCED_FORGE_TEMP = 2250;
	public static final int ROOM_TEMP = 293;
	
	public static final int HOT_TOOL_HEAD_MAX_DUR = 6000;
	
	public static int getAvgTempForBiomeInC(Biome biome, BlockPos pos) {
		if(biome == null || pos == null) {
			return ROOM_TEMP-273;
		}
		return (int)((biome.getTemperature(pos) + 1.0f) * 25.0f); //max temperature of any vanilla biome (2.0F) devided by the maximum degrees C we want a biome to reach (50) == 25.
	}
	
	public static int getCurrentTempScaled(Item material, int currentDamage, int ambientTemp) {
		int MP = SHADOW_PLATINUM_MP;
		if(material == Items.IRON_INGOT) {
			MP = IRON_MP;
		}else if(material == Items.GOLD_INGOT) {
			MP = GOLD_MP;
		}else if(material == ItemInit.INGOT_COPPER) {
			MP = COPPER_MP;
		}else if(material == ItemInit.INGOT_SILVER) {
			MP = SILVER_MP;
		}else if(material == ItemInit.INGOT_BRONZE) {
			MP = BRONZE_MP;
		}else if(material == ItemInit.INGOT_PLATINUM) {
			MP = PLATINUM_MP;
		}else if(material == ItemInit.INGOT_STEEL) {
			MP = STEEL_MP;
		}else if(material == ItemInit.INGOT_OBSIDIAN) {
			MP = OBSIDIAN_STEEL_MP;
		}else if(material == ItemInit.INGOT_KOBOLD) {
			MP = KOBOLD_MP;
		}else if(material == ItemInit.INGOT_SHADOW_PLATINUM) {
			MP = SHADOW_PLATINUM_MP;
		}else if(material == ItemInit.INGOT_FROST_STEEL) {
			MP = FROST_STEEL_MP;
		}else if(material == ItemInit.INGOT_CRYSTALLITE) {
			MP = CRYSTALLITE_MP;
		}else if(material == ItemInit.INGOT_DUSKSTEEL) {
			MP = DUSKSTEEL_MP;
		}else if(material.getRegistryName().getResourcePath().contains("gem") || material.getRegistryName().getResourcePath().contains("diamond")) {
			MP = CRYSTALLITE_MP;
		}
						
		int damage = HOT_TOOL_HEAD_MAX_DUR - currentDamage == 0 ? 1 : HOT_TOOL_HEAD_MAX_DUR - currentDamage;
		int val = (int)((double)MP/((double)HOT_TOOL_HEAD_MAX_DUR/(double)damage))+ambientTemp;
		return val;//(val - 0) * (MP - ambientTemp) / ((MP - 0) + ambientTemp); // (input - min) * (outMax - outMin) / (max - min) + outMin
	}
	
	/**
	 * @param temp in K
	 * @param ambientTemp in K
	 */
	public static int getHeatGainLoss(Item material, int temp, int currentDamage, int ambientTemp) {
		//TC is legacy now :)
		int MP = SHADOW_PLATINUM_MP;
//		int TC = FROST_STEEL_TC;
		int SH = SILVER_SH;
		double IW = COPPER_IW;
		if(material == Items.IRON_INGOT) {
			MP = IRON_MP;
//			TC = IRON_TC;
			SH = IRON_SH;
			IW = IRON_IW;
		}else if(material == Items.GOLD_INGOT) {
			MP = GOLD_MP;
//			TC = GOLD_TC;
			SH = GOLD_SH;
			IW = GOLD_IW;
		}else if(material == ItemInit.INGOT_COPPER) {
			MP = COPPER_MP;
//			TC = COPPER_TC;
			SH = COPPER_SH;
			IW = COPPER_IW;
		}else if(material == ItemInit.INGOT_SILVER) {
			MP = SILVER_MP;
//			TC = SILVER_TC;
			SH = SILVER_SH;
			IW = SILVER_IW;
		}else if(material == ItemInit.INGOT_BRONZE) {
			MP = BRONZE_MP;
//			TC = BRONZE_TC;
			SH = BRONZE_SH;
			IW = BRONZE_IW;
		}else if(material == ItemInit.INGOT_PLATINUM) {
			MP = PLATINUM_MP;
//			TC = PLATINUM_TC;
			SH = PLATINUM_SH;
			IW = PLATINUM_IW;
		}else if(material == ItemInit.INGOT_STEEL) {
			MP = STEEL_MP;
//			TC = STEEL_TC;
			SH = STEEL_SH;
			IW = STEEL_IW;
		}else if(material == ItemInit.INGOT_OBSIDIAN) {
			MP = OBSIDIAN_STEEL_MP;
//			TC = OBSIDIAN_STEEL_TC;
			SH = OBSIDIAN_STEEL_SH;
			IW = OBSIDIAN_STEEL_IW;
		}else if(material == ItemInit.INGOT_KOBOLD) {
			MP = KOBOLD_MP;
//			TC = KOBOLD_TC;
			SH = KOBOLD_SH;
			IW = KOBOLD_IW;
		}else if(material == ItemInit.INGOT_SHADOW_PLATINUM) {
			MP = SHADOW_PLATINUM_MP;
//			TC = SHADOW_PLATINUM_TC;
			SH = SHADOW_PLATINUM_SH;
			IW = SHADOW_PLATINUM_IW;
		}else if(material == ItemInit.INGOT_FROST_STEEL) {
			MP = FROST_STEEL_MP;
//			TC = FROST_STEEL_TC;
			SH = FROST_STEEL_SH;
			IW = FROST_STEEL_IW;
		}else if(material == ItemInit.INGOT_CRYSTALLITE) {
			MP = CRYSTALLITE_MP;
//			TC = CRYSTALLITE_TC;
			SH = CRYSTALLITE_SH;
			IW = CRYSTALLITE_IW;
		}else if(material == ItemInit.INGOT_DUSKSTEEL) {
			MP = DUSKSTEEL_MP;
//			TC = DUSKSTEEL_TC;
			SH = DUSKSTEEL_SH;
			IW = DUSKSTEEL_IW;
		}else if(material.getRegistryName().getResourcePath().contains("gem") || material.getRegistryName().getResourcePath().contains("diamond")) {
			MP = CRYSTALLITE_MP;
//			TC = IRON_TC;
			SH = CRYSTALLITE_SH;
			IW = SHADOW_PLATINUM_IW;
		}
		
		int damage = HOT_TOOL_HEAD_MAX_DUR - currentDamage == 0 ? 1 : HOT_TOOL_HEAD_MAX_DUR - currentDamage;
		
//		int tempDiff = Math.abs( temp - (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) );
//		double tempGainLoss = Math.sqrt( ((TC * AREA_CONST) * (tempDiff/ DIST_CONST)) / (IW * SH * damage) );
		
		int val = (int)((double)MP/((double)HOT_TOOL_HEAD_MAX_DUR/(double)damage))+ambientTemp;
		double newTempDiff = temp - val;
		double wPerSec = HEAT_TRANSFER_CONST * SURFACE_AREA_CONST * newTempDiff;
		double newTempGainLoss = (wPerSec/(IW*SH))/20;
		
		Alw.logger.debug("getHeatGainLoss = " + material + ", " + currentDamage + ", " + ambientTemp);
		Alw.logger.debug("Temp Diff = " + newTempDiff + ", Temp Gain/Loss = " + newTempGainLoss + " for damage % " + val + " and temp " + temp);
		
//		int tempGainLossFinal = (int)(newTempGainLoss < 0.2 ? newTempGainLoss : Math.ceil(newTempGainLoss));
		return (int)newTempGainLoss; //tempGainLossFinal == 0 ? -1 : tempGainLossFinal;
	}
}
