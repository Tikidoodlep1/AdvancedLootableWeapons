package com.tiki.advancedlootableweapons.inventory;

import java.util.Map;
import com.tiki.advancedlootableweapons.init.EnchantmentInit;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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

public class ContainerSharpeningStone extends Container{
	
	private final IInventory outputSlot;

    private final IInventory inputSlot;
    private final World world;

    private static ItemSharpeningStone activeStone;
    int enchLevel;
    Map<Enchantment, Integer> data;
    
    @SideOnly(Side.CLIENT)
    public ContainerSharpeningStone(InventoryPlayer playerInventory, World worldIn, EntityPlayer player, int i)
    {
        this(playerInventory, worldIn, player);
    }

    public static void sendActiveMaterial(ItemSharpeningStone stone) {
    	activeStone = stone;
    }
    
	public ContainerSharpeningStone(InventoryPlayer playerInventory, final World worldIn, EntityPlayer player)
    {
        this.outputSlot = new InventoryCraftResult();
        this.inputSlot = new InventoryBasic("Sharpening Stone", true, 1);
        this.world = worldIn;
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 80, 56){
        	public boolean isItemValid(ItemStack stack)
            {
            	if(stack.getItem().getUnlocalizedName().substring(stack.getItem().getUnlocalizedName().indexOf('_') + 1).equalsIgnoreCase(activeStone.getUnlocalizedName().substring(activeStone.getUnlocalizedName().indexOf('.') + 1, activeStone.getUnlocalizedName().indexOf('_'))) && !stack.getItem().getUnlocalizedName().contains("ingot")) {
            			if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, stack) < 6){
            				ItemStack copyStack = stack.copy();
            				enchLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, copyStack);
            				data = EnchantmentHelper.getEnchantments(copyStack);
            				data.put(EnchantmentInit.REFINED, enchLevel += 1);
            				
            				if (!stack.isEmpty() && EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, stack) >= 1)
            				{
            					EnchantmentHelper.setEnchantments(data, copyStack);
            				}else {
        		                copyStack.addEnchantment(EnchantmentInit.REFINED, 1);
            				}
            				outputSlot.setInventorySlotContents(1, copyStack);
            			}
            			if(player.inventory.getSlotFor(new ItemStack(activeStone)) == -1) {
            				player.addItemStackToInventory(stack);
            				Minecraft.getMinecraft().player.closeScreen();
            	        }
            	        //stack.shrink(1);
            		return true;
            	}else {
            		return false;
            	}
            }
        	
        	public ItemStack onTake(EntityPlayer player, ItemStack stack) {
        		ContainerSharpeningStone.this.outputSlot.setInventorySlotContents(1, ItemStack.EMPTY);
        		return stack;
        	}
        });
        
        this.addSlotToContainer(new Slot(this.outputSlot, 1, 80, 13)
        {
            public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
            
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return this.getHasStack();
            }
            public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
                ContainerSharpeningStone.this.inputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
                
				//data.put(EnchantmentInit.REFINED, enchLevel += 1);
				
				if (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, stack) >= 1)
				{
					EnchantmentHelper.setEnchantments(data, stack);
				}else {
	                stack.addEnchantment(EnchantmentInit.REFINED, 1);
				}
				
                //if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, stack) >= 1){
                //stack.addEnchantment(EnchantmentInit.REFINED, EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, stack) + 1);
                //}else {
                //	stack.addEnchantment(EnchantmentInit.REFINED, 1);
                //}
                
                thePlayer.inventory.getStackInSlot(thePlayer.inventory.getSlotFor(new ItemStack(activeStone))).shrink(1);
                
                //if(!thePlayer.addItemStackToInventory(stack)) {
                //	thePlayer.entityDropItem(stack, 0);
                //}else {
                //	thePlayer.addItemStackToInventory(stack);
                //}
                

             if (!worldIn.isRemote)
             {
                 worldIn.playEvent(1030, player.getPosition(), 0);
             }
             return stack;
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


    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        super.onCraftMatrixChanged(inventoryIn);

        if (inventoryIn == this.inputSlot)
        {
        	outputSlot.setInventorySlotContents(1, ContainerSharpeningStone.this.inputSlot.getStackInSlot(0));
        	if (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REFINED, outputSlot.getStackInSlot(1)) >= 1)
			{
				EnchantmentHelper.setEnchantments(data, outputSlot.getStackInSlot(1));
			}else {
				outputSlot.getStackInSlot(1).addEnchantment(EnchantmentInit.REFINED, 1);
			}
        }
    }
    
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, inputSlot);
        listener.sendAllWindowProperties(this, outputSlot);
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
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
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
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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

