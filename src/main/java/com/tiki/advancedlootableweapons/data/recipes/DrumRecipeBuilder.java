package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DrumRecipeBuilder implements RecipeBuilder {
    private final Ingredient input;
    private Ingredient additive = Ingredient.EMPTY;
    private final Item result;
    @Nullable
    private String group;

    private FluidStack fluid = FluidStack.EMPTY;

    private int time = 500;
    public DrumRecipeBuilder(Ingredient input,ItemLike result) {
        this.input = input;
        this.result = result.asItem();
    }

    public static DrumRecipeBuilder drum(Ingredient input,ItemLike result) {
        return new DrumRecipeBuilder(input, result);
    }

    public static DrumRecipeBuilder drum(ItemLike input,ItemLike result) {
        return drum(Ingredient.of(input),result);
    }

    public DrumRecipeBuilder additive(Ingredient additive) {
        this.additive = additive;
        return this;
    }


    public DrumRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    public DrumRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public DrumRecipeBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return this;
    }

    public DrumRecipeBuilder defaultFluid(Fluid fluid) {
        return fluid(new FluidStack(fluid,1000));
    }

    public DrumRecipeBuilder time(int time) {
        this.time = time;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(
                new DrumRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group,this.input,this.additive,this.result,fluid,time));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient input;
        private final Ingredient additive;
        private final Item result;
        private final FluidStack fluid;
        private final int time;

        public Result(ResourceLocation pId, String pGroup, Ingredient input, Ingredient additive, Item result, FluidStack fluid, int time) {
            this.id = pId;
            this.group = pGroup;
            this.input = input;
            this.additive = additive;
            this.result = result;
            this.fluid = fluid;
            this.time = time;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }
            pJson.add("input", input.toJson());

            if (!additive.isEmpty()) {
                pJson.add("additive", additive.toJson());
            }

            if (!fluid.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.addProperty("fluid", Registry.FLUID.getKey(this.fluid.getFluid()).toString());
                jsonobject1.addProperty("amount", fluid.getAmount());
                pJson.add("fluid_input", jsonobject1);
            }
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
        //    if (this.count > 1) {
         //       jsonobject.addProperty("count", this.count);
        //    }
            pJson.addProperty("time",time);
            pJson.add("result", jsonobject);
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return RecipeInit.DRUM.get();
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