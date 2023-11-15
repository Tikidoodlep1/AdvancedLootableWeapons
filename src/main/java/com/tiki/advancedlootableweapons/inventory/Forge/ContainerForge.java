package com.tiki.advancedlootableweapons.inventory.Forge;

import java.util.HashSet;
import java.util.Set;

import com.tiki.advancedlootableweapons.blocks.BlockForge;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeContent;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
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

public class ContainerForge extends Container
{
	private final TileEntityForge tileentity;
	private int temp = 0;
	private final Set<Item> heatableMaterials;
	
	public ContainerForge(InventoryPlayer player, TileEntityForge tileentity) 
	{
		this.tileentity = tileentity;
		if(tileentity.getBlockType() instanceof BlockForge) {
			this.heatableMaterials = ((BlockForge)tileentity.getBlockType()).acceptedMaterials;
		}else if(tileentity.getBlockType() instanceof BlockForgeContent) {
			this.heatableMaterials = ((BlockForgeContent)tileentity.getBlockType()).getRepresentation().getMatList();
		}else {
			heatableMaterials = new HashSet<Item>();
		}
		
		this.addSlotToContainer(new Slot(tileentity, 0, 80, 48) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					if(ContainerForge.this.heatableMaterials.size() == 0) {
						return true;
					}
					
					if(stack.hasTagCompound()) {
						NBTTagCompound tag = stack.getTagCompound();
						if(tag.hasKey("Material")) {
							ItemStack matStack = new ItemStack(tag.getCompoundTag("Material"));
							for(Item i : heatableMaterials) {
								if(matStack.getItem() == i) {
									return true;
								}
							}
						}
					}
				}
				return false;
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
		}
		
		this.temp = this.tileentity.getField(1);
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
            
            if (index == 0)
            {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0)
            {
                if (index >= 1 && index < 37 && !this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
                //playerIn.inventoryContainer.getSlot(index).decrStackSize(64);
            }
            else if (!this.mergeItemStack(itemstack1, 1, 37, false))
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
