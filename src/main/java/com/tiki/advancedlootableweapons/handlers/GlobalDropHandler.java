package com.tiki.advancedlootableweapons.handlers;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.loot_modifiers.ShadowModifierSerializer;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class GlobalDropHandler {

	public static final List<GlobalLootModifierSerializer<?>> dropList = new ArrayList<GlobalLootModifierSerializer<?>>();
	
	public static void initDropList() {
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "axolotl_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "bat_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "bee_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "blaze_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "cat_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "cave_spider_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "chicken_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "cod_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "cow_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "creeper_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "dolphin_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "donkey_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "drowned_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "elder_guardian_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "ender_dragon_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "endermite_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "enderman_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "evoker_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "fox_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "ghast_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "giant_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "glow_squid_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "goat_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "guardian_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "hoglin_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "horse_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "husk_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "illusioner_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "iron_golem_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "llama_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "magma_cube_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "mooshroom_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "mule_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "ocelot_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "panda_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "parrot_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "phantom_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "pig_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "piglin_brute_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "piglin_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "pillager_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "player_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "polar_bear_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "pufferfish_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "rabbit_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "ravager_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "salmon_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "sheep_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "shulker_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "silverfish_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "skeleton_horse_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "skeleton_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "slime_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "snow_golem_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "spider_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "squid_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "stray_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "strider_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "trader_llama_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "tropical_fish_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "turtle_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "vex_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "villager_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "vindicator_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "wandering_trader_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "witch_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "wither_skeleton_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "wolf_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "zoglin_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "zombie_horse_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "zombie_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "zombie_villager_mod")));
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(ModInfo.ID, "zombified_piglin_mod")));
	}
}
