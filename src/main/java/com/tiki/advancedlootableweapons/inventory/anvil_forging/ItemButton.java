package com.tiki.advancedlootableweapons.inventory.anvil_forging;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ItemButton extends Button {

    protected final ItemStack stack;
    public ItemButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress,ItemStack stack) {
        this(pX, pY, pWidth, pHeight, pMessage, pOnPress,NO_TOOLTIP,stack);
    }

    public ItemButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress, OnTooltip pOnTooltip,ItemStack stack) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress, pOnTooltip);
        this.stack = stack;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(stack,x+2,y+2);
    }
}
