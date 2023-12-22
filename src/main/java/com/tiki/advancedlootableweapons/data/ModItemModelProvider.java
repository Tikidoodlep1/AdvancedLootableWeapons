package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
        super(generator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        makeSimpleBlockItem(BlockInit.BLOCK_FORGE.get().asItem());
    }

    protected void makeSimpleBlockItem(Item item, ResourceLocation loc) {
        String s = Registry.ITEM.getKey(item).toString();
        getBuilder(s).parent(getExistingFile(loc));
    }

    protected void makeSimpleBlockItem(Item item) {
        makeSimpleBlockItem(item, new ResourceLocation(AdvancedLootableWeapons.MODID, "block/" + Registry.ITEM.getKey(item).getPath()));
    }


}
