package tiki.advancedlootableweapons.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;

public class PacketForgeWeaponButtonPress implements IMessage {
	
	int buttonID;
	
	public PacketForgeWeaponButtonPress() {}
	
	public PacketForgeWeaponButtonPress(int buttonID) {
		this.buttonID = buttonID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.buttonID = ByteBufUtils.readVarInt(buf, 4);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, this.buttonID, 4);
	}
	
	public static class Handler implements IMessageHandler<PacketForgeWeaponButtonPress, IMessage> {
		
		@Override
		public IMessage onMessage(PacketForgeWeaponButtonPress message, MessageContext ctx) {
			if(ctx.side == Side.SERVER) {
				EntityPlayerMP player = ctx.getServerHandler().player;
				Container c = player.openContainer;
				if(c instanceof ContainerForgeWeapon) {
					((ContainerForgeWeapon)c).enchantItem(player, message.buttonID);
				}
			}
			return null;
		}
		
	}
}
