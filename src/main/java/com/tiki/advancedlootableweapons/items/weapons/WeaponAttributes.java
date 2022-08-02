package com.tiki.advancedlootableweapons.items.weapons;

import com.tiki.advancedlootableweapons.armor.ArmorTypes;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;

public enum WeaponAttributes {

	DAGGER("Dagger", CommonConfigHandler.GLOBAL_DAGGER_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_DAGGER_ATTACK_SPEED.get(), 3.21F, false,
			0.95, 40, 0.9, 20, 0.85, 15),
	KABUTOWARI("Kabutowari", CommonConfigHandler.GLOBAL_KABUTOWARI_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_KABUTOWARI_ATTACK_SPEED.get(), 3.39F, false,
			0.95, 20, 0.85, 35, 0.8, 28),
	RAPIER("Rapier", CommonConfigHandler.GLOBAL_RAPIER_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_RAPIER_ATTACK_SPEED.get(), 4.29F, false,
			1.0, 80, 0.85, 40, 0.8, 35),
	TALWAR("Talwar", CommonConfigHandler.GLOBAL_TALWAR_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_TALWAR_ATTACK_SPEED.get(), 4.0F, true,
			0.95, 30, 0.9, 6, 0.85, 2),
	CLEAVER("Cleaver", CommonConfigHandler.GLOBAL_CLEAVER_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_CLEAVER_ATTACK_SPEED.get(), 3.28F, false,
			0.9, 45, 1.0, 0, 0.95, 0),
	MACE("Mace", CommonConfigHandler.GLOBAL_MACE_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_MACE_ATTACK_SPEED.get(), 3.75F, false,
			0.8, 1, 0.95, 0, 1.0, 0),
	STAFF("Staff", CommonConfigHandler.GLOBAL_STAFF_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_STAFF_ATTACK_SPEED.get(), 5.28F, false,
			0.85, 0, 0.95, 0, 0.95, 0),
	LONGSWORD("Longsword", CommonConfigHandler.GLOBAL_LONGSWORD_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_LONGSWORD_ATTACK_SPEED.get(), 4.14F, true,
			0.95, 25, 0.9, 10, 0.85, 4),
	KODACHI("Kodachi", CommonConfigHandler.GLOBAL_KODACHI_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_KODACHI_ATTACK_SPEED.get(), 3.49F, true,
			1.0, 45, 0.8, 7, 0.7, 25),
	NODACHI("Nodachi", CommonConfigHandler.GLOBAL_NODACHI_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_NODACHI_ATTACK_SPEED.get(), 4.66F, true,
			0.95, 25, 0.9, 2, 0.85, 4),
	BATTLEAXE("Battleaxe", CommonConfigHandler.GLOBAL_BATTLEAXE_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_BATTLEAXE_ATTACK_SPEED.get(), 4.32F, false,
			1.0, 30, 1.0, 0, 0.95, 0),
	ZWEIHANDER("Zweihander", CommonConfigHandler.GLOBAL_ZWEIHANDER_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_ZWEIHANDER_ATTACK_SPEED.get(), 4.25F, true,
			1.0, 20, 0.95, 1, 0.95, 3),
	SABRE("Sabre", CommonConfigHandler.GLOBAL_SABRE_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_SABRE_ATTACK_SPEED.get(), 3.92F, true,
			0.95, 35, 0.8, 7, 0.8, 3),
	MAKHAIRA("Makhaira", CommonConfigHandler.GLOBAL_MAKHAIRA_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_MAKHAIRA_ATTACK_SPEED.get(), 3.56F, true,
			1.0, 25, 0.95, 2, 0.9, 4),
	SPEAR("Spear", CommonConfigHandler.GLOBAL_SPEAR_BASE_DAMAGE.get(), CommonConfigHandler.GLOBAL_SPEAR_ATTACK_SPEED.get(), 5.74F, false,
			1.0, 70, 0.85, 20, 0.75, 5),
	ARROW("Arrow", -1, -1, -1, false, 0.95, 25, 0.75, 25, 0.6, 20),
	THROWN_SPEAR("Thrown Spear", -1, -1, -1, false, 0.95, 60, 0.75, 22, 0.7, 16);
	
	private final String type;
	private final float damage;
	private final double speed;
	private final float reach;
	private final boolean shouldSlash;
	private final double studdedEffectiveness;
	private final int studdedPenChance;
	private final double chainEffectiveness;
	private final int chainPenChance;
	private final double plateEffectiveness;
	private final int platePenChance;
	
	WeaponAttributes(String type, float attackDamage, double attackSpeed, float reach, boolean shouldSlash,
			double studdedDamage, int studdedPenChance, double chainDamage, int chainPenChance, double plateDamage, int platePenChance) {
		this.type = type;
		this.damage = attackDamage;
		this.speed = attackSpeed - 4.0F; // USING THIS TO KEEP NUMBERS POSITIVE AND EASY TO UNDERSTAND FOR USERS IN CONFIG ;_;
		this.reach = reach;
		this.shouldSlash = shouldSlash;
		this.studdedEffectiveness = studdedDamage;
		this.studdedPenChance = studdedPenChance;
		this.chainEffectiveness = chainDamage;
		this.chainPenChance = chainPenChance;
		this.plateEffectiveness = plateDamage;
		this.platePenChance = platePenChance;
	}
	
	public String getType() {
		return this.type;
	}
	
	public float getBaseDamage() {
		return this.damage;
	}
	
	public double getBaseAttackSpeed() {
		return this.speed;
	}
	
	public float getReach() {
		return this.reach;
	}
	
	public boolean shouldSlash() {
		return this.shouldSlash;
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
