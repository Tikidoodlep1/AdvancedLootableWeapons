package com.tiki.advancedlootableweapons.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class Utils {

    public static FluidStack getFluidStack(JsonObject json) {
        String itemName = GsonHelper.getAsString(json, "fluid");
        Fluid fluid = getFluid(itemName);
        return new FluidStack(fluid, GsonHelper.getAsInt(json, "amount", 1000));
    }

    public static Fluid getFluid(String itemName) {
        ResourceLocation itemKey = new ResourceLocation(itemName);
        if (!ForgeRegistries.FLUIDS.containsKey(itemKey))
            throw new JsonSyntaxException("Unknown item '" + itemName + "'");
        Fluid item = MCVersion.lookup(itemKey);
        return Objects.requireNonNull(item);
    }


    public static FluidStack getFluidStackFromJson(JsonObject jsonObject) {
        return getFluidStack(jsonObject);
    }
}