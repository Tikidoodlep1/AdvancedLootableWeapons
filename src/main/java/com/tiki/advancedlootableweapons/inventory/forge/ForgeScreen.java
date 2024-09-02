package com.tiki.advancedlootableweapons.inventory.forge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;

import com.tiki.advancedlootableweapons.blocks.block_entity.AdvancedForgeBlockEntity;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ForgeScreen extends AbstractContainerScreen<ForgeContainer> {

	private static final ResourceLocation TEXTURE = AdvancedLootableWeapons.id( "textures/gui/forge.png");

	static final int BAR_X_OFFSET = 122;
	static final int BAR_WIDTH = 19;
	static final int BAR_HEIGHT = 65;

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

		double progress = (double) (BAR_HEIGHT * (menu.getContainerTemp() - AdvancedForgeBlockEntity.MIN_TEMP)) / (AdvancedForgeBlockEntity.MAX_TEMP - AdvancedForgeBlockEntity.MIN_TEMP);
		this.blit(pPoseStack, this.getGuiLeft() + BAR_X_OFFSET, (int) (this.getGuiTop() + 69 - progress), 176, (int) (BAR_HEIGHT - progress), 18, (int) progress);
	}
	
	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
		if(pMouseX > this.getGuiLeft() + BAR_X_OFFSET && pMouseX < this.getGuiLeft() + BAR_X_OFFSET + BAR_WIDTH && pMouseY > this.getGuiTop() + 4 && pMouseY < this.getGuiTop() + 70) {
			double temp = ((menu.getContainerTemp()-32)*5/9);
			this.renderTooltip(pPoseStack, new TextComponent("Forge Temperature: " + (int)temp + " Celcius"), pMouseX, pMouseY);
		}
	}
}
