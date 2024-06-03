package tiki.advancedlootableweapons.proxy;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.tileentities.DrumTESR;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityDrum;
import tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.handlers.RenderHandler;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.items.ItemHotToolHead;
import tiki.advancedlootableweapons.tools.ToolForgeHammer;
import tiki.advancedlootableweapons.tools.ToolSlashSword;
import tiki.advancedlootableweapons.tools.ToolStabSword;
import tiki.advancedlootableweapons.util.ColorUtils;

public class ClientProxy extends CommonProxy {
	
	Item[] weapons = ItemInit.items.stream().filter(new Predicate<Item>() {
		@Override
		public boolean test(Item t) {
			if(t instanceof ToolStabSword) {
				return ((ToolStabSword)t).getToolMaterial() != ToolMaterial.WOOD;
			}else if(t instanceof ToolSlashSword) {
				return ((ToolSlashSword)t).getToolMaterial() != ToolMaterial.WOOD;
			}
			return false;
		}
	}).collect(Collectors.toList()).toArray(new Item[0]);
	
	Item[] toolHeads = ItemInit.items.stream().filter(new Predicate<Item>() {
		@Override
		public boolean test(Item t) {
			if(t instanceof ItemHotToolHead && ((ItemHotToolHead)t).finished && ((ItemHotToolHead)t).isMain) {
				return true;
			}
			return false;
		}
	}).collect(Collectors.toList()).toArray(new Item[0]);
	
