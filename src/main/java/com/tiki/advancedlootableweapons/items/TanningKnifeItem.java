package com.tiki.advancedlootableweapons.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;

public class TanningKnifeItem extends TieredItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public TanningKnifeItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
        defaultModifiers = HashMultimap.create();

        defaultModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", pTier.getAttackDamageBonus() + 2, AttributeModifier.Operation.ADDITION));
        defaultModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",-2, AttributeModifier.Operation.ADDITION));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack copy = itemStack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        return copy;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(pSlot);
    }
}
