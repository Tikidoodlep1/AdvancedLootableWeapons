package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.enchantments.EnchantmentRefined;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=ModInfo.ID)
public class EnchantmentInit {

	public static final List<Enchantment> enchantments = new ArrayList<Enchantment>();
	
	public static final Enchantment REFINED = new EnchantmentRefined("refined");
	
	/*
	@SubscribeEvent
	public static void EnchantmentRefinedFunction(LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		int level = EnchantmentHelper.getMaxEnchantmentLevel(REFINED, living);
		BlockPos pos = living.getPosition();
		World world = event.getEntity().getEntityWorld();
	}
	*/
}
