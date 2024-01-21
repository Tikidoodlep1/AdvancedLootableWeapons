package tiki.advancedlootableweapons.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityMill;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityTanningRack;
import tiki.advancedlootableweapons.inventory.AlloyFurnace.ContainerAlloyFurnace;
import tiki.advancedlootableweapons.inventory.AlloyFurnace.GuiAlloyFurnace;
import tiki.advancedlootableweapons.inventory.Forge.ContainerForge;
import tiki.advancedlootableweapons.inventory.Forge.ContainerForgeFuel;
import tiki.advancedlootableweapons.inventory.Forge.GuiForge;
import tiki.advancedlootableweapons.inventory.Forge.GuiForgeFuel;
import tiki.advancedlootableweapons.inventory.Forge2.ContainerForge2;
import tiki.advancedlootableweapons.inventory.Forge2.ContainerForge2Fuel;
import tiki.advancedlootableweapons.inventory.Forge2.GuiForge2;
import tiki.advancedlootableweapons.inventory.Forge2.GuiForge2Fuel;
import tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import tiki.advancedlootableweapons.inventory.ForgeWeapon.GuiForgeWeapon;
import tiki.advancedlootableweapons.inventory.Mill.ContainerMill;
import tiki.advancedlootableweapons.inventory.Mill.GuiMill;
import tiki.advancedlootableweapons.inventory.SharpeningStone.ContainerSharpeningStone;
import tiki.advancedlootableweapons.inventory.SharpeningStone.GuiSharpeningStone;
import tiki.advancedlootableweapons.inventory.TanningRack.ContainerTanningRack;
import tiki.advancedlootableweapons.inventory.TanningRack.GuiTanningRack;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new ContainerSharpeningStone(player.inventory, world, player);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new ContainerForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return new ContainerForgeWeapon(player.inventory, world, new BlockPos(x,y,z));
		if(ID == ModInfo.GUI_FORGE_2) return new ContainerForge2(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_MILL) return new ContainerMill(player.inventory, (TileEntityMill)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_TANNING_RACK) return new ContainerTanningRack(player.inventory, (TileEntityTanningRack)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_FUEL) return new ContainerForgeFuel(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_2_FUEL) return new ContainerForge2Fuel(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_SHARPENING_STONE) return new GuiSharpeningStone(player.inventory, world);
		if(ID == ModInfo.GUI_ALLOY_FURNACE) return new GuiAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE) return new GuiForge(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_WEAPON) return new GuiForgeWeapon(player.inventory, new ContainerForgeWeapon(player.inventory, world), world.getBlockState(new BlockPos(x,y,z)).getBlock().getRegistryName());
		if(ID == ModInfo.GUI_FORGE_2) return new GuiForge2(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_MILL) return new GuiMill(player.inventory, (TileEntityMill)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_TANNING_RACK) return new GuiTanningRack(player.inventory, (TileEntityTanningRack)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_FUEL) return new GuiForgeFuel(player.inventory, (TileEntityForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == ModInfo.GUI_FORGE_2_FUEL) return new GuiForge2Fuel(player.inventory, (TileEntityForge2)world.getTileEntity(new BlockPos(x,y,z)));
		
		return null;
	}
}