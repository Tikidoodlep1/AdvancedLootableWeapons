package com.tiki.advancedlootableweapons.data.models;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AdvancedLootableWeapons.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
