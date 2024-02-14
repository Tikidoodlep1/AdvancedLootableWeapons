package tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

public class TileEntityMill extends TileEntity implements ITickable, ISidedInventory {
	
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	private Random rand = new Random();
	private int crushedContents = 0;
	private int rotate = 0;
	private int rotation = 0;
	
	public boolean crushContents() {
		ShapelessOneSlotRecipes recipe = findMatchingRecipe(this.inventory, this.getWorld());
		if(recipe != null) {
			NonNullList<ItemStack> leftovers = recipe.getRemainingItems(this.inventory);
			ItemStack result = recipe.getCraftingResult(this.inventory);
			int count = result.getCount() + rand.nextInt(3) - 1;
			result.setCount(count < 1 ? 1 : count);
			if((result.getItem() == this.inventory.get(1).getItem() && this.inventory.get(1).getCount() + result.getCount() < this.getInventoryStackLimit()) || this.inventory.get(1) == ItemStack.EMPTY) {
				for(int i = 0; i < this.inventory.size(); i++) {
					if(i == 1) {
						continue; // Skip outputs to avoid removing previous stack stuff
					}
					ItemStack is = leftovers.get(i);
					this.inventory.set(i, is);
				}
				
				if(this.inventory.get(1).getItem() == result.getItem()) {
					result.setCount(result.getCount() + this.inventory.get(1).getCount());
				}
				
				this.setInventorySlotContents(1, result);
				this.crushedContents = count;
				this.rotate = 90;
				return true;
			}
		}
		
		return false;
	}
	
	@Nullable
    private ShapelessOneSlotRecipes findMatchingRecipe(NonNullList<ItemStack> craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes)
            {
            	if(((ShapelessOneSlotRecipes)irecipe).block == BlockInit.mill && ((ShapelessOneSlotRecipes)irecipe).matches(craftMatrix, worldIn)) {
            		return (ShapelessOneSlotRecipes)irecipe;
            	}
            }
        }

        return null;
    }
	
	public int getRotation() {
		return this.rotation;
	}
	
	@Override
	public void update() {
		if(rotate > 0) {
			rotation++;
			if(rotation >= 360) {
				rotation = 0;
			}
			this.rotate--;
		}
	}
	
	@Override
	public boolean hasFastRenderer() {
		return false;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_mill";
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.mill");
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
		
		if(stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		
		this.inventory.set(index, stack);
		if(!flag) {
			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.rotation = compound.getInteger("rot");
		this.rotate = compound.getInteger("rotLeft");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("rot", this.rotation);
		compound.setInteger("rotLeft", this.rotate);
		
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
		if((stack.getItem() == Item.getItemFromBlock(Blocks.STONE) && (stack.getItemDamage() == 1 || stack.getItemDamage() == 3)) || stack.getItem() == Item.getItemFromBlock(BlockInit.cobble_feldspar) || stack.getItem() == Item.getItemFromBlock(BlockInit.rock_feldspar)) {
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		switch(id) {
			case 0:
				return this.crushedContents;
			case 1:
				return this.rotation;
			case 2:
				return this.rotate;
			default:
				return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
			case 0:
				this.crushedContents = value;
				break;
			case 1:
				this.rotation = value;
				break;
			case 2:
				this.rotate = value;
				break;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch(side) {
		case DOWN:
			return new int[] {1};
		default:
			return new int[] {0};
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if(direction != EnumFacing.DOWN) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(direction == EnumFacing.DOWN) {
			return true;
		}
		return false;
	}

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        return super.getCapability(capability, facing);
    }
}
