package com.tiki.advancedlootableweapons.items.armor;

import com.tiki.advancedlootableweapons.util.MCVersion;
import com.tiki.advancedlootableweapons.util.TranslationKeys;
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

    public static final String ADDED_DURABILITY = "addedDurability";

    public ArmorPlateItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(stack, pLevel, tooltip, pIsAdvanced);
        CompoundTag nbt = stack.getTag();

        if (stack.hasTag() && nbt.contains(ADDED_DURABILITY)) {
            tooltip.add(TranslationKeys.FORGING_QUALITY);
            tooltip.add(MCVersion.literal("--------------------").withStyle(ChatFormatting.GRAY));
            tooltip.add(MCVersion.literal("+" + nbt.getInt(ADDED_DURABILITY) / 4).withStyle(ChatFormatting.BLUE)
                    .append(MCVersion.literal("alw.dur_tooltip.name")));
            tooltip.add(MCVersion.literal("--------------------").withStyle(ChatFormatting.GRAY));
        }
    }
}
