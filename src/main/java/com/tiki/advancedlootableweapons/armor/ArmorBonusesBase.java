package com.tiki.advancedlootableweapons.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBonusesBase extends ItemArmor implements IHasModel {
	
	private double bonusHealth;
	private double bonusDamage;
	private double bonusMoveSpeed;
	private double totalDamage;
	private int tier;
	private int maxDamage;
	private String binding;
	
	public ArmorBonusesBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusDamage, double bonusMoveSpeed, int tier) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.bonusHealth = bonusHealth * ConfigHandler.ARMOR_BONUS_HEALTH_MULTIPLIER;
		this.bonusDamage = bonusDamage * ConfigHandler.ARMOR_BONUS_DAMAGE_MULTIPLIER;
		this.bonusMoveSpeed = bonusMoveSpeed;
		this.totalDamage = this.bonusDamage;
		this.tier = tier;
		this.maxDamage = materialIn.getDurability(equipmentSlotIn);
		this.binding = "No Binding";
	}
	
	public ArmorBonusesBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusMoveSpeed, int tier) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.bonusHealth = bonusHealth * ConfigHandler.ARMOR_BONUS_HEALTH_MULTIPLIER;
		this.bonusMoveSpeed = bonusMoveSpeed;
		this.tier = tier;
		this.maxDamage = materialIn.getDurability(equipmentSlotIn);
		this.binding = "No Binding";
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
        	if(ConfigHandler.USE_ARMOR_BONUS_DAMAGE) {
        		multimap.put(Alw.BONUS_ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Armor modifier", this.totalDamage, 0));
        	}
        }
        if (equipmentSlot == EntityEquipmentSlot.HEAD && this.armorType == EntityEquipmentSlot.HEAD) {
        	if(ConfigHandler.USE_ARMOR_BONUS_HEALTH) {
        		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Alw.HEAD_MAX_HEALTH_MODIFIER, "Armor modifier", this.bonusHealth, 0));
        	}
        	if(ConfigHandler.USE_ARMOR_WEIGHT) {
        		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Alw.HEAD_MOVEMENT_SPEED_MODIFIER, "Armor modifier", this.bonusMoveSpeed, 0));
        	}
        }
        if (equipmentSlot == EntityEquipmentSlot.CHEST && this.armorType == EntityEquipmentSlot.CHEST) {
        	if(ConfigHandler.USE_ARMOR_BONUS_HEALTH) {
        		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Alw.CHEST_MAX_HEALTH_MODIFIER, "Armor modifier", this.bonusHealth, 0));
        	}
        	if(ConfigHandler.USE_ARMOR_WEIGHT) {
        		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Alw.CHEST_MOVEMENT_SPEED_MODIFIER, "Armor modifier", this.bonusMoveSpeed, 0));
        	}
        }
        if (equipmentSlot == EntityEquipmentSlot.LEGS && this.armorType == EntityEquipmentSlot.LEGS) {
        	if(ConfigHandler.USE_ARMOR_BONUS_HEALTH) {
        		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Alw.LEGS_MAX_HEALTH_MODIFIER, "Armor modifier", this.bonusHealth, 0));
        	}
        	if(ConfigHandler.USE_ARMOR_WEIGHT) {
        		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Alw.LEGS_MOVEMENT_SPEED_MODIFIER, "Armor modifier", this.bonusMoveSpeed, 0));
        	}
        }
        if (equipmentSlot == EntityEquipmentSlot.FEET && this.armorType == EntityEquipmentSlot.FEET) {
        	if(ConfigHandler.USE_ARMOR_BONUS_HEALTH) {
        		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Alw.FEET_MAX_HEALTH_MODIFIER, "Armor modifier", this.bonusHealth, 0));
        	}
        	if(ConfigHandler.USE_ARMOR_WEIGHT) {
        		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Alw.FEET_MOVEMENT_SPEED_MODIFIER, "Armor modifier", this.bonusMoveSpeed, 0));
        	}
        }

        return multimap;
    }
	
	public double getBonusAttackDamage() {
		return this.bonusDamage;
	}
	
	@Override
	public Item setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
		return this;
	}
	
	@Override
	public int getMaxDamage() {
		return this.maxDamage;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "Binding: " + TextFormatting.GRAY + this.binding);
		tooltip.add(TextFormatting.BLUE + "Tier: " + TextFormatting.YELLOW + "" + TextFormatting.ITALIC + this.tier);
    }
}
