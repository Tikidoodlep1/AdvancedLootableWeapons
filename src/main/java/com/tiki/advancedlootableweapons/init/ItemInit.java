package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.items.ItemBase;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;
import com.tiki.advancedlootableweapons.tools.ToolAxe;
import com.tiki.advancedlootableweapons.tools.ToolHoe;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;
import com.tiki.advancedlootableweapons.tools.ToolPickaxe;
import com.tiki.advancedlootableweapons.tools.ToolShovel;
import com.tiki.advancedlootableweapons.tools.ToolSword;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {

	public static final List<Item> items = new ArrayList<Item>();	
	
	//Items
	public static final Item INGOT_TIN = new ItemBase("ingot_tin");
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel");
	public static final Item INGOT_KOBOLD = new ItemBase("ingot_kobold");
	public static final Item INGOT_CRYSTALLITE = new ItemBase("ingot_crystallite");
	public static final Item INGOT_PLATINUM = new ItemBase("ingot_platinum");
	public static final Item INGOT_BRONZE = new ItemBase("ingot_bronze");
	public static final Item INGOT_FROST_STEEL = new ItemBase("ingot_frost_steel");
	public static final Item INGOT_OBSIDIAN = new ItemBase("ingot_obsidian");
	public static final Item INGOT_DUSKSTEEL = new ItemBase("ingot_dusksteel");
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_SILVER = new ItemBase("ingot_silver");
	public static final Item INGOT_SHADOW_PLATINUM = new ItemBase("ingot_shadow_platinum");
	public static final Item CRYSTAL = new ItemBase("crystal");
	public static final Item SHADOW = new ItemBase("shadow");
	public static final Item SHADOW_BLOB = new ItemBase("shadow_blob");
	
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
     * (String name, String textureName, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn)
	 */
	
	//Mats Declarations
	              public static final ToolMaterial MAT_KOBOLD = EnumHelper.addToolMaterial("mat_kobold", 1, 203, 6.0F, 3.5F, 22).setRepairItem(new ItemStack(INGOT_KOBOLD));
	              public static final ToolMaterial MAT_COPPER = EnumHelper.addToolMaterial("mat_copper", 2, 256, 6.5F, 4.0F, 10).setRepairItem(new ItemStack(INGOT_COPPER));
	              public static final ToolMaterial MAT_SILVER = EnumHelper.addToolMaterial("mat_silver", 2, 277, 7.0F, 4.5F, 24).setRepairItem(new ItemStack(INGOT_SILVER));
	              public static final ToolMaterial MAT_BRONZE = EnumHelper.addToolMaterial("mat_bronze", 2, 330, 6.5F, 4.75F, 12).setRepairItem(new ItemStack(INGOT_BRONZE));
	          public static final ToolMaterial MAT_PLATINUM = EnumHelper.addToolMaterial("mat_platinum", 2, 212, 10.0F, 5.25F, 26).setRepairItem(new ItemStack(INGOT_PLATINUM));
	                public static final ToolMaterial MAT_STEEL = EnumHelper.addToolMaterial("mat_steel", 2, 416, 7.0F, 6.0F, 18).setRepairItem(new ItemStack(INGOT_STEEL));
public static final ToolMaterial MAT_SHADOW_PLATINUM = EnumHelper.addToolMaterial("mar_shadow_platinum", 3, 461, 8.25F, 6.5F, 21).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	    public static final ToolMaterial MAT_FROST_STEEL = EnumHelper.addToolMaterial("mat_frost_steel", 3, 507, 7.0F, 6.5F, 30).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
              public static final ToolMaterial MAT_OBSIDIAN = EnumHelper.addToolMaterial("mat_obsidian", 3, 598, 7.5F, 6.75F, 18).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	    public static final ToolMaterial MAT_CRYSTALLITE = EnumHelper.addToolMaterial("mat_crystallite", 3, 627, 7.5F, 7.5F, 20).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	      public static final ToolMaterial MAT_DUSKSTEEL = EnumHelper.addToolMaterial("mat_dusksteel", 3, 812, 8.5F, 8.25F, 14).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	public static final ArmorMaterial AMAT_KOBOLD = EnumHelper.addArmorMaterial("amat_kobold", ModInfo.ID + ":kobold", 20, new int[]{1,3,4,2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.25F).setRepairItem(new ItemStack(INGOT_KOBOLD));
	public static final ArmorMaterial AMAT_COPPER = EnumHelper.addArmorMaterial("amat_copper", ModInfo.ID + ":copper", 25, new int[]{2,3,4,2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.56F).setRepairItem(new ItemStack(INGOT_COPPER));
	public static final ArmorMaterial AMAT_SILVER = EnumHelper.addArmorMaterial("amat_silver", ModInfo.ID + "silver", 27, new int[]{3,5,7,3}, 24, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.69F).setRepairItem(new ItemStack(INGOT_SILVER));
	public static final ArmorMaterial AMAT_BRONZE = EnumHelper.addArmorMaterial("amat_bronze", ModInfo.ID + ":bronze", 32, new int[]{5,9,12,5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F).setRepairItem(new ItemStack(INGOT_BRONZE));
	public static final ArmorMaterial AMAT_PLATINUM = EnumHelper.addArmorMaterial("amat_platinum", ModInfo.ID + ":platinum", 20, new int[]{7,13,17,8}, 26, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.25F).setRepairItem(new ItemStack(INGOT_PLATINUM));
	public static final ArmorMaterial AMAT_STEEL = EnumHelper.addArmorMaterial("amat_steel", ModInfo.ID + ":steel", 40, new int[]{3,5,6,3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5F).setRepairItem(new ItemStack(INGOT_STEEL));
	public static final ArmorMaterial AMAT_SHADOW_PLATINUM = EnumHelper.addArmorMaterial("amat_shadow_platinum", ModInfo.ID + ":shadow_platinum", 44, new int[]{7,13,16,7}, 21, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.75F).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	public static final ArmorMaterial AMAT_FROST_STEEL = EnumHelper.addArmorMaterial("amat_frost_steel", ModInfo.ID + ":frost_steel", 49, new int[]{4,7,9,4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.06F).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
	public static final ArmorMaterial AMAT_OBSIDIAN = EnumHelper.addArmorMaterial("amat_obsidian", ModInfo.ID + ":obsidian", 57, new int[]{3,5,6,3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.56F).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	public static final ArmorMaterial AMAT_CRYSTALLITE = EnumHelper.addArmorMaterial("amat_crystallite", ModInfo.ID + ":crystallite", 60, new int[]{5,10,13,6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.75F).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	public static final ArmorMaterial AMAT_DUSKSTEEL = EnumHelper.addArmorMaterial("amat_dusksteel", ModInfo.ID + ":dusksteel", 78, new int[]{5,8,11,5}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.88F).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	
	//Tools
	public static final Item AXE_STEEL = new ToolAxe("axe_steel", MAT_STEEL);
	public static final Item HOE_STEEL = new ToolHoe("hoe_steel", MAT_STEEL);
	public static final Item PICKAXE_STEEL = new ToolPickaxe("pickaxe_steel", MAT_STEEL);
	public static final Item SHOVEL_STEEL = new ToolShovel("shovel_steel", MAT_STEEL);
	public static final Item SWORD_STEEL = new ToolSword("sword_steel", MAT_STEEL);
	
	
	//Daggers
	public static final Item DAGGER_WOOD = new ToolStabSword("dagger_wood", ToolMaterial.WOOD, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_IRON = new ToolStabSword("dagger_iron", ToolMaterial.IRON, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_KOBOLD = new ToolStabSword("dagger_kobold", MAT_KOBOLD, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_COPPER = new ToolStabSword("dagger_copper", MAT_COPPER, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_SILVER = new ToolStabSword("dagger_silver", MAT_SILVER, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_BRONZE = new ToolStabSword("dagger_bronze", MAT_BRONZE, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_PLATINUM = new ToolStabSword("dagger_platinum", MAT_PLATINUM, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_STEEL = new ToolStabSword("dagger_steel", MAT_STEEL, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_SHADOW_PLATINUM = new ToolStabSword("dagger_shadow_platinum", MAT_SHADOW_PLATINUM, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_FROST_STEEL = new ToolStabSword("dagger_frost_steel", MAT_FROST_STEEL, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_OBSIDIAN = new ToolStabSword("dagger_obsidian", MAT_OBSIDIAN, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_CRYSTALLITE = new ToolStabSword("dagger_crystallite", MAT_CRYSTALLITE, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_DUSKSTEEL = new ToolStabSword("dagger_dusksteel", MAT_DUSKSTEEL, "dagger").setMaxStackSize(1);
	
	
	//Kabutowaris
	public static final Item KABUTOWARI_WOOD = new ToolStabSword("kabutowari_wood", ToolMaterial.WOOD, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_IRON = new ToolStabSword("kabutowari_iron", ToolMaterial.IRON, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_KOBOLD = new ToolStabSword("kabutowari_kobold", MAT_KOBOLD, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_COPPER = new ToolStabSword("kabutowari_copper", MAT_COPPER, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_SILVER = new ToolStabSword("kabutowari_silver", MAT_SILVER, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_BRONZE = new ToolStabSword("kabutowari_bronze", MAT_BRONZE, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_PLATINUM = new ToolStabSword("kabutowari_platinum", MAT_PLATINUM, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_STEEL = new ToolStabSword("kabutowari_steel", MAT_STEEL, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_SHADOW_PLATINUM = new ToolStabSword("kabutowari_shadow_platinum", MAT_SHADOW_PLATINUM, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_FROST_STEEL = new ToolStabSword("kabutowari_frost_steel", MAT_FROST_STEEL, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_OBSIDIAN = new ToolStabSword("kabutowari_obsidian", MAT_OBSIDIAN, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_CRYSTALLITE = new ToolStabSword("kabutowari_crystallite", MAT_CRYSTALLITE, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_DUSKSTEEL = new ToolStabSword("kabutowari_dusksteel", MAT_DUSKSTEEL, "kabutowari").setMaxStackSize(1);
	
	
	//Sharpening Stones
	public static final Item STONE_SHARPENING_STONE = new ItemSharpeningStone("stone_sharpening_stone", ToolMaterial.WOOD).setMaxStackSize(16);
	public static final Item IRON_SHARPENING_STONE = new ItemSharpeningStone("iron_sharpening_stone", ToolMaterial.IRON).setMaxStackSize(16);
	public static final Item KOBOLD_SHARPENING_STONE = new ItemSharpeningStone("kobold_sharpening_stone", MAT_KOBOLD).setMaxStackSize(16);
	public static final Item COPPER_SHARPENING_STONE = new ItemSharpeningStone("copper_sharpening_stone", MAT_COPPER).setMaxStackSize(16);
	public static final Item SILVER_SHARPENING_STONE = new ItemSharpeningStone("silver_sharpening_stone", MAT_SILVER).setMaxStackSize(16);
	public static final Item BRONZE_SHARPENING_STONE = new ItemSharpeningStone("bronze_sharpening_stone", MAT_BRONZE).setMaxStackSize(16);
	public static final Item PLATINUM_SHARPENING_STONE = new ItemSharpeningStone("platinum_sharpening_stone", MAT_PLATINUM).setMaxStackSize(16);
	public static final Item STEEL_SHARPENING_STONE = new ItemSharpeningStone("steel_sharpening_stone", MAT_STEEL).setMaxStackSize(16);
	public static final Item SHADOW_PLATINUM_SHARPENING_STONE = new ItemSharpeningStone("shadow_platinum_sharpening_stone", MAT_SHADOW_PLATINUM).setMaxStackSize(16);
	public static final Item FROST_STEEL_SHARPENING_STONE = new ItemSharpeningStone("frost_steel_sharpening_stone", MAT_FROST_STEEL).setMaxStackSize(16);
	public static final Item OBISIDAN_SHARPENING_STONE = new ItemSharpeningStone("obsidian_sharpening_stone", MAT_OBSIDIAN).setMaxStackSize(16);
	public static final Item CRYSTALLITE_SHARPENING_STONE = new ItemSharpeningStone("crystallite_sharpening_stone", MAT_CRYSTALLITE).setMaxStackSize(16);
	public static final Item DUSKSTEEL_SHARPENING_STONE = new ItemSharpeningStone("dusksteel_sharpening_stone", MAT_DUSKSTEEL).setMaxStackSize(16);
	
	
	//Armor
	public static final Item HELMET_KOBOLD = new ArmorBonusesBase("helmet_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.HEAD, 2.5D, -0.00160000512D, 3).setMaxStackSize(1);
	public static final Item CHESTPLATE_KOBOLD = new ArmorBonusesBase("chestplate_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.CHEST, 3.125D, 9.375D, -0.00767681028D, 3).setMaxStackSize(1);
	public static final Item LEGGINGS_KOBOLD = new ArmorBonusesBase("leggings_kobold", AMAT_KOBOLD, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00767681028D, 3).setMaxStackSize(1);
	public static final Item BOOTS_KOBOLD = new ArmorBonusesBase("boots_kobold", AMAT_KOBOLD, 1, EntityEquipmentSlot.FEET, 2.5D, -0.00160000512D, 3).setMaxStackSize(1);
	
	public static final Item HELMET_COPPER = new ArmorBonusesBase("helmet_copper", AMAT_COPPER, 1, EntityEquipmentSlot.HEAD, 2.5D, -0.00188214888D, 3).setMaxStackSize(1);
	public static final Item CHESTPLATE_COPPER = new ArmorBonusesBase("chestplate_copper", AMAT_COPPER, 1, EntityEquipmentSlot.CHEST, 3.125D, 10.542D, -0.00941253012D, 3).setMaxStackSize(1);
	public static final Item LEGGINGS_COPPER = new ArmorBonusesBase("leggings_copper", AMAT_COPPER, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00941253012D, 3).setMaxStackSize(1);
	public static final Item BOOTS_COPPER = new ArmorBonusesBase("boots_copper", AMAT_COPPER, 1, EntityEquipmentSlot.FEET, 2.5D, -0.00188214888D, 3).setMaxStackSize(1);
	
	public static final Item HELMET_SILVER = new ArmorBonusesBase("helmet_silver", AMAT_SILVER, 1, EntityEquipmentSlot.HEAD, 3.5D, -0.0022098285D, 4).setMaxStackSize(1);
	public static final Item CHESTPLATE_SILVER = new ArmorBonusesBase("chestplate_silver", AMAT_SILVER, 1, EntityEquipmentSlot.CHEST, 4.125D, 12.375D, -0.0110491425D, 4).setMaxStackSize(1);
	public static final Item LEGGINGS_SILVER = new ArmorBonusesBase("leggings_silver", AMAT_SILVER, 2, EntityEquipmentSlot.LEGS, 4.125D, -0.0110491425D, 4).setMaxStackSize(1);
	public static final Item BOOTS_SILVER = new ArmorBonusesBase("boots_silver", AMAT_SILVER, 1, EntityEquipmentSlot.FEET, 3.5D, -0.0022098285D, 4).setMaxStackSize(1);
	
	public static final Item HELMET_BRONZE = new ArmorBonusesBase("helmet_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.HEAD, 3.5D, -0.00403304862D, 4).setMaxStackSize(1);
	public static final Item CHESTPLATE_BRONZE = new ArmorBonusesBase("chestplate_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.CHEST, 4.125D, 22.583D, -0.020168189538D, 4).setMaxStackSize(1);
	public static final Item LEGGINGS_BRONZE = new ArmorBonusesBase("leggings_bronze", AMAT_BRONZE, 2, EntityEquipmentSlot.LEGS, 4.125D, -0.020168189538D, 4).setMaxStackSize(1);
	public static final Item BOOTS_BRONZE = new ArmorBonusesBase("boots_bronze", AMAT_BRONZE, 1, EntityEquipmentSlot.FEET, 3.5D, -0.00403304862D, 4).setMaxStackSize(1);
	
	public static final Item HELMET_PLATINUM = new ArmorBonusesBase("helmet_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.HEAD, 4.5D, -0.00452412162D, 5).setMaxStackSize(1);
	public static final Item CHESTPLATE_PLATINUM = new ArmorBonusesBase("chestplate_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.CHEST, 5.125D, 25.333D, -0.02261882238D, 5).setMaxStackSize(1);
	public static final Item LEGGINGS_PLATINUM = new ArmorBonusesBase("leggings_platinum", AMAT_PLATINUM, 2, EntityEquipmentSlot.LEGS, 5.125D, -0.02261882238D, 5).setMaxStackSize(1);
	public static final Item BOOTS_PLATINUM = new ArmorBonusesBase("boots_platinum", AMAT_PLATINUM, 1, EntityEquipmentSlot.FEET, 4.5D, -0.00452412162D, 5).setMaxStackSize(1);
	
	public static final Item HELMET_STEEL = new ArmorBonusesBase("helmet_steel", AMAT_STEEL, 1, EntityEquipmentSlot.HEAD, 4.5D, -0.0016741125D, 5).setMaxStackSize(1);
	public static final Item CHESTPLATE_STEEL = new ArmorBonusesBase("chestplate_steel", AMAT_STEEL, 1, EntityEquipmentSlot.CHEST, 5.125D, 9.375D, -0.0083705625D, 5).setMaxStackSize(1);
	public static final Item LEGGINGS_STEEL = new ArmorBonusesBase("leggings_steel", AMAT_STEEL, 2, EntityEquipmentSlot.LEGS, 5.125D, -0.0083705625D, 5).setMaxStackSize(1);
	public static final Item BOOTS_STEEL = new ArmorBonusesBase("boots_steel", AMAT_STEEL, 1, EntityEquipmentSlot.FEET, 4.5D, -0.0016741125D, 5).setMaxStackSize(1);
	
	public static final Item HELMET_SHADOW_PLATINUM = new ArmorBonusesBase("helmet_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.HEAD, 5.0D, -0.00360090438D, 6).setMaxStackSize(1);
	public static final Item CHESTPLATE_SHADOW_PLATINUM = new ArmorBonusesBase("chestplate_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.CHEST, 5.75D, 9.375D, -0.01800630762D, 6).setMaxStackSize(1);
	public static final Item LEGGINGS_SHADOW_PLATINUM = new ArmorBonusesBase("leggings_shadow_platinum", AMAT_SHADOW_PLATINUM, 2, EntityEquipmentSlot.LEGS, 5.75D, -0.01800630762D, 6).setMaxStackSize(1);
	public static final Item BOOTS_SHADOW_PLATINUM = new ArmorBonusesBase("boots_shadow_platinum", AMAT_SHADOW_PLATINUM, 1, EntityEquipmentSlot.FEET, 5.0D, -0.00360090438D, 6).setMaxStackSize(1);
	
	public static final Item HELMET_FROST_STEEL = new ArmorBonusesBase("helmet_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.HEAD, 5.0D, -0.00200179212D, 6).setMaxStackSize(1);
	public static final Item CHESTPLATE_FROST_STEEL = new ArmorBonusesBase("chestplate_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.CHEST, 5.75D, 9.375D, -0.01000717488D, 6).setMaxStackSize(1);
	public static final Item LEGGINGS_FROST_STEEL = new ArmorBonusesBase("leggings_frost_steel", AMAT_FROST_STEEL, 2, EntityEquipmentSlot.LEGS, 5.75D, -0.01000717488D, 6).setMaxStackSize(1);
	public static final Item BOOTS_FROST_STEEL = new ArmorBonusesBase("boots_frost_steel", AMAT_FROST_STEEL, 1, EntityEquipmentSlot.FEET, 5.0D, -00.00200179212D, 6).setMaxStackSize(1);
	
	public static final Item HELMET_OBSIDIAN = new ArmorBonusesBase("helmet_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.HEAD, 6.25D, -0.00117589662D, 7).setMaxStackSize(1);
	public static final Item CHESTPLATE_OBSIDIAN = new ArmorBonusesBase("chestplate_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.CHEST, 7.0D, 9.375D, -0.00587769738D, 7).setMaxStackSize(1);
	public static final Item LEGGINGS_OBSIDIAN = new ArmorBonusesBase("leggings_obsidian", AMAT_OBSIDIAN, 2, EntityEquipmentSlot.LEGS, 7.0D, -0.00587769738D, 7).setMaxStackSize(1);
	public static final Item BOOTS_OBSIDIAN = new ArmorBonusesBase("boots_obsidian", AMAT_OBSIDIAN, 1, EntityEquipmentSlot.FEET, 6.25D, -0.00117589662D, 7).setMaxStackSize(1);
	
	public static final Item HELMET_CRYSTALLITE = new ArmorBonusesBase("helmet_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.HEAD, 6.25D, -0.00240357912D, 7).setMaxStackSize(1);
	public static final Item CHESTPLATE_CRYSTALLITE = new ArmorBonusesBase("chestplate_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.CHEST, 7.0D, 9.375D, -0.01201610988D, 7).setMaxStackSize(1);
	public static final Item LEGGINGS_CRYSTALLITE = new ArmorBonusesBase("leggings_crystallite", AMAT_CRYSTALLITE, 2, EntityEquipmentSlot.LEGS, 7.0D, -0.01201610988D, 7).setMaxStackSize(1);
	public static final Item BOOTS_CRYSTALLITE = new ArmorBonusesBase("boots_crystallite", AMAT_CRYSTALLITE, 1, EntityEquipmentSlot.FEET, 6.25D, -0.00240357912D, 7).setMaxStackSize(1);
	
	public static final Item HELMET_DUSKSTEEL = new ArmorBonusesBase("helmet_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.HEAD, 7.25D, -0.00180089862D, 8).setMaxStackSize(1);
	public static final Item CHESTPLATE_DUSKSTEEL = new ArmorBonusesBase("chestplate_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.CHEST, 7.75D, 9.375D, -0.00900270738D, 8).setMaxStackSize(1);
	public static final Item LEGGINGS_DUSKSTEEL = new ArmorBonusesBase("leggings_dusksteel", AMAT_DUSKSTEEL, 2, EntityEquipmentSlot.LEGS, 7.75D, -0.00900270738D, 8).setMaxStackSize(1);
	public static final Item BOOTS_DUSKSTEEL = new ArmorBonusesBase("boots_dusksteel", AMAT_DUSKSTEEL, 1, EntityEquipmentSlot.FEET, 7.25D, -0.00180089862D, 8).setMaxStackSize(1);
}
