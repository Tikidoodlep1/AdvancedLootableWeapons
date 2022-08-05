package com.tiki.advancedlootableweapons.compat.jei.drum;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.blocks.recipes.DrumRecipes;
import com.tiki.advancedlootableweapons.init.BlockInit;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class DrumRecipeMaker {

	public static List<DrumRecipe> getRecipes(IJeiHelpers helpers)	{
		List<DrumRecipe> recipes = new ArrayList<DrumRecipe>();
		
		for(DrumRecipes recipe : DrumRecipes.getRecipes()) {
			List<ItemStack> stackList = new ArrayList<ItemStack>(4);
			stackList.add(recipe.getInput());
			stackList.add(recipe.getAdditive());
			stackList.add(FluidUtil.getFilledBucket(new FluidStack(recipe.getFluid(), 1000)));
			stackList.add(new ItemStack(BlockInit.drum));
			
			recipes.add(new DrumRecipe(stackList, recipe.getOutput()));
		}
		
		return recipes;
	}
}
