package com.tiki.advancedlootableweapons.blocks.fluids;

import java.awt.Color;

import com.tiki.advancedlootableweapons.init.FluidInit;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class AlwFluid extends Fluid {

	public AlwFluid(String fluidName, ResourceLocation still, ResourceLocation flowing, ResourceLocation overlay, Color color) {
		super(fluidName, still, flowing, overlay, color);
		
		FluidInit.fluids.add(this);
		this.setUnlocalizedName(fluidName);
	}
	
	
	
}
