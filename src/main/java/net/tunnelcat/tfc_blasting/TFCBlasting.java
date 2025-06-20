package net.tunnelcat.tfc_blasting;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static net.tunnelcat.tfc_blasting.block.TFCBlastingBlocks.BLOCKS;
import static net.tunnelcat.tfc_blasting.blockentity.TFCBlastingBlockEntities.BLOCK_ENTITIES;
import static net.tunnelcat.tfc_blasting.fluid.TFCBlastingFluids.FLUIDS;
import static net.tunnelcat.tfc_blasting.fluid.TFCBlastingFluids.FLUID_TYPES;
import static net.tunnelcat.tfc_blasting.item.TFCBlastingItems.ITEMS;
import static net.tunnelcat.tfc_blasting.util.TFCBlastingCreativeModeTabs.CREATIVE_MODE_TABS;

@Mod(TFCBlasting.MOD_ID)
public class TFCBlasting {
    public static final String MOD_ID = "tfc_blasting";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TFCBlasting(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        LOGGER.info("TFC Blasting begin registrations");

        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        LOGGER.info("TFC Blasting done registrations");
    }
}
