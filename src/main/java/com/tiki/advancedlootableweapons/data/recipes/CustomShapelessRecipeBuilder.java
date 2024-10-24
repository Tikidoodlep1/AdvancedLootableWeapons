package com.tiki.advancedlootableweapons.data.recipes;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.checkerframework.checker.units.qual.C;

public class CustomShapelessRecipeBuilder extends ShapelessRecipeBuilder {
    protected RecipeSerializer<?> serializer = RecipeSerializer.SHAPELESS_RECIPE;
    protected final ItemStack stack;

    public CustomShapelessRecipeBuilder(ItemStack result) {
        super(result.getItem(), result.getCount());
        this.stack = result;
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static CustomShapelessRecipeBuilder customShapeless(ItemStack pResult) {
        return new CustomShapelessRecipeBuilder(pResult);
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static CustomShapelessRecipeBuilder customShapeless(ItemLike pResult) {
        return customShapeless(pResult, 1);
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static CustomShapelessRecipeBuilder customShapeless(ItemLike pResult, int pCount) {
        return new CustomShapelessRecipeBuilder(new ItemStack(pResult, pCount));
    }

    public ShapelessRecipeBuilder serializer(RecipeSerializer<?> serializer) {
        this.serializer = serializer;
        return this;
    }

    /**
     * Adds an ingredient that can be any item in the given tag.
     */
    @Override
    public CustomShapelessRecipeBuilder requires(TagKey<Item> pTag) {
        return (CustomShapelessRecipeBuilder) super.requires(pTag);
    }

    /**
     * Adds an ingredient of the given item.
     */
    @Override
    public CustomShapelessRecipeBuilder requires(ItemLike pItem) {
        return (CustomShapelessRecipeBuilder) super.requires(pItem);
    }

    /**
     * Adds the given ingredient multiple times.
     */
    @Override
    public CustomShapelessRecipeBuilder requires(ItemLike pItem, int pQuantity) {
        return (CustomShapelessRecipeBuilder) super.requires(pItem,pQuantity);
    }

    /**
     * Adds an ingredient.
     */
    @Override
    public CustomShapelessRecipeBuilder requires(Ingredient pIngredient) {
        return (CustomShapelessRecipeBuilder) super.requires(pIngredient);
    }

    /**
     * Adds an ingredient multiple times.
     */
    @Override
    public CustomShapelessRecipeBuilder requires(Ingredient pIngredient, int pQuantity) {
        return (CustomShapelessRecipeBuilder) super.requires(pIngredient,pQuantity);
    }

    @Override
    public CustomShapelessRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return (CustomShapelessRecipeBuilder) super.unlockedBy(pCriterionName, pCriterionTrigger);
    }

    @Override
    public CustomShapelessRecipeBuilder group(@Nullable String pGroupName) {
        return (CustomShapelessRecipeBuilder) super.group(pGroupName);
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new CustomResult(pRecipeId, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath()),serializer, stack));
    }

    public static class CustomResult extends Result {


        private final RecipeSerializer<?> serializer;
        private final ItemStack result;

        public CustomResult(ResourceLocation pId, Item pResult, int pCount, String pGroup, List<Ingredient> pIngredients, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId, RecipeSerializer<?> serializer, ItemStack result) {
            super(pId, pResult, pCount, pGroup, pIngredients, pAdvancement, pAdvancementId);
            this.serializer = serializer;
            this.result = result;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            super.serializeRecipeData(pJson);
            pJson.remove("result");
            pJson.add("result",serializeItemStack(result,true));
        }

        public static JsonObject serializeItemStack(ItemStack stack, boolean saveNBT) {
            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("item", Registry.ITEM.getKey(stack.getItem()).toString());
            if (stack.getCount() > 1) {
                resultObj.addProperty("count", stack.getCount());
            }
            if (stack.hasTag() && saveNBT) {
                resultObj.addProperty("nbt", stack.getTag().toString());
            }
            return resultObj;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return serializer;
        }
    }
}