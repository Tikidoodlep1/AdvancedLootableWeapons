package tiki.advancedlootableweapons.compat.jei.tanningRack;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.ModInfo;

public abstract class AbstractTanningRackRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/tanning_rack.png");
	protected static final int input1 = 0;
	protected static final int output = 1;
	
	protected final IDrawableAnimated animatedTexture;

	public AbstractTanningRackRecipeCategory(IGuiHelper helper) {
		IDrawableStatic staticTexture = helper.createDrawable(TEXTURES, 176, 0, 24, 28);
		animatedTexture = helper.createAnimatedDrawable(staticTexture, 500, IDrawableAnimated.StartDirection.BOTTOM, true);
	}
}
