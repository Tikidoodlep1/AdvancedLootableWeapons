package com.tiki.advancedlootableweapons;

import java.util.UUID;

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

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VER)
public class Alw {
	@Instance
	public static Alw instance;
	
	public static final IAttribute ATTACK_RANGE = new RangedAttribute((IAttribute)null, "generic.reachDistance", 5.0D, 0.0D, 1024.0D).setDescription("Attack Range");
	public static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("972be6b4-924d-445f-afae-941b737f1b84");
	
	@SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
	
	
}
