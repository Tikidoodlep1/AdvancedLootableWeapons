package com.tiki.advancedlootableweapons.packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSendJsonModelReferenceToServer implements IMessage {
	
	private static final Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
	JsonObject model;
	short type;
	
	public PacketSendJsonModelReferenceToServer() {}
	
	public PacketSendJsonModelReferenceToServer(JsonObject model, short type) {
		this.model = model;
		this.type = type;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		//this.buttonID = ByteBufUtils.readVarInt(buf, 4);
		this.model = gson.fromJson(ByteBufUtils.readUTF8String(buf), JsonObject.class);
		this.type = (short)ByteBufUtils.readVarShort(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		//ByteBufUtils.writeVarInt(buf, this.buttonID, 4);
		ByteBufUtils.writeUTF8String(buf, gson.toJson(model));
		ByteBufUtils.writeVarShort(buf, type);
	}
	
	public static class Handler implements IMessageHandler<PacketSendJsonModelReferenceToServer, IMessage> {
		
		@Override
		public IMessage onMessage(PacketSendJsonModelReferenceToServer message, MessageContext ctx) {
			if(ctx.side == Side.SERVER) {
				switch(message.type) {
					case ZenDynamicAlwResources.FORGE_MODEL_TYPE:
						ZenDynamicAlwResources.FORGE_MODEL = message.model;
						break;
					case ZenDynamicAlwResources.ADVANCED_FORGE_MODEL_TYPE:
						ZenDynamicAlwResources.ADVANCED_FORGE_MODEL = message.model;
				}
			}
			return null;
		}
		
	}
}
