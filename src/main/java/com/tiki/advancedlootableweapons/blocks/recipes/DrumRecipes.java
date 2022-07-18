package com.tiki.advancedlootableweapons.blocks.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class DrumRecipes {

	@Nonnull private final Fluid fluid;
	@Nonnull private final ItemStack input;
	@Nonnull private final ItemStack additive;
	@Nonnull private final int recipeTime;
	@Nonnull private final ItemStack output;
	private static final Set<DrumRecipes> RECIPES = new HashSet<DrumRecipes>();
	private static final Set<Item> ACCEPTED_INPUTS = new HashSet<Item>();
	private static final Set<Item> ACCEPTED_ADDITIVES = new HashSet<Item>();
	private static final Set<Fluid> ACCEPTED_FLUIDS = new HashSet<Fluid>();
	
	private DrumRecipes(final Fluid fluid, final ItemStack input, final ItemStack additive, final ItemStack output, final int recipeTime) {
		this.fluid = fluid;
		this.input = input;
		this.additive = additive;
		this.output = output;
		this.recipeTime = recipeTime;
		ACCEPTED_INPUTS.add(input.getItem());
		ACCEPTED_ADDITIVES.add(additive.getItem());
		ACCEPTED_FLUIDS.add(fluid);
		RECIPES.add(this);
	}
	
	public static Set<DrumRecipes> getRecipes() {
		return Collections.unmodifiableSet(RECIPES);
	}
	
	public static Set<Item> getAcceptedInputs() {
		return Collections.unmodifiableSet(ACCEPTED_INPUTS);
	}
	
	public static Set<Item> getAcceptedAdditives() {
		return Collections.unmodifiableSet(ACCEPTED_ADDITIVES);
	}
	
	public static Set<Fluid> getAcceptedFluids() {
		return Collections.unmodifiableSet(ACCEPTED_FLUIDS);
	}
	
	public Fluid getFluid() {
		return this.fluid;
	}
	
	public ItemStack getInput() {
		return this.input;
	}
	
	public ItemStack getAdditive() {
		return this.additive;
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
	
	public int getRecipeTime() {
		return this.recipeTime;
	}
	
	@Nullable
	public static DrumRecipes getMatchingRecipe(Fluid fluid, ItemStack input, ItemStack additive, boolean useData) {
		if(useData) {
			for(DrumRecipes dr : getRecipes()) {
				if(dr.getFluid() == fluid && ItemStack.areItemStacksEqual(dr.getInput(), input) && dr.getInput().getCount() <= input.getCount() && (ItemStack.areItemStacksEqual(dr.getAdditive(), additive))) {
					return dr;
				}
			}
		}else {
			for(DrumRecipes dr : getRecipes()) {
				if(dr.getFluid() == fluid && dr.getInput().getItem() == input.getItem() && dr.getInput().getCount() <= input.getCount() && (dr.getAdditive() == additive || dr.getAdditive().getItem() == additive.getItem())) {
					return dr;
				}
			}
		}
		
		return null;
	}
	
	public static List<DrumRecipes> getMatchingRecipesByFluid(Fluid fluid) {
		List<DrumRecipes> matching = new ArrayList<DrumRecipes>();
		
		for(DrumRecipes dr : getRecipes()) {
			if(dr.getFluid() == fluid) {
				matching.add(dr);
			}
		}
		
		return matching;
	}
	
	public static List<DrumRecipes> getMatchingRecipesByInput(ItemStack input, boolean useData) {
		List<DrumRecipes> matching = new ArrayList<DrumRecipes>();
		
		if(useData) {
			for(DrumRecipes dr : getRecipes()) {
				if(dr.getInput().getItem() == input.getItem() && dr.getInput().getCount() <= input.getCount()) {
					matching.add(dr);
				}
			}
		}else {
			for(DrumRecipes dr : getRecipes()) {
				if(ItemStack.areItemStacksEqual(dr.getInput(), input) && dr.getInput().getCount() <= input.getCount()) {
					matching.add(dr);
				}
			}
		}
		
		return matching;
	}
	
	public static List<DrumRecipes> getMatchingRecipesByAdditive(ItemStack additive, boolean useData) {
		List<DrumRecipes> matching = new ArrayList<DrumRecipes>();
		
		if(useData) {
			for(DrumRecipes dr : getRecipes()) {
				if(dr.getAdditive().getItem() == additive.getItem()) {
					matching.add(dr);
				}
			}
		}else {
			for(DrumRecipes dr : getRecipes()) {
				if(ItemStack.areItemStacksEqual(dr.getAdditive(), additive)) {
					matching.add(dr);
				}
			}
		}
		
		return matching;
	}
	
	public static void initDrumRecipes() {
		new DrumRecipes(FluidRegistry.WATER, new ItemStack(ItemInit.TRIMMED_HIDE), new ItemStack(ItemInit.RAW_SALT), new ItemStack(ItemInit.CURED_HIDE), 1600);
		new DrumRecipes(FluidInit.MILK_OF_LIME, new ItemStack(ItemInit.CURED_HIDE), ItemStack.EMPTY, new ItemStack(ItemInit.LIMED_HIDE), 1800);
		new DrumRecipes(FluidInit.MAGNESIUM_LACTATE, new ItemStack(ItemInit.LIMED_HIDE), ItemStack.EMPTY, new ItemStack(ItemInit.DELIMED_HIDE), 1000);
	}
	
	@Override
	public String toString() {
		return "Drum Recipe[ FLUID: " + this.getFluid().getName() + ", INPUT: " + this.getInput() + ", ADDITIVE: " + this.getAdditive() + ", OUTPUT: " + this.getOutput() + " ]";
	}
}
