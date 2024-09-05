package com.tiki.advancedlootableweapons.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.menu.TanningRackMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TanningRackScreen extends AbstractContainerScreen<TanningRackMenu> {

    private static final ResourceLocation TEXTURE = AdvancedLootableWeapons.id( "textures/gui/tanning_rack.png");


    public TanningRackScreen(TanningRackMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        titleLabelY += 4;
        titleLabelX += 1;
        inventoryLabelX += 1;
        inventoryLabelY -= 3;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            int cookProgress = menu.getScaledProgress();
            blit(poseStack, x + 76, y + 55 - cookProgress, 176, 25 - cookProgress, 24, cookProgress);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}
