package tiki.advancedlootableweapons.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityAlloyFurnace;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityMill;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityTanningRack;

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
		
//		Alw.logger.info("Is Pyrotech loaded when registering TE's: " + Alw.isPyrotechLoaded);
//		if(Alw.isPyrotechLoaded) {
//			GameRegistry.registerTileEntity(TileEntityForgeAirflowConsumer.class, new ResourceLocation(ModInfo.ID, ":forge_airflow_consumer"));
//			GameRegistry.registerTileEntity(TileEntityForge2AirflowConsumer.class, new ResourceLocation(ModInfo.ID, ":forge2_airflow_consumer"));
//		}
	}
}
