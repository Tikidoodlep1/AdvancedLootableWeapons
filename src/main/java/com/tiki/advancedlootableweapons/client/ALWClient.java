package com.tiki.advancedlootableweapons.client;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.client.models.MaterialBakedModel;
import com.tiki.advancedlootableweapons.client.screens.AnvilForgingScreen;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ALWClient {

    static final ItemPropertyFunction HEAT_FUNCTION_WRAPPER = (pP1, pP2, pP3, pP4) -> HeatableToolPartItem.HEAT_FUNCTION.apply(pP1, pP2, pP3, pP4).ordinal();

    public static void setup(FMLClientSetupEvent event) {
        MenuScreens.register(MenuInit.ALLOY_FURNACE.get(), AlloyFurnaceScreen::new);
        MenuScreens.register(MenuInit.FORGE.get(), ForgeScreen::new);
        MenuScreens.register(MenuInit.ANVIL_FORGING.get(), AnvilForgingScreen::new);
        MenuScreens.register(MenuInit.JAW_CRUSHER.get(), JawCrusherScreen::new);
        event.enqueueWork(ALWClient::registerItemModelPredicates);
        BlockEntityRenderers.register(BlockEntityInit.DRUM_TE.get(), DrumBlockEntityRenderer::new);
    }

    public static void colors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        itemColors.register((p_92708_, p_92709_) -> {
            return p_92709_ > 0 ? -1 : ((DyeableLeatherItem)p_92708_.getItem()).getColor(p_92708_);
        }, Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS, Items.LEATHER_HORSE_ARMOR);
    }

    public static final ResourceLocation MATERIAL_LOADER = AdvancedLootableWeapons.id("material_loader");
    public static final ResourceLocation HEAT = AdvancedLootableWeapons.id("heat");

    public static void models(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(MATERIAL_LOADER, MaterialBakedModel.Loader.INSTANCE);
    }
    public static void registerItemModelPredicates() {
        for(Item i : ItemInit.hotToolHeads) {
            ItemProperties.register(i, HEAT, HEAT_FUNCTION_WRAPPER);
        }
    }
}
