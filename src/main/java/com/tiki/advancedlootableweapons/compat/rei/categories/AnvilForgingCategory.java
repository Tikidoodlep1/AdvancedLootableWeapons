package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.displays.AnvilForgingRecipeDisplay;
import com.tiki.advancedlootableweapons.util.TranslationKeys;
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
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class AnvilForgingCategory implements DisplayCategory<AnvilForgingRecipeDisplay> {
    @Override
    public CategoryIdentifier<? extends AnvilForgingRecipeDisplay> getCategoryIdentifier() {
        return REICompat.ANVIL_FORGING;
    }

    @Override
    public Component getTitle() {
        return TranslationKeys.ANVIL_FORGING_CAT;
    }

    @Override
    public List<Widget> setupDisplay(AnvilForgingRecipeDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 85, startPoint.y + 9)));


        List<EntryIngredient> inputs = display.getInputEntries();
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y)).entries(inputs.get(0))
                .markInput());
        if (inputs.size() > 1) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 18)).entries(inputs.get(1))
                    .markInput());
        }


        widgets.add(Widgets.createArrow(new Point(startPoint.x + 40, startPoint.y + 9)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 85, startPoint.y + 9)).entries(display.getOutputEntries().get(0))
                .disableBackground().markOutput());

        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Blocks.ANVIL);
    }
}
