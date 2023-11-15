package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ForgeArmorPlateRecipe extends ShapelessOreRecipe {
	
	//Ingot to make an armor set: 9.8678
	//Current with Armor Plates: 16
	//Current with Chain Links: 36
	//If chain link recipe gave 6 instead of 2: 12
	//If armor plate recipe gave 3: 16
	
	private final NonNullList<Ingredient> inputs;
	private final String button;
	private final int exp;
	private ItemStack material = ItemStack.EMPTY;
	
	ForgeArmorPlateRecipe(final String button, final NonNullList<Ingredient> inputs, int exp, ItemStack result) {
		super(null, inputs, result);
		this.inputs = inputs;
		this.button = button;
		this.exp = exp;
	}
	
	public NonNullList<ItemStack> getRemainingItems(final NonNullList<ItemStack> inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.size(), ItemStack.EMPTY);
		if(inputs.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.get(0));
		}
		if(inputs.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.get(1));
		}
		return remainingItems;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		if(inputs.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.getStackInSlot(0));
		}
		if(inputs.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.getStackInSlot(1));
		}
		return remainingItems;
	}
	
	public boolean matches(NonNullList<ItemStack> inv, World world) {		
		if(inv.size() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.inputs.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					match1 = true;
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.inputs.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		//System.out.println(match1 + ", " + match2 + ", " + matsMatch);
		return match1 && match2 && matsMatch;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {		
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.inputs.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					match1 = true;
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.inputs.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		//System.out.println(match1 + ", " + match2 + ", " + matsMatch);
		return match1 && match2 && matsMatch;
	}
	
	public ItemStack getCraftingResult(final NonNullList<ItemStack> inv) {
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		this.setMaterial(input1, input2);
		int origCount = this.output.getCount();
		ItemStack result = getModifiedOutput();
		result.setCount(origCount);
		
		if(input1Tag == null) {
			input1Tag = new NBTTagCompound();
		}
		
		input1Tag.setInteger("addedDurability", 0);
		
		if(input1.getItem() instanceof ItemHotToolHead) {
			if(input1Tag.hasKey("addedDurability")) {
				input1Tag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(checkHeat(input1) * 100));
			}else {
				input1Tag.setInteger("addedDurability", (int)(checkHeat(input1) * 100));
			}
			
		}
		
		if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
			double heat2 = checkHeat(input2);
			
			if(input1Tag.hasKey("addedDurability")) {
				input1Tag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(heat2 * 100));
			}else {
				input1Tag.setInteger("addedDurability", (int)(heat2 * 100));
			}
		}
		
		result.setTagCompound(input1Tag);
		
		return result;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		this.setMaterial(input1, input2);
		int origCount = super.getCraftingResult(inv).getCount();
		ItemStack result = getModifiedOutput();
		result.setCount(origCount);
		
		if(input1Tag == null) {
			input1Tag = new NBTTagCompound();
		}
		
		input1Tag.setInteger("addedDurability", 0);
		
		if(input1.getItem() instanceof ItemHotToolHead) {
			if(input1Tag.hasKey("addedDurability")) {
				input1Tag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(checkHeat(input1) * 100));
			}else {
				input1Tag.setInteger("addedDurability", (int)(checkHeat(input1) * 100));
			}
			
		}
		
		if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
			double heat2 = checkHeat(input2);
			
			if(input1Tag.hasKey("addedDurability")) {
				input1Tag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + (int)(heat2 * 100));
			}else {
				input1Tag.setInteger("addedDurability", (int)(heat2 * 100));
			}
		}
		
		result.setTagCompound(input1Tag);
		
		return result;
	}
	
	public String getButton() {
		return this.button;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		
		return map(6000 - heat, 0, 6000, 0.0, 0.5);
	}
	
	private double map(double value, double curRangeLowBound, double curRangeHighBound, double wantRangeLowBound, double wantRangeHighBound) {
		return wantRangeLowBound + (wantRangeHighBound - wantRangeLowBound) * ((value - curRangeLowBound) / (curRangeHighBound - curRangeLowBound));
	}
	
	private ItemStack getModifiedOutput() {
//		System.out.println("Material Registry Name: " + this.material.getItem().getRegistryName());
//		System.out.println("Forge Registry Contains " + (ModInfo.ID + ":armor_plate_" + material.getItem().getRegistryName().getResourcePath()) + ": " + (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(ModInfo.ID + ":armor_plate_" + material.getItem().getRegistryName().getResourcePath()))) );
		if(material != null && material.getItem().getRegistryName().getResourceDomain().equals("advancedlootableweapons") || material.getItem() == Items.IRON_INGOT) {
			if(material.getItem() == ItemInit.INGOT_BRONZE) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_bronze")));
			}else if(material.getItem() == ItemInit.INGOT_COPPER) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_copper")));
			}else if(material.getItem() == ItemInit.INGOT_CRYSTALLITE) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_crystallite")));
			}else if(material.getItem() == ItemInit.INGOT_DUSKSTEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_dusksteel")));
			}else if(material.getItem() == ItemInit.INGOT_FROST_STEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_frost_steel")));
			}else if(material.getItem() == ItemInit.INGOT_KOBOLD) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_kobold")));
			}else if(material.getItem() == ItemInit.INGOT_OBSIDIAN) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_obsidian")));
			}else if(material.getItem() == ItemInit.INGOT_PLATINUM) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_platinum")));
			}else if(material.getItem() == ItemInit.INGOT_SHADOW_PLATINUM) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_shadow_platinum")));
			}else if(material.getItem() == ItemInit.INGOT_SILVER) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_silver")));
			}else if(material.getItem() == ItemInit.INGOT_STEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_steel")));
			}else if(material.getItem() == Items.IRON_INGOT) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_iron")));
			}
		}
		
		if(material.getItem() != null && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(ModInfo.ID + ":armor_plate_" + material.getItem().getRegistryName().getResourcePath()))) {
			return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":armor_plate_" + material.getItem().getRegistryName().getResourcePath())));
		}
		
		return new ItemStack(ItemInit.CLAY_GRANITE).setStackDisplayName("ERROR IN RECIPE OUTPUT");
	}
	
	private void setMaterial(ItemStack input1, ItemStack input2) {
		if(input1.getItem() instanceof ItemHotToolHead) {
			NBTTagCompound tag = input1.getTagCompound();
			if(tag.hasKey("Material")) {
				this.material = new ItemStack(tag.getCompoundTag("Material"));
				return;
			}
		}
		
		if(input2.getItem() instanceof ItemHotToolHead) {
			NBTTagCompound tag = input1.getTagCompound();
			if(tag.hasKey("Material")) {
				this.material = new ItemStack(tag.getCompoundTag("Material"));
				return;
			}
		}
		
		if(!input1.isEmpty()) {
			this.material = input1;
			return;
		}
		
		if(!input2.isEmpty()) {
			this.material = input2;
			return;
		}
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String button = JsonUtils.getString(json, "button");
			final NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			JsonArray ing = JsonUtils.getJsonArray(json, "ingredients");
			if(ing.size() > inputs.size()) {
				throw new JsonParseException("There cannot be more ingredients than slots to input items!");
			}
				
			for(int i = 0; i < ing.size(); i++) {
				JsonElement e = ing.get(i);
				if(e.getAsJsonObject().has("slot")) {
					final JsonElement slot = e.getAsJsonObject().get("slot");
					if(slot.getAsString().equalsIgnoreCase("metal")) {
						inputs.set(i, Ingredient.fromItems(ItemInit.acceptedForgeItems.toArray(new Item[0])));
					}
				}else {
					inputs.set(i, CraftingHelper.getIngredient(e, context));
				}
			}
			
			final int exp = JsonUtils.getInt(json, "exp");
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ForgeArmorPlateRecipe(button, inputs, exp, result);
		}
		
	}
	
	
}
