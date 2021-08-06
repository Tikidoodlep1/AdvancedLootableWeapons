package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import com.tiki.advancedlootableweapons.ModInfo;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractAnvilForgingRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon.png");
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int output = 2;
	
	public AbstractAnvilForgingRecipeCategory(IGuiHelper helper) {
	}
}
