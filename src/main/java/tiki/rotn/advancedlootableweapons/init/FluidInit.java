package tiki.rotn.advancedlootableweapons.init;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.fluids.AlwFluid;

public class FluidInit {
	public static final List<Fluid> fluids = new ArrayList<Fluid>();
		
	public static final Fluid MILK_OF_LIME = new AlwFluid("milk_of_lime", new ResourceLocation(ModInfo.ID, "blocks/milk_of_lime_still"), 
			new ResourceLocation(ModInfo.ID, "blocks/milk_of_lime_flow"), new ResourceLocation(ModInfo.ID, "blocks/milk_of_lime_overlay")
			, new Color(1.0F, 0.97F, 0.919F, 1.0F)).setDensity(1000).setViscosity(1150)
			.setFillSound(SoundEvents.ITEM_BUCKET_FILL).setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY);
	
	public static final Fluid MAGNESIUM_LACTATE = new AlwFluid("magnesium_lactate", new ResourceLocation(ModInfo.ID, "blocks/magnesium_lactate_still"), 
			new ResourceLocation(ModInfo.ID, "blocks/magnesium_lactate_flow"), new ResourceLocation(ModInfo.ID, "blocks/magnesium_lactate_overlay")
			, new Color(1.0F, 1.0F, 1.0F, 1.0F)).setDensity(1000).setViscosity(1010)
			.setFillSound(SoundEvents.ITEM_BUCKET_FILL).setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY);
	
	
	public static void registerFluids() {
		for(Fluid fluid : fluids) {
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}
	}
}
