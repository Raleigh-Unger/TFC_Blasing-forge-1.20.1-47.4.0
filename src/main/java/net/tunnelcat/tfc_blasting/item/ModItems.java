package net.tunnelcat.tfc_blasting.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tunnelcat.tfc_blasting.TFCBlasting;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCBlasting.MOD_ID);

    public static final RegistryObject<Item> DYNAMITE               = ITEMS.register("dynamite",                () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLAY_ROD               = ITEMS.register("clay_rod",                () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLAY_ROD_NITROGLYCERIN = ITEMS.register("clay_rod_nitroglycerin",  () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
