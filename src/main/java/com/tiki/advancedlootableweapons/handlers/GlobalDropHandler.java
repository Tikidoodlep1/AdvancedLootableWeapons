package com.tiki.advancedlootableweapons.handlers;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.loot_modifiers.ShadowModifierSerializer;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class GlobalDropHandler {

	public static final List<GlobalLootModifierSerializer<?>> dropList = new ArrayList<GlobalLootModifierSerializer<?>>();
	
	public static void initDropList() {
		dropList.add(new ShadowModifierSerializer().setRegistryName(new ResourceLocation(AdvancedLootableWeapons.MODID, "shadow_mod_all")));
	}
}
