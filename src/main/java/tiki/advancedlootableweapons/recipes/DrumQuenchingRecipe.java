package tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.InventoryCrafting;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import tiki.advancedlootableweapons.handlers.ConfigHandler;

public class DrumQuenchingRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	
	final ItemStack ingr;
	final FluidStack fluid;
	final int ticks;
	final boolean needsClay;

    private final ResourceLocation group;
    private final NonNullList<Ingredient> input;
    private final ItemStack result;
	
	public DrumQuenchingRecipe(ResourceLocation group, ItemStack input, FluidStack fluid, int time, boolean needsClay) {
		this.group = group;
        this.input = NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(input));
        this.result = input;

		this.ingr = input;
		this.fluid = fluid;
		this.ticks = time;
		this.needsClay = needsClay;
	}

    @Override
    public boolean canFit(int width, int height) {
        return width * height <= 3;
    }

    @Override
	public ItemStack getRecipeOutput() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("quenched", true);
		ItemStack result = this.result.copy();
		result.setTagCompound(tag);
		return result;
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
		if(!ConfigHandler.ENABLE_QUENCHING) {
			return false;
		}
		
		ItemStack input = inv.getStackInSlot(TileEntityDrum.INPUT_SLOT);
		
		if(ConfigHandler.ENABLE_REGIONAL_QUENCHING && !input.hasTagCompound()) {
			return false;
		}
		
		if(needsClay && !input.getTagCompound().getBoolean("clay")) {
			return false;
		}
		
		boolean inputMatch = false;
		
		if(input.getItem() == ingr.getItem()) {
			inputMatch = true;
		}else if(inv.getStackInSlot(TileEntityDrum.ADDITIVE_SLOT).getItem() == ingr.getItem()) {
			inputMatch = true;
		}
		
		return inputMatch;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack result = this.ingr.copy();
		NBTTagCompound tag = inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).hasTagCompound() ? inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getTagCompound() : new NBTTagCompound();
		tag.setBoolean("quenched", true);
		tag.setBoolean("clay", false);
		result.setTagCompound(tag);
		
		result.setItemDamage(inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getItemDamage() + 3500 > 6000 ? 6000 : inv.getStackInSlot(TileEntityDrum.INPUT_SLOT).getItemDamage() + 3500);
		return result;
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

    @Override
    public boolean isDynamic() {
        return true;
    }
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final ItemStack input = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "input"), context);
			final FluidStack fluid = FluidRegistry.getFluidStack(JsonUtils.getString(json, "fluid"), 1000);
			final int time = JsonUtils.getInt(json, "ticks");
			final boolean needsClay = JsonUtils.getBoolean(json, "clay");
			
			if(fluid == null) {
				throw new JsonParseException("Fluid is null!");
			}
			
			return new DrumQuenchingRecipe(group.isEmpty() ? null : new ResourceLocation(group), input, fluid, time, needsClay);
		}
		
	}

}
