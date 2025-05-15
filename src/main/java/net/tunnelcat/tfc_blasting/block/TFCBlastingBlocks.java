package net.tunnelcat.tfc_blasting.block;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.fluid.TFCBlastingFluids;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;
import net.tunnelcat.tfc_blasting.item.TFCBlastingItems;

public class TFCBlastingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCBlasting.MOD_ID);

    public static final Map<SimpleFluid, RegistryObject<LiquidBlock>> SIMPLE_FLUIDS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            registerNoItem("fluid/" + fluid.getId(), () -> new LiquidBlock(TFCBlastingFluids.SIMPLE_FLUIDS.get(fluid).source(), Properties.copy(Blocks.WATER).noLootTable()))
    );

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return RegistrationHelpers.registerBlock(BLOCKS, TFCBlastingItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}