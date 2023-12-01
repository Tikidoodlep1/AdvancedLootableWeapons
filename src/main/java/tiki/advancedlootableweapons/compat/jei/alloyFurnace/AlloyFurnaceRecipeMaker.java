package tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import java.util.List;
import com.google.common.collect.Lists;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import tiki.advancedlootableweapons.recipes.AlloyingRecipe;

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
