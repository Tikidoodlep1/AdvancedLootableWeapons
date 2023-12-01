package tiki.rotn.advancedlootableweapons.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tiki.rotn.advancedlootableweapons.handlers.ConfigHandler;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

public enum WeaponLevels {

	DAGGER_HOT_TOOL_HEAD(ItemInit.DAGGER_HOT_TOOL_HEAD, 1, 1, "dagger", true, ConfigHandler.ENABLE_DAGGERS),
	DAGGER_HOT_TOOL_HEAD_2(ItemInit.DAGGER_HOT_TOOL_HEAD_2, 1, 2, "dagger", false, ConfigHandler.ENABLE_DAGGERS),
	
	KABUTOWARI_HOT_TOOL_HEAD(ItemInit.KABUTOWARI_HOT_TOOL_HEAD, 1, 1, "kabutowari", true, ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_2(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2, 1, 2, "kabutowari", false, ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_3(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_3, 1, 3, "kabutowari", false, ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_4(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_4, 1, 4, "kabutowari", false, ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_5(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5, 1, 5, "kabutowari", false, ConfigHandler.ENABLE_KABUTOWARIS),
	
	TALWAR_HOT_TOOL_HEAD(ItemInit.TALWAR_HOT_TOOL_HEAD, 1, 1, "talwar", true, ConfigHandler.ENABLE_TALWARS),
	TALWAR_HOT_TOOL_HEAD_2(ItemInit.TALWAR_HOT_TOOL_HEAD_2, 1, 2, "talwar", false, ConfigHandler.ENABLE_TALWARS),
	TALWAR_HOT_TOOL_HEAD_3(ItemInit.TALWAR_HOT_TOOL_HEAD_3, 1, 3, "talwar", false, ConfigHandler.ENABLE_TALWARS),

	RAPIER_HOT_TOOL_HEAD(ItemInit.RAPIER_HOT_TOOL_HEAD, 1, 1, "rapier", true, ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_2(ItemInit.RAPIER_HOT_TOOL_HEAD_2, 1, 2, "rapier", false, ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_3(ItemInit.RAPIER_HOT_TOOL_HEAD_3, 1, 3, "rapier", false, ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_4(ItemInit.RAPIER_HOT_TOOL_HEAD_4, 1, 4, "rapier", false, ConfigHandler.ENABLE_RAPIERS),
	
	CLEAVER_HOT_TOOL_HEAD(ItemInit.CLEAVER_HOT_TOOL_HEAD, 1, 1, "cleaver", true, ConfigHandler.ENABLE_CLEAVERS),
	
	MACE_HOT_TOOL_HEAD(ItemInit.MACE_HOT_TOOL_HEAD, 1, 1, "mace", true, ConfigHandler.ENABLE_MACES),
	MACE_HOT_TOOL_HEAD_2(ItemInit.MACE_HOT_TOOL_HEAD_2, 1, 2, "mace", false, ConfigHandler.ENABLE_MACES),
	MACE_HOT_TOOL_HEAD_3(ItemInit.MACE_HOT_TOOL_HEAD_3, 1, 3, "mace", false, ConfigHandler.ENABLE_MACES),
	
	STAFF_HOT_TOOL_HEAD(ItemInit.STAFF_HOT_TOOL_HEAD, 1, 1, "staff", true, ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_2(ItemInit.STAFF_HOT_TOOL_HEAD_2, 1, 2, "staff", false, ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_3(ItemInit.STAFF_HOT_TOOL_HEAD_3, 1, 3, "staff", false, ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_4(ItemInit.STAFF_HOT_TOOL_HEAD_4, 1, 4, "staff", false, ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_5(ItemInit.STAFF_HOT_TOOL_HEAD_5, 1, 5, "staff", false, ConfigHandler.ENABLE_STAFFS),
	
	LONGSWORD_HOT_TOOL_HEAD(ItemInit.LONGSWORD_HOT_TOOL_HEAD, 1, 1, "longsword", true, ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_2(ItemInit.LONGSWORD_HOT_TOOL_HEAD_2, 1, 2, "longsword", false, ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_3(ItemInit.LONGSWORD_HOT_TOOL_HEAD_3, 1, 3, "longsword", false, ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_4(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4, 1, 4, "longsword", false, ConfigHandler.ENABLE_LONGSWORDS),
	
	KODACHI_HOT_TOOL_HEAD(ItemInit.KODACHI_HOT_TOOL_HEAD, 1, 1, "kodachi", true, ConfigHandler.ENABLE_KODACHIS),
	KODACHI_HOT_TOOL_HEAD_2(ItemInit.KODACHI_HOT_TOOL_HEAD_2, 1, 2, "kodachi", false, ConfigHandler.ENABLE_KODACHIS),
	
	BATTLEAXE_HOT_TOOL_HEAD(ItemInit.BATTLEAXE_HOT_TOOL_HEAD, 1, 1, "battleaxe", true, ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_2(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_2, 1, 2, "battleaxe", false, ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_3(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_3, 1, 3, "battleaxe", false, ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_4(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_4, 1, 4, "battleaxe", false, ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_5(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5, 1, 5, "battleaxe", false, ConfigHandler.ENABLE_BATTLEAXES),
	
	ZWEIHANDER_HOT_TOOL_HEAD(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD, 1, 1, "zweihander", true, ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_2(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_2, 1, 2, "zweihander", false, ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_3(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_3, 1, 3, "zweihander", false, ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_4(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_4, 1, 4, "zweihander", false, ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_5(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5, 1, 5, "zweihander", false, ConfigHandler.ENABLE_ZWEIHANDERS),
	
	NODACHI_HOT_TOOL_HEAD(ItemInit.NODACHI_HOT_TOOL_HEAD, 1, 1, "nodachi", true, ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_2(ItemInit.NODACHI_HOT_TOOL_HEAD_2, 1, 2, "nodachi", false, ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_3(ItemInit.NODACHI_HOT_TOOL_HEAD_3, 1, 3, "nodachi", false, ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_4(ItemInit.NODACHI_HOT_TOOL_HEAD_4, 1, 4, "nodachi", false, ConfigHandler.ENABLE_NODACHIS),
	
	SABRE_HOT_TOOL_HEAD(ItemInit.SABRE_HOT_TOOL_HEAD, 1, 1, "sabre", true, ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_2(ItemInit.SABRE_HOT_TOOL_HEAD_2, 1, 2, "sabre", false, ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_3(ItemInit.SABRE_HOT_TOOL_HEAD_3, 1, 3, "sabre", false, ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_4(ItemInit.SABRE_HOT_TOOL_HEAD_4, 1, 4, "sabre", false, ConfigHandler.ENABLE_SABRES),
	
	MAKHAIRA_HOT_TOOL_HEAD(ItemInit.MAKHAIRA_HOT_TOOL_HEAD, 1, 1, "makhaira", true, ConfigHandler.ENABLE_MAKHAIRAS),
	MAKHAIRA_HOT_TOOL_HEAD_2(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_2, 1, 2, "makhaira", false, ConfigHandler.ENABLE_MAKHAIRAS),
	MAKHAIRA_HOT_TOOL_HEAD_3(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3, 1, 3, "makhaira", false, ConfigHandler.ENABLE_MAKHAIRAS),
	
	SPEAR_HOT_TOOL_HEAD(ItemInit.SPEAR_HOT_TOOL_HEAD, 1, 1, "spear", true, ConfigHandler.ENABLE_SPEARS),
	SPEAR_HOT_TOOL_HEAD_2(ItemInit.SPEAR_HOT_TOOL_HEAD_2, 1, 2, "spear", false, ConfigHandler.ENABLE_SPEARS),
	
	CHAIN_RING(ItemInit.CHAIN_RING, 2, 1, "chain", false, ConfigHandler.ENABLE_ARMOR_FORGING),
	
	HOT_TOOL_ROD(ItemInit.HOT_TOOL_ROD, 1, 1, "toolrod", true, true),
	HOT_TOOL_ROD_2(ItemInit.HOT_TOOL_ROD_2, 1, 2, "toolrod", false, true),
	
	ARMOR_PLATE(Items.AIR, 1, 1, "armor plate", true, ConfigHandler.ENABLE_ARMOR_FORGING),
	ARMOR_PLATE_IRON(ItemInit.ARMOR_PLATE_IRON, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_GOLD(ItemInit.ARMOR_PLATE_GOLD, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_KOBOLD(ItemInit.ARMOR_PLATE_KOBOLD, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_COPPER(ItemInit.ARMOR_PLATE_COPPER, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_SILVER(ItemInit.ARMOR_PLATE_SILVER, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_BRONZE(ItemInit.ARMOR_PLATE_BRONZE, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_PLATINUM(ItemInit.ARMOR_PLATE_PLATINUM, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_STEEL(ItemInit.ARMOR_PLATE_STEEL, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_SHADOW_PLATINUM(ItemInit.ARMOR_PLATE_SHADOW_PLATINUM, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_FROST_STEEL(ItemInit.ARMOR_PLATE_FROST_STEEL, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_OBSIDIAN(ItemInit.ARMOR_PLATE_OBSIDIAN, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_CRYSTALLITE(ItemInit.ARMOR_PLATE_CRYSTALLITE, 1, 1, "armor plate", true, true),
	ARMOR_PLATE_DUSKSTEEL(ItemInit.ARMOR_PLATE_DUSKSTEEL, 1, 1, "armor plate", true, true),
	
	NULL(null, -1, -1, "null", false, true);
	
	private final Item item;
	private final int count;
	private final int level;
	private final String weaponType;
	private final boolean needsAdditionalIngot;
	private final boolean enabled;
	private static WeaponLevels[] ordinals = new WeaponLevels[] {DAGGER_HOT_TOOL_HEAD, KABUTOWARI_HOT_TOOL_HEAD, TALWAR_HOT_TOOL_HEAD, RAPIER_HOT_TOOL_HEAD, MACE_HOT_TOOL_HEAD,
			CLEAVER_HOT_TOOL_HEAD, STAFF_HOT_TOOL_HEAD, LONGSWORD_HOT_TOOL_HEAD, KODACHI_HOT_TOOL_HEAD, BATTLEAXE_HOT_TOOL_HEAD, ZWEIHANDER_HOT_TOOL_HEAD,
			NODACHI_HOT_TOOL_HEAD, SABRE_HOT_TOOL_HEAD, MAKHAIRA_HOT_TOOL_HEAD, SPEAR_HOT_TOOL_HEAD, CHAIN_RING, ARMOR_PLATE};
	
	WeaponLevels(Item item, int count, int level, String weaponType, boolean needsAdditionalIngot, boolean enabled){
		this.item = item;
		this.count = count;
		this.level = level;
		this.weaponType = weaponType;
		this.needsAdditionalIngot = needsAdditionalIngot;
		this.enabled = enabled;
	}
	
	public static ItemStack getWeaponByLevelAndType(int level, String type) {		
		if(level == NULL.level || type == NULL.weaponType) {
			return ItemStack.EMPTY;
		}
		
		for(WeaponLevels w : values()) {
			if(w.enabled && w.level == level && w.weaponType == type) {
				return new ItemStack(w.item);
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static WeaponLevels getWeaponByStack(ItemStack stack) {
		for(WeaponLevels w : values()) {
			if(w.enabled && w.item.equals(stack.getItem())) {
				return w;
			}
		}
		return NULL;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public boolean needsAdditionalIngot() {
		return this.needsAdditionalIngot;
	}
	
	public ItemStack getStack() {
		return new ItemStack(this.item);
	}
	
	public String getType() {
		return this.weaponType;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public static WeaponLevels getEnumByOrdinal(int ordinal) {
		if(ordinal > 0 && ordinal < ordinals.length) {
			return ordinals[ordinal];
		}
		return NULL;
	}
	
	public static ItemStack getStackByOrdinal(int ordinal) {
		if(ordinal > 0 && ordinal < ordinals.length && ordinals[ordinal].enabled) {
			return new ItemStack(ordinals[ordinal].item);
		}else {
			return ItemStack.EMPTY;
		}
	}
	
	public static WeaponLevels getArmorPlateByMaterial(String mat) {
		switch(mat) {
		case "Iron":
			return ARMOR_PLATE_IRON;
		case "Gold":
			return ARMOR_PLATE_GOLD;
		case "Kobold":
			return ARMOR_PLATE_KOBOLD;
		case "Copper":
			return ARMOR_PLATE_COPPER;
		case "Silver":
			return ARMOR_PLATE_SILVER;
		case "Bronze":
			return ARMOR_PLATE_BRONZE;
		case "Platinum":
			return ARMOR_PLATE_PLATINUM;
		case "Steel":
			return ARMOR_PLATE_STEEL;
		case "Shadow Platinum":
			return ARMOR_PLATE_SHADOW_PLATINUM;
		case "Frost Steel":
			return ARMOR_PLATE_FROST_STEEL;
		case "Obsidian":
			return ARMOR_PLATE_OBSIDIAN;
		case "Crystallite":
			return ARMOR_PLATE_CRYSTALLITE;
		case "Dusksteel":
			return ARMOR_PLATE_DUSKSTEEL;
		default:
			return NULL;
		}
	}
}
