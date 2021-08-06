package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;

public class AnvilForgingRecipeCategory extends AbstractAnvilForgingRecipeCategory<AnvilForgingRecipe> {
	private final IDrawable background;
	private final String name;
	
	public AnvilForgingRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 51, 28, 87, 45);
		name = "Anvil Forging";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AnvilForgingRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 4, 4);
		stacks.init(input2, true, 4, 24);
		stacks.init(output, false, 62, 14);
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
	public String getUid() {
		return RecipeCategories.ANVILFORGING;
	}

	@Override
	public String getModName() {
		return ModInfo.NAME;
	}
}
