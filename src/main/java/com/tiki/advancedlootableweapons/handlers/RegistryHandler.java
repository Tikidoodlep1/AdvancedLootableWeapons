package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.blocks.recipes.DrumRecipes;
import com.tiki.advancedlootableweapons.compat.oreDictionary.OreDictionaryCompat;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.EnchantmentInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {		
		event.getRegistry().registerAll(ItemInit.items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.blocks.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		Alw.proxy.registerCustomMeshesAndStateStuff();
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(EnchantmentInit.enchantments.toArray(new Enchantment[0]));
		
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Alw.instance, new GuiHandler());
		OreDictionaryCompat.registerOres();
		SoundHandler.registerSounds();
		GlobalDropsHandler.registerDrops();
		DrumRecipes.initDrumRecipes();
		ItemInit.createRecipes();
		ItemInit.checkConfigOptions();
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
	}
}
