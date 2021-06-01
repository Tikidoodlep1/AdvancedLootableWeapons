package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, new ResourceLocation(ModInfo.ID + ":alloy_furnace"));
		GameRegistry.registerTileEntity(TileEntityForge.class, new ResourceLocation(ModInfo.ID + ":forge"));
	}
}
