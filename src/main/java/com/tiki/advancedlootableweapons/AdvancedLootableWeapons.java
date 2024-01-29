package com.tiki.advancedlootableweapons;

import com.tiki.advancedlootableweapons.client.ALWClient;
import com.tiki.advancedlootableweapons.data.ModDatagen;
import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.client.screens.AnvilForgingScreen;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;

import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
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
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("deprecation")
@Mod(AdvancedLootableWeapons.MODID)
public class AdvancedLootableWeapons
{
    public static final String MODID = "advancedlootableweapons";
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();

    public AdvancedLootableWeapons()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        
        BlockEntityInit.register(eventBus);
        MenuInit.register(eventBus);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigHandler.SPEC);
        
        RecipeInit.register(eventBus);
        
        ForgeMod.enableMilkFluid();
        
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(ModDatagen::start);
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(this::generate);
    }

    private void generate(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().is(Items.STICK) && event.getWorld().isClientSide) {
            massRename();
        }
    }

    private static void massRename() {
        String dir = "assets/" + MODID + "/textures/item";


        try {
            File f = new File(dir);

            File[] files = f.listFiles();

            //487
            if (files != null) {
                for (File file : files) {
                    String name = file.getName();
                    String name1 = name.replace(".png","");
                    if (containsMaterial(name1) && containsToolPart(name1)) {
                        String newPath = dir+"/"+createNewPath(name1)+".png";
                        boolean flag = file.renameTo(new File(newPath));
                        if (flag) {
                            System.out.println("File Successfully Rename");
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    private static String createNewPath(String oldPath) {
        String[] parts = oldPath.split("_");

        //the old name looks like battleaxe_shadow_platinum, we want to rename it to shadow_platinum_battleaxe

        // 0,1,2 -> 1,2,0

        //so part[0] = part[n-1], part[n-1] = part[n-2]... part[1] = part[0]

        String[] newParts = new String[parts.length];

        for (int i = 1;i < parts.length;i++) {
            newParts[i-1] = parts[i];
        }
        newParts[newParts.length-1] = parts[0];

        for (String part  : newParts) {
            if (part == null) {
                System.out.println("concen: "+oldPath);
                return oldPath;
            }
        }

        return Arrays.stream(newParts).collect(Collectors.joining("_"));
    }

    private static boolean containsMaterial(String path) {
        for (Pair<String, Tier> tierPair : ItemInit.tiers) {
            if (path.contains(tierPair.getKey())) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsToolPart(String path) {
        for (WeaponAttributes attributes : ItemInit.attributes) {
            if (path.contains(attributes.getType().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        ALWClient.setup(event);
    }
}
