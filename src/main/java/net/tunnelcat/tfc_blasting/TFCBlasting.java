package net.tunnelcat.tfc_blasting;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tunnelcat.tfc_blasting.block.ModFluidBlocks;
import net.tunnelcat.tfc_blasting.fluid.ModFluids;
import net.tunnelcat.tfc_blasting.item.ModFluidItems;
import net.tunnelcat.tfc_blasting.util.ModCreativeModeTabs;
import net.tunnelcat.tfc_blasting.item.ModItems;
import org.slf4j.Logger;

@Mod(TFCBlasting.MOD_ID)
public class TFCBlasting
{
    public static final String MOD_ID = "tfc_blasting";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TFCBlasting(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModFluidItems.FLUID_ITEMS.register(modEventBus);
        ModFluidBlocks.FLUID_BLOCKS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
//        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add items to creative tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event)
//    {
//        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
//            event.accept(ModItems.DYNAMITE);
//            event.accept(ModItems.CLAY_ROD_NITROGLYCERIN);
//            event.accept(ModItems.CLAY_ROD);
//            event.accept(ModItems.FUSE);
//            event.accept(ModItems.FUSE_CAP_COPPER);
//            event.accept(ModItems.FUSE_CAP_COPPER_EMPTY);
//            event.accept(ModItems.FUSE_CAP_TIN);
//            event.accept(ModItems.FUSE_CAP_TIN_EMPTY);
//        }
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
