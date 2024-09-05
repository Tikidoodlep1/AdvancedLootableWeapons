package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.blockentity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AlloyFurnaceRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final int count;
    private Ingredient ingredient1 = Ingredient.EMPTY;
    private int count1 = 1;
    private Ingredient ingredient2 = Ingredient.EMPTY;
    private int count2 = 1;
    private int cookTime = AlloyFurnaceBlockEntity.MAX_COOKING_TIME;

    public static final String COOKTIME = "cookingTime";

    public AlloyFurnaceRecipeBuilder(ItemLike pResult, int pCount) {
        this.result = pResult.asItem();
        this.count = pCount;
    }

    public static AlloyFurnaceRecipeBuilder alloy(ItemLike result, int count) {
        return new AlloyFurnaceRecipeBuilder(result,count);
    }

    public static AlloyFurnaceRecipeBuilder alloy(ItemLike result) {
        return alloy(result,1);
    }

    public AlloyFurnaceRecipeBuilder ingredient1(ItemLike ingredient, int count) {
        return ingredient1(Ingredient.of(ingredient),count);
    }

    public AlloyFurnaceRecipeBuilder ingredient1(TagKey<Item> ingredient, int count) {
        return ingredient1(Ingredient.of(ingredient),count);
    }

    public AlloyFurnaceRecipeBuilder ingredient1(Ingredient ingredient, int count) {
        ingredient1 = ingredient;
        count1 = count;
        return this;
    }

    public AlloyFurnaceRecipeBuilder ingredient2(ItemLike ingredient, int count) {
        return ingredient2(Ingredient.of(ingredient),count);
    }

    public AlloyFurnaceRecipeBuilder ingredient2(TagKey<Item> ingredient, int count) {
        return ingredient2(Ingredient.of(ingredient),count);
    }

    public AlloyFurnaceRecipeBuilder cookTime(int cookTime) {
        this.cookTime = cookTime;
        return this;
    }


    public AlloyFurnaceRecipeBuilder ingredient2(Ingredient ingredient, int count) {
        ingredient2 = ingredient;
        count2 = count;
        return this;
    }



    @Override
    public AlloyFurnaceRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public AlloyFurnaceRecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new Result(pRecipeId,  this.result,this.count,
                this.ingredient1,this.ingredient2, this.count1,this.count2,cookTime));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final Ingredient ingredient1;
        private final Ingredient ingredient2;
        private final int count1;
        private final int count2;
        private final int cookTime;

        public Result(ResourceLocation pId, Item pResult, int pCount, Ingredient ingredient1, Ingredient ingredient2,
                      int count1, int count2, int cookTime) {
            this.id = pId;
            this.result = pResult;
            this.count = pCount;
            this.ingredient1 = ingredient1;
            this.ingredient2 = ingredient2;
            this.count1 = count1;
            this.count2 = count2;
            this.cookTime = cookTime;
        }

        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();

            JsonElement ing1 = ingredient1.toJson();
            JsonObject obj1 = new JsonObject();
            obj1.add("ingredient", ing1);
            obj1.addProperty("count",count1);
            jsonarray.add(obj1);

            if (ingredient2 != Ingredient.EMPTY) {
                JsonElement ing2 = ingredient2.toJson();
                JsonObject obj2 = new JsonObject();
                obj2.add("ingredient", ing2);
                obj2.addProperty("count", count2);
                jsonarray.add(obj2);
            }

            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }
            pJson.addProperty(COOKTIME,cookTime);

            pJson.add("result", jsonobject);
        }

        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.ALLOY_FURNACE.get();
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement()}
         * is non-null.
         */
        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

}
