package com.tiki.advancedlootableweapons;

import com.tiki.advancedlootableweapons.client.ALWClient;
import com.tiki.advancedlootableweapons.data.ModDatagen;
import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.*;

import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AdvancedLootableWeapons.MODID)
public class AdvancedLootableWeapons
{
    public static final String MODID = "advancedlootableweapons";
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();

    public AdvancedLootableWeapons()
    {
        ForgeMod.enableMilkFluid();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        FluidInit.register(eventBus);
        
        BlockEntityInit.register(eventBus);
        MenuInit.register(eventBus);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigHandler.SPEC);
        
        RecipeInit.register(eventBus);
        
        ForgeMod.enableMilkFluid();
        
        eventBus.addListener(this::setup);
        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(this::clientSetup);
            eventBus.addListener(ALWClient::models);
        }
        eventBus.addListener(ModDatagen::start);
    }
    
    private void setup(final FMLCommonSetupEvent event) {
    }


    private void clientSetup(final FMLClientSetupEvent event) {
        ALWClient.setup(event);
    }
}
