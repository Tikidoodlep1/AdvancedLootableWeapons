package com.tiki.advancedlootableweapons.tools;

import java.util.Arrays;
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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
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
	private String type;
	private static String[] randName1 = new String[] {"Repuslor", "Balmung", "Gram", "Arondight", "Caladbolg", "Chandrahas", "Colada", "Mors", "Durendal", "Ecke", "Hauteclere", "Mimung", "Naegling", "Tizona", "Tyrfing", "Zulfiqar"};
	private static String[] randName2 = new String[] {"Lucent", "Lambent", "Dark", "Dusk", "Aphotic", "Radiant", "Scintillant", "Vacuous", "Nixing", "Abnegating", "Collector of Heads,", "Triumphant"};
	private Random randGen = new Random();
	
	public ToolStabSword(String name, ToolMaterial material, String type) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.material = material;
		this.type = type;
		this.maxStackSize = 1;
		this.bonusDamage = 0;
		this.getAttributes(type, material);
		this.setMaxDamage(material.getMaxUses());
	}
	
	public void setColors(ItemStack stack, int[] colors) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null) {
			tag = new NBTTagCompound();
		}
		
		tag.setIntArray("colors", colors);
		
		stack.setTagCompound(tag);
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
			case "dagger":
				this.attackSpeed = ConfigHandler.GLOBAL_DAGGER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_DAGGER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.21F;
				break;
			case "kabutowari":
				this.attackSpeed = ConfigHandler.GLOBAL_KABUTOWARI_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_KABUTOWARI_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.39F;
				break;
			case "rapier":
				this.attackSpeed = ConfigHandler.GLOBAL_RAPIER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_RAPIER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.29F;
				break;
			case "talwar":
				this.attackSpeed = ConfigHandler.GLOBAL_TALWAR_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_TALWAR_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 4.0F;
				break;
			case "cleaver":
				this.attackSpeed = ConfigHandler.GLOBAL_CLEAVER_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_CLEAVER_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.28F;
				break;
			case "mace":
				this.attackSpeed = ConfigHandler.GLOBAL_MACE_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_MACE_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 3.75F;
				break;
			case "staff":
				this.attackSpeed = ConfigHandler.GLOBAL_STAFF_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_STAFF_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.28F;
				break;
			case "spear":
				this.attackSpeed = ConfigHandler.GLOBAL_SPEAR_ATTACK_SPEED - 4.0;
				this.attackDamage = ConfigHandler.GLOBAL_SPEAR_BASE_DAMAGE + material.getAttackDamage();
				this.reach = 5.74F;
				break;
		}
	}
	
	public void generateNameAndModifiers(ItemStack stack, double addedDamage, int addedDurability, ItemStack material) {
		float randDamage;
		double totalDamage;
		this.rand = randGen.nextBoolean();
//		NBTTagCompound tag = stack.getTagCompound();
//		NBTTagCompound newTag = new NBTTagCompound();
//		if(tag != null) {
//			if(tag.hasKey("maxDurability", 99)) {
//				newTag.setInteger("maxDurability", tag.getInteger("maxDurability"));
//			}
//		}
//		stack.setTagCompound(newTag);
		this.setMaximumDamage(stack, addedDurability);
		
		String matName = material.getDisplayName();
		int index = matName.indexOf("Ingot");
		if(index != -1) {
			matName = matName.substring(0, index - 1);
		}
		
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
	
	public double getAttackSpeed() {
		return this.attackSpeed;
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
	
	public String getWeaponType() {
		return this.type;
	}
	
	@Override
	public void registerModels() {
		if(!ItemInit.generatedItems.contains(this)) {
			Alw.proxy.registerItemRenderer(this, 0, "inventory");
		}
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
		KeyBinding run = Minecraft.getMinecraft().gameSettings.keyBindSprint;
		
		if(Keyboard.isKeyDown(sneak.getKeyCode())) {
			WeaponEffectiveness we = WeaponEffectiveness.getWeaponEffectiveness(type);
			int studdedEffect = (int)Math.ceil(((we.getStuddedEffect()*100)/6)-9);
			int chainEffect = (int)Math.ceil(((we.getChainEffect()*100)/6)-9);
			int plateEffect = (int)Math.ceil(((we.getPlateEffect()*100)/6)-9);
			tooltip.add(TextFormatting.RED + "Effectiveness against Studded armor: " + studdedEffect + "/8");
			tooltip.add(TextFormatting.DARK_RED + "Chance to pierce Studded armor: " + we.getStuddedPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.GREEN + "Effectiveness against Chain armor: " + chainEffect + "/8");
			tooltip.add(TextFormatting.DARK_GREEN + "Chance to pierce Chain armor: " + we.getChainPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.AQUA + "Effectiveness against Plate armor: " + plateEffect + "/8");
			tooltip.add(TextFormatting.DARK_BLUE + "Chance to pierce Plate armor: " + we.getPlatePenChance() + "%");
		}else if(Keyboard.isKeyDown(run.getKeyCode())) {
			NBTTagCompound tag = stack.getTagCompound();
			if(tag != null && tag.hasKey("colors")) {
				tooltip.add(TextFormatting.RED + "Colors: ");
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Bottom Outline: " + Integer.toHexString(tag.getIntArray("colors")[0]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Top Outline: " +  Integer.toHexString(tag.getIntArray("colors")[1]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Middle Lowlight: " +  Integer.toHexString(tag.getIntArray("colors")[2]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Middle Highlight: " +  Integer.toHexString(tag.getIntArray("colors")[3]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Shiny Highlight: " +  Integer.toHexString(tag.getIntArray("colors")[4]));
			}
		}else {
			tooltip.add(TextFormatting.GRAY + "Hold " + sneak.getDisplayName() + " for Effectiveness Information");
			tooltip.add(TextFormatting.GRAY + "Hold " + run.getDisplayName() + " for Color Information");
		}
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
        return enchantment.type.canEnchantItem(Items.IRON_SWORD);
    }
	
	@Deprecated
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
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(!ItemInit.generatedItems.contains(stack.getItem())) {
			return super.getItemStackDisplayName(stack);
		}
		String material = String.valueOf(this.getToolMaterial().toString().toUpperCase().charAt(0));
		String material2 = this.getToolMaterial().toString().toLowerCase().substring(1);
		
		String weaponType = String.valueOf(this.getWeaponType().toUpperCase().charAt(0));
		String weaponType2 = this.getWeaponType().toLowerCase().substring(1);
		
		return new TextComponentTranslation("%s %s", material + material2, weaponType + weaponType2).getFormattedText();
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return false;
    }
}
