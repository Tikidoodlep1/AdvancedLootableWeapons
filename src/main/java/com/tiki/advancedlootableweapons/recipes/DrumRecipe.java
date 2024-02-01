package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.blocks.block_entity.DrumBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class DrumRecipe implements Recipe<SingleFluidRecipeWrapper> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient additive;
    private final FluidStack fluidInput;
    private final ItemStack result;
    private final int time;

    public DrumRecipe(ResourceLocation id, Ingredient input, Ingredient additive, FluidStack fluidInput, ItemStack result, int time) {
        this.id = id;
        this.input = input;
        this.additive = additive;
        this.fluidInput = fluidInput;
        this.result = result;
        this.time = time;
    }

    @Override
    public boolean matches(SingleFluidRecipeWrapper pContainer, Level pLevel) {
        if (!input.test(pContainer.getItem(DrumBlockEntity.INPUT_SLOT))) return false;
        if (!additive.test(pContainer.getItem(DrumBlockEntity.ADDITIVE_SLOT))) return false;
        if (!fluidInput.isEmpty() && !pContainer.testFluid(fluidInput)) return false;

        return true;
    }

    public Ingredient getInput() {
        return input;
    }

    public Ingredient getAdditive() {
        return additive;
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockInit.CLAY_DRUM.get());
    }

    public int getTime() {
        return time;
    }

    @Override
    public ItemStack assemble(SingleFluidRecipeWrapper pContainer) {
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.DRUM_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.DRUM;
    }


    public static class Serializer extends AbstractRecipeSerializer<DrumRecipe> {

        @Override
        public DrumRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            Ingredient input  = Ingredient.fromJson(pSerializedRecipe.get("input"));
            Ingredient additive  = Ingredient.fromJson(pSerializedRecipe.get("additive"));
            FluidStack fluidInput = Utils.getFluidStackFromJson(pSerializedRecipe.get("fluid_input").getAsJsonObject());
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));
            int time = GsonHelper.getAsInt(pSerializedRecipe,"time");

            return new DrumRecipe(pRecipeId, input, additive, fluidInput, result, time);
        }

        @Nullable
        @Override
        public DrumRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DrumRecipe pRecipe) {

        }
    }
}
