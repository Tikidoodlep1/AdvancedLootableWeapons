package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.entity.render.RenderSpear;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new IRenderFactory<EntitySpear>() {
			@Override
			public Render<? super EntitySpear> createRenderFor(RenderManager manager){
				return new RenderSpear(manager);
			}
		});
	}
}
