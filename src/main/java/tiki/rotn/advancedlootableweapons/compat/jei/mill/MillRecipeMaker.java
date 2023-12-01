package tiki.rotn.advancedlootableweapons.compat.jei.mill;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

public class MillRecipeMaker {

	public static List<MillRecipe> getRecipes(IJeiHelpers helpers){
		List<MillRecipe> recipes = new ArrayList<MillRecipe>(2);

		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes && ((ShapelessOneSlotRecipes)irecipe).block == BlockInit.mill)
            {
            	List<ItemStack> stackList = new ArrayList<ItemStack>(4);
    			NonNullList<Ingredient> ingrs = irecipe.getIngredients();
    			for(Ingredient i : ingrs) {
    				for(ItemStack stack : i.getMatchingStacks()) {
    					stackList.add(stack);
    				}
    			}
    			
    			recipes.add(new MillRecipe(stackList, irecipe.getRecipeOutput()));
            }
        }
		
		return recipes;
	}
}