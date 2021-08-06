package com.tiki.advancedlootableweapons.inventory.AlloyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlloyFurnaceOutput extends Slot{
	
	private final EntityPlayer player;
	private int removeCount;

	public SlotAlloyFurnaceOutput(EntityPlayer player, IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
		this.player = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public ItemStack onTake(EntityPlayer player, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(player, stack);
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		if(this.getHasStack()) {
			this.removeCount += Math.min(amount, this.getStack().getCount());
		}
		return super.decrStackSize(amount);
	}
}
