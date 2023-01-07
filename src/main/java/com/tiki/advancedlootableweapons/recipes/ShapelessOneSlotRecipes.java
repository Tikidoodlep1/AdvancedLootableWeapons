package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.block.Block;
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
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessOneSlotRecipes extends ShapelessOreRecipe {
	
	public final Block block;

	public ShapelessOneSlotRecipes(ResourceLocation group, NonNullList<Ingredient> input, ItemStack result, String block) {
		super(group, input, result);
		this.block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> keptItems = super.getRemainingItems(inv);
		return keptItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if(inv.getSizeInventory() > 1) {
			return false;
		}
		if(this.input.size() > 1) {
			return false;
		}
		
		for(ItemStack i : input.get(0).getMatchingStacks()) {
			if(i.getItem() == inv.getStackInSlot(0).getItem()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		return super.getCraftingResult(inv);
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
				throw new JsonParseException("No ingredients found, 1 needed");
			}
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ShapelessOneSlotRecipes(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
		
	}

}
