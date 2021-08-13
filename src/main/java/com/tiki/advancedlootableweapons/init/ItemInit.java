package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.items.ItemBase;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolSpear;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemInit {

	public static final List<Item> items = new ArrayList<Item>();	
	
	//Recipes with meta
	public static void createRecipes() {
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_dagger_head"), new ResourceLocation("dagger_head"), new ItemStack(DAGGER_HEAD), new Object[] {" w", "w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_kabutowari_head"), new ResourceLocation("kabutowari_head"), new ItemStack(KABUTOWARI_HEAD), new Object[] {"  w", "w w", " w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_rapier_head"), new ResourceLocation("rapier_head"), new ItemStack(RAPIER_HEAD), new Object[] {"w  ", " w ", "  w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_talwar_head"), new ResourceLocation("talwar_head"), new ItemStack(TALWAR_HEAD), new Object[] {" w", "w ", " w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_mace_head"), new ResourceLocation("mace_head"), new ItemStack(MACE_HEAD), new Object[] {"w w", " w ", "w w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_cleaver_head"), new ResourceLocation("cleaver_head"), new ItemStack(CLEAVER_HEAD), new Object[] {"ww", " w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_staff_head"), new ResourceLocation("staff_head"), new ItemStack(STAFF_HEAD, 2), new Object[] {"w  ", "ww ", " ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_longsword_head"), new ResourceLocation("longsword_head"), new ItemStack(LONGSWORD_HEAD), new Object[] {" w ", " w ", "www", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_battleaxe_head"), new ResourceLocation("battleaxe_head"), new ItemStack(BATTLEAXE_HEAD), new Object[] {"www", "w w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_zweihander_head"), new ResourceLocation("zweihander_head"), new ItemStack(ZWEIHANDER_HEAD), new Object[] {" w ", "www", " w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_kodachi_head"), new ResourceLocation("kodachi_head"), new ItemStack(KODACHI_HEAD), new Object[] {"w ", "ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_nodachi_head"), new ResourceLocation("nodachi_head"), new ItemStack(NODACHI_HEAD), new Object[] {"w", "w", "w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_sabre_head"), new ResourceLocation("sabre_head"), new ItemStack(SABRE_HEAD), new Object[] {" w", "w ", "w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_makhaira_head"), new ResourceLocation("makhaira_head"), new ItemStack(MAKHAIRA_HEAD), new Object[] {" w", "ww", "ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_spear_head"), new ResourceLocation("spear_head"), new ItemStack(SPEAR_HEAD), new Object[] {" w ", "www", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
	}
	
	//Items
	public static final Item INGOT_TIN = new ItemBase("ingot_tin");
	public static Item INGOT_KOBOLD;
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_SILVER = new ItemBase("ingot_silver");
	public static final Item INGOT_BRONZE = new ItemBase("ingot_bronze");
	public static final Item INGOT_PLATINUM = new ItemBase("ingot_platinum");
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel");
	public static Item INGOT_SHADOW_PLATINUM;
	public static Item INGOT_FROST_STEEL;
	public static final Item INGOT_OBSIDIAN = new ItemBase("ingot_obsidian");
	public static Item INGOT_CRYSTALLITE;
	public static Item INGOT_DUSKSTEEL;
	
	public static Item CRYSTAL;
	public static Item SHADOW;
	public static Item SHADOW_BLOB;
	
	public static final Item DAGGER_HEAD = new ItemBase("dagger_head");
	public static final Item KABUTOWARI_HEAD = new ItemBase("kabutowari_head");
	public static final Item RAPIER_HEAD = new ItemBase("rapier_head");
	public static final Item TALWAR_HEAD = new ItemBase("talwar_head");
	public static final Item MACE_HEAD = new ItemBase("mace_head");
	public static final Item CLEAVER_HEAD = new ItemBase("cleaver_head");
	public static final Item STAFF_HEAD = new ItemBase("staff_head");
	public static final Item LONGSWORD_HEAD = new ItemBase("longsword_head");
	public static final Item BATTLEAXE_HEAD = new ItemBase("battleaxe_head");
	public static final Item ZWEIHANDER_HEAD = new ItemBase("zweihander_head");
	public static final Item KODACHI_HEAD = new ItemBase("kodachi_head");
	public static final Item NODACHI_HEAD = new ItemBase("nodachi_head");
	public static final Item SABRE_HEAD = new ItemBase("sabre_head");
	public static final Item MAKHAIRA_HEAD = new ItemBase("makhaira_head");
	public static final Item SPEAR_HEAD = new ItemBase("spear_head");
	public static final Item LONG_WEAPON_HANDLE = new ItemBase("long_weapon_handle");
	
	/*
	 * Materials
	 * WOOD(0, 59, 2.0F, 0.0F, 15),
     * STONE(1, 131, 4.0F, 1.0F, 5),
     * IRON(2, 250, 6.0F, 2.0F, 14),
     * DIAMOND(3, 1561, 8.0F, 3.0F, 10),
     * 
     * (Sting name, int harvestLevel, int durability, float efficiency, float damage, int enchantability)
     * 
     * 
     * Armor Materials
     * LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
     * CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
     * IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
     * DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
     * 
     * (String name, String textureName, int maxDamageFactorIn, int[] damageReductionAmountArrayIn(boots, legs, chest, head), int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn)
	 */
	
	//Mats Declarations
	public static final ToolMaterial MAT_KOBOLD = EnumHelper.addToolMaterial("mat_kobold", 1, ConfigHandler.KOBOLD_DURABILITY, 6.0F, ConfigHandler.KOBOLD_DAMAGE, 22).setRepairItem(new ItemStack(INGOT_KOBOLD));
	public static final ToolMaterial MAT_COPPER = EnumHelper.addToolMaterial("mat_copper", 2, ConfigHandler.COPPER_DURABILITY, 6.5F, ConfigHandler.COPPER_DAMAGE, 10).setRepairItem(new ItemStack(INGOT_COPPER));
	public static final ToolMaterial MAT_SILVER = EnumHelper.addToolMaterial("mat_silver", 2, ConfigHandler.SILVER_DURABILITY, 7.0F, ConfigHandler.SILVER_DAMAGE, 24).setRepairItem(new ItemStack(INGOT_SILVER));
	public static final ToolMaterial MAT_BRONZE = EnumHelper.addToolMaterial("mat_bronze", 2, ConfigHandler.BRONZE_DURABILITY, 6.5F, ConfigHandler.BRONZE_DAMAGE, 12).setRepairItem(new ItemStack(INGOT_BRONZE));
	public static final ToolMaterial MAT_PLATINUM = EnumHelper.addToolMaterial("mat_platinum", 2, ConfigHandler.PLATINUM_DURABILITY, 10.0F, ConfigHandler.PLATINUM_DAMAGE, 26).setRepairItem(new ItemStack(INGOT_PLATINUM));
	public static final ToolMaterial MAT_STEEL = EnumHelper.addToolMaterial("mat_steel", 2, ConfigHandler.STEEL_DURABILITY, 7.0F, ConfigHandler.STEEL_DAMAGE, 18).setRepairItem(new ItemStack(INGOT_STEEL));
    public static final ToolMaterial MAT_SHADOW_PLATINUM = EnumHelper.addToolMaterial("mat_shadow_platinum", 3, ConfigHandler.SHADOW_PLATINUM_DURABILITY, 8.25F, ConfigHandler.SHADOW_PLATINUM_DAMAGE, 21).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	public static final ToolMaterial MAT_FROST_STEEL = EnumHelper.addToolMaterial("mat_frost_steel", 3, ConfigHandler.FROST_STEEL_DURABILITY, 7.0F, ConfigHandler.FROST_STEEL_DAMAGE, 30).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
    public static final ToolMaterial MAT_OBSIDIAN = EnumHelper.addToolMaterial("mat_obsidian", 3, ConfigHandler.OBSIDIAN_DURABILITY, 7.5F, ConfigHandler.OBSIDIAN_DAMAGE, 18).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	public static final ToolMaterial MAT_CRYSTALLITE = EnumHelper.addToolMaterial("mat_crystallite", 3, ConfigHandler.CRYSTALLITE_DURABILITY, 7.5F, ConfigHandler.CRYSTALLITE_DAMAGE, 20).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	public static final ToolMaterial MAT_DUSKSTEEL = EnumHelper.addToolMaterial("mat_dusksteel", 3, ConfigHandler.DUSKSTEEL_DURABILITY, 8.5F, ConfigHandler.DUSKSTEEL_DAMAGE, 14).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	public static final ArmorMaterial AMAT_KOBOLD = EnumHelper.addArmorMaterial("amat_kobold", ModInfo.ID + ":kobold", ConfigHandler.KOBOLD_ARMOR_DURABILITY, new int[]{1,3,4,2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.KOBOLD_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_KOBOLD));
	public static final ArmorMaterial AMAT_COPPER = EnumHelper.addArmorMaterial("amat_copper", ModInfo.ID + ":copper", ConfigHandler.COPPER_ARMOR_DURABILITY, new int[]{2,3,4,2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.COPPER_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_COPPER));
	public static final ArmorMaterial AMAT_SILVER = EnumHelper.addArmorMaterial("amat_silver", ModInfo.ID + ":silver", ConfigHandler.SILVER_ARMOR_DURABILITY, new int[]{3,5,7,3}, 24, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.SILVER_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_SILVER));
	public static final ArmorMaterial AMAT_BRONZE = EnumHelper.addArmorMaterial("amat_bronze", ModInfo.ID + ":bronze", ConfigHandler.BRONZE_ARMOR_DURABILITY, new int[]{5,9,12,5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.BRONZE_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_BRONZE));
	public static final ArmorMaterial AMAT_PLATINUM = EnumHelper.addArmorMaterial("amat_platinum", ModInfo.ID + ":platinum", ConfigHandler.PLATINUM_ARMOR_DURABILITY, new int[]{7,13,17,8}, 26, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.PLATINUM_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_PLATINUM));
	public static final ArmorMaterial AMAT_STEEL = EnumHelper.addArmorMaterial("amat_steel", ModInfo.ID + ":steel", ConfigHandler.STEEL_ARMOR_DURABILITY, new int[]{3,5,6,3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.STEEL_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_STEEL));
	public static final ArmorMaterial AMAT_SHADOW_PLATINUM = EnumHelper.addArmorMaterial("amat_shadow_platinum", ModInfo.ID + ":shadow_platinum", ConfigHandler.SHADOW_PLATINUM_ARMOR_DURABILITY, new int[]{7,13,16,7}, 21, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.SHADOW_PLATINUM_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	public static final ArmorMaterial AMAT_FROST_STEEL = EnumHelper.addArmorMaterial("amat_frost_steel", ModInfo.ID + ":frost_steel", ConfigHandler.FROST_STEEL_ARMOR_DURABILITY, new int[]{4,7,9,4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.FROST_STEEL_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
	public static final ArmorMaterial AMAT_OBSIDIAN = EnumHelper.addArmorMaterial("amat_obsidian", ModInfo.ID + ":obsidian", ConfigHandler.OBSIDIAN_ARMOR_DURABILITY, new int[]{3,5,6,3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.OBSIDIAN_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	public static final ArmorMaterial AMAT_CRYSTALLITE = EnumHelper.addArmorMaterial("amat_crystallite", ModInfo.ID + ":crystallite", ConfigHandler.CRYSTALLITE_ARMOR_DURABILITY, new int[]{5,10,13,6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.CRYSTALLITE_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	public static final ArmorMaterial AMAT_DUSKSTEEL = EnumHelper.addArmorMaterial("amat_dusksteel", ModInfo.ID + ":dusksteel", ConfigHandler.DUSKSTEEL_ARMOR_DURABILITY, new int[]{5,8,11,5}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.DUSKSTEEL_ARMOR_HARDNESS).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	
	//Tools
	//public static final Item AXE_STEEL = new ToolAxe("axe_steel", MAT_STEEL);
	//public static final Item HOE_STEEL = new ToolHoe("hoe_steel", MAT_STEEL);
	//public static final Item PICKAXE_STEEL = new ToolPickaxe("pickaxe_steel", MAT_STEEL);
	//public static final Item SHOVEL_STEEL = new ToolShovel("shovel_steel", MAT_STEEL);
	//public static final Item SWORD_STEEL = new ToolSword("sword_steel", MAT_STEEL);
	
	
	//Daggers
	public static Item DAGGER_WOOD;
	public static Item DAGGER_IRON;
	public static Item DAGGER_KOBOLD;
	public static Item DAGGER_COPPER;
	public static Item DAGGER_SILVER;
	public static Item DAGGER_BRONZE;
	public static Item DAGGER_PLATINUM;
	public static Item DAGGER_STEEL;
	public static Item DAGGER_SHADOW_PLATINUM;
	public static Item DAGGER_FROST_STEEL;
	public static Item DAGGER_OBSIDIAN;
	public static Item DAGGER_CRYSTALLITE;
	public static Item DAGGER_DUSKSTEEL;
	
	
	//Kabutowaris
	public static Item KABUTOWARI_WOOD;
	public static Item KABUTOWARI_IRON;
	public static Item KABUTOWARI_KOBOLD;
	public static Item KABUTOWARI_COPPER;
	public static Item KABUTOWARI_SILVER;
	public static Item KABUTOWARI_BRONZE;
	public static Item KABUTOWARI_PLATINUM;
	public static Item KABUTOWARI_STEEL;
	public static Item KABUTOWARI_SHADOW_PLATINUM;
	public static Item KABUTOWARI_FROST_STEEL;
	public static Item KABUTOWARI_OBSIDIAN;
	public static Item KABUTOWARI_CRYSTALLITE;
	public static Item KABUTOWARI_DUSKSTEEL;
	
	//Rapiers
	public static Item RAPIER_WOOD;
	public static Item RAPIER_IRON;
	public static Item RAPIER_KOBOLD;
	public static Item RAPIER_COPPER;
	public static Item RAPIER_SILVER;
	public static Item RAPIER_BRONZE;
	public static Item RAPIER_PLATINUM;
	public static Item RAPIER_STEEL;
	public static Item RAPIER_SHADOW_PLATINUM;
	public static Item RAPIER_FROST_STEEL;
	public static Item RAPIER_OBSIDIAN;
	public static Item RAPIER_CRYSTALLITE;
	public static Item RAPIER_DUSKSTEEL;
	
	//Rapiers
	public static Item TALWAR_WOOD;
	public static Item TALWAR_IRON;
	public static Item TALWAR_KOBOLD;
	public static Item TALWAR_COPPER;
	public static Item TALWAR_SILVER;
	public static Item TALWAR_BRONZE;
	public static Item TALWAR_PLATINUM;
	public static Item TALWAR_STEEL;
	public static Item TALWAR_SHADOW_PLATINUM;
	public static Item TALWAR_FROST_STEEL;
	public static Item TALWAR_OBSIDIAN;
	public static Item TALWAR_CRYSTALLITE;
	public static Item TALWAR_DUSKSTEEL;
	
	//Cleavers
	public static Item CLEAVER_WOOD;
	public static Item CLEAVER_IRON;
	public static Item CLEAVER_KOBOLD;
	public static Item CLEAVER_COPPER;
	public static Item CLEAVER_SILVER;
	public static Item CLEAVER_BRONZE;
	public static Item CLEAVER_PLATINUM;
	public static Item CLEAVER_STEEL;
	public static Item CLEAVER_SHADOW_PLATINUM;
	public static Item CLEAVER_FROST_STEEL;
	public static Item CLEAVER_OBSIDIAN;
	public static Item CLEAVER_CRYSTALLITE;
	public static Item CLEAVER_DUSKSTEEL;
	
	//Maces
	public static Item MACE_WOOD;
	public static Item MACE_IRON;
	public static Item MACE_KOBOLD;
	public static Item MACE_COPPER;
	public static Item MACE_SILVER;
	public static Item MACE_BRONZE;
	public static Item MACE_PLATINUM;
	public static Item MACE_STEEL;
	public static Item MACE_SHADOW_PLATINUM;
	public static Item MACE_FROST_STEEL;
	public static Item MACE_OBSIDIAN;
	public static Item MACE_CRYSTALLITE;
	public static Item MACE_DUSKSTEEL;
	
	//Staffs
	public static Item STAFF_WOOD;
	public static Item STAFF_IRON;
	public static Item STAFF_KOBOLD;
	public static Item STAFF_COPPER;
	public static Item STAFF_SILVER;
	public static Item STAFF_BRONZE;
	public static Item STAFF_PLATINUM;
	public static Item STAFF_STEEL;
	public static Item STAFF_SHADOW_PLATINUM;
	public static Item STAFF_FROST_STEEL;
	public static Item STAFF_OBSIDIAN;
	public static Item STAFF_CRYSTALLITE;
	public static Item STAFF_DUSKSTEEL;
	
	//Longswords
	public static Item LONGSWORD_WOOD;
	public static Item LONGSWORD_IRON;
	public static Item LONGSWORD_KOBOLD;
	public static Item LONGSWORD_COPPER;
	public static Item LONGSWORD_SILVER;
	public static Item LONGSWORD_BRONZE;
	public static Item LONGSWORD_PLATINUM;
	public static Item LONGSWORD_STEEL;
	public static Item LONGSWORD_SHADOW_PLATINUM;
	public static Item LONGSWORD_FROST_STEEL;
	public static Item LONGSWORD_OBSIDIAN;
	public static Item LONGSWORD_CRYSTALLITE;
	public static Item LONGSWORD_DUSKSTEEL;
	
	//Kodachis
	public static Item KODACHI_WOOD;
	public static Item KODACHI_IRON;
	public static Item KODACHI_KOBOLD;
	public static Item KODACHI_COPPER;
	public static Item KODACHI_SILVER;
	public static Item KODACHI_BRONZE;
	public static Item KODACHI_PLATINUM;
	public static Item KODACHI_STEEL;
	public static Item KODACHI_SHADOW_PLATINUM;
	public static Item KODACHI_FROST_STEEL;
	public static Item KODACHI_OBSIDIAN;
	public static Item KODACHI_CRYSTALLITE;
	public static Item KODACHI_DUSKSTEEL;
	
	//Battleaxes
	public static Item BATTLEAXE_WOOD;
	public static Item BATTLEAXE_IRON;
	public static Item BATTLEAXE_KOBOLD;
	public static Item BATTLEAXE_COPPER;
	public static Item BATTLEAXE_SILVER;
	public static Item BATTLEAXE_BRONZE;
	public static Item BATTLEAXE_PLATINUM;
	public static Item BATTLEAXE_STEEL;
	public static Item BATTLEAXE_SHADOW_PLATINUM;
	public static Item BATTLEAXE_FROST_STEEL;
	public static Item BATTLEAXE_OBSIDIAN;
	public static Item BATTLEAXE_CRYSTALLITE;
	public static Item BATTLEAXE_DUSKSTEEL;
	
	//Zweihanders
	public static Item ZWEIHANDER_WOOD;
	public static Item ZWEIHANDER_IRON;
	public static Item ZWEIHANDER_KOBOLD;
	public static Item ZWEIHANDER_COPPER;
	public static Item ZWEIHANDER_SILVER;
	public static Item ZWEIHANDER_BRONZE;
	public static Item ZWEIHANDER_PLATINUM;
	public static Item ZWEIHANDER_STEEL;
	public static Item ZWEIHANDER_SHADOW_PLATINUM;
	public static Item ZWEIHANDER_FROST_STEEL;
	public static Item ZWEIHANDER_OBSIDIAN;
	public static Item ZWEIHANDER_CRYSTALLITE;
	public static Item ZWEIHANDER_DUSKSTEEL;
	
	//Nodachis
	public static Item NODACHI_WOOD;
	public static Item NODACHI_IRON;
	public static Item NODACHI_KOBOLD;
	public static Item NODACHI_COPPER;
	public static Item NODACHI_SILVER;
	public static Item NODACHI_BRONZE;
	public static Item NODACHI_PLATINUM;
	public static Item NODACHI_STEEL;
	public static Item NODACHI_SHADOW_PLATINUM;
	public static Item NODACHI_FROST_STEEL;
	public static Item NODACHI_OBSIDIAN;
	public static Item NODACHI_CRYSTALLITE;
	public static Item NODACHI_DUSKSTEEL;
	
	//Sabres
	public static Item SABRE_WOOD;
	public static Item SABRE_IRON;
	public static Item SABRE_KOBOLD;
	public static Item SABRE_COPPER;
	public static Item SABRE_SILVER;
	public static Item SABRE_BRONZE;
	public static Item SABRE_PLATINUM;
	public static Item SABRE_STEEL;
	public static Item SABRE_SHADOW_PLATINUM;
	public static Item SABRE_FROST_STEEL;
	public static Item SABRE_OBSIDIAN;
	public static Item SABRE_CRYSTALLITE;
	public static Item SABRE_DUSKSTEEL;
	
	//Makhairas
	public static Item MAKHAIRA_WOOD;
	public static Item MAKHAIRA_IRON;
	public static Item MAKHAIRA_KOBOLD;
	public static Item MAKHAIRA_COPPER;
	public static Item MAKHAIRA_SILVER;
	public static Item MAKHAIRA_BRONZE;
	public static Item MAKHAIRA_PLATINUM;
	public static Item MAKHAIRA_STEEL;
	public static Item MAKHAIRA_SHADOW_PLATINUM;
	public static Item MAKHAIRA_FROST_STEEL;
	public static Item MAKHAIRA_OBSIDIAN;
	public static Item MAKHAIRA_CRYSTALLITE;
	public static Item MAKHAIRA_DUSKSTEEL;
	
	//Spears
	public static Item SPEAR_WOOD;
	public static Item SPEAR_IRON;
	public static Item SPEAR_KOBOLD;
	public static Item SPEAR_COPPER;
	public static Item SPEAR_SILVER;
	public static Item SPEAR_BRONZE;
	public static Item SPEAR_PLATINUM;
	public static Item SPEAR_STEEL;
	public static Item SPEAR_SHADOW_PLATINUM;
	public static Item SPEAR_FROST_STEEL;
	public static Item SPEAR_OBSIDIAN;
	public static Item SPEAR_CRYSTALLITE;
	public static Item SPEAR_DUSKSTEEL;
	
	//Sharpening Stones
	public static final Item STONE_SHARPENING_STONE = new ItemSharpeningStone("stone_sharpening_stone", ToolMaterial.WOOD).setMaxStackSize(16);
	public static final Item IRON_SHARPENING_STONE = new ItemSharpeningStone("iron_sharpening_stone", ToolMaterial.IRON).setMaxStackSize(16);
	public static Item KOBOLD_SHARPENING_STONE;
	public static final Item COPPER_SHARPENING_STONE = new ItemSharpeningStone("copper_sharpening_stone", MAT_COPPER).setMaxStackSize(16);
	public static final Item SILVER_SHARPENING_STONE = new ItemSharpeningStone("silver_sharpening_stone", MAT_SILVER).setMaxStackSize(16);
	public static final Item BRONZE_SHARPENING_STONE = new ItemSharpeningStone("bronze_sharpening_stone", MAT_BRONZE).setMaxStackSize(16);
	public static final Item PLATINUM_SHARPENING_STONE = new ItemSharpeningStone("platinum_sharpening_stone", MAT_PLATINUM).setMaxStackSize(16);
	public static final Item STEEL_SHARPENING_STONE = new ItemSharpeningStone("steel_sharpening_stone", MAT_STEEL).setMaxStackSize(16);
	public static Item SHADOW_PLATINUM_SHARPENING_STONE;
	public static Item FROST_STEEL_SHARPENING_STONE;
	public static final Item OBISIDAN_SHARPENING_STONE = new ItemSharpeningStone("obsidian_sharpening_stone", MAT_OBSIDIAN).setMaxStackSize(16);
	public static Item CRYSTALLITE_SHARPENING_STONE;
	public static Item DUSKSTEEL_SHARPENING_STONE;
	
	//Forge Hammers
	public static final Item STONE_FORGE_HAMMER = new ToolForgeHammer("stone_forge_hammer", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item IRON_FORGE_HAMMER = new ToolForgeHammer("iron_forge_hammer", ToolMaterial.IRON).setMaxStackSize(1);
	public static Item KOBOLD_FORGE_HAMMER;
	public static final Item COPPER_FORGE_HAMMER = new ToolForgeHammer("copper_forge_hammer", MAT_COPPER).setMaxStackSize(1);
	public static final Item SILVER_FORGE_HAMMER = new ToolForgeHammer("silver_forge_hammer", MAT_SILVER).setMaxStackSize(1);
	public static final Item BRONZE_FORGE_HAMMER = new ToolForgeHammer("bronze_forge_hammer", MAT_BRONZE).setMaxStackSize(1);
	public static final Item PLATINUM_FORGE_HAMMER = new ToolForgeHammer("platinum_forge_hammer", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item STEEL_FORGE_HAMMER = new ToolForgeHammer("steel_forge_hammer", MAT_STEEL).setMaxStackSize(1);
	public static Item SHADOW_PLATINUM_FORGE_HAMMER;
	public static Item FROST_STEEL_FORGE_HAMMER;
	public static final Item OBSIDIAN_FORGE_HAMMER = new ToolForgeHammer("obsidian_forge_hammer", MAT_OBSIDIAN).setMaxStackSize(1);
	public static Item CRYSTALLITE_FORGE_HAMMER;
	public static Item DUSKSTEEL_FORGE_HAMMER;
	
	//Hot Tool Heads
	public static final Item HOT_TOOL_HEAD = new ItemHotToolHead("hot_tool_head");
	
	public static final Item HOT_TOOL_ROD = new ItemHotToolHead("hot_tool_rod");
	public static final Item HOT_TOOL_ROD_2 = new ItemHotToolHead("hot_tool_rod_2");
	public static final Item LONG_TOOL_ROD = new ItemHotToolHead("long_tool_rod");
	
	public static final Item DAGGER_HOT_TOOL_HEAD = new ItemHotToolHead("dagger_hot_tool_head");
	public static final Item DAGGER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("dagger_hot_tool_head_2");
	
	public static final Item KABUTOWARI_HOT_TOOL_HEAD = new ItemHotToolHead("kabutowari_hot_tool_head");
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("kabutowari_hot_tool_head_2");
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_3 = new ItemHotToolHead("kabutowari_hot_tool_head_3");
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_4 = new ItemHotToolHead("kabutowari_hot_tool_head_4");
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_5 = new ItemHotToolHead("kabutowari_hot_tool_head_5");
	
	public static final Item TALWAR_HOT_TOOL_HEAD = new ItemHotToolHead("talwar_hot_tool_head");
	public static final Item TALWAR_HOT_TOOL_HEAD_2 = new ItemHotToolHead("talwar_hot_tool_head_2");
	public static final Item TALWAR_HOT_TOOL_HEAD_3 = new ItemHotToolHead("talwar_hot_tool_head_3");
	
	public static final Item RAPIER_HOT_TOOL_HEAD = new ItemHotToolHead("rapier_hot_tool_head");
	public static final Item RAPIER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("rapier_hot_tool_head_2");
	public static final Item RAPIER_HOT_TOOL_HEAD_3 = new ItemHotToolHead("rapier_hot_tool_head_3");
	public static final Item RAPIER_HOT_TOOL_HEAD_4 = new ItemHotToolHead("rapier_hot_tool_head_4");
	
	public static final Item CLEAVER_HOT_TOOL_HEAD = new ItemHotToolHead("cleaver_hot_tool_head");
	
	public static final Item MACE_HOT_TOOL_HEAD = new ItemHotToolHead("mace_hot_tool_head");
	public static final Item MACE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("mace_hot_tool_head_2");
	public static final Item MACE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("mace_hot_tool_head_3");
	
	public static final Item STAFF_HOT_TOOL_HEAD = new ItemHotToolHead("staff_hot_tool_head");
	public static final Item STAFF_HOT_TOOL_HEAD_2 = new ItemHotToolHead("staff_hot_tool_head_2");
	public static final Item STAFF_HOT_TOOL_HEAD_3 = new ItemHotToolHead("staff_hot_tool_head_3");
	public static final Item STAFF_HOT_TOOL_HEAD_4 = new ItemHotToolHead("staff_hot_tool_head_4");
	public static final Item STAFF_HOT_TOOL_HEAD_5 = new ItemHotToolHead("staff_hot_tool_head_5");
	
	public static final Item LONGSWORD_HOT_TOOL_HEAD = new ItemHotToolHead("longsword_hot_tool_head");
	public static final Item LONGSWORD_HOT_TOOL_HEAD_2 = new ItemHotToolHead("longsword_hot_tool_head_2");
	public static final Item LONGSWORD_HOT_TOOL_HEAD_3 = new ItemHotToolHead("longsword_hot_tool_head_3");
	public static final Item LONGSWORD_HOT_TOOL_HEAD_4 = new ItemHotToolHead("longsword_hot_tool_head_4");
	
	public static final Item KODACHI_HOT_TOOL_HEAD = new ItemHotToolHead("kodachi_hot_tool_head");
	public static final Item KODACHI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("kodachi_hot_tool_head_2");
	
	public static final Item BATTLEAXE_HOT_TOOL_HEAD = new ItemHotToolHead("battleaxe_hot_tool_head");
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("battleaxe_hot_tool_head_2");
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("battleaxe_hot_tool_head_3");
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_4 = new ItemHotToolHead("battleaxe_hot_tool_head_4");
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_5 = new ItemHotToolHead("battleaxe_hot_tool_head_5");
	
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD = new ItemHotToolHead("zweihander_hot_tool_head");
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("zweihander_hot_tool_head_2");
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_3 = new ItemHotToolHead("zweihander_hot_tool_head_3");
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_4 = new ItemHotToolHead("zweihander_hot_tool_head_4");
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_5 = new ItemHotToolHead("zweihander_hot_tool_head_5");
	
	public static final Item NODACHI_HOT_TOOL_HEAD = new ItemHotToolHead("nodachi_hot_tool_head");
	public static final Item NODACHI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("nodachi_hot_tool_head_2");
	public static final Item NODACHI_HOT_TOOL_HEAD_3 = new ItemHotToolHead("nodachi_hot_tool_head_3");
	public static final Item NODACHI_HOT_TOOL_HEAD_4 = new ItemHotToolHead("nodachi_hot_tool_head_4");
	
	public static final Item SABRE_HOT_TOOL_HEAD = new ItemHotToolHead("sabre_hot_tool_head");
	public static final Item SABRE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("sabre_hot_tool_head_2");
	public static final Item SABRE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("sabre_hot_tool_head_3");
	public static final Item SABRE_HOT_TOOL_HEAD_4 = new ItemHotToolHead("sabre_hot_tool_head_4");
	
	public static final Item MAKHAIRA_HOT_TOOL_HEAD = new ItemHotToolHead("makhaira_hot_tool_head");
	public static final Item MAKHAIRA_HOT_TOOL_HEAD_2 = new ItemHotToolHead("makhaira_hot_tool_head_2");
	public static final Item MAKHAIRA_HOT_TOOL_HEAD_3 = new ItemHotToolHead("makhaira_hot_tool_head_3");
	
	public static final Item SPEAR_HOT_TOOL_HEAD = new ItemHotToolHead("spear_hot_tool_head");
	public static final Item SPEAR_HOT_TOOL_HEAD_2 = new ItemHotToolHead("spear_hot_tool_head_2");
	
	//Armor
	public static Item HELMET_IRON;
	public static Item CHESTPLATE_IRON;
	public static Item LEGGINGS_IRON;
	public static Item BOOTS_IRON;
	
	public static Item HELMET_KOBOLD;
	public static Item CHESTPLATE_KOBOLD;
	public static Item LEGGINGS_KOBOLD;
	public static Item BOOTS_KOBOLD;
	
	public static Item HELMET_COPPER;
	public static Item CHESTPLATE_COPPER;
	public static Item LEGGINGS_COPPER;
	public static Item BOOTS_COPPER;
	
	public static Item HELMET_SILVER;
	public static Item CHESTPLATE_SILVER;
	public static Item LEGGINGS_SILVER;
	public static Item BOOTS_SILVER;
	
	public static Item HELMET_BRONZE;
	public static Item CHESTPLATE_BRONZE;
	public static Item LEGGINGS_BRONZE;
	public static Item BOOTS_BRONZE;
	
	public static Item HELMET_PLATINUM;
	public static Item CHESTPLATE_PLATINUM;
	public static Item LEGGINGS_PLATINUM;
	public static Item BOOTS_PLATINUM;
	
	public static Item HELMET_STEEL;
	public static Item CHESTPLATE_STEEL;
	public static Item LEGGINGS_STEEL;
	public static Item BOOTS_STEEL;
	
	public static Item HELMET_SHADOW_PLATINUM;
	public static Item CHESTPLATE_SHADOW_PLATINUM;
	public static Item LEGGINGS_SHADOW_PLATINUM;
	public static Item BOOTS_SHADOW_PLATINUM;
	
	public static Item HELMET_FROST_STEEL;
	public static Item CHESTPLATE_FROST_STEEL;
	public static Item LEGGINGS_FROST_STEEL;
	public static Item BOOTS_FROST_STEEL;
	
	public static Item HELMET_OBSIDIAN;
	public static Item CHESTPLATE_OBSIDIAN;
	public static Item LEGGINGS_OBSIDIAN;
	public static Item BOOTS_OBSIDIAN;
	
	public static Item HELMET_CRYSTALLITE;
	public static Item CHESTPLATE_CRYSTALLITE;
	public static Item LEGGINGS_CRYSTALLITE;
	public static Item BOOTS_CRYSTALLITE;
	
	public static Item HELMET_DUSKSTEEL;
	public static Item CHESTPLATE_DUSKSTEEL;
	public static Item LEGGINGS_DUSKSTEEL;
	public static Item BOOTS_DUSKSTEEL;
	
	public static void enableWeapons() {
		if(ConfigHandler.ENABLE_DAGGERS) {
			DAGGER_WOOD = new ToolStabSword("dagger_wood", ToolMaterial.WOOD, "dagger").setMaxStackSize(1);
			DAGGER_IRON = new ToolStabSword("dagger_iron", ToolMaterial.IRON, "dagger").setMaxStackSize(1);
			DAGGER_COPPER = new ToolStabSword("dagger_copper", MAT_COPPER, "dagger").setMaxStackSize(1);
			DAGGER_SILVER = new ToolStabSword("dagger_silver", MAT_SILVER, "dagger").setMaxStackSize(1);
			DAGGER_BRONZE = new ToolStabSword("dagger_bronze", MAT_BRONZE, "dagger").setMaxStackSize(1);
			DAGGER_PLATINUM = new ToolStabSword("dagger_platinum", MAT_PLATINUM, "dagger").setMaxStackSize(1);
			DAGGER_STEEL = new ToolStabSword("dagger_steel", MAT_STEEL, "dagger").setMaxStackSize(1);
			DAGGER_OBSIDIAN = new ToolStabSword("dagger_obsidian", MAT_OBSIDIAN, "dagger").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_KABUTOWARIS) {
			KABUTOWARI_WOOD = new ToolStabSword("kabutowari_wood", ToolMaterial.WOOD, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_IRON = new ToolStabSword("kabutowari_iron", ToolMaterial.IRON, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_COPPER = new ToolStabSword("kabutowari_copper", MAT_COPPER, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_SILVER = new ToolStabSword("kabutowari_silver", MAT_SILVER, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_BRONZE = new ToolStabSword("kabutowari_bronze", MAT_BRONZE, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_PLATINUM = new ToolStabSword("kabutowari_platinum", MAT_PLATINUM, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_STEEL = new ToolStabSword("kabutowari_steel", MAT_STEEL, "kabutowari").setMaxStackSize(1);
			KABUTOWARI_OBSIDIAN = new ToolStabSword("kabutowari_obsidian", MAT_OBSIDIAN, "kabutowari").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_RAPIERS) {
			RAPIER_WOOD = new ToolStabSword("rapier_wood", ToolMaterial.WOOD, "rapier").setMaxStackSize(1);
			RAPIER_IRON = new ToolStabSword("rapier_iron", ToolMaterial.IRON, "rapier").setMaxStackSize(1);
			RAPIER_COPPER = new ToolStabSword("rapier_copper", MAT_COPPER, "rapier").setMaxStackSize(1);
			RAPIER_SILVER = new ToolStabSword("rapier_silver", MAT_SILVER, "rapier").setMaxStackSize(1);
			RAPIER_BRONZE = new ToolStabSword("rapier_bronze", MAT_BRONZE, "rapier").setMaxStackSize(1);
			RAPIER_PLATINUM = new ToolStabSword("rapier_platinum", MAT_PLATINUM, "rapier").setMaxStackSize(1);
			RAPIER_STEEL = new ToolStabSword("rapier_steel", MAT_STEEL, "rapier").setMaxStackSize(1);
			RAPIER_OBSIDIAN = new ToolStabSword("rapier_obsidian", MAT_OBSIDIAN, "rapier").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_TALWARS) {
			TALWAR_WOOD = new ToolStabSword("talwar_wood", ToolMaterial.WOOD, "talwar").setMaxStackSize(1);
			TALWAR_IRON = new ToolStabSword("talwar_iron", ToolMaterial.IRON, "talwar").setMaxStackSize(1);
			TALWAR_COPPER = new ToolStabSword("talwar_copper", MAT_COPPER, "talwar").setMaxStackSize(1);
			TALWAR_SILVER = new ToolStabSword("talwar_silver", MAT_SILVER, "talwar").setMaxStackSize(1);
			TALWAR_BRONZE = new ToolStabSword("talwar_bronze", MAT_BRONZE, "talwar").setMaxStackSize(1);
			TALWAR_PLATINUM = new ToolStabSword("talwar_platinum", MAT_PLATINUM, "talwar").setMaxStackSize(1);
			TALWAR_STEEL = new ToolStabSword("talwar_steel", MAT_STEEL, "talwar").setMaxStackSize(1);
			TALWAR_OBSIDIAN = new ToolStabSword("talwar_obsidian", MAT_OBSIDIAN, "talwar").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_CLEAVERS) {
			CLEAVER_WOOD = new ToolStabSword("cleaver_wood", ToolMaterial.WOOD, "cleaver").setMaxStackSize(1);
			CLEAVER_IRON = new ToolStabSword("cleaver_iron", ToolMaterial.IRON, "cleaver").setMaxStackSize(1);
			CLEAVER_COPPER = new ToolStabSword("cleaver_copper", MAT_COPPER, "cleaver").setMaxStackSize(1);
			CLEAVER_SILVER = new ToolStabSword("cleaver_silver", MAT_SILVER, "cleaver").setMaxStackSize(1);
			CLEAVER_BRONZE = new ToolStabSword("cleaver_bronze", MAT_BRONZE, "cleaver").setMaxStackSize(1);
			CLEAVER_PLATINUM = new ToolStabSword("cleaver_platinum", MAT_PLATINUM, "cleaver").setMaxStackSize(1);
			CLEAVER_STEEL = new ToolStabSword("cleaver_steel", MAT_STEEL, "cleaver").setMaxStackSize(1);
			CLEAVER_OBSIDIAN = new ToolStabSword("cleaver_obsidian", MAT_OBSIDIAN, "cleaver").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_MACES) {
			MACE_WOOD = new ToolStabSword("mace_wood", ToolMaterial.WOOD, "mace").setMaxStackSize(1);
			MACE_IRON = new ToolStabSword("mace_iron", ToolMaterial.IRON, "mace").setMaxStackSize(1);
			MACE_COPPER = new ToolStabSword("mace_copper", MAT_COPPER, "mace").setMaxStackSize(1);
			MACE_SILVER = new ToolStabSword("mace_silver", MAT_SILVER, "mace").setMaxStackSize(1);
			MACE_BRONZE = new ToolStabSword("mace_bronze", MAT_BRONZE, "mace").setMaxStackSize(1);
			MACE_PLATINUM = new ToolStabSword("mace_platinum", MAT_PLATINUM, "mace").setMaxStackSize(1);
			MACE_STEEL = new ToolStabSword("mace_steel", MAT_STEEL, "mace").setMaxStackSize(1);
			MACE_OBSIDIAN = new ToolStabSword("mace_obsidian", MAT_OBSIDIAN, "mace").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_STAFFS) {
			STAFF_WOOD = new ToolStabSword("staff_wood", ToolMaterial.WOOD, "staff").setMaxStackSize(1);
			STAFF_IRON = new ToolStabSword("staff_iron", ToolMaterial.IRON, "staff").setMaxStackSize(1);
			STAFF_COPPER = new ToolStabSword("staff_copper", MAT_COPPER, "staff").setMaxStackSize(1);
			STAFF_SILVER = new ToolStabSword("staff_silver", MAT_SILVER, "staff").setMaxStackSize(1);
			STAFF_BRONZE = new ToolStabSword("staff_bronze", MAT_BRONZE, "staff").setMaxStackSize(1);
			STAFF_PLATINUM = new ToolStabSword("staff_platinum", MAT_PLATINUM, "staff").setMaxStackSize(1);
			STAFF_STEEL = new ToolStabSword("staff_steel", MAT_STEEL, "staff").setMaxStackSize(1);
			STAFF_OBSIDIAN = new ToolStabSword("staff_obsidian", MAT_OBSIDIAN, "staff").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_LONGSWORDS) {
			LONGSWORD_WOOD = new ToolSlashSword("longsword_wood", ToolMaterial.WOOD, "longsword").setMaxStackSize(1);
			LONGSWORD_IRON = new ToolSlashSword("longsword_iron", ToolMaterial.IRON, "longsword").setMaxStackSize(1);
			LONGSWORD_COPPER = new ToolSlashSword("longsword_copper", MAT_COPPER, "longsword").setMaxStackSize(1);
			LONGSWORD_SILVER = new ToolSlashSword("longsword_silver", MAT_SILVER, "longsword").setMaxStackSize(1);
			LONGSWORD_BRONZE = new ToolSlashSword("longsword_bronze", MAT_BRONZE, "longsword").setMaxStackSize(1);
			LONGSWORD_PLATINUM = new ToolSlashSword("longsword_platinum", MAT_PLATINUM, "longsword").setMaxStackSize(1);
			LONGSWORD_STEEL = new ToolSlashSword("longsword_steel", MAT_STEEL, "longsword").setMaxStackSize(1);
			LONGSWORD_OBSIDIAN = new ToolSlashSword("longsword_obsidian", MAT_OBSIDIAN, "longsword").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_KODACHIS) {
			KODACHI_WOOD = new ToolSlashSword("kodachi_wood", ToolMaterial.WOOD, "kodachi").setMaxStackSize(1);
			KODACHI_IRON = new ToolSlashSword("kodachi_iron", ToolMaterial.IRON, "kodachi").setMaxStackSize(1);
			KODACHI_COPPER = new ToolSlashSword("kodachi_copper", MAT_COPPER, "kodachi").setMaxStackSize(1);
			KODACHI_SILVER = new ToolSlashSword("kodachi_silver", MAT_SILVER, "kodachi").setMaxStackSize(1);
			KODACHI_BRONZE = new ToolSlashSword("kodachi_bronze", MAT_BRONZE, "kodachi").setMaxStackSize(1);
			KODACHI_PLATINUM = new ToolSlashSword("kodachi_platinum", MAT_PLATINUM, "kodachi").setMaxStackSize(1);
			KODACHI_STEEL = new ToolSlashSword("kodachi_steel", MAT_STEEL, "kodachi").setMaxStackSize(1);
			KODACHI_OBSIDIAN = new ToolSlashSword("kodachi_obsidian", MAT_OBSIDIAN, "kodachi").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_BATTLEAXES) {
			BATTLEAXE_WOOD = new ToolSlashSword("battleaxe_wood", ToolMaterial.WOOD, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_IRON = new ToolSlashSword("battleaxe_iron", ToolMaterial.IRON, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_COPPER = new ToolSlashSword("battleaxe_copper", MAT_COPPER, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_SILVER = new ToolSlashSword("battleaxe_silver", MAT_SILVER, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_BRONZE = new ToolSlashSword("battleaxe_bronze", MAT_BRONZE, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_PLATINUM = new ToolSlashSword("battleaxe_platinum", MAT_PLATINUM, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_STEEL = new ToolSlashSword("battleaxe_steel", MAT_STEEL, "battleaxe").setMaxStackSize(1);
			BATTLEAXE_OBSIDIAN = new ToolSlashSword("battleaxe_obsidian", MAT_OBSIDIAN, "battleaxe").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_ZWEIHANDERS) {
			ZWEIHANDER_WOOD = new ToolSlashSword("zweihander_wood", ToolMaterial.WOOD, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_IRON = new ToolSlashSword("zweihander_iron", ToolMaterial.IRON, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_COPPER = new ToolSlashSword("zweihander_copper", MAT_COPPER, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_SILVER = new ToolSlashSword("zweihander_silver", MAT_SILVER, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_BRONZE = new ToolSlashSword("zweihander_bronze", MAT_BRONZE, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_PLATINUM = new ToolSlashSword("zweihander_platinum", MAT_PLATINUM, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_STEEL = new ToolSlashSword("zweihander_steel", MAT_STEEL, "zweihander").setMaxStackSize(1);
			ZWEIHANDER_OBSIDIAN = new ToolSlashSword("zweihander_obsidian", MAT_OBSIDIAN, "zweihander").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_NODACHIS) {
			NODACHI_WOOD = new ToolSlashSword("nodachi_wood", ToolMaterial.WOOD, "nodachi").setMaxStackSize(1);
			NODACHI_IRON = new ToolSlashSword("nodachi_iron", ToolMaterial.IRON, "nodachi").setMaxStackSize(1);
			NODACHI_COPPER = new ToolSlashSword("nodachi_copper", MAT_COPPER, "nodachi").setMaxStackSize(1);
			NODACHI_SILVER = new ToolSlashSword("nodachi_silver", MAT_SILVER, "nodachi").setMaxStackSize(1);
			NODACHI_BRONZE = new ToolSlashSword("nodachi_bronze", MAT_BRONZE, "nodachi").setMaxStackSize(1);
			NODACHI_PLATINUM = new ToolSlashSword("nodachi_platinum", MAT_PLATINUM, "nodachi").setMaxStackSize(1);
			NODACHI_STEEL = new ToolSlashSword("nodachi_steel", MAT_STEEL, "nodachi").setMaxStackSize(1);
			NODACHI_OBSIDIAN = new ToolSlashSword("nodachi_obsidian", MAT_OBSIDIAN, "nodachi").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_SABRES) {
			SABRE_WOOD = new ToolSlashSword("sabre_wood", ToolMaterial.WOOD, "sabre").setMaxStackSize(1);
			SABRE_IRON = new ToolSlashSword("sabre_iron", ToolMaterial.IRON, "sabre").setMaxStackSize(1);
			SABRE_COPPER = new ToolSlashSword("sabre_copper", MAT_COPPER, "sabre").setMaxStackSize(1);
			SABRE_SILVER = new ToolSlashSword("sabre_silver", MAT_SILVER, "sabre").setMaxStackSize(1);
			SABRE_BRONZE = new ToolSlashSword("sabre_bronze", MAT_BRONZE, "sabre").setMaxStackSize(1);
			SABRE_PLATINUM = new ToolSlashSword("sabre_platinum", MAT_PLATINUM, "sabre").setMaxStackSize(1);
			SABRE_STEEL = new ToolSlashSword("sabre_steel", MAT_STEEL, "sabre").setMaxStackSize(1);
			SABRE_OBSIDIAN = new ToolSlashSword("sabre_obsidian", MAT_OBSIDIAN, "sabre").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_MAKHAIRAS) {
			MAKHAIRA_WOOD = new ToolSlashSword("makhaira_wood", ToolMaterial.WOOD, "makhaira").setMaxStackSize(1);
			MAKHAIRA_IRON = new ToolSlashSword("makhaira_iron", ToolMaterial.IRON, "makhaira").setMaxStackSize(1);
			MAKHAIRA_COPPER = new ToolSlashSword("makhaira_copper", MAT_COPPER, "makhaira").setMaxStackSize(1);
			MAKHAIRA_SILVER = new ToolSlashSword("makhaira_silver", MAT_SILVER, "makhaira").setMaxStackSize(1);
			MAKHAIRA_BRONZE = new ToolSlashSword("makhaira_bronze", MAT_BRONZE, "makhaira").setMaxStackSize(1);
			MAKHAIRA_PLATINUM = new ToolSlashSword("makhaira_platinum", MAT_PLATINUM, "makhaira").setMaxStackSize(1);
			MAKHAIRA_STEEL = new ToolSlashSword("makhaira_steel", MAT_STEEL, "makhaira").setMaxStackSize(1);
			MAKHAIRA_OBSIDIAN = new ToolSlashSword("makhaira_obsidian", MAT_OBSIDIAN, "makhaira").setMaxStackSize(1);
		}
		if(ConfigHandler.ENABLE_SPEARS) {
			SPEAR_WOOD = new ToolSpear("spear_wood", ToolMaterial.WOOD).setMaxStackSize(1);
			SPEAR_IRON = new ToolSpear("spear_iron", ToolMaterial.IRON).setMaxStackSize(1);
			SPEAR_COPPER = new ToolSpear("spear_copper", MAT_COPPER).setMaxStackSize(1);
			SPEAR_SILVER = new ToolSpear("spear_silver", MAT_SILVER).setMaxStackSize(1);
			SPEAR_BRONZE = new ToolSpear("spear_bronze", MAT_BRONZE).setMaxStackSize(1);
			SPEAR_PLATINUM = new ToolSpear("spear_platinum", MAT_PLATINUM).setMaxStackSize(1);
			SPEAR_STEEL = new ToolSpear("spear_steel", MAT_STEEL).setMaxStackSize(1);
			SPEAR_OBSIDIAN = new ToolSpear("spear_obsidian", MAT_OBSIDIAN).setMaxStackSize(1);
		}
		
		if(ConfigHandler.USE_IMAGINARY_RESOURCES) {
			if(ConfigHandler.ENABLE_DAGGERS) {
				DAGGER_KOBOLD = new ToolStabSword("dagger_kobold", MAT_KOBOLD, "dagger").setMaxStackSize(1);
				DAGGER_SHADOW_PLATINUM = new ToolStabSword("dagger_shadow_platinum", MAT_SHADOW_PLATINUM, "dagger").setMaxStackSize(1);
				DAGGER_FROST_STEEL = new ToolStabSword("dagger_frost_steel", MAT_FROST_STEEL, "dagger").setMaxStackSize(1);
				DAGGER_CRYSTALLITE = new ToolStabSword("dagger_crystallite", MAT_CRYSTALLITE, "dagger").setMaxStackSize(1);
				DAGGER_DUSKSTEEL = new ToolStabSword("dagger_dusksteel", MAT_DUSKSTEEL, "dagger").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_KABUTOWARIS) {
				KABUTOWARI_KOBOLD = new ToolStabSword("kabutowari_kobold", MAT_KOBOLD, "kabutowari").setMaxStackSize(1);
				KABUTOWARI_SHADOW_PLATINUM = new ToolStabSword("kabutowari_shadow_platinum", MAT_SHADOW_PLATINUM, "kabutowari").setMaxStackSize(1);
				KABUTOWARI_FROST_STEEL = new ToolStabSword("kabutowari_frost_steel", MAT_FROST_STEEL, "kabutowari").setMaxStackSize(1);
				KABUTOWARI_CRYSTALLITE = new ToolStabSword("kabutowari_crystallite", MAT_CRYSTALLITE, "kabutowari").setMaxStackSize(1);
				KABUTOWARI_DUSKSTEEL = new ToolStabSword("kabutowari_dusksteel", MAT_DUSKSTEEL, "kabutowari").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_RAPIERS) {
				RAPIER_KOBOLD = new ToolStabSword("rapier_kobold", MAT_KOBOLD, "rapier").setMaxStackSize(1);
				RAPIER_SHADOW_PLATINUM = new ToolStabSword("rapier_shadow_platinum", MAT_SHADOW_PLATINUM, "rapier").setMaxStackSize(1);
				RAPIER_FROST_STEEL = new ToolStabSword("rapier_frost_steel", MAT_FROST_STEEL, "rapier").setMaxStackSize(1);
				RAPIER_CRYSTALLITE = new ToolStabSword("rapier_crystallite", MAT_CRYSTALLITE, "rapier").setMaxStackSize(1);
				RAPIER_DUSKSTEEL = new ToolStabSword("rapier_dusksteel", MAT_DUSKSTEEL, "rapier").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_TALWARS) {
				TALWAR_KOBOLD = new ToolStabSword("talwar_kobold", MAT_KOBOLD, "talwar").setMaxStackSize(1);
				TALWAR_SHADOW_PLATINUM = new ToolStabSword("talwar_shadow_platinum", MAT_SHADOW_PLATINUM, "talwar").setMaxStackSize(1);
				TALWAR_FROST_STEEL = new ToolStabSword("talwar_frost_steel", MAT_FROST_STEEL, "talwar").setMaxStackSize(1);
				TALWAR_CRYSTALLITE = new ToolStabSword("talwar_crystallite", MAT_CRYSTALLITE, "talwar").setMaxStackSize(1);
				TALWAR_DUSKSTEEL = new ToolStabSword("talwar_dusksteel", MAT_DUSKSTEEL, "talwar").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_CLEAVERS) {
				CLEAVER_KOBOLD = new ToolStabSword("cleaver_kobold", MAT_KOBOLD, "cleaver").setMaxStackSize(1);
				CLEAVER_SHADOW_PLATINUM = new ToolStabSword("cleaver_shadow_platinum", MAT_SHADOW_PLATINUM, "cleaver").setMaxStackSize(1);
				CLEAVER_FROST_STEEL = new ToolStabSword("cleaver_frost_steel", MAT_FROST_STEEL, "cleaver").setMaxStackSize(1);
				CLEAVER_CRYSTALLITE = new ToolStabSword("cleaver_crystallite", MAT_CRYSTALLITE, "cleaver").setMaxStackSize(1);
				CLEAVER_DUSKSTEEL = new ToolStabSword("cleaver_dusksteel", MAT_DUSKSTEEL, "cleaver").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_MACES) {
				MACE_KOBOLD = new ToolStabSword("mace_kobold", MAT_KOBOLD, "mace").setMaxStackSize(1);
				MACE_SHADOW_PLATINUM = new ToolStabSword("mace_shadow_platinum", MAT_SHADOW_PLATINUM, "mace").setMaxStackSize(1);
				MACE_FROST_STEEL = new ToolStabSword("mace_frost_steel", MAT_FROST_STEEL, "mace").setMaxStackSize(1);
				MACE_CRYSTALLITE = new ToolStabSword("mace_crystallite", MAT_CRYSTALLITE, "mace").setMaxStackSize(1);
				MACE_DUSKSTEEL = new ToolStabSword("mace_dusksteel", MAT_DUSKSTEEL, "mace").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_STAFFS) {
				STAFF_KOBOLD = new ToolStabSword("staff_kobold", MAT_KOBOLD, "staff").setMaxStackSize(1);
				STAFF_SHADOW_PLATINUM = new ToolStabSword("staff_shadow_platinum", MAT_SHADOW_PLATINUM, "staff").setMaxStackSize(1);
				STAFF_FROST_STEEL = new ToolStabSword("staff_frost_steel", MAT_FROST_STEEL, "staff").setMaxStackSize(1);
				STAFF_CRYSTALLITE = new ToolStabSword("staff_crystallite", MAT_CRYSTALLITE, "staff").setMaxStackSize(1);
				STAFF_DUSKSTEEL = new ToolStabSword("staff_dusksteel", MAT_DUSKSTEEL, "staff").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_LONGSWORDS) {
				LONGSWORD_KOBOLD = new ToolSlashSword("longsword_kobold", MAT_KOBOLD, "longsword").setMaxStackSize(1);
				LONGSWORD_SHADOW_PLATINUM = new ToolSlashSword("longsword_shadow_platinum", MAT_SHADOW_PLATINUM, "longsword").setMaxStackSize(1);
				LONGSWORD_FROST_STEEL = new ToolSlashSword("longsword_frost_steel", MAT_FROST_STEEL, "longsword").setMaxStackSize(1);
				LONGSWORD_CRYSTALLITE = new ToolSlashSword("longsword_crystallite", MAT_CRYSTALLITE, "longsword").setMaxStackSize(1);
				LONGSWORD_DUSKSTEEL = new ToolSlashSword("longsword_dusksteel", MAT_DUSKSTEEL, "longsword").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_KODACHIS) {
				KODACHI_KOBOLD = new ToolSlashSword("kodachi_kobold", MAT_KOBOLD, "kodachi").setMaxStackSize(1);
				KODACHI_SHADOW_PLATINUM = new ToolSlashSword("kodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "kodachi").setMaxStackSize(1);
				KODACHI_FROST_STEEL = new ToolSlashSword("kodachi_frost_steel", MAT_FROST_STEEL, "kodachi").setMaxStackSize(1);
				KODACHI_CRYSTALLITE = new ToolSlashSword("kodachi_crystallite", MAT_CRYSTALLITE, "kodachi").setMaxStackSize(1);
				KODACHI_DUSKSTEEL = new ToolSlashSword("kodachi_dusksteel", MAT_DUSKSTEEL, "kodachi").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_BATTLEAXES) {
				BATTLEAXE_KOBOLD = new ToolSlashSword("battleaxe_kobold", MAT_KOBOLD, "battleaxe").setMaxStackSize(1);
				BATTLEAXE_SHADOW_PLATINUM = new ToolSlashSword("battleaxe_shadow_platinum", MAT_SHADOW_PLATINUM, "battleaxe").setMaxStackSize(1);
				BATTLEAXE_FROST_STEEL = new ToolSlashSword("battleaxe_frost_steel", MAT_FROST_STEEL, "battleaxe").setMaxStackSize(1);
				BATTLEAXE_CRYSTALLITE = new ToolSlashSword("battleaxe_crystallite", MAT_CRYSTALLITE, "battleaxe").setMaxStackSize(1);
				BATTLEAXE_DUSKSTEEL = new ToolSlashSword("battleaxe_dusksteel", MAT_DUSKSTEEL, "battleaxe").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_ZWEIHANDERS) {
				ZWEIHANDER_KOBOLD = new ToolSlashSword("zweihander_kobold", MAT_KOBOLD, "zweihander").setMaxStackSize(1);
				ZWEIHANDER_SHADOW_PLATINUM = new ToolSlashSword("zweihander_shadow_platinum", MAT_SHADOW_PLATINUM, "zweihander").setMaxStackSize(1);
				ZWEIHANDER_FROST_STEEL = new ToolSlashSword("zweihander_frost_steel", MAT_FROST_STEEL, "zweihander").setMaxStackSize(1);
				ZWEIHANDER_CRYSTALLITE = new ToolSlashSword("zweihander_crystallite", MAT_CRYSTALLITE, "zweihander").setMaxStackSize(1);
				ZWEIHANDER_DUSKSTEEL = new ToolSlashSword("zweihander_dusksteel", MAT_DUSKSTEEL, "zweihander").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_NODACHIS) {
				NODACHI_KOBOLD = new ToolSlashSword("nodachi_kobold", MAT_KOBOLD, "nodachi").setMaxStackSize(1);
				NODACHI_SHADOW_PLATINUM = new ToolSlashSword("nodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "nodachi").setMaxStackSize(1);
				NODACHI_FROST_STEEL = new ToolSlashSword("nodachi_frost_steel", MAT_FROST_STEEL, "nodachi").setMaxStackSize(1);
				NODACHI_CRYSTALLITE = new ToolSlashSword("nodachi_crystallite", MAT_CRYSTALLITE, "nodachi").setMaxStackSize(1);
				NODACHI_DUSKSTEEL = new ToolSlashSword("nodachi_dusksteel", MAT_DUSKSTEEL, "nodachi").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_SABRES) {
				SABRE_KOBOLD = new ToolSlashSword("sabre_kobold", MAT_KOBOLD, "sabre").setMaxStackSize(1);
				SABRE_SHADOW_PLATINUM = new ToolSlashSword("sabre_shadow_platinum", MAT_SHADOW_PLATINUM, "sabre").setMaxStackSize(1);
				SABRE_FROST_STEEL = new ToolSlashSword("sabre_frost_steel", MAT_FROST_STEEL, "sabre").setMaxStackSize(1);
				SABRE_CRYSTALLITE = new ToolSlashSword("sabre_crystallite", MAT_CRYSTALLITE, "sabre").setMaxStackSize(1);
				SABRE_DUSKSTEEL = new ToolSlashSword("sabre_dusksteel", MAT_DUSKSTEEL, "sabre").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_MAKHAIRAS) {
				MAKHAIRA_KOBOLD = new ToolSlashSword("makhaira_kobold", MAT_KOBOLD, "makhaira").setMaxStackSize(1);
				MAKHAIRA_SHADOW_PLATINUM = new ToolSlashSword("makhaira_shadow_platinum", MAT_SHADOW_PLATINUM, "makhaira").setMaxStackSize(1);
				MAKHAIRA_FROST_STEEL = new ToolSlashSword("makhaira_frost_steel", MAT_FROST_STEEL, "makhaira").setMaxStackSize(1);
				MAKHAIRA_CRYSTALLITE = new ToolSlashSword("makhaira_crystallite", MAT_CRYSTALLITE, "makhaira").setMaxStackSize(1);
				MAKHAIRA_DUSKSTEEL = new ToolSlashSword("makhaira_dusksteel", MAT_DUSKSTEEL, "makhaira").setMaxStackSize(1);
			}
			if(ConfigHandler.ENABLE_SPEARS) {
				SPEAR_KOBOLD = new ToolSpear("spear_kobold", MAT_KOBOLD).setMaxStackSize(1);
				SPEAR_SHADOW_PLATINUM = new ToolSpear("spear_shadow_platinum", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
				SPEAR_FROST_STEEL = new ToolSpear("spear_frost_steel", MAT_FROST_STEEL).setMaxStackSize(1);
				SPEAR_CRYSTALLITE = new ToolSpear("spear_crystallite", MAT_CRYSTALLITE).setMaxStackSize(1);
				SPEAR_DUSKSTEEL = new ToolSpear("spear_dusksteel", MAT_DUSKSTEEL).setMaxStackSize(1);
			}
		}
	}
	
	public static void enableArmors() {
		HELMET_IRON = new ArmorBonusesBase("minecraft:iron_helmet", ArmorMaterial.IRON, 1, EntityEquipmentSlot.HEAD, 1.5D, -0.0016741125D, 2).setMaxStackSize(1).setCreativeTab(CreativeTabs.COMBAT);
		CHESTPLATE_STEEL = new ArmorBonusesBase("minecraft:iron_chestplate", ArmorMaterial.IRON, 1, EntityEquipmentSlot.CHEST, 2.0D, 1.875D, -0.0083705625D, 2).setMaxStackSize(1).setCreativeTab(CreativeTabs.COMBAT);
		LEGGINGS_STEEL = new ArmorBonusesBase("minecraft:iron_leggings", ArmorMaterial.IRON, 2, EntityEquipmentSlot.LEGS, 2.0D, -0.0083705625D, 2).setMaxStackSize(1).setCreativeTab(CreativeTabs.COMBAT);
		BOOTS_STEEL = new ArmorBonusesBase("minecraft:iron_boots", ArmorMaterial.IRON, 1, EntityEquipmentSlot.FEET, 1.5D, -0.0016741125D, 2).setMaxStackSize(1).setCreativeTab(CreativeTabs.COMBAT);
		
		HELMET_COPPER = new ArmorBonusesBase("helmet_copper", AMAT_COPPER, 1, EntityEquipmentSlot.HEAD, 2.5D, -0.00188214888D, 3).setMaxStackSize(1);
		CHESTPLATE_COPPER = new ArmorBonusesBase("chestplate_copper", AMAT_COPPER, 1, EntityEquipmentSlot.CHEST, 3.125D, 1.506D, -0.00941253012D, 3).setMaxStackSize(1);
		LEGGINGS_COPPER = new ArmorBonusesBase("leggings_copper", AMAT_COPPER, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00941253012D, 3).setMaxStackSize(1);
		BOOTS_COPPER = new ArmorBonusesBase("boots_copper", AMAT_COPPER, 1, EntityEquipmentSlot.FEET, 2.5D, -0.00188214888D, 3).setMaxStackSize(1);
		
		HELMET_SILVER = new ArmorBonusesBase("helmet_silver", AMAT_SILVER, 1, EntityEquipmentSlot.HEAD, 3.5D, -0.0022098285D, 4).setMaxStackSize(1);
		CHESTPLATE_SILVER = new ArmorBonusesBase("chestplate_silver", AMAT_SILVER, 1, EntityEquipmentSlot.CHEST, 4.125D, 2.063D, -0.0110491425D, 4).setMaxStackSize(1);
		LEGGINGS_SILVER = new ArmorBonusesBase("leggings_silver", AMAT_SILVER, 2, EntityEquipmentSlot.LEGS, 4.125D, -0.0110491425D, 4).setMaxStackSize(1);
		BOOTS_SILVER = new ArmorBonusesBase("boots_silver", AMAT_SILVER, 1, EntityEquipmentSlot.FEET, 3.5D, -0.0022098285D, 4).setMaxStackSize(1);
		
		HELMET_BRONZE = new ArmorBonusesBase("helmet_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.HEAD, 3.5D, -0.00403304862D, 4).setMaxStackSize(1);
		CHESTPLATE_BRONZE = new ArmorBonusesBase("chestplate_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.CHEST, 4.125D, 3.764D, -0.020168189538D, 4).setMaxStackSize(1);
		LEGGINGS_BRONZE = new ArmorBonusesBase("leggings_bronze", AMAT_BRONZE, 2, EntityEquipmentSlot.LEGS, 4.125D, -0.020168189538D, 4).setMaxStackSize(1);
		BOOTS_BRONZE = new ArmorBonusesBase("boots_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.FEET, 3.5D, -0.00403304862D, 4).setMaxStackSize(1);
		
		HELMET_PLATINUM = new ArmorBonusesBase("helmet_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.HEAD, 4.5D, -0.00452412162D, 5).setMaxStackSize(1);
		CHESTPLATE_PLATINUM = new ArmorBonusesBase("chestplate_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.CHEST, 5.125D, 5.067D, -0.02261882238D, 5).setMaxStackSize(1);
		LEGGINGS_PLATINUM = new ArmorBonusesBase("leggings_platinum", AMAT_PLATINUM, 2, EntityEquipmentSlot.LEGS, 5.125D, -0.02261882238D, 5).setMaxStackSize(1);
		BOOTS_PLATINUM = new ArmorBonusesBase("boots_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.FEET, 4.5D, -0.00452412162D, 5).setMaxStackSize(1);
		
		HELMET_STEEL = new ArmorBonusesBase("helmet_steel", AMAT_STEEL, 1, EntityEquipmentSlot.HEAD, 4.5D, -0.0016741125D, 5).setMaxStackSize(1);
		CHESTPLATE_STEEL = new ArmorBonusesBase("chestplate_steel", AMAT_STEEL, 1, EntityEquipmentSlot.CHEST, 5.125D, 1.875D, -0.0083705625D, 5).setMaxStackSize(1);
		LEGGINGS_STEEL = new ArmorBonusesBase("leggings_steel", AMAT_STEEL, 2, EntityEquipmentSlot.LEGS, 5.125D, -0.0083705625D, 5).setMaxStackSize(1);
		BOOTS_STEEL = new ArmorBonusesBase("boots_steel", AMAT_STEEL, 1, EntityEquipmentSlot.FEET, 4.5D, -0.0016741125D, 5).setMaxStackSize(1);
		
		HELMET_OBSIDIAN = new ArmorBonusesBase("helmet_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.HEAD, 6.25D, -0.00117589662D, 7).setMaxStackSize(1);
		CHESTPLATE_OBSIDIAN = new ArmorBonusesBase("chestplate_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.CHEST, 7.0D, 2.194D, -0.00587769738D, 7).setMaxStackSize(1);
		LEGGINGS_OBSIDIAN = new ArmorBonusesBase("leggings_obsidian", AMAT_OBSIDIAN, 2, EntityEquipmentSlot.LEGS, 7.0D, -0.00587769738D, 7).setMaxStackSize(1);
		BOOTS_OBSIDIAN = new ArmorBonusesBase("boots_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.FEET, 6.25D, -0.00117589662D, 7).setMaxStackSize(1);
		
		if(ConfigHandler.USE_IMAGINARY_RESOURCES) {
			HELMET_KOBOLD = new ArmorBonusesBase("helmet_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.HEAD, 2.5D, -0.00160000512D, 3).setMaxStackSize(1);
			CHESTPLATE_KOBOLD = new ArmorBonusesBase("chestplate_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.CHEST, 3.125D, 1.228D, -0.00767681028D, 3).setMaxStackSize(1);
			LEGGINGS_KOBOLD = new ArmorBonusesBase("leggings_kobold", AMAT_KOBOLD, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00767681028D, 3).setMaxStackSize(1);
			BOOTS_KOBOLD = new ArmorBonusesBase("boots_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.FEET, 2.5D, -0.00160000512D, 3).setMaxStackSize(1);
			
			HELMET_SHADOW_PLATINUM = new ArmorBonusesBase("helmet_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.HEAD, 5.0D, -0.00360090438D, 6).setMaxStackSize(1);
			CHESTPLATE_SHADOW_PLATINUM = new ArmorBonusesBase("chestplate_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.CHEST, 5.75D, 5.042D, -0.01800630762D, 6).setMaxStackSize(1);
			LEGGINGS_SHADOW_PLATINUM = new ArmorBonusesBase("leggings_shadow_platinum", AMAT_SHADOW_PLATINUM, 2, EntityEquipmentSlot.LEGS, 5.75D, -0.01800630762D, 6).setMaxStackSize(1);
			BOOTS_SHADOW_PLATINUM = new ArmorBonusesBase("boots_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.FEET, 5.0D, -0.00360090438D, 6).setMaxStackSize(1);
			
			HELMET_FROST_STEEL = new ArmorBonusesBase("helmet_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.HEAD, 5.0D, -0.00200179212D, 6).setMaxStackSize(1);
			CHESTPLATE_FROST_STEEL = new ArmorBonusesBase("chestplate_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.CHEST, 5.75D, 2.802D, -0.01000717488D, 6).setMaxStackSize(1);
			LEGGINGS_FROST_STEEL = new ArmorBonusesBase("leggings_frost_steel", AMAT_FROST_STEEL, 2, EntityEquipmentSlot.LEGS, 5.75D, -0.01000717488D, 6).setMaxStackSize(1);
			BOOTS_FROST_STEEL = new ArmorBonusesBase("boots_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.FEET, 5.0D, -00.00200179212D, 6).setMaxStackSize(1);
			
			HELMET_CRYSTALLITE = new ArmorBonusesBase("helmet_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.HEAD, 6.25D, -0.00240357912D, 7).setMaxStackSize(1);
			CHESTPLATE_CRYSTALLITE = new ArmorBonusesBase("chestplate_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.CHEST, 7.0D, 4.486D, -0.01201610988D, 7).setMaxStackSize(1);
			LEGGINGS_CRYSTALLITE = new ArmorBonusesBase("leggings_crystallite", AMAT_CRYSTALLITE, 2, EntityEquipmentSlot.LEGS, 7.0D, -0.01201610988D, 7).setMaxStackSize(1);
			BOOTS_CRYSTALLITE = new ArmorBonusesBase("boots_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.FEET, 6.25D, -0.00240357912D, 7).setMaxStackSize(1);
			
			HELMET_DUSKSTEEL = new ArmorBonusesBase("helmet_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.HEAD, 7.25D, -0.00180089862D, 8).setMaxStackSize(1);
			CHESTPLATE_DUSKSTEEL = new ArmorBonusesBase("chestplate_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.CHEST, 7.75D, 5.042D, -0.00900270738D, 8).setMaxStackSize(1);
			LEGGINGS_DUSKSTEEL = new ArmorBonusesBase("leggings_dusksteel", AMAT_DUSKSTEEL, 2, EntityEquipmentSlot.LEGS, 7.75D, -0.00900270738D, 8).setMaxStackSize(1);
			BOOTS_DUSKSTEEL = new ArmorBonusesBase("boots_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.FEET, 7.25D, -0.00180089862D, 8).setMaxStackSize(1);
		}
	}
	
	public static void createImaginaries() {
		CRYSTAL = new ItemBase("crystal");
		SHADOW = new ItemBase("shadow");
		SHADOW_BLOB = new ItemBase("shadow_blob");
		INGOT_KOBOLD = new ItemBase("ingot_kobold");
		INGOT_SHADOW_PLATINUM = new ItemBase("ingot_shadow_platinum");
		INGOT_FROST_STEEL = new ItemBase("ingot_frost_steel");
		INGOT_CRYSTALLITE = new ItemBase("ingot_crystallite");
		INGOT_DUSKSTEEL = new ItemBase("ingot_dusksteel");
		
		KOBOLD_SHARPENING_STONE = new ItemSharpeningStone("kobold_sharpening_stone", MAT_KOBOLD).setMaxStackSize(16);
		SHADOW_PLATINUM_SHARPENING_STONE = new ItemSharpeningStone("shadow_platinum_sharpening_stone", MAT_SHADOW_PLATINUM).setMaxStackSize(16);
		FROST_STEEL_SHARPENING_STONE = new ItemSharpeningStone("frost_steel_sharpening_stone", MAT_FROST_STEEL).setMaxStackSize(16);
		CRYSTALLITE_SHARPENING_STONE = new ItemSharpeningStone("crystallite_sharpening_stone", MAT_CRYSTALLITE).setMaxStackSize(16);
		DUSKSTEEL_SHARPENING_STONE = new ItemSharpeningStone("dusksteel_sharpening_stone", MAT_DUSKSTEEL).setMaxStackSize(16);
		KOBOLD_FORGE_HAMMER = new ToolForgeHammer("kobold_forge_hammer", MAT_KOBOLD).setMaxStackSize(1);
		SHADOW_PLATINUM_FORGE_HAMMER = new ToolForgeHammer("shadow_platinum_forge_hammer", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
		FROST_STEEL_FORGE_HAMMER = new ToolForgeHammer("frost_steel_forge_hammer", MAT_FROST_STEEL).setMaxStackSize(1);
		CRYSTALLITE_FORGE_HAMMER = new ToolForgeHammer("crystallite_forge_hammer", MAT_CRYSTALLITE).setMaxStackSize(1);
		DUSKSTEEL_FORGE_HAMMER = new ToolForgeHammer("dusksteel_forge_hammer", MAT_DUSKSTEEL).setMaxStackSize(1);
	}
}
