package com.tiki.advancedlootableweapons.recipes;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SingleFluidRecipeWrapper extends RecipeWrapper {
    protected final IFluidHandler fluidHandler;

    public SingleFluidRecipeWrapper(IItemHandlerModifiable inv, IFluidHandler fluidHandler) {
        super(inv);
        this.fluidHandler = fluidHandler;
    }

    public IFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    public boolean testFluid(FluidStack test) {
        FluidStack fluidStack = fluidHandler.getFluidInTank(0);
        return fluidStack.containsFluid(test);
    }
}
