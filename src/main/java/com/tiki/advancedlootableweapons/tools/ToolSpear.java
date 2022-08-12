package com.tiki.advancedlootableweapons.tools;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.util.WeaponEffectiveness;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolSpear extends ToolStabSword {
	
	public ToolSpear(String name, ToolMaterial material) {
		super(name, material, "spear");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		KeyBinding sneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(Keyboard.isKeyDown(sneak.getKeyCode())) {
			WeaponEffectiveness we = WeaponEffectiveness.getWeaponEffectiveness("thrown_spear");
			tooltip.add("");
			tooltip.add(TextFormatting.LIGHT_PURPLE + "Chance to pierce Chain armor when thrown: " + we.getChainPenChance() + "%");
			tooltip.add(TextFormatting.AQUA + "Chance to pierce Plate armor when thrown: " + we.getPlatePenChance() + "%");
		}
	}
	
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
	
	public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 0.8F)
        {
            f = 0.8F;
        }

        return f;
    }
	
	private void createSpearEntity(World worldIn, EntityPlayer playerIn, ItemStack stack, float f){
		if (!worldIn.isRemote)
        {
			double totalDamage;
			int maxDur;
			if(stack.hasTagCompound()) {
				NBTTagCompound tag = stack.getTagCompound();
				if(tag.hasKey("totalDamage", 99)) {
					totalDamage = tag.getDouble("totalDamage");
				}else {
					totalDamage = this.getAttackDamage() + 1;
				}
			}else {
				totalDamage = this.getAttackDamage() + 1;
			}
			if((this.getMaxDamage(stack) - this.getToolMaterial().getMaxUses()) < 0) {
				maxDur = 0;
			}else {
				maxDur = (this.getMaxDamage(stack) - this.getToolMaterial().getMaxUses());
			}
            EntitySpear entityspear = new EntitySpear(worldIn, playerIn, this.getDamage(stack), maxDur, totalDamage, this.getReach(), this.getAttackSpeed(), stack);
            entityspear.setMaterial(this.getToolMaterialName());
            entityspear.setArrowStack(this);
            entityspear.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            boolean flag1 = playerIn.capabilities.isCreativeMode;
            if (f == 1.0F)
            {
                entityspear.setIsCritical(true);
            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            if (j > 0)
            {
                entityspear.setDamage(entityspear.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

            if (k > 0)
            {
                entityspear.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
            {
                entityspear.setFire(100);
            }

            stack.damageItem(1, playerIn);

            if (flag1 || playerIn.capabilities.isCreativeMode)
            {
                entityspear.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
            }

            worldIn.spawnEntity(entityspear);
        }
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode;

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            if (i < 0) return;

                float f = getArrowVelocity(i);

                if ((double)f >= 0.1D)
                {
                    if (!worldIn.isRemote)
                    {
                    	if(stack.isItemEnchanted()) {
                    		NBTTagList list = stack.getEnchantmentTagList();
                    		Random rando = new Random();
                    		for(int e = 0; e < list.tagCount(); e++) {
                    			if(list.getCompoundTagAt(e).hasKey("id")) {
                    				if(Enchantment.getEnchantmentByID(list.getCompoundTagAt(e).getShort("id")) instanceof EnchantmentDurability) {
                    					int level = list.getCompoundTagAt(e).getShort("lvl");
                    					if(level > 1) {
                    						level *= 0.75;
                    					}
                    					if(!((1-(rando.nextDouble() / level)) > 0.6)) {
                    						stack.setItemDamage(stack.getItemDamage() + 1);
                    					}
                    				}
                    			}
                    		}
                    	}
                    	if(this.getMaxDamage(stack) - this.getDamage(stack)  >= 0) {
                    		createSpearEntity(worldIn, entityplayer, stack, f);
                    		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    	}else {
                    		stack.shrink(1);
                    		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    	}
                    }

                    if (!flag)
                    {
                        stack.shrink(1);

                        if (stack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(stack);
                        }
                    }
                }
            }
        }
	}
