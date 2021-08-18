package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AlwToolHeadsCreativeTab extends CreativeTabs{
	
	public AlwToolHeadsCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3);
	}

}
