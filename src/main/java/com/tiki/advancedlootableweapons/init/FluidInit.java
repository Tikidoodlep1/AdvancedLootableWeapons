package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FluidInit {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, AdvancedLootableWeapons.MODID);

    public static final ResourceLocation MOL_TEXTURE = new ResourceLocation("forge", "block/milk_still");
    public static final ResourceLocation ML_TEXTURE = new ResourceLocation("forge", "block/milk_still");

    static final Supplier<ForgeFlowingFluid.Properties> props = () ->
            new ForgeFlowingFluid.Properties(FluidInit.MILK_OF_LIME,FluidInit.FLOWING_MILK_OF_LIME,FluidAttributes.builder(
                            MOL_TEXTURE,
                    new ResourceLocation("forge", "block/milk_flowing"))
                    .density(1024).viscosity(1024).color(0xff_DFD3BE))
                    .block(BlockInit.MILK_OF_LIME).bucket(ItemInit.MILK_OF_LIME_BUCKET);

    static final Supplier<ForgeFlowingFluid.Properties> magnesium_props = () ->
            new ForgeFlowingFluid.Properties(FluidInit.MAGNESIUM_LACTATE,FluidInit.FLOWING_MAGNESIUM_LACTATE,FluidAttributes.builder(
                            ML_TEXTURE,
                            new ResourceLocation("forge", "block/milk_flowing"))
                    .density(1024).viscosity(1024).color(0xff_eeeeee))
                    .block(BlockInit.MAGNESIUM_LACTATE).bucket(ItemInit.MAGNESIUM_LACTATE_BUCKET);

    public static final RegistryObject<FlowingFluid> MILK_OF_LIME = FLUIDS.register("milk_of_lime",
            () -> new ForgeFlowingFluid.Source(props.get()));
    public static final RegistryObject<FlowingFluid> FLOWING_MILK_OF_LIME = FLUIDS.register("flowing_milk_of_lime",
            () -> new ForgeFlowingFluid.Flowing(props.get()));

    public static final RegistryObject<FlowingFluid> MAGNESIUM_LACTATE = FLUIDS.register("magnesium_lactate",
            () -> new ForgeFlowingFluid.Source(magnesium_props.get()));
    public static final RegistryObject<FlowingFluid> FLOWING_MAGNESIUM_LACTATE = FLUIDS.register("flowing_magnesium_lactate",
            () -> new ForgeFlowingFluid.Flowing(magnesium_props.get()));


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }


}
