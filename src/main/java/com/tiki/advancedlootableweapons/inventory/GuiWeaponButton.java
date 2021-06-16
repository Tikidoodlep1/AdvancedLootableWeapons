package com.tiki.advancedlootableweapons.inventory;

import java.util.Timer;
import java.util.TimerTask;

import com.tiki.advancedlootableweapons.ModInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiWeaponButton extends GuiButton{

	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/button.png");
	private int cooldown;
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		if(this.cooldown == 0 && this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
			this.cooldown = 1;
			this.createTimer();
			return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		}
        return false;
    }
	
	private void createTimer() {
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
        }
    }
}	
