package com.tiki.advancedlootableweapons.handlers;

import com.tiki.advancedlootableweapons.items.armor.ModArmorMaterials;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

import java.util.HashMap;
import java.util.Map;

public record ArmorBonus(int tier,double[] bonusHealth, double[] bonusSpeed, double damageBonus) {

    public static final Map<ArmorMaterial, ArmorBonus> LOOKUP = new HashMap<>();
    public static final ArmorBonus NULL = new ArmorBonus(0,new double[]{0,0,0,0},new double[]{0,0,0,0},0);

    public static final ArmorBonus STEEL = register(ModArmorMaterials.STEEL, new ArmorBonus(5,new double[]{4.5,5.12,5.12,4.5},
            new double[]{-0.0016741125,-0.0083705625,-0.0083705625,-0.0016741125}, 1.875D));
    public static final ArmorBonus FROST_STEEL = register(ModArmorMaterials.FROST_STEEL,
            new ArmorBonus(6,new double[]{5,5.75,5.75,5},
                    new double[]{-0.00200179212,-0.01000717488,-0.01000717488, -0.00200179212}, 2.802D));
    public static final ArmorBonus COPPER = register(ModArmorMaterials.COPPER, new ArmorBonus(3,new double[]{2.5,3.125,3.125,2.5},
                    new double[]{-0.00188214888D,-0.00941253012D,-0.00941253012D,-0.00188214888D}, 1.506D));
    public static final ArmorBonus IRON = register(ArmorMaterials.IRON, new ArmorBonus(2,new double[]{1.5,2.125,2.125,1.5},
            new double[]{-0.0016741125D,-0.0083705625D,-0.0083705625D,-0.0016741125D}, 1.875));
    public static final ArmorBonus GOLD = register(ArmorMaterials.GOLD, new ArmorBonus(2,new double[]{1.5,2.125,2.125,1.5},
            new double[]{-0.00816074D,-0.01632148D,-0.01632148D,-0.00816074D}, 4.113D));
    public static final ArmorBonus DIAMOND = register(ArmorMaterials.DIAMOND, new ArmorBonus(6,new double[]{5,5.75,5.75,5},
            new double[]{-0.00247411506,-0.00652368639D,-0.00652368639D,-0.00247411506D}, 0));
    //public static final ArmorBonus NETHERITE = register(ArmorMaterials.NETHERITE, new ArmorBonus(0));
    public static final ArmorBonus BRONZE = register(ModArmorMaterials.BRONZE, new ArmorBonus(4,new double[]{3.5D,4.125,4.125,3.5},
                    new double[]{-0.00403304862D,-0.020168189538D,-0.020168189538D,-0.00403304862D}, 3.764D));
    public static final ArmorBonus KOBOLD_STEEL = register(ModArmorMaterials.KOBOLD_STEEL, new ArmorBonus(2,new double[]{2.5,3.125,3.125,2.5},
            new double[]{-0.00160000512D,-0.00767681028D,-0.00767681028D,-0.00160000512D}, 1.228D));
    public static final ArmorBonus SILVER = register(ModArmorMaterials.SILVER, new ArmorBonus(4,new double[]{3.5,4.125,4.125,3.5},
            new double[]{-0.0022098285D,-0.0110491425D,-0.0110491425D,-0.0022098285D}, 2.063D));
    public static final ArmorBonus PLATINUM = register(ModArmorMaterials.PLATINUM, new ArmorBonus(5,new double[]{4.5,5.125,5.125,4.5},
            new double[]{-0.00452412162D,-0.02261882238D,-0.02261882238D,-0.00452412162D}, 5.067));
    public static final ArmorBonus REFINED_OBSIDIAN = register(ModArmorMaterials.REFINED_OBSIDIAN, new ArmorBonus(7,new double[]{6.25,7,7,6.25},
            new double[]{-0.00452412162D,-0.02261882238D,-0.02261882238D,-0.00452412162D},2.194D));
    public static final ArmorBonus SHADOW_PLATINUM = register(ModArmorMaterials.SHADOW_PLATINUM, new ArmorBonus(6,new double[]{5,5.75,5.75,5},
            new double[]{-0.00360090438D,-0.01800630762D,-0.01800630762D,-0.00360090438D},5.042D));
    public static final ArmorBonus DUSKSTEEL = register(ModArmorMaterials.DUSKSTEEL, new ArmorBonus(8,new double[]{7.25,7.75,7.75,7.25},
            new double[]{-0.00180089862D,-0.00900270738D,-0.00900270738D,-0.00180089862D},5.042D));
    public static final ArmorBonus CRYSTALLITE = register(ModArmorMaterials.CRYSTALLITE, new ArmorBonus(7,new double[]{6.25,7,7,6.25},
            new double[]{-0.00240357912D,-0.01201610988D,-0.01201610988D,-0.00240357912D},4.486D));

