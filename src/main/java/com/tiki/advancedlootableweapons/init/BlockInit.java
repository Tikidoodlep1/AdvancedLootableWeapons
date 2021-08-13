package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.BlockBase;
import com.tiki.advancedlootableweapons.blocks.BlockForge;
import com.tiki.advancedlootableweapons.blocks.BlockAlwOre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	public static final List<Block> blocks = new ArrayList<Block>();
	
	//metals
	public static final Block steel = new BlockBase("steel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block kobold = new BlockBase("kobold", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block tin = new BlockBase("tin", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block crystallite = new BlockBase("crystallite", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block platinum = new BlockBase("platinum", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block bronze = new BlockBase("bronze", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block frost_steel = new BlockBase("frost_steel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block dusksteel = new BlockBase("dusksteel", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block copper = new BlockBase("copper", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block silver = new BlockBase("silver", Material.IRON, CreativeTabs.MATERIALS);
	public static final Block shadow_platinum = new BlockBase("shadow_platinum", Material.IRON, CreativeTabs.MATERIALS);
	
	//ores
	public static final Block ore_silver = new BlockAlwOre("ore_silver");
	public static final Block ore_copper = new BlockAlwOre("ore_copper");
	public static final Block ore_platinum = new BlockAlwOre("ore_platinum");
	public static final Block ore_crystallite = new BlockAlwOre("ore_crystallite", ItemInit.CRYSTAL, 2, 3);
	public static final Block ore_tin = new BlockAlwOre("ore_tin");
	
	//misc
	public static final Block alloy_furnace = new BlockAlloyFurnace("alloy_furnace").setHardness(1.7F).setResistance(1.8F);
	public static final Block forge = new BlockForge("forge").setHardness(1.6F).setResistance(1.7F);
}
