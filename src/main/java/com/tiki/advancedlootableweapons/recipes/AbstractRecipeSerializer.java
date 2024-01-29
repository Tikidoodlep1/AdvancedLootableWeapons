package com.tiki.advancedlootableweapons.recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;

public abstract class AbstractRecipeSerializer<T extends Recipe<?>> implements RecipeSerializer<T> {

    private ResourceLocation name;

    @Override
    public RecipeSerializer<?> setRegistryName(final ResourceLocation name) {
        this.name = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return name;
    }

    @Override
    public Class<RecipeSerializer<?>> getRegistryType() {
        return castClass(RecipeSerializer.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> castClass(Class<?> cls) {
        return (Class<T>)cls;
    }
}
