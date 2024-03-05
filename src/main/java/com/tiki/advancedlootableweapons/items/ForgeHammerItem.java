package com.tiki.advancedlootableweapons.items;

import com.tiki.advancedlootableweapons.inventory.AnvilForgingMenu;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;

import javax.annotation.Nullable;
import java.util.List;

public class ForgeHammerItem extends TieredItem {

	public ForgeHammerItem(Tier tier, Properties pProperties) {
		super(tier, pProperties);
		pProperties.defaultDurability(tier.getUses()/5);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		if(player.getLevel().getBlockState(pos).getBlock() instanceof AnvilBlock && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ForgeHammerItem) {
			if (!player.getLevel().isClientSide) {
				player.openMenu(getMenuProvider(player.getLevel(), pos));
			}
			return true;
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	@Nullable
	public MenuProvider getMenuProvider(Level pLevel, BlockPos pPos) {
	   return new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new
               AnvilForgingMenu(pContainerId, pPlayerInventory, ContainerLevelAccess.create(pLevel,pPos)),
			   MCVersion.translation("container.advancedlootableweapons.anvil_forging"));
	}

	public static final String INFO = "advancedlootableweapons.forge_hammer.info";

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(MCVersion.translation(INFO).withStyle(ChatFormatting.BLUE));
	}
}
