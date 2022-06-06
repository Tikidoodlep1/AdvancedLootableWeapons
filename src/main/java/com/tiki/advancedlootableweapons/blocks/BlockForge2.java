package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemBlockForge2;

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
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForge2 extends Block implements IHasModel
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	private static boolean keepinventory;
	
	public BlockForge2(String name)
	{
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setHarvestLevel("pickaxe", 2);
		this.fullBlock = false;
		this.setLightLevel(15.0F);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwBlocksTab);
		
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlockForge2(this).setRegistryName(name));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(BlockInit.forge2);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockInit.forge2);
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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
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
            
            if(isClearForPlacement(pos, face, worldIn)) {
            	TileEntityForge2 TEF2 = ((TileEntityForge2)worldIn.getTileEntity(pos));
            	TEF2.setMainTE(TEF2);
            	if(face == EnumFacing.WEST) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
                			if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}
            				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else if(i == 1) {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else if(face == EnumFacing.EAST) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
            				if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}
            				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else if(i == 1) {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else if(face == EnumFacing.NORTH) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
                			if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}
            				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else if(i == 1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
            				if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}
            				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else if(i == 1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}
            	
            	worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
            	
            	@SuppressWarnings("unused")
    			TileEntity setMain = ((TileEntityForge2) worldIn.getTileEntity(pos)).getMainTE(worldIn, pos);
            }
        }
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	public static boolean isClearForPlacement(BlockPos pos, EnumFacing facing, World worldIn) {
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = pos.offset(side);
		BlockPos endPos = pos.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		
		for(BlockPos iPos : BlockPos.getAllInBoxMutable(startPos, endPos)) {
			if(!worldIn.mayPlace(BlockInit.forge2, iPos, false, EnumFacing.UP, worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false)) && worldIn.getBlockState(iPos).getBlock() != BlockInit.forge2) {
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.2D + rand.nextDouble() * 6.0D / 8.0D;
        double d1 = (double)pos.getY() + 0.7D + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.4D + rand.nextDouble() * 6.0D / 14.0D;

        if (rand.nextDouble() < 0.1D)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.05D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
	
	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		
		keepinventory = false;
		
		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepinventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityForge2)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityForge2)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }
        
        EnumFacing facing = (EnumFacing)state.getValue(FACING);
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = pos.offset(side);
		BlockPos endPos = pos.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		
		for(BlockPos bp : BlockPos.getAllInBox(startPos, endPos)) {
			worldIn.setBlockToAir(bp);
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
		return new TileEntityForge2();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}	
	
}