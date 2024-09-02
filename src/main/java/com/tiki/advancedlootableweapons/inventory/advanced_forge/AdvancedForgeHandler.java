package com.tiki.advancedlootableweapons.inventory.advanced_forge;

import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class AdvancedForgeHandler extends ItemStackHandler {

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof HeatableToolPartItem;
    }
}
