package tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tiki.advancedlootableweapons.init.ItemInit;

public class ForgeGeneralCaseRecipe extends ShapelessOreRecipe {
	
	private final NonNullList<Ingredient> inputs;
	private final String button;
	private final int exp;
	public final Block block;
	
	public ForgeGeneralCaseRecipe(final String button, final NonNullList<Ingredient> inputs, final int exp, final ItemStack result, final Block block) {
		super(null, inputs, result);
		this.inputs = inputs;
		this.button = button;
		this.exp = exp;
		this.block = block;
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
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		
		if(this.input.get(0) != Ingredient.EMPTY) {
			if(this.input.get(0).apply(input1)) {
				match1 = true;
			}
		}else {
			match1 = true;
		}
		
		if(this.input.get(1) != Ingredient.EMPTY) {
			if(this.input.get(1).apply(input2)) {
				match2 = true;
			}
		}else {
			match2 = true;
		}
		
		return match1 && match2;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {		
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		
		if(this.input.get(0) != Ingredient.EMPTY) {
			if(this.input.get(0).apply(input1)) {
				match1 = true;
			}
		}else {
			match1 = true;
		}
		
		if(this.input.get(1) != Ingredient.EMPTY) {
			if(this.input.get(1).apply(input2)) {
				match2 = true;
			}
		}else {
			match2 = true;
		}
		
		return match1 && match2;
	}
	
	public ItemStack getCraftingResult(final NonNullList<ItemStack> inv) {
		return this.output;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		return this.output;
	}
	
	public String getButton() {
		return this.button;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String button = JsonUtils.getString(json, "button");
			final String block = JsonUtils.getString(json, "block", "");
			
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
						inputs.set(i, Ingredient.fromItems(ItemInit.acceptedForgeMetals.toArray(new Item[0])));
					}
				}else {
					inputs.set(i, CraftingHelper.getIngredient(e, context));
				}
			}
			
			final int exp = JsonUtils.getInt(json, "exp");
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ForgeGeneralCaseRecipe(button, inputs, exp, result, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block)));
		}
		
	}
}
