package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public class JawCrusherDisplay extends BasicDisplay implements SimpleGridMenuDisplay {


    public JawCrusherDisplay(JawCrusherRecipe recipe) {
        this(List.of(EntryIngredients.ofIngredient(recipe.getInput())),List.of(EntryIngredients.of(recipe.getResultItem())), Optional.ofNullable(recipe).map(JawCrusherRecipe::getId));
    }

    public JawCrusherDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location) {
        super(inputs, outputs, location);
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
        return REICompat.JAW_CRUSHER;
    }
}
