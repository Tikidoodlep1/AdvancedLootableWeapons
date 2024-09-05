package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.blockentity.AlloyFurnaceBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class AlloyFurnaceHandler extends ItemStackHandler {

    public AlloyFurnaceHandler(int slots) {
        super(slots);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (slot == AlloyFurnaceBlockEntity.SLOT_RESULT) return false;//don't allow player or automation to insert into output
        if (slot == AlloyFurnaceBlockEntity.SLOT_FUEL) return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;//only allow fuels in fuel slot
        return super.isItemValid(slot, stack);
    }

}
