package com.tiki.advancedlootableweapons.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.tiki.advancedlootableweapons.blocks.block_entity.DrumBlockEntity;
import com.tiki.advancedlootableweapons.init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import java.util.Random;

public class DrumBlockEntityRenderer implements BlockEntityRenderer<DrumBlockEntity> {
    protected final BlockRenderDispatcher dispatcher;
    protected final ItemRenderer itemRenderer;

    private final Vector3f bubbleColumn1 = new Vector3f(0.0f, 1.0f, 0.0f);
    private final Vector3f bubbleColumn2 = new Vector3f(0.0f, 1.0f, 0.0f);
    private float speedFactor1 = 0.01f;
    private float speedFactor2 = 0.01f;
    private float bubbleOffset1 = 0.0f;
    private float bubbleOffset2 = 0.0f;
    private boolean canBubble2 = true;

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
        for(Direction dir : new Direction[]{Direction.NORTH,Direction.SOUTH,Direction.EAST,Direction.WEST}) {
            if(world.getBlockState(blockEntity.getBlockPos().relative(dir)).isAir() &&
                    world.getBlockState(blockEntity.getBlockPos().relative(dir).relative(Direction.DOWN)).isFaceSturdy(world, blockEntity.getBlockPos(), Direction.UP)) {
                facing = dir;
                break;
            }
        }

        if(!input.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.5D, 0.4D, 0.5D);
           // GlStateManager.rotate(-90.0F, 1F, 0F, 0F);
            pPoseStack.scale(0.9f, 0.9f, 0.9f);
            itemRenderer.renderStatic(input, ItemTransforms.TransformType.FIXED,pPackedLight,pPackedOverlay,pPoseStack,pBufferSource,0);
            pPoseStack.popPose();
        }

        if(!output.isEmpty()) {
            pPoseStack.pushPose();
            switch(facing) {
                case EAST:
                    pPoseStack.translate(1.175D, 0.425D, 0.5D);
                   // GlStateManager.rotate(90.0F, 0F, 1F, 0F);
                    //GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
                    break;
                case NORTH:
                    pPoseStack.translate(0.5D,  0.4D, 0.175D);
                 //   GlStateManager.rotate(22.5F, 1F, 0F, 0F);
                    break;
                case SOUTH:
                    pPoseStack.translate(0.5D, 0.4D, 1.175D);
                  //  GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
                    break;
                case WEST:
                    pPoseStack.translate(0.175D, 0.425D, 0.5D);
                  //  GlStateManager.rotate(-90.0F, 0F, 1F, 0F);
                  //  GlStateManager.rotate(-22.5F, 1F, 0F, 0F);
                    break;
                default:
                    pPoseStack.translate(0.5D, 0.4D,  0.175D);
                  //  GlStateManager.rotate(22.5F, 1F, 0F, 0F);
                    break;
            }

            itemRenderer.renderStatic(output,  ItemTransforms.TransformType.FIXED,pPackedLight,pPackedOverlay,pPoseStack,pBufferSource,0);
            pPoseStack.popPose();
        }

        final FluidStack fluid = blockEntity.fluidTank.getFluid();
        if(!fluid.isEmpty()) {

        }

        if(blockEntity.displayQuenching) {
            renderQuench(world, blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY() - 0.5, blockEntity.getBlockPos().getZ(), blockEntity.progress);
        }else if(blockEntity.displayBubbles) {
            renderBubbles(world, blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY() - 0.5, blockEntity.getBlockPos().getZ());
        }
    }

    private void renderBubbles(Level world, double x, double y, double z) {
        Random rand = world.random;
        if((bubbleColumn1.y() + bubbleOffset1) >= 1) {
            bubbleColumn1.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
            bubbleOffset1 = 0.0f;
            speedFactor1 = (rand.nextFloat() + 0.1f) / 50f;
        }
        world.addParticle(ParticleTypes.BUBBLE, x + bubbleColumn1.x(), y + bubbleColumn1.y() + bubbleOffset1, z + bubbleColumn1.z(), 0.0f, 0.0f, 0.0f);
        bubbleOffset1 += speedFactor1;

        if(canBubble2 && rand.nextFloat() >= 0.9) {
            canBubble2 = false;
        }
        if((bubbleColumn2.y() + bubbleOffset2) >= 1) {
            bubbleColumn2.set(0.25f + (rand.nextFloat() / 2f), 0.0125f, 0.25f + (rand.nextFloat() / 2f));
            bubbleOffset2 = 0.0f;
            speedFactor2 = (rand.nextFloat() + 0.1f) / 50f;
            canBubble2 = true;
        }
        if(!canBubble2) {
            world.addParticle(ParticleTypes.BUBBLE, x + bubbleColumn2.x(), y + bubbleColumn2.y() + bubbleOffset2, z + bubbleColumn2.z(), 0.0f, 0.0f, 0.0f);
            bubbleOffset2 += speedFactor2;
        }
    }

    private void renderQuench(Level world, double x, double y, double z, int progress) {
        Random rand = world.random;
        if(progress % 2 == 0) {
            world.addParticle(ParticleTypes.SPIT, x + 0.5 + (rand.nextFloat() - 0.5), y + 0.85 + (rand.nextFloat() - 0.5), z + 0.5 + (rand.nextFloat() - 0.5), 0.0, 0.0, 0.01f);
        }
        if(progress == 6) {
            world.playSound(null,x, y, z, SoundInit.QUENCH, SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }


}
