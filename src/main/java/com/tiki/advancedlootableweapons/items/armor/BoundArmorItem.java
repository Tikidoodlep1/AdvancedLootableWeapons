package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

public class BoundArmorItem extends ArmorItem {

    public static final String BINDING = AdvancedLootableWeapons.id("binding").toString();

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    public static final String BINDING_INFO = "advancedlootableweapons.armor.armor_binding.info";
    public static final String TIER_INFO = "advancedlootableweapons.armor.tier.info";


    @Override
    public int getMaxDamage(ItemStack stack) {
        int base = super.getMaxDamage(stack);

        Item item = getArmorBinding(stack);

        if (item instanceof ArmorBindingItem armorBindingItem) {
            return base + armorBindingItem.getExtraDur();
        }

        return base;
    }

    public static Item getArmorBinding(ItemStack stack){
        if (!stack.hasTag() || !stack.getTag().contains(BINDING)) return Items.AIR;
        return Registry.ITEM.get(new ResourceLocation(stack.getTag().getString(BINDING)));
    }

    public static void setArmorBinding(ItemStack stack, Item binding) {
        stack.getOrCreateTag().putString(BINDING, Registry.ITEM.getKey(binding).toString());
    }

    public boolean usesVanillaMaterial() {
        ArmorMaterial armorMaterial = getMaterial();
        return armorMaterial == ArmorMaterials.LEATHER || armorMaterial == ArmorMaterials.GOLD || armorMaterial == ArmorMaterials.IRON;
    }
}