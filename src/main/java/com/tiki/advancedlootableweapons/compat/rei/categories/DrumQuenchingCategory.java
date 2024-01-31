package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.displays.DrumQuenchingDisplay;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.util.MCVersion;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.util.List;

public class DrumQuenchingCategory implements DisplayCategory<DrumQuenchingDisplay> {

    @Override
    public CategoryIdentifier<? extends DrumQuenchingDisplay> getCategoryIdentifier() {
        return REICompat.DRUM_QUENCHING;
    }

    @Override
    public Component getTitle() {
        return BlockInit.CLAY_DRUM.get().getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockInit.CLAY_DRUM.get());
    }

    @Override
    public List<Widget> setupDisplay(DrumQuenchingDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 85, startPoint.y + 9)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 1)).entries(display.getInputEntries().get(0))
                .markInput());

        if (display.needsClay) {
            widgets.add(Widgets.createLabel(new Point(bounds.x + 26, bounds.getMaxY() - 15),
                            MCVersion.translation("category.advancedlootableweapons.drum_quenching.requires_clay"))
                    .color(0xFF404040, 0xFFBBBBBB).noShadow().leftAligned());
        }


        widgets.add(Widgets.createArrow(new Point(startPoint.x + 52, startPoint.y + 9))
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
