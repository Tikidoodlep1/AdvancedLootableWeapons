package com.tiki.advancedlootableweapons.menu;

import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.WhetstoneItem;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class WhetstoneMenu extends AbstractContainerMenu {

    private final ItemStack whetstone;
    private final Container container = new SimpleContainer(1);
    private final WhetstoneHandler handler = new WhetstoneHandler(this,2);

    public WhetstoneMenu(int pContainerId, Inventory inventory) {
        this(pContainerId,inventory, ItemStack.EMPTY);
    }

    public WhetstoneMenu(int pContainerId, Inventory inventory, ItemStack whetstone) {
        super(MenuInit.WHETSTONE.get(), pContainerId);
        this.whetstone = whetstone;

        this.addSlot(new SlotItemHandler(handler, 0, 56, 43){

        });
        this.addSlot(new SlotItemHandler(handler, 1, 114, 43){
            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                super.onTake(pPlayer, pStack);
                whetstone.shrink(1);
                handler.getStackInSlot(0).shrink(1);
            }
        });

        this.addSlot(new Slot(container,0,-999,-999){
            @Override
            public boolean isActive() {
                return false;
            }

            @Override
            public boolean mayPickup(Player pPlayer) {
                return false;
            }
        });

        this.addPlayerInv(inventory);
        this.addPlayerHotbar(inventory);

    }

    public void createResult() {
        ItemStack refined = handler.getStackInSlot(0).copy();
        if (refined.isEmpty()) {
            handler.setStackInSlot(1,ItemStack.EMPTY);
        }else {
            WhetstoneItem.setRefined(refined, WhetstoneItem.getRefined(refined) + 1);
            handler.setStackInSlot(1, refined);
        }
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots and the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 2;  // must be the number of slots you have!

    private static final int VANILLA_FIRST_SLOT_INDEX = TE_INVENTORY_SLOT_COUNT;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = 0;



    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index >= VANILLA_FIRST_SLOT_INDEX) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT - 1, false)) {//do not shift click into outputs
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
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

    private void addPlayerInv(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142){
                @Override
                public boolean mayPickup(Player pPlayer) {
                    return !(getItem().getItem() instanceof WhetstoneItem) && super.mayPickup(pPlayer);
                }
            });
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return !whetstone.isEmpty();
    }

    public static class WhetstoneHandler extends ItemStackHandler {
        private final WhetstoneMenu whetstoneMenu;

        public WhetstoneHandler(WhetstoneMenu whetstoneMenu, int slots){
            super(slots);
            this.whetstoneMenu = whetstoneMenu;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot==0){
                int refined = WhetstoneItem.getRefined(stack);
                if (refined >= WhetstoneItem.MAX) {
                    return false;
                }
                ItemStack whetstone = whetstoneMenu.whetstone;
                if (whetstone.getItem() instanceof WhetstoneItem whetstoneItem) {
                return stack.getItem() instanceof AlwWeaponItem&& WeaponMaterial.findMaterial(HeatableToolPartItem.getCraftingMaterial(stack)).tier()== whetstoneItem.tier;
                }
               return false;
            }//don't allow taking items from otpt
            return slot != 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (slot == 0) {
                whetstoneMenu.createResult();
            }
        }
    }

    /**
     * Called when the container is closed.
     */
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.clearContainer(pPlayer, new RecipeWrapper(handler));
    }
}
