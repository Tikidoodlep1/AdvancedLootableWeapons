package com.tiki.advancedlootableweapons.inventory.Forge;

import java.util.Set;

import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForgeFuel extends Container
{
	private final TileEntityForge tileentity;
	private int temp = 0;
	private int maxBurnTime = 0;
	private int burnTime = 0;
	private final Set<Item> heatableMaterials;
	private final Set<Item> fuelList;
	
	public ContainerForgeFuel(InventoryPlayer player, TileEntityForge tileentity) 
	{
		this.tileentity = tileentity;
		this.heatableMaterials = ZenDynamicAlwResources.getMatListForBlock(tileentity.getBlock());
		this.fuelList = ZenDynamicAlwResources.getFuelListForBlock(tileentity.getBlock());
		
		this.addSlotToContainer(new Slot(tileentity, 0, 80, 36) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					if(ContainerForgeFuel.this.heatableMaterials == null || ContainerForgeFuel.this.heatableMaterials.size() == 0) {
						return true;
					}
					
					if(stack.hasTagCompound()) {
						NBTTagCompound tag = stack.getTagCompound();
						if(tag.hasKey("Material")) {
							ItemStack matStack = new ItemStack(tag.getCompoundTag("Material"));
							return ContainerForgeFuel.this.heatableMaterials.contains(matStack.getItem());
						}
					}
				}
				return false;
			}
		});
		
		this.addSlotToContainer(new Slot(tileentity, 1, 80, 59) {
			public boolean isItemValid(ItemStack stack) {
				if(ContainerForgeFuel.this.fuelList == null) {
					return true;
				}
				
				return ContainerForgeFuel.this.fuelList.contains(stack.getItem());
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
		
		for(int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.temp != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.burnTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.maxBurnTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.temp = this.tileentity.getField(1);
		this.burnTime = this.tileentity.getField(2);
		this.maxBurnTime = this.tileentity.getField(3);
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
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (index <= 1)
            {
                if (!this.mergeItemStack(itemstack1, 2, 37, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index > 1 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 0, 2, false))
                {
                    return ItemStack.EMPTY;
                }
                //playerIn.inventoryContainer.getSlot(index).decrStackSize(64);
            }
            else if (!this.mergeItemStack(itemstack1, 2, 37, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
