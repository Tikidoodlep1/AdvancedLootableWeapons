package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

public class CrusherRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private int bonus = 0;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    private String group;
    private final RecipeSerializer<?> type;

    public CrusherRecipeBuilder(RecipeSerializer<?> pType, Ingredient pIngredient, ItemLike pResult, int pCount) {
        this.type = pType;
        this.result = pResult.asItem();
        this.ingredient = pIngredient;
        this.count = pCount;
    }

    public static CrusherRecipeBuilder crusher(Ingredient pIngredient, ItemLike pResult) {
        return new CrusherRecipeBuilder(JawCrusherRecipe.Serializer.INSTANCE, pIngredient, pResult, 1);
    }

    public static CrusherRecipeBuilder crusher(Ingredient pIngredient, ItemLike pResult, int pCount) {
        return new CrusherRecipeBuilder(JawCrusherRecipe.Serializer.INSTANCE, pIngredient, pResult, pCount);
    }

    public CrusherRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        //this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    public CrusherRecipeBuilder bonus(int bonus) {
        this.bonus = bonus;
        return this;
    }

    public CrusherRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        //this.ensureValid(pRecipeId);
        //this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new CrusherRecipeBuilder.Result(pRecipeId, this.type, this.group == null ? "" : this.group, this.ingredient, this.result, this.count,this.bonus));
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
        private final Item result;
        private final int count;
        private final int bonus;
        private final RecipeSerializer<?> type;

        public Result(ResourceLocation pId, RecipeSerializer<?> pType, String pGroup, Ingredient pIngredient, Item pResult, int pCount, int bonus) {
            this.id = pId;
            this.type = pType;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.count = pCount;
            this.bonus = bonus;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.ingredient.toJson());
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }
            if (bonus > 0) {
                jsonobject.addProperty("bonus",this.bonus);
            }
            pJson.add("result", jsonobject);
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
            return null;
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement()}
         * is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}