package com.tiki.advancedlootableweapons;

import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.GuiInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModInfo.ID)
public class Alw
{
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();

    public Alw()
    {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
                
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        
        BlockEntityInit.register(eventBus);
        GuiInit.register(eventBus);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.SPEC, "ALW Config-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigHandler.SPEC, "ALW Config-common.toml");
        
        ForgeMod.enableMilkFluid();
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }
    
    
    private void clientSetup(final FMLClientSetupEvent event) {
    	MenuScreens.register(GuiInit.ALLOY_FURNACE_CONTAINER.get(), AlloyFurnaceScreen::new);
    	event.enqueueWork(new Runnable() {

			@Override
			public void run() {
				@SuppressWarnings("deprecation")
				Map<Item, ItemPropertyFunction> map = ItemInit.toolHeadMap;
				for(Item i : map.keySet()) {
					ItemProperties.register(i, new ResourceLocation(ModInfo.ID, "heat"), map.get(i));
				}
				
				
			}
			
    	});
    }
}
