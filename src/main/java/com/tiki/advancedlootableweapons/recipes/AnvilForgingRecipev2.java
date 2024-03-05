package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeapon;
import com.tiki.advancedlootableweapons.util.WeaponStackHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class AnvilForgingRecipev2 implements Recipe<Container> {

	public static final List<Item> ACCEPTED_TOP_INPUTS = new ArrayList<Item>();
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> inputs;
	private final boolean useNbt;

	public AnvilForgingRecipev2(final ResourceLocation id, final ItemStack output, final NonNullList<Ingredient> inputs, final boolean useNbt) {
		this.id = id;
		this.output = output;
		this.inputs = inputs;
		this.useNbt = useNbt;
	}
	
	@Override
	public boolean matches(final Container pContainer, final Level pLevel) {
		for(int i = 0; i < inputs.size(); i++) {
			if(!inputs.get(i).test(pContainer.getItem(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack assemble(final Container pContainer) {
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
		return RecipeInit.ANVIL_FORGING_RECIPE_V2.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.ANVIL_FORGING;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(Blocks.ANVIL.asItem());
	}
	
	public static class Serializer extends AbstractRecipeSerializer<AnvilForgingRecipev2> {
		
		@Override
		public AnvilForgingRecipev2 fromJson(final ResourceLocation pRecipeId, final JsonObject json) {
			boolean useNbt = GsonHelper.convertToBoolean(json, "transferNBT");
			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}
			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new AnvilForgingRecipev2(pRecipeId, output, inputs, useNbt);
		}
		
		@Override
		public AnvilForgingRecipev2 fromNetwork(final ResourceLocation pRecipeId, final FriendlyByteBuf pBuffer) {
			boolean useNbt = pBuffer.readBoolean();
			NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromNetwork(pBuffer));
			}
			ItemStack output = pBuffer.readItem();
			return new AnvilForgingRecipev2(pRecipeId, output, inputs, useNbt);
		}
		
		@Override
		public void toNetwork(final FriendlyByteBuf pBuffer, final AnvilForgingRecipev2 pRecipe) {
			pBuffer.writeBoolean(pRecipe.useNbt);
			pBuffer.writeInt(pRecipe.getIngredients().size());
			for(Ingredient i : pRecipe.getIngredients()) {
				i.toNetwork(pBuffer);
			}
			pBuffer.writeItemStack(pRecipe.getResultItem(), true);
		}
	}

}
