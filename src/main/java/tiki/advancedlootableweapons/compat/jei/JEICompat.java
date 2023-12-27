package tiki.advancedlootableweapons.compat.jei;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tiki.advancedlootableweapons.compat.jei.alloyFurnace.AlloyFurnaceRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.alloyFurnace.AlloyFurnaceRecipeMaker;
import tiki.advancedlootableweapons.compat.jei.anvilForging.AnvilForgingRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.anvilForging.AnvilForgingRecipeMaker;
import tiki.advancedlootableweapons.compat.jei.anvilForgingComplex.AnvilForgingComplexRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.anvilForgingComplex.AnvilForgingComplexRecipeMaker;
import tiki.advancedlootableweapons.compat.jei.drum.DrumRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.drum.DrumRecipeMaker;
import tiki.advancedlootableweapons.compat.jei.mill.MillRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.mill.MillRecipeMaker;
import tiki.advancedlootableweapons.compat.jei.tanningRack.TanningRackRecipeCategory;
import tiki.advancedlootableweapons.compat.jei.tanningRack.TanningRackRecipeMaker;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.inventory.AlloyFurnace.ContainerAlloyFurnace;
import tiki.advancedlootableweapons.inventory.AlloyFurnace.GuiAlloyFurnace;
import tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import tiki.advancedlootableweapons.inventory.ForgeWeapon.GuiForgeWeapon;
import tiki.advancedlootableweapons.inventory.Mill.ContainerMill;
import tiki.advancedlootableweapons.inventory.Mill.GuiMill;
import tiki.advancedlootableweapons.inventory.TanningRack.ContainerTanningRack;
import tiki.advancedlootableweapons.inventory.TanningRack.GuiTanningRack;

@JEIPlugin
public class JEICompat implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(
				new AlloyFurnaceRecipeCategory(gui), 
				new AnvilForgingRecipeCategory(gui), 
				new AnvilForgingComplexRecipeCategory(gui),
				new MillRecipeCategory(gui), 
				new DrumRecipeCategory(gui), 
				new TanningRackRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		//final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		
		registry.addRecipes(AlloyFurnaceRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ALLOYFURNACE);
		registry.addRecipeClickArea(GuiAlloyFurnace.class, 58, 37, 14, 14, RecipeCategories.ALLOYFURNACE);
		recipeTransfer.addRecipeTransferHandler(ContainerAlloyFurnace.class, RecipeCategories.ALLOYFURNACE, 0, 3, 3, 36);
		
		registry.addRecipes(AnvilForgingRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ANVILFORGING);
		registry.addRecipes(AnvilForgingComplexRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ANVILFORGINGCOMPLEX);
		registry.addRecipeClickArea(GuiForgeWeapon.class, 80, 43, 21, 15, RecipeCategories.ANVILFORGING);
		registry.addRecipeClickArea(GuiForgeWeapon.class, 80, 43, 21, 15, RecipeCategories.ANVILFORGINGCOMPLEX);
		recipeTransfer.addRecipeTransferHandler(ContainerForgeWeapon.class, RecipeCategories.ANVILFORGING, 0, 2, 2, 36);
		//recipeTransfer.addRecipeTransferHandler(ContainerForgeWeapon.class, RecipeCategories.ANVILFORGINGCOMPLEX, 0, 2, 2, 36);
		
		registry.addRecipes(MillRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.MILL);
		registry.addRecipeClickArea(GuiMill.class, 75, 43, 27, 13, RecipeCategories.MILL);
		recipeTransfer.addRecipeTransferHandler(ContainerMill.class, RecipeCategories.MILL, 0, 2, 2, 36);
		
		registry.addRecipes(DrumRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.DRUM);
		
		registry.addRecipes(TanningRackRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.TANNINGRACK);
		registry.addRecipeClickArea(GuiTanningRack.class, 74, 27, 24, 28, RecipeCategories.TANNINGRACK);
		recipeTransfer.addRecipeTransferHandler(ContainerTanningRack.class, RecipeCategories.TANNINGRACK, 0, 2, 2, 36);
		
		List<ItemStack> infoList = new ArrayList<ItemStack>(2);
		infoList.add(new ItemStack(BlockInit.clay_diorite));
		infoList.add(new ItemStack(BlockInit.clay_granite));
		registry.addIngredientInfo(infoList, VanillaTypes.ITEM, "alw.clay.info");
		
		registry.addRecipeCatalyst(new ItemStack(BlockInit.alloy_furnace), RecipeCategories.ALLOYFURNACE);
		for(String s : ConfigHandler.VALID_ANVILS) {
			if(ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(s))) {
				registry.addRecipeCatalyst(new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(s))),
						RecipeCategories.ANVILFORGING, RecipeCategories.ANVILFORGINGCOMPLEX);
			}
		}
		//registry.addRecipeCatalyst(new ItemStack(Blocks.ANVIL), RecipeCategories.ANVILFORGING); // legacy
		registry.addRecipeCatalyst(new ItemStack(BlockInit.mill), RecipeCategories.MILL);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.drum), RecipeCategories.DRUM);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.tanning_rack), RecipeCategories.TANNINGRACK);
	}
	
	public static String translateToLocal(String key) {
		return new TextComponentTranslation(key).getFormattedText();
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		}catch(IllegalFormatException e) {
				return "Format Error: " + s;
		}
	}
}
