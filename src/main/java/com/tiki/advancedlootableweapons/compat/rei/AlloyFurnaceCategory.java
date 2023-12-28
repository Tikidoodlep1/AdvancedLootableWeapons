package com.tiki.advancedlootableweapons.compat.rei;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;

import java.util.List;

public class AlloyFurnaceCategory implements DisplayCategory<AlloyFurnaceDisplay> {

    public static final CategoryIdentifier<AlloyFurnaceDisplay> CATEGORY_IDENTIFIER = CategoryIdentifier.of(AdvancedLootableWeapons.MODID, "plugins/alloy_furnace");

    @Override
    public CategoryIdentifier<? extends AlloyFurnaceDisplay> getCategoryIdentifier() {
        return CATEGORY_IDENTIFIER;
    }

    @Override
    public Component getTitle() {
        return BlockInit.ALLOY_FURNACE.get().getName();
    }

    @Override
    public List<Widget> setupDisplay(AlloyFurnaceDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 85, startPoint.y + 9)));


        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 1)).entries(display.getInputEntries().get(0))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 33, startPoint.y + 1)).entries(display.getInputEntries().get(1))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 52, startPoint.y + 9))
                .animationDurationTicks(display.cookTime));

        widgets.add(Widgets.createBurningFire(new Point(startPoint.x + 24, startPoint.y + 22))
                .animationDurationMS(10000));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 85, startPoint.y + 9)).entries(display.getOutputEntries().get(0))
                .disableBackground().markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 49;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockInit.ALLOY_FURNACE.get());
    }
}
