package com.tiki.advancedlootableweapons.blocks.tileentities;


import com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.util.HotMetalHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;

public class TileEntityForge2 extends TileEntity implements ITickable, IInventory
{
	private TileEntityForge2 mainTE = null;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private int heat1, heat2, heat3;
	private boolean isMain = false;
	private double currentTemp = 2250D;
	public static final int minTemp = 850;
	public static final int maxTemp = 2250;
	private int increaseFrames = 0;

	public TileEntityForge2 getMainTE(IBlockAccess world, BlockPos pos) {
		if(mainTE == null) {
			BlockPos mainBlock = BlockForge2Placeholder.getMainPos(world, pos, world.getBlockState(pos));
			TileEntityForge2 newMainTE = null;
			if(world.getTileEntity(mainBlock) != null) {
				newMainTE = TileEntityForge2.class.cast(world.getTileEntity(mainBlock));
			}
			setMainTE(newMainTE);
		}
		return mainTE;
	}
	
	public void setMainTE(TileEntityForge2 mainTE) {
		this.mainTE = mainTE;
	}
	
	public void bellowsInteraction(IBlockAccess world, BlockPos pos) {
		if(mainTE == this) {
			this.increaseFrames = 60;
			}else {
				getMainTE(world, pos).bellowsInteraction(world, pos);
			}
	}
	
	public double getCurrentTemp() {
		if(mainTE == this) {
			return this.currentTemp;
		}else {
			return getMainTE(this.getWorld(), this.getPos()).getCurrentTemp();
		}
	}
	
	@Override
	public String getName() {
		if(mainTE == this) {
			return this.hasCustomName() ? this.customName : "container_forge2";
		}else {
			return getMainTE(this.getWorld(), this.getPos()).hasCustomName() ? getMainTE(this.getWorld(), this.getPos()).customName : "container_forge2";
		}
	}
	
	public boolean hasCustomName()
	{
		if(mainTE == this) {
			return this.customName != null && !this.customName.isEmpty();
		}else {
			return getMainTE(this.getWorld(), this.getPos()).customName != null && !getMainTE(this.getWorld(), this.getPos()).customName.isEmpty();
		}
	}
	
	public void setCustomName(String customName) 
	{
		if(mainTE == this) {
			this.customName = customName;
		}else {
			getMainTE(this.getWorld(), this.getPos()).setCustomName(customName);
		}
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		if(mainTE == this) {
			return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.forge2");
		}else {
			return getMainTE(this.getWorld(), this.getPos()).hasCustomName() ? new TextComponentString(getMainTE(this.getWorld(), this.getPos()).customName) : new TextComponentTranslation("container.forge2");
		}
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}
	
