package com.tiki.advancedlootableweapons.init;

import java.util.function.Supplier;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.BlockDusksteel;
import com.tiki.advancedlootableweapons.blocks.ForgeBlock;
import com.tiki.advancedlootableweapons.blocks.BlockJawCrusher;
import com.tiki.advancedlootableweapons.blocks.BlockPowderedClay;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AdvancedLootableWeapons.MODID);
	
	//Metals
	public static final RegistryObject<Block> BLOCK_STEEL = registerBlock("block_steel", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_KOBOLD = registerBlock("block_kobold", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_TIN = registerBlock("block_tin", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(3.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_CRYSTALLITE = registerBlock("block_crystallite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(9.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_PLATINUM = registerBlock("block_platinum", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_BRONZE = registerBlock("block_bronze", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_FROST_STEEL = registerBlock("block_frost_steel", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_SILVER = registerBlock("block_silver", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(4.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_SHADOW_PLATINUM = registerBlock("block_shadow_platinum", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.8F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_OBSIDIAN_STEEL = registerBlock("block_obsidian_steel", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(6.5F).explosionResistance(100.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_DUSKSTEEL = registerBlock("block_dusksteel", () -> new BlockDusksteel(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.6F).requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL)), CreativeTabs.BLOCK_TAB);
	
	//Rocks & Clays
	public static final RegistryObject<Block> BLOCK_COBBLED_FELDSPAR = registerBlock("block_cobbled_feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.1F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_ROCK_FELDSPAR = registerBlock("block_rock_feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_CLAY_GRANITE = registerBlock("block_clay_granite", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_CLAY_DIORITE = registerBlock("block_clay_diorite", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_POWDER_GRANITE = registerBlock("block_powder_granite", () -> new BlockPowderedClay(BlockInit.BLOCK_CLAY_GRANITE.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_POWDER_DIORITE = registerBlock("block_powder_diorite", () -> new BlockPowderedClay(BlockInit.BLOCK_CLAY_DIORITE.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_BRICK_GRANITE = registerBlock("block_brick_granite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_BRICK_DIORITE = registerBlock("block_brick_diorite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	
	//Ores
	public static final RegistryObject<Block> BLOCK_STONE_ORE_SILVER = registerBlock("block_stone_ore_silver", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.3F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_STONE_ORE_PLATINUM = registerBlock("block_stone_ore_platinum", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_STONE_ORE_CRYSTALLITE = registerBlock("block_stone_ore_crystallite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_STONE_ORE_TIN = registerBlock("block_stone_ore_tin", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_DEEPSLATE_ORE_SILVER = registerBlock("block_deepslate_ore_silver", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_DEEPSLATE_ORE_PLATINUM = registerBlock("block_deepslate_ore_platinum", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_DEEPSLATE_ORE_CRYSTALLITE = registerBlock("block_deepslate_ore_crystallite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_DEEPSLATE_ORE_TIN = registerBlock("block_deepslate_ore_tin", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), CreativeTabs.BLOCK_TAB);
	
	//Functionals
	public static final RegistryObject<Block> BLOCK_ALLOY_FURNACE = registerBlock("block_alloy_furnace", () -> new BlockAlloyFurnace(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.5F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_FORGE = registerBlock("block_forge", () -> new ForgeBlock(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(2.7F).explosionResistance(3.7F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE).dynamicShape()), CreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BLOCK_JAW_CRUSHER = registerBlock("block_jaw_crusher", () -> new BlockJawCrusher(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(2.5F).explosionResistance(2.6F).requiresCorrectToolForDrops().sound(SoundType.METAL).dynamicShape()), CreativeTabs.BLOCK_TAB);
	
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}
	
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
		return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
