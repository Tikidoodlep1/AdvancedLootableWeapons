package tiki.advancedlootableweapons.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.entity.EntitySpear;

public class EntityInit {

	public static void registerEntities() {
		registerEntity("spear", EntitySpear.class, 100, 50);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range) {
		EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.ID + ":" + name), entity, name, id, Alw.instance, range, 1, true);
	}
}
