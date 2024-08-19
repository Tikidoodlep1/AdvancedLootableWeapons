package com.tiki.advancedlootableweapons.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class Utils {

    public static final Map<EquipmentSlot,String> ARMOR_SLOTS = new EnumMap<>(EquipmentSlot.class);
    static {
        ARMOR_SLOTS.put(EquipmentSlot.HEAD,"helmet");
        ARMOR_SLOTS.put(EquipmentSlot.CHEST,"chestplate");
        ARMOR_SLOTS.put(EquipmentSlot.LEGS,"leggings");
        ARMOR_SLOTS.put(EquipmentSlot.FEET,"boots");
    }


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

    public static String getMenuDescId(MenuType<?> menuType) {
        return Util.makeDescriptionId("container", Registry.MENU.getKey(menuType));
    }

    public static String getFluidDescId(Fluid fluid) {
        return Util.makeDescriptionId("fluid", Registry.FLUID.getKey(fluid));
    }

}
