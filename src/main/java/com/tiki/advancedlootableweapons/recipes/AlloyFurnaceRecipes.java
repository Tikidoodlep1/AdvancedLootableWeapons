package com.tiki.advancedlootableweapons.recipes;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistries;

public class AlloyFurnaceRecipes {

	public static int getRecipeBurnTime() {
		return 200;
	}
	
	public static final List<Recipe<Integer>> recipes = new ArrayList<Recipe<Integer>>(11);
	public static final AlloyFurnaceRecipes INSTANCE = new AlloyFurnaceRecipes().initRecipes();
	public static boolean hasDefaultRecipes = false;
	
	public void addDefaultRecipes(Level level) {
		IItemHandlerModifiable handler = new ItemStackHandler();
		List<SmeltingRecipe> recipes = level.getRecipeManager().getRecipesFor(RecipeType.SMELTING, new RecipeWrapper(handler), level);
		ResourceLocation key = ForgeRegistries.RECIPE_SERIALIZERS.getKey(RecipeSerializer.BLASTING_RECIPE);
		if(ForgeRegistries.RECIPE_SERIALIZERS.containsKey(key)) {
			
		}
		
		for(SmeltingRecipe e : recipes) {
			e.getIngredients().forEach((i) -> {
				for(ItemStack stack : i.getItems())
					if(!e.getResultItem().getItem().getRegistryName().toString().contains("ingot") || !e.getResultItem().getItem().getRegistryName().toString().contains("nugget"))
					addRecipe(stack.getItem(), 2, stack.getItem(), 2, e.getResultItem().getItem(), 2, e.getExperience());
			});
		}
		hasDefaultRecipes = true;
	}
	
	public AlloyFurnaceRecipes initRecipes() {		
		addRecipe(Items.IRON_INGOT, 2, BlockInit.BLOCK_ROCK_FELDSPAR.get().asItem(), 1, ItemInit.INGOT_KOBOLD.get(), 4, 2.0F);
		addRecipe(ItemInit.NUGGET_TIN.get(), 1, Items.COPPER_INGOT, 1, ItemInit.INGOT_BRONZE.get(), 1, 4.0F);
		addRecipe(ItemInit.INGOT_TIN.get(), 1, Items.COPPER_INGOT, 9, ItemInit.INGOT_BRONZE.get(), 9, 20.0F);
		addRecipe(Items.IRON_INGOT, 4, Items.COAL, 1, ItemInit.INGOT_STEEL.get(), 4, 5.0F);
		addRecipe(Items.IRON_INGOT, 4, Items.CHARCOAL, 1, ItemInit.INGOT_STEEL.get(), 4, 5.0F);
		addRecipe(ItemInit.INGOT_SILVER.get(), 1, ItemInit.INGOT_PLATINUM.get(), 1, ItemInit.INGOT_FROST_STEEL.get(), 1, 8.0F);
		addRecipe(ItemInit.CONGEALED_SHADOW.get(), 1, ItemInit.INGOT_PLATINUM.get(), 1, ItemInit.INGOT_SHADOW_PLATINUM.get(), 1, 8.0F);
		addRecipe(ItemInit.SHARD_OBSIDIAN.get(), 1, ItemInit.INGOT_STEEL.get(), 1, ItemInit.INGOT_OBSIDIAN.get(), 1, 9.0F);
		addRecipe(ItemInit.INGOT_STEEL.get(), 1, ItemInit.CRYSTAL.get(), 4, ItemInit.INGOT_CRYSTALLITE.get(), 2, 10.0F);
		addRecipe(ItemInit.INGOT_SHADOW_PLATINUM.get(), 4, ItemInit.INGOT_STEEL.get(), 1, ItemInit.INGOT_DUSKSTEEL.get(), 1, 12.0F);
		return this;
	}
	
	private void addRecipe(@Nonnull Item input1, @Nonnull int count1, @Nonnull Item input2, @Nonnull int count2, @Nonnull Item output, @Nonnull int count3, float exp) {
		Recipe<Integer> recipe = new Recipe<Integer>(new ItemStack(output, count3), exp, new ItemStack(input1, count1), new ItemStack(input2, count2));
		recipes.add(recipe);
	}
	
	public Recipe<Integer> getRecipe(ItemStack input1, ItemStack input2) {
		if(input1 == null || input2 == null || input1 == ItemStack.EMPTY || input2 == ItemStack.EMPTY) {
			return null;
		}
		for(Recipe<Integer> r : recipes) {
			if(r.recipeInputsMatch(input1, input2)) {
				return r;
			}
		}
		return null;
	}
}
