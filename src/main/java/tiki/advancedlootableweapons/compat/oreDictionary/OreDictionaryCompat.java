package tiki.advancedlootableweapons.compat.oreDictionary;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.init.ItemInit;

public class OreDictionaryCompat {
	
	public static final String INGOT_COPPER = "ingotCopper";
	public static final String INGOT_SILVER = "ingotSilver";
	public static final String INGOT_BRONZE = "ingotBronze";
	public static final String INGOT_PLATINUM = "ingotPlatinum";
	public static final String INGOT_STEEL = "ingotSteel";
	public static final String INGOT_OBSIDIAN = "ingotRefinedObsidian";
	public static final String INGOT_TIN = "ingotTin";
	
	public static final String ORE_COPPER = "oreCopper";
	public static final String ORE_SILVER = "oreSilver";
	public static final String ORE_TIN = "oreTin";
	public static final String ORE_PLATINUM = "orePlatinum";
	
	public static final String BLOCK_COPPER = "blockCopper";
	public static final String BLOCK_SILVER = "blockSilver";
	public static final String BLOCK_BRONZE = "blockBronze";
	public static final String BLOCK_PLATINUM = "blockPlatinum";
	public static final String BLOCK_STEEL = "blockSteel";
	
	public static final String NUGGET_COPPER = "nuggetCopper";
	public static final String NUGGET_SILVER = "nuggetSilver";
	public static final String NUGGET_BRONZE = "nuggetBronze";
	public static final String NUGGET_PLATINUM = "nuggetPlatinum";
	public static final String NUGGET_STEEL = "nuggetSteel";
	public static final String NUGGET_OBSIDIAN = "nuggetRefinedObsidian";
	public static final String NUGGET_TIN = "nuggetTin";
	
	public static final String BALL_CLAY = "ballClay";
	public static final String SHARD_OBSIDIAN = "shardObsidian";
	public static final String DUST_SALT = "dustSalt";
	public static final String DUST_GRANITE = "dustGranite";
	public static final String DUST_DIORITE = "dustDiorite";
	public static final String DUST_FELDSPAR = "dustFeldspar";
	public static final String DUST_COAL = "dustCoal";
	public static final String DUST_CHARCOAL = "dustCharcoal";
	
	public static final String DUST_CLAY_BASE = "dustClayBase";
	public static final String ARMOR_BINDING = "armorBinding";
	public static final String VALID_FORGE_HAMMER = "itemAlwForgeHammer";
	public static final String VALID_ANVIL = "blockAlwAnvil";
	
