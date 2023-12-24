package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
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
        forgeHammer(ItemInit.BRONZE_FORGE_HAMMER.get(),ModItemTags.INGOTS_BRONZE,recipeConsumer);
        forgeHammer(ItemInit.COPPER_FORGE_HAMMER.get(),Tags.Items.INGOTS_COPPER, recipeConsumer);
        forgeHammer(ItemInit.CRYSTALLITE_FORGE_HAMMER.get(),ModItemTags.INGOTS_CRYSTALLITE,recipeConsumer);
        forgeHammer(ItemInit.DUSKSTEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_DUSKSTEEL,recipeConsumer);
        forgeHammer(ItemInit.FROST_STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_FROST_STEEL,recipeConsumer);
        forgeHammer(ItemInit.IRON_FORGE_HAMMER.get(),Tags.Items.INGOTS_IRON, recipeConsumer);
        forgeHammer(ItemInit.KOBOLD_FORGE_HAMMER.get(),ModItemTags.INGOTS_KOBOLD,recipeConsumer);
        forgeHammer(ItemInit.OBSIDIAN_FORGE_HAMMER.get(),ModItemTags.INGOTS_REFINED_OBSIDIAN,recipeConsumer);
        forgeHammer(ItemInit.PLATINUM_FORGE_HAMMER.get(),ModItemTags.INGOTS_PLATINUM,recipeConsumer);
        forgeHammer(ItemInit.SHADOW_PLATINUM_FORGE_HAMMER.get(),ModItemTags.INGOTS_SHADOW_PLATINUM,recipeConsumer);
        forgeHammer(ItemInit.SILVER_FORGE_HAMMER.get(),ModItemTags.INGOTS_SILVER,recipeConsumer);
        forgeHammer(ItemInit.STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_STEEL,recipeConsumer);
        forgeHammer(ItemInit.STONE_FORGE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS, recipeConsumer);

        sharpeningStone(ItemInit.BRONZE_WHETSTONE.get(),ModItemTags.INGOTS_BRONZE,recipeConsumer);
        sharpeningStone(ItemInit.COPPER_WHETSTONE.get(),Tags.Items.INGOTS_COPPER,recipeConsumer);
        sharpeningStone(ItemInit.CRYSTALLITE_WHETSTONE.get(),ModItemTags.INGOTS_CRYSTALLITE,recipeConsumer);
        sharpeningStone(ItemInit.DUSKSTEEL_WHETSTONE.get(),ModItemTags.INGOTS_DUSKSTEEL,recipeConsumer);
        sharpeningStone(ItemInit.FROST_STEEL_WHETSTONE.get(),ModItemTags.INGOTS_FROST_STEEL,recipeConsumer);
        sharpeningStone(ItemInit.IRON_WHETSTONE.get(),Tags.Items.INGOTS_IRON,recipeConsumer);
        sharpeningStone(ItemInit.KOBOLD_WHETSTONE.get(),ModItemTags.INGOTS_KOBOLD,recipeConsumer);
        sharpeningStone(ItemInit.OBSIDIAN_WHETSTONE.get(),ModItemTags.INGOTS_REFINED_OBSIDIAN,recipeConsumer);
        sharpeningStone(ItemInit.PLATINUM_WHETSTONE.get(),ModItemTags.INGOTS_PLATINUM,recipeConsumer);
        sharpeningStone(ItemInit.SHADOW_PLATINUM_WHETSTONE.get(),ModItemTags.INGOTS_SHADOW_PLATINUM,recipeConsumer);
        sharpeningStone(ItemInit.SILVER_WHETSTONE.get(),ModItemTags.INGOTS_SILVER,recipeConsumer);
        sharpeningStone(ItemInit.STEEL_WHETSTONE.get(),ModItemTags.INGOTS_STEEL,recipeConsumer);
        sharpeningStone(ItemInit.STONE_WHETSTONE.get(), ItemTags.STONE_TOOL_MATERIALS,recipeConsumer);

        nineBlockStorageRecipesRecipesWithCustomUnpackingModded(recipeConsumer, ItemInit.STEEL_INGOT.get(), BlockInit.STEEL_BLOCK.get(),
                "steel_ingot_from_steel_block", "steel_ingot");
        nineBlockStorageRecipesWithCustomPackingModded(recipeConsumer, ItemInit.STEEL_NUGGET.get(), ItemInit.STEEL_INGOT.get(),
                "steel_ingot_from_nuggets", "steel_ingot");

       /* simpleBlockItem(BlockInit.KOBOLD_BLOCK.get());
        simpleBlockItem(BlockInit.TIN_BLOCK.get());
        simpleBlockItem(BlockInit.CRYSTALLITE_BLOCK.get());
        simpleBlockItem(BlockInit.PLATINUM_BLOCK.get());
        simpleBlockItem(BlockInit.BRONZE_BLOCK.get());
        simpleBlockItem(BlockInit.FROST_STEEL_BLOCK.get());
        simpleBlockItem(BlockInit.SILVER_BLOCK.get());
        simpleBlockItem(BlockInit.SHADOW_PLATINUM_BLOCK.get());
        simpleBlockItem(BlockInit.REFINED_OBSIDIAN_BLOCK.get());
        duskSteel();*/
    }

    protected static void nineBlockStorageRecipesWithCustomPackingModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, String pPackingRecipeGroup) {
        nineBlockStorageRecipesModded(pFinishedRecipeConsumer, pUnpacked, pPacked, pPackingRecipeName, pPackingRecipeGroup, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void nineBlockStorageRecipesRecipesWithCustomUnpackingModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pUnpackingRecipeName, String pUnpackingRecipeGroup) {
        nineBlockStorageRecipesModded(pFinishedRecipeConsumer, pUnpacked, pPacked, getSimpleRecipeName(pPacked), null, pUnpackingRecipeName, pUnpackingRecipeGroup);
    }

    protected static void nineBlockStorageRecipesModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, @Nullable String pPackingRecipeGroup, String pUnpackingRecipeName, @Nullable String pUnpackingRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpacked, 9).requires(pPacked).group(pUnpackingRecipeGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(AdvancedLootableWeapons.MODID,pUnpackingRecipeName));
        ShapedRecipeBuilder.shaped(pPacked).define('#', pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackingRecipeGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(AdvancedLootableWeapons.MODID,pPackingRecipeName));
    }

    protected void forgeHammer(Item hammer, TagKey<Item> ing, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hammer).define('I', ing).define('S',Tags.Items.RODS_WOODEN)
                .pattern("III").pattern("ISI").pattern(" S ").unlockedBy("has_material",
                        has(ing))
                .save(consumer);
    }

    protected void sharpeningStone(Item stone, TagKey<Item> ing, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stone).define('I', ing).define('S',Items.FLINT)
                .pattern("  S").pattern("III").unlockedBy("has_material",
                        has(ing))
                .save(consumer);
    }



}
