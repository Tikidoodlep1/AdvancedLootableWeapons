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
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ClayCoveringRecipe extends ShapelessOreRecipe {

	//private final NonNullList<Ingredient> inputs;
	private final ItemStack result;
	
	ClayCoveringRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
		super(group, input, result);
		//this.inputs = input;
		this.result = result;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		return remainingItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		return super.matches(inv, world);
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			if(!inv.getStackInSlot(i).isEmpty() && inv.getStackInSlot(i).hasTagCompound()) {
				NBTTagCompound tag = inv.getStackInSlot(i).getTagCompound();
				tag.setBoolean("clay", true);
				result.setTagCompound(tag);
				result.setItemDamage(inv.getStackInSlot(i).getItemDamage());
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("clay", true);
		ItemStack result = this.result.copy();
		result.setTagCompound(tag);
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
			
			ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ClayCoveringRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
		
	}
}
