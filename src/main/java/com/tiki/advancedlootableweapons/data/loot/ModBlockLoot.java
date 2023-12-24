package com.tiki.advancedlootableweapons.data.loot;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.stream.Collectors;

public class ModBlockLoot extends BlockLoot {


    @Override
    protected void addTables() {
        add(BlockInit.CRYSTALLITE_ORE.get(), (block) -> createOreDrop(block, ItemInit.CRYSTAL.get()));
        add(BlockInit.PLATINUM_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_PLATINUM.get()));
        add(BlockInit.SILVER_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_SILVER.get()));
        add(BlockInit.TIN_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_TIN.get()));

        add(BlockInit.DEEPSLATE_CRYSTALLITE_ORE.get(), (block) -> createOreDrop(block, ItemInit.CRYSTAL.get()));
        add(BlockInit.DEEPSLATE_PLATINUM_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_PLATINUM.get()));
        add(BlockInit.DEEPSLATE_SILVER_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_SILVER.get()));
        add(BlockInit.DEEPSLATE_TIN_ORE.get(), (block) -> createOreDrop(block, ItemInit.RAW_TIN.get()));

        dropSelf(BlockInit.STEEL_BLOCK.get());
        dropSelf(BlockInit.KOBOLD_BLOCK.get());
        dropSelf(BlockInit.TIN_BLOCK.get());
        dropSelf(BlockInit.CRYSTALLITE_BLOCK.get());
        dropSelf(BlockInit.PLATINUM_BLOCK.get());
        dropSelf(BlockInit.BRONZE_BLOCK.get());
        dropSelf(BlockInit.FROST_STEEL_BLOCK.get());
        dropSelf(BlockInit.SILVER_BLOCK.get());
        dropSelf(BlockInit.SHADOW_PLATINUM_BLOCK.get());
        dropSelf(BlockInit.REFINED_OBSIDIAN_BLOCK.get());
        dropSelf(BlockInit.DUSKSTEEL_BLOCK.get());

        dropSelf(BlockInit.FORGE.get());
        dropSelf(BlockInit.ALLOY_FURNACE.get());
        dropSelf(BlockInit.JAW_CRUSHER.get());

        add(BlockInit.FELDSPAR.get(), (block) -> createSingleItemTableWithSilkTouch(block, BlockInit.COBBLED_FELDSPAR.get()));
        dropSelf(BlockInit.COBBLED_FELDSPAR.get());

        this.add(BlockInit.DIORITE_CLAY.get(),(block) -> createSingleItemTableWithSilkTouch(block, ItemInit.DIORITE_CLAY_BALL.get(), ConstantValue.exactly(4.0F)));
        this.add(BlockInit.GRANITE_CLAY.get(),(block) -> createSingleItemTableWithSilkTouch(block, ItemInit.GRANITE_CLAY_BALL.get(), ConstantValue.exactly(4.0F)));

        dropSelf(BlockInit.DIORITE_BRICKS.get());
        dropSelf(BlockInit.GRANITE_BRICKS.get());

        dropSelf(BlockInit.DIORITE_CLAY_POWDER.get());
        dropSelf(BlockInit.GRANITE_CLAY_POWDER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK.stream().filter(block -> Registry.BLOCK.getKey(block).getNamespace()
                .equals(AdvancedLootableWeapons.MODID)).collect(Collectors.toList());
    }
}
