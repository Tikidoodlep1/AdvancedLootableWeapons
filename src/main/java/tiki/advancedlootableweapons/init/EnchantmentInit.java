package tiki.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.enchantments.EnchantmentLight;
import tiki.advancedlootableweapons.enchantments.EnchantmentRefined;

@Mod.EventBusSubscriber(modid=ModInfo.ID)
public class EnchantmentInit {

	public static final List<Enchantment> enchantments = new ArrayList<Enchantment>();
	
	public static final Enchantment REFINED = new EnchantmentRefined();
	public static final Enchantment LIGHT = new EnchantmentLight();
}
