package com.tiki.advancedlootableweapons.tools;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.util.WeaponEffectiveness;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
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
		if(tag == null) {
			return this.material.getMaxUses();
		}else {
			durability = tag.getInteger("maxDurability");
			return this.material.getMaxUses() + durability;
		}
	}
	
	public void setMaximumDamage(ItemStack stack, int maxDamage) {
		NBTTagCompound newNBT;
		if(stack.hasTagCompound()) {
			newNBT = stack.getTagCompound();
		}else {
			newNBT = new NBTTagCompound();
		}
		newNBT.setInteger("maxDurability", maxDamage);
		stack.setTagCompound(newNBT);
	}
	
	private void getAttributes(String type, ToolMaterial material) {
		switch(type){
			case "longsword":
				this.attackSpeed = ConfigHandler.GLOBAL_LONGSWORD_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_LONGSWORD_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.14F;
				break;
			case "kodachi":
				this.attackSpeed = ConfigHandler.GLOBAL_KODACHI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_KODACHI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.49F;
				break;
			case "battleaxe":
				this.attackSpeed = ConfigHandler.GLOBAL_BATTLEAXE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_BATTLEAXE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.32F;
				break;
			case "zweihander":
				this.attackSpeed = ConfigHandler.GLOBAL_ZWEIHANDER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_ZWEIHANDER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.25F;
				break;
			case "nodachi":
				this.attackSpeed = ConfigHandler.GLOBAL_NODACHI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_NODACHI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.66F;
				break;
			case "sabre":
				this.attackSpeed = ConfigHandler.GLOBAL_SABRE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_SABRE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.92F;
				break;
			case "makhaira":
				this.attackSpeed = ConfigHandler.GLOBAL_MAKHAIRA_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_MAKHAIRA_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.56F;
		}
	}
	
	public String getWeaponType() {
		return this.type;
	}
	
	public void generateNameAndModifiers(ItemStack stack, double addedDamage, String matName) {
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
			stack.setStackDisplayName(TextFormatting.AQUA + randName2[randGen.nextInt(12)] + " " +  randName1[randGen.nextInt(16)] + " (" + matName + " "  + this.type.substring(0, 1).toUpperCase() + this.type.substring(1) + ")");
		}else {
			stack.setStackDisplayName(TextFormatting.AQUA + randName1[randGen.nextInt(16)] + " (" + matName + " "  + this.type.substring(0, 1).toUpperCase() + this.type.substring(1) + ")");
		}
		
		randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
		
		totalDamage = ((this.getAttackDamage() + 1) + randDamage + addedDamage);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", totalDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
		if(ConfigHandler.USE_CUSTOM_WEAPON_REACH) {
			stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 4.0D, 0), EntityEquipmentSlot.MAINHAND);
		}
	}
	
	public static UUID getAttackSpeedModifierUUID() {
		return ATTACK_SPEED_MODIFIER;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		World world = entityLiving.getEntityWorld();
		float reach = this.getReach();
		
		RayTraceResult trace = entityLiving.rayTrace(reach, Minecraft.getMinecraft().getRenderPartialTicks());
		List<Entity> ents = world.getEntitiesWithinAABBExcludingEntity(entityLiving, new AxisAlignedBB(trace.getBlockPos()).shrink(0.9));
		Entity ent = null;
		if(ents.size() > 0) {
			ent = ents.get(0);
		}
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
            multimap.put(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 4.0D, 0));
        }
        
        return multimap;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		KeyBinding sneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
		if(Keyboard.isKeyDown(sneak.getKeyCode())) {
			WeaponEffectiveness we = WeaponEffectiveness.getWeaponEffectiveness(type);
			int studdedEffect = (int)Math.ceil(((we.getStuddedEffect()*100)/6)-9);
			int chainEffect = (int)Math.ceil(((we.getChainEffect()*100)/6)-9);
			int plateEffect = (int)Math.ceil(((we.getPlateEffect()*100)/6)-9);
			tooltip.add(TextFormatting.RED + "Effectiveness against Studded armor: " + studdedEffect);
			tooltip.add(TextFormatting.DARK_RED + "Chance to pierce Studded armor: " + we.getStuddedPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.GREEN + "Effectiveness against Chain armor: " + chainEffect);
			tooltip.add(TextFormatting.DARK_GREEN + "Chance to pierce Chain armor: " + we.getChainPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.AQUA + "Effectiveness against Plate armor: " + plateEffect);
			tooltip.add(TextFormatting.DARK_BLUE + "Chance to pierce Plate armor: " + we.getPlatePenChance() + "%");
		}else {
			tooltip.add(TextFormatting.GRAY + "Hold " + sneak.getDisplayName() + " for Effectiveness Information");
		}
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
