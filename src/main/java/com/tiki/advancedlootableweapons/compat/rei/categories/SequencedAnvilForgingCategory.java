package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.displays.SequencedAnvilForgingDisplay;
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
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class SequencedAnvilForgingCategory implements DisplayCategory<SequencedAnvilForgingDisplay> {
    @Override
    public CategoryIdentifier<? extends SequencedAnvilForgingDisplay> getCategoryIdentifier() {
        return REICompat.SEQUENCED_ANVIL_FORGING;
    }

    @Override
    public Component getTitle() {
        return TranslationKeys.SEQUENCED_ANVIL_FORGING_CAT;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Items.ANVIL);
    }


    @Override
    public List<Widget> setupDisplay(SequencedAnvilForgingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        List<EntryIngredient> inputs = display.getInputEntries();
        EntryIngredient output = display.getOutputEntries().get(0);

        Point startPoint = new Point(bounds.getCenterX() - 77, bounds.getCenterY() - 50);

        int size = inputs.size();
        for (int i = 0; i < size; i+=2) {
            EntryIngredient entryIngredient = inputs.get(i);
            EntryIngredient entryIngredient2 = inputs.get(i + 1);

            int gap = 14;

            int x = gap * (i % 8);

            int y = 50 * (i / 8);


            widgets.add(Widgets.createSlot(new Point(startPoint.x + 10 + x, startPoint.y + 12 + y)).entries(entryIngredient).markInput().markOutput());
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 10 + x, startPoint.y + 30 + y)).entries(entryIngredient2).markInput().markOutput());


            int xp = display.xps.getInt(i / 2);

            int j = i + 2;

            if (i / 2 == display.xps.size() - 2) {
                widgets.add(Widgets.createLabel(new Point(startPoint.x + 142 , startPoint.y + 23),
                        TranslationKeys.createXp(xp)).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));
            } else {

                x = gap * (j % 8);

                y = 50 * (j / 8);


                widgets.add(Widgets.createLabel(new Point(startPoint.x + 30 + x, startPoint.y + 2 + y),
                        TranslationKeys.createXp(xp)).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

            }
        }

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 120, startPoint.y + 38)).entries(output).disableBackground().markOutput());
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 121, startPoint.y + 39)));


        return widgets;
    }

    @Override
    public int getDisplayWidth(SequencedAnvilForgingDisplay display) {
        return DisplayCategory.super.getDisplayWidth(display);
    }

    @Override
    public int getDisplayHeight() {
        return 110;
    }
}
