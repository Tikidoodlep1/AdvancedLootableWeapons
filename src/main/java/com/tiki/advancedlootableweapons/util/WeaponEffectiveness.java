package com.tiki.advancedlootableweapons.util;

import java.util.Random;

public enum WeaponEffectiveness {

	DAGGER("dagger", 0.8, 15, 0.9, 20, 0.85, 15),
	KABUTOWARI("kabutowari", 0.85, 20, 0.85, 35, 0.8, 28),
	RAPIER("rapier", 0.75, 25, 0.85, 40, 0.8, 35),
	TALWAR("talwar", 0.85, 2, 0.9, 6, 0.85, 2),
	CLEAVER("cleaver", 0.9, 2, 1.0, 4, 0.95, 3),
	MACE("mace", 0.9, 0, 0.85, 0, 1.0, 0),
	STAFF("staff", 0.85, 0, 0.75, 0, 0.95, 0),
	SPEAR("spear", 0.7, 3, 0.85, 20, 0.75, 5),
	LONGSWORD("longsword", 0.8, 4, 0.9, 10, 0.85, 4),
	KODACHI("kodachi", 0.75, 28, 0.8, 20, 0.7, 25),
	NODACHI("nodachi", 0.8, 2, 0.9, 2, 0.85, 4),
	SABRE("sabre", 0.8, 5, 0.85, 7, 0.8, 3),
	MAKHAIRA("makhaira", 0.8, 1, 0.95, 2, 0.9, 4),
	BATTLEAXE("battleaxe", 0.9, 3, 1.0, 1, 0.95, 0),
	ZWEIHANDER("zweihander", 0.75, 20, 0.85, 1, 0.8, 3),
	ARROW("arrow", 0.65, 15, 0.75, 25, 0.7, 15),
	THROWN_SPEAR("thrown_spear", 0.7, 15, 0.85, 23, 0.75, 16);
	
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
	
	public static boolean studdedArmorChipWeapon() {
		Random rand = new Random();
		return rand.nextDouble() <= 0.35;
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
