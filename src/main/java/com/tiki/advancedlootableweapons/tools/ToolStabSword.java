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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolStabSword extends Item implements IHasModel{
	
	private float attackDamage;
	private double attackSpeed;
	private final ToolMaterial material;
	private double bonusDamage;
	private float reach;
	private boolean rand;
	protected double addedDamage;
	private static String[] randName1 = new String[] {"Repuslor", "Balmung", "Gram", "Arondight", "Caladbolg", "Chandrahas", "Colada", "Mors", "Durendal", "Ecke", "Hauteclere", "Mimung", "Naegling", "Tizona", "Tyrfing", "Zulfiqar"};
	private static String[] randName2 = new String[] {"Lucent", "Lambent", "Dark", "Dusk", "Aphotic", "Radiant", "Scintillant", "Vacuous", "Nixing", "Abnegating", "Collector of Heads,", "Triumphant"};
	private Random randGen = new Random();
	protected NBTTagCompound nbt = new NBTTagCompound();
	
	public ToolStabSword(String name, ToolMaterial material, String type) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.material = material;
		this.maxStackSize = 1;
		this.bonusDamage = 0;
		this.getAttributes(type, material);
		this.nbt.setInteger("maxDurability", nbt.getInteger("maxDurability"));
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
		NBTTagCompound newNBT = new NBTTagCompound();
		newNBT.setInteger("maxDurability", maxDamage);
		//this.nbt.setInteger("maxDurability", maxDamage);
		stack.setTagCompound(newNBT);//this.nbt);
	}
	
	private void getAttributes(String type, ToolMaterial material) {		
		switch(type){
			case "dagger":
				this.attackSpeed = ConfigHandler.GLOBAL_DAGGER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_DAGGER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.6F;
				break;
			case "kabutowari":
				this.attackSpeed = ConfigHandler.GLOBAL_KABUTOWARI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_KABUTOWARI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.1F;
				break;
			case "rapier":
				this.attackSpeed = ConfigHandler.GLOBAL_RAPIER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_RAPIER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.1F;
				break;
			case "talwar":
				this.attackSpeed = ConfigHandler.GLOBAL_TALWAR_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_TALWAR_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.9F;
				break;
			case "cleaver":
				this.attackSpeed = ConfigHandler.GLOBAL_CLEAVER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_CLEAVER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.5F;
				break;
			case "mace":
				this.attackSpeed = ConfigHandler.GLOBAL_MACE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_MACE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.5F;
				break;
			case "staff":
				this.attackSpeed = ConfigHandler.GLOBAL_STAFF_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_STAFF_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 6.1F;
				break;
			case "spear":
				this.attackSpeed = ConfigHandler.GLOBAL_SPEAR_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_SPEAR_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 6.5F;
				break;
		}
	}
	
	/*
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
		this.rand = randGen.nextBoolean();
		
		if(this.rand == true) {
			stack.setStackDisplayName(TextFormatting.AQUA + randName2[randGen.nextInt(12)] + " " +  randName1[randGen.nextInt(16)]);
		}else {
			stack.setStackDisplayName(TextFormatting.AQUA + randName1[randGen.nextInt(16)]);
		}
		
		if(randGen.nextBoolean() == true) {
			this.randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
		}else{
			this.randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
			this.randDamage += (this.randDamage * 2);
		}
		
		this.nbt.setFloat("bonusDamage", this.randDamage);
		this.tempRandDamage = nbt.getFloat("bonusDamage");
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.tempRandDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)((this.getAttackDamage() + 1) - nbt.getDouble("reducedDamage")), 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
		if(ConfigHandler.USE_CUSTOM_WEAPON_REACH) {
			stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0), EntityEquipmentSlot.MAINHAND);
		}
        stack.setTagCompound(this.nbt);
	}
	*/
	
	public void generateNameAndModifiers(ItemStack stack, double addedDamage) {
		
		//System.out.println("Running GenerateNameAndModifiers!!!!!!!!");
		//float tempRandDamage;
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
		
		randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
		totalDamage = ((this.getAttackDamage() + 1) + randDamage + addedDamage);
		newTag.setDouble("totalDamage", totalDamage);
		stack.setTagCompound(newTag);
		
		if(this.rand == true) {
			stack.setStackDisplayName(TextFormatting.AQUA + randName2[randGen.nextInt(12)] + " " +  randName1[randGen.nextInt(16)]);
		}else {
			stack.setStackDisplayName(TextFormatting.AQUA + randName1[randGen.nextInt(16)]);
		}
		//if(randGen.nextBoolean() == true) {
		
		//}else{
			//randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
			//randDamage += (randDamage * 2);
		//}
		
		//this.nbt.setFloat("bonusDamage", randDamage);
		//tempRandDamage = this.nbt.getFloat("bonusDamage");
		//this.nbt.setDouble("addedDamage", addedDamage);
		//this.addedDamage = this.nbt.getDouble("addedDamage");
		//System.out.println("Random damage is: " + randDamage + ", TempRandDamage is: " + tempRandDamage);
		//System.out.println("GetDamage: " + (this.getAttackDamage() + 1) + ", addedDamage: " + addedDamage);
		
		//System.out.println("Total Damage: " + totalDamage);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", totalDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
		if(ConfigHandler.USE_CUSTOM_WEAPON_REACH) {
			stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0), EntityEquipmentSlot.MAINHAND);
		}
		//System.out.println("Running Modifiers!!!!!!!!");
		//stack.setTagCompound(this.nbt);
	}
	
	/*
	public void generateModifiers(ItemStack stack, double addedDamage){
		double totalDamage;
		float randDamage;
		
		randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
		
		totalDamage = ((this.getAttackDamage() + 1) + randDamage + addedDamage);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", totalDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
		if(ConfigHandler.USE_CUSTOM_WEAPON_REACH) {
			stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0), EntityEquipmentSlot.MAINHAND);
		}
		//stack.setTagCompound(this.nbt);
	}
	*/
	
	public double getAttackSpeed() {
		return this.attackSpeed;
	}
	
	public static UUID getAttackSpeedModifierUUID() {
		return ATTACK_SPEED_MODIFIER;
	}
	
	@Override
	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
		float f = playerIn.rotationPitch;
        float f1 = playerIn.rotationYaw;
        double d0 = playerIn.posX;
        double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
        double d2 = playerIn.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = playerIn.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue() + this.getReach();
        Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
        return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		World world = entityLiving.getEntityWorld();
		float reach = this.getReach();//(float) ((this.getReach() - 5.5) + entityLiving.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue());
		//System.out.println("reach is: " + reach);
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
		//System.out.println("minX: " + axis.minX + ", minY: " + axis.minY + ", minZ: " + axis.minZ + ", maxX: " + axis.maxX + ", maxY: " + axis.maxY + ", maxZ: " + axis.maxZ);
		
		List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, axis, EntitySelectors.NOT_SPECTATING);
		//System.out.println(entList);
		EntityLivingBase ent = null;
		
		double distClosest = Double.MAX_VALUE;
		for(EntityLivingBase e : entList) {
			if(!e.equals(entityLiving)) {
				double dist = e.getPositionVector().distanceTo(entityLiving.getPositionVector());
				//System.out.println("dist: " + dist);
				
				if(dist < distClosest) {
					ent = e;
					distClosest = dist;
				}
			}
		}
		
		//System.out.println(ent);
		if(ent != null) {
			System.out.println("entity is not null!");
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
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		//tooltip.add(TextFormatting.GRAY + "" + (this.attackSpeed + 4.0) + " Attack Speed");
    }
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack)
    {
        return true;
    }
	
	public float getAttackDamage(){
		return this.attackDamage;
    }
	
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Block block = state.getBlock();

        if (block == Blocks.WEB)
        {
            return 15.0F;
        }
        else
        {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.WEB;
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
					target.setHealth(target.getHealth() - (float)this.bonusDamage);
					break;
				}
			}else {
				this.bonusDamage = 0.0D;
				target.setHealth(target.getHealth() - (float)this.bonusDamage);
			}
		}
		
        stack.attemptDamageItem(1, new Random(), null);//damageItem(1, attacker);
        //target.onDeath(DamageSource.causePlayerDamage((EntityPlayer)attacker));
        return true;
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
	
	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return false;
	}
	
	public float getReach() {
		return this.reach;
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
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
    {
        return enchantment.type.canEnchantItem(new ItemStack(Items.DIAMOND_SWORD).getItem());
    }
	
	public String getToolMaterialName()
    {
		if(this.material.toString().equalsIgnoreCase(ItemInit.MAT_SHADOW_PLATINUM.toString())) {
			return "Shadow Platinum";
		}else if(this.material.toString().equalsIgnoreCase(ItemInit.MAT_FROST_STEEL.toString())) {
			return "Frost Steel";
		}
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
