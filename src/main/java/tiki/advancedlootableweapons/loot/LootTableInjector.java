package tiki.advancedlootableweapons.loot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryEmpty;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.EnchantRandomly;
import net.minecraft.world.storage.loot.functions.EnchantWithLevels;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.EnchantmentInit;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.loot.function.SetBonusDamage;
import tiki.advancedlootableweapons.loot.function.SetBonusDur;
import tiki.advancedlootableweapons.loot.function.SetItemFromArray;
import tiki.advancedlootableweapons.loot.function.SetLore;
import tiki.advancedlootableweapons.loot.function.SetName;
import tiki.advancedlootableweapons.loot.function.SetNameConcat;
import tiki.advancedlootableweapons.tools.ToolSlashSword;
import tiki.advancedlootableweapons.tools.ToolStabSword;

public class LootTableInjector {

	public static void InjectLoot(final LootTableLoadEvent event) {
		//To test: /setblock ~ ~ ~ chest 0 replace {LootTable:"minecraft:chests/abandoned_mineshaft"}
		if(event.getName().toString().startsWith("minecraft:chests")) { //minecraft is 10 length, 18 marks the backslash
			InjectChestLoot(event.getName().toString().substring(19), event.getTable());
		}else if(event.getName().toString().startsWith("minecraft:entities")) {
			InjectEntityLoot(event.getName().toString().substring(21), event.getTable());
		}
		
	}
	
