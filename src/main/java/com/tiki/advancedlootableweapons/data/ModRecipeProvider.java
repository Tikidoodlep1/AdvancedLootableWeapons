package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        crafting(pFinishedRecipeConsumer);
    }

    protected void crafting(Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder.shaped(ItemInit.PLATINUM_FORGE_HAMMER.get()).define('I', Tags.Items.INGOTS_GOLD).define('S',Tags.Items.RODS_WOODEN)
                .pattern("III").pattern("ISI").pattern(" S ").unlockedBy("has_platinum",has(ItemInit.PLATINUM_INGOT.get()))
                .save(recipeConsumer);
    }
}
