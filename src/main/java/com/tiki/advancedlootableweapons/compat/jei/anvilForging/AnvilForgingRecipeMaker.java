package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.blocks.recipes.AlloyFurnaceRecipes;
import com.tiki.advancedlootableweapons.compat.jei.alloyFurnace.AlloyFurnaceRecipe;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ForgeWeaponRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AnvilForgingRecipeMaker {

	public static List<AnvilForgingRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		ForgeWeaponRecipes instance = new ForgeWeaponRecipes();
		Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> recipes = instance.getJeiCraftingList();
		List<AnvilForgingRecipe> jeiRecipes = Lists.newArrayList();
		ItemStack input1 = null, input2 = null, output = null;
		Multimap<ItemStack, ItemStack> tempStack;
		
		for(int weaponInt : recipes.rowKeySet()) {
			for(ItemStack stack1 : recipes.columnKeySet()) {
				input1 = stack1;
				tempStack = recipes.get(weaponInt, stack1);
				for(ItemStack stack2 : tempStack.keySet()) {
					input2 = stack2;
					for(ItemStack stack3 : tempStack.get(stack2)) {
						output = stack3;
						List<ItemStack> inputs = Lists.newArrayList(input1, input2);
						AnvilForgingRecipe recipe = new AnvilForgingRecipe(inputs, output);
						jeiRecipes.add(recipe);
					}
				}
			}
			break;
		}
		return jeiRecipes;
	}
}
