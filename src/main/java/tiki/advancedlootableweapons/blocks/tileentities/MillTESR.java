package tiki.advancedlootableweapons.blocks.tileentities;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MillTESR extends TileEntitySpecialRenderer<TileEntityMill> {
	
	public MillTESR() {}
	
	@Override
	public void render(TileEntityMill te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        
		Tessellator tes = Tessellator.getInstance();
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableBlend();
		GlStateManager.disableCull();
		
		if(Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		}else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}
		
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		BlockRendererDispatcher brd = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel model = brd.getModelForState(state);
		
		BufferBuilder buffer = tes.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		
		buffer.setTranslation(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
		brd.getBlockModelRenderer().renderModel(te.getWorld(), model, state, te.getPos(), buffer, false);
		buffer.setTranslation(0, 0, 0);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		GlStateManager.translate(0.5, 0.5, 0.5);
//		Vec3d rotation = new Vec3d(0.0, 90.0, 0.0);
//		GlStateManager.rotate((float)rotation.x, 1, 0, 0);
//		GlStateManager.rotate((float)rotation.y, 0, 1, 0);
//		GlStateManager.rotate((float)rotation.z, 0, 0, 1);
		
		GlStateManager.rotate((float)te.getRotation(), 0, 1, 0);
		GlStateManager.translate(-0.5, -0.5, -0.5);
		
		tes.draw();
		GlStateManager.popMatrix();
		RenderHelper.enableStandardItemLighting();
		
		//Used for the old mill test
//        this.bindTexture(TEXTURE);
//        
//        GlStateManager.pushMatrix();
//        
//        if (destroyStage < 0) {
//            GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
//        }
//        
//        GlStateManager.translate(x + 1, y + 1.5, z + 1);
//        GlStateManager.rotate(180F, 1.0F, 0.0F, 1.0F);
//        
//        mill.axis.rotateAngleY += (1F / 40F * ((float)Math.PI / 2F));
//        
//        mill.stone_wheel.rotateAngleZ += (1F / 32F * ((float)Math.PI / 2F));
//        
//        mill.renderAll();
//        GlStateManager.popMatrix();
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
	}
}
