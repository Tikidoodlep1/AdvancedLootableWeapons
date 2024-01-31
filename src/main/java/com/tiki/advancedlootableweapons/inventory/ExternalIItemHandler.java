package com.tiki.advancedlootableweapons.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExternalIItemHandler implements IItemHandler {

    private final ItemStackHandler handler;
    private final List<Integer> inputOnly;
    private final List<Integer> outputOnly;

    public ExternalIItemHandler(ItemStackHandler handler, List<Integer> inputOnly, List<Integer> outputOnly) {
        this.handler = handler;
        this.inputOnly = inputOnly;
        this.outputOnly = outputOnly;
    }

    @Override
    public int getSlots() {
        return handler.getSlots();
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return outputOnly.contains(slot) ? stack : handler.insertItem(slot, stack, simulate);
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return inputOnly.contains(slot) ? ItemStack.EMPTY : handler.extractItem(slot,amount,simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return handler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return handler.isItemValid(slot,stack);
    }
}
