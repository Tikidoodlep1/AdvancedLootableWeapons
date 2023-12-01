package tiki.advancedlootableweapons.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class AnywoodBellowsRecipe extends ShapedOreRecipe {
	

	public AnywoodBellowsRecipe(ResourceLocation group, ItemStack result, ShapedPrimer recipe) {
		super(group, result, recipe);
	}
	
	private boolean isWood(ItemStack stack) {
		for(int oreID : OreDictionary.getOreIDs(stack)) {
			if(oreID == OreDictionary.getOreID("plankWood") || oreID == OreDictionary.getOreID("plankTreatedWood")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack result = this.getRecipeOutput();
		ItemStack wood = ItemStack.EMPTY;
		
		if(isWood(inv.getStackInSlot(7))) {
			if(wood.isEmpty()) {
				wood = inv.getStackInSlot(7);
			}
		}
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("wood", wood.serializeNBT());
		result.setTagCompound(tag);
		
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

	        Map<Character, Ingredient> ingMap = Maps.newHashMap();
	        for (Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet())
	        {
	            if (entry.getKey().length() != 1)
	                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
	            if (" ".equals(entry.getKey()))
	                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
	            if ("W".equals(entry.getKey()))
	            	throw new JsonSyntaxException("Invalid key entry: 'W' is a reserved symbol.");

	            ingMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
	        }
	        List<Ingredient> ingrs = new ArrayList<Ingredient>(2);
	        ingrs.add(Ingredient.fromStacks(OreDictionary.getOres("plankWood").toArray(new ItemStack[0])));
	        ingrs.add(Ingredient.fromStacks(OreDictionary.getOres("plankTreatedWood").toArray(new ItemStack[0])));
	        
	        ingMap.put('W', Ingredient.merge(ingrs));
	        ingMap.put(' ', Ingredient.EMPTY);

	        JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

	        if (patternJ.size() == 0)
	            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");

	        String[] pattern = new String[patternJ.size()];
	        for (int x = 0; x < pattern.length; ++x)
	        {
	            String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
	            if (x > 0 && pattern[0].length() != line.length())
	                throw new JsonSyntaxException("Invalid pattern: each row must  be the same width");
	            pattern[x] = line;
	        }

	        ShapedPrimer primer = new ShapedPrimer();
	        primer.width = pattern[0].length();
	        primer.height = pattern.length;
	        primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
	        primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

	        Set<Character> keys = Sets.newHashSet(ingMap.keySet());
	        keys.remove(' ');

	        int x = 0;
	        for (String line : pattern)
	        {
	            for (char chr : line.toCharArray())
	            {
	                Ingredient ing = ingMap.get(chr);
	                if (ing == null)
	                    throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
	                primer.input.set(x++, ing);
	                keys.remove(chr);
	            }
	        }

	        if (!keys.isEmpty())
	            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);

	        ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
	        return new AnywoodBellowsRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
		}
		
	}

}
