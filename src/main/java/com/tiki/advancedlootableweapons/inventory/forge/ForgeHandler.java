package com.tiki.advancedlootableweapons.inventory.forge;

import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ForgeHandler extends ItemStackHandler {

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof HotToolHeadItem;
    }
}
