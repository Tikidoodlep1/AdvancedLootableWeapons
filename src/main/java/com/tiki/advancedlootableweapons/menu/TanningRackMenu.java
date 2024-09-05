package com.tiki.advancedlootableweapons.menu;

import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.MenuInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.items.ItemStackHandler;

public class TanningRackMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess access;

    public TanningRackMenu(int id, Inventory inv) {
        this(id, inv,new ItemStackHandler(2),  ContainerLevelAccess.NULL);
    }


    public TanningRackMenu(int pContainerId, Inventory inventory, ItemStackHandler handler, ContainerLevelAccess access) {
        super(MenuInit.TANNING_RACK.get(), pContainerId);
        this.access = access;

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(access, pPlayer, BlockInit.TANNING_RACK.get());
    }
}
