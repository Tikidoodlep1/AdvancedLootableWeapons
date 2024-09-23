package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
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

      /*  if (abstractAnvilForgingRecipe instanceof AnvilToolForgingRecipe anvilToolForgingRecipe) {
            Ingredient main = anvilToolForgingRecipe.getFirst();
            registry.add(new AnvilForgingRecipeDisplay(List.of(EntryIngredients.ofIngredient(main)), List.of(EntryIngredient.of(EntryStacks.of(abstractAnvilForgingRecipe.getResultItem())))));
        } else if (abstractAnvilForgingRecipe instanceof AnvilTwoToolForgingRecipe) {
            Ingredient main = abstractAnvilForgingRecipe.getFirst();
            Ingredient second = abstractAnvilForgingRecipe.getSecond();

            registry.add(new AnvilForgingRecipeDisplay(List.of(EntryIngredients.ofIngredient(main), EntryIngredients.ofIngredient(second)),
                    List.of(EntryIngredient.of(EntryStacks.of(abstractAnvilForgingRecipe.getResultItem())))));


        } else if (abstractAnvilForgingRecipe instanceof AnvilArmorForgingRecipe) {
            Ingredient main = abstractAnvilForgingRecipe.getFirst();
            Ingredient second = abstractAnvilForgingRecipe.getSecond();
            ItemStack result = abstractAnvilForgingRecipe.getResultItem();
            return new AnvilForgingRecipeDisplay(EntryIngredients.ofIngredients(List.of(main, second)), List.of(EntryIngredients.of(result)));
        }*/

        return new AnvilForgingRecipeDisplay(EntryIngredients.ofIngredients(ingredients), List.of(EntryIngredients.of(abstractAnvilForgingRecipe.getResultItem())));
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
