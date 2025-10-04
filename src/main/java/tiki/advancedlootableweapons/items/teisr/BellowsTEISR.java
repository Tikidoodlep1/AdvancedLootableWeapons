package tiki.advancedlootableweapons.items.teisr;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import tiki.advancedlootableweapons.blocks.tileentities.BellowsTESR;

public class BellowsTEISR extends TileEntityItemStackRenderer {
	
	public BellowsTEISR() {}
	
	@Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		//"rotation": [20, 20, 0],
		//"translation": [0, 5, 0]
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        
        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();

        if (Minecraft.isAmbientOcclusionEnabled())
        {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        }
        else
        {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        
        //GlStateManager.rotate(20, 1, 1, 0);

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        //buffer.setTranslation(-0.5, 0.5, 0);
        BellowsTESR.renderItemStack(stack, buffer);
        buffer.setTranslation(0, 0, 0);

        tessellator.draw();

        GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
	}
}
