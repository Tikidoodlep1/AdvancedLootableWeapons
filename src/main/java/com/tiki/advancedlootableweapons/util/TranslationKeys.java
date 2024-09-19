package com.tiki.advancedlootableweapons.util;

import net.minecraft.network.chat.MutableComponent;
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

    static DecimalFormat df = new DecimalFormat("###.##");

    public static MutableComponent createTime(int ticks) {
         return MCVersion.translation("category.rei.cooking.time", df.format(ticks/ 20d));
    }

    static MutableComponent createREICategory(String key) {
        return MCVersion.translation("category.rei.advancedlootableweapons."+key);
    }
}
