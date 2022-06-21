package com.tiki.advancedlootableweapons.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabs {
	public static final CreativeModeTab MISC_TAB = new CreativeModeTab("alwTab") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.INGOT_FROST_STEEL.get());
		}
		
	};
	
	public static final CreativeModeTab WEAPON_TAB = new CreativeModeTab("alwWeapons") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.SABRE_HEAD.get());
		}
		
	};
	
	public static final CreativeModeTab BLOCK_TAB = new CreativeModeTab("alwBlocks") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlockInit.BLOCK_BRICK_DIORITE.get());
		}
		
	};
}
