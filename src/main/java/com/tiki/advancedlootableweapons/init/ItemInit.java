package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.items.ItemBase;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;
import com.tiki.advancedlootableweapons.tools.ToolAxe;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import com.tiki.advancedlootableweapons.tools.ToolHoe;
import com.tiki.advancedlootableweapons.tools.ToolPickaxe;
import com.tiki.advancedlootableweapons.tools.ToolShovel;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolSpear;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;
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
	
	//Rapiers
	public static final Item RAPIER_WOOD = new ToolStabSword("rapier_wood", ToolMaterial.WOOD, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_IRON = new ToolStabSword("rapier_iron", ToolMaterial.IRON, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_KOBOLD = new ToolStabSword("rapier_kobold", MAT_KOBOLD, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_COPPER = new ToolStabSword("rapier_copper", MAT_COPPER, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_SILVER = new ToolStabSword("rapier_silver", MAT_SILVER, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_BRONZE = new ToolStabSword("rapier_bronze", MAT_BRONZE, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_PLATINUM = new ToolStabSword("rapier_platinum", MAT_PLATINUM, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_STEEL = new ToolStabSword("rapier_steel", MAT_STEEL, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_SHADOW_PLATINUM = new ToolStabSword("rapier_shadow_platinum", MAT_SHADOW_PLATINUM, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_FROST_STEEL = new ToolStabSword("rapier_frost_steel", MAT_FROST_STEEL, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_OBSIDIAN = new ToolStabSword("rapier_obsidian", MAT_OBSIDIAN, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_CRYSTALLITE = new ToolStabSword("rapier_crystallite", MAT_CRYSTALLITE, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_DUSKSTEEL = new ToolStabSword("rapier_dusksteel", MAT_DUSKSTEEL, "rapier").setMaxStackSize(1);
	
	//Rapiers
	public static final Item TALWAR_WOOD = new ToolStabSword("talwar_wood", ToolMaterial.WOOD, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_IRON = new ToolStabSword("talwar_iron", ToolMaterial.IRON, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_KOBOLD = new ToolStabSword("talwar_kobold", MAT_KOBOLD, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_COPPER = new ToolStabSword("talwar_copper", MAT_COPPER, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_SILVER = new ToolStabSword("talwar_silver", MAT_SILVER, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_BRONZE = new ToolStabSword("talwar_bronze", MAT_BRONZE, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_PLATINUM = new ToolStabSword("talwar_platinum", MAT_PLATINUM, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_STEEL = new ToolStabSword("talwar_steel", MAT_STEEL, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_SHADOW_PLATINUM = new ToolStabSword("talwar_shadow_platinum", MAT_SHADOW_PLATINUM, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_FROST_STEEL = new ToolStabSword("talwar_frost_steel", MAT_FROST_STEEL, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_OBSIDIAN = new ToolStabSword("talwar_obsidian", MAT_OBSIDIAN, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_CRYSTALLITE = new ToolStabSword("talwar_crystallite", MAT_CRYSTALLITE, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_DUSKSTEEL = new ToolStabSword("talwar_dusksteel", MAT_DUSKSTEEL, "talwar").setMaxStackSize(1);
	
	//Cleavers
	public static final Item CLEAVER_WOOD = new ToolStabSword("cleaver_wood", ToolMaterial.WOOD, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_IRON = new ToolStabSword("cleaver_iron", ToolMaterial.IRON, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_KOBOLD = new ToolStabSword("cleaver_kobold", MAT_KOBOLD, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_COPPER = new ToolStabSword("cleaver_copper", MAT_COPPER, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_SILVER = new ToolStabSword("cleaver_silver", MAT_SILVER, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_BRONZE = new ToolStabSword("cleaver_bronze", MAT_BRONZE, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_PLATINUM = new ToolStabSword("cleaver_platinum", MAT_PLATINUM, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_STEEL = new ToolStabSword("cleaver_steel", MAT_STEEL, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_SHADOW_PLATINUM = new ToolStabSword("cleaver_shadow_platinum", MAT_SHADOW_PLATINUM, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_FROST_STEEL = new ToolStabSword("cleaver_frost_steel", MAT_FROST_STEEL, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_OBSIDIAN = new ToolStabSword("cleaver_obsidian", MAT_OBSIDIAN, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_CRYSTALLITE = new ToolStabSword("cleaver_crystallite", MAT_CRYSTALLITE, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_DUSKSTEEL = new ToolStabSword("cleaver_dusksteel", MAT_DUSKSTEEL, "cleaver").setMaxStackSize(1);
	
	//Maces
	public static final Item MACE_WOOD = new ToolStabSword("mace_wood", ToolMaterial.WOOD, "mace").setMaxStackSize(1);
	public static final Item MACE_IRON = new ToolStabSword("mace_iron", ToolMaterial.IRON, "mace").setMaxStackSize(1);
	public static final Item MACE_KOBOLD = new ToolStabSword("mace_kobold", MAT_KOBOLD, "mace").setMaxStackSize(1);
	public static final Item MACE_COPPER = new ToolStabSword("mace_copper", MAT_COPPER, "mace").setMaxStackSize(1);
	public static final Item MACE_SILVER = new ToolStabSword("mace_silver", MAT_SILVER, "mace").setMaxStackSize(1);
	public static final Item MACE_BRONZE = new ToolStabSword("mace_bronze", MAT_BRONZE, "mace").setMaxStackSize(1);
	public static final Item MACE_PLATINUM = new ToolStabSword("mace_platinum", MAT_PLATINUM, "mace").setMaxStackSize(1);
	public static final Item MACE_STEEL = new ToolStabSword("mace_steel", MAT_STEEL, "mace").setMaxStackSize(1);
	public static final Item MACE_SHADOW_PLATINUM = new ToolStabSword("mace_shadow_platinum", MAT_SHADOW_PLATINUM, "mace").setMaxStackSize(1);
	public static final Item MACE_FROST_STEEL = new ToolStabSword("mace_frost_steel", MAT_FROST_STEEL, "mace").setMaxStackSize(1);
	public static final Item MACE_OBSIDIAN = new ToolStabSword("mace_obsidian", MAT_OBSIDIAN, "mace").setMaxStackSize(1);
	public static final Item MACE_CRYSTALLITE = new ToolStabSword("mace_crystallite", MAT_CRYSTALLITE, "mace").setMaxStackSize(1);
	public static final Item MACE_DUSKSTEEL = new ToolStabSword("mace_dusksteel", MAT_DUSKSTEEL, "mace").setMaxStackSize(1);
	
	//Staffs
	public static final Item STAFF_WOOD = new ToolStabSword("staff_wood", ToolMaterial.WOOD, "staff").setMaxStackSize(1);
	public static final Item STAFF_IRON = new ToolStabSword("staff_iron", ToolMaterial.IRON, "staff").setMaxStackSize(1);
	public static final Item STAFF_KOBOLD = new ToolStabSword("staff_kobold", MAT_KOBOLD, "staff").setMaxStackSize(1);
	public static final Item STAFF_COPPER = new ToolStabSword("staff_copper", MAT_COPPER, "staff").setMaxStackSize(1);
	public static final Item STAFF_SILVER = new ToolStabSword("staff_silver", MAT_SILVER, "staff").setMaxStackSize(1);
	public static final Item STAFF_BRONZE = new ToolStabSword("staff_bronze", MAT_BRONZE, "staff").setMaxStackSize(1);
	public static final Item STAFF_PLATINUM = new ToolStabSword("staff_platinum", MAT_PLATINUM, "staff").setMaxStackSize(1);
	public static final Item STAFF_STEEL = new ToolStabSword("staff_steel", MAT_STEEL, "staff").setMaxStackSize(1);
	public static final Item STAFF_SHADOW_PLATINUM = new ToolStabSword("staff_shadow_platinum", MAT_SHADOW_PLATINUM, "staff").setMaxStackSize(1);
	public static final Item STAFF_FROST_STEEL = new ToolStabSword("staff_frost_steel", MAT_FROST_STEEL, "staff").setMaxStackSize(1);
	public static final Item STAFF_OBSIDIAN = new ToolStabSword("staff_obsidian", MAT_OBSIDIAN, "staff").setMaxStackSize(1);
	public static final Item STAFF_CRYSTALLITE = new ToolStabSword("staff_crystallite", MAT_CRYSTALLITE, "staff").setMaxStackSize(1);
	public static final Item STAFF_DUSKSTEEL = new ToolStabSword("staff_dusksteel", MAT_DUSKSTEEL, "staff").setMaxStackSize(1);
	
	//Longswords
	public static final Item LONGSWORD_WOOD = new ToolSlashSword("longsword_wood", ToolMaterial.WOOD, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_IRON = new ToolSlashSword("longsword_iron", ToolMaterial.IRON, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_KOBOLD = new ToolSlashSword("longsword_kobold", MAT_KOBOLD, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_COPPER = new ToolSlashSword("longsword_copper", MAT_COPPER, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_SILVER = new ToolSlashSword("longsword_silver", MAT_SILVER, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_BRONZE = new ToolSlashSword("longsword_bronze", MAT_BRONZE, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_PLATINUM = new ToolSlashSword("longsword_platinum", MAT_PLATINUM, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_STEEL = new ToolSlashSword("longsword_steel", MAT_STEEL, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_SHADOW_PLATINUM = new ToolSlashSword("longsword_shadow_platinum", MAT_SHADOW_PLATINUM, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_FROST_STEEL = new ToolSlashSword("longsword_frost_steel", MAT_FROST_STEEL, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_OBSIDIAN = new ToolSlashSword("longsword_obsidian", MAT_OBSIDIAN, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_CRYSTALLITE = new ToolSlashSword("longsword_crystallite", MAT_CRYSTALLITE, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_DUSKSTEEL = new ToolSlashSword("longsword_dusksteel", MAT_DUSKSTEEL, "longsword").setMaxStackSize(1);
	
	//Kodachis
	public static final Item KODACHI_WOOD = new ToolSlashSword("kodachi_wood", ToolMaterial.WOOD, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_IRON = new ToolSlashSword("kodachi_iron", ToolMaterial.IRON, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_KOBOLD = new ToolSlashSword("kodachi_kobold", MAT_KOBOLD, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_COPPER = new ToolSlashSword("kodachi_copper", MAT_COPPER, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_SILVER = new ToolSlashSword("kodachi_silver", MAT_SILVER, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_BRONZE = new ToolSlashSword("kodachi_bronze", MAT_BRONZE, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_PLATINUM = new ToolSlashSword("kodachi_platinum", MAT_PLATINUM, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_STEEL = new ToolSlashSword("kodachi_steel", MAT_STEEL, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_SHADOW_PLATINUM = new ToolSlashSword("kodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_FROST_STEEL = new ToolSlashSword("kodachi_frost_steel", MAT_FROST_STEEL, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_OBSIDIAN = new ToolSlashSword("kodachi_obsidian", MAT_OBSIDIAN, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_CRYSTALLITE = new ToolSlashSword("kodachi_crystallite", MAT_CRYSTALLITE, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_DUSKSTEEL = new ToolSlashSword("kodachi_dusksteel", MAT_DUSKSTEEL, "kodachi").setMaxStackSize(1);
	
	//Battleaxes
	public static final Item BATTLEAXE_WOOD = new ToolSlashSword("battleaxe_wood", ToolMaterial.WOOD, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_IRON = new ToolSlashSword("battleaxe_iron", ToolMaterial.IRON, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_KOBOLD = new ToolSlashSword("battleaxe_kobold", MAT_KOBOLD, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_COPPER = new ToolSlashSword("battleaxe_copper", MAT_COPPER, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_SILVER = new ToolSlashSword("battleaxe_silver", MAT_SILVER, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_BRONZE = new ToolSlashSword("battleaxe_bronze", MAT_BRONZE, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_PLATINUM = new ToolSlashSword("battleaxe_platinum", MAT_PLATINUM, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_STEEL = new ToolSlashSword("battleaxe_steel", MAT_STEEL, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_SHADOW_PLATINUM = new ToolSlashSword("battleaxe_shadow_platinum", MAT_SHADOW_PLATINUM, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_FROST_STEEL = new ToolSlashSword("battleaxe_frost_steel", MAT_FROST_STEEL, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_OBSIDIAN = new ToolSlashSword("battleaxe_obsidian", MAT_OBSIDIAN, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_CRYSTALLITE = new ToolSlashSword("battleaxe_crystallite", MAT_CRYSTALLITE, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_DUSKSTEEL = new ToolSlashSword("battleaxe_dusksteel", MAT_DUSKSTEEL, "battleaxe").setMaxStackSize(1);
	
	//Zweihanders
	public static final Item ZWEIHANDER_WOOD = new ToolSlashSword("zweihander_wood", ToolMaterial.WOOD, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_IRON = new ToolSlashSword("zweihander_iron", ToolMaterial.IRON, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_KOBOLD = new ToolSlashSword("zweihander_kobold", MAT_KOBOLD, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_COPPER = new ToolSlashSword("zweihander_copper", MAT_COPPER, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_SILVER = new ToolSlashSword("zweihander_silver", MAT_SILVER, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_BRONZE = new ToolSlashSword("zweihander_bronze", MAT_BRONZE, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_PLATINUM = new ToolSlashSword("zweihander_platinum", MAT_PLATINUM, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_STEEL = new ToolSlashSword("zweihander_steel", MAT_STEEL, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_SHADOW_PLATINUM = new ToolSlashSword("zweihander_shadow_platinum", MAT_SHADOW_PLATINUM, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_FROST_STEEL = new ToolSlashSword("zweihander_frost_steel", MAT_FROST_STEEL, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_OBSIDIAN = new ToolSlashSword("zweihander_obsidian", MAT_OBSIDIAN, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_CRYSTALLITE = new ToolSlashSword("zweihander_crystallite", MAT_CRYSTALLITE, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_DUSKSTEEL = new ToolSlashSword("zweihander_dusksteel", MAT_DUSKSTEEL, "zweihander").setMaxStackSize(1);
	
	//Nodachis
	public static final Item NODACHI_WOOD = new ToolSlashSword("nodachi_wood", ToolMaterial.WOOD, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_IRON = new ToolSlashSword("nodachi_iron", ToolMaterial.IRON, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_KOBOLD = new ToolSlashSword("nodachi_kobold", MAT_KOBOLD, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_COPPER = new ToolSlashSword("nodachi_copper", MAT_COPPER, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_SILVER = new ToolSlashSword("nodachi_silver", MAT_SILVER, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_BRONZE = new ToolSlashSword("nodachi_bronze", MAT_BRONZE, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_PLATINUM = new ToolSlashSword("nodachi_platinum", MAT_PLATINUM, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_STEEL = new ToolSlashSword("nodachi_steel", MAT_STEEL, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_SHADOW_PLATINUM = new ToolSlashSword("nodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_FROST_STEEL = new ToolSlashSword("nodachi_frost_steel", MAT_FROST_STEEL, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_OBSIDIAN = new ToolSlashSword("nodachi_obsidian", MAT_OBSIDIAN, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_CRYSTALLITE = new ToolSlashSword("nodachi_crystallite", MAT_CRYSTALLITE, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_DUSKSTEEL = new ToolSlashSword("nodachi_dusksteel", MAT_DUSKSTEEL, "nodachi").setMaxStackSize(1);
	
	//Sabres
	public static final Item SABRE_WOOD = new ToolSlashSword("sabre_wood", ToolMaterial.WOOD, "sabre").setMaxStackSize(1);
	public static final Item SABRE_IRON = new ToolSlashSword("sabre_iron", ToolMaterial.IRON, "sabre").setMaxStackSize(1);
	public static final Item SABRE_KOBOLD = new ToolSlashSword("sabre_kobold", MAT_KOBOLD, "sabre").setMaxStackSize(1);
	public static final Item SABRE_COPPER = new ToolSlashSword("sabre_copper", MAT_COPPER, "sabre").setMaxStackSize(1);
	public static final Item SABRE_SILVER = new ToolSlashSword("sabre_silver", MAT_SILVER, "sabre").setMaxStackSize(1);
	public static final Item SABRE_BRONZE = new ToolSlashSword("sabre_bronze", MAT_BRONZE, "sabre").setMaxStackSize(1);
	public static final Item SABRE_PLATINUM = new ToolSlashSword("sabre_platinum", MAT_PLATINUM, "sabre").setMaxStackSize(1);
	public static final Item SABRE_STEEL = new ToolSlashSword("sabre_steel", MAT_STEEL, "sabre").setMaxStackSize(1);
	public static final Item SABRE_SHADOW_PLATINUM = new ToolSlashSword("sabre_shadow_platinum", MAT_SHADOW_PLATINUM, "sabre").setMaxStackSize(1);
	public static final Item SABRE_FROST_STEEL = new ToolSlashSword("sabre_frost_steel", MAT_FROST_STEEL, "sabre").setMaxStackSize(1);
	public static final Item SABRE_OBSIDIAN = new ToolSlashSword("sabre_obsidian", MAT_OBSIDIAN, "sabre").setMaxStackSize(1);
	public static final Item SABRE_CRYSTALLITE = new ToolSlashSword("sabre_crystallite", MAT_CRYSTALLITE, "sabre").setMaxStackSize(1);
	public static final Item SABRE_DUSKSTEEL = new ToolSlashSword("sabre_dusksteel", MAT_DUSKSTEEL, "sabre").setMaxStackSize(1);
	
	//Makhairas
	public static final Item MAKHAIRA_WOOD = new ToolSlashSword("makhaira_wood", ToolMaterial.WOOD, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_IRON = new ToolSlashSword("makhaira_iron", ToolMaterial.IRON, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_KOBOLD = new ToolSlashSword("makhaira_kobold", MAT_KOBOLD, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_COPPER = new ToolSlashSword("makhaira_copper", MAT_COPPER, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_SILVER = new ToolSlashSword("makhaira_silver", MAT_SILVER, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_BRONZE = new ToolSlashSword("makhaira_bronze", MAT_BRONZE, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_PLATINUM = new ToolSlashSword("makhaira_platinum", MAT_PLATINUM, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_STEEL = new ToolSlashSword("makhaira_steel", MAT_STEEL, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_SHADOW_PLATINUM = new ToolSlashSword("makhaira_shadow_platinum", MAT_SHADOW_PLATINUM, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_FROST_STEEL = new ToolSlashSword("makhaira_frost_steel", MAT_FROST_STEEL, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_OBSIDIAN = new ToolSlashSword("makhaira_obsidian", MAT_OBSIDIAN, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_CRYSTALLITE = new ToolSlashSword("makhaira_crystallite", MAT_CRYSTALLITE, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_DUSKSTEEL = new ToolSlashSword("makhaira_dusksteel", MAT_DUSKSTEEL, "makhaira").setMaxStackSize(1);
	
	//Spears
	public static final Item SPEAR_WOOD = new ToolSpear("spear_wood", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item SPEAR_IRON = new ToolSpear("spear_iron", ToolMaterial.IRON).setMaxStackSize(1);
	public static final Item SPEAR_KOBOLD = new ToolSpear("spear_kobold", MAT_KOBOLD).setMaxStackSize(1);
	public static final Item SPEAR_COPPER = new ToolSpear("spear_copper", MAT_COPPER).setMaxStackSize(1);
	public static final Item SPEAR_SILVER = new ToolSpear("spear_silver", MAT_SILVER).setMaxStackSize(1);
	public static final Item SPEAR_BRONZE = new ToolSpear("spear_bronze", MAT_BRONZE).setMaxStackSize(1);
	public static final Item SPEAR_PLATINUM = new ToolSpear("spear_platinum", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item SPEAR_STEEL = new ToolSpear("spear_steel", MAT_STEEL).setMaxStackSize(1);
	public static final Item SPEAR_SHADOW_PLATINUM = new ToolSpear("spear_shadow_platinum", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
	public static final Item SPEAR_FROST_STEEL = new ToolSpear("spear_frost_steel", MAT_FROST_STEEL).setMaxStackSize(1);
	public static final Item SPEAR_OBSIDIAN = new ToolSpear("spear_obsidian", MAT_OBSIDIAN).setMaxStackSize(1);
	public static final Item SPEAR_CRYSTALLITE = new ToolSpear("spear_crystallite", MAT_CRYSTALLITE).setMaxStackSize(1);
	public static final Item SPEAR_DUSKSTEEL = new ToolSpear("spear_dusksteel", MAT_DUSKSTEEL).setMaxStackSize(1);
	
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
	
	//Forge Hammers
	public static final Item STONE_FORGE_HAMMER = new ToolForgeHammer("stone_forge_hammer", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item IRON_FORGE_HAMMER = new ToolForgeHammer("iron_forge_hammer", ToolMaterial.IRON).setMaxStackSize(1);
	public static final Item KOBOLD_FORGE_HAMMER = new ToolForgeHammer("kobold_forge_hammer", MAT_KOBOLD).setMaxStackSize(1);
	public static final Item COPPER_FORGE_HAMMER = new ToolForgeHammer("copper_forge_hammer", MAT_COPPER).setMaxStackSize(1);
	public static final Item SILVER_FORGE_HAMMER = new ToolForgeHammer("silver_forge_hammer", MAT_SILVER).setMaxStackSize(1);
	public static final Item BRONZE_FORGE_HAMMER = new ToolForgeHammer("bronze_forge_hammer", MAT_BRONZE).setMaxStackSize(1);
	public static final Item PLATINUM_FORGE_HAMMER = new ToolForgeHammer("platinum_forge_hammer", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item STEEL_FORGE_HAMMER = new ToolForgeHammer("steel_forge_hammer", MAT_STEEL).setMaxStackSize(1);
	public static final Item SHADOW_PLATINUM_FORGE_HAMMER = new ToolForgeHammer("shadow_platinum_forge_hammer", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
	public static final Item FROST_STEEL_FORGE_HAMMER = new ToolForgeHammer("frost_steel_forge_hammer", MAT_FROST_STEEL).setMaxStackSize(1);
	public static final Item OBSIDIAN_FORGE_HAMMER = new ToolForgeHammer("obsidian_forge_hammer", MAT_OBSIDIAN).setMaxStackSize(1);
	public static final Item CRYSTALLITE_FORGE_HAMMER = new ToolForgeHammer("crystallite_forge_hammer", MAT_CRYSTALLITE).setMaxStackSize(1);
	public static final Item DUSKSTEEL_FORGE_HAMMER = new ToolForgeHammer("dusksteel_forge_hammer", MAT_DUSKSTEEL).setMaxStackSize(1);
	
	//Hot Tool Heads
	public static final Item HOT_TOOL_HEAD = new ItemHotToolHead("hot_tool_head");
	
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
	public static final Item KODACHI_HOT_TOOL_HEAD_3 = new ItemHotToolHead("kodachi_hot_tool_head_3");
	public static final Item KODACHI_HOT_TOOL_HEAD_4 = new ItemHotToolHead("kodachi_hot_tool_head_4");
	
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
