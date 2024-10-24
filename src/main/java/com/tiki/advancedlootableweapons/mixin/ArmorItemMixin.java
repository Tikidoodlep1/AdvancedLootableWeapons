package com.tiki.advancedlootableweapons.mixin;

import com.tiki.advancedlootableweapons.items.armor.ArmorBindingItem;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorItem.class)
public class ArmorItemMixin extends Item {
    public ArmorItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int base = super.getMaxDamage(stack);

        Item item = BoundArmorItem.getArmorBinding(stack);

        if (item instanceof ArmorBindingItem armorBindingItem) {
            return base + armorBindingItem.getExtraDur();
        }

        return base;
    }
}
