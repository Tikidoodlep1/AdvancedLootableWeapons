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
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ForgeWeaponRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class AnvilForgingRecipeMaker {

	public static List<AnvilForgingRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		ForgeWeaponRecipes instance = new ForgeWeaponRecipes();
		Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> recipes = instance.getJeiCraftingList();
		List<AnvilForgingRecipe> jeiRecipes = Lists.newArrayList();
		ItemStack input1 = null, input2 = null, output = null;
		NonNullList<ItemStack> input1List = NonNullList.<ItemStack>create();
		NonNullList<ItemStack> input2List = NonNullList.<ItemStack>create();
		Multimap<ItemStack, ItemStack> tempStack;
		List<ItemStack> inputs;
		
		for(int weaponInt : recipes.rowKeySet()) {
			for(ItemStack stack1 : recipes.columnKeySet()) {
				input1 = stack1;
				if(checkOreDict(stack1) != null) {
					input1List = checkOreDict(stack1);
				}
				tempStack = recipes.get(weaponInt, stack1);
				for(ItemStack stack2 : tempStack.keySet()) {
					input2 = stack2;
					if(checkOreDict(stack2) != null) {
						input2List = checkOreDict(stack2);
					}
					for(ItemStack stack3 : tempStack.get(stack2)) {
						output = stack3;
						if(!(input1List.isEmpty())) {
							for(ItemStack temp1 : input1List) {
								inputs = Lists.newArrayList(temp1, input2);
								AnvilForgingRecipe recipe = new AnvilForgingRecipe(inputs, output);
								jeiRecipes.add(recipe);
							}
						}
						if(!(input2List.isEmpty())) {
							for(ItemStack temp2 : input2List) {
								inputs = Lists.newArrayList(input1, temp2);
								AnvilForgingRecipe recipe = new AnvilForgingRecipe(inputs, output);
								jeiRecipes.add(recipe);
							}
						}
						if(input1List.isEmpty() && input2List.isEmpty()) {
							inputs = Lists.newArrayList(input1, input2);
							AnvilForgingRecipe recipe = new AnvilForgingRecipe(inputs, output);
							jeiRecipes.add(recipe);
						}
					}
				}
			}
			break;
		}
		return jeiRecipes;
	}
	
	private static NonNullList<ItemStack> checkOreDict(ItemStack target) {
		if(target.isItemEqualIgnoreDurability(new ItemStack(ItemInit.INGOT_COPPER))) {
			return OreDictionary.getOres("ingotCopper");
		}else if(target.isItemEqualIgnoreDurability(new ItemStack(ItemInit.INGOT_SILVER))) {
			return OreDictionary.getOres("ingotSilver");
		}else if(target.isItemEqualIgnoreDurability(new ItemStack(ItemInit.INGOT_BRONZE))) {
			return OreDictionary.getOres("ingotBronze");
		}else if(target.isItemEqualIgnoreDurability(new ItemStack(ItemInit.INGOT_PLATINUM))) {
			return OreDictionary.getOres("ingotPlatinum");
		}else if(target.isItemEqualIgnoreDurability(new ItemStack(ItemInit.INGOT_STEEL))) {
			return OreDictionary.getOres("ingotSteel");
		}else {
			return null;
		}
	}
}
