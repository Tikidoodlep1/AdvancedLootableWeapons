package tiki.advancedlootableweapons.blocks.tileentities;

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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2Content;
import tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2FuelContent;
import tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeContent;
import tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeFuelContent;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.items.ItemHotToolHead;
import tiki.advancedlootableweapons.util.HotMetalHelper;

public class TileEntityForge extends TileEntity implements ITickable, IInventory
{
	public static final int minTemp = 850;
	public static final int maxTemp = 1750;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	//private @Nonnull ItemStack fuelInventoryStack = ItemStack.EMPTY;
	private String customName;
	private int heat;
	private double currentTemp = 1750D;
	protected int increaseFrames = 0;
	protected float airflowMultiplier = 1.0f;
	private int burnTime = 0;
	private int maxBurnTime = 0;
	private boolean canUseFuel = false;
	private boolean requiresIgnition = false;
	private boolean needsFuel = false;
	private float baseHeatingSpeed = 1.0f;
	private Block block;
	
	public TileEntityForge() {}
	
	public TileEntityForge(Block block) {
		this(false, false, block);
	}
	
	public TileEntityForge(boolean needsFuel, boolean needsIgnition, Block block) {
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
		return this.inventory.get(index);
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
		ItemStack itemstack = ItemStack.EMPTY;
		itemstack = (ItemStack)this.inventory.get(index);
		this.inventory.set(index, stack);
		
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		if(index == 0 && !flag) {
			//ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.heat = stack.getItemDamage();
			this.markDirty();
		}else if(index == 1 && !flag) {
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
		this.heat = compound.getInteger("Heat");
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
		compound.setInteger("Heat", (short)this.heat);
		compound.setDouble("Temperature", this.currentTemp);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("MaxBurnTime", this.maxBurnTime);
		compound.setBoolean("CanUseFuel", this.canUseFuel);
		compound.setBoolean("RequiresIgnition", this.requiresIgnition);
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
			
			if(this.needsFuel) { // If the forge doesn't need fuel, there's no reason to deal w/ burnTime, etc.
				ItemStack fuelStack = this.inventory.get(1);
				
				if(this.burnTime <= 0) {
					if(!this.requiresIgnition) {
						this.canUseFuel = true;
					}
					if(!fuelStack.isEmpty() && this.canUseFuel) {
						if(fuelStack.getCount() <= 1) {
							this.setInventorySlotContents(1, ItemStack.EMPTY);
						}else {
							fuelStack.setCount(fuelStack.getCount() - 1);
							this.setInventorySlotContents(1, fuelStack);
						}
						
						this.maxBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack) * 2;
						this.burnTime = maxBurnTime;
					}else {
						this.maxBurnTime = 0;
						this.canUseFuel = false;
					}
					
				}else {
					this.burnTime--;
				}
			}
			
			if(currentTemp > minTemp && increaseFrames <= 0) {
				//Only drop temp if we're not burning fuel
				if(this.burnTime <= 0) {
					this.currentTemp -= (0.01875D * ConfigHandler.FORGE_TEMP_DECREASE_MULTIPLIER);
				}
			}else if(currentTemp < maxTemp && increaseFrames > 0) {
				this.currentTemp += (1.078D * ConfigHandler.FORGE_TEMP_INCREASE_MULTIPLIER * this.airflowMultiplier);
				this.increaseFrames--;
			}else {
				this.increaseFrames = 0;
			}
			if(!this.inventory.get(0).isEmpty() && canSmelt()) {
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
			ItemStack stack = this.inventory.get(0);
			Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
			if(stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * this.baseHeatingSpeed * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
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
		}else if(index == 1 && TileEntityFurnace.getItemBurnTime(stack) > 0){
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
		case 2:
			return this.burnTime;
		case 3:
			return this.maxBurnTime;
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
		case 2:
			this.burnTime = value;
			break;
		case 3:
			this.maxBurnTime = value;
			break;
		}
	}
	
	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}
}
