package tiki.advancedlootableweapons.compat.patchouli;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.init.ItemInit;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.api.PatchouliAPI.IPatchouliAPI;

public class PatchouliCompat {

	public static final IPatchouliAPI api = PatchouliAPI.instance;
	
	public static void registerCustomFlags() {
		boolean forgeHammerOreDictHasAlwHammers = false;
		
		if(OreDictionary.getOres("itemAlwForgeHammer").size() > 0) {
			for(ItemStack stack : OreDictionary.getOres("itemAlwForgeHammer")) {
				if(stack.getItem() == ItemInit.BRONZE_FORGE_HAMMER ||
						stack.getItem() == ItemInit.COPPER_FORGE_HAMMER ||
						stack.getItem() == ItemInit.CRYSTALLITE_FORGE_HAMMER ||
						stack.getItem() == ItemInit.DUSKSTEEL_FORGE_HAMMER ||
						stack.getItem() == ItemInit.FROST_STEEL_FORGE_HAMMER ||
						stack.getItem() == ItemInit.IRON_FORGE_HAMMER ||
						stack.getItem() == ItemInit.KOBOLD_FORGE_HAMMER ||
						stack.getItem() == ItemInit.OBSIDIAN_FORGE_HAMMER ||
						stack.getItem() == ItemInit.PLATINUM_FORGE_HAMMER ||
						stack.getItem() == ItemInit.SHADOW_PLATINUM_FORGE_HAMMER ||
						stack.getItem() == ItemInit.SILVER_FORGE_HAMMER ||
						stack.getItem() == ItemInit.STEEL_FORGE_HAMMER ||
						stack.getItem() == ItemInit.STONE_FORGE_HAMMER) {
					forgeHammerOreDictHasAlwHammers = true;
					break;
				}
			}
		}
		
		api.setConfigFlag(getCustomFlagString("forgeHammerOreDictHasAlwHammers"), forgeHammerOreDictHasAlwHammers);
	}
	
	public static String getCustomFlagString(String flag) {
		return ModInfo.ID + ":" + flag;
	}
}
