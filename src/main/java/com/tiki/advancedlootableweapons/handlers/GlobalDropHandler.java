package com.tiki.advancedlootableweapons.handlers;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.loot_modifiers.HideModifierSerializer;
import com.tiki.advancedlootableweapons.loot_modifiers.ShadowModifierSerializer;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class GlobalDropHandler {

	public static final List<GlobalLootModifierSerializer<?>> dropList = new ArrayList<>();
	
	public static void initDropList() {
		dropList.add(new ShadowModifierSerializer().setRegistryName(AdvancedLootableWeapons.id( "shadow_mod_all")));
		dropList.add(new HideModifierSerializer().setRegistryName(AdvancedLootableWeapons.id( "hide_replacement")));
	}
}
