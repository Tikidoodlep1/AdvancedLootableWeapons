package com.tiki.advancedlootableweapons.armor;

import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBonusesBase extends ItemArmor implements IHasModel {
	
	private double bonusHealth;
	private double bonusDamage;
	private double bonusMoveSpeed;
	private double totalHealth;
	private double totalDamage;
	private double totalMoveSpeed;

	public ArmorBonusesBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusDamage, double bonusMoveSpeed) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.items.add(this);
		
		this.bonusHealth = bonusHealth;
		this.bonusDamage = bonusDamage;
		this.bonusMoveSpeed = bonusMoveSpeed;
		this.totalHealth = this.bonusHealth;
		this.totalDamage = this.bonusDamage;
		this.totalMoveSpeed = this.bonusMoveSpeed;
	}

	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType)
        {
        	multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Alw.MAX_HEALTH_MODIFIER, "Armor modifier", this.totalHealth, 0));
        	multimap.put(Alw.BONUS_ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Armor modifier", this.totalDamage, 0));
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Alw.MOVEMENT_SPEED_MODIFIER, "Armor modifier", this.totalMoveSpeed, 0));
        }

        return multimap;
    }
	
	public double getBonusAttackDamage() {
		return this.bonusDamage;
	}
	
}
