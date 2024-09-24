package com.tiki.advancedlootableweapons.world;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacements {

    //similar to gold
    public static final Holder<PlacedFeature> ORE_SILVER_EXTRA = PlacementUtils.register(AdvancedLootableWeapons.id("ore_silver_extra").toString(),
            ModOreFeatures.ORE_SILVER, commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(256))));

    public static final Holder<PlacedFeature> ORE_PLATINUM_EXTRA = PlacementUtils.register(AdvancedLootableWeapons.id("ore_platinum_extra").toString(),
            ModOreFeatures.ORE_PLATINUM, commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(256))));

    public static final Holder<PlacedFeature> ORE_CRYSTALLITE_EXTRA = PlacementUtils.register(AdvancedLootableWeapons.id("ore_crystallite_extra").toString(),
            ModOreFeatures.ORE_CRYSTALLITE, commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(256))));

    //similar to copper
    public static final Holder<PlacedFeature> ORE_TIN = PlacementUtils.register(AdvancedLootableWeapons.id("ore_tin").toString(),
    ModOreFeatures.ORE_TIN_SMALL, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));



    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
