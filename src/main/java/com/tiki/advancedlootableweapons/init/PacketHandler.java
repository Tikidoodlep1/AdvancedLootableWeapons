package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.packet.PacketForgeWeaponButtonPress;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID);
	
	public static void init() {
		registerServerBoundMessage(PacketForgeWeaponButtonPress.Handler.class, PacketForgeWeaponButtonPress.class);
	}
	
	private static int packetID = 0;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void registerServerBoundMessage(Class packet, Class message) {
		INSTANCE.registerMessage(packet, message, packetID++, Side.SERVER);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void registerClientBoundMessage(Class packet, Class message) {
		INSTANCE.registerMessage(packet, message, packetID++, Side.CLIENT);
	}
}
