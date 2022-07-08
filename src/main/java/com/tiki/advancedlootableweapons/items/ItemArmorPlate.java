package com.tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorPlate extends ItemBase {

	public ItemArmorPlate(String name) {
		super(name);
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		NBTTagCompound Stacknbt = stack.getTagCompound();
		
		if(stack.hasTagCompound() && Stacknbt.hasKey("addedDurability")) {
			tooltip.add(TextFormatting.BLUE + "Forging Quality");
			tooltip.add(TextFormatting.GRAY + "--------------------");
			tooltip.add(TextFormatting.BLUE + "+" + Stacknbt.getInteger("addedDurability")/4 + " Durability");
			tooltip.add(TextFormatting.GRAY + "--------------------");
		}
    }
}
