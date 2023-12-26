package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.data.models.ModBlockstateProvider;
import com.tiki.advancedlootableweapons.data.models.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class ModDatagen {

    public static void start(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(new ModItemModelProvider(dataGenerator,existingFileHelper));
        dataGenerator.addProvider(new ModLangProvider(dataGenerator));
        dataGenerator.addProvider(new ModRecipeProvider(dataGenerator));
        ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(dataGenerator,existingFileHelper);
        dataGenerator.addProvider(modBlockTagsProvider);
        dataGenerator.addProvider(new ItemsTagsProvider(dataGenerator, modBlockTagsProvider,existingFileHelper));
        dataGenerator.addProvider(new ModBlockstateProvider(dataGenerator,existingFileHelper));
        dataGenerator.addProvider(new ModLootTableProvider(dataGenerator));
    }
}
