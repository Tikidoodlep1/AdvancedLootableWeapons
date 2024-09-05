package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.blockentity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.blockentity.TanningRackBlockEntity;
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

public class TanningRackRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final int count;
    private final Ingredient ingredient;
    private int cookTime = TanningRackBlockEntity.MAX_COOKING_TIME;

    public static final String COOKTIME = "cookingTime";

    public TanningRackRecipeBuilder(ItemLike pResult, Ingredient ingredient,int pCount) {
        this.result = pResult.asItem();
        this.ingredient = ingredient;
        this.count = pCount;
    }

    public static TanningRackRecipeBuilder tanning(ItemLike result, ItemLike item, int count) {
        return new TanningRackRecipeBuilder(result,Ingredient.of(item),count);
    }

    public static TanningRackRecipeBuilder tanning(ItemLike result, ItemLike item) {
        return tanning(result,item,1);
    }

    public TanningRackRecipeBuilder cookTime(int cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    @Override
    public TanningRackRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public TanningRackRecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new Result(pRecipeId,  this.result,this.count,
                this.ingredient, cookTime));
    }


    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final Ingredient ingredient1;
        private final int cookTime;

        public Result(ResourceLocation pId, Item pResult, int pCount, Ingredient ingredient1,
                      int cookTime) {
            this.id = pId;
            this.result = pResult;
            this.count = pCount;
            this.ingredient1 = ingredient1;
            this.cookTime = cookTime;
        }

        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredient", ingredient1.toJson());

            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }
            pJson.addProperty(COOKTIME,cookTime);

            pJson.add("result", jsonobject);
        }

        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.TANNING_RACK.get();
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
