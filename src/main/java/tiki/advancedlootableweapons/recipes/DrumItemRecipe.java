package tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;

public class DrumItemRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	
	final Ingredient ingr;
	final FluidStack fluid;
	final Ingredient additive;
	final int ticks;

    private final ResourceLocation group;
    private final NonNullList<Ingredient> input;
    private final ItemStack result;
	
	public DrumItemRecipe(ResourceLocation group, Ingredient input, FluidStack fluid, Ingredient additive, ItemStack result, int time) {
		this.group = group;
        this.input = NonNullList.from(Ingredient.EMPTY, input, additive);
        this.result = result;
		this.ingr = input;
		this.fluid = fluid;
		this.additive = additive;
		this.ticks = time;
	}

    @Override
    public boolean canFit(int width, int height) {
        return width * height <= 3;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return input;
    }

    @Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> keptItems = IRecipe.super.getRemainingItems(inv);
		
		for(int i = 0; i < this.input.size(); i++) {
			Ingredient in = this.input.get(i);
			for(ItemStack stack : in.getMatchingStacks()) {
				if(stack.getItem() == inv.getStackInSlot(i).getItem() && stack.getCount() < inv.getStackInSlot(i).getCount()) {
					if(keptItems.get(i) == ItemStack.EMPTY) {
						ItemStack slot = inv.getStackInSlot(i);
						slot.setCount(inv.getStackInSlot(i).getCount() - stack.getCount());
						keptItems.set(i, slot);
					}
				}
			}
		}
		return keptItems;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		
		boolean input = false;
		boolean add = false;
		
		for(ItemStack stack : ingr.getMatchingStacks()) {
			if(inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getItem() == stack.getItem() && inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getCount() >= stack.getCount()) {
				input = true;
			}else if(inv.getStackInSlot(TileEntityDrum.ADDITIVE_SLOT).getItem() == stack.getItem()) {
				input = true;
			}
		}
		
		if(additive == Ingredient.EMPTY) {
			add = true;
		}else {
			for(ItemStack stack : additive.getMatchingStacks()) {
				if(inv.getStackInSlot(TileEntityDrum.ADDITIVE_SLOT).getItem() == stack.getItem()) {
					add = true;
				}else if(inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getItem() == stack.getItem() && inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getCount() >= stack.getCount()) {
					add = true;
				}
			}
		}
		
		return input && add;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		return this.getRecipeOutput();
	}
	
	@Override
	public String getGroup() {
		return group == null ? "" : group.toString();
	}
	
	public FluidStack getFluid() {
		return this.fluid;
	}
	
	public int getTime() {
		return this.ticks;
	}
	
	public int getInputCount(final InventoryCrafting inv) {
		for(ItemStack stack : ingr.getMatchingStacks()) {
			if(inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getItem() == stack.getItem()) {
				return stack.getCount();
			}
		}
		return ingr.getMatchingStacks()[0].getCount();
	}

    @Override
    public boolean isDynamic() {
        return true;
    }
    
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final Ingredient input = CraftingHelper.getIngredient(JsonUtils.getJsonObject(json, "input"), context);
			final FluidStack fluid = FluidRegistry.getFluidStack(JsonUtils.getString(json, "fluid"), 1000);
			final Ingredient add = json.has("additive") ? CraftingHelper.getIngredient(JsonUtils.getJsonObject(json, "additive"), context) : Ingredient.EMPTY;
			final int time = JsonUtils.getInt(json, "ticks");
			
			if(fluid == null) {
				throw new JsonParseException("Fluid is null!");
			}
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new DrumItemRecipe(group.isEmpty() ? null : new ResourceLocation(group), input, fluid, add, result, time);
		}
		
	}

}
