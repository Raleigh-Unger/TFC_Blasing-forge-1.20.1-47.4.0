package net.tunnelcat.tfc_blasting.blockentity;

import net.dries007.tfc.common.blocks.TFCBlocks;
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

//    public static final RegistryObject<BlockEntityType<AcidBarrelBlockEntity>> ACID_BARREL = register("acid_barrel", AcidBarrelBlockEntity::new, TFCBlastingBlocks.BARREL_GOLD);


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block) {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

//    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks) {
//        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
//    }
}
