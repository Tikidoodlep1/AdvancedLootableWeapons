package com.tiki.advancedlootableweapons.recipes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.util.WeaponStackHelper;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class AnvilForgingRecipe implements Recipe<SimpleContainer> {

	public static final List<Item> ACCEPTED_TOP_INPUTS = new ArrayList<Item>();
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> inputs;
	private final boolean useNbt;
	
	public AnvilForgingRecipe(final ResourceLocation id, final ItemStack output, final NonNullList<Ingredient> inputs, final boolean useNbt) {
		this.id = id;
		this.output = output;
		this.inputs = inputs;
		this.useNbt = useNbt;
	}
	
	@Override
	public boolean matches(final SimpleContainer pContainer, final Level pLevel) {
		for(int i = 0; i < inputs.size(); i++) {
			if(!inputs.get(i).test(pContainer.getItem(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack assemble(final SimpleContainer pContainer) {
		ItemStack result = getResultItem();
		CompoundTag tag = result.getOrCreateTag();
		ItemStack stack1 = pContainer.getItem(0);
		ItemStack stack2 = pContainer.getItem(1);
		CompoundTag input1 = stack1.getTag();
		CompoundTag input2 = stack2.getTag();
		if(useNbt) {
			int heat1 = stack1.getDamageValue();
			int heat2 = stack2.getDamageValue();
			double damageToAdd = input1.getDouble("addedDamage") + input2.getDouble("addedDamage");
			int durToAdd = input1.getInt("addedDurability") + input2.getInt("addedDurability");
			if(heat1 >= 0 && heat1 < 3000) {
				damageToAdd += 0.25;
				durToAdd += 12;
			}else if(heat1 >= 3000 && heat1 < 5000) {
				damageToAdd += 0.125;
				durToAdd += 5;
			}else {
				damageToAdd -= 0.35;
				durToAdd -= 18;
			}
			if(heat2 >= 0 && heat2 < 3000) {
				damageToAdd += 0.25;
				durToAdd += 12;
			}else if(heat2 >= 3000 && heat2 < 5000) {
				damageToAdd += 0.125;
				durToAdd += 5;
			}else {
				damageToAdd -= 0.35;
				durToAdd -= 18;
			}
			tag.putInt("addedDurability", durToAdd);
			if(result.getItem() instanceof AlwWeapon weapon) {
				WeaponStackHelper.addAttackDamage(result, damageToAdd);
				WeaponStackHelper.setRandomWeaponName(result, weapon.attributes);
			}else {
				tag.putString("material", input1.getString("material"));
				tag.putDouble("addedDamage", damageToAdd);
			}
			result.setTag(tag);
		}
		return result;
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
		return new ItemStack(Blocks.ANVIL.asItem());
	}
	
	public static class Type implements RecipeType<AnvilForgingRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final String ID = "anvil_forging_combine";
	}
	
	public static class Serializer implements RecipeSerializer<AnvilForgingRecipe> {
		
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(AdvancedLootableWeapons.ID, Type.ID);
		
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
		public AnvilForgingRecipe fromJson(final ResourceLocation pRecipeId, final JsonObject json) {
			boolean useNbt = GsonHelper.convertToBoolean(json, "transferNBT");
			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}
			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new AnvilForgingRecipe(pRecipeId, output, inputs, useNbt);
		}
		
		@Override
		public AnvilForgingRecipe fromNetwork(final ResourceLocation pRecipeId, final FriendlyByteBuf pBuffer) {
			boolean useNbt = pBuffer.readBoolean();
			NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromNetwork(pBuffer));
			}
			ItemStack output = pBuffer.readItem();
			return new AnvilForgingRecipe(pRecipeId, output, inputs, useNbt);
		}
		
		@Override
		public void toNetwork(final FriendlyByteBuf pBuffer, final AnvilForgingRecipe pRecipe) {
			pBuffer.writeBoolean(pRecipe.useNbt);
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
