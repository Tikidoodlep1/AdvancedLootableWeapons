package tiki.advancedlootableweapons.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemUnboundArmor extends ItemBase {

	public ItemUnboundArmor(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound Stacknbt = stack.getTagCompound();
		
		if(stack.hasTagCompound() && Stacknbt.hasKey("addedDurability")) {
			tooltip.add(TextFormatting.BLUE + new TextComponentTranslation("alw.forging_quality.name").getFormattedText());
			tooltip.add(TextFormatting.GRAY + "--------------------");
			tooltip.add(TextFormatting.BLUE + "+" + Stacknbt.getInteger("addedDurability")/4 + new TextComponentTranslation("alw.dur_tooltip.name").getFormattedText());
			tooltip.add(TextFormatting.GRAY + "--------------------");
		}
	}

}
