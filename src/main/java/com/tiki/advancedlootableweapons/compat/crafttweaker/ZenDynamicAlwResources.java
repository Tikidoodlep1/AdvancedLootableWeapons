package com.tiki.advancedlootableweapons.compat.crafttweaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.teamacronymcoders.contenttweaker.api.ctobjects.resourcelocation.CTResourceLocation;
import com.teamacronymcoders.contenttweaker.modules.vanilla.tileentity.TileEntityRepresentation;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.advancedlootableweapons.AlwTweaker")
@ZenRegister
public class ZenDynamicAlwResources {

	
	public static final HashMap<Block, Set<Item>> fuelLists = new HashMap<Block, Set<Item>>();
	public static final HashMap<Block, ForgingGuiRepresentation> guiLists = new HashMap<Block, ForgingGuiRepresentation>();
	public static final String IGNITION_ORE = "ore:igniter";
	public static final String IGNITION_UPGRADE_ORE = "ore:igniter_upgrade";
	public static JsonObject FORGE_MODEL;
	public static JsonObject ADVANCED_FORGE_MODEL;
	
	public static void init() {
		try {
			File ForgeFile = new File(ZenDynamicAlwResources.class.getClassLoader().getResource("assets/advancedlootableweapons/models/block/block_forge.json").getFile());
			
			FORGE_MODEL = new JsonParser().parse(new JsonReader(new BufferedReader(new InputStreamReader(new FileInputStream(ForgeFile), "Cp1252")))).getAsJsonObject();
		} catch (JsonIOException|JsonSyntaxException|FileNotFoundException|UnsupportedEncodingException e) {
			Alw.logger.error("Unable to load forge model on initialization. This will result in ContentTweaker made forges having broken models. Please ensure you're using the latest version of ALW. If you are, please report this to the mod author!", e);
		}
		
		try {
			File AdvForgeFile = new File(ZenDynamicAlwResources.class.getClassLoader().getResource("assets/advancedlootableweapons/models/block/block_advanced_forge.json").getFile());
			ADVANCED_FORGE_MODEL = new JsonParser().parse(new JsonReader(new BufferedReader(new InputStreamReader(new FileInputStream(AdvForgeFile), "Cp1252")))).getAsJsonObject();
		} catch (JsonIOException|JsonSyntaxException|FileNotFoundException|UnsupportedEncodingException e) {
			Alw.logger.error("Unable to load advanced forge model on initialization. This will result in ContentTweaker made forges having broken models. Please ensure you're using the latest version of ALW. If you are, please report this to the mod author!", e);
		}
	}
	
	@ZenMethod
	public static boolean setGuiForAnvil(String block, CTResourceLocation texture, int[] inputs, int[] output) {
		if(inputs.length != 4 || output.length != 2) {
			Alw.logger.error("An instance of forging gui has been given an insufficient amount of slot coordinates, not creating GUI.");
			return false;
		}
		
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(forge == null) {
			Alw.logger.error("Unable to set gui for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return false;
		}
		
		ForgingGuiRepresentation gui = new ForgingGuiRepresentation(texture.getInternal(), inputs, output);
		guiLists.put(forge, gui);
		return true;
	}
	
	@ZenMethod
	public static boolean setFuelListForBlock(IIngredient[] ingrs, String block) {
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(forge == null) {
			Alw.logger.error("Unable to set fuel list for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return false;
		}
		
		HashSet<Item> fuelSet = new HashSet<Item>();
		for(IIngredient i : ingrs) {
			List<IItemStack> stacks = i.getItems();
			if(stacks != null) {
				for(IItemStack stack : stacks) {
					if(stack.getInternal() instanceof ItemStack) {
						Item item = ((ItemStack)stack.getInternal()).getItem();
						fuelSet.add(item);
					}
				}
			}
		}
		fuelLists.put(forge, fuelSet);
		return true;
	}
	
	@ZenMethod
	public static boolean addIgniterItem(IItemStack stack) {
		if(stack.getInternal() instanceof Item) {
			OreDictionary.registerOre(IGNITION_ORE, (Item)stack.getInternal());
			return true;
		}else if(stack.getInternal() instanceof ItemStack) {
			OreDictionary.registerOre(IGNITION_ORE, (ItemStack)stack.getInternal());
			return true;
		}
		return false;
	}
	
	@ZenMethod
	public static boolean addIgniterUpgradeItem(IItemStack stack) {
		if(stack.getInternal() instanceof Item) {
			OreDictionary.registerOre(IGNITION_UPGRADE_ORE, (Item)stack.getInternal());
			return true;
		}else if(stack.getInternal() instanceof ItemStack) {
			OreDictionary.registerOre(IGNITION_UPGRADE_ORE, (ItemStack)stack.getInternal());
			return true;
		}
		return false;
	}
	
	public static boolean setFuelListForBlock(Block block, Set<Item> fuel) {
		if(block == null) {
			return false;
		}
		
		fuelLists.put(block, fuel);
		return true;
	}
	
	public static Set<Item> getFuelListForBlock(Block key) {
		return fuelLists.get(key);
	}
	
	@ZenMethod
	@ParametersAreNonnullByDefault
	public static ForgeRepresentation createForge(String name, boolean useOneSlot, boolean usesAdvancedForgeModel, boolean needsFuel, boolean needsIgnition) {
		ForgeRepresentation forge = new ForgeRepresentation(name, useOneSlot, usesAdvancedForgeModel, needsFuel, needsIgnition);
		forge.setTextureLocation(CTResourceLocation.create("contenttweaker:blocks/" + name + ".png"));
		//forge.tileEntityRepresentation = new TileEntityRepresentation("tileentity" + forge.getUnlocalizedName());
		return forge;
	}
	
}