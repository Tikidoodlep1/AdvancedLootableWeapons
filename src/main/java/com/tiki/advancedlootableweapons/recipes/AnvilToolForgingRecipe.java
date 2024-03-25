package com.tiki.advancedlootableweapons.recipes;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class AnvilToolForgingRecipe extends AbstractAnvilForgeingRecipe {

	public AnvilToolForgingRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult) {
		super(ModRecipeTypes.ANVIL_FORGING, RecipeInit.ANVIL_TOOL_FORGING_RECIPE.get(), pId, pGroup, pIngredient, pResult);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(Container pInv, Level pLevel) {
		return this.ingredient.test(pInv.getItem(0));
	}

	@Override
	public ItemStack assemble(Container pInv) {
		return super.assemble(pInv);
	}
}
