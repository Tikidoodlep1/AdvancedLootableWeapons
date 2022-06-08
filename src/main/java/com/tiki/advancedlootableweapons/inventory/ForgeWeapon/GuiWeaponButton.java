package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Timer;
import java.util.TimerTask;

import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiWeaponButton extends GuiButton{
	protected static final ResourceLocation OVERLAY = new ResourceLocation(ModInfo.ID + ":textures/gui/button_overlay.png");
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/button.png");
	private int cooldown, overlayX, overlayY;
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int overlayX, int overlayY) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.overlayX = overlayX;
		this.overlayY = overlayY;
		this.enabled = true;
		this.visible = true;
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		if(mouseX > 109) {
			return false;
		}
		
		if(this.cooldown == 0 && this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
			this.cooldown = 1;
			this.createTimer();
			return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		}
        return false;
    }
	
	private void createTimer() {
		System.out.println("Creating new timer");
		Timer t = new Timer("buttonCD");
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				cooldown = 0;
			}
		}, 100);
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            this.drawTexturedModalRect(this.x, this.y, 0, 0, 20, 20);
            mc.getTextureManager().bindTexture(OVERLAY);
            this.drawTexturedModalRect(this.x, this.y, overlayX, overlayY, 20, 20);
        }
    }
}	
