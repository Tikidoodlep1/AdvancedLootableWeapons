package com.tiki.advancedlootableweapons.tools;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolSlashSword extends ItemSword implements IHasModel{
	
	private float attackDamage;
	private double attackSpeed;
	private final ToolMaterial material;
	private double bonusDamage;
	private float reach;
	private boolean rand;
	private String type;
	private String[] randName1 = new String[] {"Repuslor", "Balmung", "Gram", "Arondight", "Caladbolg", "Chandrahas", "Colada", "Mors", "Durendal", "Ecke", "Hauteclere", "Mimung", "Naegling", "Tizona", "Tyrfing", "Zulfiqar"};
	private String[] randName2 = new String[] {"Lucent", "Lambent", "Dark", "Dusk", "Aphotic", "Radiant", "Scintillant", "Vacuous", "Nixing", "Abnegating", "Collector of Heads,", "Triumphant"};
	private Random randGen = new Random();
	private NBTTagCompound nbt = new NBTTagCompound();
	
	public ToolSlashSword(String name, ToolMaterial material, String type) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.material = material;
		this.type = type;
		this.setMaxDamage(material.getMaxUses());
		this.maxStackSize = 1;
		this.bonusDamage = 0;
		this.getAttributes(type, material);
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		NBTTagCompound tag = new NBTTagCompound();
		tag = stack.getTagCompound();
		int durability;
		if(tag == null || this.nbt == null) {
			return this.material.getMaxUses();
		}else {
			durability = tag.getInteger("maxDurability");
			return this.material.getMaxUses() + durability;
		}
	}
	
	public void setMaximumDamage(ItemStack stack, int maxDamage) {
		this.nbt.setInteger("maxDurability", maxDamage);
		stack.setTagCompound(this.nbt);
	}
	
	private void getAttributes(String type, ToolMaterial material) {
		switch(type){
			case "longsword":
				this.attackSpeed = ConfigHandler.GLOBAL_LONGSWORD_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_LONGSWORD_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.14F;
				break;
			case "kodachi":
				this.attackSpeed = ConfigHandler.GLOBAL_KODACHI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_KODACHI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.49F;
				break;
			case "battleaxe":
				this.attackSpeed = ConfigHandler.GLOBAL_BATTLEAXE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_BATTLEAXE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.20F;
				break;
			case "zweihander":
				this.attackSpeed = ConfigHandler.GLOBAL_ZWEIHANDER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_ZWEIHANDER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.76F;
				break;
			case "nodachi":
				this.attackSpeed = ConfigHandler.GLOBAL_NODACHI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_NODACHI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.66F;
				break;
			case "sabre":
				this.attackSpeed = ConfigHandler.GLOBAL_SABRE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_SABRE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.92F;
				break;
			case "makhaira":
				this.attackSpeed = ConfigHandler.GLOBAL_MAKHAIRA_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_MAKHAIRA_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.56F;
		}
	}
	
	public void generateNameAndModifiers(ItemStack stack, double addedDamage) {
		float randDamage;
		double totalDamage;
		this.rand = randGen.nextBoolean();
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound newTag = new NBTTagCompound();
		if(tag != null) {
			if(tag.hasKey("maxDurability", 99)) {
				newTag.setInteger("maxDurability", tag.getInteger("maxDurability"));
			}
		}
		stack.setTagCompound(newTag);
		
		if(this.rand == true) {
			stack.setStackDisplayName(TextFormatting.AQUA + randName2[randGen.nextInt(12)] + " " +  randName1[randGen.nextInt(16)] + " (" + this.getToolMaterialName() + " " + this.type.substring(0, 1).toUpperCase() + this.type.substring(1) + ")");
		}else {
			stack.setStackDisplayName(TextFormatting.AQUA + randName1[randGen.nextInt(16)] + " (" + this.getToolMaterialName() + " " + this.type.substring(0, 1).toUpperCase() + this.type.substring(1) + ")");
		}
		
		randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
		
		totalDamage = ((this.getAttackDamage() + 1) + randDamage + addedDamage);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", totalDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
		if(ConfigHandler.USE_CUSTOM_WEAPON_REACH) {
			stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0), EntityEquipmentSlot.MAINHAND);
		}
	}
	
	public static UUID getAttackSpeedModifierUUID() {
		return ATTACK_SPEED_MODIFIER;
	}
	
