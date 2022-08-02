package com.tiki.advancedlootableweapons.init;

import java.util.HashMap;
import java.util.Map;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.weapons.SlashSword;
import com.tiki.advancedlootableweapons.items.weapons.WeaponAttributes;

import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.ID);
	public static final Map<Item, ItemPropertyFunction> toolHeadMap = new HashMap<Item, ItemPropertyFunction>();
	
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
	
	public static final RegistryObject<Item> LONGSWORD_WOOD = ITEMS.register("longsword_wood", () -> new SlashSword(Tiers.WOOD, WeaponAttributes.LONGSWORD, new Item.Properties().tab(CreativeTabs.MISC_TAB)));
	
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
