package com.tiki.advancedlootableweapons.entity;

import com.tiki.advancedlootableweapons.tools.ToolSpear;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySpear extends EntityArrow{
	private int knockbackStrength;
	private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    private int ticksInAir;
    private ItemStack arrowStack;
    public String material;
    private float spearDamage;
    public int spearDurability;
    public static final DataParameter<ItemStack> ITEMSTACK = EntityDataManager.<ItemStack>createKey(EntitySpear.class, DataSerializers.ITEM_STACK);
    
    public EntitySpear(World worldIn, EntityLivingBase shooter, String material, float spearDamage, int spearDurability, ItemStack stack) {
    	this(worldIn, shooter);
    	this.material = material;
    	this.spearDamage = spearDamage;
    	this.spearDurability = spearDurability;
    	this.setItemStack(stack);
    }
    
	public EntitySpear(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.hurtResistantTime = 0;
		this.knockbackStrength = 1;
	}
	
	public EntitySpear(World worldIn) {
		super(worldIn);
		this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
		this.hurtResistantTime = 0;
		this.knockbackStrength = 1;
	}
	
	public EntitySpear(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.hurtResistantTime = 0;
		this.knockbackStrength = 1;
	}
	
	public void setItemStack(ItemStack stack) {
		if(stack == null || stack.isEmpty() || !(stack.getItem() instanceof ToolSpear)) {
			throw new IllegalArgumentException("Item Stack needs to be a Spear!");
		}
		this.dataManager.set(ITEMSTACK, stack);
	}
	
	public ItemStack getItemStack() {
		if(!this.dataManager.isEmpty()) {
			return this.dataManager.get(ITEMSTACK);
		}else {
			System.out.print("!! DATA MANAGER IS EMPTY !!");
			return ItemStack.EMPTY;
		}
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ITEMSTACK, ItemStack.EMPTY);
	}
	
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("durability", this.spearDurability);
		
		if(this.getItemStack() != null && !this.getItemStack().isEmpty()) {
			compound.setTag("itemstack", this.getItemStack().writeToNBT(new NBTTagCompound()));
		}
	}
	
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("durability", 99))
        {
            this.spearDurability = compound.getInteger("durability");
        }
        
        setItemStack(new ItemStack(compound.getCompoundTag("itemstack")));
    }
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.pickupStatus == EntityArrow.PickupStatus.ALLOWED || this.pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && entityIn.capabilities.isCreativeMode;

            if (!(this.pickupStatus == EntityArrow.PickupStatus.ALLOWED))
            {
                flag = false;
            }
            
            if (flag)
            {
                ItemStack stack = this.getItemStack();
                stack.setItemDamage(this.spearDurability);
                if(stack.getItem().getDurabilityForDisplay(stack) < 1.0 || stack.getItem().getDurabilityForDisplay(stack) == Double.POSITIVE_INFINITY) {
                	ItemStack copyStack = this.arrowStack;
                	if(stack.isItemEnchanted()) {
                		NBTTagList list = stack.getEnchantmentTagList();
                		for(int e = 0; e < list.tagCount(); e++) {
                			if(list.getCompoundTagAt(e).hasKey("id") && list.getCompoundTagAt(e).hasKey("lvl") && list.getCompoundTagAt(e).getShort("lvl") != 0)
                			copyStack.addEnchantment(Enchantment.getEnchantmentByID(list.getCompoundTagAt(e).getShort("id")), list.getCompoundTagAt(e).getShort("lvl"));
                		}
                	}
                	this.setItemDurability(entityIn, copyStack);
                }else if(stack.getItem().getDurabilityForDisplay(stack) >= 1.0) {
                	playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
                } 
                this.setDead();
            }
        }
    }
	
	public void setItemDurability(EntityPlayer player, ItemStack stack){
		int slot = player.inventory.getFirstEmptyStack();
		player.inventory.add(slot, stack);
		ItemStack item = player.inventory.getStackInSlot(slot);
		item.setItemDamage(this.spearDurability);
	}
	
	public void setArrowStack(ToolSpear spear) {
		this.arrowStack = new ItemStack(spear);
	}
	
	@Override
	protected ItemStack getArrowStack() {
		return this.arrowStack;
	}
	
	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
            this.motionY += shooter.motionY;
        }
    }
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn)
    {
        Entity entity = raytraceResultIn.entityHit;

        if (entity != null)
        {
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            float i = MathHelper.ceil((double)f * this.spearDamage);

            if (this.getIsCritical())
            {
                i = i + (this.rand.nextInt(((int)i / 4)) + this.rand.nextFloat());
            }
            
            if(i - ((int)i) > 0.5) {
            	i = ((int)i + 1);
            }else {
            	i = (int)i;
            }

            DamageSource damagesource;

            if (this.shootingEntity == null)
            {
                damagesource = DamageSource.causeArrowDamage(this, this);
            }
            else
            {
                damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
            }

            if (this.isBurning() && !(entity instanceof EntityEnderman))
            {
                entity.setFire(5);
            }

            if (entity.attackEntityFrom(damagesource, (float)i))
            {
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    if (!this.world.isRemote)
                    {
                        entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                    }

                    if (this.knockbackStrength > 0)
                    {
                        float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                        if (f1 > 0.0F)
                        {
                            entitylivingbase.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f1, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)f1);
                        }
                    }

                    if (this.shootingEntity instanceof EntityLivingBase)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                    }

                    this.arrowHit(entitylivingbase);

                    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                
                /*
                if (!(entity instanceof EntityEnderman))
                {
                    this.setDead();
                }
                */
                
            }
            else
            {
                this.motionX *= -0.10000000149011612D;
                this.motionY *= -0.10000000149011612D;
                this.motionZ *= -0.10000000149011612D;
                this.rotationYaw += 180.0F;
                this.prevRotationYaw += 180.0F;
                this.ticksInAir = 0;

                if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D)
                {
                    if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED)
                    {
                        this.entityDropItem(this.getArrowStack(), 0.1F);
                    }

                    this.setDead();
                }
            }
        }
        else
        {
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            this.xTile = blockpos.getX();
            this.yTile = blockpos.getY();
            this.zTile = blockpos.getZ();
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            this.inTile = iblockstate.getBlock();
            this.inData = this.inTile.getMetaFromState(iblockstate);
            this.motionX = (double)((float)(raytraceResultIn.hitVec.x - this.posX));
            this.motionY = (double)((float)(raytraceResultIn.hitVec.y - this.posY));
            this.motionZ = (double)((float)(raytraceResultIn.hitVec.z - this.posZ));
            float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
            this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
            this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.inGround = true;
            this.arrowShake = 7;
            this.setIsCritical(false);

            if (iblockstate.getMaterial() != Material.AIR)
            {
                this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
            }
        }
    }
}
