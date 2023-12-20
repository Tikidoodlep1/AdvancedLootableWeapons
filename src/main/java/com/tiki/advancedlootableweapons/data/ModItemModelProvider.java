package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
        super(generator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
