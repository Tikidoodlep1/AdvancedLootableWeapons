package com.tiki.advancedlootableweapons.blocks.tileentities;

import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.util.HotMetalHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityForge extends TileEntity implements ITickable, IInventory
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	private String customName;
	private int heat;
	private double currentTemp = 1750D;
	public static final int minTemp = 850;
	public static final int maxTemp = 1750;
	private int increaseFrames = 0;
	
	public void bellowsInteraction() {
		this.increaseFrames = 60;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_forge";
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.forge");
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
		if(index == 0 && index + 1 == 1 & !flag) {
			//ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.heat = stack.getItemDamage();
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.heat = compound.getInteger("Heat");
		this.currentTemp = compound.getDouble("Temperature");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("Heat", (short)this.heat);
		compound.setDouble("Temperature", this.currentTemp);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void update() 
	{	
		if(!this.world.isRemote) {
			if(currentTemp > minTemp && increaseFrames <= 0) {
				this.currentTemp -= 0.06D;
			}else if(currentTemp < maxTemp && increaseFrames > 0) {
				this.currentTemp += 0.96D;
				this.increaseFrames--;
			}
			if(!(((ItemStack)this.inventory.get(0)).isEmpty() && canSmelt())) {
				this.smeltItem();
			}
		}
	}
	
	public boolean canSmelt() 
	{
		ItemStack result = (ItemStack)this.inventory.get(0);
		if(result.isEmpty()) return false;
		else
		{
			return true;
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack stack = (ItemStack)this.inventory.get(0);
			String material = stack.getTagCompound() == null ? "Steel" : stack.getTagCompound().getString("Material");
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()));
				this.heat = stack.getItemDamage();
			}
		}
	}
	
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
		if(index == 0 && stack.getItem() instanceof ItemHotToolHead) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:forge";
	}
	
	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.heat;
		case 1:
			return (int)this.currentTemp;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.heat = value;
			break;
		case 1:
			this.currentTemp = value;
			break;
		}
	}
	
	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}
}
