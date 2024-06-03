package tiki.advancedlootableweapons.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.init.ItemInit;

public class AlwCreativeTab extends CreativeTabs{
	
	public AlwCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE);
	}

}
