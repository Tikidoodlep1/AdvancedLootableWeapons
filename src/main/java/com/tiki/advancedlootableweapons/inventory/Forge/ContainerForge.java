package com.tiki.advancedlootableweapons.inventory.Forge;

import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForge extends Container
{
	private final TileEntityForge tileentity;
	
	public ContainerForge(InventoryPlayer player, TileEntityForge tileentity) 
	{
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 81, 36) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					return true;
				}else {
					return false;
				}
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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) 
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 3) 
			{
				if(!this.mergeItemStack(stack1, 4, 35, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 2 && index != 1 && index != 0) 
			{
				Slot slot1 = (Slot)this.inventorySlots.get(index);
				if(index + 1 < this.inventorySlots.size()) {
					slot1 = (Slot)this.inventorySlots.get(index + 1);
				}
				
				if(!slot1.getHasStack())
				{
					if(!this.mergeItemStack(stack1, 0, 2, false)) 
					{
						return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31)
					{
						if(!this.mergeItemStack(stack1, 31, 35, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
					{
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.mergeItemStack(stack1, 4, 35, false)) 
			{
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
}
