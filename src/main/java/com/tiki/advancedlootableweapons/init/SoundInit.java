package com.tiki.advancedlootableweapons.init;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AdvancedLootableWeapons.MODID);


	public static final RegistryObject<SoundEvent> SHADOW_DROP = registerSound("shadow_drop");
    public static final RegistryObject<SoundEvent> SHARPENING_STONE= registerSound("sharpening_stone");
    public static final RegistryObject<SoundEvent> JAW_CRUSHER = registerSound("jaw_crusher");
    public static final RegistryObject<SoundEvent> BELLOWS = registerSound("bellows");
    public static final RegistryObject<SoundEvent> QUENCH =registerSound("quench");
	
	public static void register(IEventBus bus) {
		SOUNDS.register(bus);
	}
	
	private static RegistryObject<SoundEvent> registerSound(String name) {
		ResourceLocation location = AdvancedLootableWeapons.id(name);
		return SOUNDS.register(name,() -> new SoundEvent(location));
	}
}
