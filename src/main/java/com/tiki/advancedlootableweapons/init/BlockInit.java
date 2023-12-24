package com.tiki.advancedlootableweapons.init;

import java.util.function.Supplier;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.blocks.BlockAlloyFurnace;
import com.tiki.advancedlootableweapons.blocks.DusksteelBlock;
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
	public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> KOBOLD_BLOCK = registerBlock("kobold_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(3.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> CRYSTALLITE_BLOCK = registerBlock("crystallite_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(9.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> FROST_STEEL_BLOCK = registerBlock("frost_steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(4.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> SHADOW_PLATINUM_BLOCK = registerBlock("shadow_platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.8F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> REFINED_OBSIDIAN_BLOCK = registerBlock("refined_obsidian_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(6.5F).explosionResistance(100.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DUSKSTEEL_BLOCK = registerBlock("dusksteel_block", () -> new DusksteelBlock(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.6F).requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB);
	
	//Rocks & Clays
	public static final RegistryObject<Block> COBBLED_FELDSPAR = registerBlock("cobbled_feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.1F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> FELDSPAR = registerBlock("feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> GRANITE_CLAY = registerBlock("granite_clay", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DIORITE_CLAY = registerBlock("diorite_clay", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> GRANITE_CLAY_POWDER = registerBlock("granite_clay_powder", () -> new BlockPowderedClay(BlockInit.GRANITE_CLAY.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DIORITE_CLAY_POWDER = registerBlock("diorite_clay_powder", () -> new BlockPowderedClay(BlockInit.DIORITE_CLAY.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> GRANITE_BRICKS = registerBlock("granite_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DIORITE_BRICKS = registerBlock("diorite_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	
	//Ores
	public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.3F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> CRYSTALLITE_ORE = registerBlock("crystallite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DEEPSLATE_CRYSTALLITE_ORE = registerBlock("deepslate_crystallite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB);
	
	//Functionals
	public static final RegistryObject<Block> ALLOY_FURNACE = registerBlock("alloy_furnace", () -> new BlockAlloyFurnace(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.5F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> FORGE = registerBlock("forge", () -> new ForgeBlock(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(2.7F).explosionResistance(3.7F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE).dynamicShape()), ModCreativeTabs.BLOCK_TAB);
	public static final RegistryObject<Block> JAW_CRUSHER = registerBlock("jaw_crusher", () -> new BlockJawCrusher(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(2.5F).explosionResistance(2.6F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL).dynamicShape()), ModCreativeTabs.BLOCK_TAB);
	
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
