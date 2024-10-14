package com.tiki.advancedlootableweapons.loot_modifiers;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
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
		if (!CommonConfigHandler.HIDE_REPLACEMENT.get()) return generatedLoot;
		if (!context.getQueriedLootTableId().getPath().startsWith("entities/")) return generatedLoot;
		int looting = context.getLootingModifier();
		int leatherCount = 0;
        for (Iterator<ItemStack> iterator = generatedLoot.iterator(); iterator.hasNext(); ) {
            ItemStack stack = iterator.next();
            if (stack.is(ModItemTags.HIDES)) {
                leatherCount += stack.getCount();
                iterator.remove();
            }
        }
		if (leatherCount > 0) {
			generatedLoot.add(new ItemStack(prop1, (int) (leatherCount * 1.25) + (looting + 1)));
		}
		return generatedLoot;
	}
}
