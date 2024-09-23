package com.tiki.advancedlootableweapons.handlers.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigHandler {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static ForgeConfigSpec.BooleanValue USE_IMAGINARY_RESOURCES;
	
	public static ForgeConfigSpec.BooleanValue USE_CUSTOM_WEAPON_REACH;
	public static ForgeConfigSpec.BooleanValue ENABLE_DAGGERS;
	public static ForgeConfigSpec.BooleanValue ENABLE_KABUTOWARIS;
	public static ForgeConfigSpec.BooleanValue ENABLE_RAPIERS;
	public static ForgeConfigSpec.BooleanValue ENABLE_TALWARS;
	public static ForgeConfigSpec.BooleanValue ENABLE_CLEAVERS;
	public static ForgeConfigSpec.BooleanValue ENABLE_MACES;
	public static ForgeConfigSpec.BooleanValue ENABLE_STAFFS;
	public static ForgeConfigSpec.BooleanValue ENABLE_SPEARS;
	public static ForgeConfigSpec.BooleanValue ENABLE_LONGSWORDS;
	public static ForgeConfigSpec.BooleanValue ENABLE_KODACHIS;
	public static ForgeConfigSpec.BooleanValue ENABLE_BATTLEAXES;
	public static ForgeConfigSpec.BooleanValue ENABLE_ZWEIHANDERS;
	public static ForgeConfigSpec.BooleanValue ENABLE_NODACHIS;
	public static ForgeConfigSpec.BooleanValue ENABLE_SABRES;
	public static ForgeConfigSpec.BooleanValue ENABLE_MAKHAIRAS;
	public static ForgeConfigSpec.BooleanValue ENABLE_ARMORS;
	public static ForgeConfigSpec.BooleanValue ENABLE_ARMOR_FORGING;
	public static ForgeConfigSpec.BooleanValue ENABLE_ADVANCED_LEATHER_TANNING;
	public static ForgeConfigSpec.BooleanValue HIDE_REPLACEMENT;
	
	public static ForgeConfigSpec.DoubleValue GLOBAL_DAGGER_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_KABUTOWARI_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_RAPIER_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_TALWAR_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_CLEAVER_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_MACE_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_STAFF_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_SPEAR_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_LONGSWORD_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_KODACHI_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_BATTLEAXE_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_ZWEIHANDER_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_NODACHI_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_SABRE_BASE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue GLOBAL_MAKHAIRA_BASE_DAMAGE;
	
	public static ForgeConfigSpec.DoubleValue GLOBAL_DAGGER_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_KABUTOWARI_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_RAPIER_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_TALWAR_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_CLEAVER_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_MACE_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_STAFF_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_LONGSWORD_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_BATTLEAXE_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_ZWEIHANDER_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_KODACHI_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_NODACHI_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_SABRE_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_MAKHAIRA_ATTACK_SPEED;
	public static ForgeConfigSpec.DoubleValue GLOBAL_SPEAR_ATTACK_SPEED;
	
	public static ForgeConfigSpec.IntValue GLOBAL_DAGGER_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_KABUTOWARI_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_RAPIER_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_TALWAR_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_CLEAVER_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_MACE_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_STAFF_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_LONGSWORD_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_BATTLEAXE_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_ZWEIHANDER_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_KODACHI_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_NODACHI_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_SABRE_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_MAKHAIRA_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_SPEAR_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_CHAIN_LINK_CRAFTING_EXP;
	public static ForgeConfigSpec.IntValue GLOBAL_ARMOR_PLATE_CRAFTING_EXP;
	
	public static ForgeConfigSpec.BooleanValue USE_ARMOR_WEIGHT;
	public static ForgeConfigSpec.BooleanValue USE_ARMOR_BONUS_HEALTH;
	public static ForgeConfigSpec.BooleanValue USE_ARMOR_BONUS_DAMAGE;
	public static ForgeConfigSpec.DoubleValue ARMOR_BONUS_HEALTH_MULTIPLIER;
	public static ForgeConfigSpec.DoubleValue ARMOR_BONUS_DAMAGE_MULTIPLIER;
	
	public static ForgeConfigSpec.DoubleValue KOBOLD_STEEL_DAMAGE;
	public static ForgeConfigSpec.DoubleValue COPPER_DAMAGE;
	public static ForgeConfigSpec.DoubleValue SILVER_DAMAGE;
	public static ForgeConfigSpec.DoubleValue BRONZE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue PLATINUM_DAMAGE;
	public static ForgeConfigSpec.DoubleValue STEEL_DAMAGE;
	public static ForgeConfigSpec.DoubleValue SHADOW_PLATINUM_DAMAGE;
	public static ForgeConfigSpec.DoubleValue FROST_STEEL_DAMAGE;
	public static ForgeConfigSpec.DoubleValue REFINED_OBSIDIAN_DAMAGE;
	public static ForgeConfigSpec.DoubleValue CRYSTALLITE_DAMAGE;
	public static ForgeConfigSpec.DoubleValue DUSKSTEEL_DAMAGE;
	public static ForgeConfigSpec.IntValue KOBOLD_STEEL_DURABILITY;
	public static ForgeConfigSpec.IntValue COPPER_DURABILITY;
	public static ForgeConfigSpec.IntValue SILVER_DURABILITY;
	public static ForgeConfigSpec.IntValue BRONZE_DURABILITY;
	public static ForgeConfigSpec.IntValue PLATINUM_DURABILITY;
	public static ForgeConfigSpec.IntValue STEEL_DURABILITY;
	public static ForgeConfigSpec.IntValue SHADOW_PLATINUM_DURABILITY;
	public static ForgeConfigSpec.IntValue FROST_STEEL_DURABILITY;
	public static ForgeConfigSpec.IntValue REFINED_OBSIDIAN_DURABILITY;
	public static ForgeConfigSpec.IntValue CRYSTALLITE_DURABILITY;
	public static ForgeConfigSpec.IntValue DUSKSTEEL_DURABILITY;

	public static ForgeConfigSpec.IntValue DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY;


	public static ForgeConfigSpec.IntValue IRON_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue GOLD_ARMOR_DURABILITY;
	
	public static ForgeConfigSpec.IntValue KOBOLD_STEEL_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue COPPER_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue SILVER_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue BRONZE_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue PLATINUM_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue STEEL_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue SHADOW_PLATINUM_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue FROST_STEEL_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue OBSIDIAN_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue CRYSTALLITE_ARMOR_DURABILITY;
	public static ForgeConfigSpec.IntValue DUSKSTEEL_ARMOR_DURABILITY;

	public static ForgeConfigSpec.DoubleValue DIAMOND_STUDDED_LEATHER_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue DIAMOND_STUDDED_STEEL_ARMOR_HARDNESS;

	public static ForgeConfigSpec.DoubleValue IRON_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue GOLD_ARMOR_HARDNESS;

	public static ForgeConfigSpec.DoubleValue KOBOLD_STEEL_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue COPPER_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue SILVER_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue BRONZE_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue PLATINUM_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue STEEL_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue SHADOW_PLATINUM_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue FROST_STEEL_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue REFINED_OBSIDIAN_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue CRYSTALLITE_ARMOR_HARDNESS;
	public static ForgeConfigSpec.DoubleValue DUSKSTEEL_ARMOR_HARDNESS;

	public static ForgeConfigSpec.DoubleValue BELLOWS_EXHAUSTION;
	public static ForgeConfigSpec.DoubleValue FORGE_TEMP_DECREASE;

	public static ForgeConfigSpec.DoubleValue FORGE_TEMP_INCREASE;

	public static ForgeConfigSpec.IntValue SHADOW_DROP_RATE;
	
	static {
		BUILDER.push("Advanced Lootable Weapons Config");
		
		//Common Config Stuff
		BUILDER.comment("GENERAL OPTIONS");
		USE_IMAGINARY_RESOURCES = BUILDER.comment("Use the custom made resources Kobold Steel, Shadow Platinum, Frost Steel, Crystallite, Dusksteel. (True/False)").define("Use Imaginary Resources", true);
		USE_CUSTOM_WEAPON_REACH = BUILDER.comment("Use the custom set weapon reach. Disabling this will make all weapons have the same reach as a vanilla sword/tool. (True/False)").define("Use Custom Weapon Reach", true);
		ENABLE_DAGGERS = BUILDER.comment("Enable or disable daggers in-game. (True/False)").define("Enable Daggers", true);
		ENABLE_KABUTOWARIS = BUILDER.comment("Enable or disable kabutowaris in-game. (True/False)").define("Enable Kabutowaris", true);
		ENABLE_RAPIERS = BUILDER.comment("Enable or disable rapiers in-game. (True/False)").define("Enable Rapiers", true);
		ENABLE_TALWARS = BUILDER.comment("Enable or disable talwars in-game. (True/False)").define("Enable Talwars", true);
		ENABLE_CLEAVERS = BUILDER.comment("Enable or disable cleavers in-game. (True/False)").define("Enable Cleavers", true);
		ENABLE_MACES = BUILDER.comment("Enable or disable maces in-game. (True/False)").define("Enable Maces", true);
		ENABLE_STAFFS = BUILDER.comment("Enable or disable staffs in-game. (True/False)").define("Enable Staffs", true);
		ENABLE_SPEARS = BUILDER.comment("Enable or disable spears in-game. (True/False)").define("Enable Spears", true);
		ENABLE_LONGSWORDS = BUILDER.comment("Enable or disable longswords in-game. (True/False)").define("Enable Longswords", true);
		ENABLE_KODACHIS = BUILDER.comment("Enable or disable kodachis in-game. (True/False)").define("Enable Kodachis", true);
		ENABLE_BATTLEAXES = BUILDER.comment("Enable or disable battleaxes in-game. (True/False)").define("Enable Battleaxes", true);
		ENABLE_ZWEIHANDERS = BUILDER.comment("Enable or disable zweihanders in-game. (True/False)").define("Enable Zweihanders", true);
		ENABLE_NODACHIS = BUILDER.comment("Enable or disable nodachis in-game. (True/False)").define("Enable Nodachis", true);
		ENABLE_SABRES = BUILDER.comment("Enable or disable kodachis in-game. (True/False)").define("Enable Sabres", true);
		ENABLE_MAKHAIRAS = BUILDER.comment("Enable or disable makhairas in-game. (True/False)").define("Enable Makhairas", true);
		ENABLE_ARMORS = BUILDER.comment("Enable or disable armor made from the custom materials. If this is disabled, \\\"Enable Armor Forging\\\" will also be disabled by default. (True/False)").define("Enable Custom Armor Sets", true);
		ENABLE_ARMOR_FORGING = BUILDER.comment("Enable or diable the armor forging. If this is disabled, armors will be crafted using the vanilla recipes (True/false)").define("Enable Armor Forging", true);
		ENABLE_ADVANCED_LEATHER_TANNING = BUILDER.comment("Enable the advanced leather tanning process. This entails needing some way to transfer fluids aswell as iron to make tanning knives. (True/False)").define("Enable Advanced Leather Tanning", true);
		BELLOWS_EXHAUSTION = BUILDER.comment("The amount of exhaustion using a bellows gives the player.").defineInRange("Bellows Exhaustion", 0.1F, 0.0F, 20.0F);
		FORGE_TEMP_DECREASE = BUILDER.comment("Forge Temperature Decrease. Set to 0.0F to disable temperature drop.").defineInRange("Forge Temperature Decrease", 0.02F, 0.0F, 10.0F);
		FORGE_TEMP_INCREASE = BUILDER.comment("Forge Temperature Increase.").defineInRange("Forge Temperature Increase", 1, 0.0F, 20.0F);

		HIDE_REPLACEMENT = BUILDER.comment("Mobs drop untrimmed hides instead of leather and rabbit hide").define("hide_replacement", true);

		BUILDER.comment("MATERIAL MODIFICATION");
		KOBOLD_STEEL_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of kobold steel.").defineInRange("Kobold Steel Base Damage", 3F, 0F, 100F);
		COPPER_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of copper.").defineInRange("Copper Base Damage", 3.5F, 0F, 100F);
		SILVER_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of silver.").defineInRange("Silver Base Damage", 4F, 0F, 100F);
		BRONZE_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of bronze.").defineInRange("Bronze Base Damage", 4.25F, 0F, 100F);
		PLATINUM_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of platinum.").defineInRange("Platinum Base Damage", 4.75F, 0F, 100F);
		STEEL_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of steel.").defineInRange("Steel Base Damage", 5.5F, 0F, 100F);
		SHADOW_PLATINUM_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of shadow platinum.").defineInRange("Shadow Platinum Base Damage", 5.75F, 0F, 100F);
		FROST_STEEL_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of frost steel.").defineInRange("Frost Steel Base Damage", 6.0F, 0F, 100F);
		REFINED_OBSIDIAN_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of refined obsidian.").defineInRange("Obsidian Base Damage", 6.25F, 0F, 100F);
		CRYSTALLITE_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of crystallite.").defineInRange("Crystallite Base Damage", 6.75F, 0F, 100F);
		DUSKSTEEL_DAMAGE = BUILDER.comment("Use to change the damage of all wepaons that are made of dusksteel.").defineInRange("Dusksteel Damage", 7.5F, 0F, 100F);
		KOBOLD_STEEL_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of kobold steel have.").defineInRange("Kobold Steel Base Durability", 203, 0, 1000);
		COPPER_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of copper have.").defineInRange("Copper Base Durability", 256, 0, 1000);
		SILVER_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of silver have.").defineInRange("Silver Base Durability", 277, 0, 1000);
		BRONZE_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of bronze have.").defineInRange("Bronze Base Durability", 330, 0, 1000);
		PLATINUM_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of platinum have.").defineInRange("Platinum Base Durability", 212, 0, 1000);
		STEEL_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of steel have.").defineInRange("Steel Base Durability", 416, 0, 1000);
		SHADOW_PLATINUM_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of shadow platinum have.").defineInRange("Shadow Platinum Base Durability", 461, 0, 1000);
		FROST_STEEL_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of frost steel have.").defineInRange("Frost Steel Base Durability", 507, 0, 1000);
		REFINED_OBSIDIAN_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of refined obsidian have.").defineInRange("Obsidian Base Durability", 598, 0, 1000);
		CRYSTALLITE_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of crystallite have.").defineInRange("Crystallite Base Durability", 627, 0, 1000);
		DUSKSTEEL_DURABILITY = BUILDER.comment("Use to change the durability that the weapons made of dusksteel have.").defineInRange("Dusksteel Base Durability", 812, 0, 1000);

		DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of studded leather have.").defineInRange("Studded Leather Base Armor Durability", 5, 0, 1000);
		DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of studded steel have.").defineInRange("Studded Steel Base Armor Durability", 38, 0, 1000);

		IRON_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of iron have.").defineInRange("Iron Base Armor Durability", 15, 0, 1000);
		GOLD_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of gold have.").defineInRange("Gold Base Armor Durability", 7, 0, 1000);

		KOBOLD_STEEL_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of kobold steel have.").defineInRange("Kobold Steel Base Armor Durability", 20, 0, 1000);
		COPPER_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of copper have.").defineInRange("Copper Base Armor Durability", 25, 0, 1000);
		SILVER_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of silver have.").defineInRange("Silver Base Armor Durability", 27, 0, 1000);
		BRONZE_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of bronze have.").defineInRange("Bronze Base Armor Durability", 32, 0, 1000);
		PLATINUM_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of platinum have.").defineInRange("Platinum Base Armor Durability", 20, 0, 1000);
		STEEL_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of steel have.").defineInRange("Steel Base Armor Durability", 38, 0, 1000);
		SHADOW_PLATINUM_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of shadow platinum have.").defineInRange("Shadow Platinum Base Armor Durability", 44, 0, 1000);
		FROST_STEEL_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of frost steel have.").defineInRange("Frost Steel Base Armor Durability", 49, 0, 1000);
		OBSIDIAN_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of obsidian have.").defineInRange("Obsidian Base Armor Durability", 57, 0, 1000);
		CRYSTALLITE_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of crystallite have.").defineInRange("Crystallite Base Armor Durability", 60, 0, 1000);
		DUSKSTEEL_ARMOR_DURABILITY = BUILDER.comment("Use to change the durability that the armors made of dusksteel have.").defineInRange("Dusksteel Base Armor Durability", 78, 0, 1000);

		DIAMOND_STUDDED_LEATHER_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of diamond studded leather have.").defineInRange("Diamond Studded Leather Armor Toughness", 0d, 0, 100);
		DIAMOND_STUDDED_STEEL_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of diamond studded steel have.").defineInRange("Diamond Studded Steel Armor Toughness", 2.5, 0, 100);

		IRON_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of iron.").defineInRange("Iron Armor Toughness", 0, 0F, 100F);
		GOLD_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of gold.").defineInRange("Gold Armor Toughness", 0, 0F, 100F);

		KOBOLD_STEEL_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of kobold steel.").defineInRange("Kobold Steel Armor Toughness", 1.25F, 0F, 100F);
		COPPER_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of copper.").defineInRange("Copper Armor Toughness", 1.56F, 0F, 100F);
		SILVER_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of silver.").defineInRange("Silver Armor Toughness", 1.69F, 0F, 100F);
		BRONZE_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of bronze.").defineInRange("Bronze Armor Toughness", 2.0F, 0F, 100F);
		PLATINUM_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of platinum.").defineInRange("Platinum Armor Toughness", 1.25F, 0F, 100F);
		STEEL_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of steel.").defineInRange("Steel Armor Toughness", 2.5F, 0F, 100F);
		SHADOW_PLATINUM_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of shadow platinum.").defineInRange("Shadow Platinum Armor Toughness", 2.75F, 0F, 100F);
		FROST_STEEL_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of frost steel.").defineInRange("Frost Steel Armor Toughness", 3.06F, 0F, 100F);
		REFINED_OBSIDIAN_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of refined obsidian.").defineInRange("Obsidian Armor Toughness", 3.56F, 0F, 100F);
		CRYSTALLITE_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of crystallite.").defineInRange("Crystallite Armor Toughness", 3.75F, 0F, 100F);
		DUSKSTEEL_ARMOR_HARDNESS = BUILDER.comment("Use to change the armor toughness per piece of all armors that are made of dusksteel.").defineInRange("Dusksteel Armor Toughness", 4.88F, 0F, 100F);
		
		BUILDER.comment("WEAPON MODIFICATION");
		GLOBAL_DAGGER_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Daggers\" is true. This modifies the damage of all types of daggers, regardless of material.").defineInRange("Global Dagger Base Damage", 1.5F, 0.0F, 100.0F);
		GLOBAL_KABUTOWARI_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Kabutowaris\" is true. This modifies the damage of all types of kabutowaris, regardless of material.").defineInRange("Global Kabutowari Base Damage", 1.0F, 0.0F, 100.0F);
		GLOBAL_RAPIER_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Rapiers\" is true. This modifies the damage of all types of rapiers, regardless of material.").defineInRange("Global Rapier Base Damage", 0.5F, 0.0F, 100.0F);
		GLOBAL_TALWAR_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Talwars\" is true. This modifies the damage of all types of talwars, regardless of material.").defineInRange("Global Talwar Base Damage", 3.25F, 0.0F, 100.0F);
		GLOBAL_CLEAVER_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Cleavers\" is true. This modifies the damage of all types of cleavers, regardless of material.").defineInRange("Global Cleaver Base Damage", 4.5F, 0.0F, 100.0F);
		GLOBAL_MACE_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Maces\" is true. This modifies the damage of all types of maces, regardless of material.").defineInRange("Global Mace Base Damage", 2.5F, 0.0F, 100.0F);
		GLOBAL_STAFF_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Staffs\" is true. This modifies the damage of all types of staffs, regardless of material.").defineInRange("Global Staff Base Damage", 2.0F, 0.0F, 100.0F);
		GLOBAL_SPEAR_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Spears\" is true. This modifies the damage of all types of spears, regardless of material.").defineInRange("Global Spear Base Damage", 2.5F, 0.0F, 100.0F);
		GLOBAL_LONGSWORD_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Longswords\" is true. This modifies the damage of all types of longswords, regardless of material.").defineInRange("Global Longsword Base Damage", 3.5F, 0.0F, 100.0F);
		GLOBAL_KODACHI_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Kodachis\" is true. This modifies the damage of all types of kodachis, regardless of material.").defineInRange("Global Kodachi Base Damage", 0.75F, 0.0F, 100.0F);
		GLOBAL_BATTLEAXE_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Battleaxes\" is true. This modifies the damage of all types of battleaxes, regardless of material.").defineInRange("Global Battleaxe Base Damage", 4.0F, 0.0F, 100.0F);
		GLOBAL_ZWEIHANDER_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Zweihanders\" is true. This modifies the damage of all types of zweihanders, regardless of material.").defineInRange("Global Zweihander Base Damage", 3.75F, 0.0F, 100.0F);
		GLOBAL_NODACHI_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Nodachis\" is true. This modifies the damage of all types of nodachis, regardless of material.").defineInRange("Global Nodachi Base Damage", 3.65F, 0.0F, 100.0F);
		GLOBAL_SABRE_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Sabres\" is true. This modifies the damage of all types of sabres, regardless of material.").defineInRange("Global Sabre Base Damage", 3.0F, 0.0F, 100.0F);
		GLOBAL_MAKHAIRA_BASE_DAMAGE = BUILDER.comment("Will only work if \"Enable Makhairas\" is true. This modifies the damage of all types of makhairas, regardless of material.").defineInRange("Global Makhaira Base Damage", 2.75F, 0.0F, 100.0F);
		
		GLOBAL_DAGGER_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Daggers\" is true. This modifies the damage of all types of daggers, regardless of material.").defineInRange("Global Dagger Attack Speed", 3.2F, 0.0F, 100.0F);
		GLOBAL_KABUTOWARI_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Kabutowaris\" is true. This modifies the damage of all types of kabutowaris, regardless of material.").defineInRange("Global Kabutowari Attack Speed", 2.4F, 0.0F, 100.0F);
		GLOBAL_RAPIER_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Rapiers\" is true. This modifies the damage of all types of rapiers, regardless of material.").defineInRange("Global Rapier Attack Speed", 3.6F, 0.0F, 100.0F);
		GLOBAL_TALWAR_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Talwars\" is true. This modifies the damage of all types of talwars, regardless of material.").defineInRange("Global Talwar Attack Speed", 1.8F, 0.0F, 100.0F);
		GLOBAL_CLEAVER_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Cleavers\" is true. This modifies the damage of all types of cleavers, regardless of material.").defineInRange("Global Cleaver Attack Speed", 1.0F, 0.0F, 100.0F);
		GLOBAL_MACE_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Maces\" is true. This modifies the damage of all types of maces, regardless of material.").defineInRange("Global Mace Attack Speed", 2.2F, 0.0F, 100.0F);
		GLOBAL_STAFF_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Staffs\" is true. This modifies the damage of all types of staffs, regardless of material.").defineInRange("Global Staff Attack Speed", 1.6F, 0.0F, 100.0F);
		GLOBAL_LONGSWORD_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Spears\" is true. This modifies the damage of all types of spears, regardless of material.").defineInRange("Global Spear Attack Speed", 1.0F, 0.0F, 100.0F);
		GLOBAL_BATTLEAXE_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Longswords\" is true. This modifies the damage of all types of longswords, regardless of material.").defineInRange("Global Longsword Attack Speed", 1.8F, 0.0F, 100.0F);
		GLOBAL_ZWEIHANDER_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Kodachis\" is true. This modifies the damage of all types of kodachis, regardless of material.").defineInRange("Global Kodachi Attack Speed", 2.9F, 0.0F, 100.0F);
		GLOBAL_KODACHI_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Battleaxes\" is true. This modifies the damage of all types of battleaxes, regardless of material.").defineInRange("Global Battleaxe Attack Speed", 1.1F, 0.0F, 100.0F);
		GLOBAL_NODACHI_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Zweihanders\" is true. This modifies the damage of all types of zweihanders, regardless of material.").defineInRange("Global Zweihander Attack Speed", 1.3F, 0.0F, 100.0F);
		GLOBAL_SABRE_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Nodachis\" is true. This modifies the damage of all types of nodachis, regardless of material.").defineInRange("Global Nodachi Attack Speed", 1.4F, 0.0F, 100.0F);
		GLOBAL_MAKHAIRA_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Sabres\" is true. This modifies the damage of all types of sabres, regardless of material.").defineInRange("Global Sabre Attack Speed", 2.4F, 0.0F, 100.0F);
		GLOBAL_SPEAR_ATTACK_SPEED = BUILDER.comment("Will only work if \"Enable Makhairas\" is true. This modifies the damage of all types of makhairas, regardless of material.").defineInRange("Global Makhaira Attack Speed", 2.2F, 0.0F, 100.0F);
		
		GLOBAL_DAGGER_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Daggers\" is true. This modifies the exp you get from crafting daggers.").defineInRange("Global Dagger Crafting Exp", 2, 0, 100);
		GLOBAL_KABUTOWARI_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Kabutowaris\" is true. This modifies the exp you get from crafting kabutowaris.").defineInRange("Global Kabutowari Crafting Exp", 5, 0, 100);
		GLOBAL_RAPIER_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Rapiers\" is true. This modifies the exp you get from crafting rapiers.").defineInRange("Global Rapier Crafting Exp", 4, 0, 100);
		GLOBAL_TALWAR_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable talwars\" is true. This modifies the exp you get from crafting talwars.").defineInRange("Global Talwars Crafting Exp", 3, 0, 100);
		GLOBAL_CLEAVER_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Cleavers\" is true. This modifies the exp you get from crafting cleavers.").defineInRange("Global Cleaver Crafting Exp", 1, 0, 100);
		GLOBAL_MACE_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Maces\" is true. This modifies the exp you get from crafting maces.").defineInRange("Global Mace Crafting Exp", 3, 0, 100);
		GLOBAL_STAFF_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Staffs\" is true. This modifies the exp you get from crafting staffs.").defineInRange("Global Staff Crafting Exp", 6, 0, 100);
		GLOBAL_LONGSWORD_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Longswords\" is true. This modifies the exp you get from crafting longswords.").defineInRange("Global Longsword Crafting Exp", 4, 0, 100);
		GLOBAL_BATTLEAXE_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Battleaxes\" is true. This modifies the exp you get from crafting battleaxes.").defineInRange("Global Battleaxe Crafting Exp", 6, 0, 100);
		GLOBAL_ZWEIHANDER_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Zweihanders\" is true. This modifies the exp you get from crafting zweihanders.").defineInRange("Global Zweihanders Crafting Exp", 6, 0, 100);
		GLOBAL_KODACHI_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Kodachis\" is true. This modifies the exp you get from crafting kodachis.").defineInRange("Global Staff Kodachi Exp", 2, 0, 100);
		GLOBAL_NODACHI_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Nodahcis\" is true. This modifies the exp you get from crafting nodachis.").defineInRange("Global Staff Nodachi Exp", 4, 0, 100);
		GLOBAL_SABRE_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Sabres\" is true. This modifies the exp you get from crafting sabres.").defineInRange("Global Sabre Crafting Exp", 4, 0, 100);
		GLOBAL_MAKHAIRA_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Makhairas\" is true. This modifies the exp you get from crafting makhairas.").defineInRange("Global Makhaira Crafting Exp", 3, 0, 100);
		GLOBAL_SPEAR_CRAFTING_EXP = BUILDER.comment("Will only work if \"Enable Spears\" is true. This modifies the exp you get from crafting spears.").defineInRange("Global Spear Crafting Exp", 3, 0, 100);
		GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP = BUILDER.comment("This modifies the exp you get from crafting long weapon handles.").defineInRange("Global Long Weapon Handle Crafting Exp", 4, 0, 100);
		GLOBAL_CHAIN_LINK_CRAFTING_EXP = BUILDER.comment("This modifies the exp you get from crafting chain links.").defineInRange("Global Chain Link Crafting Exp", 2, 0, 100);
		GLOBAL_ARMOR_PLATE_CRAFTING_EXP = BUILDER.comment("This modifies the exp you get from crafting armor plates.").defineInRange("Global Armor Plate Crafting Exp", 4, 0, 100);
		
		BUILDER.comment("ARMOR MODIFICATION");
		USE_ARMOR_WEIGHT = BUILDER.comment("If enabled, armor will slow you down proportionate to it's real-world weight. (True/False)").define("Use Armor Weight", true);
		USE_ARMOR_BONUS_HEALTH = BUILDER.comment("If enabled, armor will give you bonus health when worn. (True/False)").define("Use Armor Bonus Health", false);
		USE_ARMOR_BONUS_DAMAGE = BUILDER.comment("If enabled, chestplates will make you deal extra damage equivalent to the real-world weight with the armor tier factored in. (True/False)").define("Use Armor Bonus Damage", true);
		ARMOR_BONUS_HEALTH_MULTIPLIER = BUILDER.comment("Only works if \"Use Armor Bonus Health\" is enabled. A multiplier for the bonus health given by armor.").defineInRange("Armor Bonus Health Multiplier", 1.0F, 0.0F, 10.0F);
		ARMOR_BONUS_DAMAGE_MULTIPLIER = BUILDER.comment("Only works if \"Use Armor Bonus Damage\" is enabled. A multiplier for the bonus damage given by chestplates.").defineInRange("Armor Bonus Damage Multiplier", 1.0F, 0.0F, 10.0F);
		
		BUILDER.comment("GENERAL SHADOW DROP MODIFICATION");
		SHADOW_DROP_RATE = BUILDER.comment("The drop rate for the shadow item in percentage, 5 = 5%").defineInRange("Shadow Drop Rate (Percent)", 5, 0, 100);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
