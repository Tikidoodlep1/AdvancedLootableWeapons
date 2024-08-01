package com.tiki.advancedlootableweapons.init;


import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

	public static SoundEvent SHADOW_DROP, SHARPENING_STONE, JAW_CRUSHER, BELLOWS, QUENCH;
	
	public static void registerSounds() {
		SHADOW_DROP = registerSound("shadow_drop");
		SHARPENING_STONE = registerSound("sharpening_stone");
		JAW_CRUSHER = registerSound("jaw_crusher");
		BELLOWS = registerSound("bellows");
		QUENCH = registerSound("quench");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = AdvancedLootableWeapons.id( name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
