package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ForgeToolHeadRecipe extends ShapelessOreRecipe {
	
	//private final NonNullList<Ingredient> inputs;
	private final String button;
	private final int exp;
	public final Block block;
	
	public ForgeToolHeadRecipe(final String button, final NonNullList<Ingredient> inputs, int exp, ItemStack result, Block block) {
		super(null, inputs, result);
		//this.inputs = inputs;
		this.button = button;
		this.exp = exp;
		this.block = block;
	}
	
	public NonNullList<ItemStack> getRemainingItems(final NonNullList<ItemStack> inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.size(), ItemStack.EMPTY);
		if(input.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.get(0));
		}
		if(input.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.get(1));
		}
		return remainingItems;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		if(input.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.getStackInSlot(0));
		}
		if(input.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.getStackInSlot(1));
		}
		return remainingItems;
	}
	
	public boolean matches(NonNullList<ItemStack> inv, World world) {		
		if(inv.size() != 3) {
			return false;
		}
				
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.input.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.input.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					match1 = true;
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.input.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.input.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		
		return match1 && match2 && matsMatch;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {		
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.input.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.input.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					match1 = true;
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.input.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.input.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		
		return match1 && match2 && matsMatch;
	}
	
	public ItemStack getCraftingResult(final NonNullList<ItemStack> inv) {
		ItemStack result = this.output.copy();
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		NBTTagCompound input2Tag = input2.getTagCompound();
		NBTTagCompound resultTag = new NBTTagCompound();
		
		if(isAcceptedIngot(input1)) {
			resultTag.setTag("Material", input1.serializeNBT());
			result.setItemDamage(6000);
		}else if(!input2.isEmpty() && isAcceptedIngot(input2)){
			resultTag.setTag("Material", input2.serializeNBT());
			result.setItemDamage(6000);
		}
		
		if(input1.getItem() instanceof ItemHotToolHead) {
			if(input1Tag.hasKey("addedDamage")) {
				resultTag.setDouble("addedDamage", input1Tag.getDouble("addedDamage") + checkHeat(input1));
			}else {
				resultTag.setDouble("addedDamage", checkHeat(input1));
			}
			
			if(input1Tag.hasKey("addedDurability")) {
				resultTag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(checkHeat(input1) * 100));
			}else {
				resultTag.setInteger("addedDurability", (int)(checkHeat(input1) * 100));
			}
			
			if(input1Tag.hasKey("Material")) {
				resultTag.setTag("Material", input1Tag.getTag("Material"));
			}
		}
		
		if(input.size() >= 2 && input.get(input.size() - 1) != Ingredient.EMPTY) {
			if(input2.getItem() instanceof ItemHotToolHead) {
				if(input2Tag.hasKey("addedDamage")) {
					resultTag.setDouble("addedDamage", input2Tag.getDouble("addedDamage") + checkHeat(input2));
				}else {
					resultTag.setDouble("addedDamage", checkHeat(input2));
				}
				
				if(input2Tag.hasKey("addedDurability")) {
					resultTag.setInteger("addedDurability", input2Tag.getInteger("addedDurability") + (int)(checkHeat(input2) * 100));
				}else {
					resultTag.setInteger("addedDurability", (int)(checkHeat(input2) * 100));
				}
				
				if(input2Tag.hasKey("Material")) {
					resultTag.setTag("Material", input2Tag.getTag("Material"));
				}
			}
		}
		
		int heat = 6000;
		if(input1.getItem() instanceof ItemHotToolHead && input2.getItem() instanceof ItemHotToolHead) {
			heat = (input1.getItemDamage() + input2.getItemDamage()) / 2;
		}else if(input1.getItem() instanceof ItemHotToolHead) {
			heat = input1.getItemDamage();
		}else if(input2.getItem() instanceof ItemHotToolHead) {
			heat = input2.getItemDamage();
		}
		
		result.setItemDamage(heat + 3000 > 6000 ? 6000 : heat + 3000);
		
		result.setTagCompound(resultTag);
		
		return result;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack result = super.getCraftingResult(inv);
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		NBTTagCompound input2Tag = input2.getTagCompound();
		NBTTagCompound resultTag = new NBTTagCompound();
		
		if(isAcceptedIngot(input1)) {
			resultTag.setTag("Material", input1.serializeNBT());
			result.setItemDamage(6000);
		}else if(!input2.isEmpty() && isAcceptedIngot(input2)){
			resultTag.setTag("Material", input2.serializeNBT());
			result.setItemDamage(6000);
		}
		
		if(input1.getItem() instanceof ItemHotToolHead) {
			if(input1Tag.hasKey("addedDamage")) {
				resultTag.setDouble("addedDamage", input1Tag.getDouble("addedDamage") + checkHeat(input1));
			}else {
				resultTag.setDouble("addedDamage", checkHeat(input1));
			}
			
			if(input1Tag.hasKey("addedDurability")) {
				resultTag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(checkHeat(input1) * 100));
			}else {
				resultTag.setInteger("addedDurability", (int)(checkHeat(input1) * 100));
			}
			
			if(input1Tag.hasKey("Material")) {
				resultTag.setTag("Material", input1Tag.getTag("Material"));
			}
		}
		
		if(input.size() >= 2 && input.get(input.size() - 1) != Ingredient.EMPTY) {
			if(input2.getItem() instanceof ItemHotToolHead) {
				if(input2Tag.hasKey("addedDamage")) {
					resultTag.setDouble("addedDamage", input2Tag.getDouble("addedDamage") + checkHeat(input2));
				}else {
					resultTag.setDouble("addedDamage", checkHeat(input2));
				}
				
				if(input2Tag.hasKey("addedDurability")) {
					resultTag.setInteger("addedDurability", input2Tag.getInteger("addedDurability") + (int)(checkHeat(input2) * 100));
				}else {
					resultTag.setInteger("addedDurability", (int)(checkHeat(input2) * 100));
				}
				
				if(input2Tag.hasKey("Material")) {
					resultTag.setTag("Material", input2Tag.getTag("Material"));
				}
			}
		}
		
		int heat = 6000;
		if(input1.getItem() instanceof ItemHotToolHead && input2.getItem() instanceof ItemHotToolHead) {
			heat = (input1.getItemDamage() + input2.getItemDamage()) / 2;
		}else if(input1.getItem() instanceof ItemHotToolHead) {
			heat = input1.getItemDamage();
		}else if(input2.getItem() instanceof ItemHotToolHead) {
			heat = input2.getItemDamage();
		}
		
		result.setItemDamage(heat + 3000 > 6000 ? 6000 : heat + 3000);
		
		result.setTagCompound(resultTag);
		
		return result;
	}
	
	private boolean isAcceptedIngot(ItemStack stack) {
		return ItemInit.acceptedForgeMetals.contains(stack.getItem());
	}
	
	public String getButton() {
		return this.button;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		
		return map(6000 - heat, 0, 6000, 0.0, 0.5);
	}
	
	private double map(double value, double curRangeLowBound, double curRangeHighBound, double wantRangeLowBound, double wantRangeHighBound) {
		return wantRangeLowBound + (wantRangeHighBound - wantRangeLowBound) * ((value - curRangeLowBound) / (curRangeHighBound - curRangeLowBound));
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String button = JsonUtils.getString(json, "button");
			final String block = JsonUtils.getString(json, "block", "");
			
			final NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			JsonArray ing = JsonUtils.getJsonArray(json, "ingredients");
			if(ing.size() > inputs.size()) {
				throw new JsonParseException("There cannot be more ingredients than slots to input items!");
			}
			
			for(int i = 0; i < ing.size(); i++) {
				JsonElement e = ing.get(i);
				if(e.getAsJsonObject().has("slot")) {
					final JsonElement slot = e.getAsJsonObject().get("slot");
					if(slot.getAsString().equalsIgnoreCase("metal")) {
						inputs.set(i, Ingredient.fromItems(ItemInit.acceptedForgeMetals.toArray(new Item[0])));
					}
				}else {
					inputs.set(i, CraftingHelper.getIngredient(e, context)); //Maybe: if(inputs[i] instanceof hot tool head) {check for a material listed};
				}
			}
			
			final int exp = JsonUtils.getInt(json, "exp");
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ForgeToolHeadRecipe(button, inputs, exp, result, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block)));
		}
		
	}
	
	
}
