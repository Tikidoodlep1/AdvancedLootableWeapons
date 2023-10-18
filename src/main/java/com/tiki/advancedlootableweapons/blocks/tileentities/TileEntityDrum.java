package com.tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.handlers.SoundHandler;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.recipes.DrumItemRecipe;
import com.tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
	private boolean canQuench = false;
	private IRecipe activeRecipe = null;
	private Random rand = new Random();
	private Vector3f bubbleColumn1 = new Vector3f(0.0f, 1.0f, 0.0f);
	private Vector3f bubbleColumn2 = new Vector3f(0.0f, 1.0f, 0.0f);
	private float speedFactor1 = 0.02f;
	private float speedFactor2 = 0.02f;
	private float bubbleOffset1 = 0.0f;
	private float bubbleOffset2 = 0.0f;
	private boolean canBubble2 = true;
	private Container tempContainer = new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return false;
		}
	};
	private InventoryCrafting craft = new InventoryCrafting(tempContainer, 3, 1);
	
	public void FluidInteraction(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		if(this.getTank().getFluidAmount() <= 0) {
			this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
		}
	}
	
	public boolean EntityInteraction(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		ItemStack activeStack = playerIn.getHeldItem(hand);
		
		if(!playerIn.isSneaking() && activeStack != ItemStack.EMPTY) {
			if(this.inventory.get(INPUT_SLOT).isEmpty()) {
				this.inventory.set(INPUT_SLOT, activeStack);
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
				this.onChanged();
			}else if(this.inventory.get(ADDITIVE_SLOT).isEmpty() && !(activeStack.getItem() instanceof ItemHotToolHead)) {
				this.inventory.set(ADDITIVE_SLOT, new ItemStack(activeStack.getItem()));
				activeStack.shrink(1);
			}
			
			for(int i = 0; i < 3; i++) {
				craft.setInventorySlotContents(i, this.inventory.get(i));
			}
		}else {
			if(this.inventory.get(OUTPUT_SLOT) != ItemStack.EMPTY) {
				if(playerIn.addItemStackToInventory(this.inventory.get(OUTPUT_SLOT))) {
					this.inventory.set(OUTPUT_SLOT, ItemStack.EMPTY);
					this.onChanged();
				}
			}else if(this.inventory.get(INPUT_SLOT) != ItemStack.EMPTY) {
				if(playerIn.addItemStackToInventory(this.inventory.get(INPUT_SLOT))) {
					this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
					this.progress = 0;
					this.activeRecipe = null;
					this.onChanged();
				}
			}else if(this.inventory.get(ADDITIVE_SLOT) != ItemStack.EMPTY) {
				if(playerIn.addItemStackToInventory(this.inventory.get(ADDITIVE_SLOT))) {
					this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
					this.progress = 0;
					this.activeRecipe = null;
					this.onChanged();
				}
			}
			
			Block under = worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock();
			Fluid f = FluidRegistry.lookupFluidForBlock(under);
			boolean fluid = f != null && f.getTemperature() >= FluidRegistry.LAVA.getTemperature() - 500;
			this.canQuench = under == Blocks.FIRE || under == Blocks.LAVA || under == Blocks.FLOWING_LAVA || fluid;
			
			for(int i = 0; i < 3; i++) {
				craft.setInventorySlotContents(i, this.inventory.get(i));
			}
		}
		
		if(this.activeRecipe == null && this.getTank().getFluid() != null && this.inventory.get(INPUT_SLOT) != ItemStack.EMPTY && this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY) {
			IRecipe recipe = this.findMatchingRecipe(craft, this.getWorld());
			
			
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
		return false;
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
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		if(!flag) {
			this.markDirty();
		}
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		NBTTagCompound fluidTag = this.getTank().writeToNBT(new NBTTagCompound());
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
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
		ItemStackHelper.loadAllItems(tag, this.inventory);
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
	
	public void onChanged() {
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
			if(this.activeRecipe instanceof DrumQuenchingRecipe && this.canQuench) {
				++this.progress;
				NetHandlerPlayClient clientHandler = Minecraft.getMinecraft().getConnection();
				if(this.progress % 2 == 0) {
					SPacketParticles particlePacket = new SPacketParticles(EnumParticleTypes.SPIT, false, this.pos.getX() + 0.5f + (rand.nextFloat() - 0.5f), this.pos.getY() + 0.85f + (rand.nextFloat() - 0.5f), this.pos.getZ() + 0.5f + (rand.nextFloat() - 0.5f), 0f, 0.0f, 0f, 0.01f, 1);
					Minecraft.getMinecraft().addScheduledTask(() -> particlePacket.processPacket(clientHandler));
				}
				if(progress == 6) {
					SPacketSoundEffect soundPacket = new SPacketSoundEffect(SoundHandler.QUENCH, SoundCategory.BLOCKS, this.getPos().getX() + 0.5, this.getPos().getY(), this.getPos().getZ() + 0.5, 1.0f, 1.0f);
					Minecraft.getMinecraft().addScheduledTask(() -> soundPacket.processPacket(clientHandler));
				}
			}else if(this.activeRecipe instanceof DrumQuenchingRecipe && !this.canQuench) {
				Block under = this.getWorld().getBlockState(this.pos.offset(EnumFacing.DOWN)).getBlock();
				this.canQuench = under == Blocks.FIRE || under == Blocks.LAVA || under == Blocks.FLOWING_LAVA || FluidRegistry.lookupFluidForBlock(under).getTemperature() >= FluidRegistry.LAVA.getTemperature() - 500;
			}else if(this.activeRecipe instanceof DrumItemRecipe) {
				++this.progress;
				NetHandlerPlayClient clientHandler = Minecraft.getMinecraft().getConnection();
				
				if(((float)bubbleColumn1.getY() + bubbleOffset1) >= 1.0f) {
					bubbleColumn1.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
					bubbleOffset1 = 0.0f;
					speedFactor1 = (rand.nextFloat() + 0.1f) / 50f;
					//System.out.println("Bubble Column: " + "[" + bubbleColumn1.getX() + ", " + bubbleColumn1.getY() + bubbleOffset1 + ", " + bubbleColumn1.getZ() + "], speed: " + speedFactor1);
				}
				SPacketParticles particlePacket = new SPacketParticles(EnumParticleTypes.WATER_BUBBLE, false, this.pos.getX() + bubbleColumn1.getX(), this.pos.getY() + bubbleColumn1.getY() + bubbleOffset1, this.pos.getZ() + bubbleColumn1.getZ(), 0f, 0f, 0f, 0.001f, 1);
				Minecraft.getMinecraft().addScheduledTask(() -> particlePacket.processPacket(clientHandler));
				bubbleOffset1 += speedFactor1;
				
				if(canBubble2 && rand.nextFloat() >= 0.9) {
					canBubble2 = false;
				}
				if(((float)bubbleColumn2.getY() + bubbleOffset2) >= 1.0) {
					bubbleColumn2.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
					bubbleOffset2 = 0.0f;
					speedFactor2 = (rand.nextFloat() + 0.1f) / 50f;
					canBubble2 = true;
				}
				if(!canBubble2) {
					SPacketParticles particle2Packet = new SPacketParticles(EnumParticleTypes.WATER_BUBBLE, false, this.pos.getX() + bubbleColumn2.getX(), this.pos.getY() + bubbleColumn2.getY() + bubbleOffset2, this.pos.getZ() + bubbleColumn2.getZ(), 0f, 0f, 0f, 0.001f, 1);
					Minecraft.getMinecraft().addScheduledTask(() -> particle2Packet.processPacket(clientHandler));
					bubbleOffset2 += speedFactor2;
				}
			}
			
			if(this.activeRecipe instanceof DrumItemRecipe && this.progress >= ((DrumItemRecipe)this.activeRecipe).getTime()) {
				if(this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY) {
					int activeInputCount = ((DrumItemRecipe)this.activeRecipe).getInputCount(craft);
					int outputCount = (64 - this.inventory.get(OUTPUT_SLOT).getCount()) / this.activeRecipe.getCraftingResult(craft).getCount();
					int inputCount = this.inventory.get(INPUT_SLOT).getCount() / activeInputCount;
					int recipeCount = inputCount > outputCount ? outputCount : inputCount;
					this.inventory.set(OUTPUT_SLOT, new ItemStack(this.activeRecipe.getCraftingResult(craft).getItem(), this.activeRecipe.getCraftingResult(craft).getCount() * recipeCount));
					//System.out.println("SET OUTPUT SLOT TO " + this.inventory.get(OUTPUT_SLOT).getDisplayName() + "x" + this.inventory.get(OUTPUT_SLOT).getCount());
					if(this.inventory.get(INPUT_SLOT).getCount() - (activeInputCount * recipeCount) <= 0) {
						this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
						this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
					}else {
						this.inventory.get(INPUT_SLOT).setCount(this.inventory.get(INPUT_SLOT).getCount() - (activeInputCount * recipeCount));
						this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
					}
					this.activeRecipe = null;
					this.progress = 0;
					this.onChanged();
				}
			}else if(this.activeRecipe instanceof DrumQuenchingRecipe && this.progress >= ((DrumQuenchingRecipe)this.activeRecipe).getTime()) {
				if(this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY && this.canQuench) {
					this.inventory.set(OUTPUT_SLOT, this.activeRecipe.getCraftingResult(craft));
					this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
					this.activeRecipe = null;
					this.progress = 0;
					this.onChanged();
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
	
	@Nullable
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : CraftingManager.REGISTRY) {
            if (irecipe instanceof DrumItemRecipe) {
            	//System.out.println("Recipe Matches : " + irecipe.matches(craftMatrix, worldIn) + ", Recipe fluid: " + ((DrumItemRecipe)irecipe).getFluid().getFluid().getName() + ", Tank Fluid: " + this.getTank().getFluid().getFluid().getName());
            	if(((DrumItemRecipe)irecipe).getFluid().getFluid() == this.getTank().getFluid().getFluid() && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }else if (irecipe instanceof DrumQuenchingRecipe && ConfigHandler.ENABLE_QUENCHING) {
            	//System.out.println("Quenching Recipe Matches : " + irecipe.matches(craftMatrix, worldIn) + ", Recipe fluid: " + ((DrumQuenchingRecipe)irecipe).getFluid().getFluid().getName() + ", Tank Fluid: " + this.getTank().getFluid().getFluid().getName());
            	if(((DrumQuenchingRecipe)irecipe).getFluid().getFluid() == this.getTank().getFluid().getFluid() && irecipe.matches(craftMatrix, worldIn)) {
            		//this.canQuench = worldIn.getBlockState(this.pos.offset(EnumFacing.DOWN)).getBlock() == Blocks.FIRE;
            		return irecipe;
            	}
            }
        }

        return null;
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
