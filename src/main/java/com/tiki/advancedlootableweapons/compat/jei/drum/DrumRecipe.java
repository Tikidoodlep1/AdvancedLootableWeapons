package com.tiki.advancedlootableweapons.compat.jei.drum;

import java.awt.Color;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class DrumRecipe implements IRecipeWrapper {

	private final List<List<ItemStack>> inputs;
	private final ItemStack output;
	private final int ticks;
	public final boolean quench;
	
	public DrumRecipe(List<List<ItemStack>> inputs, ItemStack output, int ticks, boolean quench) {
		this.inputs = inputs;
		this.output = output;
		this.ticks = ticks;
		this.quench = quench;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		String ticks = "Ticks: " + this.ticks;
		FontRenderer fr = minecraft.fontRenderer;
		fr.drawString(ticks, recipeWidth - (fr.getStringWidth(ticks)), 0, Color.GRAY.getRGB());
		if(quench) {
			fr.drawString("Fire Required", recipeWidth - fr.getStringWidth("Fire Required"), 15, Color.GRAY.getRGB());
		}
	}
}
