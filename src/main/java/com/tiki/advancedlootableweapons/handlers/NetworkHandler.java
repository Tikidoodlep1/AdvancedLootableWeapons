package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.proxy.AbstractPacket;
import com.tiki.advancedlootableweapons.proxy.ForgeWeaponButtonPacket;
import com.tiki.advancedlootableweapons.proxy.NetworkWrapper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class NetworkHandler extends NetworkWrapper{
	
	public static NetworkHandler instance = new NetworkHandler();
	
	public NetworkHandler() {
		super("ALW");
	}

	public void registerPackets() {
		registerPacket(ForgeWeaponButtonPacket.class);
	}
	
	public static void sendPacket(Entity player, Packet<?> packet) {
		if(player instanceof EntityPlayerMP && ((EntityPlayerMP) player).connection != null) {
			((EntityPlayerMP) player).connection.sendPacket(packet);
		}
	}
	
	public static void sendToAll(AbstractPacket packet) {
		instance.wrapper.sendToAll(packet);
	}
	
	public static void sendTo(AbstractPacket packet, EntityPlayerMP player) {
		instance.wrapper.sendTo(packet, player);
	}
	
	public static void sendToAllAround(AbstractPacket packet, NetworkRegistry.TargetPoint point) {
		instance.wrapper.sendToAllAround(packet, point);
	}
	
	public static void sendToDimension(AbstractPacket packet, int dimensionId) {
	    instance.wrapper.sendToDimension(packet, dimensionId);
	  }

	 public static void sendToServer(AbstractPacket packet) {
	   instance.wrapper.sendToServer(packet);
	 }
	 
	 public static void sendToClients(WorldServer world, BlockPos pos, AbstractPacket packet) {
		 Chunk chunk = world.getChunkFromBlockCoords(pos);
		 for(EntityPlayer player: world.playerEntities) {
			 if(!(player instanceof EntityPlayerMP)) {
				 continue;
			 }
			 EntityPlayerMP playerMP = (EntityPlayerMP) player;
			 if(world.getPlayerChunkMap().isPlayerWatchingChunk(playerMP, chunk.x, chunk.z)) {
				 NetworkHandler.sendTo(packet, playerMP);
			 }
		 }
	 }
}
