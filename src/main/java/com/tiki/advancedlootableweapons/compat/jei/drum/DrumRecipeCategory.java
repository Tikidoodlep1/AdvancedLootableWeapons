package com.tiki.advancedlootableweapons.compat.jei.drum;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class DrumRecipeCategory extends AbstractDrumRecipeCategory<DrumRecipe> {

	private final IDrawable background;
	private final String name;
	
	public DrumRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 4, 4, 104, 64);
		name = "Drum";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, DrumRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 4, 21);
		stacks.init(input2, true, 56, 21);
		stacks.init(liquid, true, 30, 1);
		stacks.init(drum, true, 30, 43);
		stacks.init(output, false, 83, 43);
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
	}

	@Override
	public String getUid() {
		return RecipeCategories.DRUM;
	}

	@Override
	public String getModName() {
		return ModInfo.NAME;
	}

}
