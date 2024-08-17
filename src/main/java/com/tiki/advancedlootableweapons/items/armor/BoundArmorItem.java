package com.tiki.advancedlootableweapons.items.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class BoundArmorItem extends ArmorItem {

    private final ArmorType armorType;

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, ArmorType armorType) {
        super(pMaterial, pSlot, pProperties);
        this.armorType = armorType;
    }

}
