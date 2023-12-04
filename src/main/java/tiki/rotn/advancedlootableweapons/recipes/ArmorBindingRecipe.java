package tiki.rotn.advancedlootableweapons.recipes;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tiki.rotn.advancedlootableweapons.armor.ArmorBonusesBase;
import tiki.rotn.advancedlootableweapons.init.ItemInit;
import tiki.rotn.advancedlootableweapons.items.ItemArmorBinding;

public class ArmorBindingRecipe extends ShapelessOreRecipe {
	
	private final NonNullList<Ingredient> inputs;
	private final ItemStack result;
	
	public ArmorBindingRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
		super(group, input, result);
		this.inputs = input;
		this.result = result;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		
		return remainingItems;
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
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		
		((ArmorBonusesBase)result.getItem()).setBinding(new TextComponentTranslation("item.binding_leather.name").getFormattedText(), result);
		((ArmorBonusesBase)result.getItem()).setMaximumDamage(((ItemArmorBinding)ItemInit.LEATHER_BINDING).getExtraDur(), result);
		
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
			final NonNullList<Ingredient> ingredients = NonNullList.create();
			for(final JsonElement element : JsonUtils.getJsonArray(json, "ingredients")) {
				ingredients.add(CraftingHelper.getIngredient(element, context));
			}
			
			if(ingredients.isEmpty()) {
				throw new JsonParseException("No ingredients found");
			}
			
			ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new ArmorBindingRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
		
	}
}
