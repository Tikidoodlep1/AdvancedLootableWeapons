package tiki.advancedlootableweapons.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.init.BlockInit;

public class BlockDusksteel extends BlockBase {

	public static final PropertyInteger PHASE = PropertyInteger.create("phase", 0, 7);
	
	public BlockDusksteel(String name, Material material) {
		super(name, material, "pickaxe", 2, true);
		setSoundType(SoundType.METAL);
		needsRandomTick = true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		if(worldIn.getMoonPhase() != worldIn.getBlockState(pos).getValue(PHASE)) {
        	worldIn.setBlockState(pos, this.getDefaultState().withProperty(PHASE, worldIn.getMoonPhase()));
		}
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(BlockInit.block_dusksteel);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockInit.block_dusksteel);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		worldIn.setBlockState(pos, BlockInit.block_dusksteel.getDefaultState().withProperty(PHASE, worldIn.getMoonPhase()));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(PHASE, world.getMoonPhase());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(PHASE, worldIn.getMoonPhase()));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {PHASE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(PHASE, meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return state.getValue(PHASE);
	}
	
}
