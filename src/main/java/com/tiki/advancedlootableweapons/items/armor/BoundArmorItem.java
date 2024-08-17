package com.tiki.advancedlootableweapons.items.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

public class BoundArmorItem extends ArmorItem {

    private final ArmorType armorType;

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, ArmorType armorType) {
        super(pMaterial, pSlot, pProperties);
        this.armorType = armorType;
    }

    public boolean usesVanillaMaterial() {
        ArmorMaterial armorMaterial = getMaterial();
        return armorMaterial == ArmorMaterials.LEATHER || armorMaterial == ArmorMaterials.GOLD || armorMaterial == ArmorMaterials.IRON;
    }

}
