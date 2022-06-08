package com.tiki.advancedlootableweapons.compat.jei.jawCrusher;

import com.tiki.advancedlootableweapons.ModInfo;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractJawCrusherRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/jaw_crusher.png");
	protected static final int input1 = 0;
	protected static final int output = 1;

	public AbstractJawCrusherRecipeCategory(IGuiHelper helper) {
		
	}
}
