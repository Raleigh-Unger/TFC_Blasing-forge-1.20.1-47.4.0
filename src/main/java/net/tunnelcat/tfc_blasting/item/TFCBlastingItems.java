package net.tunnelcat.tfc_blasting.item;

import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;
import net.tunnelcat.tfc_blasting.fluid.TFCBlastingFluids;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TFCBlastingItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCBlasting.MOD_ID);

    public static final RegistryObject<Item> DYNAMITE               = ITEMS.register("dynamite"                 ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLAY_ROD               = ITEMS.register("clay_rod"                 ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLAY_ROD_NITROGLYCERIN = ITEMS.register("clay_rod_nitroglycerin"   ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUSE_CAP_COPPER        = ITEMS.register("fuse_cap_copper"          ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUSE_CAP_COPPER_EMPTY  = ITEMS.register("fuse_cap_copper_empty"    ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUSE_CAP_TIN           = ITEMS.register("fuse_cap_tin"             ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUSE_CAP_TIN_EMPTY     = ITEMS.register("fuse_cap_tin_empty"       ,() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUSE                   = ITEMS.register("fuse"                     ,() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAR_DRILL_IRON        = ITEMS.register("star_drill_iron"          ,() -> new StarDrillItem(
            new Item.Properties().stacksTo(1).durability(100).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> STAR_DRILL_STEEL       = ITEMS.register("star_drill_steel"         ,() -> new StarDrillItem(
                    new Item.Properties().stacksTo(1).durability(250).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> STAR_DRILL_BLACK_STEEL = ITEMS.register("star_drill_black_steel"   , () -> new StarDrillItem(
                    new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> STAR_DRILL_BLUE_STEEL  = ITEMS.register("star_drill_blue_steel"    ,() -> new StarDrillItem(
                    new Item.Properties().stacksTo(1).durability(1000).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> STAR_DRILL_RED_STEEL   = ITEMS.register("star_drill_red_steel"     ,() -> new StarDrillItem(
                    new Item.Properties().stacksTo(1).durability(1000).rarity(Rarity.EPIC)));

    // Creates registrations for each fluid's corresponding filled bucket
    public static final Map<SimpleFluid, RegistryObject<BucketItem>> SIMPLE_FLUID_BUCKETS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            register("bucket/" + fluid.getId(), () -> new BucketItem(TFCBlastingFluids.SIMPLE_FLUIDS.get(fluid).source(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)))
    );

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
