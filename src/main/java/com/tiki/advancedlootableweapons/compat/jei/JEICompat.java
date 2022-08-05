package com.tiki.advancedlootableweapons.compat.jei;

import java.util.IllegalFormatException;

import com.tiki.advancedlootableweapons.compat.jei.alloyFurnace.AlloyFurnaceRecipeCategory;
import com.tiki.advancedlootableweapons.compat.jei.alloyFurnace.AlloyFurnaceRecipeMaker;
import com.tiki.advancedlootableweapons.compat.jei.anvilForging.AnvilForgingRecipeCategory;
import com.tiki.advancedlootableweapons.compat.jei.anvilForging.AnvilForgingRecipeMaker;
import com.tiki.advancedlootableweapons.compat.jei.drum.DrumRecipeCategory;
import com.tiki.advancedlootableweapons.compat.jei.drum.DrumRecipeMaker;
import com.tiki.advancedlootableweapons.compat.jei.jawCrusher.JawCrusherRecipeCategory;
import com.tiki.advancedlootableweapons.compat.jei.jawCrusher.JawCrusherRecipeMaker;
import com.tiki.advancedlootableweapons.compat.jei.tanningRack.TanningRackRecipeMaker;
import com.tiki.advancedlootableweapons.inventory.AlloyFurnace.ContainerAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.AlloyFurnace.GuiAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.GuiForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.JawCrusher.ContainerJawCrusher;
import com.tiki.advancedlootableweapons.inventory.JawCrusher.GuiJawCrusher;
import com.tiki.advancedlootableweapons.inventory.TanningRack.ContainerTanningRack;
import com.tiki.advancedlootableweapons.inventory.TanningRack.GuiTanningRack;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("deprecation")
@JEIPlugin
public class JEICompat implements IModPlugin{

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new AlloyFurnaceRecipeCategory(gui), new AnvilForgingRecipeCategory(gui), new JawCrusherRecipeCategory(gui), new DrumRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(AlloyFurnaceRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ALLOYFURNACE);
		registry.addRecipeClickArea(GuiAlloyFurnace.class, 58, 37, 14, 14, RecipeCategories.ALLOYFURNACE);
		recipeTransfer.addRecipeTransferHandler(ContainerAlloyFurnace.class, RecipeCategories.ALLOYFURNACE, 0, 3, 3, 36);
		
		registry.addRecipes(AnvilForgingRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ANVILFORGING);
		registry.addRecipeClickArea(GuiForgeWeapon.class, 80, 43, 21, 15, RecipeCategories.ANVILFORGING);
		recipeTransfer.addRecipeTransferHandler(ContainerForgeWeapon.class, RecipeCategories.ANVILFORGING, 0, 2, 2, 36);
		
		registry.addRecipes(JawCrusherRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.JAWCRUSHER);
		registry.addRecipeClickArea(GuiJawCrusher.class, 75, 43, 27, 13, RecipeCategories.JAWCRUSHER);
		recipeTransfer.addRecipeTransferHandler(ContainerJawCrusher.class, RecipeCategories.JAWCRUSHER, 0, 2, 2, 36);
		
		registry.addRecipes(DrumRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.DRUM);
		
		registry.addRecipes(TanningRackRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.TANNINGRACK);
		registry.addRecipeClickArea(GuiTanningRack.class, 74, 27, 24, 28, RecipeCategories.TANNINGRACK);
		recipeTransfer.addRecipeTransferHandler(ContainerTanningRack.class, RecipeCategories.TANNINGRACK, 0, 2, 2, 36);
	}
	
	public static String translateToLocal(String key) {
		if(I18n.canTranslate(key)) {
			return I18n.translateToLocal(key);
		}else {
			return I18n.translateToFallback(key);
		}
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		}catch(IllegalFormatException e) {
				return "Format Error: " + s;
		}
	}
}
