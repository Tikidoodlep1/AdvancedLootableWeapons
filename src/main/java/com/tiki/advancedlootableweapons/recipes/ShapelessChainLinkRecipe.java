package com.tiki.advancedlootableweapons.recipes;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
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
	private final String material;
	
	ShapelessChainLinkRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, ItemStack result, String material) {
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
		NBTTagCompound nbt = new NBTTagCompound();
		boolean isMatch = false;
		Iterator<Ingredient> iter = inputs.iterator();
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack input = inv.getStackInSlot(i);
			if(!input.isEmpty()) {
				if(iter.hasNext()) {
					Ingredient ingred = iter.next();
					
					for(ItemStack is : ingred.getMatchingStacks()) {
						if(is.getItem() == input.getItem()) {
							isMatch = true;
						}
						if(!isMatch) {
							return false;
						}
					}
					nbt = input.getTagCompound();
					if(input.hasTagCompound() && nbt.hasKey("Material")) {
						if(!nbt.getString("Material").equalsIgnoreCase(this.material)) {
							return false;
						}
					}else {
						return false;
					}
				}else {
					return false;
				}
			}
		}
		return isMatch;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		final String[] mats = new String[super.getIngredients().size()];
		final ItemStack result = super.getCraftingResult(inv);
		int j = 0;
		
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			final ItemStack input = inv.getStackInSlot(i);
			
			if(!input.isEmpty()) {
				NBTTagCompound tag = input.getTagCompound();
				if(tag != null && tag.hasKey("Material")) {
					mats[j++] = tag.getString("Material");
				}
			}
		}
		
		for(int i = 1; i < mats.length; i++) {
			if(mats[i] == null) {
				return ItemStack.EMPTY;
			}
			if(!mats[i].equalsIgnoreCase(mats[i-1])) {
				return ItemStack.EMPTY;
			}
		}
		
		if(mats[0].equalsIgnoreCase(material)) {
			return result;
		}else {
			return ItemStack.EMPTY;
		}
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
			final String material = JsonUtils.getString(json, "Material");
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ShapelessChainLinkRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result, material);
		}
		
	}
}
