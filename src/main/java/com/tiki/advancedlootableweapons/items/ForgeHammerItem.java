package com.tiki.advancedlootableweapons.items;

import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ForgeHammerItem extends TieredItem {

    public ForgeHammerItem(Tier tier, Properties pProperties) {
        super(tier, pProperties);
        pProperties.defaultDurability(tier.getUses() / 5);
    }

    public static final String INFO = "advancedlootableweapons.forge_hammer.info";

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(MCVersion.translation(INFO).withStyle(ChatFormatting.BLUE));
    }
}
