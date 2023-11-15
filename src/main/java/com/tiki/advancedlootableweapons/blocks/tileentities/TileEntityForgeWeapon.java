package com.tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.items.ItemArmorBinding;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemUnboundArmor;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorBindingRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorPlateRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolRecipe;
import com.tiki.advancedlootableweapons.util.HotMetalHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TileEntityForgeWeapon extends TileEntity implements ITickable, IInventory {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private int button;
	private int toolForgeHammer;
	private IRecipe recipe = null;
	
	public TileEntityForgeWeapon() {
		this.button = -1;
	}
	
	public boolean forgeItem(EntityPlayer player) {
		recipe = this.findMatchingRecipe(this.inventory, this.world);
		
//		if(recipe != null) {
//			System.out.println("Recipe Name: " + recipe.getRegistryName().toString());
//			System.out.println("Ingredients:" );
//			for(Ingredient i : recipe.getIngredients()) {
//				System.out.println(Arrays.toString(i.getMatchingStacks()));
//			}
//			System.out.println("Result: " + recipe.getRecipeOutput());
//		}else {
//			System.out.println("Recipe is NULL!");
//		}
		
		ItemStack outputSlot = this.getStackInSlot(2);
		if(recipe != null && outputSlot == ItemStack.EMPTY) {
			if(recipe instanceof ForgeToolHeadRecipe) {
				ForgeToolHeadRecipe headRecipe = ((ForgeToolHeadRecipe)this.recipe);
				if(ContainerForgeWeapon.getButtonIdFromName(headRecipe.getButton()) == this.button) {
					ItemStack result = headRecipe.getCraftingResult(this.inventory);
					
					NonNullList<ItemStack> remaining = headRecipe.getRemainingItems(this.inventory);
					for(int i = 0; i < remaining.size(); i++) {
						this.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(player, 1);
					this.giveExp(player, headRecipe.getExp());
					return true;
				}
			}else if(recipe instanceof ForgeToolRecipe) {
				ForgeToolRecipe toolRecipe = ((ForgeToolRecipe)this.recipe);
				if(ContainerForgeWeapon.getButtonIdFromName(toolRecipe.getButton()) == this.button) {
					ItemStack result = toolRecipe.getCraftingResult(this.inventory);
					
					NonNullList<ItemStack> remaining = toolRecipe.getRemainingItems(this.inventory);
					for(int i = 0; i < remaining.size(); i++) {
						this.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(player, 1);
					this.giveExp(player, toolRecipe.getExp());
					return true;
				}
			}else if(recipe instanceof ForgeArmorPlateRecipe) {
				ForgeArmorPlateRecipe toolRecipe = ((ForgeArmorPlateRecipe)this.recipe);
				if(ContainerForgeWeapon.getButtonIdFromName(toolRecipe.getButton()) == this.button) {
					ItemStack result = toolRecipe.getCraftingResult(this.inventory);
					
					NonNullList<ItemStack> remaining = toolRecipe.getRemainingItems(this.inventory);
					for(int i = 0; i < remaining.size(); i++) {
						this.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(player, 1);
					this.giveExp(player, toolRecipe.getExp());
					return true;
				}
			}else if(recipe instanceof ForgeArmorBindingRecipe) {
				ForgeArmorBindingRecipe bindingRecipe = ((ForgeArmorBindingRecipe)this.recipe);
				if(ContainerForgeWeapon.getButtonIdFromName(bindingRecipe.getButton()) == this.button) {
					ItemStack result = bindingRecipe.getCraftingResult(this.inventory);
					
					NonNullList<ItemStack> remaining = bindingRecipe.getRemainingItems(this.inventory);
					for(int i = 0; i < remaining.size(); i++) {
						this.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(player, 1);
					return true;
				}
			}
		}
		return false;
	}
	
	private void giveExp(EntityPlayer player, int exp) {
		player.addExperience(exp);
		System.out.println("Giving " + exp + " from Forge Weapons");
	}
	
	private void damageForgeHammer(EntityPlayer player, int amount) {
		ItemStack forgeHammer = player.inventory.getStackInSlot(toolForgeHammer);
		if(forgeHammer.attemptDamageItem(amount, new Random(), null)) {
			player.inventory.deleteStack(forgeHammer);
			Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
			this.toolForgeHammer = -1;
		}
	}
	
	@Nullable
    private IRecipe findMatchingRecipe(NonNullList<ItemStack> craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ForgeToolHeadRecipe)
            {
            	if( ContainerForgeWeapon.getButtonIdFromName(((ForgeToolHeadRecipe)irecipe).getButton()) == this.button && ((ForgeToolHeadRecipe)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeToolRecipe)
            {
            	if( ContainerForgeWeapon.getButtonIdFromName(((ForgeToolRecipe)irecipe).getButton()) == this.button && ((ForgeToolRecipe)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeArmorPlateRecipe)
            {
            	if( ContainerForgeWeapon.getButtonIdFromName(((ForgeArmorPlateRecipe)irecipe).getButton()) == this.button && ((ForgeArmorPlateRecipe)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeArmorBindingRecipe)
            {
            	if( ContainerForgeWeapon.getButtonIdFromName(((ForgeArmorBindingRecipe)irecipe).getButton()) == this.button && ((ForgeArmorBindingRecipe)irecipe).matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
    }
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container_forge_weapon";
	}
	
	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.forge_weapon");
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}
	
	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.inventory) {
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
		if((index == 0 || index == 1) && !flag) {
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void update() {
		if(!this.world.isRemote) {
			for(int i = 0; i < this.getSizeInventory(); i++) {
				if(this.inventory.get(i).getItem() instanceof ItemHotToolHead) {
					ItemStack stack = this.inventory.get(i);
					Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
					if(stack.getItemDamage() > 0) {
						stack.setItemDamage(stack.getItemDamage() - (int)(HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.BASIC_FORGE_TEMP, stack.getItemDamage()) * ConfigHandler.TOOL_HEAD_HEATING_MULTIPLIER));
					}
				}
			}
		}
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
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
		switch(index) {
		case 0:
			return stack.getItem() instanceof ItemHotToolHead || stack.getItem() instanceof ItemUnboundArmor || 
					ItemInit.acceptedForgeItems.contains(stack.getItem());
		case 1:
			return stack.getItem() instanceof ItemHotToolHead || stack.getItem() instanceof ItemArmorBinding || 
					ItemInit.acceptedForgeItems.contains(stack.getItem());
		case 2:
			return false;
		}
		return false;
	}
	
	@Override
	public int getField(int id) {
		switch(id) {
			case 0:
				return this.button;
			case 1:
				return this.toolForgeHammer;
			default:
				return 0;
		}
	}
	
	@Override
	public void setField(int id, int value) {
		switch(id) {
			case 0:
				this.button = value;
				break;
			case 1:
				this.toolForgeHammer = value;
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
