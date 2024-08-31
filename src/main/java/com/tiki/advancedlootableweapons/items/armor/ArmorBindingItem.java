package com.tiki.advancedlootableweapons.items.armor;


import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorBindingItem extends Item {

	private final int extraDur;

	public ArmorBindingItem(Properties properties, ArmorMaterial extraDur) {
		super(properties);
		this.extraDur = extraDur.getDurabilityForSlot(EquipmentSlot.CHEST)/4;
	}

	public int getExtraDur() {
		return this.extraDur;
	}

	public static final String INFO = "advancedlootableweapons.armor_binding.info";

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
		tooltip.add(MCVersion.empty().append(MCVersion.translation(INFO).withStyle(ChatFormatting.BLUE))
				.append(MCVersion.literal( " " + this.extraDur)));
	}
}
