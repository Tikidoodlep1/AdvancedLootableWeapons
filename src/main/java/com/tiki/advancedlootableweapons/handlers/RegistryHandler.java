package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.blocks.recipes.DrumRecipes;
import com.tiki.advancedlootableweapons.blocks.tileentities.DrumTESR;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.compat.oreDictionary.OreDictionaryCompat;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.EnchantmentInit;
import com.tiki.advancedlootableweapons.init.EntityInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.world.WorldGenCustomOres;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {		
		event.getRegistry().registerAll(ItemInit.items.toArray(new Item[0]));
		ItemInit.createRecipes(); //I like this face lol \'_'/
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.blocks.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		RenderHandler.registerCustomMeshesAndStates();
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(EnchantmentInit.enchantments.toArray(new Enchantment[0]));
		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ItemInit.items) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block: BlockInit.blocks) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDrum.class, new DrumTESR());
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
//		RenderHandler.registerCustomMeshesAndStates();
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
		ConfigHandler.registerConfig(event);
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Alw.instance, new GuiHandler());
		OreDictionaryCompat.registerOres();
		SoundHandler.registerSounds();
		ItemInit.checkConfigOptions();
		GlobalDropsHandler.registerDrops();
		DrumRecipes.initDrumRecipes();
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
	}
}
