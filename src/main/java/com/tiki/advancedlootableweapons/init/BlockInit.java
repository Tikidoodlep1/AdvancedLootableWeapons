package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;
import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.BlockAlwOre;
import com.tiki.advancedlootableweapons.blocks.BlockBase;
import com.tiki.advancedlootableweapons.blocks.BlockBellows;
import com.tiki.advancedlootableweapons.blocks.BlockDrum;
import com.tiki.advancedlootableweapons.blocks.BlockOreDropItem;
import com.tiki.advancedlootableweapons.blocks.BlockDusksteel;
import com.tiki.advancedlootableweapons.blocks.BlockFeldspar;
import com.tiki.advancedlootableweapons.blocks.BlockForge;
import com.tiki.advancedlootableweapons.blocks.BlockForge2;
import com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder;
import com.tiki.advancedlootableweapons.blocks.BlockGypsum;
import com.tiki.advancedlootableweapons.blocks.BlockJawCrusher;
import com.tiki.advancedlootableweapons.blocks.BlockPowderedClay;
import com.tiki.advancedlootableweapons.blocks.BlockQuartzClay;
import com.tiki.advancedlootableweapons.blocks.BlockTanningRack;
import com.tiki.advancedlootableweapons.blocks.fluids.BlockAlwFluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> blocks = new ArrayList<Block>();
	
	//Metals
	public static final Block block_steel = new BlockBase("block_steel", Material.IRON).setHardness(5.0F).setResistance(11.0F);
	public static final Block block_kobold = new BlockBase("block_kobold", Material.IRON).setHardness(5.0F).setResistance(8.0F);
	public static final Block block_tin = new BlockBase("block_tin", Material.IRON).setHardness(3.5F).setResistance(7.0F);
	public static final Block block_crystallite = new BlockBase("block_crystallite", Material.IRON).setHardness(5.0F).setResistance(17.0F);
	public static final Block block_platinum = new BlockBase("block_platinum", Material.IRON).setHardness(5.0F).setResistance(12.0F);
	public static final Block block_bronze = new BlockBase("block_bronze", Material.IRON).setHardness(5.0F).setResistance(11.0F);
	public static final Block block_frost_steel = new BlockBase("block_frost_steel", Material.IRON).setHardness(5.0F).setResistance(13.0F);
	public static final Block block_dusksteel = new BlockDusksteel("block_dusksteel", Material.IRON).setHardness(5.0F).setResistance(12.0F);
	public static final Block block_copper = new BlockBase("block_copper", Material.IRON).setHardness(3.5F).setResistance(8.0F);
	public static final Block block_silver = new BlockBase("block_silver", Material.IRON).setHardness(4.0F).setResistance(9.0F);
	public static final Block block_shadow_platinum = new BlockBase("block_shadow_platinum", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_obsidian = new BlockBase("block_obsidian_steel", Material.IRON).setHardness(5.5F).setResistance(28.0F);
	
	
	//Rocks & Clays
	public static final Block cobble_feldspar = new BlockFeldspar("cobble_feldspar").setHardness(1.5F).setResistance(2.8F);
	public static final Block rock_feldspar = new BlockFeldspar("rock_feldspar").setHardness(1.5F).setResistance(3.0F);
	public static final Block powder_granite = new BlockPowderedClay("block_powder_granite").setHardness(0.5F);
	public static final Block powder_diorite = new BlockPowderedClay("block_powder_diorite").setHardness(0.5F);
	public static final Block clay_granite = new BlockQuartzClay("block_clay_granite", ItemInit.CLAY_GRANITE).setHardness(1.2F).setResistance(2.5F);
	public static final Block clay_diorite = new BlockQuartzClay("block_clay_diorite", ItemInit.CLAY_DIORITE).setHardness(1.2F).setResistance(2.5F);
	public static final Block brick_granite = new BlockBase("block_brick_granite", Material.ROCK).setHardness(2.0F).setResistance(10.0F);
	public static final Block brick_diorite = new BlockBase("block_brick_diorite", Material.ROCK).setHardness(2.0F).setResistance(10.0F);
	public static final Block gypsum = new BlockGypsum("block_gypsum", Material.ROCK).setHardness(0.8F).setResistance(2.0F);
	public static final Block dolomite = new BlockBase("block_dolomite", Material.ROCK).setHardness(1.55F).setResistance(3.1F);
	
	
	//Ores
	public static final Block ore_silver = new BlockAlwOre("ore_silver", 2).setHardness(1.6F).setResistance(3.0F);
	public static final Block ore_copper = new BlockAlwOre("ore_copper", 2).setHardness(1.6F).setResistance(3.0F);
	public static final Block ore_platinum = new BlockAlwOre("ore_platinum", 3).setHardness(1.7F).setResistance(3.0F);
	public static final Block ore_crystallite = new BlockOreDropItem("ore_crystallite", ItemInit.CRYSTAL, 1, 3, 3).setHardness(1.8F).setResistance(3.5F);
	public static final Block ore_tin = new BlockAlwOre("ore_tin", 2).setHardness(1.5F).setResistance(3.0F);
	public static final Block ore_salt = new BlockOreDropItem("ore_salt", ItemInit.RAW_SALT, 2, 6, 1).setHardness(1.3F).setResistance(2.6F);
	
	
	//Functionals
	public static final Block alloy_furnace = new BlockAlloyFurnace("alloy_furnace").setHardness(2.7F).setResistance(5.0F);
	public static final Block forge = new BlockForge("forge").setHardness(2.0F).setResistance(3.7F);
	public static final Block forge2 = new BlockForge2("advanced_forge").setHardness(3.0F).setResistance(4.7F);
	public static final Block forge2_1 = new BlockForge2Placeholder("advanced_forge_1").setHardness(3.0F).setResistance(4.7F);
	public static final Block crusher = new BlockJawCrusher("jaw_crusher", Material.IRON).setHardness(2.5F).setResistance(7.0F);
	public static final Block tanning_rack = new BlockTanningRack("tanning_rack").setHardness(1.3F);
	public static final Block drum = new BlockDrum("drum").setHardness(2.0F).setResistance(5.5F);
	public static final Block bellows_oak = new BlockBellows("bellows_oak").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_birch = new BlockBellows("bellows_birch").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_spruce = new BlockBellows("bellows_spruce").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_jungle = new BlockBellows("bellows_jungle").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_acacia = new BlockBellows("bellows_acacia").setHardness(1.5F).setResistance(1.8F);
	public static final Block bellows_dark_oak = new BlockBellows("bellows_dark_oak").setHardness(1.5F).setResistance(1.8F);
	
	//Fluids
	public static final Block milk_of_lime = new BlockAlwFluid("milk_of_lime", FluidInit.MILK_OF_LIME, Material.WATER, MapColor.WHITE_STAINED_HARDENED_CLAY);
	public static final Block magnesium_lactate = new BlockAlwFluid("magnesium_lactate", FluidInit.MAGNESIUM_LACTATE, Material.WATER, MapColor.WHITE_STAINED_HARDENED_CLAY);
}
