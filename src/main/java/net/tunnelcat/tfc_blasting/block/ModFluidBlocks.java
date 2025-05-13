package net.tunnelcat.tfc_blasting.block;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.fluid.ModFluids;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;
import net.tunnelcat.tfc_blasting.item.ModFluidItems;

public class ModFluidBlocks {
    public static final DeferredRegister<Block> FLUID_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCBlasting.MOD_ID);

    public static final Map<SimpleFluid, RegistryObject<LiquidBlock>> SIMPLE_FLUIDS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            registerNoItem("fluid/" + fluid.getSerializedName(), () -> new LiquidBlock(ModFluids.SIMPLE_FLUIDS.get(fluid).source(), Properties.copy(Blocks.WATER).noLootTable()))
    );

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(FLUID_BLOCKS, ModFluidItems.FLUID_ITEMS, name, blockSupplier, blockItemFactory);
    }
}