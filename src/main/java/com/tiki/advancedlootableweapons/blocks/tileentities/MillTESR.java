package com.tiki.advancedlootableweapons.blocks.tileentities;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.model.ModelMill;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MillTESR extends TileEntitySpecialRenderer<TileEntityMill> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(ModInfo.ID + ":textures/blocks/mill_big.png");
	private final ModelMill mill = new ModelMill();
	
	public MillTESR() {
		
	}
	
	@Override
	public void render(TileEntityMill te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		//GlStateManager.enableDepth();
        //GlStateManager.depthFunc(515);
        //GlStateManager.depthMask(true);
        
        this.bindTexture(TEXTURE);
        
        GlStateManager.pushMatrix();
        //GlStateManager.enableRescaleNormal();
        
        if (destroyStage < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        }
        
        GlStateManager.translate(x + 1, y + 1.5, z + 1);
        GlStateManager.rotate(180F, 1.0F, 0.0F, 1.0F);
        
        mill.axis.rotateAngleY += (1F / 40F * ((float)Math.PI / 2F));
//        for(ModelRenderer mr : mill.axis.childModels) {
//        	mr.rotateAngleY += -(partialTicks / 4F * ((float)Math.PI / 2F));
//        }
        
        mill.stone_wheel.rotateAngleZ += (1F / 32F * ((float)Math.PI / 2F));
//        for(ModelRenderer mr : mill.stone_wheel.childModels) {
//        	mr.rotateAngleX += -(partialTicks / 4F * ((float)Math.PI / 2F));
//        }
        mill.renderAll();
        //GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
	}
}
