package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class DrumQuenchingDisplay extends BasicDisplay implements SimpleGridMenuDisplay {
    public final int time;
    public final boolean needsClay;

    public DrumQuenchingDisplay(DrumQuenchingRecipe recipe) {
        this(List.of(EntryIngredients.of(recipe.getInput())),
                List.of(EntryIngredients.of(DrumQuenchingRecipe.applyQuench(recipe.getInput()))),
                Optional.ofNullable(recipe).map(DrumQuenchingRecipe::getId),recipe.getTime(),recipe.needsClay());
    }



    public DrumQuenchingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location,int time,boolean needsClay) {
        super(inputs, outputs, location);
        this.time = time;
        this.needsClay = needsClay;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICompat.DRUM_QUENCHING;
    }
}
