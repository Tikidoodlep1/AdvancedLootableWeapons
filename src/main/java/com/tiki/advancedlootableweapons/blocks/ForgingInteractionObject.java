package com.tiki.advancedlootableweapons.blocks;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class ForgingInteractionObject implements IInteractionObject {

	private World world;
	private BlockPos pos;
	
	public ForgingInteractionObject(World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
	}
	
	@Override
	public String getName() {
		return "Forge Weapon";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.forgeWeapon");
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return null;//new ContainerForgeWeapon(playerInventory, this.world, this.pos);
	}

	@Override
	public String getGuiID() {
		return ModInfo.ID + "forging";
	}

}
