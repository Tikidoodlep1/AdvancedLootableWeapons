package com.tiki.advancedlootableweapons.handlers;

import java.io.File;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static Configuration config;
	
	public static boolean USE_IMAGINARY_RESOURCES = true;
	
	public static boolean USE_CUSTOM_WEAPON_REACH = true;
	public static boolean ENABLE_DAGGERS = true;
	public static boolean ENABLE_KABUTOWARIS = true;
	public static boolean ENABLE_RAPIERS = true;
	public static boolean ENABLE_TALWARS = true;
	public static boolean ENABLE_CLEAVERS = true;
	public static boolean ENABLE_MACES = true;
	public static boolean ENABLE_STAFFS = true;
	public static boolean ENABLE_SPEARS = true;
	public static boolean ENABLE_LONGSWORDS = true;
	public static boolean ENABLE_KODACHIS = true;
	public static boolean ENABLE_BATTLEAXES = true;
	public static boolean ENABLE_ZWEIHANDERS = true;
	public static boolean ENABLE_NODACHIS = true;
	public static boolean ENABLE_SABRES = true;
	public static boolean ENABLE_MAKHAIRAS = true;
	public static boolean ENABLE_ARMORS = true;
	
	public static float GLOBAL_DAGGER_BASE_DAMAGE = 1.5F;
	public static float GLOBAL_KABUTOWARI_BASE_DAMAGE = 1.0F;
	public static float GLOBAL_RAPIER_BASE_DAMAGE = 0.5F;
	public static float GLOBAL_TALWAR_BASE_DAMAGE = 3.25F;
	public static float GLOBAL_CLEAVER_BASE_DAMAGE = 4.5F;
	public static float GLOBAL_MACE_BASE_DAMAGE = 2.5F;
	public static float GLOBAL_STAFF_BASE_DAMAGE = 2.0F;
	public static float GLOBAL_SPEAR_BASE_DAMAGE = 2.5F;
	public static float GLOBAL_LONGSWORD_BASE_DAMAGE = 3.5F;
	public static float GLOBAL_KODACHI_BASE_DAMAGE = 0.75F;
	public static float GLOBAL_BATTLEAXE_BASE_DAMAGE = 4.0F;
	public static float GLOBAL_ZWEIHANDER_BASE_DAMAGE = 3.75F;
	public static float GLOBAL_NODACHI_BASE_DAMAGE = 3.65F;
	public static float GLOBAL_SABRE_BASE_DAMAGE = 3.0F;
	public static float GLOBAL_MAKHAIRA_BASE_DAMAGE = 2.75F;
	
	public static boolean USE_ARMOR_WEIGHT = true;
	public static boolean USE_ARMOR_BONUS_HEALTH = true;
	public static boolean USE_ARMOR_BONUS_DAMAGE = true;
	public static float ARMOR_BONUS_HEALTH_MULTIPLIER = 1.0F;
	public static float ARMOR_BONUS_DAMAGE_MULTIPLIER = 1.0F;
	
	public static float KOBOLD_DAMAGE = 3F;//old was 3.5
	public static float COPPER_DAMAGE = 3.5F;//old was 4.0
	public static float SILVER_DAMAGE = 4F;//old was 4.5
	public static float BRONZE_DAMAGE = 4.25F;//old was 4.75
	public static float PLATINUM_DAMAGE = 4.75F;//old was 5.25
	public static float STEEL_DAMAGE = 5.5F;//old was 6
	public static float SHADOW_PLATINUM_DAMAGE = 5.75F;//old was 6.25
	public static float FROST_STEEL_DAMAGE = 6.0F;//old was 6.5
	public static float OBSIDIAN_DAMAGE = 6.25F;//old was 6.75
	public static float CRYSTALLITE_DAMAGE = 6.75F;//old was 7.5
	public static float DUSKSTEEL_DAMAGE = 7.5F;//old was 8.25
	public static int KOBOLD_DURABILITY = 203;
	public static int COPPER_DURABILITY = 256;
	public static int SILVER_DURABILITY = 277;
	public static int BRONZE_DURABILITY = 330;
	public static int PLATINUM_DURABILITY = 212;
	public static int STEEL_DURABILITY = 416;
	public static int SHADOW_PLATINUM_DURABILITY = 461;
	public static int FROST_STEEL_DURABILITY = 507;
	public static int OBSIDIAN_DURABILITY = 598;
	public static int CRYSTALLITE_DURABILITY = 627;
	public static int DUSKSTEEL_DURABILITY = 812;
	
	public static int KOBOLD_ARMOR_DURABILITY = 20;
	public static int COPPER_ARMOR_DURABILITY = 25;
	public static int SILVER_ARMOR_DURABILITY = 27;
	public static int BRONZE_ARMOR_DURABILITY = 32;
	public static int PLATINUM_ARMOR_DURABILITY = 20;
	public static int STEEL_ARMOR_DURABILITY = 40;
	public static int SHADOW_PLATINUM_ARMOR_DURABILITY = 44;
	public static int FROST_STEEL_ARMOR_DURABILITY = 49;
	public static int OBSIDIAN_ARMOR_DURABILITY = 57;
	public static int CRYSTALLITE_ARMOR_DURABILITY = 60;
	public static int DUSKSTEEL_ARMOR_DURABILITY = 78;
	public static float KOBOLD_ARMOR_HARDNESS = 1.25F;
	public static float COPPER_ARMOR_HARDNESS = 1.56F;
	public static float SILVER_ARMOR_HARDNESS = 1.69F;
	public static float BRONZE_ARMOR_HARDNESS = 2.0F;
	public static float PLATINUM_ARMOR_HARDNESS = 1.25F;
	public static float STEEL_ARMOR_HARDNESS = 2.5F;
	public static float SHADOW_PLATINUM_ARMOR_HARDNESS = 2.75F;
	public static float FROST_STEEL_ARMOR_HARDNESS = 3.06F;
	public static float OBSIDIAN_ARMOR_HARDNESS = 3.56F;
	public static float CRYSTALLITE_ARMOR_HARDNESS = 3.75F;
	public static float DUSKSTEEL_ARMOR_HARDNESS = 4.88F;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		String category;
		
		//**********************************ARMOR MODIFICATION**********************************
		category = "ARMOR MODIFICATION";
		config.addCustomCategoryComment(category, "Armor modifications such as chestplates giving extra damage, armor slowing, etc");
		USE_ARMOR_WEIGHT = config.getBoolean("Use Armor Weight", category, true, "If enabled, armor will slow you down proportionate to it's real-world weight. (True/False)");
		USE_ARMOR_BONUS_HEALTH = config.getBoolean("Use Armor Bonus Health", category, true, "If enabled, armor will give you bonus health when worn. (True/False)");
		USE_ARMOR_BONUS_DAMAGE = config.getBoolean("Use Armor Bonus Damage", category, true, "If enabled, chestplates will make you deal extra damage equivalent to the real-world weight with the armor tier factored in. (True/False)");
		
		ARMOR_BONUS_HEALTH_MULTIPLIER = config.getFloat("Armor Bonus Health Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Health\" is enabled. A multiplier for the bonus health given by armor. (Min=0.1F, Default = 1.0F, Max = 10.0F)");
		ARMOR_BONUS_DAMAGE_MULTIPLIER = config.getFloat("Armor Bonus Damage Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Damage\" is enabled. A multiplier for the bonus damage given by chestplates. (Min=0.1F, Default = 1.0F, Max = 10.0F)");
		
		//**********************************WEAPON MODIFICATION**********************************
		category = "WEAPON MODIFICATION";
		config.addCustomCategoryComment(category, "Weapon Modifications such as setting base weapon damages");
		GLOBAL_DAGGER_BASE_DAMAGE = config.getFloat("Global Dagger Base Damage", category, 1.5F, 0.0F, 100.0F, "Will only work if \"Enable Daggers\" is true. This modifies the damage of all types of daggers, regardless of material. (Min = 0.0F, Default = 1.5F, Max = 100.0F)");
		GLOBAL_KABUTOWARI_BASE_DAMAGE = config.getFloat("Global Kabutowari Base Damage", category, 1.0F, 0.0F, 100.0F, "Will only work if \"Enable Kabutowaris\" is true. This modifies the damage of all types of kabutowaris, regardless of material. (Min = 0.0F, Default = 1.0F, Max = 100.0F)");
		GLOBAL_RAPIER_BASE_DAMAGE = config.getFloat("Global Rapier Base Damage", category, 0.5F, 0.0F, 100.0F, "Will only work if \"Enable Rapiers\" is true. This modifies the damage of all types of rapiers, regardless of material. (Min = 0.0F, Default = 0.5F, Max = 100.0F)");
		GLOBAL_TALWAR_BASE_DAMAGE = config.getFloat("Global Talwar Base Damage", category, 3.25F, 0.0F, 100.0F, "Will only work if \"Enable Talwars\" is true. This modifies the damage of all types of talwars, regardless of material. (Min = 0.0F, Default = 3.25F, Max = 100.0F)");
		GLOBAL_CLEAVER_BASE_DAMAGE = config.getFloat("Global Cleaver Base Damage", category, 4.5F, 0.0F, 100.0F, "Will only work if \"Enable Cleavers\" is true. This modifies the damage of all types of cleavers, regardless of material. (Min = 0.0F, Default = 3.65F, Max = 100.0F)");
		GLOBAL_MACE_BASE_DAMAGE = config.getFloat("Global Mace Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Maces\" is true. This modifies the damage of all types of maces, regardless of material. (Min = 0.0F, Default = 2.5F, Max = 100.0F)");
		GLOBAL_STAFF_BASE_DAMAGE = config.getFloat("Global Staff Base Damage", category, 2.0F, 0.0F, 100.0F, "Will only work if \"Enable Staffs\" is true. This modifies the damage of all types of staffs, regardless of material. (Min = 0.0F, Default = 2.0F, Max = 100.0F)");
		GLOBAL_SPEAR_BASE_DAMAGE = config.getFloat("Global Spear Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Spears\" is true. This modifies the damage of all types of spears, regardless of material. (Min = 0.0F, Default = 2.5F, Max = 100.0F)");
		GLOBAL_LONGSWORD_BASE_DAMAGE = config.getFloat("Global Longsword Base Damage", category, 3.5F, 0.0F, 100.0F, "Will only work if \"Enable Longswords\" is true. This modifies the damage of all types of longswords, regardless of material. (Min = 0.0F, Default = 3.5F, Max = 100.0F)");
		GLOBAL_KODACHI_BASE_DAMAGE = config.getFloat("Global Kodachi Base Damage", category, 0.75F, 0.0F, 100.0F, "Will only work if \"Enable Kodachis\" is true. This modifies the damage of all types of kodachis, regardless of material. (Min = 0.0F, Default = 0.75F, Max = 100.0F)");
		GLOBAL_BATTLEAXE_BASE_DAMAGE = config.getFloat("Global Battleaxe Base Damage", category, 4.0F, 0.0F, 100.0F, "Will only work if \"Enable Battleaxes\" is true. This modifies the damage of all types of battleaxes, regardless of material. (Min = 0.0F, Default = 4.0F, Max = 100.0F)");
		GLOBAL_ZWEIHANDER_BASE_DAMAGE = config.getFloat("Global Zweihander Base Damage", category, 3.75F, 0.0F, 100.0F, "Will only work if \"Enable Zweihanders\" is true. This modifies the damage of all types of zweihanders, regardless of material. (Min = 0.0F, Default = 3.75F, Max = 100.0F)");
		GLOBAL_NODACHI_BASE_DAMAGE = config.getFloat("Global Nodachi Base Damage", category, 3.65F, 0.0F, 100.0F, "Will only work if \"Enable Nodachis\" is true. This modifies the damage of all types of nodachis, regardless of material. (Min = 0.0F, Default = 3.65F, Max = 100.0F)");
		GLOBAL_SABRE_BASE_DAMAGE = config.getFloat("Global Sabre Base Damage", category, 3.0F, 0.0F, 100.0F, "Will only work if \"Enable Sabres\" is true. This modifies the damage of all types of sabres, regardless of material. (Min = 0.0F, Default = 3.0F, Max = 100.0F)");
		GLOBAL_MAKHAIRA_BASE_DAMAGE = config.getFloat("Global Makhaira Base Damage", category, 2.75F, 0.0F, 100.0F, "Will only work if \"Enable Makhairas\" is true. This modifies the damage of all types of makhairas, regardless of material. (Min = 0.0F, Default = 2.75F, Max = 100.0F)");
		
		//**********************************GENERAL MODIFICATION**********************************
		category = "GENERAL MODIFICATION";
		config.addCustomCategoryComment(category, "General modifications such as enabling or disabling certain items. || WARNING: DISABLING ITEMS WILL MAKE THEM VANISH IF THEY EXISTED IN ANY WORLD THAT IS LOADED.");
		USE_CUSTOM_WEAPON_REACH = config.getBoolean("Use Custom Weapon Reach", category, true, "Use the custom set weapon reach. Disabling this will make things like spears have the same reach as things like daggers. (True/False)");
		
		ENABLE_ARMORS = config.getBoolean("Enable Custom Armor Sets", category, true, "Enable or disable armor made from the custom materials. (True/False)");
		ENABLE_DAGGERS = config.getBoolean("Enable Daggers", category, true, "Enable or disable daggers in-game. (True/False)");
		ENABLE_KABUTOWARIS = config.getBoolean("Enable Kabutowaris", category, true, "Enable or disable Kabutowaris in-game. (True/False)");
		ENABLE_RAPIERS = config.getBoolean("Enable Rapiers", category, true, "Enable or disable rapiers in-game. (True/False)");
		ENABLE_TALWARS = config.getBoolean("Enable Talwars", category, true, "Enable or disable talwars in-game. (True/False)");
		ENABLE_CLEAVERS = config.getBoolean("Enable Cleavers", category, true, "Enable or disable cleavers in-game. (True/False)");
		ENABLE_MACES = config.getBoolean("Enable Maces", category, true, "Enable or disable maces in-game. (True/False)");
		ENABLE_STAFFS = config.getBoolean("Enable Staffs", category, true, "Enable or disable staffs in-game. (True/False)");
		ENABLE_SPEARS = config.getBoolean("Enable Spears", category, true, "Enable or disable spears in-game. (True/False)");
		ENABLE_LONGSWORDS = config.getBoolean("Enable Longswords", category, true, "Enable or disable longswords in-game. (True/False)");
		ENABLE_KODACHIS = config.getBoolean("Enable Kodachis", category, true, "Enable or disable kodachis in-game. (True/False)");
		ENABLE_BATTLEAXES = config.getBoolean("Enable Battleaxes", category, true, "Enable or disable battleaxes in-game. (True/False)");
		ENABLE_ZWEIHANDERS = config.getBoolean("Enable Zweihanders", category, true, "Enable or disable zweihanders in-game. (True/False)");
		ENABLE_NODACHIS = config.getBoolean("Enable Nodachis", category, true, "Enable or disable nodachis in-game. (True/False)");
		ENABLE_SABRES = config.getBoolean("Enable Sabres", category, true, "Enable or disable sabres in-game. (True/False)");
		ENABLE_MAKHAIRAS = config.getBoolean("Enable Makhairas", category, true, "Enable or disable makhairas in-game. (True/False)");
		
		//**********************************MATERIAL MODIFICATION**********************************
		category = "MATERIAL MODIFICATION";
		config.addCustomCategoryComment(category, "Modifications to the materials of all weapons/armors such as durability, damage, hardness, etc");
		KOBOLD_DAMAGE = config.getFloat("Kobold Steel Damage", category, 3.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of kobold steel. (Min = 0.0F, Default = 3.5F, Max = 100.0F)");
		COPPER_DAMAGE = config.getFloat("Copper Damage", category, 4.0F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of copper. (Min = 0.0F, Default = 4.0F, Max = 100.0F)");
		SILVER_DAMAGE = config.getFloat("Silver Damage", category, 4.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of silver. (Min = 0.0F, Default = 4.5F, Max = 100.0F)");
		BRONZE_DAMAGE = config.getFloat("Bronze Damage", category, 4.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of bronze. (Min = 0.0F, Default = 4.75F, Max = 100.0F)");
		PLATINUM_DAMAGE = config.getFloat("Platinum Damage", category, 5.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of platinum. (Min = 0.0F, Default = 5.25F, Max = 100.0F)");
		STEEL_DAMAGE = config.getFloat("Steel Damage", category, 6.0F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of steel. (Min = 0.0F, Default = 6.0F, Max = 100.0F)");
		SHADOW_PLATINUM_DAMAGE = config.getFloat("Shadow Platinum Damage", category, 6.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of shadow platinum. (Min = 0.0F, Default = 6.5F, Max = 100.0F)");
		FROST_STEEL_DAMAGE = config.getFloat("Frost Steel Damage", category, 6.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of frost steel. (Min = 0.0F, Default = 6.5F, Max = 100.0F)");
		OBSIDIAN_DAMAGE = config.getFloat("Obsidian Damage", category, 6.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of obsidian. (Min = 0.0F, Default = 6.75F, Max = 100.0F)");
		CRYSTALLITE_DAMAGE = config.getFloat("Crystallite Damage", category, 7.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of crystallite. (Min = 0.0F, Default = 7.5F, Max = 100.0F)");
		DUSKSTEEL_DAMAGE = config.getFloat("Dusksteel Damage", category, 8.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of dusksteel. (Min = 0.0F, Default = 8.25F, Max = 100.0F)");
		
		KOBOLD_DURABILITY = config.getInt("Kobold Steel Base Durability", category, 203, 0, 1000, "Use to change the durability that the weapons made of kobold steel have. (Min = 0, Default = 203, Max = 1000)");
		COPPER_DURABILITY = config.getInt("Copper Base Durability", category, 256, 0, 1000, "Use to change the durability that the weapons made of copper have. (Min = 0, Default = 256, Max = 1000)");
		SILVER_DURABILITY = config.getInt("Silver Base Durability", category, 277, 0, 1000, "Use to change the durability that the weapons made of silver have. (Min = 0, Default = 277, Max = 1000)");
		BRONZE_DURABILITY = config.getInt("Bronze Base Durability", category, 330, 0, 1000, "Use to change the durability that the weapons made of bronze have. (Min = 0, Default = 330, Max = 1000)");
		PLATINUM_DURABILITY = config.getInt("Platinum Base Durability", category, 121, 0, 1000, "Use to change the durability that the weapons made of platinum have. (Min = 0, Default = 121, Max = 1000)");
		STEEL_DURABILITY = config.getInt("Steel Base Durability", category, 416, 0, 1000, "Use to change the durability that the weapons made of steel have. (Min = 0, Default = 416, Max = 1000)");
		SHADOW_PLATINUM_DURABILITY = config.getInt("Shadow Platinum Base Durability", category, 461, 0, 1000, "Use to change the durability that the weapons made of shadow platinum have. (Min = 0, Default = 461, Max = 1000)");
		FROST_STEEL_DURABILITY = config.getInt("Frost Steel Base Durability", category, 507, 0, 1000, "Use to change the durability that the weapons made of frost steel have. (Min = 0, Default = 507, Max = 1000)");
		OBSIDIAN_DURABILITY = config.getInt("Obsidian Base Durability", category, 598, 0, 1000, "Use to change the durability that the weapons made of obsidian have. (Min = 0, Default = 598, Max = 1000)");
		CRYSTALLITE_DURABILITY = config.getInt("Crystallite Base Durability", category, 627, 0, 1000, "Use to change the durability that the weapons made of crystallite have. (Min = 0, Default = 627, Max = 1000)");
		DUSKSTEEL_DURABILITY = config.getInt("Dusksteel Base Durability", category, 812, 0, 1000, "Use to change the durability that the weapons made of dusksteel have. (Min = 0, Default = 812, Max = 1000)");
		
		KOBOLD_ARMOR_DURABILITY = config.getInt("Kobold Steel Base Armor Durability", category, 20, 0, 1000, "Use to change the durability that the armors made of kobold steel have. (Min = 0, Default = 20, Max = 1000)");
		COPPER_ARMOR_DURABILITY = config.getInt("Copper Base Armor Durability", category, 25, 0, 1000, "Use to change the durability that the armors made of copper have. (Min = 0, Default = 25, Max = 1000)");
		SILVER_ARMOR_DURABILITY = config.getInt("Silver Base Armor Durability", category, 27, 0, 1000, "Use to change the durability that the armors made of silver have. (Min = 0, Default = 27, Max = 1000)");
		BRONZE_ARMOR_DURABILITY = config.getInt("Bronze Base Armor Durability", category, 32, 0, 1000, "Use to change the durability that the armors made of bronze have. (Min = 0, Default = 32, Max = 1000)");
		PLATINUM_ARMOR_DURABILITY = config.getInt("Platinum Base Armor Durability", category, 20, 0, 1000, "Use to change the durability that the armors made of platinum have. (Min = 0, Default = 20, Max = 1000)");
		STEEL_ARMOR_DURABILITY = config.getInt("Steel Base Armor Durability", category, 40, 0, 1000, "Use to change the durability that the armors made of steel have. (Min = 0, Default = 40, Max = 1000)");
		SHADOW_PLATINUM_ARMOR_DURABILITY = config.getInt("Shadow Platinum Base Armor Durability", category, 44, 0, 1000, "Use to change the durability that the armors made of shadow platinum have. (Min = 0, Default = 44, Max = 1000)");
		FROST_STEEL_ARMOR_DURABILITY = config.getInt("Frost Steel Base Armor Durability", category, 49, 0, 1000, "Use to change the durability that the armors made of frost steel have. (Min = 0, Default = 49, Max = 1000)");
		OBSIDIAN_ARMOR_DURABILITY = config.getInt("Obsidian Base Armor Durability", category, 57, 0, 1000, "Use to change the durability that the armors made of obsidian have. (Min = 0, Default = 57, Max = 1000)");
		CRYSTALLITE_ARMOR_DURABILITY = config.getInt("Crystallite Base Armor Durability", category, 60, 0, 1000, "Use to change the durability that the armors made of crystallite have. (Min = 0, Default = 60, Max = 1000)");
		DUSKSTEEL_ARMOR_DURABILITY = config.getInt("Dusksteel Base Armor Durability", category, 78, 0, 1000, "Use to change the durability that the armors made of dusksteel have. (Min = 0, Default = 78, Max = 1000)");
		
		KOBOLD_ARMOR_HARDNESS = config.getFloat("Kobold Steel Armor Toughness", category, 1.25F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of kobold steel. (Min = 0.0F, Default = 1.25F, Max = 10.0F)");
		COPPER_ARMOR_HARDNESS = config.getFloat("Copper Armor Toughness", category, 1.56F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of copper. (Min = 0.0F, Default = 1.56F, Max = 10.0F)");
		SILVER_ARMOR_HARDNESS = config.getFloat("Silver Armor Toughness", category, 1.69F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of silver. (Min = 0.0F, Default = 1.69F, Max = 10.0F)");
		BRONZE_ARMOR_HARDNESS = config.getFloat("Bronze Armor Toughness", category, 2.0F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of bronze. (Min = 0.0F, Default = 2.0F, Max = 10.0F)");
		PLATINUM_ARMOR_HARDNESS = config.getFloat("Platinum Armor Toughness", category, 1.25F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of platinum. (Min = 0.0F, Default = 1.25F, Max = 10.0F)");
		STEEL_ARMOR_HARDNESS = config.getFloat("Steel Armor Toughness", category, 2.5F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of steel. (Min = 0.0F, Default = 2.5F, Max = 10.0F)");
		SHADOW_PLATINUM_ARMOR_HARDNESS = config.getFloat("Shadow Platinum Armor Toughness", category, 2.75F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of shadow platinum. (Min = 0.0F, Default = 2.75F, Max = 10.0F)");
		FROST_STEEL_ARMOR_HARDNESS = config.getFloat("Frost Steel Armor Toughness", category, 3.06F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of frost steel. (Min = 0.0F, Default = 3.06F, Max = 10.0F)");
		OBSIDIAN_ARMOR_HARDNESS = config.getFloat("Obsidian Armor Toughness", category, 3.56F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of obsidian. (Min = 0.0F, Default = 3.56F, Max = 10.0F)");
		CRYSTALLITE_ARMOR_HARDNESS = config.getFloat("Crystallite Armor Toughness", category, 3.75F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of crystallite. (Min = 0.0F, Default = 3.75F, Max = 10.0F)");
		DUSKSTEEL_ARMOR_HARDNESS = config.getFloat("Dusksteel Armor Toughness", category, 4.88F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of dusksteel. (Min = 0.0F, Default = 4.88F, Max = 10.0F)");
		
		//**********************************WORLD GEN MODIFICATION**********************************
		category = "WORLD GEN";
		config.addCustomCategoryComment(category, "Modifications related to world gen such as ores, imaginary resources, etc");
		USE_IMAGINARY_RESOURCES = config.getBoolean("Use Imaginary Resources", category, true, "Use the custom made resources Kobold Steel, Shadow Platinum, Frost Steel, Crystallite, Dusksteel. (True/False)");
		
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		Alw.config = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.config.mkdirs();
		init(new File(Alw.config.getPath(), ModInfo.ID + ".cfg"));
	}
}
