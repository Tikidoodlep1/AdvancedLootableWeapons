package com.tiki.advancedlootableweapons.compat.rei;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.categories.AlloyFurnaceCategory;
import com.tiki.advancedlootableweapons.compat.rei.categories.JawCrusherCategory;
import com.tiki.advancedlootableweapons.compat.rei.displays.AlloyFurnaceDisplay;
import com.tiki.advancedlootableweapons.compat.rei.displays.JawCrusherDisplay;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class REICompat implements REIClientPlugin {

    public static final CategoryIdentifier<JawCrusherDisplay> JAW_CRUSHER = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/jaw_crusher");
    public static final CategoryIdentifier<AlloyFurnaceDisplay> ALLOY_FURNACE = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/alloy_furnace");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        AlloyFurnaceCategory alloyFurnaceCategory = new AlloyFurnaceCategory();
        JawCrusherCategory jawCrusherCategory = new JawCrusherCategory();
        registry.add(alloyFurnaceCategory);
        registry.add(jawCrusherCategory);
        registry.addWorkstations(alloyFurnaceCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.ALLOY_FURNACE.get()));
        registry.addWorkstations(jawCrusherCategory.getCategoryIdentifier(),EntryStacks.of(BlockInit.JAW_CRUSHER.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlloyFurnaceRecipe.class, ModRecipeTypes.ALLOY_FURNACE, AlloyFurnaceDisplay::new);
        registry.registerRecipeFiller(JawCrusherRecipe.class,ModRecipeTypes.CRUSHING, JawCrusherDisplay::new);

    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(80, 32, 28, 23),//x,y,width,height
                AlloyFurnaceScreen.class, ALLOY_FURNACE);
        registry.registerContainerClickArea(new Rectangle(75, 32, 28, 23),//x,y,width,height
                JawCrusherScreen.class, JAW_CRUSHER);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        //inputs are 0 and 1
        registry.register(SimpleTransferHandler.create(AlloyFurnaceContainer.class, ALLOY_FURNACE,
                new SimpleTransferHandler.IntRange(0, 2)));

         registry.register(SimpleTransferHandler.create(JawCrusherContainer.class, JAW_CRUSHER,
                 new SimpleTransferHandler.IntRange(0, 1)));
    }
}
