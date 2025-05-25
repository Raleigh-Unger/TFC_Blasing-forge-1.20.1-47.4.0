package net.tunnelcat.tfc_blasting.blockentity;

import net.dries007.tfc.common.blockentities.BarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tunnelcat.tfc_blasting.TFCBlasting;

public class AcidBarrelBlockEntity extends BarrelBlockEntity {
    public AcidBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BarrelBlockEntity barrel) {
        TFCBlasting.LOGGER.warn("BARREL TICK!");
    }
}
