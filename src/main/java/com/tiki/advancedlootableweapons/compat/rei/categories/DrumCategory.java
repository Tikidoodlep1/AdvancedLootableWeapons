package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.displays.DrumDisplay;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.util.MCVersion;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class DrumCategory implements DisplayCategory<DrumDisplay> {

    private final String categoryName;

    public DrumCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public CategoryIdentifier<? extends DrumDisplay> getCategoryIdentifier() {
        return REICompat.DRUM;
    }

    @Override
    public Component getTitle() {
        return MCVersion.translation(categoryName);
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockInit.CLAY_DRUM.get());
    }

    @Override
    public List<Widget> setupDisplay(DrumDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 85, startPoint.y + 9)));

        List<EntryIngredient> entryIngredients = display.getInputEntries();

        int ingY = 8;

        widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + ingY)).entries(entryIngredients.get(0))
                .markInput());

        if (!entryIngredients.get(1).isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + ingY)).entries(entryIngredients.get(1))
                    .markInput());
        } else {
            ItemStack stack = new ItemStack(Items.BARRIER);
            stack.setHoverName(MCVersion.literal("Empty Slot"));
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + ingY)).entries(List.of(EntryStacks.of(stack)))
                    .markInput());
        }

            widgets.add(Widgets.createSlot(new Point(startPoint.x + 36, startPoint.y + ingY)).entries(entryIngredients.get(2))
                    .markInput());


        widgets.add(Widgets.createArrow(new Point(startPoint.x + 55, startPoint.y + 9))
                .animationDurationTicks(display.time));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 85, startPoint.y + 9)).entries(display.getOutputEntries().get(0))
                .disableBackground().markOutput());

        return widgets;
    }


    @Override
    public int getDisplayHeight() {
        return 49;
    }
}
