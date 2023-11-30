package com.tiki.advancedlootableweapons.blocks.tileentities;

import com.codetaylor.mc.pyrotech.IAirflowConsumerCapability;
import com.codetaylor.mc.pyrotech.modules.core.ModuleCore;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityForgeAirflowConsumer extends TileEntityForge implements ITickable, IInventory, IAirflowConsumerCapability
{
	public TileEntityForgeAirflowConsumer() {
		super();
	}
	
	public TileEntityForgeAirflowConsumer(Block block) {
		this(false, false, block);
	}
	
	public TileEntityForgeAirflowConsumer(boolean needsFuel, boolean needsIgnition, Block block) {
		super(needsFuel, needsIgnition, block);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == ModuleCore.CAPABILITY_AIRFLOW_CONSUMER) {
			return true;
		}
		return false; //super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == ModuleCore.CAPABILITY_AIRFLOW_CONSUMER) {
			return (T) this;
		}
		return null; //super.getCapability(capability, facing);
	}
	
	@Override
	public float consumeAirflow(float airflow, boolean simulate) {
		if(!simulate) {
			this.increaseFrames = 1;
			this.airflowMultiplier = (((float)Math.sin(airflow) * 0.9f) + 1.1f)/2.0f;
		}
		return 0;
	}
}
