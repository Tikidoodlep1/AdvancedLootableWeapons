package tiki.rotn.advancedlootableweapons.inventory.Mill;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityMill;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

public class ContainerMill extends Container {

	private final TileEntityMill tileentity;
	private int crushedContents;
	
	public ContainerMill(InventoryPlayer player, TileEntityMill tileEntityMill) 
	{
		this.tileentity = tileEntityMill;
		
		this.addSlotToContainer(new Slot(tileEntityMill, 0, 59, 17) {
			public boolean isItemValid(ItemStack stack) {
				return ContainerMill.this.findMatchingRecipe(NonNullList.from(null, stack), ContainerMill.this.tileentity.getWorld()) != null;
			}
		});
		
		this.addSlotToContainer(new Slot(tileEntityMill, 1, 107, 45) {
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
	
	@Nullable
    private IRecipe findMatchingRecipe(NonNullList<ItemStack> craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes)
            {
            	if(((ShapelessOneSlotRecipes)irecipe).block == BlockInit.mill && ((ShapelessOneSlotRecipes)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
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
			
			if(this.crushedContents != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
		}
		
		this.crushedContents = this.tileentity.getField(0);
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
            
            if (index == 0 || index == 1)
            {
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0)
            {
                if (index >= 1 && index < 38 && !this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
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
