package com.tiki.advancedlootableweapons.init;

import java.util.function.Supplier;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.block.*;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.LiquidBlock;
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
			.destroyTime(5.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> KOBOLD_STEEL_BLOCK = registerBlock("kobold_steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(3.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> CRYSTALLITE_BLOCK = registerBlock("crystallite_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(9.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> FROST_STEEL_BLOCK = registerBlock("frost_steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.5F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(4.0F).explosionResistance(5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> SHADOW_PLATINUM_BLOCK = registerBlock("shadow_platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(4.8F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> REFINED_OBSIDIAN_BLOCK = registerBlock("refined_obsidian_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(6.5F).explosionResistance(100.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DUSKSTEEL_BLOCK = registerBlock("dusksteel_block", () -> new DusksteelBlock(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(5.0F).explosionResistance(5.6F).requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL)), ModCreativeTabs.BLOCK_TAB, true);
	
	//Rocks & Clays
	public static final RegistryObject<Block> COBBLED_FELDSPAR = registerBlock("cobbled_feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.1F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> FELDSPAR = registerBlock("feldspar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(3.2F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> GRANITE_CLAY = registerBlock("granite_clay", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DIORITE_CLAY = registerBlock("diorite_clay", () -> new Block(BlockBehaviour.Properties.of(Material.CLAY)
			.destroyTime(0.8F).explosionResistance(1.3F).sound(SoundType.WET_GRASS)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> GRANITE_CLAY_POWDER = registerBlock("granite_clay_powder", () -> new ConcretePowderBlock(BlockInit.GRANITE_CLAY.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DIORITE_CLAY_POWDER = registerBlock("diorite_clay_powder", () -> new ConcretePowderBlock(BlockInit.DIORITE_CLAY.get(), BlockBehaviour.Properties.of(Material.SAND)
			.destroyTime(0.5F).sound(SoundType.SAND)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> GRANITE_BRICKS = registerBlock("granite_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DIORITE_BRICKS = registerBlock("diorite_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> GYPSUM = registerBlock("gypsum",() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DOLOMITE = registerBlock("dolomite",() -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(1.4F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);

	//Ores
	public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.3F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> CRYSTALLITE_ORE = registerBlock("crystallite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.5F).explosionResistance(3.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.1F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DEEPSLATE_CRYSTALLITE_ORE = registerBlock("deepslate_crystallite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.8F).explosionResistance(3.4F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(4.1F).explosionResistance(2.8F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeTabs.BLOCK_TAB, true);
	
	//Functionals
	public static final RegistryObject<Block> ALLOY_FURNACE = registerBlock("alloy_furnace", () -> new AlloyFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(3.5F).explosionResistance(4.1F).requiresCorrectToolForDrops().sound(SoundType.STONE)), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> FORGE = registerBlock("forge", () -> new ForgeBlock(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(2.7F).explosionResistance(3.7F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> ADVANCED_FORGE = registerBlock("advanced_forge", () -> new AdvancedForgeBlock(BlockBehaviour.Properties.of(Material.STONE)
			.destroyTime(2.7F).explosionResistance(3.7F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.STONE).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> JAW_CRUSHER = registerBlock("jaw_crusher", () -> new JawCrusherBlock(BlockBehaviour.Properties.of(Material.METAL)
			.destroyTime(2.5F).explosionResistance(2.6F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);
	public static final RegistryObject<Block> TANNING_RACK = registerBlock("tanning_rack", () -> new TanningRackBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(3).noOcclusion().sound(SoundType.METAL).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> CLAY_DRUM = registerBlock("clay_drum",() ->
			new DrumBlock(BlockBehaviour.Properties.of(Material.STONE).destroyTime(2.5F).explosionResistance(2.6F).requiresCorrectToolForDrops()),ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> OAK_BELLOWS = registerBlock("oak_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> SPRUCE_BELLOWS = registerBlock("spruce_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> BIRCH_BELLOWS = registerBlock("birch_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> JUNGLE_BELLOWS = registerBlock("jungle_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> ACACIA_BELLOWS = registerBlock("acacia_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<Block> DARK_OAK_BELLOWS = registerBlock("dark_oak_bellows", () -> new BellowsBlock(BlockBehaviour.Properties.of(Material.WOOD)
			.destroyTime(1.5F).explosionResistance(1.8F)
			.noOcclusion().sound(SoundType.WOOD).dynamicShape()), ModCreativeTabs.BLOCK_TAB, true);

	public static final RegistryObject<LiquidBlock> MILK_OF_LIME = registerBlock("milk_of_lime",
			() -> new LiquidBlock(FluidInit.MILK_OF_LIME,BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()),
			null,false);

	public static final RegistryObject<LiquidBlock> MAGNESIUM_LACTATE = registerBlock("magnesium_lactate",
			() -> new LiquidBlock(FluidInit.MAGNESIUM_LACTATE,BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()),
			null,false);

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab,boolean withItem) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		if (withItem) {
			registerBlockItem(name, toReturn, tab);
		}
		return toReturn;
	}
	
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
		return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
