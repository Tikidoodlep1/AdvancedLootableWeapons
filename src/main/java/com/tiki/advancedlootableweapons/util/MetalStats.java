package com.tiki.advancedlootableweapons.util;

public class MetalStats {

    public final int mp;
    public final int tc;
    public final int sh;
    public final double iw;
    public MetalStats(int mp, int tc, int sh, double iw) {
        this.mp = mp;
        this.tc = tc;
        this.sh = sh;
        this.iw = iw;
    }

    public static final MetalStats BRONZE = new MetalStats(1127,26,376,2.82);
    public static final MetalStats COPPER = new MetalStats(1537,413,376,1.29);
    public static final MetalStats CRYSTALLITE = new MetalStats(2864,284,882,2.28);
    public static final MetalStats DUSKSTEEL = new MetalStats(1582,27,402,1.26);
    public static final MetalStats FROST_STEEL = new MetalStats(1442,158,318,1.4);
    public static final MetalStats GOLD = new MetalStats(1337,327,129,2.78);
    public static final MetalStats IRON = new MetalStats(1811, 94, 460, 1.13);
    public static final MetalStats KOBOLD_STEEL = new MetalStats(487,183,284,1.22);
    public static final MetalStats REFINED_OBSIDIAN = new MetalStats(1578,52,837,.35);
    public static final MetalStats PLATINUM = new MetalStats(2041,72,125,3.09);
    public static final MetalStats SHADOW_PLATINUM = new MetalStats(1463,124,627,2.52);
    public static final MetalStats SILVER = new MetalStats(1234,403,238,1.51);
    public static final MetalStats STEEL = new MetalStats(1753,59,502,1.16);


}
