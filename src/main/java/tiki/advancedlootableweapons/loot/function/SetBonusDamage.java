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

public class SetBonusDamage extends LootFunction {

	private final RandomValueRange range;
	
	public SetBonusDamage(LootCondition[] conditionsIn, RandomValueRange range) {
		super(conditionsIn);
		this.range = range;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setDouble("addedDamage", this.range.generateFloat(rand));
		
		if(tag.hasKey("addedDurability")) {
			if(stack.getItem() instanceof ToolStabSword) {
				((ToolStabSword)stack.getItem()).generateNameAndModifiers(stack, tag.getDouble("addedDamage"), tag.getInteger("addedDurability"), ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack());
			}else if(stack.getItem() instanceof ToolSlashSword) {
				((ToolSlashSword)stack.getItem()).generateNameAndModifiers(stack, tag.getDouble("addedDamage"), tag.getInteger("addedDurability"), ((ToolSlashSword)stack.getItem()).getToolMaterial().getRepairItemStack());
			}
		}
		
		stack.setTagCompound(tag);
		return stack;
	}
	
	public static class Serializer extends LootFunction.Serializer<SetBonusDamage> {

		public Serializer() {
			super(new ResourceLocation("set_bonus_damage"), SetBonusDamage.class);
		}

		@Override
		public void serialize(JsonObject object, SetBonusDamage functionClazz, JsonSerializationContext context) {
			object.add("range", context.serialize(functionClazz.range));
		}
 
		@Override
		public SetBonusDamage deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditionsIn) {
			return new SetBonusDamage(conditionsIn, context.deserialize(object.get("range"), RandomValueRange.class));
		}
		
	}

}
