package com.tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.items.ItemArmorBinding;
import com.tiki.advancedlootableweapons.items.ItemArmorPlate;
import com.tiki.advancedlootableweapons.items.ItemBase;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;
import com.tiki.advancedlootableweapons.items.ItemUnboundArmor;
import com.tiki.advancedlootableweapons.recipes.RemoveRecipe;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolSpear;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;
import com.tiki.advancedlootableweapons.tools.ToolTanningKnife;
import com.tiki.advancedlootableweapons.util.ArmorTypes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class ItemInit {

	public static final List<Item> items = new ArrayList<Item>();
	public static final HashSet<Item> generatedItems = new HashSet<Item>();
	public static final HashSet<Item> weaponItems = new HashSet<Item>();
	public static final Set<Item> acceptedForgeItems = new HashSet<Item>();
	public static final HashMap<ToolMaterial, ItemStack> customRepairItems = new HashMap<ToolMaterial, ItemStack>();
	
	public static void generateAcceptedForgeItems() {
		String[] ores = new String[] {"ingotBronze", "ingotCopper", "ingotPlatinum", "ingotSteel", "ingotRefinedObsidian", "ingotSilver", "ingotIron", "ingotGold"};
		for(String ore : ores) {
			NonNullList<ItemStack> stacks = OreDictionary.getOres(ore);
			for(ItemStack s : stacks) {
				if(!s.isEmpty()) {
					acceptedForgeItems.add(s.getItem());
				}
			}
		}
		acceptedForgeItems.add(INGOT_KOBOLD);
		acceptedForgeItems.add(INGOT_CRYSTALLITE);
		acceptedForgeItems.add(INGOT_DUSKSTEEL);
		acceptedForgeItems.add(INGOT_FROST_STEEL);
		acceptedForgeItems.add(INGOT_SHADOW_PLATINUM);
	}
	
	public static void checkConfigOptions() {
		
		IForgeRegistryModifiable<IRecipe> recipes = (IForgeRegistryModifiable<IRecipe>)ForgeRegistries.RECIPES;
		
		for(String s : ConfigHandler.EXTRA_MATERIALS) {
			List<ToolMaterial> addedMats = new ArrayList<ToolMaterial>();
			addedMats.add(ToolMaterial.WOOD);
			addedMats.add(ToolMaterial.IRON);
			addedMats.add(MAT_KOBOLD);
			addedMats.add(MAT_COPPER);
			addedMats.add(MAT_SILVER);
			addedMats.add(MAT_BRONZE);
			addedMats.add(MAT_PLATINUM);
			addedMats.add(MAT_STEEL);
			addedMats.add(MAT_SHADOW_PLATINUM);
			addedMats.add(MAT_FROST_STEEL);
			addedMats.add(MAT_OBSIDIAN);
			addedMats.add(MAT_CRYSTALLITE);
			addedMats.add(MAT_DUSKSTEEL);
			if(s.contains(",")) {
				if(s.contains("#")) {
					customRepairItems.put(ToolMaterial.valueOf(s.substring(0, s.indexOf(','))), new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s.substring(s.indexOf(',') + 1, s.indexOf('#')))), 1, Integer.parseInt(s.substring(s.indexOf('#') + 1))));
				}else {
					customRepairItems.put(ToolMaterial.valueOf(s.substring(0, s.indexOf(','))), new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s.substring(s.indexOf(','))))));
				}
				s = s.substring(0, s.indexOf(','));
			}
			
			try {
				//Alw.logger.info("Adding Extra Material " + s + ", Tool Material: " + ToolMaterial.valueOf(s));
				if(ToolMaterial.valueOf(s) != null && !addedMats.contains(ToolMaterial.valueOf(s))) {
					generatedItems.add(new ToolStabSword("dagger_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "dagger").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("kabutowari_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "kabutowari").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("rapier_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "rapier").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("talwar_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "talwar").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("cleaver_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "cleaver").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("mace_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "mace").setMaxStackSize(1));
					generatedItems.add(new ToolStabSword("staff_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "staff").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("longsword_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "longsword").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("kodachi_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "kodachi").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("battleaxe_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "battleaxe").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("zweihander_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "zweihander").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("nodachi_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "nodachi").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("sabre_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "sabre").setMaxStackSize(1));
					generatedItems.add(new ToolSlashSword("makhaira_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s), "makhaira").setMaxStackSize(1));
					generatedItems.add(new ToolSpear("spear_" + s.replace(':', '_').toLowerCase(), ToolMaterial.valueOf(s)).setMaxStackSize(1));
					
					acceptedForgeItems.add(ToolMaterial.valueOf(s).getRepairItemStack().getItem());
					addedMats.add(ToolMaterial.valueOf(s));
				}
			}catch (IllegalArgumentException e) {
				//Alw.logger.error("Tried to add extra material " + s + " which does not exist. Valid materials are: " + Arrays.toString(ToolMaterial.values()));
				Alw.logger.error("Tried to add extra material " + s + ", but it does not currently exist.");
			}
		}
		
		if(ConfigHandler.ENABLE_ADVANCED_LEATHER_TANNING) {
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "tanning_leather_simple"));
		}else {
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "tanning_leather_advanced"));
		}
		
		if(ConfigHandler.DISABLE_VANILLA_ARMORS) {
			removeRecipe(recipes, new ResourceLocation("minecraft", "leather_helmet"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "leather_chestplate"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "leather_leggings"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "leather_boots"));
			
			removeRecipe(recipes, new ResourceLocation("minecraft", "iron_helmet"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "iron_chestplate"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "iron_leggings"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "iron_boots"));
			
			removeRecipe(recipes, new ResourceLocation("minecraft", "golden_helmet"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "golden_chestplate"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "golden_leggings"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "golden_boots"));
			
			removeRecipe(recipes, new ResourceLocation("minecraft", "diamond_helmet"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "diamond_chestplate"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "diamond_leggings"));
			removeRecipe(recipes, new ResourceLocation("minecraft", "diamond_boots"));
		}
		
		if(!ConfigHandler.ENABLE_ARMORS) {
			ConfigHandler.ENABLE_ARMOR_FORGING = false;
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_bronze"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_copper"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_crystallite"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_diamond"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_dusksteel"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_frost_steel"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_gold"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_iron"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_kobold"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_obsidian"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_platinum"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_shadow_platinum"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_silver"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_helmet_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_chestplate_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_leggings_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chain_boots_steel"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_bronze"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_bronze"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_copper"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_copper"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_crystallite"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_crystallite"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_diamond"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_diamond"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_dusksteel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_dusksteel"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_frost_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_frost_steel"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_gold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_gold"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_iron"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_iron"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_kobold"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_kobold"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_obsidian"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_obsidian"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_platinum"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_shadow_platinum"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_shadow_platinum"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_silver"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_silver"));
			
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_helmet_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_chestplate_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_leggings_steel"));
			removeRecipe(recipes, new ResourceLocation(ModInfo.ID, "unbound_boots_steel"));
		}
	}
	
	public static void removeRecipe(IForgeRegistryModifiable<IRecipe> registry, ResourceLocation recipeLoc) {
		IRecipe recipe = (IRecipe)registry.getValue(recipeLoc);
		registry.remove(recipeLoc);
		if(recipe != null) {
			registry.register(RemoveRecipe.from(recipe));
		}
	}
	
	public static void createRecipes() {
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_dagger_head"), new ResourceLocation("dagger_head"), new ItemStack(DAGGER_HEAD), new Object[] {" w", "w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_kabutowari_head"), new ResourceLocation("kabutowari_head"), new ItemStack(KABUTOWARI_HEAD), new Object[] {"w  ", "w w", " w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_rapier_head"), new ResourceLocation("rapier_head"), new ItemStack(RAPIER_HEAD), new Object[] {"w  ", " w ", " ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_talwar_head"), new ResourceLocation("talwar_head"), new ItemStack(TALWAR_HEAD), new Object[] {" w", "w ", " w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_mace_head"), new ResourceLocation("mace_head"), new ItemStack(MACE_HEAD), new Object[] {"w w", " w ", "w w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_cleaver_head"), new ResourceLocation("cleaver_head"), new ItemStack(CLEAVER_HEAD), new Object[] {"w ", "ww", "ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_staff_head"), new ResourceLocation("staff_head"), new ItemStack(STAFF_HEAD, 2), new Object[] {"w  ", "ww ", " ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_longsword_head"), new ResourceLocation("longsword_head"), new ItemStack(LONGSWORD_HEAD), new Object[] {"  w", " w ", "w  ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_battleaxe_head"), new ResourceLocation("battleaxe_head"), new ItemStack(BATTLEAXE_HEAD), new Object[] {"w w", "www", "w w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_zweihander_head"), new ResourceLocation("zweihander_head"), new ItemStack(ZWEIHANDER_HEAD), new Object[] {" w ", " w ", "www", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_kodachi_head"), new ResourceLocation("kodachi_head"), new ItemStack(KODACHI_HEAD), new Object[] {"w ", " w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_nodachi_head"), new ResourceLocation("nodachi_head"), new ItemStack(NODACHI_HEAD), new Object[] {"w", "w", "w", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_sabre_head"), new ResourceLocation("sabre_head"), new ItemStack(SABRE_HEAD), new Object[] {" w", "w ", "w ", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_makhaira_head"), new ResourceLocation("makhaira_head"), new ItemStack(MAKHAIRA_HEAD), new Object[] {" w", "ww", "ww", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_spear_head"), new ResourceLocation("spear_head"), new ItemStack(SPEAR_HEAD), new Object[] {" w ", "www", Character.valueOf('w'), new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE)});
		
		if(ConfigHandler.ENABLE_ARMORS && !ConfigHandler.ENABLE_ARMOR_FORGING) {
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_kobold"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_KOBOLD), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_KOBOLD)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_copper"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_COPPER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_COPPER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_silver"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_SILVER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SILVER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_bronze"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_BRONZE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_BRONZE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_platinum"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_steel"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_shadow_platinum"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_SHADOW_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SHADOW_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_frost_steel"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_FROST_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_FROST_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_crystallite"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_CRYSTALLITE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_CRYSTALLITE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_helmet_dusksteel"), new ResourceLocation("armor_helmet"), new ItemStack(PLATE_HELMET_DUSKSTEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_DUSKSTEEL)});
			
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_kobold"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_KOBOLD), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_KOBOLD)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_copper"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_COPPER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_COPPER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_silver"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_SILVER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SILVER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_bronze"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_BRONZE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_BRONZE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_platinum"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_steel"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_shadow_platinum"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_SHADOW_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SHADOW_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_frost_steel"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_FROST_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_FROST_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_crystallite"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_CRYSTALLITE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_CRYSTALLITE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_chestplate_dusksteel"), new ResourceLocation("armor_chestplate"), new ItemStack(PLATE_CHESTPLATE_DUSKSTEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_DUSKSTEEL)});
			
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_kobold"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_KOBOLD), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_KOBOLD)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_copper"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_COPPER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_COPPER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_silver"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_SILVER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SILVER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_bronze"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_BRONZE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_BRONZE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_platinum"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_steel"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_shadow_platinum"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_SHADOW_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SHADOW_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_frost_steel"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_FROST_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_FROST_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_crystallite"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_CRYSTALLITE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_CRYSTALLITE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_leggings_dusksteel"), new ResourceLocation("armor_leggings"), new ItemStack(PLATE_LEGGINGS_DUSKSTEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_DUSKSTEEL)});
			
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_kobold"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_KOBOLD), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_KOBOLD)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_copper"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_COPPER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_COPPER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_silver"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_SILVER), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SILVER)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_bronze"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_BRONZE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_BRONZE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_platinum"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_steel"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_shadow_platinum"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_SHADOW_PLATINUM), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_SHADOW_PLATINUM)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_frost_steel"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_FROST_STEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_FROST_STEEL)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_crystallite"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_CRYSTALLITE), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_CRYSTALLITE)});
			GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID + ":recipe_boots_dusksteel"), new ResourceLocation("armor_boots"), new ItemStack(PLATE_BOOTS_DUSKSTEEL), new Object[] {"i i", "i i", Character.valueOf('i'), new ItemStack(INGOT_DUSKSTEEL)});
		}
		
		GameRegistry.addSmelting(BlockInit.cobble_feldspar, new ItemStack(BlockInit.rock_feldspar), 0.1F);
		GameRegistry.addSmelting(BlockInit.ore_copper, new ItemStack(ItemInit.INGOT_COPPER), 1.0F);
		GameRegistry.addSmelting(BlockInit.ore_tin, new ItemStack(ItemInit.INGOT_TIN), 1.0F);
		GameRegistry.addSmelting(BlockInit.ore_silver, new ItemStack(ItemInit.INGOT_SILVER), 1.0F);
		GameRegistry.addSmelting(BlockInit.ore_platinum, new ItemStack(ItemInit.INGOT_PLATINUM), 1.0F);
		GameRegistry.addSmelting(ItemInit.CLAY_GRANITE, new ItemStack(ItemInit.BRICK_GRANITE), 1.0F);
		GameRegistry.addSmelting(ItemInit.CLAY_DIORITE, new ItemStack(ItemInit.BRICK_DIORITE), 1.0F);
		GameRegistry.addSmelting(BlockInit.clay_granite, new ItemStack(BlockInit.brick_granite), 1.0F);
		GameRegistry.addSmelting(BlockInit.clay_diorite, new ItemStack(BlockInit.brick_diorite), 1.0F);
	}
	
	//Items
	public static final Item INGOT_TIN = new ItemBase("ingot_tin");
	public static final Item INGOT_KOBOLD = new ItemBase("ingot_kobold");
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_SILVER = new ItemBase("ingot_silver");
	public static final Item INGOT_BRONZE = new ItemBase("ingot_bronze");
	public static final Item INGOT_PLATINUM = new ItemBase("ingot_platinum");
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel");
	public static final Item INGOT_SHADOW_PLATINUM = new ItemBase("ingot_shadow_platinum");
	public static final Item INGOT_FROST_STEEL = new ItemBase("ingot_frost_steel");
	public static final Item INGOT_OBSIDIAN = new ItemBase("ingot_obsidian");
	public static final Item INGOT_CRYSTALLITE = new ItemBase("ingot_crystallite");
	public static final Item INGOT_DUSKSTEEL = new ItemBase("ingot_dusksteel");
	
	public static final Item SHARD_OBSIDIAN = new ItemBase("shard_obsidian");
	public static final Item NUGGET_TIN = new ItemBase("nugget_tin");
	public static final Item NUGGET_KOBOLD = new ItemBase("nugget_kobold");
	public static final Item NUGGET_COPPER = new ItemBase("nugget_copper");
	public static final Item NUGGET_SILVER = new ItemBase("nugget_silver");
	public static final Item NUGGET_BRONZE = new ItemBase("nugget_bronze");
	public static final Item NUGGET_PLATINUM = new ItemBase("nugget_platinum");
	public static final Item NUGGET_STEEL = new ItemBase("nugget_steel");
	public static final Item NUGGET_SHADOW_PLATINUM = new ItemBase("nugget_shadow_platinum");
	public static final Item NUGGET_FROST_STEEL = new ItemBase("nugget_frost_steel");
	public static final Item NUGGET_OBSIDIAN = new ItemBase("nugget_obsidian");
	public static final Item NUGGET_CRYSTALLITE = new ItemBase("nugget_crystallite");
	public static final Item NUGGET_DUSKSTEEL = new ItemBase("nugget_dusksteel");
	
	public static final Item POWDER_FELDSPAR = new ItemBase("powder_feldspar");
	public static final Item POWDER_GRANITE = new ItemBase("powder_granite");
	public static final Item POWDER_DIORITE = new ItemBase("powder_diorite");
	public static final Item POWDER_CHARCOAL = new ItemBase("powder_charcoal");
	public static final Item CLAY_GRANITE = new ItemBase("clay_granite");
	public static final Item CLAY_DIORITE = new ItemBase("clay_diorite");
	public static final Item CLAY_YAKI_IRE_MIX = new ItemBase("clay_quenching_mix");
	public static final Item CLAY_YAKI_IRE_SLIP = new ItemBase("clay_quenching_slip");
	
	public static final Item BRICK_GRANITE = new ItemBase("brick_granite");
	public static final Item BRICK_DIORITE = new ItemBase("brick_diorite");
	
	public static final Item CRYSTAL = new ItemBase("crystal");
	public static final Item SHADOW = new ItemBase("shadow");
	public static final Item SHADOW_BLOB = new ItemBase("shadow_blob");
	public static final Item UNTRIMMED_HIDE = new ItemBase("untrimmed_hide");
	public static final Item TRIMMED_HIDE = new ItemBase("trimmed_hide");
	public static final Item CURED_HIDE = new ItemBase("cured_hide");
	public static final Item LIMED_HIDE = new ItemBase("limed_hide");
	public static final Item DELIMED_HIDE = new ItemBase("delimed_hide");
	public static final Item TANNING_KNIFE = new ToolTanningKnife("tanning_knife", ToolMaterial.IRON);
	public static final Item LEATHER_STRIP = new ItemBase("leather_strip");
	public static final Item DIAMOND_STUDDED_LEATHER = new ItemBase("diamond_studded_leather");
	public static final Item RAW_SALT = new ItemBase("raw_salt");
	
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
	
	public static final Item UNBOUND_HELMET_LEATHER = new ItemUnboundArmor("unbound_helmet_leather");
	public static final Item UNBOUND_CHESTPLATE_LEATHER = new ItemUnboundArmor("unbound_chestplate_leather");
	public static final Item UNBOUND_LEGGINGS_LEATHER = new ItemUnboundArmor("unbound_leggings_leather");
	public static final Item UNBOUND_BOOTS_LEATHER = new ItemUnboundArmor("unbound_boots_leather");
	
	public static final Item UNBOUND_CHAIN_HELMET_IRON = new ItemUnboundArmor("unbound_chain_helmet_iron");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_IRON = new ItemUnboundArmor("unbound_chain_chestplate_iron");
	public static final Item UNBOUND_CHAIN_LEGGINGS_IRON = new ItemUnboundArmor("unbound_chain_leggings_iron");
	public static final Item UNBOUND_CHAIN_BOOTS_IRON = new ItemUnboundArmor("unbound_chain_boots_iron");
	
	public static final Item UNBOUND_CHAIN_HELMET_GOLD = new ItemUnboundArmor("unbound_chain_helmet_gold");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_GOLD = new ItemUnboundArmor("unbound_chain_chestplate_gold");
	public static final Item UNBOUND_CHAIN_LEGGINGS_GOLD = new ItemUnboundArmor("unbound_chain_leggings_gold");
	public static final Item UNBOUND_CHAIN_BOOTS_GOLD = new ItemUnboundArmor("unbound_chain_boots_gold");
	
	public static final Item UNBOUND_CHAIN_HELMET_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_leather_helmet");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_leather_chestplate");
	public static final Item UNBOUND_CHAIN_LEGGINGS_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_leather_leggings");
	public static final Item UNBOUND_CHAIN_BOOTS_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_leather_boots");
	
	public static final Item UNBOUND_CHAIN_HELMET_KOBOLD = new ItemUnboundArmor("unbound_chain_helmet_kobold");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_KOBOLD = new ItemUnboundArmor("unbound_chain_chestplate_kobold");
	public static final Item UNBOUND_CHAIN_LEGGINGS_KOBOLD = new ItemUnboundArmor("unbound_chain_leggings_kobold");
	public static final Item UNBOUND_CHAIN_BOOTS_KOBOLD = new ItemUnboundArmor("unbound_chain_boots_kobold");
	
	public static final Item UNBOUND_CHAIN_HELMET_COPPER = new ItemUnboundArmor("unbound_chain_helmet_copper");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_COPPER = new ItemUnboundArmor("unbound_chain_chestplate_copper");
	public static final Item UNBOUND_CHAIN_LEGGINGS_COPPER = new ItemUnboundArmor("unbound_chain_leggings_copper");
	public static final Item UNBOUND_CHAIN_BOOTS_COPPER = new ItemUnboundArmor("unbound_chain_boots_copper");
	
	public static final Item UNBOUND_CHAIN_HELMET_SILVER = new ItemUnboundArmor("unbound_chain_helmet_silver");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_SILVER = new ItemUnboundArmor("unbound_chain_chestplate_silver");
	public static final Item UNBOUND_CHAIN_LEGGINGS_SILVER = new ItemUnboundArmor("unbound_chain_leggings_silver");
	public static final Item UNBOUND_CHAIN_BOOTS_SILVER = new ItemUnboundArmor("unbound_chain_boots_silver");
	
	public static final Item UNBOUND_CHAIN_HELMET_BRONZE = new ItemUnboundArmor("unbound_chain_helmet_bronze");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_BRONZE = new ItemUnboundArmor("unbound_chain_chestplate_bronze");
	public static final Item UNBOUND_CHAIN_LEGGINGS_BRONZE = new ItemUnboundArmor("unbound_chain_leggings_bronze");
	public static final Item UNBOUND_CHAIN_BOOTS_BRONZE = new ItemUnboundArmor("unbound_chain_boots_bronze");
	
	public static final Item UNBOUND_CHAIN_HELMET_PLATINUM = new ItemUnboundArmor("unbound_chain_helmet_platinum");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_PLATINUM = new ItemUnboundArmor("unbound_chain_chestplate_platinum");
	public static final Item UNBOUND_CHAIN_LEGGINGS_PLATINUM = new ItemUnboundArmor("unbound_chain_leggings_platinum");
	public static final Item UNBOUND_CHAIN_BOOTS_PLATINUM = new ItemUnboundArmor("unbound_chain_boots_platinum");
	
	public static final Item UNBOUND_CHAIN_HELMET_STEEL = new ItemUnboundArmor("unbound_chain_helmet_steel");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_STEEL = new ItemUnboundArmor("unbound_chain_chestplate_steel");
	public static final Item UNBOUND_CHAIN_LEGGINGS_STEEL = new ItemUnboundArmor("unbound_chain_leggings_steel");
	public static final Item UNBOUND_CHAIN_BOOTS_STEEL = new ItemUnboundArmor("unbound_chain_boots_steel");
	
	public static final Item UNBOUND_CHAIN_HELMET_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_chain_helmet_shadow_platinum");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_chain_chestplate_shadow_platinum");
	public static final Item UNBOUND_CHAIN_LEGGINGS_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_chain_leggings_shadow_platinum");
	public static final Item UNBOUND_CHAIN_BOOTS_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_chain_boots_shadow_platinum");
	
	public static final Item UNBOUND_CHAIN_HELMET_FROST_STEEL = new ItemUnboundArmor("unbound_chain_helmet_frost_steel");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_FROST_STEEL = new ItemUnboundArmor("unbound_chain_chestplate_frost_steel");
	public static final Item UNBOUND_CHAIN_LEGGINGS_FROST_STEEL = new ItemUnboundArmor("unbound_chain_leggings_frost_steel");
	public static final Item UNBOUND_CHAIN_BOOTS_FROST_STEEL = new ItemUnboundArmor("unbound_chain_boots_frost_steel");
	
	public static final Item UNBOUND_CHAIN_HELMET_OBSIDIAN = new ItemUnboundArmor("unbound_chain_helmet_obsidian");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_OBSIDIAN = new ItemUnboundArmor("unbound_chain_chestplate_obsidian");
	public static final Item UNBOUND_CHAIN_LEGGINGS_OBSIDIAN = new ItemUnboundArmor("unbound_chain_leggings_obsidian");
	public static final Item UNBOUND_CHAIN_BOOTS_OBSIDIAN = new ItemUnboundArmor("unbound_chain_boots_obsidian");
	
	public static final Item UNBOUND_CHAIN_HELMET_CRYSTALLITE = new ItemUnboundArmor("unbound_chain_helmet_crystallite");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_CRYSTALLITE = new ItemUnboundArmor("unbound_chain_chestplate_crystallite");
	public static final Item UNBOUND_CHAIN_LEGGINGS_CRYSTALLITE = new ItemUnboundArmor("unbound_chain_leggings_crystallite");
	public static final Item UNBOUND_CHAIN_BOOTS_CRYSTALLITE = new ItemUnboundArmor("unbound_chain_boots_crystallite");
	
	public static final Item UNBOUND_CHAIN_HELMET_DUSKSTEEL = new ItemUnboundArmor("unbound_chain_helmet_dusksteel");
	public static final Item UNBOUND_CHAIN_CHESTPLATE_DUSKSTEEL = new ItemUnboundArmor("unbound_chain_chestplate_dusksteel");
	public static final Item UNBOUND_CHAIN_LEGGINGS_DUSKSTEEL = new ItemUnboundArmor("unbound_chain_leggings_dusksteel");
	public static final Item UNBOUND_CHAIN_BOOTS_DUSKSTEEL = new ItemUnboundArmor("unbound_chain_boots_dusksteel");
	
	public static final Item UNBOUND_PLATE_HELMET_IRON = new ItemUnboundArmor("unbound_plate_helmet_iron");
	public static final Item UNBOUND_PLATE_CHESTPLATE_IRON = new ItemUnboundArmor("unbound_plate_chestplate_iron");
	public static final Item UNBOUND_PLATE_LEGGINGS_IRON = new ItemUnboundArmor("unbound_plate_leggings_iron");
	public static final Item UNBOUND_PLATE_BOOTS_IRON = new ItemUnboundArmor("unbound_plate_boots_iron");
	
	public static final Item UNBOUND_PLATE_HELMET_GOLD = new ItemUnboundArmor("unbound_plate_helmet_gold");
	public static final Item UNBOUND_PLATE_CHESTPLATE_GOLD = new ItemUnboundArmor("unbound_plate_chestplate_gold");
	public static final Item UNBOUND_PLATE_LEGGINGS_GOLD = new ItemUnboundArmor("unbound_plate_leggings_gold");
	public static final Item UNBOUND_PLATE_BOOTS_GOLD = new ItemUnboundArmor("unbound_plate_boots_gold");
	
	public static final Item UNBOUND_PLATE_HELMET_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_steel_helmet");
	public static final Item UNBOUND_PLATE_CHESTPLATE_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_steel_chestplate");
	public static final Item UNBOUND_PLATE_LEGGINGS_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_steel_leggings");
	public static final Item UNBOUND_PLATE_BOOTS_DIAMOND = new ItemUnboundArmor("unbound_diamond_studded_steel_boots");
	
	public static final Item UNBOUND_PLATE_HELMET_KOBOLD = new ItemUnboundArmor("unbound_plate_helmet_kobold");
	public static final Item UNBOUND_PLATE_CHESTPLATE_KOBOLD = new ItemUnboundArmor("unbound_plate_chestplate_kobold");
	public static final Item UNBOUND_PLATE_LEGGINGS_KOBOLD = new ItemUnboundArmor("unbound_plate_leggings_kobold");
	public static final Item UNBOUND_PLATE_BOOTS_KOBOLD = new ItemUnboundArmor("unbound_plate_boots_kobold");
	
	public static final Item UNBOUND_PLATE_HELMET_COPPER = new ItemUnboundArmor("unbound_plate_helmet_copper");
	public static final Item UNBOUND_PLATE_CHESTPLATE_COPPER = new ItemUnboundArmor("unbound_plate_chestplate_copper");
	public static final Item UNBOUND_PLATE_LEGGINGS_COPPER = new ItemUnboundArmor("unbound_plate_leggings_copper");
	public static final Item UNBOUND_PLATE_BOOTS_COPPER = new ItemUnboundArmor("unbound_plate_boots_copper");
	
	public static final Item UNBOUND_PLATE_HELMET_SILVER = new ItemUnboundArmor("unbound_plate_helmet_silver");
	public static final Item UNBOUND_PLATE_CHESTPLATE_SILVER = new ItemUnboundArmor("unbound_plate_chestplate_silver");
	public static final Item UNBOUND_PLATE_LEGGINGS_SILVER = new ItemUnboundArmor("unbound_plate_leggings_silver");
	public static final Item UNBOUND_PLATE_BOOTS_SILVER = new ItemUnboundArmor("unbound_plate_boots_silver");
	
	public static final Item UNBOUND_PLATE_HELMET_BRONZE = new ItemUnboundArmor("unbound_plate_helmet_bronze");
	public static final Item UNBOUND_PLATE_CHESTPLATE_BRONZE = new ItemUnboundArmor("unbound_plate_chestplate_bronze");
	public static final Item UNBOUND_PLATE_LEGGINGS_BRONZE = new ItemUnboundArmor("unbound_plate_leggings_bronze");
	public static final Item UNBOUND_PLATE_BOOTS_BRONZE = new ItemUnboundArmor("unbound_plate_boots_bronze");
	
	public static final Item UNBOUND_PLATE_HELMET_PLATINUM = new ItemUnboundArmor("unbound_plate_helmet_platinum");
	public static final Item UNBOUND_PLATE_CHESTPLATE_PLATINUM = new ItemUnboundArmor("unbound_plate_chestplate_platinum");
	public static final Item UNBOUND_PLATE_LEGGINGS_PLATINUM = new ItemUnboundArmor("unbound_plate_leggings_platinum");
	public static final Item UNBOUND_PLATE_BOOTS_PLATINUM = new ItemUnboundArmor("unbound_plate_boots_platinum");
	
	public static final Item UNBOUND_PLATE_HELMET_STEEL = new ItemUnboundArmor("unbound_plate_helmet_steel");
	public static final Item UNBOUND_PLATE_CHESTPLATE_STEEL = new ItemUnboundArmor("unbound_plate_chestplate_steel");
	public static final Item UNBOUND_PLATE_LEGGINGS_STEEL = new ItemUnboundArmor("unbound_plate_leggings_steel");
	public static final Item UNBOUND_PLATE_BOOTS_STEEL = new ItemUnboundArmor("unbound_plate_boots_steel");
	
	public static final Item UNBOUND_PLATE_HELMET_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_plate_helmet_shadow_platinum");
	public static final Item UNBOUND_PLATE_CHESTPLATE_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_plate_chestplate_shadow_platinum");
	public static final Item UNBOUND_PLATE_LEGGINGS_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_plate_leggings_shadow_platinum");
	public static final Item UNBOUND_PLATE_BOOTS_SHADOW_PLATINUM = new ItemUnboundArmor("unbound_plate_boots_shadow_platinum");
	
	public static final Item UNBOUND_PLATE_HELMET_FROST_STEEL = new ItemUnboundArmor("unbound_plate_helmet_frost_steel");
	public static final Item UNBOUND_PLATE_CHESTPLATE_FROST_STEEL = new ItemUnboundArmor("unbound_plate_chestplate_frost_steel");
	public static final Item UNBOUND_PLATE_LEGGINGS_FROST_STEEL = new ItemUnboundArmor("unbound_plate_leggings_frost_steel");
	public static final Item UNBOUND_PLATE_BOOTS_FROST_STEEL = new ItemUnboundArmor("unbound_plate_boots_frost_steel");
	
	public static final Item UNBOUND_PLATE_HELMET_OBSIDIAN = new ItemUnboundArmor("unbound_plate_helmet_obsidian");
	public static final Item UNBOUND_PLATE_CHESTPLATE_OBSIDIAN = new ItemUnboundArmor("unbound_plate_chestplate_obsidian");
	public static final Item UNBOUND_PLATE_LEGGINGS_OBSIDIAN = new ItemUnboundArmor("unbound_plate_leggings_obsidian");
	public static final Item UNBOUND_PLATE_BOOTS_OBSIDIAN = new ItemUnboundArmor("unbound_plate_boots_obsidian");
	
	public static final Item UNBOUND_PLATE_HELMET_CRYSTALLITE = new ItemUnboundArmor("unbound_plate_helmet_crystallite");
	public static final Item UNBOUND_PLATE_CHESTPLATE_CRYSTALLITE = new ItemUnboundArmor("unbound_plate_chestplate_crystallite");
	public static final Item UNBOUND_PLATE_LEGGINGS_CRYSTALLITE = new ItemUnboundArmor("unbound_plate_leggings_crystallite");
	public static final Item UNBOUND_PLATE_BOOTS_CRYSTALLITE = new ItemUnboundArmor("unbound_plate_boots_crystallite");
	
	public static final Item UNBOUND_PLATE_HELMET_DUSKSTEEL = new ItemUnboundArmor("unbound_plate_helmet_dusksteel");
	public static final Item UNBOUND_PLATE_CHESTPLATE_DUSKSTEEL = new ItemUnboundArmor("unbound_plate_chestplate_dusksteel");
	public static final Item UNBOUND_PLATE_LEGGINGS_DUSKSTEEL = new ItemUnboundArmor("unbound_plate_leggings_dusksteel");
	public static final Item UNBOUND_PLATE_BOOTS_DUSKSTEEL = new ItemUnboundArmor("unbound_plate_boots_dusksteel");
	
	
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
	public static final ToolMaterial MAT_KOBOLD = EnumHelper.addToolMaterial("alw:kobold", 1, ConfigHandler.KOBOLD_DURABILITY, 6.0F, ConfigHandler.KOBOLD_DAMAGE, 22).setRepairItem(new ItemStack(INGOT_KOBOLD));;
	public static final ToolMaterial MAT_COPPER = EnumHelper.addToolMaterial("alw:copper", 2, ConfigHandler.COPPER_DURABILITY, 6.5F, ConfigHandler.COPPER_DAMAGE, 10).setRepairItem(new ItemStack(INGOT_COPPER));
	public static final ToolMaterial MAT_SILVER = EnumHelper.addToolMaterial("alw:silver", 2, ConfigHandler.SILVER_DURABILITY, 7.0F, ConfigHandler.SILVER_DAMAGE, 24).setRepairItem(new ItemStack(INGOT_SILVER));
	public static final ToolMaterial MAT_BRONZE = EnumHelper.addToolMaterial("alw:bronze", 2, ConfigHandler.BRONZE_DURABILITY, 6.5F, ConfigHandler.BRONZE_DAMAGE, 12).setRepairItem(new ItemStack(INGOT_BRONZE));
	public static final ToolMaterial MAT_PLATINUM = EnumHelper.addToolMaterial("alw:platinum", 2, ConfigHandler.PLATINUM_DURABILITY, 10.0F, ConfigHandler.PLATINUM_DAMAGE, 26).setRepairItem(new ItemStack(INGOT_PLATINUM));
	public static final ToolMaterial MAT_STEEL = EnumHelper.addToolMaterial("alw:steel", 2, ConfigHandler.STEEL_DURABILITY, 7.0F, ConfigHandler.STEEL_DAMAGE, 18).setRepairItem(new ItemStack(INGOT_STEEL));
    public static final ToolMaterial MAT_SHADOW_PLATINUM = EnumHelper.addToolMaterial("alw:shadow_platinum", 3, ConfigHandler.SHADOW_PLATINUM_DURABILITY, 8.25F, ConfigHandler.SHADOW_PLATINUM_DAMAGE, 21).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	public static final ToolMaterial MAT_FROST_STEEL = EnumHelper.addToolMaterial("alw:frost_steel", 3, ConfigHandler.FROST_STEEL_DURABILITY, 7.0F, ConfigHandler.FROST_STEEL_DAMAGE, 30).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
    public static final ToolMaterial MAT_OBSIDIAN = EnumHelper.addToolMaterial("alw:obsidian", 3, ConfigHandler.OBSIDIAN_DURABILITY, 7.5F, ConfigHandler.OBSIDIAN_DAMAGE, 18).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	public static final ToolMaterial MAT_CRYSTALLITE = EnumHelper.addToolMaterial("alw:crystallite", 3, ConfigHandler.CRYSTALLITE_DURABILITY, 7.5F, ConfigHandler.CRYSTALLITE_DAMAGE, 20).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	public static final ToolMaterial MAT_DUSKSTEEL = EnumHelper.addToolMaterial("alw:dusksteel", 3, ConfigHandler.DUSKSTEEL_DURABILITY, 8.5F, ConfigHandler.DUSKSTEEL_DAMAGE, 14).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	public static final ArmorMaterial CAMAT_IRON = EnumHelper.addArmorMaterial("alw:camat_iron", ModInfo.ID + ":chain_iron", (int)(ConfigHandler.IRON_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.IRON_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.IRON_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.IRON_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.IRON_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.IRON_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.IRON_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(Items.IRON_INGOT));
	
	public static final ArmorMaterial CAMAT_GOLD = EnumHelper.addArmorMaterial("alw:camat_gold", ModInfo.ID + ":chain_gold",  (int)(ConfigHandler.GOLD_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.GOLD_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.GOLD_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.GOLD_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.GOLD_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.GOLD_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.GOLD_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(Items.GOLD_INGOT));
	
	public static final ArmorMaterial CAMAT_DIAMOND = EnumHelper.addArmorMaterial("alw:camat_diamond", ModInfo.ID + ":diamond_studded_DIAMOND_STUDDED_LEATHER", (int)(ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, ConfigHandler.DIAMOND_STUDDED_LEATHER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(ItemInit.DIAMOND_STUDDED_LEATHER));
	
	public static final ArmorMaterial CAMAT_KOBOLD = EnumHelper.addArmorMaterial("alw:camat_kobold", ModInfo.ID + ":chain_kobold", (int)(ConfigHandler.KOBOLD_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.KOBOLD_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.KOBOLD_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.KOBOLD_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.KOBOLD_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.KOBOLD_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.KOBOLD_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_KOBOLD));
	
	public static final ArmorMaterial CAMAT_COPPER = EnumHelper.addArmorMaterial("alw:camat_copper", ModInfo.ID + ":chain_copper", (int)(ConfigHandler.COPPER_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.COPPER_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.COPPER_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.COPPER_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.COPPER_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.COPPER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.COPPER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_COPPER));
	
	public static final ArmorMaterial CAMAT_SILVER = EnumHelper.addArmorMaterial("alw:camat_silver", ModInfo.ID + ":chain_silver", (int)(ConfigHandler.SILVER_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.SILVER_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.SILVER_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.SILVER_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.SILVER_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.SILVER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.SILVER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_SILVER));
	
	public static final ArmorMaterial CAMAT_BRONZE = EnumHelper.addArmorMaterial("alw:camat_bronze", ModInfo.ID + ":chain_bronze", (int)(ConfigHandler.BRONZE_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.BRONZE_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.BRONZE_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.BRONZE_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.BRONZE_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.BRONZE_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, ConfigHandler.BRONZE_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_BRONZE));
	
	public static final ArmorMaterial CAMAT_PLATINUM = EnumHelper.addArmorMaterial("alw:camat_platinum", ModInfo.ID + ":chain_platinum", (int)(ConfigHandler.PLATINUM_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.PLATINUM_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.PLATINUM_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.PLATINUM_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.PLATINUM_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.PLATINUM_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, ConfigHandler.PLATINUM_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_PLATINUM));
	
	public static final ArmorMaterial CAMAT_STEEL = EnumHelper.addArmorMaterial("alw:camat_steel", ModInfo.ID + ":chain_steel", (int)(ConfigHandler.STEEL_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.STEEL_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.STEEL_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.STEEL_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.STEEL_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.STEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.STEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_STEEL));
	
	public static final ArmorMaterial CAMAT_SHADOW_PLATINUM = EnumHelper.addArmorMaterial("alw:camat_shadow_platinum", ModInfo.ID + ":chain_shadow_platinum", (int)(ConfigHandler.SHADOW_PLATINUM_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.SHADOW_PLATINUM_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.SHADOW_PLATINUM_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.SHADOW_PLATINUM_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.SHADOW_PLATINUM_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.SHADOW_PLATINUM_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.SHADOW_PLATINUM_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	
	public static final ArmorMaterial CAMAT_FROST_STEEL = EnumHelper.addArmorMaterial("alw:camat_frost_steel", ModInfo.ID + ":chain_frost_steel", (int)(ConfigHandler.FROST_STEEL_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.FROST_STEEL_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.FROST_STEEL_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.FROST_STEEL_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.FROST_STEEL_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.FROST_STEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.FROST_STEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
	
	public static final ArmorMaterial CAMAT_OBSIDIAN = EnumHelper.addArmorMaterial("alw:camat_obsidian", ModInfo.ID + ":chain_obsidian", (int)(ConfigHandler.OBSIDIAN_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.OBSIDIAN_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.OBSIDIAN_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.OBSIDIAN_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.OBSIDIAN_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.OBSIDIAN_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, ConfigHandler.OBSIDIAN_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	
	public static final ArmorMaterial CAMAT_CRYSTALLITE = EnumHelper.addArmorMaterial("alw:camat_crystallite", ModInfo.ID + ":chain_crystallite", (int)(ConfigHandler.CRYSTALLITE_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.CRYSTALLITE_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.CRYSTALLITE_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.CRYSTALLITE_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.CRYSTALLITE_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.CRYSTALLITE_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, ConfigHandler.CRYSTALLITE_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	
	public static final ArmorMaterial CAMAT_DUSKSTEEL = EnumHelper.addArmorMaterial("alw:camat_dusksteel", ModInfo.ID + ":chain_dusksteel", (int)(ConfigHandler.DUSKSTEEL_ARMOR_DURABILITY * ConfigHandler.CHAIN_DURABILITY_MULTIPLIER), 
			new int[] {(int)(ConfigHandler.DUSKSTEEL_ARMOR_BOOTS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.DUSKSTEEL_ARMOR_LEGGINGS_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), 
					(int)(ConfigHandler.DUSKSTEEL_ARMOR_CHESTPLATE_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER), (int)(ConfigHandler.DUSKSTEEL_ARMOR_HELMET_REDUCTION * ConfigHandler.CHAIN_PROTECTION_MULTIPLIER)},
			ConfigHandler.DUSKSTEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, ConfigHandler.DUSKSTEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	public static final ArmorMaterial AMAT_LEATHER = EnumHelper.addArmorMaterial("alw:amat_leather", "Minecraft:leather", ConfigHandler.LEATHER_ARMOR_DURABILITY, new int[] {ConfigHandler.LEATHER_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.LEATHER_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.LEATHER_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.LEATHER_ARMOR_HELMET_REDUCTION}, ConfigHandler.LEATHER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 
			ConfigHandler.LEATHER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(Items.LEATHER));
	
	public static final ArmorMaterial AMAT_IRON = EnumHelper.addArmorMaterial("alw:amat_iron", "Minecraft:iron", ConfigHandler.IRON_ARMOR_DURABILITY, new int[] {ConfigHandler.IRON_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.IRON_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.IRON_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.IRON_ARMOR_HELMET_REDUCTION}, ConfigHandler.IRON_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.IRON_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(Items.IRON_INGOT));
	
	public static final ArmorMaterial AMAT_GOLD = EnumHelper.addArmorMaterial("alw:amat_gold", "Minecraft:gold",ConfigHandler.GOLD_ARMOR_DURABILITY, new int[] {ConfigHandler.GOLD_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.GOLD_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.GOLD_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.GOLD_ARMOR_HELMET_REDUCTION}, ConfigHandler.GOLD_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 
			ConfigHandler.GOLD_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(Items.GOLD_INGOT));
	
	public static final ArmorMaterial AMAT_DIAMOND = EnumHelper.addArmorMaterial("alw:amat_diamond", ModInfo.ID + ":diamond_studded_steel",ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_DURABILITY, new int[] {ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_HELMET_REDUCTION}, ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.DIAMOND_STUDDED_STEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_STEEL));
	public static final ArmorMaterial AMAT_KOBOLD = EnumHelper.addArmorMaterial("alw:amat_kobold", ModInfo.ID + ":kobold",ConfigHandler.KOBOLD_ARMOR_DURABILITY, new int[] {ConfigHandler.KOBOLD_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.KOBOLD_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.KOBOLD_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.KOBOLD_ARMOR_HELMET_REDUCTION}, ConfigHandler.KOBOLD_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 
			ConfigHandler.KOBOLD_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_KOBOLD));
	
	public static final ArmorMaterial AMAT_COPPER = EnumHelper.addArmorMaterial("alw:amat_copper", ModInfo.ID + ":copper",ConfigHandler.COPPER_ARMOR_DURABILITY, new int[] {ConfigHandler.COPPER_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.COPPER_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.COPPER_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.COPPER_ARMOR_HELMET_REDUCTION}, ConfigHandler.COPPER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.COPPER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_COPPER));
	
	public static final ArmorMaterial AMAT_SILVER = EnumHelper.addArmorMaterial("alw:amat_silver", ModInfo.ID + ":silver",ConfigHandler.SILVER_ARMOR_DURABILITY, new int[] {ConfigHandler.SILVER_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.SILVER_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.SILVER_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.SILVER_ARMOR_HELMET_REDUCTION}, ConfigHandler.SILVER_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 
			ConfigHandler.SILVER_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_SILVER));
	
	public static final ArmorMaterial AMAT_BRONZE = EnumHelper.addArmorMaterial("alw:amat_bronze", ModInfo.ID + ":bronze",ConfigHandler.BRONZE_ARMOR_DURABILITY, new int[] {ConfigHandler.BRONZE_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.BRONZE_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.BRONZE_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.BRONZE_ARMOR_HELMET_REDUCTION}, ConfigHandler.BRONZE_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.BRONZE_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_BRONZE));
	
	public static final ArmorMaterial AMAT_PLATINUM = EnumHelper.addArmorMaterial("alw:amat_platinum", ModInfo.ID + ":platinum",ConfigHandler.PLATINUM_ARMOR_DURABILITY, new int[] {ConfigHandler.PLATINUM_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.PLATINUM_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.PLATINUM_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.PLATINUM_ARMOR_HELMET_REDUCTION}, ConfigHandler.PLATINUM_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.PLATINUM_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_PLATINUM));
	
	public static final ArmorMaterial AMAT_STEEL = EnumHelper.addArmorMaterial("alw:amat_steel", ModInfo.ID + ":steel",ConfigHandler.STEEL_ARMOR_DURABILITY, new int[] {ConfigHandler.STEEL_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.STEEL_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.STEEL_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.STEEL_ARMOR_HELMET_REDUCTION}, ConfigHandler.STEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.STEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_STEEL));
	
	public static final ArmorMaterial AMAT_SHADOW_PLATINUM = EnumHelper.addArmorMaterial("alw:amat_shadow_platinum", ModInfo.ID + ":shadow_platinum",ConfigHandler.SHADOW_PLATINUM_ARMOR_DURABILITY, new int[] {ConfigHandler.SHADOW_PLATINUM_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.SHADOW_PLATINUM_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.SHADOW_PLATINUM_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.SHADOW_PLATINUM_ARMOR_HELMET_REDUCTION}, ConfigHandler.SHADOW_PLATINUM_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 
			ConfigHandler.SHADOW_PLATINUM_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_SHADOW_PLATINUM));
	
	public static final ArmorMaterial AMAT_FROST_STEEL = EnumHelper.addArmorMaterial("alw:amat_frost_steel", ModInfo.ID + ":frost_steel",ConfigHandler.FROST_STEEL_ARMOR_DURABILITY, new int[] {ConfigHandler.FROST_STEEL_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.FROST_STEEL_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.FROST_STEEL_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.FROST_STEEL_ARMOR_HELMET_REDUCTION}, ConfigHandler.FROST_STEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 
			ConfigHandler.FROST_STEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_FROST_STEEL));
	
	public static final ArmorMaterial AMAT_OBSIDIAN = EnumHelper.addArmorMaterial("alw:amat_obsidian", ModInfo.ID + ":obsidian",ConfigHandler.OBSIDIAN_ARMOR_DURABILITY, new int[] {ConfigHandler.OBSIDIAN_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.OBSIDIAN_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.OBSIDIAN_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.OBSIDIAN_ARMOR_HELMET_REDUCTION}, ConfigHandler.OBSIDIAN_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.OBSIDIAN_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_OBSIDIAN));
	
	public static final ArmorMaterial AMAT_CRYSTALLITE = EnumHelper.addArmorMaterial("alw:amat_crystallite", ModInfo.ID + ":crystallite",ConfigHandler.CRYSTALLITE_ARMOR_DURABILITY, new int[] {ConfigHandler.CRYSTALLITE_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.CRYSTALLITE_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.CRYSTALLITE_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.CRYSTALLITE_ARMOR_HELMET_REDUCTION}, ConfigHandler.CRYSTALLITE_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 
			ConfigHandler.CRYSTALLITE_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_CRYSTALLITE));
	
	public static final ArmorMaterial AMAT_DUSKSTEEL = EnumHelper.addArmorMaterial("alw:amat_dusksteel", ModInfo.ID + ":dusksteel",ConfigHandler.DUSKSTEEL_ARMOR_DURABILITY, new int[] {ConfigHandler.DUSKSTEEL_ARMOR_BOOTS_REDUCTION, 
			ConfigHandler.DUSKSTEEL_ARMOR_LEGGINGS_REDUCTION, ConfigHandler.DUSKSTEEL_ARMOR_CHESTPLATE_REDUCTION, ConfigHandler.DUSKSTEEL_ARMOR_HELMET_REDUCTION}, ConfigHandler.DUSKSTEEL_ARMOR_ENCHANTABILITY, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
			ConfigHandler.DUSKSTEEL_ARMOR_TOUGHNESS).setRepairItem(new ItemStack(INGOT_DUSKSTEEL));
	
	
	//Tools
	//public static final Item AXE_STEEL = new ToolAxe("axe_steel", MAT_STEEL);
	//public static final Item HOE_STEEL = new ToolHoe("hoe_steel", MAT_STEEL);
	//public static final Item PICKAXE_STEEL = new ToolPickaxe("pickaxe_steel", MAT_STEEL);
	//public static final Item SHOVEL_STEEL = new ToolShovel("shovel_steel", MAT_STEEL);
	//public static final Item SWORD_STEEL = new ToolSword("sword_steel", MAT_STEEL);
	
	
	//Daggers
	public static final Item DAGGER_WOOD = new ToolStabSword("dagger_wood", ToolMaterial.WOOD, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_IRON = new ToolStabSword("dagger_iron", ToolMaterial.IRON, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_COPPER = new ToolStabSword("dagger_copper", MAT_COPPER, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_SILVER = new ToolStabSword("dagger_silver", MAT_SILVER, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_BRONZE = new ToolStabSword("dagger_bronze", MAT_BRONZE, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_PLATINUM = new ToolStabSword("dagger_platinum", MAT_PLATINUM, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_STEEL = new ToolStabSword("dagger_steel", MAT_STEEL, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_OBSIDIAN = new ToolStabSword("dagger_obsidian", MAT_OBSIDIAN, "dagger").setMaxStackSize(1);
	
	public static final Item DAGGER_KOBOLD = new ToolStabSword("dagger_kobold", MAT_KOBOLD, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_SHADOW_PLATINUM = new ToolStabSword("dagger_shadow_platinum", MAT_SHADOW_PLATINUM, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_FROST_STEEL = new ToolStabSword("dagger_frost_steel", MAT_FROST_STEEL, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_CRYSTALLITE = new ToolStabSword("dagger_crystallite", MAT_CRYSTALLITE, "dagger").setMaxStackSize(1);
	public static final Item DAGGER_DUSKSTEEL = new ToolStabSword("dagger_dusksteel", MAT_DUSKSTEEL, "dagger").setMaxStackSize(1);
	
	
	//Kabutowaris
	public static final Item KABUTOWARI_WOOD = new ToolStabSword("kabutowari_wood", ToolMaterial.WOOD, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_IRON = new ToolStabSword("kabutowari_iron", ToolMaterial.IRON, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_COPPER = new ToolStabSword("kabutowari_copper", MAT_COPPER, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_SILVER = new ToolStabSword("kabutowari_silver", MAT_SILVER, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_BRONZE = new ToolStabSword("kabutowari_bronze", MAT_BRONZE, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_PLATINUM = new ToolStabSword("kabutowari_platinum", MAT_PLATINUM, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_STEEL = new ToolStabSword("kabutowari_steel", MAT_STEEL, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_OBSIDIAN = new ToolStabSword("kabutowari_obsidian", MAT_OBSIDIAN, "kabutowari").setMaxStackSize(1);
	
	public static final Item KABUTOWARI_KOBOLD = new ToolStabSword("kabutowari_kobold", MAT_KOBOLD, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_SHADOW_PLATINUM = new ToolStabSword("kabutowari_shadow_platinum", MAT_SHADOW_PLATINUM, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_FROST_STEEL = new ToolStabSword("kabutowari_frost_steel", MAT_FROST_STEEL, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_CRYSTALLITE = new ToolStabSword("kabutowari_crystallite", MAT_CRYSTALLITE, "kabutowari").setMaxStackSize(1);
	public static final Item KABUTOWARI_DUSKSTEEL = new ToolStabSword("kabutowari_dusksteel", MAT_DUSKSTEEL, "kabutowari").setMaxStackSize(1);
	
	
	
	//Rapiers
	public static final Item RAPIER_WOOD = new ToolStabSword("rapier_wood", ToolMaterial.WOOD, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_IRON = new ToolStabSword("rapier_iron", ToolMaterial.IRON, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_COPPER = new ToolStabSword("rapier_copper", MAT_COPPER, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_SILVER = new ToolStabSword("rapier_silver", MAT_SILVER, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_BRONZE = new ToolStabSword("rapier_bronze", MAT_BRONZE, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_PLATINUM = new ToolStabSword("rapier_platinum", MAT_PLATINUM, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_STEEL = new ToolStabSword("rapier_steel", MAT_STEEL, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_OBSIDIAN = new ToolStabSword("rapier_obsidian", MAT_OBSIDIAN, "rapier").setMaxStackSize(1);
	
	public static final Item RAPIER_KOBOLD = new ToolStabSword("rapier_kobold", MAT_KOBOLD, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_SHADOW_PLATINUM = new ToolStabSword("rapier_shadow_platinum", MAT_SHADOW_PLATINUM, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_FROST_STEEL = new ToolStabSword("rapier_frost_steel", MAT_FROST_STEEL, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_CRYSTALLITE = new ToolStabSword("rapier_crystallite", MAT_CRYSTALLITE, "rapier").setMaxStackSize(1);
	public static final Item RAPIER_DUSKSTEEL = new ToolStabSword("rapier_dusksteel", MAT_DUSKSTEEL, "rapier").setMaxStackSize(1);
	
	//Rapiers
	public static final Item TALWAR_WOOD = new ToolStabSword("talwar_wood", ToolMaterial.WOOD, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_IRON = new ToolStabSword("talwar_iron", ToolMaterial.IRON, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_COPPER = new ToolStabSword("talwar_copper", MAT_COPPER, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_SILVER = new ToolStabSword("talwar_silver", MAT_SILVER, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_BRONZE = new ToolStabSword("talwar_bronze", MAT_BRONZE, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_PLATINUM = new ToolStabSword("talwar_platinum", MAT_PLATINUM, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_STEEL = new ToolStabSword("talwar_steel", MAT_STEEL, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_OBSIDIAN = new ToolStabSword("talwar_obsidian", MAT_OBSIDIAN, "talwar").setMaxStackSize(1);
	
	public static final Item TALWAR_KOBOLD = new ToolStabSword("talwar_kobold", MAT_KOBOLD, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_SHADOW_PLATINUM = new ToolStabSword("talwar_shadow_platinum", MAT_SHADOW_PLATINUM, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_FROST_STEEL = new ToolStabSword("talwar_frost_steel", MAT_FROST_STEEL, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_CRYSTALLITE = new ToolStabSword("talwar_crystallite", MAT_CRYSTALLITE, "talwar").setMaxStackSize(1);
	public static final Item TALWAR_DUSKSTEEL = new ToolStabSword("talwar_dusksteel", MAT_DUSKSTEEL, "talwar").setMaxStackSize(1);
	
	//Cleavers
	public static final Item CLEAVER_WOOD = new ToolStabSword("cleaver_wood", ToolMaterial.WOOD, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_IRON = new ToolStabSword("cleaver_iron", ToolMaterial.IRON, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_COPPER = new ToolStabSword("cleaver_copper", MAT_COPPER, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_SILVER = new ToolStabSword("cleaver_silver", MAT_SILVER, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_BRONZE = new ToolStabSword("cleaver_bronze", MAT_BRONZE, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_PLATINUM = new ToolStabSword("cleaver_platinum", MAT_PLATINUM, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_STEEL = new ToolStabSword("cleaver_steel", MAT_STEEL, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_OBSIDIAN = new ToolStabSword("cleaver_obsidian", MAT_OBSIDIAN, "cleaver").setMaxStackSize(1);
	
	public static final Item CLEAVER_KOBOLD = new ToolStabSword("cleaver_kobold", MAT_KOBOLD, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_SHADOW_PLATINUM = new ToolStabSword("cleaver_shadow_platinum", MAT_SHADOW_PLATINUM, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_FROST_STEEL = new ToolStabSword("cleaver_frost_steel", MAT_FROST_STEEL, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_CRYSTALLITE = new ToolStabSword("cleaver_crystallite", MAT_CRYSTALLITE, "cleaver").setMaxStackSize(1);
	public static final Item CLEAVER_DUSKSTEEL = new ToolStabSword("cleaver_dusksteel", MAT_DUSKSTEEL, "cleaver").setMaxStackSize(1);
	
	//Maces
	public static final Item MACE_WOOD = new ToolStabSword("mace_wood", ToolMaterial.WOOD, "mace").setMaxStackSize(1);
	public static final Item MACE_IRON = new ToolStabSword("mace_iron", ToolMaterial.IRON, "mace").setMaxStackSize(1);
	public static final Item MACE_COPPER = new ToolStabSword("mace_copper", MAT_COPPER, "mace").setMaxStackSize(1);
	public static final Item MACE_SILVER = new ToolStabSword("mace_silver", MAT_SILVER, "mace").setMaxStackSize(1);
	public static final Item MACE_BRONZE = new ToolStabSword("mace_bronze", MAT_BRONZE, "mace").setMaxStackSize(1);
	public static final Item MACE_PLATINUM = new ToolStabSword("mace_platinum", MAT_PLATINUM, "mace").setMaxStackSize(1);
	public static final Item MACE_STEEL = new ToolStabSword("mace_steel", MAT_STEEL, "mace").setMaxStackSize(1);
	public static final Item MACE_OBSIDIAN = new ToolStabSword("mace_obsidian", MAT_OBSIDIAN, "mace").setMaxStackSize(1);
	
	public static final Item MACE_KOBOLD = new ToolStabSword("mace_kobold", MAT_KOBOLD, "mace").setMaxStackSize(1);
	public static final Item MACE_SHADOW_PLATINUM = new ToolStabSword("mace_shadow_platinum", MAT_SHADOW_PLATINUM, "mace").setMaxStackSize(1);
	public static final Item MACE_FROST_STEEL = new ToolStabSword("mace_frost_steel", MAT_FROST_STEEL, "mace").setMaxStackSize(1);
	public static final Item MACE_CRYSTALLITE = new ToolStabSword("mace_crystallite", MAT_CRYSTALLITE, "mace").setMaxStackSize(1);
	public static final Item MACE_DUSKSTEEL = new ToolStabSword("mace_dusksteel", MAT_DUSKSTEEL, "mace").setMaxStackSize(1);
	
	//Staffs
	public static final Item STAFF_WOOD = new ToolStabSword("staff_wood", ToolMaterial.WOOD, "staff").setMaxStackSize(1);
	public static final Item STAFF_IRON = new ToolStabSword("staff_iron", ToolMaterial.IRON, "staff").setMaxStackSize(1);
	public static final Item STAFF_COPPER = new ToolStabSword("staff_copper", MAT_COPPER, "staff").setMaxStackSize(1);
	public static final Item STAFF_SILVER = new ToolStabSword("staff_silver", MAT_SILVER, "staff").setMaxStackSize(1);
	public static final Item STAFF_BRONZE = new ToolStabSword("staff_bronze", MAT_BRONZE, "staff").setMaxStackSize(1);
	public static final Item STAFF_PLATINUM = new ToolStabSword("staff_platinum", MAT_PLATINUM, "staff").setMaxStackSize(1);
	public static final Item STAFF_STEEL = new ToolStabSword("staff_steel", MAT_STEEL, "staff").setMaxStackSize(1);
	public static final Item STAFF_OBSIDIAN = new ToolStabSword("staff_obsidian", MAT_OBSIDIAN, "staff").setMaxStackSize(1);
	
	public static final Item STAFF_KOBOLD = new ToolStabSword("staff_kobold", MAT_KOBOLD, "staff").setMaxStackSize(1);
	public static final Item STAFF_SHADOW_PLATINUM = new ToolStabSword("staff_shadow_platinum", MAT_SHADOW_PLATINUM, "staff").setMaxStackSize(1);
	public static final Item STAFF_FROST_STEEL = new ToolStabSword("staff_frost_steel", MAT_FROST_STEEL, "staff").setMaxStackSize(1);
	public static final Item STAFF_CRYSTALLITE = new ToolStabSword("staff_crystallite", MAT_CRYSTALLITE, "staff").setMaxStackSize(1);
	public static final Item STAFF_DUSKSTEEL = new ToolStabSword("staff_dusksteel", MAT_DUSKSTEEL, "staff").setMaxStackSize(1);
	
	//Longswords
	public static final Item LONGSWORD_WOOD = new ToolSlashSword("longsword_wood", ToolMaterial.WOOD, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_IRON = new ToolSlashSword("longsword_iron", ToolMaterial.IRON, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_COPPER = new ToolSlashSword("longsword_copper", MAT_COPPER, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_SILVER = new ToolSlashSword("longsword_silver", MAT_SILVER, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_BRONZE = new ToolSlashSword("longsword_bronze", MAT_BRONZE, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_PLATINUM = new ToolSlashSword("longsword_platinum", MAT_PLATINUM, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_STEEL = new ToolSlashSword("longsword_steel", MAT_STEEL, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_OBSIDIAN = new ToolSlashSword("longsword_obsidian", MAT_OBSIDIAN, "longsword").setMaxStackSize(1);
	
	public static final Item LONGSWORD_KOBOLD = new ToolSlashSword("longsword_kobold", MAT_KOBOLD, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_SHADOW_PLATINUM = new ToolSlashSword("longsword_shadow_platinum", MAT_SHADOW_PLATINUM, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_FROST_STEEL = new ToolSlashSword("longsword_frost_steel", MAT_FROST_STEEL, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_CRYSTALLITE = new ToolSlashSword("longsword_crystallite", MAT_CRYSTALLITE, "longsword").setMaxStackSize(1);
	public static final Item LONGSWORD_DUSKSTEEL = new ToolSlashSword("longsword_dusksteel", MAT_DUSKSTEEL, "longsword").setMaxStackSize(1);
	
	//Kodachis
	public static final Item KODACHI_WOOD = new ToolSlashSword("kodachi_wood", ToolMaterial.WOOD, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_IRON = new ToolSlashSword("kodachi_iron", ToolMaterial.IRON, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_COPPER = new ToolSlashSword("kodachi_copper", MAT_COPPER, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_SILVER = new ToolSlashSword("kodachi_silver", MAT_SILVER, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_BRONZE = new ToolSlashSword("kodachi_bronze", MAT_BRONZE, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_PLATINUM = new ToolSlashSword("kodachi_platinum", MAT_PLATINUM, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_STEEL = new ToolSlashSword("kodachi_steel", MAT_STEEL, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_OBSIDIAN = new ToolSlashSword("kodachi_obsidian", MAT_OBSIDIAN, "kodachi").setMaxStackSize(1);
	
	public static final Item KODACHI_KOBOLD = new ToolSlashSword("kodachi_kobold", MAT_KOBOLD, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_SHADOW_PLATINUM = new ToolSlashSword("kodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_FROST_STEEL = new ToolSlashSword("kodachi_frost_steel", MAT_FROST_STEEL, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_CRYSTALLITE = new ToolSlashSword("kodachi_crystallite", MAT_CRYSTALLITE, "kodachi").setMaxStackSize(1);
	public static final Item KODACHI_DUSKSTEEL = new ToolSlashSword("kodachi_dusksteel", MAT_DUSKSTEEL, "kodachi").setMaxStackSize(1);
	
	//Battleaxes
	public static final Item BATTLEAXE_WOOD = new ToolSlashSword("battleaxe_wood", ToolMaterial.WOOD, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_IRON = new ToolSlashSword("battleaxe_iron", ToolMaterial.IRON, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_COPPER = new ToolSlashSword("battleaxe_copper", MAT_COPPER, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_SILVER = new ToolSlashSword("battleaxe_silver", MAT_SILVER, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_BRONZE = new ToolSlashSword("battleaxe_bronze", MAT_BRONZE, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_PLATINUM = new ToolSlashSword("battleaxe_platinum", MAT_PLATINUM, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_STEEL = new ToolSlashSword("battleaxe_steel", MAT_STEEL, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_OBSIDIAN = new ToolSlashSword("battleaxe_obsidian", MAT_OBSIDIAN, "battleaxe").setMaxStackSize(1);
	
	public static final Item BATTLEAXE_KOBOLD = new ToolSlashSword("battleaxe_kobold", MAT_KOBOLD, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_SHADOW_PLATINUM = new ToolSlashSword("battleaxe_shadow_platinum", MAT_SHADOW_PLATINUM, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_FROST_STEEL = new ToolSlashSword("battleaxe_frost_steel", MAT_FROST_STEEL, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_CRYSTALLITE = new ToolSlashSword("battleaxe_crystallite", MAT_CRYSTALLITE, "battleaxe").setMaxStackSize(1);
	public static final Item BATTLEAXE_DUSKSTEEL = new ToolSlashSword("battleaxe_dusksteel", MAT_DUSKSTEEL, "battleaxe").setMaxStackSize(1);
	
	//Zweihanders
	public static final Item ZWEIHANDER_WOOD = new ToolSlashSword("zweihander_wood", ToolMaterial.WOOD, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_IRON = new ToolSlashSword("zweihander_iron", ToolMaterial.IRON, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_COPPER = new ToolSlashSword("zweihander_copper", MAT_COPPER, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_SILVER = new ToolSlashSword("zweihander_silver", MAT_SILVER, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_BRONZE = new ToolSlashSword("zweihander_bronze", MAT_BRONZE, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_PLATINUM = new ToolSlashSword("zweihander_platinum", MAT_PLATINUM, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_STEEL = new ToolSlashSword("zweihander_steel", MAT_STEEL, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_OBSIDIAN = new ToolSlashSword("zweihander_obsidian", MAT_OBSIDIAN, "zweihander").setMaxStackSize(1);
	
	public static final Item ZWEIHANDER_KOBOLD = new ToolSlashSword("zweihander_kobold", MAT_KOBOLD, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_SHADOW_PLATINUM = new ToolSlashSword("zweihander_shadow_platinum", MAT_SHADOW_PLATINUM, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_FROST_STEEL = new ToolSlashSword("zweihander_frost_steel", MAT_FROST_STEEL, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_CRYSTALLITE = new ToolSlashSword("zweihander_crystallite", MAT_CRYSTALLITE, "zweihander").setMaxStackSize(1);
	public static final Item ZWEIHANDER_DUSKSTEEL = new ToolSlashSword("zweihander_dusksteel", MAT_DUSKSTEEL, "zweihander").setMaxStackSize(1);
	
	//Nodachis
	public static final Item NODACHI_WOOD = new ToolSlashSword("nodachi_wood", ToolMaterial.WOOD, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_IRON = new ToolSlashSword("nodachi_iron", ToolMaterial.IRON, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_COPPER = new ToolSlashSword("nodachi_copper", MAT_COPPER, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_SILVER = new ToolSlashSword("nodachi_silver", MAT_SILVER, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_BRONZE = new ToolSlashSword("nodachi_bronze", MAT_BRONZE, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_PLATINUM = new ToolSlashSword("nodachi_platinum", MAT_PLATINUM, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_STEEL = new ToolSlashSword("nodachi_steel", MAT_STEEL, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_OBSIDIAN = new ToolSlashSword("nodachi_obsidian", MAT_OBSIDIAN, "nodachi").setMaxStackSize(1);
	
	public static final Item NODACHI_KOBOLD = new ToolSlashSword("nodachi_kobold", MAT_KOBOLD, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_SHADOW_PLATINUM = new ToolSlashSword("nodachi_shadow_platinum", MAT_SHADOW_PLATINUM, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_FROST_STEEL = new ToolSlashSword("nodachi_frost_steel", MAT_FROST_STEEL, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_CRYSTALLITE = new ToolSlashSword("nodachi_crystallite", MAT_CRYSTALLITE, "nodachi").setMaxStackSize(1);
	public static final Item NODACHI_DUSKSTEEL = new ToolSlashSword("nodachi_dusksteel", MAT_DUSKSTEEL, "nodachi").setMaxStackSize(1);
	
	//Sabres
	public static final Item SABRE_WOOD = new ToolSlashSword("sabre_wood", ToolMaterial.WOOD, "sabre").setMaxStackSize(1);
	public static final Item SABRE_IRON = new ToolSlashSword("sabre_iron", ToolMaterial.IRON, "sabre").setMaxStackSize(1);
	public static final Item SABRE_COPPER = new ToolSlashSword("sabre_copper", MAT_COPPER, "sabre").setMaxStackSize(1);
	public static final Item SABRE_SILVER = new ToolSlashSword("sabre_silver", MAT_SILVER, "sabre").setMaxStackSize(1);
	public static final Item SABRE_BRONZE = new ToolSlashSword("sabre_bronze", MAT_BRONZE, "sabre").setMaxStackSize(1);
	public static final Item SABRE_PLATINUM = new ToolSlashSword("sabre_platinum", MAT_PLATINUM, "sabre").setMaxStackSize(1);
	public static final Item SABRE_STEEL = new ToolSlashSword("sabre_steel", MAT_STEEL, "sabre").setMaxStackSize(1);
	public static final Item SABRE_OBSIDIAN = new ToolSlashSword("sabre_obsidian", MAT_OBSIDIAN, "sabre").setMaxStackSize(1);
	
	public static final Item SABRE_KOBOLD = new ToolSlashSword("sabre_kobold", MAT_KOBOLD, "sabre").setMaxStackSize(1);
	public static final Item SABRE_SHADOW_PLATINUM = new ToolSlashSword("sabre_shadow_platinum", MAT_SHADOW_PLATINUM, "sabre").setMaxStackSize(1);
	public static final Item SABRE_FROST_STEEL = new ToolSlashSword("sabre_frost_steel", MAT_FROST_STEEL, "sabre").setMaxStackSize(1);
	public static final Item SABRE_CRYSTALLITE = new ToolSlashSword("sabre_crystallite", MAT_CRYSTALLITE, "sabre").setMaxStackSize(1);
	public static final Item SABRE_DUSKSTEEL = new ToolSlashSword("sabre_dusksteel", MAT_DUSKSTEEL, "sabre").setMaxStackSize(1);
	
	//Makhairas
	public static final Item MAKHAIRA_WOOD = new ToolSlashSword("makhaira_wood", ToolMaterial.WOOD, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_IRON = new ToolSlashSword("makhaira_iron", ToolMaterial.IRON, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_COPPER = new ToolSlashSword("makhaira_copper", MAT_COPPER, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_SILVER = new ToolSlashSword("makhaira_silver", MAT_SILVER, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_BRONZE = new ToolSlashSword("makhaira_bronze", MAT_BRONZE, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_PLATINUM = new ToolSlashSword("makhaira_platinum", MAT_PLATINUM, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_STEEL = new ToolSlashSword("makhaira_steel", MAT_STEEL, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_OBSIDIAN = new ToolSlashSword("makhaira_obsidian", MAT_OBSIDIAN, "makhaira").setMaxStackSize(1);
	
	public static final Item MAKHAIRA_KOBOLD = new ToolSlashSword("makhaira_kobold", MAT_KOBOLD, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_SHADOW_PLATINUM = new ToolSlashSword("makhaira_shadow_platinum", MAT_SHADOW_PLATINUM, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_FROST_STEEL = new ToolSlashSword("makhaira_frost_steel", MAT_FROST_STEEL, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_CRYSTALLITE = new ToolSlashSword("makhaira_crystallite", MAT_CRYSTALLITE, "makhaira").setMaxStackSize(1);
	public static final Item MAKHAIRA_DUSKSTEEL = new ToolSlashSword("makhaira_dusksteel", MAT_DUSKSTEEL, "makhaira").setMaxStackSize(1);
	
	//Spears
	public static final Item SPEAR_WOOD = new ToolSpear("spear_wood", ToolMaterial.WOOD).setMaxStackSize(1);
	public static final Item SPEAR_IRON = new ToolSpear("spear_iron", ToolMaterial.IRON).setMaxStackSize(1);
	public static final Item SPEAR_COPPER = new ToolSpear("spear_copper", MAT_COPPER).setMaxStackSize(1);
	public static final Item SPEAR_SILVER = new ToolSpear("spear_silver", MAT_SILVER).setMaxStackSize(1);
	public static final Item SPEAR_BRONZE = new ToolSpear("spear_bronze", MAT_BRONZE).setMaxStackSize(1);
	public static final Item SPEAR_PLATINUM = new ToolSpear("spear_platinum", MAT_PLATINUM).setMaxStackSize(1);
	public static final Item SPEAR_STEEL = new ToolSpear("spear_steel", MAT_STEEL).setMaxStackSize(1);
	public static final Item SPEAR_OBSIDIAN = new ToolSpear("spear_obsidian", MAT_OBSIDIAN).setMaxStackSize(1);
	
	public static final Item SPEAR_KOBOLD = new ToolSpear("spear_kobold", MAT_KOBOLD).setMaxStackSize(1);
	public static final Item SPEAR_SHADOW_PLATINUM = new ToolSpear("spear_shadow_platinum", MAT_SHADOW_PLATINUM).setMaxStackSize(1);
	public static final Item SPEAR_FROST_STEEL = new ToolSpear("spear_frost_steel", MAT_FROST_STEEL).setMaxStackSize(1);
	public static final Item SPEAR_CRYSTALLITE = new ToolSpear("spear_crystallite", MAT_CRYSTALLITE).setMaxStackSize(1);
	public static final Item SPEAR_DUSKSTEEL = new ToolSpear("spear_dusksteel", MAT_DUSKSTEEL).setMaxStackSize(1);
	
	public static final Item CHAIN_LINK_IRON = new ItemBase("chain_link_iron");
	public static final Item CHAIN_LINK_GOLD = new ItemBase("chain_link_gold");
	public static final Item CHAIN_LINK_COPPER = new ItemBase("chain_link_copper");
	public static final Item CHAIN_LINK_SILVER = new ItemBase("chain_link_silver");
	public static final Item CHAIN_LINK_BRONZE = new ItemBase("chain_link_bronze");
	public static final Item CHAIN_LINK_PLATINUM = new ItemBase("chain_link_platinum");
	public static final Item CHAIN_LINK_STEEL = new ItemBase("chain_link_steel");
	public static final Item CHAIN_LINK_OBSIDIAN = new ItemBase("chain_link_obsidian");
	
	public static final Item CHAIN_LINK_KOBOLD = new ItemBase("chain_link_kobold");
	public static final Item CHAIN_LINK_SHADOW_PLATINUM = new ItemBase("chain_link_shadow_platinum");
	public static final Item CHAIN_LINK_FROST_STEEL = new ItemBase("chain_link_frost_steel");
	public static final Item CHAIN_LINK_CRYSTALLITE = new ItemBase("chain_link_crystallite");
	public static final Item CHAIN_LINK_DUSKSTEEL = new ItemBase("chain_link_dusksteel");
	
	public static final Item ARMOR_PLATE_IRON = new ItemArmorPlate("armor_plate_iron");
	public static final Item ARMOR_PLATE_GOLD = new ItemArmorPlate("armor_plate_gold");
	public static final Item ARMOR_PLATE_DIAMOND = new ItemArmorPlate("armor_plate_diamond");
	public static final Item ARMOR_PLATE_COPPER = new ItemArmorPlate("armor_plate_copper");
	public static final Item ARMOR_PLATE_SILVER = new ItemArmorPlate("armor_plate_silver");
	public static final Item ARMOR_PLATE_BRONZE = new ItemArmorPlate("armor_plate_bronze");
	public static final Item ARMOR_PLATE_PLATINUM = new ItemArmorPlate("armor_plate_platinum");
	public static final Item ARMOR_PLATE_STEEL = new ItemArmorPlate("armor_plate_steel");
	public static final Item ARMOR_PLATE_OBSIDIAN = new ItemArmorPlate("armor_plate_obsidian");
	
	public static final Item ARMOR_PLATE_KOBOLD = new ItemArmorPlate("armor_plate_kobold");
	public static final Item ARMOR_PLATE_SHADOW_PLATINUM = new ItemArmorPlate("armor_plate_shadow_platinum");
	public static final Item ARMOR_PLATE_FROST_STEEL = new ItemArmorPlate("armor_plate_frost_steel");
	public static final Item ARMOR_PLATE_CRYSTALLITE = new ItemArmorPlate("armor_plate_crystallite");
	public static final Item ARMOR_PLATE_DUSKSTEEL = new ItemArmorPlate("armor_plate_dusksteel");
	
	public static final Item CHAIN_BINDING_IRON = new ItemArmorBinding("binding_chain_iron", ArmorMaterial.IRON);
	public static final Item CHAIN_BINDING_GOLD = new ItemArmorBinding("binding_chain_gold", ArmorMaterial.GOLD);
	public static final Item CHAIN_BINDING_COPPER = new ItemArmorBinding("binding_chain_copper", AMAT_COPPER);
	public static final Item CHAIN_BINDING_SILVER = new ItemArmorBinding("binding_chain_silver", AMAT_SILVER);
	public static final Item CHAIN_BINDING_BRONZE = new ItemArmorBinding("binding_chain_bronze", AMAT_BRONZE);
	public static final Item CHAIN_BINDING_PLATINUM = new ItemArmorBinding("binding_chain_platinum", AMAT_PLATINUM);
	public static final Item CHAIN_BINDING_STEEL = new ItemArmorBinding("binding_chain_steel", AMAT_STEEL);
	public static final Item CHAIN_BINDING_OBSIDIAN = new ItemArmorBinding("binding_chain_obsidian", AMAT_OBSIDIAN);
	
	public static final Item CHAIN_BINDING_KOBOLD = new ItemArmorBinding("binding_chain_kobold", AMAT_KOBOLD);
	public static final Item CHAIN_BINDING_SHADOW_PLATINUM = new ItemArmorBinding("binding_chain_shadow_platinum", AMAT_SHADOW_PLATINUM);
	public static final Item CHAIN_BINDING_FROST_STEEL = new ItemArmorBinding("binding_chain_frost_steel", AMAT_FROST_STEEL);
	public static final Item CHAIN_BINDING_CRYSTALLITE = new ItemArmorBinding("binding_chain_crystallite", AMAT_CRYSTALLITE);
	public static final Item CHAIN_BINDING_DUSKSTEEL = new ItemArmorBinding("binding_chain_dusksteel", AMAT_DUSKSTEEL);
	
	public static final Item LEATHER_BINDING = new ItemArmorBinding("binding_leather", ArmorMaterial.LEATHER);
	
	
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
	public static final Item STONE_FORGE_HAMMER = new ToolForgeHammer("stone_forge_hammer", ToolMaterial.STONE).setMaxStackSize(1);
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
	//						 ItemHotToolHead(String name, ItemHotToolHead next, int level, boolean finished)
	public static final Item HOT_TOOL_HEAD = new ItemHotToolHead("hot_tool_head", null, 0, false, false);
	
	public static final Item HOT_TOOL_ROD_2 = new ItemHotToolHead("hot_tool_rod_2", null, 2, true, false);
	public static final Item HOT_TOOL_ROD = new ItemHotToolHead("hot_tool_rod", (ItemHotToolHead)HOT_TOOL_ROD_2, 1, false, false);
	public static final Item LONG_TOOL_ROD = new ItemHotToolHead("long_tool_rod", null, 3, true, false);
	
	public static final Item DAGGER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("dagger_hot_tool_head_2", null, 2, true, true);
	public static final Item DAGGER_HOT_TOOL_HEAD = new ItemHotToolHead("dagger_hot_tool_head", (ItemHotToolHead)DAGGER_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_5 = new ItemHotToolHead("kabutowari_hot_tool_head_5", null, 5, true, true);
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_4 = new ItemHotToolHead("kabutowari_hot_tool_head_4", (ItemHotToolHead)KABUTOWARI_HOT_TOOL_HEAD_5, 4, false, true);
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_3 = new ItemHotToolHead("kabutowari_hot_tool_head_3", (ItemHotToolHead)KABUTOWARI_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item KABUTOWARI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("kabutowari_hot_tool_head_2", (ItemHotToolHead)KABUTOWARI_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item KABUTOWARI_HOT_TOOL_HEAD = new ItemHotToolHead("kabutowari_hot_tool_head", (ItemHotToolHead)KABUTOWARI_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item TALWAR_HOT_TOOL_HEAD_3 = new ItemHotToolHead("talwar_hot_tool_head_3", null, 3, true, true);
	public static final Item TALWAR_HOT_TOOL_HEAD_2 = new ItemHotToolHead("talwar_hot_tool_head_2", (ItemHotToolHead)TALWAR_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item TALWAR_HOT_TOOL_HEAD = new ItemHotToolHead("talwar_hot_tool_head", (ItemHotToolHead)TALWAR_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item RAPIER_HOT_TOOL_HEAD_4 = new ItemHotToolHead("rapier_hot_tool_head_4", null, 4, true, true);
	public static final Item RAPIER_HOT_TOOL_HEAD_3 = new ItemHotToolHead("rapier_hot_tool_head_3", (ItemHotToolHead)RAPIER_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item RAPIER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("rapier_hot_tool_head_2", (ItemHotToolHead)RAPIER_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item RAPIER_HOT_TOOL_HEAD = new ItemHotToolHead("rapier_hot_tool_head", (ItemHotToolHead)RAPIER_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item CLEAVER_HOT_TOOL_HEAD = new ItemHotToolHead("cleaver_hot_tool_head", null, 2, true, true);
	
	public static final Item MACE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("mace_hot_tool_head_3", null, 3, true, true);
	public static final Item MACE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("mace_hot_tool_head_2", (ItemHotToolHead)MACE_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item MACE_HOT_TOOL_HEAD = new ItemHotToolHead("mace_hot_tool_head", (ItemHotToolHead)MACE_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item STAFF_HOT_TOOL_HEAD_5 = new ItemHotToolHead("staff_hot_tool_head_5", null, 5, true, true);
	public static final Item STAFF_HOT_TOOL_HEAD_4 = new ItemHotToolHead("staff_hot_tool_head_4", (ItemHotToolHead)STAFF_HOT_TOOL_HEAD_5, 4, false, true);
	public static final Item STAFF_HOT_TOOL_HEAD_3 = new ItemHotToolHead("staff_hot_tool_head_3", (ItemHotToolHead)STAFF_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item STAFF_HOT_TOOL_HEAD_2 = new ItemHotToolHead("staff_hot_tool_head_2", (ItemHotToolHead)STAFF_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item STAFF_HOT_TOOL_HEAD = new ItemHotToolHead("staff_hot_tool_head", (ItemHotToolHead)STAFF_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item LONGSWORD_HOT_TOOL_HEAD_4 = new ItemHotToolHead("longsword_hot_tool_head_4", null, 4, true, true);
	public static final Item LONGSWORD_HOT_TOOL_HEAD_3 = new ItemHotToolHead("longsword_hot_tool_head_3", (ItemHotToolHead)LONGSWORD_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item LONGSWORD_HOT_TOOL_HEAD_2 = new ItemHotToolHead("longsword_hot_tool_head_2", (ItemHotToolHead)LONGSWORD_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item LONGSWORD_HOT_TOOL_HEAD = new ItemHotToolHead("longsword_hot_tool_head", (ItemHotToolHead)LONGSWORD_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item KODACHI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("kodachi_hot_tool_head_2", null, 2, true, true);
	public static final Item KODACHI_HOT_TOOL_HEAD = new ItemHotToolHead("kodachi_hot_tool_head", (ItemHotToolHead)KODACHI_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_5 = new ItemHotToolHead("battleaxe_hot_tool_head_5", null, 5, true, true);
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_4 = new ItemHotToolHead("battleaxe_hot_tool_head_4", (ItemHotToolHead)BATTLEAXE_HOT_TOOL_HEAD_5, 4, false, true);
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("battleaxe_hot_tool_head_3", (ItemHotToolHead)BATTLEAXE_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item BATTLEAXE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("battleaxe_hot_tool_head_2", (ItemHotToolHead)BATTLEAXE_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item BATTLEAXE_HOT_TOOL_HEAD = new ItemHotToolHead("battleaxe_hot_tool_head", (ItemHotToolHead)BATTLEAXE_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_5 = new ItemHotToolHead("zweihander_hot_tool_head_5", null, 5, true, true);
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_4 = new ItemHotToolHead("zweihander_hot_tool_head_4", (ItemHotToolHead)ZWEIHANDER_HOT_TOOL_HEAD_5, 4, false, true);
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_3 = new ItemHotToolHead("zweihander_hot_tool_head_3", (ItemHotToolHead)ZWEIHANDER_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD_2 = new ItemHotToolHead("zweihander_hot_tool_head_2", (ItemHotToolHead)ZWEIHANDER_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item ZWEIHANDER_HOT_TOOL_HEAD = new ItemHotToolHead("zweihander_hot_tool_head", (ItemHotToolHead)ZWEIHANDER_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item NODACHI_HOT_TOOL_HEAD_4 = new ItemHotToolHead("nodachi_hot_tool_head_4", null, 4, true, true);
	public static final Item NODACHI_HOT_TOOL_HEAD_3 = new ItemHotToolHead("nodachi_hot_tool_head_3", (ItemHotToolHead)NODACHI_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item NODACHI_HOT_TOOL_HEAD_2 = new ItemHotToolHead("nodachi_hot_tool_head_2", (ItemHotToolHead)NODACHI_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item NODACHI_HOT_TOOL_HEAD = new ItemHotToolHead("nodachi_hot_tool_head", (ItemHotToolHead)NODACHI_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item SABRE_HOT_TOOL_HEAD_4 = new ItemHotToolHead("sabre_hot_tool_head_4", null, 4, true, true);
	public static final Item SABRE_HOT_TOOL_HEAD_3 = new ItemHotToolHead("sabre_hot_tool_head_3", (ItemHotToolHead)SABRE_HOT_TOOL_HEAD_4, 3, false, true);
	public static final Item SABRE_HOT_TOOL_HEAD_2 = new ItemHotToolHead("sabre_hot_tool_head_2", (ItemHotToolHead)SABRE_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item SABRE_HOT_TOOL_HEAD = new ItemHotToolHead("sabre_hot_tool_head", (ItemHotToolHead)SABRE_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item MAKHAIRA_HOT_TOOL_HEAD_3 = new ItemHotToolHead("makhaira_hot_tool_head_3", null, 3, true, true);
	public static final Item MAKHAIRA_HOT_TOOL_HEAD_2 = new ItemHotToolHead("makhaira_hot_tool_head_2", (ItemHotToolHead)MAKHAIRA_HOT_TOOL_HEAD_3, 2, false, true);
	public static final Item MAKHAIRA_HOT_TOOL_HEAD = new ItemHotToolHead("makhaira_hot_tool_head", (ItemHotToolHead)MAKHAIRA_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item SPEAR_HOT_TOOL_HEAD_2 = new ItemHotToolHead("spear_hot_tool_head_2", null, 2, true, true);
	public static final Item SPEAR_HOT_TOOL_HEAD = new ItemHotToolHead("spear_hot_tool_head", (ItemHotToolHead)SPEAR_HOT_TOOL_HEAD_2, 1, false, true);
	
	public static final Item CHAIN_RING = new ItemHotToolHead("chain_ring", null, 1, true, false);
	
	//Armor
	public static final Item HELMET_LEATHER = new ArmorBonusesBase("helmet_leather", ArmorTypes.SOFT, ArmorMaterial.LEATHER, 1, 0.0D, 1, EntityEquipmentSlot.HEAD, 0.5D, 0D, 1);
	public static final Item CHESTPLATE_LEATHER = new ArmorBonusesBase("chestplate_leather", ArmorTypes.SOFT, ArmorMaterial.LEATHER, 1, 0.0D, 1, EntityEquipmentSlot.CHEST, 1.125D, 0D, 0D, 1);
	public static final Item LEGGINGS_LEATHER = new ArmorBonusesBase("leggings_leather", ArmorTypes.SOFT, ArmorMaterial.LEATHER, 2, 0.0D, 1, EntityEquipmentSlot.LEGS, 1.125D, 0D, 1);
	public static final Item BOOTS_LEATHER = new ArmorBonusesBase("boots_leather", ArmorTypes.SOFT, ArmorMaterial.LEATHER, 1, 0.0D, 1, EntityEquipmentSlot.FEET, 0.5D, 0D, 1);
	
	public static final Item CHAIN_HELMET_IRON = new ArmorBonusesBase("chain_helmet_iron", ArmorTypes.CHAIN, CAMAT_IRON, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 1.5D, -0.001255584375D, 2);
	public static final Item CHAIN_CHESTPLATE_IRON = new ArmorBonusesBase("chain_chestplate_iron", ArmorTypes.CHAIN, CAMAT_IRON, 1, 0.0, 2, EntityEquipmentSlot.CHEST, 2.125D, 1.406D, -0.006277921875D, 2);
	public static final Item CHAIN_LEGGINGS_IRON = new ArmorBonusesBase("chain_leggings_iron", ArmorTypes.CHAIN, CAMAT_IRON, 2, 0.0, 2, EntityEquipmentSlot.LEGS, 2.125D, -0.006277921875D, 2);
	public static final Item CHAIN_BOOTS_IRON = new ArmorBonusesBase("chain_boots_iron", ArmorTypes.CHAIN, CAMAT_IRON, 1, 0.0, 2, EntityEquipmentSlot.FEET, 1.5D, -0.001255584375D, 2);
	
	public static final Item CHAIN_HELMET_GOLD = new ArmorBonusesBase("chain_helmet_golden", ArmorTypes.CHAIN, CAMAT_GOLD, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 1.5D, -0.006120555D, 2);
	public static final Item CHAIN_CHESTPLATE_GOLD = new ArmorBonusesBase("chain_chestplate_golden", ArmorTypes.CHAIN, CAMAT_GOLD, 1, 0.0D, 2, EntityEquipmentSlot.CHEST, 2.125D, 3.084D, -0.01224111D, 2);
	public static final Item CHAIN_LEGGINGS_GOLD = new ArmorBonusesBase("chain_leggings_golden", ArmorTypes.CHAIN, CAMAT_GOLD, 2, 0.0D, 2, EntityEquipmentSlot.LEGS, 2.125D, -0.01224111D, 2);
	public static final Item CHAIN_BOOTS_GOLD = new ArmorBonusesBase("chain_boots_golden", ArmorTypes.CHAIN, CAMAT_GOLD, 1, 0.0D, 2, EntityEquipmentSlot.FEET, 1.5D, -0.006120555D, 2);
	
	public static final Item DIAMOND_STUDDED_LEATHER_HELMET = new ArmorBonusesBase("diamond_studded_leather_helmet", ArmorTypes.STUDDED, CAMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.00185558629D, 6);
	public static final Item DIAMOND_STUDDED_LEATHER_CHESTPLATE = new ArmorBonusesBase("diamond_studded_leather_chestplate", ArmorTypes.STUDDED, CAMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.CHEST, 5.75D, 1.546D, -0.00489276479D, 6);
	public static final Item DIAMOND_STUDDED_LEATHER_LEGGINGS = new ArmorBonusesBase("diamond_studded_leather_leggings", ArmorTypes.STUDDED, CAMAT_DIAMOND, 2, 0.0625D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.00489276479D, 6);
	public static final Item DIAMOND_STUDDED_LEATHER_BOOTS = new ArmorBonusesBase("diamond_studded_leather_boots", ArmorTypes.STUDDED, CAMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.FEET, 5.0D, -0.00185558629D, 6);
	
	public static final Item CHAIN_HELMET_COPPER = new ArmorBonusesBase("chain_helmet_copper", ArmorTypes.CHAIN, CAMAT_COPPER, 1, 0.0D, 3, EntityEquipmentSlot.HEAD, 2.5D, -0.00141161166D, 3);
	public static final Item CHAIN_CHESTPLATE_COPPER = new ArmorBonusesBase("chain_chestplate_copper", ArmorTypes.CHAIN, CAMAT_COPPER, 1, 0.0D, 3,  EntityEquipmentSlot.CHEST, 3.125D, 1.129D, -0.00705939759D, 3);
	public static final Item CHAIN_LEGGINGS_COPPER = new ArmorBonusesBase("chain_leggings_copper", ArmorTypes.CHAIN, CAMAT_COPPER, 2, 0.0D, 3,  EntityEquipmentSlot.LEGS, 3.125D, -0.00705939759D, 3);
	public static final Item CHAIN_BOOTS_COPPER = new ArmorBonusesBase("chain_boots_copper", ArmorTypes.CHAIN, CAMAT_COPPER, 1, 0.0D, 3,  EntityEquipmentSlot.FEET, 2.5D, -0.00141161166D, 3);
	
	public static final Item CHAIN_HELMET_SILVER = new ArmorBonusesBase("chain_helmet_silver", ArmorTypes.CHAIN, CAMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.HEAD, 3.5D, -0.001657371375D, 4);
	public static final Item CHAIN_CHESTPLATE_SILVER = new ArmorBonusesBase("chain_chestplate_silver", ArmorTypes.CHAIN, CAMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.CHEST, 4.125D, 1.547D, -0.008286856875D, 4);
	public static final Item CHAIN_LEGGINGS_SILVER = new ArmorBonusesBase("chain_leggings_silver", ArmorTypes.CHAIN, CAMAT_SILVER, 2, 0.0417D, 4, EntityEquipmentSlot.LEGS, 4.125D, -0.008286856875D, 4);
	public static final Item CHAIN_BOOTS_SILVER = new ArmorBonusesBase("chain_boots_silver", ArmorTypes.CHAIN, CAMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.FEET, 3.5D, -0.001657371375D, 4);
	
	public static final Item CHAIN_HELMET_BRONZE = new ArmorBonusesBase("chain_helmet_bronze", ArmorTypes.CHAIN, CAMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.HEAD, 3.5D, -0.003024786465D, 4);
	public static final Item CHAIN_CHESTPLATE_BRONZE = new ArmorBonusesBase("chain_chestplate_bronze", ArmorTypes.CHAIN, CAMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.CHEST, 4.125D, 2.823D, -0.0151261421535D, 4);
	public static final Item CHAIN_LEGGINGS_BRONZE = new ArmorBonusesBase("chain_leggings_bronze", ArmorTypes.CHAIN, CAMAT_BRONZE, 2, 0.1291D, 4, EntityEquipmentSlot.LEGS, 4.125D, -0.0151261421535D, 4);
	public static final Item CHAIN_BOOTS_BRONZE = new ArmorBonusesBase("chain_boots_bronze", ArmorTypes.CHAIN, CAMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.FEET, 3.5D, -0.003024786465D, 4);
	
	public static final Item CHAIN_HELMET_PLATINUM = new ArmorBonusesBase("chain_helmet_platinum", ArmorTypes.CHAIN, CAMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.HEAD, 4.5D, -0.003393091215D, 5);
	public static final Item CHAIN_CHESTPLATE_PLATINUM = new ArmorBonusesBase("chain_chestplate_platinum", ArmorTypes.CHAIN, CAMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.CHEST, 5.125D, 3.801D, -0.016964116785D, 5);
	public static final Item CHAIN_LEGGINGS_PLATINUM = new ArmorBonusesBase("chain_leggings_platinum", ArmorTypes.CHAIN, CAMAT_PLATINUM, 2, 0.1667D, 5, EntityEquipmentSlot.LEGS, 5.125D, -0.016964116785D, 5);
	public static final Item CHAIN_BOOTS_PLATINUM = new ArmorBonusesBase("chain_boots_platinum", ArmorTypes.CHAIN, CAMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.FEET, 4.5D, -0.003393091215D, 5);
	
	public static final Item CHAIN_HELMET_STEEL = new ArmorBonusesBase("chain_helmet_steel", ArmorTypes.CHAIN, CAMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.HEAD, 4.5D, -0.001255584375D, 5);
	public static final Item CHAIN_CHESTPLATE_STEEL = new ArmorBonusesBase("chain_chestplate_steel", ArmorTypes.CHAIN, CAMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.CHEST, 5.125D, 1.875D, -0.006277921875D, 5);
	public static final Item CHAIN_LEGGINGS_STEEL = new ArmorBonusesBase("chain_leggings_steel", ArmorTypes.CHAIN, CAMAT_STEEL, 2, 0.0295D, 5, EntityEquipmentSlot.LEGS, 5.125D, -0.006277921875D, 5);
	public static final Item CHAIN_BOOTS_STEEL = new ArmorBonusesBase("chain_boots_steel", ArmorTypes.CHAIN, CAMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.FEET, 4.5D, -0.001255584375D, 5);
	
	public static final Item CHAIN_HELMET_OBSIDIAN = new ArmorBonusesBase("chain_helmet_obsidian", ArmorTypes.CHAIN, CAMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.HEAD, 6.25D, -0.000881922465D, 7);
	public static final Item CHAIN_CHESTPLATE_OBSIDIAN = new ArmorBonusesBase("chain_chestplate_obsidian", ArmorTypes.CHAIN, CAMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.CHEST, 7.0D, 1.645D, -0.004408273035D, 7);
	public static final Item CHAIN_LEGGINGS_OBSIDIAN = new ArmorBonusesBase("chain_leggings_obsidian", ArmorTypes.CHAIN, CAMAT_OBSIDIAN, 2, 0.0295D, 7, EntityEquipmentSlot.LEGS, 7.0D, -0.004408273035D, 7);
	public static final Item CHAIN_BOOTS_OBSIDIAN = new ArmorBonusesBase("chain_boots_obsidian", ArmorTypes.CHAIN, CAMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.FEET, 6.25D, -0.000881922465D, 7);
	
	public static final Item CHAIN_HELMET_KOBOLD = new ArmorBonusesBase("chain_helmet_kobold", ArmorTypes.CHAIN, CAMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 2.5D, -0.00120000384D, 2);
	public static final Item CHAIN_CHESTPLATE_KOBOLD = new ArmorBonusesBase("chain_chestplate_kobold", ArmorTypes.CHAIN, CAMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.CHEST, 3.125D, 0.921D, -0.00575760771D, 2);
	public static final Item CHAIN_LEGGINGS_KOBOLD = new ArmorBonusesBase("chain_leggings_kobold", ArmorTypes.CHAIN, CAMAT_KOBOLD, 2, 0.0D, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00575760771D, 2);
	public static final Item CHAIN_BOOTS_KOBOLD = new ArmorBonusesBase("chain_boots_kobold", ArmorTypes.CHAIN, CAMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.FEET, 2.5D, -0.00120000384D, 2);
	
	public static final Item CHAIN_HELMET_SHADOW_PLATINUM = new ArmorBonusesBase("chain_helmet_shadow_platinum", ArmorTypes.CHAIN, CAMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.002700678285D, 6);
	public static final Item CHAIN_CHESTPLATE_SHADOW_PLATINUM = new ArmorBonusesBase("chain_chestplate_shadow_platinum", ArmorTypes.CHAIN, CAMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.CHEST, 5.75D, 3.781D, -0.013504730715D, 6);
	public static final Item CHAIN_LEGGINGS_SHADOW_PLATINUM = new ArmorBonusesBase("chain_leggings_shadow_platinum", ArmorTypes.CHAIN, CAMAT_SHADOW_PLATINUM, 2, 0.1628D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.013504730715D, 6);
	public static final Item CHAIN_BOOTS_SHADOW_PLATINUM = new ArmorBonusesBase("chain_boots_shadow_platinum", ArmorTypes.CHAIN, CAMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.FEET, 5.0D, -0.002700678285D, 6);
	
	public static final Item CHAIN_HELMET_FROST_STEEL = new ArmorBonusesBase("chain_helmet_frost_steel", ArmorTypes.CHAIN, CAMAT_FROST_STEEL, 1, 0.0938D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.00150134409D, 6);
	public static final Item CHAIN_CHESTPLATE_FROST_STEEL = new ArmorBonusesBase("chain_chestplate_frost_steel", ArmorTypes.CHAIN, CAMAT_FROST_STEEL, 2, 0.0938D, 6, EntityEquipmentSlot.CHEST, 5.75D, 2.101D, -0.00750538116D, 6);
	public static final Item CHAIN_LEGGINGS_FROST_STEEL = new ArmorBonusesBase("chain_leggings_frost_steel", ArmorTypes.CHAIN, CAMAT_FROST_STEEL, 2, 0.0938D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.00750538116D, 6);
	public static final Item CHAIN_BOOTS_FROST_STEEL = new ArmorBonusesBase("chain_boots_frost_steel", ArmorTypes.CHAIN, CAMAT_FROST_STEEL, 1, 0.0938D, 6, EntityEquipmentSlot.FEET, 5.0D, -00.00150134409D, 6);
	
	public static final Item CHAIN_HELMET_CRYSTALLITE = new ArmorBonusesBase("chain_helmet_crystallite", ArmorTypes.CHAIN, CAMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.HEAD, 6.25D, -0.00180268434D, 7);
	public static final Item CHAIN_CHESTPLATE_CRYSTALLITE = new ArmorBonusesBase("chain_chestplate_crystallite", ArmorTypes.CHAIN, CAMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.CHEST, 7.0D, 3.365D, -0.00901208241D, 7);
	public static final Item CHAIN_LEGGINGS_CRYSTALLITE = new ArmorBonusesBase("chain_leggings_crystallite", ArmorTypes.CHAIN, CAMAT_CRYSTALLITE, 2, 0.1397D, 7, EntityEquipmentSlot.LEGS, 7.0D, -0.00901208241D, 7);
	public static final Item CHAIN_BOOTS_CRYSTALLITE = new ArmorBonusesBase("chain_boots_crystallite", ArmorTypes.CHAIN, CAMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.FEET, 6.25D, -0.00180268434D, 7);
	
	public static final Item CHAIN_HELMET_DUSKSTEEL = new ArmorBonusesBase("chain_helmet_dusksteel", ArmorTypes.CHAIN, CAMAT_DUSKSTEEL, 1, 0.1207D, 8, EntityEquipmentSlot.HEAD, 7.25D, -0.001350673965D, 8);
	public static final Item CHAIN_CHESTPLATE_DUSKSTEEL = new ArmorBonusesBase("chain_chestplate_dusksteel", ArmorTypes.CHAIN, CAMAT_DUSKSTEEL, 1, 0.1207D, 8, EntityEquipmentSlot.CHEST, 7.75D, 3.781, -0.006752030535D, 8);
	public static final Item CHAIN_LEGGINGS_DUSKSTEEL = new ArmorBonusesBase("chain_leggings_dusksteel", ArmorTypes.CHAIN, CAMAT_DUSKSTEEL, 2, 0.1207D, 8, EntityEquipmentSlot.LEGS, 7.75D, -0.006752030535D, 8);
	public static final Item CHAIN_BOOTS_DUSKSTEEL = new ArmorBonusesBase("chain_boots_dusksteel", ArmorTypes.CHAIN, CAMAT_DUSKSTEEL, 1, 0.1207D, 6, EntityEquipmentSlot.FEET, 7.25D, -0.001350673965D, 8);
	
	public static final Item PLATE_HELMET_IRON = new ArmorBonusesBase("plate_helmet_iron", ArmorTypes.PLATE, ArmorMaterial.IRON, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 1.5D, -0.0016741125D, 2);
	public static final Item PLATE_CHESTPLATE_IRON = new ArmorBonusesBase("plate_chestplate_iron", ArmorTypes.PLATE, ArmorMaterial.IRON, 1, 0.0, 2, EntityEquipmentSlot.CHEST, 2.125D, 1.875D, -0.0083705625D, 2);
	public static final Item PLATE_LEGGINGS_IRON = new ArmorBonusesBase("plate_leggings_iron", ArmorTypes.PLATE, ArmorMaterial.IRON, 2, 0.0, 2, EntityEquipmentSlot.LEGS, 2.125D, -0.0083705625D, 2);
	public static final Item PLATE_BOOTS_IRON = new ArmorBonusesBase("plate_boots_iron", ArmorTypes.PLATE, ArmorMaterial.IRON, 1, 0.0, 2, EntityEquipmentSlot.FEET, 1.5D, -0.0016741125D, 2);
	
	public static final Item PLATE_HELMET_GOLD = new ArmorBonusesBase("plate_helmet_golden", ArmorTypes.PLATE, ArmorMaterial.GOLD, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 1.5D, -0.00816074D, 2);
	public static final Item PLATE_CHESTPLATE_GOLD = new ArmorBonusesBase("plate_chestplate_golden", ArmorTypes.PLATE, ArmorMaterial.GOLD, 1, 0.0D, 2, EntityEquipmentSlot.CHEST, 2.125D, 4.113D, -0.01632148D, 2);
	public static final Item PLATE_LEGGINGS_GOLD = new ArmorBonusesBase("plate_leggings_golden", ArmorTypes.PLATE, ArmorMaterial.GOLD, 2, 0.0D, 2, EntityEquipmentSlot.LEGS, 2.125D, -0.01632148D, 2);
	public static final Item PLATE_BOOTS_GOLD = new ArmorBonusesBase("plate_boots_golden", ArmorTypes.PLATE, ArmorMaterial.GOLD, 1, 0.0D, 2, EntityEquipmentSlot.FEET, 1.5D, -0.00816074D, 2);
	
	public static final Item HELMET_DIAMOND = new ArmorBonusesBase("diamond_studded_steel_helmet", ArmorTypes.STUDDED, AMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.00247411506D, 6);
	public static final Item CHESTPLATE_DIAMOND = new ArmorBonusesBase("diamond_studded_steel_chestplate", ArmorTypes.STUDDED, AMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.CHEST, 5.75D, 2.062D, -0.00652368639D, 6);
	public static final Item LEGGINGS_DIAMOND = new ArmorBonusesBase("diamond_studded_steel_leggings", ArmorTypes.STUDDED, AMAT_DIAMOND, 2, 0.0625D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.00652368639D, 6);
	public static final Item BOOTS_DIAMOND = new ArmorBonusesBase("diamond_studded_steel_boots", ArmorTypes.STUDDED, AMAT_DIAMOND, 1, 0.0625D, 6, EntityEquipmentSlot.FEET, 5.0D, -0.00247411506D, 6);
	
	public static final Item PLATE_HELMET_COPPER = new ArmorBonusesBase("plate_helmet_copper", ArmorTypes.PLATE, AMAT_COPPER, 1, 0.0D, 3, EntityEquipmentSlot.HEAD, 2.5D, -0.00188214888D, 3);
	public static final Item PLATE_CHESTPLATE_COPPER = new ArmorBonusesBase("plate_chestplate_copper", ArmorTypes.PLATE, AMAT_COPPER, 1, 0.0D, 3,  EntityEquipmentSlot.CHEST, 3.125D, 1.506D, -0.00941253012D, 3);
	public static final Item PLATE_LEGGINGS_COPPER = new ArmorBonusesBase("plate_leggings_copper", ArmorTypes.PLATE, AMAT_COPPER, 2, 0.0D, 3,  EntityEquipmentSlot.LEGS, 3.125D, -0.00941253012D, 3);
	public static final Item PLATE_BOOTS_COPPER = new ArmorBonusesBase("plate_boots_copper", ArmorTypes.PLATE, AMAT_COPPER, 1, 0.0D, 3,  EntityEquipmentSlot.FEET, 2.5D, -0.00188214888D, 3);
	
	public static final Item PLATE_HELMET_SILVER = new ArmorBonusesBase("plate_helmet_silver", ArmorTypes.PLATE, AMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.HEAD, 3.5D, -0.0022098285D, 4);
	public static final Item PLATE_CHESTPLATE_SILVER = new ArmorBonusesBase("plate_chestplate_silver", ArmorTypes.PLATE, AMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.CHEST, 4.125D, 2.063D, -0.0110491425D, 4);
	public static final Item PLATE_LEGGINGS_SILVER = new ArmorBonusesBase("plate_leggings_silver", ArmorTypes.PLATE, AMAT_SILVER, 2, 0.0417D, 4, EntityEquipmentSlot.LEGS, 4.125D, -0.0110491425D, 4);
	public static final Item PLATE_BOOTS_SILVER = new ArmorBonusesBase("plate_boots_silver", ArmorTypes.PLATE, AMAT_SILVER, 1, 0.0417D, 4, EntityEquipmentSlot.FEET, 3.5D, -0.0022098285D, 4);
	
	public static final Item PLATE_HELMET_BRONZE = new ArmorBonusesBase("plate_helmet_bronze", ArmorTypes.PLATE, AMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.HEAD, 3.5D, -0.00403304862D, 4);
	public static final Item PLATE_CHESTPLATE_BRONZE = new ArmorBonusesBase("plate_chestplate_bronze", ArmorTypes.PLATE, AMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.CHEST, 4.125D, 3.764D, -0.020168189538D, 4);
	public static final Item PLATE_LEGGINGS_BRONZE = new ArmorBonusesBase("plate_leggings_bronze", ArmorTypes.PLATE, AMAT_BRONZE, 2, 0.1291D, 4, EntityEquipmentSlot.LEGS, 4.125D, -0.020168189538D, 4);
	public static final Item PLATE_BOOTS_BRONZE = new ArmorBonusesBase("plate_boots_bronze", ArmorTypes.PLATE, AMAT_BRONZE, 1, 0.1291D, 4, EntityEquipmentSlot.FEET, 3.5D, -0.00403304862D, 4);
	
	public static final Item PLATE_HELMET_PLATINUM = new ArmorBonusesBase("plate_helmet_platinum", ArmorTypes.PLATE, AMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.HEAD, 4.5D, -0.00452412162D, 5);
	public static final Item PLATE_CHESTPLATE_PLATINUM = new ArmorBonusesBase("plate_chestplate_platinum", ArmorTypes.PLATE, AMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.CHEST, 5.125D, 5.067D, -0.02261882238D, 5);
	public static final Item PLATE_LEGGINGS_PLATINUM = new ArmorBonusesBase("plate_leggings_platinum", ArmorTypes.PLATE, AMAT_PLATINUM, 2, 0.1667D, 5, EntityEquipmentSlot.LEGS, 5.125D, -0.02261882238D, 5);
	public static final Item PLATE_BOOTS_PLATINUM = new ArmorBonusesBase("plate_boots_platinum", ArmorTypes.PLATE, AMAT_PLATINUM, 1, 0.1667D, 5, EntityEquipmentSlot.FEET, 4.5D, -0.00452412162D, 5);
	
	public static final Item PLATE_HELMET_STEEL = new ArmorBonusesBase("plate_helmet_steel", ArmorTypes.PLATE, AMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.HEAD, 4.5D, -0.0016741125D, 5);
	public static final Item PLATE_CHESTPLATE_STEEL = new ArmorBonusesBase("plate_chestplate_steel", ArmorTypes.PLATE, AMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.CHEST, 5.125D, 1.875D, -0.0083705625D, 5);
	public static final Item PLATE_LEGGINGS_STEEL = new ArmorBonusesBase("plate_leggings_steel", ArmorTypes.PLATE, AMAT_STEEL, 2, 0.0295D, 5, EntityEquipmentSlot.LEGS, 5.125D, -0.0083705625D, 5);
	public static final Item PLATE_BOOTS_STEEL = new ArmorBonusesBase("plate_boots_steel", ArmorTypes.PLATE, AMAT_STEEL, 1, 0.0295D, 5, EntityEquipmentSlot.FEET, 4.5D, -0.0016741125D, 5);
	
	public static final Item PLATE_HELMET_OBSIDIAN = new ArmorBonusesBase("plate_helmet_obsidian", ArmorTypes.PLATE, AMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.HEAD, 6.25D, -0.00117589662D, 7);
	public static final Item PLATE_CHESTPLATE_OBSIDIAN = new ArmorBonusesBase("plate_chestplate_obsidian", ArmorTypes.PLATE, AMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.CHEST, 7.0D, 2.194D, -0.00587769738D, 7);
	public static final Item PLATE_LEGGINGS_OBSIDIAN = new ArmorBonusesBase("plate_leggings_obsidian", ArmorTypes.PLATE, AMAT_OBSIDIAN, 2, 0.0295D, 7, EntityEquipmentSlot.LEGS, 7.0D, -0.00587769738D, 7);
	public static final Item PLATE_BOOTS_OBSIDIAN = new ArmorBonusesBase("plate_boots_obsidian", ArmorTypes.PLATE, AMAT_OBSIDIAN, 1, 0.0295D, 7, EntityEquipmentSlot.FEET, 6.25D, -0.00117589662D, 7);
	
	public static final Item PLATE_HELMET_KOBOLD = new ArmorBonusesBase("plate_helmet_kobold", ArmorTypes.PLATE, AMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.HEAD, 2.5D, -0.00160000512D, 2);
	public static final Item PLATE_CHESTPLATE_KOBOLD = new ArmorBonusesBase("plate_chestplate_kobold", ArmorTypes.PLATE, AMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.CHEST, 3.125D, 1.228D, -0.00767681028D, 2);
	public static final Item PLATE_LEGGINGS_KOBOLD = new ArmorBonusesBase("plate_leggings_kobold", ArmorTypes.PLATE, AMAT_KOBOLD, 2, 0.0D, 2, EntityEquipmentSlot.LEGS, 3.125D, -0.00767681028D, 2);
	public static final Item PLATE_BOOTS_KOBOLD = new ArmorBonusesBase("plate_boots_kobold", ArmorTypes.PLATE, AMAT_KOBOLD, 1, 0.0D, 2, EntityEquipmentSlot.FEET, 2.5D, -0.00160000512D, 2);
	
	public static final Item PLATE_HELMET_SHADOW_PLATINUM = new ArmorBonusesBase("plate_helmet_shadow_platinum", ArmorTypes.PLATE, AMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.00360090438D, 6);
	public static final Item PLATE_CHESTPLATE_SHADOW_PLATINUM = new ArmorBonusesBase("plate_chestplate_shadow_platinum", ArmorTypes.PLATE, AMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.CHEST, 5.75D, 5.042D, -0.01800630762D, 6);
	public static final Item PLATE_LEGGINGS_SHADOW_PLATINUM = new ArmorBonusesBase("plate_leggings_shadow_platinum", ArmorTypes.PLATE, AMAT_SHADOW_PLATINUM, 2, 0.1628D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.01800630762D, 6);
	public static final Item PLATE_BOOTS_SHADOW_PLATINUM = new ArmorBonusesBase("plate_boots_shadow_platinum", ArmorTypes.PLATE, AMAT_SHADOW_PLATINUM, 1, 0.1628D, 6, EntityEquipmentSlot.FEET, 5.0D, -0.00360090438D, 6);
	
	public static final Item PLATE_HELMET_FROST_STEEL = new ArmorBonusesBase("plate_helmet_frost_steel", ArmorTypes.PLATE, AMAT_FROST_STEEL, 1, 0.0938D, 6, EntityEquipmentSlot.HEAD, 5.0D, -0.00200179212D, 6);
	public static final Item PLATE_CHESTPLATE_FROST_STEEL = new ArmorBonusesBase("plate_chestplate_frost_steel", ArmorTypes.PLATE, AMAT_FROST_STEEL, 2, 0.0938D, 6, EntityEquipmentSlot.CHEST, 5.75D, 2.802D, -0.01000717488D, 6);
	public static final Item PLATE_LEGGINGS_FROST_STEEL = new ArmorBonusesBase("plate_leggings_frost_steel", ArmorTypes.PLATE, AMAT_FROST_STEEL, 2, 0.0938D, 6, EntityEquipmentSlot.LEGS, 5.75D, -0.01000717488D, 6);
	public static final Item PLATE_BOOTS_FROST_STEEL = new ArmorBonusesBase("plate_boots_frost_steel", ArmorTypes.PLATE, AMAT_FROST_STEEL, 1, 0.0938D, 6, EntityEquipmentSlot.FEET, 5.0D, -00.00200179212D, 6);
	
	public static final Item PLATE_HELMET_CRYSTALLITE = new ArmorBonusesBase("plate_helmet_crystallite", ArmorTypes.PLATE, AMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.HEAD, 6.25D, -0.00240357912D, 7);
	public static final Item PLATE_CHESTPLATE_CRYSTALLITE = new ArmorBonusesBase("plate_chestplate_crystallite", ArmorTypes.PLATE, AMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.CHEST, 7.0D, 4.486D, -0.01201610988D, 7);
	public static final Item PLATE_LEGGINGS_CRYSTALLITE = new ArmorBonusesBase("plate_leggings_crystallite", ArmorTypes.PLATE, AMAT_CRYSTALLITE, 2, 0.1397D, 7, EntityEquipmentSlot.LEGS, 7.0D, -0.01201610988D, 7);
	public static final Item PLATE_BOOTS_CRYSTALLITE = new ArmorBonusesBase("plate_boots_crystallite", ArmorTypes.PLATE, AMAT_CRYSTALLITE, 1, 0.1397D, 7, EntityEquipmentSlot.FEET, 6.25D, -0.00240357912D, 7);
	
	public static final Item PLATE_HELMET_DUSKSTEEL = new ArmorBonusesBase("plate_helmet_dusksteel", ArmorTypes.PLATE, AMAT_DUSKSTEEL, 1, 0.1207D, 8, EntityEquipmentSlot.HEAD, 7.25D, -0.00180089862D, 8);
	public static final Item PLATE_CHESTPLATE_DUSKSTEEL = new ArmorBonusesBase("plate_chestplate_dusksteel", ArmorTypes.PLATE, AMAT_DUSKSTEEL, 1, 0.1207D, 8, EntityEquipmentSlot.CHEST, 7.75D, 5.042D, -0.00900270738D, 8);
	public static final Item PLATE_LEGGINGS_DUSKSTEEL = new ArmorBonusesBase("plate_leggings_dusksteel", ArmorTypes.PLATE, AMAT_DUSKSTEEL, 2, 0.1207D, 8, EntityEquipmentSlot.LEGS, 7.75D, -0.00900270738D, 8);
	public static final Item PLATE_BOOTS_DUSKSTEEL = new ArmorBonusesBase("plate_boots_dusksteel", ArmorTypes.PLATE, AMAT_DUSKSTEEL, 1, 0.1207D, 6, EntityEquipmentSlot.FEET, 7.25D, -0.00180089862D, 8);
}
