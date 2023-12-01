package tiki.rotn.advancedlootableweapons.blocks;

import java.util.Random;

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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.IHasModel;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.rotn.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

public class BlockForge2Placeholder extends Block implements IHasModel {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	private BlockPos mainPos = null;
	private Block mainBlock = null;

	public BlockForge2Placeholder(String name) {
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, false).withProperty(RIGHT, false));
		this.setHarvestLevel("pickaxe", 1);
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
		if(this.mainBlock == null) {
			return new ItemStack(this.getMainBlock(world, pos, state));
		}else {
			return new ItemStack(this.mainBlock);
		}
        
    }
	
	@Override
	@Deprecated
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
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
			this.mainBlock = getMainBlock(worldIn, pos, state);
			
			//System.out.println(this.mainBlock + ", " + this.mainPos);
			
			ItemStack mainHand = playerIn.getHeldItemMainhand();
			if(this.mainBlock instanceof BlockForge2Fuel) {
				
				if(worldIn.getBlockState(this.mainPos).getValue(BlockForge2Fuel.REQUIRES_IGNITION) && !mainHand.isEmpty()) {
					for(ItemStack stack : OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_ORE)) {
						if(stack.getItem() == mainHand.getItem()) {
							TileEntity te = worldIn.getTileEntity(this.mainPos);
							if(te instanceof TileEntityForge2) {
								((TileEntityForge2)te).usedIgniterOnForge();
							}
							if(mainHand.getItem().isDamageable()) {
								mainHand.damageItem(1, playerIn);
							}else {
								mainHand.setCount(mainHand.getCount() - 1);
							}
							return true;
						}
					}
					
					for(ItemStack stack : OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_UPGRADE_ORE)) {
						if(stack.getItem() == mainHand.getItem()) {
							TileEntity te = worldIn.getTileEntity(this.mainPos);
							if(te instanceof TileEntityForge2) {
								((TileEntityForge2)te).setRequiresIgnition(false);
							}
							BlockForge2Fuel.setState(false, worldIn, this.mainPos);
							if(mainHand.getItem().isDamageable()) {
								mainHand.damageItem(1, playerIn);
							}else {
								mainHand.setCount(mainHand.getCount() - 1);
							}
							return true;
						}
					}
				}
			}
			
			if(this.mainBlock instanceof BlockForge2Fuel) {
				playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_2_FUEL, worldIn, this.mainPos.getX(), this.mainPos.getY(), this.mainPos.getZ());
			}else {
				playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_2, worldIn, this.mainPos.getX(), this.mainPos.getY(), this.mainPos.getZ());
			}
			
		}
		
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
		if(this.mainBlock == null) {
			return ItemStack.EMPTY;
		}else {
			return new ItemStack(this.mainBlock);
		}
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return false;
	}
	
	public BlockPos getMainPos(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
		BlockPos locateMain = BlockPos.ORIGIN;
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockForge2) {
			return pos;
		}else if(!(state.getBlock() instanceof BlockForge2Placeholder)) {
			return locateMain;
		}else if(this.mainPos != null && worldIn.getBlockState(this.mainPos).getBlock() instanceof BlockForge2) {
			return mainPos;
		}
		
		boolean left = state.getValue(LEFT);
		boolean right = state.getValue(RIGHT);
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		if(right) {
			BlockPos rightOffset = pos.offset(facing.rotateY());
			
			if(worldIn.getBlockState(rightOffset).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset;
			}else if(worldIn.getBlockState(rightOffset.offset(facing)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(facing);
			}else if (worldIn.getBlockState(rightOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(rightOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else if(left) {
			BlockPos leftOffset = pos.offset(facing.rotateYCCW());
			
			if(worldIn.getBlockState(leftOffset).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset;
			}else if(worldIn.getBlockState(leftOffset.offset(facing)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(facing);
			}else if (worldIn.getBlockState(leftOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(leftOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else {
			BlockPos midOffset = pos.offset(facing);
			if(worldIn.getBlockState(midOffset).getBlock() instanceof BlockForge2) {
				locateMain = midOffset;
			}else if(worldIn.getBlockState(midOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = midOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = pos.offset(EnumFacing.DOWN);
			}
		}
		
		this.mainPos = locateMain;
		this.mainBlock = worldIn.getBlockState(mainPos).getBlock();
		return locateMain;
	}
	
	public Block getMainBlock(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
		BlockPos locateMain = BlockPos.ORIGIN;
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockForge2) {
			return worldIn.getBlockState(pos).getBlock();
		}else if(!(state.getBlock() instanceof BlockForge2Placeholder)) {
			return null;
		}
//		else if(this.mainBlock != null && this.mainBlock instanceof BlockForge2) {
//			return this.mainBlock;
//		}
		
		boolean left = state.getValue(LEFT);
		boolean right = state.getValue(RIGHT);
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		if(right) {
			BlockPos rightOffset = pos.offset(facing.rotateY());
			//System.out.println("Block is on the right! Checking " + rightOffset + ", " + rightOffset.offset(facing) + ", " + rightOffset.offset(facing).offset(EnumFacing.DOWN));
			
			if(worldIn.getBlockState(rightOffset).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset;
			}else if(worldIn.getBlockState(rightOffset.offset(facing)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(facing);
			}else if (worldIn.getBlockState(rightOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(rightOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = rightOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else if(left) {
			BlockPos leftOffset = pos.offset(facing.rotateYCCW());
			//System.out.println("Block is on the left! Checking " + leftOffset + ", " + leftOffset.offset(facing) + ", " + leftOffset.offset(facing).offset(EnumFacing.DOWN));
			
			if(worldIn.getBlockState(leftOffset).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset;
			}else if(worldIn.getBlockState(leftOffset.offset(facing)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(facing);
			}else if (worldIn.getBlockState(leftOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(leftOffset.offset(facing).offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = leftOffset.offset(facing).offset(EnumFacing.DOWN);
			}
		}else {
			BlockPos midOffset = pos.offset(facing);
			//System.out.println("Block is in the middle! Checking " + midOffset + ", " + midOffset.offset(EnumFacing.DOWN) + ", " + pos.offset(EnumFacing.DOWN));
			
			if(worldIn.getBlockState(midOffset).getBlock() instanceof BlockForge2) {
				locateMain = midOffset;
			}else if(worldIn.getBlockState(midOffset.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = midOffset.offset(EnumFacing.DOWN);
			}else if(worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockForge2) {
				locateMain = pos.offset(EnumFacing.DOWN);
			}
		}
		
		this.mainPos = locateMain;
		this.mainBlock = worldIn.getBlockState(mainPos).getBlock();
		return this.mainBlock;
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
		if(this.mainBlock == null) {
			return Items.AIR;
		}else {
			return Item.getItemFromBlock(this.mainBlock);
		}
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