    public static final ArmorBonus DIAMOND_STUDDED_LEATHER = register(ModArmorMaterials.DIAMOND_STUDDED_LEATHER, new ArmorBonus(2,new double[]{4.5,5.12,5.12,4.5},
            new double[]{-0.0016741125,-0.0083705625,-0.0083705625,-0.0016741125}, 1.875D));
    public static final ArmorBonus DIAMOND_STUDDED_STEEL = register(ModArmorMaterials.DIAMOND_STUDDED_STEEL, new ArmorBonus(6,new double[]{4.5,5.12,5.12,4.5},
            new double[]{-0.0016741125,-0.0083705625,-0.0083705625,-0.0016741125}, 1.875D));


    public static final ArmorBonus IRON_CHAIN = register(ModArmorMaterials.IRON_CHAIN, new ArmorBonus(2,new double[]{1.5,2.125,2.125,1.5},new double[]{-0.0016741125D,-0.0083705625D,-0.0083705625D,-0.0016741125D},1.406));
    public static final ArmorBonus GOLD_CHAIN = register(ModArmorMaterials.GOLD_CHAIN, new ArmorBonus(2,new double[]{1.5,2.125,2.125,1.5},new double[]{-0.00816074D,-0.01632148D,-0.01632148D,-0.00816074D},3.084D));
    public static final ArmorBonus FROST_STEEL_CHAIN = register(ModArmorMaterials.FROST_STEEL_CHAIN,
            new ArmorBonus(6,new double[]{5,5.75,5.75,5},
                    new double[]{-0.00150134409D,-0.00750538116D,-0.00750538116D, -0.00150134409D}, 2.101));
    public static final ArmorBonus COPPER_CHAIN = register(ModArmorMaterials.COPPER_CHAIN, new ArmorBonus(3,new double[]{2.5,3.125,3.125,2.5},
            new double[]{-0.00188214888D,-0.00941253012D,-0.00941253012D,-0.00188214888D}, 1.129D));
    public static final ArmorBonus STEEL_CHAIN = register(ModArmorMaterials.STEEL_CHAIN, new ArmorBonus(5,new double[]{4.5,5.12,5.12,4.5},
            new double[]{-0.001255584375D,-0.006277921875D,-0.006277921875D,-0.001255584375D}, 1.875D));
    public static final ArmorBonus BRONZE_CHAIN = register(ModArmorMaterials.BRONZE_CHAIN, new ArmorBonus(4,new double[]{3.5D,4.125,4.125,3.5},new double[]{-0.00403304862D,-0.020168189538D,-0.020168189538D,-0.00403304862D},2.823D));
    public static final ArmorBonus KOBOLD_STEEL_CHAIN = register(ModArmorMaterials.KOBOLD_STEEL_CHAIN, new ArmorBonus(2,new double[]{2.5,3.125,3.125,2.5},new double[]{-0.00160000512D,-0.00767681028D,-0.00767681028D,-0.00160000512D},0.921D));
    public static final ArmorBonus SILVER_CHAIN = register(ModArmorMaterials.SILVER_CHAIN, new ArmorBonus(4,new double[]{3,3.5,4.125,4.125,3.5},new double[]{-0.0022098285D,-0.0110491425D,-0.0110491425D,-0.0022098285D},1.547D));
    public static final ArmorBonus PLATINUM_CHAIN = register(ModArmorMaterials.PLATINUM_CHAIN, new ArmorBonus(5,new double[]{4.5,5.125,5.125,4.5},new double[]{-0.00452412162D,-0.02261882238D,-0.02261882238D,-0.00452412162D},3.801D));
    public static final ArmorBonus REFINED_OBSIDIAN_CHAIN = register(ModArmorMaterials.REFINED_OBSIDIAN_CHAIN, new ArmorBonus(7,new double[]{6.25,7,7,6.25},new double[]{-0.00452412162D,-0.02261882238D,-0.02261882238D,-0.00452412162D},1.645D));
    public static final ArmorBonus SHADOW_PLATINUM_CHAIN = register(ModArmorMaterials.SHADOW_PLATINUM_CHAIN, new ArmorBonus(6,new double[]{5,5.75,5.75,5},new double[]{-0.00360090438D,-0.01800630762D,-0.01800630762D,-0.00360090438D},3.781D));
    public static final ArmorBonus DUSKSTEEL_CHAIN = register(ModArmorMaterials.DUSKSTEEL_CHAIN, new ArmorBonus(8,new double[]{7.25,7.75,7.75,7.25},new double[]{-0.00180089862D,-0.00900270738D,-0.00900270738D,-0.00180089862D},3.781));
    public static final ArmorBonus CRYSTALLITE_CHAIN = register(ModArmorMaterials.CRYSTALLITE_CHAIN, new ArmorBonus(7,new double[]{6.25,7,7,6.25},new double[]{-0.00240357912D,-0.01201610988D,-0.01201610988D,-0.00240357912D},3.365D));

    public static ArmorBonus register(ArmorMaterial armorMaterial,ArmorBonus armorBonus) {
        LOOKUP.put(armorMaterial,armorBonus);
        return armorBonus;
    }

    public static boolean isPresent(ArmorMaterial armorMaterial) {
        return LOOKUP.containsKey(armorMaterial);
    }

    public static ArmorBonus getArmorBonus(ArmorMaterial armorMaterial) {
        return LOOKUP.getOrDefault(armorMaterial,NULL);
    }

}
