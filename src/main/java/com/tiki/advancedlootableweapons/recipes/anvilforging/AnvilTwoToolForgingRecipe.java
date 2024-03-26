package com.tiki.advancedlootableweapons.recipes.anvilforging;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilTwoToolForgingRecipe extends AbstractAnvilForgingRecipe {

	public AnvilTwoToolForgingRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, Ingredient ingredient2, ItemStack pResult) {
		super(ModRecipeTypes.ANVIL_FORGING, RecipeInit.ANVIL_TWO_TOOL_FORGING_RECIPE.get(), pId, pGroup, pIngredient,ingredient2, pResult);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(RecipeWrapper pInv, Level pLevel) {
		ItemStack stackA = pInv.getItem(0);
		ItemStack stackB = pInv.getItem(1);
		return this.ingredient.test(stackA) && this.ingredient2.test(stackB) && HotToolHeadItem.isSameMaterial(stackA,stackB);
	}

	@Override
	public ItemStack getProcessedResult(ItemStack input) {
		ItemStack copy = result.copy();
		copy.setTag(input.getTag());
		return copy;
	}

	@Override
	public ItemStack assemble(RecipeWrapper pInv) {
		return getProcessedResult(pInv.getItem(0));
	}
}
