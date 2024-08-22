package com.tiki.advancedlootableweapons.data.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DrumQuenchingRecipeBuilder implements RecipeBuilder {
    private final Item input;
    @Nullable
    private String group;

    private FluidStack fluid = FluidStack.EMPTY;

    private int time = 50;
    private boolean requiresClay;

    public DrumQuenchingRecipeBuilder(Item input) {
        this.input = input;
    }

    public static DrumQuenchingRecipeBuilder quenching(HeatableToolPartItem item) {
        if (!item.isFinished()) throw new RuntimeException("can't quench "+item);
        return new DrumQuenchingRecipeBuilder(item);
    }


    public DrumQuenchingRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    public DrumQuenchingRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public DrumQuenchingRecipeBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return this;
    }

    public DrumQuenchingRecipeBuilder defaultFluid(Fluid fluid) {
        return fluid(new FluidStack(fluid,1000));
    }

    public DrumQuenchingRecipeBuilder time(int time) {
        this.time = time;
        return this;
    }

    public DrumQuenchingRecipeBuilder needsClay() {
        this.requiresClay = true;
        return this;
    }

    public Item getResult() {
        return this.input;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(
                new DrumQuenchingRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group,this.input,fluid,time,requiresClay));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Item input;
        private final FluidStack fluid;
        private final int time;
        private final boolean requiresClay;

        public Result(ResourceLocation pId, String pGroup, Item input, FluidStack fluid, int time, boolean requiresClay) {
            this.id = pId;
            this.group = pGroup;
            this.input = input;
            this.fluid = fluid;
            this.time = time;
            this.requiresClay = requiresClay;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.input).toString());
            pJson.add("input", jsonobject);

            if (!fluid.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.addProperty("fluid", Registry.FLUID.getKey(this.fluid.getFluid()).toString());
                jsonobject1.addProperty("amount", fluid.getAmount());
                pJson.add("fluid_input", jsonobject1);
            }
            pJson.addProperty("time",time);
            pJson.addProperty("clay",requiresClay);
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.DRUM_QUENCHING.get();
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