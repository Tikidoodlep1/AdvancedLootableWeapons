package tiki.rotn.advancedlootableweapons.blocks.tileentities;

import javax.annotation.Nullable;

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
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.init.ItemInit;
import tiki.rotn.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

public class TileEntityTanningRack extends TileEntity implements ITickable, IInventory {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	private int progress = 0;
	private final Container tempContainer = new Container() {
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) {
			return true;
		}
	};
	private InventoryCrafting craft = new InventoryCrafting(tempContainer, 2, 1);
	private IRecipe recipe = null;
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_tanning_rack";
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.tanning_rack");
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
		
		recipe = findMatchingRecipe(this.inventory, this.getWorld());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.progress = compound.getInteger("progress");
		this.recipe = CraftingManager.REGISTRY.getObject(new ResourceLocation(compound.getString("recipe")));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("progress", this.progress);
		
		if(this.recipe != null) {
			compound.setString("recipe", this.recipe.getRegistryName().toString());
		}
		
		
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
			if(this.recipe != null) {
				++this.progress;
				if(this.progress >= 1200 && (this.inventory.get(1).getItem() == recipe.getCraftingResult(craft).getItem() || this.inventory.get(1) == ItemStack.EMPTY) ) {
					NonNullList<ItemStack> leftovers = ((ShapelessOneSlotRecipes)recipe).getRemainingItems(this.inventory);
					this.inventory.set(0, leftovers.get(0));
					ItemStack result = recipe.getCraftingResult(craft);
					if(this.inventory.get(1).getItem() == result.getItem()) {
						this.inventory.get(1).setCount(this.inventory.get(1).getCount() + result.getCount());
					}else if(this.inventory.get(1) == ItemStack.EMPTY) {
						this.inventory.set(1, result);
					}
					this.progress = 0;
					this.recipe = findMatchingRecipe(this.inventory, this.getWorld());
				}
			}else {
				this.progress = 0;
			}
		}
	}
	
	@Nullable
    private IRecipe findMatchingRecipe(NonNullList<ItemStack> craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes)
            {
            	//System.out.println("matches: " + ((ShapelessOneSlotRecipes)irecipe).matches(craftMatrix, worldIn));
            	if(((ShapelessOneSlotRecipes)irecipe).block == BlockInit.tanning_rack && ((ShapelessOneSlotRecipes)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
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
		if(index == 0 && stack.getItem() == ItemInit.DELIMED_HIDE) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getGuiID() {
		return "advancedlootableweapons:tanning_rack";
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
}
