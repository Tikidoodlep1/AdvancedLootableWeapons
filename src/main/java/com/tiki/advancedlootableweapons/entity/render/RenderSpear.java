package com.tiki.advancedlootableweapons.entity.render;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.entity.models.ModelSpear;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSpear extends RenderThrownItem<EntitySpear>{
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/entity/spear.png");
	
	public RenderSpear(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSpear(), 1.0F, 180, TEXTURES);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpear entity) {
		return TEXTURES;
	}
}
