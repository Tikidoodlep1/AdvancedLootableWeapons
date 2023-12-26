package com.tiki.advancedlootableweapons.inventory.alloy_furnace;

import com.tiki.advancedlootableweapons.blocks.block_entity.AlloyFurnaceBlockEntity;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.MenuInit;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlloyFurnaceContainer extends AbstractContainerMenu {

    private final ContainerData data;
    private final ContainerLevelAccess access;

    public AlloyFurnaceContainer(int id, Inventory inv) {
        this(id, inv, new ItemStackHandler(4), new SimpleContainerData(4), ContainerLevelAccess.NULL);
    }

    public AlloyFurnaceContainer(int id, Inventory inv, ItemStackHandler handler, ContainerData data, ContainerLevelAccess access) {
        super(MenuInit.ALLOY_FURNACE_CONTAINER.get(), id);
        checkContainerSize(inv, 4);
        this.access = access;
        this.data = data;

        this.addPlayerInv(inv);
        this.addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(handler, 0, 45, 17));
        this.addSlot(new SlotItemHandler(handler, 1, 68, 17));
        this.addSlot(new SlotItemHandler(handler, 2, 57, 54));
        this.addSlot(new SlotItemHandler(handler, 3, 116, 36));

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(AlloyFurnaceBlockEntity.DATA_COOKING_PROGRESS) > 0;
    }

    public int getScaledProgress() {
        int progress = data.get(AlloyFurnaceBlockEntity.DATA_COOKING_PROGRESS);
        int maxProgress = data.get(AlloyFurnaceBlockEntity.DATA_COOKING_TOTAL_TIME);
        int progressArrowSize = 26;

        if (maxProgress == 0) return 0;

        return (int) (((double)progress / maxProgress) * progressArrowSize);
    }

    public int getLitTime() {
        double time = data.get(AlloyFurnaceBlockEntity.DATA_LIT_TIME);
        double totalTime = data.get(AlloyFurnaceBlockEntity.DATA_LIT_DURATION);

        if (totalTime == 0) totalTime = 200;

        double percent = time / totalTime;

        return(int) (percent * 15);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT - 1, false)) {//do not shift click into outputs
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, BlockInit.ALLOY_FURNACE.get());
    }

    private void addPlayerInv(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
