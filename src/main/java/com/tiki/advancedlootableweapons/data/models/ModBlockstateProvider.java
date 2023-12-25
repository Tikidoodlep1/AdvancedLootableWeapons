package com.tiki.advancedlootableweapons.data.models;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.DusksteelBlock;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AdvancedLootableWeapons.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(BlockInit.CRYSTALLITE_ORE.get());
        simpleBlock(BlockInit.PLATINUM_ORE.get());
        simpleBlock(BlockInit.SILVER_ORE.get());
        simpleBlock(BlockInit.TIN_ORE.get());

/*todo        simpleBlock(BlockInit.DEEPSLATE_CRYSTALLITE_ORE.get());
        simpleBlock(BlockInit.DEEPSLATE_PLATINUM_ORE.get());
        simpleBlock(BlockInit.DEEPSLATE_SILVER_ORE.get());
        simpleBlock(BlockInit.DEEPSLATE_TIN_ORE.get());*/

        simpleBlock(BlockInit.STEEL_BLOCK.get());
        simpleBlock(BlockInit.KOBOLD_BLOCK.get());
        simpleBlock(BlockInit.TIN_BLOCK.get());
        simpleBlock(BlockInit.CRYSTALLITE_BLOCK.get());
        simpleBlock(BlockInit.PLATINUM_BLOCK.get());
        simpleBlock(BlockInit.BRONZE_BLOCK.get());
        simpleBlock(BlockInit.FROST_STEEL_BLOCK.get());
        simpleBlock(BlockInit.SILVER_BLOCK.get());
        simpleBlock(BlockInit.SHADOW_PLATINUM_BLOCK.get());
        simpleBlock(BlockInit.REFINED_OBSIDIAN_BLOCK.get());

        simpleBlock(BlockInit.COBBLED_FELDSPAR.get());
        simpleBlock(BlockInit.FELDSPAR.get());

        simpleBlock(BlockInit.DIORITE_CLAY.get());
        simpleBlock(BlockInit.GRANITE_CLAY.get());

        simpleBlock(BlockInit.DIORITE_CLAY_POWDER.get());
        simpleBlock(BlockInit.GRANITE_CLAY_POWDER.get());

        simpleBlock(BlockInit.DIORITE_BRICKS.get());
        simpleBlock(BlockInit.GRANITE_BRICKS.get());

        getVariantBuilder(BlockInit.DUSKSTEEL_BLOCK.get()).forAllStates(state -> {
            int phase = state.getValue(DusksteelBlock.PHASE);
            ResourceLocation name = new ResourceLocation(AdvancedLootableWeapons.MODID, "block/dusksteel_block_" + phase);
            return ConfiguredModel.builder().modelFile(models().cubeAll("dusksteel_block_" + phase, name)).build();
        });

        getVariantBuilder(BlockInit.ALLOY_FURNACE.get()).forAllStates(state -> {
            boolean lit = state.getValue(BlockAlloyFurnace.LIT);
            ResourceLocation side = new ResourceLocation(AdvancedLootableWeapons.MODID, "block/alloy_furnace_side");
            ResourceLocation front = new ResourceLocation(AdvancedLootableWeapons.MODID, "block/alloy_furnace_front" + (lit ? "_lit" : ""));
            ResourceLocation top = new ResourceLocation(AdvancedLootableWeapons.MODID, "block/alloy_furnace_top");
            return ConfiguredModel.builder()
                    .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .modelFile(models().orientable("alloy_furnace"+(lit ? "_on" : ""),
                    side,front,top)).build();
        });
    }

}
