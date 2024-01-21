package tiki.advancedlootableweapons.loot.function;

import java.util.Iterator;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SetItemFromArray extends LootFunction {

	private final Item[] options;
	
	public SetItemFromArray(LootCondition[] conditionsIn, Item... options) {
		super(conditionsIn);
		this.options = options;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		ItemStack output = new ItemStack(this.options[rand.nextInt(this.options.length)]);
		if(stack.hasTagCompound()) {
			output.setTagCompound(stack.getTagCompound());
		}
		return output;
	}
	
	public static class Serializer extends LootFunction.Serializer<SetItemFromArray> {

		public Serializer() {
			super(new ResourceLocation("set_item_from_array"), SetItemFromArray.class);
		}

		@Override
		public void serialize(JsonObject object, SetItemFromArray functionClazz, JsonSerializationContext context) {
			JsonArray arr = new JsonArray();
			for(Item i : functionClazz.options) {
				arr.add(i.getRegistryName().toString());
			}
			object.add("options", arr);
		}
 
		@Override
		public SetItemFromArray deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			Item[] items = new Item[object.get("options").getAsJsonArray().size()];
			Iterator<JsonElement> iter = object.get("options").getAsJsonArray().iterator();
			int i = 0;
			while(iter.hasNext()) {
				JsonElement e = iter.next();
				if(ForgeRegistries.ITEMS.containsKey(new ResourceLocation(e.getAsString()))) {
					items[i++] = ForgeRegistries.ITEMS.getValue(new ResourceLocation(e.getAsString()));
				}
			}
			return new SetItemFromArray(conditionsIn, items);
		}
		
	}

}
