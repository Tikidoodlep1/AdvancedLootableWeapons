package com.tiki.advancedlootableweapons.util;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

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
}
