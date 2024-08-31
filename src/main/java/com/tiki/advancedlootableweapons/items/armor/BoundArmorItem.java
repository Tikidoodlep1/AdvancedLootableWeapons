package com.tiki.advancedlootableweapons.items.armor;

import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.ArmorBonus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BoundArmorItem extends ArmorItem {

    protected final String armorTexture;

    protected String armorTextureOverlay;

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
/*        switch (armorType) {
            case CHAIN -> {

                String mat_name = getMaterial().getName();

                ResourceLocation resourceLocation = new ResourceLocation(mat_name);

                String domain = resourceLocation.getNamespace();

                if (domain.equals("minecraft")) {
                    domain = AdvancedLootableWeapons.MODID;
                }
                armorTexture = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/%s_chain_layer_%d%s.png", domain, resourceLocation.getPath(), (usesInnerModel(slot) ? 2 : 1), "");
            }
            case STUDDED -> {

                String mat_name = getMaterial().getName();

                ResourceLocation resourceLocation = new ResourceLocation(mat_name);

                String domain = resourceLocation.getNamespace();

                if (domain.equals("minecraft")) {
                    domain = AdvancedLootableWeapons.MODID;
                }
                armorTexture = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/diamond_studded_%s_layer_%d.png", domain, resourceLocation.getPath(), (usesInnerModel(slot) ? 2 : 1));
                String type = "overlay";
                armorTextureOverlay = String.format(java.util.Locale.ROOT, "%s:textures/models/armor/diamond_studded_%s_layer_%d%s.png", domain, resourceLocation.getPath(), (usesInnerModel(slot) ? 2 : 1), String.format(java.util.Locale.ROOT, "_%s", type));
            }
            default -> armorTexture = null;
        }*/
        armorTexture = null;
    }

    private ArmorBonus armorBonus;

    public ArmorBonus getArmorBonus() {
        if (armorBonus == null) {
            armorBonus = ArmorBonus.getArmorBonus(material);
        }
        return armorBonus;
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

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public boolean usesVanillaMaterial() {
        ArmorMaterial armorMaterial = getMaterial();
        return armorMaterial == ArmorMaterials.LEATHER || armorMaterial == ArmorMaterials.GOLD || armorMaterial == ArmorMaterials.IRON;
    }

}
