package com.tiki.advancedlootableweapons.compat.jei.jawCrusher;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class JawCrusherRecipe implements IRecipeWrapper {

	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public JawCrusherRecipe(List<ItemStack> inputs, ItemStack output) {
		this.inputs = inputs;
		this.output = output;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
}
