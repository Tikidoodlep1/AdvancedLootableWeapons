package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.displays.TanningRackDisplay;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.util.MCVersion;
import com.tiki.advancedlootableweapons.util.TranslationKeys;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

public class TanningRackCategory implements DisplayCategory<TanningRackDisplay> {


    @Override
    public CategoryIdentifier<? extends TanningRackDisplay> getCategoryIdentifier() {
        return REICompat.TANNING_RACK;
    }

    @Override
    public Component getTitle() {
        return TranslationKeys.TANNING_RACK_CAT;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockInit.TANNING_RACK.get());
    }

    @Override
    public List<Widget> setupDisplay(TanningRackDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 85, startPoint.y + 9)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 10)).entries(display.getInputEntries().get(0))
                .markInput());

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 40, startPoint.y + 9))
                .animationDurationTicks(display.time));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 85, startPoint.y + 9)).entries(display.getOutputEntries().get(0))
                .disableBackground().markOutput());

        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5),
               TranslationKeys.createTime(display.time)).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

        return widgets;
    }


    @Override
    public int getDisplayHeight() {
        return 55;
    }
}
