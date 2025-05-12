package net.tunnelcat.tfc_blasting.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.item.ModItems;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TFCBlasting.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TFCBLASTING_TAB = CREATIVE_MODE_TABS.register("tfc_blasting_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.DYNAMITE.get()))
                    .title(Component.translatable("creativetab.tfc_blasting_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DYNAMITE.get());
                        output.accept(ModItems.CLAY_ROD_NITROGLYCERIN.get());
                        output.accept(ModItems.CLAY_ROD.get());
                        output.accept(ModItems.FUSE.get());
                        output.accept(ModItems.FUSE_CAP_COPPER.get());
                        output.accept(ModItems.FUSE_CAP_COPPER_EMPTY.get());
                        output.accept(ModItems.FUSE_CAP_TIN.get());
                        output.accept(ModItems.FUSE_CAP_TIN_EMPTY.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
