package com.tiki.advancedlootableweapons.init;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.menu.*;
import com.tiki.advancedlootableweapons.inventory.AnvilForgingMenu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {

	public static final DeferredRegister<MenuType<?>> GUIS = DeferredRegister.create(ForgeRegistries.CONTAINERS, AdvancedLootableWeapons.MODID);
	
	public static final RegistryObject<MenuType<AlloyFurnaceMenu>> ALLOY_FURNACE = registerMenuType(AlloyFurnaceMenu::new, "alloy_furnace");
	
	public static final RegistryObject<MenuType<ForgeMenu>> FORGE = registerMenuType(ForgeMenu::new, "forge");
	public static final RegistryObject<MenuType<AdvancedForgeMenu>> ADVANCED_FORGE = registerMenuType(AdvancedForgeMenu::new, "advanced_forge");
	public static final RegistryObject<MenuType<TanningRackMenu>> TANNING_RACK = registerMenuType(TanningRackMenu::new, "tanning_rack");


	public static final RegistryObject<MenuType<AnvilForgingMenu>> ANVIL_FORGING = registerMenuType(AnvilForgingMenu::new, "anvil_forging");

	public static final RegistryObject<MenuType<JawCrusherMenu>> JAW_CRUSHER = registerMenuType(JawCrusherMenu::new, "jaw_crusher");
	public static final RegistryObject<MenuType<WhetstoneMenu>> WHETSTONE = registerMenuType(WhetstoneMenu::new,"whetstone");
	
	private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(MenuType.MenuSupplier<T> factory, String name) {
		return GUIS.register(name,  () -> new MenuType<>(factory));
	}
	
	public static void register(IEventBus bus) {
		GUIS.register(bus);
	}
}
