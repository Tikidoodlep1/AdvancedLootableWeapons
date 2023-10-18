package com.tiki.advancedlootableweapons.blocks.tileentities;

import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DrumTESR extends TileEntitySpecialRenderer<TileEntityDrum> {
    
	@Override
	public void render(final TileEntityDrum te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		final ItemStack input = te.getStackInSlot(TileEntityDrum.INPUT_SLOT);
		final ItemStack output = te.getStackInSlot(TileEntityDrum.OUTPUT_SLOT);
		final World world = te.getWorld();
		EnumFacing facing = EnumFacing.NORTH;
		for(EnumFacing dir : EnumFacing.HORIZONTALS) {
			if(world.getBlockState(te.getPos().offset(dir)) == Blocks.AIR.getDefaultState() && world.getBlockState(te.getPos().offset(dir).offset(EnumFacing.DOWN)).isTopSolid()) {
				facing = dir;
				break;
			}
		}
		
		if(!input.isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5D, y + 0.4D, z + 0.5D);
			GlStateManager.rotate(-90.0F, 1F, 0F, 0F);
			GlStateManager.scale(0.9D, 0.9D, 0.9D);
			GlStateManager.disableLighting();
			RenderHelper.enableStandardItemLighting();
			Minecraft.getMinecraft().getRenderItem().renderItem(input, ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
		
		if(!output.isEmpty()) {
			GlStateManager.pushMatrix();
			switch(facing) {
			case EAST:
				GlStateManager.translate(x + 1.175D, y + 0.425D, z + 0.5D);
				GlStateManager.rotate(90.0F, 0F, 1F, 0F);
				GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
				break;
			case NORTH:
				GlStateManager.translate(x + 0.5D, y + 0.4D, z - 0.175D);
				GlStateManager.rotate(22.5F, 1F, 0F, 0F);
				break;
			case SOUTH:
				GlStateManager.translate(x + 0.5D, y + 0.4D, z + 1.175D);
				GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
				break;
			case WEST:
				GlStateManager.translate(x - 0.175D, y + 0.425D, z + 0.5D);
				GlStateManager.rotate(-90.0F, 0F, 1F, 0F);
				GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
				break;
			default:
				GlStateManager.translate(x + 0.5D, y + 0.4D, z - 0.175D);
				GlStateManager.rotate(22.5F, 1F, 0F, 0F);
				break;
			}
			GlStateManager.disableLighting();
			RenderHelper.enableStandardItemLighting();
			Minecraft.getMinecraft().getRenderItem().renderItem(output, ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
		
		final FluidStack fluid = te.getTank().getFluid();
		if(fluid != null) {
			ItemStack item = new ItemStack(fluid.getFluid().getBlock());
			if(fluid.getFluid() == FluidRegistry.WATER) {
				item = new ItemStack(BlockInit.water_block_display);
			}else if(fluid.getFluid() == FluidRegistry.LAVA) {
				item = new ItemStack(BlockInit.lava_block_display);
			}
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5D, y + 0.5D, z + 0.5D);
			GlStateManager.scale(1.5D, 0.75D, 1.5D);
			GlStateManager.disableLighting();
			RenderHelper.enableStandardItemLighting();
			Minecraft.getMinecraft().getRenderItem().renderItem(item, ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}
	
//	@Override
//	public void renderTileEntityFast(final TileEntityDrum te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float partial, final BufferBuilder buffer) {
//		if(te.isInvalid()) {
//			return;
//		}
//		
//		TextureMap texMap = Minecraft.getMinecraft().getTextureMapBlocks();
//		FluidStack fluid = te.getTank().getFluid();
//		if(fluid != null) {
//			buffer.setTranslation(x, y, z);
//			final World world = te.getWorld();
//			final int fluidLightLevel = fluid.getFluid().getLuminosity();
//			final int combinedLightLevel = world.getCombinedLight(te.getPos(), fluidLightLevel);
//			final int skyLight = (combinedLightLevel >> 16) & 0xFFFF;
//			final int blockLight = (combinedLightLevel >> 0) & 0xFFFF;
//			
//			final double fluidHeight = 0.625D;
//			final ResourceLocation fluidTextureLoc = fluid.getFluid().getStill(fluid);
//			final TextureAtlasSprite fluidSprite = texMap.getAtlasSprite(fluidTextureLoc.toString());
//			final int color = fluid.getFluid().getColor();
//			final int r = ((color >> 16) & 0xFF);
//			final int g = ((color >> 8) & 0xFF);
//			final int b = ((color >> 0) & 0xFF);
//			final int a = ((color >> 24) & 0xFF);
//			
//			final double uMin = fluidSprite.getMinU();
//			final double vMin = fluidSprite.getMinV();
//			final double uMax = fluidSprite.getMaxU();
//			final double vMax = fluidSprite.getMaxV();
//			
//			final double uMid = (uMax + uMin) / 2;
//			final double vMid = (vMax + vMin) / 2;
//			
//			buffer.pos(0.5, fluidHeight, 0.5).color(r, g, b, a).tex(uMid, vMid).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(1, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(1, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(0, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(skyLight, blockLight).endVertex();
//			
//			buffer.pos(0, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(1, fluidHeight, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(0.5, fluidHeight, 0.5).color(r, g, b, a).tex(uMid, vMid).lightmap(skyLight, blockLight).endVertex();
//			buffer.pos(0, fluidHeight, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(skyLight, blockLight).endVertex();
//			
//			buffer.setTranslation(0, 0, 0);
//		}
//	}
}
