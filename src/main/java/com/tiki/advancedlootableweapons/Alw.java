package com.tiki.advancedlootableweapons;

import com.mojang.logging.LogUtils;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModInfo.ID)
public class Alw
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Alw()
    {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
    	event.enqueueWork(new Runnable() {

			@Override
			public void run() {
				ItemProperties.register(ItemInit.HOT_TOOL_HEAD.get(), new ResourceLocation("advancedlootableweapons:heat"), new ItemPropertyFunction() {

						@Override
						public float call(ItemStack stack, ClientLevel world, LivingEntity player, int id) {
							return stack.getDamageValue();
						}
				});
    		
			}
    	});
    }
}
