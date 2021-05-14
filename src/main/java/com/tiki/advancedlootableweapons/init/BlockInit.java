package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	public static final List<Block> blocks = new ArrayList<Block>();
	
	//metals
	public static final Block block_steel = new BlockBase("block_steel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_kobold = new BlockBase("block_kobold", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_tin = new BlockBase("block_tin", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_crystallite = new BlockBase("block_crystallite", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_platinum = new BlockBase("block_platinum", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_bronze = new BlockBase("block_bronze", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_frost_steel = new BlockBase("block_frost_steel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_dusksteel = new BlockBase("block_dusksteel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_copper = new BlockBase("block_copper", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_silver = new BlockBase("block_silver", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block block_shadow_platinum = new BlockBase("block_shadow_platinum", Material.IRON, CreativeTabs.MATERIALS);
	
	//ores
	public static final Block block_ore_silver = new BlockBase("block_ore_silver", Material.ROCK, CreativeTabs.MATERIALS);
	public static final Block block_ore_copper = new BlockBase("block_ore_copper", Material.ROCK, CreativeTabs.MATERIALS);
	public static final Block block_ore_platinum = new BlockBase("block_ore_platinum", Material.ROCK, CreativeTabs.MATERIALS);
	public static final Block block_ore_crystallite = new BlockBase("block_ore_crystallite", Material.ROCK, CreativeTabs.MATERIALS);
	public static final Block block_ore_tin = new BlockBase("block_ore_tin", Material.ROCK, CreativeTabs.MATERIALS);
	
	//misc
	public static final Block alloy_furnace = new BlockAlloyFurnace("alloy_furnace");
}
