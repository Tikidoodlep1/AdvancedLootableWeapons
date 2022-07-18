package com.tiki.advancedlootableweapons.blocks.tileentities;

import com.tiki.advancedlootableweapons.blocks.recipes.DrumRecipes;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.TileFluidHandler;

public class TileEntityDrum extends TileFluidHandler implements ITickable, IInventory, IFluidHandler
{
	public static final int INPUT_SLOT = 0;
	public static final int ADDITIVE_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private int progress = 0;
	private DrumRecipes activeRecipe = null;
	
	public void FluidInteraction(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		if(this.getTank().getFluidAmount() <= 0) {
			this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
		}
	}
	
	public boolean EntityInteraction(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		ItemStack activeStack = playerIn.getHeldItem(hand);
		if(!playerIn.isSneaking() && activeStack != ItemStack.EMPTY) {
			if(DrumRecipes.getAcceptedAdditives().contains(activeStack.getItem()) && !this.inventory.get(ADDITIVE_SLOT).isItemEqual(activeStack)) {
				this.inventory.set(ADDITIVE_SLOT, new ItemStack(activeStack.getItem()));
				activeStack.shrink(1);
			}
			if(DrumRecipes.getAcceptedInputs().contains(activeStack.getItem()) && this.inventory.get(INPUT_SLOT) == ItemStack.EMPTY) {
				this.inventory.set(INPUT_SLOT, activeStack);
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
			}
		}else {
			if(this.inventory.get(OUTPUT_SLOT) != ItemStack.EMPTY) {
				if(playerIn.addItemStackToInventory(this.inventory.get(OUTPUT_SLOT))) {
					this.inventory.set(OUTPUT_SLOT, ItemStack.EMPTY);
				}
			}else if(this.inventory.get(INPUT_SLOT) != ItemStack.EMPTY) {
				if(playerIn.addItemStackToInventory(this.inventory.get(INPUT_SLOT))) {
					this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
					this.progress = 0;
					this.activeRecipe = null;
				}
			}
		}
		
		if(this.activeRecipe == null && DrumRecipes.getAcceptedFluids().contains(this.getTank().getFluid().getFluid()) && this.inventory.get(INPUT_SLOT) != ItemStack.EMPTY) {
			DrumRecipes recipe = DrumRecipes.getMatchingRecipe(this.getTank().getFluid().getFluid(), this.inventory.get(INPUT_SLOT), this.inventory.get(ADDITIVE_SLOT), false);
			System.out.println("RECIPE IS " + recipe);
			if(recipe != null) {
				this.activeRecipe = recipe;
			}else {
				this.progress = 0;
			}
		}
		
		return true;
	}
	
	public TileEntityDrum() {
		this.tank.setCapacity(Fluid.BUCKET_VOLUME);
	}
	
	public FluidTank getTank() {
		return this.tank;
	}
	
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_drum";
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.drum");
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
			//ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.markDirty();
		}
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		NBTTagCompound fluidTag = this.getTank().writeToNBT(new NBTTagCompound());
//		compound.setTag("Fluid", fluidTag);
		if(fluidTag.hasKey("FluidName")) {
			compound.setString("FluidName", fluidTag.getString("FluidName"));
		}
		if(fluidTag.hasKey("Amount")) {
			compound.setInteger("Amount", fluidTag.getInteger("Amount"));
		}
		if(fluidTag.hasKey("Empty")) {
			compound.setString("Empty", fluidTag.getString("Empty"));
		}
		return compound;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		if(tag.hasKey("Fluid")) {
			this.getTank().readFromNBT(tag.getCompoundTag("Fluid"));
		}
		super.handleUpdateTag(tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), this.getUpdateTag());
	}
	
	public void onFluidChanged() {		
		onDataPacket(Minecraft.getMinecraft().getConnection().getNetworkManager(), getUpdatePacket());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.getTank().readFromNBT(compound);
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
		if(this.getWorld() != null) {
			this.getWorld().notifyBlockUpdate(this.getPos(), BlockInit.drum.getDefaultState(), BlockInit.drum.getDefaultState(), 2);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		this.getTank().writeToNBT(compound);
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
		if(!this.world.isRemote && this.activeRecipe != null) {
			++this.progress;
			if(this.progress >= this.activeRecipe.getRecipeTime()) {
				if(this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY) {
					int outputCount = (64 - this.inventory.get(OUTPUT_SLOT).getCount()) / this.activeRecipe.getOutput().getCount();
					int inputCount = this.inventory.get(INPUT_SLOT).getCount() / this.activeRecipe.getInput().getCount();
					int recipeCount = inputCount > outputCount ? outputCount : inputCount;
					this.inventory.set(OUTPUT_SLOT, new ItemStack(this.activeRecipe.getOutput().getItem(), this.activeRecipe.getOutput().getCount() * recipeCount));
					if(this.inventory.get(INPUT_SLOT).getCount() - (this.activeRecipe.getInput().getCount() * recipeCount) <= 0) {
						this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
						this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
					}else {
						this.inventory.get(INPUT_SLOT).setCount(this.inventory.get(INPUT_SLOT).getCount() - (this.activeRecipe.getInput().getCount() * recipeCount));
						this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
					}
					this.activeRecipe = null;
					this.progress = 0;
				}
			}
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:drum";
	}
	
	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.progress;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.progress = value;
			break;
		}
	}
	
	@Override
	public int getFieldCount() {
		return 1;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		this.markDirty();
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		this.markDirty();
		return tank.drain(resource, doDrain);
	}
	
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		this.markDirty();
		return tank.drain(maxDrain, doDrain);
	}
}
