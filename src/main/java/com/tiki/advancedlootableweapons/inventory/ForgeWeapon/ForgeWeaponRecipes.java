package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ForgeWeaponRecipes {

	private Map<String, ItemStack> toolRodMats = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> daggerMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> kabutowariMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> rapierMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> talwarMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> cleaverMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> maceMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> staffMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> longswordMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> kodachiMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> battleaxeMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> zweihanderMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> nodachiMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> sabreMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> makhairaMaterials = Maps.<String, ItemStack>newHashMap();
	private Map<String, ItemStack> spearMaterials = Maps.<String, ItemStack>newHashMap();
	private Multimap<ItemStack, ItemStack> toolRodJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> daggerJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> kabutowariJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> rapierJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> talwarJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> cleaverJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> maceJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> staffJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> longswordJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> kodachiJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> battleaxeJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> zweihanderJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> nodachiJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> sabreJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> makhairaJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> spearJeiMap = ArrayListMultimap.create();
	private Multimap<ItemStack, ItemStack> toolHeadJeiMap = ArrayListMultimap.create();
	private Table<ItemStack, ItemStack, Map<String, ItemStack>> recipes = HashBasedTable.<ItemStack, ItemStack, Map<String, ItemStack>>create();
	private Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> jeiRecipes = HashBasedTable.<Integer, ItemStack, Multimap<ItemStack, ItemStack>>create();
	private Map<ItemStack, Integer> expValues = Maps.<ItemStack, Integer>newHashMap();
	
	public ForgeWeaponRecipes() {
		registerNewRecipe(new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "dagger", ConfigHandler.GLOBAL_DAGGER_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "kabutowari", ConfigHandler.GLOBAL_KABUTOWARI_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "rapier", ConfigHandler.GLOBAL_RAPIER_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "talwar", ConfigHandler.GLOBAL_TALWAR_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "cleaver", ConfigHandler.GLOBAL_CLEAVER_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "mace", ConfigHandler.GLOBAL_MACE_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_5), new ItemStack(ItemInit.LONG_TOOL_ROD), "staff", ConfigHandler.GLOBAL_STAFF_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "longsword", ConfigHandler.GLOBAL_LONGSWORD_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "kodachi", ConfigHandler.GLOBAL_KODACHI_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5), new ItemStack(ItemInit.LONG_TOOL_ROD), "battleaxe", ConfigHandler.GLOBAL_BATTLEAXE_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5), new ItemStack(ItemInit.LONG_TOOL_ROD), "zweihander", ConfigHandler.GLOBAL_ZWEIHANDER_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "nodachi", ConfigHandler.GLOBAL_NODACHI_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "sabre", ConfigHandler.GLOBAL_SABRE_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "makhaira", ConfigHandler.GLOBAL_MAKHAIRA_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.LONG_TOOL_ROD), "spear", ConfigHandler.GLOBAL_SPEAR_CRAFTING_EXP);
		registerNewRecipe(new ItemStack(ItemInit.HOT_TOOL_ROD_2), new ItemStack(ItemInit.HOT_TOOL_ROD_2), "handle", ConfigHandler.GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP);
		registerJeiHotToolHeadRecipes();
	}
	
	private void registerJeiHotToolHeadRecipes() {
		toolHeadJeiMap.put(ItemStack.EMPTY, new ItemStack(ItemInit.HOT_TOOL_HEAD));
		jeiRecipes.put(0, new ItemStack(Items.IRON_INGOT), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_KOBOLD), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_COPPER), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_SILVER), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_BRONZE), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_PLATINUM), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_STEEL), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_FROST_STEEL), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_OBSIDIAN), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_CRYSTALLITE), toolHeadJeiMap);
		jeiRecipes.put(0, new ItemStack(ItemInit.INGOT_DUSKSTEEL), toolHeadJeiMap);
	}
	
	public ItemStack getItemResult(String material, ItemStack input1, ItemStack input2) {
		if(input1.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) && input2.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2))) {
			return new ItemStack(ItemInit.LONG_TOOL_ROD);
		}
		Table<ItemStack, ItemStack, Map<String, ItemStack>> recipe = this.getDualCraftingList();
		Map<String, ItemStack> recipeMap;
		
		for(ItemStack stack1 : recipe.rowKeySet()) {
			if(input1.getItem() instanceof ItemHotToolHead && input1.isItemEqualIgnoreDurability(stack1)) {
				String mat = material;
				for(ItemStack stack2 : recipe.columnKeySet()) {
					if(input2.isItemEqualIgnoreDurability(stack2)) {
						if(recipe.contains(stack1, stack2)) {
							recipeMap = recipe.get(stack1, stack2);
							for(String type : recipeMap.keySet()) {
								if(mat.equalsIgnoreCase(type)) {
									return recipeMap.get(type);
								}
							}
						}
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public int getExpValue(ItemStack stack) {
		Map<ItemStack, Integer> exp = getExpValues();
		for(ItemStack compStack : exp.keySet()) {
			if(compStack.isItemEqualIgnoreDurability(stack)) {
				return exp.get(compStack);
			}
		}
		return 0;
	}
	
	public Map<ItemStack, Integer> getExpValues(){
		return this.expValues;
	}
	
	public Table<ItemStack, ItemStack, Map<String, ItemStack>> getDualCraftingList(){
		return this.recipes;
	}
	
	public Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> getJeiCraftingList(){
		return this.jeiRecipes;
	}
	
	private Table<ItemStack, ItemStack, Map<String, ItemStack>> registerNewRecipe(ItemStack input1, ItemStack input2, String output, int exp){
		if(!(recipes.contains(input1, input2))) {
			coverMaterials(input1, input2, output, exp);
		}
		return recipes;
	}
	
	private void coverMaterials(ItemStack input1, ItemStack input2, String type, int exp) {
		switch(type) {
		case "dagger":
			daggerMaterials.put("Iron", new ItemStack(ItemInit.DAGGER_IRON));
			daggerMaterials.put("Kobold", new ItemStack(ItemInit.DAGGER_KOBOLD));
			daggerMaterials.put("Copper", new ItemStack(ItemInit.DAGGER_COPPER));
			daggerMaterials.put("Silver", new ItemStack(ItemInit.DAGGER_SILVER));
			daggerMaterials.put("Bronze", new ItemStack(ItemInit.DAGGER_BRONZE));
			daggerMaterials.put("Platinum", new ItemStack(ItemInit.DAGGER_PLATINUM));
			daggerMaterials.put("Steel", new ItemStack(ItemInit.DAGGER_STEEL));
			daggerMaterials.put("Shadow Platinum", new ItemStack(ItemInit.DAGGER_SHADOW_PLATINUM));
			daggerMaterials.put("Frost Steel", new ItemStack(ItemInit.DAGGER_FROST_STEEL));
			daggerMaterials.put("Obsidian", new ItemStack(ItemInit.DAGGER_OBSIDIAN));
			daggerMaterials.put("Crystallite", new ItemStack(ItemInit.DAGGER_CRYSTALLITE));
			daggerMaterials.put("Dusksteel", new ItemStack(ItemInit.DAGGER_DUSKSTEEL));
			recipes.put(input1, input2, daggerMaterials);
			for(ItemStack stack : daggerMaterials.values()) {
				daggerJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, daggerJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.DAGGER_IRON), exp);
			expValues.put(new ItemStack(ItemInit.DAGGER_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.DAGGER_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.DAGGER_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.DAGGER_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.DAGGER_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.DAGGER_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.DAGGER_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.DAGGER_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.DAGGER_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.DAGGER_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.DAGGER_DUSKSTEEL), exp+6);
			break;
		case "kabutowari":
			kabutowariMaterials.put("Iron", new ItemStack(ItemInit.KABUTOWARI_IRON));
			kabutowariMaterials.put("Kobold", new ItemStack(ItemInit.KABUTOWARI_KOBOLD));
			kabutowariMaterials.put("Copper", new ItemStack(ItemInit.KABUTOWARI_COPPER));
			kabutowariMaterials.put("Silver", new ItemStack(ItemInit.KABUTOWARI_SILVER));
			kabutowariMaterials.put("Bronze", new ItemStack(ItemInit.KABUTOWARI_BRONZE));
			kabutowariMaterials.put("Platinum", new ItemStack(ItemInit.KABUTOWARI_PLATINUM));
			kabutowariMaterials.put("Steel", new ItemStack(ItemInit.KABUTOWARI_STEEL));
			kabutowariMaterials.put("Shadow Platinum", new ItemStack(ItemInit.KABUTOWARI_SHADOW_PLATINUM));
			kabutowariMaterials.put("Frost Steel", new ItemStack(ItemInit.KABUTOWARI_FROST_STEEL));
			kabutowariMaterials.put("Obsidian", new ItemStack(ItemInit.KABUTOWARI_OBSIDIAN));
			kabutowariMaterials.put("Crystallite", new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE));
			kabutowariMaterials.put("Dusksteel", new ItemStack(ItemInit.KABUTOWARI_DUSKSTEEL));
			recipes.put(input1, input2, kabutowariMaterials);
			for(ItemStack stack : kabutowariMaterials.values()) {
				kabutowariJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, kabutowariJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_IRON), exp);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.KABUTOWARI_DUSKSTEEL), exp+6);
			break;
		case "rapier":
			rapierMaterials.put("Iron", new ItemStack(ItemInit.RAPIER_IRON));
			rapierMaterials.put("Kobold", new ItemStack(ItemInit.RAPIER_KOBOLD));
			rapierMaterials.put("Copper", new ItemStack(ItemInit.RAPIER_COPPER));
			rapierMaterials.put("Silver", new ItemStack(ItemInit.RAPIER_SILVER));
			rapierMaterials.put("Bronze", new ItemStack(ItemInit.RAPIER_BRONZE));
			rapierMaterials.put("Platinum", new ItemStack(ItemInit.RAPIER_PLATINUM));
			rapierMaterials.put("Steel", new ItemStack(ItemInit.RAPIER_STEEL));
			rapierMaterials.put("Shadow Platinum", new ItemStack(ItemInit.RAPIER_SHADOW_PLATINUM));
			rapierMaterials.put("Frost Steel", new ItemStack(ItemInit.RAPIER_FROST_STEEL));
			rapierMaterials.put("Obsidian", new ItemStack(ItemInit.RAPIER_OBSIDIAN));
			rapierMaterials.put("Crystallite", new ItemStack(ItemInit.RAPIER_CRYSTALLITE));
			rapierMaterials.put("Dusksteel", new ItemStack(ItemInit.RAPIER_DUSKSTEEL));
			recipes.put(input1, input2, rapierMaterials);
			for(ItemStack stack : rapierMaterials.values()) {
				rapierJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, rapierJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.RAPIER_IRON), exp);
			expValues.put(new ItemStack(ItemInit.RAPIER_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.RAPIER_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.RAPIER_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.RAPIER_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.RAPIER_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.RAPIER_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.RAPIER_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.RAPIER_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.RAPIER_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.RAPIER_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.RAPIER_DUSKSTEEL), exp+6);
			break;
		case "talwar":
			talwarMaterials.put("Iron", new ItemStack(ItemInit.TALWAR_IRON));
			talwarMaterials.put("Kobold", new ItemStack(ItemInit.TALWAR_KOBOLD));
			talwarMaterials.put("Copper", new ItemStack(ItemInit.TALWAR_COPPER));
			talwarMaterials.put("Silver", new ItemStack(ItemInit.TALWAR_SILVER));
			talwarMaterials.put("Bronze", new ItemStack(ItemInit.TALWAR_BRONZE));
			talwarMaterials.put("Platinum", new ItemStack(ItemInit.TALWAR_PLATINUM));
			talwarMaterials.put("Steel", new ItemStack(ItemInit.TALWAR_STEEL));
			talwarMaterials.put("Shadow Platinum", new ItemStack(ItemInit.TALWAR_SHADOW_PLATINUM));
			talwarMaterials.put("Frost Steel", new ItemStack(ItemInit.TALWAR_FROST_STEEL));
			talwarMaterials.put("Obsidian", new ItemStack(ItemInit.TALWAR_OBSIDIAN));
			talwarMaterials.put("Crystallite", new ItemStack(ItemInit.TALWAR_CRYSTALLITE));
			talwarMaterials.put("Dusksteel", new ItemStack(ItemInit.TALWAR_DUSKSTEEL));
			recipes.put(input1, input2, talwarMaterials);
			for(ItemStack stack : talwarMaterials.values()) {
				talwarJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, talwarJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.TALWAR_IRON), exp);
			expValues.put(new ItemStack(ItemInit.TALWAR_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.TALWAR_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.TALWAR_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.TALWAR_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.TALWAR_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.TALWAR_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.TALWAR_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.TALWAR_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.TALWAR_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.TALWAR_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.TALWAR_DUSKSTEEL), exp+6);
			break;
		case "cleaver":
			cleaverMaterials.put("Iron", new ItemStack(ItemInit.CLEAVER_IRON));
			cleaverMaterials.put("Kobold", new ItemStack(ItemInit.CLEAVER_KOBOLD));
			cleaverMaterials.put("Copper", new ItemStack(ItemInit.CLEAVER_COPPER));
			cleaverMaterials.put("Silver", new ItemStack(ItemInit.CLEAVER_SILVER));
			cleaverMaterials.put("Bronze", new ItemStack(ItemInit.CLEAVER_BRONZE));
			cleaverMaterials.put("Platinum", new ItemStack(ItemInit.CLEAVER_PLATINUM));
			cleaverMaterials.put("Steel", new ItemStack(ItemInit.CLEAVER_STEEL));
			cleaverMaterials.put("Shadow Platinum", new ItemStack(ItemInit.CLEAVER_SHADOW_PLATINUM));
			cleaverMaterials.put("Frost Steel", new ItemStack(ItemInit.CLEAVER_FROST_STEEL));
			cleaverMaterials.put("Obsidian", new ItemStack(ItemInit.CLEAVER_OBSIDIAN));
			cleaverMaterials.put("Crystallite", new ItemStack(ItemInit.CLEAVER_CRYSTALLITE));
			cleaverMaterials.put("Dusksteel", new ItemStack(ItemInit.CLEAVER_DUSKSTEEL));
			recipes.put(input1, input2, cleaverMaterials);
			for(ItemStack stack : cleaverMaterials.values()) {
				cleaverJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, cleaverJeiMap);
			}
						expValues.put(new ItemStack(ItemInit.CLEAVER_IRON), exp);
			expValues.put(new ItemStack(ItemInit.CLEAVER_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.CLEAVER_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.CLEAVER_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.CLEAVER_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.CLEAVER_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.CLEAVER_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.CLEAVER_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.CLEAVER_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.CLEAVER_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.CLEAVER_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.CLEAVER_DUSKSTEEL), exp+6);
			break;
		case "mace":
			maceMaterials.put("Iron", new ItemStack(ItemInit.MACE_IRON));
			maceMaterials.put("Kobold", new ItemStack(ItemInit.MACE_KOBOLD));
			maceMaterials.put("Copper", new ItemStack(ItemInit.MACE_COPPER));
			maceMaterials.put("Silver", new ItemStack(ItemInit.MACE_SILVER));
			maceMaterials.put("Bronze", new ItemStack(ItemInit.MACE_BRONZE));
			maceMaterials.put("Platinum", new ItemStack(ItemInit.MACE_PLATINUM));
			maceMaterials.put("Steel", new ItemStack(ItemInit.MACE_STEEL));
			maceMaterials.put("Shadow Platinum", new ItemStack(ItemInit.MACE_SHADOW_PLATINUM));
			maceMaterials.put("Frost Steel", new ItemStack(ItemInit.MACE_FROST_STEEL));
			maceMaterials.put("Obsidian", new ItemStack(ItemInit.MACE_OBSIDIAN));
			maceMaterials.put("Crystallite", new ItemStack(ItemInit.MACE_CRYSTALLITE));
			maceMaterials.put("Dusksteel", new ItemStack(ItemInit.MACE_DUSKSTEEL));
			recipes.put(input1, input2, maceMaterials);
			for(ItemStack stack : maceMaterials.values()) {
				maceJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, maceJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.MACE_IRON), exp);
			expValues.put(new ItemStack(ItemInit.MACE_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.MACE_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.MACE_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.MACE_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.MACE_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.MACE_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.MACE_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.MACE_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.MACE_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.MACE_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.MACE_DUSKSTEEL), exp+6);
			break;
		case "staff":
			staffMaterials.put("Iron", new ItemStack(ItemInit.STAFF_IRON));
			staffMaterials.put("Kobold", new ItemStack(ItemInit.STAFF_KOBOLD));
			staffMaterials.put("Copper", new ItemStack(ItemInit.STAFF_COPPER));
			staffMaterials.put("Silver", new ItemStack(ItemInit.STAFF_SILVER));
			staffMaterials.put("Bronze", new ItemStack(ItemInit.STAFF_BRONZE));
			staffMaterials.put("Platinum", new ItemStack(ItemInit.STAFF_PLATINUM));
			staffMaterials.put("Steel", new ItemStack(ItemInit.STAFF_STEEL));
			staffMaterials.put("Shadow Platinum", new ItemStack(ItemInit.STAFF_SHADOW_PLATINUM));
			staffMaterials.put("Frost Steel", new ItemStack(ItemInit.STAFF_FROST_STEEL));
			staffMaterials.put("Obsidian", new ItemStack(ItemInit.STAFF_OBSIDIAN));
			staffMaterials.put("Crystallite", new ItemStack(ItemInit.STAFF_CRYSTALLITE));
			staffMaterials.put("Dusksteel", new ItemStack(ItemInit.STAFF_DUSKSTEEL));
			recipes.put(input1, input2, staffMaterials);
			for(ItemStack stack : staffMaterials.values()) {
				staffJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, staffJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.STAFF_IRON), exp);
			expValues.put(new ItemStack(ItemInit.STAFF_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.STAFF_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.STAFF_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.STAFF_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.STAFF_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.STAFF_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.STAFF_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.STAFF_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.STAFF_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.STAFF_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.STAFF_DUSKSTEEL), exp+6);
			break;
		case "longsword":
			longswordMaterials.put("Iron", new ItemStack(ItemInit.LONGSWORD_IRON));
			longswordMaterials.put("Kobold", new ItemStack(ItemInit.LONGSWORD_KOBOLD));
			longswordMaterials.put("Copper", new ItemStack(ItemInit.LONGSWORD_COPPER));
			longswordMaterials.put("Silver", new ItemStack(ItemInit.LONGSWORD_SILVER));
			longswordMaterials.put("Bronze", new ItemStack(ItemInit.LONGSWORD_BRONZE));
			longswordMaterials.put("Platinum", new ItemStack(ItemInit.LONGSWORD_PLATINUM));
			longswordMaterials.put("Steel", new ItemStack(ItemInit.LONGSWORD_STEEL));
			longswordMaterials.put("Shadow Platinum", new ItemStack(ItemInit.LONGSWORD_SHADOW_PLATINUM));
			longswordMaterials.put("Frost Steel", new ItemStack(ItemInit.LONGSWORD_FROST_STEEL));
			longswordMaterials.put("Obsidian", new ItemStack(ItemInit.LONGSWORD_OBSIDIAN));
			longswordMaterials.put("Crystallite", new ItemStack(ItemInit.LONGSWORD_CRYSTALLITE));
			longswordMaterials.put("Dusksteel", new ItemStack(ItemInit.LONGSWORD_DUSKSTEEL));
			recipes.put(input1, input2, longswordMaterials);
			for(ItemStack stack : longswordMaterials.values()) {
				longswordJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, longswordJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.LONGSWORD_IRON), exp);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.LONGSWORD_DUSKSTEEL), exp+6);
			break;
		case "kodachi":
			kodachiMaterials.put("Iron", new ItemStack(ItemInit.KODACHI_IRON));
			kodachiMaterials.put("Kobold", new ItemStack(ItemInit.KODACHI_KOBOLD));
			kodachiMaterials.put("Copper", new ItemStack(ItemInit.KODACHI_COPPER));
			kodachiMaterials.put("Silver", new ItemStack(ItemInit.KODACHI_SILVER));
			kodachiMaterials.put("Bronze", new ItemStack(ItemInit.KODACHI_BRONZE));
			kodachiMaterials.put("Platinum", new ItemStack(ItemInit.KODACHI_PLATINUM));
			kodachiMaterials.put("Steel", new ItemStack(ItemInit.KODACHI_STEEL));
			kodachiMaterials.put("Shadow Platinum", new ItemStack(ItemInit.KODACHI_SHADOW_PLATINUM));
			kodachiMaterials.put("Frost Steel", new ItemStack(ItemInit.KODACHI_FROST_STEEL));
			kodachiMaterials.put("Obsidian", new ItemStack(ItemInit.KODACHI_OBSIDIAN));
			kodachiMaterials.put("Crystallite", new ItemStack(ItemInit.KODACHI_CRYSTALLITE));
			kodachiMaterials.put("Dusksteel", new ItemStack(ItemInit.KODACHI_DUSKSTEEL));
			recipes.put(input1, input2, kodachiMaterials);
			for(ItemStack stack : kodachiMaterials.values()) {
				kodachiJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, kodachiJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.KODACHI_IRON), exp);
			expValues.put(new ItemStack(ItemInit.KODACHI_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.KODACHI_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.KODACHI_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.KODACHI_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.KODACHI_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.KODACHI_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.KODACHI_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.KODACHI_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.KODACHI_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.KODACHI_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.KODACHI_DUSKSTEEL), exp+6);
			break;
		case "battleaxe":
			battleaxeMaterials.put("Iron", new ItemStack(ItemInit.BATTLEAXE_IRON));
			battleaxeMaterials.put("Kobold", new ItemStack(ItemInit.BATTLEAXE_KOBOLD));
			battleaxeMaterials.put("Copper", new ItemStack(ItemInit.BATTLEAXE_COPPER));
			battleaxeMaterials.put("Silver", new ItemStack(ItemInit.BATTLEAXE_SILVER));
			battleaxeMaterials.put("Bronze", new ItemStack(ItemInit.BATTLEAXE_BRONZE));
			battleaxeMaterials.put("Platinum", new ItemStack(ItemInit.BATTLEAXE_PLATINUM));
			battleaxeMaterials.put("Steel", new ItemStack(ItemInit.BATTLEAXE_STEEL));
			battleaxeMaterials.put("Shadow Platinum", new ItemStack(ItemInit.BATTLEAXE_SHADOW_PLATINUM));
			battleaxeMaterials.put("Frost Steel", new ItemStack(ItemInit.BATTLEAXE_FROST_STEEL));
			battleaxeMaterials.put("Obsidian", new ItemStack(ItemInit.BATTLEAXE_OBSIDIAN));
			battleaxeMaterials.put("Crystallite", new ItemStack(ItemInit.BATTLEAXE_CRYSTALLITE));
			battleaxeMaterials.put("Dusksteel", new ItemStack(ItemInit.BATTLEAXE_DUSKSTEEL));
			recipes.put(input1, input2, battleaxeMaterials);
			for(ItemStack stack : battleaxeMaterials.values()) {
				battleaxeJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, battleaxeJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_IRON), exp);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.BATTLEAXE_DUSKSTEEL), exp+6);
			break;
		case "zweihander":
			zweihanderMaterials.put("Iron", new ItemStack(ItemInit.ZWEIHANDER_IRON));
			zweihanderMaterials.put("Kobold", new ItemStack(ItemInit.ZWEIHANDER_KOBOLD));
			zweihanderMaterials.put("Copper", new ItemStack(ItemInit.ZWEIHANDER_COPPER));
			zweihanderMaterials.put("Silver", new ItemStack(ItemInit.ZWEIHANDER_SILVER));
			zweihanderMaterials.put("Bronze", new ItemStack(ItemInit.ZWEIHANDER_BRONZE));
			zweihanderMaterials.put("Platinum", new ItemStack(ItemInit.ZWEIHANDER_PLATINUM));
			zweihanderMaterials.put("Steel", new ItemStack(ItemInit.ZWEIHANDER_STEEL));
			zweihanderMaterials.put("Shadow Platinum", new ItemStack(ItemInit.ZWEIHANDER_SHADOW_PLATINUM));
			zweihanderMaterials.put("Frost Steel", new ItemStack(ItemInit.ZWEIHANDER_FROST_STEEL));
			zweihanderMaterials.put("Obsidian", new ItemStack(ItemInit.ZWEIHANDER_OBSIDIAN));
			zweihanderMaterials.put("Crystallite", new ItemStack(ItemInit.ZWEIHANDER_CRYSTALLITE));
			zweihanderMaterials.put("Dusksteel", new ItemStack(ItemInit.ZWEIHANDER_DUSKSTEEL));
			recipes.put(input1, input2, zweihanderMaterials);
			for(ItemStack stack : zweihanderMaterials.values()) {
				zweihanderJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, zweihanderJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_IRON), exp);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.ZWEIHANDER_DUSKSTEEL), exp+6);
			break;
		case "nodachi":
			nodachiMaterials.put("Iron", new ItemStack(ItemInit.NODACHI_IRON));
			nodachiMaterials.put("Kobold", new ItemStack(ItemInit.NODACHI_KOBOLD));
			nodachiMaterials.put("Copper", new ItemStack(ItemInit.NODACHI_COPPER));
			nodachiMaterials.put("Silver", new ItemStack(ItemInit.NODACHI_SILVER));
			nodachiMaterials.put("Bronze", new ItemStack(ItemInit.NODACHI_BRONZE));
			nodachiMaterials.put("Platinum", new ItemStack(ItemInit.NODACHI_PLATINUM));
			nodachiMaterials.put("Steel", new ItemStack(ItemInit.NODACHI_STEEL));
			nodachiMaterials.put("Shadow Platinum", new ItemStack(ItemInit.NODACHI_SHADOW_PLATINUM));
			nodachiMaterials.put("Frost Steel", new ItemStack(ItemInit.NODACHI_FROST_STEEL));
			nodachiMaterials.put("Obsidian", new ItemStack(ItemInit.NODACHI_OBSIDIAN));
			nodachiMaterials.put("Crystallite", new ItemStack(ItemInit.NODACHI_CRYSTALLITE));
			nodachiMaterials.put("Dusksteel", new ItemStack(ItemInit.NODACHI_DUSKSTEEL));
			recipes.put(input1, input2, nodachiMaterials);
			for(ItemStack stack : nodachiMaterials.values()) {
				nodachiJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, nodachiJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.NODACHI_IRON), exp);
			expValues.put(new ItemStack(ItemInit.NODACHI_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.NODACHI_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.NODACHI_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.NODACHI_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.NODACHI_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.NODACHI_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.NODACHI_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.NODACHI_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.NODACHI_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.NODACHI_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.NODACHI_DUSKSTEEL), exp+6);
			break;
		case "sabre":
			sabreMaterials.put("Iron", new ItemStack(ItemInit.SABRE_IRON));
			sabreMaterials.put("Kobold", new ItemStack(ItemInit.SABRE_KOBOLD));
			sabreMaterials.put("Copper", new ItemStack(ItemInit.SABRE_COPPER));
			sabreMaterials.put("Silver", new ItemStack(ItemInit.SABRE_SILVER));
			sabreMaterials.put("Bronze", new ItemStack(ItemInit.SABRE_BRONZE));
			sabreMaterials.put("Platinum", new ItemStack(ItemInit.SABRE_PLATINUM));
			sabreMaterials.put("Steel", new ItemStack(ItemInit.SABRE_STEEL));
			sabreMaterials.put("Shadow Platinum", new ItemStack(ItemInit.SABRE_SHADOW_PLATINUM));
			sabreMaterials.put("Frost Steel", new ItemStack(ItemInit.SABRE_FROST_STEEL));
			sabreMaterials.put("Obsidian", new ItemStack(ItemInit.SABRE_OBSIDIAN));
			sabreMaterials.put("Crystallite", new ItemStack(ItemInit.SABRE_CRYSTALLITE));
			sabreMaterials.put("Dusksteel", new ItemStack(ItemInit.SABRE_DUSKSTEEL));
			recipes.put(input1, input2, sabreMaterials);
			for(ItemStack stack : sabreMaterials.values()) {
				sabreJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, sabreJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.SABRE_IRON), exp);
			expValues.put(new ItemStack(ItemInit.SABRE_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.SABRE_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.SABRE_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.SABRE_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.SABRE_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.SABRE_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.SABRE_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.SABRE_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.SABRE_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.SABRE_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.SABRE_DUSKSTEEL), exp+6);
			break;
		case "makhaira":
			makhairaMaterials.put("Iron", new ItemStack(ItemInit.MAKHAIRA_IRON));
			makhairaMaterials.put("Kobold", new ItemStack(ItemInit.MAKHAIRA_KOBOLD));
			makhairaMaterials.put("Copper", new ItemStack(ItemInit.MAKHAIRA_COPPER));
			makhairaMaterials.put("Silver", new ItemStack(ItemInit.MAKHAIRA_SILVER));
			makhairaMaterials.put("Bronze", new ItemStack(ItemInit.MAKHAIRA_BRONZE));
			makhairaMaterials.put("Platinum", new ItemStack(ItemInit.MAKHAIRA_PLATINUM));
			makhairaMaterials.put("Steel", new ItemStack(ItemInit.MAKHAIRA_STEEL));
			makhairaMaterials.put("Shadow Platinum", new ItemStack(ItemInit.MAKHAIRA_SHADOW_PLATINUM));
			makhairaMaterials.put("Frost Steel", new ItemStack(ItemInit.MAKHAIRA_FROST_STEEL));
			makhairaMaterials.put("Obsidian", new ItemStack(ItemInit.MAKHAIRA_OBSIDIAN));
			makhairaMaterials.put("Crystallite", new ItemStack(ItemInit.MAKHAIRA_CRYSTALLITE));
			makhairaMaterials.put("Dusksteel", new ItemStack(ItemInit.MAKHAIRA_DUSKSTEEL));
			recipes.put(input1, input2, makhairaMaterials);
			for(ItemStack stack : makhairaMaterials.values()) {
				makhairaJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, makhairaJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_IRON), exp);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.MAKHAIRA_DUSKSTEEL), exp+6);
			break;
		case "spear":
			spearMaterials.put("Iron", new ItemStack(ItemInit.SPEAR_IRON));
			spearMaterials.put("Kobold", new ItemStack(ItemInit.SPEAR_KOBOLD));
			spearMaterials.put("Copper", new ItemStack(ItemInit.SPEAR_COPPER));
			spearMaterials.put("Silver", new ItemStack(ItemInit.SPEAR_SILVER));
			spearMaterials.put("Bronze", new ItemStack(ItemInit.SPEAR_BRONZE));
			spearMaterials.put("Platinum", new ItemStack(ItemInit.SPEAR_PLATINUM));
			spearMaterials.put("Steel", new ItemStack(ItemInit.SPEAR_STEEL));
			spearMaterials.put("Shadow Platinum", new ItemStack(ItemInit.SPEAR_SHADOW_PLATINUM));
			spearMaterials.put("Frost Steel", new ItemStack(ItemInit.SPEAR_FROST_STEEL));
			spearMaterials.put("Obsidian", new ItemStack(ItemInit.SPEAR_OBSIDIAN));
			spearMaterials.put("Crystallite", new ItemStack(ItemInit.SPEAR_CRYSTALLITE));
			spearMaterials.put("Dusksteel", new ItemStack(ItemInit.SPEAR_DUSKSTEEL));
			recipes.put(input1, input2, spearMaterials);
			for(ItemStack stack : spearMaterials.values()) {
				spearJeiMap.put(input2, stack);
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, input1, spearJeiMap);
			}
			
			expValues.put(new ItemStack(ItemInit.SPEAR_IRON), exp);
			expValues.put(new ItemStack(ItemInit.SPEAR_KOBOLD), exp+1);
			expValues.put(new ItemStack(ItemInit.SPEAR_COPPER), exp+1);
			expValues.put(new ItemStack(ItemInit.SPEAR_SILVER), exp+2);
			expValues.put(new ItemStack(ItemInit.SPEAR_BRONZE), exp+2);
			expValues.put(new ItemStack(ItemInit.SPEAR_PLATINUM), exp+3);
			expValues.put(new ItemStack(ItemInit.SPEAR_STEEL), exp+3);
			expValues.put(new ItemStack(ItemInit.SPEAR_SHADOW_PLATINUM), exp+4);
			expValues.put(new ItemStack(ItemInit.SPEAR_FROST_STEEL), exp+4);
			expValues.put(new ItemStack(ItemInit.SPEAR_OBSIDIAN), exp+5);
			expValues.put(new ItemStack(ItemInit.SPEAR_CRYSTALLITE), exp+5);
			expValues.put(new ItemStack(ItemInit.SPEAR_DUSKSTEEL), exp+6);
			break;
		case "handle":
			toolRodMats.put("ToolRod", new ItemStack(ItemInit.LONG_TOOL_ROD));
			recipes.put(input1, input2, toolRodMats);
			toolRodJeiMap.put(input2, new ItemStack(ItemInit.LONG_TOOL_ROD));
			jeiRecipes.put(0, input1, toolRodJeiMap);
			expValues.put(new ItemStack(ItemInit.LONG_TOOL_ROD), exp);
			break;
		}
	}
}
