package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.data.recipes.AlloyFurnaceRecipeBuilder;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class TanningRackRecipe implements Recipe<RecipeWrapper> {


    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient input;
    private final int cookTime;

    public TanningRackRecipe(final ResourceLocation id, final ItemStack output, final Ingredient input,
                             int cookTime) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(RecipeWrapper recipeWrapper, Level level) {
        return input.test(recipeWrapper.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeWrapper recipeWrapper) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.TANNING_RACK.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.TANNING_RACK;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockInit.TANNING_RACK.get());
    }

    public int getCookTime() {
        return cookTime;
    }

    public static class Serializer extends AbstractRecipeSerializer<TanningRackRecipe> {


        @Override
        public TanningRackRecipe fromJson(final ResourceLocation pRecipeId, final JsonObject json) {

            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            int cookTime = GsonHelper.getAsInt(json, AlloyFurnaceRecipeBuilder.COOKTIME);
            return new TanningRackRecipe(pRecipeId, output,ingredient, cookTime);
        }

        @Override
        public TanningRackRecipe fromNetwork(final ResourceLocation pRecipeId, final FriendlyByteBuf pBuffer) {
            Ingredient ing = Ingredient.fromNetwork(pBuffer);

            ItemStack result = pBuffer.readItem();
            int cooktime = pBuffer.readInt();

            return new TanningRackRecipe(pRecipeId,result,ing, cooktime);
        }

        @Override
        public void toNetwork(final FriendlyByteBuf pBuffer, final TanningRackRecipe pRecipe) {
            pRecipe.input.toNetwork(pBuffer);

            pBuffer.writeItemStack(pRecipe.output,false);
            pBuffer.writeInt(pRecipe.cookTime);
        }
    }
}
