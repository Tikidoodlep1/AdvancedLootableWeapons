package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AlloyingRecipe extends ShapelessOreRecipe {
	
	final float exp;
	
	public AlloyingRecipe(ResourceLocation group, NonNullList<Ingredient> input, float exp, ItemStack result) {
		super(group, input, result);
		this.exp = exp;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> keptItems = super.getRemainingItems(inv);
		
		for(int i = 0; i < this.input.size(); i++) {
			Ingredient in = this.input.get(i);
			for(ItemStack stack : in.getMatchingStacks()) {
				if(stack.getItem() == inv.getStackInSlot(i).getItem() && stack.getCount() < inv.getStackInSlot(i).getCount()) {
					if(keptItems.get(i) == ItemStack.EMPTY) {
						ItemStack slot = inv.getStackInSlot(i);
						slot.setCount(inv.getStackInSlot(i).getCount() - stack.getCount());
						keptItems.set(i, slot);
					}
				}
			}
		}
		return keptItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		if(this.input.size() > 2) {
			return false;
		}
		
		
		for(int i = 0; i < this.input.size(); i++) {
			boolean match = false;
			Ingredient in = this.input.get(i);
			for(ItemStack stack : in.getMatchingStacks()) {
				if(stack.getItem() == inv.getStackInSlot(i).getItem() && stack.getCount() <= inv.getStackInSlot(i).getCount()) {
					match = true;
				}
			}
			if(!match) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		return super.getCraftingResult(inv);
	}
	
	@Override
	public String getGroup() {
		return group == null ? "" : group.toString();
	}
	
	public float getExp() {
		return this.exp;
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = NonNullList.create();
			for(final JsonElement element : JsonUtils.getJsonArray(json, "ingredients")) {
				ingredients.add(CraftingHelper.getIngredient(element, context));
			}
			
			final float exp = JsonUtils.getFloat(json, "exp");
			
			if(ingredients.isEmpty()) {
				throw new JsonParseException("No ingredients found, 1 needed");
			}else if(ingredients.size() > 2) {
				throw new JsonParseException("There have to be less than 3 ingredients");
			}
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new AlloyingRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, exp, result);
		}
		
	}

}
