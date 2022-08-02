package com.tiki.advancedlootableweapons.handlers.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfigHandler {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	static {
		BUILDER.push("Advanced Lootable Weapons Config");
		
		//Client Config Stuff
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
