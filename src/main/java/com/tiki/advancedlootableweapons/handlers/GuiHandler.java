package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.inventory.ContainerAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.ContainerForge;
import com.tiki.advancedlootableweapons.inventory.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.ContainerSharpeningStone;
import com.tiki.advancedlootableweapons.inventory.GuiAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.GuiForge;
import com.tiki.advancedlootableweapons.inventory.GuiForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.GuiSharpeningStone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new ContainerSharpeningStone(player.inventory, world, player);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new ContainerForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return new ContainerForgeWeapon(player.inventory, world, player);
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new GuiSharpeningStone(player.inventory, world);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new GuiAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new GuiForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return new GuiForgeWeapon(player.inventory, world);
		return null;
	}
}