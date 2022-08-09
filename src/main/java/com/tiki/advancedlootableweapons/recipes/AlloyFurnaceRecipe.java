package com.tiki.advancedlootableweapons.recipes;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class AlloyFurnaceRecipe implements Recipe<SimpleContainer> {

	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> inputs;
	
	public AlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs) {
		this.id = id;
		this.output = output;
		this.inputs = inputs;
	}
	
	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		for(int i = 0; i < inputs.size(); i++) {
			if(!inputs.get(i).test(pContainer.getItem(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack assemble(SimpleContainer pContainer) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.BLOCK_ALLOY_FURNACE.get().asItem());
	}
	
	public static class Type implements RecipeType<AlloyFurnaceRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final String ID = "alloy_furnace";
	}
	
	public static class Serializer implements RecipeSerializer<AlloyFurnaceRecipe> {
		
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(ModInfo.ID, Type.ID);
		
		@Override
		public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
			return INSTANCE;
		}
		
		@Nullable
		@Override
		public ResourceLocation getRegistryName() {
			return ID;
		}
		
		@Override
		public Class<RecipeSerializer<?>> getRegistryType() {
			return Serializer.castClass(RecipeSerializer.class);
		}
		
		@Override
		public AlloyFurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}
			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new AlloyFurnaceRecipe(pRecipeId, output, inputs);
		}

		@Override
		public AlloyFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
			NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromNetwork(pBuffer));
			}
			ItemStack output = pBuffer.readItem();
			return new AlloyFurnaceRecipe(pRecipeId, output, inputs);
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, AlloyFurnaceRecipe pRecipe) {
			pBuffer.writeInt(pRecipe.getIngredients().size());
			for(Ingredient i : pRecipe.getIngredients()) {
				i.toNetwork(pBuffer);
			}
			pBuffer.writeItemStack(pRecipe.getResultItem(), true);
		}
		
		@SuppressWarnings("unchecked")
		private static <T> Class<T> castClass(Class<?> cls) {
			return (Class<T>)cls;
		}
		
	}

}
