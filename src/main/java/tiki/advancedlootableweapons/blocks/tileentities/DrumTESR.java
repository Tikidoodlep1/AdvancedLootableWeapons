package tiki.advancedlootableweapons.blocks.tileentities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.init.BlockInit;

@SideOnly(Side.CLIENT)
public class DrumTESR extends TileEntitySpecialRenderer<TileEntityDrum> {
    
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
	}
	
}
