package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.init.ModRecipeSerializers;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

public class ThreeMatchingItemsRecipe extends ShapelessRecipe {

    public ThreeMatchingItemsRecipe(ResourceLocation id,ShapelessRecipe shapelessRecipe) {
        this(id,shapelessRecipe.getGroup(),shapelessRecipe.getResultItem(),shapelessRecipe.getIngredients());
    }

    public ThreeMatchingItemsRecipe(ResourceLocation pId, String pGroup, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        super(pId, pGroup, pResult, pIngredients);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CHAIN_LINK.get();
    }

    @Override
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        return super.matches(pInv, pLevel) && allItemsSameMaterial(pInv,pLevel);
    }

    private boolean allItemsSameMaterial(CraftingContainer pInv, Level pLevel) {

        ItemStack same = ItemStack.EMPTY;
        for (int i = 0; i < pInv.getContainerSize();i++) {
            ItemStack stack = pInv.getItem(i);
            if (!stack.isEmpty()) {
                if (same.isEmpty()) {
                    same = stack;
                } else {
                    if (!HeatableToolPartItem.isSameMaterial(same,stack)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static class CustomSerializer extends Serializer {

        @Override
        public ShapelessRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            ShapelessRecipe shapelessRecipe = super.fromJson(pRecipeId, pJson);
            return new ThreeMatchingItemsRecipe(pRecipeId,shapelessRecipe);
        }

        @Override
        public ShapelessRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ShapelessRecipe shapelessRecipe = super.fromNetwork(pRecipeId, pBuffer);
            return new ThreeMatchingItemsRecipe(pRecipeId,shapelessRecipe);
        }
    }
}
