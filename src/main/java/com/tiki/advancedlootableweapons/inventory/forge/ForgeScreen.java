package com.tiki.advancedlootableweapons.inventory.forge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ForgeScreen extends AbstractContainerScreen<ForgeContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(ModInfo.ID, "textures/gui/forge.png");

	public ForgeScreen(ForgeContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
		
		int temp = (int)(menu.getContainerTemp() * 0.037);
		this.blit(pPoseStack, this.getGuiLeft() + 122, this.getGuiTop() + 69 - temp, 176, 65 - temp, 18, temp);
	}
	
	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
		if(pMouseX > this.getGuiLeft() + 122 && pMouseX < this.getGuiLeft() + 141 && pMouseY > this.getGuiTop() + 4 && pMouseY < this.getGuiTop() + 70) {
			double temp = ((menu.getContainerTemp()-32)*5/9);
			this.renderTooltip(pPoseStack, new TextComponent("Forge Temperature: " + (int)temp + " Celcius"), pMouseX, pMouseY);
		}
	}
}
