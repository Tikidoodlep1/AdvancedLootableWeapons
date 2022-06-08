package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForgeWeaponRecipes {

	private static final HashMap<String, Item> toolRodMats = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> daggerMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> kabutowariMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> rapierMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> talwarMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> cleaverMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> maceMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> staffMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> longswordMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> kodachiMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> battleaxeMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> zweihanderMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> nodachiMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> sabreMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> makhairaMaterials = Maps.<String, Item>newHashMap();
	private static final HashMap<String, Item> spearMaterials = Maps.<String, Item>newHashMap();
	private static final Multimap<ItemStack, ItemStack> toolRodJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> daggerJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> kabutowariJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> rapierJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> talwarJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> cleaverJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> maceJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> staffJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> longswordJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> kodachiJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> battleaxeJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> zweihanderJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> nodachiJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> sabreJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> makhairaJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> spearJeiMap = ArrayListMultimap.create();
	private static final Multimap<ItemStack, ItemStack> toolHeadJeiMap = ArrayListMultimap.create();
	private static final Table<Item, Item, HashMap<String, Item>> recipes = HashBasedTable.<Item, Item, HashMap<String, Item>>create();
	private static final Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> jeiRecipes = HashBasedTable.<Integer, ItemStack, Multimap<ItemStack, ItemStack>>create();
	private static final Map<Item, Integer> expValues = Maps.<Item, Integer>newHashMap();
	public static final ForgeWeaponRecipes INSTANCE = new ForgeWeaponRecipes();
	
	public ForgeWeaponRecipes() {
		registerNewRecipe(ItemInit.DAGGER_HOT_TOOL_HEAD_2, ItemInit.HOT_TOOL_ROD_2, "dagger", ConfigHandler.GLOBAL_DAGGER_CRAFTING_EXP);
		registerNewRecipe(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5, ItemInit.HOT_TOOL_ROD_2, "kabutowari", ConfigHandler.GLOBAL_KABUTOWARI_CRAFTING_EXP);
		registerNewRecipe(ItemInit.RAPIER_HOT_TOOL_HEAD_4, ItemInit.HOT_TOOL_ROD_2, "rapier", ConfigHandler.GLOBAL_RAPIER_CRAFTING_EXP);
		registerNewRecipe(ItemInit.TALWAR_HOT_TOOL_HEAD_3, ItemInit.HOT_TOOL_ROD_2, "talwar", ConfigHandler.GLOBAL_TALWAR_CRAFTING_EXP);
		registerNewRecipe(ItemInit.CLEAVER_HOT_TOOL_HEAD, ItemInit.HOT_TOOL_ROD_2, "cleaver", ConfigHandler.GLOBAL_CLEAVER_CRAFTING_EXP);
		registerNewRecipe(ItemInit.MACE_HOT_TOOL_HEAD_3, ItemInit.HOT_TOOL_ROD_2, "mace", ConfigHandler.GLOBAL_MACE_CRAFTING_EXP);
		registerNewRecipe(ItemInit.STAFF_HOT_TOOL_HEAD_5, ItemInit.LONG_TOOL_ROD, "staff", ConfigHandler.GLOBAL_STAFF_CRAFTING_EXP);
		registerNewRecipe(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4, ItemInit.HOT_TOOL_ROD_2, "longsword", ConfigHandler.GLOBAL_LONGSWORD_CRAFTING_EXP);
		registerNewRecipe(ItemInit.KODACHI_HOT_TOOL_HEAD_2, ItemInit.HOT_TOOL_ROD_2, "kodachi", ConfigHandler.GLOBAL_KODACHI_CRAFTING_EXP);
		registerNewRecipe(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5, ItemInit.LONG_TOOL_ROD, "battleaxe", ConfigHandler.GLOBAL_BATTLEAXE_CRAFTING_EXP);
		registerNewRecipe(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5, ItemInit.LONG_TOOL_ROD, "zweihander", ConfigHandler.GLOBAL_ZWEIHANDER_CRAFTING_EXP);
		registerNewRecipe(ItemInit.NODACHI_HOT_TOOL_HEAD_4, ItemInit.HOT_TOOL_ROD_2, "nodachi", ConfigHandler.GLOBAL_NODACHI_CRAFTING_EXP);
		registerNewRecipe(ItemInit.SABRE_HOT_TOOL_HEAD_4, ItemInit.HOT_TOOL_ROD_2, "sabre", ConfigHandler.GLOBAL_SABRE_CRAFTING_EXP);
		registerNewRecipe(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3, ItemInit.HOT_TOOL_ROD_2, "makhaira", ConfigHandler.GLOBAL_MAKHAIRA_CRAFTING_EXP);
		registerNewRecipe(ItemInit.SPEAR_HOT_TOOL_HEAD_2, ItemInit.LONG_TOOL_ROD, "spear", ConfigHandler.GLOBAL_SPEAR_CRAFTING_EXP);
		registerNewRecipe(ItemInit.HOT_TOOL_ROD_2, ItemInit.HOT_TOOL_ROD_2, "handle", ConfigHandler.GLOBAL_LONG_WEAPON_HANDLE_CRAFTING_EXP);
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
	
	public ItemStack getItemResult(String material, Item input1, Item input2) {
		if(input1 == ItemInit.HOT_TOOL_ROD_2 && input2 == ItemInit.HOT_TOOL_ROD_2) {
			return new ItemStack(ItemInit.LONG_TOOL_ROD);
		}
		
		final HashMap<String, Item> recipe = recipes.get(input1, input2);
		System.out.println(input1.toString() + ", " + input2.toString());
		if(recipe != null) {
			return new ItemStack(recipe.get(material));
		}
//		for(Item stack1 : recipe.rowKeySet()) {
//			if(input1 instanceof ItemHotToolHead && input1 == stack1) {
//				String mat = material;
//				for(Item stack2 : recipe.columnKeySet()) {
//					if(input2 == stack2) {
//						if(recipe.contains(stack1, stack2)) {
//							final Map<String, Item> recipeMap = recipe.get(stack1, stack2);
//							for(String type : recipeMap.keySet()) {
//								if(mat.equalsIgnoreCase(type)) {
//									return new ItemStack(recipeMap.get(type));
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
		return ItemStack.EMPTY;
	}
	
	public int getExpValue(ItemStack stack) {
		Map<Item, Integer> exp = getExpValues();
		for(Item compStack : exp.keySet()) {
			if(compStack == stack.getItem()) {
				return exp.get(compStack);
			}
		}
		return 0;
	}
	
	public Map<Item, Integer> getExpValues(){
		return ForgeWeaponRecipes.expValues;
	}
	
	public Table<Item, Item, HashMap<String, Item>> getDualCraftingList(){
		return ForgeWeaponRecipes.recipes;
	}
	
	public Table<Integer, ItemStack, Multimap<ItemStack, ItemStack>> getJeiCraftingList(){
		return ForgeWeaponRecipes.jeiRecipes;
	}
	
	private Table<Item, Item, HashMap<String, Item>> registerNewRecipe(Item input1, Item input2, String output, int exp){
		if(!(recipes.contains(input1, input2))) {
			coverMaterials(input1, input2, output, exp);
		}
		return recipes;
	}
	
	private void coverMaterials(Item input1, Item input2, String type, int exp) {
		switch(type) {
		case "dagger":
			daggerMaterials.put("Iron", ItemInit.DAGGER_IRON);
			daggerMaterials.put("Kobold", ItemInit.DAGGER_KOBOLD);
			daggerMaterials.put("Copper", ItemInit.DAGGER_COPPER);
			daggerMaterials.put("Silver", ItemInit.DAGGER_SILVER);
			daggerMaterials.put("Bronze", ItemInit.DAGGER_BRONZE);
			daggerMaterials.put("Platinum", ItemInit.DAGGER_PLATINUM);
			daggerMaterials.put("Steel", ItemInit.DAGGER_STEEL);
			daggerMaterials.put("Shadow Platinum", ItemInit.DAGGER_SHADOW_PLATINUM);
			daggerMaterials.put("Frost Steel", ItemInit.DAGGER_FROST_STEEL);
			daggerMaterials.put("Obsidian", ItemInit.DAGGER_OBSIDIAN);
			daggerMaterials.put("Crystallite", ItemInit.DAGGER_CRYSTALLITE);
			daggerMaterials.put("Dusksteel", ItemInit.DAGGER_DUSKSTEEL);
			recipes.put(input1, input2, daggerMaterials);
			for(Item stack : daggerMaterials.values()) {
				daggerJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i,new ItemStack(input1), daggerJeiMap);
			}
			
			expValues.put(ItemInit.DAGGER_IRON, exp);
			expValues.put(ItemInit.DAGGER_KOBOLD, exp+1);
			expValues.put(ItemInit.DAGGER_COPPER, exp+1);
			expValues.put(ItemInit.DAGGER_SILVER, exp+2);
			expValues.put(ItemInit.DAGGER_BRONZE, exp+2);
			expValues.put(ItemInit.DAGGER_PLATINUM, exp+3);
			expValues.put(ItemInit.DAGGER_STEEL, exp+3);
			expValues.put(ItemInit.DAGGER_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.DAGGER_FROST_STEEL, exp+4);
			expValues.put(ItemInit.DAGGER_OBSIDIAN, exp+5);
			expValues.put(ItemInit.DAGGER_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.DAGGER_DUSKSTEEL, exp+6);
			break;
		case "kabutowari":
			kabutowariMaterials.put("Iron", ItemInit.KABUTOWARI_IRON);
			kabutowariMaterials.put("Kobold", ItemInit.KABUTOWARI_KOBOLD);
			kabutowariMaterials.put("Copper", ItemInit.KABUTOWARI_COPPER);
			kabutowariMaterials.put("Silver", ItemInit.KABUTOWARI_SILVER);
			kabutowariMaterials.put("Bronze", ItemInit.KABUTOWARI_BRONZE);
			kabutowariMaterials.put("Platinum", ItemInit.KABUTOWARI_PLATINUM);
			kabutowariMaterials.put("Steel", ItemInit.KABUTOWARI_STEEL);
			kabutowariMaterials.put("Shadow Platinum", ItemInit.KABUTOWARI_SHADOW_PLATINUM);
			kabutowariMaterials.put("Frost Steel", ItemInit.KABUTOWARI_FROST_STEEL);
			kabutowariMaterials.put("Obsidian", ItemInit.KABUTOWARI_OBSIDIAN);
			kabutowariMaterials.put("Crystallite", ItemInit.KABUTOWARI_CRYSTALLITE);
			kabutowariMaterials.put("Dusksteel", ItemInit.KABUTOWARI_DUSKSTEEL);
			recipes.put(input1, input2, kabutowariMaterials);
			for(Item stack : kabutowariMaterials.values()) {
				kabutowariJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), kabutowariJeiMap);
			}
			
			expValues.put(ItemInit.KABUTOWARI_IRON, exp);
			expValues.put(ItemInit.KABUTOWARI_KOBOLD, exp+1);
			expValues.put(ItemInit.KABUTOWARI_COPPER, exp+1);
			expValues.put(ItemInit.KABUTOWARI_SILVER, exp+2);
			expValues.put(ItemInit.KABUTOWARI_BRONZE, exp+2);
			expValues.put(ItemInit.KABUTOWARI_PLATINUM, exp+3);
			expValues.put(ItemInit.KABUTOWARI_STEEL, exp+3);
			expValues.put(ItemInit.KABUTOWARI_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.KABUTOWARI_FROST_STEEL, exp+4);
			expValues.put(ItemInit.KABUTOWARI_OBSIDIAN, exp+5);
			expValues.put(ItemInit.KABUTOWARI_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.KABUTOWARI_DUSKSTEEL, exp+6);
			break;
		case "rapier":
			rapierMaterials.put("Iron", ItemInit.RAPIER_IRON);
			rapierMaterials.put("Kobold", ItemInit.RAPIER_KOBOLD);
			rapierMaterials.put("Copper", ItemInit.RAPIER_COPPER);
			rapierMaterials.put("Silver", ItemInit.RAPIER_SILVER);
			rapierMaterials.put("Bronze", ItemInit.RAPIER_BRONZE);
			rapierMaterials.put("Platinum", ItemInit.RAPIER_PLATINUM);
			rapierMaterials.put("Steel", ItemInit.RAPIER_STEEL);
			rapierMaterials.put("Shadow Platinum", ItemInit.RAPIER_SHADOW_PLATINUM);
			rapierMaterials.put("Frost Steel", ItemInit.RAPIER_FROST_STEEL);
			rapierMaterials.put("Obsidian", ItemInit.RAPIER_OBSIDIAN);
			rapierMaterials.put("Crystallite", ItemInit.RAPIER_CRYSTALLITE);
			rapierMaterials.put("Dusksteel", ItemInit.RAPIER_DUSKSTEEL);
			recipes.put(input1, input2, rapierMaterials);
			for(Item stack : rapierMaterials.values()) {
				rapierJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), rapierJeiMap);
			}
			
			expValues.put(ItemInit.RAPIER_IRON, exp);
			expValues.put(ItemInit.RAPIER_KOBOLD, exp+1);
			expValues.put(ItemInit.RAPIER_COPPER, exp+1);
			expValues.put(ItemInit.RAPIER_SILVER, exp+2);
			expValues.put(ItemInit.RAPIER_BRONZE, exp+2);
			expValues.put(ItemInit.RAPIER_PLATINUM, exp+3);
			expValues.put(ItemInit.RAPIER_STEEL, exp+3);
			expValues.put(ItemInit.RAPIER_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.RAPIER_FROST_STEEL, exp+4);
			expValues.put(ItemInit.RAPIER_OBSIDIAN, exp+5);
			expValues.put(ItemInit.RAPIER_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.RAPIER_DUSKSTEEL, exp+6);
			break;
		case "talwar":
			talwarMaterials.put("Iron", ItemInit.TALWAR_IRON);
			talwarMaterials.put("Kobold", ItemInit.TALWAR_KOBOLD);
			talwarMaterials.put("Copper", ItemInit.TALWAR_COPPER);
			talwarMaterials.put("Silver", ItemInit.TALWAR_SILVER);
			talwarMaterials.put("Bronze", ItemInit.TALWAR_BRONZE);
			talwarMaterials.put("Platinum", ItemInit.TALWAR_PLATINUM);
			talwarMaterials.put("Steel", ItemInit.TALWAR_STEEL);
			talwarMaterials.put("Shadow Platinum", ItemInit.TALWAR_SHADOW_PLATINUM);
			talwarMaterials.put("Frost Steel", ItemInit.TALWAR_FROST_STEEL);
			talwarMaterials.put("Obsidian", ItemInit.TALWAR_OBSIDIAN);
			talwarMaterials.put("Crystallite", ItemInit.TALWAR_CRYSTALLITE);
			talwarMaterials.put("Dusksteel", ItemInit.TALWAR_DUSKSTEEL);
			recipes.put(input1, input2, talwarMaterials);
			for(Item stack : talwarMaterials.values()) {
				talwarJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), talwarJeiMap);
			}
			
			expValues.put(ItemInit.TALWAR_IRON, exp);
			expValues.put(ItemInit.TALWAR_KOBOLD, exp+1);
			expValues.put(ItemInit.TALWAR_COPPER, exp+1);
			expValues.put(ItemInit.TALWAR_SILVER, exp+2);
			expValues.put(ItemInit.TALWAR_BRONZE, exp+2);
			expValues.put(ItemInit.TALWAR_PLATINUM, exp+3);
			expValues.put(ItemInit.TALWAR_STEEL, exp+3);
			expValues.put(ItemInit.TALWAR_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.TALWAR_FROST_STEEL, exp+4);
			expValues.put(ItemInit.TALWAR_OBSIDIAN, exp+5);
			expValues.put(ItemInit.TALWAR_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.TALWAR_DUSKSTEEL, exp+6);
			break;
		case "cleaver":
			cleaverMaterials.put("Iron", ItemInit.CLEAVER_IRON);
			cleaverMaterials.put("Kobold", ItemInit.CLEAVER_KOBOLD);
			cleaverMaterials.put("Copper", ItemInit.CLEAVER_COPPER);
			cleaverMaterials.put("Silver", ItemInit.CLEAVER_SILVER);
			cleaverMaterials.put("Bronze", ItemInit.CLEAVER_BRONZE);
			cleaverMaterials.put("Platinum", ItemInit.CLEAVER_PLATINUM);
			cleaverMaterials.put("Steel", ItemInit.CLEAVER_STEEL);
			cleaverMaterials.put("Shadow Platinum", ItemInit.CLEAVER_SHADOW_PLATINUM);
			cleaverMaterials.put("Frost Steel", ItemInit.CLEAVER_FROST_STEEL);
			cleaverMaterials.put("Obsidian", ItemInit.CLEAVER_OBSIDIAN);
			cleaverMaterials.put("Crystallite", ItemInit.CLEAVER_CRYSTALLITE);
			cleaverMaterials.put("Dusksteel", ItemInit.CLEAVER_DUSKSTEEL);
			recipes.put(input1, input2, cleaverMaterials);
			for(Item stack : cleaverMaterials.values()) {
				cleaverJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), cleaverJeiMap);
			}
			expValues.put(ItemInit.CLEAVER_IRON, exp);
			expValues.put(ItemInit.CLEAVER_KOBOLD, exp+1);
			expValues.put(ItemInit.CLEAVER_COPPER, exp+1);
			expValues.put(ItemInit.CLEAVER_SILVER, exp+2);
			expValues.put(ItemInit.CLEAVER_BRONZE, exp+2);
			expValues.put(ItemInit.CLEAVER_PLATINUM, exp+3);
			expValues.put(ItemInit.CLEAVER_STEEL, exp+3);
			expValues.put(ItemInit.CLEAVER_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.CLEAVER_FROST_STEEL, exp+4);
			expValues.put(ItemInit.CLEAVER_OBSIDIAN, exp+5);
			expValues.put(ItemInit.CLEAVER_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.CLEAVER_DUSKSTEEL, exp+6);
			break;
		case "mace":
			maceMaterials.put("Iron", ItemInit.MACE_IRON);
			maceMaterials.put("Kobold", ItemInit.MACE_KOBOLD);
			maceMaterials.put("Copper", ItemInit.MACE_COPPER);
			maceMaterials.put("Silver", ItemInit.MACE_SILVER);
			maceMaterials.put("Bronze", ItemInit.MACE_BRONZE);
			maceMaterials.put("Platinum", ItemInit.MACE_PLATINUM);
			maceMaterials.put("Steel", ItemInit.MACE_STEEL);
			maceMaterials.put("Shadow Platinum", ItemInit.MACE_SHADOW_PLATINUM);
			maceMaterials.put("Frost Steel", ItemInit.MACE_FROST_STEEL);
			maceMaterials.put("Obsidian", ItemInit.MACE_OBSIDIAN);
			maceMaterials.put("Crystallite", ItemInit.MACE_CRYSTALLITE);
			maceMaterials.put("Dusksteel", ItemInit.MACE_DUSKSTEEL);
			recipes.put(input1, input2, maceMaterials);
			for(Item stack : maceMaterials.values()) {
				maceJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), maceJeiMap);
			}
			
			expValues.put(ItemInit.MACE_IRON, exp);
			expValues.put(ItemInit.MACE_KOBOLD, exp+1);
			expValues.put(ItemInit.MACE_COPPER, exp+1);
			expValues.put(ItemInit.MACE_SILVER, exp+2);
			expValues.put(ItemInit.MACE_BRONZE, exp+2);
			expValues.put(ItemInit.MACE_PLATINUM, exp+3);
			expValues.put(ItemInit.MACE_STEEL, exp+3);
			expValues.put(ItemInit.MACE_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.MACE_FROST_STEEL, exp+4);
			expValues.put(ItemInit.MACE_OBSIDIAN, exp+5);
			expValues.put(ItemInit.MACE_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.MACE_DUSKSTEEL, exp+6);
			break;
		case "staff":
			staffMaterials.put("Iron", ItemInit.STAFF_IRON);
			staffMaterials.put("Kobold", ItemInit.STAFF_KOBOLD);
			staffMaterials.put("Copper", ItemInit.STAFF_COPPER);
			staffMaterials.put("Silver", ItemInit.STAFF_SILVER);
			staffMaterials.put("Bronze", ItemInit.STAFF_BRONZE);
			staffMaterials.put("Platinum", ItemInit.STAFF_PLATINUM);
			staffMaterials.put("Steel", ItemInit.STAFF_STEEL);
			staffMaterials.put("Shadow Platinum", ItemInit.STAFF_SHADOW_PLATINUM);
			staffMaterials.put("Frost Steel", ItemInit.STAFF_FROST_STEEL);
			staffMaterials.put("Obsidian", ItemInit.STAFF_OBSIDIAN);
			staffMaterials.put("Crystallite", ItemInit.STAFF_CRYSTALLITE);
			staffMaterials.put("Dusksteel", ItemInit.STAFF_DUSKSTEEL);
			recipes.put(input1, input2, staffMaterials);
			for(Item stack : staffMaterials.values()) {
				staffJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), staffJeiMap);
			}
			
			expValues.put(ItemInit.STAFF_IRON, exp);
			expValues.put(ItemInit.STAFF_KOBOLD, exp+1);
			expValues.put(ItemInit.STAFF_COPPER, exp+1);
			expValues.put(ItemInit.STAFF_SILVER, exp+2);
			expValues.put(ItemInit.STAFF_BRONZE, exp+2);
			expValues.put(ItemInit.STAFF_PLATINUM, exp+3);
			expValues.put(ItemInit.STAFF_STEEL, exp+3);
			expValues.put(ItemInit.STAFF_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.STAFF_FROST_STEEL, exp+4);
			expValues.put(ItemInit.STAFF_OBSIDIAN, exp+5);
			expValues.put(ItemInit.STAFF_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.STAFF_DUSKSTEEL, exp+6);
			break;
		case "longsword":
			longswordMaterials.put("Iron", ItemInit.LONGSWORD_IRON);
			longswordMaterials.put("Kobold", ItemInit.LONGSWORD_KOBOLD);
			longswordMaterials.put("Copper", ItemInit.LONGSWORD_COPPER);
			longswordMaterials.put("Silver", ItemInit.LONGSWORD_SILVER);
			longswordMaterials.put("Bronze", ItemInit.LONGSWORD_BRONZE);
			longswordMaterials.put("Platinum", ItemInit.LONGSWORD_PLATINUM);
			longswordMaterials.put("Steel", ItemInit.LONGSWORD_STEEL);
			longswordMaterials.put("Shadow Platinum", ItemInit.LONGSWORD_SHADOW_PLATINUM);
			longswordMaterials.put("Frost Steel", ItemInit.LONGSWORD_FROST_STEEL);
			longswordMaterials.put("Obsidian", ItemInit.LONGSWORD_OBSIDIAN);
			longswordMaterials.put("Crystallite", ItemInit.LONGSWORD_CRYSTALLITE);
			longswordMaterials.put("Dusksteel", ItemInit.LONGSWORD_DUSKSTEEL);
			recipes.put(input1, input2, longswordMaterials);
			for(Item stack : longswordMaterials.values()) {
				longswordJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), longswordJeiMap);
			}
			
			expValues.put(ItemInit.LONGSWORD_IRON, exp);
			expValues.put(ItemInit.LONGSWORD_KOBOLD, exp+1);
			expValues.put(ItemInit.LONGSWORD_COPPER, exp+1);
			expValues.put(ItemInit.LONGSWORD_SILVER, exp+2);
			expValues.put(ItemInit.LONGSWORD_BRONZE, exp+2);
			expValues.put(ItemInit.LONGSWORD_PLATINUM, exp+3);
			expValues.put(ItemInit.LONGSWORD_STEEL, exp+3);
			expValues.put(ItemInit.LONGSWORD_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.LONGSWORD_FROST_STEEL, exp+4);
			expValues.put(ItemInit.LONGSWORD_OBSIDIAN, exp+5);
			expValues.put(ItemInit.LONGSWORD_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.LONGSWORD_DUSKSTEEL, exp+6);
			break;
		case "kodachi":
			kodachiMaterials.put("Iron", ItemInit.KODACHI_IRON);
			kodachiMaterials.put("Kobold", ItemInit.KODACHI_KOBOLD);
			kodachiMaterials.put("Copper", ItemInit.KODACHI_COPPER);
			kodachiMaterials.put("Silver", ItemInit.KODACHI_SILVER);
			kodachiMaterials.put("Bronze", ItemInit.KODACHI_BRONZE);
			kodachiMaterials.put("Platinum", ItemInit.KODACHI_PLATINUM);
			kodachiMaterials.put("Steel", ItemInit.KODACHI_STEEL);
			kodachiMaterials.put("Shadow Platinum", ItemInit.KODACHI_SHADOW_PLATINUM);
			kodachiMaterials.put("Frost Steel", ItemInit.KODACHI_FROST_STEEL);
			kodachiMaterials.put("Obsidian", ItemInit.KODACHI_OBSIDIAN);
			kodachiMaterials.put("Crystallite", ItemInit.KODACHI_CRYSTALLITE);
			kodachiMaterials.put("Dusksteel", ItemInit.KODACHI_DUSKSTEEL);
			recipes.put(input1, input2, kodachiMaterials);
			for(Item stack : kodachiMaterials.values()) {
				kodachiJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), kodachiJeiMap);
			}
			
			expValues.put(ItemInit.KODACHI_IRON, exp);
			expValues.put(ItemInit.KODACHI_KOBOLD, exp+1);
			expValues.put(ItemInit.KODACHI_COPPER, exp+1);
			expValues.put(ItemInit.KODACHI_SILVER, exp+2);
			expValues.put(ItemInit.KODACHI_BRONZE, exp+2);
			expValues.put(ItemInit.KODACHI_PLATINUM, exp+3);
			expValues.put(ItemInit.KODACHI_STEEL, exp+3);
			expValues.put(ItemInit.KODACHI_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.KODACHI_FROST_STEEL, exp+4);
			expValues.put(ItemInit.KODACHI_OBSIDIAN, exp+5);
			expValues.put(ItemInit.KODACHI_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.KODACHI_DUSKSTEEL, exp+6);
			break;
		case "battleaxe":
			battleaxeMaterials.put("Iron", ItemInit.BATTLEAXE_IRON);
			battleaxeMaterials.put("Kobold", ItemInit.BATTLEAXE_KOBOLD);
			battleaxeMaterials.put("Copper", ItemInit.BATTLEAXE_COPPER);
			battleaxeMaterials.put("Silver", ItemInit.BATTLEAXE_SILVER);
			battleaxeMaterials.put("Bronze", ItemInit.BATTLEAXE_BRONZE);
			battleaxeMaterials.put("Platinum", ItemInit.BATTLEAXE_PLATINUM);
			battleaxeMaterials.put("Steel", ItemInit.BATTLEAXE_STEEL);
			battleaxeMaterials.put("Shadow Platinum", ItemInit.BATTLEAXE_SHADOW_PLATINUM);
			battleaxeMaterials.put("Frost Steel", ItemInit.BATTLEAXE_FROST_STEEL);
			battleaxeMaterials.put("Obsidian", ItemInit.BATTLEAXE_OBSIDIAN);
			battleaxeMaterials.put("Crystallite", ItemInit.BATTLEAXE_CRYSTALLITE);
			battleaxeMaterials.put("Dusksteel", ItemInit.BATTLEAXE_DUSKSTEEL);
			recipes.put(input1, input2, battleaxeMaterials);
			for(Item stack : battleaxeMaterials.values()) {
				battleaxeJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), battleaxeJeiMap);
			}
			
			expValues.put(ItemInit.BATTLEAXE_IRON, exp);
			expValues.put(ItemInit.BATTLEAXE_KOBOLD, exp+1);
			expValues.put(ItemInit.BATTLEAXE_COPPER, exp+1);
			expValues.put(ItemInit.BATTLEAXE_SILVER, exp+2);
			expValues.put(ItemInit.BATTLEAXE_BRONZE, exp+2);
			expValues.put(ItemInit.BATTLEAXE_PLATINUM, exp+3);
			expValues.put(ItemInit.BATTLEAXE_STEEL, exp+3);
			expValues.put(ItemInit.BATTLEAXE_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.BATTLEAXE_FROST_STEEL, exp+4);
			expValues.put(ItemInit.BATTLEAXE_OBSIDIAN, exp+5);
			expValues.put(ItemInit.BATTLEAXE_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.BATTLEAXE_DUSKSTEEL, exp+6);
			break;
		case "zweihander":
			zweihanderMaterials.put("Iron", ItemInit.ZWEIHANDER_IRON);
			zweihanderMaterials.put("Kobold", ItemInit.ZWEIHANDER_KOBOLD);
			zweihanderMaterials.put("Copper", ItemInit.ZWEIHANDER_COPPER);
			zweihanderMaterials.put("Silver", ItemInit.ZWEIHANDER_SILVER);
			zweihanderMaterials.put("Bronze", ItemInit.ZWEIHANDER_BRONZE);
			zweihanderMaterials.put("Platinum", ItemInit.ZWEIHANDER_PLATINUM);
			zweihanderMaterials.put("Steel", ItemInit.ZWEIHANDER_STEEL);
			zweihanderMaterials.put("Shadow Platinum", ItemInit.ZWEIHANDER_SHADOW_PLATINUM);
			zweihanderMaterials.put("Frost Steel", ItemInit.ZWEIHANDER_FROST_STEEL);
			zweihanderMaterials.put("Obsidian", ItemInit.ZWEIHANDER_OBSIDIAN);
			zweihanderMaterials.put("Crystallite", ItemInit.ZWEIHANDER_CRYSTALLITE);
			zweihanderMaterials.put("Dusksteel", ItemInit.ZWEIHANDER_DUSKSTEEL);
			recipes.put(input1, input2, zweihanderMaterials);
			for(Item stack : zweihanderMaterials.values()) {
				zweihanderJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), zweihanderJeiMap);
			}
			
			expValues.put(ItemInit.ZWEIHANDER_IRON, exp);
			expValues.put(ItemInit.ZWEIHANDER_KOBOLD, exp+1);
			expValues.put(ItemInit.ZWEIHANDER_COPPER, exp+1);
			expValues.put(ItemInit.ZWEIHANDER_SILVER, exp+2);
			expValues.put(ItemInit.ZWEIHANDER_BRONZE, exp+2);
			expValues.put(ItemInit.ZWEIHANDER_PLATINUM, exp+3);
			expValues.put(ItemInit.ZWEIHANDER_STEEL, exp+3);
			expValues.put(ItemInit.ZWEIHANDER_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.ZWEIHANDER_FROST_STEEL, exp+4);
			expValues.put(ItemInit.ZWEIHANDER_OBSIDIAN, exp+5);
			expValues.put(ItemInit.ZWEIHANDER_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.ZWEIHANDER_DUSKSTEEL, exp+6);
			break;
		case "nodachi":
			nodachiMaterials.put("Iron", ItemInit.NODACHI_IRON);
			nodachiMaterials.put("Kobold", ItemInit.NODACHI_KOBOLD);
			nodachiMaterials.put("Copper", ItemInit.NODACHI_COPPER);
			nodachiMaterials.put("Silver", ItemInit.NODACHI_SILVER);
			nodachiMaterials.put("Bronze", ItemInit.NODACHI_BRONZE);
			nodachiMaterials.put("Platinum", ItemInit.NODACHI_PLATINUM);
			nodachiMaterials.put("Steel", ItemInit.NODACHI_STEEL);
			nodachiMaterials.put("Shadow Platinum", ItemInit.NODACHI_SHADOW_PLATINUM);
			nodachiMaterials.put("Frost Steel", ItemInit.NODACHI_FROST_STEEL);
			nodachiMaterials.put("Obsidian", ItemInit.NODACHI_OBSIDIAN);
			nodachiMaterials.put("Crystallite", ItemInit.NODACHI_CRYSTALLITE);
			nodachiMaterials.put("Dusksteel", ItemInit.NODACHI_DUSKSTEEL);
			recipes.put(input1, input2, nodachiMaterials);
			for(Item stack : nodachiMaterials.values()) {
				nodachiJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), nodachiJeiMap);
			}
			
			expValues.put(ItemInit.NODACHI_IRON, exp);
			expValues.put(ItemInit.NODACHI_KOBOLD, exp+1);
			expValues.put(ItemInit.NODACHI_COPPER, exp+1);
			expValues.put(ItemInit.NODACHI_SILVER, exp+2);
			expValues.put(ItemInit.NODACHI_BRONZE, exp+2);
			expValues.put(ItemInit.NODACHI_PLATINUM, exp+3);
			expValues.put(ItemInit.NODACHI_STEEL, exp+3);
			expValues.put(ItemInit.NODACHI_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.NODACHI_FROST_STEEL, exp+4);
			expValues.put(ItemInit.NODACHI_OBSIDIAN, exp+5);
			expValues.put(ItemInit.NODACHI_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.NODACHI_DUSKSTEEL, exp+6);
			break;
		case "sabre":
			sabreMaterials.put("Iron", ItemInit.SABRE_IRON);
			sabreMaterials.put("Kobold", ItemInit.SABRE_KOBOLD);
			sabreMaterials.put("Copper", ItemInit.SABRE_COPPER);
			sabreMaterials.put("Silver", ItemInit.SABRE_SILVER);
			sabreMaterials.put("Bronze", ItemInit.SABRE_BRONZE);
			sabreMaterials.put("Platinum", ItemInit.SABRE_PLATINUM);
			sabreMaterials.put("Steel", ItemInit.SABRE_STEEL);
			sabreMaterials.put("Shadow Platinum", ItemInit.SABRE_SHADOW_PLATINUM);
			sabreMaterials.put("Frost Steel", ItemInit.SABRE_FROST_STEEL);
			sabreMaterials.put("Obsidian", ItemInit.SABRE_OBSIDIAN);
			sabreMaterials.put("Crystallite", ItemInit.SABRE_CRYSTALLITE);
			sabreMaterials.put("Dusksteel", ItemInit.SABRE_DUSKSTEEL);
			recipes.put(input1, input2, sabreMaterials);
			for(Item stack : sabreMaterials.values()) {
				sabreJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), sabreJeiMap);
			}
			
			expValues.put(ItemInit.SABRE_IRON, exp);
			expValues.put(ItemInit.SABRE_KOBOLD, exp+1);
			expValues.put(ItemInit.SABRE_COPPER, exp+1);
			expValues.put(ItemInit.SABRE_SILVER, exp+2);
			expValues.put(ItemInit.SABRE_BRONZE, exp+2);
			expValues.put(ItemInit.SABRE_PLATINUM, exp+3);
			expValues.put(ItemInit.SABRE_STEEL, exp+3);
			expValues.put(ItemInit.SABRE_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.SABRE_FROST_STEEL, exp+4);
			expValues.put(ItemInit.SABRE_OBSIDIAN, exp+5);
			expValues.put(ItemInit.SABRE_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.SABRE_DUSKSTEEL, exp+6);
			break;
		case "makhaira":
			makhairaMaterials.put("Iron", ItemInit.MAKHAIRA_IRON);
			makhairaMaterials.put("Kobold", ItemInit.MAKHAIRA_KOBOLD);
			makhairaMaterials.put("Copper", ItemInit.MAKHAIRA_COPPER);
			makhairaMaterials.put("Silver", ItemInit.MAKHAIRA_SILVER);
			makhairaMaterials.put("Bronze", ItemInit.MAKHAIRA_BRONZE);
			makhairaMaterials.put("Platinum", ItemInit.MAKHAIRA_PLATINUM);
			makhairaMaterials.put("Steel", ItemInit.MAKHAIRA_STEEL);
			makhairaMaterials.put("Shadow Platinum", ItemInit.MAKHAIRA_SHADOW_PLATINUM);
			makhairaMaterials.put("Frost Steel", ItemInit.MAKHAIRA_FROST_STEEL);
			makhairaMaterials.put("Obsidian", ItemInit.MAKHAIRA_OBSIDIAN);
			makhairaMaterials.put("Crystallite", ItemInit.MAKHAIRA_CRYSTALLITE);
			makhairaMaterials.put("Dusksteel", ItemInit.MAKHAIRA_DUSKSTEEL);
			recipes.put(input1, input2, makhairaMaterials);
			for(Item stack : makhairaMaterials.values()) {
				makhairaJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), makhairaJeiMap);
			}
			
			expValues.put(ItemInit.MAKHAIRA_IRON, exp);
			expValues.put(ItemInit.MAKHAIRA_KOBOLD, exp+1);
			expValues.put(ItemInit.MAKHAIRA_COPPER, exp+1);
			expValues.put(ItemInit.MAKHAIRA_SILVER, exp+2);
			expValues.put(ItemInit.MAKHAIRA_BRONZE, exp+2);
			expValues.put(ItemInit.MAKHAIRA_PLATINUM, exp+3);
			expValues.put(ItemInit.MAKHAIRA_STEEL, exp+3);
			expValues.put(ItemInit.MAKHAIRA_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.MAKHAIRA_FROST_STEEL, exp+4);
			expValues.put(ItemInit.MAKHAIRA_OBSIDIAN, exp+5);
			expValues.put(ItemInit.MAKHAIRA_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.MAKHAIRA_DUSKSTEEL, exp+6);
			break;
		case "spear":
			spearMaterials.put("Iron", ItemInit.SPEAR_IRON);
			spearMaterials.put("Kobold", ItemInit.SPEAR_KOBOLD);
			spearMaterials.put("Copper", ItemInit.SPEAR_COPPER);
			spearMaterials.put("Silver", ItemInit.SPEAR_SILVER);
			spearMaterials.put("Bronze", ItemInit.SPEAR_BRONZE);
			spearMaterials.put("Platinum", ItemInit.SPEAR_PLATINUM);
			spearMaterials.put("Steel", ItemInit.SPEAR_STEEL);
			spearMaterials.put("Shadow Platinum", ItemInit.SPEAR_SHADOW_PLATINUM);
			spearMaterials.put("Frost Steel", ItemInit.SPEAR_FROST_STEEL);
			spearMaterials.put("Obsidian", ItemInit.SPEAR_OBSIDIAN);
			spearMaterials.put("Crystallite", ItemInit.SPEAR_CRYSTALLITE);
			spearMaterials.put("Dusksteel", ItemInit.SPEAR_DUSKSTEEL);
			recipes.put(input1, input2, spearMaterials);
			for(Item stack : spearMaterials.values()) {
				spearJeiMap.put(new ItemStack(input2), new ItemStack(stack));
			}
			for(int i = 0; i < 13; i++) {
				jeiRecipes.put(i, new ItemStack(input1), spearJeiMap);
			}
			
			expValues.put(ItemInit.SPEAR_IRON, exp);
			expValues.put(ItemInit.SPEAR_KOBOLD, exp+1);
			expValues.put(ItemInit.SPEAR_COPPER, exp+1);
			expValues.put(ItemInit.SPEAR_SILVER, exp+2);
			expValues.put(ItemInit.SPEAR_BRONZE, exp+2);
			expValues.put(ItemInit.SPEAR_PLATINUM, exp+3);
			expValues.put(ItemInit.SPEAR_STEEL, exp+3);
			expValues.put(ItemInit.SPEAR_SHADOW_PLATINUM, exp+4);
			expValues.put(ItemInit.SPEAR_FROST_STEEL, exp+4);
			expValues.put(ItemInit.SPEAR_OBSIDIAN, exp+5);
			expValues.put(ItemInit.SPEAR_CRYSTALLITE, exp+5);
			expValues.put(ItemInit.SPEAR_DUSKSTEEL, exp+6);
			break;
		case "handle":
			toolRodMats.put("ToolRod", ItemInit.LONG_TOOL_ROD);
			recipes.put(input1, input2, toolRodMats);
			toolRodJeiMap.put(new ItemStack(input2), new ItemStack(ItemInit.LONG_TOOL_ROD));
			jeiRecipes.put(0, new ItemStack(input1), toolRodJeiMap);
			expValues.put(ItemInit.LONG_TOOL_ROD, exp);
			break;
		}
	}
}
