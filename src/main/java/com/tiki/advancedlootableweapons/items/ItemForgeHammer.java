package com.tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.inventory.anvil_forging.AnvilForgingContainer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;

public class ItemForgeHammer extends TieredItem {

	public ItemForgeHammer(Tier tier, Properties pProperties) {
		super(tier, pProperties);
		pProperties.defaultDurability(tier.getUses()/5);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		if(player.getLevel().getBlockState(pos).getBlock() instanceof AnvilBlock && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ItemForgeHammer) {
			if (player.getLevel().isClientSide) {
		         return true;
		      } else {
		         player.openMenu(getMenuProvider(player.getLevel(), pos));
		         return true;
		      }
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	@Nullable
	public MenuProvider getMenuProvider(Level pLevel, BlockPos pPos) {
	   return new SimpleMenuProvider((int pContainerId, Inventory pPlayerInventory, Player pPlayer) -> {
	      return new AnvilForgingContainer(pContainerId, pPlayerInventory, pPos);
	   }, new TextComponent("Anvil Forging"));
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(new TextComponent(ChatFormatting.BLUE + "Hit an anvil with me to start forging weapons!"));
	}
}