package com.tiki.advancedlootableweapons.data.models;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.block.AlloyFurnaceBlock;
import com.tiki.advancedlootableweapons.block.DusksteelBlock;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
        simpleBlock(BlockInit.KOBOLD_STEEL_BLOCK.get());
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

        simpleBlock(BlockInit.MILK_OF_LIME.get(),
                ConfiguredModel.builder().modelFile(models().getBuilder("milk_of_lime")
                .texture("particle", FluidInit.MOL_TEXTURE)).build());

        simpleBlock(BlockInit.MAGNESIUM_LACTATE.get(),
                ConfiguredModel.builder().modelFile(models().getBuilder("magnesium_lactate")
                        .texture("particle", FluidInit.ML_TEXTURE)).build());

        getVariantBuilder(BlockInit.DUSKSTEEL_BLOCK.get()).forAllStates(state -> {
            int phase = state.getValue(DusksteelBlock.PHASE);
            ResourceLocation name = AdvancedLootableWeapons.id( "block/dusksteel_block_" + phase);
            return ConfiguredModel.builder().modelFile(models().cubeAll("dusksteel_block_" + phase, name)).build();
        });


        horizontalBlock(BlockInit.FORGE.get(), models().getExistingFile(
                AdvancedLootableWeapons.id( "block/forge")), 180);

        horizontalBlock(BlockInit.JAW_CRUSHER.get(), models().getExistingFile(
                AdvancedLootableWeapons.id( "block/jaw_crusher")), 180);

        simpleBlock(BlockInit.CLAY_DRUM.get(),models().getExistingFile(AdvancedLootableWeapons.id("block/clay_drum")));

        int bellows = 270;


        horizontalBlock(BlockInit.OAK_BELLOWS.get(), models()
                        .withExistingParent("block/oak_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/oak_planks"))
                , bellows);

        horizontalBlock(BlockInit.BIRCH_BELLOWS.get(), models()
                        .withExistingParent("block/birch_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/birch_planks"))
                , bellows);

        horizontalBlock(BlockInit.JUNGLE_BELLOWS.get(), models()
                        .withExistingParent("block/jungle_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/jungle_planks"))
                , bellows);

        horizontalBlock(BlockInit.SPRUCE_BELLOWS.get(), models()
                        .withExistingParent("block/spruce_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/spruce_planks"))
                , bellows);

        horizontalBlock(BlockInit.ACACIA_BELLOWS.get(), models()
                        .withExistingParent("block/acacia_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/acacia_planks"))
                , bellows);

        horizontalBlock(BlockInit.DARK_OAK_BELLOWS.get(), models()
                        .withExistingParent("block/dark_oak_bellows", AdvancedLootableWeapons.id( "block/bellows"))
                        .texture("#3", new ResourceLocation("block/dark_oak_planks"))
                , bellows);

        horizontalBlock(BlockInit.TANNING_RACK.get(), models().getExistingFile(AdvancedLootableWeapons.id("block/tanning_rack"))
                , bellows);

        getVariantBuilder(BlockInit.ALLOY_FURNACE.get()).forAllStates(state -> {
            boolean lit = state.getValue(AlloyFurnaceBlock.LIT);
            ResourceLocation side = AdvancedLootableWeapons.id( "block/alloy_furnace_side");
            ResourceLocation front = AdvancedLootableWeapons.id( "block/alloy_furnace_front" + (lit ? "_lit" : ""));
            ResourceLocation top = AdvancedLootableWeapons.id( "block/alloy_furnace_top");
            return ConfiguredModel.builder()
                    .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .modelFile(models().orientable("alloy_furnace" + (lit ? "_on" : ""),
                            side, front, top)).build();
        });
    }

}
