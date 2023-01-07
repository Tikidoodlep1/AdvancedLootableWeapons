package com.tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.compat.jei.RecipeCategories;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class AlloyFurnaceRecipeCategory extends AbstractAlloyFurnaceRecipeCategory<AlloyFurnaceRecipe>{

	private final IDrawable background;
	private final String name;
	
	public AlloyFurnaceRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 40, 12, 97, 63);
		name = "Alloy Furnace";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlloyFurnaceRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 4, 4);
		stacks.init(input2, true, 27, 4);
		stacks.init(input3, true, 16, 41);
		stacks.init(output, false, 75, 23);
		stacks.set(ingredients);
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedFlame.draw(minecraft, 17, 25);
		animatedArrow.draw(minecraft, 40, 24);
	}

	@Override
	public String getUid() {
		return RecipeCategories.ALLOYFURNACE;
	}

	@Override
	public String getModName() {
		return ModInfo.NAME;
	}
	
}
