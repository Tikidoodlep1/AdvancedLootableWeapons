package tiki.advancedlootableweapons.loot.function;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class SetName extends LootFunction {

	private final String name;
	
	public SetName(LootCondition[] conditionsIn, String name) {
		super(conditionsIn);
		this.name = name;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound displayTag = tag.hasKey("display") ? tag.getCompoundTag("display") : new NBTTagCompound();
		
		displayTag.setString("Name", this.name);
		
		tag.setTag("display", displayTag);
		stack.setTagCompound(tag);
		return stack;
	}
	
	public static class Serializer  extends LootFunction.Serializer<SetName> {

		public Serializer() {
			super(new ResourceLocation("set_name"), SetName.class);
		}

		@Override
		public void serialize(JsonObject object, SetName functionClazz, JsonSerializationContext context) {
			object.addProperty("name", functionClazz.name);
		}
 
		@Override
		public SetName deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			return new SetName(conditionsIn, object.get("name").getAsString());
		}
		
	}

}
