package tiki.rotn.advancedlootableweapons.proxy;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.commands.SeeToolMatsCommand;
import tiki.rotn.advancedlootableweapons.handlers.ConfigHandler;
import tiki.rotn.advancedlootableweapons.init.ItemInit;
import tiki.rotn.advancedlootableweapons.loot.LootTableInjector;

public class CommonProxy {
	
	public void addColoredItemRenderer() {}
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void registerModel(Item item, int metadata) {}
	
	public void registerEntityRenders() {}
	
	public void registerCustomMeshesAndStateStuff() {}
	
	public void registerTESRs() {}
	
	public void modelBake(final ModelBakeEvent event) {}
	
	public void registerCustomModelLoaders() {}
	
	public void onTooltip(ItemTooltipEvent event) {}
	
	public void handleRequestContainerID(int id) {}
	
	public void initDynamicResources() {}
	
	public void registerCommands(final FMLServerStartingEvent e) {
		e.registerServerCommand(new SeeToolMatsCommand());
	}
	
	public void onBlockAttemptBreak(final LeftClickBlock event) {
		if(event.getSide() == Side.SERVER) {
			BlockPos blockPos = event.getPos();
			Block block = event.getWorld().getBlockState(blockPos).getBlock();
			if(ConfigHandler.VALID_ANVILS.contains(block.getRegistryName().toString())) {
				EntityPlayer player = event.getEntityPlayer();
				if(ConfigHandler.VALID_HAMMERS.contains(player.inventory.getCurrentItem().getItem().getRegistryName().toString())) {
					player.openGui(Alw.instance, ModInfo.GUI_FORGE_WEAPON, player.getEntityWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
					event.setCanceled(true);
				}
			}
		}
		
		if(event.getSide() == Side.CLIENT) {
			BlockPos blockPos = event.getPos();
			Block block = event.getWorld().getBlockState(blockPos).getBlock();
			if(ConfigHandler.VALID_ANVILS.contains(block.getRegistryName().toString())) {
				EntityPlayer player = event.getEntityPlayer();
				if(ConfigHandler.VALID_HAMMERS.contains(player.inventory.getCurrentItem().getItem().getRegistryName().toString())) {
					event.setCanceled(true); // Cancel event but don't open GUI so the GUI can correctly pick the anvil block instead of AIR!
				}
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
				ResourceLocation[] recipes = {new ResourceLocation("minecraft", "leather_helmet"), new ResourceLocation("minecraft", "leather_chestplate"),
						new ResourceLocation("minecraft", "leather_leggings"), new ResourceLocation("minecraft", "leather_boots"),
						new ResourceLocation("minecraft", "iron_helmet"), new ResourceLocation("minecraft", "iron_chestplate"),
						new ResourceLocation("minecraft", "iron_leggings"), new ResourceLocation("minecraft", "iron_boots"),
						new ResourceLocation("minecraft", "golden_helmet"), new ResourceLocation("minecraft", "golden_chestplate"),
						new ResourceLocation("minecraft", "golden_leggings"), new ResourceLocation("minecraft", "golden_boots"),
						new ResourceLocation("minecraft", "diamond_helmet"), new ResourceLocation("minecraft", "diamond_chestplate"),
						new ResourceLocation("minecraft", "diamond_leggings"), new ResourceLocation("minecraft", "diamond_boots")};
				event.getEntityPlayer().unlockRecipes(recipes);
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
		
	}
	
	public void onLootTableLoad(final LootTableLoadEvent event) {
		LootTableInjector.InjectLoot(event);
	}
}
