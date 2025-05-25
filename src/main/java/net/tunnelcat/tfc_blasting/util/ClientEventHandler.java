package net.tunnelcat.tfc_blasting.util;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.dries007.tfc.util.Helpers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import net.tunnelcat.tfc_blasting.block.TFCBlastingBlocks;
import net.tunnelcat.tfc_blasting.blockentity.TFCBlastingBlockEntities;
import net.tunnelcat.tfc_blasting.mixin.BlockEntityTypeAccessor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ClientEventHandler {
    public static void init(IEventBus bus) {
//        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEventHandler::clientSetup);
        bus.addListener(ClientEventHandler::commonSetup);
    }

//    @SuppressWarnings("deprecation")
    public static void clientSetup(FMLClientSetupEvent event) {
        // Screens
        event.enqueueWork(() -> {
            for(RegistryObject<BarrelBlock> b : TFCBlastingBlocks.BARRELS.values()){
                ItemProperties.register(b.get().asItem(), Helpers.identifier("sealed"), (stack, level, entity, unused) -> stack.hasTag() ? 1.0f :0f);
//                ItemBlockRenderTypes.setRenderLayer(b.get(), RenderType.cutout());
            }
        });

//        event.enqueueWork(() -> ItemProperties.register(
//                TFCBlastingBlocks.BARREL_GOLD.get().asItem(),
//                Helpers.identifier("sealed"),
//                (stack, level, entity, unused) -> stack.hasTag() ? 1.0f :0f
//        ));
    }

//    @SuppressWarnings("deprecation")
    public static void commonSetup(FMLCommonSetupEvent event) {
        // Screens
        event.enqueueWork(() -> {
            for(RegistryObject<BarrelBlock> b : TFCBlastingBlocks.BARRELS.values()){
                modifyBlockEntityType(TFCBlockEntities.BARREL.get(), b.get());
            }
        });

//        event.enqueueWork(() -> modifyBlockEntityType(
//                TFCBlastingBlockEntities.ACID_BARREL.get(),
//                TFCBlastingBlocks.BARREL_GOLD.get()
//        ));
    }

    private static void modifyBlockEntityType(BlockEntityType<?> type, Block extraBlock) {
        BlockEntityTypeAccessor acc = ((BlockEntityTypeAccessor) (Object) type);
        Set<Block> blocks = acc.accessor$getValidBlocks();
        blocks = new HashSet<>(blocks);

        blocks.add(extraBlock);
        acc.accessor$setValidBlocks(blocks);
    }

    private static void modifyBlockEntityType(BlockEntityType<?> type, Stream<Block> extraBlocks) {
        BlockEntityTypeAccessor acc = ((BlockEntityTypeAccessor) (Object) type);
        Set<Block> blocks = acc.accessor$getValidBlocks();
        blocks = new HashSet<>(blocks);

        blocks.addAll(extraBlocks.toList());
        acc.accessor$setValidBlocks(blocks);
    }
}