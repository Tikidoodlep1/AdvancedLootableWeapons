/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package com.tiki.advancedlootableweapons.init;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ConfigurableForgeTier implements Tier
{
    private final int level;
    private final ForgeConfigSpec.IntValue uses;
    private final float speed;
    private final ForgeConfigSpec.DoubleValue attackDamageBonus;
    private final int enchantmentValue;
    @Nonnull
    private final TagKey<Block> tag;
    @Nonnull
    private final Supplier<Ingredient> repairIngredient;

    public ConfigurableForgeTier(int level, ForgeConfigSpec.IntValue uses, float speed, ForgeConfigSpec.DoubleValue attackDamageBonus, int enchantmentValue,
                                 @Nonnull TagKey<Block> tag, @Nonnull Supplier<Ingredient> repairIngredient)
    {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.tag = tag;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses()
    {
        return this.uses.get();
    }

    @Override
    public float getSpeed()
    {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus()
    {
        return (float)(double)this.attackDamageBonus.get();
    }

    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    @Nonnull
    public TagKey<Block> getTag()
    {
        return this.tag;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }
}
