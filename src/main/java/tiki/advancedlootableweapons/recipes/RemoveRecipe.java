package tiki.advancedlootableweapons.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RemoveRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	
	@SuppressWarnings("unused")
	private final ItemStack output;
	
	RemoveRecipe(ItemStack output) {
		this.output = output;
	}
	
	public static IRecipe from(IRecipe recipe) {
		return new RemoveRecipe(recipe.getRecipeOutput()).setRegistryName(recipe.getRegistryName());
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;//output;
	}
	

}
