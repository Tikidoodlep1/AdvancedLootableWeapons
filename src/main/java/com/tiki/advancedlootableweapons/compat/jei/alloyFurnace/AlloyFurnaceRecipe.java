package com.tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import java.awt.Color;
import java.util.List;

import com.tiki.advancedlootableweapons.blocks.recipes.AlloyFurnaceRecipes;
import com.tiki.advancedlootableweapons.compat.jei.JEICompat;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class AlloyFurnaceRecipe implements IRecipeWrapper{

	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public AlloyFurnaceRecipe(List<ItemStack> inputs, ItemStack output) {
		this.inputs = inputs;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		AlloyFurnaceRecipes recipes = AlloyFurnaceRecipes.getInstance();
		float exp = recipes.getAlloyingExperience(output);
		
		if(exp > 0){
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", exp);
			FontRenderer renderer = minecraft.fontRenderer;
			int StringWidth = renderer.getStringWidth(expString);
			renderer.drawString(expString, recipeWidth - 28, 46, Color.GRAY.getRGB());
		}
	}
}
