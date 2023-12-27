package tiki.advancedlootableweapons.compat.jei.anvilForgingComplex;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.ModInfo;

public abstract class AbstractAnvilForgingComplexRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon_jei.png");
	
	protected static final int ingot = 0;
	
	protected static final int output1 = 1;
	protected static final int input2 = 2;
	
	protected static final int output2 = 3;
	protected static final int input3 = 4;
	
	protected static final int output3 = 5;
	protected static final int input4 = 6;
	
	protected static final int output4 = 7;
	protected static final int input5 = 8;
	
	protected static final int output5 = 9;
	protected static final int input6 = 10;
	
	protected static final int output6 = 11;
	protected static final int input7 = 12;
	
	protected static final int output7 = 13;
	
	public AbstractAnvilForgingComplexRecipeCategory(IGuiHelper helper) {
	}
}
