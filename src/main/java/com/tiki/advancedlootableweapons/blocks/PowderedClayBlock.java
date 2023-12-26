package com.tiki.advancedlootableweapons.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PowderedClayBlock extends FallingBlock {
	
	private final BlockState clayBlock;

	public PowderedClayBlock(Block clay, Properties prop) {
		super(prop);
		clayBlock = clay.defaultBlockState();
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext p_52063_) {
	      BlockGetter blockgetter = p_52063_.getLevel();
	      BlockPos blockpos = p_52063_.getClickedPos();
	      BlockState blockstate = blockgetter.getBlockState(blockpos);
	      return shouldSolidify(blockgetter, blockpos, blockstate) ? this.clayBlock : super.getStateForPlacement(p_52063_);
	}
	
	@Override
	public BlockState updateShape(BlockState p_52074_, Direction p_52075_, BlockState p_52076_, LevelAccessor p_52077_, BlockPos p_52078_, BlockPos p_52079_) {
	      return touchesLiquid(p_52077_, p_52078_) ? this.clayBlock : super.updateShape(p_52074_, p_52075_, p_52076_, p_52077_, p_52078_, p_52079_);
	}
	
	@Override
	public void onLand(Level world, BlockPos pos, BlockState state, BlockState posState, FallingBlockEntity fallingEntity) {
	      if (shouldSolidify(world, pos, posState)) {
	         world.setBlock(pos, this.clayBlock, 3);
	      }
	}
	
	private static boolean shouldSolidify(BlockGetter p_52081_, BlockPos p_52082_, BlockState p_52083_) {
	      return canSolidify(p_52083_) || touchesLiquid(p_52081_, p_52082_);
	}
	
	private static boolean canSolidify(BlockState p_52089_) {
	      return p_52089_.getFluidState().is(FluidTags.WATER);
	}
	
	private static boolean touchesLiquid(BlockGetter p_52065_, BlockPos p_52066_) {
	      boolean flag = false;
	      BlockPos.MutableBlockPos blockpos$mutableblockpos = p_52066_.mutable();

	      for(Direction direction : Direction.values()) {
	         BlockState blockstate = p_52065_.getBlockState(blockpos$mutableblockpos);
	         if (direction != Direction.DOWN || canSolidify(blockstate)) {
	            blockpos$mutableblockpos.setWithOffset(p_52066_, direction);
	            blockstate = p_52065_.getBlockState(blockpos$mutableblockpos);
	            if (canSolidify(blockstate) && !blockstate.isFaceSturdy(p_52065_, p_52066_, direction.getOpposite())) {
	               flag = true;
	               break;
	            }
	         }
	      }
	      return flag;
	}
}
