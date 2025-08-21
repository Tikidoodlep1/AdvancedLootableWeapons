package tiki.advancedlootableweapons.blocks.tileentities;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityBellows extends TileEntity implements ITickable {

	private static final float animationLengthSeconds = 1.5f;
	public static final int animationTicks = (int)(animationLengthSeconds * 20);
	private boolean animating = false;
	private boolean animationQueued = false;
	private int currentAnimationTick = 0;
	
	public TileEntityBellows() {
		super();
	}
	
	@Override
	public void update() {
		if(animating) {
			if(currentAnimationTick >= animationTicks) {
				if(animationQueued) {
					animating = false;
				}
				currentAnimationTick = 0;
			}
			currentAnimationTick++;
		}
	}
	
	public void startAnimating() {
		if(animating && !animationQueued) {
			animationQueued = true;
		}else if(!animating) {
			animating = true;
			currentAnimationTick = 0;
		}
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

}
