package net.tunnelcat.tfc_blasting.item;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.dries007.tfc.util.Helpers;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.fluid.ModFluids;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;

public class ModFluidItems {
    public static final DeferredRegister<Item> FLUID_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCBlasting.MOD_ID);

    public static final Map<SimpleFluid, RegistryObject<BucketItem>> SIMPLE_FLUID_BUCKETS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            register("bucket/" + fluid.getSerializedName(), () -> new BucketItem(ModFluids.SIMPLE_FLUIDS.get(fluid).source(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)))
    );

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return FLUID_ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}