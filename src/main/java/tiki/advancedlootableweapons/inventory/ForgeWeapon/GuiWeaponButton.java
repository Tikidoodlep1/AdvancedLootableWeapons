package tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.compat.crafttweaker.ForgingGuiButtonRepresentation;

public class GuiWeaponButton extends GuiButton {
	//We use two texture locations here - One for the texture of the button itself, and one for the texture of the overlay file.
	protected static final ResourceLocation OVERLAY = new ResourceLocation(ModInfo.ID + ":textures/gui/button_overlay.png");
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/button.png");
	private int cooldown, overlayX, overlayY, textureX, textureY, pressedX, pressedY;
	private boolean pressed;
	private final String name;
	//This is a helper for CraftTweaker Comapatability that contains all of the information the container and GUI would need.
	private final ForgingGuiButtonRepresentation CUSTOM_TEXTURE;
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int overlayX, int overlayY) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.name = buttonText;
		this.overlayX = overlayX;
		this.overlayY = overlayY;
		this.textureX = 0;
		this.textureY = 0;
		this.pressedX = 23;
		this.pressedY = 0;
		this.enabled = true;
		this.visible = true;
		this.pressed = false;
		this.CUSTOM_TEXTURE = null;
	}
	
	public GuiWeaponButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int overlayX, int overlayY, int textureX, int textureY, int pressedX, int pressedY) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.name = buttonText;
		this.overlayX = overlayX;
		this.overlayY = overlayY;
		this.textureX = textureX;
		this.textureY = textureY;
		this.pressedX = pressedX;
		this.pressedY = pressedY;
		this.enabled = true;
		this.visible = true;
		this.pressed = false;
		this.CUSTOM_TEXTURE = null;
	}
	
	public GuiWeaponButton(int buttonId, int widthIn, int heightIn, ForgingGuiButtonRepresentation rep) {
		super(buttonId, rep.getX(), rep.getY(), widthIn, heightIn, rep.getName());
		this.name = rep.getName();
		this.overlayX = rep.getOverlayX();
		this.overlayY = rep.getOverlayY();
		this.textureX = 0;
		this.textureY = 0;
		this.pressedX = 23;
		this.pressedY = 0;
		this.enabled = true;
		this.visible = true;
		this.pressed = false;
		this.CUSTOM_TEXTURE = rep;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		if(mouseX > 130) {
			return false;
		}
		//When pressed, create a 0.5s cooldown timer and set it to pressed
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
        	//Draw the button background
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            if(this.pressed) {
            	this.drawTexturedModalRect(this.x, this.y, this.pressedX, this.pressedY, this.width, this.height);
            }else {
            	this.drawTexturedModalRect(this.x, this.y, this.textureX, this.textureY, this.width, this.height);
            }
            
            //If it's made with CraftTweaker, get the texture path. Otherwise, get the default overlay image
            if(this.CUSTOM_TEXTURE == null || !this.CUSTOM_TEXTURE.usesCustomOverlay()) {
            	mc.getTextureManager().bindTexture(OVERLAY);
            }else {
            	mc.getTextureManager().bindTexture(this.CUSTOM_TEXTURE.getOverlay());
            }
            
            //width -  1 to account for shading, - 20 to account for overlay width, /2 to center overlay
            this.drawTexturedModalRect(this.x + ((this.width - 1 - 20) / 2), this.y + ((this.height - 1 - 20) / 2), overlayX, overlayY, 20, 20);
            GlStateManager.popMatrix();
            
            GL11.glPushMatrix();
            //Scale text to 55% of the normal size, then draw the button name.
            GL11.glScalef(0.55f, 0.55f, 0.55f);
	        this.drawCenteredString(mc.fontRenderer, this.displayString, (int)((this.x + (this.getButtonWidth() / 2)) / 0.55), (int)((this.y - 3) / 0.55), Color.WHITE.getRGB());
	        GL11.glPopMatrix();
        }
    }
	
	@Override
	public String toString() {
		return this.id + ", " + this.name + ", " + this.CUSTOM_TEXTURE;
	}
}	
