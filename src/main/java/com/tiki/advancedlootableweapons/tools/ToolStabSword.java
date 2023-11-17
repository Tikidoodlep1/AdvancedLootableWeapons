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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolStabSword extends Item implements IHasModel {
	private float attackDamage;
	private double attackSpeed;
	private final ToolMaterial material;
	private double bonusDamage;
	private float reach;
	private boolean rand;
	protected double addedDamage;
	private String type;
	private String[] randName1 = new String[] {"alw.weapon_name.repulsor.name", "alw.weapon_name.balmung.name", "alw.weapon_name.gram.name", "alw.weapon_name.arondight.name", "alw.weapon_name.caladbolg.name", "alw.weapon_name.chandrahas.name", "alw.weapon_name.colada.name", "alw.weapon_name.mors.name", "alw.weapon_name.durendal.name", "alw.weapon_name.ecke.name", "alw.weapon_name.hauteclere.name", "alw.weapon_name.mimung.name", "alw.weapon_name.naegling.name", "alw.weapon_name.tizona.name", "alw.weapon_name.tyrfing.name", "alw.weapon_name.zulfiqar.name"};
	private String[] randName2 = new String[] {"alw.weapon_modifier.lucent.name", "alw.weapon_modifier.lambent.name", "alw.weapon_modifier.dark.name", "alw.weapon_modifier.dusk.name", "alw.weapon_modifier.aphotic.name", "alw.weapon_modifier.radiant.name", "alw.weapon_modifier.scintillant.name", "alw.weapon_modifier.vacuous.name", "alw.weapon_modifier.nixing.name", "alw.weapon_modifier.abnegating.name", "alw.weapon_modifier.collector_of_heads.name", "alw.weapon_modifier.triumphant.name"};
	private Random randGen = new Random();
	
	public ToolStabSword(String name, ToolMaterial material, String type) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		ItemInit.weaponItems.add(this);
		
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
	
	public void setUnquenched(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(!stack.hasTagCompound()) {
			tag = new NBTTagCompound();
		}
		tag.setBoolean("quenched", false);
		stack.setTagCompound(tag);
	}
	
	public void setQuenched(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(!stack.hasTagCompound()) {
			tag = new NBTTagCompound();
		}
		tag.setBoolean("quenched", true);
		stack.setTagCompound(tag);
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
		
		this.setMaximumDamage(stack, addedDurability);
		this.setUnquenched(stack);
		
		String matName = material.getDisplayName();
		int index = matName.indexOf("Ingot");
		if(index != -1) {
			matName = matName.substring(0, index - 1);
		}
		
		if(this.rand == true) {
			stack.setStackDisplayName(new TextComponentTranslation(randName2[randGen.nextInt(12)]).getFormattedText() + " " +  new TextComponentTranslation(randName1[randGen.nextInt(16)]).getFormattedText() + " (" + this.getItemStackDisplayName(stack) + ")");
		}else {
			stack.setStackDisplayName(new TextComponentTranslation(randName1[randGen.nextInt(16)]).getFormattedText() + " (" + this.getItemStackDisplayName(stack) + ")");
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
		AxisAlignedBB aabb = new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d1.x, vec3d1.y, vec3d1.z);
		aabb.contract(0.9d, 0d, 0.9d);
		
		List<Entity> ents = world.getEntitiesWithinAABBExcludingEntity(entityLiving, aabb);
		Entity ent = null;
		if(ents.size() > 0) {
			ent = ents.get(0);
		}
		if(ent != null) {
			if((ent.getPositionVector().distanceTo(entityLiving.getPositionVector()) < reach && entityLiving.canEntityBeSeen(ent))) {
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
			tooltip.add(TextFormatting.RED + new TextComponentTranslation("alw.effectiveness.studded.level").getFormattedText() + " " + studdedEffect + "/8");
			tooltip.add(TextFormatting.DARK_RED + new TextComponentTranslation("alw.effectiveness.studded.pierce").getFormattedText() + " " + we.getStuddedPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.GREEN + new TextComponentTranslation("alw.effectiveness.chain.level").getFormattedText() + " " + chainEffect + "/8");
			tooltip.add(TextFormatting.DARK_GREEN + new TextComponentTranslation("alw.effectiveness.chain.pierce").getFormattedText() + " " + we.getChainPenChance() + "%");
			tooltip.add("");
			tooltip.add(TextFormatting.AQUA + new TextComponentTranslation("alw.effectiveness.plate.level").getFormattedText() + " " + plateEffect + "/8");
			tooltip.add(TextFormatting.DARK_BLUE + new TextComponentTranslation("alw.effectiveness.plate.pierce").getFormattedText() + " " + we.getPlatePenChance() + "%");
		}else if(Keyboard.isKeyDown(run.getKeyCode())) {
			NBTTagCompound tag = stack.getTagCompound();
			if(tag != null && tag.hasKey("colors")) {
				tooltip.add(TextFormatting.RED + new TextComponentTranslation("alw.colors.title").getFormattedText());
				tooltip.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.colors.bot_outline").getFormattedText() + " " + Integer.toHexString(tag.getIntArray("colors")[0]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.colors.top_outline").getFormattedText() + " " +  Integer.toHexString(tag.getIntArray("colors")[1]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.colors.mid_lowlight").getFormattedText() + " " +  Integer.toHexString(tag.getIntArray("colors")[2]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.colors.mid_highlight").getFormattedText() + " " +  Integer.toHexString(tag.getIntArray("colors")[3]));
				tooltip.add(TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.colors.shine").getFormattedText() + " " +  Integer.toHexString(tag.getIntArray("colors")[4]));
			}
		}else {
			tooltip.add(TextFormatting.GRAY + new TextComponentTranslation("alw.hold").getFormattedText() + " " + sneak.getDisplayName() + " " + new TextComponentTranslation("alw.effectiveness.info.name").getFormattedText());
			tooltip.add(TextFormatting.GRAY + new TextComponentTranslation("alw.hold").getFormattedText() + " " + run.getDisplayName() + " " + new TextComponentTranslation("alw.colors.info.name").getFormattedText());
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
