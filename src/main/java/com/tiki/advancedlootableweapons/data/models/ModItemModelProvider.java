package com.tiki.advancedlootableweapons.data.models;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.client.ALWClient;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import com.tiki.advancedlootableweapons.items.armor.UnboundArmorItem;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AdvancedLootableWeapons.MODID, existingFileHelper);
    }

    protected final Set<Item> use_sprite = new HashSet<>();

    protected boolean shouldUseOneLayerModel(Item item) {
        return item instanceof UnboundArmorItem || (item instanceof BoundArmorItem boundArmorItem && !boundArmorItem.usesVanillaMaterial());
    }

    @Override
    protected void registerModels() {

        ItemInit.ITEMS.getEntries().stream().map(RegistryObject::get).filter(this::shouldUseOneLayerModel).forEach(use_sprite::add);
        use_sprite.addAll(ItemInit.DIAMOND_STUDDED_LEATHER_SET.values().stream().map(RegistryObject::get).toList());
        use_sprite.addAll(ItemInit.IRON_CHAIN_SET.values().stream().map(RegistryObject::get).toList());
        use_sprite.addAll(ItemInit.GOLD_CHAIN_SET.values().stream().map(RegistryObject::get).toList());
        use_sprite.add(ItemInit.LEATHER_STRIP.get());
        use_sprite.add(ItemInit.LEATHER_BINDING.get());
        use_sprite.forEach(this::oneLayerItem);

        /*oneLayerItem(ItemInit.LEATHER_SET.get(EquipmentSlot.HEAD).get(),new ResourceLocation("item/leather_helmet"));
        oneLayerItem(ItemInit.LEATHER_SET.get(EquipmentSlot.CHEST).get(),new ResourceLocation("item/leather_chestplate"));
        oneLayerItem(ItemInit.LEATHER_SET.get(EquipmentSlot.LEGS).get(),new ResourceLocation("item/leather_leggings"));
        oneLayerItem(ItemInit.LEATHER_SET.get(EquipmentSlot.FEET).get(),new ResourceLocation("item/leather_boots"));*/

        /*oneLayerItem(ItemInit.GOLD_SET.get(EquipmentSlot.HEAD).get(),new ResourceLocation("item/golden_helmet"));
        oneLayerItem(ItemInit.GOLD_SET.get(EquipmentSlot.CHEST).get(),new ResourceLocation("item/golden_chestplate"));
        oneLayerItem(ItemInit.GOLD_SET.get(EquipmentSlot.LEGS).get(),new ResourceLocation("item/golden_leggings"));
        oneLayerItem(ItemInit.GOLD_SET.get(EquipmentSlot.FEET).get(),new ResourceLocation("item/golden_boots"));

        oneLayerItem(ItemInit.IRON_SET.get(EquipmentSlot.HEAD).get(),new ResourceLocation("item/iron_helmet"));
        oneLayerItem(ItemInit.IRON_SET.get(EquipmentSlot.CHEST).get(),new ResourceLocation("item/iron_chestplate"));
        oneLayerItem(ItemInit.IRON_SET.get(EquipmentSlot.LEGS).get(),new ResourceLocation("item/iron_leggings"));
        oneLayerItem(ItemInit.IRON_SET.get(EquipmentSlot.FEET).get(),new ResourceLocation("item/iron_boots"));*/

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
        oneLayerItem(ItemInit.SALT.get());

        oneLayerItem(ItemInit.DIORITE_POWDER.get());
        oneLayerItem(ItemInit.FELDSPAR_POWDER.get());
        oneLayerItem(ItemInit.GRANITE_POWDER.get());

        oneLayerItem(ItemInit.OBSIDIAN_SHARD.get());

        oneLayerItem(ItemInit.BRONZE_INGOT.get());
        oneLayerItem(ItemInit.CRYSTALLITE_INGOT.get());
        oneLayerItem(ItemInit.DUSKSTEEL_INGOT.get());
        oneLayerItem(ItemInit.FROST_STEEL_INGOT.get());
        oneLayerItem(ItemInit.KOBOLD_STEEL_INGOT.get());
        oneLayerItem(ItemInit.PLATINUM_INGOT.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_INGOT.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_INGOT.get());
        oneLayerItem(ItemInit.SILVER_INGOT.get());
        oneLayerItem(ItemInit.STEEL_INGOT.get());
        oneLayerItem(ItemInit.TIN_INGOT.get());

        getBuilder("milk_of_lime_bucket")
                .parent(getExistingFile(new ResourceLocation("forge", "item/bucket")))
                .customLoader(DynamicBucketModelBuilder::begin).fluid(FluidInit.MILK_OF_LIME.get());

        getBuilder("magnesium_lactate_bucket")
                .parent(getExistingFile(new ResourceLocation("forge", "item/bucket")))
                .customLoader(DynamicBucketModelBuilder::begin).fluid(FluidInit.MAGNESIUM_LACTATE.get());


        oneLayerItem(ItemInit.BRONZE_NUGGET.get());
        oneLayerItem(ItemInit.COPPER_NUGGET.get());
        oneLayerItem(ItemInit.CRYSTALLITE_NUGGET.get());
        oneLayerItem(ItemInit.DUSKSTEEL_NUGGET.get());
        oneLayerItem(ItemInit.FROST_STEEL_NUGGET.get());
        oneLayerItem(ItemInit.KOBOLD_STEEL_NUGGET.get());
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
        oneLayerItem(ItemInit.KOBOLD_STEEL_WHETSTONE.get());
        oneLayerItem(ItemInit.PLATINUM_WHETSTONE.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_WHETSTONE.get());
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

        heatableItems();

        oneLayerItemHandHeld(ItemInit.WOODEN_LONG_TOOL_ROD.get());

        oneLayerItem(ItemInit.IRON_CHAIN_LINK.get());
        oneLayerItem(ItemInit.GOLD_CHAIN_LINK.get());
        oneLayerItem(ItemInit.COPPER_CHAIN_LINK.get());
        oneLayerItem(ItemInit.SILVER_CHAIN_LINK.get());
        oneLayerItem(ItemInit.BRONZE_CHAIN_LINK.get());
        oneLayerItem(ItemInit.PLATINUM_CHAIN_LINK.get());
        oneLayerItem(ItemInit.STEEL_CHAIN_LINK.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_CHAIN_LINK.get());
        oneLayerItem(ItemInit.KOBOLD_STEEL_CHAIN_LINK.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_CHAIN_LINK.get());
        oneLayerItem(ItemInit.FROST_STEEL_CHAIN_LINK.get());
        oneLayerItem(ItemInit.CRYSTALLITE_CHAIN_LINK.get());
        oneLayerItem(ItemInit.DUSKSTEEL_CHAIN_LINK.get());

        oneLayerItem(ItemInit.IRON_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.GOLD_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.COPPER_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.SILVER_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.BRONZE_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.PLATINUM_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.STEEL_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.KOBOLD_STEEL_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.FROST_STEEL_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.CRYSTALLITE_CHAIN_BINDING.get());
        oneLayerItem(ItemInit.DUSKSTEEL_CHAIN_BINDING.get());

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
        oneLayerItem(ItemInit.DIAMOND_STUDDED_LEATHER.get());
        oneLayerItem(ItemInit.DIAMOND_STUDDED_STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.COPPER_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.SILVER_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.BRONZE_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.PLATINUM_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.REFINED_OBSIDIAN_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.KOBOLD_STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.SHADOW_PLATINUM_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.FROST_STEEL_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.CRYSTALLITE_ARMOR_PLATE.get());
        oneLayerItem(ItemInit.DUSKSTEEL_ARMOR_PLATE.get());

        for (WeaponAttributes weaponAttributes : WeaponAttributes.values()) {
            if (weaponAttributes.hasItem()) {
                String type = weaponAttributes.getType();

                oneLayerItemHandHeld("wooden_"+type+"_head",AdvancedLootableWeapons.id("wooden_"+type+"_head"));

                getBuilder(type).customLoader(MaterialBakedModelBuilder::begin).folder(type);

                for (Map.Entry<String, WeaponMaterial> entry : WeaponMaterial.LOOKUP.entrySet()) {

                    if (entry.getValue().canMakeWeapon()) {
                        String material = entry.getKey();

                        ResourceLocation modelPath = AdvancedLootableWeapons.id( "item/" + type + "/" + material);
                        ResourceLocation texturePath = AdvancedLootableWeapons.id( "item/" + material + "_" + type);
                        if (existingFileHelper.exists(texturePath, PackType.CLIENT_RESOURCES, ".png", "textures")) {
                            if (weaponAttributes.isCustomModel()) {
                                ResourceLocation parent = modLoc("item/custom/" + type);
                                getBuilder(modelPath.getPath()).parent(getExistingFile(parent))
                                        .texture("layer0", texturePath);
                            } else {
                                getBuilder(modelPath.getPath()).parent(getExistingFile(mcLoc("item/handheld")))
                                        .texture("layer0", texturePath);
                            }
                        } else {
                            System.out.println("no texture for " + material + "/" + type + " found, skipping");
                        }
                    }
                }
            }
        }
    }

    protected void heatableItems() {
        heatableItem(ItemInit.TOOL_HEAD.get());
        heatableItem(ItemInit.TOOL_ROD.get());
        heatableItem(ItemInit.TOOL_ROD_2.get());
        heatableItem(ItemInit.LONG_TOOL_ROD.get());

        heatableItem(ItemInit.BATTLEAXE_HEAD.get());
        heatableItem(ItemInit.BATTLEAXE_HEAD_2.get());
        heatableItem(ItemInit.BATTLEAXE_HEAD_3.get());
        heatableItem(ItemInit.BATTLEAXE_HEAD_4.get());
        heatableItem(ItemInit.BATTLEAXE_HEAD_5.get());

        heatableItem(ItemInit.CLEAVER_HEAD.get());

        heatableItem(ItemInit.DAGGER_HEAD.get());
        heatableItem(ItemInit.DAGGER_HEAD_2.get());

        heatableItem(ItemInit.KABUTOWARI_HEAD.get());
        heatableItem(ItemInit.KABUTOWARI_HEAD_2.get());
        heatableItem(ItemInit.KABUTOWARI_HEAD_3.get());
        heatableItem(ItemInit.KABUTOWARI_HEAD_4.get());
        heatableItem(ItemInit.KABUTOWARI_HEAD_5.get());

        heatableItem(ItemInit.KODACHI_HEAD.get());
        heatableItem(ItemInit.KODACHI_HEAD_2.get());

        heatableItem(ItemInit.LONGSWORD_HEAD.get());
        heatableItem(ItemInit.LONGSWORD_HEAD_2.get());
        heatableItem(ItemInit.LONGSWORD_HEAD_3.get());
        heatableItem(ItemInit.LONGSWORD_HEAD_4.get());

        heatableItem(ItemInit.MACE_HEAD.get());
        heatableItem(ItemInit.MACE_HEAD_2.get());
        heatableItem(ItemInit.MACE_HEAD_3.get());

        heatableItem(ItemInit.MAKHAIRA_HEAD.get());
        heatableItem(ItemInit.MAKHAIRA_HEAD_2.get());
        heatableItem(ItemInit.MAKHAIRA_HEAD_3.get());

        heatableItem(ItemInit.NODACHI_HEAD.get());
        heatableItem(ItemInit.NODACHI_HEAD_2.get());
        heatableItem(ItemInit.NODACHI_HEAD_3.get());
        heatableItem(ItemInit.NODACHI_HEAD_4.get());

        heatableItem(ItemInit.RAPIER_HEAD.get());
        heatableItem(ItemInit.RAPIER_HEAD_2.get());
        heatableItem(ItemInit.RAPIER_HEAD_3.get());
        heatableItem(ItemInit.RAPIER_HEAD_4.get());

        heatableItem(ItemInit.SABRE_HEAD.get());
        heatableItem(ItemInit.SABRE_HEAD_2.get());
        heatableItem(ItemInit.SABRE_HEAD_3.get());
        heatableItem(ItemInit.SABRE_HEAD_4.get());

        heatableItem(ItemInit.SPEAR_HEAD.get());
        heatableItem(ItemInit.SPEAR_HEAD_2.get());

        heatableItem(ItemInit.STAFF_HEAD.get());
        heatableItem(ItemInit.STAFF_HEAD_2.get());
        heatableItem(ItemInit.STAFF_HEAD_3.get());
        heatableItem(ItemInit.STAFF_HEAD_4.get());
        heatableItem(ItemInit.STAFF_HEAD_5.get());

        heatableItem(ItemInit.TALWAR_HEAD.get());
        heatableItem(ItemInit.TALWAR_HEAD_2.get());
        heatableItem(ItemInit.TALWAR_HEAD_3.get());

        heatableItem(ItemInit.ZWEIHANDER_HEAD.get());
        heatableItem(ItemInit.ZWEIHANDER_HEAD_2.get());
        heatableItem(ItemInit.ZWEIHANDER_HEAD_3.get());
        heatableItem(ItemInit.ZWEIHANDER_HEAD_4.get());
        heatableItem(ItemInit.ZWEIHANDER_HEAD_5.get());

    }

    protected final ModelFile GENERATED = getExistingFile(mcLoc("item/generated"));

    protected void heatableItem(HeatableToolPartItem heatableToolPartItem) {
        String path = Registry.ITEM.getKey(heatableToolPartItem).getPath();
        ResourceLocation base = AdvancedLootableWeapons.id("item/"+path);
        ResourceLocation hot = AdvancedLootableWeapons.id("item/hot_"+path);
        ResourceLocation warm = AdvancedLootableWeapons.id("item/warm_"+path);
        ResourceLocation cool = AdvancedLootableWeapons.id("item/cool_"+path);
            getBuilder(path).parent(GENERATED)
                    .texture("layer0",hot)
                    .override().model(getBuilder(hot.toString()).parent(GENERATED).texture("layer0",hot)).predicate(ALWClient.HEAT,0).end()
                    .override().model(getBuilder(warm.toString()).parent(GENERATED).texture("layer0",warm)).predicate(ALWClient.HEAT,1).end()
                    .override().model(getBuilder(cool.toString()).parent(GENERATED).texture("layer0",cool)).predicate(ALWClient.HEAT,2).end();
    }



    protected void oneLayerItem(Item item, ResourceLocation texture) {
        String path = Registry.ITEM.getKey(item).getPath();
        if (existingFileHelper.exists(texture
                , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(path).parent(GENERATED)
                    .texture("layer0", texture);
        } else {
            System.out.println("no texture for " + item + " found, skipping");
        }
    }

    protected void oneLayerItem(Item item) {
        ResourceLocation texture = Registry.ITEM.getKey(item);
        oneLayerItem(item, new ResourceLocation(texture.getNamespace(),"item/"+texture.getPath()));
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

    protected void oneLayerItemHandHeld(String item, ResourceLocation texture) {
        if (existingFileHelper.exists(new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath())
                , PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(item).parent(getExistingFile(mcLoc("item/handheld")))
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
        getBuilder(s).parent(new ModelFile.UncheckedModelFile(AdvancedLootableWeapons.id( "block/dusksteel_block_0")));//the model is generated
    }

    protected void simpleBlockItem(Block block) {
        simpleBlockItem(block.asItem());
    }


    protected void simpleBlockItem(Item item) {
        simpleBlockItem(item, AdvancedLootableWeapons.id( "block/" + Registry.ITEM.getKey(item).getPath()));
    }


}
