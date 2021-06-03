package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.handlers.NetworkHandler;
import com.tiki.advancedlootableweapons.inventory.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.GuiForgeWeapon;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.WorldServer;

public class ForgeWeaponButtonPacket extends AbstractPacketThreadsafe{
	public int button;
	
	public ForgeWeaponButtonPacket() {
	}
	
	public ForgeWeaponButtonPacket(int button) {
		this.button = button;
	}

	@Override
	public void handleClientSafe(NetHandlerPlayClient netHandler) {
		Container container = Minecraft.getMinecraft().player.openContainer;
		if(container instanceof ContainerForgeWeapon) {
			((ContainerForgeWeapon) container).buttonPressed = button;
			((ContainerForgeWeapon) container).changeItem();
			if(Minecraft.getMinecraft().currentScreen instanceof GuiForgeWeapon) {
				((GuiForgeWeapon) Minecraft.getMinecraft().currentScreen).setButtonPressed(button);
			}
		}
	}

	@Override
	public void handleServerSafe(NetHandlerPlayServer netHandler) {
		Container container = netHandler.player.openContainer;
		if(container instanceof ContainerForgeWeapon) {
			((ContainerForgeWeapon) container).buttonPressed = button;
			((ContainerForgeWeapon) container).changeItem();
			
			WorldServer server = netHandler.player.getServerWorld();
			for(EntityPlayer player: server.playerEntities) {
				if(player == netHandler.player) {
					continue;
				}
				if(player.openContainer instanceof ContainerForgeWeapon) {
					if(container == player.openContainer) {
						((ContainerForgeWeapon) container).buttonPressed = button;
						
						NetworkHandler.sendTo(this, (EntityPlayerMP) player);
					}
				}
			}
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		button = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(button);
	}
}
