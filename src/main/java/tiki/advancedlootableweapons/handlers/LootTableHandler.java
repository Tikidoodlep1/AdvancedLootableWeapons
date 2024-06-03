package tiki.advancedlootableweapons.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import tiki.advancedlootableweapons.ModInfo;

public class LootTableHandler {

	public static void registerAll() {
		// Chests
		register(new ResourceLocation(ModInfo.ID, "inject/chest/abandonded_mineshaft"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/desert_pyramid"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/end_city_treasure"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/igloo_chest"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/jungle_temple"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/nether_bridge"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/simple_dungeon"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/spawn_bonus_chest"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/stronghold_corridor"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/stronghold_crossing"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/village_blacksmith"));
		register(new ResourceLocation(ModInfo.ID, "inject/chest/woodland_mansion"));
		
		// Entities
		register(new ResourceLocation(ModInfo.ID, "inject/entity/drop_shadow_generic"));
		register(new ResourceLocation(ModInfo.ID, "inject/entity/wither_skeleton"));
	}
	
	public static void register(ResourceLocation name) {
		LootTableList.register(name);
	}
}
