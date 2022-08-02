package com.tiki.advancedlootableweapons.handlers;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import com.tiki.advancedlootableweapons.ModInfo;

@Mod.EventBusSubscriber(modid = ModInfo.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
}
