package net.tunnelcat.tfc_blasting.block;

import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.dries007.tfc.common.blockentities.BarrelBlockEntity;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.dries007.tfc.common.items.BarrelBlockItem;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.blockentity.AcidBarrelBlockEntity;
import net.tunnelcat.tfc_blasting.blockentity.TFCBlastingBlockEntities;
import net.tunnelcat.tfc_blasting.fluid.TFCBlastingFluids;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;
import net.tunnelcat.tfc_blasting.item.TFCBlastingItems;

public class TFCBlastingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCBlasting.MOD_ID);

//    public static final RegistryObject<BarrelBlock> BARREL_GOLD = registerBarrel("metal/barrel/gold");
    public static enum BarrelMetals {gold};
    public static final Map<BarrelMetals, RegistryObject<BarrelBlock>> BARRELS = Helpers.mapOfKeys(BarrelMetals.class, barrel -> registerBarrel("metal/barrel/" + barrel.name()));

    // Register liquid blocks
    public static final Map<SimpleFluid, RegistryObject<LiquidBlock>> SIMPLE_FLUIDS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            registerNoItem("fluid/" + fluid.getId(), () -> new LiquidBlock(TFCBlastingFluids.SIMPLE_FLUIDS.get(fluid).source(), Properties.copy(Blocks.WATER))));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return RegistrationHelpers.registerBlock(BLOCKS, TFCBlastingItems.ITEMS, name, block, blockItemFactory);
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return register(name, block, (Function<T, ? extends BlockItem>) null);
    }

    private static RegistryObject<BarrelBlock> registerBarrel(String name) {
        return RegistrationHelpers.<BarrelBlock>registerBlock(
                TFCBlastingBlocks.BLOCKS,
                TFCBlastingItems.ITEMS,
                (name).toLowerCase(Locale.ROOT),
                () -> new BarrelBlock(ExtendedProperties.of(Metal.Default.STEEL.mapColor())
                        .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                        .strength(2.0F, 3.0F)
                        .sound(SoundType.METAL)
                        .noOcclusion()
                        .blockEntity(TFCBlockEntities.BARREL)
                        .serverTicks(BarrelBlockEntity::serverTick))
                , (block) -> new BarrelBlockItem(block,  new net.minecraft.world.item.Item.Properties())
        );
    }
}