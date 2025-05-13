package net.tunnelcat.tfc_blasting.fluid;

import java.util.Locale;
import net.minecraft.util.StringRepresentable;

public enum SimpleFluid implements StringRepresentable {
    GLYCERIN(0xFFa79464),
    NITROGLYCERIN(0xFFfcfae2)
    ;

    private final String id;
    private final int color;

    SimpleFluid(int color) {
        this.id = name().toLowerCase(Locale.ROOT);
        this.color = color;
    }

    @Override
    public String getSerializedName() {
        return id;
    }

    public int getColor() {
        return color;
    }
}