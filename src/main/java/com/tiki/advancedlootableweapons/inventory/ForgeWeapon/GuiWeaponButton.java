package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Timer;
import java.util.TimerTask;

import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiWeaponButton extends GuiButton {
	protected static final ResourceLocation OVERLAY = new ResourceLocation(ModInfo.ID + ":textures/gui/button_overlay.png");
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/button.png");
	private int cooldown, overlayX, overlayY;
	private boolean pressed;
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int overlayX, int overlayY) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.overlayX = overlayX;
		this.overlayY = overlayY;
		this.enabled = true;
		this.visible = true;
		this.pressed = false;
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		if(mouseX > 109) {
			return false;
		}
		
		if(this.cooldown == 0 && this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
			this.cooldown = 1;
			this.pressed = true;
			this.createTimer();
			return true;
		}
        return false;
    }
	
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		super.mouseReleased(mouseX, mouseY);
		this.pressed = false;
	}
	
	private void createTimer() {
		Timer t = new Timer("ALWCoolDowns");
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				cooldown = 0;
			}
		}, 500);
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
        	GlStateManager.pushMatrix();
        	//GlStateManager.enableDepth();
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            if(this.pressed) {
            	this.drawTexturedModalRect(this.x, this.y, 21, 0, 20, 20);
            }else {
            	this.drawTexturedModalRect(this.x, this.y, 0, 0, 20, 20);
            }
            
            mc.getTextureManager().bindTexture(OVERLAY);
            this.drawTexturedModalRect(this.x, this.y, overlayX, overlayY, 20, 20);
            //GlStateManager.disableDepth();
            GlStateManager.popMatrix();
            
        }
    }
}	
