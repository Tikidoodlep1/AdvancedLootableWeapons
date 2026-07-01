package tiki.advancedlootableweapons.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.commands.SeeToolMatErrorsCommand;
import tiki.advancedlootableweapons.commands.SeeToolMatsCommand;
import tiki.advancedlootableweapons.compat.oreDictionary.OreDictionaryCompat;
import tiki.advancedlootableweapons.compat.patchouli.PatchouliCompat;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.loot.LootTableInjector;

public class CommonProxy {
	
//	public void onOreRegister(OreDictionary.OreRegisterEvent event) {
//		Alw.logger.info("Registering OreDictionary item " + event.getOre().getItem().getRegistryName() + "*" + event.getOre().getCount() + " to ore name " + event.getName());
//		
//		String s = "OreDict " + event.getName() + ": ";
//		for(ItemStack stack : OreDictionary.getOres(event.getName())) {
//			s += stack.getItem().getRegistryName() + "*" + stack.getCount() + ", ";
//		}
//		Alw.logger.info(s);
//	}
	public void initColoredItemRendererLists() {}
	
	public void addColoredItemRenderer() {}
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void registerModel(Item item, int metadata) {}
	
	public void registerEntityRenders() {}
	
	public void registerCustomMeshesAndStateStuff() {}
	
	public void registerTESRs() {}
	
	public void registerTEISRs() {}
	
	public void onModelBake(final ModelBakeEvent event) {}
	
	public void onModelRegister(final ModelRegistryEvent event) {}
	
	public void onTextureStitch(final TextureStitchEvent.Pre event) {}
	
	public void registerCustomModelLoaders() {}
	
	public void onTooltip(ItemTooltipEvent event) {}
	
	public void handleRequestContainerID(int id) {}
	
	public void initDynamicResources() {}
	
	public void registerCommands(final FMLServerStartingEvent e) {
		e.registerServerCommand(new SeeToolMatsCommand());
		e.registerServerCommand(new SeeToolMatErrorsCommand());
	}
	
	public void onBlockAttemptBreak(final LeftClickBlock event) {
		BlockPos blockPos = event.getPos();
		Block block = event.getWorld().getBlockState(blockPos).getBlock();
		
		if(OreDictionaryCompat.anvilOres.contains(new ItemStack(block).getItem())) {
			EntityPlayer player = event.getEntityPlayer();
			if(OreDictionaryCompat.hammerOres.contains(player.inventory.getCurrentItem().getItem())) {
				if(event.getSide() == Side.SERVER) {
					player.openGui(Alw.instance, ModInfo.GUI_FORGE_WEAPON, player.getEntityWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
				}
				event.setCanceled(true);
			}
		}
		
	}

	public void onBlockDrops(final HarvestDropsEvent event) {
		Block block = event.getState().getBlock();
		if(block instanceof BlockObsidian) {
			if(event.isSilkTouching()) {
				return;
			}
			
			Random rand = new Random();
			List<ItemStack> drops = event.getDrops();
			int count;
			int max = 4;
			drops.clear();
			count = rand.nextInt(max) + 1;
			drops.add(new ItemStack(ItemInit.SHARD_OBSIDIAN, count));
		}
	}
	
	public void onPlayerClone(final PlayerEvent.Clone event) {
		if(event.getEntityPlayer() instanceof EntityPlayerMP) {
			if(!ConfigHandler.DISABLE_VANILLA_ARMORS) {
                ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
                IRecipe leather_helmet = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "leather_helmet"));
                if(leather_helmet != null) {
                    recipes.add(leather_helmet);
                }
                IRecipe leather_chestplate = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "leather_chestplate"));
                if(leather_chestplate != null) {
                    recipes.add(leather_chestplate);
                }
                IRecipe leather_leggings = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "leather_leggings"));
                if(leather_leggings != null) {
                    recipes.add(leather_leggings);
                }
                IRecipe leather_boots = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "leather_boots"));
                if(leather_boots != null) {
                    recipes.add(leather_boots);
                }

                IRecipe iron_helmet = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "iron_helmet"));
                if(iron_helmet != null) {
                    recipes.add(iron_helmet);
                }
                IRecipe iron_chestplate = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "iron_chestplate"));
                if(iron_chestplate != null) {
                    recipes.add(iron_chestplate);
                }
                IRecipe iron_leggings = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "iron_leggings"));
                if(iron_leggings != null) {
                    recipes.add(iron_leggings);
                }
                IRecipe iron_boots = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "iron_boots"));
                if(iron_boots != null) {
                    recipes.add(iron_boots);
                }

                IRecipe gold_helmet = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "gold_helmet"));
                if(gold_helmet != null) {
                    recipes.add(gold_helmet);
                }
                IRecipe gold_chestplate = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "gold_chestplate"));
                if(gold_chestplate != null) {
                    recipes.add(gold_chestplate);
                }
                IRecipe gold_leggings = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "gold_leggings"));
                if(gold_leggings != null) {
                    recipes.add(gold_leggings);
                }
                IRecipe gold_boots = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "gold_boots"));
                if(gold_boots != null) {
                    recipes.add(gold_boots);
                }

                IRecipe diamond_helmet = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "diamond_helmet"));
                if(diamond_helmet != null) {
                    recipes.add(diamond_helmet);
                }
                IRecipe diamond_chestplate = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "diamond_chestplate"));
                if(diamond_chestplate != null) {
                    recipes.add(diamond_chestplate);
                }
                IRecipe diamond_leggings = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "diamond_leggings"));
                if(diamond_leggings != null) {
                    recipes.add(diamond_leggings);
                }
                IRecipe diamond_boots = ForgeRegistries.RECIPES.getValue(new ResourceLocation("minecraft", "diamond_boots"));
                if(diamond_boots != null) {
                    recipes.add(diamond_boots);
                }

                event.getEntityPlayer().unlockRecipes(recipes);
			}

            if(Alw.isPatchouliLoaded) {
                if(PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers") != null
                    && !PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers").isEmpty()) {
                    event.getEntityPlayer().addItemStackToInventory(PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers"));
                }
            }
		}
		
