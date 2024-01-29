package com.tiki.advancedlootableweapons.blocks.block_entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceHandler;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SLOT_INPUT_1 = 0;
    public static final int SLOT_INPUT_2 = 1;
    public static final int SLOT_FUEL = 2;
    public static final int SLOT_RESULT = 3;
    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int MAX_COOKING_TIME = 266;
    public static final int BURN_COOL_SPEED = 2;
    private int litTime;
    private int litDuration;
    private int cookingProgress = 0;
    private int cookingTotalTime = MAX_COOKING_TIME;

    private AlloyFurnaceRecipe cachedRecipe = null;

    private Component name;
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int data) {
            switch (data) {
                case DATA_LIT_TIME:
                    return AlloyFurnaceBlockEntity.this.litTime;
                case DATA_LIT_DURATION:
                    return AlloyFurnaceBlockEntity.this.litDuration;
                case DATA_COOKING_PROGRESS:
                    return AlloyFurnaceBlockEntity.this.cookingProgress;
                case DATA_COOKING_TOTAL_TIME:
                    return AlloyFurnaceBlockEntity.this.cookingTotalTime;
                default:
                    return 0;
            }
        }

        public void set(int data, int val) {
            switch (data) {
                case DATA_LIT_TIME:
                    AlloyFurnaceBlockEntity.this.litTime = val;
                    break;
                case DATA_LIT_DURATION:
                    AlloyFurnaceBlockEntity.this.litDuration = val;
                    break;
                case DATA_COOKING_PROGRESS:
                    AlloyFurnaceBlockEntity.this.cookingProgress = val;
                    break;
                case DATA_COOKING_TOTAL_TIME:
                    AlloyFurnaceBlockEntity.this.cookingTotalTime = val;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

    };

    private final ItemStackHandler itemHandler = new AlloyFurnaceHandler(4) {


        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (slot == SLOT_INPUT_1 || slot == SLOT_INPUT_2) {
                lookForRecipe = true;
            } else if (slot == SLOT_FUEL) {
                findFuel = true;
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ALLOY_FURNACE_TE.get(), pos, state);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inv, Player player) {
        return new AlloyFurnaceContainer(containerId, inv, this.itemHandler, this.dataAccess, ContainerLevelAccess.create(level, worldPosition));
    }

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
        return MCVersion.translation("container.advancedlootableweapons.alloy_furnace");
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
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
        tag.putInt("BurnTime", this.litTime);
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
        this.litTime = tag.getInt("BurnTime");
        this.cookingProgress = tag.getInt("CookTime");
        this.cookingTotalTime = tag.getInt("CookTimeTotal");
        this.litDuration = ForgeHooks.getBurnTime(this.itemHandler.getStackInSlot(SLOT_FUEL), RecipeType.SMELTING);
        if (tag.contains("CustomName", Tag.TAG_STRING)) {
            this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

    protected void serverTick(Level level,BlockPos pos,BlockState state) {
        if (lookForRecipe) {
            lookForRecipe();
            lookForRecipe = false;
            if (cachedRecipe != null) {
                findFuel = true;
            }
        }

        boolean wasLit = isLit();

        //checking fuel
        if (wasLit) {
            litTime--;
        } else if (findFuel) {
            findFuel();
            //didn't find fuel, reset to 0 and end early
            if (!isLit()) {
                cookingProgress = 0;
            }
        }

        if (cachedRecipe != null) {
            if (hasRoom() && isLit()) {
                cookingProgress++;
                //the recipe is finished, cook the items and reset the timer
                if (cookingProgress >= cookingTotalTime) {
                    smelt();
                    cookingProgress = 0;
                }
            }
        }

        if (wasLit != isLit()) {
            state = state.setValue(AbstractFurnaceBlock.LIT,isLit());
            level.setBlock(pos, state, 3);
        }
    }

    //find fuel and use it to cook
    protected void findFuel() {
        //there is no recipe, don't look for fuel
        if (cachedRecipe == null) return;
        //don't bother looking for fuel if there's no room left
        if (!hasRoom()) return;
        int burnTime = ForgeHooks.getBurnTime(itemHandler.getStackInSlot(SLOT_FUEL),RecipeType.SMELTING);
        if (burnTime > 0) {
            this.litDuration = burnTime;
            this.litTime = burnTime;
            itemHandler.extractItem(SLOT_FUEL,1,false);
            findFuel = false;
        }
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    protected void smelt() {
        boolean normalOrientation = getOrientation();

        if (normalOrientation) {
            //shrink 1st stack using 1st recipe input
            itemHandler.extractItem(SLOT_INPUT_1, cachedRecipe.getCount1(), false);
            //shrink 2nd stack using 2nd recipe input
            itemHandler.extractItem(SLOT_INPUT_2, cachedRecipe.getCount2(), false);
        } else {
            //shrink 1st stack using 2nd recipe input
            itemHandler.extractItem(SLOT_INPUT_1, cachedRecipe.getCount2(), false);
            //shrink 2nd stack using 1st recipe input
            itemHandler.extractItem(SLOT_INPUT_2, cachedRecipe.getCount1(), false);
        }
        int existingCount = itemHandler.getStackInSlot(SLOT_RESULT).getCount();
        //add result to output
        itemHandler.setStackInSlot(SLOT_RESULT, ItemHandlerHelper.
                copyStackWithSize(cachedRecipe.getResultItem(),cachedRecipe.getResultItem().getCount() + existingCount));
    }

    protected boolean getOrientation() {
        return cachedRecipe.normalOrientation(new RecipeWrapper(itemHandler));
    }

    //check if there's enough room to process the current recipe
    protected boolean hasRoom() {
        ItemStack result = cachedRecipe.assemble(null);
        ItemStack output = itemHandler.getStackInSlot(SLOT_RESULT);
        //if the output is empty, then there's always enough room
        if (output.isEmpty()) return true;
        //if the result of the recipe does NOT match what is currently in the output, like say bronze and steel, there's no room
        if (!ItemStack.isSameItemSameTags(result, output)) return false;
        //else make sure current recipe result + existing count does not go over the max stack size
        return output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private boolean lookForRecipe = true;
    private boolean findFuel = true;

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
        cachedRecipe = level.getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, new RecipeWrapper(itemHandler), level).orElse(null);
        if (cachedRecipe != null) {
            cookingTotalTime = cachedRecipe.getCookTime();
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
    }

    public static void tick(Level world, BlockPos pos, BlockState state, AlloyFurnaceBlockEntity entity) {
        entity.serverTick(world,pos,state);
    }

    public int getContainerSize() {
        return 4;
    }
}
