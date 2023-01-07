package com.tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TileEntityJawCrusher extends TileEntity implements ITickable, IInventory {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	private Random rand = new Random();
	private final Container tempContainer = new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return true;
		}
	};
	
	public boolean crushContents() {
		tempContainer.putStackInSlot(0, this.inventory.get(0));
		tempContainer.putStackInSlot(1, this.inventory.get(1));
		InventoryCrafting craft = new InventoryCrafting(tempContainer, 2, 1);
		IRecipe recipe = findMatchingRecipe(craft, this.getWorld());
		NonNullList<ItemStack> leftovers = recipe.getRemainingItems(craft);
		if(recipe != null) {
			ItemStack result = recipe.getCraftingResult(craft);
			int count = result.getCount() + rand.nextInt(3) - 1;
			result.setCount(count < 1 ? 1 : count);
			if(result.getItem() == this.inventory.get(1).getItem() && this.inventory.get(1).getCount() + result.getCount() > this.getInventoryStackLimit()) {
				for(int i = 0; i < this.inventory.size(); i++) {
					this.inventory.set(i, leftovers.get(i));
				}
				this.setInventorySlotContents(1, result);
				return true;
			}
		}
		
		return false;
	}
	
	@Nullable
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes)
            {
            	if(((ShapelessOneSlotRecipes)irecipe).block == BlockInit.crusher && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
    }
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_jaw_crusher";
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.jaw_crusher");
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack: this.inventory) {
			if(!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		if(index == 0 && !flag) {
			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		if(this.world.getTileEntity(this.pos) != this) {
			return false; 
		}else if(player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 1) {
			return false;
		}
		if(stack.getItem() == Item.getItemFromBlock(Blocks.STONE) && (stack.getItemDamage() == 1 || stack.getItemDamage() == 3)) {
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public void update() {}

}
