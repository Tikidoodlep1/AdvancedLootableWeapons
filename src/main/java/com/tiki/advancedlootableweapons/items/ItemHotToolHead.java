
package com.tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHotToolHead extends Item implements IHasModel{
	NBTTagCompound nbt = new NBTTagCompound();
	
	public ItemHotToolHead(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwToolHeadsTab);
		
		ItemInit.items.add(this);
		
		this.setMaxDamage(6000);
		this.maxStackSize = 1;
		this.canRepair = false;
		this.setHasSubtypes(true);
		this.addPropertyOverride(new ResourceLocation("damage"), new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				int i = stack.getMetadata();
		        if(i <= 2999) {
		        	return 0;
		        }else if(i >= 2999 && i <= 4999) {
		        	return 1;
		        }else {
		        	return 2;
		        }
			}
		});
	}
	
	public String getMaterial(ItemStack toolHead) {
		NBTTagCompound tag = new NBTTagCompound();
		tag = toolHead.getTagCompound();
		if(toolHead.hasTagCompound() && tag.hasKey("Material")) {
			return tag.getString("Material");
		}else {
			return "Material not found";
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		nbt = stack.getTagCompound();
		
		if(stack.hasTagCompound() && nbt.hasKey("Material")) {
			tooltip.add(TextFormatting.BLUE + nbt.getString("Material"));
		}
		
		if(stack.hasTagCompound() && nbt.hasKey("addedDamage")) {
			tooltip.add(TextFormatting.BLUE + "Forging Quality");
			tooltip.add(TextFormatting.GRAY + "--------------------");
			tooltip.add(TextFormatting.BLUE + "+" + nbt.getDouble("addedDamage") + " Damage");
			tooltip.add(TextFormatting.BLUE + "+" + nbt.getInteger("addedDurability") + " Durability");
			tooltip.add(TextFormatting.GRAY + "--------------------");
		}
    }
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		int damage = stack.getMetadata();
		if(damage <= 5999) {
			this.setDamage(stack, damage + 1);
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        if(i <= 2999) {
        	return super.getUnlocalizedName() + ".hot";
        }else if(i >= 2999 && i <= 4999) {
        	return super.getUnlocalizedName() + ".warm";
        }else {
        	return super.getUnlocalizedName() + ".cool";
        }
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
        	
        	items.add(new ItemStack(this, 1, 6000));
        	items.add(new ItemStack(this, 1, 3000));
        	items.add(new ItemStack(this, 1, 0));
        }
    }
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
}
