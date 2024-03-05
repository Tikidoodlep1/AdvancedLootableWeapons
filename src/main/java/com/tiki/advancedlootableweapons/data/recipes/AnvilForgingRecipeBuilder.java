package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class AnvilForgingRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient ingredient;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    private String group;
    private final RecipeSerializer<?> type;

    public AnvilForgingRecipeBuilder(RecipeSerializer<?> pType, Ingredient pIngredient, ItemStack result) {
        this.type = pType;
        this.result = result;
        this.ingredient = pIngredient;
    }

    public static AnvilForgingRecipeBuilder anvilForging(Ingredient pIngredient, ItemLike pResult) {
        return new AnvilForgingRecipeBuilder(RecipeInit.ANVIL_FORGING_RECIPE.get(), pIngredient, new ItemStack(pResult));
    }

    public static AnvilForgingRecipeBuilder anvilForging(Ingredient pIngredient, ItemLike pResult, int pCount) {
        return new AnvilForgingRecipeBuilder(RecipeInit.ANVIL_FORGING_RECIPE.get(), pIngredient,new ItemStack(pResult, pCount));
    }

    public static AnvilForgingRecipeBuilder anvilForging(Ingredient pIngredient, ItemStack stack) {
        return new AnvilForgingRecipeBuilder(RecipeInit.ANVIL_FORGING_RECIPE.get(), pIngredient,stack);
    }


    public AnvilForgingRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    public AnvilForgingRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.result.getItem();
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        //this.ensureValid(pRecipeId);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new Result(pRecipeId, this.type, this.group == null ? "" : this.group, this.ingredient, this.result, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + this.result.getItem().getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final ItemStack result;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<?> type;

        public Result(ResourceLocation pId, RecipeSerializer<?> pType, String pGroup, Ingredient pIngredient, ItemStack pResult, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            this.id = pId;
            this.type = pType;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.ingredient.toJson());
            pJson.add("result", serializeStack(result));
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return this.type;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement()}
         * is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }

    private static JsonObject serializeStack(ItemStack stack) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item",Registry.ITEM.getKey(stack.getItem()).toString());
        jsonObject.addProperty("Count",stack.getCount());
        if (stack.hasTag()) {
            jsonObject.addProperty("nbt",stack.getTag().toString());
        }
        return jsonObject;
    }

}
