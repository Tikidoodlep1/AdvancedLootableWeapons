package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.data.models.ModBlockstateProvider;
import com.tiki.advancedlootableweapons.data.models.ModItemModelProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.stream.Stream;

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


    public static Stream<Block> getKnownBlocks() {
        return getKnown(Registry.BLOCK);
    }
    public static Stream<Item> getKnownItems() {
        return getKnown(Registry.ITEM);
    }

    public static <V> Stream<V> getKnown(Registry<V> registry) {
        return registry.stream().filter(o -> registry.getKey(o).getNamespace().equals(AdvancedLootableWeapons.MODID));
    }

}
