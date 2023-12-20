package com.tiki.advancedlootableweapons.inventory.anvil_forging;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class WeaponButton extends Button {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(AdvancedLootableWeapons.MODID + ":textures/gui/anvil_forging");
	private final int x, y, u, v;
	private boolean down = false;
	
	public WeaponButton(int pX, int pY, int u, int v, Component pMessage) {
		super(pX, pY, 20, 20, pMessage, new Button.OnPress() {
			@Override
			public void onPress(Button pButton) {
				((WeaponButton)pButton).down = true;
				pButton.playDownSound(Minecraft.getInstance().getSoundManager());
			}
		}, new Button.OnTooltip() {
			@Override
			public void onTooltip(Button pButton, PoseStack pPoseStack, int pMouseX, int pMouseY) {
				Minecraft minecraft = Minecraft.getInstance();
				Font font = minecraft.font;
				drawString(pPoseStack, font, pMessage, pMouseX, pMouseY, 0xFFFFFF);
				pButton.blitOutlineBlack(font.width(pMessage.getString()), font.lineHeight, (i, j) -> {});
			}
		});
		this.x = pX;
		this.y = pY;
		this.u = u;
		this.v = v;
	}
	
	@Override
	public void onRelease(double pMouseX, double pMouseY) {
		this.down = false;
	}
	
	@Override
	public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		if(this.isHoveredOrFocused()) {
			this.renderToolTip(pPoseStack, pMouseX, pMouseY);
		}
		Minecraft minecraft = Minecraft.getInstance();
		//Font font = minecraft.font;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURES);
		RenderSystem.enableBlend();
	    RenderSystem.defaultBlendFunc();
	    RenderSystem.enableDepthTest();
	    if(this.isHovered) {
	    	this.blit(pPoseStack, 21, 166, x, y, 20, 20);
	    }else if(this.down) {
	    	this.blit(pPoseStack, 42, 166, x, y, 20, 20);
	    }else {
	    	this.blit(pPoseStack, 0, 166, x, y, 20, 20);
	    }
	    this.blit(pPoseStack, x, y, u+2, v+2, 16, 16);
	    this.renderBg(pPoseStack, minecraft, pMouseX, pMouseY);
	    //int j = getFGColor();
	    //drawCenteredString(pPoseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
	}

}
