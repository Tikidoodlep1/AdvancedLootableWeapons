package com.tiki.advancedlootableweapons.loot_modifiers;

import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

public class ShadowModifier extends LootModifier {

	public final Item prop1;
	public final LootItemCondition[] conditions;
	
	protected ShadowModifier(LootItemCondition[] conditionsIn, Item prop1) {
		super(conditionsIn);
		this.prop1 = prop1;
		this.conditions = conditionsIn;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		Random rand = context.getRandom();		
		if(rand.nextInt(100) + 1 <= CommonConfigHandler.SHADOW_DROP_RATE.get()) {
			generatedLoot.add(new ItemStack(prop1));
		}
		return generatedLoot;
	}
}
