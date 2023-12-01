package tiki.advancedlootableweapons.blocks.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import tiki.advancedlootableweapons.init.FluidInit;

public class AlwFluid extends Fluid {

	public AlwFluid(String fluidName, ResourceLocation still, ResourceLocation flowing, ResourceLocation overlay, Color color) {
		super(fluidName, still, flowing, overlay, color);
		
		FluidInit.fluids.add(this);
		this.setUnlocalizedName(fluidName);
	}
	
	
	
}
