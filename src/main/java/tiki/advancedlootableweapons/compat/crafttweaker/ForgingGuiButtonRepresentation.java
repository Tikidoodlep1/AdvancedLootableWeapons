package tiki.advancedlootableweapons.compat.crafttweaker;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public class ForgingGuiButtonRepresentation {

	private final String name;
	private final int id;
	private int x, y, overlayX, overlayY;
	private final ResourceLocation overlay;
	private final boolean usesCustomOverlay;
	
	public ForgingGuiButtonRepresentation(int id, String name, int x, int y, @Nullable ResourceLocation overlay, int overlayX, int overlayY) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.overlay = overlay;
		this.usesCustomOverlay = overlay != null;
		this.overlayX = overlayX;
		this.overlayY = overlayY;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getOverlayX() {
		return overlayX;
	}
	
	public void setOverlayX(int overlayX) {
		this.overlayX = overlayX;
	}
	
	public int getOverlayY() {
		return overlayY;
	}
	
	public void setOverlayY(int overlayY) {
		this.overlayY = overlayY;
	}
	
	public ResourceLocation getOverlay() {
		return overlay;
	}
	
	public boolean usesCustomOverlay() {
		return usesCustomOverlay;
	}
	
	@Override
	public String toString() {
		return this.id + ", " + this.name + ", " + this.overlay;
	}
}
