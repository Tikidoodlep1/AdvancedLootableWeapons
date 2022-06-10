package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum WeaponLevels {

	DAGGER_HOT_TOOL_HEAD(ItemInit.DAGGER_HOT_TOOL_HEAD, 1, "dagger", ConfigHandler.ENABLE_DAGGERS),
	DAGGER_HOT_TOOL_HEAD_2(ItemInit.DAGGER_HOT_TOOL_HEAD_2, 2, "dagger", ConfigHandler.ENABLE_DAGGERS),
	
	KABUTOWARI_HOT_TOOL_HEAD(ItemInit.KABUTOWARI_HOT_TOOL_HEAD, 1, "kabutowari", ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_2(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2, 2, "kabutowari", ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_3(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_3, 3, "kabutowari", ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_4(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_4, 4, "kabutowari", ConfigHandler.ENABLE_KABUTOWARIS),
	KABUTOWARI_HOT_TOOL_HEAD_5(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5, 5, "kabutowari", ConfigHandler.ENABLE_KABUTOWARIS),
	
	TALWAR_HOT_TOOL_HEAD(ItemInit.TALWAR_HOT_TOOL_HEAD, 1, "talwar", ConfigHandler.ENABLE_TALWARS),
	TALWAR_HOT_TOOL_HEAD_2(ItemInit.TALWAR_HOT_TOOL_HEAD_2, 2, "talwar", ConfigHandler.ENABLE_TALWARS),
	TALWAR_HOT_TOOL_HEAD_3(ItemInit.TALWAR_HOT_TOOL_HEAD_3, 3, "talwar", ConfigHandler.ENABLE_TALWARS),

	RAPIER_HOT_TOOL_HEAD(ItemInit.RAPIER_HOT_TOOL_HEAD, 1, "rapier", ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_2(ItemInit.RAPIER_HOT_TOOL_HEAD_2, 2, "rapier", ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_3(ItemInit.RAPIER_HOT_TOOL_HEAD_3, 3, "rapier", ConfigHandler.ENABLE_RAPIERS),
	RAPIER_HOT_TOOL_HEAD_4(ItemInit.RAPIER_HOT_TOOL_HEAD_4, 4, "rapier", ConfigHandler.ENABLE_RAPIERS),
	
	CLEAVER_HOT_TOOL_HEAD(ItemInit.CLEAVER_HOT_TOOL_HEAD, 1, "cleaver", ConfigHandler.ENABLE_CLEAVERS),
	
	MACE_HOT_TOOL_HEAD(ItemInit.MACE_HOT_TOOL_HEAD, 1, "mace", ConfigHandler.ENABLE_MACES),
	MACE_HOT_TOOL_HEAD_2(ItemInit.MACE_HOT_TOOL_HEAD_2, 2, "mace", ConfigHandler.ENABLE_MACES),
	MACE_HOT_TOOL_HEAD_3(ItemInit.MACE_HOT_TOOL_HEAD_3, 3, "mace", ConfigHandler.ENABLE_MACES),
	
	STAFF_HOT_TOOL_HEAD(ItemInit.STAFF_HOT_TOOL_HEAD, 1, "staff", ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_2(ItemInit.STAFF_HOT_TOOL_HEAD_2, 2, "staff", ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_3(ItemInit.STAFF_HOT_TOOL_HEAD_3, 3, "staff", ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_4(ItemInit.STAFF_HOT_TOOL_HEAD_4, 4, "staff", ConfigHandler.ENABLE_STAFFS),
	STAFF_HOT_TOOL_HEAD_5(ItemInit.STAFF_HOT_TOOL_HEAD_5, 5, "staff", ConfigHandler.ENABLE_STAFFS),
	
	LONGSWORD_HOT_TOOL_HEAD(ItemInit.LONGSWORD_HOT_TOOL_HEAD, 1, "longsword", ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_2(ItemInit.LONGSWORD_HOT_TOOL_HEAD_2, 2, "longsword", ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_3(ItemInit.LONGSWORD_HOT_TOOL_HEAD_3, 3, "longsword", ConfigHandler.ENABLE_LONGSWORDS),
	LONGSWORD_HOT_TOOL_HEAD_4(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4, 4, "longsword", ConfigHandler.ENABLE_LONGSWORDS),
	
	KODACHI_HOT_TOOL_HEAD(ItemInit.KODACHI_HOT_TOOL_HEAD, 1, "kodachi", ConfigHandler.ENABLE_KODACHIS),
	KODACHI_HOT_TOOL_HEAD_2(ItemInit.KODACHI_HOT_TOOL_HEAD_2, 2, "kodachi", ConfigHandler.ENABLE_KODACHIS),
	
	BATTLEAXE_HOT_TOOL_HEAD(ItemInit.BATTLEAXE_HOT_TOOL_HEAD, 1, "battleaxe", ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_2(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_2, 2, "battleaxe", ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_3(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_3, 3, "battleaxe", ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_4(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_4, 4, "battleaxe", ConfigHandler.ENABLE_BATTLEAXES),
	BATTLEAXE_HOT_TOOL_HEAD_5(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5, 5, "battleaxe", ConfigHandler.ENABLE_BATTLEAXES),
	
	ZWEIHANDER_HOT_TOOL_HEAD(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD, 1, "zweihander", ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_2(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_2, 2, "zweihander", ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_3(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_3, 3, "zweihander", ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_4(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_4, 4, "zweihander", ConfigHandler.ENABLE_ZWEIHANDERS),
	ZWEIHANDER_HOT_TOOL_HEAD_5(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5, 5, "zweihander", ConfigHandler.ENABLE_ZWEIHANDERS),
	
	NODACHI_HOT_TOOL_HEAD(ItemInit.NODACHI_HOT_TOOL_HEAD, 1, "nodachi", ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_2(ItemInit.NODACHI_HOT_TOOL_HEAD_2, 2, "nodachi", ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_3(ItemInit.NODACHI_HOT_TOOL_HEAD_3, 3, "nodachi", ConfigHandler.ENABLE_NODACHIS),
	NODACHI_HOT_TOOL_HEAD_4(ItemInit.NODACHI_HOT_TOOL_HEAD_4, 4, "nodachi", ConfigHandler.ENABLE_NODACHIS),
	
	SABRE_HOT_TOOL_HEAD(ItemInit.SABRE_HOT_TOOL_HEAD, 1, "sabre", ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_2(ItemInit.SABRE_HOT_TOOL_HEAD_2, 2, "sabre", ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_3(ItemInit.SABRE_HOT_TOOL_HEAD_3, 3, "sabre", ConfigHandler.ENABLE_SABRES),
	SABRE_HOT_TOOL_HEAD_4(ItemInit.SABRE_HOT_TOOL_HEAD_4, 4, "sabre", ConfigHandler.ENABLE_SABRES),
	
	MAKHAIRA_HOT_TOOL_HEAD(ItemInit.MAKHAIRA_HOT_TOOL_HEAD, 1, "makhaira", ConfigHandler.ENABLE_MAKHAIRAS),
	MAKHAIRA_HOT_TOOL_HEAD_2(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_2, 2, "makhaira", ConfigHandler.ENABLE_MAKHAIRAS),
	MAKHAIRA_HOT_TOOL_HEAD_3(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3, 3, "makhaira", ConfigHandler.ENABLE_MAKHAIRAS),
	
	SPEAR_HOT_TOOL_HEAD(ItemInit.SPEAR_HOT_TOOL_HEAD, 1, "spear", ConfigHandler.ENABLE_SPEARS),
	SPEAR_HOT_TOOL_HEAD_2(ItemInit.SPEAR_HOT_TOOL_HEAD_2, 2, "spear", ConfigHandler.ENABLE_SPEARS),
	
	HOT_TOOL_ROD(ItemInit.HOT_TOOL_ROD, 1, "toolrod", true),
	HOT_TOOL_ROD_2(ItemInit.HOT_TOOL_ROD_2, 2, "toolrod", true),
	
	NULL(null, -1, "null", true);
	
	private final Item item;
	private final int level;
	private final String weaponType;
	private final boolean enabled;
	
	WeaponLevels(Item item, int level, String weaponType, boolean enabled){
		this.item = item;
		this.level = level;
		this.weaponType = weaponType;
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
		return WeaponLevels.NULL;
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
	
	public static ItemStack getStackByOrdinal(int ordinal) {
		WeaponLevels[] ordinals = new WeaponLevels[] {DAGGER_HOT_TOOL_HEAD, KABUTOWARI_HOT_TOOL_HEAD, TALWAR_HOT_TOOL_HEAD, RAPIER_HOT_TOOL_HEAD, MACE_HOT_TOOL_HEAD,
				CLEAVER_HOT_TOOL_HEAD, STAFF_HOT_TOOL_HEAD, LONGSWORD_HOT_TOOL_HEAD, KODACHI_HOT_TOOL_HEAD, BATTLEAXE_HOT_TOOL_HEAD, ZWEIHANDER_HOT_TOOL_HEAD,
				NODACHI_HOT_TOOL_HEAD, SABRE_HOT_TOOL_HEAD, MAKHAIRA_HOT_TOOL_HEAD, SPEAR_HOT_TOOL_HEAD};
		if(ordinals[ordinal].enabled) {
			return new ItemStack(ordinals[ordinal].item);
		}else {
			return ItemStack.EMPTY;
		}
	}
}
