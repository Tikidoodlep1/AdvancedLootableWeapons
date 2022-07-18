package com.tiki.advancedlootableweapons.items;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMilkOfLimeBucket extends ItemBucket implements IHasModel {
	

	public ItemMilkOfLimeBucket(String name, Block containedBlockIn) {
		super(containedBlockIn);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
	}
	
	@Override
	public boolean tryPlaceContainedLiquid(EntityPlayer player, World worldIn, BlockPos posIn) {
		if(!worldIn.isRemote) {
				System.out.println("Does Water Vaporize: " + worldIn.provider.doesWaterVaporize());
				if (worldIn.provider.doesWaterVaporize())
            	{
                	int l = posIn.getX();
                	int i = posIn.getY();
                	int j = posIn.getZ();
                	worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                	
                	for (int k = 0; k < 8; ++k)
                	{
                    	worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D, 0.0D, 0.0D);
                	}
            	}else {
            		SoundEvent soundevent = SoundEvents.ITEM_BUCKET_EMPTY;
                	worldIn.playSound(player, posIn, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                	worldIn.setBlockState(posIn, BlockInit.milk_of_lime.getDefaultState(), 11);
            	}
			}
		return true;
	}
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
