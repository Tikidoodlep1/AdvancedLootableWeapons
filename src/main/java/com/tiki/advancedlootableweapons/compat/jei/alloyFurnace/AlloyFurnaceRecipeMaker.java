package com.tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import java.util.List;
import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.recipes.AlloyingRecipe;
import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class AlloyFurnaceRecipeMaker {

	public static List<AlloyFurnaceRecipe> getRecipes(IJeiHelpers helpers){
		List<AlloyFurnaceRecipe> jeiRecipes = Lists.newArrayList();
		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof AlloyingRecipe)
            {
            	AlloyingRecipe recipe = (AlloyingRecipe)irecipe;
            	jeiRecipes.add(new AlloyFurnaceRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp()));
            }
        }
		
		return jeiRecipes;
	}
}
