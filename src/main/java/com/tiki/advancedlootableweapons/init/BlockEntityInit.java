package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.blocks.block_entity.AdvancedForgeBlockEntity;
import com.tiki.advancedlootableweapons.blocks.block_entity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.blocks.block_entity.ForgeBlockEntity;
import com.tiki.advancedlootableweapons.blocks.block_entity.JawCrusherBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, AdvancedLootableWeapons.MODID);
	
	public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> ALLOY_FURNACE_TE = 
			BLOCK_ENTITIES.register("alloy_furnace", () -> BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new, BlockInit.ALLOY_FURNACE.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE_TE = 
			BLOCK_ENTITIES.register("forge", () -> BlockEntityType.Builder.of(ForgeBlockEntity::new, BlockInit.FORGE.get()).build(null));

	public static final RegistryObject<BlockEntityType<AdvancedForgeBlockEntity>> ADVANCED_FORGE_TE =
			BLOCK_ENTITIES.register("advanced_forge", () -> BlockEntityType.Builder.of(AdvancedForgeBlockEntity::new, BlockInit.ADVANCED_FORGE.get())
					.build(null));

	public static final RegistryObject<BlockEntityType<JawCrusherBlockEntity>> JAW_CRUSHER_TE =
			BLOCK_ENTITIES.register("jaw_crusher", () -> BlockEntityType.Builder.of(JawCrusherBlockEntity::new, BlockInit.JAW_CRUSHER.get()).build(null));
	
	public static void register(IEventBus bus) {
		BLOCK_ENTITIES.register(bus);
	}
}
