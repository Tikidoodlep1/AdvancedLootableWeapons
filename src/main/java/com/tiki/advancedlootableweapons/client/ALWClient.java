package com.tiki.advancedlootableweapons.client;

import com.tiki.advancedlootableweapons.client.screens.AnvilForgingScreen;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.tiki.advancedlootableweapons.AdvancedLootableWeapons.MODID;

public class ALWClient {

    static final ItemPropertyFunction HEAT_FUNCTION_WRAPPER = (pP1, pP2, pP3, pP4) -> HotToolHeadItem.HEAT_FUNCTION.apply(pP1, pP2, pP3, pP4).ordinal();

    public static void setup(FMLClientSetupEvent event) {
        MenuScreens.register(MenuInit.ALLOY_FURNACE_CONTAINER.get(), AlloyFurnaceScreen::new);
        MenuScreens.register(MenuInit.FORGE_CONTAINER.get(), ForgeScreen::new);
        MenuScreens.register(MenuInit.ANVIL_FORGING_CONTAINER.get(), AnvilForgingScreen::new);
        MenuScreens.register(MenuInit.JAW_CRUSHER_CONTAINER.get(), JawCrusherScreen::new);
        event.enqueueWork(ALWClient::registerItemModelPredicates);
        BlockEntityRenderers.register(BlockEntityInit.DRUM_TE.get(), DrumBlockEntityRenderer::new);
    }
    public static void registerItemModelPredicates() {
        for(Item i : ItemInit.hotToolHeads) {
            ItemProperties.register(i, new ResourceLocation(MODID, "heat"), HEAT_FUNCTION_WRAPPER);
        }
    }
}
