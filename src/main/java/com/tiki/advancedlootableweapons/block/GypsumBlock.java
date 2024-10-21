package com.tiki.advancedlootableweapons.block;

import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class GypsumBlock extends Block {
    public GypsumBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pos, Random pRandom) {
        for(Direction facing : Direction.values()) {
            if (facing == Direction.UP) continue;
            if(pLevel.getFluidState(pos.relative(facing)).is(FluidTags.WATER)) {//should this be its own tag?
                pLevel.setBlock(pos.relative(facing), BlockInit.MILK_OF_LIME.get().defaultBlockState(),3);
                pLevel.removeBlock(pos, false);
                break;
            }
        }
    }
}
