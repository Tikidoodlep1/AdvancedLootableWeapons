package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.recipes.*;

import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilArmorForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilMaterialForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilToolForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.anvilforging.AnvilTwoToolForgingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AdvancedLootableWeapons.MODID);
	
	public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOY_FURNACE = RECIPE_SERIALIZERS.register("alloy_furnace",
			AlloyFurnaceRecipe.Serializer::new);
	
	public static final RegistryObject<RecipeSerializer<AnvilMaterialForgingRecipe>> ANVIL_MATERIAL_FORGING = RECIPE_SERIALIZERS.register("anvil_material_forging",
			() -> new AbstractAnvilForgingRecipe.Serializer<>(AnvilMaterialForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<AnvilToolForgingRecipe>> ANVIL_TOOL_FORGING = RECIPE_SERIALIZERS.register("anvil_tool_forging",
			() -> new AbstractAnvilForgingRecipe.Serializer<>(AnvilToolForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<AnvilArmorForgingRecipe>> ANVIL_ARMOR_FORGING = RECIPE_SERIALIZERS.register("anvil_armor_forging",
			() -> new AbstractAnvilForgingRecipe.Serializer<>(AnvilArmorForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<AnvilTwoToolForgingRecipe>> ANVIL_TWO_TOOL_FORGING = RECIPE_SERIALIZERS.register("anvil_two_tool_forging",
			() -> new AbstractAnvilForgingRecipe.Serializer<>(AnvilTwoToolForgingRecipe::new));

	public static final RegistryObject<RecipeSerializer<JawCrusherRecipe>> JAW_CRUSHER = RECIPE_SERIALIZERS.register("crushing",
			JawCrusherRecipe.Serializer::new);

	public static final RegistryObject<RecipeSerializer<DrumRecipe>> DRUM = RECIPE_SERIALIZERS.register("drum",
			DrumRecipe.Serializer::new);

	public static final RegistryObject<RecipeSerializer<DrumQuenchingRecipe>> DRUM_QUENCHING = RECIPE_SERIALIZERS.register("drum_quenching",
			DrumQuenchingRecipe.Serializer::new);

	public static final RegistryObject<ThreeMatchingItemsRecipe.CustomSerializer> CHAIN_LINK = RECIPE_SERIALIZERS.register("chain_link",
            ThreeMatchingItemsRecipe.CustomSerializer::new);

	public static final RegistryObject<RecipeSerializer<TanningRackRecipe>> TANNING_RACK = RECIPE_SERIALIZERS.register("tanning_rack",
			TanningRackRecipe.Serializer::new);


	public static void register(IEventBus bus) {
		RECIPE_SERIALIZERS.register(bus);
	}
}
