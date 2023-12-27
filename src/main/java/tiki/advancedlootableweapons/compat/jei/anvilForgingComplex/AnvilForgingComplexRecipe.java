package tiki.advancedlootableweapons.compat.jei.anvilForgingComplex;

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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.compat.jei.JEICompat;

public class AnvilForgingComplexRecipe implements IRecipeWrapper {
	
	protected static final ResourceLocation BUTTON = new ResourceLocation(ModInfo.ID + ":textures/gui/button");
	protected static final ResourceLocation OVERLAY = new ResourceLocation(ModInfo.ID + ":textures/gui/button_overlay");
	
	public static final List<Item> hasRecipe = new ArrayList<Item>();
	
//	public IGuiHelper helper = null;
	private final List<NonNullList<Ingredient>> inputs;
	private final List<Integer> exps;
	private final List<String> buttons;
	private final int exp;
	private final String buttonName;
	
	public AnvilForgingComplexRecipe(List<NonNullList<Ingredient>> inputs, List<Integer> exps, List<String> buttons, int exp, String buttonName) {
		this.inputs = inputs;
		this.exp = exp;
		this.buttonName = buttonName;
		this.exps = exps;
		this.buttons = buttons;
		
//		String ingredients = "";
//		for(NonNullList<Ingredient> ingrs : inputs) {
//			for(Ingredient ingr : ingrs) {
//				ingredients += "[";
//				for(ItemStack stack : ingr.getMatchingStacks()) {
//					ingredients += stack.getDisplayName() + ", ";
//				}
//				ingredients += "], ";
//			}
//		}
//		
//		Alw.logger.debug("Inputs: " + ingredients);// + ", Outputs: " + Arrays.toString(output.toArray(new ItemStack[0])));
//		Alw.logger.debug("====================================================================================================================================");
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> inputList = new ArrayList<List<ItemStack>>();
		List<List<ItemStack>> outputList = new ArrayList<List<ItemStack>>();
		
		NonNullList<Ingredient> firstRecipe = this.inputs.get(0);
		inputList.add(Arrays.stream(firstRecipe.get(0).getMatchingStacks()).collect(Collectors.toList()));
		
		for(int j = 2; j < this.inputs.size(); j++) {
			NonNullList<Ingredient> recipe = this.inputs.get(j);
			for(int i = 0; i < recipe.size(); i++) {
				if(i % 2 == 0) {
					outputList.add(Arrays.stream(recipe.get(i).getMatchingStacks()).collect(Collectors.toList()));
					for(ItemStack stack : recipe.get(i).getMatchingStacks()) {
						hasRecipe.add(stack.getItem());
					}
				}else {
					inputList.add(Arrays.stream(recipe.get(i).getMatchingStacks()).collect(Collectors.toList()));
				}
			}
		}
		
		String input = "[";
		for(List<ItemStack> inputs : inputList) {
			for(ItemStack in : inputs) {
				input += in.getDisplayName() + ", ";
			}
		}
		input += "]";
		
		String output = "[";
		for(List<ItemStack> outputs : outputList) {
			for(ItemStack out : outputs) {
				output += out.getDisplayName() + ", ";
			}
		}
		output += "]";
		
		Alw.logger.debug("================================After Modification of Recipe================================");
		Alw.logger.debug("Inputs: " + input + ", Outputs: " + output);
		Alw.logger.debug("============================================================================================");
		
		ingredients.setInputLists(VanillaTypes.ITEM, inputList);
		ingredients.setOutputLists(VanillaTypes.ITEM, outputList);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer renderer = minecraft.fontRenderer;
		
		int buttonSpace = 46;
		int buttonMultiplier = 0;
		int buttonY = 11;
		
		int expSpace = 42;
		int expMultiplier = 0;
		int expY = 44;
		
//		int bX = 21;
//		int bY = 24;
//		int bM = 0;
		
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.8F, 0.8F, 0.8F);
		for(String button : buttons) {
			String properButton = toProperCase(button);
			renderer.drawString("Button: ", (int)((buttonSpace * buttonMultiplier) * 1.2), buttonY - renderer.FONT_HEIGHT - 1, Color.GRAY.getRGB());
//			if(renderer.getStringWidth(button) > buttonSpace) {
				renderer.drawSplitString(properButton, (int)((buttonSpace * buttonMultiplier++) * 1.2), buttonY, buttonSpace, Color.DARK_GRAY.getRGB());
//			}else {
//				renderer.drawString(button, (int)((1 + buttonSpace) * buttonMultiplier++ * 1.2), buttonY, Color.GRAY.getRGB());
//			}
			if(buttonMultiplier > 3) {
				buttonMultiplier = 0;
				buttonY += (int)(63 * 1.2);
			}
			
//			if(helper != null) {
//				minecraft.getTextureManager().bindTexture(BUTTON);
//				helper.createDrawable(BUTTON, 1, 1, 20, 20).draw(minecraft, (int)((bX + bX) * bM * 1.2), (int)(bY * 1.2));
//				int[] pos = getButtonPosByName(button);
//				minecraft.getTextureManager().bindTexture(OVERLAY);
//				helper.createDrawable(OVERLAY, pos[0], pos[1], 20, 20).draw(minecraft, (int)((bX + bX) * bM++ * 1.2), (int)(bY * 1.2));
//			}
		}
		renderer.drawString("Button: ", (int)((buttonSpace * buttonMultiplier) * 1.2), buttonY - renderer.FONT_HEIGHT - 1, Color.GRAY.getRGB());
		renderer.drawSplitString(this.buttonName, (int)((buttonSpace * buttonMultiplier++) * 1.2), buttonY, buttonSpace, Color.DARK_GRAY.getRGB());
		
