package tiki.advancedlootableweapons.compat.jei.anvilForgingComplex;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.compat.jei.RecipeCategories;
import tiki.advancedlootableweapons.handlers.ConfigHandler;

public class AnvilForgingComplexRecipeCategory extends AbstractAnvilForgingComplexRecipeCategory<AnvilForgingComplexRecipe> {
	private final IDrawable background;
	private final String name;
	private final IGuiHelper helper;
	
	public AnvilForgingComplexRecipeCategory(IGuiHelper helper) {
		super(helper);
		this.helper = helper;
		background = helper.createDrawable(TEXTURES, 0, 0, 162, 123);
		name = "Anvil Forging";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AnvilForgingComplexRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(ingot, true, 1, 23);
		
		stacks.init(output1, false, 41, 23);
		stacks.init(input2, true, 41, 43);
		
		stacks.init(output2, false, 81, 23);
		stacks.init(input3, true, 81, 43);
		
		stacks.init(output3, false, 121, 23);
		stacks.init(input4, true, 121, 43);
		
		stacks.init(output4, false, 1, 84);
		stacks.init(input5, true, 1, 104);
		
		stacks.init(output5, false, 41, 84);
		stacks.init(input6, true, 41, 104);
		
		stacks.init(output6, false, 81, 84);
		stacks.init(input7, true, 81, 104);
		
		stacks.init(output7, false, 130, 85);
		 
		stacks.set(ingredients);
	}
	
	@Override
	public IDrawable getIcon() {
		for(String next : ConfigHandler.VALID_ANVILS) {
			if(ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(next))) {
				return helper.createDrawableIngredient(new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(next))));
			}
		}
		return helper.createDrawableIngredient(new ItemStack(Blocks.ANVIL));
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		super.drawExtras(minecraft);
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getUid() {
		return RecipeCategories.ANVILFORGINGCOMPLEX;
	}

	@Override
	public String getModName() {
		return ModInfo.NAME;
	}
}
