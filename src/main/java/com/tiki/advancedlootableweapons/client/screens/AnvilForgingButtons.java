package com.tiki.advancedlootableweapons.client.screens;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AnvilForgingButtons extends GuiComponent implements GuiEventListener, Widget, NarratableEntry {

    private int width;
    private int height;
    private int xOffset;
    protected Minecraft minecraft;

    @Nullable
    private EditBox searchBox;
    private String lastSearch = "";
    protected final AnvilForgingPage anvilForgingPage = new AnvilForgingPage();


    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, 0.0D, 100.0D);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, AnvilForgingPage.RECIPE_BOOK_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = (this.width - 147) / 2 - this.xOffset;
        int j = (this.height - 166) / 2;
        this.blit(pPoseStack, i, j, 1, 1, 147, 166);

        this.anvilForgingPage.render(pPoseStack, i, j, pMouseX, pMouseY, pPartialTick);
        pPoseStack.popPose();
    }

    public void renderTooltip(PoseStack pPoseStack, int pRenderX, int pRenderY, int pMouseX, int pMouseY) {
        this.anvilForgingPage.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    public static final List<ButtonInfo> allButtons = new ArrayList<>();

    static {
        allButtons.add(new ButtonInfo(lookup("copper_dagger"),"Dagger"));
        allButtons.add(new ButtonInfo(lookup("copper_kabutowari"),"Kabutowari"));
        allButtons.add(new ButtonInfo(lookup( "copper_talwar"),"Talwar"));
        allButtons.add(new ButtonInfo(lookup( "copper_rapier"),"Rapier"));
        allButtons.add(new ButtonInfo(lookup("copper_mace"),"Mace"));
        allButtons.add(new ButtonInfo(lookup("copper_cleaver"),"Cleaver"));
        allButtons.add(new ButtonInfo(lookup( "copper_staff"),"Staff"));
        allButtons.add(new ButtonInfo(lookup("copper_longsword"),"Longsword"));
        allButtons.add(new ButtonInfo(lookup("copper_kodachi"),"Kodachi"));
        allButtons.add(new ButtonInfo(lookup("copper_nodachi"),"Nodachi"));
        allButtons.add(new ButtonInfo(lookup( "copper_battleaxe"),"Battleaxe"));
        allButtons.add(new ButtonInfo(lookup( "copper_zweihander"),"Zweihander"));
        allButtons.add(new ButtonInfo(lookup("copper_sabre"),"Sabre"));
        allButtons.add(new ButtonInfo(lookup("copper_makhaira"),"Makhaira"));
        allButtons.add(new ButtonInfo(lookup("copper_spear"),"Spear"));
        allButtons.add(new ButtonInfo(lookup( "copper_chain_link"),"Chain Link"));
        allButtons.add(new ButtonInfo(lookup("copper_armor_plate"),"Armor Plate"));
        allButtons.add(new ButtonInfo(lookup( "hot_tool_rod_2"),"Handle"));
        allButtons.add(new ButtonInfo(lookup("iron_forge_hammer"),"Forge Weapon"));
    }

    private static ItemStack lookup(String string) {
        return Registry.ITEM.get(new ResourceLocation(AdvancedLootableWeapons.MODID,string)).getDefaultInstance();
    }

    public int updateScreenPosition(int p_181402_, int p_181403_) {
        int i;
        i = 177 + (p_181402_ - p_181403_ - 200) / 2;

        return i;
    }
    private void updateCollections(boolean p_100383_) {
        List<ButtonInfo> list1 = Lists.newArrayList(allButtons);
        String s = this.searchBox.getValue();
       /* if (!s.isEmpty()) {
            ObjectSet<String> objectset = new ObjectLinkedOpenHashSet<>(this.minecraft.getSearchTree(SearchRegistry.RECIPE_COLLECTIONS).search(s.toLowerCase(Locale.ROOT)));
            list1.removeIf((p_100334_) -> {
                return !objectset.contains(p_100334_);
            });
        }*/


        this.anvilForgingPage.updateCollections(list1, p_100383_);
    }

    public void init(int pWidth, int pHeight, Minecraft pMinecraft) {
        this.minecraft = pMinecraft;
        this.width = pWidth;
        this.height = pHeight;
        xOffset = 86;

        int i = (this.width - 147) / 2 - this.xOffset;
        int j = (this.height - 166) / 2;


        String s = this.searchBox != null ? this.searchBox.getValue() : "";
        this.searchBox = new EditBox(this.minecraft.font, i + 25, j + 14, 80, 9 + 5, new TranslatableComponent("itemGroup.search"));
        this.searchBox.setMaxLength(50);
        this.searchBox.setBordered(false);
        this.searchBox.setVisible(true);
        this.searchBox.setTextColor(0xffffff);
        this.searchBox.setValue(s);


        this.anvilForgingPage.init(this.minecraft, i, j);
      //  this.anvilForgingPage.addListener(this);

        this.updateCollections(false);

        pMinecraft.keyboardHandler.setSendRepeatsToGui(true);
    }

    public void removed() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
    }


    @Override
    public NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