//		event.getEntityPlayer().getArmorInventoryList().forEach((stack) -> {
//			int i = 1;
//			if(!stack.isEmpty() && stack.getItem() instanceof ArmorBonusesBase && ConfigHandler.USE_ARMOR_BONUS_HEALTH) {
//				//event.getEntityPlayer().setHealth(event.getEntityPlayer().getHealth() + (float)((ArmorBonusesBase)stack.getItem()).getBonusHealth() );
//				event.getEntityPlayer().getEntityAttribute(Alw.HEAD_MAX_HEALTH_MODIFIER)
//			}
//			Alw.logger.info("Armor slot " + i++ + " is " + (stack.isEmpty() ? "empty" : stack.getDisplayName()) + ", player health is: " + ((EntityPlayer)event.getEntity()).getHealth() );
//		});// end forEach
		
		
//		String s = "onPlayerClone OreDict ingotSteel: ";
//		for(ItemStack stack : OreDictionary.getOres("ingotSteel")) {
//			s += stack.getItem().getRegistryName() + "*" + stack.getCount() + ", ";
//		}
//		Alw.logger.info(s);
	}
	
	public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
		if(ItemInit.erroredToolMaterials.size() > 0) {
			String errMats = Arrays.toString(ItemInit.erroredToolMaterials.toArray(new String[0]));
			errMats = errMats.replace("[", "");
			errMats = errMats.replace("]", "");
			event.getEntity().sendMessage(new TextComponentString(TextFormatting.RED + "GENERATED TOOL MATERIAL ERRORS: You can see this message again by typing /materialerrors. Tool Materials " + errMats + " were added to the generated materials in the Alw Weapon config but have no crafting and repair item. Please specify a crafting/repair item. The weapons were unable to be generated for these materials."));
		}

        if(Alw.isPatchouliLoaded) {
            if(event.getEntity() instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP)event.getEntity();

                NBTTagCompound persistentData = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
                Alw.logger.info("Persistent Player Data: " + persistentData.toString());

                if(!persistentData.getBoolean("firstJoin")) {
                    if(PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers") != null
                            && !PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers").isEmpty()) {
                        player.addItemStackToInventory(PatchouliCompat.api.getBookStack("advancedlootableweapons:smiths_and_leatherworkers"));
                    }
                }

                persistentData.setBoolean("firstJoin", true);
                player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, persistentData);
            }

        }
	}
	
	public void onLootTableLoad(final LootTableLoadEvent event) {
		LootTableInjector.InjectLoot(event);
	}
}
