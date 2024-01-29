package com.tiki.advancedlootableweapons.client.screens;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class AnvilForgingPage {

    public static final int ITEMS_PER_PAGE = 20;
    protected static final ResourceLocation RECIPE_BOOK_LOCATION = new ResourceLocation("textures/gui/recipe_book.png");

    private final List<SimpleImageButton> buttons = Lists.newArrayListWithCapacity(20);
    private Minecraft minecraft;
    private List<ButtonInfo> recipeCollections = ImmutableList.of();

    @Nullable
    private SimpleImageButton hoveredButton;

    private StateSwitchingButton forwardButton;
    private StateSwitchingButton backButton;

    private int totalPages;
    private int currentPage;

    public AnvilForgingPage() {
        for(int i = 0; i < 20; ++i) {
            this.buttons.add(new SimpleImageButton());
        }

    }


    public void init(Minecraft pMinecraft, int pX, int pY) {
        this.minecraft = pMinecraft;

        for(int i = 0; i < this.buttons.size(); ++i) {
            this.buttons.get(i).setPosition(pX + 11 + 25 * (i % 5), pY + 31 + 25 * (i / 5));
        }

        this.forwardButton = new StateSwitchingButton(pX + 93, pY + 137, 12, 17, false);
        this.forwardButton.initTextureValues(1, 208, 13, 18, RECIPE_BOOK_LOCATION);
        this.backButton = new StateSwitchingButton(pX + 38, pY + 137, 12, 17, true);
        this.backButton.initTextureValues(1, 208, 13, 18, RECIPE_BOOK_LOCATION);
    }

    private void updateArrowButtons() {
        this.forwardButton.visible = this.totalPages > 1 && this.currentPage < this.totalPages - 1;
        this.backButton.visible = this.totalPages > 1 && this.currentPage > 0;
    }

    public void render(PoseStack pPoseStack, int p_100423_, int p_100424_, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.totalPages > 1) {
            String s = this.currentPage + 1 + "/" + this.totalPages;
            int i = this.minecraft.font.width(s);
            this.minecraft.font.draw(pPoseStack, s, (float)(p_100423_ - i / 2 + 73), (float)(p_100424_ + 141), -1);
        }

        this.hoveredButton = null;

        for(SimpleImageButton recipebutton : this.buttons) {
            recipebutton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
            if (recipebutton.visible && recipebutton.isHoveredOrFocused()) {
                this.hoveredButton = recipebutton;
            }
        }

        this.backButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.forwardButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void updateCollections(List<ButtonInfo> pRecipeCollections, boolean p_100438_) {
        this.recipeCollections = pRecipeCollections;
        this.totalPages = (int)Math.ceil((double)pRecipeCollections.size() / ITEMS_PER_PAGE);
        if (this.totalPages <= this.currentPage || p_100438_) {
            this.currentPage = 0;
        }

        this.updateButtonsForPage();
    }

    private void updateButtonsForPage() {
        int i = ITEMS_PER_PAGE * this.currentPage;

        for(int j = 0; j < this.buttons.size(); ++j) {
            SimpleImageButton recipebutton = this.buttons.get(j);
            if (i + j < this.recipeCollections.size()) {
                ButtonInfo recipecollection = this.recipeCollections.get(i + j);
                recipebutton.init(recipecollection);
                recipebutton.visible = true;
            } else {
                recipebutton.visible = false;
            }
        }

        this.updateArrowButtons();
    }

    public void renderTooltip(PoseStack pPoseStack, int pX, int pY) {
        if (this.minecraft.screen != null && this.hoveredButton != null) {
           this.minecraft.screen.renderComponentTooltip(pPoseStack, this.hoveredButton.getTooltipText(this.minecraft.screen), pX, pY,
                   this.hoveredButton.collection.icon());
        }
    }
}
