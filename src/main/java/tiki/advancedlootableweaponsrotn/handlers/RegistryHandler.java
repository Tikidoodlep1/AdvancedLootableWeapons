package tiki.advancedlootableweaponsrotn.handlers;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.BlockForge;
import tiki.advancedlootableweapons.blocks.BlockForge2;
import tiki.advancedlootableweapons.blocks.BlockForge2Fuel;
import tiki.advancedlootableweapons.blocks.BlockForgeFuel;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.items.ItemBlockForge2;
import tiki.advancedlootableweaponsrotn.AlwRotn;
import tiki.advancedlootableweaponsrotn.blocks.BlockForge2AirflowConsumer;
import tiki.advancedlootableweaponsrotn.blocks.BlockForge2FuelAirflowConsumer;
import tiki.advancedlootableweaponsrotn.blocks.BlockForgeAirflowConsumer;
import tiki.advancedlootableweaponsrotn.blocks.BlockForgeFuelAirflowConsumer;

@EventBusSubscriber
public class RegistryHandler {
	
//	private static List<Block> remove = new ArrayList<Block>();
	private static List<Block> add = new ArrayList<Block>();
	private static List<Block> copy = new ArrayList<Block>(BlockInit.blocks);
	private static boolean shouldRegister = false;
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		TileEntityHandler.registerTileEntities();
		
		if(add.size() == 0) {
			shouldRegister = true;
			
			for(Block b : copy) {
				if(b instanceof BlockForgeFuel) {
					BlockForgeFuel forge = (BlockForgeFuel)b;
//					remove.add(b);
					add.add(new BlockForgeFuelAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()), 
							false, 
							forge.getFuelList(), 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge2Fuel) {
					BlockForge2Fuel forge = (BlockForge2Fuel)b;
//					remove.add(b);
					add.add(new BlockForge2FuelAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.getFuelList(), 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge) {
					BlockForge forge = (BlockForge)b;
//					remove.add(b);
					add.add(new BlockForgeAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge2) {
					BlockForge2 forge = (BlockForge2)b;
//					remove.add(b);
					add.add(new BlockForge2AirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.acceptedMaterials));
				}
			}
		}
		
		for(Block b : add) {
			if(b instanceof BlockForge2AirflowConsumer) {
				if(shouldRegister) {
					((BlockForge2AirflowConsumer) b).register();
				}
			}else if(b instanceof BlockForge2FuelAirflowConsumer) {
				if(shouldRegister) {
					((BlockForge2FuelAirflowConsumer) b).register();
				}
			}else if(b instanceof BlockForgeAirflowConsumer) {
				if(shouldRegister) {
					((BlockForgeAirflowConsumer) b).register();
				}
			}else if(b instanceof BlockForgeFuelAirflowConsumer) {
				if(shouldRegister) {
					((BlockForgeFuelAirflowConsumer) b).register();
				}
			}
			event.getRegistry().register(b);
			AlwRotn.logger.info("Registering Block replacement for " + b.getRegistryName().toString());
		}
		
		if(shouldRegister) {
			shouldRegister = false;
		}
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		if(add.size() == 0) {
			shouldRegister = true;
			
			for(Block b : copy) {
				if(b instanceof BlockForgeFuel) {
					BlockForgeFuel forge = (BlockForgeFuel)b;
//					remove.add(b);
                    if(forge.getRegistryName() == null) {
                        AlwRotn.logger.error("BlockForgeFuel <" + forge.getUnlocalizedName() + "> has a null registry name!");
                    }
					add.add(new BlockForgeFuelAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()), 
							false, 
							forge.getFuelList(), 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge2Fuel) {
					BlockForge2Fuel forge = (BlockForge2Fuel)b;
//					remove.add(b);
                    if(forge.getRegistryName() == null) {
                        AlwRotn.logger.error("BlockForge2Fuel <" + forge.getUnlocalizedName() + "> has a null registry name!");
                    }
					add.add(new BlockForge2FuelAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.getFuelList(), 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge) {
					BlockForge forge = (BlockForge)b;
//					remove.add(b);
                    if(forge.getRegistryName() == null) {
                        AlwRotn.logger.error("BlockForge <" + forge.getUnlocalizedName() + "> has a null registry name!");
                    }
					add.add(new BlockForgeAirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.acceptedMaterials));
				}else if(b instanceof BlockForge2) {
					BlockForge2 forge = (BlockForge2)b;
//					remove.add(b);
                    if(forge.getRegistryName() == null) {
                        AlwRotn.logger.error("BlockForge2 <" + forge.getUnlocalizedName() + "> has a null registry name!");
                    }
					add.add(new BlockForge2AirflowConsumer(
							forge.getRegistryName().getResourcePath(), 
							forge.getMaterial(forge.getDefaultState()), 
							forge.getSoundType(), 
							forge.getHarvestTool(forge.getDefaultState()), 
							forge.getHarvestLevel(forge.getDefaultState()),
							false, 
							forge.acceptedMaterials));
				}
			}
		}
		
		for(Block b : add) {
			if(b instanceof BlockForge2AirflowConsumer) {
				if(shouldRegister) {
					((BlockForge2AirflowConsumer) b).register();
				}
                if(((BlockForge2AirflowConsumer) b).name == null) {
                    AlwRotn.logger.error("Advanced Forge Airflow Consumer has a null name!");
                }
				event.getRegistry().register(new ItemBlockForge2(b).setRegistryName(ModInfo.ID, ((BlockForge2AirflowConsumer) b).name));
			}else if(b instanceof BlockForge2FuelAirflowConsumer) {
				if(shouldRegister) {
					((BlockForge2FuelAirflowConsumer) b).register();
				}
                if(((BlockForge2FuelAirflowConsumer) b).name == null) {
                    AlwRotn.logger.error("Advanced Forge Fuel Airflow Consumer has a null name!");
                }
				event.getRegistry().register(new ItemBlockForge2(b).setRegistryName(ModInfo.ID, ((BlockForge2FuelAirflowConsumer) b).name));
			}else if(b instanceof BlockForgeAirflowConsumer) {
				if(shouldRegister) {
					((BlockForgeAirflowConsumer) b).register();
				}
                if(((BlockForgeAirflowConsumer) b).name == null) {
                    AlwRotn.logger.error("Forge Airflow Consumer has a null name!");
                }
				event.getRegistry().register(new ItemBlock(b).setRegistryName(ModInfo.ID, ((BlockForgeAirflowConsumer) b).name));
			}else if(b instanceof BlockForgeFuelAirflowConsumer) {
				if(shouldRegister) {
					((BlockForgeFuelAirflowConsumer) b).register();
				}
                if(((BlockForgeFuelAirflowConsumer) b).name == null) {
                    AlwRotn.logger.error("Forge Fuel Airflow Consumer has a null name!");
                }
				event.getRegistry().register(new ItemBlock(b).setRegistryName(ModInfo.ID, ((BlockForgeFuelAirflowConsumer) b).name));
			}
			AlwRotn.logger.info("Registering Item Block replacement for " + b.getRegistryName().toString());
		}
		
		if(shouldRegister) {
			shouldRegister = false;
		}
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Block block: add) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{	
	
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
		
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		
	}
}
