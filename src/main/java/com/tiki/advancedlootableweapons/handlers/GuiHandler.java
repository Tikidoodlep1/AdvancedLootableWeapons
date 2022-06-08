package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityJawCrusher;
import com.tiki.advancedlootableweapons.inventory.AlloyFurnace.ContainerAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.AlloyFurnace.GuiAlloyFurnace;
import com.tiki.advancedlootableweapons.inventory.Forge.ContainerForge;
import com.tiki.advancedlootableweapons.inventory.Forge.GuiForge;
import com.tiki.advancedlootableweapons.inventory.Forge2.ContainerForge2;
import com.tiki.advancedlootableweapons.inventory.Forge2.GuiForge2;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.GuiForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.JawCrusher.ContainerJawCrusher;
import com.tiki.advancedlootableweapons.inventory.JawCrusher.GuiJawCrusher;
import com.tiki.advancedlootableweapons.inventory.SharpeningStone.ContainerSharpeningStone;
import com.tiki.advancedlootableweapons.inventory.SharpeningStone.GuiSharpeningStone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	ContainerForgeWeapon containerForgeWeapons;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		containerForgeWeapons = new ContainerForgeWeapon(player.inventory, world, player);
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new ContainerSharpeningStone(player.inventory, world, player);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new ContainerForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return containerForgeWeapons;
		if(ID == ModInfo.GUI_FORGE_2) return new ContainerForge2(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_CRUSHER) return new ContainerJawCrusher(player.inventory, (TileEntityJawCrusher)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new GuiSharpeningStone(player.inventory, world);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new GuiAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new GuiForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return new GuiForgeWeapon(player.inventory, containerForgeWeapons);
		if(ID == ModInfo.GUI_FORGE_2) return new GuiForge2(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_CRUSHER) return new GuiJawCrusher(player.inventory, (TileEntityJawCrusher)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
}