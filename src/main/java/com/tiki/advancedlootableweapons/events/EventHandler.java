package com.tiki.advancedlootableweapons.events;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import com.tiki.advancedlootableweapons.handlers.GlobalDropHandler;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import com.tiki.advancedlootableweapons.recipes.AnvilForgingRecipe;

@Mod.EventBusSubscriber(modid = AdvancedLootableWeapons.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
	
	@SubscribeEvent
	public static void registerLootSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();
		GlobalDropHandler.initDropList();
		for(GlobalLootModifierSerializer<?> s : GlobalDropHandler.dropList) {
			registry.register(s);
		}
	}
	
	@SubscribeEvent
	public static void registerEntityAttributeModifiers(final EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
		event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
	}
	
	@SubscribeEvent
	public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
		Registry.register(Registry.RECIPE_TYPE, AlloyFurnaceRecipe.Type.ID, AlloyFurnaceRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, AnvilForgingRecipe.Type.ID, AnvilForgingRecipe.Type.INSTANCE);
	}
}
