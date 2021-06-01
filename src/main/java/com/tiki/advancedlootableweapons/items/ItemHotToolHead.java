
package com.tiki.advancedlootableweapons.items;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemHotToolHead extends Item implements IHasModel{
	
	public ItemHotToolHead(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.setMaxDamage(6000);
		this.maxStackSize = 1;
		this.canRepair = false;
		this.setHasSubtypes(true);
		this.addPropertyOverride(new ResourceLocation("damage"), new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				int i = stack.getMetadata();
		        if(i <= 3001) {
		        	return 0;
		        }else if(i >= 3000 && i <= 5999) {
		        	return 1;
		        }else {
		        	return 2;
		        }
			}
		});
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
        }else if(i >= 3000 && i <= 4999) {
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
}
