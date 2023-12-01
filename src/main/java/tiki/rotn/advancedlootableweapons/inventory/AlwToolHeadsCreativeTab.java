package tiki.rotn.advancedlootableweapons.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

public class AlwToolHeadsCreativeTab extends CreativeTabs{
	
	public AlwToolHeadsCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3);
	}

}
