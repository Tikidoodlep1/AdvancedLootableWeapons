package com.tiki.advancedlootableweapons.client.screens;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SimpleImageButton extends AbstractWidget {
    private static final ResourceLocation RECIPE_BOOK_LOCATION = new ResourceLocation("textures/gui/recipe_book.png");
    private static final Component MORE_RECIPES_TOOLTIP = new TranslatableComponent("gui.recipebook.moreRecipes");
    ButtonInfo collection;
    private float time;
    private float animationTime;

    public SimpleImageButton() {
        super(0, 0, 25, 25, TextComponent.EMPTY);
    }

    public void init(ButtonInfo buttonInfo) {
        this.collection = buttonInfo;

    }

    public void setPosition(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (!Screen.hasControlDown()) {
            this.time += pPartialTick;
        }

        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, RECIPE_BOOK_LOCATION);
        int i = 29;
      //  if (!this.collection.hasCraftable()) {
     //       i += 25;
       // }

        int j = 206;
    //    if (this.collection.getRecipes(this.book.isFiltering(this.menu)).size() > 1) {
    //        j += 25;
   //     }

        boolean flag = this.animationTime > 0.0F;
        PoseStack posestack = RenderSystem.getModelViewStack();
        if (flag) {
            float f = 1.0F + 0.1F * (float) Math.sin(this.animationTime / 15.0F * Math.PI);
            posestack.pushPose();
            posestack.translate(this.x + 8, this.y + 12, 0.0D);
            posestack.scale(f, f, 1.0F);
            posestack.translate(-(this.x + 8), -(this.y + 12), 0.0D);
            RenderSystem.applyModelViewMatrix();
            this.animationTime -= pPartialTick;
        }

        this.blit(pPoseStack, this.x, this.y, i, j, this.width, this.height);
        int k = 4;
        minecraft.getItemRenderer().renderAndDecorateFakeItem(collection.icon(), this.x + k, this.y + k);
        if (flag) {
            posestack.popPose();
            RenderSystem.applyModelViewMatrix();
        }

    }

    public List<Component> getTooltipText(Screen pScreen) {
        List<Component> list = Lists.newArrayList(MCVersion.translation(collection.desc()));

        return list;
    }

    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
        pNarrationElementOutput.add(NarratedElementType.TITLE, new TranslatableComponent("narration.recipe",
                MCVersion.translation(collection.desc())));

            pNarrationElementOutput.add(NarratedElementType.USAGE, new TranslatableComponent("narration.button.usage.hovered"));
    }

    public int getWidth() {
        return 25;
    }

    protected boolean isValidClickButton(int pButton) {
        return pButton == 0 || pButton == 1;
    }
}
