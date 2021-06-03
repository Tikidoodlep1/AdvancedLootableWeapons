package com.tiki.advancedlootableweapons.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkWrapper {
	public final SimpleNetworkWrapper wrapper;
	protected final AbstractPacketHandler handler;
	private int id = 0;
	
	public NetworkWrapper(String channel) {
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(channel);
		handler = new AbstractPacketHandler();
	}
	
	public void registerPacket(Class<? extends AbstractPacket> packetClazz) {
		registerPacketClient(packetClazz);
		registerPacketServer(packetClazz);
	}
	
	public void registerPacketClient(Class<? extends AbstractPacket> packetClazz) {
		registerPacketImpl(packetClazz, Side.CLIENT);
	}
	
	public void registerPacketServer(Class<? extends AbstractPacket> packetClazz) {
		registerPacketImpl(packetClazz, Side.SERVER);
	}
	
	public void registerPacketImpl(Class<? extends AbstractPacket> packetClazz, Side side) {
		wrapper.registerMessage(handler, packetClazz, id++, side);
	}
	
	public static class AbstractPacketHandler implements IMessageHandler<AbstractPacket, IMessage>{
		
		@Override
		public IMessage onMessage(AbstractPacket packet, MessageContext context) {
			if(context.side == Side.SERVER) {
				return packet.handleServer(context.getServerHandler());
			}else {
				return packet.handleClient(context.getClientHandler());
			}
		}
	}
	
}
