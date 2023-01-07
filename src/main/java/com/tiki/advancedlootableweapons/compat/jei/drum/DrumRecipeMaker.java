package com.tiki.advancedlootableweapons.compat.jei.drum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.compat.jei.drum.DrumRecipe;
import com.tiki.advancedlootableweapons.recipes.DrumItemRecipe;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class DrumRecipeMaker {

	public static List<DrumRecipe> getRecipes(IJeiHelpers helpers)	{
		List<DrumRecipe> recipes = new ArrayList<DrumRecipe>();
		
		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof DrumItemRecipe)
            {
            	List<List<ItemStack>> stackList = new ArrayList<List<ItemStack>>(4);
    			NonNullList<Ingredient> ingrs = irecipe.getIngredients();
    			for(Ingredient i : ingrs) {
    				stackList.add(Arrays.stream(i.getMatchingStacks()).collect(Collectors.toList()));
    			}
    			List<ItemStack> bucketList = new ArrayList<ItemStack>();
    			bucketList.add(FluidUtil.getFilledBucket(new FluidStack(((DrumItemRecipe)irecipe).getFluid(), 1000)));
    			stackList.add(bucketList);
    			List<ItemStack> drumList = new ArrayList<ItemStack>();
    			drumList.add(new ItemStack(BlockInit.drum));
    			stackList.add(drumList);
    			
    			recipes.add(new DrumRecipe(stackList, irecipe.getRecipeOutput(), ((DrumItemRecipe)irecipe).getTime()));
            }
        }
		
		return recipes;
	}
}
