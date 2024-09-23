package com.tiki.advancedlootableweapons.compat.rei;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.categories.*;
import com.tiki.advancedlootableweapons.compat.rei.displays.*;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.items.armor.ArmorPlateItem;
import com.tiki.advancedlootableweapons.menu.AlloyFurnaceMenu;
import com.tiki.advancedlootableweapons.client.screens.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.menu.JawCrusherMenu;
import com.tiki.advancedlootableweapons.client.screens.JawCrusherScreen;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import com.tiki.advancedlootableweapons.recipes.*;
import com.tiki.advancedlootableweapons.util.TranslationKeys;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@REIPluginClient
public class REICompat implements REIClientPlugin {

    public static final CategoryIdentifier<JawCrusherDisplay> JAW_CRUSHER = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/jaw_crusher");
    public static final CategoryIdentifier<AlloyFurnaceDisplay> ALLOY_FURNACE = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/alloy_furnace");
    public static final CategoryIdentifier<DrumQuenchingDisplay> DRUM_QUENCHING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum_quenching");
    public static final CategoryIdentifier<DrumDisplay> DRUM = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum");
    public static final CategoryIdentifier<AnvilForgingRecipeDisplay> ANVIL_FORGING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/anvil_forging");
    public static final CategoryIdentifier<SequencedAnvilForgingDisplay> SEQUENCED_ANVIL_FORGING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/sequenced_anvil_forging");
    public static final CategoryIdentifier<TanningRackDisplay> TANNING_RACK = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/tanning_rack");


