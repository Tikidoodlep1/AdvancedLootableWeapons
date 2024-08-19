package com.tiki.advancedlootableweapons.util;

import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;

public class HotMetalHelper {

	public static final double AREA_CONST = 2.4D;
	public static final double DIST_CONST = 0.5D;

	public static final int BASIC_FORGE_TEMP = 1755;
	public static final int ADVANCED_FORGE_TEMP = 2250;
	public static final int ROOM_TEMP = 293;

	public static final int HOT_TOOL_HEAD_MAX_DUR = 6000;

	public static int getHeatGainLoss(String material, int temp, int currentDamage) {

		WeaponMaterial weaponMaterial = WeaponMaterial.LOOKUP.getOrDefault(material,WeaponMaterial.NULL);

		MetalStats metalStats = weaponMaterial.metalStats();

		if (metalStats == null) return 0;//not a heatable material

		int MP = metalStats.mp();
		int TC = metalStats.tc();
		int SH = metalStats.sh();
		double IW = metalStats.iw();

		int damage = currentDamage == 0 ? 1 : currentDamage;
		int tempDiff = temp > (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) ? temp - (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) : (MP/(HOT_TOOL_HEAD_MAX_DUR/damage)) - temp;

		return (int) Math.max(Math.ceil(Math.sqrt( ((TC * AREA_CONST) * (tempDiff/ DIST_CONST) / (IW * SH * damage)) )), 1);
	}

	public static int getForgeHeatLoss(int temp) {
		//return 40;
		return (int)((ROOM_TEMP / (Math.sqrt(temp)))*5);
	}
}