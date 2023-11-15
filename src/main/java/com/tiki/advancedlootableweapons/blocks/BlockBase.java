package com.tiki.advancedlootableweapons.blocks;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material, String toolClass, int harvestLevel, boolean shouldRegister) 
	{
		super(material);
		if(shouldRegister) {
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(Alw.AlwBlocksTab);
			this.setHarvestLevel(toolClass, harvestLevel);
			
			BlockInit.blocks.add(this);
			ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
		}
	}
	
	public BlockBase(String name, Material material, String toolClass, int harvestLevel) 
	{
		this(name, material, toolClass, harvestLevel, true);
	}

	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}
