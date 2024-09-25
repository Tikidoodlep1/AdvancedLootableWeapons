package com.tiki.advancedlootableweapons.world;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ModOreFeatures {
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SILVER = FeatureUtils.register(AdvancedLootableWeapons.id("ore_silver").toString(),
            Feature.ORE, new OreConfiguration(Worldgen.ORE_SILVER_TARGET_LIST, 9));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_TIN_SMALL = FeatureUtils.register(AdvancedLootableWeapons.id("ore_tin_small").toString(),
            Feature.ORE, new OreConfiguration(Worldgen.ORE_TIN_TARGET_LIST, 10));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_PLATINUM = FeatureUtils.register(AdvancedLootableWeapons.id("ore_platinum").toString(),
            Feature.ORE, new OreConfiguration(Worldgen.ORE_PLATINUM_TARGET_LIST, 9));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_CRYSTALLITE = FeatureUtils.register(AdvancedLootableWeapons.id("ore_crystallite").toString(),
            Feature.ORE, new OreConfiguration(Worldgen.ORE_CRYSTALLITE_TARGET_LIST, 9));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_GYPSUM = FeatureUtils.register(AdvancedLootableWeapons.id("ore_gypsum").toString(), Feature.ORE,
            new OreConfiguration(OreFeatures.NATURAL_STONE, BlockInit.GYPSUM.get().defaultBlockState(), 33));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_FELDSPAR = FeatureUtils.register(AdvancedLootableWeapons.id("ore_feldspar").toString(), Feature.ORE,
            new OreConfiguration(OreFeatures.NATURAL_STONE, BlockInit.FELDSPAR.get().defaultBlockState(), 33));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DOLOMITE = FeatureUtils.register(AdvancedLootableWeapons.id("ore_dolomite").toString(), Feature.ORE,
            new OreConfiguration(OreFeatures.NATURAL_STONE, BlockInit.DOLOMITE.get().defaultBlockState(), 33));

}
