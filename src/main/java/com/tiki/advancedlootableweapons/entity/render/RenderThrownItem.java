package com.tiki.advancedlootableweapons.entity.render;

import org.lwjgl.opengl.GL11;

import com.tiki.advancedlootableweapons.entity.EntitySpear;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderThrownItem<T extends Entity> extends Render<T>{
	
	private float scale = 0.5F;
	private ModelBase model;
	private int rotation;
	private ResourceLocation textureLocation;

	protected RenderThrownItem(RenderManager renderManager, ModelBase model, float scale, int rotation, ResourceLocation texture) {
		this(renderManager, model, rotation);
		this.scale = scale;
		this.textureLocation = texture;
	}
	
	protected RenderThrownItem(RenderManager renderManager, ModelBase model, int rotation) {
		super(renderManager);
		this.model = model;
		this.rotation = rotation;

	}
	
	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
		if(entity instanceof EntitySpear) {
			GlStateManager.pushMatrix();
			
			int color = ((EntitySpear)entity).getColorData();
			GL11.glColor4f( ((color >> 16) & 0xFF) / 255F, ((color >> 8) & 0xFF) / 255F, ((color) & 0xFF) / 255F, ((color >> 24) & 0xFF) / 255F);
			super.doRender(entity, x, y, z, yaw, partialTicks);
			renderEntityModel(entity, x, y, z, yaw, partialTicks);
			GlStateManager.popMatrix();
		}else {
			super.doRender(entity, x, y, z, yaw, partialTicks);
			renderEntityModel(entity, x, y, z, yaw, partialTicks);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return this.textureLocation;
	}
	
	public void renderEntityModel(T entity, double x, double y, double z, float yaw, float partialTicks) {
		GL11.glPushMatrix();
		bindTexture(getEntityTexture(entity));
		GL11.glTranslated(x, y, z);
		GL11.glScalef(this.scale, this.scale, this.scale);
		GL11.glRotated(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0.0F, -1.1F, 0.0F);
		
		model.render(entity, (float)x, (float)y, (float)z, yaw + rotation, partialTicks, 0.0475F);
		GL11.glPopMatrix();
	}

}