	public static void registerOres() {
		
		Alw.logger.info("Registering OreDictionary");
		
		OreDictionary.registerOre(ZenDynamicAlwResources.IGNITION_ORE, Items.FLINT_AND_STEEL);
		OreDictionary.registerOre(ZenDynamicAlwResources.IGNITION_UPGRADE_ORE, Items.BLAZE_POWDER);
		
		OreDictionary.registerOre(INGOT_COPPER, ItemInit.INGOT_COPPER);
		OreDictionary.registerOre(INGOT_SILVER, ItemInit.INGOT_SILVER);
		OreDictionary.registerOre(INGOT_BRONZE, ItemInit.INGOT_BRONZE);
		OreDictionary.registerOre(INGOT_PLATINUM, ItemInit.INGOT_PLATINUM);
		OreDictionary.registerOre(INGOT_STEEL, ItemInit.INGOT_STEEL);
		OreDictionary.registerOre(INGOT_OBSIDIAN, ItemInit.INGOT_OBSIDIAN);
		OreDictionary.registerOre(INGOT_TIN, ItemInit.INGOT_TIN);
		
		OreDictionary.registerOre(ORE_COPPER, BlockInit.ore_copper);
		OreDictionary.registerOre(ORE_SILVER, BlockInit.ore_silver);
		OreDictionary.registerOre(ORE_TIN, BlockInit.ore_tin);
		OreDictionary.registerOre(ORE_PLATINUM, BlockInit.ore_platinum);
		
		OreDictionary.registerOre(BLOCK_COPPER, BlockInit.block_copper);
		OreDictionary.registerOre(BLOCK_SILVER, BlockInit.block_silver);
		OreDictionary.registerOre(BLOCK_BRONZE, BlockInit.block_bronze);
		OreDictionary.registerOre(BLOCK_PLATINUM, BlockInit.block_platinum);
		OreDictionary.registerOre(BLOCK_STEEL, BlockInit.block_steel);
		
		OreDictionary.registerOre(NUGGET_COPPER, ItemInit.NUGGET_COPPER);
		OreDictionary.registerOre(NUGGET_SILVER, ItemInit.NUGGET_SILVER);
		OreDictionary.registerOre(NUGGET_BRONZE, ItemInit.NUGGET_BRONZE);
		OreDictionary.registerOre(NUGGET_PLATINUM, ItemInit.NUGGET_PLATINUM);
		OreDictionary.registerOre(NUGGET_STEEL, ItemInit.NUGGET_STEEL);
		OreDictionary.registerOre(NUGGET_OBSIDIAN, ItemInit.NUGGET_OBSIDIAN);
		OreDictionary.registerOre(NUGGET_TIN, ItemInit.NUGGET_TIN);
		
		OreDictionary.registerOre(BALL_CLAY, ItemInit.CLAY_GRANITE);
		OreDictionary.registerOre(BALL_CLAY, ItemInit.CLAY_DIORITE);
		OreDictionary.registerOre(SHARD_OBSIDIAN, ItemInit.SHARD_OBSIDIAN);
		OreDictionary.registerOre(DUST_SALT, ItemInit.RAW_SALT);
		OreDictionary.registerOre(DUST_GRANITE, ItemInit.POWDER_GRANITE);
		OreDictionary.registerOre(DUST_DIORITE, ItemInit.POWDER_DIORITE);
		OreDictionary.registerOre(DUST_FELDSPAR, ItemInit.POWDER_FELDSPAR);
		OreDictionary.registerOre(DUST_COAL, ItemInit.POWDER_CHARCOAL);
		OreDictionary.registerOre(DUST_CHARCOAL, ItemInit.POWDER_CHARCOAL);
		
		OreDictionary.registerOre(DUST_CLAY_BASE, ItemInit.POWDER_GRANITE);
		OreDictionary.registerOre(DUST_CLAY_BASE, ItemInit.POWDER_FELDSPAR);
		
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.LEATHER_BINDING);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_BRONZE);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_COPPER);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_CRYSTALLITE);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_DUSKSTEEL);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_FROST_STEEL);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_GOLD);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_IRON);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_KOBOLD);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_OBSIDIAN);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_PLATINUM);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_SHADOW_PLATINUM);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_SILVER);
		OreDictionary.registerOre(ARMOR_BINDING, ItemInit.CHAIN_BINDING_STEEL);
	}
	
	public static void registerConfigDependentOres() {
		
		Alw.logger.info("Registering valid Forge Hammers and valid Anvils to OreDictionary");
		Long startTime = System.currentTimeMillis();
		
		IForgeRegistry<Item> itemRegistry = ForgeRegistries.ITEMS;
		IForgeRegistry<Block> blockRegistry = ForgeRegistries.BLOCKS;
		
		for(String itemId : ConfigHandler.VALID_HAMMERS) {
			ResourceLocation itemLoc = new ResourceLocation(itemId);
			if(itemRegistry.containsKey(itemLoc)) {
				OreDictionary.registerOre(VALID_FORGE_HAMMER, itemRegistry.getValue(itemLoc));
			}else {
				Alw.logger.warn("Forge hammer {" + itemId + "} from Block and Item Config is an invalid item id! Item id's must be in the format ResourceDomain:ResourcePath, ex. \"minecraft:stick\"\nItem will not be registered as a valid forge hammer for " + ModInfo.NAME + ".");
			}
		}
		
		for(String blockId : ConfigHandler.VALID_ANVILS) {
			ResourceLocation blockLoc = new ResourceLocation(blockId);
			if(blockRegistry.containsKey(blockLoc)) {
				OreDictionary.registerOre(VALID_ANVIL, blockRegistry.getValue(blockLoc));
			}else {
				Alw.logger.warn("Anvil {" + blockId + "} from Block and Item Config is an invalid block id! Block id's must be in the format ResourceDomain:ResourcePath, ex. \"minecraft:dirt\"\nBlock will not be registered as a valid anvil for " + ModInfo.NAME + ".");
			}
		}
		
		Alw.logger.info("Finished registering valid Forge Hammers (Size " + ConfigHandler.VALID_HAMMERS.size() + ") and valid Anvils (Size " + ConfigHandler.VALID_ANVILS.size() + ") to OreDictionary in " + (System.currentTimeMillis() - startTime) + "ms.");
	}
}
