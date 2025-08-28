package tiki.advancedlootableweapons.blocks.tileentities;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.animation.FastTESR;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.util.RenderHelper;
import tiki.advancedlootableweapons.util.UnpackedModel;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedFace;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedVertex;

public class BellowsTESR extends FastTESR<TileEntityBellows> {

	//CONSTANTS
	public static IBakedModel topModel = null;
	public static IBakedModel leatherModel = null;
	private static UnpackedModel model = null;
	
	//FUNCTIONAL
	//Stateless - Calculated before use
	private static int light = 0;
	private static int skylight = 0;
	private static int blocklight = 0;
	
	@Override
	public void renderTileEntityFast(
			@Nonnull TileEntityBellows te, 
			double x, 
			double y, 
			double z, 
			float partialTicks,
			int destroyStage, 
			float alpha, 
			@Nonnull BufferBuilder buffer
		) {
		
		if(topModel == null) {
			Alw.logger.error("Top Model is null in BellowsTESR. If you're seeing this, please report it to the mod author.");
			//return;
		}
		
		if(leatherModel == null) {
			Alw.logger.error("Top Model is null in BellowsTESR. If you're seeing this, please report it to the mod author.");
			//return;
		}
		
		if(model == null) {
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			IBakedModel bottomModel = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			model = new UnpackedModel(
					new IBakedModel[]{bottomModel, leatherModel, topModel}, 
					new IBlockState[]{state, state, state}
				);
		}
		
		TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite bellows = textureMapBlocks.getAtlasSprite("advancedlootableweapons:blocks/bellows");
		//TextureAtlasSprite plank = textureMapBlocks.getAtlasSprite("minecraft:planks_oak");
		
		float animationProgress = 0f;
		float totalTicks = te.getClientTotalTicks();
		boolean animationQueued = false;
		
		//Alw.logger.info("Te => {}, Is Animating => {}, Should Animate => {}, Ticks W/Out Partial => {}", te.getPos(), te.isAnimating(), te.isAnimating() && (animationQueued || (totalTicks + partialTicks) <= TileEntityBellows.animationTicks), totalTicks);
		
		if(te.isAnimating()) {
			te.checkTicks(partialTicks);
			animationQueued = te.hasAnimationQueued();
			animationProgress = (totalTicks + partialTicks) % TileEntityBellows.animationTicks / TileEntityBellows.animationTicks;
		}else if(totalTicks > 0 || animationProgress > 0) {
			//If the te isn't animating, we don't care about animations performed any more until the next animation, so set it to 0
			Alw.logger.info("No longer animating, settting ticks and progress to 0.");
			te.setClientTotalTicks(0f);
		}
		
		boolean shouldAnimate = te.isAnimating() && (animationQueued || (totalTicks + partialTicks) <= TileEntityBellows.animationTicks);
				
		light = te.getWorld().isBlockLoaded(te.getPos()) ? te.getWorld().getCombinedLight(te.getPos(), 0) : 0;
		skylight = (light >> 16) & 0xFFFF;
		blocklight = light & 0xFFFF;
		
		buffer.setTranslation(x, y, z);
		
		//Bottom panel of the bellows
		for(UnpackedFace face : model.getFaces(0)) {
			for(UnpackedVertex v : face.getVertices()) {
				v.getPos().rotateEnumFacingAroundPivot(te.getFacing());
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, bellows, skylight, blocklight);
			
			for(UnpackedVertex v : face.getVertices()) {
				if(v.getPos().hasRotation() || v.getPos().hasPendingRotation() || v.getPos().isScaled()) {
					v.getPos().clean();
				}
			}
		}
		
		float targetRotation = animationProgress > 0.3f ? 0f : 12.5f;
		float startRotation = animationProgress > 0.3f ? 12.5f : 0f;
		
		float targetScale = animationProgress > 0.3f ? 1f : 1.15f;
		float startScale = animationProgress > 0.3f ? 1.15f : 1f;
		
		float targetScaleY = animationProgress > 0.3f ? 1f : 0.5f;
		float startScaleY = animationProgress > 0.3f ? 0.5f : 1f;
		
		float frameRotation = 0f;
		float frameScale = 1f;
		float frameScaleY = 1f;
		
		if(shouldAnimate) {
			if(animationProgress < 0.3) {
				frameRotation = (float)MathHelper.clampedLerp(startRotation, targetRotation, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
				frameScale = (float)MathHelper.clampedLerp(startScale, targetScale, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
				frameScaleY = (float)MathHelper.clampedLerp(startScaleY, targetScaleY, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
			}else {
				frameRotation = (float)MathHelper.clampedLerp(startRotation, targetRotation, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
				frameScale = (float)MathHelper.clampedLerp(startScale, targetScale, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
				frameScaleY = (float)MathHelper.clampedLerp(startScaleY, targetScaleY, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
			}
			
			Alw.logger.info("Animation Progress => {}, Target Rotation => {}, Frame Rotation => {}, Total Ticks => {}", animationProgress, targetRotation, frameRotation, totalTicks + partialTicks);
			Alw.logger.info("Target Scale X/Z => {}, Frame Scale X/Z => {}, Target Scale Y => {}, Frame Scale Y => {}", targetScale, frameScale, targetScaleY, frameScaleY);

		}
		
		//Leather part of the bellows
		for(UnpackedFace face : model.getFaces(1)) {
			for(UnpackedVertex v : face.getVertices()) {
				if(shouldAnimate) {
					v.getPos().scaleAroundPivot(frameScale, frameScaleY, frameScale, 7.3f/16f, 0f, 7.5f/16f); //Not working? Rotation is off too
				}
				v.getPos().rotateEnumFacingAroundPivot(te.getFacing());
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, bellows, skylight, blocklight);
			
			for(UnpackedVertex v : face.getVertices()) {
				if(v.getPos().hasRotation() || v.getPos().hasPendingRotation() || v.getPos().isScaled()) {
					v.getPos().clean();
				}
			}
		}
		
		//Top Panel of the bellows
		for(UnpackedFace face : model.getFaces(2)) {
			
			for(UnpackedVertex v : face.getVertices()) {
				if(shouldAnimate) {
					v.getPos().rotateZ(frameRotation);
				}
				v.getPos().rotateEnumFacingAroundPivot(te.getFacing());
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, bellows, skylight, blocklight);
			
			for(UnpackedVertex v : face.getVertices()) {
				if(v.getPos().hasRotation() || v.getPos().hasPendingRotation() || v.getPos().isScaled()) {
					v.getPos().clean();
				}
			}
		}
	}
}
