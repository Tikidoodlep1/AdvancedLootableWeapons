package com.tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.EnumMaterialType;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemHotToolHead extends Item {
	private final CompoundTag nbt = new CompoundTag();
	private final ItemHotToolHead next;
	private final int level;
	private final boolean finished;
	
	public ItemHotToolHead(@Nullable ItemHotToolHead next, int level, boolean finished, Properties prop) {
		super(prop);
		this.next = next;
		this.level = level;
		this.finished = finished;
		nbt.putString("material", "null");
		nbt.putDouble("addedDamage", 0.0D);
		nbt.putInt("addedDurability", 0);
	}
	
	public ItemHotToolHead getNextToolHead() {
		return this.next;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public boolean isFinished() {
		return this.finished;
	}

	public void setMaterial(Tier mat) {
		nbt.putString("material", EnumMaterialType.getMaterialNameF(mat));
	}
	
	public void setMaterial(String matName) {
		nbt.putString("material", matName);
	}
	
	public String getMaterial() {
		return nbt.getString("material");
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		if(this.getMaterial() != EnumMaterialType.NULL_MAT.getMaterialNameF()) {
			tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("material")));
		}else {
			tooltip.add(new TextComponent(ChatFormatting.BLUE + "No Material"));
		}
		
		tooltip.add(new TextComponent(ChatFormatting.BLUE + "Forging Quality"));
		tooltip.add(new TextComponent(ChatFormatting.GRAY + "--------------------"));
		tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("addedDamage")));
		tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("addedDurability")));
		tooltip.add(new TextComponent(ChatFormatting.GRAY + "--------------------"));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity player, int p_41407_, boolean p_41408_) {
		int damage = stack.getDamageValue();
		if(damage < 6000) {
			this.setDamage(stack, damage + 1);
		}
	}

	@Override//todo
	public Component getName(ItemStack pStack) {
		return super.getName(pStack);
	}

	@Override
	public boolean isEnchantable(ItemStack p_41456_) {
	   return false;
	}
	
	public ItemHotToolHead addToRegistryMap() {
		ItemInit.toolHeadMap.put(this, (stack, world, player, id) -> {if(stack.getDamageValue() <= 3000) {
														   return 0;
													   }else if(stack.getDamageValue() > 3000 && stack.getDamageValue() < 5000) {
														   return 1;
													   }else {
														   return 2;
													   }
		});
		return this;
	}
}
