package com.tiki.advancedlootableweapons.tags;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {

    public static final TagKey<Block> DRUM_HEATING = create(AdvancedLootableWeapons.id("drum_heating"));

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, name);
    }

}
