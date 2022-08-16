package com.tiki.advancedlootableweapons.items.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.client.IItemRenderProperties;

public class ArmorBase extends ArmorItem implements IItemRenderProperties {

    private final ArmorTypes armorTypes;

    public ArmorBase(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, ArmorTypes armorTypes) {
        super(pMaterial, pSlot, pProperties);
        this.armorTypes = armorTypes;
    }

}
