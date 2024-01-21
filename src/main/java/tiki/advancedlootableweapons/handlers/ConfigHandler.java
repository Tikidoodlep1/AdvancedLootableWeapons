package tiki.advancedlootableweapons.handlers;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;

public class ConfigHandler {
	public static Configuration ArmorConfig;
	public static Configuration WeaponConfig;
	public static Configuration MobConfig;
	public static Configuration ItemConfig;
	public static Configuration WorldConfig;
	
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
	public static boolean USE_LEGACY_TEXTURES = false;
	public static boolean USE_LARGER_TEXTURE_FOR_TEMP = false;
	public static HashSet<String> VALID_ANVILS = new HashSet<String>();
	public static HashSet<String> VALID_HAMMERS = new HashSet<String>();
	public static HashSet<String> EXTRA_MATERIALS = new HashSet<String>();
	public static HashSet<String> EXTRA_MATERIAL_MOD_IDS = new HashSet<String>();
	public static boolean DISABLE_VANILLA_ARMORS = true;
	public static boolean ENABLE_ADVANCED_LEATHER_TANNING = false;
	public static boolean ENABLE_QUENCHING = true;
	public static boolean ENABLE_REGIONAL_QUENCHING = true;
	public static float BELLOWS_EXHAUSTION = 0.1F;
	public static float FORGE_TEMP_DECREASE_MULTIPLIER = 1.0F;
	public static float FORGE_TEMP_INCREASE_MULTIPLIER = 1.0F;
	public static float FORGE_NETHER_HEATING_MULTIPLIER = 2.5F;
	public static float FORGING_EXHAUSTION = 0.0F;
	public static float TOOL_HEAD_HEATING_MULTIPLIER = 1.0F;
	
	public static float GLOBAL_DAGGER_BASE_DAMAGE = 3.0F;
	public static float GLOBAL_KABUTOWARI_BASE_DAMAGE = 3.25F;
	public static float GLOBAL_RAPIER_BASE_DAMAGE = 2.5F;
	public static float GLOBAL_TALWAR_BASE_DAMAGE = 4.5F;
	public static float GLOBAL_CLEAVER_BASE_DAMAGE = 6.5F;
	public static float GLOBAL_MACE_BASE_DAMAGE = 3.75F;
	public static float GLOBAL_STAFF_BASE_DAMAGE = 4.0F;
	public static float GLOBAL_SPEAR_BASE_DAMAGE = 4.5F;
	public static float GLOBAL_LONGSWORD_BASE_DAMAGE = 4.5F;
	public static float GLOBAL_KODACHI_BASE_DAMAGE = 2.75F;
	public static float GLOBAL_BATTLEAXE_BASE_DAMAGE = 6.0F;
	public static float GLOBAL_ZWEIHANDER_BASE_DAMAGE = 5.75F;
	public static float GLOBAL_NODACHI_BASE_DAMAGE = 5.65F;
	public static float GLOBAL_SABRE_BASE_DAMAGE = 3.75F;
	public static float GLOBAL_MAKHAIRA_BASE_DAMAGE = 4.0F;
	
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
	public static boolean USE_ARMOR_BONUS_HEALTH = false;
	public static boolean USE_ARMOR_BONUS_DAMAGE = true;
	public static float ARMOR_BONUS_HEALTH_MULTIPLIER = 1.0F;
	public static float ARMOR_BONUS_DAMAGE_MULTIPLIER = 1.0F;
	
	//										ALL OLD DAMAGE/DUR VALUES WERE UPDATED ALW 1.4.2
	public static float KOBOLD_DAMAGE = 4.25F;//old was 3.5
	public static float COPPER_DAMAGE = 3.5F;//old was 4.0
	public static float SILVER_DAMAGE = 4F;//old was 4.25
	public static float BRONZE_DAMAGE = 4.25F;//old was 4.75
	public static float PLATINUM_DAMAGE = 4.75F;//old was 5.25
	public static float STEEL_DAMAGE = 5.5F;//old was 6
	public static float SHADOW_PLATINUM_DAMAGE = 5.75F;//old was 6.25
	public static float FROST_STEEL_DAMAGE = 6.5F;//old was 6.5
	public static float OBSIDIAN_DAMAGE = 6.25F;//old was 6.75
	public static float CRYSTALLITE_DAMAGE = 6.75F;//old was 7.5
	public static float DUSKSTEEL_DAMAGE = 7.5F;//old was 8.25
	public static int KOBOLD_DURABILITY = 128;// was 256
	public static int COPPER_DURABILITY = 101;// was 203
	public static int SILVER_DURABILITY = 138;// was 277
	public static int BRONZE_DURABILITY = 165;// was 330
	public static int PLATINUM_DURABILITY = 106;// was 212
	public static int STEEL_DURABILITY = 208;// was 416
	public static int SHADOW_PLATINUM_DURABILITY = 230;// was 461
	public static int FROST_STEEL_DURABILITY = 253;// was 507
	public static int OBSIDIAN_DURABILITY = 299;// was 598
	public static int CRYSTALLITE_DURABILITY = 313;// was 627
	public static int DUSKSTEEL_DURABILITY = 406;// was 812
	
	public static float CHAIN_DURABILITY_MULTIPLIER = 0.75F;
	public static float CHAIN_PROTECTION_MULTIPLIER = 0.8F;
	
	public static int LEATHER_ARMOR_HELMET_REDUCTION = 1;
	public static int LEATHER_ARMOR_CHESTPLATE_REDUCTION = 3;
	public static int LEATHER_ARMOR_LEGGINGS_REDUCTION = 2;
	public static int LEATHER_ARMOR_BOOTS_REDUCTION = 1;
	public static int LEATHER_ARMOR_DURABILITY = 5;
	public static float LEATHER_ARMOR_TOUGHNESS = 0.0F;
	public static int LEATHER_ARMOR_ENCHANTABILITY = 15;
	
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_HELMET_REDUCTION = 3;
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_CHESTPLATE_REDUCTION = 6;
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_LEGGINGS_REDUCTION = 5;
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_BOOTS_REDUCTION = 2;
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY = 11;
	public static float DIAMOND_STUDDED_LEATHER_ARMOR_TOUGHNESS = 1.0F;
	public static int DIAMOND_STUDDED_LEATHER_ARMOR_ENCHANTABILITY = 18;
	
	public static int DIAMOND_STUDDED_STEEL_ARMOR_HELMET_REDUCTION = 3;
	public static int DIAMOND_STUDDED_STEEL_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int DIAMOND_STUDDED_STEEL_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int DIAMOND_STUDDED_STEEL_ARMOR_BOOTS_REDUCTION = 3;
	public static int DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY = 42;
	public static float DIAMOND_STUDDED_STEEL_ARMOR_TOUGHNESS = 2.0F;
	public static int DIAMOND_STUDDED_STEEL_ARMOR_ENCHANTABILITY = 19;
	
	public static int IRON_ARMOR_HELMET_REDUCTION = 2;
	public static int IRON_ARMOR_CHESTPLATE_REDUCTION = 6;
	public static int IRON_ARMOR_LEGGINGS_REDUCTION = 5;
	public static int IRON_ARMOR_BOOTS_REDUCTION = 2;
	public static int IRON_ARMOR_DURABILITY = 13;
	public static float IRON_ARMOR_TOUGHNESS = 0.0F;
	public static int IRON_ARMOR_ENCHANTABILITY = 9;
	
	public static int GOLD_ARMOR_HELMET_REDUCTION = 2;
	public static int GOLD_ARMOR_CHESTPLATE_REDUCTION = 5;
	public static int GOLD_ARMOR_LEGGINGS_REDUCTION = 3;
	public static int GOLD_ARMOR_BOOTS_REDUCTION = 1;
	public static int GOLD_ARMOR_DURABILITY = 7;
	public static float GOLD_ARMOR_TOUGHNESS = 0.0F;
	public static int GOLD_ARMOR_ENCHANTABILITY = 25;
	
