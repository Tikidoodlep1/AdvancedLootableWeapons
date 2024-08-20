package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BoundArmorItem extends ArmorItem {

    private final ArmorType armorType;
    protected final String armorTexture;

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, ArmorType armorType) {
        super(pMaterial, pSlot, pProperties);
        this.armorType = armorType;
        if (armorType == ArmorType.CHAIN) {

            String texture = getMaterial().getName();

            ResourceLocation resourceLocation = new ResourceLocation(texture);

            String domain = resourceLocation.getNamespace();

            if (domain.equals("minecraft")) {
                domain = AdvancedLootableWeapons.MODID;
            }

            String type = null;


            armorTexture = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/%s_chain_layer_%d%s.png", domain, texture, (usesInnerModel(slot) ? 2 : 1), type == null ? "" : String.format(java.util.Locale.ROOT, "_%s", type));
        } else {
            armorTexture = null;
        }
    }

    private boolean usesInnerModel(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.LEGS;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture;
    }

    public boolean usesVanillaMaterial() {
        ArmorMaterial armorMaterial = getMaterial();
        return armorMaterial == ArmorMaterials.LEATHER || armorMaterial == ArmorMaterials.GOLD || armorMaterial == ArmorMaterials.IRON;
    }

}
