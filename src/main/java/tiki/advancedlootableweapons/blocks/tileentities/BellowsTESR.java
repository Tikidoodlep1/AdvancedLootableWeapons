package tiki.advancedlootableweapons.blocks.tileentities;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.animation.FastTESR;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.util.RenderHelper;
import tiki.advancedlootableweapons.util.UnpackedModel;
import tiki.advancedlootableweapons.util.UnpackedModel.UnpackedFace;

public class BellowsTESR extends FastTESR<TileEntityBellows> {

	// 1d / 16d, the size of 1 pixel from a single block
	//private static final double pixelSize = 0.0625d;
	public static IBakedModel topModel = null;
	
	private UnpackedModel model = null;
	
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
			return;
		}
		
		if(model == null) {
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			IBakedModel bottomModel = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			model = new UnpackedModel(
					new IBakedModel[]{bottomModel, topModel}, 
					new IBlockState[]{state, state}
				);
		}
		
		TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite bellows = textureMapBlocks.getAtlasSprite("advancedlootableweapons:blocks/bellows");
				
		float animationProgress = (float)te.getAnimationTick() / (float)TileEntityBellows.animationTicks;
		
		buffer.setTranslation(x, y, z);
		
		//Body & Bottom panel of the bellows
		for(UnpackedFace face : model.getFaces(0)) {
			RenderHelper.addQuadToBuffer(buffer, 
					face.getFrom().getX(), face.getFrom().getY(), face.getFrom().getZ(), 
					face.getTo().getX(), face.getTo().getY(), face.getTo().getZ(), 
					face.getMinU(), face.getMaxU(), 
					face.getMinV(), face.getMaxV(), 
					bellows, 
					(face.getAvgLight() >> 16) & 0xFFFF, 
					face.getAvgLight() & 0xFFFF, 
					face.getFace());
		}
		
		float targetRotation = animationProgress > 0.3f ? 0f : -22.5f;
		float frameRotation = (targetRotation * animationProgress) * partialTicks;
		
		//Top panel of the bellows
		for(UnpackedFace face : model.getFaces(1)) {
			
			if(frameRotation != 0 && (te.getFacing() == EnumFacing.NORTH || te.getFacing() == EnumFacing.WEST)) {
				face.getFrom().clean();
				face.getTo().clean();
				face.getFrom().rotateZ(frameRotation);
				face.getTo().rotateZ(frameRotation);
				face.getFrom().applyRotation();
				face.getTo().applyRotation();
			}else if(frameRotation != 0 && (te.getFacing() == EnumFacing.SOUTH || te.getFacing() == EnumFacing.EAST)) {
				face.getFrom().clean();
				face.getTo().clean();
				face.getFrom().rotateX(frameRotation);
				face.getTo().rotateX(frameRotation);
				face.getFrom().applyRotation();
				face.getTo().applyRotation();
			}
			
			RenderHelper.addQuadToBuffer(buffer, 
					face.getFrom().getX(), face.getFrom().getY(), face.getFrom().getZ(), 
					face.getTo().getX(), face.getTo().getY(), face.getTo().getZ(), 
					face.getMinU(), face.getMaxU(), 
					face.getMinV(), face.getMaxV(), 
					bellows, 
					(face.getAvgLight() >> 16) & 0xFFFF, 
					face.getAvgLight() & 0xFFFF, 
					face.getFace());
		}
	}
	
}
