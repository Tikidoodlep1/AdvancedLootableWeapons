package com.tiki.advancedlootableweapons.inventory.anvil_forging;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AnvilForgingScreen extends AbstractContainerScreen<AnvilForgingContainer> {

	static final ResourceLocation TEXTURE = new ResourceLocation(AdvancedLootableWeapons.MODID, "textures/gui/anvil_forging.png");
	private static final WeaponButton DAGGER = new WeaponButton(30, 40, 177, 1, new TextComponent("Dagger"));
	private static final WeaponButton KABUTOWARI = new WeaponButton(60, 40, 177, 1, new TextComponent("Kabutowari"));
	private static final WeaponButton TALWAR = new WeaponButton(90, 40, 177, 1, new TextComponent("Talwar"));
	private static final WeaponButton RAPIER = new WeaponButton(30, 70, 177, 1, new TextComponent("Rapier"));
	private static final WeaponButton MACE = new WeaponButton(60, 70, 177, 1, new TextComponent("Mace"));
	private static final WeaponButton CLEAVER = new WeaponButton(90, 70, 177, 1, new TextComponent("Cleaver"));
	private static final WeaponButton STAFF = new WeaponButton(30, 100, 177, 1, new TextComponent("Staff"));
	private static final WeaponButton LONGSWORD = new WeaponButton(60, 100, 177, 1, new TextComponent("Longsword"));
	private static final WeaponButton KODACHI = new WeaponButton(90, 100, 177, 1, new TextComponent("Kodachi"));
	private static final WeaponButton NODACHI = new WeaponButton(30, 130, 177, 1, new TextComponent("Nodachi"));
	private static final WeaponButton BATTLEAXE = new WeaponButton(60, 130, 177, 1, new TextComponent("Battleaxe"));
	private static final WeaponButton ZWEIHANDER = new WeaponButton(90, 130, 177, 1, new TextComponent("Zweihander"));
	private static final WeaponButton SABRE = new WeaponButton(30, 160, 177, 1, new TextComponent("Sabre"));
	private static final WeaponButton MAKHAIRA = new WeaponButton(60, 160, 177, 1, new TextComponent("Makhaira"));
	private static final WeaponButton SPEAR = new WeaponButton(90, 160, 177, 1, new TextComponent("Spear"));
	private static final WeaponButton CHAIN_LINK = new WeaponButton(30, 190, 177, 1, new TextComponent("Chain Link"));
	private static final WeaponButton ARMOR_PLATE = new WeaponButton(60, 190, 177, 1, new TextComponent("Armor Plate"));
	private static final WeaponButton TOOL_ROD = new WeaponButton(90, 190, 177, 1, new TextComponent("Tool Rod"));
	private static final WeaponButton FORGE = new WeaponButton(90, 220, 177, 1, new TextComponent("Forge"));
	
	public AnvilForgingScreen(AnvilForgingContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}
	
	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
		DAGGER.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		KABUTOWARI.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		TALWAR.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		RAPIER.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		MACE.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		CLEAVER.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		STAFF.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		LONGSWORD.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		KODACHI.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		NODACHI.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		BATTLEAXE.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		ZWEIHANDER.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		SABRE.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		MAKHAIRA.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		SPEAR.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		CHAIN_LINK.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		ARMOR_PLATE.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		TOOL_ROD.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
		FORGE.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}
	
	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
	}
}
