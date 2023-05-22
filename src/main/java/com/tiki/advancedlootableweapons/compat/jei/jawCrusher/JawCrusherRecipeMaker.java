package com.tiki.advancedlootableweapons.compat.jei.jawCrusher;

import java.util.ArrayList;
import java.util.List;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

public class JawCrusherRecipeMaker {

	public static List<JawCrusherRecipe> getRecipes(IJeiHelpers helpers){
		List<JawCrusherRecipe> recipes = new ArrayList<JawCrusherRecipe>(2);

		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ShapelessOneSlotRecipes && ((ShapelessOneSlotRecipes)irecipe).block == BlockInit.crusher)
            {
            	List<ItemStack> stackList = new ArrayList<ItemStack>(4);
    			NonNullList<Ingredient> ingrs = irecipe.getIngredients();
    			for(Ingredient i : ingrs) {
    				for(ItemStack stack : i.getMatchingStacks()) {
    					stackList.add(stack);
    				}
    			}
    			
    			recipes.add(new JawCrusherRecipe(stackList, irecipe.getRecipeOutput()));
            }
        }
		
		return recipes;
	}
}
