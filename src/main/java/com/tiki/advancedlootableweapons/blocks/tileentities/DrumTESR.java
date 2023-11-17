package com.tiki.advancedlootableweapons.blocks.tileentities;

import java.util.Random;

import javax.vecmath.Vector3f;

import com.tiki.advancedlootableweapons.handlers.SoundHandler;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DrumTESR extends TileEntitySpecialRenderer<TileEntityDrum> {
    
	private Random rand = new Random();
	private Vector3f bubbleColumn1 = new Vector3f(0.0f, 1.0f, 0.0f);
	private Vector3f bubbleColumn2 = new Vector3f(0.0f, 1.0f, 0.0f);
	private float speedFactor1 = 0.01f;
	private float speedFactor2 = 0.01f;
	private float bubbleOffset1 = 0.0f;
	private float bubbleOffset2 = 0.0f;
	private boolean canBubble2 = true;
	
	@Override
	public void render(final TileEntityDrum te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		final ItemStack input = te.getStackInSlot(TileEntityDrum.INPUT_SLOT);
		final ItemStack output = te.getStackInSlot(TileEntityDrum.OUTPUT_SLOT);
		final World world = te.getWorld();
		EnumFacing facing = EnumFacing.NORTH;
		for(EnumFacing dir : EnumFacing.HORIZONTALS) {
			if(world.getBlockState(te.getPos().offset(dir)) == Blocks.AIR.getDefaultState() && world.getBlockState(te.getPos().offset(dir).offset(EnumFacing.DOWN)).isSideSolid(world, new BlockPos(x, y, z), EnumFacing.UP)) {
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
				
		if(te.needsQuench) {
			renderQuench(world, te.getPos().getX(), te.getPos().getY() - 0.5, te.getPos().getZ(), te.getField(0));
		}else if(te.needsBubbles) {
			renderBubbles(world, te.getPos().getX(), te.getPos().getY() - 0.5, te.getPos().getZ());
		}
	}
	
	private void renderBubbles(World world, double x, double y, double z) {
		if(((float)bubbleColumn1.getY() + bubbleOffset1) >= 1.0f) {
			bubbleColumn1.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
			bubbleOffset1 = 0.0f;
			speedFactor1 = (rand.nextFloat() + 0.1f) / 50f;
		}
		world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x + bubbleColumn1.getX(), y + bubbleColumn1.getY() + bubbleOffset1, z + bubbleColumn1.getZ(), 0.0f, 0.0f, 0.0f, 1);
		bubbleOffset1 += speedFactor1;
		
		if(canBubble2 && rand.nextFloat() >= 0.9) {
			canBubble2 = false;
		}
		if(((float)bubbleColumn2.getY() + bubbleOffset2) >= 1.0) {
			bubbleColumn2.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
			bubbleOffset2 = 0.0f;
			speedFactor2 = (rand.nextFloat() + 0.1f) / 50f;
			canBubble2 = true;
		}
		if(!canBubble2) {
			world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x + bubbleColumn2.getX(), y + bubbleColumn2.getY() + bubbleOffset2, z + bubbleColumn2.getZ(), 0.0f, 0.0f, 0.0f, 1);
			bubbleOffset2 += speedFactor2;
		}
	}
	
	private void renderQuench(World world, double x, double y, double z, int progress) {
		if(progress % 2 == 0) {
			world.spawnParticle(EnumParticleTypes.SPIT, x + 0.5 + (rand.nextFloat() - 0.5), y + 0.85 + (rand.nextFloat() - 0.5), z + 0.5 + (rand.nextFloat() - 0.5), 0.0, 0.0, 0.01f, 1);
		}
		if(progress == 6) {
			world.playSound(x, y, z, SoundHandler.QUENCH, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
		}
	}
	
}
