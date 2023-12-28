package com.tiki.advancedlootableweapons.compat.rei;


import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class REICompat implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        AlloyFurnaceCategory alloyFurnaceCategory = new AlloyFurnaceCategory();
        registry.add(alloyFurnaceCategory);
        registry.addWorkstations(alloyFurnaceCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.ALLOY_FURNACE.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlloyFurnaceRecipe.class, AlloyFurnaceRecipe.Type.INSTANCE, AlloyFurnaceDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(80, 32, 28, 23),//x,y,width,height
                AlloyFurnaceScreen.class, AlloyFurnaceCategory.CATEGORY_IDENTIFIER);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        //inputs are 0 and 1
       // registry.register(SimpleTransferHandler.create(AlloyFurnaceContainer.class, AlloyFurnaceCategory.CATEGORY_IDENTIFIER,
       //         new SimpleTransferHandler.IntRange(0, 2)));
    }
}
