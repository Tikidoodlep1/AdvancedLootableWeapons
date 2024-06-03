package tiki.advancedlootableweapons.compat.jei.tanningRack;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.ItemInit;

public class TanningRackRecipeMaker {

	public static List<TanningRackRecipe> getRecipes(IJeiHelpers helpers){
		List<TanningRackRecipe> tRecipes = new ArrayList<TanningRackRecipe>();
		List<ItemStack> recipes = new ArrayList<ItemStack>();
		if(ConfigHandler.ENABLE_ADVANCED_LEATHER_TANNING) {
			recipes.add(new ItemStack(ItemInit.DELIMED_HIDE));
		}else {
			recipes.add(new ItemStack(ItemInit.UNTRIMMED_HIDE));
		}
		tRecipes.add(new TanningRackRecipe(recipes, new ItemStack(Items.LEATHER)));
		
		return tRecipes;
	}
}
