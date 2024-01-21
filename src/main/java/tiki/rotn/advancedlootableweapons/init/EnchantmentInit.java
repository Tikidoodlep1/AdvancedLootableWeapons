package tiki.rotn.advancedlootableweapons.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.enchantments.EnchantmentLight;
import tiki.rotn.advancedlootableweapons.enchantments.EnchantmentRefined;

@Mod.EventBusSubscriber(modid=ModInfo.ID)
public class EnchantmentInit {

	public static final List<Enchantment> enchantments = new ArrayList<Enchantment>();
	
	public static final Enchantment REFINED = new EnchantmentRefined();
	public static final Enchantment LIGHT = new EnchantmentLight();
}
