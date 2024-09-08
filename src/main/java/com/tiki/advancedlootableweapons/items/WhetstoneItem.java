package com.tiki.advancedlootableweapons.items;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.menu.WhetstoneMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class WhetstoneItem extends Item {

    public static final String REFINED = AdvancedLootableWeapons.id("refined").toString();
    public static final int MAX = 6;

    public final Tier tier;

    public WhetstoneItem(Properties pProperties, Tier tier) {
        super(pProperties);
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            pPlayer.openMenu(provider(pPlayer,stack));
        }
        return InteractionResultHolder.sidedSuccess(stack,pLevel.isClientSide);
    }


    MenuProvider provider(Player player,ItemStack stack){
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return stack.getHoverName();
            }

            @Override
            public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
                return new WhetstoneMenu(pContainerId,pPlayerInventory, stack);
            }
        };
    }

    public static void setRefined(ItemStack stack,int refined) {
        stack.getOrCreateTag().putInt(REFINED,refined);
    }

    public static int getRefined(ItemStack stack){
        return stack.hasTag() ? stack.getTag().getInt(REFINED) : 0;
    }

}
