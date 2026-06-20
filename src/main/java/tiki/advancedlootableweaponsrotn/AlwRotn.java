package tiki.advancedlootableweaponsrotn;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import tiki.advancedlootableweaponsrotn.handlers.RegistryHandler;

//@Mod.EventBusSubscriber
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VER, dependencies = ModInfo.LOAD_AFTER)
public class AlwRotn {
	@Instance
	public static AlwRotn instance;
			
	public static Logger logger;
		
//	@SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
//	public static CommonProxy proxy; // No need for a proxy rn
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		RegistryHandler.preInitRegistries(event);
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries(event);
	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
}
