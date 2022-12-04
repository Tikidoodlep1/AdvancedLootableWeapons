package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.blocks.tileentities.DrumTESR;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.handlers.RenderHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void addColoredItemRenderer() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ItemArmor)stack.getItem()).getColor(stack);
            }
        }, ItemInit.HELMET_LEATHER, ItemInit.CHESTPLATE_LEATHER, ItemInit.LEGGINGS_LEATHER, ItemInit.BOOTS_LEATHER, 
        ItemInit.DIAMOND_STUDDED_LEATHER_HELMET, ItemInit.DIAMOND_STUDDED_LEATHER_CHESTPLATE, ItemInit.DIAMOND_STUDDED_LEATHER_LEGGINGS, ItemInit.DIAMOND_STUDDED_LEATHER_BOOTS);
	}
	
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
	
	@Override
	public void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDrum.class, new DrumTESR());
	}
	
}
