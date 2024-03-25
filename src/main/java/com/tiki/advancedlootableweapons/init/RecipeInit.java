package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.recipes.*;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AdvancedLootableWeapons.MODID);
	
	public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOY_FURNACE_RECIPE = RECIPE_SERIALIZERS.register("alloy_furnace",
			AlloyFurnaceRecipe.Serializer::new);
	
	public static final RegistryObject<RecipeSerializer<AnvilMaterialForgingRecipe>> ANVIL_MATERIAL_FORGING_RECIPE = RECIPE_SERIALIZERS.register("anvil_material_forging",
			() -> new AbstractAnvilForgeingRecipe.Serializer<>(AnvilMaterialForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<AnvilToolForgingRecipe>> ANVIL_TOOL_FORGING_RECIPE = RECIPE_SERIALIZERS.register("anvil_tool_forging",
			() -> new AbstractAnvilForgeingRecipe.Serializer<>(AnvilToolForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<JawCrusherRecipe>> JAW_CRUSHER_RECIPE = RECIPE_SERIALIZERS.register("crushing",
			JawCrusherRecipe.Serializer::new);

	public static final RegistryObject<RecipeSerializer<DrumRecipe>> DRUM_RECIPE = RECIPE_SERIALIZERS.register("drum",
			DrumRecipe.Serializer::new);

	public static final RegistryObject<RecipeSerializer<DrumQuenchingRecipe>> DRUM_QUENCHING_RECIPE = RECIPE_SERIALIZERS.register("drum_quenching",
			DrumQuenchingRecipe.Serializer::new);
	
	public static void register(IEventBus bus) {
		RECIPE_SERIALIZERS.register(bus);
	}
}
