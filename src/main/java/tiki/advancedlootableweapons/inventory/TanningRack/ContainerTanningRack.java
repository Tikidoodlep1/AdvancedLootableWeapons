package tiki.advancedlootableweapons.inventory.TanningRack;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityTanningRack;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

public class ContainerTanningRack extends Container
{
	private final TileEntityTanningRack tileentity;
	private int progress;
	public static final Item itemToTan = ConfigHandler.ENABLE_ADVANCED_LEATHER_TANNING ? ItemInit.DELIMED_HIDE : ItemInit.UNTRIMMED_HIDE;
	
	public ContainerTanningRack(InventoryPlayer player, TileEntityTanningRack tileentity) 
	{
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 26, 34) {
			public boolean isItemValid(ItemStack stack) {
				InventoryCrafting inv = new InventoryCrafting(ContainerTanningRack.this, 1, 1);
				inv.setInventorySlotContents(0, stack);
				return ContainerTanningRack.this.findMatchingRecipe(inv, ContainerTanningRack.this.tileentity.getWorld()) != null;
			}
		});
		
		this.addSlotToContainer(new Slot(tileentity, 1, 134, 34) {
			public boolean isItemValid(ItemStack stack) {
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
			
			if(this.progress != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
		}
		
		this.progress = this.tileentity.getField(0);
	}
	
	@Nullable
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes)
            {
            	if(((ShapelessOneSlotRecipes)irecipe).block == BlockInit.tanning_rack && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
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
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0)
            {
                if (index >= 2 && index < 38 && !this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
                //playerIn.inventoryContainer.getSlot(index).decrStackSize(64);
            }
            else if (!this.mergeItemStack(itemstack1, 2, 38, false))
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