	@Override
	public boolean isEmpty() {
		if(mainTE == this) {
			for(ItemStack stack: this.inventory) {
				if(!stack.isEmpty()) {
					return false;
				}
			}
			return true;
		}else {
			return getMainTE(this.getWorld(), this.getPos()).isEmpty();
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		if(mainTE == this) {
			return (ItemStack)this.inventory.get(index);
		}else {
			return getMainTE(this.getWorld(), this.getPos()).inventory.get(index);
		}
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if(mainTE == this) {
			return ItemStackHelper.getAndSplit(this.inventory, index, count);
		}else {
			return getMainTE(this.getWorld(), this.getPos()).decrStackSize(index, count);
		}
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		if(mainTE == this) {
			return ItemStackHelper.getAndRemove(this.inventory, index);
		}else {
			return getMainTE(this.getWorld(), this.getPos()).removeStackFromSlot(index);
		}
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(mainTE == this) {
			ItemStack itemstack = (ItemStack)this.inventory.get(index);
			boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
			this.inventory.set(index, stack);
		
			if(stack.getCount() > this.getInventoryStackLimit()) {
				stack.setCount(this.getInventoryStackLimit());
			}
			
			if(!flag) {
				switch(index) {
				case 0:
					this.heat1 = stack.getItemDamage();
					break;
				case 1:
					this.heat2 = stack.getItemDamage();
					break;
				case 2:
					this.heat3 = stack.getItemDamage();
					break;
				}
				this.markDirty();
			}
		}else {
			getMainTE(this.getWorld(), this.getPos()).setInventorySlotContents(index, stack);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		this.isMain = compound.getBoolean("isMain");
		if(isMain) {
			this.setMainTE(this);
		}
		
		if(mainTE == this) {
			this.heat1 = compound.getInteger("Heat1");
			this.heat2 = compound.getInteger("Heat2");
			this.heat3 = compound.getInteger("Heat3");
			this.currentTemp = compound.getDouble("Temperature");
		
			if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
		}else {
			getMainTE(this.getWorld(), this.getPos()).readFromNBT(compound);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		
		if(mainTE == this) {
			compound.setInteger("Heat1", (short)this.heat1);
			compound.setInteger("Heat2", (short)this.heat2);
			compound.setInteger("Heat3", (short)this.heat3);
			compound.setDouble("Temperature", this.currentTemp);
			compound.setBoolean("isMain", true);
			ItemStackHelper.saveAllItems(compound, this.inventory);
		
			if(this.hasCustomName()) compound.setString("CustomName", this.customName);
			return compound;
		}else {
			compound.setBoolean("isMain", false);
			ItemStackHelper.saveAllItems(compound, getMainTE(this.getWorld(), this.getPos()).inventory);
			return getMainTE(this.getWorld(), this.getPos()).writeToNBT(compound);
		}
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void update()
	{
		if(mainTE == this && !this.world.isRemote) {
			if(currentTemp > minTemp && this.increaseFrames <= 0) {
				this.currentTemp -= 0.01875D;
			}else if(currentTemp < maxTemp && this.increaseFrames > 0) {
				this.currentTemp += 1.078D;
				this.increaseFrames--;
			}else {
				this.increaseFrames = 0;
			}
			this.smeltItem();
		}
	}
	
	public void smeltItem() {
		if(this.inventory.get(0) != ItemStack.EMPTY && this.inventory.get(0).getItem() instanceof ItemHotToolHead) {
			ItemStack stack = this.inventory.get(0);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
				this.heat1 = stack.getItemDamage();
			}
		}
		if(this.inventory.get(1) != ItemStack.EMPTY && this.inventory.get(1).getItem() instanceof ItemHotToolHead) {
			ItemStack stack = this.inventory.get(1);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
				this.heat2 = stack.getItemDamage();
			}
		}
		if(this.inventory.get(2) != ItemStack.EMPTY && this.inventory.get(2).getItem() instanceof ItemHotToolHead) {
			ItemStack stack = this.inventory.get(2);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
				this.heat3 = stack.getItemDamage();
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
		if(mainTE == this) {
			if(stack.getItem() instanceof ItemHotToolHead) {
				return true;
			}else {
				return false;
			}
		}else {
			return getMainTE(this.getWorld(), this.getPos()).isItemValidForSlot(index, stack);
		}
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:forge_2";
	}
	
	public int getField(int id) 
	{
		if(mainTE == this) {
			switch(id) 
			{
			case 0:
				return this.heat1;
			case 1:
				return this.heat2;
			case 2:
				return this.heat3;
			case 3:
				return (int)this.currentTemp;
			default:
				return -1;
			}
		}else {
			return getMainTE(this.getWorld(), this.getPos()).getField(id);
		}
	}
	
	public void setField(int id, int value) 
	{
		if(mainTE == this) {
			switch(id) 
			{
			case 0:
				this.heat1 = value;
				break;
			case 1:
				this.heat2 = value;
				break;
			case 2:
				this.heat3 = value;
				break;
			case 3:
				this.currentTemp = value;
				break;
			}
		}else {
			getMainTE(this.getWorld(), this.getPos()).setField(id, value);
		}
	}
	
	@Override
	public int getFieldCount() {
		return 4;
	}
	
	@Override
	public void clear() {
		if(mainTE == this) {
			this.inventory.clear();
		}else {
			getMainTE(this.getWorld(), this.getPos()).clear();
		}
	}
}
