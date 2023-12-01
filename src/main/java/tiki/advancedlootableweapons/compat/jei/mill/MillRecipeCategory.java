package tiki.advancedlootableweapons.compat.jei.mill;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.compat.jei.RecipeCategories;
import tiki.advancedlootableweapons.init.BlockInit;

public class MillRecipeCategory extends AbstractMillRecipeCategory<MillRecipe> {

	private final IDrawable background;
	private final String name;
	private final IGuiHelper helper;
	
	public MillRecipeCategory(IGuiHelper helper) {
		super(helper);
		this.helper = helper;
		background = helper.createDrawable(TEXTURES, 40, 22, 97, 60);
		name = "Mill";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MillRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 13, 20);
		stacks.init(output, false, 67, 20);
		stacks.set(ingredients);
	}
	
	@Override
	public IDrawable getIcon() {
		return helper.createDrawableIngredient(new ItemStack(BlockInit.mill));
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
		return RecipeCategories.MILL;
	}

	@Override
	public String getModName() {
		return ModInfo.NAME;
	}

}