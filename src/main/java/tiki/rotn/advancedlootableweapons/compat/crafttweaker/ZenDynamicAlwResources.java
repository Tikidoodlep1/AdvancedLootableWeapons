package tiki.rotn.advancedlootableweapons.compat.crafttweaker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.teamacronymcoders.contenttweaker.api.ctobjects.resourcelocation.CTResourceLocation;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.oredict.IOreDictEntry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.init.ItemInit;
import tiki.rotn.advancedlootableweapons.recipes.DrumItemRecipe;
import tiki.rotn.advancedlootableweapons.recipes.DrumQuenchingRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ForgeArmorBindingRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ForgeArmorPlateRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ForgeGeneralCaseRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ForgeToolRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ShapelessChainLinkRecipe;
import tiki.rotn.advancedlootableweapons.recipes.ShapelessOneSlotRecipes;

@ZenClass("mods.advancedlootableweapons.AlwTweaker")
@ZenRegister
public class ZenDynamicAlwResources {

	public static final ResourceLocation FORGE_WEAPON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon_new.png");

	public static final HashMap<Block, Set<Item>> fuelLists = new HashMap<Block, Set<Item>>();
	public static final HashMap<Block, ForgingGuiRepresentation> guiLists = new HashMap<Block, ForgingGuiRepresentation>();
	public static final HashMap<Block, Set<Item>> matLists = new HashMap<Block, Set<Item>>();
	public static final String IGNITION_ORE = "ore:igniter";
	public static final String IGNITION_UPGRADE_ORE = "ore:igniter_upgrade";
	public static JsonObject FORGE_MODEL;
	public static JsonObject ADVANCED_FORGE_MODEL;
	public static final short FORGE_MODEL_TYPE = 0;
	public static final short ADVANCED_FORGE_MODEL_TYPE = 1;
	
	@SideOnly(Side.CLIENT)
	public static void init() {
		try {
			InputStream ForgeFile = ZenDynamicAlwResources.class.getClassLoader().getResourceAsStream("assets/advancedlootableweapons/models/block/block_forge.json");
			FORGE_MODEL = new JsonParser().parse(new JsonReader(new BufferedReader(new InputStreamReader(ForgeFile, "Cp1252")))).getAsJsonObject();
		} catch (JsonIOException|JsonSyntaxException|UnsupportedEncodingException e) {
			Alw.logger.error("Unable to load forge model on initialization. This will result in ContentTweaker made forges having broken models. Please ensure you're using the latest version of ALW. If you are, please report this to the mod author!", e);
		}
		
		try {
			InputStream AdvForgeFile = ZenDynamicAlwResources.class.getClassLoader().getResourceAsStream("assets/advancedlootableweapons/models/block/block_advanced_forge.json");
			ADVANCED_FORGE_MODEL = new JsonParser().parse(new JsonReader(new BufferedReader(new InputStreamReader(AdvForgeFile, "Cp1252")))).getAsJsonObject();
		} catch (JsonIOException|JsonSyntaxException|UnsupportedEncodingException e) {
			Alw.logger.error("Unable to load advanced forge model on initialization. This will result in ContentTweaker made forges having broken models. Please ensure you're using the latest version of ALW. If you are, please report this to the mod author!", e);
		}
	}
	
