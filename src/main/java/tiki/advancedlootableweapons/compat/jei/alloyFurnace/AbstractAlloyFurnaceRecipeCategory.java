package tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.ModInfo;

public abstract class AbstractAlloyFurnaceRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/alloy_furnace.png");
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int input3 = 2;
	protected static final int output = 3;
	
	protected final IDrawableAnimated animatedFlame;
	protected final IDrawableAnimated animatedArrow;
	
	public AbstractAlloyFurnaceRecipeCategory(IGuiHelper helper) {
		IDrawableStatic staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
}
