package com.tiki.advancedlootableweapons.enchantments;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentRefined extends Enchantment {
	
	public EnchantmentRefined(String name) {
		super(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName(name);
		this.setRegistryName(ModInfo.ID + ":" + name);
		
		EnchantmentInit.enchantments.add(this);
	}
	
	@Override
	public Enchantment.Rarity getRarity()
    {
        return Enchantment.Rarity.VERY_RARE;
    }
	
	@Override
    public int getMinLevel()
    {
        return 1;
    }

	@Override
    public int getMaxLevel()
    {
        return 6;
    }
	
	@Override
	public boolean isCurse()
    {
        return false;
    }
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 99;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 101;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.SHARPNESS;
	}
	
	@Override
	public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType){
		return 0.5F * level;
    }
}
