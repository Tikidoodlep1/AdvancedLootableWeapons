package com.tiki.advancedlootableweapons.compat.oreDictionary;

import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {

	public static void registerOres() {
		OreDictionary.registerOre("ingotCopper", ItemInit.INGOT_COPPER);
		OreDictionary.registerOre("ingotSilver", ItemInit.INGOT_SILVER);
		OreDictionary.registerOre("ingotBronze", ItemInit.INGOT_BRONZE);
		OreDictionary.registerOre("ingotPlatinum", ItemInit.INGOT_PLATINUM);
		OreDictionary.registerOre("ingotSteel", ItemInit.INGOT_STEEL);
		OreDictionary.registerOre("ingotCopper", ItemInit.SHADOW_BLOB);
		
		OreDictionary.registerOre("oreCopper", BlockInit.ore_copper);
		OreDictionary.registerOre("oreSilver", BlockInit.ore_silver);
		OreDictionary.registerOre("oreTin", BlockInit.ore_tin);
		OreDictionary.registerOre("orePlatinum", BlockInit.ore_platinum);
	}
}
