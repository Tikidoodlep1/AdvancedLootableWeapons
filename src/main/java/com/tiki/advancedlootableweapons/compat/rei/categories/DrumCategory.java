package com.tiki.advancedlootableweapons.compat.rei.categories;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

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

        Point startPoint = new Point(bounds.getX() + 25, bounds.getY() + 3);

        widgets.add(Widgets.createTexturedWidget(new ResourceLocation(AdvancedLootableWeapons.MODID, "textures/gui/rei/drum.png"),
                new Rectangle(startPoint.x, startPoint.y, 100, 66)));
        List<EntryIngredient> entryIngredients = display.getInputEntries();


        widgets.add(Widgets.createSlot(new Point(startPoint.x+1, startPoint.y+21)).entries(entryIngredients.get(0))
                .disableBackground().markInput());

        if (!entryIngredients.get(1).isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 27, startPoint.y+1)).entries(entryIngredients.get(1))
                    .disableBackground().markInput());
        }

         ItemStack stack = new ItemStack(BlockInit.CLAY_DRUM.get());
              widgets.add(Widgets.createSlot(new Point(startPoint.x + 27, startPoint.y + 43)).entries(List.of(EntryStacks.of(stack)))
                      .disableBackground().markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 53, startPoint.y + 21)).entries(entryIngredients.get(2))
                .disableBackground().markInput());


        widgets.add(Widgets.createArrow(new Point(startPoint.x + 49, startPoint.y + 44))
                .animationDurationTicks(display.time));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 43)).entries(display.getOutputEntries().get(0))
                .disableBackground().markOutput());

        return widgets;
    }
}
