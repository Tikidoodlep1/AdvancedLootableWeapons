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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
        super(generator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(BlockInit.FORGE.get().asItem());
        simpleBlockItem(BlockInit.ALLOY_FURNACE.get());
        simpleBlockItem(BlockInit.ADVANCED_FORGE.get());
        simpleBlockItem(BlockInit.CLAY_DRUM.get());

        simpleBlockItem(BlockInit.OAK_BELLOWS.get());
        simpleBlockItem(BlockInit.BIRCH_BELLOWS.get());
        simpleBlockItem(BlockInit.JUNGLE_BELLOWS.get());
        simpleBlockItem(BlockInit.SPRUCE_BELLOWS.get());
        simpleBlockItem(BlockInit.ACACIA_BELLOWS.get());
        simpleBlockItem(BlockInit.DARK_OAK_BELLOWS.get());

        simpleBlockItem(BlockInit.CRYSTALLITE_ORE.get());
        simpleBlockItem(BlockInit.PLATINUM_ORE.get());
        simpleBlockItem(BlockInit.SILVER_ORE.get());
        simpleBlockItem(BlockInit.TIN_ORE.get());

        simpleBlockItem(BlockInit.DEEPSLATE_CRYSTALLITE_ORE.get());
        simpleBlockItem(BlockInit.DEEPSLATE_PLATINUM_ORE.get());
        simpleBlockItem(BlockInit.DEEPSLATE_SILVER_ORE.get());
        simpleBlockItem(BlockInit.DEEPSLATE_TIN_ORE.get());

        simpleBlockItem(BlockInit.COBBLED_FELDSPAR.get());
        simpleBlockItem(BlockInit.FELDSPAR.get());

        oneLayerItem(ItemInit.RAW_PLATINUM.get());
        oneLayerItem(ItemInit.RAW_SILVER.get());
        oneLayerItem(ItemInit.RAW_TIN.get());

        oneLayerItem(ItemInit.DIORITE_BRICK.get());
        oneLayerItem(ItemInit.GRANITE_BRICK.get());

        simpleBlockItem(BlockInit.DIORITE_CLAY.get());
        simpleBlockItem(BlockInit.GRANITE_CLAY.get());

        simpleBlockItem(BlockInit.DIORITE_CLAY_POWDER.get());
        simpleBlockItem(BlockInit.GRANITE_CLAY_POWDER.get());

        simpleBlockItem(BlockInit.DIORITE_BRICKS.get());
        simpleBlockItem(BlockInit.GRANITE_BRICKS.get());

        oneLayerItem(ItemInit.DIORITE_CLAY_BALL.get());
        oneLayerItem(ItemInit.GRANITE_CLAY_BALL.get());

        oneLayerItem(ItemInit.TANNING_KNIFE.get());
        oneLayerItem(ItemInit.UNTRIMMED_HIDE.get());
        oneLayerItem(ItemInit.TRIMMED_HIDE.get());
        oneLayerItem(ItemInit.CURED_HIDE.get());
        oneLayerItem(ItemInit.LIMED_HIDE.get());
        oneLayerItem(ItemInit.DELIMED_HIDE.get());

        oneLayerItem(ItemInit.DIORITE_POWDER.get());
        oneLayerItem(ItemInit.FELDSPAR_POWDER.get());
        oneLayerItem(ItemInit.GRANITE_POWDER.get());

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
        oneLayerItem(ItemInit.COPPER_NUGGET.get());
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

        oneLayerItem(ItemInit.BRONZE_WHETSTONE.get());
        oneLayerItem(ItemInit.COPPER_WHETSTONE.get());
        oneLayerItem(ItemInit.CRYSTALLITE_WHETSTONE.get());
        oneLayerItem(ItemInit.DUSKSTEEL_WHETSTONE.get());
        oneLayerItem(ItemInit.FROST_STEEL_WHETSTONE.get());
        oneLayerItem(ItemInit.IRON_WHETSTONE.get());
        oneLayerItem(ItemInit.KOBOLD_WHETSTONE.get());
        oneLayerItem(ItemInit.PLATINUM_WHETSTONE.get());
        oneLayerItem(ItemInit.OBSIDIAN_WHETSTONE.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_WHETSTONE.get());
        oneLayerItem(ItemInit.SILVER_WHETSTONE.get());
        oneLayerItem(ItemInit.STEEL_WHETSTONE.get());
        oneLayerItem(ItemInit.STONE_WHETSTONE.get());

        simpleBlockItem(BlockInit.STEEL_BLOCK.get());
        simpleBlockItem(BlockInit.KOBOLD_STEEL_BLOCK.get());
        simpleBlockItem(BlockInit.TIN_BLOCK.get());
        simpleBlockItem(BlockInit.CRYSTALLITE_BLOCK.get());
        simpleBlockItem(BlockInit.PLATINUM_BLOCK.get());
        simpleBlockItem(BlockInit.BRONZE_BLOCK.get());
        simpleBlockItem(BlockInit.FROST_STEEL_BLOCK.get());
        simpleBlockItem(BlockInit.SILVER_BLOCK.get());
        simpleBlockItem(BlockInit.SHADOW_PLATINUM_BLOCK.get());
        simpleBlockItem(BlockInit.REFINED_OBSIDIAN_BLOCK.get());
        duskSteel();

        oneLayerItem(ItemInit.IRON_CHAIN_LINK.get());
        oneLayerItem(ItemInit.GOLD_CHAIN_LINK.get());
        oneLayerItem(ItemInit.COPPER_CHAIN_LINK.get());
        oneLayerItem(ItemInit.SILVER_CHAIN_LINK.get());
        oneLayerItem(ItemInit.BRONZE_CHAIN_LINK.get());
        oneLayerItem(ItemInit.PLATINUM_CHAIN_LINK.get());
        oneLayerItem(ItemInit.STEEL_CHAIN_LINK.get());
        oneLayerItem(ItemInit.OBSIDIAN_CHAIN_LINK.get());
        oneLayerItem(ItemInit.KOBOLD_CHAIN_LINK.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_CHAIN_LINK.get());
        oneLayerItem(ItemInit.FROST_STEEL_CHAIN_LINK.get());
        oneLayerItem(ItemInit.CRYSTALLITE_CHAIN_LINK.get());
        oneLayerItem(ItemInit.DUSKSTEEL_CHAIN_LINK.get());

        oneLayerItemHandHeld(ItemInit.STONE_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.IRON_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.COPPER_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.SILVER_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.BRONZE_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.PLATINUM_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.STEEL_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.REFINED_OBSIDIAN_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.KOBOLD_STEEL_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.SHADOW_PLATINUM_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.FROST_STEEL_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.CRYSTALLITE_FORGE_HAMMER.get());
        oneLayerItemHandHeld(ItemInit.DUSKSTEEL_FORGE_HAMMER.get());

        oneLayerItem(ItemInit.IRON_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.GOLD_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.DIAMOND_STUDDED_STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.COPPER_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.SILVER_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.BRONZE_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.PLATINUM_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.OBSIDIAN_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.KOBOLD_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.FROST_STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.CRYSTALLITE_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.DUSKSTEEL_ARMOR_PLATE.get());


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
        getBuilder(s).parent(new ModelFile.UncheckedModelFile(loc));//the model is generated
    }

    protected void duskSteel() {
        String s = Registry.ITEM.getKey(BlockInit.DUSKSTEEL_BLOCK.get().asItem()).toString();
        getBuilder(s).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(AdvancedLootableWeapons.MODID,"block/dusksteel_block_0")));//the model is generated
    }

    protected void simpleBlockItem(Block block) {
        simpleBlockItem(block.asItem());
    }


    protected void simpleBlockItem(Item item) {
        simpleBlockItem(item, new ResourceLocation(AdvancedLootableWeapons.MODID, "block/" + Registry.ITEM.getKey(item).getPath()));
    }


}