	public static void InjectChestLoot(String name, LootTable table) {
		switch(name) {
		case "abandoned_mineshaft":
			LootEntry loadedMineshaft = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/abandoned_mineshaft"), 100, 1, new LootCondition[] {}, "alw_mineshaft_inject");
			//start weapon pool				
			Item[] MineshaftDaggers = getWeaponByNameAndMaterials("dagger", ItemInit.MAT_BRONZE, ItemInit.MAT_COPPER, ItemInit.MAT_KOBOLD, ItemInit.MAT_SILVER, ToolMaterial.IRON, ToolMaterial.WOOD);
			
			LootEntry weaponMineshaft = new LootEntryItem(MineshaftDaggers[0], 3, 1, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, MineshaftDaggers),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(2.5F, 4.5F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(0.0F, 25.0F)),
					new SetName(new LootCondition[] {}, "Explorer's Pocket Knife"),
					new SetLore(new LootCondition[] {new RandomChance(0.001F)}, "It's dangerous to go alone, take this!"),
					new SetDamage(new LootCondition[] {}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.18F)}, new RandomValueRange(1.0F, 18.0F), false)
					}, 
					new LootCondition[] {}, 
					"alw_weapon_mineshaft_inject");
			//end weapon pool
			table.addPool(new LootPool(new LootEntry[] {loadedMineshaft, weaponMineshaft},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_abandoned_mineshaft_inject"));
			break;
		case "desert_pyramid":
			LootEntry loadedPyramid = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/desert_pyramid"), 100, 1, new LootCondition[] {}, "alw_desert_pyramid_inject");
			//start weapon pool
			Item[] PyramidTalwars = getWeaponByNameAndMaterials("talwar", ItemInit.MAT_BRONZE, ItemInit.MAT_COPPER, ItemInit.MAT_KOBOLD, ItemInit.MAT_SILVER, ItemInit.MAT_STEEL, ToolMaterial.WOOD);
			
			LootEntry weaponPyramid = new LootEntryItem(PyramidTalwars[0], 6, 1, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, PyramidTalwars),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(1.5F, 4.5F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(0.0F, 80.0F)),
					new SetName(new LootCondition[] {}, "Sacred Sword"),
					new SetName(new LootCondition[] {new RandomChance(0.2F)}, "Giza's Blade"),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "...Are also often lost in time."),
					new SetDamage(new LootCondition[] {new RandomChance(0.15F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.31F)}, new RandomValueRange(5.0F, 21.0F), false)
					}, 
					new LootCondition[] {}, 
					"alw_weapon_pyramid_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedPyramid, weaponPyramid},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(1.0F), ModInfo.ID + "_desert_pyramid_inject"));
			break;
		case "end_city_treasure":
			LootEntry loadedEndCity = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/end_city_treasure"), 100, 1, new LootCondition[] {}, "alw_end_treasure_inject");
			//start weapon pool
			Item[] EndCityWeapons = getAnyWeaponByMaterials(ItemInit.MAT_BRONZE, ItemInit.MAT_CRYSTALLITE, ItemInit.MAT_DUSKSTEEL, ItemInit.MAT_FROST_STEEL, ItemInit.MAT_OBSIDIAN,
					ItemInit.MAT_PLATINUM, ItemInit.MAT_SHADOW_PLATINUM, ItemInit.MAT_STEEL);
			
			LootEntry weaponEndCity = new LootEntryItem(EndCityWeapons[0], 22, 4, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, EndCityWeapons),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(3.5F, 7.0F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(45.0F, 100.0F)),
					new SetNameConcat(new LootCondition[] {}, "Mysterious ", true),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "Signs of the Successful"),
					new SetDamage(new LootCondition[] {new RandomChance(0.25F)}, new RandomValueRange(0.1F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.84F)}, new RandomValueRange(12.0F, 30.0F), false)
					}, 
					new LootCondition[] {}, 
					"alw_weapon_end_city_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedEndCity},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(1.0F), ModInfo.ID + "_end_city_treasure_inject"));
			table.addPool(new LootPool(
					new LootEntry[] {weaponEndCity, new LootEntryEmpty(78, 0, new LootCondition[] {}, "alw_weapon_end_city_empty_inject")},
					new LootCondition[] {}, new RandomValueRange(1.0F, 3.0F), new RandomValueRange(1.0F), ModInfo.ID + "_end_city_treasure_weapon_inject"));
			break;
		case "igloo_chest":
			LootEntry loadedIgloo = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/igloo_chest"), 100, 1, new LootCondition[] {}, "alw_igloo_inject");
			table.addPool(new LootPool(
					new LootEntry[] {loadedIgloo},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_igloo_chest_inject"));
			break;
		case "jungle_temple":
			LootEntry loadedJungleTemple = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/jungle_temple"), 100, 1, new LootCondition[] {}, "alw_jungle_temple_inject");
			//start weapon pool
			List<Item> JungleTempleWeapons = new ArrayList<Item>();
			JungleTempleWeapons.addAll(Arrays.asList(getWeaponByNameAndMaterials("sabre", ItemInit.MAT_BRONZE, ItemInit.MAT_STEEL, ToolMaterial.WOOD, ToolMaterial.IRON)));
			JungleTempleWeapons.addAll(Arrays.asList(getWeaponByNameAndMaterials("makhaira", ItemInit.MAT_BRONZE, ItemInit.MAT_STEEL, ItemInit.MAT_KOBOLD, ToolMaterial.IRON)));
			
			LootEntry weaponJungleTemple = new LootEntryItem(JungleTempleWeapons.get(0), 7, 1, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, JungleTempleWeapons.toArray(new Item[0])),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(2.0F, 5.0F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(0.0F, 20.0F)),
					new SetName(new LootCondition[] {}, "Explorer's Machete"),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "Those who lose their way..."),
					new SetDamage(new LootCondition[] {new RandomChance(0.1F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.24F)}, new RandomValueRange(1.0F, 20.0F), false)
			}, new LootCondition[] {}, "alw_weapon_jungle_temple_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedJungleTemple, weaponJungleTemple},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_jungle_temple_inject"));
			break;
		case "nether_bridge":
			LootEntry loadedNether = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/nether_bridge"), 100, 1, new LootCondition[] {}, "alw_nether_bridge_inject");
			//start weapon pool
			List<Item> NetherWeapons = new ArrayList<Item>();
			NetherWeapons.addAll(Arrays.asList(getWeaponByNameAndMaterials("zweihander", ItemInit.MAT_OBSIDIAN, ItemInit.MAT_BRONZE, ItemInit.MAT_STEEL)));
			NetherWeapons.addAll(Arrays.asList(getWeaponByNameAndMaterials("nodachi", ItemInit.MAT_OBSIDIAN, ItemInit.MAT_BRONZE, ItemInit.MAT_KOBOLD)));
			NetherWeapons.addAll(Arrays.asList(getWeaponByNameAndMaterials("cleaver", ItemInit.MAT_OBSIDIAN, ItemInit.MAT_BRONZE, ItemInit.MAT_STEEL, ItemInit.MAT_SHADOW_PLATINUM)));
			
			LootEntry weaponNether = new LootEntryItem(NetherWeapons.get(0), 6, 2, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, NetherWeapons.toArray(new Item[0])),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(2.5F, 5.5F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(30.0F, 55.0F)),
					new SetName(new LootCondition[] {}, "Hellforged Blade"),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "Proof of the Lost"),
					new SetDamage(new LootCondition[] {new RandomChance(0.15F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.21F)}, new RandomValueRange(6.0F, 20.0F), false)
			}, new LootCondition[] {}, "alw_weapon_nether_inject");
			
			List<Enchantment> netherEnchs = new ArrayList<Enchantment>(1);
			netherEnchs.add(Enchantments.VANISHING_CURSE);
			LootEntry weaponNetherCursed = new LootEntryItem(NetherWeapons.get(0), 6, 2, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, NetherWeapons.toArray(new Item[0])),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(4.0F, 6.5F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(-20.0F, 0.0F)),
					new SetName(new LootCondition[] {}, "Cursed Blade"),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "Proof of the Lost"),
					new SetDamage(new LootCondition[] {new RandomChance(0.15F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantRandomly(new LootCondition[] {}, netherEnchs),
					new EnchantWithLevels(new LootCondition[] {}, new RandomValueRange(6.0F, 20.0F), false)
			}, new LootCondition[] {}, "alw_cursed_weapon_nether_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedNether}, 
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(2.0F), ModInfo.ID + "_nether_bridge_inject"));
			table.addPool(new LootPool(
					new LootEntry[] {weaponNether, weaponNetherCursed, new LootEntryEmpty(82, 0, new LootCondition[] {}, "_alw_nether_weapon_empty_inject")},
					new LootCondition[] {}, new RandomValueRange(0.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_nether_bridge_weapon_inject"));
			break;
		case "simple_dungeon":
			LootEntry loadedDungeon = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/simple_dungeon"), 100, 1, new LootCondition[] {}, "alw_dungeon_inject");
			
			table.addPool(new LootPool(
					new LootEntry[] {loadedDungeon},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_simple_dungeon_inject"));
			break;
		case "spawn_bonus_chest":
			LootEntry loadedSpawnChest = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/spawn_bonus_chest"), 100, 1, new LootCondition[] {}, "alw_spawn_chest_inject");
			
			table.addPool(new LootPool(
					new LootEntry[] {loadedSpawnChest},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_spawn_bonus_chest_inject"));
			break;
		case "stronghold_corridor":
			LootEntry loadedCorridor = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/stronghold_corridor"), 100, 1, new LootCondition[] {}, "alw_corridor_inject");
			//start weapon pool
			Item[] CorridorWeapons = getAnyWeaponByMaterials(ItemInit.MAT_KOBOLD, ItemInit.MAT_BRONZE, ItemInit.MAT_STEEL, ItemInit.MAT_PLATINUM, ItemInit.MAT_FROST_STEEL);
			
			List<Enchantment> corridorEnchs = new ArrayList<Enchantment>(1);
			corridorEnchs.add(Enchantments.SHARPNESS);
			corridorEnchs.add(EnchantmentInit.REFINED);
			corridorEnchs.add(Enchantments.SMITE);
			LootEntry weaponCorridor = new LootEntryItem(CorridorWeapons[0], 6, 2, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, CorridorWeapons),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(1.0F, 5.5F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(0.0F, 60.0F)),
					new SetName(new LootCondition[] {}, "Shimmering Edge"),
					new SetName(new LootCondition[] {new RandomChance(0.4F)}, "Sharp Edge"),
					new SetLore(new LootCondition[] {new RandomChance(0.01F)}, "Save your Breath"),
					new SetDamage(new LootCondition[] {new RandomChance(0.15F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantRandomly(new LootCondition[] {new RandomChance(0.20F)}, corridorEnchs),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.08F)}, new RandomValueRange(8.0F, 22.0F), false)
			}, new LootCondition[] {}, "alw_weapon_corridor_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedCorridor, weaponCorridor},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_stronghold_corridor_inject"));
			break;
		case "stronghold_crossing":
			LootEntry loadedCrossing = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/stronghold_crossing"), 100, 1, new LootCondition[] {}, "alw_crossing_inject");
			//start weapon pool
			Item[] CrossingWeapons = getAnyWeaponByMaterials(ItemInit.MAT_KOBOLD, ItemInit.MAT_SILVER, ItemInit.MAT_PLATINUM, ItemInit.MAT_OBSIDIAN);
			
			LootEntry weaponCrossing = new LootEntryItem(CrossingWeapons[0], 9, 2, new LootFunction[] {
					new SetItemFromArray(new LootCondition[] {}, CrossingWeapons),
					new SetBonusDamage(new LootCondition[] {}, new RandomValueRange(1.5F, 5.0F)),
					new SetBonusDur(new LootCondition[] {}, new RandomValueRange(15.0F, 40.0F)),
					new SetName(new LootCondition[] {}, "Shimmering Edge"),
					new SetName(new LootCondition[] {new RandomChance(0.4F)}, "Sharp Edge"),
					new SetDamage(new LootCondition[] {new RandomChance(0.15F)}, new RandomValueRange(0.3F, 0.99F)),
					new EnchantWithLevels(new LootCondition[] {new RandomChance(0.29F)}, new RandomValueRange(4.0F, 18.0F), false)
			}, new LootCondition[] {}, "alw_weapon_crossing_inject");
			//end weapon pool
			table.addPool(new LootPool(
					new LootEntry[] {loadedCrossing, weaponCrossing},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_stronghold_crossing_inject"));
			break;
		case "stronghold_library":
			
			break;
		case "village_blacksmith":
			LootEntry loadedBlacksmith = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/village_blacksmith"), 100, 1, new LootCondition[] {}, "alw_blacksmith_inject");
			
			table.addPool(new LootPool(
					new LootEntry[] {loadedBlacksmith},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_village_blacksmith_inject"));
			break;
		case "woodland_mansion":
			LootEntry loadedMansion = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/chest/woodland_mansion"), 100, 1, new LootCondition[] {}, "alw_mansion_inject");
			
			table.addPool(new LootPool(
					new LootEntry[] {loadedMansion},
					new LootCondition[] {}, new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_woodland_mansion_inject"));
			break;
		}
	}
	
	public static void InjectEntityLoot(String name, LootTable table) {
		switch(name) {
		case "evocation_illager":
			if(!ConfigHandler.EVOKER_DROP_SHADOW) {
				break;
			}
		case "vindication_illager":
			if(!ConfigHandler.VINDICATOR_DROP_SHADOW) {
				break;
			}
		case "vex":
			if(!ConfigHandler.VEX_DROP_SHADOW) {
				break;
			}
			LootEntry loadedGenericShadow = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/entity/drop_shadow_generic"), 100, 1, new LootCondition[] {}, "alw_generic_shadow_inject");
			table.addPool(new LootPool(new LootEntry[] {loadedGenericShadow}, new LootCondition[] {}, 
					new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_generic_shadow_inject"));
			break;
		case "wither_skeleton":
			if(!ConfigHandler.WITHERSKELETON_DROP_SHADOW) {
				break;
			}
			LootEntry loadedWitherSkeleton = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/entity/wither_skeleton"), 100, 1, new LootCondition[] {}, "alw_wither_skeleton_inject");
			table.addPool(new LootPool(new LootEntry[] {loadedWitherSkeleton}, new LootCondition[] {}, 
					new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_wither_skeleton_inject"));
			break;
		case "cow":
		case "donkey":
		case "horse":
		case "llama":
		case "mule":
		case "mushroom_cow":
			LootEntry loadedHideGeneric = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/entity/hide_generic"), 1, 1, new LootCondition[] {}, "alw_hide_generic_inject");
			table.removePool("main");
			table.addPool(new LootPool(new LootEntry[] {loadedHideGeneric}, new LootCondition[] {}, 
					new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_hide_generic_inject"));
			break;
		case "rabbit":
			LootEntry loadedRabbit = new LootEntryTable(new ResourceLocation(ModInfo.ID, "inject/entity/rabbit"), 1, 1, new LootCondition[] {}, "alw_rabbit_inject");
			table.removePool("main");
			table.addPool(new LootPool(new LootEntry[] {loadedRabbit}, new LootCondition[] {}, 
					new RandomValueRange(1.0F, 1.0F), new RandomValueRange(0.0F), ModInfo.ID + "_rabbit_inject"));
			break;
		}
	}
	
	public static Item[] getWeaponByNameAndMaterials(String name, ToolMaterial... mats) {
		List<Item> arr = new ArrayList<Item>();
		weapon: for(Item item : ItemInit.weaponItems) {
			if(item.getRegistryName().getResourcePath().startsWith(name.toLowerCase())) {
				if(item instanceof ToolSlashSword) {
					for(ToolMaterial tm : mats) {
						if(tm == ((ToolSlashSword)item).getToolMaterial()) {
							arr.add(item);
							continue weapon;
						}
					}
				}else if(item instanceof ToolStabSword) {
					for(ToolMaterial tm : mats) {
						if(tm == ((ToolStabSword)item).getToolMaterial()) {
							arr.add(item);
							continue weapon;
						}
					}
				}
			}
		}
		return arr.toArray(new Item[0]);
	}
	
	public static Item[] getAnyWeaponByMaterials(ToolMaterial... mats) {
		List<Item> arr = new ArrayList<Item>();
		weapon: for(Item item : ItemInit.weaponItems) {
			if(item instanceof ToolSlashSword) {
				for(int i = 0; i < mats.length; i++) {
					ToolMaterial tm = mats[i];
					if(tm == ((ToolSlashSword)item).getToolMaterial()) {
						arr.add(item);
						continue weapon;
					}
				}
			}else if(item instanceof ToolStabSword) {
				for(int i = 0; i < mats.length; i++) {
					ToolMaterial tm = mats[i];
					if(tm == ((ToolStabSword)item).getToolMaterial()) {
						arr.add(item);
						continue weapon;
					}
				}
			}
		}
		return arr.toArray(new Item[0]);
	}
	
	
}
