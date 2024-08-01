package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilMaterialForgingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class AnvilForgingRecipeDisplay extends BasicDisplay implements SimpleGridMenuDisplay {
    public AnvilForgingRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public static AnvilForgingRecipeDisplay create(AbstractAnvilForgingRecipe abstractAnvilForgingRecipe) {
        List<Ingredient> ingredients = abstractAnvilForgingRecipe.getIngredients();
        return new AnvilForgingRecipeDisplay(EntryIngredients.ofIngredients(ingredients),List.of(EntryIngredients.of(abstractAnvilForgingRecipe.getResultItem())));
    }

    @Override
    public int getWidth() {
        return 160;
    }

    @Override
    public int getHeight() {
        return 60;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICompat.ANVIL_FORGING;
    }
}
