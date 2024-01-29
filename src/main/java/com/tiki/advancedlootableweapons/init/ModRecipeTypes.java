package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import com.tiki.advancedlootableweapons.recipes.AnvilForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.DrumRecipe;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {

    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE = register("alloy_furnace");
    public static final RecipeType<JawCrusherRecipe> CRUSHING = register("crushing");
    public static final RecipeType<AnvilForgingRecipe> ANVIL_FORGING = register("anvil_forging");
    public static final RecipeType<DrumRecipe> DRUM = register("drum");
    private static <T extends Recipe<?>> RecipeType<T> register(String path) {
        return RecipeType.register(AdvancedLootableWeapons.MODID+":"+path);
    }

    public static void poke() {

    }

}
