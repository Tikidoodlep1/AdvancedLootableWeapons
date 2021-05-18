package com.tiki.advancedlootableweapons.tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
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
	private float randDamage;
	private float tempRandDamage;
	private String[] randName1 = new String[] {"Repuslor", "Balmung", "Gram", "Arondight", "Caladbolg", "Chandrahas", "Colada", "Mors", "Durendal", "Ecke", "Hauteclere", "Mimung", "Naegling", "Tizona", "Tyrfing", "Zulfiqar"};
	private String[] randName2 = new String[] {"Lucent", "Lambent", "Dark", "Dusk", "Aphotic", "Radiant", "Scintillant", "Vacuous", "Nixing", "Abnegating", "Collector of Heads,", "Triumphant"};
	private Random randGen = new Random();
	
	
	public ToolStabSword(String name, ToolMaterial material, String type) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.items.add(this);
		
		this.material = material;
		this.setMaxDamage(material.getMaxUses());
		this.maxStackSize = 1;
		this.bonusDamage = 0;
		this.getAttributes(type, material);
		this.randDamage = (((float)randGen.nextInt(14)) * (this.material.getAttackDamage() / 100)) + randGen.nextFloat();
	}
	
	private void getAttributes(String type, ToolMaterial material) {		
		switch(type){
			case "kabutowari":
				this.attackSpeed = 0.2D;
				this.attackDamage = 1.0F + material.getAttackDamage();
				this.reach = 4.6F;
				break;
			case "dagger":
				this.attackSpeed = 0.0D;
				this.attackDamage = 1.5F + material.getAttackDamage();
				this.reach = 4.1F;
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
		this.rand = randGen.nextBoolean();
		NBTTagCompound nbt = new NBTTagCompound();
		
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
		
		nbt = stack.getOrCreateSubCompound("damageModifier");
		nbt.setFloat("bonusDamage", this.randDamage);
		this.tempRandDamage = nbt.getFloat("bonusDamage");
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Alw.BONUS_ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.tempRandDamage, 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)(this.getAttackDamage() + 1), 0), EntityEquipmentSlot.MAINHAND);
		stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0), EntityEquipmentSlot.MAINHAND);
        stack.addAttributeModifier(Alw.ATTACK_RANGE.getName(), new AttributeModifier(Alw.ATTACK_RANGE_MODIFIER, "weapon modifier", (double)this.getReach() - 5.0D, 0), EntityEquipmentSlot.MAINHAND);
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GRAY + "" + (this.attackSpeed + 4) + " Attack Speed");
		
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
		float x = this.attackDamage;
        return x;
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
		
        stack.damageItem(1, attacker);
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
	
	public float getReach() {
		return reach;
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
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
