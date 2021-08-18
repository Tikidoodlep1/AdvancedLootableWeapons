package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AlwCreativeTab extends CreativeTabs{
	
	public AlwCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE);
	}

}
