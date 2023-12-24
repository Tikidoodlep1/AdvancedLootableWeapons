package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        forgeHammer(ItemInit.BRONZE_FORGE_HAMMER.get(),ModItemTags.INGOTS_BRONZE,ItemInit.BRONZE_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.COPPER_FORGE_HAMMER.get(),Tags.Items.INGOTS_COPPER, Items.COPPER_INGOT,recipeConsumer);
        forgeHammer(ItemInit.CRYSTALLITE_FORGE_HAMMER.get(),ModItemTags.INGOTS_CRYSTALLITE,ItemInit.CRYSTALLITE_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.DUSKSTEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_DUSKSTEEL,ItemInit.DUSKSTEEL_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.FROST_STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_FROST_STEEL,ItemInit.FROST_STEEL_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.IRON_FORGE_HAMMER.get(),Tags.Items.INGOTS_IRON, Items.IRON_INGOT,recipeConsumer);
        forgeHammer(ItemInit.KOBOLD_FORGE_HAMMER.get(),ModItemTags.INGOTS_KOBOLD,ItemInit.KOBOLD_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.OBSIDIAN_FORGE_HAMMER.get(),ModItemTags.INGOTS_REFINED_OBSIDIAN,ItemInit.REFINED_OBSIDIAN_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.PLATINUM_FORGE_HAMMER.get(),ModItemTags.INGOTS_PLATINUM,ItemInit.PLATINUM_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.SHADOW_PLATINUM_FORGE_HAMMER.get(),ModItemTags.INGOTS_SHADOW_PLATINUM,ItemInit.SHADOW_PLATINUM_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.SILVER_FORGE_HAMMER.get(),ModItemTags.INGOTS_SILVER,ItemInit.SILVER_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_STEEL,ItemInit.STEEL_INGOT.get(),recipeConsumer);
        forgeHammer(ItemInit.STONE_FORGE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS,Items.COBBLESTONE,recipeConsumer);
    }

    protected void forgeHammer(Item hammer, TagKey<Item> ing,Item ingot,Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hammer).define('I', ing).define('S',Tags.Items.RODS_WOODEN)
                .pattern("III").pattern("ISI").pattern(" S ").unlockedBy("has_ingot",
                        has(ingot))
                .save(consumer);
    }

}
