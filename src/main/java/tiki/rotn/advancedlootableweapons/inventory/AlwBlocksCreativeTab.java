package tiki.rotn.advancedlootableweapons.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class AlwBlocksCreativeTab extends CreativeTabs {
	
	public AlwBlocksCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockInit.forge);
	}

}
