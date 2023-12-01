package tiki.rotn.advancedlootableweapons.compat.jei.anvilForging;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.compat.jei.RecipeCategories;

public class AnvilForgingRecipeCategory extends AbstractAnvilForgingRecipeCategory<AnvilForgingRecipe> {
	private final IDrawable background;
	private final String name;
	private final IGuiHelper helper;
	
	public AnvilForgingRecipeCategory(IGuiHelper helper) {
		super(helper);
		this.helper = helper;
		background = helper.createDrawable(TEXTURES, 51, 18, 87, 55);
		name = "Anvil Forging";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AnvilForgingRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 4, 14);
		stacks.init(input2, true, 4, 34);
		stacks.init(output, false, 62, 24);
		stacks.set(ingredients);
	}
	
	@Override
	public IDrawable getIcon() {
		return helper.createDrawableIngredient(new ItemStack(Blocks.ANVIL));
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		super.drawExtras(minecraft);
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
