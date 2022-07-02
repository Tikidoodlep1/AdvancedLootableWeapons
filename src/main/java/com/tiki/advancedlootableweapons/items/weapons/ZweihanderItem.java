package com.tiki.advancedlootableweapons.items.weapons;

import com.tiki.advancedlootableweapons.items.weapons.setup.ALWSwordItem;
import com.tiki.advancedlootableweapons.items.weapons.setup.AttributeUUID;
import net.minecraft.world.item.Tier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.common.Mod;

@Mod("advancedlootableweapons")
public class ZweihanderItem extends ALWSwordItem {

    public ZweihanderItem(Tier tier, int damage, float speed, Properties props) {
        super(tier, damage, speed, props);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(AttributeUUID.ATK_RNG_UUID, "Attack Reach modifier", 2.76D, AttributeModifier.Operation.ADDITION));
        builder.putAll(super.getAttributeModifiers(slot, stack));

        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot, stack);
    }
}