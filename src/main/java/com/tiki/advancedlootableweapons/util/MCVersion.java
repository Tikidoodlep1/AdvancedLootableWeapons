package com.tiki.advancedlootableweapons.util;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * this class is to help ease porting to future versions
 */
public class MCVersion {

    public static MutableComponent translation(String key,Object... objects) {
        return new TranslatableComponent(key,objects);
    }

    public static MutableComponent literal(String key) {
        return new TextComponent(key);
    }

    public static MutableComponent empty() {
        return (MutableComponent) TextComponent.EMPTY;
    }

    public static Fluid lookup(ResourceLocation location) {
        return Registry.FLUID.get(location);
    }
}
