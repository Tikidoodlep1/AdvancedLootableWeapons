package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2AirflowConsumer;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForgeAirflowConsumer;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityMill;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityTanningRack;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, new ResourceLocation(ModInfo.ID + ":alloy_furnace"));
		GameRegistry.registerTileEntity(TileEntityForge.class, new ResourceLocation(ModInfo.ID + ":forge"));
		GameRegistry.registerTileEntity(TileEntityForge2.class, new ResourceLocation(ModInfo.ID + ":forge2"));
		GameRegistry.registerTileEntity(TileEntityMill.class, new ResourceLocation(ModInfo.ID + ":mill"));
		GameRegistry.registerTileEntity(TileEntityTanningRack.class, new ResourceLocation(ModInfo.ID + ":tanning_rack"));
		GameRegistry.registerTileEntity(TileEntityDrum.class, new ResourceLocation(ModInfo.ID + ":drum"));
		
		if(Alw.isPyrotechLoaded) {
			GameRegistry.registerTileEntity(TileEntityForgeAirflowConsumer.class, new ResourceLocation(ModInfo.ID + ":forge_airflow_consumer"));
			GameRegistry.registerTileEntity(TileEntityForge2AirflowConsumer.class, new ResourceLocation(ModInfo.ID + ":forge2_airflow_consumer"));
		}
		
	}
}
