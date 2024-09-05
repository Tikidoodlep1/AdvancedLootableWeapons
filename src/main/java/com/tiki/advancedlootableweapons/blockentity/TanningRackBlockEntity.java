package com.tiki.advancedlootableweapons.blockentity;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.inventory.TanningRackHandler;
import com.tiki.advancedlootableweapons.menu.TanningRackMenu;
import com.tiki.advancedlootableweapons.recipes.TanningRackRecipe;
import com.tiki.advancedlootableweapons.util.MCVersion;
import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.*;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class TanningRackBlockEntity extends BlockEntity implements MenuProvider {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_RESULT = 1;

    public static final int DATA_COOKING_PROGRESS = 0;
    public static final int DATA_COOKING_TOTAL_TIME = 1;
    private int cookingProgress = 0;
    private int cookingTotalTime = MAX_COOKING_TIME;
    public static final int MAX_COOKING_TIME = 600;


    private TanningRackRecipe cachedRecipe = null;

    private Component name;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int data) {
            return switch (data) {
                case DATA_COOKING_PROGRESS -> cookingProgress;
                case DATA_COOKING_TOTAL_TIME -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int data, int val) {
            switch (data) {
                case DATA_COOKING_PROGRESS -> cookingProgress = val;
                case DATA_COOKING_TOTAL_TIME -> cookingTotalTime = val;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public TanningRackBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public TanningRackBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(BlockEntityInit.TANNING_RACK.get(), pPos, pBlockState);
    }


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ItemStackHandler itemHandler = new TanningRackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (slot == SLOT_INPUT) {
                lookForRecipe = true;
            }
        }
    };

    public void setCustomName(Component pName) {
        this.name = pName;
    }

    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    protected Component getDefaultName() {
        return MCVersion.translation(Utils.getMenuDescId(MenuInit.TANNING_RACK.get()));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new TanningRackMenu(pContainerId, pPlayerInventory, itemHandler, dataAccess, ContainerLevelAccess.create(level, worldPosition));
    }

    public IItemHandlerModifiable getItemHandler() {
        return itemHandler;
    }

    public static void tick(Level world, BlockPos pos, BlockState state, TanningRackBlockEntity entity) {
        entity.serverTick(world, pos, state);
    }


    protected void serverTick(Level level, BlockPos pos, BlockState state) {
        if (lookForRecipe) {
            lookForRecipe();
            lookForRecipe = false;
            if (cachedRecipe != null) {
            }
        }

        if (cachedRecipe != null) {
            if (hasRoom()) {
                cookingProgress++;
                //the recipe is finished, cook the items and reset the timer
                if (cookingProgress >= cookingTotalTime) {
                    smelt();
                    cookingProgress = 0;
                }
            }
        }
    }

    protected void smelt() {

        //shrink 1st stack using recipe input
        itemHandler.extractItem(SLOT_INPUT, 1, false);

        ItemStack existing = itemHandler.getStackInSlot(SLOT_RESULT);
        if (existing.isEmpty()) {
            itemHandler.setStackInSlot(SLOT_RESULT,cachedRecipe.assemble(new RecipeWrapper(itemHandler)));
        }else {
            //add result to output
            itemHandler.setStackInSlot(SLOT_RESULT, ItemHandlerHelper.
                    copyStackWithSize(existing, existing.getCount() + 1));
        }
    }

    //check if there's enough room to process the current recipe
    protected boolean hasRoom() {
        ItemStack result = cachedRecipe.assemble(new RecipeWrapper(itemHandler));
        ItemStack output = itemHandler.getStackInSlot(SLOT_RESULT);
        //if the output is empty, then there's always enough room
        if (output.isEmpty()) return true;
        //if the result of the recipe does NOT match what is currently in the output, like say bronze and steel, there's no room
        if (!ItemStack.isSameItemSameTags(result, output)) return false;
        //else make sure current recipe result + existing count does not go over the max stack size
        return output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private boolean lookForRecipe = true;

    protected void lookForRecipe() {
        //check if the prior recipe matches
        if (cachedRecipe != null) {
            if (cachedRecipe.matches(new RecipeWrapper(itemHandler), level)) {
                return;
            }
            //else invalidate and set progress to 0
            cachedRecipe = null;
            cookingProgress = 0;
        }
        //lookup a new recipe
        cachedRecipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.TANNING_RACK, new RecipeWrapper(itemHandler), level).orElse(null);
        if (cachedRecipe != null) {
            cookingTotalTime = cachedRecipe.getCookTime();
        }
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
        if (this.name != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        this.cookingProgress = tag.getInt("CookTime");
        this.cookingTotalTime = tag.getInt("CookTimeTotal");
        if (tag.contains("CustomName", Tag.TAG_STRING)) {
            this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

}
