package com.tiki.advancedlootableweapons.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpear extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer head_r1;

	public ModelSpear() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 4, 4, -32.0F, -2.0F, 0.0F, 32, 2, 2, 0.0F, false));

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, -0.7854F, 0.0F);
		head_r1.cubeList.add(new ModelBox(head_r1, 4, 8, 0.0F, -1.5F, -0.6F, 2, 1, 2, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}