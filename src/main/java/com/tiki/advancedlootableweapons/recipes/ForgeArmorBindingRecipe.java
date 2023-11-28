package com.tiki.advancedlootableweapons.recipes;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.items.ItemArmorBinding;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ForgeArmorBindingRecipe extends ShapelessOreRecipe {
	
	private final NonNullList<Ingredient> inputs;
	private final ItemStack result;
	private final String button;
	public final Block block;
	
	public ForgeArmorBindingRecipe(@Nullable final ResourceLocation group, final String button, final NonNullList<Ingredient> input, final ItemStack result, final Block block) {
		super(group, input, result);
		this.inputs = input;
		this.result = result;
		this.button = button;
		this.block = block;
	}
	
	public String getButton() {
		return this.button;
	}
	
	public NonNullList<ItemStack> getRemainingItems(final NonNullList<ItemStack> inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.size(), ItemStack.EMPTY);
		
		return remainingItems;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		
		return remainingItems;
	}
	
	public boolean matches(NonNullList<ItemStack> inv, World world) {
		int matches = 0;
		
		inventory: for(int i = 0; i < inv.size(); i++) {
			ItemStack input = inv.get(i);
			if(!input.isEmpty()) {
				
				Iterator<Ingredient> iter = inputs.iterator();
				while(iter.hasNext()) {
					Ingredient ingred = iter.next();
					for(ItemStack is : ingred.getMatchingStacks()) {
						if(is.getItem() == input.getItem()) {
							matches++;
							continue inventory;
						}
					}
				}
				if(matches == inputs.size()) {
					break inventory;
				}
				return false;
			}
		}
		
		if(matches != inputs.size()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		int matches = 0;
		
		inventory: for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack input = inv.getStackInSlot(i);
			if(!input.isEmpty()) {
				
				Iterator<Ingredient> iter = inputs.iterator();
				while(iter.hasNext()) {
					Ingredient ingred = iter.next();
					for(ItemStack is : ingred.getMatchingStacks()) {
						if(is.getItem() == input.getItem()) {
							matches++;
							continue inventory;
						}
					}
				}
				if(matches == inputs.size()) {
					break inventory;
				}
				return false;
			}
		}
		
		if(matches != inputs.size()) {
			return false;
		}
		return true;
	}
	
	public ItemStack getCraftingResult(final NonNullList<ItemStack> inv) {
		ItemStack bindingStack = ItemStack.EMPTY;
		ItemArmorBinding binding = null;
		
		for(int i = 0; i < inv.size(); i++) {
			if(inv.get(i).getItem() instanceof ItemArmorBinding) {
				bindingStack = inv.get(i);
				binding = (ItemArmorBinding)bindingStack.getItem();
				break;
			}
		}
		
		if(result.getItem() instanceof ArmorBonusesBase) {
			((ArmorBonusesBase)result.getItem()).setBinding(bindingStack.getDisplayName(), result);
			((ArmorBonusesBase)result.getItem()).setMaximumDamage(binding.getExtraDur(), result);
		}
		
		return result;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack bindingStack = ItemStack.EMPTY;
		ItemArmorBinding binding = null;
		
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			if(inv.getStackInSlot(i).getItem() instanceof ItemArmorBinding) {
				bindingStack = inv.getStackInSlot(i);
				binding = (ItemArmorBinding)bindingStack.getItem();
				break;
			}
		}
		
		if(result.getItem() instanceof ArmorBonusesBase) {
			((ArmorBonusesBase)result.getItem()).setBinding(bindingStack.getDisplayName(), result);
			((ArmorBonusesBase)result.getItem()).setMaximumDamage(binding.getExtraDur(), result);
		}
		
		return result;
	}
	
	@Override
	public String getGroup() {
		return group == null ? "" : group.toString();
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final String button = JsonUtils.getString(json, "button");
			final String block = JsonUtils.getString(json, "block", "");
			
			final NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			JsonArray ing = JsonUtils.getJsonArray(json, "ingredients");
			if(ing.size() > inputs.size()) {
				throw new JsonParseException("There cannot be more ingredients than slots to input items!");
			}
			if(ing.size() <= 0) {
				throw new JsonParseException("No ingredients found");
			}
			
			for(int i = 0; i < ing.size(); i++) {
				JsonElement e = ing.get(i);
				if(e.getAsJsonObject().has("slot")) {
					final JsonElement slot = e.getAsJsonObject().get("slot");
					if(slot.getAsString().equalsIgnoreCase("binding")) {
						NonNullList<ItemStack> bindingOre = OreDictionary.getOres("armorBinding");
						Item[] items = new Item[bindingOre.size()];
						for(int j = 0; j < bindingOre.size(); j++) {
							items[j] = bindingOre.get(j).getItem();
						}
						inputs.set(i, Ingredient.fromItems(items));
					}
				}else {
					inputs.set(i, CraftingHelper.getIngredient(e, context));
				}
			}
			
			ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ForgeArmorBindingRecipe(group.isEmpty() ? null : new ResourceLocation(group), button, inputs, result, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block)));
		}
		
	}
}