		for(int e : exps) {
			if(e >= 0) {
				String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience") + ": " + e;
				renderer.drawString(expString, (int)((18 + (expSpace * expMultiplier)) * 1.2), (int)(expY * 1.2), Color.GRAY.getRGB());
				
			}
			expMultiplier++;
			if(expMultiplier > 3) {
				expMultiplier = 0;
				expY += (int)(52 * 1.2);
			}
		}
		if(exp >= 0){
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience") + ": " + exp;
			renderer.drawString(expString, (int)((18 + (expSpace * expMultiplier)) * 1.2), (int)(expY * 1.2), Color.GRAY.getRGB());
		}
		
		GlStateManager.popMatrix();
	}
	
	private static String toProperCase(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.replace(0, 1, String.valueOf(s.charAt(0)).toUpperCase());
		int spInd = sb.indexOf(" ");
		while(spInd >= 0) {
			sb.replace(spInd + 1, spInd + 2, String.valueOf(s.charAt(spInd + 1)).toUpperCase());
			spInd = sb.indexOf(" ", spInd + 1);
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unused")
	private static int[] getButtonPosByName(String button) {
		if(button.equalsIgnoreCase("dagger")) {
			return new int[] {1, 1};
		}else if(button.equalsIgnoreCase("kabutowari")) {
			return new int[] {22, 1};
		}else if(button.equalsIgnoreCase("talwar")) {
			return new int[] {64, 1};
		}else if(button.equalsIgnoreCase("rapier")) {
			return new int[] {43, 1};
		}else if(button.equalsIgnoreCase("mace")) {
			return new int[] {1, 22};
		}else if(button.equalsIgnoreCase("cleaver")) {
			return new int[] {22, 22};
		}else if(button.equalsIgnoreCase("staff")) {
			return new int[] {43, 22};
		}else if(button.equalsIgnoreCase("longsword")) {
			return new int[] {64, 22};
		}else if(button.equalsIgnoreCase("kodachi")) {
			return new int[] {1, 43};
		}else if(button.equalsIgnoreCase("battleaxe")) {
			return new int[] {22, 43};
		}else if(button.equalsIgnoreCase("zweihander")) {
			return new int[] {43, 43};
		}else if(button.equalsIgnoreCase("nodachi")) {
			return new int[] {64, 43};
		}else if(button.equalsIgnoreCase("sabre")) {
			return new int[] {1, 64};
		}else if(button.equalsIgnoreCase("makhaira")) {
			return new int[] {22, 64};
		}else if(button.equalsIgnoreCase("spear")) {
			return new int[] {43, 64};
		}else if(button.equalsIgnoreCase("chain")) {
			return new int[] {22, 85};
		}else if(button.equalsIgnoreCase("armor plate")) {
			return new int[] {43, 85};
		}else if(button.equalsIgnoreCase("handle")) {
			return new int[] {64, 64};
		}else if(button.equalsIgnoreCase("forge weapon")) {
			return new int[] {1, 85};
		}
		return new int[] {232, 232};
	}
}
