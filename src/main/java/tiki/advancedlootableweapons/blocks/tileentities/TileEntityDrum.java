package tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.handlers.SoundHandler;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.items.ItemHotToolHead;
import tiki.advancedlootableweapons.particle.ParticleAirBubble;
import tiki.advancedlootableweapons.recipes.DrumItemRecipe;
import tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;

public class TileEntityDrum extends TileFluidHandler implements ITickable, ISidedInventory, IFluidHandler
{
	public static final int INPUT_SLOT = 0;
	public static final int ADDITIVE_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	private static final Random rand = new Random();
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private int progress = 0;
	private boolean canQuench = false;
	private IRecipe activeRecipe = null;
	public boolean needsBubbles = false;
	public boolean needsQuench = false;
	
	@SideOnly(Side.CLIENT)
	private boolean clientHasPlayedQuenchSound = false;
	
	private Container tempContainer = new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return false;
		}
	};
	private InventoryCrafting craft = new InventoryCrafting(tempContainer, 3, 1);
	
	public void FluidInteraction(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			if(this.getTank().getFluidAmount() <= 0) {
				if(!this.inventory.get(ADDITIVE_SLOT).isEmpty()) {
					EntityItem entityitem = new EntityItem(
							worldIn, 
							this.pos.getX() + (rand.nextFloat() * 0.8F + 0.1F), 
							this.pos.getY() + (rand.nextFloat() * 0.8F + 0.1F), 
							this.pos.getZ() + (rand.nextFloat() * 0.8F + 0.1F), 
							this.inventory.get(ADDITIVE_SLOT));
		            entityitem.motionX = rand.nextGaussian() * 0.05000000074505806D;
		            entityitem.motionY = rand.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
		            entityitem.motionZ = rand.nextGaussian() * 0.05000000074505806D;
		            worldIn.spawnEntity(entityitem);
				}
				this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
			}
			this.onChanged();
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
				this.progress = 0;
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
	
	public boolean hasActiveRecipe() {
		return this.activeRecipe != null;
	}
	
	public IRecipe getActiveRecipe() {
		return this.activeRecipe;
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
	public NBTTagCompound getUpdateTag() { // server side
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
		
		compound.setBoolean("bubbles", this.needsBubbles);
		compound.setBoolean("quench", this.needsQuench);
		compound.setInteger("progress", this.progress);
		return compound;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) { // client side
		if(tag.hasKey("Fluid")) {
			this.getTank().readFromNBT(tag.getCompoundTag("Fluid"));
		}
		ItemStackHelper.loadAllItems(tag, this.inventory);
		
		this.needsBubbles = tag.getBoolean("bubbles");
		this.needsQuench = tag.getBoolean("quench");
		this.progress = tag.getInteger("progress");
		super.handleUpdateTag(tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) { // client side
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() { // server side
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), this.getUpdateTag());
	}
	
	//@SideOnly(Side.SERVER)
	public void onChanged() {
		IBlockState state = this.world.getBlockState(pos);
		this.markDirty();
		this.world.notifyBlockUpdate(pos, state, state, 3);
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
		if(!this.world.isRemote) {
			if(this.activeRecipe != null) {
				boolean markChanged = false;
				if(this.activeRecipe instanceof DrumQuenchingRecipe && this.canQuench) {
					++this.progress;
					if(!this.needsQuench) {
						this.needsQuench = true;
						markChanged = true;
					}
				}else if(this.activeRecipe instanceof DrumQuenchingRecipe && !this.canQuench) {
					Block under = this.getWorld().getBlockState(this.pos.offset(EnumFacing.DOWN)).getBlock();
					Fluid f = FluidRegistry.lookupFluidForBlock(under);
					boolean fluid = f != null && f.getTemperature() >= FluidRegistry.LAVA.getTemperature() - 500;
					if(this.canQuench != (under == Blocks.FIRE || under == Blocks.LAVA || under == Blocks.FLOWING_LAVA || fluid)) {
						this.canQuench = under == Blocks.FIRE || under == Blocks.LAVA || under == Blocks.FLOWING_LAVA || fluid;
						markChanged = true;
					}
				}else if(this.activeRecipe instanceof DrumItemRecipe) {
					++this.progress;
					if(!this.needsBubbles) {
						this.needsBubbles = true;
						markChanged = true;
					}
				}
				
				if(this.progress == 6) {
					markChanged = true;
				}
				
				if(this.activeRecipe instanceof DrumItemRecipe && this.progress >= ((DrumItemRecipe)this.activeRecipe).getTime()) {
					if(this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY) {
						int activeInputCount = ((DrumItemRecipe)this.activeRecipe).getInputCount(craft);
						int outputCount = (64 - this.inventory.get(OUTPUT_SLOT).getCount()) / this.activeRecipe.getCraftingResult(craft).getCount();
						int inputCount = this.inventory.get(INPUT_SLOT).getCount() / activeInputCount;
						int recipeCount = inputCount > outputCount ? outputCount : inputCount;
						this.inventory.set(OUTPUT_SLOT, new ItemStack(this.activeRecipe.getCraftingResult(craft).getItem(), this.activeRecipe.getCraftingResult(craft).getCount() * recipeCount));
						if(this.inventory.get(INPUT_SLOT).getCount() - (activeInputCount * recipeCount) <= 0) {
							this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
							this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
						}else {
							this.inventory.get(INPUT_SLOT).setCount(this.inventory.get(INPUT_SLOT).getCount() - (activeInputCount * recipeCount));
							this.inventory.set(ADDITIVE_SLOT, ItemStack.EMPTY);
						}
						this.activeRecipe = null;
						this.progress = 0;
						this.needsBubbles = false;
						markChanged = true;
					}
				}else if(this.activeRecipe instanceof DrumQuenchingRecipe && this.progress >= ((DrumQuenchingRecipe)this.activeRecipe).getTime()) {
					if(this.inventory.get(OUTPUT_SLOT) == ItemStack.EMPTY && this.canQuench) {
						this.inventory.set(OUTPUT_SLOT, this.activeRecipe.getCraftingResult(craft));
						this.inventory.set(INPUT_SLOT, ItemStack.EMPTY);
						this.activeRecipe = null;
						this.progress = 0;
						this.needsQuench = false;
						markChanged = true;
					}
				}
				
				if(markChanged) {
					this.onChanged();
				}
			}else {
				boolean markChanged = false;
				if(this.needsBubbles) {
					this.needsBubbles = false;
					markChanged = true;
				}
				if(this.needsQuench) {
					this.needsQuench = false;
					markChanged = true;
				}
				
				if(markChanged) {
					this.onChanged();
				}
			}
		}else {
			if(this.needsBubbles) {
				if(rand.nextFloat() >= 0.8) {
					Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleAirBubble(
							this.getWorld(), 
							this.getPos().getX() + 0.25f + (rand.nextDouble() / 2d), 
							this.getPos().getY() + 0.1875f,
							this.getPos().getZ() + 0.25f + (rand.nextDouble() / 2d), 
							0,  
							0.02 + (0.02 * rand.nextDouble()), 
							0,
							this.getPos().getY() + 0.875));
					if(rand.nextFloat() >= 0.8) {
						Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleAirBubble(
								this.getWorld(), 
								this.getPos().getX() + 0.25 + (rand.nextDouble() / 2d), 
								this.getPos().getY() + 0.1875, 
								this.getPos().getZ() + 0.25 + (rand.nextDouble() / 2d), 
								0, 
								0.02 + (0.02 * rand.nextDouble()), 
								0,
								this.getPos().getY() + 0.875));
					}
				}
			}else if(this.needsQuench) {
				world.spawnParticle(
						EnumParticleTypes.SPIT, 
						this.getPos().getX() + 0.5 + (rand.nextDouble() - 0.5), 
						this.getPos().getY() + 0.85 + (rand.nextDouble() - 0.5), 
						this.getPos().getZ() + 0.5 + (rand.nextDouble() - 0.5), 
						(rand.nextDouble() + 0.1) / 50d, 
						rand.nextDouble() * 0.2, 
						(rand.nextDouble() + 0.1) / 50d);
				if(this.progress == 6 && !this.clientHasPlayedQuenchSound) {
					world.playSound(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), SoundHandler.QUENCH, SoundCategory.BLOCKS, 0.8f, 1.0f, false);
					this.clientHasPlayedQuenchSound = true;
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

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch(side) {
		case DOWN:
			return new int[] {OUTPUT_SLOT};
		case UP:
			return new int[] {INPUT_SLOT};
		default:
			return new int[] {ADDITIVE_SLOT};
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if(direction == EnumFacing.DOWN) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(direction != EnumFacing.DOWN) {
			return false;
		}
		return true;
	}
	
	IItemHandler handlerAdd = new SidedInvWrapper(this, EnumFacing.EAST);
    IItemHandler handlerOutput = new SidedInvWrapper(this, EnumFacing.DOWN);
    IItemHandler handlerInput = new SidedInvWrapper(this, EnumFacing.UP);

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    	return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        	if(facing == EnumFacing.DOWN) {
        		return (T) handlerOutput;
        	}else if(facing == EnumFacing.UP) {
        		return (T) handlerInput;
        	}else {
        		return (T) handlerAdd;
        	}
        }else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) tank;
        }
        return super.getCapability(capability, facing);
    }
    
}
