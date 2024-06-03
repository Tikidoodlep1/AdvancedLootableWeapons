package tiki.advancedlootableweapons.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.init.EnchantmentInit;

public class EnchantmentRefined extends Enchantment {
	
	public EnchantmentRefined() {
		super(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("refined");
		this.setRegistryName(ModInfo.ID + ":refined");
		
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
	public int getMinEnchantability(int enchantmentLevel) {
		return 100;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 101;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.isItemStackDamageable();
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.SHARPNESS;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return false;
	}
	
	@Override
	public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType){
		return 0.5F * level;
    }
}
