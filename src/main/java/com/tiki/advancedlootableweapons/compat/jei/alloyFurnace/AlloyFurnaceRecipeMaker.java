package com.tiki.advancedlootableweapons.compat.jei.alloyFurnace;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.blocks.recipes.AlloyFurnaceRecipes;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;

public class AlloyFurnaceRecipeMaker {

	public static List<AlloyFurnaceRecipe> getRecipes(IJeiHelpers helpers){
		AlloyFurnaceRecipes instance = AlloyFurnaceRecipes.getInstance();
		Table<ItemStack, ItemStack, ItemStack> recipes = instance.getDualSmeltingList();
		List<AlloyFurnaceRecipe> jeiRecipes = Lists.newArrayList();
		Integer step = 0;
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipes.columnMap().entrySet()) {
			step += 1;
			if(step % 2 == 0) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					ItemStack input1 = entry.getKey();
					ItemStack input2 = ent.getKey();
					ItemStack output = ent.getValue();
					List<ItemStack> inputs = Lists.newArrayList(input1, input2);
					AlloyFurnaceRecipe recipe = new AlloyFurnaceRecipe(inputs, output);
					jeiRecipes.add(recipe);
				}
			}
		}
		
		return jeiRecipes;
	}
}
