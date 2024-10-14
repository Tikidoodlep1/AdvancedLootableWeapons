package com.tiki.advancedlootableweapons.loot_modifiers;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

public class ShadowLootModifier extends LootModifier {

	public final Item prop1;
	public final LootItemCondition[] conditions;
	
	protected ShadowLootModifier(LootItemCondition[] conditionsIn, Item prop1) {
		super(conditionsIn);
		this.prop1 = prop1;
		this.conditions = conditionsIn;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		Random rand = context.getRandom();
		int chance = CommonConfigHandler.SHADOW_DROP_RATE.get();
		if(rand.nextInt(100) + 1 <= chance) {
			generatedLoot.add(new ItemStack(prop1));
		}
		return generatedLoot;
	}
}
