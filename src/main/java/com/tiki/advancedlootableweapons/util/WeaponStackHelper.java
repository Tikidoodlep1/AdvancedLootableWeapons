package com.tiki.advancedlootableweapons.util;

import java.util.Random;

import com.tiki.advancedlootableweapons.init.AttributeModifiers;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;

public class WeaponStackHelper {
	
	private static final String[] randName1 = new String[] {"Repuslor", "Balmung", "Gram", "Arondight", "Caladbolg", "Chandrahas", "Colada", "Mors", "Durendal", "Ecke", "Hauteclere", "Mimung", "Naegling", "Tizona", "Tyrfing", "Zulfiqar"};
	private static final String[] randName2 = new String[] {"Lucent", "Lambent", "Dark", "Dusk", "Aphotic", "Radiant", "Scintillant", "Vacuous", "Nixing", "Abnegating", "Collector of Heads,", "Triumphant"};
	private static final Random rand = new Random();

	public static void addAttackDamage(ItemStack stack, double damageToAdd) {
		stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(AttributeModifiers.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damageToAdd, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
	}
	
	public static void setRandomWeaponName(ItemStack stack, WeaponAttributes attributes) {
		StringBuilder builder = new StringBuilder("");
		if(rand.nextBoolean()) {
			builder.append(randName2[rand.nextInt(12)] + " " + randName1[rand.nextInt(16)]);
		}else {
			builder.append(randName1[rand.nextInt(16)]);
		}
		String tier = "";
		if(stack.getItem() instanceof TieredItem) {
			tier = " " + ((TieredItem)stack.getItem()).getTier().toString() + " ";
		}
		stack.setHoverName(new TextComponent(ChatFormatting.AQUA + builder.toString() + " (" + tier + attributes.getType() + ")"));
	}
}
