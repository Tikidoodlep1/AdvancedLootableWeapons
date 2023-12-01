package tiki.advancedlootableweapons.tools;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.util.WeaponEffectiveness;

public class ToolSlashSword extends ItemSword implements IHasModel {
	
	private float attackDamage;
	private double attackSpeed;
	private final ToolMaterial material;
	private double bonusDamage;
	private float reach;
	private boolean rand;
	private String type;
	private String[] randName1 = new String[] {"alw.weapon_name.repulsor.name", "alw.weapon_name.balmung.name", "alw.weapon_name.gram.name", "alw.weapon_name.arondight.name", "alw.weapon_name.caladbolg.name", "alw.weapon_name.chandrahas.name", "alw.weapon_name.colada.name", "alw.weapon_name.mors.name", "alw.weapon_name.durendal.name", "alw.weapon_name.ecke.name", "alw.weapon_name.hauteclere.name", "alw.weapon_name.mimung.name", "alw.weapon_name.naegling.name", "alw.weapon_name.tizona.name", "alw.weapon_name.tyrfing.name", "alw.weapon_name.zulfiqar.name"};
	private String[] randName2 = new String[] {"alw.weapon_modifier.lucent.name", "alw.weapon_modifier.lambent.name", "alw.weapon_modifier.dark.name", "alw.weapon_modifier.dusk.name", "alw.weapon_modifier.aphotic.name", "alw.weapon_modifier.radiant.name", "alw.weapon_modifier.scintillant.name", "alw.weapon_modifier.vacuous.name", "alw.weapon_modifier.nixing.name", "alw.weapon_modifier.abnegating.name", "alw.weapon_modifier.collector_of_heads.name", "alw.weapon_modifier.triumphant.name"};
	private Random randGen = new Random();
	
	public ToolSlashSword(String name, ToolMaterial material, String type) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		ItemInit.weaponItems.add(this);
		
		this.material = material;
		this.type = type;
		this.setMaxDamage(material.getMaxUses());
		this.maxStackSize = 1;
		this.bonusDamage = 0;
		this.getAttributes(type, material);
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
	
	public void generateNameAndModifiers(ItemStack stack, double addedDamage, int addedDurability, ItemStack material) {
		float randDamage;
		double totalDamage;
		this.rand = randGen.nextBoolean();
		
		this.setMaximumDamage(stack, addedDurability);
		
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
	
	@Override
	public void registerModels() {
		if(!ItemInit.generatedItems.contains(this)) {
			Alw.proxy.registerItemRenderer(this, 0, "inventory");
		}
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
	
	public float getAttackDamage() {
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
