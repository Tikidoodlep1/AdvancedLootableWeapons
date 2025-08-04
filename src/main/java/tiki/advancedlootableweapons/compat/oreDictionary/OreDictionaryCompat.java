package tiki.advancedlootableweapons.compat.oreDictionary;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.init.ItemInit;

public class OreDictionaryCompat {

	public static void registerOres() {
		
		Alw.logger.info("Registering OreDictionary");
		
		registerOre(ZenDynamicAlwResources.IGNITION_ORE, Items.FLINT_AND_STEEL);
		registerOre(ZenDynamicAlwResources.IGNITION_UPGRADE_ORE, Items.BLAZE_POWDER);
		
		registerOre("ingotCopper", ItemInit.INGOT_COPPER);
		registerOre("ingotSilver", ItemInit.INGOT_SILVER);
		registerOre("ingotBronze", ItemInit.INGOT_BRONZE);
		registerOre("ingotPlatinum", ItemInit.INGOT_PLATINUM);
		registerOre("ingotSteel", ItemInit.INGOT_STEEL);
		registerOre("ingotRefinedObsidian", ItemInit.INGOT_OBSIDIAN);
		registerOre("ingotTin", ItemInit.INGOT_TIN);
		
		registerOre("oreCopper", BlockInit.ore_copper);
		registerOre("oreSilver", BlockInit.ore_silver);
		registerOre("oreTin", BlockInit.ore_tin);
		registerOre("orePlatinum", BlockInit.ore_platinum);
		
		registerOre("blockCopper", BlockInit.block_copper);
		registerOre("blockSilver", BlockInit.block_silver);
		registerOre("blockBronze", BlockInit.block_bronze);
		registerOre("blockPlatinum", BlockInit.block_platinum);
		registerOre("blockSteel", BlockInit.block_steel);
		
		registerOre("nuggetCopper", ItemInit.NUGGET_COPPER);
		registerOre("nuggetSilver", ItemInit.NUGGET_SILVER);
		registerOre("nuggetBronze", ItemInit.NUGGET_BRONZE);
		registerOre("nuggetPlatinum", ItemInit.NUGGET_PLATINUM);
		registerOre("nuggetSteel", ItemInit.NUGGET_STEEL);
		registerOre("nuggetRefinedObsidian", ItemInit.NUGGET_OBSIDIAN);
		registerOre("nuggetTin", ItemInit.NUGGET_TIN);
		
		registerOre("shardObsidian", ItemInit.SHARD_OBSIDIAN);
		registerOre("dustSalt", ItemInit.RAW_SALT);
		
		registerOre("armorBinding", ItemInit.LEATHER_BINDING);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_BRONZE);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_COPPER);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_CRYSTALLITE);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_DUSKSTEEL);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_FROST_STEEL);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_GOLD);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_IRON);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_KOBOLD);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_OBSIDIAN);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_PLATINUM);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_SHADOW_PLATINUM);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_SILVER);
		registerOre("armorBinding", ItemInit.CHAIN_BINDING_STEEL);
	}
	
	private static void registerOre(String oreName, Item item) {
		if(item == null ||  item == Items.AIR) {
			Alw.logger.error("Tried to register item " + item.getRegistryName() + " to ore " + oreName + " but found that it's null!");
		}
		OreDictionary.registerOre(oreName, item);
	}
	
	private static void registerOre(String oreName, Block block) {
		if(block == null ||  block == Blocks.AIR) {
			Alw.logger.error("Tried to register item " + block.getRegistryName() + " to ore " + oreName + " but found that it's null!");
		}
		OreDictionary.registerOre(oreName, block);
	}
	
}
