package com.tiki.advancedlootableweapons.compat.rei;

import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AlloyFurnaceDisplay extends BasicDisplay implements SimpleGridMenuDisplay {


    public final int cookTime;

    public AlloyFurnaceDisplay(AlloyFurnaceRecipe recipe) {
        this(List.of(createStackedIngredient(recipe.getInput1(),recipe.getCount1()),
                        createStackedIngredient(recipe.getInput2(),recipe.getCount2())),
                Collections.singletonList(EntryIngredients.of(recipe.getResultItem())), Optional.ofNullable(recipe).map(AlloyFurnaceRecipe::getId), recipe.getCookTime());
    }

    static EntryIngredient createStackedIngredient(Ingredient ingredient,int count) {
        ItemStack[] matching = ingredient.getItems();
        EntryIngredient.Builder result = EntryIngredient.builder(matching.length);
        for (ItemStack stack : matching) {
            ItemStack copy = stack.copy();
            copy.setCount(count);
            result.add(EntryStacks.of(copy));
        }
        return result.build();
    }

    public AlloyFurnaceDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> recipe, int cookTime) {
        super(inputs, outputs,recipe);
        this.cookTime = cookTime;
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
        return AlloyFurnaceCategory.CATEGORY_IDENTIFIER;
    }

    public static <R extends AlloyFurnaceDisplay> BasicDisplay.Serializer<R> serializer(BasicDisplay.Serializer.RecipeLessConstructor<R> constructor) {
        return BasicDisplay.Serializer.ofRecipeLess(constructor, (display, tag) -> {
            //tag.putFloat("xp", display.getXp());
            tag.putDouble("cookTime", display.cookTime);
        });
    }

}
