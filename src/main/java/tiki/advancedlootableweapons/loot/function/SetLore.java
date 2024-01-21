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

public class SetLore extends LootFunction {

	private final String lore;
	
	public SetLore(LootCondition[] conditionsIn, String lore) {
		super(conditionsIn);
		this.lore = lore;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		NBTTagCompound displayTag = tag.hasKey("display") ? tag.getCompoundTag("display") : new NBTTagCompound();
		
		NBTTagList loreTag = new NBTTagList();
		loreTag.appendTag(new NBTTagString(this.lore));
		displayTag.setTag("Lore", loreTag);
		
		tag.setTag("display", displayTag);
		stack.setTagCompound(tag);
		return stack;
	}

	public static class Serializer  extends LootFunction.Serializer<SetLore> {

		public Serializer() {
			super(new ResourceLocation("set_lore"), SetLore.class);
		}

		@Override
		public void serialize(JsonObject object, SetLore functionClazz, JsonSerializationContext context) {
			object.addProperty("lore", functionClazz.lore);
		}
 
		@Override
		public SetLore deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			return new SetLore(conditionsIn, object.get("lore").getAsString());
		}
		
	}
}
