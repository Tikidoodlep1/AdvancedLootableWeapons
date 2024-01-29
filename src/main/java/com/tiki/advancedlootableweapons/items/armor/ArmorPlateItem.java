package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorPlateItem extends Item {
    public ArmorPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(stack, pLevel, tooltip, pIsAdvanced);
        CompoundTag nbt = stack.getTag();

        if (stack.hasTag() && nbt.contains("addedDurability")) {
            tooltip.add(MCVersion.translation("alw.forging_quality.name").withStyle(ChatFormatting.BLUE));
            tooltip.add(MCVersion.literal("--------------------").withStyle(ChatFormatting.GRAY));
            tooltip.add(MCVersion.literal("+" + nbt.getInt("addedDurability") / 4).withStyle(ChatFormatting.BLUE)
                    .append(MCVersion.literal("alw.dur_tooltip.name")));
            tooltip.add(MCVersion.literal("--------------------").withStyle(ChatFormatting.GRAY));
        }
    }
}
