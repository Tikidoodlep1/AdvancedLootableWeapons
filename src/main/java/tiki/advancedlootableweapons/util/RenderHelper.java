package tiki.advancedlootableweapons.util;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedFace;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedVertex;

public class RenderHelper {
	public static final double pixelSize = 0.0625d;
	
	public static void addFaceToBuffer(@Nonnull BufferBuilder buffer, UnpackedFace face, TextureAtlasSprite texture, int skylight, int blocklight) {	    
		float u, v;
				
		for(UnpackedVertex vert : face.getVertices()) {
			u = texture.getInterpolatedU(vert.getU());
			v = texture.getInterpolatedV(vert.getV());
			
			buffer
			.pos(vert.getPos().getX(), vert.getPos().getY(), vert.getPos().getZ())
			.color(1f, 1f, 1f, 1f)
			.tex(u, v)
			.lightmap(skylight, blocklight)
			.endVertex();
		}
	}
	
	public static float mapfZeroToOne(float min, float max, float slide) {
		return (slide - min) / (max - min);
	}
	
	public static float mapf(float oldMin, float oldMax, float newMin, float newMax, float slide) {
		return newMin + ((slide - oldMin) / (oldMax - oldMin)) * (newMax - newMin);
	}
}