	public static int KOBOLD_ARMOR_HELMET_REDUCTION = 2;
	public static int KOBOLD_ARMOR_CHESTPLATE_REDUCTION = 4;
	public static int KOBOLD_ARMOR_LEGGINGS_REDUCTION = 3;
	public static int KOBOLD_ARMOR_BOOTS_REDUCTION = 1;
	public static int KOBOLD_ARMOR_DURABILITY = 20;
	public static float KOBOLD_ARMOR_TOUGHNESS = 1.25F;
	public static int KOBOLD_ARMOR_ENCHANTABILITY = 22;
	
	public static int COPPER_ARMOR_HELMET_REDUCTION = 2;
	public static int COPPER_ARMOR_CHESTPLATE_REDUCTION = 4;
	public static int COPPER_ARMOR_LEGGINGS_REDUCTION = 3;
	public static int COPPER_ARMOR_BOOTS_REDUCTION = 2;
	public static int COPPER_ARMOR_DURABILITY = 25;
	public static float COPPER_ARMOR_TOUGHNESS = 1.56F;
	public static int COPPER_ARMOR_ENCHANTABILITY = 10;
	
	public static int SILVER_ARMOR_HELMET_REDUCTION = 3;
	public static int SILVER_ARMOR_CHESTPLATE_REDUCTION = 7;
	public static int SILVER_ARMOR_LEGGINGS_REDUCTION = 5;
	public static int SILVER_ARMOR_BOOTS_REDUCTION = 3;
	public static int SILVER_ARMOR_DURABILITY = 27;
	public static float SILVER_ARMOR_TOUGHNESS = 1.69F;
	public static int SILVER_ARMOR_ENCHANTABILITY = 24;
	
	public static int BRONZE_ARMOR_HELMET_REDUCTION = 3;
	public static int BRONZE_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int BRONZE_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int BRONZE_ARMOR_BOOTS_REDUCTION = 3;
	public static int BRONZE_ARMOR_DURABILITY = 32;
	public static float BRONZE_ARMOR_TOUGHNESS = 2.0F;
	public static int BRONZE_ARMOR_ENCHANTABILITY = 12;
	
	public static int PLATINUM_ARMOR_HELMET_REDUCTION = 3;
	public static int PLATINUM_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int PLATINUM_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int PLATINUM_ARMOR_BOOTS_REDUCTION = 3;
	public static int PLATINUM_ARMOR_DURABILITY = 20;
	public static float PLATINUM_ARMOR_TOUGHNESS = 1.25F;
	public static int PLATINUM_ARMOR_ENCHANTABILITY = 26;
	
	public static int STEEL_ARMOR_HELMET_REDUCTION = 3;
	public static int STEEL_ARMOR_CHESTPLATE_REDUCTION = 6;
	public static int STEEL_ARMOR_LEGGINGS_REDUCTION = 5;
	public static int STEEL_ARMOR_BOOTS_REDUCTION = 3;
	public static int STEEL_ARMOR_DURABILITY = 38;
	public static float STEEL_ARMOR_TOUGHNESS = 2.5F;
	public static int STEEL_ARMOR_ENCHANTABILITY = 18;
	
	public static int SHADOW_PLATINUM_ARMOR_HELMET_REDUCTION = 3;
	public static int SHADOW_PLATINUM_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int SHADOW_PLATINUM_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int SHADOW_PLATINUM_ARMOR_BOOTS_REDUCTION = 3;
	public static int SHADOW_PLATINUM_ARMOR_DURABILITY = 44;
	public static float SHADOW_PLATINUM_ARMOR_TOUGHNESS = 2.75F;
	public static int SHADOW_PLATINUM_ARMOR_ENCHANTABILITY = 21;
	
	public static int FROST_STEEL_ARMOR_HELMET_REDUCTION = 3;
	public static int FROST_STEEL_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int FROST_STEEL_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int FROST_STEEL_ARMOR_BOOTS_REDUCTION = 3;
	public static int FROST_STEEL_ARMOR_DURABILITY = 49;
	public static float FROST_STEEL_ARMOR_TOUGHNESS = 3.06F;
	public static int FROST_STEEL_ARMOR_ENCHANTABILITY = 30;
	
	public static int OBSIDIAN_ARMOR_HELMET_REDUCTION = 3;
	public static int OBSIDIAN_ARMOR_CHESTPLATE_REDUCTION = 6;
	public static int OBSIDIAN_ARMOR_LEGGINGS_REDUCTION = 5;
	public static int OBSIDIAN_ARMOR_BOOTS_REDUCTION = 3;
	public static int OBSIDIAN_ARMOR_DURABILITY = 57;
	public static float OBSIDIAN_ARMOR_TOUGHNESS = 3.56F;
	public static int OBSIDIAN_ARMOR_ENCHANTABILITY = 18;
	
	public static int CRYSTALLITE_ARMOR_HELMET_REDUCTION = 3;
	public static int CRYSTALLITE_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int CRYSTALLITE_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int CRYSTALLITE_ARMOR_BOOTS_REDUCTION = 3;
	public static int CRYSTALLITE_ARMOR_DURABILITY = 60;
	public static float CRYSTALLITE_ARMOR_TOUGHNESS = 3.75F;
	public static int CRYSTALLITE_ARMOR_ENCHANTABILITY = 20;
	
	public static int DUSKSTEEL_ARMOR_HELMET_REDUCTION = 3;
	public static int DUSKSTEEL_ARMOR_CHESTPLATE_REDUCTION = 8;
	public static int DUSKSTEEL_ARMOR_LEGGINGS_REDUCTION = 6;
	public static int DUSKSTEEL_ARMOR_BOOTS_REDUCTION = 3;
	public static int DUSKSTEEL_ARMOR_DURABILITY = 78;
	public static float DUSKSTEEL_ARMOR_TOUGHNESS = 4.88F;
	public static int DUSKSTEEL_ARMOR_ENCHANTABILITY = 14;
	
	
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
	
	public static int FELDSPAR_BLOCKS_PER_VEIN = 15;
	public static int FELDSPAR_MAX_VEINS_PER_CHUNK = 8;
	public static int FELDSPAR_MIN_SPAWN_HEIGHT = 33;
	public static int FELDSPAR_MAX_SPAWN_HEIGHT = 143;
	
	public static int GYPSUM_BLOCKS_PER_VEIN = 12;
	public static int GYPSUM_MAX_VEINS_PER_CHUNK = 7;
	public static int GYPSUM_MIN_SPAWN_HEIGHT = 14;
	public static int GYPSUM_MAX_SPAWN_HEIGHT = 124;
	
	public static int DOLOMITE_BLOCKS_PER_VEIN = 16;
	public static int DOLOMITE_MAX_VEINS_PER_CHUNK = 8;
	public static int DOLOMITE_MIN_SPAWN_HEIGHT = 42;
	public static int DOLOMITE_MAX_SPAWN_HEIGHT = 182;
	
	public static int TIN_ORE_BLOCKS_PER_VEIN = 7;
	public static int TIN_ORE_MAX_VEINS_PER_CHUNK = 14;
	public static int TIN_ORE_MIN_SPAWN_HEIGHT = 34;
	public static int TIN_ORE_MAX_SPAWN_HEIGHT = 102;
	
	public static int COPPER_ORE_BLOCKS_PER_VEIN = 7;
	public static int COPPER_ORE_MAX_VEINS_PER_CHUNK = 12;
	public static int COPPER_ORE_MIN_SPAWN_HEIGHT = 30;
	public static int COPPER_ORE_MAX_SPAWN_HEIGHT = 73;
	
	public static int SILVER_ORE_BLOCKS_PER_VEIN = 7;
	public static int SILVER_ORE_MAX_VEINS_PER_CHUNK = 13;
	public static int SILVER_ORE_MIN_SPAWN_HEIGHT = 18;
	public static int SILVER_ORE_MAX_SPAWN_HEIGHT = 54;
	
