package net.tunnelcat.tfc_blasting.util;

import net.minecraft.core.Direction;

public class TFCBlastingHelpers {
    // Returns the offset value required to spawn particles on the interacted side of the interacted block
    public static double getParticleOffsetToFace(char axis, Direction face) {
        double offset = 0.5;

        switch (axis) {
            case 'x':
                if(face == Direction.EAST) {offset = 1;}
                if(face == Direction.WEST) {offset = 0;}
                break;
            case 'y':
                if(face == Direction.UP) {offset = 1;}
                if(face == Direction.DOWN) {offset = 0;}
                break;
            case 'z':
                if(face == Direction.SOUTH) {offset = 1;}
                if(face == Direction.NORTH) {offset = 0;}
                break;
        }

        return offset;
    }
}
