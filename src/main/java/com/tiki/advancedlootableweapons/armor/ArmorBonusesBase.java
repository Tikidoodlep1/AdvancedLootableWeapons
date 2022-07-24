package com.tiki.advancedlootableweapons.armor;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;
import com.tiki.advancedlootableweapons.util.ArmorTypes;
import com.tiki.advancedlootableweapons.util.WeaponEffectiveness;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
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
	private final ArmorTypes armorMakeup;
	
	public ArmorBonusesBase(String name, ArmorTypes armorMakeup, ArmorMaterial materialIn, int renderIndexIn, double absorbRatio, int maxAbsorb, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusDamage, double bonusMoveSpeed, int tier) {
		this(name, armorMakeup, materialIn, renderIndexIn, absorbRatio, maxAbsorb, equipmentSlotIn, bonusHealth, bonusMoveSpeed, tier);
		this.bonusDamage = bonusDamage * ConfigHandler.ARMOR_BONUS_DAMAGE_MULTIPLIER;
	}
	
	public ArmorBonusesBase(String name, ArmorTypes armorMakeup, ArmorMaterial materialIn, int renderIndexIn, double absorbRatio, int maxAbsorb, EntityEquipmentSlot equipmentSlotIn, double bonusHealth, double bonusMoveSpeed, int tier) {
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
		this.armorMakeup = armorMakeup;
		this.properties = new ArmorProperties(1, absorbRatio, maxAbsorb);
	}
	
	public void setBinding(String binding, ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(!stack.hasTagCompound()) {
			tag = new NBTTagCompound();
		}
		tag.setString("Binding", binding);
		stack.setTagCompound(tag);
	}
	
	public String getBinding(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(stack.hasTagCompound() && tag.hasKey("Binding")) {
			return tag.getString("Binding");
		}
		return this.binding;
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
	
	public ArmorTypes getArmorType() {
		return this.armorMakeup;
	}
	
	public double getBonusAttackDamage() {
		return this.bonusDamage;
	}
	
	public Item setMaximumDamage(int maxDamage, ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(!stack.hasTagCompound()) {
			tag = new NBTTagCompound();
		}
		tag.setInteger("MaxDamage", this.maxDamage + maxDamage);
		stack.setTagCompound(tag);
		return this;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(stack.hasTagCompound() && tag.hasKey("MaxDamage")) {
			return tag.getInteger("MaxDamage");
		}
		return this.maxDamage;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "Tier: " + TextFormatting.YELLOW + "" + TextFormatting.ITALIC + this.tier);
		tooltip.add(TextFormatting.BLUE + "Binding: " + TextFormatting.GRAY + this.getBinding(stack));
    }
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		Random rand = new Random();
		ArmorProperties propCopy = new ArmorProperties(properties.Priority, properties.AbsorbRatio, properties.AbsorbMax);
		
		if(armor.getItem() instanceof ArmorBonusesBase) {
			ArmorTypes makeup = ((ArmorBonusesBase)armor.getItem()).getArmorType();
			Entity ent = source.getTrueSource();
			EntityLivingBase attacker = null;
			WeaponEffectiveness we = null;
			if(ent instanceof EntityLivingBase) {
				attacker = (EntityLivingBase)ent;
			}
			//System.out.println("Attacker: " + attacker.getClass().getCanonicalName() + ", Mainhand: " + attacker.getHeldItem(EnumHand.MAIN_HAND));
			System.out.println("Armor makeup: " + makeup);
			if(makeup == ArmorTypes.SOFT) {
				propCopy.Priority = 2;
			}
			
			if(attacker != null && attacker.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ToolStabSword) {
				System.out.println("Attacker's mainhand item is a ToolStabSword");
				we = WeaponEffectiveness.getWeaponEffectiveness(((ToolStabSword)attacker.getHeldItem(EnumHand.MAIN_HAND).getItem()).getWeaponType());
			}else if(attacker != null && attacker.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ToolSlashSword) {
				System.out.println("Attacker's mainhand item is a ToolSlashSword");
				we = WeaponEffectiveness.getWeaponEffectiveness(((ToolSlashSword)attacker.getHeldItem(EnumHand.MAIN_HAND).getItem()).getWeaponType());
			}else if(source.getImmediateSource() instanceof EntityArrow) {
				System.out.println("Attacker used an arrow");
				we = WeaponEffectiveness.getWeaponEffectiveness("arrow");
			}else if(source.getImmediateSource() instanceof EntitySpear) {
				we = WeaponEffectiveness.getWeaponEffectiveness("thrown_spear");
			}
			
			if(we != null) {
				if((rand.nextInt(100)+1) < we.getPenChanceByArmorType(makeup)) {
					propCopy.AbsorbRatio = 0;
					propCopy.Armor -= 1;
					System.out.println("Damage bypasses armor!");
				}else {
					propCopy.AbsorbRatio *= we.getEffectByArmorType(makeup);
					System.out.println("Effectiveness: " + we.getEffectByArmorType(makeup));
				}
			}
		}
		System.out.println(damage);
		
		return propCopy;
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
