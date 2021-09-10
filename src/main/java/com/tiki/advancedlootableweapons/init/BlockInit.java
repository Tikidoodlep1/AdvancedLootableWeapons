package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;
import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.BlockAlwOre;
import com.tiki.advancedlootableweapons.blocks.BlockBase;
import com.tiki.advancedlootableweapons.blocks.BlockCrystalliteOre;
import com.tiki.advancedlootableweapons.blocks.BlockFeldspar;
import com.tiki.advancedlootableweapons.blocks.BlockForge;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> blocks = new ArrayList<Block>();
	
	//Metals
	public static final Block block_steel = new BlockBase("block_steel", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_kobold = new BlockBase("block_kobold", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_tin = new BlockBase("block_tin", Material.IRON).setHardness(3.5F).setResistance(7.0F);
	public static final Block block_crystallite = new BlockBase("block_crystallite", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_platinum = new BlockBase("block_platinum", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_bronze = new BlockBase("block_bronze", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_frost_steel = new BlockBase("block_frost_steel", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_dusksteel = new BlockBase("block_dusksteel", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	public static final Block block_copper = new BlockBase("block_copper", Material.IRON).setHardness(3.5F).setResistance(7.0F);
	public static final Block block_silver = new BlockBase("block_silver", Material.IRON).setHardness(4.0F).setResistance(7.0F);
	public static final Block block_shadow_platinum = new BlockBase("block_shadow_platinum", Material.IRON).setHardness(5.0F).setResistance(7.0F);
	
	
	//Rocks
	public static final Block cobble_feldspar = new BlockFeldspar("cobble_feldspar").setHardness(1.5F).setResistance(6.0F);
	public static final Block rock_feldspar = new BlockFeldspar("rock_feldspar").setHardness(1.5F).setResistance(6.0F);
	
	
	//Ores
	public static final Block ore_silver = new BlockAlwOre("ore_silver").setHardness(1.6F).setResistance(3.0F);
	public static final Block ore_copper = new BlockAlwOre("ore_copper").setHardness(1.6F).setResistance(3.0F);
	public static final Block ore_platinum = new BlockAlwOre("ore_platinum").setHardness(1.7F).setResistance(3.0F);
	public static final Block ore_crystallite = new BlockCrystalliteOre("ore_crystallite").setHardness(1.8F).setResistance(3.5F);
	public static final Block ore_tin = new BlockAlwOre("ore_tin").setHardness(1.6F).setResistance(3.0F);
	
	
	//Functionals
	public static final Block alloy_furnace = new BlockAlloyFurnace("alloy_furnace").setHardness(2.7F).setResistance(5.0F);
	public static final Block forge = new BlockForge("forge").setHardness(2.0F).setResistance(3.7F);
}
