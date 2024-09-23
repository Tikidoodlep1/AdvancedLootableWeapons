package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractAnvilForgingRecipe implements Recipe<RecipeWrapper> {
    protected final Ingredient ingredient;
    protected final Ingredient ingredient2;
    protected final ItemStack result;
    private final boolean useSequence;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    protected final ResourceLocation id;
    protected final String group;

    public AbstractAnvilForgingRecipe(RecipeType<?> pType, RecipeSerializer<?> pSerializer, ResourceLocation pId, String pGroup, Ingredient pIngredient,Ingredient ingredient2, ItemStack pResult,boolean useSequence) {
        this.type = pType;
        this.serializer = pSerializer;
        this.id = pId;
        this.group = pGroup;
        this.ingredient = pIngredient;
        this.ingredient2 = ingredient2;
        this.result = pResult;
        this.useSequence = useSequence;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    @Override
    public String getGroup() {
        return this.group;
    }

    /**
     * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
     * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
     */
    @Override
    public ItemStack getResultItem() {
        return result;
    }

    public ItemStack getProcessedResult(ItemStack input) {
        return result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        nonnulllist.add(this.ingredient2);
        return nonnulllist;
    }

    public boolean isUseSequence() {
        return useSequence;
    }

    public Ingredient getFirst() {
        return ingredient;
    }

    public Ingredient getSecond() {
        return ingredient2;
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    public ItemStack assemble(RecipeWrapper pInv) {
        return this.result.copy();
    }

    public static class Serializer<T extends AbstractAnvilForgingRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {
        final DualItemMaker<T> factory;

        public Serializer(DualItemMaker<T> pFactory) {
            this.factory = pFactory;
        }

        @Override
        public @NotNull T fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            Ingredient ingredient;
            if (GsonHelper.isArrayNode(pJson, "ingredient")) {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(pJson, "ingredient"));
            } else {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "ingredient"));
            }

            Ingredient ingredient2;

            if (pJson.has("ingredient2")) {

                if (GsonHelper.isArrayNode(pJson, "ingredient2")) {
                    ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonArray(pJson, "ingredient2"));
                } else {
                    ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "ingredient2"));
                }
            } else {
                ingredient2 = Ingredient.EMPTY;
            }

                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                return this.factory.create(pRecipeId, s, ingredient, ingredient2, itemstack);
        }

        @Override
        public T fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            Ingredient ingredient2 = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            return this.factory.create(pRecipeId, s, ingredient,ingredient2, itemstack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, T pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pRecipe.ingredient2.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
        }

        public interface DualItemMaker<T extends AbstractAnvilForgingRecipe> {
            T create(ResourceLocation pId, String pGroup, Ingredient pIngredient,Ingredient ingredient2, ItemStack pResult);
        }
    }
}
