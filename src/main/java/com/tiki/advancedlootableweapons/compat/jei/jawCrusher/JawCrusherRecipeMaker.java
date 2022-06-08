package com.tiki.advancedlootableweapons.compat.jei.jawCrusher;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class JawCrusherRecipeMaker {

	public static List<JawCrusherRecipe> getRecipes(IJeiHelpers helpers){
		List<JawCrusherRecipe> recipes = new ArrayList<JawCrusherRecipe>(2);
		List<ItemStack> recipe1 = new ArrayList<ItemStack>(1);
		recipe1.add(new ItemStack(Blocks.STONE, 1, 1));
		List<ItemStack> recipe2 = new ArrayList<ItemStack>(1);
		recipe2.add(new ItemStack(Blocks.STONE, 1, 3));
		List<ItemStack> recipe3 = new ArrayList<ItemStack>(1);
		recipe3.add(new ItemStack(BlockInit.rock_feldspar));
		recipes.add(new JawCrusherRecipe(recipe1, new ItemStack(ItemInit.POWDER_GRANITE)));
		recipes.add(new JawCrusherRecipe(recipe2, new ItemStack(ItemInit.POWDER_DIORITE)));
		recipes.add(new JawCrusherRecipe(recipe3, new ItemStack(ItemInit.POWDER_FELDSPAR)));
		
		return recipes;
	}
}
