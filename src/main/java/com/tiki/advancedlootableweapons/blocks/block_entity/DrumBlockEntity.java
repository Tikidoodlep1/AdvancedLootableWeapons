package com.tiki.advancedlootableweapons.blocks.block_entity;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.recipes.DrumRecipe;
import com.tiki.advancedlootableweapons.recipes.SingleFluidRecipeWrapper;
import com.tiki.advancedlootableweapons.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrumBlockEntity extends BlockEntity {

    public static final int INPUT_SLOT = 0;
    public static final int ADDITIVE_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public int progress = 0;
    private int cookingTotalTime = MAX_COOKING_TIME;

    private DrumRecipe activeRecipe = null;
    private boolean lookForRecipe = true;

    public boolean displayBubbles = false;
    public boolean displayQuenching = false;

    public static final int MAX_COOKING_TIME = 66;

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (slot == INPUT_SLOT) {
                lookForRecipe = true;
            }
            setChanged();
        }
    };

    public final FluidTank fluidTank = new FluidTank(1000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            setChanged();
        }
    };

    protected final LazyOptional<ItemStackHandler> itemStackHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    protected final LazyOptional<FluidTank> fluidTankLazyOptional = LazyOptional.of(() -> fluidTank);
    public DrumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.DRUM_TE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("items",itemStackHandler.serializeNBT());
        pTag.put("fluids",fluidTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("items"));
        fluidTank.readFromNBT(pTag.getCompound("fluids"));
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    public static void tick(Level world, BlockPos pos, BlockState state, DrumBlockEntity entity) {
        entity.serverTick(world,pos,state);
    }

    protected void serverTick(Level level, BlockPos pos, BlockState state) {
        if (lookForRecipe) {
            lookForRecipe();
            lookForRecipe = false;
        }

        if (activeRecipe != null) {
            boolean canProceed = true;
            boolean isQuenchRecipe = activeRecipe.needsQuenching();
            if (isQuenchRecipe) {
                canProceed = hasHotBlockOrFluid();
            }
            if (canProceed) {
                if (!isQuenchRecipe) {
                    displayBubbles = true;
                } else {
                    displayQuenching = true;
                }
                progress++;
                if (progress >= cookingTotalTime) {
                    process();
                    progress = 0;
                }
            }
        }
    }

    protected void process() {
            //shrink 1st stack using 1st recipe input
            itemStackHandler.extractItem(INPUT_SLOT, 1, false);

        int existingCount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        //add result to output
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemHandlerHelper.
                copyStackWithSize(activeRecipe.getResultItem(),activeRecipe.getResultItem().getCount() + existingCount));
        displayQuenching = false;
        displayBubbles = false;
    }

    protected void lookForRecipe() {
        //check if the prior recipe matches
        if (activeRecipe != null) {
            if (activeRecipe.matches(new SingleFluidRecipeWrapper(itemStackHandler,fluidTank), level)) {
                return;
            }
            //else invalidate and set progress to 0
            activeRecipe = null;
            progress = 0;
        }
        //lookup a new recipe
        activeRecipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRUM,
                new SingleFluidRecipeWrapper(itemStackHandler,fluidTank), level).orElse(null);
        if (activeRecipe != null) {
            cookingTotalTime = activeRecipe.getTime();
        }
    }

    public boolean playerInteraction(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
        lookForRecipe = true;
        ItemStack activeStack = playerIn.getItemInHand(hand);

        if(!playerIn.isCrouching() && activeStack != ItemStack.EMPTY) {
            if(this.itemStackHandler.getStackInSlot(INPUT_SLOT).isEmpty()) {
                this.itemStackHandler.setStackInSlot(INPUT_SLOT, activeStack);
                playerIn.setItemInHand(hand, ItemStack.EMPTY);
            }else if(this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT).isEmpty() && !(activeStack.getItem() instanceof HotToolHeadItem)) {
                this.itemStackHandler.setStackInSlot(ADDITIVE_SLOT, new ItemStack(activeStack.getItem()));
                activeStack.shrink(1);
            }
        }else {
            if(this.itemStackHandler.getStackInSlot(OUTPUT_SLOT) != ItemStack.EMPTY) {
                if(playerIn.addItem(this.itemStackHandler.getStackInSlot(OUTPUT_SLOT))) {
                    this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            }else if(this.itemStackHandler.getStackInSlot(INPUT_SLOT) != ItemStack.EMPTY) {
                if(playerIn.addItem(this.itemStackHandler.getStackInSlot(INPUT_SLOT))) {
                    this.itemStackHandler.setStackInSlot(INPUT_SLOT, ItemStack.EMPTY);
                    this.progress = 0;
                    this.activeRecipe = null;
                }
            }else if(this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT) != ItemStack.EMPTY) {
                if(playerIn.addItem(this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT))) {
                    this.itemStackHandler.setStackInSlot(ADDITIVE_SLOT, ItemStack.EMPTY);
                    this.progress = 0;
                    this.activeRecipe = null;
                }
            }


        }
        return true;
    }

    public boolean hasHotBlockOrFluid() {
        BlockPos down = getBlockPos().below();
        Fluid f = level.getFluidState(down).getType();
        BlockState block = level.getBlockState(down);
        boolean hotFluid = f.getAttributes().getTemperature() >= Fluids.LAVA.getAttributes().getTemperature() - 500;

        boolean hotBlock = block.is(ModBlockTags.DRUM_HEATING);

       return hotBlock || hotFluid;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemStackHandlerLazyOptional.cast();
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidTankLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }
}
