package com.tiki.advancedlootableweapons.inventory.jaw_crusher;

import com.tiki.advancedlootableweapons.blockentity.JawCrusherBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class JawCrusherHandler extends ItemStackHandler {

    public JawCrusherHandler(int slots) {
        super(slots);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return slot != JawCrusherBlockEntity.OUTPUT_SLOT && super.isItemValid(slot, stack);//dont allow inserting items into output
    }
}
