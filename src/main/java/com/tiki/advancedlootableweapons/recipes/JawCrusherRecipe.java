package com.tiki.advancedlootableweapons.recipes;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class JawCrusherRecipe implements Recipe<RecipeWrapper> {

	private final ResourceLocation id;
	private final ItemStack output;
	private final Ingredient input;
	private final int bonus;
	
	public JawCrusherRecipe(final ResourceLocation id, final ItemStack output, final int bonus, final Ingredient input) {
		this.id = id;
		this.output = output;
		this.bonus = bonus;
		this.input = input;
	}
	
	public int getBonus() {
		return this.bonus;
	}
	
	@Override
	public boolean matches(final RecipeWrapper pContainer, final Level pLevel) {
		return input.test(pContainer.getItem(0));
	}
	
	@Override
	public ItemStack assemble(final RecipeWrapper pContainer) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(final int pWidth, final int pHeight) {
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
		return new ItemStack(BlockInit.JAW_CRUSHER.get().asItem());
	}
	
	public static class Type implements RecipeType<JawCrusherRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final String ID = "crushing";
	}
	
	public static class Serializer implements RecipeSerializer<JawCrusherRecipe> {
		
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(AdvancedLootableWeapons.MODID, Type.ID);
		
		@Override
		public RecipeSerializer<?> setRegistryName(final ResourceLocation name) {
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
		public JawCrusherRecipe fromJson(final ResourceLocation pRecipeId, final JsonObject json) {
			Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

			int maxCount = json.has("bonus") ? GsonHelper.getAsInt(json, "bonus") : 0;
			return new JawCrusherRecipe(pRecipeId, output, maxCount, input);
		}
		
		@Override
		public JawCrusherRecipe fromNetwork(final ResourceLocation pRecipeId, final FriendlyByteBuf pBuffer) {
			Ingredient input = Ingredient.fromNetwork(pBuffer);
			ItemStack output = pBuffer.readItem();
			int maxCount = pBuffer.readInt();
			return new JawCrusherRecipe(pRecipeId, output, maxCount, input);
		}
		
		@Override
		public void toNetwork(final FriendlyByteBuf pBuffer, final JawCrusherRecipe pRecipe) {
			pRecipe.input.toNetwork(pBuffer);
			pBuffer.writeItemStack(pRecipe.getResultItem(), true);
			pBuffer.writeInt(pRecipe.bonus);
		}
		
		@SuppressWarnings("unchecked")
		private static <T> Class<T> castClass(Class<?> cls) {
			return (Class<T>)cls;
		}
		
	}

}
