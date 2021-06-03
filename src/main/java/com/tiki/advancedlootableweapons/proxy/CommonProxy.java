package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.inventory.ContainerForgeWeapon;
import com.tiki.advancedlootableweapons.inventory.GuiForgeWeapon;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void registerModel(Item item, int metadata) {}
	
	public void onBlockAttemptBreak(LeftClickBlock event){
		BlockPos blockPos = event.getPos();
		Block block = event.getWorld().getBlockState(blockPos).getBlock();
		if(block == Blocks.ANVIL) {
			EntityPlayer player = event.getEntityPlayer();
			if(player.inventory.getCurrentItem().getItem() instanceof ToolForgeHammer) {
				player.openGui(Alw.instance, ModInfo.GUI_FORGE_WEAPON, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
				event.setCanceled(true);
			}
		}
	}
	
	public void onButtonPressed(GuiScreenEvent.ActionPerformedEvent.Post event) {
		int buttonID = event.getButton().id;
		System.out.print("BUTTON ID IS " + buttonID);
		if(event.getGui() instanceof GuiForgeWeapon && Minecraft.getMinecraft().player.openContainer instanceof ContainerForgeWeapon) {
			System.out.print("GUI IS GuiForgeWeapon AND CONTAINER IS ContainerForgeWeapon");
			ContainerForgeWeapon container = (ContainerForgeWeapon)Minecraft.getMinecraft().player.openContainer;
			GuiForgeWeapon gui = (GuiForgeWeapon)event.getGui();
			container.buttonPressed = gui.getButtonPressed();
			container.changeItem();
		}
	}
}