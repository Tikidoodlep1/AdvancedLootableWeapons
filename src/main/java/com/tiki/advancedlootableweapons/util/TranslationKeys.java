package com.tiki.advancedlootableweapons.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.text.DecimalFormat;

public class TranslationKeys {
    public static final MutableComponent ANVIL_FORGING_CAT = createREICategory("anvil_forging");
    public static final MutableComponent SEQUENCED_ANVIL_FORGING_CAT = createREICategory("sequenced_anvil_forging");
    public static final MutableComponent TANNING_RACK_CAT = createREICategory("tanning_rack");
    public static final MutableComponent ALLOY_FURNACE_CAT = createREICategory("alloy_furnace");
    public static final MutableComponent JAW_CRUSHER_CAT = createREICategory("jaw_crusher");
    public static final MutableComponent DRUM_CAT = createREICategory("drum");
    public static final MutableComponent DRUM_QUENCHING_CAT = createREICategory("drum_quenching");

    public static final MutableComponent NONE = MCVersion.translation("advancedlootableweapons.none");
    public static final String REFINED_C = "tooltip.advancedlootableweapons.refined";
    public static final MutableComponent QUENCH_KEY = MCVersion.translation("advancedlootableweapons.tool_head.quenched").withStyle(ChatFormatting.BLUE);
    public static final MutableComponent UNQUENCH_KEY = MCVersion.translation("advancedlootableweapons.tool_head.unquenched").withStyle(ChatFormatting.RED);
    public static final MutableComponent FORGING_QUALITY = MCVersion.translation("advancedlootableweapons.tool_head.forging_quality").withStyle(ChatFormatting.BLUE);
    public static final MutableComponent PLATE_PEN_CHANCE = MCVersion.translation("advancedlootableweapons.weapon.plate_penetration_chance").withStyle(ChatFormatting.DARK_BLUE);
    public static final MutableComponent CHAIN_PEN_CHANCE = MCVersion.translation("advancedlootableweapons.weapon.chain_penetration_chance").withStyle(ChatFormatting.DARK_GREEN);
    public static final MutableComponent STUDDED_PEN_CHANCE = MCVersion.translation("advancedlootableweapons.weapon.studded_penetration_chance").withStyle(ChatFormatting.DARK_RED);

    static DecimalFormat df = new DecimalFormat("###.##");

    public static MutableComponent createTime(int ticks) {
         return MCVersion.translation("category.rei.cooking.time", df.format(ticks/ 20d));
    }

    public static MutableComponent createXp(int xp) {
        return MCVersion.translation("category.rei.xp", xp);
    }

    public static MutableComponent createPlatePenChance(int chance) {
        return MCVersion.empty().append(PLATE_PEN_CHANCE).append(chance+"%");
    }

    public static MutableComponent createChainPenChance(int chance) {
        return MCVersion.empty().append(CHAIN_PEN_CHANCE).append(chance+"%");
    }

    public static MutableComponent createStuddedPenChance(int chance) {
        return MCVersion.empty().append(STUDDED_PEN_CHANCE).append(chance+"%");
    }

    static MutableComponent createREICategory(String key) {
        return MCVersion.translation("category.rei.advancedlootableweapons."+key);
    }
}
