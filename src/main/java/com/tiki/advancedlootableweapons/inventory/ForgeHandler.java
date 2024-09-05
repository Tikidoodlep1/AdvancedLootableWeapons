package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ForgeHandler extends ItemStackHandler {

    public ForgeHandler() {

    }

    public ForgeHandler(int slots) {
        super(slots);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof HeatableToolPartItem;
    }
}
