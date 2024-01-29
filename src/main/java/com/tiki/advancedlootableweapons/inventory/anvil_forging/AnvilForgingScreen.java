package com.tiki.advancedlootableweapons.inventory.anvil_forging;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;

import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AnvilForgingScreen extends AbstractContainerScreen<AnvilForgingContainer> {

	static final ResourceLocation TEXTURE = new ResourceLocation(AdvancedLootableWeapons.MODID, "textures/gui/anvil_forging.png");

	int uOffset = 176;
	private final ItemButton DAGGER = makeButton(30, 40,"copper_dagger");
	private final ItemButton KABUTOWARI = makeButton(60, 40, "copper_kabutowari");
	private final ItemButton TALWAR = makeButton(90, 40, "copper_talwar");
	private final ItemButton RAPIER = makeButton(30, 70, "copper_rapier");
	private final ItemButton MACE = makeButton(60, 70, "copper_mace");
	private final ItemButton CLEAVER = makeButton(90, 70, "copper_cleaver");
	private final ItemButton STAFF = makeButton(30, 100, "copper_staff");
	private final ItemButton LONGSWORD = makeButton(60, 100, "copper_longsword");
	private final ItemButton KODACHI = makeButton(90, 100, "copper_kodachi");
	private final ItemButton NODACHI = makeButton(30, 130, "copper_nodachi");
	private final ItemButton BATTLEAXE = makeButton(60, 130, "copper_battleaxe");
	private final ItemButton ZWEIHANDER = makeButton(90, 130, "copper_zweihander");
	private final ItemButton SABRE = makeButton(30, 160, "copper_sabre");
	private final ItemButton MAKHAIRA = makeButton(60, 160, "copper_makhaira");
	private final ItemButton SPEAR = makeButton(90, 160, "copper_spear");
	private final ItemButton CHAIN_LINK = makeButton(30, 190, "copper_chain_link");
	private final ItemButton ARMOR_PLATE = makeButton(60, 190, "copper_armor_plate");
	private final ItemButton TOOL_ROD = makeButton(90, 190, "hot_tool_rod");
	private final ItemButton FORGE = makeButton(90, 220, "copper_forge");

	int index = 0;
	private ItemButton makeButton(int x, int y, String string) {
		Item item = lookup(string);
		ItemStack stack = item.getDefaultInstance();
		Component component = stack.getHoverName();

		Button.OnTooltip tooltip = (pButton, pPoseStack, pMouseX, pMouseY) -> renderTooltip(pPoseStack,component, x, y);
		return new ItemButton(x,y,20,20, MCVersion.empty(), pButton -> {},tooltip, stack);
	}

	private static Item lookup(String string) {
		return Registry.ITEM.get(new ResourceLocation(AdvancedLootableWeapons.MODID,string));
	}

	public AnvilForgingScreen(AnvilForgingContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void init() {
		super.init();

		addRenderableWidget(DAGGER);
		addRenderableWidget(KABUTOWARI);
		addRenderableWidget(TALWAR);
		addRenderableWidget(RAPIER);
		addRenderableWidget(MACE);
		addRenderableWidget(CLEAVER);
		addRenderableWidget(STAFF);
		addRenderableWidget(LONGSWORD);
		addRenderableWidget(KODACHI);
		addRenderableWidget(NODACHI);
		addRenderableWidget(BATTLEAXE);
		addRenderableWidget(ZWEIHANDER);
		addRenderableWidget(SABRE);
		addRenderableWidget(MAKHAIRA);
		addRenderableWidget(SPEAR);
		addRenderableWidget(CHAIN_LINK);
		addRenderableWidget(ARMOR_PLATE);
		addRenderableWidget(TOOL_ROD);
		addRenderableWidget(FORGE);
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
	}
}
