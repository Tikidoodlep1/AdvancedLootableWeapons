package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.data.recipes.AlloyFurnaceRecipeBuilder;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import net.minecraft.core.Registry;
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
import net.minecraft.world.level.block.Blocks;
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
        alloyFurnace(pFinishedRecipeConsumer);
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

        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.DUSKSTEEL_NUGGET.get(),ItemInit.DUSKSTEEL_INGOT.get(),BlockInit.DUSKSTEEL_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.KOBOLD_NUGGET.get(),ItemInit.KOBOLD_INGOT.get(),BlockInit.KOBOLD_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.TIN_NUGGET.get(),ItemInit.TIN_INGOT.get(),BlockInit.TIN_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.CRYSTALLITE_NUGGET.get(),ItemInit.CRYSTALLITE_INGOT.get(),BlockInit.CRYSTALLITE_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.PLATINUM_NUGGET.get(),ItemInit.PLATINUM_INGOT.get(),BlockInit.PLATINUM_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.BRONZE_NUGGET.get(),ItemInit.BRONZE_INGOT.get(),BlockInit.BRONZE_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.FROST_STEEL_NUGGET.get(),ItemInit.FROST_STEEL_INGOT.get(),BlockInit.FROST_STEEL_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.SILVER_NUGGET.get(),ItemInit.SILVER_INGOT.get(),BlockInit.SILVER_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.SHADOW_PLATINUM_NUGGET.get(),ItemInit.SHADOW_PLATINUM_INGOT.get(),BlockInit.SHADOW_PLATINUM_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.REFINED_OBSIDIAN_NUGGET.get(),ItemInit.REFINED_OBSIDIAN_INGOT.get(),BlockInit.REFINED_OBSIDIAN_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.STEEL_NUGGET.get(),ItemInit.STEEL_INGOT.get(),BlockInit.STEEL_BLOCK.get());


        woodenWeapon(recipeConsumer,ItemInit.BATTLEAXE_HEAD.get(),true);
        woodenWeapon(recipeConsumer,ItemInit.CLEAVER_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.DAGGER_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.KABUTOWARI_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.KODACHI_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.LONGSWORD_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.MACE_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.MAKHAIRA_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.NODACHI_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.RAPIER_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.SABRE_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.STAFF_HEAD.get(),true);
        woodenWeapon(recipeConsumer,ItemInit.SPEAR_HEAD.get(),true);
        woodenWeapon(recipeConsumer,ItemInit.TALWAR_HEAD.get(),false);
        woodenWeapon(recipeConsumer,ItemInit.ZWEIHANDER_HEAD.get(),true);

        twoByTwo(recipeConsumer,BlockInit.DIORITE_BRICKS.get(),ItemInit.DIORITE_BRICK.get());
        twoByTwo(recipeConsumer,BlockInit.GRANITE_BRICKS.get(),ItemInit.GRANITE_BRICK.get());
        twoByTwo(recipeConsumer,Blocks.OBSIDIAN,ItemInit.OBSIDIAN_SHARD.get());

        twoByTwo(recipeConsumer,BlockInit.DIORITE_CLAY.get(),ItemInit.DIORITE_CLAY_BALL.get());
        twoByTwo(recipeConsumer,BlockInit.GRANITE_CLAY.get(),ItemInit.GRANITE_CLAY_BALL.get());

    }

    protected void alloyFurnace(Consumer<FinishedRecipe> recipeConsumer) {
        AlloyFurnaceRecipeBuilder.alloy(ItemInit.BRONZE_INGOT.get(),4)
                .ingredient1(Tags.Items.INGOTS_COPPER,3)
                .ingredient2(ModItemTags.INGOTS_TIN,1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"bronze_alloying"));
    }

    protected static void twoByTwo(Consumer<FinishedRecipe> consumer,ItemLike result,Item ing) {
        ShapedRecipeBuilder.shaped(result).define('#', ing)
                .pattern("##").pattern("##")
                .unlockedBy("has_brick", has(ing)).save(consumer);
    }

    protected static void woodenWeapon(Consumer<FinishedRecipe> consumer,Item head,boolean longHandle) {
        Item handle = longHandle ? ItemInit.LONG_WEAPON_HANDLE.get() : Items.STICK;
        String base = Registry.ITEM.getKey(head).getPath().replace("_head","");
        ShapedRecipeBuilder.shaped(itemLookup("wood_"+base)).define('W',head).define('S',handle)
                .pattern("W").pattern("S").unlockedBy("has_"+base+"_head",has(head)).save(consumer);
    }

    protected static void nuggetIngotBlockRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer,ItemLike nugget,ItemLike ingot,ItemLike block
    ) {
        String materialname = Registry.ITEM.getKey((Item)nugget).getPath().replace("_nugget","");

        nineBlockStorageRecipesRecipesWithCustomUnpackingModded(pFinishedRecipeConsumer,ingot,block,
                materialname+"_ingot_from_"+materialname+"_block",materialname+"_ingot");

        nineBlockStorageRecipesWithCustomPackingModded(pFinishedRecipeConsumer, nugget, ingot,
                materialname+"_ingot_from_nuggets", materialname+"_ingot");
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

    protected static Item itemLookup(String name) {
        return Registry.ITEM.get(new ResourceLocation(AdvancedLootableWeapons.MODID,name));
    }
}