    public static FluidStack convert(net.minecraftforge.fluids.FluidStack stack) {
        return FluidStack.create(stack.getFluid(), stack.getAmount());
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        AlloyFurnaceCategory alloyFurnaceCategory = new AlloyFurnaceCategory(TranslationKeys.ALLOY_FURNACE_CAT);
        JawCrusherCategory jawCrusherCategory = new JawCrusherCategory();
        DrumQuenchingCategory drumQuenchingCategory = new DrumQuenchingCategory();
        DrumCategory drumCategory = new DrumCategory();
        AnvilForgingCategory anvilForgingCategory = new AnvilForgingCategory();
        SequencedAnvilForgingCategory sequencedAnvilForgingCategory = new SequencedAnvilForgingCategory();
        TanningRackCategory tanningRackCategory = new TanningRackCategory();

        registry.add(alloyFurnaceCategory);
        registry.add(jawCrusherCategory);
        registry.add(drumQuenchingCategory);
        registry.add(drumCategory);
        registry.add(anvilForgingCategory);
        registry.add(sequencedAnvilForgingCategory);
        registry.add(tanningRackCategory);

        registry.addWorkstations(alloyFurnaceCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.ALLOY_FURNACE.get()));
        registry.addWorkstations(jawCrusherCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.JAW_CRUSHER.get()));
        registry.addWorkstations(drumQuenchingCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        registry.addWorkstations(drumCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        registry.addWorkstations(tanningRackCategory.getCategoryIdentifier(),EntryStacks.of(BlockInit.TANNING_RACK.get()));
        List<ItemStack> hammers = Registry.ITEM.stream().filter(ForgeHammerItem.class::isInstance).map(Item::getDefaultInstance).toList();
        for (ItemStack stack : hammers) {
            registry.addWorkstations(anvilForgingCategory.getCategoryIdentifier(), EntryStacks.of(stack));
            registry.addWorkstations(sequencedAnvilForgingCategory.getCategoryIdentifier(), EntryStacks.of(stack));
        }
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlloyFurnaceRecipe.class, ModRecipeTypes.ALLOY_FURNACE, AlloyFurnaceDisplay::new);
        registry.registerRecipeFiller(JawCrusherRecipe.class, ModRecipeTypes.CRUSHING, JawCrusherDisplay::new);
        registry.registerRecipeFiller(DrumQuenchingRecipe.class, ModRecipeTypes.DRUM_QUENCHING, DrumQuenchingDisplay::new);
        registry.registerRecipeFiller(DrumRecipe.class, ModRecipeTypes.DRUM, DrumDisplay::new);
        registry.registerRecipeFiller(TanningRackRecipe.class,ModRecipeTypes.TANNING_RACK,TanningRackDisplay::new);
        //  registry.registerRecipeFiller(AbstractAnvilForgingRecipe.class,ModRecipeTypes.ANVIL_FORGING, abstractAnvilForgingRecipe -> AnvilForgingRecipeDisplay.create(abstractAnvilForgingRecipe,registry));

        List<AbstractAnvilForgingRecipe> allRecipes = registry.getRecipeManager().getAllRecipesFor(ModRecipeTypes.ANVIL_FORGING);

        List<AbstractAnvilForgingRecipe> nonSequencedRecipes = new ArrayList<>();
        List<AbstractAnvilForgingRecipe> sequencedRecipes = new ArrayList<>();

        for (AbstractAnvilForgingRecipe abstractAnvilForgingRecipe : allRecipes) {
            if (abstractAnvilForgingRecipe.isUseSequence()) {
                sequencedRecipes.add(abstractAnvilForgingRecipe);
            } else {
                nonSequencedRecipes.add(abstractAnvilForgingRecipe);
            }
        }


        nonSequencedRecipes.forEach(abstractAnvilForgingRecipe -> registry.add(AnvilForgingRecipeDisplay.create(abstractAnvilForgingRecipe)));

        List<SequencedAnvilForgingDisplay.Builder> builders = new ArrayList<>();


            for (AbstractAnvilForgingRecipe toolrecipe : sequencedRecipes) {
                ItemStack result = toolrecipe.getResultItem();
                if (result.getItem() instanceof AlwWeaponItem || result.getItem() == ItemInit.CHAIN_RING.get() || result.getItem() instanceof ArmorPlateItem) {
                    SequencedAnvilForgingDisplay.Builder builder = SequencedAnvilForgingDisplay.Builder.builder(result);
                    Ingredient input1 = toolrecipe.getFirst();
                    Ingredient input2 = toolrecipe.getSecond();

                    ItemStack stack1 = getFirstOrEmpty(input1);
                    ItemStack stack2 = getFirstOrEmpty(input2);

                    builder.addItem(stack1, stack2);

                    builders.add(builder);
                }
            }


            for (int i = 0; i < 8; i++) {
                for (AbstractAnvilForgingRecipe toolrecipe : sequencedRecipes) {
                    ItemStack result = toolrecipe.getResultItem();


                    for (SequencedAnvilForgingDisplay.Builder builder : builders) {
                        if (builder.finished) continue;
                        if (ItemStack.isSame(result,builder.getLast()) && (Objects.equals(result.getTag(),builder.getLast().getTag()) || result.getItem() instanceof ArmorPlateItem)) {
                            Ingredient input1 = toolrecipe.getFirst();
                            Ingredient input2 = toolrecipe.getSecond();
                            ItemStack stack1 = getFirstOrEmpty(input1);
                            ItemStack stack2 = getFirstOrEmpty(input2);


                            builder.addItem(stack1, stack2);
                        }
                    }
                }
            }
        for (SequencedAnvilForgingDisplay.Builder builder : builders) {
            registry.add(builder.build());
        }
    }

    public static ItemStack getFirstOrEmpty(Ingredient ingredient) {
        ItemStack[] matches = ingredient.getItems();
        if (matches.length == 0) return ItemStack.EMPTY;
        return matches[0];
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
        registry.register(SimpleTransferHandler.create(AlloyFurnaceMenu.class, ALLOY_FURNACE,
                new SimpleTransferHandler.IntRange(0, 2)));

        registry.register(SimpleTransferHandler.create(JawCrusherMenu.class, JAW_CRUSHER,
                new SimpleTransferHandler.IntRange(0, 1)));
    }
}
