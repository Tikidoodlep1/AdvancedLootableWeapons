package tiki.rotn.advancedlootableweapons.recipes;

import java.util.Random;

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
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessUseDurabilityRecipe extends ShapelessOreRecipe {

	public ShapelessUseDurabilityRecipe(ResourceLocation group, NonNullList<Ingredient> input, ItemStack result) {
		super(group, input, result);
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> keptItems = super.getRemainingItems(inv);
		for(int i = 0; i < keptItems.size(); i++) {
			final ItemStack input = keptItems.get(i);
			if(!input.isEmpty() && input.getItem().isDamageable()) {
				input.setItemDamage(inv.getStackInSlot(i).getItemDamage());
				input.attemptDamageItem(1, new Random(), null);
				keptItems.set(i, input);
			}
		}
		return keptItems;
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
				throw new JsonParseException("No ingredients found");
			}
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ShapelessUseDurabilityRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
		
	}

}
