package tiki.rotn.advancedlootableweapons.compat.jei.alloyFurnace;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.compat.jei.RecipeCategories;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class AlloyFurnaceRecipeCategory extends AbstractAlloyFurnaceRecipeCategory<AlloyFurnaceRecipe>{

	private final IDrawable background;
	private final String name;
	private final IGuiHelper helper;
	
	public AlloyFurnaceRecipeCategory(IGuiHelper helper) {
		super(helper);
		this.helper = helper;
		background = helper.createDrawable(TEXTURES, 40, 12, 97, 63);
		name = new TextComponentTranslation("tile.block_alloy_furnace.name").getFormattedText();
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
	public IDrawable getIcon() {
		return helper.createDrawableIngredient(new ItemStack(BlockInit.alloy_furnace));
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
