package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockForge2Placeholder extends Block implements IHasModel {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	public BlockPos mainPos;

	public BlockForge2Placeholder(String name) {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, false).withProperty(RIGHT, false));
		this.setHarvestLevel("pickaxe", 2);
		this.useNeighborBrightness = true;
		this.fullBlock = false;
		this.translucent = true;
		this.lightOpacity = 0;
		this.setLightOpacity(0);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(BlockInit.forge2));
    }
	
	@Override
	@Deprecated
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(BlockInit.forge2));
    }
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING, LEFT, RIGHT});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		if(meta > 3 && meta < 8) {
			EnumFacing facing = EnumFacing.getHorizontal(meta-4);
			if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
			return this.getDefaultState().withProperty(FACING, facing).withProperty(LEFT, true);
		}else if(meta > 7) {
			EnumFacing facing = EnumFacing.getHorizontal(meta-8);
			if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
			return this.getDefaultState().withProperty(FACING, facing).withProperty(RIGHT, true);
		}
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int facing = state.getValue(FACING).getHorizontalIndex();
		if(!state.getValue(LEFT) && !state.getValue(RIGHT)) {
			return facing;
		}else if(state.getValue(RIGHT)) {
			return 8 + facing;
		}else if(state.getValue(LEFT)) {
			return 4 + facing;
		}
		return facing;
	}
	
//	@Override
//	public IBlockState withRotation(IBlockState state, Rotation rot)
//	{
//		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
//	}
//	
//	@Override
//	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
//	{
//		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
//	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, BlockInit.forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		
		
		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_2, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return ItemStack.EMPTY;
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityForge2();
	}
	
	public static BlockPos getMainPos(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
		BlockPos locateMain = BlockPos.ORIGIN;
		if(worldIn.getBlockState(pos).getBlock() == BlockInit.forge2) {
			return pos;
		}else if(state.getBlock() != BlockInit.forge2_1) {
			return locateMain;
		}
		boolean left = state.getValue(LEFT);
		boolean right = state.getValue(RIGHT);
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		if(right) {
			BlockPos rightOffset = pos.offset(facing.rotateY()); //-311, 76, 185
			/*
[09:37:14] [Server thread/INFO] [STDOUT]: [com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder:getMainPos:203]: BlockPos{x=-312, y=77, z=184}
[09:37:14] [Server thread/INFO] [STDOUT]: [com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder:getMainPos:204]: BlockPos{x=-312, y=77, z=185}
[09:37:14] [Server thread/INFO] [STDOUT]: [com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder:getMainPos:205]: BlockPos{x=-312, y=76, z=184}
[09:37:14] [Server thread/INFO] [STDOUT]: [com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder:getMainPos:206]: BlockPos{x=-312, y=76, z=185}
			 */
			System.out.println(rightOffset.toString());
			System.out.println(rightOffset.offset(facing).toString());
			System.out.println(rightOffset.offset(EnumFacing.DOWN).toString());
			System.out.println(rightOffset.offset(facing).offset(EnumFacing.DOWN).toString());
			
			if(worldIn.getBlockState(rightOffset).getBlock() == BlockInit.forge2) {
				locateMain = rightOffset;
			}else if(worldIn.getBlockState(rightOffset.offset(facing)).getBlock() == BlockInit.forge2) {
				locateMain = rightOffset.offset(facing);
			}else if (worldIn.getBlockState(rightOffset.offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = rightOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(rightOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = rightOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else if(left) {
			BlockPos leftOffset = pos.offset(facing.rotateYCCW());
			System.out.println(leftOffset.toString());
			System.out.println(leftOffset.offset(facing).toString());
			System.out.println(leftOffset.offset(EnumFacing.DOWN).toString());
			System.out.println(leftOffset.offset(facing).offset(EnumFacing.DOWN).toString());
			
			if(worldIn.getBlockState(leftOffset).getBlock() == BlockInit.forge2) {
				locateMain = leftOffset;
			}else if(worldIn.getBlockState(leftOffset.offset(facing)).getBlock() == BlockInit.forge2) {
				locateMain = leftOffset.offset(facing);
			}else if (worldIn.getBlockState(leftOffset.offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = leftOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(leftOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = leftOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else {
			BlockPos midOffset = pos.offset(facing);
			if(worldIn.getBlockState(midOffset).getBlock() == BlockInit.forge2) {
				locateMain = midOffset;
			}else if(worldIn.getBlockState(midOffset.offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = midOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() == BlockInit.forge2) {
				locateMain = pos.offset(EnumFacing.DOWN);
			}
		}
		return locateMain;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		BlockPos locateMain = getMainPos(worldIn, pos, state);
		 
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = locateMain.offset(side);
		BlockPos endPos = locateMain.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		
		for(BlockPos bp : BlockPos.getAllInBox(startPos, endPos)) {
			worldIn.setBlockToAir(bp);
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockInit.forge2);
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
    {
        return false;
    }
	
	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}	
}
