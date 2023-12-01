package tiki.rotn.advancedlootableweapons.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

@SideOnly(Side.CLIENT)
public class ModelMill extends ModelBase {
	public final ModelRenderer bb_main;
	private final ModelRenderer grinding_stone_corners;
	private final ModelRenderer grinding_stone_corners_r1;
	private final ModelRenderer grinding_stone_corners_r2;
	private final ModelRenderer grinding_stone_corners_r3;
	private final ModelRenderer grinding_stone_corners_r4;
	public final ModelRenderer axis;
	public final ModelRenderer stone_wheel;
	public final ModelRenderer stone_wheel_r1;
	public final ModelRenderer stone_wheel_r2;
	public final ModelRenderer stone_wheel_r3;
	public final ModelRenderer stone_wheel_r4;
	public final ModelRenderer stone_wheel_r5;
	public final ModelRenderer stone_wheel_r6;
	public final ModelRenderer stone_wheel_r7;

	public ModelMill() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 33, 0, -16.0F, -8.0F, -10.0F, 32, 8, 20, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 48, -10.0F, -8.0F, 10.0F, 20, 8, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 42, -10.0F, -8.0F, -16.0F, 20, 8, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 0, -3.0F, -10.0F, -3.0F, 6, 2, 6, 0.0F, false));

		grinding_stone_corners = new ModelRenderer(this);
		grinding_stone_corners.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		grinding_stone_corners_r1 = new ModelRenderer(this);
		grinding_stone_corners_r1.setRotationPoint(11.2374F, -3.999F, -11.2284F);
		grinding_stone_corners.addChild(grinding_stone_corners_r1);
		setRotationAngle(grinding_stone_corners_r1, 0.0F, 2.3562F, 0.0F);
		grinding_stone_corners_r1.cubeList.add(new ModelBox(grinding_stone_corners_r1, 0, 40, -4.25F, -4.0001F, -2.5F, 8, 8, 5, -0.0025F, false));

		grinding_stone_corners_r2 = new ModelRenderer(this);
		grinding_stone_corners_r2.setRotationPoint(-11.2284F, -3.999F, -11.2374F);
		grinding_stone_corners.addChild(grinding_stone_corners_r2);
		setRotationAngle(grinding_stone_corners_r2, 0.0F, -2.3562F, 0.0F);
		grinding_stone_corners_r2.cubeList.add(new ModelBox(grinding_stone_corners_r2, 0, 40, -4.25F, -4.0001F, -2.5F, 8, 8, 5, -0.0025F, false));

		grinding_stone_corners_r3 = new ModelRenderer(this);
		grinding_stone_corners_r3.setRotationPoint(-11.2374F, -3.999F, 11.2284F);
		grinding_stone_corners.addChild(grinding_stone_corners_r3);
		setRotationAngle(grinding_stone_corners_r3, 0.0F, -0.7854F, 0.0F);
		grinding_stone_corners_r3.cubeList.add(new ModelBox(grinding_stone_corners_r3, 0, 40, -4.25F, -4.0001F, -2.5F, 8, 8, 5, -0.0025F, false));

		grinding_stone_corners_r4 = new ModelRenderer(this);
		grinding_stone_corners_r4.setRotationPoint(13.1716F, -1.0F, 11.4142F);
		grinding_stone_corners.addChild(grinding_stone_corners_r4);
		setRotationAngle(grinding_stone_corners_r4, 0.0F, 0.7854F, 0.0F);
		grinding_stone_corners_r4.cubeList.add(new ModelBox(grinding_stone_corners_r4, 0, 40, -5.5F, -6.9991F, -4.0F, 8, 8, 5, -0.0025F, false));

		axis = new ModelRenderer(this);
		axis.setRotationPoint(0.0F, 14.0F, 0.0F);
		axis.cubeList.add(new ModelBox(axis, 52, 24, -2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F, false));
		axis.cubeList.add(new ModelBox(axis, 0, 42, -1.0F, -8.0F, -4.0F, 2, 2, 20, 0.0F, false));

		stone_wheel = new ModelRenderer(this);
		stone_wheel.setRotationPoint(0.0F, -7.0F, 10.5F);
		axis.addChild(stone_wheel);
		stone_wheel.cubeList.add(new ModelBox(stone_wheel, 0, 0, -5.0F, -5.0F, -4.5F, 10, 10, 6, 0.0F, false));
		stone_wheel.cubeList.add(new ModelBox(stone_wheel, 0, 0, -9.0F, -4.0F, -4.5F, 4, 8, 6, 0.0F, true));

		stone_wheel_r1 = new ModelRenderer(this);
		stone_wheel_r1.setRotationPoint(6.4544F, -5.8385F, -1.5F);
		stone_wheel.addChild(stone_wheel_r1);
		setRotationAngle(stone_wheel_r1, 0.0F, 0.0F, 2.3562F);
		stone_wheel_r1.cubeList.add(new ModelBox(stone_wheel_r1, 0, 0, -0.5F, -3.1F, -2.999F, 3, 7, 5, 0.0F, true));

		stone_wheel_r2 = new ModelRenderer(this);
		stone_wheel_r2.setRotationPoint(5.8385F, 6.4544F, -1.5F);
		stone_wheel.addChild(stone_wheel_r2);
		setRotationAngle(stone_wheel_r2, 0.0F, 0.0F, -2.3562F);
		stone_wheel_r2.cubeList.add(new ModelBox(stone_wheel_r2, 0, 0, -0.5F, -3.1F, -2.999F, 3, 7, 5, 0.0F, true));

		stone_wheel_r3 = new ModelRenderer(this);
		stone_wheel_r3.setRotationPoint(-6.4544F, 5.8385F, -1.5F);
		stone_wheel.addChild(stone_wheel_r3);
		setRotationAngle(stone_wheel_r3, 0.0F, 0.0F, -0.7854F);
		stone_wheel_r3.cubeList.add(new ModelBox(stone_wheel_r3, 0, 0, -0.5F, -3.1F, -2.999F, 3, 7, 5, 0.0F, true));

		stone_wheel_r4 = new ModelRenderer(this);
		stone_wheel_r4.setRotationPoint(-5.818F, -6.4749F, -1.5F);
		stone_wheel.addChild(stone_wheel_r4);
		setRotationAngle(stone_wheel_r4, 0.0F, 0.0F, 0.7854F);
		stone_wheel_r4.cubeList.add(new ModelBox(stone_wheel_r4, 0, 0, -0.5F, -3.1F, -2.999F, 3, 7, 5, 0.0F, true));

		stone_wheel_r5 = new ModelRenderer(this);
		stone_wheel_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		stone_wheel.addChild(stone_wheel_r5);
		setRotationAngle(stone_wheel_r5, 0.0F, 0.0F, 1.5708F);
		stone_wheel_r5.cubeList.add(new ModelBox(stone_wheel_r5, 0, 0, -9.0F, -4.0F, -4.5F, 4, 8, 6, 0.0F, true));

		stone_wheel_r6 = new ModelRenderer(this);
		stone_wheel_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		stone_wheel.addChild(stone_wheel_r6);
		setRotationAngle(stone_wheel_r6, 0.0F, 0.0F, -1.5708F);
		stone_wheel_r6.cubeList.add(new ModelBox(stone_wheel_r6, 0, 0, -9.0F, -4.0F, -4.5F, 4, 8, 6, 0.0F, true));

		stone_wheel_r7 = new ModelRenderer(this);
		stone_wheel_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		stone_wheel.addChild(stone_wheel_r7);
		setRotationAngle(stone_wheel_r7, 0.0F, 0.0F, -3.1416F);
		stone_wheel_r7.cubeList.add(new ModelBox(stone_wheel_r7, 0, 0, -9.0F, -4.0F, -4.5F, 4, 8, 6, 0.0F, true));
	}

//	@Override
//	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//		
//	}
	
	public void renderAll() {
		bb_main.render(0.0625f);
		grinding_stone_corners.render(0.0625f);
		axis.render(0.0625f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}