package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum ModArmorMaterials implements ArmorMaterial {

    KOBOLD_STEEL("kobold_steel", new int[]{1, 3, 4, 2}, CommonConfigHandler.KOBOLD_STEEL_ARMOR_DURABILITY, 22, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.KOBOLD_STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.KOBOLD_STEEL_INGOT.get());
    }),
    COPPER("copper", new int[]{2, 3, 4, 2}, CommonConfigHandler.COPPER_ARMOR_DURABILITY, 10, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.COPPER_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    SILVER("silver", new int[]{3, 5, 7, 3}, CommonConfigHandler.SILVER_ARMOR_DURABILITY, 24, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.SILVER_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.SILVER_INGOT.get());
    }),
    BRONZE("bronze", new int[]{5, 9, 12, 5}, CommonConfigHandler.BRONZE_ARMOR_DURABILITY, 12, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.BRONZE_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.BRONZE_INGOT.get());
    }),
    PLATINUM("platinum", new int[]{7, 13, 17, 8}, CommonConfigHandler.PLATINUM_ARMOR_DURABILITY, 26, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.PLATINUM_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.PLATINUM_INGOT.get());
    }),
    STEEL("steel", new int[]{3, 5, 6, 3}, CommonConfigHandler.STEEL_ARMOR_DURABILITY, 18, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.STEEL_INGOT.get());
    }),
    SHADOW_PLATINUM("shadow_platinum", new int[]{7, 13, 16, 7}, CommonConfigHandler.SHADOW_PLATINUM_ARMOR_DURABILITY, 21, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.SHADOW_PLATINUM_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.SHADOW_PLATINUM_INGOT.get());
    }),
    FROST_STEEL("frost_steel", new int[]{4, 7, 9, 4}, CommonConfigHandler.FROST_STEEL_ARMOR_DURABILITY, 30, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.FROST_STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.FROST_STEEL_INGOT.get());
    }),
    REFINED_OBSIDIAN("refined_obsidian", new int[]{3, 5, 6, 3}, CommonConfigHandler.OBSIDIAN_ARMOR_DURABILITY, 18, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.REFINED_OBSIDIAN_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.REFINED_OBSIDIAN_INGOT.get());
    }),
    CRYSTALLITE("crystallite", new int[]{5, 10, 13, 6}, CommonConfigHandler.CRYSTALLITE_ARMOR_DURABILITY, 20, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.CRYSTALLITE_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.CRYSTALLITE_INGOT.get());
    }),
    DUSKSTEEL("dusksteel", new int[] {5, 8, 11, 5}, CommonConfigHandler.DUSKSTEEL_ARMOR_DURABILITY, 14, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.DUSKSTEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.DUSKSTEEL_INGOT.get());
    }),

    DIAMOND_STUDDED_LEATHER("diamond_studded_leather",  new int[]{1, 2, 3, 1}, CommonConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY, 14, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_HARDNESS, 0.0F, () -> Ingredient.of(Items.LEATHER)),

    DIAMOND_STUDDED_STEEL("diamond_studded_steel", new int[] {5, 8, 11, 5}, CommonConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY, 14, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_HARDNESS, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT.get())),


    IRON_CHAIN("iron_chain",new int[]{2, 5, 6, 2},CommonConfigHandler.IRON_ARMOR_DURABILITY,9, SoundEvents.ARMOR_EQUIP_IRON,CommonConfigHandler.IRON_ARMOR_HARDNESS,0, ArmorMaterials.IRON::getRepairIngredient),
    GOLD_CHAIN("gold_chain",new int[]{1, 3, 5, 2},CommonConfigHandler.GOLD_ARMOR_DURABILITY,25, SoundEvents.ARMOR_EQUIP_GOLD,CommonConfigHandler.GOLD_ARMOR_HARDNESS,0, ArmorMaterials.GOLD::getRepairIngredient),

    KOBOLD_STEEL_CHAIN("kobold_steel_chain", new int[]{1, 3, 4, 2}, CommonConfigHandler.KOBOLD_STEEL_ARMOR_DURABILITY, 22, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.KOBOLD_STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.KOBOLD_STEEL_INGOT.get());
    }),
    COPPER_CHAIN("copper_chain", new int[]{2, 3, 4, 2}, CommonConfigHandler.COPPER_ARMOR_DURABILITY, 10, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.COPPER_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    SILVER_CHAIN("silver_chain", new int[]{3, 5, 7, 3}, CommonConfigHandler.SILVER_ARMOR_DURABILITY, 24, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.SILVER_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.SILVER_INGOT.get());
    }),
    BRONZE_CHAIN("bronze_chain", new int[]{5, 9, 12, 5}, CommonConfigHandler.BRONZE_ARMOR_DURABILITY, 12, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.BRONZE_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.BRONZE_INGOT.get());
    }),
    PLATINUM_CHAIN("platinum_chain", new int[]{7, 13, 17, 8}, CommonConfigHandler.PLATINUM_ARMOR_DURABILITY, 26, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.PLATINUM_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.PLATINUM_INGOT.get());
    }),
    STEEL_CHAIN("steel_chain", new int[]{3, 5, 6, 3}, CommonConfigHandler.STEEL_ARMOR_DURABILITY, 18, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.STEEL_INGOT.get());
    }),
    SHADOW_PLATINUM_CHAIN("shadow_platinum_chain", new int[]{7, 13, 16, 7}, CommonConfigHandler.SHADOW_PLATINUM_ARMOR_DURABILITY, 21, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.SHADOW_PLATINUM_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.SHADOW_PLATINUM_INGOT.get());
    }),
    FROST_STEEL_CHAIN("frost_steel_chain", new int[]{4, 7, 9, 4}, CommonConfigHandler.FROST_STEEL_ARMOR_DURABILITY, 30, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.FROST_STEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.FROST_STEEL_INGOT.get());
    }),
    REFINED_OBSIDIAN_CHAIN("refined_obsidian_chain", new int[]{3, 5, 6, 3}, CommonConfigHandler.OBSIDIAN_ARMOR_DURABILITY, 18, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.REFINED_OBSIDIAN_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.REFINED_OBSIDIAN_INGOT.get());
    }),
    CRYSTALLITE_CHAIN("crystallite_chain", new int[]{5, 10, 13, 6}, CommonConfigHandler.CRYSTALLITE_ARMOR_DURABILITY, 20, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.CRYSTALLITE_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.CRYSTALLITE_INGOT.get());
    }),
    DUSKSTEEL_CHAIN("dusksteel_chain", new int[] {5, 8, 11, 5}, CommonConfigHandler.DUSKSTEEL_ARMOR_DURABILITY, 14, SoundEvents.ARMOR_EQUIP_IRON, CommonConfigHandler.DUSKSTEEL_ARMOR_HARDNESS, 0.0F, () -> {
        return Ingredient.of(ItemInit.DUSKSTEEL_INGOT.get());
    });

    private static final float HELMET_DURABILITY_OFFSET = 0.75F;
    private static final float CHEST_DURABILITY_OFFSET = 1F;
    private static final float LEGGINGS_DURABILITY_OFFSET = 0.9F;
    private static final float BOOTS_DURABILITY_OFFSET = 0.7F;

    private static final float[] slotDurMultipliers = new float[]{HELMET_DURABILITY_OFFSET,CHEST_DURABILITY_OFFSET,LEGGINGS_DURABILITY_OFFSET,BOOTS_DURABILITY_OFFSET};

    private final String name;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final ForgeConfigSpec.DoubleValue toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final ForgeConfigSpec.IntValue healthPerSlot;

    ModArmorMaterials(String name, int[] damageReductionAmount, ForgeConfigSpec.IntValue healthPerSlot, int enchantability, SoundEvent sound, ForgeConfigSpec.DoubleValue toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.slotProtections = damageReductionAmount;
        this.healthPerSlot = healthPerSlot;
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
        return AdvancedLootableWeapons.MODID + ":" + this.name;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot type) {
        int base = healthPerSlot.get();
        return (int) (base * slotDurMultipliers[type.getIndex()]);
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
        return (float)(double)this.toughness.get();
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
