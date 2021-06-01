package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiWeaponButton extends GuiButton{

	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/button.png");
	protected static final ResourceLocation BUTTON_OVERLAY = new ResourceLocation(ModInfo.ID + ":textures/gui/overlay.png");
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		
	}
	
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            this.drawTexturedModalRect(this.x, this.y, 0, 0, 20, 20);
        }
    }
}	
