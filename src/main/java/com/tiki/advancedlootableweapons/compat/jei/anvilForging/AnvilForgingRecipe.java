package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.awt.Color;
import java.util.List;
import java.util.Map.Entry;

import com.tiki.advancedlootableweapons.compat.jei.JEICompat;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ForgeWeaponRecipes;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AnvilForgingRecipe implements IRecipeWrapper{
	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public AnvilForgingRecipe(List<ItemStack> inputs, ItemStack output) {
		this.inputs = inputs;
		this.output = output;
		
	}
	
	public ItemStack getoutput(){
		return this.output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		ForgeWeaponRecipes recipes = new ForgeWeaponRecipes();
		float exp = -1;
		for(Entry<Item, Integer> entry : recipes.getExpValues().entrySet()) {
			if(output.getItem() == entry.getKey()){
				exp = entry.getValue();
			}
		}
		
		if(exp > 0){
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", exp);
			FontRenderer renderer = minecraft.fontRenderer;
			renderer.drawString(expString, recipeWidth - 30, 16, Color.GRAY.getRGB());
		}
	}
}
