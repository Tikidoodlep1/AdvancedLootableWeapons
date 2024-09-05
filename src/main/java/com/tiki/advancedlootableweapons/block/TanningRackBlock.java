package com.tiki.advancedlootableweapons.block;

import com.tiki.advancedlootableweapons.blockentity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.blockentity.ForgeBlockEntity;
import com.tiki.advancedlootableweapons.blockentity.JawCrusherBlockEntity;
import com.tiki.advancedlootableweapons.blockentity.TanningRackBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.menu.TanningRackMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

public class TanningRackBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


    public TanningRackBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TanningRackBlockEntity(pPos,pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!world.isClientSide()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof TanningRackBlockEntity tanningRackBlock) {
                player.openMenu(tanningRackBlock);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof TanningRackBlockEntity tanningRackBlock) {
                Containers.dropContents(pLevel, pPos, new RecipeWrapper(tanningRackBlock.getItemHandler()));
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState pState, BlockEntityType<T> type) {
        return level.isClientSide ? null :createTickerHelper(type, BlockEntityInit.TANNING_RACK.get(), TanningRackBlockEntity::tick);
    }
}
