package com.tiki.advancedlootableweapons.recipes.anvilforging;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilArmorForgingRecipe extends AbstractAnvilForgingRecipe {

	public AnvilArmorForgingRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, Ingredient ingredient2, ItemStack pResult,int xp) {
		super(ModRecipeTypes.ANVIL_FORGING, ModRecipeSerializers.ANVIL_ARMOR_FORGING.get(), pId, pGroup, pIngredient,ingredient2, pResult, xp, false);
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(RecipeWrapper pInv, Level pLevel) {
		return this.ingredient.test(pInv.getItem(0)) && ingredient2.test(pInv.getItem(1));
	}

	@Override
	public ItemStack getProcessedResult(ItemStack input) {
		ItemStack copy = result.copy();
		//copy.setTag(input.getTag());
		return copy;
	}

	@Override
	public ItemStack assemble(RecipeWrapper pInv) {
		ItemStack binding = pInv.getItem(1);
		ItemStack resultCopy = result.copy();
		BoundArmorItem.setArmorBinding(resultCopy,binding.getItem());
		return resultCopy;
	}
}
