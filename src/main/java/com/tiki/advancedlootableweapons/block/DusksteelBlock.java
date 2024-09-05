package com.tiki.advancedlootableweapons.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DusksteelBlock extends Block {

	public static final IntegerProperty PHASE = IntegerProperty.create("phase", 0, 7);
	
	public DusksteelBlock(Properties prop) {
		super(prop);
	}
	
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		if(world.getMoonPhase() != state.getValue(PHASE)) {
			world.setBlock(pos, state.setValue(PHASE, world.getMoonPhase()), UPDATE_NEIGHBORS);
		}
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
	   return this.defaultBlockState().setValue(PHASE, context.getLevel().getMoonPhase());
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(PHASE);
	}
}
