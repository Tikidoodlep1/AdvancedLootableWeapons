package com.tiki.advancedlootableweapons.proxy;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.DrumTESR;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import com.tiki.advancedlootableweapons.handlers.RenderHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;
import com.tiki.advancedlootableweapons.util.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import scala.actors.threadpool.Arrays;

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
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				NBTTagCompound tag = stack.getTagCompound();
				int[] colorArray;
				if(tag != null && tag.hasKey("colors")) {
					colorArray = tag.getIntArray("colors");
				}else {
					colorArray = new int[5];
					Arrays.fill(colorArray, 0xFFFFFF);
				
					if(stack.getItem() instanceof ToolSlashSword) {
						ItemStack repair = ((ToolSlashSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010;
							colorArray[1] = 0xFF424242;
							colorArray[2] = 0xFF696969;
							colorArray[3] = 0xFFBDBDBD;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00;
							colorArray[1] = 0xFF505000;
							colorArray[2] = 0xFFDEDE00;
							colorArray[3] = 0xFFFFFF8B;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730;
							colorArray[1] = 0xFF1B7B6B;
							colorArray[2] = 0xFF2CCDB1;
							colorArray[3] = 0xFF8CF4E2;
							colorArray[4] = 0xFFFFFFFF;
						}else {
							System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
						}
						((ToolSlashSword)stack.getItem()).setColors(stack, colorArray);
					}else if(stack.getItem() instanceof ToolStabSword) {
						ItemStack repair = ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010;
							colorArray[1] = 0xFF424242;
							colorArray[2] = 0xFF696969;
							colorArray[3] = 0xFFBDBDBD;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00;
							colorArray[1] = 0xFF505000;
							colorArray[2] = 0xFFDEDE00;
							colorArray[3] = 0xFFFFFF8B;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730;
							colorArray[1] = 0xFF1B7B6B;
							colorArray[2] = 0xFF2CCDB1;
							colorArray[3] = 0xFF8CF4E2;
							colorArray[4] = 0xFFFFFFFF;
						}else {
							System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
						}
						((ToolStabSword)stack.getItem()).setColors(stack, colorArray);
					}
				}
				return colorArray[tintIndex];
			}
			
		}, ItemInit.DAGGER_IRON, ItemInit.DAGGER_KOBOLD, ItemInit.DAGGER_COPPER, ItemInit.DAGGER_SILVER, ItemInit.DAGGER_BRONZE, ItemInit.DAGGER_PLATINUM, ItemInit.DAGGER_STEEL, ItemInit.DAGGER_SHADOW_PLATINUM, ItemInit.DAGGER_FROST_STEEL, ItemInit.DAGGER_OBSIDIAN, ItemInit.DAGGER_CRYSTALLITE, ItemInit.DAGGER_DUSKSTEEL,
				ItemInit.KABUTOWARI_IRON, ItemInit.KABUTOWARI_KOBOLD, ItemInit.KABUTOWARI_COPPER, ItemInit.KABUTOWARI_SILVER, ItemInit.KABUTOWARI_BRONZE, ItemInit.KABUTOWARI_PLATINUM, ItemInit.KABUTOWARI_STEEL, ItemInit.KABUTOWARI_SHADOW_PLATINUM, ItemInit.KABUTOWARI_FROST_STEEL, ItemInit.KABUTOWARI_OBSIDIAN, ItemInit.KABUTOWARI_CRYSTALLITE, ItemInit.KABUTOWARI_DUSKSTEEL,
				ItemInit.TALWAR_IRON, ItemInit.TALWAR_KOBOLD, ItemInit.TALWAR_COPPER, ItemInit.TALWAR_SILVER, ItemInit.TALWAR_BRONZE, ItemInit.TALWAR_PLATINUM, ItemInit.TALWAR_STEEL, ItemInit.TALWAR_SHADOW_PLATINUM, ItemInit.TALWAR_FROST_STEEL, ItemInit.TALWAR_OBSIDIAN, ItemInit.TALWAR_CRYSTALLITE, ItemInit.TALWAR_DUSKSTEEL,
				ItemInit.RAPIER_IRON, ItemInit.RAPIER_KOBOLD, ItemInit.RAPIER_COPPER, ItemInit.RAPIER_SILVER, ItemInit.RAPIER_BRONZE, ItemInit.RAPIER_PLATINUM, ItemInit.RAPIER_STEEL, ItemInit.RAPIER_SHADOW_PLATINUM, ItemInit.RAPIER_FROST_STEEL, ItemInit.RAPIER_OBSIDIAN, ItemInit.RAPIER_CRYSTALLITE, ItemInit.RAPIER_DUSKSTEEL,
				ItemInit.CLEAVER_IRON, ItemInit.CLEAVER_KOBOLD, ItemInit.CLEAVER_COPPER, ItemInit.CLEAVER_SILVER, ItemInit.CLEAVER_BRONZE, ItemInit.CLEAVER_PLATINUM, ItemInit.CLEAVER_STEEL, ItemInit.CLEAVER_SHADOW_PLATINUM, ItemInit.CLEAVER_FROST_STEEL, ItemInit.CLEAVER_OBSIDIAN, ItemInit.CLEAVER_CRYSTALLITE, ItemInit.CLEAVER_DUSKSTEEL,
				ItemInit.MACE_IRON, ItemInit.MACE_KOBOLD, ItemInit.MACE_COPPER, ItemInit.MACE_SILVER, ItemInit.MACE_BRONZE, ItemInit.MACE_PLATINUM, ItemInit.MACE_STEEL, ItemInit.MACE_SHADOW_PLATINUM, ItemInit.MACE_FROST_STEEL, ItemInit.MACE_OBSIDIAN, ItemInit.MACE_CRYSTALLITE, ItemInit.MACE_DUSKSTEEL,
				ItemInit.STAFF_IRON, ItemInit.STAFF_KOBOLD, ItemInit.STAFF_COPPER, ItemInit.STAFF_SILVER, ItemInit.STAFF_BRONZE, ItemInit.STAFF_PLATINUM, ItemInit.STAFF_STEEL, ItemInit.STAFF_SHADOW_PLATINUM, ItemInit.STAFF_FROST_STEEL, ItemInit.STAFF_OBSIDIAN, ItemInit.STAFF_CRYSTALLITE, ItemInit.STAFF_DUSKSTEEL,
				ItemInit.LONGSWORD_IRON, ItemInit.LONGSWORD_KOBOLD, ItemInit.LONGSWORD_COPPER, ItemInit.LONGSWORD_SILVER, ItemInit.LONGSWORD_BRONZE, ItemInit.LONGSWORD_PLATINUM, ItemInit.LONGSWORD_STEEL, ItemInit.LONGSWORD_SHADOW_PLATINUM, ItemInit.LONGSWORD_FROST_STEEL, ItemInit.LONGSWORD_OBSIDIAN, ItemInit.LONGSWORD_CRYSTALLITE, ItemInit.LONGSWORD_DUSKSTEEL,
				ItemInit.KODACHI_IRON, ItemInit.KODACHI_KOBOLD, ItemInit.KODACHI_COPPER, ItemInit.KODACHI_SILVER, ItemInit.KODACHI_BRONZE, ItemInit.KODACHI_PLATINUM, ItemInit.KODACHI_STEEL, ItemInit.KODACHI_SHADOW_PLATINUM, ItemInit.KODACHI_FROST_STEEL, ItemInit.KODACHI_OBSIDIAN, ItemInit.KODACHI_CRYSTALLITE, ItemInit.KODACHI_DUSKSTEEL,
				ItemInit.NODACHI_IRON, ItemInit.NODACHI_KOBOLD, ItemInit.NODACHI_COPPER, ItemInit.NODACHI_SILVER, ItemInit.NODACHI_BRONZE, ItemInit.NODACHI_PLATINUM, ItemInit.NODACHI_STEEL, ItemInit.NODACHI_SHADOW_PLATINUM, ItemInit.NODACHI_FROST_STEEL, ItemInit.NODACHI_OBSIDIAN, ItemInit.NODACHI_CRYSTALLITE, ItemInit.NODACHI_DUSKSTEEL,
				ItemInit.BATTLEAXE_IRON, ItemInit.BATTLEAXE_KOBOLD, ItemInit.BATTLEAXE_COPPER, ItemInit.BATTLEAXE_SILVER, ItemInit.BATTLEAXE_BRONZE, ItemInit.BATTLEAXE_PLATINUM, ItemInit.BATTLEAXE_STEEL, ItemInit.BATTLEAXE_SHADOW_PLATINUM, ItemInit.BATTLEAXE_FROST_STEEL, ItemInit.BATTLEAXE_OBSIDIAN, ItemInit.BATTLEAXE_CRYSTALLITE, ItemInit.BATTLEAXE_DUSKSTEEL,
				ItemInit.ZWEIHANDER_IRON, ItemInit.ZWEIHANDER_KOBOLD, ItemInit.ZWEIHANDER_COPPER, ItemInit.ZWEIHANDER_SILVER, ItemInit.ZWEIHANDER_BRONZE, ItemInit.ZWEIHANDER_PLATINUM, ItemInit.ZWEIHANDER_STEEL, ItemInit.ZWEIHANDER_SHADOW_PLATINUM, ItemInit.ZWEIHANDER_FROST_STEEL, ItemInit.ZWEIHANDER_OBSIDIAN, ItemInit.ZWEIHANDER_CRYSTALLITE, ItemInit.ZWEIHANDER_DUSKSTEEL,
				ItemInit.SABRE_IRON, ItemInit.SABRE_KOBOLD, ItemInit.SABRE_COPPER, ItemInit.SABRE_SILVER, ItemInit.SABRE_BRONZE, ItemInit.SABRE_PLATINUM, ItemInit.SABRE_STEEL, ItemInit.SABRE_SHADOW_PLATINUM, ItemInit.SABRE_FROST_STEEL, ItemInit.SABRE_OBSIDIAN, ItemInit.SABRE_CRYSTALLITE, ItemInit.SABRE_DUSKSTEEL,
				ItemInit.MAKHAIRA_IRON, ItemInit.MAKHAIRA_KOBOLD, ItemInit.MAKHAIRA_COPPER, ItemInit.MAKHAIRA_SILVER, ItemInit.MAKHAIRA_BRONZE, ItemInit.MAKHAIRA_PLATINUM, ItemInit.MAKHAIRA_STEEL, ItemInit.MAKHAIRA_SHADOW_PLATINUM, ItemInit.MAKHAIRA_FROST_STEEL, ItemInit.MAKHAIRA_OBSIDIAN, ItemInit.MAKHAIRA_CRYSTALLITE, ItemInit.MAKHAIRA_DUSKSTEEL,
				ItemInit.SPEAR_IRON, ItemInit.SPEAR_KOBOLD, ItemInit.SPEAR_COPPER, ItemInit.SPEAR_SILVER, ItemInit.SPEAR_BRONZE, ItemInit.SPEAR_PLATINUM, ItemInit.SPEAR_STEEL, ItemInit.SPEAR_SHADOW_PLATINUM, ItemInit.SPEAR_FROST_STEEL, ItemInit.SPEAR_OBSIDIAN, ItemInit.SPEAR_CRYSTALLITE, ItemInit.SPEAR_DUSKSTEEL
				);
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				NBTTagCompound tag = stack.getTagCompound();
				int[] colorArray;
				if(tag != null && tag.hasKey("colors")) {
					colorArray = tag.getIntArray("colors");
				}else {
					colorArray = new int[5];
					Arrays.fill(colorArray, 0xFFFFFF);
				
					if(stack.getItem() instanceof ToolSlashSword) {
						ItemStack repair = ((ToolSlashSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010;
							colorArray[1] = 0xFF424242;
							colorArray[2] = 0xFF696969;
							colorArray[3] = 0xFFBDBDBD;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00;
							colorArray[1] = 0xFF505000;
							colorArray[2] = 0xFFDEDE00;
							colorArray[3] = 0xFFFFFF8B;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730;
							colorArray[1] = 0xFF1B7B6B;
							colorArray[2] = 0xFF2CCDB1;
							colorArray[3] = 0xFF8CF4E2;
							colorArray[4] = 0xFFFFFFFF;
						}else {
							System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
						}
						((ToolSlashSword)stack.getItem()).setColors(stack, colorArray);
					}else if(stack.getItem() instanceof ToolStabSword) {
						ItemStack repair = ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010;
							colorArray[1] = 0xFF424242;
							colorArray[2] = 0xFF696969;
							colorArray[3] = 0xFFBDBDBD;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00;
							colorArray[1] = 0xFF505000;
							colorArray[2] = 0xFFDEDE00;
							colorArray[3] = 0xFFFFFF8B;
							colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730;
							colorArray[1] = 0xFF1B7B6B;
							colorArray[2] = 0xFF2CCDB1;
							colorArray[3] = 0xFF8CF4E2;
							colorArray[4] = 0xFFFFFFFF;
						}else {
							System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
						}
						((ToolStabSword)stack.getItem()).setColors(stack, colorArray);
					}
				}
				return colorArray[tintIndex];
			}
			
		}, ItemInit.generatedItems.toArray(new Item[0]) );
	}
	
	@Override
	public void registerCustomModelLoaders() {
		//ModelLoaderRegistry.registerLoader(ModelWeapon.Loader.INSTANCE);
		ItemInit.generatedItems.forEach((item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getGenericWeaponResourceLocation(item), "inventory")));
	}
	
	@Override
	public void modelBake(ModelBakeEvent event) {
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
	
	private ResourceLocation getGenericWeaponResourceLocation(Item item) {
		if(item.getRegistryName().getResourcePath().startsWith("dagger_")) {
			return new ResourceLocation(ModInfo.ID + ":dagger_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("kabutowari_")) {
			return new ResourceLocation(ModInfo.ID + ":kabutowari_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("talwar_")) {
			return new ResourceLocation(ModInfo.ID + ":talwar_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("rapier_")) {
			return new ResourceLocation(ModInfo.ID + ":rapier_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("mace_")) {
			return new ResourceLocation(ModInfo.ID + ":mace_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("cleaver_")) {
			return new ResourceLocation(ModInfo.ID + ":cleaver_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("staff_")) {
			return new ResourceLocation(ModInfo.ID + ":staff_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("longsword_")) {
			return new ResourceLocation(ModInfo.ID + ":longsword_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("kodachi_")) {
			return new ResourceLocation(ModInfo.ID + ":kodachi_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("nodachi_")) {
			return new ResourceLocation(ModInfo.ID + ":nodachi_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("battleaxe_")) {
			return new ResourceLocation(ModInfo.ID + ":battleaxe_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("zweihander_")) {
			return new ResourceLocation(ModInfo.ID + ":zweihander_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("sabre_")) {
			return new ResourceLocation(ModInfo.ID + ":sabre_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("makhaira_")) {
			return new ResourceLocation(ModInfo.ID + ":makhaira_extra");
		}else if(item.getRegistryName().getResourcePath().startsWith("spear_")) {
			return new ResourceLocation(ModInfo.ID + ":spear_extra");
		}
		return item.getRegistryName();
	}
}
