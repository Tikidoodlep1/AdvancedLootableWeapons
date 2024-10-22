package com.tiki.advancedlootableweapons.block;

import com.tiki.advancedlootableweapons.block.state.AdvancedForgeMultiblock;
import com.tiki.advancedlootableweapons.blockentity.AdvancedForgeBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdvancedForgeBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<AdvancedForgeMultiblock> ADVANCED_FORGE_MULTIBLOCK = EnumProperty.create("advanced_forge_multiblock", AdvancedForgeMultiblock.class);

    public AdvancedForgeBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(ADVANCED_FORGE_MULTIBLOCK).isMain()) {
            return new AdvancedForgeBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            Direction direction = pState.getValue(FACING);
            AdvancedForgeMultiblock advancedForgeMultiblock = pState.getValue(ADVANCED_FORGE_MULTIBLOCK);
            if (advancedForgeMultiblock.isMain()) {
                BlockEntity blockentity = pLevel.getBlockEntity(pos);
                if (blockentity instanceof AdvancedForgeBlockEntity forgeBlockEntity) {
                    Containers.dropContents(pLevel, pos, new RecipeWrapper(forgeBlockEntity.getItemHandler()));
                    pLevel.updateNeighbourForOutputSignal(pos, this);
                }
                breakMultiblock(pLevel,direction,pos);
            } else {
                BlockPos mainPos = getMain(pos,direction,advancedForgeMultiblock);
                BlockState state = pLevel.getBlockState(mainPos);
                if (state.is(this) && state.getValue(ADVANCED_FORGE_MULTIBLOCK).isMain()) {
                    breakMultiblock(pLevel,direction,mainPos);
                }
            }
            super.onRemove(pState, pLevel, pos, pNewState, pIsMoving);
        }
    }

    protected void breakMultiblock(Level level,Direction direction,BlockPos mainPos) {
        getConnected(mainPos,direction).forEach(pos -> level.destroyBlock(pos,true));
    }

    protected static List<BlockPos> getConnected(BlockPos pos,Direction direction) {
        List<BlockPos> list = new ArrayList<>();

        for (AdvancedForgeMultiblock multiblock : AdvancedForgeMultiblock.values()) {
            list.add(multiblock.offsetFromMain(pos,direction));
        }
        return list;
    }

    protected static BlockPos getMain(BlockPos pos,Direction direction,AdvancedForgeMultiblock advancedForgeMultiblock) {
        return switch (advancedForgeMultiblock) {
            case main -> pos;
            case left -> pos.relative(direction.getClockWise());
            case right -> pos.relative(direction.getCounterClockWise());
            case back -> pos.relative(direction);
            case back_left -> pos.relative(direction).relative(direction.getClockWise());
            case back_right -> pos.relative(direction).relative(direction.getCounterClockWise());
            case up -> pos.relative(Direction.DOWN);
            case up_left -> pos.relative(Direction.DOWN).relative(direction.getClockWise());
            case up_right -> pos.relative(Direction.DOWN).relative(direction.getCounterClockWise());
            case up_back -> pos.relative(Direction.DOWN).relative(direction);
            case up_back_left -> pos.relative(Direction.DOWN).relative(direction).relative(direction.getClockWise());
            case up_back_right -> pos.relative(Direction.DOWN).relative(direction).relative(direction.getCounterClockWise());
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!world.isClientSide()) {
            AdvancedForgeMultiblock advancedForgeMultiblock = state.getValue(ADVANCED_FORGE_MULTIBLOCK);
            Direction direction = state.getValue(FACING);
            BlockPos mainPos = getMain(pos,direction,advancedForgeMultiblock);
            BlockEntity entity = world.getBlockEntity(mainPos);
            if(entity instanceof AdvancedForgeBlockEntity forge) {
                player.openMenu(forge);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        if (state.getValue(ADVANCED_FORGE_MULTIBLOCK).isMain()) {
            return RenderShape.MODEL;
        }
        return RenderShape.INVISIBLE;
    }



    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection().getOpposite();

        BlockPos mainPos = pContext.getClickedPos();

        boolean allClear = getConnected(mainPos,direction).stream().allMatch(pos -> canBeReplacedAndInBounds(pContext,pos));

        return allClear ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    boolean canBeReplacedAndInBounds(BlockPlaceContext context,BlockPos pos) {
        Level level = context.getLevel();
        return level.getBlockState(pos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(pos);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos mainPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        Direction direction = pState.getValue(FACING);

        for (AdvancedForgeMultiblock advancedForgeMultiblock : AdvancedForgeMultiblock.values()) {
            pLevel.setBlock(advancedForgeMultiblock.offsetFromMain(mainPos,direction),
                    pState.setValue(ADVANCED_FORGE_MULTIBLOCK,advancedForgeMultiblock),3);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return world.isClientSide ? null : createTickerHelper(type, BlockEntityInit.ADVANCED_FORGE_TE.get(), AdvancedForgeBlockEntity::tick);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING,ADVANCED_FORGE_MULTIBLOCK);
    }
}
