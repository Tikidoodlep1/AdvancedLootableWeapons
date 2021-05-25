package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
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
				
				event.setCanceled(true);
			}
		}
	}
	
}