	public static int PLATINUM_ORE_BLOCKS_PER_VEIN = 5;
	public static int PLATINUM_ORE_MAX_VEINS_PER_CHUNK = 8;
	public static int PLATINUM_ORE_MIN_SPAWN_HEIGHT = 5;
	public static int PLATINUM_ORE_MAX_SPAWN_HEIGHT = 46;
	
	public static int CRYSTALLITE_ORE_BLOCKS_PER_VEIN = 3;
	public static int CRYSTALLITE_ORE_MAX_VEINS_PER_CHUNK = 8;
	public static int CRYSTALLITE_ORE_MIN_SPAWN_HEIGHT = 0;
	public static int CRYSTALLITE_ORE_MAX_SPAWN_HEIGHT = 55;
	
	public static int SALT_ORE_BLOCKS_PER_VEIN = 6;
	public static int SALT_ORE_MAX_VEINS_PER_CHUNK = 10;
	public static int SALT_ORE_MIN_SPAWN_HEIGHT = 42;
	public static int SALT_ORE_MAX_SPAWN_HEIGHT = 145;
	
	public static void initArmor(File file) {
		ArmorConfig = new Configuration(file);
		
		String category;
		
		//**********************************ARMOR MODIFICATION**********************************
		category = "GENERAL ARMOR MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Armor modifications such as chestplates giving extra damage, armor slowing, etc");
		USE_ARMOR_WEIGHT = ArmorConfig.getBoolean("Use Armor Weight", category, true, "If enabled, armor will slow you down proportionate to it's real-world weight. (True/False)");
		USE_ARMOR_BONUS_HEALTH = ArmorConfig.getBoolean("Use Armor Bonus Health", category, false, "If enabled, armor will give you bonus health when worn. (True/False)");
		USE_ARMOR_BONUS_DAMAGE = ArmorConfig.getBoolean("Use Armor Bonus Damage", category, true, "If enabled, chestplates will make you deal extra damage equivalent to the real-world weight with the armor tier factored in. (True/False)");
		
		ARMOR_BONUS_HEALTH_MULTIPLIER = ArmorConfig.getFloat("Armor Bonus Health Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Health\" is enabled. A multiplier for the bonus health given by armor.");
		ARMOR_BONUS_DAMAGE_MULTIPLIER = ArmorConfig.getFloat("Armor Bonus Damage Multiplier", category, 1.0F, 0.1F, 10.0F, "Only works if \"Use Armor Bonus Damage\" is enabled. A multiplier for the bonus damage given by chestplates.");
		
		ENABLE_ARMORS = ArmorConfig.getBoolean("Enable Custom Armor Sets", category, true, "Enable or disable armor made from the custom materials. If this is disabled, \"Enable Armor Forging\" will also be disabled by default. (True/False)");
		ENABLE_ARMOR_FORGING = ArmorConfig.getBoolean("Enable Armor Forging", category, true, "Enable or diable the armor forging. If this is disabled, armors will be crafted using the vanilla recipes (True/false)");
		DISABLE_VANILLA_ARMORS = ArmorConfig.getBoolean("Disable Vanilla Armors", category, true, "Make vanilla armors uncraftable, forcing players use only modded armors. (True/False)");
		
