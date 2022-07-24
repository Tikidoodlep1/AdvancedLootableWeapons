package com.tiki.advancedlootableweapons.util;

public enum WeaponEffectiveness {

	DAGER("dagger", 0.95, 40, 0.9, 20, 0.85, 15),
	KABUTOWARI("kabutowari", 0.95, 20, 0.85, 35, 0.8, 28),
	RAPIER("rapier", 1.0, 80, 0.85, 40, 0.8, 35),
	TALWAR("talwar", 0.95, 30, 0.9, 1, 0.85, 2),
	CLEAVER("cleaver", 0.9, 45, 1.0, 0, 0.95, 0),
	MACE("mace", 0.8, 1, 0.95, 0, 1.0, 0),
	STAFF("staff", 0.85, 0, 0.95, 0, 0.95, 0),
	SPEAR("spear", 1.0, 70, 0.85, 20, 0.75, 5),
	LONGSWORD("longsword", 0.95, 25, 0.9, 1, 0.85, 2),
	KODACHI("kodachi", 1.0, 45, 0.8, 20, 0.7, 25),
	NODACHI("nodachi", 0.95, 25, 0.9, 3, 0.85, 4),
	SABRE("sabre", 0.95, 35, 0.8, 7, 0.8, 3),
	MAKHAIRA("makhaira", 1.0, 25, 0.95, 0, 0.9, 1),
	BATTLEAXE("battleaxe", 1.0, 30, 1.0, 0, 0.95, 0),
	ZWEIHANDER("zweihander", 1.0, 20, 0.95, 1, 0.95, 1),
	ARROW("arrow", 0.95, 25, 0.75, 25, 0.6, 20),
	THROWN_SPEAR("thrown_spear", 0.95, 60, 0.75, 23, 0.7, 16);
	
	private final String type;
	private final double studdedEffectiveness;
	private final int studdedPenChance;
	private final double chainEffectiveness;
	private final int chainPenChance;
	private final double plateEffectiveness;
	private final int platePenChance;
	
	WeaponEffectiveness(String type, double studded, int studdedChance, double chain, int chainChance, double plate, int plateChance) {
		this.type = type;
		this.studdedEffectiveness = studded;
		this.studdedPenChance = studdedChance;
		this.chainEffectiveness = chain;
		this.chainPenChance = chainChance;
		this.plateEffectiveness = plate;
		this.platePenChance = plateChance;
	}
	
	public static WeaponEffectiveness getWeaponEffectiveness(String type) {
		for(WeaponEffectiveness e : WeaponEffectiveness.values()) {
			if(e.getType().equalsIgnoreCase(type)) {
				return e;
			}
		}
		return null;
	}
	
	public double getEffectByArmorType(ArmorTypes make) {
		switch(make) {
		case CHAIN:
			return this.chainEffectiveness;
		case PLATE:
			return this.plateEffectiveness;
		case STUDDED:
			return this.studdedEffectiveness;
		default:
			return 1.0;
		}
	}
	
	public int getPenChanceByArmorType(ArmorTypes make) {
		switch(make) {
		case CHAIN:
			return this.chainPenChance;
		case PLATE:
			return this.platePenChance;
		case STUDDED:
			return this.studdedPenChance;
		default:
			return 0;
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getStuddedEffect() {
		return this.studdedEffectiveness;
	}
	
	public int getStuddedPenChance() {
		return this.studdedPenChance;
	}
	
	public double getChainEffect() {
		return this.chainEffectiveness;
	}
	
	public int getChainPenChance() {
		return this.chainPenChance;
	}
	
	public double getPlateEffect() {
		return this.plateEffectiveness;
	}
	
	public int getPlatePenChance() {
		return this.platePenChance;
	}
}
