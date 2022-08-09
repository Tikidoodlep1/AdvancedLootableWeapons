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

    KOBOLD("kobold", new int[]{1, 3, 4, 2}, CommonConfigHandler.KOBOLD_ARMOR_DURABILITY.get(),22, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.KOBOLD_ARMOR_HARDNESS.get(), 0.0F, () -> {
        return Ingredient.of(ItemInit.INGOT_KOBOLD.get());
    }),
    COPPER("copper", new int[]{2, 3, 4, 2}, CommonConfigHandler.COPPER_ARMOR_DURABILITY.get(),10, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.COPPER_ARMOR_HARDNESS.get(), 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    SILVER("silver", new int[]{3, 5, 7, 3}, CommonConfigHandler.SILVER_ARMOR_DURABILITY.get(), 24, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.SILVER_ARMOR_HARDNESS.get(), 0.0F, () -> {
        return Ingredient.of(ItemInit.INGOT_SILVER.get());
    }),
    BRONZE("bronze", new int[]{5, 9, 12, 5}, CommonConfigHandler.BRONZE_ARMOR_DURABILITY.get(), 12, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.BRONZE_ARMOR_HARDNESS.get(), 0.0F, () -> {
        return Ingredient.of(ItemInit.INGOT_BRONZE.get());
    }),
    PLATINUM("platinum", new int[]{7, 13, 17, 8}, CommonConfigHandler.PLATINUM_DAMAGE.get(), 26, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.PLATINUM_ARMOR_HARDNESS.get(), 0.0F, () -> {
        return Ingredient.of(ItemInit.INGOT_PLATINUM.get());
    })
    ;

    private static final float HELMET_DURABILITY_OFFSET = 0.75F;
    private static final float CHEST_DURABILITY_OFFSET = 1F;
    private static final float LEGGINGS_DURABILITY_OFFSET = 0.9F;
    private static final float BOOTS_DURABILITY_OFFSET = 0.7F;

    private final String name;
    private final int[] slotProtections;
    private final float[] healthPerSlot;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ArmorAttributes(String name, int[] damageReductionAmount, float healthPerSlot, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.slotProtections = damageReductionAmount;
        this.healthPerSlot = new float[] {healthPerSlot * HELMET_DURABILITY_OFFSET, healthPerSlot * CHEST_DURABILITY_OFFSET, healthPerSlot * LEGGINGS_DURABILITY_OFFSET, healthPerSlot * BOOTS_DURABILITY_OFFSET};
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
        return (int) this.healthPerSlot[type.getIndex()];
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
