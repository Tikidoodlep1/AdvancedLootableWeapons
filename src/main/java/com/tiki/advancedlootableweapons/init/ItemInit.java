package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.armor.ArmorBase;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.items.ItemBase;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;
import com.tiki.advancedlootableweapons.tools.ToolAxe;
import com.tiki.advancedlootableweapons.tools.ToolDagger;
import com.tiki.advancedlootableweapons.tools.ToolHoe;
import com.tiki.advancedlootableweapons.tools.ToolKabutowari;
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
	public static final Item INGOT_DEMONSCALE = new ItemBase("ingot_demonscale"); //Dusk Steel
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_SILVER = new ItemBase("ingot_silver");
	public static final Item INGOT_SHADOW_PLATINUM = new ItemBase("ingot_shadow_platinum");
	
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
	      public static final ToolMaterial MAT_DEMONSCALE = EnumHelper.addToolMaterial("mat_demonscale", 3, 812, 8.5F, 8.25F, 14).setRepairItem(new ItemStack(INGOT_DEMONSCALE));
	
	public static final ArmorMaterial AMAT_STEEL = EnumHelper.addArmorMaterial("amat_steel", ModInfo.ID + ":steel", 40, new int[]{4,7,9,4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5F).setRepairItem(new ItemStack(INGOT_STEEL));
	public static final ArmorMaterial AMAT_DEMONSCALE = EnumHelper.addArmorMaterial("amat_demonscale", ModInfo.ID + ":demonscale", 40, new int[]{0,12,14,9}, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F).setRepairItem(new ItemStack(INGOT_DEMONSCALE));
	
	
	//Tools
	public static final Item AXE_STEEL = new ToolAxe("axe_steel", MAT_STEEL);
	public static final Item HOE_STEEL = new ToolHoe("hoe_steel", MAT_STEEL);
	public static final Item PICKAXE_STEEL = new ToolPickaxe("pickaxe_steel", MAT_STEEL);
	public static final Item SHOVEL_STEEL = new ToolShovel("shovel_steel", MAT_STEEL);
	public static final Item SWORD_STEEL = new ToolSword("sword_steel", MAT_STEEL);
	
	
	//Daggers
	public static final Item DAGGER_WOOD = new ToolDagger("dagger_wood", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item DAGGER_IRON = new ToolDagger("dagger_iron", ToolMaterial.IRON).setMaxStackSize(1);
	public static final Item DAGGER_KOBOLD = new ToolDagger("dagger_kobold", MAT_KOBOLD).setMaxStackSize(1);
	public static final Item DAGGER_COPPER = new ToolDagger("dagger_copper", MAT_COPPER).setMaxStackSize(1);
	public static final Item DAGGER_SILVER = new ToolDagger("dagger_silver", MAT_SILVER).setMaxStackSize(1);
	public static final Item DAGGER_BRONZE = new ToolDagger("dagger_bronze", MAT_BRONZE).setMaxStackSize(1);
	public static final Item DAGGER_PLATINUM = new ToolDagger("dagger_platinum", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item DAGGER_STEEL = new ToolDagger("dagger_steel", MAT_STEEL).setMaxStackSize(1);
	public static final Item DAGGER_SHADOW_PLATINUM = new ToolDagger("dagger_shadow_platinum", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
	public static final Item DAGGER_FROST_STEEL = new ToolDagger("dagger_frost_steel", MAT_FROST_STEEL).setMaxStackSize(1);
	public static final Item DAGGER_OBSIDIAN = new ToolDagger("dagger_obsidian", MAT_OBSIDIAN).setMaxStackSize(1);
	public static final Item DAGGER_CRYSTALLITE = new ToolDagger("dagger_crystallite", MAT_CRYSTALLITE).setMaxStackSize(1);
	public static final Item DAGGER_DEMONSCALE = new ToolDagger("dagger_demonscale", MAT_DEMONSCALE).setMaxStackSize(1);
	
	
	//Kabutowaris
	public static final Item KABUTOWARI_WOOD = new ToolKabutowari("kabutowari_wood", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item KABUTOWARI_IRON = new ToolKabutowari("kabutowari_iron", ToolMaterial.IRON).setMaxStackSize(1);
	public static final Item KABUTOWARI_KOBOLD = new ToolKabutowari("kabutowari_kobold", MAT_KOBOLD).setMaxStackSize(1);
	public static final Item KABUTOWARI_COPPER = new ToolKabutowari("kabutowari_copper", MAT_COPPER).setMaxStackSize(1);
	public static final Item KABUTOWARI_SILVER = new ToolKabutowari("kabutowari_silver", MAT_SILVER).setMaxStackSize(1);
	public static final Item KABUTOWARI_BRONZE = new ToolKabutowari("kabutowari_bronze", MAT_BRONZE).setMaxStackSize(1);
	public static final Item KABUTOWARI_PLATINUM = new ToolKabutowari("kabutowari_platinum", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item KABUTOWARI_STEEL = new ToolKabutowari("kabutowari_steel", MAT_STEEL).setMaxStackSize(1);
	public static final Item KABUTOWARI_SHADOW_PLATINUM = new ToolKabutowari("kabutowari_shadow_platinum", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
	public static final Item KABUTOWARI_FROST_STEEL = new ToolKabutowari("kabutowari_frost_steel", MAT_FROST_STEEL).setMaxStackSize(1);
	public static final Item KABUTOWARI_OBSIDIAN = new ToolKabutowari("kabutowari_obsidian", MAT_OBSIDIAN).setMaxStackSize(1);
	public static final Item KABUTOWARI_CRYSTALLITE = new ToolKabutowari("kabutowari_crystallite", MAT_CRYSTALLITE).setMaxStackSize(1);
	public static final Item KABUTOWARI_DEMONSCALE = new ToolKabutowari("kabutowari_demonscale", MAT_DEMONSCALE).setMaxStackSize(1);
	
	
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
	public static final Item DEMONSCALE_SHARPENING_STONE = new ItemSharpeningStone("demonscale_sharpening_stone", MAT_DEMONSCALE).setMaxStackSize(16);
	
	
	//Armor
	public static final Item HELMET_STEEL = new ArmorBase("helmet_steel", AMAT_STEEL, 1, EntityEquipmentSlot.HEAD).setMaxStackSize(1);
	public static final Item CHESTPLATE_STEEL = new ArmorBase("chestplate_steel", AMAT_STEEL, 1, EntityEquipmentSlot.CHEST).setMaxStackSize(1);
	public static final Item LEGGINGS_STEEL = new ArmorBase("leggings_steel", AMAT_STEEL, 2, EntityEquipmentSlot.LEGS).setMaxStackSize(1);
	public static final Item BOOTS_STEEL = new ArmorBase("boots_steel", AMAT_STEEL, 1, EntityEquipmentSlot.FEET).setMaxStackSize(1);
	
	public static final Item HELMET_DEMONSCALE = new ArmorBonusesBase("helmet_demonscale", AMAT_DEMONSCALE, 1, EntityEquipmentSlot.HEAD, 50.0D, 50.0D, 0.2D).setMaxStackSize(1);
}
