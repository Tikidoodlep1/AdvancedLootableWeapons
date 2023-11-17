package com.tiki.advancedlootableweapons.inventory.Forge2;

import java.util.HashSet;
import java.util.Set;

import com.tiki.advancedlootableweapons.blocks.BlockForge2Fuel;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2FuelContent;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.block.Block;
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

public class ContainerForge2Fuel extends Container
{
	private final TileEntityForge2 tileentity;
	private int temp = 0;
	private int burnTime = 0;
	private int maxBurnTime = 0;
	private final Set<Item> heatableMaterials;
	
	public ContainerForge2Fuel(InventoryPlayer player, TileEntityForge2 tileEntityForge2) 
	{
		this.tileentity = tileEntityForge2;
		if(tileentity.getBlockType() instanceof BlockForge2Fuel) {
			this.heatableMaterials = ((BlockForge2Fuel)tileentity.getBlockType()).acceptedMaterials;
		}else if(tileentity.getBlockType() instanceof BlockForge2FuelContent) {
			this.heatableMaterials = ((BlockForge2FuelContent)tileentity.getBlockType()).getRepresentation().getMatList();
		}else {
			heatableMaterials = new HashSet<Item>();
		}
		
		this.addSlotToContainer(new Slot(tileEntityForge2, 0, 52, 27) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					if(ContainerForge2Fuel.this.heatableMaterials.size() == 0) {
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
		
		this.addSlotToContainer(new Slot(tileEntityForge2, 1, 80, 27) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					if(ContainerForge2Fuel.this.heatableMaterials.size() == 0) {
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
		
		this.addSlotToContainer(new Slot(tileEntityForge2, 2, 108, 27) {
			public boolean isItemValid(ItemStack stack) {
				if(stack.getItem() instanceof ItemHotToolHead) {
					if(ContainerForge2Fuel.this.heatableMaterials.size() == 0) {
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
		
		this.addSlotToContainer(new Slot(tileEntityForge2, 3, 80, 50) {
			public boolean isItemValid(ItemStack stack) {
				Block forge = tileentity.getBlockType();
				if(forge instanceof BlockForge2Fuel) {
					return ZenDynamicAlwResources.getFuelListForBlock(forge).contains(stack.getItem());
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
			
			if(this.temp != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			if(this.burnTime != this.tileentity.getField(4)) listener.sendWindowProperty(this, 4, this.tileentity.getField(4));
			if(this.maxBurnTime != this.tileentity.getField(5)) listener.sendWindowProperty(this, 5, this.tileentity.getField(5));
		}
		
		this.temp = this.tileentity.getField(3);
		this.burnTime = this.tileentity.getField(4);
		this.maxBurnTime = this.tileentity.getField(5);
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
            
            if (index <= 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 39, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index > 3 && index < 39)
            {
                if (!this.mergeItemStack(itemstack1, 0, 4, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 39, false))
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
