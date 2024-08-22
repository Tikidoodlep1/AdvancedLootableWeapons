package com.tiki.advancedlootableweapons.compat.rei;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.categories.*;
import com.tiki.advancedlootableweapons.compat.rei.displays.*;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceScreen;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherScreen;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.checkerframework.checker.units.qual.A;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@REIPluginClient
public class REICompat implements REIClientPlugin {

    public static final CategoryIdentifier<JawCrusherDisplay> JAW_CRUSHER = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/jaw_crusher");
    public static final CategoryIdentifier<AlloyFurnaceDisplay> ALLOY_FURNACE = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/alloy_furnace");
    public static final CategoryIdentifier<DrumQuenchingDisplay> DRUM_QUENCHING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum_quenching");
    public static final CategoryIdentifier<DrumDisplay> DRUM = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/drum");
    public static final CategoryIdentifier<AnvilForgingRecipeDisplay> ANVIL_FORGING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/anvil_forging");
    public static final CategoryIdentifier<SequencedAnvilForgingDisplay> SEQUENCED_ANVIL_FORGING = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/sequenced_anvil_forging");


    public static final String ALLOY_FURNACE_CAT = "category.rei.advancedlootableweapons.alloy_furnace";
    public static final String JAW_CRUSHER_CAT = "category.rei.advancedlootableweapons.jaw_crusher";

    public static final String QUENCHING = "category.rei.advancedlootableweapons.drum_quenching";
    public static final String DRUM_CAT = "category.rei.advancedlootableweapons.drum";

