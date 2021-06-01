package com.tiki.advancedlootableweapons.inventory;

import java.util.Map;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForgeWeapon extends Container{
	
    public final IInventory inputSlot;
    public final IInventory outputSlot;
    private final World world;
    public int buttonPressed;
    private final EntityPlayer player;

    Map<Enchantment, Integer> data;
    
    @SideOnly(Side.CLIENT)
    public ContainerForgeWeapon(InventoryPlayer playerInventory, World worldIn, EntityPlayer player, int i)
    {
        this(playerInventory, worldIn, player);
    }
    
	public ContainerForgeWeapon(InventoryPlayer playerInventory, final World worldIn, EntityPlayer player)
    {
		this.outputSlot = new InventoryCraftResult();
        this.inputSlot = new InventoryBasic("Forge Weapon", false, 1);
        this.world = worldIn;
        this.player = player;
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 80, 33){
        	public boolean isItemValid(ItemStack stack)
            {
        		return stack.getItem() instanceof ItemHotToolHead;
            }
        });
        
        this.addSlotToContainer(new Slot(this.outputSlot, 1, 80, 33){
        	public boolean isItemValid(ItemStack stack)
            {
        		return false;
            }
        });
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }
	
	public void changeItem() {
		int heat = this.getSlot(0).getStack().getItemDamage();
		if(this.buttonPressed != -1) {
	        if(this.inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	        		int weapon = this.buttonPressed;
	        		if(weapon == 1) {
	        			ItemStack stack = new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD);
	        			this.inputSlot.decrStackSize(0, 1);
	        			this.outputSlot.setInventorySlotContents(1, stack);
	        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        		}else if(weapon == 2) {
	        			ItemStack stack = new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD);
	        			this.inputSlot.decrStackSize(0, 1);
	        			this.outputSlot.setInventorySlotContents(1, stack);
	        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        		}else if(weapon == 3) {
	        			ItemStack stack = new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD);
	        			this.inputSlot.decrStackSize(0, 1);
	        			this.outputSlot.setInventorySlotContents(1, stack);
	        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        		}else if(weapon == 4) {
	        			ItemStack stack = new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD);
	        			this.inputSlot.decrStackSize(0, 1);
	        			this.outputSlot.setInventorySlotContents(1, stack);
	        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        		}
	        		this.buttonPressed = -1;
	        	}else if(this.inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD))) {
	        		ItemStack stack = new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2);
        			this.inputSlot.decrStackSize(0, 1);
        			this.outputSlot.setInventorySlotContents(1, stack);
        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        	}else if(this.inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD))) {
	        		ItemStack stack = new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2);
        			this.inputSlot.decrStackSize(0, 1);
        			this.outputSlot.setInventorySlotContents(1, stack);
        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        	}else if(this.inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD))) {
	        		ItemStack stack = new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_2);
        			this.inputSlot.decrStackSize(0, 1);
        			this.outputSlot.setInventorySlotContents(1, stack);
        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        	}else if(this.inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD))) {
	        		ItemStack stack = new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_2);
        			this.inputSlot.decrStackSize(0, 1);
        			this.outputSlot.setInventorySlotContents(1, stack);
        			this.outputSlot.getStackInSlot(1).setItemDamage(heat);
	        	}
	        }
		
	}
	
	public ItemStack getInventoryStack() {
		return this.inventoryItemStacks.get(0);
	}
	
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        super.onCraftMatrixChanged(inventoryIn);
        
    }
    
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, inputSlot);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
    }

    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            this.clearContainer(playerIn, this.world, this.inputSlot);
        }
    }


    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }


    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 37, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0 && index != 1)
            {
                if (index >= 3 && index < 39 && !this.mergeItemStack(itemstack1, 0, 2, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 37, false))
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

