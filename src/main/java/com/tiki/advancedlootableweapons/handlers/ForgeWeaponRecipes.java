package com.tiki.advancedlootableweapons.handlers;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.item.ItemStack;
/*
public class ForgeWeaponRecipes {
	static ItemStack result;
	
	public static ItemStack ForgingRecipes(ItemStack stack, String weaponType, String material){
		Table<ItemStack, ItemStack, ItemStack> recipeSet = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
		switch(material) {
		case "Iron":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_IRON);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_IRON);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_IRON);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_IRON);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_IRON);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_IRON);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_IRON);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_IRON);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_IRON);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_IRON);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_IRON);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_IRON);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_IRON);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_IRON);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_IRON);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Kobold":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_KOBOLD);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_KOBOLD);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_KOBOLD);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Copper":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_COPPER);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_COPPER);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_COPPER);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_COPPER);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_COPPER);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_COPPER);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_COPPER);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_COPPER);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_COPPER);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_COPPER);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_COPPER);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_COPPER);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_COPPER);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_COPPER);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_COPPER);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Silver":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_SILVER);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_SILVER);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_SILVER);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_SILVER);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_SILVER);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_SILVER);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_SILVER);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_SILVER);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_SILVER);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_SILVER);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_SILVER);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_SILVER);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_SILVER);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_SILVER);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_SILVER);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Bronze":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_BRONZE);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_BRONZE);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_BRONZE);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_BRONZE);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_BRONZE);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_BRONZE);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_BRONZE);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_BRONZE);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_BRONZE);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_BRONZE);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_BRONZE);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_BRONZE);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_BRONZE);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_BRONZE);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_BRONZE);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Platinum":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_PLATINUM);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_PLATINUM);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Steel":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_STEEL);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_STEEL);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_STEEL);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_STEEL);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_STEEL);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_STEEL);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_STEEL);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_STEEL);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_STEEL);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_STEEL);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_STEEL);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_STEEL);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_STEEL);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_STEEL);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_STEEL);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Shadow_Platinum":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_SHADOW_PLATINUM);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_SHADOW_PLATINUM);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_SHADOW_PLATINUM);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Frost_Steel":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_FROST_STEEL);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_FROST_STEEL);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_FROST_STEEL);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Obsidian":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_OBSIDIAN);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_OBSIDIAN);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_OBSIDIAN);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Crystallite":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_CRYSTALLITE);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_CRYSTALLITE);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_CRYSTALLITE);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		case "Dusksteel":
			if(weaponType.equalsIgnoreCase("dagger")) {
				result = new ItemStack(ItemInit.DAGGER_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("kabutowari")) {
				result = new ItemStack(ItemInit.KABUTOWARI_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("rapier")) {
				result = new ItemStack(ItemInit.RAPIER_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("talwar")) {
				result = new ItemStack(ItemInit.TALWAR_DUSKSTEEL);
			}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
				result = new ItemStack(ItemInit.CLEAVER_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("mace")) {
				result = new ItemStack(ItemInit.MACE_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("staff")) {
				result = new ItemStack(ItemInit.STAFF_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("spear")) {
				result = new ItemStack(ItemInit.SPEAR_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("longsword")) {
				result = new ItemStack(ItemInit.LONGSWORD_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("kodachi")) {
				result = new ItemStack(ItemInit.KODACHI_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("battleaxe")) {
				result = new ItemStack(ItemInit.BATTLEAXE_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("zweihander")) {
				result = new ItemStack(ItemInit.ZWEIHANDER_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("nodachi")) {
				result = new ItemStack(ItemInit.NODACHI_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("sabre")) {
				result = new ItemStack(ItemInit.SABRE_DUSKSTEEL);
			}else if(weaponType.equalsIgnoreCase("makhaira")) {
				result = new ItemStack(ItemInit.MAKHAIRA_DUSKSTEEL);
			}else {
				result = ItemStack.EMPTY;
			}
			break;
		default:
			System.out.println("material not found, can't make a weapon");
			result = ItemStack.EMPTY;
		}
		return result;
	}
}
*/