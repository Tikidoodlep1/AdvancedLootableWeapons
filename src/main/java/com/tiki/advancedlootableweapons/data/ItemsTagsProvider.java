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
        tag(ModItemTags.INGOTS_BRONZE).add(ItemInit.BRONZE_INGOT.get());
        tag(ModItemTags.INGOTS_CRYSTALLITE).add(ItemInit.CRYSTALLITE_INGOT.get());
        tag(ModItemTags.INGOTS_PLATINUM).add(ItemInit.PLATINUM_INGOT.get());
        tag(ModItemTags.INGOTS_REFINED_OBSIDIAN).add(ItemInit.REFINED_OBSIDIAN_INGOT.get());
        tag(ModItemTags.INGOTS_SHADOW_PLATINUM).add(ItemInit.SHADOW_PLATINUM_INGOT.get());
        tag(ModItemTags.INGOTS_SILVER).add(ItemInit.SILVER_INGOT.get());
        tag(ModItemTags.INGOTS_STEEL).add(ItemInit.STEEL_INGOT.get());
        tag(ModItemTags.INGOTS_TIN).add(ItemInit.TIN_INGOT.get());

        tag(ModItemTags.SALT).add(ItemInit.SALT.get());

        tag(ModItemTags.CHAIN_BINDINGS).
                add(ItemInit.IRON_CHAIN_LINK.get(),
                        ItemInit.GOLD_CHAIN_LINK.get(),
                        ItemInit.KOBOLD_STEEL_CHAIN_LINK.get(),
                        ItemInit.COPPER_CHAIN_LINK.get(),
                        ItemInit.SILVER_CHAIN_LINK.get(),
                        ItemInit.BRONZE_CHAIN_LINK.get(),
                        ItemInit.PLATINUM_CHAIN_LINK.get(),
                        ItemInit.STEEL_CHAIN_LINK.get(),
                        ItemInit.SHADOW_PLATINUM_CHAIN_LINK.get(),
                        ItemInit.FROST_STEEL_CHAIN_LINK.get(),
                        ItemInit.REFINED_OBSIDIAN_CHAIN_LINK.get(),
                        ItemInit.CRYSTALLITE_CHAIN_LINK.get(),
                        ItemInit.DUSKSTEEL_CHAIN_LINK.get());
    }
}
