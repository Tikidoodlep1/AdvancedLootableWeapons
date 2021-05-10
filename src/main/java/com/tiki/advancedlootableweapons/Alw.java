package com.tiki.advancedlootableweapons;

import java.util.UUID;

import com.tiki.advancedlootableweapons.handlers.RegistryHandler;
import com.tiki.advancedlootableweapons.proxy.CommonProxy;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VER)
public class Alw {
	@Instance
	public static Alw instance;
	
	public static final IAttribute ATTACK_RANGE = new RangedAttribute((IAttribute)null, "generic.reachDistance", 5.0D, 0.0D, 1024.0D).setDescription("Attack Range");
	public static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("972be6b4-924d-445f-afae-941b737f1b84");
	public static final IAttribute BONUS_ATTACK_DAMAGE = new RangedAttribute((IAttribute)null, "generic.bonusAttackDamage", 2.0D, 0.0D, 2048.0D).setDescription("Bonus Attack Damage");
	public static final UUID BONUS_ATTACK_DAMAGE_MODIFIER = UUID.fromString("6cc22d6f-317d-43c4-9617-732527c08f6a");
	public static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("e0d86ef8-95f3-40d6-a641-7505ca4e0af2");
	public static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("11ecb09d-f84f-4940-92d8-4c88b8a48533");
	
	@SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries(event);
	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
	
	
}
