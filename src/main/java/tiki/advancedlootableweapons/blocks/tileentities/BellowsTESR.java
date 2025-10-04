package tiki.advancedlootableweapons.blocks.tileentities;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.animation.FastTESR;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.util.RenderHelper;
import tiki.advancedlootableweapons.util.UnpackedModel;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedFace;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedVertex;

public class BellowsTESR extends FastTESR<TileEntityBellows> {

	//CONSTANTS
	public static String error = "";
	public static IBakedModel topModel = null;
	public static IBakedModel leatherModel = null;
	public static IBakedModel bottomModel = null;
	private static UnpackedModel model = null;
	private static TextureMap textureMapBlocks = null;
	//private static final TextureAtlasSprite bellows = textureMapBlocks.getAtlasSprite("advancedlootableweapons:blocks/bellows");
	private static TextureAtlasSprite leather = null;
	private static TextureAtlasSprite defaultPlank = null;
	private static TextureAtlasSprite nbtPlank = null;
	
	//FUNCTIONAL
	//Stateless - Calculated before use
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
		
		if(te.getClientTexture() != "") {
			init(te.getWorld().isBlockLoaded(te.getPos()) ? te.getWorld().getCombinedLight(te.getPos(), 0) : 0, te.getClientTexture());
		}else {
			init(te.getWorld().isBlockLoaded(te.getPos()) ? te.getWorld().getCombinedLight(te.getPos(), 0) : 0, "");
		}
		
		
		float animationProgress = 0f;
		float totalTicks = 0;
		boolean animationQueued = false;
		
		if(te.isAnimating()) {
			te.clientCheckTicks(partialTicks);
			totalTicks = te.getClientTotalTicks();
			
			animationQueued = te.hasAnimationQueued();
			animationProgress = (totalTicks + partialTicks) % TileEntityBellows.animationTicks / TileEntityBellows.animationTicks;
		}else {
			te.clientStoppedAnimating();
		}
		
		boolean shouldAnimate = te.isAnimating() && (animationQueued || (totalTicks + partialTicks) <= TileEntityBellows.animationTicks);
		
		buffer.setTranslation(x, y, z);
		
		//Bottom panel of the bellows
		for(UnpackedFace face : model.getFaces(0)) {
			for(UnpackedVertex v : face.getVertices()) {
				v.getPos().rotateEnumFacingAroundPivot(0.5f, 0f, 0.5f, te.getFacing().rotateYCCW());
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, nbtPlank.getIconName() == "missingno" ? defaultPlank : nbtPlank, skylight, blocklight);
			
			for(UnpackedVertex v : face.getVertices()) {
				if(v.getPos().hasRotation() || v.getPos().hasPendingRotation() || v.getPos().isScaled()) {
					v.getPos().clean();
				}
			}
		}
		
		float targetRotation = animationProgress > 0.3f ? 0f : 10f;
		float startRotation = animationProgress > 0.3f ? 10f : 0f;
		
//		float targetScale = animationProgress > 0.3f ? 1f : 1.15f;
//		float startScale = animationProgress > 0.3f ? 1.15f : 1f;
		
		float targetScaleY = animationProgress > 0.3f ? 1f : 0.45f;
		float startScaleY = animationProgress > 0.3f ? 0.45f : 1f;
		
		float frameRotation = 0f;
		float frameScale = 1f;
		float frameScaleY = 1f;
		
