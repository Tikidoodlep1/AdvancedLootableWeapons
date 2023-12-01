package tiki.rotn.advancedlootableweapons.compat.jei.drum;

import java.awt.Color;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class DrumRecipe implements IRecipeWrapper {

	private final List<List<ItemStack>> inputs;
	private final ItemStack output;
	private final int ticks;
	public final boolean quench;
	
	public DrumRecipe(List<List<ItemStack>> inputs, ItemStack output, int ticks, boolean quench) {
		this.inputs = inputs;
		this.output = output;
		this.ticks = ticks;
		this.quench = quench;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		String ticks = "Ticks: " + this.ticks;
		FontRenderer fr = minecraft.fontRenderer;
		fr.drawString(ticks, recipeWidth - (fr.getStringWidth(ticks)), -2, Color.GRAY.getRGB());
		fr.drawString("1", 37, 24, Color.DARK_GRAY.getRGB());
		fr.drawString("2", 24, 20, Color.DARK_GRAY.getRGB());
		fr.drawString("3", 48, 20, Color.DARK_GRAY.getRGB());
		if(quench) {
			fr.drawString("Fire-", recipeWidth - fr.getStringWidth("Required"), 6, Color.GRAY.getRGB());
			fr.drawString("Required", recipeWidth - fr.getStringWidth("Required"), 14, Color.GRAY.getRGB());
		}
	}
}
