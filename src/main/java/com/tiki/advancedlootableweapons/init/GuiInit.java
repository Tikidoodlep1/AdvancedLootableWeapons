package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeContainer;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GuiInit {

	public static final DeferredRegister<MenuType<?>> GUIS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModInfo.ID);
	
	public static final RegistryObject<MenuType<AlloyFurnaceContainer>> ALLOY_FURNACE_CONTAINER = registerMenuType(AlloyFurnaceContainer::new, "alloy_furnace_container");
	
	public static final RegistryObject<MenuType<ForgeContainer>> FORGE_CONTAINER = registerMenuType(ForgeContainer::new, "forge_container");
	
	
	private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
		return GUIS.register(name,  () -> IForgeMenuType.create(factory));
	}
	
	public static void register(IEventBus bus) {
		GUIS.register(bus);
	}
}
