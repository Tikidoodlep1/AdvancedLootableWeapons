package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.recipes.*;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {

    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE = register("alloy_furnace");
    public static final RecipeType<JawCrusherRecipe> CRUSHING = register("crushing");
    public static final RecipeType<AbstractAnvilForgeingRecipe> ANVIL_FORGING = register("anvil_forging");
    public static final RecipeType<DrumRecipe> DRUM = register("drum");
    public static final RecipeType<DrumQuenchingRecipe> DRUM_QUENCHING = register("drum_quenching");

    private static <T extends Recipe<?>> RecipeType<T> register(String path) {
        return RecipeType.register(AdvancedLootableWeapons.MODID+":"+path);
    }

    public static void poke() {

    }

}
