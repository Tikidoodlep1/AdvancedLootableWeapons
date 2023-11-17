package com.tiki.advancedlootableweapons.blocks.tileentities;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.recipes.AlloyingRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAlloyFurnace extends TileEntity implements ITickable, IInventory
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	private String customName;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 400;
	private IRecipe recipe = null;
	public float recipeExp = 0;
	private Container tempContainer = new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return true;
		}
	};
	
	private InventoryCrafting inv = new InventoryCrafting(tempContainer, 3, 1);

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_alloy_furnace";
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.alloy_furnace");
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
	
	public void findNewRecipe() {
		this.inv.setInventorySlotContents(0, this.getStackInSlot(0));
		this.inv.setInventorySlotContents(1, this.getStackInSlot(1));
		this.inv.setInventorySlotContents(2, this.getStackInSlot(3));
		this.recipe = findMatchingRecipe(inv, this.getWorld());
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		if( (index == 0 || index == 1) && !flag) {
			ItemStack stack0 = (ItemStack)this.inventory.get(0);
			ItemStack stack1 = (ItemStack)this.inventory.get(1);
			this.totalCookTime = this.getCookTime(stack0, stack1);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
//	public void sendExp(int exp) { // This crosses logical sides! TE's are present on the server!
//		NetHandlerPlayClient clientHandler = Minecraft.getMinecraft().getConnection();
//		SPacketSpawnExperienceOrb packet = new SPacketSpawnExperienceOrb(new EntityXPOrb(this.world, (double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), exp));
//		Minecraft.getMinecraft().addScheduledTask(() -> packet.processPacket(clientHandler));
//	}
	
	@Nullable
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof AlloyingRecipe)
            {
            	if(irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = ForgeEventFactory.getItemBurnTime(this.inventory.get(2));
		this.findNewRecipe();
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) 
	{
		return inventory.getField(0) > 0;
	}
	
	public void update() {
		boolean burningFlag = this.isBurning();
		boolean dirty = false;
		
		if(this.isBurning()) {
			--this.burnTime;
		}
		
		if(!this.world.isRemote) {
			ItemStack stack = this.inventory.get(2);
			
			if(!this.isBurning() && this.recipe != null && !stack.isEmpty()) {
				this.burnTime = ForgeEventFactory.getItemBurnTime(stack) <= 0 ? TileEntityFurnace.getItemBurnTime(stack) : ForgeEventFactory.getItemBurnTime(stack);
				this.currentBurnTime = this.burnTime;
				
				stack.shrink(1);
				if(stack.isEmpty() && stack.getItem().hasContainerItem(stack)) {
					ItemStack item1 = stack.getItem().getContainerItem(stack);
					this.setInventorySlotContents(2, item1);
				}
				dirty = true;
			}
			
			ItemStack output = this.inventory.get(3);
			
			if(this.isBurning() && this.recipe != null && (output.isEmpty() || (output.getItem() == recipe.getCraftingResult(inv).getItem() && output.getCount() + recipe.getCraftingResult(inv).getCount() <= this.getInventoryStackLimit()) )) {
				++this.cookTime;
				
				if(this.cookTime >= this.totalCookTime) {
					this.cookTime = 0;
					this.totalCookTime = this.getCookTime(this.inventory.get(0), this.inventory.get(1));
					this.smeltItem();
					dirty = true;
				}
			}else {
				this.cookTime = 0;
			}
		}
		
		if(this.isBurning() != burningFlag) {
			dirty = true;
			BlockAlloyFurnace.setState(this.isBurning(), this.getWorld(), pos);
		}
		
		if(dirty) {
			this.markDirty();
		}
	}
	
	public int getCookTime(ItemStack input1, ItemStack input2) {
		return 400;
	}
	
	public void smeltItem() {
		ItemStack result = recipe.getCraftingResult(inv);
		ItemStack output = this.inventory.get(3);
		NonNullList<ItemStack> remaining = recipe.getRemainingItems(inv);
		this.recipeExp = ((AlloyingRecipe)recipe).getExp();
		
		this.setInventorySlotContents(0, remaining.get(0));
		this.setInventorySlotContents(1, remaining.get(1));
		
		if(output.isEmpty()) {
			this.setInventorySlotContents(3, result);
		}else {
			output.grow(result.getCount());
		}
		
		this.findNewRecipe();
	}
	
	public static boolean isItemFuel(ItemStack fuel)
	{
		if(TileEntityFurnace.getItemBurnTime(fuel) > 0) {
			return true;
		}
		return ForgeEventFactory.getItemBurnTime(fuel) > 0;
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
		if(index == 3) {
			return false;
		}else if(index != 2){
			this.findNewRecipe();
			return true;
		}else {
			this.findNewRecipe();
			return isItemFuel(stack);
		}
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:alloy_furnace";
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
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
