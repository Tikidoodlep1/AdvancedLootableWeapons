package com.tiki.advancedlootableweapons.compat.jei.anvilForging;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorPlateRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolRecipe;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class AnvilForgingRecipeMaker {

	public static List<AnvilForgingRecipe> getRecipes(IJeiHelpers helpers) {
		List<AnvilForgingRecipe> jeiRecipes = new ArrayList<AnvilForgingRecipe>(10);
		
		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ForgeToolHeadRecipe)
            {
            	ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
            else if (irecipe instanceof ForgeToolRecipe)
            {
            	ForgeToolRecipe recipe = (ForgeToolRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	
            	Item output = recipe.getRecipeOutput().getItem();
            	if(output instanceof ToolStabSword) {
            		String type = ((ToolStabSword)output).getWeaponType();
            		
            		for(Item i : ItemInit.generatedItems) {
            			if(output != i && i instanceof ToolStabSword) {
            				if(type.equals(((ToolStabSword)i).getWeaponType())) {
            					jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), new ItemStack(i), recipe.getExp(), button.toString()));
            				}
            			}
            		}
            		
            		for(Item i : ItemInit.weaponItems) {
            			if(output != i && i instanceof ToolStabSword) {
            				if(type.equals(((ToolStabSword)i).getWeaponType())) {
            					jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), new ItemStack(i), recipe.getExp(), button.toString()));
            				}
            			}
            		}
            		
            	}else if(output instanceof ToolSlashSword) {
            		String type = ((ToolSlashSword)output).getWeaponType();
            		
            		for(Item i : ItemInit.generatedItems) {            			
            			if(output != i && i instanceof ToolSlashSword) {
            				if(type.equals(((ToolSlashSword)i).getWeaponType())) {
            					jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), new ItemStack(i), recipe.getExp(), button.toString()));
            				}
            			}
            		}
            		
            		for(Item i : ItemInit.weaponItems) {            			
            			if(output != i && i instanceof ToolSlashSword) {
            				if(type.equals(((ToolSlashSword)i).getWeaponType())) {
            					jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), new ItemStack(i), recipe.getExp(), button.toString()));
            				}
            			}
            		}
            		
            	}
            	
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
            else if (irecipe instanceof ForgeArmorPlateRecipe)
            {
            	ForgeArmorPlateRecipe recipe = (ForgeArmorPlateRecipe)irecipe;
            	StringBuilder button = new StringBuilder(recipe.getButton());
            	button.replace(0, 1, button.substring(0, 1).toUpperCase());
            	int space = button.indexOf(" ");
            	if(space != -1) {
            		button.replace(space + 1, space + 2, button.substring(space + 1, space + 2).toUpperCase());
            	}
            	jeiRecipes.add(new AnvilForgingRecipe(recipe.getIngredients(), recipe.getRecipeOutput(), recipe.getExp(), button.toString()));
            }
        }
		
		return jeiRecipes;
	}
}
