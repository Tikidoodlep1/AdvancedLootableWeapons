package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.entity.EntitySpear;
import com.tiki.advancedlootableweapons.entity.render.RenderSpear;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.model.ModelLoader;
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
	
	public static void registerCustomMeshesAndStates() {		
		ModelLoader.setCustomStateMapper(BlockInit.milk_of_lime, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(ModInfo.ID + ":milk_of_lime", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.magnesium_lactate, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(ModInfo.ID + ":magnesium_lactate", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.water_block_display, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(ModInfo.ID + ":water_display", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.lava_block_display, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(ModInfo.ID + ":lava_display", "fluid");
			}
		});
	}
	
}
