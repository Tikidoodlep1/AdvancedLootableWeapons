package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.te.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.blocks.te.ForgeBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ModInfo.ID);
	
	public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> ALLOY_FURNACE_TE = 
			BLOCK_ENTITIES.register("alloy_furnace_te", () -> BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new, BlockInit.BLOCK_ALLOY_FURNACE.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE_TE = 
			BLOCK_ENTITIES.register("forge_te", () -> BlockEntityType.Builder.of(ForgeBlockEntity::new, BlockInit.BLOCK_FORGE.get()).build(null));
	
	public static void register(IEventBus bus) {
		BLOCK_ENTITIES.register(bus);
	}
}
