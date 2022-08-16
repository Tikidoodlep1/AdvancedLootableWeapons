package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import com.tiki.advancedlootableweapons.blocks.block_entity.ForgeBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class BlockForge extends BaseEntityBlock {
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public BlockForge(Properties prop) {
		super(prop);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
    	double d0 = pPos.getX() + 0.5D + pRandom.nextDouble() * 4.0D / 16.0D;
    	double d1 = pPos.getY() + pRandom.nextDouble() * 6.0D / 16.0D;
    	double d2 = pPos.getX() + 0.5D;
    	double d3 = pRandom.nextDouble() * 0.6D - 0.3D;
    	
    	if(pRandom.nextDouble() < 0.1D) {
    		pLevel.playLocalSound(pPos.getX()+0.5D, pPos.getY()+0.5D, pPos.getZ()+0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0f, 1.0f, false);
    	}
    	pLevel.addParticle(ParticleTypes.SMALL_FLAME, d0, d1 + 0.75D, d2 + d3, 0.0D, 0.0D, 0.0D);
		pLevel.addParticle(ParticleTypes.SMOKE, d0, d1 + 0.75D, d2 + d3, 0.0D, 0.0D, 0.0D);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
    	return RenderShape.MODEL;
    }
    
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ForgeBlockEntity(pos, state);
	}
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(!world.isClientSide()) {
			BlockEntity entity = world.getBlockEntity(pos);
			if(entity instanceof ForgeBlockEntity forge) {
				NetworkHooks.openGui(((ServerPlayer)player), forge, pos);
			}else {
				throw new IllegalStateException("Forge Container Provider is Missing!");
			}
		}
		return InteractionResult.sidedSuccess(world.isClientSide());
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, BlockEntityInit.FORGE_TE.get(), ForgeBlockEntity::tick);
	}
}
