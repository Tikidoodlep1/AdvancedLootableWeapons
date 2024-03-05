package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.data.recipes.*;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.recipes.AnvilForgingRecipe;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        crafting(pFinishedRecipeConsumer);
        smelting(pFinishedRecipeConsumer);
        crusher(pFinishedRecipeConsumer);
        alloyFurnace(pFinishedRecipeConsumer);
        drumQuenching(pFinishedRecipeConsumer);
        drum(pFinishedRecipeConsumer);
        anvilForging(pFinishedRecipeConsumer);

    }

    protected void crafting(Consumer<FinishedRecipe> recipeConsumer) {
        forgeHammer(ItemInit.BRONZE_FORGE_HAMMER.get(),ModItemTags.INGOTS_BRONZE,recipeConsumer);
        forgeHammer(ItemInit.COPPER_FORGE_HAMMER.get(),Tags.Items.INGOTS_COPPER, recipeConsumer);
        forgeHammer(ItemInit.CRYSTALLITE_FORGE_HAMMER.get(),ModItemTags.INGOTS_CRYSTALLITE,recipeConsumer);
        forgeHammer(ItemInit.DUSKSTEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_DUSKSTEEL,recipeConsumer);
        forgeHammer(ItemInit.FROST_STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_FROST_STEEL,recipeConsumer);
        forgeHammer(ItemInit.IRON_FORGE_HAMMER.get(),Tags.Items.INGOTS_IRON, recipeConsumer);
        forgeHammer(ItemInit.KOBOLD_STEEL_FORGE_HAMMER.get(),ModItemTags.INGOTS_KOBOLD,recipeConsumer);
        forgeHammer(ItemInit.REFINED_OBSIDIAN_FORGE_HAMMER.get(),ModItemTags.INGOTS_REFINED_OBSIDIAN,recipeConsumer);
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
        nuggetIngotBlockRecipe(recipeConsumer,ItemInit.KOBOLD_NUGGET.get(),ItemInit.KOBOLD_INGOT.get(),BlockInit.KOBOLD_STEEL_BLOCK.get());
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

        ShapedRecipeBuilder.shaped(BlockInit.FORGE.get())
                .define('C', ItemInit.GRANITE_BRICK.get()).define('S',ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I', Blocks.COAL_BLOCK)
                .pattern(" C ").pattern("CIC").pattern("CSC").unlockedBy("has_material",
                        has(ItemInit.GRANITE_BRICK.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.ALLOY_FURNACE.get())
                .define('C', ItemInit.GRANITE_BRICK.get()).define('S',ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I',BlockInit.GRANITE_CLAY.get())
                .pattern("CCC").pattern("C C").pattern("ISI").unlockedBy("has_material",
                        has(ItemInit.GRANITE_BRICK.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.JAW_CRUSHER.get())
                .define('C',ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I',Tags.Items.INGOTS_IRON)
                .pattern("I I")
                .pattern("ICC")
                .pattern("ICI")
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.ADVANCED_FORGE.get())
                .define('C',ItemTags.STONE_CRAFTING_MATERIALS)
                .define('D',BlockInit.DIORITE_BRICKS.get())
                .define('B',ItemInit.DIORITE_BRICK.get())
                .define('F',Items.FLINT_AND_STEEL)
                .define('N',Blocks.NETHERRACK)
                .pattern("CCC")
                .pattern("CNF")
                .pattern("DBD")
                .unlockedBy("has_netherrack", has(Blocks.NETHERRACK))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.SPEAR_HEAD.get())
                .define('S',ItemTags.PLANKS)
                .pattern(" S ").pattern("SSS").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.DIORITE_CLAY_POWDER.get(),2)
                .define('#', ItemInit.FELDSPAR_POWDER.get()).define('X', ItemInit.DIORITE_POWDER.get())
                .pattern("X#X").pattern("#X#").pattern("X#X")
                .unlockedBy("has_feldspar_powder", has(ItemInit.FELDSPAR_POWDER.get())).save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.GRANITE_CLAY_POWDER.get(),2)
                .define('#', ItemInit.FELDSPAR_POWDER.get()).define('X', ItemInit.GRANITE_POWDER.get())
                .pattern("X#X").pattern("#X#").pattern("X#X")
                .unlockedBy("has_feldspar_powder", has(ItemInit.FELDSPAR_POWDER.get())).save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.TRIMMED_HIDE.get()).requires(ItemInit.UNTRIMMED_HIDE.get()).requires(ItemInit.TANNING_KNIFE.get())
                .unlockedBy("has_tanning_knife",has(ItemInit.TANNING_KNIFE.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.CURED_HIDE.get())
                .requires(ItemInit.TRIMMED_HIDE.get()).requires(ModItemTags.SALT)
                .unlockedBy("has_salt",has(ModItemTags.SALT)).save(recipeConsumer);

        singleItemRecipe(ItemInit.LIMED_HIDE.get(), ItemInit.CURED_HIDE.get(),recipeConsumer);
        singleItemRecipe(ItemInit.DELIMED_HIDE.get(), ItemInit.LIMED_HIDE.get(),recipeConsumer);
        singleItemRecipe(Items.LEATHER, ItemInit.DELIMED_HIDE.get(),recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"leather_from_tanning"));

    }

    protected void smelting(Consumer<FinishedRecipe> recipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.DIORITE_CLAY_BALL.get()), ItemInit.DIORITE_BRICK.get(),
                        0.3F, 200)
                .unlockedBy("has_diorite_clay_ball", has(ItemInit.DIORITE_CLAY_BALL.get())).save(recipeConsumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GRANITE_CLAY_BALL.get()), ItemInit.GRANITE_BRICK.get(),
                        0.3F, 200)
                .unlockedBy("has_granite_clay_ball", has(ItemInit.GRANITE_CLAY_BALL.get())).save(recipeConsumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.COBBLED_FELDSPAR.get()), BlockInit.FELDSPAR.get(),
                        0.1F, 200)
                .unlockedBy("has_cobbled_feldspar", has(BlockInit.COBBLED_FELDSPAR.get())).save(recipeConsumer);
    }

    protected void crusher(Consumer<FinishedRecipe> recipeConsumer) {
        CrusherRecipeBuilder.crusher(Ingredient.of(BlockInit.FELDSPAR.get()),ItemInit.FELDSPAR_POWDER.get(),4)
                .save(recipeConsumer);

        CrusherRecipeBuilder.crusher(Ingredient.of(Blocks.DIORITE),ItemInit.DIORITE_POWDER.get(),4)
                .save(recipeConsumer);
        CrusherRecipeBuilder.crusher(Ingredient.of(Blocks.GRANITE),ItemInit.GRANITE_POWDER.get(),4)
                .save(recipeConsumer);
    }

    protected void alloyFurnace(Consumer<FinishedRecipe> recipeConsumer) {
        AlloyFurnaceRecipeBuilder.alloy(ItemInit.BRONZE_INGOT.get(),4)
                .ingredient1(Tags.Items.INGOTS_COPPER,3)
                .ingredient2(ModItemTags.INGOTS_TIN,1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"bronze_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.CRYSTALLITE_INGOT.get(),2)
                .ingredient1(ModItemTags.INGOTS_STEEL,1)
                .ingredient2(ItemInit.CRYSTAL.get(),1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"crystallite_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.DUSKSTEEL_INGOT.get(),4)
                .ingredient1(ModItemTags.INGOTS_STEEL,1)
                .ingredient2(ModItemTags.INGOTS_SHADOW_PLATINUM,3)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"dusksteel_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.FROST_STEEL_INGOT.get(),2)
                .ingredient1(ModItemTags.INGOTS_PLATINUM,1)
                .ingredient2(ModItemTags.INGOTS_SILVER,1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"frost_steel_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.KOBOLD_INGOT.get(),4)
                .ingredient1(BlockInit.FELDSPAR.get(), 1)
                .ingredient2(Tags.Items.INGOTS_IRON,2)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"kobold_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.REFINED_OBSIDIAN_INGOT.get(),2)
                .ingredient1(ItemInit.OBSIDIAN_SHARD.get(), 1)
                .ingredient2(ModItemTags.INGOTS_STEEL,1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"refined_obsidian_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.SHADOW_PLATINUM_INGOT.get(),1)
                .ingredient1(ModItemTags.INGOTS_PLATINUM, 1)
                .ingredient2(ItemInit.CONGEALED_SHADOW.get(), 1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"shadow_platinum_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.STEEL_INGOT.get(),5)
                .ingredient1(Tags.Items.INGOTS_IRON, 4)
                .ingredient2(ItemTags.COALS, 1)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"steel_alloying"));

    }

    protected void drumQuenching(Consumer<FinishedRecipe> recipeConsumer) {

        DrumQuenchingRecipeBuilder.quenching(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"battleaxe_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.DAGGER_HOT_TOOL_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"dagger_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.CLEAVER_HOT_TOOL_HEAD.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"cleaver_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"kabutowari_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.KODACHI_HOT_TOOL_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"kodachi_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"longsword_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.MACE_HOT_TOOL_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"mace_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"makhaira_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.RAPIER_HOT_TOOL_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"rapier_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.SABRE_HOT_TOOL_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"sabre_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.SPEAR_HOT_TOOL_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"spear_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.STAFF_HOT_TOOL_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"staff_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.TALWAR_HOT_TOOL_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"talwar_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"zweihander_quenching"));
    }

    protected void drum(Consumer<FinishedRecipe> recipeConsumer) {

        DrumRecipeBuilder.drum(ItemInit.TRIMMED_HIDE.get(),ItemInit.CURED_HIDE.get())
                .time(500)
                .defaultFluid(Fluids.WATER)
                .additive(Ingredient.of(ModItemTags.SALT))
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"cured_hide_advanced"));

        DrumRecipeBuilder.drum(ItemInit.CURED_HIDE.get(),ItemInit.LIMED_HIDE.get())
                .time(500)
                .defaultFluid(FluidInit.MILK_OF_LIME.get())
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"limed_hide_advanced"));

        DrumRecipeBuilder.drum(ItemInit.LIMED_HIDE.get(),ItemInit.DELIMED_HIDE.get())
                .time(500)
                .defaultFluid(FluidInit.MAGNESIUM_LACTATE.get())
                .save(recipeConsumer,new ResourceLocation(AdvancedLootableWeapons.MODID,"delimed_hide_advanced"));
    }

    protected void anvilForging(Consumer<FinishedRecipe> recipeConsumer) {
        for (Map.Entry<String, WeaponMaterial> entry : WeaponMaterial.LOOKUP.entrySet()) {
            WeaponMaterial weaponMaterial = entry.getValue();
            if (weaponMaterial != WeaponMaterial.NULL) {
                AnvilForgingRecipeBuilder.anvilForging(weaponMaterial.tier().getRepairIngredient(), createToolHead(weaponMaterial))
                        .save(recipeConsumer, new ResourceLocation(AdvancedLootableWeapons.MODID, "anvil_forging_tool_head_" + entry.getKey()));
            }
        }
    }

    protected static ItemStack createToolHead(WeaponMaterial weaponMaterial) {
        ItemStack stack = new ItemStack(ItemInit.HOT_TOOL_HEAD.get());
        HotToolHeadItem.setMaterial(stack,weaponMaterial);
        HotToolHeadItem.setTemperature(stack,0);
        return stack;
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

    protected static void singleItemRecipe(ItemLike result,ItemLike input,Consumer<FinishedRecipe> consumer) {
        singleItemRecipe(result,input,consumer,RecipeBuilder.getDefaultRecipeId(result));
    }

    protected static void singleItemRecipe(ItemLike result,ItemLike input,Consumer<FinishedRecipe> consumer,ResourceLocation name) {
        ShapelessRecipeBuilder.shapeless(result)
                .requires(input)
                .unlockedBy("has_"+getItemName(input),has(input))
                .save(consumer,name);
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
