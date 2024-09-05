package com.tiki.advancedlootableweapons.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;

import com.tiki.advancedlootableweapons.menu.AlloyFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {
	private static final ResourceLocation TEXTURE = AdvancedLootableWeapons.id( "textures/gui/alloy_furnace.png");

	public AlloyFurnaceScreen(AlloyFurnaceMenu container, Inventory inv, Component name) {
		super(container, inv, name);
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);


		if(menu.isCrafting()) {
			int cookProgress = menu.getScaledProgress();
			blit(poseStack, x + 79, y + 36, 176, 14, cookProgress, 17);
		}

		int litTime = menu.getLitTime();
		if (litTime > 0) {
			blit(poseStack, x + 58, y + 51 - litTime, 176, 14 - litTime, 14, litTime + 1);
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, delta);
		renderTooltip(poseStack, mouseX, mouseY);

	}

}
