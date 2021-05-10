package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.inventory.ContainerSharpeningStone;
import com.tiki.advancedlootableweapons.inventory.GuiSharpeningStone;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new ContainerSharpeningStone(player.inventory, world, player);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new GuiSharpeningStone(player.inventory, world);
		return null;
	}
}