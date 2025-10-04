package tiki.advancedlootableweapons.handlers;

import net.minecraft.client.Minecraft;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.particle.ParticleAirBubble;

public class ParticleHandler {

	public static void RegisterParticles() {
		Minecraft.getMinecraft().effectRenderer.registerParticle(ModInfo.PARTICLE_BUBBLE, new ParticleAirBubble.Factory());
	}
	
}
