package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

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
	
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		ItemStack crafted = event.crafting;
		//									LONG TOOL ROD CRAFTING
		//---------------------------------------------------------------------------------------------
		if(crafted.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))) {
			IInventory craftingGrid = event.craftMatrix;
			NBTTagCompound tag = new NBTTagCompound();
			double temp1[] = {-1, -1};
			int temp2[] = {-1, -1};
			for(int i = 0; i < craftingGrid.getSizeInventory(); i++) {
				if(craftingGrid.getStackInSlot(i).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2))){
					ItemStack stack = craftingGrid.getStackInSlot(i);
					tag = stack.getTagCompound();
					if(temp1[0] == -1) {
						temp1[0] = tag.getDouble("reducedDamage");
					}else {
						temp1[1] = tag.getDouble("reducedDamage");
					}
					if(temp2[0] == -1) {
						temp2[0] = tag.getInteger("reducedDurability");
					}else {
						temp2[1] = tag.getInteger("reducedDurability");
					}
				}
			}
			tag.setDouble("reducedDamage", (temp1[0] + temp1[1]));
			tag.setDouble("reducedDurability", (temp2[0] + temp2[1]));
			crafted.setTagCompound(tag);
		}
		//---------------------------------------------------------------------------------------------
	}
}
