package com.tiki.advancedlootableweapons.block;

import com.tiki.advancedlootableweapons.blockentity.AdvancedForgeBlockEntity;
import com.tiki.advancedlootableweapons.blockentity.ForgeBlockEntity;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BellowsBlock extends Block {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final VoxelShape BELLOWS_AABB_NS = Shapes.create(new AABB(0.25D, 0.0D, 0.0D, 0.75D, 0.4D, 1.0D));

	public static final VoxelShape BELLOWS_AABB_EW = Shapes.create(new AABB(0.0D, 0.0D, 0.25D, 1.0D, 0.4D, 0.75D));

	public BellowsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		switch (pState.getValue(FACING)) {
			case NORTH, SOUTH -> {
				return BELLOWS_AABB_NS;
			}
			case EAST, WEST -> {
				return BELLOWS_AABB_EW;
			}
		}
		return super.getShape(pState, pLevel, pPos, pContext);
	}

	//@Override//todo burning
	//public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
//		return true;
//	}


	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if(!pLevel.isClientSide) {
		//	AdvancedLootableWeapons.logger.info("Activating bellows server side");
			BlockEntity te = pLevel.getBlockEntity(pPos.relative(pState.getValue(FACING)));
			BlockState placeholderState = pLevel.getBlockState(pPos.relative(pState.getValue(FACING)));
			if(te == null && placeholderState.getBlock() instanceof AdvancedForgeBlock) {
				AdvancedForgeBlock extra = ((AdvancedForgeBlock)placeholderState.getBlock());
				te = null;// todo pLevel.getBlockEntity(extra.getMainPos(pLevel, pPos, placeholderState));
			}
			if(te instanceof ForgeBlockEntity) {
				((ForgeBlockEntity)te).bellowsInteraction();
				pPlayer.getFoodData().addExhaustion((float) (double)CommonConfigHandler.BELLOWS_EXHAUSTION.get());
				return InteractionResult.SUCCESS;
			}else if(te instanceof AdvancedForgeBlockEntity) {
				((AdvancedForgeBlockEntity)te).bellowsInteraction();
				pPlayer.getFoodData().addExhaustion((float) (double)CommonConfigHandler.BELLOWS_EXHAUSTION.get());
			}

		}else {
			pLevel.playSound(pPlayer, pPos, SoundInit.BELLOWS, SoundSource.BLOCKS, 6.0F, 1.0F);
		/*	t.schedule(new TimerTask() {
				@Override
				public void run() {
					Random rand = new Random();
					double d0 = pPos.relative(pState.getValue(FACING)).getX() + 0.3D + rand.nextDouble() * 6.0D / 16.0D;
					double d1 = pPos.relative(pState.getValue(FACING)).getY() + 0.8D + rand.nextDouble() * 6.0D / 16.0D;
					double d2 = pPos.relative(pState.getValue(FACING)).getZ() + 0.3D + rand.nextDouble() * 6.0D / 16.0D;
					pLevel.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + (rand.nextDouble() / 2), d1, d2, 0.0D, 0.0D, 0.0D);
					pLevel.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			}, 500);*/

		}

		return InteractionResult.SUCCESS;
	}

	/*@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote)
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}*/

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#rotate} whenever
	 * possible. Implementing/overriding is fine.
	 */
	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#mirror} whenever
	 * possible. Implementing/overriding is fine.
	 */
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING);
	}
}
