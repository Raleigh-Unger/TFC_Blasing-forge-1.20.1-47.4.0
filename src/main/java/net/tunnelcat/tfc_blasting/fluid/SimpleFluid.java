package net.tunnelcat.tfc_blasting.fluid;

import java.util.Locale;

@SuppressWarnings("unused")
public enum SimpleFluid {
    GLYCERIN_SLURRY(0xFFd9bc00),
    GLYCERIN(0xFFd18902),
    NITROGLYCERIN(0xFFf5c87f),
    SULFURIC_ACID(0xFFebe8b0),
    NITRIC_ACID(0xFFd9ce5b),
    GUNPOWDER_SLURRY(0xFF6b6b6b),
    PICRIC_ACID_WET(0xFFcfd94a)
    ;

    private final String id;
    private final int color;

    SimpleFluid(int color) {
        this.id = name().toLowerCase(Locale.ROOT);
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public boolean isTransparent()
    {
        return this != GUNPOWDER_SLURRY;
    }
}