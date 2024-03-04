package com.tiki.advancedlootableweapons.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.tiki.advancedlootableweapons.blocks.block_entity.DrumBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public class DrumBlockEntityRenderer implements BlockEntityRenderer<DrumBlockEntity> {
    protected final BlockRenderDispatcher dispatcher;
    protected final ItemRenderer itemRenderer;



    public DrumBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        dispatcher = context.getBlockRenderDispatcher();
        itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(DrumBlockEntity blockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        final ItemStack input = blockEntity.itemStackHandler.getStackInSlot(DrumBlockEntity.INPUT_SLOT);
        final ItemStack output = blockEntity.itemStackHandler.getStackInSlot(DrumBlockEntity.OUTPUT_SLOT);
        final Level world = blockEntity.getLevel();
        Direction facing = Direction.NORTH;
        for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}) {
            if (world.getBlockState(blockEntity.getBlockPos().relative(dir)).isAir() &&
                    world.getBlockState(blockEntity.getBlockPos().relative(dir).relative(Direction.DOWN)).isFaceSturdy(world, blockEntity.getBlockPos(), Direction.UP)) {
                facing = dir;
                break;
            }
        }

        if (!input.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.5D, 0.4D, 0.5D);
            pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
            pPoseStack.scale(0.9f, 0.9f, 0.9f);
            itemRenderer.renderStatic(input, ItemTransforms.TransformType.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBufferSource, 0);
            pPoseStack.popPose();
        }

        if (!output.isEmpty()) {
            pPoseStack.pushPose();

            double offset = .15d;

            switch (facing) {
                case EAST -> {
                    pPoseStack.translate(1+offset, 0.425D, 0.5D);
                    pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
                    pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-22.5F));
                }
                case NORTH -> {
                    pPoseStack.translate(0.5D, 0.4D, -offset);
                    pPoseStack.mulPose(Vector3f.XP.rotationDegrees(22.5F));
                }
                case SOUTH -> {
                    pPoseStack.translate(0.5D, 0.4D, 1+offset);
                    pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-22.5F));
                }
                case WEST -> {
                    pPoseStack.translate(-offset, 0.425D, 0.5D);
                    pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-22.5F));
                }
            }

            itemRenderer.renderStatic(output, ItemTransforms.TransformType.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBufferSource, 0);
            pPoseStack.popPose();
        }

        final FluidStack fluid = blockEntity.fluidTank.getFluid();
        if (!fluid.isEmpty()) {
            renderFluid(fluid, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
        }


     //   } else if (blockEntity.displayBubbles) {
     //       renderBubbles(world, blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY() - 0.5, blockEntity.getBlockPos().getZ());
      //  }
    }



    static final float MAX_D = 10.01F / 16F;

    static final float MIN_WH = 0; //Width/height of cutout
    static final float MAX_WH = 1; //Width/height of cutout

    protected void renderFluid(FluidStack fluid, PoseStack pPoseStack, MultiBufferSource buffer, int pPackedLight, int overlay) {

        TextureAtlasSprite sprite = FluidSpriteCache.getStillTexture(fluid);

        int color = getFluidColor(fluid);

        VertexConsumer builder = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        Matrix4f matrix = pPoseStack.last().pose();
        Matrix3f normal = pPoseStack.last().normal();

        float depth = (fluid.getAmount() / 1000f) * MAX_D; //Depth

        float minU = sprite.getU(0);
        float maxU = sprite.getU(16);
        float minV = sprite.getV(0);
        float maxV = sprite.getV(16);

        builder.vertex(matrix, MAX_WH, depth, MIN_WH).color(color).uv(minU, minV).overlayCoords(overlay).uv2(pPackedLight).normal(normal, 0, 1, 0).endVertex();
        builder.vertex(matrix, MIN_WH, depth, MIN_WH).color(color).uv(maxU, minV).overlayCoords(overlay).uv2(pPackedLight).normal(normal, 0, 1, 0).endVertex();
        builder.vertex(matrix, MIN_WH, depth, MAX_WH).color(color).uv(maxU, maxV).overlayCoords(overlay).uv2(pPackedLight).normal(normal, 0, 1, 0).endVertex();
        builder.vertex(matrix, MAX_WH, depth, MAX_WH).color(color).uv(minU, maxV).overlayCoords(overlay).uv2(pPackedLight).normal(normal, 0, 1, 0).endVertex();
    }

    public int getFluidColor(FluidStack fluidStack) {
        // todo, uncomment in 1.19+
        // IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid.getFluid());
        // return renderProperties.getTintColor(fluid);

        return fluidStack.getFluid().getAttributes().getColor(fluidStack);
    }
}
