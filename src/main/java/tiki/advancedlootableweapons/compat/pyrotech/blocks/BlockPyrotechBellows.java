package tiki.advancedlootableweapons.compat.pyrotech.blocks;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.codetaylor.mc.pyrotech.IAirflowConsumerCapability;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;
import com.codetaylor.mc.pyrotech.modules.tech.machine.ModuleTechMachineConfig;

import betterwithmods.common.BWMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.blocks.BlockBellows;
import tiki.advancedlootableweapons.blocks.BlockForge2Placeholder;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.handlers.SoundHandler;

public class BlockPyrotechBellows extends BlockBellows {

	private final Timer t = new Timer("ALWCoolDowns");
	private int airflow = 0;
	
	public BlockPyrotechBellows(String name) {
		super(name);
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
				((TileEntityForge2)te).bellowsInteraction();								
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
			
			airflow = 25;
			performBellows(worldIn, pos, state);
			return true;			
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
	
}