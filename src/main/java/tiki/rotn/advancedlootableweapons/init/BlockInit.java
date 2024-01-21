package tiki.rotn.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;
import tiki.rotn.advancedlootableweapons.blocks.BlockAlloyFurnace;
import tiki.rotn.advancedlootableweapons.blocks.BlockAlwOre;
import tiki.rotn.advancedlootableweapons.blocks.BlockBase;
import tiki.rotn.advancedlootableweapons.blocks.BlockBellows;
import tiki.rotn.advancedlootableweapons.blocks.BlockDrum;
import tiki.rotn.advancedlootableweapons.blocks.BlockDusksteel;
import tiki.rotn.advancedlootableweapons.blocks.BlockFeldspar;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge2;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge2Placeholder;
import tiki.rotn.advancedlootableweapons.blocks.BlockGypsum;
import tiki.rotn.advancedlootableweapons.blocks.BlockMill;
import tiki.rotn.advancedlootableweapons.blocks.BlockOreDropItem;
import tiki.rotn.advancedlootableweapons.blocks.BlockPowderedClay;
import tiki.rotn.advancedlootableweapons.blocks.BlockQuartzClay;
import tiki.rotn.advancedlootableweapons.blocks.BlockTanningRack;
import tiki.rotn.advancedlootableweapons.blocks.fluids.BlockAlwFluid;

public class BlockInit {
	public static final List<Block> blocks = new ArrayList<Block>();
	
