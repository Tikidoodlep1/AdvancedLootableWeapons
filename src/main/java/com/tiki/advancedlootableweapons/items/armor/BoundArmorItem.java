package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoundArmorItem extends ArmorItem {

    public static final String BINDING = AdvancedLootableWeapons.id("binding").toString();

    public BoundArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        Item armorBinding = getArmorBinding(pStack);
        if (armorBinding == Items.AIR) {
            pTooltipComponents.add(MCVersion.literal("Binding: ").append("None"));
        } else {
            pTooltipComponents.add(MCVersion.literal("Binding: ").append(armorBinding.getDescription()));
        }
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int base = super.getMaxDamage(stack);

        Item item = getArmorBinding(stack);

        if (item instanceof ArmorBindingItem armorBindingItem) {
            return base + armorBindingItem.getExtraDur();
        }

        return base;
    }

    public Item getArmorBinding(ItemStack stack){
        if (!stack.hasTag() || stack.getTag().contains(BINDING)) return Items.AIR;
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