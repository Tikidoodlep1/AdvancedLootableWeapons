package com.tiki.advancedlootableweapons.proxy;

import java.util.List;
import java.util.Random;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.handlers.GlobalDropsHandler;
import com.tiki.advancedlootableweapons.handlers.SoundHandler;
import com.tiki.advancedlootableweapons.init.EntityInit;
import com.tiki.advancedlootableweapons.init.FluidInit;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import com.tiki.advancedlootableweapons.world.WorldGenCustomOres;

import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void registerModel(Item item, int metadata) {}
	
	public void registerEntityRenders() {}
	
	public void registerCustomMeshesAndStateStuff() {}
	
	public void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
		EntityInit.registerEntities();
		ConfigHandler.registerConfig(event);
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		registerEntityRenders();
	}
	
	public void onBlockAttemptBreak(LeftClickBlock event){		
		BlockPos blockPos = event.getPos();
		Block block = event.getWorld().getBlockState(blockPos).getBlock();
		if(block == Blocks.ANVIL) {
			EntityPlayer player = event.getEntityPlayer();
			if(player.inventory.getCurrentItem().getItem() instanceof ToolForgeHammer) {
				player.openGui(Alw.instance, ModInfo.GUI_FORGE_WEAPON, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
				event.setCanceled(true);
			}
		}
	}
	
	public void onEntityDrops(LivingDropsEvent event) {
		Random rand = new Random();
		boolean containsLeather = false;
		int leatherCount = 0;
		for(EntityItem e : event.getDrops()) {
			if(e.getItem().getItem() == Items.LEATHER) {
				leatherCount = e.getItem().getCount();
				containsLeather = true;
				event.getDrops().remove(e);
				break;
			}
		}
		if(containsLeather) {
			event.getEntity().entityDropItem(new ItemStack(ItemInit.UNTRIMMED_HIDE, (leatherCount*2)+rand.nextInt(event.getLootingLevel() + 1)), 0.25F);
		}
		if((rand.nextInt(100) + 1) <= (ConfigHandler.SHADOW_DROP_RATE*100) && GlobalDropsHandler.getEntityMap().get(event.getEntity().getClass())) {
			Entity entity = event.getEntity();
			if(event.getLootingLevel() > 0) {
				entity.entityDropItem(new ItemStack(ItemInit.SHADOW, rand.nextInt(event.getLootingLevel() + 1)), 0.25F);
				entity.playSound(SoundHandler.SHADOW_DROP, 1.0F, 1.0F);
			}else {
				entity.entityDropItem(new ItemStack(ItemInit.SHADOW, 1), 0.25F);
				entity.playSound(SoundHandler.SHADOW_DROP, 1.0F, 1.0F);
			}
		}
	}

	public void onBlockDrops(HarvestDropsEvent event) {
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
}
