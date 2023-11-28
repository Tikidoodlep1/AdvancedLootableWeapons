package com.tiki.advancedlootableweapons.blocks.tileentities;


import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2Content;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2FuelContent;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeContent;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeFuelContent;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.util.HotMetalHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TileEntityForge2 extends TileEntity implements ITickable, IInventory
{
	//private TileEntityForge2 mainTE = null;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	//private @Nonnull ItemStack fuelInventoryStack = ItemStack.EMPTY;
	private String customName;
	private int heat1, heat2, heat3;
	//private boolean isMain = false;
	private double currentTemp = 2250D;
	public static final int minTemp = 850;
	public static final int maxTemp = 2250;
	protected int increaseFrames = 0;
	protected float airflowMultiplier = 1.0f;
	private int burnTime = 0;
	private int maxBurnTime = 0;
	private boolean canUseFuel = false;
	private boolean requiresIgnition = false;
	private boolean needsFuel = false;
	private float baseHeatingSpeed = 1.0f;
	private Block block;
	
	public TileEntityForge2() {}
	
	public TileEntityForge2(Block block) {
		this(false, false, block);
	}

//	@SuppressWarnings("deprecation")
	public TileEntityForge2(boolean needsFuel, boolean needsIgnition, Block block) {
//		boolean ignitionStateValue = this.blockType.getStateFromMeta(this.getBlockMetadata()).getValue(BlockForge2Fuel.REQUIRES_IGNITION);
//		if(ignitionStateValue) {
//			needsIgnition = ignitionStateValue;
//		}
		
		if(needsFuel && needsIgnition) {
			this.canUseFuel = false;
		}else if(needsFuel && !needsIgnition) {
			this.canUseFuel = needsFuel;
		}
		this.needsFuel = needsFuel;
		this.requiresIgnition = needsIgnition;
		this.block = block;
		//Block block = this.getWorld().getBlockState(this.getPos()).getBlock();
		
		if(Alw.isCoTLoaded) {
			if(block instanceof BlockForgeContent) {
				this.baseHeatingSpeed = ((BlockForgeContent)block).getRepresentation().getBaseHeatingSpeed();
				this.customName = block.getLocalizedName();
			}else if(block instanceof BlockForgeFuelContent) {
				this.baseHeatingSpeed = ((BlockForgeFuelContent)block).getRepresentation().getBaseHeatingSpeed();
				this.customName = block.getLocalizedName();
			}else if(block instanceof BlockForge2Content) {
				this.baseHeatingSpeed = ((BlockForge2Content)block).getRepresentation().getBaseHeatingSpeed();
				this.customName = block.getLocalizedName();
			}else if(block instanceof BlockForge2FuelContent) {
				this.baseHeatingSpeed = ((BlockForge2FuelContent)block).getRepresentation().getBaseHeatingSpeed();
				this.customName = block.getLocalizedName();
			}
		}
		
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public void bellowsInteraction(IBlockAccess world, BlockPos pos) {
		this.increaseFrames = 60;
	}
	
	public double getCurrentTemp() {
		return this.currentTemp;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_forge2";
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName)  {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName()  {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.forge2");
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
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(compound.getString("block")));
		this.heat1 = compound.getInteger("Heat1");
		this.heat2 = compound.getInteger("Heat2");
		this.heat3 = compound.getInteger("Heat3");
		this.currentTemp = compound.getDouble("Temperature");
		this.burnTime = compound.getInteger("BurnTime");
		this.maxBurnTime = compound.getInteger("MaxBurnTime");
		this.canUseFuel = compound.getBoolean("CanUseFuel");
		this.requiresIgnition = compound.getBoolean("RequiresIgnition");
		this.needsFuel = compound.getBoolean("NeedsFuel");
		this.baseHeatingSpeed = compound.getFloat("baseHeatingSpeed");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setString("block", this.block.getRegistryName().toString());
		compound.setInteger("Heat1", (short)this.heat1);
		compound.setInteger("Heat2", (short)this.heat2);
		compound.setInteger("Heat3", (short)this.heat3);
		compound.setDouble("Temperature", this.currentTemp);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("MaxBurnTime", this.maxBurnTime);
		compound.setBoolean("CanUseFuel", this.canUseFuel);
		compound.setBoolean("RequiresIgnition", this.requiresIgnition);
		compound.setBoolean("isMain", true);
		compound.setBoolean("NeedsFuel", this.needsFuel);
		compound.setFloat("baseHeatingSpeed", this.baseHeatingSpeed);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void usedIgniterOnForge() {
		this.canUseFuel = true;
	}
	
	public void setRequiresIgnition(boolean requiresIgnition) {
		this.requiresIgnition = requiresIgnition;
	}
	
	public void update()
	{
		if(!this.world.isRemote) {
			
			if(this.needsFuel) {
				ItemStack fuelStack = this.inventory.get(3);
				
				if(this.burnTime <= 0) {
					if(!this.requiresIgnition) {
						this.canUseFuel = true;
					}
					if(!fuelStack.isEmpty() && this.canUseFuel) {
						if(fuelStack.getCount() <= 1) {
							this.setInventorySlotContents(3, ItemStack.EMPTY);
						}else {
							fuelStack.setCount(fuelStack.getCount() - 1);
							this.setInventorySlotContents(3, fuelStack);
						}
						
						this.maxBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack) * 2;
						this.burnTime = maxBurnTime;
					}else {
						this.canUseFuel = false;
					}
					
				}else {
					this.burnTime--;
				}
			}
			
			if(currentTemp > minTemp && this.increaseFrames <= 0) {
				if(this.burnTime <= 0) {
					this.currentTemp -= (0.01875D * ConfigHandler.FORGE_TEMP_DECREASE_MULTIPLIER);
				}
			}else if(currentTemp < maxTemp && this.increaseFrames > 0) {
				this.currentTemp += (1.078D * ConfigHandler.FORGE_TEMP_INCREASE_MULTIPLIER * this.airflowMultiplier);
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
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * this.baseHeatingSpeed * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
				this.heat1 = stack.getItemDamage();
			}
		}
		if(this.inventory.get(1) != ItemStack.EMPTY && this.inventory.get(1).getItem() instanceof ItemHotToolHead) {
			ItemStack stack = this.inventory.get(1);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * this.baseHeatingSpeed * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
				this.heat2 = stack.getItemDamage();
			}
		}
		if(this.inventory.get(2) != ItemStack.EMPTY && this.inventory.get(2).getItem() instanceof ItemHotToolHead) {
			ItemStack stack = this.inventory.get(2);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * this.baseHeatingSpeed * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
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
		if(stack.getItem() instanceof ItemHotToolHead) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:forge_2";
	}
	
	public int getField(int id) 
	{
		switch(id) {
		case 0:
			return this.heat1;
		case 1:
			return this.heat2;
		case 2:
			return this.heat3;
		case 3:
			return (int)this.currentTemp;
		case 4:
			return this.burnTime;
		case 5:
			return this.maxBurnTime;
		default:
			return -1;
		}
	}
	
	public void setField(int id, int value) 
	{
		switch(id) {
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
		case 4:
			this.burnTime = value;
			break;
		case 5:
			this.maxBurnTime = value;
			break;
		}
	}
	
	@Override
	public int getFieldCount() {
		return 6;
	}
	
	@Override
	public void clear() {
		this.inventory.clear();
	}
}
