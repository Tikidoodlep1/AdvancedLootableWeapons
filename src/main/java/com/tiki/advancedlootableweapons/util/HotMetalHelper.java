package com.tiki.advancedlootableweapons.util;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.world.item.Item;

public class HotMetalHelper {

	public static final double AREA_CONST = 2.4D;

	public static final int BASIC_FORGE_TEMP = 1755;
	public static final int ADVANCED_FORGE_TEMP = 2250;
	public static final int ROOM_TEMP = 293;

	public static final double HEAT_TRANSFER_CONST = 10.0D;


	/**
	 * @param containerTemp in K
	 * @param ambientTemp in K
	 */
	public static double getHeatGainLoss(Item material, int containerTemp, int itemTemp, int ambientTemp) {

		WeaponMaterial weaponMaterial = WeaponMaterial.findMaterial(material);
		MetalStats metalStats = weaponMaterial.metalStats();

		if (metalStats == null) return 0;//not a heatable material

		//TC is legacy now :)
		int MP = metalStats.mp();
//		int TC = FROST_STEEL_TC;
		int SH = metalStats.sh();
		double IW = metalStats.iw();

		int damage = itemTemp == 0 ? 1 : itemTemp;

//		int tempDiff = Math.abs( containerTemp - (MP/(HeatableToolPartItem.MAX_HEAT/damage)) );
//		double tempGainLoss = Math.sqrt( ((TC * AREA_CONST) * (tempDiff/ DIST_CONST)) / (IW * SH * damage) );

		int val = (int)((double)MP/((double)HeatableToolPartItem.MAX_HEAT/(double)damage))+ambientTemp;
		double newTempDiff = containerTemp - val;
		double wPerSec = HEAT_TRANSFER_CONST * AREA_CONST * newTempDiff;
		double newTempGainLoss = (wPerSec/(IW*SH))/20;

	//	AdvancedLootableWeapons.LOGGER.debug("getHeatGainLoss = " + material + ", " + itemTemp + ", " + ambientTemp);
		//AdvancedLootableWeapons.LOGGER.debug("Temp Diff = " + newTempDiff + ", Temp Gain/Loss = " + newTempGainLoss + " for itemTemp " + val + " and containerTemp " + containerTemp);

//		int tempGainLossFinal = (int)(newTempGainLoss < 0.2 ? newTempGainLoss : Math.ceil(newTempGainLoss));
		return newTempGainLoss; //tempGainLossFinal == 0 ? -1 : tempGainLossFinal;
	}

	public static int getForgeHeatLoss(int temp) {
		//return 40;
		return (int)((ROOM_TEMP / (Math.sqrt(temp)))*5);
	}
}