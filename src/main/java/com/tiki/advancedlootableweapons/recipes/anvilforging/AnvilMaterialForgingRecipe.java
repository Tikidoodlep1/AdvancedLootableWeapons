package com.tiki.advancedlootableweapons.recipes.anvilforging;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilMaterialForgingRecipe extends AbstractAnvilForgingRecipe {

	public AnvilMaterialForgingRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient,Ingredient ingredient2, ItemStack pResult) {
		super(ModRecipeTypes.ANVIL_FORGING, RecipeInit.ANVIL_MATERIAL_FORGING_RECIPE.get(), pId, pGroup, pIngredient,ingredient2, pResult);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(RecipeWrapper pInv, Level pLevel) {
		return this.ingredient.test(pInv.getItem(0)) && pInv.getItem(1).isEmpty();
	}
}