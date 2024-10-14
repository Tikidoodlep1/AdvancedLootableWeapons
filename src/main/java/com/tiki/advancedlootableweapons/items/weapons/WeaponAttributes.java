package com.tiki.advancedlootableweapons.items.weapons;

import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.common.ForgeConfigSpec;

public enum WeaponAttributes {

	DAGGER("dagger", CommonConfigHandler.GLOBAL_DAGGER_BASE_DAMAGE, CommonConfigHandler.GLOBAL_DAGGER_ATTACK_SPEED, 3.21F, false,
			0.95, 40, 0.9, 20, 0.85, 15, false, true),
	KABUTOWARI("kabutowari", CommonConfigHandler.GLOBAL_KABUTOWARI_BASE_DAMAGE, CommonConfigHandler.GLOBAL_KABUTOWARI_ATTACK_SPEED, 3.39F, false,
			0.95, 20, 0.85, 35, 0.8, 28, true, true),
	RAPIER("rapier", CommonConfigHandler.GLOBAL_RAPIER_BASE_DAMAGE, CommonConfigHandler.GLOBAL_RAPIER_ATTACK_SPEED, 4.29F, false,
			1.0, 80, 0.85, 40, 0.8, 35, true, true),
	TALWAR("talwar", CommonConfigHandler.GLOBAL_TALWAR_BASE_DAMAGE, CommonConfigHandler.GLOBAL_TALWAR_ATTACK_SPEED, 4.0F, true,
			0.95, 30, 0.9, 6, 0.85, 2, false, true),
	CLEAVER("cleaver", CommonConfigHandler.GLOBAL_CLEAVER_BASE_DAMAGE, CommonConfigHandler.GLOBAL_CLEAVER_ATTACK_SPEED, 3.28F, false,
			0.9, 2, 1.0, 4, 0.95, 3, false, true),
	MACE("mace", CommonConfigHandler.GLOBAL_MACE_BASE_DAMAGE, CommonConfigHandler.GLOBAL_MACE_ATTACK_SPEED, 3.75F, false,
			0.8, 1, 0.95, 0, 1.0, 0, false, true),
	STAFF("staff", CommonConfigHandler.GLOBAL_STAFF_BASE_DAMAGE, CommonConfigHandler.GLOBAL_STAFF_ATTACK_SPEED, 5.28F, false,
			0.85, 0, 0.95, 0, 0.95, 0, true, true),
	LONGSWORD("longsword", CommonConfigHandler.GLOBAL_LONGSWORD_BASE_DAMAGE, CommonConfigHandler.GLOBAL_LONGSWORD_ATTACK_SPEED, 4.14F, true,
			0.95, 25, 0.9, 10, 0.85, 4, false, true),
	KODACHI("kodachi", CommonConfigHandler.GLOBAL_KODACHI_BASE_DAMAGE, CommonConfigHandler.GLOBAL_KODACHI_ATTACK_SPEED, 3.49F, true,
			1.0, 45, 0.8, 7, 0.7, 25, false, true),
	NODACHI("nodachi", CommonConfigHandler.GLOBAL_NODACHI_BASE_DAMAGE, CommonConfigHandler.GLOBAL_NODACHI_ATTACK_SPEED, 4.66F, true,
			0.95, 25, 0.9, 2, 0.85, 4, false, true),
	BATTLEAXE("battleaxe", CommonConfigHandler.GLOBAL_BATTLEAXE_BASE_DAMAGE, CommonConfigHandler.GLOBAL_BATTLEAXE_ATTACK_SPEED, 4.32F, false,
			1.0, 30, 1.0, 1, 0.95, 0, true, true),
	ZWEIHANDER("zweihander", CommonConfigHandler.GLOBAL_ZWEIHANDER_BASE_DAMAGE, CommonConfigHandler.GLOBAL_ZWEIHANDER_ATTACK_SPEED, 4.25F, true,
			1.0, 20, 0.95, 1, 0.95, 3, false, true),
	SABRE("sabre", CommonConfigHandler.GLOBAL_SABRE_BASE_DAMAGE, CommonConfigHandler.GLOBAL_SABRE_ATTACK_SPEED, 3.92F, true,
			0.95, 35, 0.8, 7, 0.8, 3, false, true),
	MAKHAIRA("makhaira", CommonConfigHandler.GLOBAL_MAKHAIRA_BASE_DAMAGE, CommonConfigHandler.GLOBAL_MAKHAIRA_ATTACK_SPEED, 3.56F, true,
			1.0, 25, 0.95, 2, 0.9, 4, false, true),
	SPEAR("spear", CommonConfigHandler.GLOBAL_SPEAR_BASE_DAMAGE, CommonConfigHandler.GLOBAL_SPEAR_ATTACK_SPEED, 5.74F, false,
			1.0, 70, 0.85, 20, 0.75, 5, false, true),
	ARROW("arrow", null, null, -1, false, 0.95, 25, 0.75, 25, 0.6, 20, false, false),
	THROWN_SPEAR("thrown_spear", null, null, -1, false, 0.95, 60, 0.75, 22, 0.7, 16, false, false);
	
	private final String type;
	private final ForgeConfigSpec.DoubleValue damage;
	private final ForgeConfigSpec.DoubleValue speed;
	private final float reach;
	private final boolean shouldSlash;
	private final double studdedEffectiveness;
	private final int studdedPenChance;
	private final double chainEffectiveness;
	private final int chainPenChance;
	private final double plateEffectiveness;
	private final int platePenChance;
	private final boolean customModel;
	private final boolean hasItem;

	WeaponAttributes(String type, ForgeConfigSpec.DoubleValue attackDamage, ForgeConfigSpec.DoubleValue attackSpeed, float reach, boolean shouldSlash,
					 double studdedDamage, int studdedPenChance, double chainDamage, int chainPenChance, double plateDamage, int platePenChance, boolean customModel,boolean hasItem) {
		this.type = type;
		this.damage = attackDamage;
		this.speed = attackSpeed; // USING THIS TO KEEP NUMBERS POSITIVE AND EASY TO UNDERSTAND FOR USERS IN CONFIG ;_;
		this.reach = reach;
		this.shouldSlash = shouldSlash;
		this.studdedEffectiveness = studdedDamage;
		this.studdedPenChance = studdedPenChance;
		this.chainEffectiveness = chainDamage;
		this.chainPenChance = chainPenChance;
		this.plateEffectiveness = plateDamage;
		this.platePenChance = platePenChance;
		this.customModel = customModel;
		this.hasItem = hasItem;
	}
	
	public String getType() {
		return this.type;
	}

	public boolean hasItem() {
		return hasItem;
	}

	public float getBaseDamage() {
		return (float)(double)this.damage.get();
	}
	
	public double getBaseAttackSpeed() {
		return (float)(double)this.speed.get() - 4d;
	}
	
	public float getReach() {
		return this.reach;
	}
	
	public boolean shouldSlash() {
		return this.shouldSlash;
	}
	
	public double getEffectByArmorType(ArmorMaterial make) {
		if (make.getName().contains("chain")) {
			return chainEffectiveness;
		} else if (make.getName().contains("studded")) {
			return studdedEffectiveness;
		}
		return plateEffectiveness;
	}
	
	public int getPenChanceByArmorType(ArmorMaterial make) {
		if (make.getName().contains("chain")) {
			return chainPenChance;
		} else if (make.getName().contains("studded")) {
			return studdedPenChance;
		}
		return platePenChance;
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

	public boolean isCustomModel() {
		return customModel;
	}
}