    public static FluidStack convert(net.minecraftforge.fluids.FluidStack stack) {
        return FluidStack.create(stack.getFluid(), stack.getAmount());
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        AlloyFurnaceCategory alloyFurnaceCategory = new AlloyFurnaceCategory(ALLOY_FURNACE_CAT);
        JawCrusherCategory jawCrusherCategory = new JawCrusherCategory(JAW_CRUSHER_CAT);
        DrumQuenchingCategory drumQuenchingCategory = new DrumQuenchingCategory(QUENCHING);
        DrumCategory drumCategory = new DrumCategory(DRUM_CAT);
        AnvilForgingCategory anvilForgingCategory = new AnvilForgingCategory();
        SequencedAnvilForgingCategory sequencedAnvilForgingCategory = new SequencedAnvilForgingCategory();

        registry.add(alloyFurnaceCategory);
        registry.add(jawCrusherCategory);
        registry.add(drumQuenchingCategory);
        registry.add(drumCategory);
        registry.add(anvilForgingCategory);
        registry.add(sequencedAnvilForgingCategory);

        registry.addWorkstations(alloyFurnaceCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.ALLOY_FURNACE.get()));
        registry.addWorkstations(jawCrusherCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.JAW_CRUSHER.get()));
        registry.addWorkstations(drumQuenchingCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        registry.addWorkstations(drumCategory.getCategoryIdentifier(), EntryStacks.of(BlockInit.CLAY_DRUM.get()));
        List<ItemStack> hammers = Registry.ITEM.stream().filter(ForgeHammerItem.class::isInstance).map(Item::getDefaultInstance).toList();
        for (ItemStack stack : hammers) {
            registry.addWorkstations(anvilForgingCategory.getCategoryIdentifier(), EntryStacks.of(stack));
        }
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlloyFurnaceRecipe.class, ModRecipeTypes.ALLOY_FURNACE, AlloyFurnaceDisplay::new);
        registry.registerRecipeFiller(JawCrusherRecipe.class, ModRecipeTypes.CRUSHING, JawCrusherDisplay::new);
        registry.registerRecipeFiller(DrumQuenchingRecipe.class, ModRecipeTypes.DRUM_QUENCHING, DrumQuenchingDisplay::new);
        registry.registerRecipeFiller(DrumRecipe.class, ModRecipeTypes.DRUM, DrumDisplay::new);
        //  registry.registerRecipeFiller(AbstractAnvilForgingRecipe.class,ModRecipeTypes.ANVIL_FORGING, abstractAnvilForgingRecipe -> AnvilForgingRecipeDisplay.create(abstractAnvilForgingRecipe,registry));

        List<AbstractAnvilForgingRecipe> allRecipes = registry.getRecipeManager().getAllRecipesFor(ModRecipeTypes.ANVIL_FORGING);

        List<AbstractAnvilForgingRecipe> nonToolRecipes = new ArrayList<>();
        List<AbstractAnvilForgingRecipe> toolRecipes = new ArrayList<>();

        for (AbstractAnvilForgingRecipe abstractAnvilForgingRecipe : allRecipes) {
            ItemStack result = abstractAnvilForgingRecipe.getResultItem();
            if (result.getItem() instanceof HeatableToolPartItem || result.getItem() instanceof AlwWeaponItem) {
                toolRecipes.add(abstractAnvilForgingRecipe);
            } else if (result.getItem() instanceof BoundArmorItem) {
                nonToolRecipes.add(abstractAnvilForgingRecipe);
            }
        }


        nonToolRecipes.forEach(abstractAnvilForgingRecipe -> registry.add(AnvilForgingRecipeDisplay.create(abstractAnvilForgingRecipe, registry)));

        List<SequencedAnvilForgingDisplay.Builder> builders = new ArrayList<>();


        for (WeaponMaterial weaponMaterial :WeaponMaterial.LOOKUP.values()) {
            if ((weaponMaterial.metalStats() == null))continue;
            for (AbstractAnvilForgingRecipe toolrecipe : toolRecipes) {
                ItemStack result = toolrecipe.getResultItem();
                if (result.getItem() instanceof AlwWeaponItem || result.getItem() == ItemInit.CHAIN_RING.get()) {
                    if (result.getItem() != ItemInit.CHAIN_RING.get() && !weaponMaterial.canMakeWeapon()) continue;
                    ItemStack stack = new ItemStack(result.getItem());
                    String materialName = WeaponMaterial.getMaterialNameF(weaponMaterial);
                    AlwWeaponItem.setMaterial(stack, materialName);
                    SequencedAnvilForgingDisplay.Builder builder = SequencedAnvilForgingDisplay.Builder.builder(stack);
                    Ingredient input1 = toolrecipe.getFirst();
                    Ingredient input2 = toolrecipe.getSecond();

                    ItemStack stack1 = getFirstOrEmpty(input1);
                    ItemStack stack2 = getFirstOrEmpty(input2);

                    ItemStack disp1 = new ItemStack(stack1.getItem());
                    ItemStack disp2 = new ItemStack(stack2.getItem());

                    if (disp1.getItem() instanceof HeatableToolPartItem) {
                        AlwWeaponItem.setMaterial(disp1, materialName);
                    }

                    if (disp2.getItem() instanceof HeatableToolPartItem) {
                        AlwWeaponItem.setMaterial(disp2, materialName);
                    }

                    builder.addItem(disp1, disp2);

                    builders.add(builder);
                }
            }


            for (int i = 0; i < 9; i++) {
                for (AbstractAnvilForgingRecipe toolrecipe : toolRecipes) {
                    ItemStack result = toolrecipe.getResultItem();
                    ItemStack stack = new ItemStack(result.getItem());


                    for (SequencedAnvilForgingDisplay.Builder builder : builders) {
                        if (builder.finished) continue;
                        if (stack.getItem() == builder.getLast()) {
                            String materialName = WeaponMaterial.getMaterialNameF(weaponMaterial);
                            AlwWeaponItem.setMaterial(stack, materialName);
                            Ingredient input1 = toolrecipe.getFirst();
                            Ingredient input2 = toolrecipe.getSecond();
                            ItemStack stack1 = getFirstOrEmpty(input1);
                            ItemStack stack2 = getFirstOrEmpty(input2);

                            ItemStack disp1 = new ItemStack(stack1.getItem());
                            ItemStack disp2 = new ItemStack(stack2.getItem());

                            if (disp1.getItem() instanceof HeatableToolPartItem) {
                                AlwWeaponItem.setMaterial(disp1, materialName);
                            } else {
                                disp1 = getFirstOrEmpty(weaponMaterial.tier().getRepairIngredient());
                            }

                            if (disp2.getItem() instanceof HeatableToolPartItem) {
                                AlwWeaponItem.setMaterial(disp2, materialName);
                            }
                            builder.addItem(disp1, disp2);
                        }
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
        registry.register(SimpleTransferHandler.create(AlloyFurnaceContainer.class, ALLOY_FURNACE,
                new SimpleTransferHandler.IntRange(0, 2)));

        registry.register(SimpleTransferHandler.create(JawCrusherContainer.class, JAW_CRUSHER,
                new SimpleTransferHandler.IntRange(0, 1)));
    }
}
