package com.tiki.advancedlootableweapons.inventory.AlloyFurnace;

import java.util.Random;

import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAlloyFurnace extends Container
{
	private final TileEntityAlloyFurnace tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	private final Random rand = new Random();
	
	public ContainerAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace tileentity) 
	{
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 45, 17) {
			public boolean isItemValid(ItemStack stack) {
				return !(stack.getItem().isDamageable());
			}
			
			@Override
			public void onSlotChanged() {
				ContainerAlloyFurnace.this.tileentity.findNewRecipe();
				super.onSlotChanged();
			}
		});
		
		this.addSlotToContainer(new Slot(tileentity, 1, 68, 17) {
			public boolean isItemValid(ItemStack stack) {
				return !(stack.getItem().isDamageable());
			}
			
			@Override
			public void onSlotChanged() {
				ContainerAlloyFurnace.this.tileentity.findNewRecipe();
				super.onSlotChanged();
			}
		});
		
		this.addSlotToContainer(new Slot(tileentity, 2, 57, 54) {
			public boolean isItemValid(ItemStack stack) {
				return TileEntityAlloyFurnace.isItemFuel(stack);
			}
		});
		
		this.addSlotToContainer(new Slot(tileentity, 3, 116, 36) {
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
			
			public ItemStack onTake(EntityPlayer player, ItemStack stack) {
				int exp = (int)Math.ceil(ContainerAlloyFurnace.this.tileentity.recipeExp * stack.getCount());
				player.addExperience(exp);
				player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F + (rand.nextFloat() / 2F));
				ContainerAlloyFurnace.this.tileentity.recipeExp = 0;
				return stack;
			}
		});
		
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener){
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.burnTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentBurnTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCookTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2 || index == 3){
                if (!this.mergeItemStack(itemstack1, 4, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
                
            }else if (index != 0 && index != 1) {
                if (index >= 4 && index <= 39 && !this.mergeItemStack(itemstack1, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            }else if (!this.mergeItemStack(itemstack1, 4, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack);
        }
        return itemstack;
    }
}
