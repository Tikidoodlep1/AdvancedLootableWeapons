package tiki.rotn.advancedlootableweapons.loot.function;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class SetNameConcat extends LootFunction {

	private final String name;
	private final boolean prefix;
	
	public SetNameConcat(LootCondition[] conditionsIn, String name, boolean prefix) {
		super(conditionsIn);
		this.name = name;
		this.prefix = prefix;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound displayTag = tag.hasKey("display") ? tag.getCompoundTag("display") : new NBTTagCompound();
		
		if(this.prefix) {
			displayTag.setString("Name", this.name + displayTag.getString("Name"));
		}else {
			displayTag.setString("Name", displayTag.getString("Name") + this.name);
		}
		
		tag.setTag("display", displayTag);
		stack.setTagCompound(tag);
		return stack;
	}
	
	public static class Serializer  extends LootFunction.Serializer<SetNameConcat> {

		public Serializer() {
			super(new ResourceLocation("set_name_concat"), SetNameConcat.class);
		}

		@Override
		public void serialize(JsonObject object, SetNameConcat functionClazz, JsonSerializationContext context) {
			object.addProperty("name", functionClazz.name);
			object.addProperty("prefix", functionClazz.prefix);
		}
 
		@Override
		public SetNameConcat deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			return new SetNameConcat(conditionsIn, object.get("name").getAsString(), object.get("prefix").getAsBoolean());
		}
		
	}
}
