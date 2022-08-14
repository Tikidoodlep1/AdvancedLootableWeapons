package com.tiki.advancedlootableweapons.blocks;

import java.util.List;
import java.util.Random;

import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class BlockDrum extends BlockBase implements ITileEntityProvider
{
	private static boolean keepinventory;
	protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 0.75D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 0.75D, 1.0D);
	
	public BlockDrum(String name)
	{
		super(name, Material.ROCK);
		setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 1);
		this.fullBlock = false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.BOWL;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityDrum) {
			TileEntityDrum drum = (TileEntityDrum)te;
			FluidStack fluid = drum.getTank().getFluid();
			int fluidAmount = fluid == null ? 0 : drum.getTank().getFluidAmount();
			double f = pos.getY() + (10.0F) / 16.0F;
			
	        if (!worldIn.isRemote && fluidAmount > 0 && entityIn.getEntityBoundingBox().minY <= (double)f)
	        {
	        	if(fluid.getFluid().getTemperature() <= FluidRegistry.WATER.getTemperature()+50 && entityIn.isBurning()) {
	        		entityIn.extinguish();
	        	}else if(fluid.getFluid().getTemperature() >= 950) {
	        		entityIn.setFire(5);
	        	}
	        }
		}
	}
	;
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D));
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(BlockInit.drum);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockInit.drum);
	}
	
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);
			ItemStack activeStack = playerIn.getHeldItem(hand);
			
			if(te instanceof TileEntityDrum) {
				TileEntityDrum drum = (TileEntityDrum) te;				
				if(activeStack.getItem() instanceof ItemBucket || activeStack.getItem() instanceof UniversalBucket || activeStack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, facing)) {
					FluidUtil.interactWithFluidHandler(playerIn, hand, drum);
					worldIn.notifyBlockUpdate(pos, state, state, 2);
					drum.onChanged();
					if(ConfigHandler.ENABLE_ADVANCED_LEATHER_TANNING ) {
						drum.FluidInteraction(worldIn, pos, playerIn, hand);
					}
				}else {
					if(ConfigHandler.ENABLE_ADVANCED_LEATHER_TANNING ) {
						drum.EntityInteraction(worldIn, pos, playerIn, hand);
					}
				}
			}
			
		}
		
		return true;
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TileEntityDrum) {
			TileEntityDrum drum = (TileEntityDrum) te;
			if(drum.getTank().getFluid() == null) {
				return 0;
			}
			return drum.getTank().getFluid().getFluid().getLuminosity();
		}
		return super.getLightValue(state, world, pos);
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepinventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityDrum)
            {
            	((TileEntityDrum) tileentity).setInventorySlotContents(TileEntityDrum.ADDITIVE_SLOT, ItemStack.EMPTY);
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityDrum)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityDrum();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDrum();
	}
}