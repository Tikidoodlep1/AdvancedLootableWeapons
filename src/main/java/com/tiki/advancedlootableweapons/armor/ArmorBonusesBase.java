package com.tiki.advancedlootableweapons.armor;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBonusesBase extends ItemArmor implements IHasModel, ISpecialArmor {
	
	private final double bonusHealth;
	private double bonusDamage;
	private final double bonusMoveSpeed;
	private final int tier;
	private int maxDamage;
	private String binding;
	private final ArmorProperties properties;
	
	public ArmorBonusesBase(String name, ArmorMaterial materialIn, int renderIndexIn, double absorbRatio, int maxAbsorb, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusDamage, double bonusMoveSpeed, int tier) {
		this(name, materialIn, renderIndexIn, absorbRatio, maxAbsorb, equipmentSlotIn, bonusHealth, bonusMoveSpeed, tier);
		this.bonusDamage = bonusDamage * ConfigHandler.ARMOR_BONUS_DAMAGE_MULTIPLIER;
	}
	
	public ArmorBonusesBase(String name, ArmorMaterial materialIn, int renderIndexIn, double absorbRatio, int maxAbsorb, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusMoveSpeed, int tier) {
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
		if(equipmentSlotIn == EntityEquipmentSlot.CHEST || equipmentSlotIn == EntityEquipmentSlot.LEGS) {
			properties = new ArmorProperties(2, absorbRatio, maxAbsorb);
		}else {
			properties = new ArmorProperties(1, absorbRatio, maxAbsorb);
		}
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
        		multimap.put(Alw.BONUS_ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Armor modifier", this.bonusDamage, 0));
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
		tooltip.add(TextFormatting.BLUE + "Tier: " + TextFormatting.YELLOW + "" + TextFormatting.ITALIC + this.tier);
		tooltip.add(TextFormatting.BLUE + "Binding: " + TextFormatting.GRAY + this.binding);
    }
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return properties;
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return (int) Math.floor(properties.Armor);
	}
	
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		Random rand = new Random();
		if(rand.nextDouble() >= 0.98) {
			return;
		}else {
			stack.attemptDamageItem(damage, rand, null);
		}
	}
}
