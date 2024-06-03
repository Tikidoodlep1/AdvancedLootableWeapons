package tiki.advancedlootableweapons.loot.function;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import tiki.advancedlootableweapons.tools.ToolSlashSword;
import tiki.advancedlootableweapons.tools.ToolStabSword;

public class SetBonusDur extends LootFunction {

	private final RandomValueRange range;
	
	public SetBonusDur(LootCondition[] conditionsIn, RandomValueRange range) {
		super(conditionsIn);
		this.range = range;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setInteger("addedDurability", this.range.generateInt(rand));
		
		if(tag.hasKey("addedDamage")) {
			if(stack.getItem() instanceof ToolStabSword) {
				((ToolStabSword)stack.getItem()).generateNameAndModifiers(stack, tag.getDouble("addedDamage"), tag.getInteger("addedDurability"), ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack());
			}else if(stack.getItem() instanceof ToolSlashSword) {
				((ToolSlashSword)stack.getItem()).generateNameAndModifiers(stack, tag.getDouble("addedDamage"), tag.getInteger("addedDurability"), ((ToolSlashSword)stack.getItem()).getToolMaterial().getRepairItemStack());
			}
		}
		
		stack.setTagCompound(tag);
		return stack;
	}

	public static class Serializer  extends LootFunction.Serializer<SetBonusDur> {

		public Serializer() {
			super(new ResourceLocation("set_bonus_dur"), SetBonusDur.class);
		}

		@Override
		public void serialize(JsonObject object, SetBonusDur functionClazz, JsonSerializationContext context) {
			object.add("range", context.serialize(functionClazz.range));
		}
 
		@Override
		public SetBonusDur deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			return new SetBonusDur(conditionsIn, context.deserialize(object.get("range"), RandomValueRange.class));
		}
		
	}
}
