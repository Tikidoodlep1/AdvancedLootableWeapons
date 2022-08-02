package com.tiki.advancedlootableweapons.init;

import java.util.HashMap;
import java.util.Map;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.ID);
	public static final Map<Item, ItemPropertyFunction> toolHeadMap = new HashMap<Item, ItemPropertyFunction>();
	
	//Mats
	public static final ForgeTier MAT_KOBOLD = new ForgeTier(1, CommonConfigHandler.KOBOLD_DURABILITY.get(), 6.0F, CommonConfigHandler.KOBOLD_DAMAGE.get(), 22,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_KOBOLD.get()));
	public static final ForgeTier MAT_COPPER = new ForgeTier(2, CommonConfigHandler.COPPER_DURABILITY.get(), 6.0F, CommonConfigHandler.COPPER_DAMAGE.get(), 22,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
	public static final ForgeTier MAT_SILVER = new ForgeTier(2, CommonConfigHandler.SILVER_DURABILITY.get(), 7.0F, CommonConfigHandler.SILVER_DAMAGE.get(), 24,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_SILVER.get()));
	public static final ForgeTier MAT_BRONZE = new ForgeTier(2, CommonConfigHandler.BRONZE_DURABILITY.get(), 6.5F, CommonConfigHandler.BRONZE_DAMAGE.get(), 12,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_BRONZE.get()));
	public static final ForgeTier MAT_PLATINUM = new ForgeTier(2, CommonConfigHandler.PLATINUM_DURABILITY.get(), 10.0F, CommonConfigHandler.PLATINUM_DAMAGE.get(), 26,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_PLATINUM.get()));
	public static final ForgeTier MAT_STEEL = new ForgeTier(2, CommonConfigHandler.STEEL_DURABILITY.get(), 7.0F, CommonConfigHandler.STEEL_DAMAGE.get(), 18,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_STEEL.get()));
	public static final ForgeTier MAT_SHADOW_PLATINUM = new ForgeTier(3, CommonConfigHandler.SHADOW_PLATINUM_DURABILITY.get(), 8.25F, CommonConfigHandler.SHADOW_PLATINUM_DAMAGE.get(), 21,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_SHADOW_PLATINUM.get()));
	public static final ForgeTier MAT_FROST_STEEL = new ForgeTier(3, CommonConfigHandler.FROST_STEEL_DURABILITY.get(), 7.0F, CommonConfigHandler.FROST_STEEL_DAMAGE.get(), 30,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_FROST_STEEL.get()));
	public static final ForgeTier MAT_OBSIDIAN = new ForgeTier(3, CommonConfigHandler.OBSIDIAN_DURABILITY.get(), 7.5F, CommonConfigHandler.OBSIDIAN_DAMAGE.get(), 18,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_OBSIDIAN.get()));
	public static final ForgeTier MAT_CRYSTALLITE = new ForgeTier(3, CommonConfigHandler.CRYSTALLITE_DURABILITY.get(), 7.5F, CommonConfigHandler.CRYSTALLITE_DAMAGE.get(), 20,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_CRYSTALLITE.get()));
	public static final ForgeTier MAT_DUSKSTEEL = new ForgeTier(3, CommonConfigHandler.DUSKSTEEL_DURABILITY.get(), 8.5F, CommonConfigHandler.DUSKSTEEL_DAMAGE.get(), 14,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemInit.INGOT_DUSKSTEEL.get()));
	
	
	public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_KOBOLD = ITEMS.register("ingot_kobold", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_SILVER = ITEMS.register("ingot_silver", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_BRONZE = ITEMS.register("ingot_bronze", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_PLATINUM = ITEMS.register("ingot_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_STEEL = ITEMS.register("ingot_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_SHADOW_PLATINUM = ITEMS.register("ingot_shadow_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_FROST_STEEL = ITEMS.register("ingot_frost_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_OBSIDIAN = ITEMS.register("ingot_obsidian_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_CRYSTALLITE = ITEMS.register("ingot_crystallite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> INGOT_DUSKSTEEL = ITEMS.register("ingot_dusksteel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

	public static final RegistryObject<Item> SHARD_OBSIDIAN = ITEMS.register("shard_obsidian", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_TIN = ITEMS.register("nugget_tin", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_KOBOLD = ITEMS.register("nugget_kobold", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_SILVER = ITEMS.register("nugget_silver", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_BRONZE = ITEMS.register("nugget_bronze", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_PLATINUM = ITEMS.register("nugget_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_STEEL = ITEMS.register("nugget_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_SHADOW_PLATINUM = ITEMS.register("nugget_shadow_platinum", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_FROST_STEEL = ITEMS.register("nugget_frost_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_OBSIDIAN = ITEMS.register("nugget_obsidian_steel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_CRYSTALLITE = ITEMS.register("nugget_crystallite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NUGGET_DUSKSTEEL = ITEMS.register("nugget_dusksteel", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

	public static final RegistryObject<Item> POWDER_FELDSPAR = ITEMS.register("powder_feldspar", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> POWDER_GRANITE = ITEMS.register("powder_granite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> POWDER_DIORITE = ITEMS.register("powder_diorite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLAY_GRANITE = ITEMS.register("clay_granite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLAY_DIORITE = ITEMS.register("clay_diorite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BRICK_GRANITE = ITEMS.register("brick_granite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BRICK_DIORITE = ITEMS.register("brick_diorite", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));

	public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SHADOW = ITEMS.register("shadow", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CONGEALED_SHADOW = ITEMS.register("congealed_shadow", () -> new Item(new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> DAGGER_HEAD = ITEMS.register("dagger_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_HEAD = ITEMS.register("kabutowari_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> RAPIER_HEAD = ITEMS.register("rapier_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> TALWAR_HEAD = ITEMS.register("talwar_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> MACE_HEAD = ITEMS.register("mace_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> CLEAVER_HEAD = ITEMS.register("cleaver_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> STAFF_HEAD = ITEMS.register("staff_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> LONGSWORD_HEAD = ITEMS.register("longsword_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_HEAD = ITEMS.register("battleaxe_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_HEAD = ITEMS.register("zweihander_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> KODACHI_HEAD = ITEMS.register("kodachi_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> NODACHI_HEAD = ITEMS.register("nodachi_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> SABRE_HEAD = ITEMS.register("sabre_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_HEAD = ITEMS.register("makhaira_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> SPEAR_HEAD = ITEMS.register("spear_head", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	public static final RegistryObject<Item> LONG_WEAPON_HANDLE = ITEMS.register("long_weapon_handle", () -> new Item(new Item.Properties().tab(CreativeTabs.WEAPON_TAB)));
	
	
	public static final RegistryObject<Item> DAGGER_WOOD = ITEMS.register("dagger_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_IRON = ITEMS.register("dagger_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_KOBOLD = ITEMS.register("dagger_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_COPPER = ITEMS.register("dagger_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_SILVER = ITEMS.register("dagger_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_BRONZE = ITEMS.register("dagger_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_PLATINUM = ITEMS.register("dagger_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_STEEL = ITEMS.register("dagger_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_SHADOW_PLATINUM = ITEMS.register("dagger_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_FROST_STEEL = ITEMS.register("dagger_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_OBSIDIAN = ITEMS.register("dagger_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_CRYSTALLITE = ITEMS.register("dagger_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> DAGGER_DUSKSTEEL = ITEMS.register("dagger_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.DAGGER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> KABUTOWARI_WOOD = ITEMS.register("kabutowari_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_IRON = ITEMS.register("kabutowari_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_KOBOLD = ITEMS.register("kabutowari_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_COPPER = ITEMS.register("kabutowari_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_SILVER = ITEMS.register("kabutowari_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_BRONZE = ITEMS.register("kabutowari_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_PLATINUM = ITEMS.register("kabutowari_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_STEEL = ITEMS.register("kabutowari_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_SHADOW_PLATINUM = ITEMS.register("kabutowari_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_FROST_STEEL = ITEMS.register("kabutowari_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_OBSIDIAN = ITEMS.register("kabutowari_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_CRYSTALLITE = ITEMS.register("kabutowari_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KABUTOWARI_DUSKSTEEL = ITEMS.register("kabutowari_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.KABUTOWARI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> RAPIER_WOOD = ITEMS.register("rapier_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_IRON = ITEMS.register("rapier_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_KOBOLD = ITEMS.register("rapier_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_COPPER = ITEMS.register("rapier_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_SILVER = ITEMS.register("rapier_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_BRONZE = ITEMS.register("rapier_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_PLATINUM = ITEMS.register("rapier_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_STEEL = ITEMS.register("rapier_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_SHADOW_PLATINUM = ITEMS.register("rapier_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_FROST_STEEL = ITEMS.register("rapier_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_OBSIDIAN = ITEMS.register("rapier_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_CRYSTALLITE = ITEMS.register("rapier_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> RAPIER_DUSKSTEEL = ITEMS.register("rapier_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.RAPIER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> TALWAR_WOOD = ITEMS.register("talwar_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_IRON = ITEMS.register("talwar_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_KOBOLD = ITEMS.register("talwar_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_COPPER = ITEMS.register("talwar_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_SILVER = ITEMS.register("talwar_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_BRONZE = ITEMS.register("talwar_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_PLATINUM = ITEMS.register("talwar_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_STEEL = ITEMS.register("talwar_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_SHADOW_PLATINUM = ITEMS.register("talwar_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_FROST_STEEL = ITEMS.register("talwar_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_OBSIDIAN = ITEMS.register("talwar_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_CRYSTALLITE = ITEMS.register("talwar_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> TALWAR_DUSKSTEEL = ITEMS.register("talwar_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.TALWAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> CLEAVER_WOOD = ITEMS.register("cleaver_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_IRON = ITEMS.register("cleaver_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_KOBOLD = ITEMS.register("cleaver_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_COPPER = ITEMS.register("cleaver_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_SILVER = ITEMS.register("cleaver_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_BRONZE = ITEMS.register("cleaver_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_PLATINUM = ITEMS.register("cleaver_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_STEEL = ITEMS.register("cleaver_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_SHADOW_PLATINUM = ITEMS.register("cleaver_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_FROST_STEEL = ITEMS.register("cleaver_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_OBSIDIAN = ITEMS.register("cleaver_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_CRYSTALLITE = ITEMS.register("cleaver_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> CLEAVER_DUSKSTEEL = ITEMS.register("cleaver_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.CLEAVER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> MACE_WOOD = ITEMS.register("mace_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_IRON = ITEMS.register("mace_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_KOBOLD = ITEMS.register("mace_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_COPPER = ITEMS.register("mace_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_SILVER = ITEMS.register("mace_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_BRONZE = ITEMS.register("mace_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_PLATINUM = ITEMS.register("mace_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_STEEL = ITEMS.register("mace_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_SHADOW_PLATINUM = ITEMS.register("mace_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_FROST_STEEL = ITEMS.register("mace_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_OBSIDIAN = ITEMS.register("mace_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_CRYSTALLITE = ITEMS.register("mace_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MACE_DUSKSTEEL = ITEMS.register("mace_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.MACE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> STAFF_WOOD = ITEMS.register("staff_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_IRON = ITEMS.register("staff_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_KOBOLD = ITEMS.register("staff_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_COPPER = ITEMS.register("staff_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_SILVER = ITEMS.register("staff_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_BRONZE = ITEMS.register("staff_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_PLATINUM = ITEMS.register("staff_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_STEEL = ITEMS.register("staff_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_SHADOW_PLATINUM = ITEMS.register("staff_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_FROST_STEEL = ITEMS.register("staff_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_OBSIDIAN = ITEMS.register("staff_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_CRYSTALLITE = ITEMS.register("staff_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> STAFF_DUSKSTEEL = ITEMS.register("staff_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.STAFF, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> LONGSWORD_WOOD = ITEMS.register("longsword_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_IRON = ITEMS.register("longsword_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_KOBOLD = ITEMS.register("longsword_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_COPPER = ITEMS.register("longsword_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_SILVER = ITEMS.register("longsword_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_BRONZE = ITEMS.register("longsword_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_PLATINUM = ITEMS.register("longsword_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_STEEL = ITEMS.register("longsword_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_SHADOW_PLATINUM = ITEMS.register("longsword_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_FROST_STEEL = ITEMS.register("longsword_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_OBSIDIAN = ITEMS.register("longsword_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_CRYSTALLITE = ITEMS.register("longsword_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> LONGSWORD_DUSKSTEEL = ITEMS.register("longsword_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> KODACHI_WOOD = ITEMS.register("kodachi_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_IRON = ITEMS.register("kodachi_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_KOBOLD = ITEMS.register("kodachi_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_COPPER = ITEMS.register("kodachi_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_SILVER = ITEMS.register("kodachi_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_BRONZE = ITEMS.register("kodachi_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_PLATINUM = ITEMS.register("kodachi_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_STEEL = ITEMS.register("kodachi_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_SHADOW_PLATINUM = ITEMS.register("kodachi_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_FROST_STEEL = ITEMS.register("kodachi_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_OBSIDIAN = ITEMS.register("kodachi_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_CRYSTALLITE = ITEMS.register("kodachi_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> KODACHI_DUSKSTEEL = ITEMS.register("kodachi_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.KODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> NODACHI_WOOD = ITEMS.register("nodachi_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_IRON = ITEMS.register("nodachi_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_KOBOLD = ITEMS.register("nodachi_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_COPPER = ITEMS.register("nodachi_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_SILVER = ITEMS.register("nodachi_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_BRONZE = ITEMS.register("nodachi_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_PLATINUM = ITEMS.register("nodachi_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_STEEL = ITEMS.register("nodachi_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_SHADOW_PLATINUM = ITEMS.register("nodachi_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_FROST_STEEL = ITEMS.register("nodachi_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_OBSIDIAN = ITEMS.register("nodachi_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_CRYSTALLITE = ITEMS.register("nodachi_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> NODACHI_DUSKSTEEL = ITEMS.register("nodachi_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.NODACHI, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> BATTLEAXE_WOOD = ITEMS.register("battleaxe_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_IRON = ITEMS.register("battleaxe_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_KOBOLD = ITEMS.register("battleaxe_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_COPPER = ITEMS.register("battleaxe_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_SILVER = ITEMS.register("battleaxe_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_BRONZE = ITEMS.register("battleaxe_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_PLATINUM = ITEMS.register("battleaxe_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_STEEL = ITEMS.register("battleaxe_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_SHADOW_PLATINUM = ITEMS.register("battleaxe_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_FROST_STEEL = ITEMS.register("battleaxe_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_OBSIDIAN = ITEMS.register("battleaxe_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_CRYSTALLITE = ITEMS.register("battleaxe_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> BATTLEAXE_DUSKSTEEL = ITEMS.register("battleaxe_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.BATTLEAXE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> ZWEIHANDER_WOOD = ITEMS.register("zweihander_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_IRON = ITEMS.register("zweihander_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_KOBOLD = ITEMS.register("zweihander_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_COPPER = ITEMS.register("zweihander_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_SILVER = ITEMS.register("zweihander_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_BRONZE = ITEMS.register("zweihander_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_PLATINUM = ITEMS.register("zweihander_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_STEEL = ITEMS.register("zweihander_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_SHADOW_PLATINUM = ITEMS.register("zweihander_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_FROST_STEEL = ITEMS.register("zweihander_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_OBSIDIAN = ITEMS.register("zweihander_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_CRYSTALLITE = ITEMS.register("zweihander_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> ZWEIHANDER_DUSKSTEEL = ITEMS.register("zweihander_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.ZWEIHANDER, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> SABRE_WOOD = ITEMS.register("sabre_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_IRON = ITEMS.register("sabre_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_KOBOLD = ITEMS.register("sabre_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_COPPER = ITEMS.register("sabre_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_SILVER = ITEMS.register("sabre_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_BRONZE = ITEMS.register("sabre_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_PLATINUM = ITEMS.register("sabre_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_STEEL = ITEMS.register("sabre_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_SHADOW_PLATINUM = ITEMS.register("sabre_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_FROST_STEEL = ITEMS.register("sabre_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_OBSIDIAN = ITEMS.register("sabre_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_CRYSTALLITE = ITEMS.register("sabre_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SABRE_DUSKSTEEL = ITEMS.register("sabre_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.SABRE, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> MAKHAIRA_WOOD = ITEMS.register("makhaira_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_IRON = ITEMS.register("makhaira_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_KOBOLD = ITEMS.register("makhaira_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_COPPER = ITEMS.register("makhaira_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_SILVER = ITEMS.register("makhaira_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_BRONZE = ITEMS.register("makhaira_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_PLATINUM = ITEMS.register("makhaira_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_STEEL = ITEMS.register("makhaira_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_SHADOW_PLATINUM = ITEMS.register("makhaira_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_FROST_STEEL = ITEMS.register("makhaira_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_OBSIDIAN = ITEMS.register("makhaira_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_CRYSTALLITE = ITEMS.register("makhaira_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> MAKHAIRA_DUSKSTEEL = ITEMS.register("makhaira_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.MAKHAIRA, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	public static final RegistryObject<Item> SPEAR_WOOD = ITEMS.register("spear_wood", () -> new AlwWeapon(Tiers.WOOD, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_IRON = ITEMS.register("spear_iron", () -> new AlwWeapon(Tiers.IRON, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_KOBOLD = ITEMS.register("spear_kobold", () -> new AlwWeapon(MAT_KOBOLD, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_COPPER = ITEMS.register("spear_copper", () -> new AlwWeapon(MAT_COPPER, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_SILVER = ITEMS.register("spear_silver", () -> new AlwWeapon(MAT_SILVER, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_BRONZE = ITEMS.register("spear_bronze", () -> new AlwWeapon(MAT_BRONZE, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_PLATINUM = ITEMS.register("spear_platinum", () -> new AlwWeapon(MAT_PLATINUM, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_STEEL = ITEMS.register("spear_steel", () -> new AlwWeapon(MAT_STEEL, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_SHADOW_PLATINUM = ITEMS.register("spear_shadow_platinum", () -> new AlwWeapon(MAT_SHADOW_PLATINUM, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_FROST_STEEL = ITEMS.register("spear_frost_steel", () -> new AlwWeapon(MAT_FROST_STEEL, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_OBSIDIAN = ITEMS.register("spear_obsidian", () -> new AlwWeapon(MAT_OBSIDIAN, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_CRYSTALLITE = ITEMS.register("spear_crystallite", () -> new AlwWeapon(MAT_CRYSTALLITE, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	public static final RegistryObject<Item> SPEAR_DUSKSTEEL = ITEMS.register("spear_dusksteel", () -> new AlwWeapon(MAT_DUSKSTEEL, WeaponAttributes.SPEAR, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
	
	public static final RegistryObject<Item> HOT_TOOL_HEAD = ITEMS.register("hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	
	public static final RegistryObject<Item> HOT_TOOL_ROD = ITEMS.register("hot_tool_rod", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> HOT_TOOL_ROD_2 = ITEMS.register("hot_tool_rod_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> LONG_TOOL_ROD = ITEMS.register("long_tool_rod", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> DAGGER_HOT_TOOL_HEAD = ITEMS.register("dagger_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> DAGGER_HOT_TOOL_HEAD_2 = ITEMS.register("dagger_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> KABUTOWARI_HOT_TOOL_HEAD = ITEMS.register("kabutowari_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> KABUTOWARI_HOT_TOOL_HEAD_2 = ITEMS.register("kabutowari_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> KABUTOWARI_HOT_TOOL_HEAD_3 = ITEMS.register("kabutowari_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> KABUTOWARI_HOT_TOOL_HEAD_4 = ITEMS.register("kabutowari_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> KABUTOWARI_HOT_TOOL_HEAD_5 = ITEMS.register("kabutowari_hot_tool_head_5", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> TALWAR_HOT_TOOL_HEAD = ITEMS.register("talwar_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> TALWAR_HOT_TOOL_HEAD_2 = ITEMS.register("talwar_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> TALWAR_HOT_TOOL_HEAD_3 = ITEMS.register("talwar_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> RAPIER_HOT_TOOL_HEAD = ITEMS.register("rapier_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> RAPIER_HOT_TOOL_HEAD_2 = ITEMS.register("rapier_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> RAPIER_HOT_TOOL_HEAD_3 = ITEMS.register("rapier_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> RAPIER_HOT_TOOL_HEAD_4 = ITEMS.register("rapier_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> CLEAVER_HOT_TOOL_HEAD = ITEMS.register("cleaver_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> MACE_HOT_TOOL_HEAD = ITEMS.register("mace_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> MACE_HOT_TOOL_HEAD_2 = ITEMS.register("mace_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> MACE_HOT_TOOL_HEAD_3 = ITEMS.register("mace_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> STAFF_HOT_TOOL_HEAD = ITEMS.register("staff_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> STAFF_HOT_TOOL_HEAD_2 = ITEMS.register("staff_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> STAFF_HOT_TOOL_HEAD_3 = ITEMS.register("staff_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> STAFF_HOT_TOOL_HEAD_4 = ITEMS.register("staff_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> STAFF_HOT_TOOL_HEAD_5 = ITEMS.register("staff_hot_tool_head_5", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> LONGSWORD_HOT_TOOL_HEAD = ITEMS.register("longsword_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> LONGSWORD_HOT_TOOL_HEAD_2 = ITEMS.register("longsword_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> LONGSWORD_HOT_TOOL_HEAD_3 = ITEMS.register("longsword_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> LONGSWORD_HOT_TOOL_HEAD_4 = ITEMS.register("longsword_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> BATTLEAXE_HOT_TOOL_HEAD = ITEMS.register("battleaxe_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> BATTLEAXE_HOT_TOOL_HEAD_2 = ITEMS.register("battleaxe_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> BATTLEAXE_HOT_TOOL_HEAD_3 = ITEMS.register("battleaxe_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> BATTLEAXE_HOT_TOOL_HEAD_4 = ITEMS.register("battleaxe_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> BATTLEAXE_HOT_TOOL_HEAD_5 = ITEMS.register("battleaxe_hot_tool_head_5", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> ZWEIHANDER_HOT_TOOL_HEAD = ITEMS.register("zweihander_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> ZWEIHANDER_HOT_TOOL_HEAD_2 = ITEMS.register("zweihander_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> ZWEIHANDER_HOT_TOOL_HEAD_3 = ITEMS.register("zweihander_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> ZWEIHANDER_HOT_TOOL_HEAD_4 = ITEMS.register("zweihander_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> ZWEIHANDER_HOT_TOOL_HEAD_5 = ITEMS.register("zweihander_hot_tool_head_5", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> KODACHI_HOT_TOOL_HEAD = ITEMS.register("kodachi_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> KODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("kodachi_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> NODACHI_HOT_TOOL_HEAD = ITEMS.register("nodachi_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> NODACHI_HOT_TOOL_HEAD_2 = ITEMS.register("nodachi_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> NODACHI_HOT_TOOL_HEAD_3 = ITEMS.register("nodachi_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> NODACHI_HOT_TOOL_HEAD_4 = ITEMS.register("nodachi_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> SABRE_HOT_TOOL_HEAD = ITEMS.register("sabre_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> SABRE_HOT_TOOL_HEAD_2 = ITEMS.register("sabre_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> SABRE_HOT_TOOL_HEAD_3 = ITEMS.register("sabre_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> SABRE_HOT_TOOL_HEAD_4 = ITEMS.register("sabre_hot_tool_head_4", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> MAKHAIRA_HOT_TOOL_HEAD = ITEMS.register("makhaira_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> MAKHAIRA_HOT_TOOL_HEAD_2 = ITEMS.register("makhaira_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> MAKHAIRA_HOT_TOOL_HEAD_3 = ITEMS.register("makhaira_hot_tool_head_3", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static final RegistryObject<Item> SPEAR_HOT_TOOL_HEAD = ITEMS.register("spear_hot_tool_head", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());
	public static final RegistryObject<Item> SPEAR_HOT_TOOL_HEAD_2 = ITEMS.register("spear_hot_tool_head_2", () -> new ItemHotToolHead(new Item.Properties().tab(CreativeTabs.WEAPON_TAB).durability(6000).setNoRepair()).addToRegistryMap());

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
