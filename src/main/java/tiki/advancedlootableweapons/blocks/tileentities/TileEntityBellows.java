package tiki.advancedlootableweapons.blocks.tileentities;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.Alw;

public class TileEntityBellows extends TileEntity implements ITickable {

	
	//==========================================================================================================================================================
	//                                                                   SERVER SIDE
	//==========================================================================================================================================================
	
	public static final float animationLengthSeconds = 1.5f;
	public static final int animationTicks = (int)(animationLengthSeconds * 20);
	private boolean animating = false;
	private boolean animationQueued = false;
	private int currentAnimationTick = 0;
	
	//==========================================================================================================================================================
	//                                                                   CLIENT SIDE
	//==========================================================================================================================================================
	
	@SideOnly(Side.CLIENT)
	private float clientTotalTicks = 0;
	@SideOnly(Side.CLIENT)
	private float clientPrevPartialTicks = 0f;
	
	public TileEntityBellows() {
		super();
	}
	
	@Override
	public void update() {
		if(animating) {
			Alw.logger.info("Tile Entity is currently animating!");
			if(currentAnimationTick >= animationTicks) {
				if(!animationQueued) {
					animating = false;
				}else {
					this.animationQueued = false;
					currentAnimationTick = 0;
					clientTotalTicks -= 30f;
				}
				this.onChanged();
			}
			currentAnimationTick++;
			Alw.logger.info("Current animation tick => " + currentAnimationTick + ", Client Total Ticks => " + clientTotalTicks);
		}
	}
	
	public void startAnimating() {
		Alw.logger.debug("Tile Entity recieved call to start bellows animation!");
		if(animating && !animationQueued) {
			animationQueued = true;
			this.onChanged();
		}else if(!animating) {
			animating = true;
			currentAnimationTick = 0;
			this.onChanged();
		}
	}
	
	public boolean isAnimating() {
		return this.animating;
	}
	
	public boolean hasAnimationQueued() {
		return this.animationQueued;
	}
	
	public int getAnimationTick() {
		return this.currentAnimationTick;
	}
	
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
	
	public static int getHeatIncreaseTimeInTicks() {
		return animationTicks;
	}
	
	public EnumFacing getFacing() {
		return ((EnumFacing)this.getWorld().getBlockState(this.getPos()).getValue(BlockHorizontal.FACING));
	}
	
	public void onChanged() {
		Alw.logger.info("TileEntityBellows#onChanged() called! Animating => " + this.animating);
		IBlockState state = this.world.getBlockState(pos);
		this.markDirty();
		this.world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.animating = compound.getBoolean("animating");
		this.animationQueued = compound.getBoolean("animationQueued");
		this.currentAnimationTick = compound.getInteger("animationProgress");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("animating", animating);
		compound.setBoolean("animationQueued", animationQueued);
		compound.setInteger("animationProgress", currentAnimationTick);
		
		return compound;
	}
	
	//==========================================================================================================================================================
	//                                                                   SERVER SIDE
	//==========================================================================================================================================================
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		
		compound.setBoolean("animating", animating);
		compound.setBoolean("animationQueued", animationQueued);
		compound.setInteger("animationProgress", currentAnimationTick);
		
		return compound;
	}
	
	//==========================================================================================================================================================
	//                                                                   CLIENT SIDE
	//==========================================================================================================================================================
	
	@Override
	public void handleUpdateTag(NBTTagCompound compound) {
		this.animating = compound.getBoolean("animating");
		this.animationQueued = compound.getBoolean("animationQueued");
		this.currentAnimationTick = compound.getInteger("animationProgress");
		
		super.handleUpdateTag(compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@SideOnly(Side.CLIENT)
	public float getClientTotalTicks() {
		return this.clientTotalTicks;
	}
	
	@SideOnly(Side.CLIENT)
	public void setClientTotalTicks(float ticks) {
		this.clientTotalTicks = ticks;
	}
	
	@SideOnly(Side.CLIENT)
	public void checkTicks(float partialTicks) {
		if(partialTicks < this.clientPrevPartialTicks) {
			this.clientTotalTicks++;
		}
		this.clientPrevPartialTicks = partialTicks;
	}

}
