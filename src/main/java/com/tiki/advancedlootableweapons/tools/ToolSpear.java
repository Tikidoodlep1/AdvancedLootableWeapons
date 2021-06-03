package com.tiki.advancedlootableweapons.tools;

import com.tiki.advancedlootableweapons.entity.EntitySpear;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ToolSpear extends ToolStabSword{
	
	public ToolSpear(String name, ToolMaterial material) {
		super(name, material, "spear");
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
            EntitySpear entityspear = new EntitySpear(worldIn, playerIn, this.getToolMaterialName(), this.getAttackDamage(), this.getDamage(stack), stack);
            System.out.print(this.getToolMaterialName());
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
                    boolean flag1 = entityplayer.capabilities.isCreativeMode;

                    if (!worldIn.isRemote)
                    {
                    	stack.setItemDamage(stack.getItemDamage() + 1);
                    	if(this.getMaxDamage(stack) - this.getDamage(stack)  >= 0) {
                    		createSpearEntity(worldIn, entityplayer, stack, f);
                    		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    	}else {
                    		stack.shrink(1);
                    		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    	}
                    }

                    if (!flag1 && !flag)
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
