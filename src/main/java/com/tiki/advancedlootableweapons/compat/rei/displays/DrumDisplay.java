package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;
import com.tiki.advancedlootableweapons.recipes.DrumRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public class DrumDisplay extends BasicDisplay implements SimpleGridMenuDisplay {
    public final int time;
    public DrumDisplay(DrumRecipe recipe) {
        this(List.of(EntryIngredients.ofIngredient(recipe.getInput()),EntryIngredients.ofIngredient(recipe.getAdditive())
                ,EntryIngredients.of(REICompat.convert(recipe.getFluidInput()))),
                List.of(EntryIngredients.of(recipe.getResultItem())),
                Optional.ofNullable(recipe).map(DrumRecipe::getId),recipe.getTime());
    }



    public DrumDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, int time) {
        super(inputs, outputs, location);
        this.time = time;
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
        return REICompat.DRUM;
    }
}
