package net.tunnelcat.tfc_blasting;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.tunnelcat.tfc_blasting.util.ClientEventHandler;
import org.slf4j.Logger;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

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

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientEventHandler.init(modEventBus);
        }
    }

    static void setFinal(Field field, Object source, Object newValue) throws Exception {
        field.setAccessible(true);

        final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe u = (Unsafe) unsafeField.get(null);

        long fieldOffset = u.objectFieldOffset(field);
        u.putObject(source, fieldOffset, newValue);
    }
}
