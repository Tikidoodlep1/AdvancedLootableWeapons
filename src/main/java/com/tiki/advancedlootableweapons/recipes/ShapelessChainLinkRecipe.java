package com.tiki.advancedlootableweapons.recipes;

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
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import scala.actors.threadpool.Arrays;

public class ShapelessChainLinkRecipe extends ShapelessOreRecipe {
	
	private final String material;
	
	ShapelessChainLinkRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, ItemStack result, String material) {
		super(group, input, result);
		this.material = material;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		
		return remainingItems;
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
			if(!mats[i].equalsIgnoreCase(mats[i-1])) {
				return ItemStack.EMPTY;
			}
		}
		
		System.out.println(material + ", " + Arrays.toString(mats));
		
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
