package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.util.ArrayList;
import java.util.List;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorPlateRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolRecipe;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class AnvilForgingRecipeMaker {

	public static List<AnvilForgingRecipe> getRecipes(IJeiHelpers helpers) {
		List<AnvilForgingRecipe> jeiRecipes = new ArrayList<AnvilForgingRecipe>(10);
		
		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ForgeToolHeadRecipe)
            {
            	ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
            else if (irecipe instanceof ForgeToolRecipe)
            {
            	ForgeToolRecipe recipe = (ForgeToolRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
            else if (irecipe instanceof ForgeArmorPlateRecipe)
            {
            	ForgeArmorPlateRecipe recipe = (ForgeArmorPlateRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
        }
		
		return jeiRecipes;
	}
}