		if(shouldAnimate) {
			if(animationProgress < 0.3) {
				frameRotation = (float)MathHelper.clampedLerp(startRotation, targetRotation, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
//				frameScale = (float)MathHelper.clampedLerp(startScale, targetScale, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
				frameScaleY = (float)MathHelper.clampedLerp(startScaleY, targetScaleY, RenderHelper.mapfZeroToOne(0f, 0.3f, animationProgress));
			}else {
				frameRotation = (float)MathHelper.clampedLerp(startRotation, targetRotation, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
//				frameScale = (float)MathHelper.clampedLerp(startScale, targetScale, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
				frameScaleY = (float)MathHelper.clampedLerp(startScaleY, targetScaleY, RenderHelper.mapfZeroToOne(0.3f, 1f, animationProgress));
			}
			
			//Alw.logger.debug("Animation Progress => {}, Target Rotation => {}, Frame Rotation => {}, Total Ticks => {}, TE Facing => " + te.getFacing() + " at POS => " + te.getPos(), animationProgress, targetRotation, frameRotation, totalTicks + partialTicks);
			//Alw.logger.debug("Target Scale X/Z => {}, Frame Scale X/Z => {}, Target Scale Y => {}, Frame Scale Y => {}", targetScale, frameScale, targetScaleY, frameScaleY);
		}
		
		//Leather part of the bellows
		for(UnpackedFace face : model.getFaces(1)) {
			for(UnpackedVertex v : face.getVertices()) {
				if(shouldAnimate) {
					v.getPos().scaleAroundPivot(frameScale, frameScaleY, frameScale, 7.3f/16f, 0f, 7.5f/16f);
				}
				
				v.getPos().rotateEnumFacingAroundPivot(0.5f, 0f, 0.5f, te.getFacing().rotateYCCW());
				
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, leather, skylight, blocklight);
			
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
					v.getPos().rotateZAroundPivot(-1f/16f, 0f, 8f/16f, frameRotation);
					v.getPos().applyRotation();
				}
				
				v.getPos().rotateEnumFacingAroundPivot(0.5f, 0f, 0.5f, te.getFacing().rotateYCCW());
				
				if(v.getPos().hasPendingRotation()) {
					v.getPos().applyRotation();
				}
			}
			
			RenderHelper.addFaceToBuffer(buffer, face, nbtPlank.getIconName() == "missingno" ? defaultPlank : nbtPlank, skylight, blocklight);
			
			for(UnpackedVertex v : face.getVertices()) {
				if(v.getPos().hasRotation() || v.getPos().hasPendingRotation() || v.getPos().isScaled()) {
					v.getPos().clean();
				}
			}
		}
	}
	
	public static void renderItemStack(ItemStack stack, @Nonnull BufferBuilder buffer) {
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("WoodTexture")) {
			init(0xF00000, stack.getTagCompound().getString("WoodTexture"));
		}else {
			init(0xF00000, "");
		}
		
		//Bottom panel of the bellows
		for(UnpackedFace face : model.getFaces(0)) {
			RenderHelper.addFaceToBuffer(buffer, face, nbtPlank.getIconName() == "missingno" ? defaultPlank : nbtPlank, skylight, blocklight);
		}
		
		//Leather part of the bellows
		for(UnpackedFace face : model.getFaces(1)) {
			RenderHelper.addFaceToBuffer(buffer, face, leather, skylight, blocklight);
		}
		
		//Top Panel of the bellows
		for(UnpackedFace face : model.getFaces(2)) {
			RenderHelper.addFaceToBuffer(buffer, face, nbtPlank.getIconName() == "missingno" ? defaultPlank : nbtPlank, skylight, blocklight);
		}
	}
	
	public static void init(int light, String nbtTexture) {
		error = "";
		
		if(topModel == null) {
			error = "Top Model";
		}
		
		if(leatherModel == null) {
			if(error != "") {
				error += ", ";
			}
			error += "Leather Model";
		}
		
		if(bottomModel == null) {
			if(error != "") {
				error += ", ";
			}
			error += "Bottom Model";
		}
		
		if(error != "") {
			Alw.logger.error(error + " == null in BellowsTESR. If you're seeing this, please report it to the mod author along with a screenshot or log of this error."
					+ "\n---- Version => " + ModInfo.VER);
			return;
		}
		
		if(textureMapBlocks == null) {
			textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
		}
		
		if(leather == null || leather.getIconName() == "missingno") {
			leather = textureMapBlocks.getAtlasSprite("advancedlootableweapons:blocks/bellows_leather");
		}
		
		if(defaultPlank == null || defaultPlank.getIconName() == "missingno") {
			defaultPlank = textureMapBlocks.getAtlasSprite("minecraft:blocks/planks_oak");
		}
		
		nbtPlank = textureMapBlocks.getAtlasSprite(nbtTexture);
		
		if(model == null) {
			IBlockState state = BlockInit.bellows.getDefaultState();
			model = new UnpackedModel(
					new IBakedModel[]{bottomModel, leatherModel, topModel}, 
					new IBlockState[]{state, state, state}
				);
		}
		
		skylight = (light >> 16) & 0xFFFF;
		blocklight = light & 0xFFFF;	
	}
	
}
