package com.tiki.advancedlootableweapons.compat.crafttweaker;

import java.util.HashSet;
import java.util.Set;
import org.lwjgl.util.Point;

import net.minecraft.util.ResourceLocation;

public class ForgingGuiRepresentation {

	public final Set<Point> slots = new HashSet<Point>();
	public final ResourceLocation textureLocation;
	
	public ForgingGuiRepresentation(ResourceLocation texture, int[] inputs, int[] output) {
		this.textureLocation = texture;
		this.slots.add(new Point(inputs[0], inputs[1]));
		this.slots.add(new Point(inputs[2], inputs[3]));
		this.slots.add(new Point(output[0], output[1]));
	}
	
	public Set<Point> getSlots() {
		return slots;
	}

	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}
}