//	@Override
//	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
//		float f = playerIn.rotationPitch;
//        float f1 = playerIn.rotationYaw;
//        double d0 = playerIn.posX;
//        double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
//        double d2 = playerIn.posZ;
//        Vec3d vec3d = new Vec3d(d0, d1, d2);
//        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
//        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
//        float f4 = -MathHelper.cos(-f * 0.017453292F);
//        float f5 = MathHelper.sin(-f * 0.017453292F);
//        float f6 = f3 * f4;
//        float f7 = f2 * f4;
//        double d3 = playerIn.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue() + this.getReach();
//        Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
//        return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
//	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		World world = entityLiving.getEntityWorld();
		float reach = this.getReach();
		float f = entityLiving.rotationPitch;
        float f1 = entityLiving.rotationYaw;
        double d0 = entityLiving.posX;
        double d1 = entityLiving.posY + (double)entityLiving.getEyeHeight();
        double d2 = entityLiving.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3d vec3d1 = vec3d.addVector((double)f6 * reach, (double)f5 * reach, (double)f7 * reach);
        
		AxisAlignedBB axis = new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d1.x, vec3d1.y, vec3d1.z);
		
		List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, axis, EntitySelectors.NOT_SPECTATING);
		EntityLivingBase ent = null;
		
		double distClosest = Double.MAX_VALUE;
		for(EntityLivingBase e : entList) {
			if(!e.equals(entityLiving)) {
				double dist = e.getPositionVector().distanceTo(entityLiving.getPositionVector());
				
				if(dist < this.getReach() && dist < distClosest) {
					ent = e;
					distClosest = dist;
				}
			}
		}
		
		RayTraceResult trace = world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
		if(ent != null) {
			if(trace == null || (ent.getPositionVector().distanceTo(entityLiving.getPositionVector()) < trace.getBlockPos().distanceSq(entityLiving.getPosition()) && entityLiving.canEntityBeSeen(ent))) {
				if(entityLiving instanceof EntityPlayer) {
					((EntityPlayer) entityLiving).attackTargetEntityWithCurrentItem(ent);
					if(!((EntityPlayer) entityLiving).isCreative()) {
						stack.attemptDamageItem(1, new Random(), null);
					}
				}else {
					entityLiving.attackEntityAsMob(ent);
					stack.attemptDamageItem(1, new Random(), null);
				}
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		//tooltip.add(TextFormatting.GRAY + "" + (this.attackSpeed + 4) + " Attack Speed");
    }

	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.getAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0));
            multimap.put(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0));
        }
        
        return multimap;
    }
	
	public float getAttackDamage(){
		float x = this.attackDamage;
        return x;
    }
	
	public float getReach() {
		return reach;
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		Iterable<ItemStack> armorlist = attacker.getArmorInventoryList();
		ArmorBonusesBase armor;
		for(ItemStack i: armorlist) {
			if(i.getItem() instanceof ArmorBonusesBase) {
				armor = (ArmorBonusesBase)i.getItem();
				if(armor.armorType == EntityEquipmentSlot.CHEST) {
					this.bonusDamage = armor.getBonusAttackDamage();
					if(attacker instanceof EntityPlayer) {
						target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), (float)this.bonusDamage);
					}else {
						target.attackEntityFrom(DamageSource.causeMobDamage(attacker), (float)this.bonusDamage);
					}
					break;
				}
			}
		}
		
        return true;
    }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) {
        	return true; 
        }else if(mat.isItemEqualIgnoreDurability(repair)){
        	return true;
        }else {
        	return false;
        }
    }
	
	public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }
	
	public String getToolMaterialName()
    {
        return this.material.toString();
    }
	
	public ToolMaterial getToolMaterial() {
		return this.material;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}
