package com.tiki.advancedlootableweapons.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
	public static final CreativeModeTab MISC_TAB = new CreativeModeTab("alw_tab") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.FROST_STEEL_INGOT.get());
		}
		
	};
	
	public static final CreativeModeTab WEAPON_TAB = new CreativeModeTab("alw_weapons") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.SABRE_HEAD.get());
		}
		
	};
	
	public static final CreativeModeTab BLOCK_TAB = new CreativeModeTab("alw_blocks") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlockInit.BLOCK_BRICK_DIORITE.get());
		}
		
	};
}
