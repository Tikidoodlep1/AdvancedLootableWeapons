package com.tiki.advancedlootableweapons.blocks;

import com.tiki.advancedlootableweapons.blocks.block_entity.AdvancedForgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AdvancedForgeBlock extends Block implements EntityBlock {
    public AdvancedForgeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedForgeBlockEntity(pos, state);
    }
}
