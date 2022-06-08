package com.tiki.advancedlootableweapons.entity.render;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.entity.models.ModelSpear;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSpear extends RenderThrownItem<EntitySpear>{
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/entity/spear.png");
	
	public RenderSpear(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSpear(), 0.3F, 0, TEXTURES);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpear entity) {
		if(entity == null) {
			return new ResourceLocation(ModInfo.ID + ":textures/entity/spear.png");
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
			return new ResourceLocation(ModInfo.ID + ":textures/entity/spear.png");
		}
	}
}
