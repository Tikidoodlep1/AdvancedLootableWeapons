package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum ArmorAttributes implements ArmorMaterial {

    KOBOLD("kobold", new int[]{1, 3, 4, 2}, 22, SoundEvents.ARMOR_EQUIP_LEATHER, 1.25F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    });

    private static final float HELMET_DURABILITY_OFFSET = 0.75F;
    private static final float CHEST_DURABILITY_OFFSET = 1F;
    private static final float LEGGINGS_DURABILITY_OFFSET = 0.9F;
    private static final float BOOTS_DURABILITY_OFFSET = 0.7F;

    private static final float[] HEALTH_PER_SLOT = new float[]{
            CommonConfigHandler.KOBOLD_ARMOR_DURABILITY.get() * HELMET_DURABILITY_OFFSET,
            CommonConfigHandler.KOBOLD_ARMOR_DURABILITY.get() * CHEST_DURABILITY_OFFSET,
            CommonConfigHandler.KOBOLD_ARMOR_DURABILITY.get() * LEGGINGS_DURABILITY_OFFSET,
            CommonConfigHandler.KOBOLD_ARMOR_DURABILITY.get() * BOOTS_DURABILITY_OFFSET};

    private final String name;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ArmorAttributes(String name, int[] damageReductionAmount, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.slotProtections = damageReductionAmount;
        this.enchantmentValue = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    @NotNull
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    @NotNull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    @NotNull
    public String getName() {
        return ModInfo.ID + ":" + this.name;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot type) {
        return (int) HEALTH_PER_SLOT[type.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot type) {
        return this.slotProtections[type.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
