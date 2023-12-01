package tiki.rotn.advancedlootableweapons.compat.jei.alloyFurnace;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import tiki.rotn.advancedlootableweapons.compat.jei.JEICompat;

public class AlloyFurnaceRecipe implements IRecipeWrapper {

	private final NonNullList<Ingredient> inputs;
	private final ItemStack output;
	private final float exp;
	
	public AlloyFurnaceRecipe(NonNullList<Ingredient> inputs, ItemStack output, float exp) {
		this.inputs = inputs;
		this.output = output;
		this.exp = exp;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> inputList = new ArrayList<List<ItemStack>>();
		for(Ingredient i : this.inputs) {
			inputList.add(Arrays.stream(i.getMatchingStacks()).collect(Collectors.toList()));
		}
		ArrayList<ItemStack> fuels = new ArrayList<ItemStack>(5);
		fuels.add(new ItemStack(Blocks.PLANKS));
		fuels.add(new ItemStack(Items.COAL));
		ItemStack charcoal = new ItemStack(Items.COAL);
		charcoal.setItemDamage(1);
		fuels.add(charcoal);
		fuels.add(new ItemStack(Items.LAVA_BUCKET));
		fuels.add(new ItemStack(Items.BLAZE_ROD));
		
		inputList.add(fuels);
		ingredients.setInputLists(VanillaTypes.ITEM, inputList);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		if(exp > 0){
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience") + ": " + exp;
			FontRenderer renderer = minecraft.fontRenderer;
			renderer.drawString(expString, recipeWidth - renderer.getStringWidth(expString), 48, Color.GRAY.getRGB());
		}
	}
}
