package com.tiki.advancedlootableweapons.blocks;

import com.tiki.advancedlootableweapons.blocks.block_entity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.blocks.block_entity.DrumBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nullable;

public class DrumBlock extends Block implements EntityBlock {
    public DrumBlock(Properties pProperties) {
        super(pProperties);
    }

    protected static final VoxelShape AABB_WALL_NORTH = Shapes.create(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 0.125D);
    protected static final VoxelShape AABB_WALL_SOUTH = Shapes.create(0.0D, 0.0D, 0.875D, 1.0D, 0.75D, 1.0D);
    protected static final VoxelShape AABB_WALL_EAST = Shapes.create(0.875D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    protected static final VoxelShape AABB_WALL_WEST = Shapes.create(0.0D, 0.0D, 0.0D, 0.125D, 0.75D, 1.0D);
    protected static final VoxelShape bottom = Shapes.create(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
    
    protected static final VoxelShape SHAPE = Shapes.or(AABB_WALL_NORTH,AABB_WALL_SOUTH,AABB_WALL_EAST,AABB_WALL_WEST,bottom);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof DrumBlockEntity drumBlockEntity) {
                if (!pLevel.isClientSide) {
                    boolean b = FluidUtil.interactWithFluidHandler(pPlayer, pHand, pLevel, pPos, null);
                if (b) {
                    return InteractionResult.sidedSuccess(pLevel.isClientSide);
                } else {
                    drumBlockEntity.playerInteraction(pLevel,pPos,pPlayer,pHand);
                }
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return world.isClientSide ? null :createTickerHelper(type, BlockEntityInit.DRUM_TE.get(), DrumBlockEntity::tick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DrumBlockEntity(pPos,pState);
    }
}
