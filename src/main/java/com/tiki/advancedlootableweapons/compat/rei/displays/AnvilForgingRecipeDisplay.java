package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilArmorForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilToolForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilTwoToolForgingRecipe;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class AnvilForgingRecipeDisplay extends BasicDisplay implements SimpleGridMenuDisplay {
    public AnvilForgingRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public static AnvilForgingRecipeDisplay create(AbstractAnvilForgingRecipe abstractAnvilForgingRecipe, DisplayRegistry registry) {
        List<Ingredient> ingredients = abstractAnvilForgingRecipe.getIngredients();

        if (abstractAnvilForgingRecipe instanceof AnvilToolForgingRecipe anvilToolForgingRecipe) {
            Ingredient main = anvilToolForgingRecipe.getFirst();
            ItemStack stack = main.getItems()[0];

            for (WeaponMaterial weaponMaterial : WeaponMaterial.LOOKUP) {
                ItemStack copy = new ItemStack(stack.getItem());
                HeatableToolPartItem.setCraftingMaterial(copy,weaponMaterial.defaultItem().get());
                ItemStack result = anvilToolForgingRecipe.getProcessedResult(copy);
                registry.add(new AnvilForgingRecipeDisplay(List.of(EntryIngredient.of(EntryStacks.of(copy))), List.of(EntryIngredient.of(EntryStacks.of(result)))));
            }
        } else if (abstractAnvilForgingRecipe instanceof AnvilTwoToolForgingRecipe) {
            Ingredient main = abstractAnvilForgingRecipe.getFirst();
            Ingredient second = abstractAnvilForgingRecipe.getSecond();
            ItemStack stack = main.getItems()[0];
            ItemStack stack2 = second.getItems()[0];

            for (WeaponMaterial weaponMaterial : WeaponMaterial.LOOKUP) {
                ItemStack copy = new ItemStack(stack.getItem());
                ItemStack copy2 = new ItemStack(stack2.getItem());
                HeatableToolPartItem.setCraftingMaterial(copy,weaponMaterial.defaultItem().get());
                HeatableToolPartItem.setCraftingMaterial(copy2,weaponMaterial.defaultItem().get());
                ItemStack result = abstractAnvilForgingRecipe.getProcessedResult(copy);
                registry.add(new AnvilForgingRecipeDisplay(List.of(EntryIngredient.of(EntryStacks.of(copy)),EntryIngredient.of(EntryStacks.of(copy2))),
                        List.of(EntryIngredient.of(EntryStacks.of(result)))));
            }
        } else if (abstractAnvilForgingRecipe instanceof AnvilArmorForgingRecipe) {
            Ingredient main = abstractAnvilForgingRecipe.getFirst();
            Ingredient second = abstractAnvilForgingRecipe.getSecond();
            ItemStack result = abstractAnvilForgingRecipe.getResultItem();
            return new AnvilForgingRecipeDisplay(EntryIngredients.ofIngredients(List.of(main,second)),List.of(EntryIngredients.of(result)));
        }

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
