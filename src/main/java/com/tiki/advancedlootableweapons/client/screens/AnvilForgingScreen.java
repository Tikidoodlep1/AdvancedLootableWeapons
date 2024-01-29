package com.tiki.advancedlootableweapons.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.inventory.anvil_forging.AnvilForgingContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AnvilForgingScreen extends AbstractContainerScreen<AnvilForgingContainer> {

	static final ResourceLocation TEXTURE = new ResourceLocation(AdvancedLootableWeapons.MODID, "textures/gui/anvil_forging.png");

	private final AnvilForgingButtons anvilForgingButtons = new AnvilForgingButtons();


	public AnvilForgingScreen(AnvilForgingContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void init() {
		super.init();
		this.anvilForgingButtons.init(this.width, this.height, this.minecraft);
		this.leftPos = this.anvilForgingButtons.updateScreenPosition(this.width, this.imageWidth);
		this.addWidget(this.anvilForgingButtons);
		this.setInitialFocus(this.anvilForgingButtons);
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int i = this.leftPos;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pPoseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		this.anvilForgingButtons.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
		this.anvilForgingButtons.renderTooltip(pPoseStack, this.leftPos, this.topPos, pMouseX, pMouseY);
	}

	@Override
	public void removed() {
		anvilForgingButtons.removed();
		super.removed();
	}
}
