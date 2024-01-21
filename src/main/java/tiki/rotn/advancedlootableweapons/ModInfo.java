package tiki.rotn.advancedlootableweapons;

public class ModInfo {
	public static final String ID = "advancedlootableweapons";
	public static final String NAME = "Advanced Lootable Weapons";
	public static final String VER = "RotN-1.4.3-1.12.2";
	public static final String LOAD_AFTER = "required-after:forge@[14.23.5.2860,);required-after:pyrotech@[1.6.9,);after:betterwithmods;after:thermalfoundation;after:appliedenergistics2;after:tconstruct;after:spartanweaponry";
	public static final String CLIENTPROXY = "tiki.rotn.advancedlootableweapons.proxy.ClientProxy";
	public static final String COMMONPROXY = "tiki.rotn.advancedlootableweapons.proxy.CommonProxy";
	
	public static final int GUI_SHARPENING_STONE = 0;
	public static final int GUI_ALLOY_FURNACE = 1;
	public static final int GUI_FORGE = 2;
	public static final int GUI_FORGE_WEAPON = 3;
	public static final int GUI_FORGE_2 = 4;
	public static final int GUI_MILL = 5;
	public static final int GUI_TANNING_RACK = 6;
	public static final int GUI_FORGE_FUEL = 7;
	public static final int GUI_FORGE_2_FUEL = 8;
}
