package tiki.advancedlootableweapons.compat.jei.mill;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.ModInfo;

public abstract class AbstractMillRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/jaw_crusher.png");
	protected static final int input1 = 0;
	protected static final int output = 1;

	public AbstractMillRecipeCategory(IGuiHelper helper) {
		
	}
}
