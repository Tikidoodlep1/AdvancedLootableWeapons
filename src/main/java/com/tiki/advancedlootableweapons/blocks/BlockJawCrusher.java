package com.tiki.advancedlootableweapons.blocks;

import com.tiki.advancedlootableweapons.blocks.block_entity.JawCrusherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockJawCrusher extends BaseEntityBlock {
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlockJawCrusher(Properties pProperties) {
		super(pProperties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return super.getShape(pState, pLevel, pPos, pContext);//todo
	}

	@Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
	
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
    	return RenderShape.MODEL;
    }
    
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new JawCrusherBlockEntity(pos, state);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(!world.isClientSide()) {
			BlockEntity entity = world.getBlockEntity(pos);
			if(entity instanceof JawCrusherBlockEntity crusher) {
				if(player.isShiftKeyDown()) {
					crusher.crushContents();
				}else {
					player.openMenu(crusher);
				}
			}else {
				throw new IllegalStateException("Forge Container Provider is Missing!");
			}
		}
		return InteractionResult.sidedSuccess(world.isClientSide());
	}
}
