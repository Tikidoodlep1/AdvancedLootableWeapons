package com.tiki.advancedlootableweapons;

import com.tiki.advancedlootableweapons.data.ModDatagen;
import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.GuiInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.anvil_forging.AnvilForgingScreen;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;

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
@SuppressWarnings("deprecation")
@Mod(AdvancedLootableWeapons.ID)
public class AdvancedLootableWeapons
{
    public static final String ID = "advancedlootableweapons";
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();

    public AdvancedLootableWeapons()
    {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        
        BlockEntityInit.register(eventBus);
        GuiInit.register(eventBus);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigHandler.SPEC);
        
        RecipeInit.register(eventBus);
        
        ForgeMod.enableMilkFluid();
        
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(ModDatagen::start);
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
    	MenuScreens.register(GuiInit.ALLOY_FURNACE_CONTAINER.get(), AlloyFurnaceScreen::new);
    	MenuScreens.register(GuiInit.FORGE_CONTAINER.get(), ForgeScreen::new);
    	MenuScreens.register(GuiInit.ANVIL_FORGING_CONTAINER.get(), AnvilForgingScreen::new);
    	MenuScreens.register(GuiInit.JAW_CRUSHER_CONTAINER.get(), JawCrusherScreen::new);
    	event.enqueueWork(() -> {
            Map<Item, ItemPropertyFunction> map = ItemInit.toolHeadMap;
            for(Item i : map.keySet()) {
                ItemProperties.register(i, new ResourceLocation(ID, "heat"), map.get(i));
            }
        });
    }
}
