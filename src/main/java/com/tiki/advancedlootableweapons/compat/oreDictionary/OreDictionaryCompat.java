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
		OreDictionary.registerOre("ingotRefinedObsidian", ItemInit.INGOT_OBSIDIAN);
		OreDictionary.registerOre("ingotTin", ItemInit.INGOT_TIN);
		
		OreDictionary.registerOre("oreCopper", BlockInit.ore_copper);
		OreDictionary.registerOre("oreSilver", BlockInit.ore_silver);
		OreDictionary.registerOre("oreTin", BlockInit.ore_tin);
		OreDictionary.registerOre("orePlatinum", BlockInit.ore_platinum);
		
		OreDictionary.registerOre("blockCopper", BlockInit.block_copper);
		OreDictionary.registerOre("blockSilver", BlockInit.block_silver);
		OreDictionary.registerOre("blockBronze", BlockInit.block_bronze);
		OreDictionary.registerOre("blockPlatinum", BlockInit.block_platinum);
		OreDictionary.registerOre("blockSteel", BlockInit.block_steel);
		
		OreDictionary.registerOre("nuggetCopper", ItemInit.NUGGET_COPPER);
		OreDictionary.registerOre("nuggetSilver", ItemInit.NUGGET_SILVER);
		OreDictionary.registerOre("nuggetBronze", ItemInit.NUGGET_BRONZE);
		OreDictionary.registerOre("nuggetPlatinum", ItemInit.NUGGET_PLATINUM);
		OreDictionary.registerOre("nuggetSteel", ItemInit.NUGGET_STEEL);
		OreDictionary.registerOre("nuggetRefinedObsidian", ItemInit.NUGGET_OBSIDIAN);
		OreDictionary.registerOre("nuggetTin", ItemInit.NUGGET_TIN);
		
		OreDictionary.registerOre("shardObsidian", ItemInit.SHARD_OBSIDIAN);
		OreDictionary.registerOre("dustSalt", ItemInit.RAW_SALT);
	}
}
