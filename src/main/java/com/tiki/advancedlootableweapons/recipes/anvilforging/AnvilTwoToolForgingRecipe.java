package com.tiki.advancedlootableweapons.recipes.anvilforging;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilTwoToolForgingRecipe extends AbstractAnvilForgingRecipe {

	public AnvilTwoToolForgingRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, Ingredient ingredient2, ItemStack pResult,int xp) {
		super(ModRecipeTypes.ANVIL_FORGING, ModRecipeSerializers.ANVIL_TWO_TOOL_FORGING.get(), pId, pGroup, pIngredient,ingredient2, pResult, xp, true);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(RecipeWrapper pInv, Level pLevel) {
		ItemStack stackA = pInv.getItem(0);
		ItemStack stackB = pInv.getItem(1);
		return this.ingredient.test(stackA) && this.ingredient2.test(stackB);
	}

	@Override
	public ItemStack getProcessedResult(ItemStack input) {
		ItemStack copy = result.copy();
		return copy;
	}

	@Override
	public ItemStack assemble(RecipeWrapper pInv) {
		return getProcessedResult(pInv.getItem(0));
	}
}