	//Metals
	public static final Block block_steel = new BlockBase("block_steel", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(11.0F);
	public static final Block block_kobold = new BlockBase("block_kobold", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(8.0F);
	public static final Block block_tin = new BlockBase("block_tin", Material.IRON, "pickaxe", 1).setHardness(3.5F).setResistance(7.0F);
	public static final Block block_crystallite = new BlockBase("block_crystallite", Material.IRON, "pickaxe", 3).setHardness(5.0F).setResistance(17.0F);
	public static final Block block_platinum = new BlockBase("block_platinum", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(12.0F);
	public static final Block block_bronze = new BlockBase("block_bronze", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(11.0F);
	public static final Block block_frost_steel = new BlockBase("block_frost_steel", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(13.0F);
	public static final Block block_dusksteel = new BlockDusksteel("block_dusksteel", Material.IRON).setHardness(5.0F).setResistance(12.0F);
	public static final Block block_copper = new BlockBase("block_copper", Material.IRON, "pickaxe", 1).setHardness(3.5F).setResistance(8.0F);
	public static final Block block_silver = new BlockBase("block_silver", Material.IRON, "pickaxe", 1).setHardness(4.0F).setResistance(9.0F);
	public static final Block block_shadow_platinum = new BlockBase("block_shadow_platinum", Material.IRON, "pickaxe", 2).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_obsidian = new BlockBase("block_obsidian_steel", Material.IRON, "pickaxe", 3).setHardness(5.5F).setResistance(28.0F);
	
	
	//Rocks & Clays
	public static final Block cobble_feldspar = new BlockFeldspar("cobble_feldspar").setHardness(1.7F).setResistance(2.8F);
	public static final Block rock_feldspar = new BlockFeldspar("rock_feldspar").setHardness(1.8F).setResistance(3.0F);
	public static final Block powder_granite = new BlockPowderedClay("block_powder_granite").setHardness(0.5F);
	public static final Block powder_diorite = new BlockPowderedClay("block_powder_diorite").setHardness(0.5F);
	public static final Block clay_granite = new BlockQuartzClay("block_clay_granite", ItemInit.CLAY_GRANITE).setHardness(1.2F).setResistance(2.5F);
	public static final Block clay_diorite = new BlockQuartzClay("block_clay_diorite", ItemInit.CLAY_DIORITE).setHardness(1.2F).setResistance(2.5F);
	public static final Block brick_granite = new BlockBase("block_brick_granite", Material.ROCK, "pickaxe", 0).setHardness(2.0F).setResistance(10.0F);
	public static final Block brick_diorite = new BlockBase("block_brick_diorite", Material.ROCK, "pickaxe", 0).setHardness(2.0F).setResistance(10.0F);
	public static final Block gypsum = new BlockGypsum("block_gypsum", Material.ROCK).setHardness(1.5F).setResistance(2.0F);
	public static final Block dolomite = new BlockBase("block_dolomite", Material.ROCK, "pickaxe", 0).setHardness(1.7F).setResistance(3.1F);
	
	
	//Ores
	public static final Block ore_silver = new BlockAlwOre("block_ore_silver", 2).setHardness(2.6F).setResistance(3.0F);
	public static final Block ore_copper = new BlockAlwOre("block_ore_copper", 1).setHardness(1.9F).setResistance(3.0F);
	public static final Block ore_platinum = new BlockAlwOre("block_ore_platinum", 2).setHardness(3.2F).setResistance(3.0F);
	public static final Block ore_crystallite = new BlockOreDropItem("block_ore_crystallite", ItemInit.CRYSTAL, 1, 3, 3).setHardness(3.8F).setResistance(3.5F);
	public static final Block ore_end_crystallite = new BlockOreDropItem("block_ore_end_crystallite", ItemInit.CRYSTAL, 1, 3, 3).setHardness(4.6F).setResistance(3.5F);
	public static final Block ore_tin = new BlockAlwOre("block_ore_tin", 1).setHardness(1.5F).setResistance(3.0F);
	public static final Block ore_salt = new BlockOreDropItem("block_ore_salt", ItemInit.RAW_SALT, 2, 6, 1).setHardness(1.3F).setResistance(2.6F);
	
	
	//Functionals
	public static final Block alloy_furnace = new BlockAlloyFurnace("block_alloy_furnace").setHardness(2.7F).setResistance(5.0F);
	public static final Block forge = new BlockForge("block_forge", Material.ROCK, SoundType.STONE, true).setHardness(2.9F).setResistance(3.7F);
	public static final Block forge2 = new BlockForge2("block_advanced_forge", Material.ROCK, SoundType.STONE, true).setHardness(3.2F).setResistance(4.7F);
	public static final Block forge2_1 = new BlockForge2Placeholder("block_advanced_forge_extra").setHardness(3.0F).setResistance(4.7F);
	public static final Block mill = new BlockMill("block_mill", Material.IRON).setHardness(2.5F).setResistance(7.0F);
	public static final Block tanning_rack = new BlockTanningRack("block_tanning_rack").setHardness(1.3F);
	public static final Block drum = new BlockDrum("block_drum").setHardness(2.0F).setResistance(5.5F);
	
	public static final Block bellows = new BlockBellows("block_bellows").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_birch = new BlockBellows("block_bellows_birch").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_spruce = new BlockBellows("block_bellows_spruce").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_jungle = new BlockBellows("block_bellows_jungle").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_acacia = new BlockBellows("block_bellows_acacia").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_dark_oak = new BlockBellows("block_bellows_dark_oak").setHardness(1.5F).setResistance(1.8F);
	
	//public static final Block forge_fuel = new BlockForgeFuel("block_forge_fuel", Material.ROCK, SoundType.STONE, "pickaxe", 0, true, getItemFuels(), new HashSet<Item>(), 1.0F);
	//public static final Block forge2_fuel = new BlockForge2Fuel("block_advanced_forge_fuel", Material.ROCK, SoundType.STONE, "pickaxe", 1, true, getItemFuels(), new HashSet<Item>(), 1.0F);
	//public static final Block mill2 = new BlockMill2("mill2", Material.ROCK); //Legacy model test
	
	//Fluids
	public static final Block milk_of_lime = new BlockAlwFluid("milk_of_lime", FluidInit.MILK_OF_LIME, Material.WATER, MapColor.WHITE_STAINED_HARDENED_CLAY);
	public static final Block magnesium_lactate = new BlockAlwFluid("magnesium_lactate", FluidInit.MAGNESIUM_LACTATE, Material.WATER, MapColor.WHITE_STAINED_HARDENED_CLAY);
	public static final Block water_block_display = new BlockAlwFluid("water_display", FluidRegistry.WATER, Material.WATER, MapColor.WATER);
	public static final Block lava_block_display = new BlockAlwFluid("lava_display", FluidRegistry.LAVA, Material.LAVA, MapColor.RED_STAINED_HARDENED_CLAY);
	
//	private static Set<Item> getItemFuels() {
//		HashSet<Item> fuels = new HashSet<Item>();
//		fuels.add(Items.COAL);
//		fuels.add(Items.LAVA_BUCKET);
//		fuels.add(Item.getItemFromBlock(Blocks.COAL_BLOCK));
//		return fuels;
//	}
}
