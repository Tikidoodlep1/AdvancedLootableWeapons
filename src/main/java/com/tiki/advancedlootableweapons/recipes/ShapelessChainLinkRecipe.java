package com.tiki.advancedlootableweapons.recipes;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessChainLinkRecipe extends ShapelessOreRecipe {
	
	private final NonNullList<Ingredient> inputs;
	private final Item material;
	
	ShapelessChainLinkRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, ItemStack result, Item material) {
		super(group, input, result);
		this.inputs = input;
		this.material = material;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		
		return remainingItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		int matches = 0;
		
		inventory: for(int i = 0; i < inv.getSizeInventory(); i++) {
			//Found an item in the inventory
			if(!inv.getStackInSlot(i).isEmpty()) {
				Iterator<Ingredient> iter = inputs.iterator();
				//Checking all input ingredients against found item
				//System.out.println("Input item " + inv.getStackInSlot(i).getItem());
				while(iter.hasNext()) {
					Ingredient ingr = iter.next();
					for(ItemStack is : ingr.getMatchingStacks()) {
						//We have a match
						if(is.getItem() == inv.getStackInSlot(i).getItem()) {
							//System.out.println("Ingredient item " + is.getItem());
							NBTTagCompound nbt = inv.getStackInSlot(i).getTagCompound();
							if(nbt != null && nbt.hasKey("Material")) {
								//System.out.println("Material: " + nbt.getCompoundTag("Material"));
								if(new ItemStack(nbt.getCompoundTag("Material")).getItem() == this.material) {
									matches++;
									continue inventory;
								}else {
									return false;
								}
							}
						}
					}
				}
				if(matches == inputs.size()) {
					break inventory;
				}
				return false;
			}
			
			
		}
		
		if(matches != inputs.size()) {
			return false;
		}

		return true;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		final Item[] mats = new Item[super.getIngredients().size()];
		final ItemStack result = super.getCraftingResult(inv);
		int j = 0;
		
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			final ItemStack input = inv.getStackInSlot(i);
			
			if(!input.isEmpty()) {
				NBTTagCompound tag = input.getTagCompound();
				if(tag != null && tag.hasKey("Material")) {
					mats[j++] = new ItemStack(tag.getCompoundTag("Material")).getItem();
				}
			}
		}
		
		for(int i = 1; i < mats.length; i++) {
			if(mats[i] == null) {
				return ItemStack.EMPTY;
			}else if(mats[i] != mats[i-1]) {
				return ItemStack.EMPTY;
			}
		}
		
		return result;
	}
	
	@Override
	public String getGroup() {
		return group == null ? "" : group.toString();
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = NonNullList.create();
			for(final JsonElement element : JsonUtils.getJsonArray(json, "ingredients")) {
				ingredients.add(CraftingHelper.getIngredient(element, context));
			}
			
			if(ingredients.isEmpty()) {
				throw new JsonParseException("No ingredients found");
			}
			final Item material = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "material"), context).getItem();
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ShapelessChainLinkRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result, material);
		}
		
	}
}
