package tiki.advancedlootableweapons.util;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import tiki.advancedlootableweapons.Alw;

public class RenderHelper {
	public static final double pixelSize = 0.0625d;
	
	public static void addQuadToBuffer(@Nonnull BufferBuilder buffer, float fromX, float fromY, float fromZ, float toX, float toY, float toZ, float uMin, float uMax, float vMin, float vMax, TextureAtlasSprite texture, int skylight, int blocklight, EnumFacing facing) {
		Alw.logger.info("Passed UV: u = {} -> {}, v = {} -> {}", uMin, uMax, vMin, vMax);
		float u0 = texture.getMinU() * uMin * (texture.getMaxU() - texture.getMinU());//texture.getInterpolatedU(uMin / pixelSize);
		float v0 = texture.getMinV() * vMin * (texture.getMaxV() - texture.getMinV());//texture.getInterpolatedV(vMin / pixelSize);
		float u1 = texture.getMinU() * uMax * (texture.getMaxU() - texture.getMinU());//texture.getInterpolatedU(uMax / pixelSize);
		float v1 = texture.getMinV() * vMax * (texture.getMaxV() - texture.getMinV());//texture.getInterpolatedV(vMax / pixelSize);
		
		/*
		 * A = (X1, Y1, Z1)
		 * B = (X2, Y1, Z1)
		 * C = (X2, Y1, Z2)
		 * D = (X1, Y1, Z2)
		 * 
		 * FACES MUST BE RENDERED IN THIS ORDER TO PREVENT CULLING THEM ACCIDENTALLY
		 * FOR UV:
		 * 
		 * A (X1,Z1) -> (u0, v0)  u0 = sprite.getMinU();  u1 = sprite.getMaxU();
		 * B (X2,Z1) -> (u1, v0)  v0 = sprite.getMinV();  v1 = sprite.getMaxV();
		 * C (X2,Z2) -> (u1, v1)
		 * D (X1,Z2) -> (u0, v1)
		 */
		//Alw.logger.info("From -> To {} {} {} -> {} {} {}, uv: u {} -> {}, v {} -> {}", fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/, toX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/, u0, u1, v0, v1);
		switch(facing) {
			case DOWN:
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
			case UP:
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
			case NORTH: // -Z
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
			case EAST: // +X
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
			case SOUTH: // +Z
				buffer
					.pos(toX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(toX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
			case WEST: // -X
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, fromY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v1)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, fromZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u1, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				buffer
					.pos(fromX /*/ pixelSize*/, toY /*/ pixelSize*/, toZ /*/ pixelSize*/)
					.color(1f, 1f, 1f, 1f)
					.tex(u0, v0)
					.lightmap(skylight, blocklight)
					.endVertex();
				break;
		}
	}
}
