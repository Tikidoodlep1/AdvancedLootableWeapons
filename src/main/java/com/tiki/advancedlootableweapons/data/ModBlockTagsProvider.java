package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockInit.ALLOY_FURNACE.get(),
                        BlockInit.BRONZE_BLOCK.get(),BlockInit.COBBLED_FELDSPAR.get(),BlockInit.CRYSTALLITE_BLOCK.get(),
                        BlockInit.CRYSTALLITE_ORE.get(),BlockInit.DEEPSLATE_CRYSTALLITE_ORE.get(),
                        BlockInit.DEEPSLATE_PLATINUM_ORE.get(),BlockInit.DEEPSLATE_SILVER_ORE.get(),BlockInit.DEEPSLATE_TIN_ORE.get(),
                        BlockInit.DIORITE_BRICKS.get(),
                        BlockInit.DUSKSTEEL_BLOCK.get(), BlockInit.FELDSPAR.get(),BlockInit.FORGE.get(),
                        BlockInit.FROST_STEEL_BLOCK.get(),BlockInit.GRANITE_BRICKS.get(),BlockInit.JAW_CRUSHER.get(),
                        BlockInit.KOBOLD_BLOCK.get(), BlockInit.PLATINUM_BLOCK.get(),BlockInit.PLATINUM_ORE.get(),
                        BlockInit.REFINED_OBSIDIAN_BLOCK.get(),
                        BlockInit.SHADOW_PLATINUM_BLOCK.get(),BlockInit.SILVER_ORE.get(),
                        BlockInit.SILVER_BLOCK.get(), BlockInit.STEEL_BLOCK.get(), BlockInit.TIN_BLOCK.get(),
                        BlockInit.TIN_ORE.get()
                );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockInit.DIORITE_CLAY.get(),BlockInit.GRANITE_CLAY.get());
    }
}
