package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.blocks.tileentities.DrumTESR;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.handlers.RenderHandler;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void registerModel(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	@Override
	public void registerEntityRenders() {
		RenderHandler.registerEntityRenders();
	}
	
	@Override
	public void registerCustomMeshesAndStateStuff() {
		RenderHandler.registerCustomMeshesAndStates();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ItemInit.items) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block: BlockInit.blocks) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDrum.class, new DrumTESR());
	}
	
}
