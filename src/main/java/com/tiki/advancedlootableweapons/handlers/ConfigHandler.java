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
	public static boolean ENABLE_ARMOR_FORGING = true;
	public static boolean OVERRIDE_VANILLA_ARMORS = true;
	
	public static float GLOBAL_DAGGER_BASE_DAMAGE = 2.0F;
	public static float GLOBAL_KABUTOWARI_BASE_DAMAGE = 2.25F;
	public static float GLOBAL_RAPIER_BASE_DAMAGE = 1.5F;
	public static float GLOBAL_TALWAR_BASE_DAMAGE = 3.5F;
	public static float GLOBAL_CLEAVER_BASE_DAMAGE = 5.5F;
	public static float GLOBAL_MACE_BASE_DAMAGE = 2.75F;
	public static float GLOBAL_STAFF_BASE_DAMAGE = 3.0F;
	public static float GLOBAL_SPEAR_BASE_DAMAGE = 3.5F;
	public static float GLOBAL_LONGSWORD_BASE_DAMAGE = 3.5F;
	public static float GLOBAL_KODACHI_BASE_DAMAGE = 1.75F;
	public static float GLOBAL_BATTLEAXE_BASE_DAMAGE = 5.0F;
	public static float GLOBAL_ZWEIHANDER_BASE_DAMAGE = 4.75F;
	public static float GLOBAL_NODACHI_BASE_DAMAGE = 4.65F;
	public static float GLOBAL_SABRE_BASE_DAMAGE = 2.75F;
	public static float GLOBAL_MAKHAIRA_BASE_DAMAGE = 3.0F;
	
	public static float GLOBAL_DAGGER_ATTACK_SPEED = 3.2F;
	public static float GLOBAL_KABUTOWARI_ATTACK_SPEED = 2.4F;
	public static float GLOBAL_RAPIER_ATTACK_SPEED = 3.6F;
	public static float GLOBAL_TALWAR_ATTACK_SPEED = 1.8F;
	public static float GLOBAL_CLEAVER_ATTACK_SPEED = 1.0F;
	public static float GLOBAL_MACE_ATTACK_SPEED = 2.2F;
	public static float GLOBAL_STAFF_ATTACK_SPEED = 1.6F;
	public static float GLOBAL_LONGSWORD_ATTACK_SPEED = 1.8F;
	public static float GLOBAL_BATTLEAXE_ATTACK_SPEED = 1.3F;
	public static float GLOBAL_ZWEIHANDER_ATTACK_SPEED = 1.4F;
	public static float GLOBAL_KODACHI_ATTACK_SPEED = 2.9F;
	public static float GLOBAL_NODACHI_ATTACK_SPEED = 1.4F;
	public static float GLOBAL_SABRE_ATTACK_SPEED = 2.4F;
	public static float GLOBAL_MAKHAIRA_ATTACK_SPEED = 2.0F;
	public static float GLOBAL_SPEAR_ATTACK_SPEED = 1.0F;
	
	public static int GLOBAL_DAGGER_CRAFTING_EXP = 2;
	public static int GLOBAL_KABUTOWARI_CRAFTING_EXP = 5;
	public static int GLOBAL_RAPIER_CRAFTING_EXP = 4;
	public static int GLOBAL_TALWAR_CRAFTING_EXP = 3;
	public static int GLOBAL_CLEAVER_CRAFTING_EXP = 1;
	public static int GLOBAL_MACE_CRAFTING_EXP = 3;
	public static int GLOBAL_STAFF_CRAFTING_EXP = 6;
	public static int GLOBAL_LONGSWORD_CRAFTING_EXP = 4;
	public static int GLOBAL_BATTLEAXE_CRAFTING_EXP = 6;
	public static int GLOBAL_ZWEIHANDER_CRAFTING_EXP = 6;
	public static int GLOBAL_KODACHI_CRAFTING_EXP = 2;
	public static int GLOBAL_NODACHI_CRAFTING_EXP = 4;
	public static int GLOBAL_SABRE_CRAFTING_EXP = 4;
	public static int GLOBAL_MAKHAIRA_CRAFTING_EXP = 3;
	public static int GLOBAL_SPEAR_CRAFTING_EXP = 3;
	public static int GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP = 2;
	public static int GLOBAL_CHAIN_LINK_CRAFTING_EXP = 2;
	public static int GLOBAL_ARMOR_PLATE_CRAFTING_EXP = 4;
	
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
	
	public static float SHADOW_DROP_RATE = 0.05F;
	public static boolean LAND_MOBS_DROP_SHADOW = true;
	public static boolean AIR_MOBS_DROP_SHADOW = false;
	public static boolean WATER_MOBS_DROP_SHADOW = false;
	public static boolean PEACEFUL_MOBS_DROP_SHADOW = false;
	public static boolean NEUTRAL_MOBS_DROP_SHADOW = false;
	public static boolean HOSTILE_MOBS_DROP_SHADOW = true;
	public static boolean BOSS_DROPS_SHADOW = true;
	public static boolean ONLY_BOSS_DROPS_SHADOW = false;
	public static boolean NON_VANILLA_DROPS_SHADOW = true;
	
	public static boolean VILLAGER_DROP_SHADOW = true;
	public static boolean PARROT_DROP_SHADOW = true;
	public static boolean LLAMA_DROP_SHADOW = true;
	public static boolean POLARBEAR_DROP_SHADOW = true;
	public static boolean RABBIT_DROP_SHADOW = true;
	public static boolean HORSE_DROP_SHADOW = true;
	public static boolean IRONGOLEM_DROP_SHADOW = true;
	public static boolean OCELOT_DROP_SHADOW = true;
	public static boolean SNOWMAN_DROP_SHADOW = true;
	public static boolean MOOSHROOM_DROP_SHADOW = true;
	public static boolean WOLF_DROP_SHADOW = true;
	public static boolean SQUID_DROP_SHADOW = true;
	public static boolean CHICKEN_DROP_SHADOW = true;
	public static boolean SHEEP_DROP_SHADOW = true;
	public static boolean COW_DROP_SHADOW = true;
	public static boolean PIG_DROP_SHADOW = true;
	public static boolean BAT_DROP_SHADOW = true;
	public static boolean GUARDIAN_DROP_SHADOW = true;
	public static boolean WITHER_DROP_SHADOW = true;
	public static boolean PIGZOMBIE_DROP_SHADOW = true;
	public static boolean GIANTZOMBIE_DROP_SHADOW = true;
	public static boolean ILLUSIONILLAGER_DROP_SHADOW = true;
	public static boolean MULE_DROP_SHADOW = true;
	public static boolean DONKEY_DROP_SHADOW = true;
	public static boolean ZOMBIEHORSE_DROP_SHADOW = true;
	public static boolean SKELETONHORSE_DROP_SHADOW = true;
	public static boolean ZOMBIEVILLAGER_DROP_SHADOW = true;
	public static boolean WITHERSKELETON_DROP_SHADOW = true;
	public static boolean ELDERGUARDIAN_DROP_SHADOW = true;
	public static boolean ZOMBIE_DROP_SHADOW = true;
	public static boolean HUSK_DROP_SHADOW = true;
	public static boolean SKELETON_DROP_SHADOW = true;
	public static boolean STRAY_DROP_SHADOW = true;
	public static boolean BLAZE_DROP_SHADOW = true;
	public static boolean CREEPER_DROP_SHADOW = true;
	public static boolean GHAST_DROP_SHADOW = true;
	public static boolean MAGMACUBE_DROP_SHADOW = true;
	public static boolean SILVERFISH_DROP_SHADOW = true;
	public static boolean SLIME_DROP_SHADOW = true;
	public static boolean SPIDER_DROP_SHADOW = true;
	public static boolean CAVESPIDER_DROP_SHADOW = true;
	public static boolean WITCH_DROP_SHADOW = true;
	public static boolean VEX_DROP_SHADOW = true;
	public static boolean EVOKER_DROP_SHADOW = true;
	public static boolean VINDICATOR_DROP_SHADOW = true;
	public static boolean SHULKER_DROP_SHADOW = true;
	public static boolean DRAGON_DROP_SHADOW = true;
	public static boolean ENDERMAN_DROP_SHADOW = true;
	public static boolean ENDERMITE_DROP_SHADOW = true;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		String category;
		
		//**********************************ARMOR MODIFICATION**********************************
		category = "ARMOR MODIFICATION";
		config.addCustomCategoryComment(category, "Armor modifications such as chestplates giving extra damage, armor slowing, etc");
		USE_ARMOR_WEIGHT = config.getBoolean("Use Armor Weight", category, true, "If enabled, armor will slow you down proportionate to it's real-world weight. (True/False)");
		USE_ARMOR_BONUS_HEALTH = config.getBoolean("Use Armor Bonus Health", category, true, "If enabled, armor will give you bonus health when worn. (True/False)");
		USE_ARMOR_BONUS_DAMAGE = config.getBoolean("Use Armor Bonus Damage", category, true, "If enabled, chestplates will make you deal extra damage equivalent to the real-world weight with the armor tier factored in. (True/False)");
		
		ARMOR_BONUS_HEALTH_MULTIPLIER = config.getFloat("Armor Bonus Health Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Health\" is enabled. A multiplier for the bonus health given by armor.");
		ARMOR_BONUS_DAMAGE_MULTIPLIER = config.getFloat("Armor Bonus Damage Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Damage\" is enabled. A multiplier for the bonus damage given by chestplates.");
		
		//**********************************WEAPON MODIFICATION**********************************
		category = "WEAPON MODIFICATION";
		config.addCustomCategoryComment(category, "Weapon Modifications such as setting base weapon damages and attack speeds");
		GLOBAL_DAGGER_BASE_DAMAGE = config.getFloat("Global Dagger Base Damage", category, 1.5F, 0.0F, 100.0F, "Will only work if \"Enable Daggers\" is true. This modifies the damage of all types of daggers, regardless of material.");
		GLOBAL_KABUTOWARI_BASE_DAMAGE = config.getFloat("Global Kabutowari Base Damage", category, 1.0F, 0.0F, 100.0F, "Will only work if \"Enable Kabutowaris\" is true. This modifies the damage of all types of kabutowaris, regardless of material.");
		GLOBAL_RAPIER_BASE_DAMAGE = config.getFloat("Global Rapier Base Damage", category, 0.5F, 0.0F, 100.0F, "Will only work if \"Enable Rapiers\" is true. This modifies the damage of all types of rapiers, regardless of material.");
		GLOBAL_TALWAR_BASE_DAMAGE = config.getFloat("Global Talwar Base Damage", category, 3.25F, 0.0F, 100.0F, "Will only work if \"Enable Talwars\" is true. This modifies the damage of all types of talwars, regardless of material.");
		GLOBAL_CLEAVER_BASE_DAMAGE = config.getFloat("Global Cleaver Base Damage", category, 4.5F, 0.0F, 100.0F, "Will only work if \"Enable Cleavers\" is true. This modifies the damage of all types of cleavers, regardless of material.");
		GLOBAL_MACE_BASE_DAMAGE = config.getFloat("Global Mace Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Maces\" is true. This modifies the damage of all types of maces, regardless of material.");
		GLOBAL_STAFF_BASE_DAMAGE = config.getFloat("Global Staff Base Damage", category, 2.0F, 0.0F, 100.0F, "Will only work if \"Enable Staffs\" is true. This modifies the damage of all types of staffs, regardless of material.");
		GLOBAL_SPEAR_BASE_DAMAGE = config.getFloat("Global Spear Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Spears\" is true. This modifies the damage of all types of spears, regardless of material.");
		GLOBAL_LONGSWORD_BASE_DAMAGE = config.getFloat("Global Longsword Base Damage", category, 3.5F, 0.0F, 100.0F, "Will only work if \"Enable Longswords\" is true. This modifies the damage of all types of longswords, regardless of material.");
		GLOBAL_KODACHI_BASE_DAMAGE = config.getFloat("Global Kodachi Base Damage", category, 0.75F, 0.0F, 100.0F, "Will only work if \"Enable Kodachis\" is true. This modifies the damage of all types of kodachis, regardless of material.");
		GLOBAL_BATTLEAXE_BASE_DAMAGE = config.getFloat("Global Battleaxe Base Damage", category, 4.0F, 0.0F, 100.0F, "Will only work if \"Enable Battleaxes\" is true. This modifies the damage of all types of battleaxes, regardless of material.");
		GLOBAL_ZWEIHANDER_BASE_DAMAGE = config.getFloat("Global Zweihander Base Damage", category, 3.75F, 0.0F, 100.0F, "Will only work if \"Enable Zweihanders\" is true. This modifies the damage of all types of zweihanders, regardless of material.");
		GLOBAL_NODACHI_BASE_DAMAGE = config.getFloat("Global Nodachi Base Damage", category, 3.65F, 0.0F, 100.0F, "Will only work if \"Enable Nodachis\" is true. This modifies the damage of all types of nodachis, regardless of material.");
		GLOBAL_SABRE_BASE_DAMAGE = config.getFloat("Global Sabre Base Damage", category, 3.0F, 0.0F, 100.0F, "Will only work if \"Enable Sabres\" is true. This modifies the damage of all types of sabres, regardless of material.");
		GLOBAL_MAKHAIRA_BASE_DAMAGE = config.getFloat("Global Makhaira Base Damage", category, 2.75F, 0.0F, 100.0F, "Will only work if \"Enable Makhairas\" is true. This modifies the damage of all types of makhairas, regardless of material.");
		
		GLOBAL_DAGGER_ATTACK_SPEED = config.getFloat("Global Dagger Attack Speed", category, 3.2F, 0.0F, 6.0F, "Will only work if \"Enable Daggers\" is true. This modifies the attack speed of all types of daggers, regardless of material.");
		GLOBAL_KABUTOWARI_ATTACK_SPEED = config.getFloat("Global Kabutowari Attack Speed", category, 2.4F, 0.0F, 6.0F, "Will only work if \"Enable Kabutowaris\" is true. This modifies the attack speed of all types of kabutowaris, regardless of material.");
		GLOBAL_RAPIER_ATTACK_SPEED = config.getFloat("Global Rapier Attack Speed", category, 3.6F, 0.0F, 6.0F, "Will only work if \"Enable Rapiers\" is true. This modifies the attack speed of all types of rapiers, regardless of material.");
		GLOBAL_TALWAR_ATTACK_SPEED = config.getFloat("Global Talwar Attack Speed", category, 1.8F, 0.0F, 6.0F, "Will only work if \"Enable Talwars\" is true. This modifies the attack speed of all types of talwars, regardless of material.");
		GLOBAL_CLEAVER_ATTACK_SPEED = config.getFloat("Global Cleaver Attack Speed", category, 1.0F, 0.0F, 6.0F, "Will only work if \"Enable Cleavers\" is true. This modifies the attack speed of all types of cleavers, regardless of material.");
		GLOBAL_MACE_ATTACK_SPEED = config.getFloat("Global Mace Attack Speed", category, 2.2F, 0.0F, 6.0F, "Will only work if \"Enable Maces\" is true. This modifies the attack speed of all types of maces, regardless of material.");
		GLOBAL_STAFF_ATTACK_SPEED = config.getFloat("Global Staff Attack Speed", category, 1.6F, 0.0F, 6.0F, "Will only work if \"Enable Staffs\" is true. This modifies the attack speed of all types of staffs, regardless of material.");
		GLOBAL_SPEAR_ATTACK_SPEED = config.getFloat("Global Spear Attack Speed", category, 1.0F, 0.0F, 6.0F, "Will only work if \"Enable Spears\" is true. This modifies the attack speed of all types of spears, regardless of material.");
		GLOBAL_LONGSWORD_ATTACK_SPEED = config.getFloat("Global Longsword Attack Speed", category, 1.8F, 0.0F, 6.0F, "Will only work if \"Enable Longswords\" is true. This modifies the attack speed of all types of longswords, regardless of material.");
		GLOBAL_KODACHI_ATTACK_SPEED = config.getFloat("Global Kodachi Attack Speed", category, 2.9F, 0.0F, 6.0F, "Will only work if \"Enable Kodachis\" is true. This modifies the attack speed of all types of kodachis, regardless of material.");
		GLOBAL_BATTLEAXE_ATTACK_SPEED = config.getFloat("Global Battleaxe Attack Speed", category, 1.1F, 0.0F, 6.0F, "Will only work if \"Enable Battleaxes\" is true. This modifies the attack speed of all types of battleaxes, regardless of material.");
		GLOBAL_ZWEIHANDER_ATTACK_SPEED = config.getFloat("Global Zweihander Attack Speed", category, 1.3F, 0.0F, 6.0F, "Will only work if \"Enable Zweihanders\" is true. This modifies the attack speed of all types of zweihanders, regardless of material.");
		GLOBAL_NODACHI_ATTACK_SPEED = config.getFloat("Global Nodachi Attack Speed", category, 1.4F, 0.0F, 6.0F, "Will only work if \"Enable Nodachis\" is true. This modifies the attack speed of all types of nodachis, regardless of material.");
		GLOBAL_SABRE_ATTACK_SPEED = config.getFloat("Global Sabre Attack Speed", category, 2.4F, 0.0F, 6.0F, "Will only work if \"Enable Sabres\" is true. This modifies the attack speed of all types of sabres, regardless of material.");
		GLOBAL_MAKHAIRA_ATTACK_SPEED = config.getFloat("Global Makhaira Attack Speed", category, 2.2F, 0.0F, 6.0F, "Will only work if \"Enable Makhairas\" is true. This modifies the attack speed of all types of makhairas, regardless of material.");
		
		GLOBAL_DAGGER_CRAFTING_EXP = config.getInt("Global Dagger Crafting Exp", category, 2, 0, 100, "Will only work if \"Enable Daggers\" is true. This modifies the exp you get from crafting daggers.");
		GLOBAL_KABUTOWARI_CRAFTING_EXP = config.getInt("Global Kabutowari Crafting Exp", category, 5, 0, 100, "Will only work if \"Enable Kabutowaris\" is true. This modifies the exp you get from crafting kabutowaris.");
		GLOBAL_RAPIER_CRAFTING_EXP = config.getInt("Global Rapier Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Rapiers\" is true. This modifies the exp you get from crafting rapiers.");
		GLOBAL_TALWAR_CRAFTING_EXP = config.getInt("Global Talwars Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable talwars\" is true. This modifies the exp you get from crafting talwars.");
		GLOBAL_CLEAVER_CRAFTING_EXP = config.getInt("Global Cleaver Crafting Exp", category, 1, 0, 100, "Will only work if \"Enable Cleavers\" is true. This modifies the exp you get from crafting cleavers.");
		GLOBAL_MACE_CRAFTING_EXP = config.getInt("Global Mace Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Maces\" is true. This modifies the exp you get from crafting maces.");
		GLOBAL_STAFF_CRAFTING_EXP = config.getInt("Global Staff Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Staffs\" is true. This modifies the exp you get from crafting staffs.");
		GLOBAL_LONGSWORD_CRAFTING_EXP = config.getInt("Global Longsword Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Longswords\" is true. This modifies the exp you get from crafting longswords.");
		GLOBAL_BATTLEAXE_CRAFTING_EXP = config.getInt("Global Battleaxe Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Battleaxes\" is true. This modifies the exp you get from crafting battleaxes.");
		GLOBAL_ZWEIHANDER_CRAFTING_EXP = config.getInt("Global Zweihanders Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Zweihanders\" is true. This modifies the exp you get from crafting zweihanders.");
		GLOBAL_KODACHI_CRAFTING_EXP = config.getInt("Global Kodachi Crafting Exp", category, 2, 0, 100, "Will only work if \"Enable Kodachis\" is true. This modifies the exp you get from crafting kodachis.");
		GLOBAL_NODACHI_CRAFTING_EXP = config.getInt("Global Nodachi Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Nodahcis\" is true. This modifies the exp you get from crafting nodachis.");
		GLOBAL_SABRE_CRAFTING_EXP = config.getInt("Global Sabre Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Sabres\" is true. This modifies the exp you get from crafting sabres.");
		GLOBAL_MAKHAIRA_CRAFTING_EXP = config.getInt("Global Makhaira Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Makhairas\" is true. This modifies the exp you get from crafting makhairas.");
		GLOBAL_SPEAR_CRAFTING_EXP = config.getInt("Global Spear Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Spears\" is true. This modifies the exp you get from crafting spears.");
		GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP = config.getInt("Global Long Weapon Handle Crafting Exp", category, 4, 0, 100, "This modifies the exp you get from crafting long weapon handles.");
		GLOBAL_CHAIN_LINK_CRAFTING_EXP = config.getInt("Global Chain Link Crafting Exp", category, 2, 0, 100, "This modifies the exp you get from crafting chain links.");
		GLOBAL_ARMOR_PLATE_CRAFTING_EXP = config.getInt("Global Armor Plate Crafting Exp", category, 4, 0, 100, "This modifies the exp you get from crafting armor plates.");

		//**********************************GENERAL MODIFICATION**********************************
		category = "GENERAL MODIFICATION";
		config.addCustomCategoryComment(category, "General modifications such as enabling or disabling certain items. || WARNING: DISABLING ITEMS WILL MAKE THEM VANISH IF THEY EXISTED IN ANY WORLD THAT IS LOADED.");
		USE_CUSTOM_WEAPON_REACH = config.getBoolean("Use Custom Weapon Reach", category, true, "Use the custom set weapon reach. Disabling this will make things like spears have the same reach as things like daggers. (True/False)");
		
		ENABLE_ARMORS = config.getBoolean("Enable Custom Armor Sets", category, true, "Enable or disable armor made from the custom materials. (True/False)");
		ENABLE_ARMOR_FORGING = config.getBoolean("Enable Armor Forging", category, true, "Enable or diable the armor forging. If this is disabled, armors will be crafted using the vanilla recipes (True/false)");
		OVERRIDE_VANILLA_ARMORS = config.getBoolean("Override Vanilla Armors", category, true, "Should vanilla armors be given tiers, weights, bonus health, and bonus damage. (True/False)");
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
		category = "WEAPON MATERIAL MODIFICATION";
		config.addCustomCategoryComment(category, "Modifications to the materials of all weapons such as durability and damage");
		KOBOLD_DAMAGE = config.getFloat("Kobold Steel Damage", category, 3.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of kobold steel.");
		COPPER_DAMAGE = config.getFloat("Copper Damage", category, 4.0F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of copper.");
		SILVER_DAMAGE = config.getFloat("Silver Damage", category, 4.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of silver.");
		BRONZE_DAMAGE = config.getFloat("Bronze Damage", category, 4.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of bronze.");
		PLATINUM_DAMAGE = config.getFloat("Platinum Damage", category, 5.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of platinum.");
		STEEL_DAMAGE = config.getFloat("Steel Damage", category, 6.0F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of steel.");
		SHADOW_PLATINUM_DAMAGE = config.getFloat("Shadow Platinum Damage", category, 6.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of shadow platinum.");
		FROST_STEEL_DAMAGE = config.getFloat("Frost Steel Damage", category, 6.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of frost steel.");
		OBSIDIAN_DAMAGE = config.getFloat("Obsidian Damage", category, 6.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of obsidian.");
		CRYSTALLITE_DAMAGE = config.getFloat("Crystallite Damage", category, 7.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of crystallite.");
		DUSKSTEEL_DAMAGE = config.getFloat("Dusksteel Damage", category, 8.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of dusksteel.");
		
		KOBOLD_DURABILITY = config.getInt("Kobold Steel Base Durability", category, 203, 0, 1000, "Use to change the durability that the weapons made of kobold steel have.");
		COPPER_DURABILITY = config.getInt("Copper Base Durability", category, 256, 0, 1000, "Use to change the durability that the weapons made of copper have.");
		SILVER_DURABILITY = config.getInt("Silver Base Durability", category, 277, 0, 1000, "Use to change the durability that the weapons made of silver have.");
		BRONZE_DURABILITY = config.getInt("Bronze Base Durability", category, 330, 0, 1000, "Use to change the durability that the weapons made of bronze have.");
		PLATINUM_DURABILITY = config.getInt("Platinum Base Durability", category, 121, 0, 1000, "Use to change the durability that the weapons made of platinum have.");
		STEEL_DURABILITY = config.getInt("Steel Base Durability", category, 416, 0, 1000, "Use to change the durability that the weapons made of steel have.");
		SHADOW_PLATINUM_DURABILITY = config.getInt("Shadow Platinum Base Durability", category, 461, 0, 1000, "Use to change the durability that the weapons made of shadow platinum have.");
		FROST_STEEL_DURABILITY = config.getInt("Frost Steel Base Durability", category, 507, 0, 1000, "Use to change the durability that the weapons made of frost steel have.");
		OBSIDIAN_DURABILITY = config.getInt("Obsidian Base Durability", category, 598, 0, 1000, "Use to change the durability that the weapons made of obsidian have.");
		CRYSTALLITE_DURABILITY = config.getInt("Crystallite Base Durability", category, 627, 0, 1000, "Use to change the durability that the weapons made of crystallite have.");
		DUSKSTEEL_DURABILITY = config.getInt("Dusksteel Base Durability", category, 812, 0, 1000, "Use to change the durability that the weapons made of dusksteel have.");
		
		category = "ARMOR MATERIAL MODIFICATION";
		config.addCustomCategoryComment(category, "Modifications to the materials of all armors such as durability and hardness");
		KOBOLD_ARMOR_DURABILITY = config.getInt("Kobold Steel Base Armor Durability", category, 20, 0, 1000, "Use to change the durability that the armors made of kobold steel have.");
		COPPER_ARMOR_DURABILITY = config.getInt("Copper Base Armor Durability", category, 25, 0, 1000, "Use to change the durability that the armors made of copper have.");
		SILVER_ARMOR_DURABILITY = config.getInt("Silver Base Armor Durability", category, 27, 0, 1000, "Use to change the durability that the armors made of silver have.");
		BRONZE_ARMOR_DURABILITY = config.getInt("Bronze Base Armor Durability", category, 32, 0, 1000, "Use to change the durability that the armors made of bronze have.");
		PLATINUM_ARMOR_DURABILITY = config.getInt("Platinum Base Armor Durability", category, 20, 0, 1000, "Use to change the durability that the armors made of platinum have.");
		STEEL_ARMOR_DURABILITY = config.getInt("Steel Base Armor Durability", category, 40, 0, 1000, "Use to change the durability that the armors made of steel have.");
		SHADOW_PLATINUM_ARMOR_DURABILITY = config.getInt("Shadow Platinum Base Armor Durability", category, 44, 0, 1000, "Use to change the durability that the armors made of shadow platinum have.");
		FROST_STEEL_ARMOR_DURABILITY = config.getInt("Frost Steel Base Armor Durability", category, 49, 0, 1000, "Use to change the durability that the armors made of frost steel have.");
		OBSIDIAN_ARMOR_DURABILITY = config.getInt("Obsidian Base Armor Durability", category, 57, 0, 1000, "Use to change the durability that the armors made of obsidian have.");
		CRYSTALLITE_ARMOR_DURABILITY = config.getInt("Crystallite Base Armor Durability", category, 60, 0, 1000, "Use to change the durability that the armors made of crystallite have.");
		DUSKSTEEL_ARMOR_DURABILITY = config.getInt("Dusksteel Base Armor Durability", category, 78, 0, 1000, "Use to change the durability that the armors made of dusksteel have.");
		
		KOBOLD_ARMOR_HARDNESS = config.getFloat("Kobold Steel Armor Toughness", category, 1.25F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of kobold steel.");
		COPPER_ARMOR_HARDNESS = config.getFloat("Copper Armor Toughness", category, 1.56F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of copper.");
		SILVER_ARMOR_HARDNESS = config.getFloat("Silver Armor Toughness", category, 1.69F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of silver.");
		BRONZE_ARMOR_HARDNESS = config.getFloat("Bronze Armor Toughness", category, 2.0F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of bronze.");
		PLATINUM_ARMOR_HARDNESS = config.getFloat("Platinum Armor Toughness", category, 1.25F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of platinum.");
		STEEL_ARMOR_HARDNESS = config.getFloat("Steel Armor Toughness", category, 2.5F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of steel.");
		SHADOW_PLATINUM_ARMOR_HARDNESS = config.getFloat("Shadow Platinum Armor Toughness", category, 2.75F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of shadow platinum.");
		FROST_STEEL_ARMOR_HARDNESS = config.getFloat("Frost Steel Armor Toughness", category, 3.06F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of frost steel.");
		OBSIDIAN_ARMOR_HARDNESS = config.getFloat("Obsidian Armor Toughness", category, 3.56F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of obsidian.");
		CRYSTALLITE_ARMOR_HARDNESS = config.getFloat("Crystallite Armor Toughness", category, 3.75F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of crystallite.");
		DUSKSTEEL_ARMOR_HARDNESS = config.getFloat("Dusksteel Armor Toughness", category, 4.88F, 0.0F, 10.0F, "Use to change the armor toughness per piece of all armors that are made of dusksteel.");
		
		//**********************************WORLD GEN MODIFICATION**********************************
		category = "WORLD GEN";
		config.addCustomCategoryComment(category, "Modifications related to world gen such as ores, imaginary resources, etc");
		USE_IMAGINARY_RESOURCES = config.getBoolean("Use Imaginary Resources", category, true, "Use the custom made resources Kobold Steel, Shadow Platinum, Frost Steel, Crystallite, Dusksteel. (True/False)");
		
		
		category = "GLOBAL DROPS - GENERAL MODIFICATION";
		config.addCustomCategoryComment(category, "You can modify which vanilla mobs drop shadows, blood, etc...");
		SHADOW_DROP_RATE = config.getFloat("Shadow Drop Rate", category, 0.05F, 0.01F, 1.0F, "The drop rate for the shadow item in percentage, 0.05 = 5%");
		LAND_MOBS_DROP_SHADOW = config.getBoolean("Land Mobs - Shadow", category, true, "Should land mobs drop the shadow item? (True/False)");
		WATER_MOBS_DROP_SHADOW = config.getBoolean("Water Mobs - Shadow", category, false, "Should water mobs drop the shadow item? (True/False)");
		AIR_MOBS_DROP_SHADOW = config.getBoolean("Air Mobs - Shadow", category, false, "Should air mobs drop the shadow item? NOTE: The ender dragon and wither are considered flying, but \"Bosses - Shadow\" will override this option. (True/False)");
		PEACEFUL_MOBS_DROP_SHADOW = config.getBoolean("Peaceful Mobs - Shadow", category, false, "Should peaceful mobs drop the shadow item? (True/False)");
		NEUTRAL_MOBS_DROP_SHADOW = config.getBoolean("Neutral Mobs - Shadow", category, false, "Should neutral mobs drop the shadow item? (True/False)");
		HOSTILE_MOBS_DROP_SHADOW = config.getBoolean("Hostile Mobs - Shadow", category, true, "Should hostile mobs drop the shadow item? (True/False)");
		BOSS_DROPS_SHADOW = config.getBoolean("Bosses - Shadow", category, true, "Should the witch drop the shadow item? (True/False)");
		ONLY_BOSS_DROPS_SHADOW = config.getBoolean("Only Bosses Drop Shadows", category, false, "Should \"boss\" mobs be the only ones to drop shadows? (True/False)");
		NON_VANILLA_DROPS_SHADOW = config.getBoolean("Non-Vanilla Mobs - Shadow", category, true, "Should any non-vanilla mobs drop shadows? This does NOT take into account if the mob is passive, flying, water, etc. (True/False)");

		
		category = "GLOBAL DROPS - INDIVIDUAL MODIFICATION";
		config.addCustomCategoryComment(category, "You can modify whether you want individual mobs to drop the shadow items. NOTE: The overarching categories in \"GLOBAL DROPS - GENERAL MODIFICATION\" are used to determine each group, but these values will override any individual mob in each enabled group, Ex. If \"Hostile Mobs - Shadow\" is TRUE, but \"Skeleton - Shadow\" is FALSE, the skeleton will NOT drop the shadow item.");
		WITCH_DROP_SHADOW = config.getBoolean("Witch - Shadow", category, true, "Should the witch drop the shadow item. (True/False)");
		VEX_DROP_SHADOW = config.getBoolean("Vex - Shadow", category, true, "Should the vex drop the shadow item. (True/False)");
		EVOKER_DROP_SHADOW = config.getBoolean("Evoker - Shadow", category, true, "Should the evoker drop the shadow item. (True/False)");
		SHULKER_DROP_SHADOW = config.getBoolean("Shulker - Shadow", category, true, "Should the shulker drop the shadow item. (True/False)");
		DRAGON_DROP_SHADOW = config.getBoolean("Ender Dragon - Shadow", category, true, "Should the ender dragon drop the shadow item. (True/False)");
		ENDERMAN_DROP_SHADOW = config.getBoolean("Enderman - Shadow", category, true, "Should the enderman drop the shadow item. (True/False)");
		ENDERMITE_DROP_SHADOW = config.getBoolean("Endermite - Shadow", category, true, "Should the endermite drop the shadow item. (True/False)");
		VILLAGER_DROP_SHADOW = config.getBoolean("Villager - Shadow", category, true, "Should the villager drop the shadow item. (True/False)");
		PARROT_DROP_SHADOW = config.getBoolean("Parrot - Shadow", category, true, "Should the parrot drop the shadow item. (True/False)");
		LLAMA_DROP_SHADOW = config.getBoolean("Llama - Shadow", category, true, "Should the llama drop the shadow item. (True/False)");
		POLARBEAR_DROP_SHADOW = config.getBoolean("Polar Bear - Shadow", category, true, "Should the polar bear drop the shadow item. (True/False)");
		RABBIT_DROP_SHADOW = config.getBoolean("Rabbit - Shadow", category, true, "Should the rabbit drop the shadow item. (True/False)");
		HORSE_DROP_SHADOW = config.getBoolean("Horse - Shadow", category, true, "Should the horse drop the shadow item. (True/False)");
		IRONGOLEM_DROP_SHADOW = config.getBoolean("Iron Golem - Shadow", category, true, "Should the iron golem drop the shadow item. (True/False)");
		OCELOT_DROP_SHADOW = config.getBoolean("Ocelot - Shadow", category, true, "Should the ocelot drop the shadow item. (True/False)");
		SNOWMAN_DROP_SHADOW = config.getBoolean("Snowman - Shadow", category, true, "Should the snowman drop the shadow item. (True/False)");
		MOOSHROOM_DROP_SHADOW = config.getBoolean("Mooshroom - Shadow", category, true, "Should the mooshroom drop the shadow item. (True/False)");
		WOLF_DROP_SHADOW = config.getBoolean("Wolf - Shadow", category, true, "Should the wolf drop the shadow item. (True/False)");
		SQUID_DROP_SHADOW = config.getBoolean("Squid - Shadow", category, true, "Should the squid drop the shadow item. (True/False)");
		CHICKEN_DROP_SHADOW = config.getBoolean("Chicken - Shadow", category, true, "Should the chicken drop the shadow item. (True/False)");
		SHEEP_DROP_SHADOW = config.getBoolean("Sheep - Shadow", category, true, "Should the sheep drop the shadow item. (True/False)");
		COW_DROP_SHADOW = config.getBoolean("Cow - Shadow", category, true, "Should the cow drop the shadow item. (True/False)");
		PIG_DROP_SHADOW = config.getBoolean("Pig - Shadow", category, true, "Should the pig drop the shadow item. (True/False)");
		BAT_DROP_SHADOW = config.getBoolean("Bat - Shadow", category, true, "Should the bat drop the shadow item. (True/False)");
		GUARDIAN_DROP_SHADOW = config.getBoolean("Guardian - Shadow", category, true, "Should the guardian drop the shadow item. (True/False)");
		WITHER_DROP_SHADOW = config.getBoolean("Wither - Shadow", category, true, "Should the wither drop the shadow item. (True/False)");
		PIGZOMBIE_DROP_SHADOW = config.getBoolean("Zombie Pigman - Shadow", category, true, "Should the zombie pigman drop the shadow item. (True/False)");
		GIANTZOMBIE_DROP_SHADOW = config.getBoolean("Giant - Shadow", category, true, "Should the giant drop the shadow item. (True/False)");
		ILLUSIONILLAGER_DROP_SHADOW = config.getBoolean("Illusioner - Shadow", category, true, "Should the illusioner drop the shadow item. (True/False)");
		MULE_DROP_SHADOW = config.getBoolean("Mule - Shadow", category, true, "Should the mule drop the shadow item. (True/False)");
		DONKEY_DROP_SHADOW = config.getBoolean("Donkey - Shadow", category, true, "Should the donkey drop the shadow item. (True/False)");
		ZOMBIEHORSE_DROP_SHADOW = config.getBoolean("Zombie Horse - Shadow", category, true, "Should the zombie horse drop the shadow item. (True/False)");
		SKELETONHORSE_DROP_SHADOW = config.getBoolean("Skeleton - Shadow", category, true, "Should the skeleton horse drop the shadow item. (True/False)");
		ZOMBIEVILLAGER_DROP_SHADOW = config.getBoolean("Zombie Villager - Shadow", category, true, "Should the zombie villager drop the shadow item. (True/False)");
		WITHERSKELETON_DROP_SHADOW = config.getBoolean("Wither Skeleton - Shadow", category, true, "Should the wither drop the shadow item. (True/False)");
		ELDERGUARDIAN_DROP_SHADOW = config.getBoolean("Elder Guardian - Shadow", category, true, "Should the elder guardian drop the shadow item. (True/False)");
		ZOMBIE_DROP_SHADOW = config.getBoolean("Zombie - Shadow", category, true, "Should the zombie drop the shadow item. (True/False)");
		HUSK_DROP_SHADOW = config.getBoolean("Husk - Shadow", category, true, "Should the husk drop the shadow item. (True/False)");
		SKELETON_DROP_SHADOW = config.getBoolean("Skeleton - Shadow", category, true, "Should the skeleton drop the shadow item. (True/False)");
		STRAY_DROP_SHADOW = config.getBoolean("Stray - Shadow", category, true, "Should the stray drop the shadow item. (True/False)");
		BLAZE_DROP_SHADOW = config.getBoolean("Blaze - Shadow", category, true, "Should the blaze drop the shadow item. (True/False)");
		CREEPER_DROP_SHADOW = config.getBoolean("Creeper - Shadow", category, true, "Should the creeper drop the shadow item. (True/False)");
		GHAST_DROP_SHADOW = config.getBoolean("Ghast - Shadow", category, true, "Should the ghast drop the shadow item. (True/False)");
		MAGMACUBE_DROP_SHADOW = config.getBoolean("Magma Cube - Shadow", category, true, "Should the magma cube drop the shadow item. (True/False)");
		SILVERFISH_DROP_SHADOW = config.getBoolean("Silverfish - Shadow", category, true, "Should the silverfish drop the shadow item. (True/False)");
		SLIME_DROP_SHADOW = config.getBoolean("Slime - Shadow", category, true, "Should the slime drop the shadow item. (True/False)");
		SPIDER_DROP_SHADOW = config.getBoolean("Spider - Shadow", category, true, "Should the spider drop the shadow item. (True/False)");
		CAVESPIDER_DROP_SHADOW = config.getBoolean("Cave Spider - Shadow", category, true, "Should the cave spider drop the shadow item. (True/False)");
		VINDICATOR_DROP_SHADOW = config.getBoolean("Vindicator - Shadow", category, true, "Should the vindicator drop the shadow item. (True/False)");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		Alw.config = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.config.mkdirs();
		init(new File(Alw.config.getPath(), ModInfo.ID + ".cfg"));
	}
}
