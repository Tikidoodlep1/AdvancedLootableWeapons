package com.tiki.advancedlootableweapons.blocks.tileentities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class DrumTESR extends FastTESR<TileEntityDrum>{

	@Override
	public void renderTileEntityFast(final TileEntityDrum te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float partial, final BufferBuilder buffer) {
		if(te.isInvalid()) {
			return;
		}
		
		FluidStack fluid = te.getTank().getFluid();
		//System.out.println("Fluid: " + fluid);
		if(fluid != null) {
			buffer.setTranslation(x, y, z);
			final World world = te.getWorld();
			final int fluidLightLevel = fluid.getFluid().getLuminosity();
			final int combinedLightLevel = world.getCombinedLight(te.getPos(), fluidLightLevel);
			final int skyLight = (combinedLightLevel >> 16) & 0xFFFF;
			final int blockLight = (combinedLightLevel >> 0) & 0xFFFF;
			
			final double fluidHeight = 0.625D;
			final ResourceLocation fluidTextureLoc = fluid.getFluid().getStill(fluid);
			final TextureAtlasSprite fluidSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluidTextureLoc.toString());
			final int color = fluid.getFluid().getColor();
			final int r = ((color >> 16) & 0xFF);
			final int g = ((color >> 8) & 0xFF);
			final int b = ((color >> 0) & 0xFF);
			final int a = ((color >> 24) & 0xFF);
			
			final double uMin = fluidSprite.getMinU();
			final double vMin = fluidSprite.getMinV();
			final double uMax = fluidSprite.getMaxU();
			final double vMax = fluidSprite.getMaxV();
			
			final double uMid = (uMax + uMin) / 2;
			final double vMid = (vMax + vMin) / 2;
			
			buffer.pos(0.5, fluidHeight, 0.5).color(r, g, b, a).tex(uMid, vMid).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(1, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(1, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(0, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(skyLight, blockLight).endVertex();
			
			buffer.pos(0, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(1, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(0.5, fluidHeight, 0.5).color(r, g, b, a).tex(uMid, vMid).lightmap(skyLight, blockLight).endVertex();
			buffer.pos(0, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(skyLight, blockLight).endVertex();
			
			buffer.setTranslation(0, 0, 0);
		}
	}
}
