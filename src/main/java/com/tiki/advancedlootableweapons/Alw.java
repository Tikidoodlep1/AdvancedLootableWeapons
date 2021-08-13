package com.tiki.advancedlootableweapons;

import java.io.File;
import java.util.UUID;

import com.tiki.advancedlootableweapons.handlers.RegistryHandler;
import com.tiki.advancedlootableweapons.inventory.AlwCreativeTab;
import com.tiki.advancedlootableweapons.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

//@Mod.EventBusSubscriber
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VER)
public class Alw {
	@Instance
	public static Alw instance;
	
	public static File config;
	
	public static final IAttribute MATERIAL = new RangedAttribute((IAttribute)null, "attribute.toolMaterial", 0.0D, 0.0D, 12.0D).setDescription("Material");
	public static final UUID MATERIAL_MODIFIER = UUID.fromString("ac55927e-2f67-4b25-a284-a4c26f42d9aa");
	public static final IAttribute ATTACK_RANGE = new RangedAttribute((IAttribute)null, "generic.reachDistance", 5.0D, 0.0D, 1024.0D).setDescription("Attack Range");
	public static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("972be6b4-924d-445f-afae-941b737f1b84");
	public static final IAttribute BONUS_ATTACK_DAMAGE = new RangedAttribute((IAttribute)null, "generic.bonusAttackDamage", 0.0D, 0.0D, 2048.0D).setDescription("Bonus Attack Damage").setShouldWatch(true);
	public static final UUID BONUS_ATTACK_DAMAGE_MODIFIER = UUID.fromString("6cc22d6f-317d-43c4-9617-732527c08f6a");
	public static final UUID HEAD_MOVEMENT_SPEED_MODIFIER = UUID.fromString("e0d86ef8-95f3-40d6-a641-7505ca4e0af2");
	public static final UUID CHEST_MOVEMENT_SPEED_MODIFIER = UUID.fromString("447d94e0-8018-4fef-acad-1b7a21d58cce");
	public static final UUID LEGS_MOVEMENT_SPEED_MODIFIER = UUID.fromString("54755bda-7eb6-47d6-a9c0-5ad30f80dd30");
	public static final UUID FEET_MOVEMENT_SPEED_MODIFIER = UUID.fromString("c7f7402c-91d2-484b-9c65-8dee46988640");
	public static final UUID HEAD_MAX_HEALTH_MODIFIER = UUID.fromString("11ecb09d-f84f-4940-92d8-4c88b8a48533");
	public static final UUID CHEST_MAX_HEALTH_MODIFIER = UUID.fromString("5787953f-0d88-4eae-908e-4780088c972a");
	public static final UUID LEGS_MAX_HEALTH_MODIFIER = UUID.fromString("9d422672-1e4d-4e57-ae47-a589b72f1a38");
	public static final UUID FEET_MAX_HEALTH_MODIFIER = UUID.fromString("d39be1d1-4e3e-4c25-a135-c53b4a00dbcc");
	
	public static final CreativeTabs AlwTab = new AlwCreativeTab("alwcreativetab");
	
	@SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries(event);
		MinecraftForge.EVENT_BUS.register(instance);
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
	
	@SubscribeEvent
	public void onBlockAttemptBreak(LeftClickBlock event) {
		proxy.onBlockAttemptBreak(event);
	}
	
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event) {
		proxy.onEntityDrops(event);
	}
}
