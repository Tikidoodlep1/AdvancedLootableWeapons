package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.data.recipes.*;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import com.tiki.advancedlootableweapons.items.armor.UnboundArmorItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

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
        forgeHammer(ItemInit.BRONZE_FORGE_HAMMER.get(), ModItemTags.INGOTS_BRONZE, recipeConsumer);
        forgeHammer(ItemInit.COPPER_FORGE_HAMMER.get(), Tags.Items.INGOTS_COPPER, recipeConsumer);
        forgeHammer(ItemInit.CRYSTALLITE_FORGE_HAMMER.get(), ModItemTags.INGOTS_CRYSTALLITE, recipeConsumer);
        forgeHammer(ItemInit.DUSKSTEEL_FORGE_HAMMER.get(), ModItemTags.INGOTS_DUSKSTEEL, recipeConsumer);
        forgeHammer(ItemInit.FROST_STEEL_FORGE_HAMMER.get(), ModItemTags.INGOTS_FROST_STEEL, recipeConsumer);
        forgeHammer(ItemInit.IRON_FORGE_HAMMER.get(), Tags.Items.INGOTS_IRON, recipeConsumer);
        forgeHammer(ItemInit.KOBOLD_STEEL_FORGE_HAMMER.get(), ModItemTags.INGOTS_KOBOLD, recipeConsumer);
        forgeHammer(ItemInit.REFINED_OBSIDIAN_FORGE_HAMMER.get(), ModItemTags.INGOTS_REFINED_OBSIDIAN, recipeConsumer);
        forgeHammer(ItemInit.PLATINUM_FORGE_HAMMER.get(), ModItemTags.INGOTS_PLATINUM, recipeConsumer);
        forgeHammer(ItemInit.SHADOW_PLATINUM_FORGE_HAMMER.get(), ModItemTags.INGOTS_SHADOW_PLATINUM, recipeConsumer);
        forgeHammer(ItemInit.SILVER_FORGE_HAMMER.get(), ModItemTags.INGOTS_SILVER, recipeConsumer);
        forgeHammer(ItemInit.STEEL_FORGE_HAMMER.get(), ModItemTags.INGOTS_STEEL, recipeConsumer);
        forgeHammer(ItemInit.STONE_FORGE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS, recipeConsumer);

        sharpeningStone(ItemInit.BRONZE_WHETSTONE.get(), ModItemTags.INGOTS_BRONZE, recipeConsumer);
        sharpeningStone(ItemInit.COPPER_WHETSTONE.get(), Tags.Items.INGOTS_COPPER, recipeConsumer);
        sharpeningStone(ItemInit.CRYSTALLITE_WHETSTONE.get(), ModItemTags.INGOTS_CRYSTALLITE, recipeConsumer);
        sharpeningStone(ItemInit.DUSKSTEEL_WHETSTONE.get(), ModItemTags.INGOTS_DUSKSTEEL, recipeConsumer);
        sharpeningStone(ItemInit.FROST_STEEL_WHETSTONE.get(), ModItemTags.INGOTS_FROST_STEEL, recipeConsumer);
        sharpeningStone(ItemInit.IRON_WHETSTONE.get(), Tags.Items.INGOTS_IRON, recipeConsumer);
        sharpeningStone(ItemInit.KOBOLD_STEEL_WHETSTONE.get(), ModItemTags.INGOTS_KOBOLD, recipeConsumer);
        sharpeningStone(ItemInit.REFINED_OBSIDIAN_WHETSTONE.get(), ModItemTags.INGOTS_REFINED_OBSIDIAN, recipeConsumer);
        sharpeningStone(ItemInit.PLATINUM_WHETSTONE.get(), ModItemTags.INGOTS_PLATINUM, recipeConsumer);
        sharpeningStone(ItemInit.SHADOW_PLATINUM_WHETSTONE.get(), ModItemTags.INGOTS_SHADOW_PLATINUM, recipeConsumer);
        sharpeningStone(ItemInit.SILVER_WHETSTONE.get(), ModItemTags.INGOTS_SILVER, recipeConsumer);
        sharpeningStone(ItemInit.STEEL_WHETSTONE.get(), ModItemTags.INGOTS_STEEL, recipeConsumer);
        sharpeningStone(ItemInit.STONE_WHETSTONE.get(), ItemTags.STONE_TOOL_MATERIALS, recipeConsumer);

        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.DUSKSTEEL_NUGGET.get(), ItemInit.DUSKSTEEL_INGOT.get(), BlockInit.DUSKSTEEL_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.KOBOLD_STEEL_NUGGET.get(), ItemInit.KOBOLD_STEEL_INGOT.get(), BlockInit.KOBOLD_STEEL_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.TIN_NUGGET.get(), ItemInit.TIN_INGOT.get(), BlockInit.TIN_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.CRYSTALLITE_NUGGET.get(), ItemInit.CRYSTALLITE_INGOT.get(), BlockInit.CRYSTALLITE_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.PLATINUM_NUGGET.get(), ItemInit.PLATINUM_INGOT.get(), BlockInit.PLATINUM_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.BRONZE_NUGGET.get(), ItemInit.BRONZE_INGOT.get(), BlockInit.BRONZE_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.FROST_STEEL_NUGGET.get(), ItemInit.FROST_STEEL_INGOT.get(), BlockInit.FROST_STEEL_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.SILVER_NUGGET.get(), ItemInit.SILVER_INGOT.get(), BlockInit.SILVER_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.SHADOW_PLATINUM_NUGGET.get(), ItemInit.SHADOW_PLATINUM_INGOT.get(), BlockInit.SHADOW_PLATINUM_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.REFINED_OBSIDIAN_NUGGET.get(), ItemInit.REFINED_OBSIDIAN_INGOT.get(), BlockInit.REFINED_OBSIDIAN_BLOCK.get());
        nuggetIngotBlockRecipe(recipeConsumer, ItemInit.STEEL_NUGGET.get(), ItemInit.STEEL_INGOT.get(), BlockInit.STEEL_BLOCK.get());


        woodenWeapon(recipeConsumer, ItemInit.BATTLEAXE.get(), ItemInit.WOODEN_BATTLEAXE_HEAD.get(), true);
        woodenWeapon(recipeConsumer, ItemInit.CLEAVER.get(), ItemInit.WOODEN_CLEAVER_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.DAGGER.get(), ItemInit.WOODEN_DAGGER_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.KABUTOWARI.get(), ItemInit.WOODEN_KABUTOWARI_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.KODACHI.get(), ItemInit.WOODEN_KODACHI_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.LONGSWORD.get(), ItemInit.WOODEN_LONGSWORD_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.MACE.get(), ItemInit.WOODEN_MACE_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.MAKHAIRA.get(), ItemInit.WOODEN_MAKHAIRA_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.NODACHI.get(), ItemInit.WOODEN_NODACHI_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.RAPIER.get(), ItemInit.WOODEN_RAPIER_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.SABRE.get(), ItemInit.WOODEN_SABRE_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.STAFF.get(), ItemInit.WOODEN_STAFF_HEAD.get(), true);
        woodenWeapon(recipeConsumer, ItemInit.SPEAR.get(), ItemInit.WOODEN_SPEAR_HEAD.get(), true);
        woodenWeapon(recipeConsumer, ItemInit.TALWAR.get(), ItemInit.WOODEN_TALWAR_HEAD.get(), false);
        woodenWeapon(recipeConsumer, ItemInit.ZWEIHANDER.get(), ItemInit.WOODEN_ZWEIHANDER_HEAD.get(), true);

        twoByTwo(recipeConsumer, BlockInit.DIORITE_BRICKS.get(), ItemInit.DIORITE_BRICK.get());
        twoByTwo(recipeConsumer, BlockInit.GRANITE_BRICKS.get(), ItemInit.GRANITE_BRICK.get());
        twoByTwo(recipeConsumer, Blocks.OBSIDIAN, ItemInit.OBSIDIAN_SHARD.get());

        twoByTwo(recipeConsumer, BlockInit.DIORITE_CLAY.get(), ItemInit.DIORITE_CLAY_BALL.get());
        twoByTwo(recipeConsumer, BlockInit.GRANITE_CLAY.get(), ItemInit.GRANITE_CLAY_BALL.get());

        ShapedRecipeBuilder.shaped(BlockInit.FORGE.get())
                .define('C', ItemInit.GRANITE_BRICK.get()).define('S', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I', Blocks.COAL_BLOCK)
                .pattern(" C ").pattern("CIC").pattern("CSC").unlockedBy("has_material",
                        has(ItemInit.GRANITE_BRICK.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.ALLOY_FURNACE.get())
                .define('C', ItemInit.GRANITE_BRICK.get()).define('S', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I', BlockInit.GRANITE_CLAY.get())
                .pattern("CCC").pattern("C C").pattern("ISI").unlockedBy("has_material",
                        has(ItemInit.GRANITE_BRICK.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.JAW_CRUSHER.get())
                .define('C', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('I', Tags.Items.INGOTS_IRON)
                .pattern("I I")
                .pattern("ICC")
                .pattern("ICI")
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.ADVANCED_FORGE.get())
                .define('C', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('D', BlockInit.DIORITE_BRICKS.get())
                .define('B', ItemInit.DIORITE_BRICK.get())
                .define('F', Items.FLINT_AND_STEEL)
                .define('N', Blocks.NETHERRACK)
                .pattern("CCC")
                .pattern("CNF")
                .pattern("DBD")
                .unlockedBy("has_netherrack", has(Blocks.NETHERRACK))
                .save(recipeConsumer);

        //wooden parts

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_BATTLEAXE_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("S S")
                .pattern("SSS")
                .pattern("S S").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_CLEAVER_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S")
                .pattern("SS")
                .pattern("SS").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_DAGGER_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S")
                .pattern("S ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_KABUTOWARI_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("S  ")
                .pattern("S S")
                .pattern(" S ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_LONG_TOOL_ROD.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("S").pattern("S").unlockedBy("has_sticks", has(Tags.Items.RODS_WOODEN))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_LONGSWORD_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("  S")
                .pattern(" S ")
                .pattern("S  ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_MACE_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("S S")
                .pattern(" S ")
                .pattern("S S").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_MAKHAIRA_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("S ")
                .pattern("SS")
                .pattern("SS").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_NODACHI_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("S")
                .pattern("S")
                .pattern("S").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_RAPIER_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("  S")
                .pattern(" S ")
                .pattern("SS ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_SABRE_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S")
                .pattern("S ")
                .pattern("S ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_SPEAR_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S ").pattern("SSS").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_STAFF_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern("  S")
                .pattern(" SS")
                .pattern("SS ").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_TALWAR_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S")
                .pattern("S ")
                .pattern(" S").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(ItemInit.WOODEN_ZWEIHANDER_HEAD.get())
                .define('S', ItemTags.PLANKS)
                .pattern(" S ")
                .pattern(" S ")
                .pattern("SSS").unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeConsumer);
        ////

        ShapedRecipeBuilder.shaped(BlockInit.DIORITE_CLAY_POWDER.get(), 2)
                .define('#', ItemInit.FELDSPAR_POWDER.get()).define('X', ItemInit.DIORITE_POWDER.get())
                .pattern("X#X").pattern("#X#").pattern("X#X")
                .unlockedBy("has_feldspar_powder", has(ItemInit.FELDSPAR_POWDER.get())).save(recipeConsumer);

        ShapedRecipeBuilder.shaped(BlockInit.GRANITE_CLAY_POWDER.get(), 2)
                .define('#', ItemInit.FELDSPAR_POWDER.get()).define('X', ItemInit.GRANITE_POWDER.get())
                .pattern("X#X").pattern("#X#").pattern("X#X")
                .unlockedBy("has_feldspar_powder", has(ItemInit.FELDSPAR_POWDER.get())).save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.TRIMMED_HIDE.get()).requires(ItemInit.UNTRIMMED_HIDE.get()).requires(ItemInit.TANNING_KNIFE.get())
                .unlockedBy("has_tanning_knife", has(ItemInit.TANNING_KNIFE.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.CURED_HIDE.get())
                .requires(ItemInit.TRIMMED_HIDE.get()).requires(ModItemTags.SALT)
                .unlockedBy("has_salt", has(ModItemTags.SALT)).save(recipeConsumer);

        singleItemRecipe(ItemInit.LIMED_HIDE.get(), ItemInit.CURED_HIDE.get(), recipeConsumer);
        singleItemRecipe(ItemInit.DELIMED_HIDE.get(), ItemInit.LIMED_HIDE.get(), recipeConsumer);
        singleItemRecipe(Items.LEATHER, ItemInit.DELIMED_HIDE.get(), recipeConsumer, AdvancedLootableWeapons.id("leather_from_tanning"));

        ShapelessRecipeBuilder.shapeless(ItemInit.LEATHER_STRIP.get(), 4)
                .requires(ItemInit.TANNING_KNIFE.get())
                .requires(Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.LEATHER_BINDING.get())
                .requires(ItemInit.LEATHER_STRIP.get())
                .requires(ItemInit.LEATHER_STRIP.get())
                .unlockedBy(getHasName(ItemInit.LEATHER_STRIP.get()), has(ItemInit.LEATHER_STRIP.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.DIAMOND_STUDDED_LEATHER.get(), 4)
                .requires(Items.LEATHER)
                .requires(Items.LEATHER)
                .requires(Items.LEATHER)
                .requires(Items.LEATHER)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(recipeConsumer);

        unboundArmorSet(Items.LEATHER, ItemInit.UNBOUND_LEATHER_SET, recipeConsumer);

        twoShapeless(ItemInit.IRON_CHAIN_BINDING.get(), ItemInit.IRON_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.GOLD_CHAIN_BINDING.get(), ItemInit.GOLD_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.KOBOLD_STEEL_CHAIN_BINDING.get(), ItemInit.KOBOLD_STEEL_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.COPPER_CHAIN_BINDING.get(), ItemInit.COPPER_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.SILVER_CHAIN_BINDING.get(), ItemInit.SILVER_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.BRONZE_CHAIN_BINDING.get(), ItemInit.BRONZE_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.PLATINUM_CHAIN_BINDING.get(), ItemInit.PLATINUM_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.STEEL_CHAIN_BINDING.get(), ItemInit.STEEL_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.SHADOW_PLATINUM_CHAIN_BINDING.get(), ItemInit.SHADOW_PLATINUM_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.FROST_STEEL_CHAIN_BINDING.get(), ItemInit.FROST_STEEL_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.REFINED_OBSIDIAN_CHAIN_BINDING.get(), ItemInit.REFINED_OBSIDIAN_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.CRYSTALLITE_CHAIN_BINDING.get(), ItemInit.CRYSTALLITE_CHAIN_LINK.get(), recipeConsumer);
        twoShapeless(ItemInit.DUSKSTEEL_CHAIN_BINDING.get(), ItemInit.DUSKSTEEL_CHAIN_LINK.get(), recipeConsumer);

        unboundArmorSet(ItemInit.IRON_CHAIN_LINK.get(), ItemInit.UNBOUND_IRON_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.GOLD_CHAIN_LINK.get(), ItemInit.UNBOUND_GOLD_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.KOBOLD_STEEL_CHAIN_LINK.get(), ItemInit.UNBOUND_KOBOLD_STEEL_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.COPPER_CHAIN_LINK.get(), ItemInit.UNBOUND_COPPER_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.SILVER_CHAIN_LINK.get(), ItemInit.UNBOUND_SILVER_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.BRONZE_CHAIN_LINK.get(), ItemInit.UNBOUND_BRONZE_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.PLATINUM_CHAIN_LINK.get(), ItemInit.UNBOUND_PLATINUM_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.STEEL_CHAIN_LINK.get(), ItemInit.UNBOUND_STEEL_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.SHADOW_PLATINUM_CHAIN_LINK.get(), ItemInit.UNBOUND_SHADOW_PLATINUM_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.FROST_STEEL_CHAIN_LINK.get(), ItemInit.UNBOUND_FROST_STEEL_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.REFINED_OBSIDIAN_CHAIN_LINK.get(), ItemInit.UNBOUND_REFINED_OBSIDIAN_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.CRYSTALLITE_CHAIN_LINK.get(), ItemInit.UNBOUND_CRYSTALLITE_CHAIN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.DUSKSTEEL_CHAIN_LINK.get(), ItemInit.UNBOUND_DUSKSTEEL_CHAIN_SET, recipeConsumer);

        unboundArmorSet(ItemInit.IRON_ARMOR_PLATE.get(), ItemInit.UNBOUND_IRON_SET, recipeConsumer);
        unboundArmorSet(ItemInit.GOLD_ARMOR_PLATE.get(), ItemInit.UNBOUND_GOLD_SET, recipeConsumer);
        unboundArmorSet(ItemInit.KOBOLD_STEEL_ARMOR_PLATE.get(), ItemInit.UNBOUND_KOBOLD_STEEL_SET, recipeConsumer);
        unboundArmorSet(ItemInit.COPPER_ARMOR_PLATE.get(), ItemInit.UNBOUND_COPPER_SET, recipeConsumer);
        unboundArmorSet(ItemInit.SILVER_ARMOR_PLATE.get(), ItemInit.UNBOUND_SILVER_SET, recipeConsumer);
        unboundArmorSet(ItemInit.BRONZE_ARMOR_PLATE.get(), ItemInit.UNBOUND_BRONZE_SET, recipeConsumer);
        unboundArmorSet(ItemInit.PLATINUM_ARMOR_PLATE.get(), ItemInit.UNBOUND_PLATINUM_SET, recipeConsumer);
        unboundArmorSet(ItemInit.STEEL_ARMOR_PLATE.get(), ItemInit.UNBOUND_STEEL_SET, recipeConsumer);
        unboundArmorSet(ItemInit.SHADOW_PLATINUM_ARMOR_PLATE.get(), ItemInit.UNBOUND_SHADOW_PLATINUM_SET, recipeConsumer);
        unboundArmorSet(ItemInit.FROST_STEEL_ARMOR_PLATE.get(), ItemInit.UNBOUND_FROST_STEEL_SET, recipeConsumer);
        unboundArmorSet(ItemInit.REFINED_OBSIDIAN_ARMOR_PLATE.get(), ItemInit.UNBOUND_REFINED_OBSIDIAN_SET, recipeConsumer);
        unboundArmorSet(ItemInit.CRYSTALLITE_ARMOR_PLATE.get(), ItemInit.UNBOUND_CRYSTALLITE_SET, recipeConsumer);
        unboundArmorSet(ItemInit.DUSKSTEEL_ARMOR_PLATE.get(), ItemInit.UNBOUND_DUSKSTEEL_SET, recipeConsumer);

        unboundArmorSet(ItemInit.DIAMOND_STUDDED_LEATHER.get(), ItemInit.UNBOUND_DIAMOND_STUDDED_LEATHER_SET, recipeConsumer);
        unboundArmorSet(ItemInit.DIAMOND_STUDDED_STEEL_ARMOR_PLATE.get(), ItemInit.UNBOUND_DIAMOND_STUDDED_STEEL_SET, recipeConsumer);

        //armorSet(Ingredient.of(ItemInit.LEATHER_BINDING.get()), ItemInit.UNBOUND_LEATHER_SET, ItemInit.LEATHER_SET, recipeConsumer);
        armorSet(Ingredient.of(ItemInit.LEATHER_BINDING.get()), ItemInit.UNBOUND_DIAMOND_STUDDED_LEATHER_SET, ItemInit.DIAMOND_STUDDED_LEATHER_SET, recipeConsumer);
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
        CrusherRecipeBuilder.crusher(Ingredient.of(BlockInit.FELDSPAR.get()), ItemInit.FELDSPAR_POWDER.get(), 4)
                .save(recipeConsumer);

        CrusherRecipeBuilder.crusher(Ingredient.of(Blocks.DIORITE), ItemInit.DIORITE_POWDER.get(), 4)
                .save(recipeConsumer);
        CrusherRecipeBuilder.crusher(Ingredient.of(Blocks.GRANITE), ItemInit.GRANITE_POWDER.get(), 4)
                .save(recipeConsumer);
    }

    protected void alloyFurnace(Consumer<FinishedRecipe> recipeConsumer) {
        AlloyFurnaceRecipeBuilder.alloy(ItemInit.BRONZE_INGOT.get(), 4)
                .ingredient1(Tags.Items.INGOTS_COPPER, 3)
                .ingredient2(ModItemTags.INGOTS_TIN, 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("bronze_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.CRYSTALLITE_INGOT.get(), 2)
                .ingredient1(ModItemTags.INGOTS_STEEL, 1)
                .ingredient2(ItemInit.CRYSTAL.get(), 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("crystallite_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.DUSKSTEEL_INGOT.get(), 4)
                .ingredient1(ModItemTags.INGOTS_STEEL, 1)
                .ingredient2(ModItemTags.INGOTS_SHADOW_PLATINUM, 3)
                .save(recipeConsumer, AdvancedLootableWeapons.id("dusksteel_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.FROST_STEEL_INGOT.get(), 2)
                .ingredient1(ModItemTags.INGOTS_PLATINUM, 1)
                .ingredient2(ModItemTags.INGOTS_SILVER, 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("frost_steel_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.KOBOLD_STEEL_INGOT.get(), 4)
                .ingredient1(BlockInit.FELDSPAR.get(), 1)
                .ingredient2(Tags.Items.INGOTS_IRON, 2)
                .save(recipeConsumer, AdvancedLootableWeapons.id("kobold_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.REFINED_OBSIDIAN_INGOT.get(), 2)
                .ingredient1(ItemInit.OBSIDIAN_SHARD.get(), 1)
                .ingredient2(ModItemTags.INGOTS_STEEL, 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("refined_obsidian_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.SHADOW_PLATINUM_INGOT.get(), 1)
                .ingredient1(ModItemTags.INGOTS_PLATINUM, 1)
                .ingredient2(ItemInit.CONGEALED_SHADOW.get(), 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("shadow_platinum_alloying"));

        AlloyFurnaceRecipeBuilder.alloy(ItemInit.STEEL_INGOT.get(), 5)
                .ingredient1(Tags.Items.INGOTS_IRON, 4)
                .ingredient2(ItemTags.COALS, 1)
                .save(recipeConsumer, AdvancedLootableWeapons.id("steel_alloying"));

    }

    protected void drumQuenching(Consumer<FinishedRecipe> recipeConsumer) {

        DrumQuenchingRecipeBuilder.quenching(ItemInit.BATTLEAXE_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("battleaxe_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.DAGGER_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("dagger_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.CLEAVER_HEAD.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("cleaver_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.KABUTOWARI_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer, AdvancedLootableWeapons.id("kabutowari_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.KODACHI_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer, AdvancedLootableWeapons.id("kodachi_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.LONGSWORD_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("longsword_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.MACE_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("mace_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.MAKHAIRA_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer, AdvancedLootableWeapons.id("makhaira_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.RAPIER_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("rapier_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.SABRE_HEAD_4.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("sabre_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.SPEAR_HEAD_2.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer, AdvancedLootableWeapons.id("spear_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.STAFF_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("staff_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.TALWAR_HEAD_3.get())
                .defaultFluid(Fluids.WATER)
                .needsClay()
                .save(recipeConsumer, AdvancedLootableWeapons.id("talwar_quenching"));

        DrumQuenchingRecipeBuilder.quenching(ItemInit.ZWEIHANDER_HEAD_5.get())
                .defaultFluid(Fluids.WATER)
                .save(recipeConsumer, AdvancedLootableWeapons.id("zweihander_quenching"));
    }

    protected void drum(Consumer<FinishedRecipe> recipeConsumer) {

        DrumRecipeBuilder.drum(ItemInit.TRIMMED_HIDE.get(), ItemInit.CURED_HIDE.get())
                .time(500)
                .defaultFluid(Fluids.WATER)
                .additive(Ingredient.of(ModItemTags.SALT))
                .save(recipeConsumer, AdvancedLootableWeapons.id("cured_hide_advanced"));

        DrumRecipeBuilder.drum(ItemInit.CURED_HIDE.get(), ItemInit.LIMED_HIDE.get())
                .time(500)
                .defaultFluid(FluidInit.MILK_OF_LIME.get())
                .save(recipeConsumer, AdvancedLootableWeapons.id("limed_hide_advanced"));

        DrumRecipeBuilder.drum(ItemInit.LIMED_HIDE.get(), ItemInit.DELIMED_HIDE.get())
                .time(500)
                .defaultFluid(FluidInit.MAGNESIUM_LACTATE.get())
                .save(recipeConsumer, AdvancedLootableWeapons.id("delimed_hide_advanced"));
    }

    protected void anvilForging(Consumer<FinishedRecipe> recipeConsumer) {
        for (Map.Entry<String, WeaponMaterial> entry : WeaponMaterial.LOOKUP.entrySet()) {
            WeaponMaterial weaponMaterial = entry.getValue();
            if (weaponMaterial.metalStats() != null) {
                ItemStack toolHead = createToolHead(weaponMaterial);
                AnvilForgingRecipeBuilder.anvilMaterialForging(weaponMaterial.tier().getRepairIngredient(), toolHead)
                        .save(recipeConsumer, AdvancedLootableWeapons.id("anvil_forging_tool_head_" + entry.getKey()));
            }
        }

        //tool rods

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.TOOL_ROD.get(), recipeConsumer);
        toolForging(ItemInit.TOOL_ROD.get(), ItemInit.TOOL_ROD_2.get(), recipeConsumer);
        twoToolForging(ItemInit.TOOL_ROD_2.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.LONG_TOOL_ROD.get(), recipeConsumer);

        // battleaxe

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.BATTLEAXE_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.BATTLEAXE_HEAD.get(), ItemInit.BATTLEAXE_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.BATTLEAXE_HEAD_2.get(), ItemInit.BATTLEAXE_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.BATTLEAXE_HEAD_3.get(), ItemInit.BATTLEAXE_HEAD_4.get(), recipeConsumer);
        toolForging(ItemInit.BATTLEAXE_HEAD_4.get(), ItemInit.BATTLEAXE_HEAD_5.get(), recipeConsumer);
        twoToolForging(ItemInit.BATTLEAXE_HEAD_5.get(), ItemInit.LONG_TOOL_ROD.get(), ItemInit.BATTLEAXE.get(), recipeConsumer);

        // cleaver

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.CLEAVER_HEAD.get(), recipeConsumer);
        twoToolForging(ItemInit.CLEAVER_HEAD.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.CLEAVER.get(), recipeConsumer);

        //dagger chain

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.DAGGER_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.DAGGER_HEAD.get(), ItemInit.DAGGER_HEAD_2.get(), recipeConsumer);
        twoToolForging(ItemInit.DAGGER_HEAD_2.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.DAGGER.get(), recipeConsumer);

        // kabutowari

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.KABUTOWARI_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.KABUTOWARI_HEAD.get(), ItemInit.KABUTOWARI_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.KABUTOWARI_HEAD_2.get(), ItemInit.KABUTOWARI_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.KABUTOWARI_HEAD_3.get(), ItemInit.KABUTOWARI_HEAD_4.get(), recipeConsumer);
        toolForging(ItemInit.KABUTOWARI_HEAD_4.get(), ItemInit.KABUTOWARI_HEAD_5.get(), recipeConsumer);
        twoToolForging(ItemInit.KABUTOWARI_HEAD_5.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.KABUTOWARI.get(), recipeConsumer);

        // kodachi

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.KODACHI_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.KODACHI_HEAD.get(), ItemInit.KODACHI_HEAD_2.get(), recipeConsumer);
        twoToolForging(ItemInit.KODACHI_HEAD_2.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.KODACHI.get(), recipeConsumer);

        // longsword

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.LONGSWORD_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.LONGSWORD_HEAD.get(), ItemInit.LONGSWORD_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.LONGSWORD_HEAD_2.get(), ItemInit.LONGSWORD_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.LONGSWORD_HEAD_3.get(), ItemInit.LONGSWORD_HEAD_4.get(), recipeConsumer);
        twoToolForging(ItemInit.LONGSWORD_HEAD_4.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.LONGSWORD.get(), recipeConsumer);

        // mace

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.MACE_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.MACE_HEAD.get(), ItemInit.MACE_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.MACE_HEAD_2.get(), ItemInit.MACE_HEAD_3.get(), recipeConsumer);
        twoToolForging(ItemInit.MACE_HEAD_3.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.MACE.get(), recipeConsumer);

        // makhaira

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.MAKHAIRA_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.MAKHAIRA_HEAD.get(), ItemInit.MAKHAIRA_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.MAKHAIRA_HEAD_2.get(), ItemInit.MAKHAIRA_HEAD_3.get(), recipeConsumer);
        twoToolForging(ItemInit.MAKHAIRA_HEAD_3.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.MAKHAIRA.get(), recipeConsumer);

        // nodachi

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.NODACHI_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.NODACHI_HEAD.get(), ItemInit.NODACHI_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.NODACHI_HEAD_2.get(), ItemInit.NODACHI_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.NODACHI_HEAD_3.get(), ItemInit.NODACHI_HEAD_4.get(), recipeConsumer);
        twoToolForging(ItemInit.NODACHI_HEAD_4.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.NODACHI.get(), recipeConsumer);

        // rapier

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.RAPIER_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.RAPIER_HEAD.get(), ItemInit.RAPIER_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.RAPIER_HEAD_2.get(), ItemInit.RAPIER_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.RAPIER_HEAD_3.get(), ItemInit.RAPIER_HEAD_4.get(), recipeConsumer);
        twoToolForging(ItemInit.RAPIER_HEAD_4.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.RAPIER.get(), recipeConsumer);

        // sabre

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.SABRE_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.SABRE_HEAD.get(), ItemInit.SABRE_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.SABRE_HEAD_2.get(), ItemInit.SABRE_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.SABRE_HEAD_3.get(), ItemInit.SABRE_HEAD_4.get(), recipeConsumer);
        twoToolForging(ItemInit.SABRE_HEAD_4.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.SABRE.get(), recipeConsumer);

        // spear

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.SPEAR_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.SPEAR_HEAD.get(), ItemInit.SPEAR_HEAD_2.get(), recipeConsumer);
        twoToolForging(ItemInit.SPEAR_HEAD_2.get(), ItemInit.LONG_TOOL_ROD.get(), ItemInit.SPEAR.get(), recipeConsumer);

        // staff

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.STAFF_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.STAFF_HEAD.get(), ItemInit.STAFF_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.STAFF_HEAD_2.get(), ItemInit.STAFF_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.STAFF_HEAD_3.get(), ItemInit.STAFF_HEAD_4.get(), recipeConsumer);
        twoToolForging(ItemInit.STAFF_HEAD_4.get(), ItemInit.LONG_TOOL_ROD.get(), ItemInit.STAFF.get(), recipeConsumer);

        // talwar

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.TALWAR_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.TALWAR_HEAD.get(), ItemInit.TALWAR_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.TALWAR_HEAD_2.get(), ItemInit.TALWAR_HEAD_3.get(), recipeConsumer);
        twoToolForging(ItemInit.TALWAR_HEAD_3.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.TALWAR.get(), recipeConsumer);

        // zweihander

        toolForging(ItemInit.TOOL_HEAD.get(), ItemInit.ZWEIHANDER_HEAD.get(), recipeConsumer);
        toolForging(ItemInit.ZWEIHANDER_HEAD.get(), ItemInit.ZWEIHANDER_HEAD_2.get(), recipeConsumer);
        toolForging(ItemInit.ZWEIHANDER_HEAD_2.get(), ItemInit.ZWEIHANDER_HEAD_3.get(), recipeConsumer);
        toolForging(ItemInit.ZWEIHANDER_HEAD_3.get(), ItemInit.ZWEIHANDER_HEAD_4.get(), recipeConsumer);
        toolForging(ItemInit.ZWEIHANDER_HEAD_4.get(), ItemInit.ZWEIHANDER_HEAD_5.get(), recipeConsumer);
        twoToolForging(ItemInit.ZWEIHANDER_HEAD_5.get(), ItemInit.TOOL_ROD_2.get(), ItemInit.ZWEIHANDER.get(), recipeConsumer);

        //armors


        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_BRONZE_SET,ItemInit.BRONZE_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_COPPER_SET,ItemInit.COPPER_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_CRYSTALLITE_SET,ItemInit.CRYSTALLITE_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_DUSKSTEEL_SET,ItemInit.DUSKSTEEL_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_FROST_STEEL_SET,ItemInit.FROST_STEEL_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_KOBOLD_STEEL_SET,ItemInit.KOBOLD_STEEL_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_PLATINUM_SET,ItemInit.PLATINUM_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_SHADOW_PLATINUM_SET,ItemInit.SHADOW_PLATINUM_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_SILVER_SET,ItemInit.SILVER_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_STEEL_SET,ItemInit.STEEL_SET,recipeConsumer);

        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_BRONZE_CHAIN_SET,ItemInit.BRONZE_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_COPPER_CHAIN_SET,ItemInit.COPPER_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_CRYSTALLITE_CHAIN_SET,ItemInit.CRYSTALLITE_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_DUSKSTEEL_CHAIN_SET,ItemInit.DUSKSTEEL_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_FROST_STEEL_CHAIN_SET,ItemInit.FROST_STEEL_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_KOBOLD_STEEL_CHAIN_SET,ItemInit.KOBOLD_STEEL_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_PLATINUM_CHAIN_SET,ItemInit.PLATINUM_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_SHADOW_PLATINUM_CHAIN_SET,ItemInit.SHADOW_PLATINUM_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_SILVER_CHAIN_SET,ItemInit.SILVER_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_STEEL_CHAIN_SET,ItemInit.STEEL_CHAIN_SET,recipeConsumer);

        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_GOLD_CHAIN_SET,ItemInit.GOLD_CHAIN_SET,recipeConsumer);
        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_IRON_CHAIN_SET,ItemInit.IRON_CHAIN_SET,recipeConsumer);

        armorSetForging(Ingredient.of(ModItemTags.CHAIN_BINDINGS),ItemInit.UNBOUND_DIAMOND_STUDDED_STEEL_SET,ItemInit.DIAMOND_STUDDED_STEEL_SET,recipeConsumer);



    }

    protected void toolForging(ItemLike input, ItemLike result, Consumer<FinishedRecipe> consumer) {
        AnvilForgingRecipeBuilder.anvilToolForging(input, result).save(consumer, new ResourceLocation("anvil_forging_" + getItemName(result)));
    }

    protected void twoToolForging(ItemLike input, ItemLike input2, ItemLike result, Consumer<FinishedRecipe> consumer) {
        AnvilForgingRecipeBuilder.anvilTwoToolForging(input, input2, result).save(consumer, new ResourceLocation("anvil_forging_" + getItemName(result)));
    }


    protected static ItemStack createToolHead(WeaponMaterial weaponMaterial) {
        ItemStack stack = new ItemStack(ItemInit.TOOL_HEAD.get());
        HeatableToolPartItem.setMaterial(stack, weaponMaterial);
        HeatableToolPartItem.setTemperature(stack, 0);
        return stack;
    }

    protected static void twoByTwo(Consumer<FinishedRecipe> consumer, ItemLike result, Item ing) {
        ShapedRecipeBuilder.shaped(result).define('#', ing)
                .pattern("##").pattern("##")
                .unlockedBy("has_brick", has(ing)).save(consumer);
    }

    protected static void twoShapeless(ItemLike result, Item ing, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(result)
                .requires(ing).requires(ing)
                .unlockedBy(getHasName(ing),has(ing))
                .save(consumer);
    }

    protected static void woodenWeapon(Consumer<FinishedRecipe> consumer, ItemLike result, Item head, boolean longHandle) {
        Item handle = longHandle ? ItemInit.WOODEN_LONG_TOOL_ROD.get() : Items.STICK;
        String base = Registry.ITEM.getKey(head).getPath().replace("_head", "");

        ItemStack woodWeapon = new ItemStack(result);
        AlwWeaponItem.setMaterial(woodWeapon, "wood");

        NBTOutputShapedRecipeBuilder.shaped(woodWeapon).define('W', head).define('S', handle)
                .pattern("W").pattern("S").unlockedBy("has_" + base + "_head", has(head)).save(consumer);
    }

    protected static void singleItemRecipe(ItemLike result, ItemLike input, Consumer<FinishedRecipe> consumer) {
        singleItemRecipe(result, input, consumer, RecipeBuilder.getDefaultRecipeId(result));
    }

    protected static void singleItemRecipe(ItemLike result, ItemLike input, Consumer<FinishedRecipe> consumer, ResourceLocation name) {
        ShapelessRecipeBuilder.shapeless(result)
                .requires(input)
                .unlockedBy("has_" + getItemName(input), has(input))
                .save(consumer, name);
    }

    protected static void nuggetIngotBlockRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike nugget, ItemLike ingot, ItemLike block
    ) {
        String materialname = Registry.ITEM.getKey((Item) nugget).getPath().replace("_nugget", "");

        nineBlockStorageRecipesRecipesWithCustomUnpackingModded(pFinishedRecipeConsumer, ingot, block,
                materialname + "_ingot_from_" + materialname + "_block", materialname + "_ingot");

        nineBlockStorageRecipesWithCustomPackingModded(pFinishedRecipeConsumer, nugget, ingot,
                materialname + "_ingot_from_nuggets", materialname + "_ingot");
    }

    protected static void nineBlockStorageRecipesWithCustomPackingModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, String pPackingRecipeGroup) {
        nineBlockStorageRecipesModded(pFinishedRecipeConsumer, pUnpacked, pPacked, pPackingRecipeName, pPackingRecipeGroup, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void nineBlockStorageRecipesRecipesWithCustomUnpackingModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pUnpackingRecipeName, String pUnpackingRecipeGroup) {
        nineBlockStorageRecipesModded(pFinishedRecipeConsumer, pUnpacked, pPacked, getSimpleRecipeName(pPacked), null, pUnpackingRecipeName, pUnpackingRecipeGroup);
    }

    protected static void nineBlockStorageRecipesModded(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pUnpacked, ItemLike pPacked, String pPackingRecipeName, @Nullable String pPackingRecipeGroup, String pUnpackingRecipeName, @Nullable String pUnpackingRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpacked, 9).requires(pPacked).group(pUnpackingRecipeGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, AdvancedLootableWeapons.id(pUnpackingRecipeName));
        ShapedRecipeBuilder.shaped(pPacked).define('#', pUnpacked).pattern("###").pattern("###").pattern("###").group(pPackingRecipeGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, AdvancedLootableWeapons.id(pPackingRecipeName));
    }

    protected void forgeHammer(Item hammer, TagKey<Item> ing, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hammer).define('I', ing).define('S', Tags.Items.RODS_WOODEN)
                .pattern("III").pattern("ISI").pattern(" S ").unlockedBy("has_material",
                        has(ing))
                .save(consumer);
    }

    protected void sharpeningStone(Item stone, TagKey<Item> ing, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(stone).define('I', ing).define('S', Items.FLINT)
                .pattern("  S").pattern("III").unlockedBy("has_material",
                        has(ing))
                .save(consumer);
    }

    protected void unboundArmorSet(Item material, Map<EquipmentSlot, RegistryObject<UnboundArmorItem>> map, Consumer<FinishedRecipe> consumer) {
        unboundArmorSet(material, map.get(EquipmentSlot.HEAD).get(), map.get(EquipmentSlot.CHEST).get(),
                map.get(EquipmentSlot.LEGS).get(), map.get(EquipmentSlot.FEET).get(), consumer);
    }

    protected void unboundArmorSet(Item material, Item helmet, Item chestplate, Item leggings, Item boots, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(boots).define('X', material).pattern("X X").pattern("X X").unlockedBy(getHasName(material), has(material)).save(consumer);
        ShapedRecipeBuilder.shaped(chestplate).define('X', material).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy(getHasName(material), has(material)).save(consumer);
        ShapedRecipeBuilder.shaped(helmet).define('X', material).pattern("XXX").pattern("X X").unlockedBy(getHasName(material), has(material)).save(consumer);
        ShapedRecipeBuilder.shaped(leggings).define('X', material).pattern("XXX").pattern("X X").pattern("X X").unlockedBy(getHasName(material), has(material)).save(consumer);
    }

    protected <I extends Item> void armorSet(Ingredient material, Map<EquipmentSlot, RegistryObject<UnboundArmorItem>> map,
                            Map<EquipmentSlot, RegistryObject<I>> finished, Consumer<FinishedRecipe> consumer) {
        for (EquipmentSlot slot : Utils.ARMOR_SLOTS.keySet()) {
            RegistryObject<UnboundArmorItem> unbound = map.get(slot);
            RegistryObject<I> bound = finished.get(slot);
            ShapelessRecipeBuilder.shapeless(bound.get())
                    .requires(material)
                    .requires(unbound.get())
                    .unlockedBy(getHasName(unbound.get()), has(unbound.get()))
                    .save(consumer);
        }
    }

    protected void armorSetForging(Ingredient material, Map<EquipmentSlot, RegistryObject<UnboundArmorItem>> map,
                            Map<EquipmentSlot, RegistryObject<BoundArmorItem>> finished, Consumer<FinishedRecipe> consumer) {
        for (EquipmentSlot slot : Utils.ARMOR_SLOTS.keySet()) {
            RegistryObject<UnboundArmorItem> unbound = map.get(slot);
            RegistryObject<BoundArmorItem> bound = finished.get(slot);
            AnvilForgingRecipeBuilder.anvilArmorForging(unbound.get(),material,new ItemStack(bound.get()))
                    .unlockedBy(getHasName(unbound.get()), has(unbound.get()))
                    .save(consumer);
        }
    }
}
