package tiki.rotn.advancedlootableweapons.blocks;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.codetaylor.mc.pyrotech.IAirflowConsumerCapability;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachineConfig;

import betterwithmods.common.BWMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge2Placeholder;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.rotn.advancedlootableweapons.handlers.ConfigHandler;
import tiki.rotn.advancedlootableweapons.handlers.SoundHandler;

public class BlockBellows extends BlockBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;	
	public static final AxisAlignedBB BELLOWS_AABB_NS = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.4D, 1.0D);
	public static final AxisAlignedBB BELLOWS_AABB_EW = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 0.4D, 0.75D);
	private final Timer t = new Timer("ALWCoolDowns");
	private boolean canUse = true;
	private int airflow = 0;
	
	public BlockBellows(String name) {
		super(name, Material.WOOD, "axe", 0, true);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.fullBlock = false;
		this.translucent = true;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		
		if(stack.hasTagCompound()) {
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.hasKey("wood")) {
				ItemStack wood = new ItemStack(tag.getCompoundTag("wood"));
				if(!wood.isEmpty()) {
					tooltip.add(wood.getDisplayName());
				}
			}
		}
	}
	
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if(state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
			return BELLOWS_AABB_NS;
		}else {
			return BELLOWS_AABB_EW;
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
			return BELLOWS_AABB_NS;
		}else {
			return BELLOWS_AABB_EW;
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(this);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos.offset(state.getValue(FACING)));
			IBlockState placeholderState = worldIn.getBlockState(pos.offset(state.getValue(FACING)));
			if(te == null && placeholderState.getBlock() instanceof BlockForge2Placeholder) {
				BlockForge2Placeholder extra = ((BlockForge2Placeholder)placeholderState.getBlock());
				te = worldIn.getTileEntity(extra.getMainPos(worldIn, pos, placeholderState));
			}
			if(te instanceof TileEntityForge) {
				((TileEntityForge)te).bellowsInteraction();				
				playerIn.getFoodStats().addExhaustion(ConfigHandler.BELLOWS_EXHAUSTION);
				return true;
			}else if(te instanceof TileEntityForge2) {
				((TileEntityForge2)te).bellowsInteraction(worldIn, pos);								
				playerIn.getFoodStats().addExhaustion(ConfigHandler.BELLOWS_EXHAUSTION);
				return true;
			}
			
			//System.out.println("Is BWM present: " + Alw.isBWMLoaded);
			if(Alw.isBWMLoaded) {
				EnumFacing face = worldIn.getBlockState(pos).getValue(FACING);
				Block front = worldIn.getBlockState(pos.offset(face)).getBlock();
				//System.out.println("Front is " + front.getRegistryName() + " at pos " + pos.offset(face) + ", and this block is at " + pos + " facing " + face);
				if(front == Blocks.FIRE || front == BWMBlocks.STOKED_FLAME) {
					if(worldIn.getBlockState(pos.offset(face).offset(EnumFacing.DOWN)).getBlock() == BWMBlocks.HIBACHI) {
						int updateFlag = worldIn.getBlockState(pos.offset(face)).getBlock() == BWMBlocks.STOKED_FLAME ? 4 : 3;
						//System.out.println("Setting " + pos.offset(face) + " to a STOKED_FLAME");
						worldIn.setBlockState(pos.offset(face), BWMBlocks.STOKED_FLAME.getDefaultState(), updateFlag);
						return true;
					}else {
						worldIn.setBlockToAir(pos.offset(face));
						return true;
					}
				}
			}
			
			if(Alw.isPyrotechLoaded) {
				airflow = 25;
				performBellows(worldIn, pos, state);
				return true;
			}
			
		}else {
			worldIn.playSound(playerIn, pos, SoundHandler.BELLOWS, SoundCategory.BLOCKS, 6.0F, 1.0F);
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					Random rand = new Random();
					double d0 = pos.offset(state.getValue(FACING)).getX() + 0.3D + rand.nextDouble() * 6.0D / 16.0D;
			        double d1 = pos.offset(state.getValue(FACING)).getY() + 0.8D + rand.nextDouble() * 6.0D / 16.0D;
			        double d2 = pos.offset(state.getValue(FACING)).getZ() + 0.3D + rand.nextDouble() * 6.0D / 16.0D;
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + (rand.nextDouble() / 2), d1, d2, 0.0D, 0.0D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			}, 500);
			
		}
		
		return true;
	}
	
	private void performBellows(World worldIn, BlockPos pos, IBlockState state) { // Kinda wonky but it works for now - I don't want bellows to use a TE if not necessary
		if(!worldIn.isRemote) {
			if(airflow > 0) {
				EnumFacing face = worldIn.getBlockState(pos).getValue(FACING);
				TileEntity afc = worldIn.getTileEntity(pos.offset(face));
				if(afc != null && afc.hasCapability(ModuleCore.CAPABILITY_AIRFLOW_CONSUMER, face.getOpposite())) {
					IAirflowConsumerCapability consumer = afc.getCapability(ModuleCore.CAPABILITY_AIRFLOW_CONSUMER, face.getOpposite());
					consumer.consumeAirflow((float) (ModuleTechMachineConfig.BELLOWS.BASE_AIRFLOW * (512 * Math.pow(0.2, 5))), false);
				}
				airflow--;
				performBellows(worldIn, pos, state);
			}
		}
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
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()), 2);
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
}
