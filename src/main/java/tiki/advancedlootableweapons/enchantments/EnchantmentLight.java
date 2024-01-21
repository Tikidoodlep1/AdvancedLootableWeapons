package tiki.advancedlootableweapons.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import tiki.advancedlootableweapons.init.EnchantmentInit;

public class EnchantmentLight extends Enchantment {

	public EnchantmentLight() {
		super(Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, 
				EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
		this.setName("light");
		this.setRegistryName(ModInfo.ID + ":light");
		
		EnchantmentInit.enchantments.add(this);
	}

	@Override
	public Enchantment.Rarity getRarity()
    {
        return Enchantment.Rarity.UNCOMMON;
    }
	
	@Override
    public int getMinLevel()
    {
        return 1;
    }

	@Override
    public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel * 8;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ArmorBonusesBase;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}
	
	public static double getSpeedModifier(int level) {
		return 1.0 + (level/3);
	}
}
