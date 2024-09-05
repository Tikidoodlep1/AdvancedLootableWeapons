package com.tiki.advancedlootableweapons.blockentity;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.menu.TanningRackMenu;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class TanningRackBlockEntity extends BlockEntity implements MenuProvider {

    private int cookingProgress = 0;
    private int cookingTotalTime = 50;

    private AlloyFurnaceRecipe cachedRecipe = null;

    private Component name;
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int data) {
            return switch (data) {
                case AlloyFurnaceBlockEntity.DATA_COOKING_PROGRESS -> cookingProgress;
                case AlloyFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME -> cookingTotalTime;
                default -> 0;
            };
        }

        public void set(int data, int val) {
            switch (data) {
                case AlloyFurnaceBlockEntity.DATA_COOKING_PROGRESS -> cookingProgress = val;
                case AlloyFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME -> cookingTotalTime = val;
            }
        }

        @Override
        public int getCount() {
            return 0;
        }
    };

    public TanningRackBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public TanningRackBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(BlockEntityInit.TANNING_RACK.get(),pPos,pBlockState);
    }


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ItemStackHandler itemHandler = new ItemStackHandler() {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new TanningRackMenu(pContainerId,pPlayerInventory,itemHandler, ContainerLevelAccess.create(level,worldPosition));
    }

    public IItemHandlerModifiable getItemHandler() {
        return itemHandler;
    }
}
