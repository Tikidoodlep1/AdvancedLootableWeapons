package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ForgeWeaponRecipes;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class AnvilForgingRecipeMaker {

	public static List<AnvilForgingRecipe> getRecipes(IJeiHelpers helpers){
		final ForgeWeaponRecipes instance = ForgeWeaponRecipes.INSTANCE;
		final Table<ItemStack, ItemStack, List<ItemStack>> recipes = instance.getJeiCraftingList();
		List<AnvilForgingRecipe> jeiRecipes = Lists.newArrayList();
		Set<Cell<ItemStack, ItemStack, List<ItemStack>>> recipeEntrySet = recipes.cellSet();
		List<ItemStack> outputList;
		
		for(Cell<ItemStack, ItemStack, List<ItemStack>> cell : recipeEntrySet) {
			ItemStack row = cell.getRowKey();
			ItemStack col = cell.getColumnKey();
			outputList = cell.getValue();
			
			for(int j = 0; j < outputList.size(); j++) {
				jeiRecipes.add(new AnvilForgingRecipe(NonNullList.from(ItemStack.EMPTY, row, col), outputList.get(j)));
			}
			
		}
		
		for(Cell<List<Item>, List<Item>, Item> e : instance.getArmorCraftingList().cellSet()) {
			List<Item> row = e.getRowKey();
			List<Item> col = e.getColumnKey();
			for(int i = 0; i < e.getRowKey().size(); i++) {
				jeiRecipes.add(new AnvilForgingRecipe(NonNullList.from(ItemStack.EMPTY, new ItemStack(row.get(i)), new ItemStack(col.get(i))), new ItemStack(e.getValue())));
			}
		}
		
		return jeiRecipes;
	}
}