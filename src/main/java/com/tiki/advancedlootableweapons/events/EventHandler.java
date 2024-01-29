package com.tiki.advancedlootableweapons.events;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.GlobalDropHandler;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = AdvancedLootableWeapons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
	
	@SubscribeEvent
	public static void registerLootSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();
		GlobalDropHandler.initDropList();
		for(GlobalLootModifierSerializer<?> s : GlobalDropHandler.dropList) {
			registry.register(s);
		}
		ModRecipeTypes.poke();
	}
	
	@SubscribeEvent
	public static void registerEntityAttributeModifiers(final EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
		event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
	}
}
