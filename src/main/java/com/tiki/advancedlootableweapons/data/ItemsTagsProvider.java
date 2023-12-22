package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tags.ModItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemsTagsProvider extends ItemTagsProvider {

    public ItemsTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModItemTags.INGOTS_SILVER).add(ItemInit.SILVER_INGOT.get());
        tag(ModItemTags.INGOTS_PLATINUM).add(ItemInit.PLATINUM_INGOT.get());
    }
}