	@ZenMethod
	public static boolean addArmorBindingRecipe(String name, String group, String button, IItemStack output, IIngredient[] inputs, String block) {
		Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					
					IRecipe recipe = new ForgeArmorBindingRecipe(new ResourceLocation(group), button, input, out, b);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addToolHeadRecipe(String name, String button, int exp, IItemStack output, IIngredient[] inputs, String block) {
		Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					
					IRecipe recipe = new ForgeToolHeadRecipe(button, input, exp, out, b);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addToolRecipe(String name, String button, int exp, IItemStack output, IIngredient[] inputs, String block) {
		Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					
					IRecipe recipe = new ForgeToolRecipe(button, input, exp, out, b);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addArmorPlateRecipe(String name, String button, int exp, IItemStack output, IIngredient[] inputs, String block) {
		Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					
					IRecipe recipe = new ForgeArmorPlateRecipe(button, input, exp, out, b);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addGeneralForgingRecipe(String name, String button, int exp, IItemStack output, IIngredient[] inputs, String block) {
		Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					
					IRecipe recipe = new ForgeGeneralCaseRecipe(button, input, exp, out, b);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addTanningRackRecipe(String name, String group, IItemStack output, IIngredient[] inputs) {
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					IRecipe recipe = new ShapelessOneSlotRecipes(new ResourceLocation(group), input, out, BlockInit.tanning_rack);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addMillRecipe(String name, String group, IItemStack output, IIngredient[] inputs) {
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY) {
					IRecipe recipe = new ShapelessOneSlotRecipes(new ResourceLocation(group), input, out, BlockInit.mill);
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addDrumRecipe(String name, String group, IItemStack output, IIngredient main, ILiquidStack fluid, IIngredient additive, int ticks) {
		Ingredient input = Ingredient.EMPTY;
		Ingredient add = Ingredient.EMPTY;
		FluidStack fluidStack = null;
		ItemStack out = ItemStack.EMPTY;
		
		if(main instanceof IItemStack) {
			if(main.getInternal() instanceof ItemStack) {
				input = Ingredient.fromStacks((ItemStack)main.getInternal());
			}else if(main.getInternal() instanceof Item) {
				input = Ingredient.fromItem((Item)main.getInternal());
			}
		}else if(main instanceof IOreDictEntry) {
			NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) main).getName());
			input = Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0]));
		}
		
		if(additive instanceof IItemStack) {
			if(additive.getInternal() instanceof ItemStack) {
				add = Ingredient.fromStacks((ItemStack)additive.getInternal());
			}else if(additive.getInternal() instanceof Item) {
				add = Ingredient.fromItem((Item)additive.getInternal());
			}
		}else if(additive instanceof IOreDictEntry) {
			NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) additive).getName());
			add = Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0]));
		}
		
		if(fluid.getInternal() instanceof FluidStack) {
			fluidStack = (FluidStack)fluid.getInternal();
		}else if(fluid.getInternal() instanceof Fluid) {
			fluidStack = new FluidStack((Fluid)fluid.getInternal(), Fluid.BUCKET_VOLUME);
		}else if(fluid.getInternal() instanceof Block) {
			Fluid lookup = FluidRegistry.lookupFluidForBlock((Block)fluid.getInternal());
			if(lookup != null) {
				fluidStack = new FluidStack(lookup, Fluid.BUCKET_VOLUME);
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		if(input != Ingredient.EMPTY && add != Ingredient.EMPTY && fluidStack != null && out != ItemStack.EMPTY) {
			IRecipe recipe = new DrumItemRecipe(new ResourceLocation(group), input, fluidStack, add, out, ticks);
			recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
			ForgeRegistries.RECIPES.register(recipe);
			return true;
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addDrumQuenchRecipe(String name, String group, IIngredient main, ILiquidStack fluid, int ticks, boolean needsClay) {
		ItemStack input = ItemStack.EMPTY;
		FluidStack fluidStack = null;
		
		if(main.getInternal() instanceof ItemStack) {
			input = (ItemStack)main.getInternal();
		}
		
		if(fluid.getInternal() instanceof FluidStack) {
			fluidStack = (FluidStack)fluid.getInternal();
		}else if(fluid.getInternal() instanceof Fluid) {
			fluidStack = new FluidStack((Fluid)fluid.getInternal(), Fluid.BUCKET_VOLUME);
		}else if(fluid.getInternal() instanceof Block) {
			Fluid lookup = FluidRegistry.lookupFluidForBlock((Block)fluid.getInternal());
			if(lookup != null) {
				fluidStack = new FluidStack(lookup, Fluid.BUCKET_VOLUME);
			}
		}
		
		if(input != ItemStack.EMPTY && fluidStack != null) {
			IRecipe recipe = new DrumQuenchingRecipe(new ResourceLocation(group), input, fluidStack, ticks, needsClay);
			recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
			ForgeRegistries.RECIPES.register(recipe);
			return true;
		}
		
		return false;
	}
	
	@ZenMethod
	public static boolean addChainLinkRecipe(String name, String group, IItemStack output, IIngredient[] inputs, IItemStack material) {
		NonNullList<Ingredient> input = NonNullList.withSize(2, Ingredient.EMPTY);
		ItemStack out = ItemStack.EMPTY;
		ItemStack mat = ItemStack.EMPTY;
		
		for(int it = 0; it < inputs.length; it++) {
			IIngredient i = inputs[it];
			Object internal = i.getInternal();
			if(i.getItems() != null) {
				List<IItemStack> stackList = i.getItems();
				ItemStack[] stacks = new ItemStack[stackList.size()];
				for(int j = 0; j < stackList.size(); j++) {
					stacks[j] = (ItemStack)stackList.get(j).getInternal();
				}
				input.set(it, Ingredient.fromStacks(stacks));
			}else if(internal instanceof IItemStack) {
				if(((IItemStack)internal).getInternal() instanceof ItemStack) {
					input.set(it, Ingredient.fromStacks( (ItemStack)( (IItemStack)internal ).getInternal() ));
				}
			}else if(internal instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				input.set(it, Ingredient.fromStacks(oreStacks.toArray(new ItemStack[0])));
			}
		}
		
		if(output.getInternal() instanceof ItemStack) {
			out = (ItemStack)output.getInternal();
		}
		
		if(material.getInternal() instanceof ItemStack) {
			mat = (ItemStack)material.getInternal();
		}
		
		for(Ingredient i : input) {
			if(i != Ingredient.EMPTY) {
				if(out != ItemStack.EMPTY && mat != ItemStack.EMPTY) {
					for(Ingredient ingr : input) {
						for(ItemStack st : ingr.getMatchingStacks()) {
							ItemInit.forgeRecipeInputs.add(st.getItem());
						}
					}
					IRecipe recipe = new ShapelessChainLinkRecipe(new ResourceLocation(group), input, out, mat.getItem());
					recipe.setRegistryName(new ResourceLocation(ModInfo.ID, name));
					ForgeRegistries.RECIPES.register(recipe);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@ZenMethod
	public static ForgingGuiRepresentation setGuiForAnvil(String block, CTResourceLocation texture, int[] inputs, int[] output) {
		if(inputs.length != 4 || output.length != 2) {
			Alw.logger.error("An instance of forging gui has been given an insufficient amount of slot coordinates, not creating GUI.");
			return null;
		}
		
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(forge == null) {
			Alw.logger.error("Unable to set gui for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return null;
		}
		
		ForgingGuiRepresentation gui = new ForgingGuiRepresentation(texture.getInternal(), inputs, output);
		guiLists.put(forge, gui);
		return gui;
	}
	
	@ZenMethod
	public static ForgingGuiRepresentation setGuiForAnvil(String block) {
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(forge == null) {
			Alw.logger.error("Unable to set gui for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return null;
		}
		
		ForgingGuiRepresentation gui = new ForgingGuiRepresentation(FORGE_WEAPON_TEXTURES, new int[] {56, 33, 56, 53}, new int[] {114, 43});
		guiLists.put(forge, gui);
		return gui;
	}
	
	@ZenMethod
	public static int[] getButtonPosByNameInDefaultGui(String button) {
		if(button.equalsIgnoreCase("dagger")) {
			return new int[] {1, 1};
		}else if(button.equalsIgnoreCase("kabutowari")) {
			return new int[] {22, 1};
		}else if(button.equalsIgnoreCase("talwar")) {
			return new int[] {64, 1};
		}else if(button.equalsIgnoreCase("rapier")) {
			return new int[] {43, 1};
		}else if(button.equalsIgnoreCase("mace")) {
			return new int[] {1, 22};
		}else if(button.equalsIgnoreCase("cleaver")) {
			return new int[] {22, 22};
		}else if(button.equalsIgnoreCase("staff")) {
			return new int[] {43, 22};
		}else if(button.equalsIgnoreCase("longsword")) {
			return new int[] {64, 22};
		}else if(button.equalsIgnoreCase("kodachi")) {
			return new int[] {1, 43};
		}else if(button.equalsIgnoreCase("battleaxe")) {
			return new int[] {22, 43};
		}else if(button.equalsIgnoreCase("zweihander")) {
			return new int[] {43, 43};
		}else if(button.equalsIgnoreCase("nodachi")) {
			return new int[] {64, 43};
		}else if(button.equalsIgnoreCase("sabre")) {
			return new int[] {1, 64};
		}else if(button.equalsIgnoreCase("makhaira")) {
			return new int[] {22, 64};
		}else if(button.equalsIgnoreCase("spear")) {
			return new int[] {43, 64};
		}else if(button.equalsIgnoreCase("chain")) {
			return new int[] {22, 85};
		}else if(button.equalsIgnoreCase("armor plate")) {
			return new int[] {43, 85};
		}else if(button.equalsIgnoreCase("handle")) {
			return new int[] {64, 64};
		}else if(button.equalsIgnoreCase("forge weapon")) {
			return new int[] {1, 85};
		}
		return new int[] {232, 232};
	}
	
	@ZenMethod
	public static void setFuelListForBlock(IIngredient[] ingrs, String block) {
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(forge == null) {
			Alw.logger.error("Unable to set fuel list for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return;
		}
		
		HashSet<Item> fuelSet = new HashSet<Item>();
		for(IIngredient i : ingrs) {
			if(i instanceof IItemStack) {
				if(i.getInternal() instanceof ItemStack) {
					Item item = ((ItemStack)i.getInternal()).getItem();
					fuelSet.add(item);
				}else if(i.getInternal() instanceof Item) {
					fuelSet.add((Item)i.getInternal());
				}
			}else if(i instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				for(ItemStack ore : oreStacks) {
					fuelSet.add(ore.getItem());
				}
			}
		}
		
		if(!fuelSet.isEmpty()) {
			fuelLists.put(forge, fuelSet);
		}
	}
	
	@ZenMethod
	public static void setMaterialListForBlock(IIngredient[] stacks, String block) {
		Block forge = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		
		if(forge == null) {
			Alw.logger.error("Unable to set material list for block {" + block + "}. {" + block + "} is not in the Forge Block registry.");
			return;
		}
		
		HashSet<Item> matList = new HashSet<Item>();
		for(IIngredient i : stacks) {
			if(i instanceof IItemStack) {
				if(i.getInternal() instanceof ItemStack) {
					Item item = ((ItemStack)i.getInternal()).getItem();
					matList.add(item);
				}else if(i.getInternal() instanceof Item) {
					matList.add((Item)i.getInternal());
				}
			}else if(i instanceof IOreDictEntry) {
				NonNullList<ItemStack> oreStacks = OreDictionary.getOres(((IOreDictEntry) i).getName());
				for(ItemStack ore : oreStacks) {
					matList.add(ore.getItem());
				}
			}
		}
		
		if(!matList.isEmpty()) {
			matLists.put(forge, matList);
		}
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
		if(block == null || fuel == null || fuel.size() == 0) {
			return false;
		}
		
		fuelLists.put(block, fuel);
		return true;
	}
	
	public static Set<Item> getFuelListForBlock(Block key) {
		if(key == null || fuelLists.get(key) == null) {
			return null;
		}
		
		return Collections.unmodifiableSet(fuelLists.get(key));
	}
	
	public static boolean setMatListForBlock(Block block, Set<Item> mats) {
		if(block == null || mats == null || mats.size() == 0) {
			return false;
		}
		
		matLists.put(block, mats);
		return true;
	}
	
	public static Set<Item> getMatListForBlock(Block key) {
		if(key == null || matLists.get(key) == null) {
			return null;
		}
		
		return Collections.unmodifiableSet(matLists.get(key));
	}
	
	@ZenMethod
	public static ForgeRepresentation createForge(String name, boolean useOneSlot, boolean usesAdvancedForgeModel, boolean needsFuel, boolean needsIgnition) {
		ForgeRepresentation forge = new ForgeRepresentation(name, useOneSlot, usesAdvancedForgeModel, needsFuel, needsIgnition);
		forge.setTextureLocation(CTResourceLocation.create("contenttweaker:blocks/" + name + ".png"));
		return forge;
	}
	
}