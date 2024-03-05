package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.inventory.AnvilForgingMenu;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeContainer;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {

	public static final DeferredRegister<MenuType<?>> GUIS = DeferredRegister.create(ForgeRegistries.CONTAINERS, AdvancedLootableWeapons.MODID);
	
	public static final RegistryObject<MenuType<AlloyFurnaceContainer>> ALLOY_FURNACE = registerMenuType(AlloyFurnaceContainer::new, "alloy_furnace");
	
	public static final RegistryObject<MenuType<ForgeContainer>> FORGE = registerMenuType(ForgeContainer::new, "forge");
	
	public static final RegistryObject<MenuType<AnvilForgingMenu>> ANVIL_FORGING = registerMenuType(AnvilForgingMenu::new, "anvil_forging");
	
	public static final RegistryObject<MenuType<JawCrusherContainer>> JAW_CRUSHER = registerMenuType(JawCrusherContainer::new, "jaw_crusher");
	
	
	private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(MenuType.MenuSupplier<T> factory, String name) {
		return GUIS.register(name,  () -> new MenuType<>(factory));
	}
	
	public static void register(IEventBus bus) {
		GUIS.register(bus);
	}
}
