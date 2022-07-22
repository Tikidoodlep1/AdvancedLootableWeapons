package com.tiki.advancedlootableweapons.util;

public enum WeaponEffectiveness {

	DAGER("dagger", 0.9, 20, 0.85, 15),
	KABUTOWARI("kabutowari", 0.95, 35, 0.85, 28),
	RAPIER("rapier", 0.85, 40, 0.8, 35),
	TALWAR("talwar", 0.9, 1, 0.85, 2),
	CLEAVER("cleaver", 1.0, 0, 0.95, 0),
	MACE("mace", 1.0, 0, 1.0, 0),
	STAFF("staff", 0.95, 0, 0.95, 0),
	SPEAR("spear", 0.85, 20, 0.75, 5),
	LONGSWORD("longsword", 0.9, 1, 0.85, 2),
	KODACHI("kodachi", 0.8, 20, 0.7, 25),
	NODACHI("nodachi", 0.9, 3, 0.85, 4),
	SABRE("sabre", 0.8, 7, 0.8, 3),
	MAKHAIRA("makhaira", 0.95, 0, 0.9, 1),
	BATTLEAXE("battleaxe", 1.0, 0, 0.95, 0),
	ZWEIHANDER("zweihander", 0.95, 1, 0.95, 1),
	ARROW("arrow", 0.75, 25, 0.6, 20),
	THROWN_SPEAR("thrown_spear", 0.75, 23, 0.7, 16);
	
	private final String type;
	private final double chainEffectiveness;
	private final int chainPenChance;
	private final double plateEffectiveness;
	private final int platePenChance;
	
	WeaponEffectiveness(String type, double chain, int chainChance, double plate, int plateChance) {
		this.type = type;
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
	
	public String getType() {
		return this.type;
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
