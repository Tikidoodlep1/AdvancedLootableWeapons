package com.tiki.advancedlootableweapons.data.models;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
        super(generator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(BlockInit.BLOCK_FORGE.get().asItem());

        oneLayerItem(ItemInit.OBSIDIAN_SHARD.get());

        oneLayerItem(ItemInit.BRONZE_INGOT.get());
        oneLayerItem(ItemInit.CRYSTALLITE_INGOT.get());
        oneLayerItem(ItemInit.DUSKSTEEL_INGOT.get());
        oneLayerItem(ItemInit.FROST_STEEL_INGOT.get());
        oneLayerItem(ItemInit.KOBOLD_INGOT.get());
        oneLayerItem(ItemInit.PLATINUM_INGOT.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_INGOT.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_INGOT.get());
        oneLayerItem(ItemInit.SILVER_INGOT.get());
        oneLayerItem(ItemInit.STEEL_INGOT.get());
        oneLayerItem(ItemInit.TIN_INGOT.get());


        oneLayerItem(ItemInit.BRONZE_NUGGET.get());
        oneLayerItem(ItemInit.CRYSTALLITE_NUGGET.get());
        oneLayerItem(ItemInit.DUSKSTEEL_NUGGET.get());
        oneLayerItem(ItemInit.FROST_STEEL_NUGGET.get());
        oneLayerItem(ItemInit.KOBOLD_NUGGET.get());
        oneLayerItem(ItemInit.PLATINUM_NUGGET.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_NUGGET.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_NUGGET.get());
        oneLayerItem(ItemInit.SILVER_NUGGET.get());
        oneLayerItem(ItemInit.STEEL_NUGGET.get());
        oneLayerItem(ItemInit.TIN_NUGGET.get());

        oneLayerItem(ItemInit.CHAIN_LINK_IRON.get());
        oneLayerItem(ItemInit.CHAIN_LINK_GOLD.get());
        oneLayerItem(ItemInit.CHAIN_LINK_COPPER.get());
        oneLayerItem(ItemInit.CHAIN_LINK_SILVER.get());
        oneLayerItem(ItemInit.CHAIN_LINK_BRONZE.get());
        oneLayerItem(ItemInit.CHAIN_LINK_PLATINUM.get());
        oneLayerItem(ItemInit.CHAIN_LINK_STEEL.get());
        oneLayerItem(ItemInit.CHAIN_LINK_OBSIDIAN.get());
        oneLayerItem(ItemInit.CHAIN_LINK_KOBOLD.get());
        oneLayerItem(ItemInit.CHAIN_LINK_SHADOW_PLATINUM.get());
        oneLayerItem(ItemInit.CHAIN_LINK_FROST_STEEL.get());
        oneLayerItem(ItemInit.CHAIN_LINK_CRYSTALLITE.get());
        oneLayerItem(ItemInit.CHAIN_LINK_DUSKSTEEL.get());


        for (RegistryObject<AlwWeapon> weapon : ItemInit.WEAPONS) {
            WeaponAttributes weaponAttributes = weapon.get().attributes;

            if (weaponAttributes.isCustomModel()) {
                oneLayerItemWithParent(weapon.get(), modLoc("item/"+weaponAttributes.name().toLowerCase()));
            } else {
                oneLayerItemHandHeld(weapon.get());
            }
        }
    }

    protected void oneLayerItemWithParent(Item item, ResourceLocation texture,ResourceLocation parent) {
        String path = Registry.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists(new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath())
                , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(getExistingFile(parent))
                    .texture("layer0", new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath()));
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void oneLayerItemWithParent(Item item,ResourceLocation parent) {
        ResourceLocation texture = Registry.ITEM.getKey(item);
        oneLayerItemWithParent(item, texture,parent);
    }

    protected void oneLayerItem(Item item, ResourceLocation texture) {
        String path = Registry.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists(new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath())
                , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(getExistingFile(mcLoc("item/generated")))
                    .texture("layer0", new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath()));
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void oneLayerItem(Item item) {
        ResourceLocation texture = Registry.ITEM.getKey(item);
        oneLayerItem(item, texture);
    }

    protected void oneLayerItemHandHeld(Item item, ResourceLocation texture) {
        String path = Registry.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists(new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath())
                , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(getExistingFile(mcLoc("item/handheld")))
                    .texture("layer0", new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath()));
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void oneLayerItemHandHeld(Item item) {
        ResourceLocation texture = Registry.ITEM.getKey(item);
        oneLayerItemHandHeld(item, texture);
    }

    protected void simpleBlockItem(Item item, ResourceLocation loc) {
        String s = Registry.ITEM.getKey(item).toString();
        getBuilder(s).parent(getExistingFile(loc));
    }

    protected void simpleBlockItem(Item item) {
        simpleBlockItem(item, new ResourceLocation(AdvancedLootableWeapons.MODID, "block/" + Registry.ITEM.getKey(item).getPath()));
    }


}
