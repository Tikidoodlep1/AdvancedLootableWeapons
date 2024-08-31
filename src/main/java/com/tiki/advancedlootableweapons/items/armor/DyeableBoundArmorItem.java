package com.tiki.advancedlootableweapons.items.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class DyeableBoundArmorItem extends DyeableArmorItem {
    protected final String armorTexture;

    protected String armorTextureOverlay;

    public DyeableBoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
        armorTexture = null;
    }

    private boolean usesInnerModel(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.LEGS;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if ("overlay".equals(type)) {
            return armorTextureOverlay;
        }
        return armorTexture;
    }
}