	@Override
	public void addColoredItemRenderer() {
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : ((ItemArmor)stack.getItem()).getColor(stack);
            }
        }, ItemInit.HELMET_LEATHER, ItemInit.CHESTPLATE_LEATHER, ItemInit.LEGGINGS_LEATHER, ItemInit.BOOTS_LEATHER, 
        ItemInit.DIAMOND_STUDDED_LEATHER_HELMET, ItemInit.DIAMOND_STUDDED_LEATHER_CHESTPLATE, ItemInit.DIAMOND_STUDDED_LEATHER_LEGGINGS, ItemInit.DIAMOND_STUDDED_LEATHER_BOOTS);
		
		if(!ConfigHandler.USE_LEGACY_TEXTURES) {
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
								colorArray[0] = 0xFF101010; colorArray[1] = 0xFF424242; colorArray[2] = 0xFF696969; colorArray[3] = 0xFFBDBDBD; colorArray[4] = 0xFFFFFFFF;
							}else if(repair.getItem() == Items.GOLD_INGOT) {
								colorArray[0] = 0xFF3C3C00; colorArray[1] = 0xFF505000; colorArray[2] = 0xFFDEDE00; colorArray[3] = 0xFFFFFF8B; colorArray[4] = 0xFFFFFFFF;
							}else if(repair.getItem() == Items.DIAMOND) {
								colorArray[0] = 0xFF0C3730; colorArray[1] = 0xFF1B7B6B; colorArray[2] = 0xFF2CCDB1; colorArray[3] = 0xFF8CF4E2; colorArray[4] = 0xFFFFFFFF;
							}else {
								System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
							}
							((ToolSlashSword)stack.getItem()).setColors(stack, colorArray);
						}else if(stack.getItem() instanceof ToolStabSword) {
							ItemStack repair = ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack();
							if(repair.getItem() == Items.IRON_INGOT) {
								colorArray[0] = 0xFF101010; colorArray[1] = 0xFF424242; colorArray[2] = 0xFF696969; colorArray[3] = 0xFFBDBDBD; colorArray[4] = 0xFFFFFFFF;
							}else if(repair.getItem() == Items.GOLD_INGOT) {
								colorArray[0] = 0xFF3C3C00; colorArray[1] = 0xFF505000; colorArray[2] = 0xFFDEDE00; colorArray[3] = 0xFFFFFF8B; colorArray[4] = 0xFFFFFFFF;
							}else if(repair.getItem() == Items.DIAMOND) {
								colorArray[0] = 0xFF0C3730; colorArray[1] = 0xFF1B7B6B; colorArray[2] = 0xFF2CCDB1; colorArray[3] = 0xFF8CF4E2; colorArray[4] = 0xFFFFFFFF;
							}else {
								System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
							}
							((ToolStabSword)stack.getItem()).setColors(stack, colorArray);
						}
					}
					return colorArray[tintIndex];
				}
				
			}, weapons);
		}
		
		
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
						ItemStack repair;
						if(ItemInit.customRepairItems.containsKey(((ToolSlashSword)stack.getItem()).getToolMaterial())) {
							repair = ItemInit.customRepairItems.get(((ToolSlashSword)stack.getItem()).getToolMaterial());
						}else {
							repair = ((ToolSlashSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						}
						
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010; colorArray[1] = 0xFF424242; colorArray[2] = 0xFF696969; colorArray[3] = 0xFFBDBDBD; colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00; colorArray[1] = 0xFF505000; colorArray[2] = 0xFFDEDE00; colorArray[3] = 0xFFFFFF8B; colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730; colorArray[1] = 0xFF1B7B6B; colorArray[2] = 0xFF2CCDB1; colorArray[3] = 0xFF8CF4E2; colorArray[4] = 0xFFFFFFFF;
						}else {
							System.arraycopy(ColorUtils.getColorPalateFromItemStack(repair), 0, colorArray, 0, 5); 
						}
						((ToolSlashSword)stack.getItem()).setColors(stack, colorArray);
					}else if(stack.getItem() instanceof ToolStabSword) {
						ItemStack repair;
						if(ItemInit.customRepairItems.containsKey(((ToolStabSword)stack.getItem()).getToolMaterial())) {
							repair = ItemInit.customRepairItems.get(((ToolStabSword)stack.getItem()).getToolMaterial());
						}else {
							repair = ((ToolStabSword)stack.getItem()).getToolMaterial().getRepairItemStack();
						}
						
						if(repair.getItem() == Items.IRON_INGOT) {
							colorArray[0] = 0xFF101010; colorArray[1] = 0xFF424242; colorArray[2] = 0xFF696969; colorArray[3] = 0xFFBDBDBD; colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.GOLD_INGOT) {
							colorArray[0] = 0xFF3C3C00; colorArray[1] = 0xFF505000; colorArray[2] = 0xFFDEDE00; colorArray[3] = 0xFFFFFF8B; colorArray[4] = 0xFFFFFFFF;
						}else if(repair.getItem() == Items.DIAMOND) {
							colorArray[0] = 0xFF0C3730; colorArray[1] = 0xFF1B7B6B; colorArray[2] = 0xFF2CCDB1; colorArray[3] = 0xFF8CF4E2; colorArray[4] = 0xFFFFFFFF;
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
	public void initDynamicResources() {
		ZenDynamicAlwResources.init();
	}
	
	@Override
	public void registerCustomModelLoaders() {
		if(!ConfigHandler.USE_LEGACY_TEXTURES) {
			Arrays.stream(weapons).forEach((item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getGenericWeaponResourceLocation(item), "inventory")));
		}
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
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMill.class, new MillTESR());
	}
	
	public void onTooltip(ItemTooltipEvent event) {
		if(!(event.getItemStack().getItem() instanceof ToolForgeHammer) && ConfigHandler.VALID_HAMMERS.contains(event.getItemStack().getItem().getRegistryName().toString())) {
			event.getToolTip().add(TextFormatting.BLUE + I18n.format("alw.forge_hammer.tooltip"));
			event.getToolTip().add(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + I18n.format("alw.forge_hammer.config_marker.name"));
		}else if(event.getItemStack().getItem() != Item.getItemFromBlock(Blocks.ANVIL) && ConfigHandler.VALID_ANVILS.contains(event.getItemStack().getItem().getRegistryName().toString())) {
			event.getToolTip().add(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + I18n.format("alw.anvil.config_marker.name"));
		}
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
