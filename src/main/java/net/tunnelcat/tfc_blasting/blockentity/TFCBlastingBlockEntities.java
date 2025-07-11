package net.tunnelcat.tfc_blasting.blockentity;

import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.block.TFCBlastingBlocks;

import java.util.function.Supplier;

public class TFCBlastingBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TFCBlasting.MOD_ID);

    public static final RegistryObject<BlockEntityType<DrillHoleBlockEntity>> DRILL_HOLE_BLOCK_ENTITY = register(
            "drill_hole_block_entity",
            DrillHoleBlockEntity::new,
            TFCBlastingBlocks.DRILL_HOLE_STONE
    );

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block> block
    ) {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }
}
