package tiki.rotn.advancedlootableweapons.compat.jei.drum;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;
import tiki.rotn.advancedlootableweapons.ModInfo;

public abstract class AbstractDrumRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	protected static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/drum_jei.png");
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int liquid = 2;
	protected static final int drum = 3;
	protected static final int output = 4;
	protected static final int fire = 5;
	

	public AbstractDrumRecipeCategory(IGuiHelper helper) {
	}
}
