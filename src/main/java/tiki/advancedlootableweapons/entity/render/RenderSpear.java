package tiki.advancedlootableweapons.entity.render;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.entity.EntitySpear;
import tiki.advancedlootableweapons.entity.models.ModelSpear;

public class RenderSpear extends RenderThrownItem<EntitySpear> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/entity/spear.png");
	
	public RenderSpear(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSpear(), 0.3F, 0, TEXTURES);
	}
	
	@Override
	public void doRender(EntitySpear entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		
		int color;
		if(entity.getTagData().getIntArray("colors") != null && entity.getTagData().getIntArray("colors").length >= 3) {
			color = entity.getTagData().getIntArray("colors")[2];
		}else {
			color = 0xFFFFFFFF;
		}
		Alw.logger.debug("Rendering spear! Colors: " + entity.getTagData().getIntArray("colors") == null ? "null" : Arrays.toString(entity.getTagData().getIntArray("colors")) + " === Color: " + Integer.toHexString(color));
		GL11.glColor4f( ((color >> 16) & 0xFF) / 255F, ((color >> 8) & 0xFF) / 255F, ((color) & 0xFF) / 255F, ((color >> 24) & 0xFF) / 255F);
		super.doRender(entity, x, y, z, yaw, partialTicks);
		renderEntityModel(entity, x, y, z, yaw, partialTicks);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpear entity) {
		if(entity == null) {
			return new ResourceLocation(ModInfo.ID + ":textures/entity/extra_spear.png");
		}
		switch(entity.getMaterial()) {
		case "WOOD":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/wood_spear.png");
		case "IRON":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/iron_spear.png");
		case "mat_kobold":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/kobold_spear.png");
		case "mat_copper":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/copper_spear.png");
		case "mat_silver":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/silver_spear.png");
		case "mat_bronze":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/bronze_spear.png");
		case "mat_platinum":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/platinum_spear.png");
		case "mat_steel":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/steel_spear.png");
		case "mat_shadow_platinum":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/shadow_platinum_spear.png");
		case "mat_frost_steel":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/frost_steel_spear.png");
		case "mat_obsidian":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/obsidian_spear.png");
		case "mat_crystallite":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/crystallite_spear.png");
		case "mat_dusksteel":
			return new ResourceLocation(ModInfo.ID + ":textures/entity/dusksteel_spear.png");
		default:
			return new ResourceLocation(ModInfo.ID + ":textures/entity/extra_spear.png");
		}
	}
}
