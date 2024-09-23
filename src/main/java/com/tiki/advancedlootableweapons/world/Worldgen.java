package com.tiki.advancedlootableweapons.world;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;


public class Worldgen {

    public static final List<OreConfiguration.TargetBlockState> ORE_SILVER_TARGET_LIST = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));


    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SILVER = FeatureUtils.register(AdvancedLootableWeapons.id("ore_silver").toDebugFileName(),
            Feature.ORE, new OreConfiguration(ORE_SILVER_TARGET_LIST, 9));



}
