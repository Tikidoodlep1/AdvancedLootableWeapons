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

public class DrumQuenchingRecipe implements Recipe<SingleFluidRecipeWrapper> {

    private final ResourceLocation id;
    private final ItemStack input;
    private final FluidStack fluidInput;
    private final int time;
    private final boolean needsClay;

    public DrumQuenchingRecipe(ResourceLocation id, ItemStack input, FluidStack fluidInput, int time, boolean needsClay) {
        this.id = id;
        this.input = input;
        this.fluidInput = fluidInput;
        this.time = time;
        this.needsClay = needsClay;
    }

    @Override
    public boolean matches(SingleFluidRecipeWrapper pContainer, Level pLevel) {
        if (!Ingredient.of(input).test(pContainer.getItem(DrumBlockEntity.INPUT_SLOT))) return false;
        if (!fluidInput.isEmpty() && !pContainer.testFluid(fluidInput)) return false;
        if (needsClay && (!input.hasTag() || !input.getTag().getBoolean("clay"))) return false;

        return true;
    }

    public ItemStack getInput() {
        return input;
    }

    public boolean needsClay() {
        return needsClay;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockInit.CLAY_DRUM.get());
    }

    public int getTime() {
        return time;
    }

    @Override//important, no side effects allowed
    public ItemStack assemble(SingleFluidRecipeWrapper pContainer) {
        ItemStack itemInSlot = pContainer.getItem(DrumBlockEntity.INPUT_SLOT);
        return applyQuench(itemInSlot);
    }

    public static ItemStack applyQuench(ItemStack stack) {
        ItemStack copy = stack.copy();

        //the more "damage" an item has, the colder it is
        copy.setDamageValue(stack.getDamageValue() + 3500);

        copy.getOrCreateTag().putBoolean("quenched", true);
        copy.getTag().remove("clay");

        return copy;

    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return input.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.DRUM_QUENCHING_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.DRUM_QUENCHING;
    }


    public static class Serializer extends AbstractRecipeSerializer<DrumQuenchingRecipe> {

        @Override
        public DrumQuenchingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack input  = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "input"));
            FluidStack fluidInput = Utils.getFluidStackFromJson(pSerializedRecipe.get("fluid_input").getAsJsonObject());
            int time = GsonHelper.getAsInt(pSerializedRecipe,"time");
            boolean clay = GsonHelper.getAsBoolean(pSerializedRecipe,"clay");
            return new DrumQuenchingRecipe(pRecipeId, input, fluidInput,  time, clay);
        }

        @Nullable
        @Override
        public DrumQuenchingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DrumQuenchingRecipe pRecipe) {

        }
    }
}