		category = "ARMOR MATERIAL MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Modifications to the materials of all armors such as durability and hardness. Note that the damage formula is quite complicated and is not 1 to 1 for armor and damage reduced.");
		
		CHAIN_DURABILITY_MULTIPLIER = ArmorConfig.getFloat("Chain Durability Multiplier", category, 0.75F, 0.1F, 200.0F, "");
		CHAIN_PROTECTION_MULTIPLIER = ArmorConfig.getFloat("Chain Protection Multiplier", category, 0.8F, 0.1F, 200.0F, "");
		
		category = "LEATHER MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Leather Armor Modification");
		LEATHER_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Leather Helmet Armor", category, 1, 0, 20, "");
		LEATHER_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Leather Chestplate Armor", category, 3, 0, 20, "");
		LEATHER_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Leather Leggings Armor", category, 2, 0, 20, "");
		LEATHER_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Leather Boots Armor", category, 1, 0, 20, "");
		LEATHER_ARMOR_DURABILITY = ArmorConfig.getInt("Leather Armor Base Durability", category, 5, 0, 1000, "");
		LEATHER_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Leather Armor Toughness", category, 0.0F, 0.0F, 10.0F, "");
		LEATHER_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Leather Armor Enchantability", category, 15, 0, 100, "");
		
		category = "DIAMOND STUDDED LEATHER MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Diamond Studded LeatherArmor Modification");
		DIAMOND_STUDDED_LEATHER_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Diamond Studded Leather Helmet Armor", category, 3, 0, 20, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Diamond Studded Leather Chestplate Armor", category, 6, 0, 20, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Diamond Studded Leather Leggings Armor", category, 5, 0, 20, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Diamond Studded Leather Boots Armor", category, 2, 0, 20, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY = ArmorConfig.getInt("Diamond Studded Leather Armor Base Durability", category, 11, 0, 1000, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Diamond Studded Leather Armor Toughness", category, 1.0F, 0.0F, 10.0F, "");
		DIAMOND_STUDDED_LEATHER_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Diamond Studded Leather Armor Enchantability", category, 18, 0, 100, "");
		
		category = "IRON MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Iron Armor Modification");
		IRON_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Iron Helmet Armor", category, 2, 0, 20, "");
		IRON_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Iron Chestplate Armor", category, 6, 0, 20, "");
		IRON_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Iron Leggings Armor", category, 5, 0, 20, "");
		IRON_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Iron Boots Armor", category, 2, 0, 20, "");
		IRON_ARMOR_DURABILITY = ArmorConfig.getInt("Iron Armor Base Durability", category, 13, 0, 1000, "");
		IRON_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Iron Armor Toughness", category, 0.0F, 0.0F, 10.0F, "");
		IRON_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Iron Armor Enchantability", category, 9, 0, 100, "");
		
		category = "GOLD MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Gold Armor Modification");
		GOLD_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Gold Helmet Armor", category, 2, 0, 20, "");
		GOLD_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Gold Chestplate Armor", category, 5, 0, 20, "");
		GOLD_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Gold Leggings Armor", category, 3, 0, 20, "");
		GOLD_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Gold Boots Armor", category, 1, 0, 20, "");
		GOLD_ARMOR_DURABILITY = ArmorConfig.getInt("Gold Armor Base Durability", category, 7, 0, 1000, "");
		GOLD_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Gold Armor Toughness", category, 0.0F, 0.0F, 10.0F, "");
		GOLD_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Gold Armor Enchantability", category, 25, 0, 100, "");
		
		category = "KOBOLD MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Kobold Steel Armor Modification");
		KOBOLD_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Kobold Steel Helmet Armor", category, 2, 0, 20, "");
		KOBOLD_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Kobold Steel Chestplate Armor", category, 4, 0, 20, "");
		KOBOLD_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Kobold Steel Leggings Armor", category, 3, 0, 20, "");
		KOBOLD_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Kobold Steel Boots Armor", category, 1, 0, 20, "");
		KOBOLD_ARMOR_DURABILITY = ArmorConfig.getInt("Kobold Steel Armor Base Durability", category, 20, 0, 1000, "");
		KOBOLD_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Kobold Steel Armor Toughness", category, 1.25F, 0.0F, 10.0F, "");
		KOBOLD_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Kobold Steel Armor Enchantability", category, 22, 0, 100, "");
		
		category = "COPPER MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Copper Armor Modification");
		COPPER_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Copper Helmet Armor", category, 2, 0, 20, "");
		COPPER_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Copper Chestplate Armor", category, 4, 0, 20, "");
		COPPER_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Copper Leggings Armor", category, 3, 0, 20, "");
		COPPER_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Copper Boots Armor", category, 2, 0, 20, "");
		COPPER_ARMOR_DURABILITY = ArmorConfig.getInt("Copper Armor Base Durability", category, 25, 0, 1000, "");
		COPPER_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Copper Armor Toughness", category, 1.56F, 0.0F, 10.0F, "");
		COPPER_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Copper Armor Enchantability", category, 10, 0, 100, "");
		
		category = "SILVER MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Silver Armor Modification");
		SILVER_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Silver Helmet Armor", category, 3, 0, 20, "");
		SILVER_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Silver Chestplate Armor", category, 7, 0, 20, "");
		SILVER_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Silver Leggings Armor", category, 5, 0, 20, "");
		SILVER_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Silver Boots Armor", category, 3, 0, 20, "");
		SILVER_ARMOR_DURABILITY = ArmorConfig.getInt("Silver Armor Base Durability", category, 27, 0, 1000, "");
		SILVER_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Silver Armor Toughness", category, 1.69F, 0.0F, 10.0F, "");
		SILVER_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Silver Armor Enchantability", category, 24, 0, 100, "");
		
		category = "BRONZE MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Bronze Armor Modification");
		BRONZE_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Bronze Helmet Armor", category, 3, 0, 20, "");
		BRONZE_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Bronze Chestplate Armor", category, 8, 0, 20, "");
		BRONZE_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Bronze Leggings Armor", category, 6, 0, 20, "");
		BRONZE_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Bronze Boots Armor", category, 3, 0, 20, "");
		BRONZE_ARMOR_DURABILITY = ArmorConfig.getInt("Bronze Armor Base Durability", category, 32, 0, 1000, "");
		BRONZE_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Bronze Armor Toughness", category, 2.0F, 0.0F, 10.0F, "");
		BRONZE_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Bronze Armor Enchantability", category, 12, 0, 100, "");
		
		category = "PLATINUM MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Platinum Armor Modification");
		PLATINUM_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Platinum Helmet Armor", category, 3, 0, 20, "");
		PLATINUM_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Platinum Chestplate Armor", category, 8, 0, 20, "");
		PLATINUM_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Platinum Leggings Armor", category, 6, 0, 20, "");
		PLATINUM_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Platinum Boots Armor", category, 3, 0, 20, "");
		PLATINUM_ARMOR_DURABILITY = ArmorConfig.getInt("Platinum Armor Base Durability", category, 20, 0, 1000, "");
		PLATINUM_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Platinum Armor Toughness", category, 1.25F, 0.0F, 10.0F, "");
		PLATINUM_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Platinum Armor Enchantability", category, 26, 0, 100, "");
		
		category = "STEEL MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Steel Armor Modification");
		STEEL_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Steel Helmet Armor", category, 3, 0, 20, "");
		STEEL_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Steel Chestplate Armor", category, 6, 0, 20, "");
		STEEL_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Steel Leggings Armor", category, 5, 0, 20, "");
		STEEL_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Steel Boots Armor", category, 3, 0, 20, "");
		STEEL_ARMOR_DURABILITY = ArmorConfig.getInt("Steel Armor Base Durability", category, 1338, 0, 1000, "");
		STEEL_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Steel Armor Toughness", category, 2.5F, 0.0F, 10.0F, "");
		STEEL_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Steel Armor Enchantability", category, 18, 0, 100, "");
		
		category = "DIAMOND STUDDED STEEL MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Diamond Studded Steel Armor Modification");
		DIAMOND_STUDDED_STEEL_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Diamond Studded Steel Helmet Armor", category, 3, 0, 20, "");
		DIAMOND_STUDDED_STEEL_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Diamond Studded Steel Chestplate Armor", category, 8, 0, 20, "");
		DIAMOND_STUDDED_STEEL_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Diamond Studded Steel Leggings Armor", category, 6, 0, 20, "");
		DIAMOND_STUDDED_STEEL_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Diamond Studded Steel Boots Armor", category, 3, 0, 20, "");
		DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY = ArmorConfig.getInt("Diamond Studded Steel Armor Base Durability", category, 42, 0, 1000, "");
		DIAMOND_STUDDED_STEEL_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Diamond Studded Steel Armor Toughness", category, 2.0F, 0.0F, 10.0F, "");
		DIAMOND_STUDDED_STEEL_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Diamond Studded Steel Armor Enchantability", category, 19, 0, 100, "");
		
		category = "SHADOW PLATINUM MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Shadow Platinum Armor Modification");
		SHADOW_PLATINUM_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Shadow Platinum Helmet Armor", category, 3, 0, 20, "");
		SHADOW_PLATINUM_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Shadow Platinum Chestplate Armor", category, 8, 0, 20, "");
		SHADOW_PLATINUM_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Shadow Platinum Leggings Armor", category, 6, 0, 20, "");
		SHADOW_PLATINUM_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Shadow Platinum Boots Armor", category, 3, 0, 20, "");
		SHADOW_PLATINUM_ARMOR_DURABILITY = ArmorConfig.getInt("Shadow Platinum Armor Base Durability", category, 44, 0, 1000, "");
		SHADOW_PLATINUM_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Shadow Platinum Armor Toughness", category, 2.75F, 0.0F, 10.0F, "");
		SHADOW_PLATINUM_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Shadow Platinum Armor Enchantability", category, 21, 0, 100, "");
		
		category = "FROST STEEL MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Frost Steel Armor Modification");
		FROST_STEEL_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Frost Steel Helmet Armor", category, 3, 0, 20, "");
		FROST_STEEL_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Frost Steel Chestplate Armor", category, 8, 0, 20, "");
		FROST_STEEL_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Frost Steel Leggings Armor", category, 6, 0, 20, "");
		FROST_STEEL_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Frost Steel Boots Armor", category, 3, 0, 20, "");
		FROST_STEEL_ARMOR_DURABILITY = ArmorConfig.getInt("Frost Steel Armor Base Durability", category, 49, 0, 1000, "");
		FROST_STEEL_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Frost Steel Armor Toughness", category, 3.06F, 0.0F, 10.0F, "");
		FROST_STEEL_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Frost Steel Armor Enchantability", category, 30, 0, 100, "");
		
		category = "OBSIDIAN MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Obsidian Armor Modification");
		OBSIDIAN_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Obsidian Helmet Armor", category, 3, 0, 20, "");
		OBSIDIAN_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Obsidian Chestplate Armor", category, 6, 0, 20, "");
		OBSIDIAN_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Obsidian Leggings Armor", category, 5, 0, 20, "");
		OBSIDIAN_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Obsidian Boots Armor", category, 3, 0, 20, "");
		OBSIDIAN_ARMOR_DURABILITY = ArmorConfig.getInt("Obsidian Armor Base Durability", category, 57, 0, 1000, "");
		OBSIDIAN_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Obsidian Armor Toughness", category, 3.56F, 0.0F, 10.0F, "");
		OBSIDIAN_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Obsidian Armor Enchantability", category, 18, 0, 100, "");
		
		category = "CRYSTALLITE MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Crystallite Armor Modification");
		CRYSTALLITE_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Crystallite Helmet Armor", category, 3, 0, 20, "");
		CRYSTALLITE_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Crystallite Chestplate Armor", category, 8, 0, 20, "");
		CRYSTALLITE_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Crystallite Leggings Armor", category, 6, 0, 20, "");
		CRYSTALLITE_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Crystallite Boots Armor", category, 3, 0, 20, "");
		CRYSTALLITE_ARMOR_DURABILITY = ArmorConfig.getInt("Crystallite Armor Base Durability", category, 60, 0, 1000, "");
		CRYSTALLITE_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Crystallite Armor Toughness", category, 3.75F, 0.0F, 10.0F, "");
		CRYSTALLITE_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Crystallite Armor Enchantability", category, 20, 0, 100, "");
		
		category = "DUSKSTEEL MODIFICATION";
		ArmorConfig.addCustomCategoryComment(category, "Dusksteel Armor Modification");
		DUSKSTEEL_ARMOR_HELMET_REDUCTION = ArmorConfig.getInt("Dusksteel Helmet Armor", category, 3, 0, 20, "");
		DUSKSTEEL_ARMOR_CHESTPLATE_REDUCTION = ArmorConfig.getInt("Dusksteel Chestplate Armor", category, 8, 0, 20, "");
		DUSKSTEEL_ARMOR_LEGGINGS_REDUCTION = ArmorConfig.getInt("Dusksteel Leggings Armor", category, 6, 0, 20, "");
		DUSKSTEEL_ARMOR_BOOTS_REDUCTION = ArmorConfig.getInt("Dusksteel Boots Armor", category, 3, 0, 20, "");
		DUSKSTEEL_ARMOR_DURABILITY = ArmorConfig.getInt("Dusksteel Armor Base Durability", category, 78, 0, 1000, "");
		DUSKSTEEL_ARMOR_TOUGHNESS = ArmorConfig.getFloat("Dusksteel Armor Toughness", category, 4.88F, 0.0F, 10.0F, "");
		DUSKSTEEL_ARMOR_ENCHANTABILITY = ArmorConfig.getInt("Dusksteel Armor Enchantability", category, 14, 0, 100, "");
		
		ArmorConfig.save();
	}
	
	public static void initWeapon(File file) {
		WeaponConfig = new Configuration(file);
		
		String category;
		
		//**********************************WEAPON MODIFICATION**********************************
		category = "WEAPON MODIFICATION";
		
		USE_LEGACY_TEXTURES = WeaponConfig.getBoolean("Use Legacy Weapon Textures", category, false, "Change weapons to use the legacy textures. This makes weapon textures more easily changed via resource packs.");
		
		WeaponConfig.addCustomCategoryComment(category, "Enable or disable weapon types");
		ENABLE_DAGGERS = WeaponConfig.getBoolean("Enable Daggers", category, true, "Enable or disable daggers in-game. (True/False)");
		ENABLE_KABUTOWARIS = WeaponConfig.getBoolean("Enable Kabutowaris", category, true, "Enable or disable Kabutowaris in-game. (True/False)");
		ENABLE_RAPIERS = WeaponConfig.getBoolean("Enable Rapiers", category, true, "Enable or disable rapiers in-game. (True/False)");
		ENABLE_TALWARS = WeaponConfig.getBoolean("Enable Talwars", category, true, "Enable or disable talwars in-game. (True/False)");
		ENABLE_CLEAVERS = WeaponConfig.getBoolean("Enable Cleavers", category, true, "Enable or disable cleavers in-game. (True/False)");
		ENABLE_MACES = WeaponConfig.getBoolean("Enable Maces", category, true, "Enable or disable maces in-game. (True/False)");
		ENABLE_STAFFS = WeaponConfig.getBoolean("Enable Staffs", category, true, "Enable or disable staffs in-game. (True/False)");
		ENABLE_SPEARS = WeaponConfig.getBoolean("Enable Spears", category, true, "Enable or disable spears in-game. (True/False)");
		ENABLE_LONGSWORDS = WeaponConfig.getBoolean("Enable Longswords", category, true, "Enable or disable longswords in-game. (True/False)");
		ENABLE_KODACHIS = WeaponConfig.getBoolean("Enable Kodachis", category, true, "Enable or disable kodachis in-game. (True/False)");
		ENABLE_BATTLEAXES = WeaponConfig.getBoolean("Enable Battleaxes", category, true, "Enable or disable battleaxes in-game. (True/False)");
		ENABLE_ZWEIHANDERS = WeaponConfig.getBoolean("Enable Zweihanders", category, true, "Enable or disable zweihanders in-game. (True/False)");
		ENABLE_NODACHIS = WeaponConfig.getBoolean("Enable Nodachis", category, true, "Enable or disable nodachis in-game. (True/False)");
		ENABLE_SABRES = WeaponConfig.getBoolean("Enable Sabres", category, true, "Enable or disable sabres in-game. (True/False)");
		ENABLE_MAKHAIRAS = WeaponConfig.getBoolean("Enable Makhairas", category, true, "Enable or disable makhairas in-game. (True/False)");
		
		WeaponConfig.addCustomCategoryComment(category, "Weapon Modifications such as setting base weapon damages and attack speeds");
		USE_CUSTOM_WEAPON_REACH = WeaponConfig.getBoolean("Use Custom Weapon Reach", category, true, "Use the custom set weapon reach. Disabling this will make things like spears have the same reach as things like daggers. (True/False)");
		GLOBAL_DAGGER_BASE_DAMAGE = WeaponConfig.getFloat("Global Dagger Base Damage", category, 1.5F, 0.0F, 100.0F, "Will only work if \"Enable Daggers\" is true. This modifies the damage of all types of daggers, regardless of material.");
		GLOBAL_KABUTOWARI_BASE_DAMAGE = WeaponConfig.getFloat("Global Kabutowari Base Damage", category, 1.0F, 0.0F, 100.0F, "Will only work if \"Enable Kabutowaris\" is true. This modifies the damage of all types of kabutowaris, regardless of material.");
		GLOBAL_RAPIER_BASE_DAMAGE = WeaponConfig.getFloat("Global Rapier Base Damage", category, 0.5F, 0.0F, 100.0F, "Will only work if \"Enable Rapiers\" is true. This modifies the damage of all types of rapiers, regardless of material.");
		GLOBAL_TALWAR_BASE_DAMAGE = WeaponConfig.getFloat("Global Talwar Base Damage", category, 3.25F, 0.0F, 100.0F, "Will only work if \"Enable Talwars\" is true. This modifies the damage of all types of talwars, regardless of material.");
		GLOBAL_CLEAVER_BASE_DAMAGE = WeaponConfig.getFloat("Global Cleaver Base Damage", category, 4.5F, 0.0F, 100.0F, "Will only work if \"Enable Cleavers\" is true. This modifies the damage of all types of cleavers, regardless of material.");
		GLOBAL_MACE_BASE_DAMAGE = WeaponConfig.getFloat("Global Mace Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Maces\" is true. This modifies the damage of all types of maces, regardless of material.");
		GLOBAL_STAFF_BASE_DAMAGE = WeaponConfig.getFloat("Global Staff Base Damage", category, 2.0F, 0.0F, 100.0F, "Will only work if \"Enable Staffs\" is true. This modifies the damage of all types of staffs, regardless of material.");
		GLOBAL_SPEAR_BASE_DAMAGE = WeaponConfig.getFloat("Global Spear Base Damage", category, 2.5F, 0.0F, 100.0F, "Will only work if \"Enable Spears\" is true. This modifies the damage of all types of spears, regardless of material.");
		GLOBAL_LONGSWORD_BASE_DAMAGE = WeaponConfig.getFloat("Global Longsword Base Damage", category, 3.5F, 0.0F, 100.0F, "Will only work if \"Enable Longswords\" is true. This modifies the damage of all types of longswords, regardless of material.");
		GLOBAL_KODACHI_BASE_DAMAGE = WeaponConfig.getFloat("Global Kodachi Base Damage", category, 0.75F, 0.0F, 100.0F, "Will only work if \"Enable Kodachis\" is true. This modifies the damage of all types of kodachis, regardless of material.");
		GLOBAL_BATTLEAXE_BASE_DAMAGE = WeaponConfig.getFloat("Global Battleaxe Base Damage", category, 4.0F, 0.0F, 100.0F, "Will only work if \"Enable Battleaxes\" is true. This modifies the damage of all types of battleaxes, regardless of material.");
		GLOBAL_ZWEIHANDER_BASE_DAMAGE = WeaponConfig.getFloat("Global Zweihander Base Damage", category, 3.75F, 0.0F, 100.0F, "Will only work if \"Enable Zweihanders\" is true. This modifies the damage of all types of zweihanders, regardless of material.");
		GLOBAL_NODACHI_BASE_DAMAGE = WeaponConfig.getFloat("Global Nodachi Base Damage", category, 3.65F, 0.0F, 100.0F, "Will only work if \"Enable Nodachis\" is true. This modifies the damage of all types of nodachis, regardless of material.");
		GLOBAL_SABRE_BASE_DAMAGE = WeaponConfig.getFloat("Global Sabre Base Damage", category, 3.0F, 0.0F, 100.0F, "Will only work if \"Enable Sabres\" is true. This modifies the damage of all types of sabres, regardless of material.");
		GLOBAL_MAKHAIRA_BASE_DAMAGE = WeaponConfig.getFloat("Global Makhaira Base Damage", category, 2.75F, 0.0F, 100.0F, "Will only work if \"Enable Makhairas\" is true. This modifies the damage of all types of makhairas, regardless of material.");
			
		GLOBAL_DAGGER_ATTACK_SPEED = WeaponConfig.getFloat("Global Dagger Attack Speed", category, 3.2F, 0.0F, 6.0F, "Will only work if \"Enable Daggers\" is true. This modifies the attack speed of all types of daggers, regardless of material.");
		GLOBAL_KABUTOWARI_ATTACK_SPEED = WeaponConfig.getFloat("Global Kabutowari Attack Speed", category, 2.4F, 0.0F, 6.0F, "Will only work if \"Enable Kabutowaris\" is true. This modifies the attack speed of all types of kabutowaris, regardless of material.");
		GLOBAL_RAPIER_ATTACK_SPEED = WeaponConfig.getFloat("Global Rapier Attack Speed", category, 3.6F, 0.0F, 6.0F, "Will only work if \"Enable Rapiers\" is true. This modifies the attack speed of all types of rapiers, regardless of material.");
		GLOBAL_TALWAR_ATTACK_SPEED = WeaponConfig.getFloat("Global Talwar Attack Speed", category, 1.8F, 0.0F, 6.0F, "Will only work if \"Enable Talwars\" is true. This modifies the attack speed of all types of talwars, regardless of material.");
		GLOBAL_CLEAVER_ATTACK_SPEED = WeaponConfig.getFloat("Global Cleaver Attack Speed", category, 1.0F, 0.0F, 6.0F, "Will only work if \"Enable Cleavers\" is true. This modifies the attack speed of all types of cleavers, regardless of material.");
		GLOBAL_MACE_ATTACK_SPEED = WeaponConfig.getFloat("Global Mace Attack Speed", category, 2.2F, 0.0F, 6.0F, "Will only work if \"Enable Maces\" is true. This modifies the attack speed of all types of maces, regardless of material.");
		GLOBAL_STAFF_ATTACK_SPEED = WeaponConfig.getFloat("Global Staff Attack Speed", category, 1.6F, 0.0F, 6.0F, "Will only work if \"Enable Staffs\" is true. This modifies the attack speed of all types of staffs, regardless of material.");
		GLOBAL_SPEAR_ATTACK_SPEED = WeaponConfig.getFloat("Global Spear Attack Speed", category, 1.0F, 0.0F, 6.0F, "Will only work if \"Enable Spears\" is true. This modifies the attack speed of all types of spears, regardless of material.");
		GLOBAL_LONGSWORD_ATTACK_SPEED = WeaponConfig.getFloat("Global Longsword Attack Speed", category, 1.8F, 0.0F, 6.0F, "Will only work if \"Enable Longswords\" is true. This modifies the attack speed of all types of longswords, regardless of material.");
		GLOBAL_KODACHI_ATTACK_SPEED = WeaponConfig.getFloat("Global Kodachi Attack Speed", category, 2.9F, 0.0F, 6.0F, "Will only work if \"Enable Kodachis\" is true. This modifies the attack speed of all types of kodachis, regardless of material.");
		GLOBAL_BATTLEAXE_ATTACK_SPEED = WeaponConfig.getFloat("Global Battleaxe Attack Speed", category, 1.1F, 0.0F, 6.0F, "Will only work if \"Enable Battleaxes\" is true. This modifies the attack speed of all types of battleaxes, regardless of material.");
		GLOBAL_ZWEIHANDER_ATTACK_SPEED = WeaponConfig.getFloat("Global Zweihander Attack Speed", category, 1.3F, 0.0F, 6.0F, "Will only work if \"Enable Zweihanders\" is true. This modifies the attack speed of all types of zweihanders, regardless of material.");
		GLOBAL_NODACHI_ATTACK_SPEED = WeaponConfig.getFloat("Global Nodachi Attack Speed", category, 1.4F, 0.0F, 6.0F, "Will only work if \"Enable Nodachis\" is true. This modifies the attack speed of all types of nodachis, regardless of material.");
		GLOBAL_SABRE_ATTACK_SPEED = WeaponConfig.getFloat("Global Sabre Attack Speed", category, 2.4F, 0.0F, 6.0F, "Will only work if \"Enable Sabres\" is true. This modifies the attack speed of all types of sabres, regardless of material.");
		GLOBAL_MAKHAIRA_ATTACK_SPEED = WeaponConfig.getFloat("Global Makhaira Attack Speed", category, 2.2F, 0.0F, 6.0F, "Will only work if \"Enable Makhairas\" is true. This modifies the attack speed of all types of makhairas, regardless of material.");
		
		GLOBAL_DAGGER_CRAFTING_EXP = WeaponConfig.getInt("Global Dagger Crafting Exp", category, 2, 0, 100, "Will only work if \"Enable Daggers\" is true. This modifies the exp you get from crafting daggers.");
		GLOBAL_KABUTOWARI_CRAFTING_EXP = WeaponConfig.getInt("Global Kabutowari Crafting Exp", category, 5, 0, 100, "Will only work if \"Enable Kabutowaris\" is true. This modifies the exp you get from crafting kabutowaris.");
		GLOBAL_RAPIER_CRAFTING_EXP = WeaponConfig.getInt("Global Rapier Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Rapiers\" is true. This modifies the exp you get from crafting rapiers.");
		GLOBAL_TALWAR_CRAFTING_EXP = WeaponConfig.getInt("Global Talwars Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable talwars\" is true. This modifies the exp you get from crafting talwars.");
		GLOBAL_CLEAVER_CRAFTING_EXP = WeaponConfig.getInt("Global Cleaver Crafting Exp", category, 1, 0, 100, "Will only work if \"Enable Cleavers\" is true. This modifies the exp you get from crafting cleavers.");
		GLOBAL_MACE_CRAFTING_EXP = WeaponConfig.getInt("Global Mace Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Maces\" is true. This modifies the exp you get from crafting maces.");
		GLOBAL_STAFF_CRAFTING_EXP = WeaponConfig.getInt("Global Staff Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Staffs\" is true. This modifies the exp you get from crafting staffs.");
		GLOBAL_LONGSWORD_CRAFTING_EXP = WeaponConfig.getInt("Global Longsword Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Longswords\" is true. This modifies the exp you get from crafting longswords.");
		GLOBAL_BATTLEAXE_CRAFTING_EXP = WeaponConfig.getInt("Global Battleaxe Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Battleaxes\" is true. This modifies the exp you get from crafting battleaxes.");
		GLOBAL_ZWEIHANDER_CRAFTING_EXP = WeaponConfig.getInt("Global Zweihanders Crafting Exp", category, 6, 0, 100, "Will only work if \"Enable Zweihanders\" is true. This modifies the exp you get from crafting zweihanders.");
		GLOBAL_KODACHI_CRAFTING_EXP = WeaponConfig.getInt("Global Kodachi Crafting Exp", category, 2, 0, 100, "Will only work if \"Enable Kodachis\" is true. This modifies the exp you get from crafting kodachis.");
		GLOBAL_NODACHI_CRAFTING_EXP = WeaponConfig.getInt("Global Nodachi Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Nodahcis\" is true. This modifies the exp you get from crafting nodachis.");
		GLOBAL_SABRE_CRAFTING_EXP = WeaponConfig.getInt("Global Sabre Crafting Exp", category, 4, 0, 100, "Will only work if \"Enable Sabres\" is true. This modifies the exp you get from crafting sabres.");
		GLOBAL_MAKHAIRA_CRAFTING_EXP = WeaponConfig.getInt("Global Makhaira Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Makhairas\" is true. This modifies the exp you get from crafting makhairas.");
		GLOBAL_SPEAR_CRAFTING_EXP = WeaponConfig.getInt("Global Spear Crafting Exp", category, 3, 0, 100, "Will only work if \"Enable Spears\" is true. This modifies the exp you get from crafting spears.");
		GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP = WeaponConfig.getInt("Global Long Weapon Handle Crafting Exp", category, 4, 0, 100, "This modifies the exp you get from crafting long weapon handles.");
		GLOBAL_CHAIN_LINK_CRAFTING_EXP = WeaponConfig.getInt("Global Chain Link Crafting Exp", category, 2, 0, 100, "This modifies the exp you get from crafting chain links.");
		GLOBAL_ARMOR_PLATE_CRAFTING_EXP = WeaponConfig.getInt("Global Armor Plate Crafting Exp", category, 4, 0, 100, "This modifies the exp you get from crafting armor plates.");

		category = "WEAPON MATERIAL MODIFICATION";
		WeaponConfig.addCustomCategoryComment(category, "Modifications to the materials of all weapons such as durability and damage");
		EXTRA_MATERIALS.addAll(Arrays.stream(WeaponConfig.getStringList("Extra Weapon Materials", category, new String[] {}, "Put tool materials here (Ex. DIAMOND for vanilla diamonds) to add a new craftable set of ALW weapons. The repair item will be used as the base item (Ex. Iron ingots for iron). If the tool material does not have a repair item (Crafts into granite clay), specify one using a comma as a delimiter. (Ex TF:CONSTANTAN,thermalfoundation:material#164 using # to specify item metadata) Available tool materials can be seen with /materials in game. NOTE: If you're haiving issues with the items crafting into air, try adding \"zz\" to the beginning of the ALW jar file.")).collect(Collectors.toSet()));
		
		KOBOLD_DAMAGE = WeaponConfig.getFloat("Kobold Steel Base Damage", category, 4.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of kobold steel.");
		COPPER_DAMAGE = WeaponConfig.getFloat("Copper Base Damage", category, 3.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of copper.");
		SILVER_DAMAGE = WeaponConfig.getFloat("Silver Base Damage", category, 4F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of silver.");
		BRONZE_DAMAGE = WeaponConfig.getFloat("Bronze Base Damage", category, 4.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of bronze.");
		PLATINUM_DAMAGE = WeaponConfig.getFloat("Platinum Base Damage", category, 4.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of platinum.");
		STEEL_DAMAGE = WeaponConfig.getFloat("Steel Base Damage", category, 5.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of steel.");
		SHADOW_PLATINUM_DAMAGE = WeaponConfig.getFloat("Shadow Platinum Base Damage", category, 5.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of shadow platinum.");
		FROST_STEEL_DAMAGE = WeaponConfig.getFloat("Frost Steel Base Damage", category, 6.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of frost steel.");
		OBSIDIAN_DAMAGE = WeaponConfig.getFloat("Obsidian Base Damage", category, 6.25F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of obsidian.");
		CRYSTALLITE_DAMAGE = WeaponConfig.getFloat("Crystallite Base Damage", category, 6.75F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of crystallite.");
		DUSKSTEEL_DAMAGE = WeaponConfig.getFloat("Dusksteel Base Damage", category, 7.5F, 0.0F, 100.0F, "Use to change the damage of all wepaons that are made of dusksteel.");
		
		KOBOLD_DURABILITY = WeaponConfig.getInt("Kobold Steel Base Durability", category, 128, 0, 1000, "Use to change the durability that the weapons made of kobold steel have.");
		COPPER_DURABILITY = WeaponConfig.getInt("Copper Base Durability", category, 101, 0, 1000, "Use to change the durability that the weapons made of copper have.");
		SILVER_DURABILITY = WeaponConfig.getInt("Silver Base Durability", category, 138, 0, 1000, "Use to change the durability that the weapons made of silver have.");
		BRONZE_DURABILITY = WeaponConfig.getInt("Bronze Base Durability", category, 165, 0, 1000, "Use to change the durability that the weapons made of bronze have.");
		PLATINUM_DURABILITY = WeaponConfig.getInt("Platinum Base Durability", category, 106, 0, 1000, "Use to change the durability that the weapons made of platinum have.");
		STEEL_DURABILITY = WeaponConfig.getInt("Steel Base Durability", category, 208, 0, 1000, "Use to change the durability that the weapons made of steel have.");
		SHADOW_PLATINUM_DURABILITY = WeaponConfig.getInt("Shadow Platinum Base Durability", category, 230, 0, 1000, "Use to change the durability that the weapons made of shadow platinum have.");
		FROST_STEEL_DURABILITY = WeaponConfig.getInt("Frost Steel Base Durability", category, 253, 0, 1000, "Use to change the durability that the weapons made of frost steel have.");
		OBSIDIAN_DURABILITY = WeaponConfig.getInt("Obsidian Base Durability", category, 299, 0, 1000, "Use to change the durability that the weapons made of obsidian have.");
		CRYSTALLITE_DURABILITY = WeaponConfig.getInt("Crystallite Base Durability", category, 313, 0, 1000, "Use to change the durability that the weapons made of crystallite have.");
		DUSKSTEEL_DURABILITY = WeaponConfig.getInt("Dusksteel Base Durability", category, 406, 0, 1000, "Use to change the durability that the weapons made of dusksteel have.");
		
		WeaponConfig.save();
	}
	
	public static void initMob(File file) {
		MobConfig = new Configuration(file);
		
		String category;
		
		category = "SHADOW DROPS - INDIVIDUAL MODIFICATION";
		MobConfig.addCustomCategoryComment(category, "You can modify whether you want mobs to drop the shadow item.");
		VEX_DROP_SHADOW = MobConfig.getBoolean("Vex - Shadow", category, true, "Should the vex drop the shadow item. (True/False)");
		EVOKER_DROP_SHADOW = MobConfig.getBoolean("Evoker - Shadow", category, true, "Should the evoker drop the shadow item. (True/False)");
		WITHERSKELETON_DROP_SHADOW = MobConfig.getBoolean("Wither Skeleton - Shadow", category, true, "Should the wither drop the shadow item. (True/False)");
		VINDICATOR_DROP_SHADOW = MobConfig.getBoolean("Vindicator - Shadow", category, true, "Should the vindicator drop the shadow item. (True/False)");
		
		MobConfig.save();
	}
	
	public static void initItem(File file) {
		ItemConfig = new Configuration(file);
		
		String category;
		
		category = "GENERAL BLOCK/ITEM CONFIG";
		//ItemConfig.addCustomCategoryComment(category, "");
		ENABLE_ADVANCED_LEATHER_TANNING = ItemConfig.getBoolean("Enable Advanced Leather Tanning", category, true, "Enable the advanced leather tanning process. This entails needing some way to transfer fluids. (True/False)");
		ENABLE_QUENCHING = ItemConfig.getBoolean("Enable Quenching", category, true, "Enable or Disable quenching of weapons. (True/False)");
		ENABLE_REGIONAL_QUENCHING = ItemConfig.getBoolean("Enable Regional Quenching", category, true, "Enable or Disable regional quenching techniques. Ex. Should the Kabutowari use clay tempering and the longsword traditional water quenching? (True/False)");
		
		TOOL_HEAD_HEATING_MULTIPLIER = ItemConfig.getFloat("Tool Head Heating Multiplier", category, 1.0F, 0.1F, 10.0F, "A multiplier for how quickly hot tool heads and weapon parts will heat up in the forge. 1.0 is realistic.");
		BELLOWS_EXHAUSTION = ItemConfig.getFloat("Bellows Exhaustion", category, 0.1f, 0.0f, 20.0f, "The amount of exhaustion using a bellows gives the player.");
		FORGING_EXHAUSTION = ItemConfig.getFloat("Forging Exhaustion", category, 0.0f, 0.0f, 20.0f, "");
		FORGE_TEMP_DECREASE_MULTIPLIER = ItemConfig.getFloat("Forge Temperature Decrease Multiplier. Set to 0.0F to disable temperature drop.", category, 1.0F, 0.0F, 10.0F, "");
		FORGE_TEMP_INCREASE_MULTIPLIER = ItemConfig.getFloat("Forge Temperature Increase Multiplier.", category, 1.0F, 0.1F, 10.0F, "");
		FORGE_NETHER_HEATING_MULTIPLIER = ItemConfig.getFloat("Forge Nether Heating Multiplier", category, 2.5F, 0.1F, 20.0F, "A multiplier used for when forges are placed in the nether.");
		USE_LARGER_TEXTURE_FOR_TEMP = ItemConfig.getBoolean("Use Larger Texture Region to Show Temperature", category, false, "Uses a larger part of the simple forge and advanced forge textures to show how hot the forge is compared to it's max heat value. In the simple forge, this uses the smoke. In the advanced forge, this uses the fiery part of the background. (True/False)");
		VALID_ANVILS.addAll(Arrays.stream(ItemConfig.getStringList("Valid Anvil Blocks", category, new String[] {"minecraft:anvil"}, "Write any block ID to register it as a valid anvil to forge weapons on.")).collect(Collectors.toSet()));
		VALID_HAMMERS.addAll(Arrays.stream(ItemConfig.getStringList("Valid Hammer Items", category, new String[] {
				"advancedlootableweapons:stone_forge_hammer", "advancedlootableweapons:iron_forge_hammer", "advancedlootableweapons:kobold_forge_hammer",
				"advancedlootableweapons:copper_forge_hammer", "advancedlootableweapons:silver_forge_hammer", "advancedlootableweapons:bronze_forge_hammer",
				"advancedlootableweapons:platinum_forge_hammer", "advancedlootableweapons:steel_forge_hammer", "advancedlootableweapons:shadow_platinum_forge_hammer",
				"advancedlootableweapons:frost_steel_forge_hammer", "advancedlootableweapons:obsidian_forge_hammer", "advancedlootableweapons:crystallite_forge_hammer",
				"advancedlootableweapons:dusksteel_forge_hammer"}, "Write any item ID to register it as a valid forge hammer to open the Anvil Forging GUI with.")).collect(Collectors.toSet()));
		
		ItemConfig.save();
	}
	
	public static void initWorld(File file) {
		WorldConfig = new Configuration(file);
		
		String category;
	
		category = "RESOURCE GENERATION";
		WorldConfig.addCustomCategoryComment(category, "Modify anything related to resource generation.");
		FELDSPAR_BLOCKS_PER_VEIN = WorldConfig.getInt("Feldspar Max Blocks Per Vein", category, 15, 1, 100, "");
		FELDSPAR_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Feldspar Max Veins Per Chunk", category, 8, 1, 100, "");
		FELDSPAR_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Feldspar Min Spawn Height", category, 33, 0, 256, "");
		FELDSPAR_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Feldspar Max Spawn Height", category, 143, 0, 256, "");
		
		GYPSUM_BLOCKS_PER_VEIN = WorldConfig.getInt("Gypsum Max Blocks Per Vein", category, 12, 1, 100, "");
		GYPSUM_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Gypsum Max Veins Per Chunk", category, 7, 1, 100, "");
		GYPSUM_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Gypsum Min Spawn Height", category, 14, 0, 256, "");
		GYPSUM_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Gypsum Max Spawn Height", category, 124, 0, 256, "");
		
		DOLOMITE_BLOCKS_PER_VEIN = WorldConfig.getInt("Dolomite Max Blocks Per Vein", category, 16, 1, 100, "");
		DOLOMITE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Dolomite Max Veins Per Chunk", category, 8, 1, 100, "");
		DOLOMITE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Dolomite Min Spawn Height", category, 42, 0, 256, "");
		DOLOMITE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Dolomite Max Spawn Height", category, 182, 0, 256, "");
		
		TIN_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Tin Ore Max Blocks Per Vein", category, 7, 1, 100, "");
		TIN_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Tin Ore Max Veins Per Chunk", category, 14, 1, 100, "");
		TIN_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Tin Ore Min Spawn Height", category, 34, 0, 256, "");
		TIN_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Tin Ore Max Spawn Height", category, 102, 0, 256, "");
		
		COPPER_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Copper Ore Max Blocks Per Vein", category, 7, 1, 100, "");
		COPPER_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Copper Ore Max Veins Per Chunk", category, 12, 1, 100, "");
		COPPER_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Copper Ore Min Spawn Height", category, 30, 0, 256, "");
		COPPER_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Copper Ore Max Spawn Height", category, 73, 0, 256, "");
		
		SILVER_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Silver Ore Max Blocks Per Vein", category, 7, 1, 100, "");
		SILVER_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Silver Ore Max Veins Per Chunk", category, 13, 1, 100, "");
		SILVER_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Silver Ore Min Spawn Height", category, 18, 0, 256, "");
		SILVER_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Silver Ore Max Spawn Height", category, 54, 0, 256, "");
		
		PLATINUM_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Platinum Ore Max Blocks Per Vein", category, 5, 1, 100, "");
		PLATINUM_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Platinum Ore Max Veins Per Chunk", category, 8, 1, 100, "");
		PLATINUM_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Platinum Ore Min Spawn Height", category, 5, 0, 256, "");
		PLATINUM_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Platinum Ore Max Spawn Height", category, 146, 0, 256, "");
		
		CRYSTALLITE_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Crystallite Ore Max Blocks Per Vein", category, 3, 1, 100, "");
		CRYSTALLITE_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Crystallite Ore Max Veins Per Chunk", category, 11, 1, 100, "");
		CRYSTALLITE_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Crystallite Ore Min Spawn Height", category, 0, 0, 255, "");
		CRYSTALLITE_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Crystallite Ore Max Spawn Height", category, 60, 0, 256, "");
		
		SALT_ORE_BLOCKS_PER_VEIN = WorldConfig.getInt("Salt Ore Max Blocks Per Vein", category, 6, 1, 100, "");
		SALT_ORE_MAX_VEINS_PER_CHUNK = WorldConfig.getInt("Salt Ore Max Veins Per Chunk", category, 10, 1, 100, "");
		SALT_ORE_MIN_SPAWN_HEIGHT = WorldConfig.getInt("Salt Ore Min Spawn Height", category, 42, 0, 256, "");
		SALT_ORE_MAX_SPAWN_HEIGHT = WorldConfig.getInt("Salt Ore Max Spawn Height", category, 145, 0, 256, "");
		
		WorldConfig.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		Alw.ArmorConfig = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.ArmorConfig.mkdirs();
		initArmor(new File(Alw.ArmorConfig.getPath(), "Armor Config.cfg"));
		
		Alw.WeaponConfig = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.WeaponConfig.mkdirs();
		initWeapon(new File(Alw.WeaponConfig.getPath(), "Weapon Config.cfg"));
		
		Alw.MobConfig = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.MobConfig.mkdirs();
		initMob(new File(Alw.MobConfig.getPath(), "Mob and Drop Config.cfg"));
		
		Alw.ItemConfig = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.ItemConfig.mkdirs();
		initItem(new File(Alw.ItemConfig.getPath(), "Block and Item Config.cfg"));
		
		Alw.WorldConfig = new File(event.getModConfigurationDirectory() + "/" + ModInfo.ID);
		Alw.WorldConfig.mkdirs();
		initWorld(new File(Alw.WorldConfig.getPath(), "World Config.cfg"));
	}
}
