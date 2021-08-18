package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AlwBlocksCreativeTab extends CreativeTabs{
	
	public AlwBlocksCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockInit.forge);
	}

}
