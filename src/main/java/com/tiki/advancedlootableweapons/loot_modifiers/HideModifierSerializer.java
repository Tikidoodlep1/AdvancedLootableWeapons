package com.tiki.advancedlootableweapons.loot_modifiers;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class HideModifierSerializer extends GlobalLootModifierSerializer<HideModifier> {

	@Override
	public HideModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
		Item prop1 = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "prop1")));
		return new HideModifier(conditions, prop1);
	}

	@Override
	public JsonObject write(HideModifier instance) {
		JsonObject res = this.makeConditions(instance.conditions);
		res.addProperty("prop1", ForgeRegistries.ITEMS.getKey(instance.prop1).toString());
		return null;
	}

}
