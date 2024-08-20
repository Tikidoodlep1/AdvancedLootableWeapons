package com.tiki.advancedlootableweapons.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tiki.advancedlootableweapons.blocks.block_entity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.data.recipes.AlloyFurnaceRecipeBuilder;
import com.tiki.advancedlootableweapons.init.BlockInit;

import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.RecipeInit;
import net.minecraft.core.NonNullList;
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

public class AlloyFurnaceRecipe implements Recipe<RecipeWrapper> {

	private final ResourceLocation id;
	private final ItemStack output;
	private final Ingredient input1;
	private final Ingredient input2;
	private final int count1;
	private final int count2;
	private final int cookTime;

	public AlloyFurnaceRecipe(final ResourceLocation id, final ItemStack output, final Ingredient input1, final Ingredient input2,
							  final int count1, final int count2, int cookTime) {
		this.id = id;
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
		this.count1 = count1;
		this.count2 = count2;
		this.cookTime = cookTime;
	}
	
	@Override
	public boolean matches(final RecipeWrapper pContainer, final Level pLevel) {
		ItemStack slot1 = pContainer.getItem(AlloyFurnaceBlockEntity.SLOT_INPUT_1);
		ItemStack slot2 = pContainer.getItem(AlloyFurnaceBlockEntity.SLOT_INPUT_2);

		//example recipe: 3 copper + 1 tin = 4 bronze

		//so first we check if slot1 has copper and slot2 has tin
		if (testSlot1(slot1) && testSlot2(slot2)) {
			return true;
		}

		//or slot1 has tin and slot2 has copper
		if (testSlot1(slot2) && testSlot2(slot1)) {
			return true;
		}

		return false;
	}

	public boolean normalOrientation(RecipeWrapper container) {
		ItemStack slot1 = container.getItem(AlloyFurnaceBlockEntity.SLOT_INPUT_1);
		ItemStack slot2 = container.getItem(AlloyFurnaceBlockEntity.SLOT_INPUT_2);
		return testSlot1(slot1)&& testSlot2(slot2);
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(input1);
		ingredients.add(input2);
		return ingredients;
	}

	//check ingredient and count
	public boolean testSlot1(ItemStack stack) {
		return input1.test(stack) && stack.getCount() >= count1;
	}

	public boolean testSlot2(ItemStack stack) {
		return input2.test(stack) && stack.getCount() >= count2;
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
		return RecipeInit.ALLOY_FURNACE.get();
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.ALLOY_FURNACE;
	}

	public Ingredient getInput1() {
		return input1;
	}

	public Ingredient getInput2() {
		return input2;
	}

	public int getCount1() {
		return count1;
	}

	public int getCount2() {
		return count2;
	}

	public int getCookTime() {
		return cookTime;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.ALLOY_FURNACE.get());
	}

	public static class Serializer extends AbstractRecipeSerializer<AlloyFurnaceRecipe> {


		@Override
		public AlloyFurnaceRecipe fromJson(final ResourceLocation pRecipeId, final JsonObject json) {
			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

			JsonObject element1 = ingredients.get(0).getAsJsonObject();
			JsonObject element2 = ingredients.get(1).getAsJsonObject();

			Ingredient ingredient1 = Ingredient.fromJson(element1.get("ingredient"));
			Ingredient ingredient2 = Ingredient.fromJson(element2.get("ingredient"));

			int count1 = element1.get("count").getAsInt();
			int count2 = element2.get("count").getAsInt();

			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			int cookTime = GsonHelper.getAsInt(json, AlloyFurnaceRecipeBuilder.COOKTIME);
			return new AlloyFurnaceRecipe(pRecipeId, output,ingredient1,ingredient2,count1,count2, cookTime);
		}

		@Override
		public AlloyFurnaceRecipe fromNetwork(final ResourceLocation pRecipeId, final FriendlyByteBuf pBuffer) {
			Ingredient ing1 = Ingredient.fromNetwork(pBuffer);
			Ingredient ing2 = Ingredient.fromNetwork(pBuffer);

			int count1 = pBuffer.readInt();
			int count2 = pBuffer.readInt();

			ItemStack result = pBuffer.readItem();

			int cooktime = pBuffer.readInt();

			return new AlloyFurnaceRecipe(pRecipeId,result,ing1,ing2,count1,count2,cooktime);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf pBuffer, final AlloyFurnaceRecipe pRecipe) {
			pRecipe.input1.toNetwork(pBuffer);
			pRecipe.input2.toNetwork(pBuffer);

			pBuffer.writeInt(pRecipe.count1);
			pBuffer.writeInt(pRecipe.count2);

			pBuffer.writeItemStack(pRecipe.output,false);
			pBuffer.writeInt(pRecipe.cookTime);
		}
	}
}
