package tiki.rotn.advancedlootableweapons.handlers;

import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import tiki.rotn.advancedlootableweapons.loot.function.SetBonusDamage;
import tiki.rotn.advancedlootableweapons.loot.function.SetBonusDur;
import tiki.rotn.advancedlootableweapons.loot.function.SetItemFromArray;
import tiki.rotn.advancedlootableweapons.loot.function.SetLore;
import tiki.rotn.advancedlootableweapons.loot.function.SetName;
import tiki.rotn.advancedlootableweapons.loot.function.SetNameConcat;

public class LootHandler {

	public static void registerLootFunctions() {
		LootFunctionManager.registerFunction(new SetBonusDamage.Serializer());
		LootFunctionManager.registerFunction(new SetBonusDur.Serializer());
		LootFunctionManager.registerFunction(new SetLore.Serializer());
		LootFunctionManager.registerFunction(new SetName.Serializer());
		LootFunctionManager.registerFunction(new SetNameConcat.Serializer());
		LootFunctionManager.registerFunction(new SetItemFromArray.Serializer());
	}
}
