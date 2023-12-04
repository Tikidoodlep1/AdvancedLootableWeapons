package tiki.rotn.advancedlootableweapons.compat.crafttweaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.util.ResourceLocation;

public class CrTForgingGuiRepresentation {

	public final HashSet<Point> slots;
	public final List<ForgingGuiButtonRepresentation> buttons = new ArrayList<ForgingGuiButtonRepresentation>();
	public final ResourceLocation textureLocation;
	public final ResourceLocation block;
	
	public CrTForgingGuiRepresentation(ResourceLocation texture, HashSet<Point> slots, ResourceLocation block) {
		this.textureLocation = texture;
		this.slots = slots;
		this.block = block;
	}
	
	public HashSet<Point> getSlots() {
		return slots;
	}

	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}
}
