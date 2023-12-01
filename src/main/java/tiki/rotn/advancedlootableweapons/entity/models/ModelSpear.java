package tiki.rotn.advancedlootableweapons.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelSpear extends ModelBase {
	private final ModelRenderer SpearHead;
	private final ModelRenderer FrontEdgeHeightFillerRight_r1;
	private final ModelRenderer FrontEdgeHeightFillerLeft_r1;
	private final ModelRenderer FrontEdgeFillerRight_r1;
	private final ModelRenderer FrontEdgeFillerLeft_r1;
	private final ModelRenderer FrontEdgeBottom_r1;
	private final ModelRenderer MiddleEdgeHeightRightBottom_r1;
	private final ModelRenderer MiddleHeightBottomLeft_r1;
	private final ModelRenderer BackLeftSlopeBottom_r1;
	private final ModelRenderer BackLeftSlopeTop_r1;
	private final ModelRenderer MiddleRightSlopeBottom_r1;
	private final ModelRenderer MiddleLeftSlopeBottom_r1;
	private final ModelRenderer MiddleRightSlopeTop_r1;
	private final ModelRenderer MiddleLeftSlopeTop_r1;
	private final ModelRenderer SpearHeadBackSlopeBottomLeft_r1;
	private final ModelRenderer SpearHeadBackSlopeTopLeft_r1;
	private final ModelRenderer SpearHeadBackBottomLeft_r1;
	private final ModelRenderer SpearHeadBackSlopeBottomRight_r1;
	private final ModelRenderer SpearHeadBackSlopeTopRight_r1;
	private final ModelRenderer SpearHeadBackBottomRight_r1;
	private final ModelRenderer SpearHandle;
	private final ModelRenderer SpearHandleDiagonal4_r1;
	private final ModelRenderer SpearHandleDiagonal2_r1;
	private final ModelRenderer SpearHandleHorizontalLeft2_r1;
	private final ModelRenderer SpearHandleVerticalTop4_r1;
	private final ModelRenderer SpearHandleVerticalTop5_r1;

	public ModelSpear() {
		textureWidth = 1024;
		textureHeight = 1024;

		SpearHead = new ModelRenderer(this);
		SpearHead.setRotationPoint(2.0F, 24.0F, 0.0F);
		setRotationAngle(SpearHead, 0.0F, -1.5708F, 0.0F);
		SpearHead.cubeList.add(new ModelBox(SpearHead, 272, 820, -5.0F, -1.001F, -4.0F, 10, 1, 8, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 272, 840, -2.0F, -1.6F, -4.001F, 4, 1, 10, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 272, 829, -2.0F, -0.4F, -4.001F, 4, 1, 10, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 232, 955, -2.0F, -1.6F, -9.0F, 4, 1, 5, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 214, 955, -2.0F, -0.4F, -9.0F, 4, 1, 5, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 955, 2.0F, -2.0F, -4.0F, 2, 3, 8, 0.0F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 272, 869, -4.0F, -2.0F, -4.0F, 2, 3, 8, 0.0F, false));

		FrontEdgeHeightFillerRight_r1 = new ModelRenderer(this);
		FrontEdgeHeightFillerRight_r1.setRotationPoint(0.5638F, -0.5F, -10.8386F);
		SpearHead.addChild(FrontEdgeHeightFillerRight_r1);
		setRotationAngle(FrontEdgeHeightFillerRight_r1, 0.0F, 0.2182F, 0.0F);
		FrontEdgeHeightFillerRight_r1.cubeList.add(new ModelBox(FrontEdgeHeightFillerRight_r1, 46, 966, -0.9838F, -1.5F, -0.5314F, 2, 3, 1, 0.0F, false));

		FrontEdgeHeightFillerLeft_r1 = new ModelRenderer(this);
		FrontEdgeHeightFillerLeft_r1.setRotationPoint(-0.5438F, -0.5F, -10.8523F);
		SpearHead.addChild(FrontEdgeHeightFillerLeft_r1);
		setRotationAngle(FrontEdgeHeightFillerLeft_r1, 0.0F, -0.2182F, 0.0F);
		FrontEdgeHeightFillerLeft_r1.cubeList.add(new ModelBox(FrontEdgeHeightFillerLeft_r1, 40, 966, -0.9762F, -1.5F, -0.5477F, 2, 3, 1, 0.0F, false));

		FrontEdgeFillerRight_r1 = new ModelRenderer(this);
		FrontEdgeFillerRight_r1.setRotationPoint(1.3037F, -0.5F, -11.5545F);
		SpearHead.addChild(FrontEdgeFillerRight_r1);
		setRotationAngle(FrontEdgeFillerRight_r1, 0.0F, 0.2618F, 0.0F);
		FrontEdgeFillerRight_r1.cubeList.add(new ModelBox(FrontEdgeFillerRight_r1, 52, 968, -1.0F, -0.501F, -0.5F, 2, 1, 1, 0.0F, false));

		FrontEdgeFillerLeft_r1 = new ModelRenderer(this);
		FrontEdgeFillerLeft_r1.setRotationPoint(-1.2587F, -0.5F, -11.8111F);
		SpearHead.addChild(FrontEdgeFillerLeft_r1);
		setRotationAngle(FrontEdgeFillerLeft_r1, 0.0F, -0.2618F, 0.0F);
		FrontEdgeFillerLeft_r1.cubeList.add(new ModelBox(FrontEdgeFillerLeft_r1, 52, 966, -0.9313F, -0.501F, -0.2589F, 2, 1, 1, 0.0F, false));

		FrontEdgeBottom_r1 = new ModelRenderer(this);
		FrontEdgeBottom_r1.setRotationPoint(-0.3587F, -0.5F, -12.4111F);
		SpearHead.addChild(FrontEdgeBottom_r1);
		setRotationAngle(FrontEdgeBottom_r1, 0.0F, -0.7854F, 0.0F);
		FrontEdgeBottom_r1.cubeList.add(new ModelBox(FrontEdgeBottom_r1, 32, 966, -0.1413F, 0.51F, -0.6889F, 2, 1, 2, 0.0F, false));
		FrontEdgeBottom_r1.cubeList.add(new ModelBox(FrontEdgeBottom_r1, 24, 966, -0.1413F, -1.51F, -0.6889F, 2, 1, 2, 0.0F, false));
		FrontEdgeBottom_r1.cubeList.add(new ModelBox(FrontEdgeBottom_r1, 352, 961, -1.1413F, -0.5F, -1.6889F, 3, 1, 3, 0.0F, false));

		MiddleEdgeHeightRightBottom_r1 = new ModelRenderer(this);
		MiddleEdgeHeightRightBottom_r1.setRotationPoint(2.9285F, -0.5F, -5.3617F);
		SpearHead.addChild(MiddleEdgeHeightRightBottom_r1);
		setRotationAngle(MiddleEdgeHeightRightBottom_r1, 0.0F, 0.3403F, 0.0F);
		MiddleEdgeHeightRightBottom_r1.cubeList.add(new ModelBox(MiddleEdgeHeightRightBottom_r1, 160, 955, -1.4485F, 0.501F, -6.3433F, 2, 1, 8, 0.0F, false));
		MiddleEdgeHeightRightBottom_r1.cubeList.add(new ModelBox(MiddleEdgeHeightRightBottom_r1, 140, 955, -1.4485F, -1.501F, -6.3433F, 2, 1, 8, 0.0F, false));
		MiddleEdgeHeightRightBottom_r1.cubeList.add(new ModelBox(MiddleEdgeHeightRightBottom_r1, 272, 860, -1.5F, -0.5F, -6.0F, 3, 1, 8, 0.0F, false));

		MiddleHeightBottomLeft_r1 = new ModelRenderer(this);
		MiddleHeightBottomLeft_r1.setRotationPoint(-2.2398F, -0.5F, -7.2428F);
		SpearHead.addChild(MiddleHeightBottomLeft_r1);
		setRotationAngle(MiddleHeightBottomLeft_r1, 0.0F, -0.3491F, 0.0F);
		MiddleHeightBottomLeft_r1.cubeList.add(new ModelBox(MiddleHeightBottomLeft_r1, 120, 955, -0.5402F, 0.501F, -4.3322F, 2, 1, 8, 0.0F, false));
		MiddleHeightBottomLeft_r1.cubeList.add(new ModelBox(MiddleHeightBottomLeft_r1, 100, 955, -0.5402F, -1.501F, -4.3322F, 2, 1, 8, 0.0F, false));
		MiddleHeightBottomLeft_r1.cubeList.add(new ModelBox(MiddleHeightBottomLeft_r1, 272, 851, -1.5F, -0.5F, -4.0F, 3, 1, 8, 0.0F, false));

		BackLeftSlopeBottom_r1 = new ModelRenderer(this);
		BackLeftSlopeBottom_r1.setRotationPoint(1.3132F, -1.5078F, -1.5F);
		SpearHead.addChild(BackLeftSlopeBottom_r1);
		setRotationAngle(BackLeftSlopeBottom_r1, 0.0F, 0.0F, -0.6109F);
		BackLeftSlopeBottom_r1.cubeList.add(new ModelBox(BackLeftSlopeBottom_r1, 80, 955, -4.1532F, -0.8522F, -2.51F, 2, 1, 8, 0.0F, false));
		BackLeftSlopeBottom_r1.cubeList.add(new ModelBox(BackLeftSlopeBottom_r1, 60, 955, -1.1532F, -0.0072F, -2.51F, 2, 1, 8, 0.0F, false));

		BackLeftSlopeTop_r1 = new ModelRenderer(this);
		BackLeftSlopeTop_r1.setRotationPoint(-1.5F, -1.5F, -1.5F);
		SpearHead.addChild(BackLeftSlopeTop_r1);
		setRotationAngle(BackLeftSlopeTop_r1, 0.0F, 0.0F, 0.6109F);
		BackLeftSlopeTop_r1.cubeList.add(new ModelBox(BackLeftSlopeTop_r1, 40, 955, -0.7F, -0.12F, -2.51F, 2, 1, 8, 0.0F, false));
		BackLeftSlopeTop_r1.cubeList.add(new ModelBox(BackLeftSlopeTop_r1, 20, 955, 2.3F, -0.96F, -2.51F, 2, 1, 8, 0.0F, false));

		MiddleRightSlopeBottom_r1 = new ModelRenderer(this);
		MiddleRightSlopeBottom_r1.setRotationPoint(1.467F, 0.016F, -7.0F);
		SpearHead.addChild(MiddleRightSlopeBottom_r1);
		setRotationAngle(MiddleRightSlopeBottom_r1, 0.192F, 0.2836F, 0.6109F);
		MiddleRightSlopeBottom_r1.cubeList.add(new ModelBox(MiddleRightSlopeBottom_r1, 180, 955, -0.987F, 0.164F, -4.0F, 1, 1, 8, 0.0F, false));

		MiddleLeftSlopeBottom_r1 = new ModelRenderer(this);
		MiddleLeftSlopeBottom_r1.setRotationPoint(-0.7862F, 0.3227F, -6.5379F);
		SpearHead.addChild(MiddleLeftSlopeBottom_r1);
		setRotationAngle(MiddleLeftSlopeBottom_r1, 0.1963F, -0.288F, -0.6109F);
		MiddleLeftSlopeBottom_r1.cubeList.add(new ModelBox(MiddleLeftSlopeBottom_r1, 198, 955, -0.4998F, -0.5177F, -3.5F, 1, 1, 7, 0.0F, false));

		MiddleRightSlopeTop_r1 = new ModelRenderer(this);
		MiddleRightSlopeTop_r1.setRotationPoint(0.8884F, -1.3172F, -6.472F);
		SpearHead.addChild(MiddleRightSlopeTop_r1);
		setRotationAngle(MiddleRightSlopeTop_r1, -0.192F, 0.2836F, -0.6109F);
		MiddleRightSlopeTop_r1.cubeList.add(new ModelBox(MiddleRightSlopeTop_r1, 296, 955, -0.5384F, -0.5128F, -2.8F, 1, 1, 6, 0.0F, false));

		MiddleLeftSlopeTop_r1 = new ModelRenderer(this);
		MiddleLeftSlopeTop_r1.setRotationPoint(-1.1147F, -1.4943F, -6.3924F);
		SpearHead.addChild(MiddleLeftSlopeTop_r1);
		setRotationAngle(MiddleLeftSlopeTop_r1, -0.1963F, -0.2836F, 0.6109F);
		MiddleLeftSlopeTop_r1.cubeList.add(new ModelBox(MiddleLeftSlopeTop_r1, 282, 955, -0.2F, -0.5F, -3.0F, 1, 1, 6, 0.0F, false));

		SpearHeadBackSlopeBottomLeft_r1 = new ModelRenderer(this);
		SpearHeadBackSlopeBottomLeft_r1.setRotationPoint(0.8027F, 0.3209F, 4.0938F);
		SpearHead.addChild(SpearHeadBackSlopeBottomLeft_r1);
		setRotationAngle(SpearHeadBackSlopeBottomLeft_r1, -0.4189F, 0.6894F, -0.6109F);
		SpearHeadBackSlopeBottomLeft_r1.cubeList.add(new ModelBox(SpearHeadBackSlopeBottomLeft_r1, 16, 966, -1.5F, -0.9909F, -2.8F, 1, 1, 3, 0.0F, false));

		SpearHeadBackSlopeTopLeft_r1 = new ModelRenderer(this);
		SpearHeadBackSlopeTopLeft_r1.setRotationPoint(-0.5403F, -1.4521F, 4.3621F);
		SpearHead.addChild(SpearHeadBackSlopeTopLeft_r1);
		setRotationAngle(SpearHeadBackSlopeTopLeft_r1, 0.4189F, 0.6894F, 0.6109F);
		SpearHeadBackSlopeTopLeft_r1.cubeList.add(new ModelBox(SpearHeadBackSlopeTopLeft_r1, 8, 966, -0.4F, -0.4F, -2.0F, 1, 1, 3, 0.0F, false));

		SpearHeadBackBottomLeft_r1 = new ModelRenderer(this);
		SpearHeadBackBottomLeft_r1.setRotationPoint(2.1872F, -0.5F, 5.409F);
		SpearHead.addChild(SpearHeadBackBottomLeft_r1);
		setRotationAngle(SpearHeadBackBottomLeft_r1, 0.0F, 0.7854F, 0.0F);
		SpearHeadBackBottomLeft_r1.cubeList.add(new ModelBox(SpearHeadBackBottomLeft_r1, 352, 955, -3.3822F, 0.499F, -5.379F, 2, 1, 5, 0.0F, false));
		SpearHeadBackBottomLeft_r1.cubeList.add(new ModelBox(SpearHeadBackBottomLeft_r1, 338, 955, -3.3822F, -1.499F, -5.379F, 2, 1, 5, 0.0F, false));
		SpearHeadBackBottomLeft_r1.cubeList.add(new ModelBox(SpearHeadBackBottomLeft_r1, 266, 955, -4.0822F, -0.5F, -6.079F, 2, 1, 6, 0.0F, false));

		SpearHeadBackSlopeBottomRight_r1 = new ModelRenderer(this);
		SpearHeadBackSlopeBottomRight_r1.setRotationPoint(0.8027F, 0.3209F, 4.0938F);
		SpearHead.addChild(SpearHeadBackSlopeBottomRight_r1);
		setRotationAngle(SpearHeadBackSlopeBottomRight_r1, -0.4189F, -0.6894F, 0.6109F);
		SpearHeadBackSlopeBottomRight_r1.cubeList.add(new ModelBox(SpearHeadBackSlopeBottomRight_r1, 0, 966, -0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F, false));

		SpearHeadBackSlopeTopRight_r1 = new ModelRenderer(this);
		SpearHeadBackSlopeTopRight_r1.setRotationPoint(-0.8629F, -1.3182F, 3.9774F);
		SpearHead.addChild(SpearHeadBackSlopeTopRight_r1);
		setRotationAngle(SpearHeadBackSlopeTopRight_r1, 0.4189F, -0.6894F, -0.6109F);
		SpearHeadBackSlopeTopRight_r1.cubeList.add(new ModelBox(SpearHeadBackSlopeTopRight_r1, 382, 955, 0.6F, 0.0382F, -2.6F, 1, 1, 3, 0.0F, false));

		SpearHeadBackBottomRight_r1 = new ModelRenderer(this);
		SpearHeadBackBottomRight_r1.setRotationPoint(-4.0F, -0.5F, 7.0F);
		SpearHead.addChild(SpearHeadBackBottomRight_r1);
		setRotationAngle(SpearHeadBackBottomRight_r1, 0.0F, -0.7854F, 0.0F);
		SpearHeadBackBottomRight_r1.cubeList.add(new ModelBox(SpearHeadBackBottomRight_r1, 324, 955, 1.55F, 0.49F, -7.8F, 2, 1, 5, 0.0F, false));
		SpearHeadBackBottomRight_r1.cubeList.add(new ModelBox(SpearHeadBackBottomRight_r1, 310, 955, 1.55F, -1.49F, -7.8F, 2, 1, 5, 0.0F, false));
		SpearHeadBackBottomRight_r1.cubeList.add(new ModelBox(SpearHeadBackBottomRight_r1, 250, 955, 2.25F, -0.5F, -8.5F, 2, 1, 6, 0.0F, false));

		SpearHandle = new ModelRenderer(this);
		SpearHandle.setRotationPoint(0.0F, 0.0F, 0.0F);
		SpearHead.addChild(SpearHandle);
		SpearHandle.cubeList.add(new ModelBox(SpearHandle, 0, 820, -1.1F, -1.0F, 8.001F, 2, 1, 134, 0.0F, false));
		SpearHandle.cubeList.add(new ModelBox(SpearHandle, 272, 414, -0.9F, 0.0F, 8.003F, 2, 2, 134, 0.0F, false));
		SpearHandle.cubeList.add(new ModelBox(SpearHandle, 0, 414, -1.1F, 0.001F, 8.001F, 2, 2, 134, 0.0F, false));
		SpearHandle.cubeList.add(new ModelBox(SpearHandle, 272, 278, -1.1F, -3.001F, 8.002F, 2, 2, 134, 0.0F, false));
		SpearHandle.cubeList.add(new ModelBox(SpearHandle, 0, 278, -0.9F, -3.0F, 8.001F, 2, 2, 134, 0.0F, false));

		SpearHandleDiagonal4_r1 = new ModelRenderer(this);
		SpearHandleDiagonal4_r1.setRotationPoint(-0.0283F, -0.4667F, 6.5F);
		SpearHandle.addChild(SpearHandleDiagonal4_r1);
		setRotationAngle(SpearHandleDiagonal4_r1, 0.0F, 0.0F, -0.7854F);
		SpearHandleDiagonal4_r1.cubeList.add(new ModelBox(SpearHandleDiagonal4_r1, 272, 685, -0.9617F, -2.5433F, 1.51F, 2, 1, 134, 0.0F, false));
		SpearHandleDiagonal4_r1.cubeList.add(new ModelBox(SpearHandleDiagonal4_r1, 0, 685, -0.9617F, 1.5367F, 1.51F, 2, 1, 134, 0.0F, false));

		SpearHandleDiagonal2_r1 = new ModelRenderer(this);
		SpearHandleDiagonal2_r1.setRotationPoint(0.0F, 0.0F, -2.0F);
		SpearHandle.addChild(SpearHandleDiagonal2_r1);
		setRotationAngle(SpearHandleDiagonal2_r1, 0.0F, 0.0F, 0.7854F);
		SpearHandleDiagonal2_r1.cubeList.add(new ModelBox(SpearHandleDiagonal2_r1, 272, 550, -1.35F, 1.19F, 10.01F, 2, 1, 134, 0.0F, false));
		SpearHandleDiagonal2_r1.cubeList.add(new ModelBox(SpearHandleDiagonal2_r1, 0, 550, -1.35F, -2.89F, 10.01F, 2, 1, 134, 0.0F, false));

		SpearHandleHorizontalLeft2_r1 = new ModelRenderer(this);
		SpearHandleHorizontalLeft2_r1.setRotationPoint(0.0F, -0.5F, 6.5F);
		SpearHandle.addChild(SpearHandleHorizontalLeft2_r1);
		setRotationAngle(SpearHandleHorizontalLeft2_r1, 0.0F, 0.0F, 1.5708F);
		SpearHandleHorizontalLeft2_r1.cubeList.add(new ModelBox(SpearHandleHorizontalLeft2_r1, 278, 139, -0.9F, 0.501F, -1.498F, 2, 2, 137, 0.0F, false));
		SpearHandleHorizontalLeft2_r1.cubeList.add(new ModelBox(SpearHandleHorizontalLeft2_r1, 0, 139, -1.1F, 0.5F, -1.497F, 2, 2, 137, 0.0F, false));
		SpearHandleHorizontalLeft2_r1.cubeList.add(new ModelBox(SpearHandleHorizontalLeft2_r1, 278, 0, -1.1F, -2.5F, -1.497F, 2, 2, 137, 0.0F, false));
		SpearHandleHorizontalLeft2_r1.cubeList.add(new ModelBox(SpearHandleHorizontalLeft2_r1, 0, 0, -0.9F, -2.501F, -1.498F, 2, 2, 137, 0.0F, false));

		SpearHandleVerticalTop4_r1 = new ModelRenderer(this);
		SpearHandleVerticalTop4_r1.setRotationPoint(0.1F, -2.0F, 7.001F);
		SpearHandle.addChild(SpearHandleVerticalTop4_r1);
		setRotationAngle(SpearHandleVerticalTop4_r1, 0.5236F, 0.0F, 0.0F);
		SpearHandleVerticalTop4_r1.cubeList.add(new ModelBox(SpearHandleVerticalTop4_r1, 374, 959, -1.2F, -0.361F, -0.651F, 2, 2, 2, 0.0F, false));
		SpearHandleVerticalTop4_r1.cubeList.add(new ModelBox(SpearHandleVerticalTop4_r1, 374, 955, -1.0F, -0.36F, -0.651F, 2, 2, 2, 0.0F, false));

		SpearHandleVerticalTop5_r1 = new ModelRenderer(this);
		SpearHandleVerticalTop5_r1.setRotationPoint(0.1F, 0.88F, 7.401F);
		SpearHandle.addChild(SpearHandleVerticalTop5_r1);
		setRotationAngle(SpearHandleVerticalTop5_r1, -0.5236F, 0.0F, 0.0F);
		SpearHandleVerticalTop5_r1.cubeList.add(new ModelBox(SpearHandleVerticalTop5_r1, 366, 959, -1.2F, -1.339F, -0.9F, 2, 2, 2, 0.0F, false));
		SpearHandleVerticalTop5_r1.cubeList.add(new ModelBox(SpearHandleVerticalTop5_r1, 366, 955, -1.0F, -1.34F, -0.9F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		SpearHead.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}