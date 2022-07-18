package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGypsum extends BlockBase {

	public BlockGypsum(String name, Material material) {
		super(name, material);
		this.needsRandomTick = true;
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		for(EnumFacing facing : EnumFacing.HORIZONTALS) {
			if(worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.WATER) {
				worldIn.setBlockState(pos.offset(facing), BlockInit.milk_of_lime.getDefaultState());
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				break;
			}
		}
		super.randomTick(worldIn, pos, state, random);
	}
}
