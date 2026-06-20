package tiki.advancedlootableweaponsrotn.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tiki.advancedlootableweaponsrotn.ModInfo;
import tiki.advancedlootableweaponsrotn.blocks.tileentities.TileEntityForge2AirflowConsumer;
import tiki.advancedlootableweaponsrotn.blocks.tileentities.TileEntityForgeAirflowConsumer;

public class TileEntityHandler 
{
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityForgeAirflowConsumer.class, new ResourceLocation(ModInfo.ID + ":forge_airflow_consumer"));
		GameRegistry.registerTileEntity(TileEntityForge2AirflowConsumer.class, new ResourceLocation(ModInfo.ID + ":forge2_airflow_consumer"));
	}
}
