package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModInfo.ID);
	
	public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOY_FURNACE_RECIPE = RECIPE_SERIALIZERS.register(AlloyFurnaceRecipe.Type.ID, 
			() -> AlloyFurnaceRecipe.Serializer.INSTANCE);
	
	public static void register(IEventBus bus) {
		RECIPE_SERIALIZERS.register(bus);
	}
}
