package com.tiki.advancedlootableweapons.compat.rei;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.categories.*;
import com.tiki.advancedlootableweapons.compat.rei.displays.*;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;
import com.tiki.advancedlootableweapons.recipes.*;
import dev.architectury.fluid.FluidStack;
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
import net.minecraft.core.Registry;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@REIPluginClient
public class REICompat implements REIClientPlugin {

    public static final CategoryIdentifier<JawCrusherDisplay> JAW_CRUSHER = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/jaw_crusher");
    public static final CategoryIdentifier<AlloyFurnaceDisplay> ALLOY_FURNACE = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/alloy_furnace");
    public static final CategoryIdentifier<DrumQuenchingDisplay> DRUM_QUENCHING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum_quenching");
    public static final CategoryIdentifier<DrumDisplay> DRUM = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum");
    public static final CategoryIdentifier<AnvilForgingRecipeDisplay> ANVIL_FORGING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/anvil_forging");

    public static final String ALLOY_FURNACE_CAT = "category.rei.advancedlootableweapons.alloy_furnace";
    public static final String JAW_CRUSHER_CAT = "category.rei.advancedlootableweapons.jaw_crusher";

    public static final String QUENCHING = "category.rei.advancedlootableweapons.drum_quenching";
    public static final String DRUM_CAT = "category.rei.advancedlootableweapons.drum";

    public static FluidStack convert(net.minecraftforge.fluids.FluidStack stack) {
        return FluidStack.create(stack.getFluid(),stack.getAmount());
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        AlloyFurnaceCategory alloyFurnaceCategory = new AlloyFurnaceCategory(ALLOY_FURNACE_CAT);
        JawCrusherCategory jawCrusherCategory = new JawCrusherCategory(JAW_CRUSHER_CAT);
        DrumQuenchingCategory drumQuenchingCategory = new DrumQuenchingCategory(QUENCHING);
        DrumCategory drumCategory = new DrumCategory(DRUM_CAT);
        AnvilForgingCategory anvilForgingCategory = new AnvilForgingCategory();

        registry.add(alloyFurnaceCategory);
        registry.add(jawCrusherCategory);
        registry.add(drumQuenchingCategory);
        registry.add(drumCategory);
        registry.add(anvilForgingCategory);

        registry.addWorkstations(alloyFurnaceCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.ALLOY_FURNACE.get()));
        registry.addWorkstations(jawCrusherCategory.getCategoryIdentifier(),EntryStacks.of(BlockInit.JAW_CRUSHER.get()));
        registry.addWorkstations(drumQuenchingCategory.getCategoryIdentifier(),EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        registry.addWorkstations(drumCategory.getCategoryIdentifier(),EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        List<ItemStack> anvils = Registry.ITEM.stream().filter(item -> item.builtInRegistryHolder().is(ItemTags.ANVIL)).map(Item::getDefaultInstance).toList();
        for (ItemStack stack : anvils) {
            registry.addWorkstations(anvilForgingCategory.getCategoryIdentifier(), EntryStacks.of(stack));
        }
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlloyFurnaceRecipe.class, ModRecipeTypes.ALLOY_FURNACE, AlloyFurnaceDisplay::new);
        registry.registerRecipeFiller(JawCrusherRecipe.class,ModRecipeTypes.CRUSHING, JawCrusherDisplay::new);
        registry.registerRecipeFiller(DrumQuenchingRecipe.class,ModRecipeTypes.DRUM_QUENCHING, DrumQuenchingDisplay::new);
        registry.registerRecipeFiller(DrumRecipe.class,ModRecipeTypes.DRUM, DrumDisplay::new);
        registry.registerRecipeFiller(AbstractAnvilForgingRecipe.class,ModRecipeTypes.ANVIL_FORGING, AnvilForgingRecipeDisplay::create);
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
