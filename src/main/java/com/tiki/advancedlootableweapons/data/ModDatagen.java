package com.tiki.advancedlootableweapons.data;

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
        BlocksTagsProvider blocksTagsProvider = new BlocksTagsProvider(dataGenerator,existingFileHelper);
        dataGenerator.addProvider(blocksTagsProvider);
        dataGenerator.addProvider(new ItemsTagsProvider(dataGenerator,blocksTagsProvider,existingFileHelper));
    }
}
