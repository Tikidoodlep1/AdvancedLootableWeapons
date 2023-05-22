package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tiki.advancedlootableweapons.compat.jei.JEICompat;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class AnvilForgingRecipe implements IRecipeWrapper {
	private final NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
	private final ItemStack output;
	private final int exp;
	private final String buttonName;
	
	public AnvilForgingRecipe(NonNullList<Ingredient> inputs, ItemStack output, int exp, String buttonName) {
		
		for(int i = 0; i < this.inputs.size(); i++) {
			this.inputs.set(i, inputs.get(i));
		}
		ItemStack out = output.copy();
		if(ConfigHandler.ENABLE_QUENCHING && output.getItem() instanceof ToolSlashSword || output.getItem() instanceof ToolStabSword) {
			for(ItemStack stack : inputs.get(0).getMatchingStacks()) {
				NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
				tag.setBoolean("quenched", true);
				stack.setTagCompound(tag);
			}
		}
		this.output = out;
		this.exp = exp;
		this.buttonName = buttonName;
	}
	
	public ItemStack getoutput(){
		return this.output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> inputList = new ArrayList<List<ItemStack>>();
		for(Ingredient i : this.inputs) {
			inputList.add(Arrays.stream(i.getMatchingStacks()).collect(Collectors.toList()));
		}
		ingredients.setInputLists(VanillaTypes.ITEM, inputList);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer renderer = minecraft.fontRenderer;
		float stringWidth = renderer.getStringWidth("Button: " + this.buttonName) * 0.8F;
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.8F, 0.8F, 0.8F);
		renderer.drawString("Button: " + this.buttonName, (recipeWidth / 2) - (int)(stringWidth / 2), 2, Color.GRAY.getRGB());
		GlStateManager.popMatrix();
		
		if(exp > 0){
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience") + exp;
			renderer.drawString(expString, recipeWidth - 26, 48, Color.GRAY.getRGB());
		}
		
		/*
		if(ConfigHandler.ENABLE_QUENCHING && this.output.getItem() instanceof ToolSlashSword || this.getoutput().getItem() instanceof ToolStabSword) {
			renderer.drawString("Weapon head must be Quenched!", (recipeWidth / 2) - (renderer.getStringWidth("Weapon head must be Quenched!") / 2), recipeHeight - 9, Color.GRAY.getRGB());
		}
		*/
	}
}
