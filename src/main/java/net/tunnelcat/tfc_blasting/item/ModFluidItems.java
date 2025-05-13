package net.tunnelcat.tfc_blasting.item;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.dries007.tfc.util.Helpers;
import net.tunnelcat.tfc_blasting.TFCBlasting;
import net.tunnelcat.tfc_blasting.fluid.ModFluids;
import net.tunnelcat.tfc_blasting.fluid.SimpleFluid;

public class ModFluidItems
{
    public static final DeferredRegister<Item> FLUID_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCBlasting.MOD_ID);

//    public static final RegistryObject<Item> BEEHIVE_FRAME = register("beehive_frame", () -> new BeehiveFrameItem(prop()));
//    public static final RegistryObject<Item> BEESWAX = register("beeswax", () -> new HoneycombItem(prop()));
//    public static final RegistryObject<Item> CINNAMON_BARK = register("cinnamon_bark");
//    public static final RegistryObject<Item> CHEESECLOTH = register("cheesecloth");
//    public static final RegistryObject<Item> FRUIT_LEAF = register("fruit_leaf");
//    public static final RegistryObject<Item> HOLLOW_SHELL = register("hollow_shell",  () -> new HollowShellItem(prop(), FLConfig.SERVER.hollowShellCapacity, FLTags.Fluids.USABLE_IN_HOLLOW_SHELL, false, false));
//    public static final RegistryObject<Item> WINE_GLASS = register("wine_glass",  () -> new WineGlassItem(prop(), FLConfig.SERVER.wineGlassCapacity, FLTags.Fluids.USABLE_IN_WINE_GLASS));
//    public static final RegistryObject<Item> ICE_SHAVINGS = register("ice_shavings");
//    public static final RegistryObject<Item> OVEN_INSULATION = register("oven_insulation", () -> new PeelItem(prop()));
//    public static final RegistryObject<Item> PEEL = register("peel", () -> new PeelItem(prop()));
//    public static final RegistryObject<Item> PIE_PAN = register("pie_pan");
//    public static final RegistryObject<Item> PINEAPPLE_FIBER = register("pineapple_fiber");
//    public static final RegistryObject<Item> PINEAPPLE_LEATHER = register("pineapple_leather");
//    public static final RegistryObject<Item> PINEAPPLE_YARN = register("pineapple_yarn");
//    public static final RegistryObject<Item> POTTERY_SHERD = register("pottery_sherd");
//    public static final RegistryObject<Item> RAW_HONEY = register("raw_honey");
//    public static final RegistryObject<Item> REINFORCED_GLASS = register("reinforced_glass");
//    public static final RegistryObject<Item> RENNET = register("rennet");
//    public static final RegistryObject<Item> SEED_BALL = register("seed_ball", () -> new SeedBallItem(prop()));
//    public static final RegistryObject<Item> SPOON = register("spoon");
//    public static final RegistryObject<Item> SPRINKLER = register("sprinkler", () -> new SprinklerItem(FLBlocks.SPRINKLER.get(), FLBlocks.FLOOR_SPRINKLER.get(), prop()));
//    public static final RegistryObject<Item> STAINLESS_STEEL_JAR_LID = register("stainless_steel_jar_lid");
//    public static final RegistryObject<Item> EMPTY_JAR_WITH_STAINLESS_STEEL_LID = register("empty_jar_with_stainless_steel_lid");
//    public static final RegistryObject<Item> TREATED_LUMBER = register("treated_lumber");
//    public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(prop().defaultDurability(20)));
//    public static final RegistryObject<Item> HEMATITIC_WINE_BOTTLE = register("hematitic_wine_bottle", () -> new FilledWineBottleItem(prop(), FLHelpers.identifier("block/hematitic_wine_bottle")));
//    public static final RegistryObject<Item> VOLCANIC_WINE_BOTTLE = register("volcanic_wine_bottle", () -> new FilledWineBottleItem(prop(), FLHelpers.identifier("block/volcanic_wine_bottle")));
//    public static final RegistryObject<Item> OLIVINE_WINE_BOTTLE = register("olivine_wine_bottle", () -> new FilledWineBottleItem(prop(), FLHelpers.identifier("block/olivine_wine_bottle")));
//    public static final RegistryObject<Item> EMPTY_HEMATITIC_WINE_BOTTLE = register("empty_hematitic_wine_bottle", () -> new WineBottleItem(prop(), FLHelpers.identifier("block/empty_hematitic_wine_bottle")));
//    public static final RegistryObject<Item> EMPTY_VOLCANIC_WINE_BOTTLE = register("empty_volcanic_wine_bottle", () -> new WineBottleItem(prop(), FLHelpers.identifier("block/empty_volcanic_wine_bottle")));
//    public static final RegistryObject<Item> EMPTY_OLIVINE_WINE_BOTTLE = register("empty_olivine_wine_bottle", () -> new WineBottleItem(prop(), FLHelpers.identifier("block/empty_olivine_wine_bottle")));
//    public static final RegistryObject<Item> CORK = register("cork");
//    public static final RegistryObject<Item> BARREL_STAVE = register("barrel_stave");
//    public static final RegistryObject<Item> BOTTLE_LABEL = register("bottle_label");
//    public static final RegistryObject<Item> RED_GRAPE_SEEDS = register("seeds/red_grape", () -> new GrapeSeedItem(new Item.Properties(), FLBlocks.GRAPE_STRING_PLANT_RED));
//    public static final RegistryObject<Item> WHITE_GRAPE_SEEDS = register("seeds/white_grape", () -> new GrapeSeedItem(new Item.Properties(), FLBlocks.GRAPE_STRING_PLANT_WHITE));

    public static final Map<SimpleFluid, RegistryObject<BucketItem>> SIMPLE_FLUID_BUCKETS = Helpers.mapOfKeys(SimpleFluid.class, fluid ->
            register("bucket/" + fluid.getSerializedName(), () -> new BucketItem(ModFluids.SIMPLE_FLUIDS.get(fluid).source(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)))
    );

    private static Item.Properties prop()
    {
        return new Item.Properties();
    }

    private static Item.Properties foodProperties()
    {
        return new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3f).build());
    }

    private static RegistryObject<Item> register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return FLUID_ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}