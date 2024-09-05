package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.blockentity.TanningRackBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class TanningRackHandler extends ItemStackHandler {

    public TanningRackHandler(int slots) {
        super(slots);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return slot != TanningRackBlockEntity.SLOT_RESULT && super.isItemValid(slot, stack);//dont allow inserting items into output
    }

}
