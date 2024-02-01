package com.tiki.advancedlootableweapons.blocks.block_entity;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.init.SoundInit;
import com.tiki.advancedlootableweapons.inventory.ExternalIItemHandler;
import com.tiki.advancedlootableweapons.items.HotToolHeadItem;
import com.tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;
import com.tiki.advancedlootableweapons.recipes.DrumRecipe;
import com.tiki.advancedlootableweapons.recipes.SingleFluidRecipeWrapper;
import com.tiki.advancedlootableweapons.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
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
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class DrumBlockEntity extends BlockEntity {

    public static final int INPUT_SLOT = 0;
    public static final int ADDITIVE_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public int progress = 0;
    private int cookingTotalTime = MAX_COOKING_TIME;

    private Recipe<SingleFluidRecipeWrapper> activeRecipe = null;
    private boolean lookForRecipe = true;
    public static final int MAX_COOKING_TIME = 66;

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (slot == INPUT_SLOT) {
                lookForRecipe = true;
            }
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot == ADDITIVE_SLOT ? !(stack.getItem() instanceof HotToolHeadItem) : super.isItemValid(slot, stack);
        }
    };

    public final FluidTank fluidTank = new FluidTank(1000) {
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            setChanged();
        }
    };

    protected final LazyOptional<IItemHandler> itemStackHandlerLazyOptional = LazyOptional.of(() ->
            new ExternalIItemHandler(itemStackHandler, List.of(INPUT_SLOT,ADDITIVE_SLOT),List.of(OUTPUT_SLOT)));
    protected final LazyOptional<FluidTank> fluidTankLazyOptional = LazyOptional.of(() -> fluidTank);

    public DrumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.DRUM_TE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("items", itemStackHandler.serializeNBT());
        pTag.put("fluids", fluidTank.writeToNBT(new CompoundTag()));
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

    @Override
    public void setChanged() {
        super.setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);//this is required to sync fluids/items to client
    }

    public static void tick(Level world, BlockPos pos, BlockState state, DrumBlockEntity entity) {
        entity.serverTick(world, pos, state);
    }

    protected void serverTick(Level level, BlockPos pos, BlockState state) {
        if (lookForRecipe) {
            lookForRecipe();
            lookForRecipe = false;
        }

        if (activeRecipe != null) {
            boolean canProceed = true;
            boolean isQuenchRecipe = activeRecipe instanceof DrumQuenchingRecipe;
            if (isQuenchRecipe) {
                canProceed = hasHotBlockOrFluid();
            }
            if (canProceed) {
                BlockPos blockPos = getBlockPos();
                Random rand = level.getRandom();

                if (isQuenchRecipe) {


                    //  world.addParticle(ParticleTypes.SPIT, x + 0.5 + (rand.nextFloat() - 0.5), y + 0.85 + (rand.nextFloat() - 0.5),
                    //          z + 0.5 + (rand.nextFloat() - 0.5), 0.0, 0.0, 0.01f);


                    ((ServerLevel) level).sendParticles(ParticleTypes.SPIT, blockPos.getX() + rand.nextFloat(),
                            blockPos.getY() + rand.nextFloat(), blockPos.getZ() + rand.nextFloat(),
                            1, 0, 0, 0, .01f);
                } else {

                }
                progress++;
                if (progress >= cookingTotalTime) {
                    ((ServerLevel) level).sendParticles(ParticleTypes.SPIT, blockPos.getX() + rand.nextFloat(),
                            blockPos.getY() + rand.nextFloat(), blockPos.getZ() + rand.nextFloat(),
                            8, 0, 0, 0, .01f);

                    level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundInit.QUENCH, SoundSource.BLOCKS,
                            1.0f, 1.0f);

                    process();
                    progress = 0;
                }
            }
        }
    }

    protected void process() {
        ItemStack result = activeRecipe.assemble(new SingleFluidRecipeWrapper(itemStackHandler, fluidTank));
        //using setStackInSlot bypasses insertion/extraction checks and is faster
        //shrink 1st stack using 1st recipe input
        ItemStack input = itemStackHandler.getStackInSlot(INPUT_SLOT);
        itemStackHandler.setStackInSlot(INPUT_SLOT, ItemHandlerHelper.copyStackWithSize(input,input.getCount() - 1));
        //add result to output
        itemStackHandler.setStackInSlot(OUTPUT_SLOT,result);
    }

    protected void lookForRecipe() {
        //check if the prior recipe matches
        if (activeRecipe != null) {
            if (activeRecipe.matches(new SingleFluidRecipeWrapper(itemStackHandler, fluidTank), level)) {
                return;
            }
            //else invalidate and set progress to 0
            activeRecipe = null;
            progress = 0;
        }
        //lookup a new recipe
        activeRecipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRUM_QUENCHING,
                new SingleFluidRecipeWrapper(itemStackHandler, fluidTank), level).orElse(null);
        if (activeRecipe != null) {
            cookingTotalTime = ((DrumQuenchingRecipe)activeRecipe).getTime();
        } else {
            activeRecipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRUM,
                    new SingleFluidRecipeWrapper(itemStackHandler, fluidTank), level).orElse(null);
            cookingTotalTime = ((DrumRecipe)activeRecipe).getTime();
        }
    }

    public boolean playerInteraction(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
        lookForRecipe = true;
        ItemStack activeStack = playerIn.getItemInHand(hand);

        if (!playerIn.isCrouching() && activeStack != ItemStack.EMPTY) {
            if (this.itemStackHandler.getStackInSlot(INPUT_SLOT).isEmpty()) {
                this.itemStackHandler.setStackInSlot(INPUT_SLOT, activeStack);
                playerIn.setItemInHand(hand, ItemStack.EMPTY);
            } else if (this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT).isEmpty() && !(activeStack.getItem() instanceof HotToolHeadItem)) {
                this.itemStackHandler.setStackInSlot(ADDITIVE_SLOT, new ItemStack(activeStack.getItem()));
                activeStack.shrink(1);
            }
        } else {
            if (this.itemStackHandler.getStackInSlot(OUTPUT_SLOT) != ItemStack.EMPTY) {
                if (playerIn.addItem(this.itemStackHandler.getStackInSlot(OUTPUT_SLOT))) {
                    this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            } else if (this.itemStackHandler.getStackInSlot(INPUT_SLOT) != ItemStack.EMPTY) {
                if (playerIn.addItem(this.itemStackHandler.getStackInSlot(INPUT_SLOT))) {
                    this.itemStackHandler.setStackInSlot(INPUT_SLOT, ItemStack.EMPTY);
                    this.progress = 0;
                    this.activeRecipe = null;
                }
            } else if (this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT) != ItemStack.EMPTY) {
                if (playerIn.addItem(this.itemStackHandler.getStackInSlot(ADDITIVE_SLOT))) {
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
