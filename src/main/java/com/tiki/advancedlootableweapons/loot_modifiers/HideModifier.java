package com.tiki.advancedlootableweapons.loot_modifiers;

import java.util.List;
import javax.annotation.Nonnull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

public class HideModifier extends LootModifier {

	public final Item prop1;
	public final LootItemCondition[] conditions;
	
	protected HideModifier(LootItemCondition[] conditionsIn, Item prop1) {
		super(conditionsIn);
		this.prop1 = prop1;
		this.conditions = conditionsIn;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		int looting = context.getLootingModifier();
		int leatherCount = 1;
		for(ItemStack stack : generatedLoot) {
			if(stack.getItem() == Items.LEATHER || stack.getItem() == Items.RABBIT_HIDE) {
				leatherCount = stack.getCount();
				generatedLoot.remove(stack);
			}
		}
		generatedLoot.add(new ItemStack(prop1, (int)(leatherCount*1.25) + (looting+1) ));
		return generatedLoot;
	}
}
