package tiki.advancedlootableweapons.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tiki.advancedlootableweapons.ModInfo;

public class SoundHandler {

	public static SoundEvent SHADOW_DROP, SHARPENING_STONE, JAW_CRUSHER, BELLOWS, QUENCH;
	
	public static void registerSounds() {
		SHADOW_DROP = registerSound("shadow_drop");
		SHARPENING_STONE = registerSound("sharpening_stone");
		JAW_CRUSHER = registerSound("jaw_crusher");
		BELLOWS = registerSound("bellows");
		QUENCH = registerSound("quench");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(ModInfo.ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
